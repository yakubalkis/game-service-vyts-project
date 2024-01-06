package com.game.server.service;

import com.game.server.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    Category findById(Long id);

    void deleteById(Long id);
}
