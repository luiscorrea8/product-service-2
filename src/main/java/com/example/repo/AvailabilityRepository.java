package com.example.repo;



import com.example.api.model.Availability;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends MongoRepository<Availability, String> {
  List<Availability> findAll();
}
