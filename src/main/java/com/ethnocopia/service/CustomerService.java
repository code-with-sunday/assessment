package com.ethnocopia.service;

import com.ethnocopia.payload.requestDto.CustomerRequest;
import com.ethnocopia.payload.responseDto.EthnocopiaResponse;

import java.util.List;

public interface CustomerService {

    EthnocopiaResponse addCustomer(CustomerRequest customerRequest);

    EthnocopiaResponse updateCustomer(CustomerRequest customerRequest, Long id);

    EthnocopiaResponse deleteCustomerById(Long id);

    List<CustomerRequest> getAllCustomer();
}
