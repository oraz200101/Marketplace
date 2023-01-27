package com.example.marketplace.service;

import com.example.marketplace.model.dto.CreateCategoryDto;
import com.example.marketplace.model.dto.CategoryUpdateDto;
import com.example.marketplace.model.entity.Category;
import com.example.marketplace.service.base.CrudService;

public interface CategoryService extends CrudService<Category, CreateCategoryDto, CategoryUpdateDto> {
    boolean existByName(String name);
}
