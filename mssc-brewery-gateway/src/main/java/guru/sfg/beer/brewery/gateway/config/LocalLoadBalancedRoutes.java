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
                        .uri("lb://inventory-service")
                        .id("inventory-service"))
                .build();
    }

    @Bean
    public RouteLocator localDiscoveryCustomerAPIRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route.path("/api/v1/customers*", "/api/v1/customers/*", "/api/v1/customers/**")
                        .uri("lb://order-service")
                        .id("order-service"))
                .build();
    }
}
