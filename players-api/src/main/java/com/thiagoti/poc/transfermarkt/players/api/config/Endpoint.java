package com.thiagoti.poc.transfermarkt.players.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties
public class Endpoint {
	
	private String url;
	private String httpMethod;
}
