package com.game.server.rest;

import com.game.server.entity.Category;


import com.game.server.entity.Item;
import com.game.server.rest.dto.CategoryRequest;
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


@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<?> uploadCategoriesData(@RequestParam("file") MultipartFile file){

        this.categoryService.saveCategoriesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Categories data uploaded and saved to database successfully"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Item item = itemService.findById(categoryRequest.getItemId());
        Category category = new Category(categoryRequest.getCategoryName(), categoryRequest.getSymbol());
        category.addItem(item);

        categoryService.save(category);
        return ResponseEntity.ok(Map.of("Message","Added Category to database successfully"));
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.findAll();
    }

}
