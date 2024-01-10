package com.game.server.rest.dto;

import java.util.List;

public record ItemDto(Long id, String itemName, String symbol, String categoryName, Integer currentPrice, String priceType, List<String> specialities) {
}
