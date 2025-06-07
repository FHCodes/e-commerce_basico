package com.mdp.service;

import com.mdp.dto.request.ClienteRequestDTO;
import com.mdp.dto.response.ClienteResponseDTO;
import com.mdp.entity.Cliente;
import com.mdp.mapper.ClienteMapper;
import com.mdp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public ClienteResponseDTO clienteById(Long id){
        return ClienteMapper.clienteMapper(clienteRepository.findByClienteId(id));
    }

    @Transactional
    public void cadastraCliente(ClienteRequestDTO clienteRequestDTO){
        try {
            Cliente cliente = new Cliente(clienteRequestDTO);
            clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro no cadastro do cliente");
        }
    }

    @Transactional
    public void atualizaCliente(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = new Cliente(clienteRequestDTO);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listaTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteMapper.clientesMapper(clientes);
    }
}