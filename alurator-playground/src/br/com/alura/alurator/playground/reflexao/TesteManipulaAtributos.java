package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Field;

import br.com.alura.alurator.playground.modelo.Produto;

public class TesteManipulaAtributos {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Object produto = new Produto("Produto 1", 20.0, "Marca 1");
		Class<?> classe = produto.getClass();
		
//		<produto>
//			<nome>Produto 1</nome>
//			<valor>20.0</valor>
//			<marcar>Marca 1</marca>
//		</produto>
		
		StringBuffer xml = new StringBuffer();
		xml.append("<"+classe.getName().split("[.]")[classe.getName().split("[.]").length-1] + ">");
		for (Field atributo : classe.getDeclaredFields()) {
			atributo.setAccessible(true);
			xml.append("<"+atributo.getName()+">"+atributo.get(produto) + "</"+ atributo.get(produto)+">");
			System.out.println(atributo.getName() + ": "+ atributo.get(produto));
		}
		xml.append("</"+classe.getName().split("[.]")[classe.getName().split("[.]").length-1] + ">");
		
		System.out.println(xml.toString());
	}

}
