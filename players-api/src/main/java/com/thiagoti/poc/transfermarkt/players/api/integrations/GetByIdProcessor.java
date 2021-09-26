package com.thiagoti.poc.transfermarkt.players.api.integrations;

import com.thiagoti.poc.transfermarkt.players.api.domain.GetById;

public interface GetByIdProcessor {

  public static final String PARAM_ID = "{id}";

  GetById process(Long id);

}
