package guru.sfg.beer.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aminebechraoui, on 19/12/2020, in guru.sfg.beer.brewery.gateway.config
 */
@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator localHostBeerAPIRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                .uri("http://localhost:8080")
                .id("beer-service"))
                .build();
    }

    @Bean
    public RouteLocator localHostCustomerAPIRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route.path("/api/v1/customers*", "/api/v1/customers/*", "/api/v1/customers/**")
                        .uri("http://localhost:8081")
                        .id("customer-service"))
                .build();
    }

}
