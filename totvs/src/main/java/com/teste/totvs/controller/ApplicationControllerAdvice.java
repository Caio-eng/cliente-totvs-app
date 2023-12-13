package com.teste.totvs.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.teste.totvs.controller.exeception.ApiErrors;

/**
 * Classe que fornece tratamento global para exceções na aplicação.
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

	/**
	 * Trata exceções relacionadas a erros de validação nos dados de entrada.
	 *
	 * @param ex A exceção de validação.
	 * @return Objeto ApiErrors contendo mensagens de erro de validação.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationsErros(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = bindingResult.getAllErrors().stream()
				.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
		return new ApiErrors(messages);
	}

	/**
	 * Trata exceções do tipo ResponseStatusException.
	 *
	 * @param ex A exceção ResponseStatusException.
	 * @return Resposta HTTP contendo mensagens de erro e código de status.
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusExeption(ResponseStatusException ex) {
		String messageError = ex.getMessage();
		HttpStatus codeStatus = (HttpStatus) ex.getStatusCode();
		ApiErrors apiErrors = new ApiErrors(messageError);
		return new ResponseEntity<>(apiErrors, codeStatus);
	}
}
