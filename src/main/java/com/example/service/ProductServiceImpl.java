
package com.example.service;

import com.example.repo.AvailabilityRepository;
import com.example.repo.ProductRepository;
import com.example.api.model.Availability;
import com.example.api.model.Product;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/*
    implementar los metodos list, getById y availability usando el repositorio ProductRepository
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final AvailabilityRepository availabilityRepository;

  @Override
  public Flux<Product> list(String category, BigDecimal maxPrice, int page, int size) {
    return Flux.defer(() -> Flux.fromIterable(productRepository.findAll()))
        .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Mono<Product> getById(String id) {
    return Mono.fromCallable(
            () ->
                productRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)))
        .subscribeOn(Schedulers.boundedElastic());
  }

  @Override
  public Flux<Availability> availability(List<String> skus) {
    return Flux.defer(() -> Flux.fromIterable(availabilityRepository.findAll()))
        .subscribeOn(Schedulers.boundedElastic());
  }
}
