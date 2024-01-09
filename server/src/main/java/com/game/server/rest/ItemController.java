package com.game.server.rest;

import com.game.server.entity.Category;
import com.game.server.entity.Item;
import com.game.server.mapper.UserMapper;
import com.game.server.rest.dto.ItemAddRequest;
import com.game.server.rest.dto.ItemDto;
import com.game.server.service.CategoryService;
import com.game.server.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;
    private final UserMapper userMapper;
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> uploadItemsData(@RequestParam("file")MultipartFile file){

        this.itemService.saveItemsToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Items data uploaded and saved to database successfully"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createItem(@RequestBody ItemAddRequest request) {
        Item item = new Item(request.getItemName(), request.getSymbol());

        if(request.getCategoryId() != null) {
            Category category = categoryService.findById(request.getCategoryId());
            category.addItem(item);
        }
        itemService.save(item);
        return ResponseEntity.ok(Map.of("Message","Saved item to database successfully"));
    }

    @GetMapping
    public List<ItemDto> getItems(){ // TO-DO: burada currentPriceDate dönüyo ama onun yerine current price dönmeli
                                    // price isi sonrasi güncellenecek, gerekirse dto yazilsin
        return itemService.findAll().stream()
                .map(userMapper::toItemDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/inventory-user-counts")
    public List<Object[]> getInventoryUserCountsGroupByItem() {
        return itemService.getInventoryUserCountsGroupByItem();
    }

}
