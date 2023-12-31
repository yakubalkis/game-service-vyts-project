package com.game.server.rest;

import com.game.server.entity.Item;
import com.game.server.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;
    @PostMapping("")
    public ResponseEntity<?> uploadItemsData(@RequestParam("file")MultipartFile file){

        this.itemService.saveItemsToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Items data uploaded and saved to database successfully"));
    }


    @GetMapping
    public List<Item> getItems(){ // TO-DO: burada currentPriceDate dönüyo ama onun yerine current price dönmeli
                                    // price isi sonrasi güncellenecek, gerekirse dto yazilsin
        return itemService.findAll();
    }


    @GetMapping("/inventory-user-counts")
    public List<Object[]> getInventoryUserCountsGroupByItem() {
        return itemService.getInventoryUserCountsGroupByItem();
    }

}
