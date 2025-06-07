package com.mdp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(Long id,
                                @NotBlank(message = "Nome é obrigatório")
                                String nome,

                                @Email(message = "Email inválido")
                                @NotBlank(message = "Email é obrigatório")
                                String email,

                                @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
                                String senha,

                                @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
                                String cpf) {}
