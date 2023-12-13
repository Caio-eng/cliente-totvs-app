package com.teste.totvs.controller.exeception;

import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa erros da API em formato de lista de mensagens.
 */
public class ApiErrors {

	private List<String> errors;

	/**
	 * Construtor que recebe uma lista de mensagens de erro.
	 *
	 * @param errors Lista de mensagens de erro.
	 */
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * Construtor que recebe uma única mensagem de erro.
	 *
	 * @param message Mensagem de erro.
	 */
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}

	/**
	 * Obtém a lista de mensagens de erro.
	 *
	 * @return Lista de mensagens de erro.
	 */
	public List<String> getErrors() {
		return errors;
	}
}