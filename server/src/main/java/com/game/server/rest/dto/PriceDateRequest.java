package com.game.server.rest.dto;

import lombok.Data;

@Data
public class PriceDateRequest {

    private Long itemId;
    private int price;
    private String priceDate;
    private String priceType;
}
