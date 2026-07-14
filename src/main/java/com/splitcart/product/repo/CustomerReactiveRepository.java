package com.splitcart.product.repo;

import com.splitcart.product.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReactiveRepository
    extends ReactiveMongoRepository<CustomerEntity, String> {}