package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.brewery.model.events.DeallocateOrderRequest;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by aminebechraoui, on 09/12/2020, in guru.sfg.beer.inventory.service.services
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeallocationListener {

    private AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
