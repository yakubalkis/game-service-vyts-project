package com.game.server.rest.dto;

import lombok.Data;

@Data
public class RiseRequest {

    Integer point;
    Integer currentLevelName;
    String currentRankName;
}
