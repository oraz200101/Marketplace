package com.example.marketplace.controller;

import com.example.marketplace.model.dto.OrderCreateDto;
import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.Order;
import com.example.marketplace.service.impl.OrderServiceImpl;
import com.example.marketplace.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bucket")
public class BucketController {

    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;
    @GetMapping
    public ResponseEntity<Bucket> getBucket(Principal principal){
        return ResponseEntity.ok(productService.getBucket(principal.getName()));
    }
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(Principal principal, @RequestBody OrderCreateDto orderCreateDto){
        return ResponseEntity.ok(orderService.create(principal.getName(),orderCreateDto));
    }


}
