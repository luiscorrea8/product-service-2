package com.example.service;

import com.example.entity.CustomerEntity;
import com.example.entity.enums.CustomerType;
import com.example.repo.CustomerRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  public Mono<CustomerEntity> createCustomer(CustomerEntity customer) {
    return Mono.fromCallable(() -> customerRepository.save(customer))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<CustomerEntity> getCustomerById(String id) {
    return Mono.fromCallable(
            () ->
                customerRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id)))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Flux<CustomerEntity> getAllCustomers() {
    return Flux.defer(() -> Flux.fromIterable(customerRepository.findAll()))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<CustomerType> customerType(String id) {
    return Mono.fromCallable(
            () ->
                customerRepository
                    .findById(id)
                    .map(CustomerEntity::getCustomerType)
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id)))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<CustomerEntity> updateCustomer(CustomerEntity customer) {
    return Mono.fromCallable(
            () ->
                customerRepository
                    .findById(customer.getId())
                    .map(existingCustomer -> customerRepository.save(customer))
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customer.getId())))
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Boolean> deleteCustomer(String id) {
    return Mono.fromCallable(
            () ->
                customerRepository
                    .findById(id)
                    .map(
                        customer -> {
                          customer.setStatus(com.example.entity.enums.Status.INACTIVE);
                          return customerRepository.save(customer) != null;
                        })
                    .orElse(false))
        .subscribeOn(Schedulers.boundedElastic());
  }
}
