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
public class Contract implements Serializable {

  private Team team;

  private String startDate;

  private String endDate;

}
