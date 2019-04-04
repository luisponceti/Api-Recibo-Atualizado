package br.com.db1.pedidos.pedidosapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.pedidos.pedidosapi.domain.dto.ProdutoDTO;
import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> getAll(){
		Iterable<Produto> produtosDatabase = produtoRepository.findAll();
		Iterator<Produto> iterator = produtosDatabase.iterator();
		
		List<ProdutoDTO> produtos = new ArrayList<>(); 
		
		while(iterator.hasNext())
			{
			Produto next = iterator.next();
			ProdutoDTO produtoDTO = new ProdutoDTO(next.getCodigo(), next.getNome(), next.getValor());
			produtos.add(produtoDTO);
			}
		return produtos;
	}

}
