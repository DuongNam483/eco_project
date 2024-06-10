package com.project.shopapp.services;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.models.Category;

import java.util.List;

public interface CategoryService {
    Category creatCategory(CategoryDTO category);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
