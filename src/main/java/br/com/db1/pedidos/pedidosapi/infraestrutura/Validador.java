package br.com.db1.pedidos.pedidosapi.infraestrutura;

public class Validador {

	public static void naoPodeSerNulo(Object valor, String campo) {
		if(valor == null){
			throw new RuntimeException("Campo " + campo + " � obrigat�rio");
		}
		
	}
	
	public static void cpfDeveTerOnzeChar(String valor){
		if(valor.length() < 11 || valor.length() > 11){
			throw new RuntimeException("Cpf deve conter Onze caracteres ");
		}
	}
	
	public static void valorDeveSerMaiorQueZero(Double valor, String campo){
		if(valor <= 0 ||valor == null){
			throw new RuntimeException("O pre�o do campo " + campo + " n�o pode ser zero ou inferior a zero");
		}
	}

	
		
	
	
}
