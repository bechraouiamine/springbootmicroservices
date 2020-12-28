package guru.sfg.beer.service.services.inventory;

import guru.sfg.beer.service.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by aminebechraoui, on 28/12/2020, in guru.sfg.beer.service.services.inventory
 */
@FeignClient("inventory-failover")
public interface InventoryFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();

}
