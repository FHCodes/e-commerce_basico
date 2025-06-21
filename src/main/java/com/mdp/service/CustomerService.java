package com.mdp.service;

import com.mdp.dto.request.CustomerRequestDTO;
import com.mdp.dto.response.CustomerResponseDTO;
import com.mdp.entity.Customer;
import com.mdp.exceptions.CustomerNotFoundException;
import com.mdp.mapper.CustomerMapper;
import com.mdp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public CustomerResponseDTO getCustomerDTOById(Long id) {
        return CustomerMapper.toCustomerDTO(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Transactional
    public void registerCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(customerRequestDTO);
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO.id() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null for update.");
        }

        Customer existingCustomer = getCustomerById(customerRequestDTO.id());

        if (customerRequestDTO.name() != null) {
            existingCustomer.setName(customerRequestDTO.name());
        }
        if (customerRequestDTO.email() != null) {
            existingCustomer.setEmail(customerRequestDTO.email());
        }
        if (customerRequestDTO.password() != null) {
            existingCustomer.setPassword(customerRequestDTO.password());
        }
        if (customerRequestDTO.cpf() != null) {
            existingCustomer.setCpf(customerRequestDTO.cpf());
        }

        customerRepository.save(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.toCustomerDTOList(customers);
    }
}
