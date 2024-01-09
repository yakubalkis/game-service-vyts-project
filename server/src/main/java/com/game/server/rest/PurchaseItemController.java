package com.game.server.rest;

import com.game.server.entity.Budget;
import com.game.server.entity.Item;
import com.game.server.entity.Log;
import com.game.server.entity.Purchase;
import com.game.server.entity.User;
import com.game.server.mapper.UserMapper;
import com.game.server.rest.dto.PurchaseDto;
import com.game.server.rest.dto.PurchaseRequest;
import com.game.server.service.ItemService;
import com.game.server.service.PurchaseService;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
public class PurchaseItemController {
    private final PurchaseService purchaseService;
    private final ItemService itemService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public String setItemToPurchase(@RequestBody PurchaseRequest request) {

        User user = userService.getUserByUsername(request.getUsername());
        Item item = itemService.findById(request.getItemId());
        Purchase purchase = new Purchase();

        // getCurrentPriceDate fonksiyonu yazildiktan sonra sag kosul düzeltilcek, itemin fiyati ile karsilastirilmali
        if(user.getBudget().getCurrentGameMoney() >= item.getCurrentPriceDate().getPrice()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            user.getInventory().addItem(item); // cantaya ekle

            Budget budget = user.getBudget(); // cüzdan bakiyesinden düş
            budget.setCurrentGameMoney(user.getBudget().getCurrentGameMoney() - item.getCurrentPriceDate().getPrice()* request.getAmount());
            user.setBudget(budget);
            purchase.addItem(item); // satin alima ekle
            purchase.setPurchaseDate(formattedDateTime);
            purchase.setPurchasePrice(item.getCurrentPriceDate().getPrice());
            purchase.setAmount(request.getAmount());
            user.addPurchase(purchase); // user ile satin_alim iliski kur
            //log
            String message = "User " + user.getUsername() +
                    " bought "+ request.getAmount() + " " +
                    item.getItemName() +"with price " + item.getCurrentPriceDate().getPrice() + " on " + formattedDateTime + ".";
            Log log = new Log(message,"purchase");
            user.addLog(log);
            userService.saveUser(user);
            return "Purchase process is successful.";
        }
        else {
            throw new RuntimeException("Current Money is not enough to buy this item.");
        }
    }

    @GetMapping("/all")
    public List<PurchaseDto> getAllPurchases() {

        return purchaseService.findAll()
                .stream().map(userMapper::toPurchaseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/purchase-details/{userId}")
    public ResponseEntity<List<Object[]>> getTotalPurchaseDetailsByUser(@PathVariable Long userId) {
        List<Object[]> purchaseDetails = purchaseService.getUserPurchaseDetailsByDate (userId);
        return new ResponseEntity<>(purchaseDetails, HttpStatus.OK);
    }

}