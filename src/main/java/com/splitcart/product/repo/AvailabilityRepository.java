package com.splitcart.product.repo;



import com.splitcart.product.api.model.Availability;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends MongoRepository<Availability, String> {
  List<Availability> findAll();
}
