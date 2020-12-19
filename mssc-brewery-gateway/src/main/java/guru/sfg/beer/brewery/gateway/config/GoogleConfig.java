package guru.sfg.beer.brewery.gateway.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by aminebechraoui, on 15/12/2020, in guru.sfg.beer.brewery.gateway.config
 */
//@Profile("google")
//@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/googlesearch2")
                        .filters(f-> f.rewritePath("/googlesearch2(?<segment>/?.*)", "/${segment}"))
                        .uri("https://google.com")
                        .id("google"))
                .build();
    }
}
