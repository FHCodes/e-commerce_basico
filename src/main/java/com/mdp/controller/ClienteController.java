package com.mdp.controller;

import com.mdp.dto.response.ClienteResponseDTO;
import com.mdp.dto.request.ClienteRequestDTO;
import com.mdp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("esta a funcionar Rafael");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> clienteById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.clienteDTOById(id));
    }


    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastraCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.cadastraCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        //return new ResponseEntity<>(clienteService.cadastraCliente(clienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/atualiza")
    public ResponseEntity<Void> updateCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.atualizaCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
        //return new ResponseEntity<>(clienteService.atualizaCliente(clienteDTO),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleta/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClienteResponseDTO>> listaTodosClientes() {
        return ResponseEntity.ok(clienteService.listaTodosClientes());
    }
}
