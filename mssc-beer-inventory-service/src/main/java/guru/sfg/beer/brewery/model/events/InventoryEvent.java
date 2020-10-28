package guru.sfg.beer.brewery.model.events;

import guru.sfg.beer.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryEvent extends BeerEvent {
    public InventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
