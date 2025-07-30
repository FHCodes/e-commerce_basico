package com.mdp.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {}

