package com.game.server.rest.dto;

import lombok.Data;

@Data
public class SpecialityRequest {

    private String specialityName;

    private String description;

    private String symbol;

    private int powerAmount;

    private Long itemId;

}
