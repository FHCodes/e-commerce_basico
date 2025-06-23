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

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(readOnly = true)
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
    public void updateCustomer(CustomerRequestDTO customerDTO) {
        if (customerDTO.id() == null) {
            throw new CustomerNotFoundException(customerDTO.id());
        }

        Customer existingCustomer = getCustomerById(customerDTO.id());

        if (customerDTO.name() != null && !customerDTO.name().isBlank()) {
            existingCustomer.setName(customerDTO.name());
        }
        if (customerDTO.email() != null && !customerDTO.email().isBlank()) {
            existingCustomer.setEmail(customerDTO.email());
        }
        if (customerDTO.password() != null && !customerDTO.password().isBlank()) {
            existingCustomer.setPassword(customerDTO.password());
        }
        if (customerDTO.cpf() != null && !customerDTO.cpf().isBlank()) {
            existingCustomer.setCpf(customerDTO.cpf());
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
