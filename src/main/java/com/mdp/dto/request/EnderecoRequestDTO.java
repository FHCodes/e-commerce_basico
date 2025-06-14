package com.mdp.dto.request;

import jakarta.validation.constraints.*;

public record EnderecoRequestDTO(

        Long id,

        @NotBlank(message = "Rua é obrigatória")
        String rua,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotBlank(message = "Estado é obrigatório")
        String estado,

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve estar no formato 00000-000")
        String cep,

        @NotNull(message = "Número é obrigatório")
        @Positive(message = "Número deve ser positivo")
        Integer numero,

        @NotNull(message = "É necessário informar se este é o endereço atual")
        Boolean endAtual,

        @NotNull(message = "ID do cliente é obrigatório")
        Long clienteId

) {}
