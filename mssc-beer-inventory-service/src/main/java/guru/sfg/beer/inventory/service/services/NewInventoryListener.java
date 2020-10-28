package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.brewery.model.events.InventoryEvent;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.INVENTORY_QUEUE)
    public void listen(InventoryEvent inventoryEvent) {

        log.debug("Got Inventory : " + inventoryEvent.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(inventoryEvent.getBeerDto().getId())
                .upc(inventoryEvent.getBeerDto().getUpc())
                .quantityOnHand(inventoryEvent.getBeerDto().getQuantityOnHand())
                .build());
    }
}
