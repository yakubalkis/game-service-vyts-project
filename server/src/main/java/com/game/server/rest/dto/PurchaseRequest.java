package com.game.server.rest.dto;

import lombok.Data;

@Data
public class PurchaseRequest {

    private Long itemId;
    private Long purchaseId;
}
