package com.teste.totvs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	@Length(min = 12, message = "O Nome deve ser maior do que 12 caracteres")
	private String nome;

	@Column(unique = true, nullable = false)
	@CPF(message = "CPF inválido")
	private String cpf;

	@Column
	private String endereco;

	@Column
	private String bairro;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones = new ArrayList<>();
	
	@PrePersist
    public void prePersist() {
        if (telefones != null) {
            for (Telefone telefone : telefones) {
                telefone.setCliente(this);
            }
        }
    }

	public Cliente() {
	}

	public Cliente(Long id, String nome, @CPF String cpf, String endereco, String bairro, List<Telefone> telefones) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.bairro = bairro;
		this.telefones = telefones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		return Objects.hash( id );
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals( id, other.id );
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", bairro=" + bairro
				+ ", telefones=" + telefones + "]";
	}
	
	public void adicionarTelefone(Telefone telefone) {
        telefone.setCliente(this);
        telefones.add(telefone);
    }

    public void removerTelefone(Telefone telefone) {
        telefones.remove(telefone);
        telefone.setCliente(null);
    }

}
