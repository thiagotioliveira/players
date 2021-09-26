package com.thiagoti.poc.transfermarkt.players.api.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.thiagoti.poc.transfermarkt.players.api.integrations.GetByIdProcessor;
import com.thiagoti.poc.transfermarkt.players.api.integrations.GetByNameProcessor;
import com.thiagoti.poc.transfermarkt.players.spec.PlayersApi;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayersController implements PlayersApi {

	private final GetByNameProcessor getByNameProcessor;
	
  private final GetByIdProcessor getByIdProcessor;

	@Override
	public ResponseEntity getByName(@NotNull @Valid String name, @Valid Integer pageNumber) {
		return ResponseEntity.ok(getByNameProcessor.process(name, pageNumber == null ? 0 : pageNumber));
	}

  @Override
  public ResponseEntity<Object> getById(Long id) {
    return ResponseEntity.ok(getByIdProcessor.process(id));
  }

}
