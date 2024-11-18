package com.rkisuru.customer.customer;

import com.rkisuru.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Integer createCustomer(CustomerRequest request) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public Customer updateCustomer(CustomerRequest request, Integer customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("Customer with id " + customerId + " not found")
                ));
        mergerCustomer(customer, request);
        return customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setFirstname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setFirstname(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(Integer customerId) {

        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with id " + customerId + " not found"));
    }

    public void deleteCustomer(Integer customerId) {

        customerRepository.deleteById(customerId);
    }
}
