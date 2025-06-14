package com.mdp.mapper;

import com.mdp.dto.response.ClienteResponseDTO;
import com.mdp.dto.response.EnderecoResponseDTO;
import com.mdp.entity.Cliente;
import com.mdp.entity.Endereco;

import java.util.List;

public class EnderecoMapper {

    public static List<EnderecoResponseDTO> enderecosMapper(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoMapper::enderecoMapper)
                .toList();
    }

    public static EnderecoResponseDTO enderecoMapper(Endereco endereco){
        if (endereco == null) {
            return null;
        }
        return new EnderecoResponseDTO(endereco.getEnderecoId(),
                endereco.getRua()+" - "+endereco.getCidade()+" - "+endereco.getEstado()+" - "+endereco.getCep(),
                endereco.getEndAtual()
        );
    }
}



