package com.app.proto.service.mapper;

import com.app.proto.domain.*;
import com.app.proto.service.dto.CustomerDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customersToCustomerDTOs(List<Customer> customers);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    List<Customer> customerDTOsToCustomers(List<CustomerDTO> customerDTOs);
}
