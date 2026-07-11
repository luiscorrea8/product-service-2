
package com.splitcart.product.repo;

/*
Completar el repository que consuma de mongoDb de manera reactiva los productos
 */


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.splitcart.product.api.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	List<Product> findAll();

}

