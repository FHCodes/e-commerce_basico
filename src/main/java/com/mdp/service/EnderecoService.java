package com.mdp.service;

import com.mdp.dto.request.EnderecoRequestDTO;
import com.mdp.dto.response.EnderecoResponseDTO;
import com.mdp.entity.Endereco;
import com.mdp.mapper.EnderecoMapper;
import com.mdp.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public void verificaEnderecoAtual(EnderecoRequestDTO enderecoRequestDTO) {

        if (enderecoRequestDTO.endAtual()) {
            enderecoRepository.desativarEnderecosDoCliente(enderecoRequestDTO.clienteId());
        }
    }

    @Transactional(readOnly = true)
    public List<EnderecoResponseDTO> listaTodosEnderecosDoCliente(Long clienteId) {
        List<Endereco> enderecos = enderecoRepository.findByCliente_ClienteId(clienteId);
        return EnderecoMapper.enderecosMapper(enderecos);
    }

    @Transactional
    public void cadastraEndereco(EnderecoRequestDTO enderecoRequestDTO){

        verificaEnderecoAtual(enderecoRequestDTO);
        Endereco endereco = new Endereco(enderecoRequestDTO,clienteService.clienteById(enderecoRequestDTO.clienteId()));

        enderecoRepository.save(endereco);
    }

    @Transactional
    public void updateEndereco(EnderecoRequestDTO enderecoRequestDTO){
        verificaEnderecoAtual(enderecoRequestDTO);
        Endereco endereco = new Endereco(enderecoRequestDTO,clienteService.clienteById(enderecoRequestDTO.clienteId()));
        enderecoRepository.save(endereco);
    }

    @Transactional
    public void deletaEndereco(Long enderecoId){
        enderecoRepository.deleteById(enderecoId);
    }
}
