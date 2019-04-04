package br.com.db1.pedidos.pedidosapi.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusTipoProduto;
import br.com.db1.pedidos.pedidosapi.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@After
	public void after() {
		produtoRepository.deleteAll();
	}

	@Test
	public void deveSalvarUmProduto() {
		Produto produto = new Produto("000", "Produto para teste", 10.00);
		produtoRepository.save(produto);

		long count = produtoRepository.count();

		Assert.assertEquals(1, count, 0);
	}

	@Test
	public void deveSalverProdutoAlterado() {
		Produto produto = new Produto("000", "Produto para teste", 10.00);
		produtoRepository.save(produto);

		Produto produtoSalvo = produtoRepository.findByCodigo("000");
		
		produtoSalvo.inativarProduto();
		
		produtoRepository.save(produtoSalvo);
		
		Produto produtoAlterado = produtoRepository.findByCodigo("000");

		Assert.assertEquals(StatusTipoProduto.INATIVO,produtoAlterado.getStatus());
	}

}
