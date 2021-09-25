package com.thiagoti.poc.transfermarkt.players.api.integrations;

import org.jsoup.nodes.Document;

public interface HtmlParser {

	Document get(String url) throws Exception;
	
}
