package guru.sfg.beer.service.repositories;

import guru.sfg.beer.brewery.model.BeerStyleEnum;
import guru.sfg.beer.service.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}
