package guru.sfg.beer.service.services.brewing;

import guru.sfg.beer.brewery.model.BeerDto;
import guru.sfg.beer.brewery.model.events.BrewBeerEvent;
import guru.sfg.beer.brewery.model.events.InventoryEvent;
import guru.sfg.beer.service.config.JmsConfig;
import guru.sfg.beer.service.domain.Beer;
import guru.sfg.beer.service.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;


    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent) {
        BeerDto beerDto = brewBeerEvent.getBeerDto();

        Beer beer = beerRepository.findByUpc(beerDto.getUpc());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        InventoryEvent inventoryEvent = new InventoryEvent(beerDto);

        log.debug("Brewed Beer : " + beer.getMinOnHand() + " QOH : " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.INVENTORY_QUEUE, inventoryEvent);
    }
}
