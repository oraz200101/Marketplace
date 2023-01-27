package com.example.marketplace.service.impl;

import com.example.marketplace.model.dto.CreateCategoryDto;
import com.example.marketplace.model.dto.SearchRequestDto;
import com.example.marketplace.model.dto.CategoryUpdateDto;
import com.example.marketplace.model.entity.Category;
import com.example.marketplace.model.repository.CategoryRepository;
import com.example.marketplace.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Page<Category> getAll(Pageable pageable,int pageNumber) {
        return categoryRepository.findAll(pageable.withPage(pageNumber));
    }

    @Override
    public Page<Category> searchWithFilter(SearchRequestDto request, Pageable pageable,int pageNumber) {
        return null;
    }

    @Override
    public Category create(CreateCategoryDto createCategoryDto) {
       Category category=new Category();
       category.setName(createCategoryDto.getName());
       return save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Category update(CategoryUpdateDto updateCategoryDto) {
        Category category=categoryRepository.getById(updateCategoryDto.getId());
        modelMapper.map(updateCategoryDto, Category.class);
        return save(category);
    }

    @Override
    public Category save(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public boolean existByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return !categoryRepository.existsById(id);
    }
}
