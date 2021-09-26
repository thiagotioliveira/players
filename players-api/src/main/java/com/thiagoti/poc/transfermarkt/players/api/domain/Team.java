package com.thiagoti.poc.transfermarkt.players.api.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {

  private Long id;

  private String name;

  private String mainLeague;

}
