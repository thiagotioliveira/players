package com.thiagoti.poc.transfermarkt.players.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GetById implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String nickName;

  private String image;

  private Contract contract;

  private String position;

  private String bestFoot;

  private String birthDate;

  private String countryBirth;

  private Integer age;

  private String height;

  private List<String> nationalities = new ArrayList<>();

  private Prince price;

  private String businessBroker;

  private String sponsorBrand;

  private List<SocialMedia> socialMedia = new ArrayList<>();
}
