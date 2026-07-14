package com.example.mapper;

import com.example.entity.CustomerEntity;
import com.example.api.model.Customer;
import com.example.api.model.CustomerInput;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer toDto(CustomerEntity customerEntity);

  CustomerEntity toEntity(CustomerInput customerInput);

  CustomerEntity toEntity(Customer customer);
}