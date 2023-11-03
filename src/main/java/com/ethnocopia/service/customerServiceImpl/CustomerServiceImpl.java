package com.ethnocopia.service.customerServiceImpl;

import com.ethnocopia.Utils.AppUtils;
import com.ethnocopia.entity.Customer;
import com.ethnocopia.exception.ResourceNotFoundException;
import com.ethnocopia.payload.requestDto.AppAccountInfoRequest;
import com.ethnocopia.payload.requestDto.CustomerRequest;
import com.ethnocopia.payload.responseDto.EthnocopiaResponse;
import com.ethnocopia.repository.CustomerRepository;
import com.ethnocopia.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ModelMapper mapper, CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    private CustomerRequest mapToDto(Customer customer){
        CustomerRequest customerRequest = mapper.map(customer, CustomerRequest.class);
        return customerRequest;
    }

    private Customer mapToEntity(CustomerRequest customerRequest){
        Customer customer = mapper.map(customerRequest, Customer.class);
        return customer;
    }


    @Override
    public EthnocopiaResponse addCustomer(CustomerRequest customerRequest) {
        Boolean checkCustomer = customerRepository.existsByEmail(customerRequest.getEmail());

        if (checkCustomer.equals(customerRequest.getEmail())){
            return EthnocopiaResponse.builder()
                    .responseCode(AppUtils.CUSTOMER_EXITS_CODE)
                    .responseMessage(AppUtils.CUSTOMER_EXITS_MESSAGE)
                    .appAccountInfoRequest(null)
                    .build();
        }

        Customer savedCustomer = mapToEntity(customerRequest);
        customerRepository.save(savedCustomer);

        return EthnocopiaResponse.builder()
                .responseCode(AppUtils.CUSTOMER_CREATION_SUCCESS)
                .responseMessage(AppUtils.CUSTOMER_CREATION_MESSAGE)
                .build();
    }

    @Override
    public EthnocopiaResponse updateCustomer(CustomerRequest customerRequest, Long id) {
        Customer checkCustomerDetails = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        checkCustomerDetails.setAge(customerRequest.getAge());
        checkCustomerDetails.setName(customerRequest.getName());
        checkCustomerDetails.setEmail(customerRequest.getEmail());

        customerRepository.save(checkCustomerDetails);

        return EthnocopiaResponse.builder()
                .responseCode(AppUtils.CUSTOMER_UPDATED_SUCCESS_CODE)
                .responseMessage(AppUtils.CUSTOMER_UPDATED_SUCCESS_MESSAGE)
                .appAccountInfoRequest(AppAccountInfoRequest.builder()
                        .username(checkCustomerDetails.getName())
                        .message(AppUtils.CUSTOMER_UPDATED_SUCCESS_MESSAGE_NOTE)
                        .build())
                .build();
    }

    @Override
    public EthnocopiaResponse deleteCustomerById(Long id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customerToDelete);
        return EthnocopiaResponse.builder()
                .responseCode(AppUtils.CUSTOMER_DELETE_SUCCESS)
                .responseMessage(AppUtils.CUSTOMER_DELETE_SUCCESS_MESSAGE)
                .appAccountInfoRequest(null)
                .build();
    }

    @Override
    public List<CustomerRequest> getAllCustomer() {
        List<Customer> customerEntities = customerRepository.findAll();

        List<CustomerRequest> customerRequests = customerEntities
                .stream()
                .map(customer -> new CustomerRequest(customer.getName(), customer.getEmail(),
                        customer.getAge()))
                .collect(Collectors.toList());

        return customerRequests;

    }
}
