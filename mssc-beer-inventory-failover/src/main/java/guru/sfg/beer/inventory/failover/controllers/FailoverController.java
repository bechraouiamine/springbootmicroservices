package guru.sfg.beer.inventory.failover.controllers;

import guru.sfg.beer.brewery.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by aminebechraoui, on 27/12/2020, in guru.springframework.msscbeerinventoryfailover.web.controllers
 */
@Profile("!local-discovery")
@Slf4j
//@RestController
public class FailoverController {

    @GetMapping("api/v1/beer/failover")
    List<BeerInventoryDto> failOver() {
        return Arrays.asList(BeerInventoryDto.builder()
                .quantityOnHand(999)
                .beerId(new UUID(0,0))
                .build());
    }

}
