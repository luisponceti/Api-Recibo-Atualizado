package br.com.db1.pedidos.pedidosapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	Cliente findByCpf(String cpf);

}
