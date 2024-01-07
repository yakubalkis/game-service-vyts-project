package com.game.server.rest;

import com.game.server.entity.Budget;
import com.game.server.entity.Item;
import com.game.server.entity.Purchase;
import com.game.server.entity.User;
import com.game.server.rest.dto.PurchaseRequest;
import com.game.server.service.ItemService;
import com.game.server.service.PurchaseService;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseItemController {
    private final PurchaseService purchaseService;
    private final ItemService itemService;
    private final UserService userService;

    @PostMapping
    public String setItemToPurchase(@RequestBody PurchaseRequest request) {

        User user = userService.getUserByUsername(request.getUsername());
        Item item = itemService.findById(request.getItemId());
        Purchase purchase = new Purchase();

        // getCurrentPriceDate fonksiyonu yazildiktan sonra sag kosul düzeltilcek, itemin fiyati ile karsilastirilmali
        if(user.getBudget().getCurrentGameMoney() >= 1) {
            user.getInventory().addItem(item); // cantaya ekle

            Budget budget = user.getBudget(); // cüzdan bakiyesinden düş
            budget.setCurrentGameMoney(user.getBudget().getCurrentGameMoney() - 10);
            user.setBudget(budget);

            purchase.addItem(item); // satin alima ekle
            user.addPurchase(purchase); // user ile satin_alim iliski kur
            userService.saveUser(user);
            purchaseService.savePurchase(purchase);
            return "Purchase process is successful.";
        }
        else {
            throw new RuntimeException("Current Money is not enough to buy this item.");
        }
}
}