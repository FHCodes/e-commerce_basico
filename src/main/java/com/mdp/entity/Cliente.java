package com.mdp.entity;

import com.mdp.dto.request.ClienteRequestDTO;
import com.mdp.dto.response.ClienteResponseDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "CLIENTES")
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(ClienteRequestDTO cliente) {
        if (cliente.id() != null) {
            this.clienteId = cliente.id();
        }
        this.nome = cliente.nome();
        this.email = cliente.email();
        this.senha = cliente.senha();
        this.cpf = cliente.cpf();
    }


    public Cliente(String nome, String email, String senha, String cpf) {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
