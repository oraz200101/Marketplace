package com.example.marketplace.model.repository;

import com.example.marketplace.model.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket,Long> {
}
