package com.game.server.rest.dto;

import lombok.Data;

@Data
public class BudgetRequest {

    private String processType;
    private Integer amount;
}
