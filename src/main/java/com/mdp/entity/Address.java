package com.mdp.entity;

import com.mdp.dto.request.AddressRequestDTO;
import jakarta.persistence.*;

@Entity(name = "ADDRESS")
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Integer number;
    private Boolean currentAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Address() {
    }

    public Address(AddressRequestDTO addressRequestDTO, Customer customer) {
        if (addressRequestDTO.id() != null) {
            this.id = addressRequestDTO.id();
        }

        this.street = addressRequestDTO.street();
        this.city = addressRequestDTO.city();
        this.state = addressRequestDTO.state();
        this.zipCode = addressRequestDTO.zipCode();
        this.number = addressRequestDTO.number();
        this.currentAddress = addressRequestDTO.currentAddress();
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getCurrentAddress() {
        return currentAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCurrentAddress(Boolean currentAddress) {
        this.currentAddress = currentAddress;
    }
}
