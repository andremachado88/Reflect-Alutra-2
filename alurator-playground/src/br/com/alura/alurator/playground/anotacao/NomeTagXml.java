package br.com.alura.alurator.playground.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// Array indicando onde ser� usado a anota��o - Nesse caso ser� na classe e nos atributos
@Target({ElementType.TYPE, ElementType.FIELD })
public @interface NomeTagXml {
	//Convers�o - quando se utiliza somente 1 atributo � utilizado como value, 
//	para n�o ser necess�rio fazer refer�ncia do atributo na classe.
	public String value();
}
