package com.example.marketplace.service.impl;

import com.example.marketplace.model.dto.OrderCreateDto;
import com.example.marketplace.model.entity.*;
import com.example.marketplace.model.enums.OrderStatus;
import com.example.marketplace.repository.OrderRepository;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    @Override
    public Order create(String username, OrderCreateDto orderCreateDto) {
        User user=userRepository.getUserByPhoneNumber(username);
        if(user==null){
            throw new RuntimeException("user is not found");
        }
        Bucket bucket=user.getBucket();
        if (bucket==null || bucket.getProducts().isEmpty()){
            throw new RuntimeException("bucket is empty");
        }
        Order order=new Order();
        order.setOrderStatus(OrderStatus.NEW);
        order.setUser(user);
        Map<Product,Long> productWithAmount=bucket.getProducts().stream().collect(Collectors.groupingBy(product -> product,Collectors.counting()));
        List<OrderDetails>orderDetails = productWithAmount.entrySet().stream()
                .map(pair->new OrderDetails(order,pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());
        BigDecimal total=new BigDecimal(orderDetails.stream()
                .map(detail->detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());
        order.setOrderDetails(orderDetails);
        order.setSum(total);
        order.setAddress(orderCreateDto.getAddress());
        bucket.getProducts().clear();
        return orderRepository.save(order);
    }
}
