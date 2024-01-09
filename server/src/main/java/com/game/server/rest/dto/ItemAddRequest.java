package com.game.server.rest.dto;

import lombok.Data;

@Data
public class ItemAddRequest {
    private Long categoryId;
    private String itemName;
    private String symbol;
}
