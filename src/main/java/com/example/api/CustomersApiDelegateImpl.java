package com.example.api;

import com.example.mapper.CustomerMapper;
import com.example.service.CustomerService;
import com.example.api.CustomersApiDelegate;
import com.example.api.model.Customer;
import com.example.api.model.CustomerInput;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomersApiDelegateImpl implements CustomersApiDelegate {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @Override
  @CircuitBreaker(name = "CircuitBreakerApi", fallbackMethod = "fallbackAllCustomer")
  @TimeLimiter(name = "CircuitBreakerApi")
  public Mono<ResponseEntity<Flux<Customer>>> getAllCustomers(ServerWebExchange exchange) {
    log.info("getAllCustomers executed");
    Flux<Customer> customerFlux = customerService.getAllCustomers().map(customerMapper::toDto);
    return Mono.just(ResponseEntity.ok(customerFlux));
  }

  @Override
  @CircuitBreaker(name = "CircuitBreakerApi", fallbackMethod = "fallbackCustomerById")
  @TimeLimiter(name = "CircuitBreakerApi")
  public Mono<ResponseEntity<Customer>> getCustomerById(String id, ServerWebExchange exchange) {
    log.info("getCustomerById executed");
    return customerService
        .getCustomerById(id)
        .map(customerMapper::toDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  @CircuitBreaker(name = "CircuitBreakerApi", fallbackMethod = "fallbackCreateCustomer")
  @TimeLimiter(name = "CircuitBreakerApi")
  public Mono<ResponseEntity<Customer>> createCustomer(
      @Parameter(name = "CustomerInput", description = "", required = true) @Valid @RequestBody
          Mono<CustomerInput> customerInput,
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    log.info("createCustomer executed");
    return customerInput
        .map(customerMapper::toEntity)
        .flatMap(customerService::createCustomer)
        .map(customerMapper::toDto)
        .map(customer -> ResponseEntity.status(HttpStatus.CREATED).body(customer));
  }

  @Override
  @CircuitBreaker(name = "CircuitBreakerApi", fallbackMethod = "fallbackUpdateCustomer")
  @TimeLimiter(name = "CircuitBreakerApi")
  public Mono<ResponseEntity<Customer>> updateCustomer(
      Mono<Customer> customer, ServerWebExchange exchange) {
    log.info("updateCustomer executed");
    return customer
        .map(customerMapper::toEntity)
        .flatMap(customerService::updateCustomer)
        .map(customerMapper::toDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  @CircuitBreaker(name = "CircuitBreakerApi", fallbackMethod = "fallbackDeleteCustomer")
  @TimeLimiter(name = "CircuitBreakerApi")
  public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
    log.info("deleteCustomer executed");
    return customerService
        .deleteCustomer(id)
        .flatMap(
            deleted -> {
              if (Boolean.TRUE.equals(deleted)) {
                return Mono.just(ResponseEntity.noContent().<Void>build());
              } else {
                return Mono.just(ResponseEntity.notFound().build());
              }
            });
  }

  /**
   * Fallback method for getAllCustomers.
   *
   * @param exchange the server web exchange
   * @param t the throwable that caused the fallback
   * @return a default response
   */
  public Mono<ResponseEntity<Flux<Customer>>> fallbackAllCustomer(ServerWebExchange exchange, Throwable t) {
    log.error("Fallback for getAllCustomers executed", t);
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Flux.empty()));
  }

  /**
   * Fallback method for getCustomerById.
   *
   * @param id the customer id
   * @param exchange the server web exchange
   * @param t the throwable that caused the fallback
   * @return a default response
   */
  public Mono<ResponseEntity<Customer>> fallbackCustomerById(String id, ServerWebExchange exchange, Throwable t) {
    log.error("Fallback for getCustomerById executed for id: {}", id, t);
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
  }

  /**
   * Fallback method for createCustomer.
   *
   * @param customerInput the customer input mono
   * @param exchange the server web exchange
   * @param t the throwable that caused the fallback
   * @return a default response
   */
  public Mono<ResponseEntity<Customer>> fallbackCreateCustomer(Mono<CustomerInput> customerInput, ServerWebExchange exchange, Throwable t) {
    log.error("Fallback for createCustomer executed", t);
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
  }

  /**
   * Fallback method for updateCustomer.
   *
   * @param customer the customer mono
   * @param exchange the server web exchange
   * @param t the throwable that caused the fallback
   * @return a default response
   */
  public Mono<ResponseEntity<Customer>> fallbackUpdateCustomer(Mono<Customer> customer, ServerWebExchange exchange, Throwable t) {
    log.error("Fallback for updateCustomer executed", t);
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
  }

  /**
   * Fallback method for deleteCustomer.
   *
   * @param id the customer id
   * @param exchange the server web exchange
   * @param t the throwable that caused the fallback
   * @return a default response
   */
  public Mono<ResponseEntity<Void>> fallbackDeleteCustomer(String id, ServerWebExchange exchange, Throwable t) {
    log.error("Fallback for deleteCustomer executed for id: {}", id, t);
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
  }
}
