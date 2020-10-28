package guru.sfg.beer.service.services.brewing;

import guru.sfg.beer.brewery.model.events.BrewBeerEvent;
import guru.sfg.beer.service.config.JmsConfig;
import guru.sfg.beer.service.domain.Beer;
import guru.sfg.beer.service.repositories.BeerRepository;
import guru.sfg.beer.service.services.inventory.BeerInventoryService;
import guru.sfg.beer.service.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {

        Iterable<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer beerInventory = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min on hand is : " + beer.getMinOnHand());
            log.debug("Inventory is : " + beerInventory);

            if (beerInventory < beer.getMinOnHand()) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });

    }

}
