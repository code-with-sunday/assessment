package com.ethnocopia.controller;

import com.ethnocopia.payload.requestDto.CustomerRequest;
import com.ethnocopia.payload.responseDto.EthnocopiaResponse;
import com.ethnocopia.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerRequest>> getAllCustomers() {
        List<CustomerRequest> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }


    @PostMapping("/addCustomer")
    public ResponseEntity<EthnocopiaResponse> addCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
      return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<EthnocopiaResponse> updateCustomer(@PathVariable("customerId") Long id, @RequestBody @Valid CustomerRequest customerRequest) {
       return new ResponseEntity<>(customerService.updateCustomer(customerRequest, id), HttpStatus.OK);

    }
}
