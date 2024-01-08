package com.game.server.mapper;

import com.game.server.entity.User;
import com.game.server.rest.dto.CustomUserDto;
import com.game.server.rest.dto.UserDto;

public interface UserMapper {
    UserDto toUserDto(User user);

    CustomUserDto toCustomUserDto(User user);
}
