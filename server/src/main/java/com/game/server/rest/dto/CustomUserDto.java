package com.game.server.rest.dto;

public record CustomUserDto(Long id,
                            String username, String email,
                            String phoneNumber, String role,
                            String dateOfJoin, Integer currentGameMoney,
                            Integer level, String levelName, Integer rank, String rankName) {
}
