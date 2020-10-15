package br.com.alura.alurator.playground.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// Array indicando onde será usado a anotação - Nesse caso será na classe e nos atributos
@Target({ElementType.TYPE, ElementType.FIELD })
public @interface NomeTagXml {
	//Conversão - quando se utiliza somente 1 atributo é utilizado como value, 
//	para não ser necessário fazer referência do atributo na classe.
	public String value();
}
