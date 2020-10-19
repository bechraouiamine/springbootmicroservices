package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public class InventoryEvent extends BeerEvent {
    InventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
