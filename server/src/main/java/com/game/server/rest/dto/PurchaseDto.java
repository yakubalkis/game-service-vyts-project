package com.game.server.rest.dto;

import com.game.server.entity.Item;

import java.util.List;

public record PurchaseDto(Long id, int purchasePrice,
                          String purchaseDate, int amount,
                          String priceType, String boughtItemName,
                          Long boughtItemId, Long userID) {
}
