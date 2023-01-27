package com.example.marketplace.service;

import com.example.marketplace.model.dto.OrderCreateDto;
import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.Order;

public interface OrderService {
    Order create(String username, OrderCreateDto orderCreateDto);
}
