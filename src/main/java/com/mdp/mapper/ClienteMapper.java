package com.mdp.mapper;

import com.mdp.dto.response.ClienteResponseDTO;
import com.mdp.entity.Cliente;
import java.util.List;


public class ClienteMapper {

    public static List<ClienteResponseDTO> clientesMapper(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::clienteMapper)
                .toList();
    }

    public static ClienteResponseDTO clienteMapper(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return new ClienteResponseDTO(
                cliente.getClienteId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf()
        );
    }
}
