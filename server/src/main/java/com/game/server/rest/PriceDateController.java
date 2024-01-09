package com.game.server.rest;

import com.game.server.entity.Item;
import com.game.server.entity.PriceDate;
import com.game.server.service.ItemService;
import com.game.server.service.PriceDateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/price_dates")
@AllArgsConstructor
public class PriceDateController {

    private PriceDateService priceDateService;
    private final ItemService itemService;
    @PostMapping("")
    public ResponseEntity<?> uploadPriceDatesData(@RequestParam("file")MultipartFile file){

        this.priceDateService.savePriceDatesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","PriceDates data uploaded and saved to database successfully"));
    }


    @GetMapping
    public ResponseEntity<List<PriceDate>> getPriceDates(){

        return new ResponseEntity<>(priceDateService.findAll(), HttpStatus.FOUND);

    }

    @GetMapping("/{itemId}")
    public Integer getCurrentPrice(@PathVariable Long itemId) {
        Item item = itemService.findById(itemId);
        return item.getCurrentPriceDate().getPrice();
    }


}
