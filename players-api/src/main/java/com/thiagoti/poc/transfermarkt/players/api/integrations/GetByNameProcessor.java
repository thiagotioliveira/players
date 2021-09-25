package com.thiagoti.poc.transfermarkt.players.api.integrations;

import org.springframework.data.domain.Page;

import com.thiagoti.poc.transfermarkt.players.api.domain.Player;

public interface GetByNameProcessor {

	public static final Integer PAGE_SIZE = 10;
	
	public static final String PARAM_NAME = "{name}";
	
	public static final String PARAM_PAGE_NUMBER = "{pageNumber}";
	
	Page<Player> process(String name, Integer pageNumber);
	
}
