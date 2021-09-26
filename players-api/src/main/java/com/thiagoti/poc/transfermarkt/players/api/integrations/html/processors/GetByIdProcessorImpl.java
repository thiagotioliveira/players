package com.thiagoti.poc.transfermarkt.players.api.integrations.html.processors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.thiagoti.poc.transfermarkt.players.api.config.Endpoint;
import com.thiagoti.poc.transfermarkt.players.api.domain.Contract;
import com.thiagoti.poc.transfermarkt.players.api.domain.GetById;
import com.thiagoti.poc.transfermarkt.players.api.domain.Prince;
import com.thiagoti.poc.transfermarkt.players.api.domain.SocialMedia;
import com.thiagoti.poc.transfermarkt.players.api.domain.Team;
import com.thiagoti.poc.transfermarkt.players.api.integrations.GetByIdProcessor;
import com.thiagoti.poc.transfermarkt.players.api.integrations.HtmlParser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetByIdProcessorImpl implements GetByIdProcessor {

  private final HtmlParser htmlParser;

  private final Endpoint getByIdEndpoint;

  @Override
  public GetById process(Long id) {
    //@formatter:off
    try {
      Document document = htmlParser.get(getByIdEndpoint.getUrl().replace(PARAM_ID, id.toString()));

      Elements playerPesoalInfo = document.getElementsByClass("player-data-personal-info__content--right");
      
      GetById getById = new GetById();
      
      getById.setId(id);
      getById.setName(playerPesoalInfo.get(0).text());
      getById.setNickName(document.getElementsByClass("dataHeader dataExtended").get(0).getElementsByAttribute("itemprop").get(0).text());
      getById.setImage(document.getElementById("fotoauswahlOeffnen").getElementsByTag("img").attr("src"));
      getById.setContract(
          new Contract()
          .withTeam(
              new Team()
              .withId(Long.valueOf(playerPesoalInfo.get(9).getElementsByTag("a").attr("id")))
              .withName(playerPesoalInfo.get(9).getElementsByTag("a").get(0).getElementsByTag("img").attr("alt"))
              .withMainLeague(document.getElementsByClass("dataHeader dataExtended").get(0).getElementsByClass("mediumpunkt").get(0).getElementsByTag("img").get(1).attr("title"))
              )
          .withStartDate(playerPesoalInfo.get(10).text())
          .withEndDate(playerPesoalInfo.get(11).text())
          );
      getById.setPosition(playerPesoalInfo.get(6).text());
      getById.setAge(Integer.valueOf(playerPesoalInfo.get(3).text()));
      getById.setHeight(playerPesoalInfo.get(4).text());
      getById.setBirthDate(playerPesoalInfo.get(1).getElementsByTag("a").get(0).text());
      getById.setCountryBirth(playerPesoalInfo.get(2).getElementsByAttribute("title").get(0).getElementsByTag("img").attr("title"));
      
      String amount = document.getElementsByClass("right-td").get(0).getElementsByTag("a").size() > 0 ? 
          document.getElementsByClass("right-td").get(0).getElementsByTag("a").get(0).text() :
            document.getElementsByClass("right-td").get(0).text();
      
      getById.setPrice(new Prince()
          .withAmount(amount)
          .withLastUpdate(document.getElementsByClass("right-td").get(1).text())
          .withGreaterAmount(document.getElementsByClass("right-td").get(2).text()));
      
      String bussinessBroker = playerPesoalInfo.get(8).getElementsByTag("a").size() > 0 ?
          playerPesoalInfo.get(8).getElementsByTag("a").get(0).text() :
            playerPesoalInfo.get(8).text();
      
      getById.setBusinessBroker(bussinessBroker);
      getById.setSponsorBrand(playerPesoalInfo.get(playerPesoalInfo.size() - 2).text());
      
      Elements nationalitiesElements = playerPesoalInfo.get(5).getElementsByTag("img");
      
      for (Element nationalitiesElement : nationalitiesElements) {
        getById.getNationalities().add(nationalitiesElement.attr("title"));
      }
      
      getById.setBestFoot(playerPesoalInfo.get(7).text());
      
      Elements socialMediaElements = document.getElementsByClass("socialmedia-icons").get(0).getElementsByTag("a");
      
      for (Element element : socialMediaElements) {
        getById.getSocialMedia().add(new SocialMedia().withUrl(element.attr("href")).withName(element.attr("title")));
      }
      
      return getById;
      //@formatter:on
    } catch (Exception e) {
      throw new InternalError(e);
    }
  }

}
