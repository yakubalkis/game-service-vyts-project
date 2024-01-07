package com.game.server.rest.dto;

import lombok.Data;

@Data
public class PurchaseRequest {

    private String username;
    private Long itemId;
}
