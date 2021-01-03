package guru.sfg.beer.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by aminebechraoui, on 22/12/2020, in guru.sfg.beer.brewery.gateway.config
 */
@Profile("local-discovery")
@Configuration
public class LocalLoadBalancedRoutes {

    @Bean
    public RouteLocator localDiscoveryBeerAPIRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .uri("lb://beer-service")
                        .id("beer-service"))
                .route(route -> route.path("/api/v1/beer/*/inventory*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("inventoryCircuitBreaker")
                                .setFallbackUri("forward:/inventory-failover")
                                .setRouteId("inv-failover") ))
                        .uri("lb://inventory-service")
                        .id("inventory-service"))
                .route(r -> r.path("/inventory-failover/**", "/inventory-failover*")
                        .uri("lb://inventory-failover")
                        .id("inventory-failover-service"))
                .route(route -> route.path("/api/v1/customers*", "/api/v1/customers/*", "/api/v1/customers/**")
                        .uri("lb://beer-order-service")
                        .id("beer-order-service"))
                .build();
    }

}
