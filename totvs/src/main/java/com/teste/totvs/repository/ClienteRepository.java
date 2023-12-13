package com.teste.totvs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.totvs.model.Cliente;

/**
 * Interface de repositório para a entidade Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/**
	 * Recupera um cliente pelo seu CPF.
	 *
	 * @param cpf O CPF do cliente.
	 * @return Um Optional contendo o cliente, se encontrado.
	 */
	Optional<Cliente> findByCpf(String cpf);

	/**
	 * Recupera um cliente pelo número de telefone.
	 *
	 * @param numero O número de telefone do cliente.
	 * @return Um Optional contendo o cliente, se encontrado.
	 */
	Optional<Cliente> findByTelefonesNumero(String numero);
}
