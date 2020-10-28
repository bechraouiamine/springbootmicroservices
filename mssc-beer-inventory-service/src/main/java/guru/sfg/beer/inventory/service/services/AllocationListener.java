package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.brewery.model.events.AllocateOrderRequest;
import guru.sfg.beer.brewery.model.events.AllocateOrderResult;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by BECHRAOUI, 28/10/2020
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AllocationListener {
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {
        AllocateOrderResult.AllocateOrderResultBuilder allocateOrderResultBuilder = AllocateOrderResult.builder();

        allocateOrderResultBuilder.beerOrderDto(request.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());
            if (allocationResult) {
                allocateOrderResultBuilder.pendingInventory(false);
            } else {
                allocateOrderResultBuilder.pendingInventory(true);
            }
        } catch (Exception e) {
            allocateOrderResultBuilder.allocationError(true);
            log.info("Allocation order : " + request.getBeerOrderDto().getId() + " Error");
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, allocateOrderResultBuilder.build());

        log.debug("Allocation order : " + request.getBeerOrderDto().getId() + " success !! ");
    }
}
