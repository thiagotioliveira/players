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
public class Prince implements Serializable {

  private String amount;

  private String lastUpdate;

  private String greaterAmount;

}
