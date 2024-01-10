package com.game.server.mapper;

import com.game.server.entity.Category;
import com.game.server.entity.Item;
import com.game.server.entity.PriceDate;
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
import java.util.List;
import java.util.stream.Collectors;

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
        Category category = null;
        List<Speciality> specialities = null;
        int price = 0;
        String priceType = "";

        if(item == null) {
            return null;
        }
        if(item.getCategory()!=null) {
            category = item.getCategory();
        }
        if(item.getSpecialities()!=null) {
            specialities = item.getSpecialities();
        }
        if(item.getCurrentPriceDate()!=null) {
            price = item.getCurrentPriceDate().getPrice();
            priceType = item.getCurrentPriceDate().getPriceType();
        }

        return new ItemDto(item.getId(), item.getItemName(), item.getSymbol(),
                category.getCategoryName(), price, priceType,
                specialities
                .stream()
                .map(speciality -> speciality.getSpecialityName())
                .collect(Collectors.toList()));
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
