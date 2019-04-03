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
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "codigo", length = 50, nullable = false, unique = true)
	private String codigo;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "valor", precision = 4, scale = 2, nullable = false)
	private Double valor;

	@Column(name = "status", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTipoProduto status;

	protected Produto(){}
	
	public Produto(String codigo, String nome, Double valor) {

		Validador.naoPodeSerNulo(codigo, "codigo");
		Validador.naoPodeSerNulo(nome, "nome");
		Validador.valorDeveSerMaiorQueZero(valor, "valor");

		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.status = StatusTipoProduto.ATIVO;
	}

	public void inativarProduto() {
		if (!StatusTipoProduto.ATIVO.equals(this.status)) {
			throw new RuntimeException("O produto est� " + this.status);
		}
		status = StatusTipoProduto.INATIVO;
	}

	public boolean ativo() {
		return StatusTipoProduto.ATIVO.equals(this.status);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public Double getValor() {
		return this.valor;
	}

	public StatusTipoProduto getStatus() {
		return status;
	}

}
