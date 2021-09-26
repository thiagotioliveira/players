package com.thiagoti.poc.transfermarkt.players.api.integrations.html.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.thiagoti.poc.transfermarkt.players.api.config.Endpoint;
import com.thiagoti.poc.transfermarkt.players.api.domain.Player;
import com.thiagoti.poc.transfermarkt.players.api.integrations.GetByNameProcessor;
import com.thiagoti.poc.transfermarkt.players.api.integrations.HtmlParser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetByNameProcessorImpl implements GetByNameProcessor {

	private final HtmlParser htmlParser;

	private final Endpoint getByNameEndpoint;

	@Override
	public Page<Player> process(String name, Integer pageNumber){
		try {
		
			List<Player> list = new ArrayList<>();

			Document document = htmlParser.get(getByNameEndpoint.getUrl().replace(PARAM_NAME, name.replaceAll(" ", "+")).replace(PARAM_PAGE_NUMBER, Integer.valueOf(pageNumber + 1).toString()));

			Element divTarget = document.getElementsByClass("row").get(5);
			
			Element playersPage = divTarget.getElementsByTag("tbody").get(0);

			addPlayer(playersPage, list);

			return new PageImpl<>(list, PageRequest.of(pageNumber, PAGE_SIZE), Long.valueOf(divTarget.getElementsByClass("table-header").text().replaceAll("\\D", "")));
			
		} catch (Exception e) {
			throw new InternalError(e);
		}
	}

	private void addPlayer(Element root, List<Player> list) {

		Elements rowsOdd = root.getElementsByClass("odd");
		Elements rowsEven = root.getElementsByClass("even");

		Element[] array = new Element[rowsOdd.size() + rowsEven.size()];
		
		if(rowsOdd.size() > 0) {
			for (int i = 0, j = 0; i < rowsOdd.size(); i++, j += 2) {
				array[j] = rowsOdd.get(i);				
			}
		}
		if(rowsEven.size() > 0) {
			for (int i = 0, j = 1; i < rowsEven.size(); i++, j += 2) {
				array[j] = rowsEven.get(i);				
			}
		}

		Arrays.stream(array).forEach(p -> {
			Player player = new Player();

			Element imgInfo = p.getElementsByTag("td").get(0).getElementsByTag("a").get(0).getElementsByTag("img")
					.get(0);
			player.setImage(imgInfo.attr("src"));
			player.setName(imgInfo.attr("title"));
			
			player.setPosition(p.getElementsByClass("zentriert").get(0).text());
			player.setTeam(p.getElementsByClass("zentriert").get(1).getElementsByAttribute("alt").attr("alt"));
			
      String age = p.getElementsByClass("zentriert").get(2).text();
      try {
        player.setAge(Integer.valueOf(age));
      } catch (NumberFormatException e) {
        player.setAge(null);
      }

			Elements nacionalities = p.getElementsByClass("zentriert").get(3).getElementsByTag("img");
			
			nacionalities.forEach(n -> player.getNationalities().add(n.attr("title")));
			
			player.setPrice(p.getElementsByClass("rechts hauptlink").text());
			player.setBusinessBroker(p.getElementsByClass("rechts").get(1).text());
			
			list.add(player);
		});

	}

}
