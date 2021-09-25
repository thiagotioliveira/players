package com.thiagoti.poc.transfermarkt.players.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String image;
	private String team;
	private String position;
	private Integer age;
	private List<String> nationalities = new ArrayList<>();
	private String price;
	private String businessBroker;
	
}
