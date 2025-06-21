package com.mdp.controller;

import com.mdp.dto.request.AddressRequestDTO;
import com.mdp.dto.response.AddressResponseDTO;
import com.mdp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{customerId}/all")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses(@PathVariable Long customerId) {
        return ResponseEntity.ok(addressService.getAllAddressesByCustomerId(customerId));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        addressService.registerAddress(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        addressService.updateAddress(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

