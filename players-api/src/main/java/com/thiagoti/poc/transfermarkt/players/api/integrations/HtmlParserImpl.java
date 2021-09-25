package com.thiagoti.poc.transfermarkt.players.api.integrations;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class HtmlParserImpl implements HtmlParser {

	@Override
	public Document get(String url) throws Exception {
		return Jsoup.connect(url).get();
	}

}
