package com.game.server.mapper;

import com.game.server.entity.Item;
import com.game.server.entity.User;
import com.game.server.rest.dto.CustomUserDto;
import com.game.server.rest.dto.ItemDto;
import com.game.server.rest.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    @Override
    public CustomUserDto toCustomUserDto(User user) {
        if(user == null) {
            return null;
        }
        return new CustomUserDto(user.getId(), user.getUsername(),
                user.getEmail(), user.getPhoneNumber(), user.getRole(),
                user.getDateOfJoin(), user.getBudget().getCurrentGameMoney(),
                user.getLevelPointOfUser(), user.getLevel().getLevelName(),
                user.getRankPointOfUser(), user.getRank().getRankName());
    }

    @Override
    public ItemDto toItemDto(Item item) {
        if(item == null) {
            return null;
        }
        if(item.getCategory()==null) {
            return new ItemDto(item.getId(), item.getItemName(), item.getSymbol(), null);
        }
        return new ItemDto(item.getId(), item.getItemName(), item.getSymbol(), item.getCategory().getCategoryName());
    }
}
