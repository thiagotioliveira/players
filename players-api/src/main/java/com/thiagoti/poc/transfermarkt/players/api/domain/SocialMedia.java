package com.thiagoti.poc.transfermarkt.players.api.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class SocialMedia implements Serializable {

  private String url;

  private String name;

}
