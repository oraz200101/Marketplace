package com.example.marketplace.service.impl;

import com.example.marketplace.model.entity.Bucket;
import com.example.marketplace.model.entity.Product;
import com.example.marketplace.model.entity.User;
import com.example.marketplace.model.repository.BucketRepository;
import com.example.marketplace.model.repository.ProductRepository;
import com.example.marketplace.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;

    @Autowired
    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
    }

    @Override
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket=new Bucket();
        bucket.setUser(user);
        List<Product>products=getCollectRefProductsById(productIds);
        bucket.setProducts(products);
        return bucketRepository.save(bucket);
    }

    @Override
    public Bucket addProductToBucket(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsById(productIds));
        bucket.setProducts(newProductList);
        return bucketRepository.save(bucket);
    }



    private List<Product> getCollectRefProductsById(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

}
