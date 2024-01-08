package com.game.server.service;

import com.game.server.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    Category findById(Long id);

    void deleteById(Long id);

    public void saveCategoriesToDatabase(MultipartFile file);

}
