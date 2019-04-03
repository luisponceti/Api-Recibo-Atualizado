package br.com.db1.pedidos.pedidosapi.domain;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Validador;

public class ProdutoDoPedido {

	private Produto produto;

	private Double quantidade;

	private Double valor;

	public ProdutoDoPedido(Produto produto2, Double quantidade) {

		Validador.naoPodeSerNulo(produto2, "produto");
		Validador.naoPodeSerNulo(quantidade, "quantidade");
		Validador.valorDeveSerMaiorQueZero(quantidade, "quantidade");

		this.produto = produto2;
		this.quantidade = quantidade;
	
	}

	public Produto getProduto() {
		return this.produto;
	}

	public Double getQuantidade() {
		return this.quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public Double getValorTotal() {
		return this.quantidade * this.valor;
	}

}
