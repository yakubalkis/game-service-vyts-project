package com.game.server.rest.dto;

import com.game.server.entity.Item;
import com.game.server.entity.Purchase;
import com.game.server.service.ItemService;
import com.game.server.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseItemController {
    private final PurchaseService purchaseService;
    private final ItemService itemService;

    @PostMapping
    public String setItemToPurchase(@RequestBody PurchaseRequest request) {

        Item item = itemService.findById(request.getId());
        Purchase purchase = purchaseService.findById(request.getPurchaseId());

        purchase.addItem(item);
        purchaseService.savePurchase(purchase);

        return "Process is successful";
    }

    @DeleteMapping
    public String deleteItemfromPurchase(@RequestBody PurchaseRequest request) {
        Item item = itemService.findById(request.getId());
        Purchase purchase = purchaseService.findById(request.getPurchaseId());


        purchase.deleteItem(item);
        purchaseService.savePurchase(purchase);

        return "Process is successful";
    }
}
