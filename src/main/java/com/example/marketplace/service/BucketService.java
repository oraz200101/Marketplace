package com.example.marketplace.service;

import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    Bucket addProductToBucket(Bucket bucket,List<Long> productIds);

}