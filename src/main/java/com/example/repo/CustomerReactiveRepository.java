package com.example.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CustomerEntity;

@Repository
public interface CustomerReactiveRepository
    extends ReactiveMongoRepository<CustomerEntity, String> {}