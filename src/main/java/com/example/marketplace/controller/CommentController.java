package com.example.marketplace.controller;

import com.example.marketplace.model.dto.CommentCreateDto;
import com.example.marketplace.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor

public class CommentController {
    private final ProductServiceImpl productService;
    @PostMapping("/{id}/comment")
    public ResponseEntity<String> createComment(@PathVariable Long id, @RequestBody CommentCreateDto commentCreateDto){
        return ResponseEntity.ok(productService.createComment(id,commentCreateDto));
    }
}
