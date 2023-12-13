package com.teste.totvs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teste.totvs.model.Cliente;
import com.teste.totvs.model.Telefone;
import com.teste.totvs.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente listarPeloId(Long id) {
		Optional<Cliente> obj = clienteRepository.findById( id );
		return obj.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado! Id: " + id ) );
	}

	@Transactional
	public Cliente salvarCliente(Cliente cliente) {

		Optional<Cliente> clienteExistente = buscarClientePorCpf( cliente.getCpf() );

		if ( clienteExistente.isPresent() ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente com CPF já cadastrado." );
		}

		validarCaracteresTelefone( cliente.getTelefones() );
		
		if ( cliente.getTelefones().isEmpty() ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone não pode ser vazio ");
		}

		for ( Telefone telefone : cliente.getTelefones() ) {
			
			Optional<Cliente> clienteComTelefone = clienteRepository.findByTelefonesNumero( telefone.getNumero() );
			if ( clienteComTelefone.isPresent() && !clienteComTelefone.get().getId().equals( cliente.getId() ) ) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone já vinculado a outro cliente." );
			}
		}

		return clienteRepository.save( cliente );
	}

	@Transactional
	public Cliente atualizarCliente(Long clienteId, Cliente clienteAtualizado) {
		Cliente clienteExistente = clienteRepository.findById( clienteId )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com o ID: " + clienteId ) );

		Optional<Cliente> clienteExistenteComCpf = buscarClientePorCpf( clienteAtualizado.getCpf() );
		if ( clienteExistenteComCpf.isPresent() && !clienteExistenteComCpf.get().getId().equals( clienteId ) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado para outro cliente." );
		}

		clienteExistente.setNome( clienteAtualizado.getNome() );
		clienteExistente.setCpf( clienteAtualizado.getCpf() );
		clienteExistente.setEndereco( clienteAtualizado.getEndereco() );
		clienteExistente.setBairro( clienteAtualizado.getBairro() );

		List<Telefone> telefonesAtualizados = clienteAtualizado.getTelefones();
		
		if ( telefonesAtualizados.isEmpty() ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone não pode ser vazio ");
		}
		for ( Telefone telefone : telefonesAtualizados ) {
			
			Telefone telefoneExistente = clienteExistente.getTelefones().stream().findFirst()
					.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado: " + telefone.getId() ) );

			telefoneExistente.setNumero( telefone.getNumero() );
		}

		validarCaracteresTelefone( clienteExistente.getTelefones() );

		return clienteRepository.save( clienteExistente );
	}

	private void validarCaracteresTelefone(List<Telefone> telefones) {
	    for (Telefone telefone : telefones) {
	        if (telefone.getNumero() == null || telefone.getNumero().isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone não pode ser nulo ou vazio");
	        }

	        if (!isNumeroTelefoneValido(telefone.getNumero())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido: " + telefone.getNumero());
	        }
	    }
	}


	private boolean isNumeroTelefoneValido(String numero) {
	    // - (XX) XXXX-XXXX (com DDD)
	    // - XXXX-XXXX (sem DDD)
	    return numero.matches("^\\(?(\\d{2})\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$") &&
	            !numero.matches("(\\d)(?:\\1{3,})?\\d*");
	}

	@Transactional
	public Optional<Cliente> buscarClientePorCpf(String cpf) {
		return clienteRepository.findByCpf( cpf );
	}

	@Transactional
	public void deletarCliente(Long id) {
		Cliente clienteExistente = clienteRepository.findById( id )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com o ID: " + id ) );

		List<Telefone> telefonesParaRemover = clienteExistente.getTelefones().stream()
				.filter( t -> clienteExistente.getTelefones().stream()
						.noneMatch( at -> at.getId() != null && at.getId().equals( t.getId() ) ) )
				.collect( Collectors.toList() );

		for ( Telefone telefone : telefonesParaRemover ) {
			clienteExistente.removerTelefone( telefone );
		}

		clienteRepository.deleteById( id );
	}
}
