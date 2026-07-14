package com.splitcart.customer.mapper;

import com.splitcart.customer.api.model.Customer;
import com.splitcart.customer.api.model.CustomerInput;
import com.splitcart.product.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer toDto(CustomerEntity customerEntity);

  CustomerEntity toEntity(CustomerInput customerInput);

  CustomerEntity toEntity(Customer customer);
}