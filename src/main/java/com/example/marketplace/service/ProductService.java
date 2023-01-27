package com.example.marketplace.service;

import com.example.marketplace.model.dto.CommentCreateDto;
import com.example.marketplace.model.dto.ProductCreateDto;
import com.example.marketplace.model.dto.ProductUpdateDto;
import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.Comment;
import com.example.marketplace.model.entity.Product;
import com.example.marketplace.service.base.CrudService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService extends CrudService<Product, ProductCreateDto, ProductUpdateDto> {
     Product create(ProductCreateDto productCreateDto, List<Long> categoryIds);
    String addToUserBucket(Long productId, String username);
    Bucket getBucket(String username);
}
