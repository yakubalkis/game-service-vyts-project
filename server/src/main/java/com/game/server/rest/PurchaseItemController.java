package com.game.server.rest;

import com.game.server.entity.Item;
import com.game.server.entity.Purchase;
import com.game.server.rest.dto.PurchaseRequest;
import com.game.server.service.ItemService;
import com.game.server.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping
    public String setItemToPurchase(@RequestBody PurchaseRequest request) {
        // TO-DO: satin alinan item, userin item listesine eklencek ve kaydedilecek
        // Cüzdan bakiye kontrolü yapilmali!!
        Item item = itemService.findById(request.getItemId());
        Purchase purchase = purchaseService.findById(request.getPurchaseId());

        purchase.addItem(item);
        purchaseService.savePurchase(purchase);

        return "Process is successful";
    }

    @DeleteMapping
    public String deleteItemfromPurchase(@RequestBody PurchaseRequest request) {
        Item item = itemService.findById(request.getItemId());
        Purchase purchase = purchaseService.findById(request.getPurchaseId());


        purchase.deleteItem(item);
        purchaseService.savePurchase(purchase);

        return "Process is successful";
    }
}
