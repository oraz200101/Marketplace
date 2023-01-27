package com.example.marketplace.controller;

import com.example.marketplace.exception.domain.BodyObjectIdDoesNotMatchRequestIdCustomException;
import com.example.marketplace.model.dto.*;
import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.Product;
import com.example.marketplace.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.example.marketplace.constants.swagger.ProductSwaggerConstants.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = NAME_OF_PRODUCT_CONTROLLER,description = DESCRIPTION_OF_PRODUCT_CONTROLLER)
public class ProductController {
    private final ProductServiceImpl productService;



    @PostMapping("/categoriesId/{categoryIds}")
    @Operation(description = DESCRIPTION_OF_API_FOR_CREATING_PRODUCT)
    public ResponseEntity<Product> create(@Validated @RequestBody ProductCreateDto productCreateDto,
                                          @PathVariable List<Long> categoryIds){
        return ResponseEntity.ok(productService.create(productCreateDto,categoryIds));
    }
    @GetMapping("/all/{pageNumber}")
    @Operation(description = DESCRIPTION_OF_API_FOR_ALL_PRODUCT)
    public ResponseEntity<Page<Product>>getAll(@PageableDefault(size = 10) Pageable pageable, @PathVariable int pageNumber){
        return ResponseEntity.ok(productService.getAll(pageable,pageNumber));
    }

    @PostMapping("/filter/{pageNumber}")
    @Operation(description = DESCRIPTION_OF_API_FOR_SEARCH_PRODUCT)
    public ResponseEntity<Page<Product>>searchWithFilter(@RequestBody SearchRequestDto searchRequestDto,@PageableDefault(size = 10) Pageable pageable,
                                                         @PathVariable int pageNumber){
        return ResponseEntity.ok(productService.searchWithFilter(searchRequestDto,pageable,pageNumber));
    }

    @PostMapping("/{id}/bucket")
    @Operation(description = DESCRIPTION_OF_API_FOR_ADD_PRODUCT_TO_BUCKET)
    public ResponseEntity<String> addToUserBucket(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(productService.addToUserBucket(id,principal.getName()));
    }
    @PutMapping("/{id}")
    @Operation(description = DESCRIPTION_OF_API_FOR_UPDATE_PRODUCT)
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto){
        if(!productUpdateDto.getId().equals(id))
            throw new BodyObjectIdDoesNotMatchRequestIdCustomException();
        return ResponseEntity.ok(productService.update(productUpdateDto));
    }

    @GetMapping("/{id}")
    @Operation(description = DESCRIPTION_OF_API_FOR_PRODUCT_BY_ID)
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }


}
