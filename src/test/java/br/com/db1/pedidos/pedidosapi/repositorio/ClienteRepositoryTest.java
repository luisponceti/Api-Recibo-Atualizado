package br.com.db1.pedidos.pedidosapi.repositorio;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.db1.pedidos.pedidosapi.domain.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.StatusTipoCliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@After
	public void after() {
		clienteRepository.deleteAll();
	}

	@Test
	public void deveSalvarUmCliente() {
		Cliente cliente = new Cliente("Luís Fernando Chicoski Ponceti", "09855171900");
		clienteRepository.save(cliente);
		
		long count = clienteRepository.count();
		Assert.assertEquals(1, count, 0);
	}

	@Test
	public void deveSalverClienteAlterado() {
		Cliente cliente = new Cliente("Luís Fernando Chicoski Ponceti", "09855171900");
		clienteRepository.save(cliente);

		Cliente clienteSalvo = clienteRepository.findByCpf("09855171900");

		clienteSalvo.inativarCliente();

		clienteRepository.save(clienteSalvo);

		Cliente clienteAlterado = clienteRepository.findByCpf("09855171900");

		Assert.assertEquals(StatusTipoCliente.INATIVO, clienteAlterado.getStatus());
	}

}
