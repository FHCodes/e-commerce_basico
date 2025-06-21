package com.mdp.service;

import com.mdp.dto.request.AddressRequestDTO;
import com.mdp.dto.response.AddressResponseDTO;
import com.mdp.entity.Address;
import com.mdp.mapper.AddressMapper;
import com.mdp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void checkIfAddressIsCurrent(AddressRequestDTO addressRequestDTO) {
        if (addressRequestDTO.currentAddress()) {
            addressRepository.deactivateCustomerAddresses(addressRequestDTO.customerId());
        }
    }

    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getAllAddressesByCustomerId(Long customerId) {
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        return AddressMapper.toAddressDTOList(addresses);
    }

    @Transactional
    public void registerAddress(AddressRequestDTO addressRequestDTO) {
        checkIfAddressIsCurrent(addressRequestDTO);
        Address address = new Address(addressRequestDTO, customerService.getCustomerById(addressRequestDTO.customerId()));
        addressRepository.save(address);
    }

    @Transactional
    public void updateAddress(AddressRequestDTO addressRequestDTO) {
        checkIfAddressIsCurrent(addressRequestDTO);
        Address address = new Address(addressRequestDTO, customerService.getCustomerById(addressRequestDTO.customerId()));
        addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
