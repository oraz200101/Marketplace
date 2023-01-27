package com.example.marketplace.controller;

import com.example.marketplace.exception.domain.BodyObjectIdDoesNotMatchRequestIdCustomException;
import com.example.marketplace.exception.domain.CategoryNotFoundByIdException;
import com.example.marketplace.model.dto.CreateCategoryDto;
import com.example.marketplace.model.dto.CategoryUpdateDto;
import com.example.marketplace.model.entity.Category;
import com.example.marketplace.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.marketplace.constants.swagger.CategorySwaggerConstants.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = NAME_OF_CATEGORY_CONTROLLER, description = DESCRIPTION_OF_CATEGORY_CONTROLLER)
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/all/{pageNumber}")
    @Operation(description = DESCRIPTION_OF_API_GET_ALL_CATEGORY)
    public ResponseEntity<Page<Category>> getAll(@PageableDefault(size = 20) Pageable pageable, @PathVariable int pageNumber){
        return ResponseEntity.ok(categoryService.getAll(pageable,pageNumber));
    }
    @PostMapping
    @Operation(description = DESCRIPTION_OF_API_FOR_CREATING_CATEGORY)
    public ResponseEntity<Category> create(@RequestBody @Validated CreateCategoryDto createCategoryDto){
        return ResponseEntity.ok(categoryService.create(createCategoryDto));
    }
    @PutMapping("/{id}")
    @Operation(description = DESCRIPTION_OF_API_FOR_UPDATE_CATEGORY)
    public ResponseEntity<Category>update(@RequestBody @Validated CategoryUpdateDto updateCategoryDto, @PathVariable Long id){
        if(!updateCategoryDto.getId().equals(id))
            throw new BodyObjectIdDoesNotMatchRequestIdCustomException();
        if(!categoryService.existsById(id))
            throw new CategoryNotFoundByIdException();
        return ResponseEntity.ok(categoryService.update(updateCategoryDto));

    }
    @GetMapping("/{id}")
    @Operation(description = DESCRIPTION_OF_API_FOR_GET_BY_ID)
    public ResponseEntity<Category> findById(@PathVariable Long id){
        if(categoryService.existsById(id))
            throw new CategoryNotFoundByIdException();

        return ResponseEntity.ok(categoryService.getById(id));
    }


}
