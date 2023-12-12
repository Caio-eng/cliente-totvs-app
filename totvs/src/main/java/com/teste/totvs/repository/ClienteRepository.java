package com.teste.totvs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.totvs.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByCpf(String cpf);

	Optional<Cliente> findByTelefonesNumero(String numero);

}
