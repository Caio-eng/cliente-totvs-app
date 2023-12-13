package com.teste.totvs.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.teste.totvs.model.Cliente;
import com.teste.totvs.service.ClienteService;

import jakarta.validation.Valid;

/**
 * Controlador responsável por manipular operações relacionadas aos clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Recupera um cliente pelo seu ID.
	 *
	 * @param id O ID do cliente.
	 * @return Resposta HTTP contendo o cliente se encontrado.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> listarPeloId(@PathVariable Long id) {
		Cliente cliente = clienteService.listarPeloId(id);
		return ResponseEntity.ok().body(cliente);
	}

	/**
	 * Recupera uma lista de todos os clientes.
	 *
	 * @return Resposta HTTP contendo a lista de clientes.
	 */
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		List<Cliente> list = clienteService.listarTodos();
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Cadastra um novo cliente.
	 *
	 * @param cliente    O cliente a ser cadastrado.
	 * @param uriBuilder Um construtor de URI para a resposta.
	 * @return Resposta HTTP com o cliente cadastrado e URI para sua localização.
	 */
	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente,
			UriComponentsBuilder uriBuilder) {
		URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		clienteService.salvarCliente(cliente);
		return ResponseEntity.created(location).build();
	}

	/**
	 * Atualiza um cliente existente.
	 *
	 * @param id                O ID do cliente a ser atualizado.
	 * @param clienteAtualizado O cliente com as informações atualizadas.
	 * @return Resposta HTTP contendo o cliente atualizado.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable @Valid Long id,
			@RequestBody @Valid Cliente clienteAtualizado) {
		Cliente clienteAtualizadoNoBanco = clienteService.atualizarCliente(id, clienteAtualizado);
		return ResponseEntity.ok(clienteAtualizadoNoBanco);
	}

	/**
	 * Exclui um cliente pelo seu ID.
	 *
	 * @param id O ID do cliente a ser excluído.
	 * @return Resposta HTTP indicando o sucesso da exclusão.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}
}