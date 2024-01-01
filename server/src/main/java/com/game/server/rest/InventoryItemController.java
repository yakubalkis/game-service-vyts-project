package com.game.server.rest;

import com.game.server.entity.Item;
import com.game.server.entity.User;
import com.game.server.rest.dto.ItemRequest;
import com.game.server.service.ItemService;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class InventoryItemController { // deneme amacli!!, bu senaryo bir satin alimda gerceklesecek
                                        // satin alim gerceklesir ardindan alinan item cantaya eklenir.

    private final UserService userService;
    private final ItemService itemService;

    @PostMapping
    public String setItemToInventory(@RequestBody ItemRequest request) {

        Item item = itemService.findById(request.getId());
        User user = userService.getUserByUsername(request.getUsername());

        user.getInventory().addItem(item);
        userService.saveUser(user);

        return "Process is successful";
    }

    @DeleteMapping
    public String deleteItemfromInventory(@RequestBody ItemRequest request) {
        Item item = itemService.findById(request.getId());
        User user = userService.getUserByUsername(request.getUsername());

        user.getInventory().deleteItem(item);
        userService.saveUser(user);

        return "Process is successful";
    }

}
