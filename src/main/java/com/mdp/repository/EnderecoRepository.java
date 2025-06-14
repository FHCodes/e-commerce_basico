package com.mdp.repository;

import com.mdp.entity.Cliente;
import com.mdp.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    List<Endereco> findByCliente_ClienteId(Long clienteId);

    @Modifying
    @Transactional
    @Query("UPDATE ENDERECOS e SET e.endAtual = false WHERE e.cliente.clienteId = :clienteId")
    void desativarEnderecosDoCliente(@Param("clienteId") Long clienteId);


}
