package com.example.marketplace.service;

import com.example.marketplace.model.dto.CommentCreateDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    String createComment(Long productId, CommentCreateDto commentCreateDto);
}
