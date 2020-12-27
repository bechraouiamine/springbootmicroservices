package guru.sfg.beer.inventory.failover.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by aminebechraoui, on 27/12/2020, in guru.sfg.beer.inventory.failover.config
 */
@Profile("local-discovery")
@EnableEurekaClient
@Configuration
public class LocalDiscovery {
}
