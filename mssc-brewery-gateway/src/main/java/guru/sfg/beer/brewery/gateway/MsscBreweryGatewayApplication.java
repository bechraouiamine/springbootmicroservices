package guru.sfg.beer.brewery.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by aminebechraoui, on 15/12/2020, in guru.sfg.beer.brewery.gateway
 */
@EnableEurekaClient
@SpringBootApplication
public class MsscBreweryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryGatewayApplication.class, args);
	}

}
