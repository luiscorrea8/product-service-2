
package com.example.service;

import com.example.api.model.Availability;
import com.example.api.model.Product;
import java.math.BigDecimal;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Flux<Product> list(String category, BigDecimal maxPrice, int page, int size);

  Mono<Product> getById(String id);

  Flux<Availability> availability(List<String> skus);
}
