package guru.sfg.commun.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryEvent extends BeerEvent {
    public InventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
