package com.game.server.service;

import com.game.server.entity.Category;

import com.game.server.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExcelImportService excelImportService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> result = categoryRepository.findById(id);
        Category category = null;

        if(result.isPresent()) {
            category = result.get();
        } else {
            throw new RuntimeException("Did not found category with id: "+ id);
        }

        return category;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void saveCategoriesToDatabase(MultipartFile file){

        if(excelImportService.isValidExcelFile(file)){
            try {
                List<Category> categories = excelImportService.getCategoriesDataFromExcel(file.getInputStream());
                this.categoryRepository.saveAll(categories);

            } catch (IOException e) {

                throw new IllegalArgumentException("The file is not a valid excel file");

            }
        }

    }

}
