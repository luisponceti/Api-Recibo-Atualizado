package br.com.db1.pedidos.pedidosapi.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Validador;

@Entity
@Table(name="pedido_hisotrico")
public class ProdutoHistorico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDateTime data;

	private StatusTipoPedido status;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;

	public ProdutoHistorico(Pedido pedido,LocalDateTime data, StatusTipoPedido statusPedido) {
		Validador.naoPodeSerNulo(status, "status");
		this.data = LocalDateTime.now();
		this.pedido = pedido;
		statusPedido = status;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
	public StatusTipoPedido getStatus(){
		return this.status;
	}

	public void setStatus(StatusTipoPedido status) {
		this.status = status;
	}
}
