
package com.example.api;

import com.example.service.ProductService;
import com.example.api.ProductsApi;
import com.example.api.model.Availability;
import com.example.api.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  implementar los metodos listProducts, getProductById y getAvailability usando el servicio ProductService
 */
@Component
@RequiredArgsConstructor
public class ProductsApiDelegateImpl implements ProductsApi {

  private final ProductService service;

  @Override
  public Mono<ResponseEntity<Flux<Product>>> listProducts(
      String category, BigDecimal maxPrice, Integer page, Integer size, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Product>> getProductById(String id, ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<Flux<Availability>>> getAvailability(
      String ids, ServerWebExchange exchange) {
    return null;
  }
}
