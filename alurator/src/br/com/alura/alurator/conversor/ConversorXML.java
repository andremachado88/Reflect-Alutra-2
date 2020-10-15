package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.alura.alurator.conversor.anotacao.NomeTagXml;

public class ConversorXML {

	public String converter(Object objeto) 
	{
		try {
				Class<?> classeObjeto = objeto.getClass();
				StringBuilder xmlBuilder = new StringBuilder();
				
				if (objeto instanceof Collection)
				{
					Collection<?> colecao = (Collection<?>) objeto;
					xmlBuilder.append("<list>");
					for (Object o : colecao)
					{
						String xml = converter(o);
						xmlBuilder.append(xml);
					}
					
					xmlBuilder.append("</list>");
				}else 
				{

					NomeTagXml annotationClass = classeObjeto.getDeclaredAnnotation(br.com.alura.alurator.conversor.anotacao.NomeTagXml.class); 
					
					String nomeClasse = annotationClass == null ?
							nomeClasse = classeObjeto.getName().split("[.]")[classeObjeto.getName().split("[.]").length-1]
									: annotationClass.value();
					
					xmlBuilder.append("<"+nomeClasse + ">");
					for (Field atribuito : classeObjeto.getDeclaredFields())
					{
						atribuito.setAccessible(true);
						NomeTagXml atributoAnotation = atribuito.getDeclaredAnnotation(NomeTagXml.class);
						
						String nomeAtributo = atributoAnotation == null ?
								atribuito.getName()
								: atributoAnotation.value();
								
						Object valorAtributo = atribuito.get(objeto);
						xmlBuilder.append("<"+ nomeAtributo + ">");
						xmlBuilder.append(valorAtributo);
						xmlBuilder.append("<"+ nomeAtributo + ">");
						
					}
					xmlBuilder.append("</"+nomeClasse + ">");
					
				}
				return xmlBuilder.toString();
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Erro ao gerar XML");
			}
	}

}
