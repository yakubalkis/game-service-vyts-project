package com.game.server.mapper;

import com.game.server.entity.Item;
import com.game.server.entity.Speciality;
import com.game.server.entity.Purchase;
import com.game.server.entity.User;
import com.game.server.rest.dto.CustomUserDto;
import com.game.server.rest.dto.ItemDto;
import com.game.server.rest.dto.SpecialityDto;
import com.game.server.rest.dto.PurchaseDto;
import com.game.server.rest.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    @Override
    public SpecialityDto toSpecialityDto(Speciality speciality) {
        if(speciality == null) {
            return null;
        }
        return new SpecialityDto(speciality.getId(),
                speciality.getSpecialityName(),speciality.getDescription(),
                speciality.getSymbol(),speciality.getPowerAmount());
    }

    @Override
    public PurchaseDto toPurchaseDto(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        if (purchase.getItems().size() > 0) {
            return new PurchaseDto(purchase.getId(), purchase.getPurchasePrice(),
                    purchase.getPurchaseDate(), purchase.getAmount(), purchase.getPriceType(),
                    purchase.getItems().get(0).getItemName(), purchase.getItems().get(0).getId(), purchase.getUser().getId());
        }
        return new PurchaseDto(purchase.getId(), purchase.getPurchasePrice(),
                purchase.getPurchaseDate(), purchase.getAmount(), purchase.getPriceType(),
                "Lost Item", 22332323L, purchase.getUser().getId());
    }
}
