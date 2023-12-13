package com.teste.totvs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.teste.totvs.model.Cliente;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {TotvsApplication.class})
@ActiveProfiles("teste")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteIntegrationTest {

	@Autowired(required = true)
	private WebTestClient testClient;
	
	@BeforeEach
	void setUp() {	
		Cliente cliente = mock( Cliente.class );
		when( cliente.getNome() ).thenReturn( "João Atualizado" );
		when( cliente.getCpf() ).thenReturn( "11330966031" );
		when( cliente.getEndereco() ).thenReturn( "Rua ABC, 123" );
		when( cliente.getBairro() ).thenReturn( "Centro" );
	}
	
	@Test
	@Order(1)
	void testCriarCliente() {
		testClient.post().uri( "/clientes" )
			.contentType( MediaType.APPLICATION_JSON )
			.accept( MediaType.APPLICATION_JSON )
			.bodyValue( """
											{
						  "nome": "João da Silva",
						  "cpf": "11330966031",
						  "endereco": "Rua ABC, 123",
						  "bairro": "Centro",
						  "telefones": [
						    {"numero": "628116-5459"},	
						    {"numero": "628526-7088"}
						  ]
						} """ )
			.exchange()
				.expectStatus().isCreated();
	}
	
	@Test
	@Order(2)
	void testAtualizarCliente() {
		testClient.put().uri( "/clientes/1" )
			.contentType( MediaType.APPLICATION_JSON )
			.accept( MediaType.APPLICATION_JSON )
			.bodyValue( """
											{
						  "nome": "João Atualizado",
						  "cpf": "11330966031",
						  "endereco": "Rua ABC, 123",
						  "bairro": "Centro",
						  "telefones": [
						    {"numero": "628116-5459"},
						    {"numero": "628526-7088"}
						  ]
						} """ )
			.exchange()
				.expectStatus().isOk();
	}
	
	@Test
	@Order(3)
	void testListarTodos() {
		
		testClient.get().uri( "/clientes" )
			.accept( MediaType.APPLICATION_JSON )
			.exchange()
				.expectStatus().isOk()
				.expectBody()
					.jsonPath( "$" ).isArray()
					.jsonPath( "[0].nome" ).isEqualTo( "João Atualizado" )
					.jsonPath( "[0].cpf" ).isEqualTo( "11330966031" )
					.jsonPath( "[0].endereco" ).isEqualTo( "Rua ABC, 123" )
					.jsonPath( "[0].bairro" ).isEqualTo( "Centro" );
	}
	
	@Test
	@Order(4)
	void testListarPeloId() {
		
		testClient.get().uri( "/clientes/1" )
			.accept( MediaType.APPLICATION_JSON )
			.exchange()
				.expectStatus().isOk()
				.expectBody()
					.jsonPath( "nome" ).isEqualTo( "João Atualizado" )
					.jsonPath( "cpf" ).isEqualTo( "11330966031" )
					.jsonPath( "endereco" ).isEqualTo( "Rua ABC, 123" )
					.jsonPath( "bairro" ).isEqualTo( "Centro" );
	}
	
	@Test
	@Order(5)
	void testDeletarCliente() {
		testClient.delete().uri( "/clientes/1" )
			.accept( MediaType.APPLICATION_JSON )
			.exchange()
				.expectStatus().isNoContent();
	}
}
