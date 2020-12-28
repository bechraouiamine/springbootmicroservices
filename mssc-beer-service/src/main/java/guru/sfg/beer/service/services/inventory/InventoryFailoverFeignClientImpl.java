package guru.sfg.beer.service.services.inventory;

import guru.sfg.beer.service.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by aminebechraoui, on 28/12/2020, in guru.sfg.beer.service.services.inventory
 */
@RequiredArgsConstructor
@Component
public class InventoryFailoverFeignClientImpl implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return inventoryFailoverFeignClient.getOnHandInventory();
    }
}
