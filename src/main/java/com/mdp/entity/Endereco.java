package com.mdp.entity;

import com.mdp.dto.request.EnderecoRequestDTO;
import jakarta.persistence.*;

@Entity(name = "ENDERECOS")
@Table(name = "ENDERECOS")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private Integer numero;
    private Boolean endAtual;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(EnderecoRequestDTO enderecoRequestDTO, Cliente cliente) {
        if (enderecoRequestDTO.id() != null){
            this.enderecoId = enderecoRequestDTO.id();
        }

        this.rua = enderecoRequestDTO.rua();
        this.cidade = enderecoRequestDTO.cidade();
        this.estado = enderecoRequestDTO.estado();
        this.cep = enderecoRequestDTO.cep();
        this.numero = enderecoRequestDTO.numero();
        this.endAtual = enderecoRequestDTO.endAtual();
        this.cliente = cliente;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public Boolean getEndAtual() {
        return endAtual;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setEndAtual(Boolean endAtual) {
        this.endAtual = endAtual;
    }
}
