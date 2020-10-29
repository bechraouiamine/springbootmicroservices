package guru.sfg.beer.order.service.statemachine.actions;

import guru.sfg.beer.brewery.model.BeerOrderDto;
import guru.sfg.beer.brewery.model.BeerOrderEventEnum;
import guru.sfg.beer.brewery.model.BeerOrderStatusEnum;
import guru.sfg.beer.brewery.model.events.ValidateOrderRequest;
import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.repositories.BeerOrderRepository;
import guru.sfg.beer.order.service.services.BeerOrderManagerImpl;
import guru.sfg.beer.order.service.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by BECHRAOUI, 26/10/2020
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER).toString();
        Optional<BeerOrder> optionalBeerOrder = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        optionalBeerOrder.ifPresentOrElse(beerOrder -> {
            BeerOrderDto beerOrderDto = beerOrderMapper.beerOrderToDto(beerOrder);
            ValidateOrderRequest validateOrderRequest = ValidateOrderRequest.builder().beerOrderDto(beerOrderDto).build();
            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, validateOrderRequest);
        }, () -> log.error("Failed execution, beer id : " + beerOrderId));

        log.info("Sent Validation request to queue for order id : " + beerOrderId);
    }
}
