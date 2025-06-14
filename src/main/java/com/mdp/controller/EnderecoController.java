package com.mdp.controller;

import com.mdp.dto.request.EnderecoRequestDTO;
import com.mdp.dto.response.EnderecoResponseDTO;
import com.mdp.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{clienteId}/all")
    public ResponseEntity<List<EnderecoResponseDTO>> listaTodosEnderecos(@PathVariable Long clienteId){
        return ResponseEntity.ok(enderecoService.listaTodosEnderecosDoCliente(clienteId));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastraEndereco(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        enderecoService.cadastraEndereco(enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualiza")
    public ResponseEntity<Void> updateEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        enderecoService.updateEndereco(enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deleta/{enderecoId}")
    public ResponseEntity<Void> deletaEndereco(@PathVariable Long enderecoId){
        enderecoService.deletaEndereco(enderecoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
