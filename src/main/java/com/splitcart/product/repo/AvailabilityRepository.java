package com.splitcart.product.repo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.splitcart.product.api.model.Availability;

import java.util.List;

@Repository
public interface AvailabilityRepository extends MongoRepository<Availability, String> {
	List<Availability> findAll();

}

