package com.thiagoti.poc.transfermarkt.players.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

	@Bean
	@ConfigurationProperties(prefix = "app.endpoint.get-by-name")
	public Endpoint getByNameEndpoint() {
		return new Endpoint();
	}
	
  @Bean
  @ConfigurationProperties(prefix = "app.endpoint.get-by-id")
  public Endpoint getByIdEndpoint() {
    return new Endpoint();
  }

}
