package com.game.server.rest;

import com.game.server.entity.Category;


import com.game.server.service.CategoryService;
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
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    @PostMapping("")
    public ResponseEntity<?> uploadCategoriesData(@RequestParam("file") MultipartFile file){

        this.categoryService.saveCategoriesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Categories data uploaded and saved to database successfully"));
    }


    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){

        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.FOUND);

    }

}
