package com.teste.totvs.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.teste.totvs.model.Cliente;
import com.teste.totvs.model.Telefone;
import com.teste.totvs.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> listarPeloId(@PathVariable Long id) {
		Cliente cliente = clienteService.listarPeloId(id);
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		List<Cliente> list = clienteService.listarTodos();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente,
			UriComponentsBuilder uriBuilder) {
		for (Telefone telefone : cliente.getTelefones()) {
	        telefone = Telefone.fromString(telefone.getNumero());
	    }
		URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		clienteService.salvarCliente(cliente);
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable @Valid Long id,
			@RequestBody @Valid Cliente clienteAtualizado) {
		for (Telefone telefone : clienteAtualizado.getTelefones()) {
	        telefone = Telefone.fromString(telefone.getNumero());
	    }
		Cliente clienteAtualizadoNoBanco = clienteService.atualizarCliente(id, clienteAtualizado);
		return ResponseEntity.ok(clienteAtualizadoNoBanco);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}
}
