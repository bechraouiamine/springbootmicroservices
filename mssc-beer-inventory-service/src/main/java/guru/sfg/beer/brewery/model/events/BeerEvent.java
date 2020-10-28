package guru.sfg.beer.brewery.model.events;

import guru.sfg.beer.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -7360482540404645659L;

    private BeerDto beerDto;
}
