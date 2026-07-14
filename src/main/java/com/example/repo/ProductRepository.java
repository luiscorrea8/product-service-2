
package com.example.repo;

/*
Completar el repository que consuma de mongoDb de manera reactiva los productos
 */


import com.example.api.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findAll();
}
