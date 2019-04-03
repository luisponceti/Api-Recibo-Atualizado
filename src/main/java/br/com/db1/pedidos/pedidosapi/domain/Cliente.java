package br.com.db1.pedidos.pedidosapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Validador;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "status", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTipoCliente status;

	protected Cliente() {
	}

	public Cliente(String nome, String cpf) {

		Validador.naoPodeSerNulo(nome, "nome");
		Validador.naoPodeSerNulo(cpf, "CPF");
		Validador.cpfDeveTerOnzeChar(cpf);

		this.nome = nome;
		this.cpf = cpf;
		this.status = StatusTipoCliente.ATIVO;

	}

	public void inativarCliente() {
		if (!StatusTipoCliente.ATIVO.equals(this.status)) {
			throw new RuntimeException("O cliete est� " + this.status);
		}
		status = StatusTipoCliente.INATIVO;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public StatusTipoCliente getStatus() {
		return status;
	}

	public boolean ativo() {
		return StatusTipoCliente.ATIVO.equals(this.status);
	}

}
