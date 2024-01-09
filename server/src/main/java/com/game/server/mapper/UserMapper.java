package com.game.server.mapper;

import com.game.server.entity.Item;
import com.game.server.entity.Speciality;
import com.game.server.entity.Purchase;
import com.game.server.entity.Speciality;
import com.game.server.entity.User;
import com.game.server.rest.dto.CustomUserDto;
import com.game.server.rest.dto.ItemDto;
import com.game.server.rest.dto.SpecialityDto;
import com.game.server.rest.dto.PurchaseDto;
import com.game.server.rest.dto.UserDto;

public interface UserMapper {
    UserDto toUserDto(User user);

    CustomUserDto toCustomUserDto(User user);

    ItemDto toItemDto(Item item);

    PurchaseDto toPurchaseDto(Purchase purchase);

    SpecialityDto toSpecialityDto(Speciality speciality);
}
