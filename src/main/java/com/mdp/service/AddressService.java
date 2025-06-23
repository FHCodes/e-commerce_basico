package com.mdp.service;

import com.mdp.dto.request.AddressRequestDTO;
import com.mdp.dto.response.AddressResponseDTO;
import com.mdp.entity.Address;
import com.mdp.exceptions.AddressNotFoundException;
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
    public Address updateAddress(AddressRequestDTO addressDTO) {

        if (addressDTO.id() == null) {
            throw new AddressNotFoundException(addressDTO.id());
        }

        checkIfAddressIsCurrent(addressDTO);

        Address existingAddress = addressRepository.findById(addressDTO.id())
                .orElseThrow(() -> new AddressNotFoundException(addressDTO.id()));

        if (addressDTO.street() != null && !addressDTO.street().isBlank()) {
            existingAddress.setStreet(addressDTO.street());
        }

        if (addressDTO.city() != null && !addressDTO.city().isBlank()) {
            existingAddress.setCity(addressDTO.city());
        }

        if (addressDTO.state() != null && !addressDTO.state().isBlank()) {
            existingAddress.setState(addressDTO.state());
        }

        if (addressDTO.zipCode() != null && !addressDTO.zipCode().isBlank()) {
            existingAddress.setZipCode(addressDTO.zipCode());
        }

        if (addressDTO.number() != null) {
            existingAddress.setNumber(addressDTO.number());
        }

        if (addressDTO.currentAddress() != null) {
            existingAddress.setCurrentAddress(addressDTO.currentAddress());
        }

        return addressRepository.save(existingAddress);
    }


    @Transactional
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
