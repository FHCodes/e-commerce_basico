package com.mdp.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(Long id) {
        super("Seller not found with id: " + id);
    }
}

