package br.com.db1.pedidos.pedidosapi.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Validador;

public class Pedido {

	private static final int Quantidade_Limite_Itens = 10;

	private StatusTipoPedido status;

	private Cliente cliente;

	private String codigo;

	@OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ProdutoHistorico> historico = new ArrayList<>();

	private List<ProdutoDoPedido> itens = new ArrayList<>();

	private LocalDateTime dataStatus;

	public Pedido(Cliente cliente, String numero) {
		Validador.naoPodeSerNulo(cliente, "cliente");
		Validador.naoPodeSerNulo(codigo, "codigo");
		this.verificarStatusClienteAtivo();
		
		this.cliente = cliente;
		this.codigo = numero;
		this.status = StatusTipoPedido.ABERTO;

	}

	public void adicionarItens(Produto produto, Double quantidade) {
		this.verificarStatusPedidoAlterar();

		if (this.itens.size() == Quantidade_Limite_Itens) {
			throw new RuntimeException("Quantidade m�xima de itens excedida: " + Quantidade_Limite_Itens);
		}

		this.itens.add(new ProdutoDoPedido(produto, quantidade));
	}

	public void removerItens(Produto produto) {
		this.verificarStatusPedidoAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}

	public void fechar() {
		if (!StatusTipoPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido est�  " + this.status);
		}

		if (this.itens.size() == 0 || this.itens.size() > Quantidade_Limite_Itens) {
			throw new RuntimeException(
					"Pedido deve ter no min�mo 1 item e no m�ximo 10 itens. Quantidade atual: " + this.itens.size());
		}

		this.verificarStatusClienteAtivo();

		this.status = StatusTipoPedido.FATURADO;
	}

	public void cancelar() {
		this.verificarStatusPedidoAlterar();
		this.status = StatusTipoPedido.CANCELADO;
	}

	public void devolucao() {
		if (!StatusTipoPedido.CANCELADO.equals(this.status)) {
			throw new RuntimeException("Pedido est� " + this.status);
		}

		this.status = StatusTipoPedido.ABERTO;
	}

	public String getCodigo() {
		return codigo;
	}

	public StatusTipoPedido getStatus() {
		return status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ProdutoHistorico> getHistoricos() {
		return Collections.unmodifiableList(historico);
	}

	public List<ProdutoDoPedido> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public LocalDateTime getDataStatus() {
		return dataStatus;
	}

	private void novoHistoricoStatus() {
		ProdutoHistorico historico = new ProdutoHistorico(this, dataStatus, this.status);
		this.historico.add(historico);
		this.dataStatus = historico.getData();
	}

	private void verificarStatusClienteAtivo() {
		if (!cliente.ativo()) {
			throw new RuntimeException("Cliente " + cliente.getNome() + " est� inativo");
		}
	}

	private void verificarStatusPedidoAlterar() {
		if (!StatusTipoPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido est�  " + this.status);
		}
	}

}
