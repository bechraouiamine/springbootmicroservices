package guru.springframework.msscbeerservice.services.brewing;

import guru.sfg.commun.events.BrewBeerEvent;
import guru.sfg.commun.events.InventoryEvent;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerDto;
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
