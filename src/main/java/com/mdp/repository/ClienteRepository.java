package com.mdp.repository;

import com.mdp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Cliente findByClienteId(Long clienteId);

}
