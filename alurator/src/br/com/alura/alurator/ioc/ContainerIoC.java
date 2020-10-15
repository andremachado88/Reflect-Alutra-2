package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {
	private Map<Class<?>, Class<?>>  mapaDeTipos = new HashMap<>();
	
	public Object getInstancia(Class<?> tipoFonte)  {
		Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);
		if (tipoDestino != null)
		{
			return getInstancia(tipoDestino);
		}
		
		Stream<Constructor<?>> construtores =
				Stream.of(tipoFonte.getDeclaredConstructors());
		
		Optional<Constructor<?>> construtorPadrao =
				construtores.filter(construtor -> construtor.getParameterCount() == 0)
				.findFirst();
		
		
		try 
		{
			if (construtorPadrao.isPresent())
			{
				Object instancia = construtorPadrao.get().newInstance();
				return instancia;
			}
			else 
			{
				Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
				List<Object> params = new ArrayList<>();
				for (Parameter param : construtor.getParameters())
				{
					Class<?> tipoParametro = param.getType();
					params.add(getInstancia(tipoParametro));
				}
				
				return construtor.newInstance(params.toArray());
			}
			
		}catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {

//		boolean compativel = verificaCompatibilidade(tipoFonte, tipoDestino);
//		if (!compativel)
//			throw new ClassCastException("Não é possível resolver "+ tipoFonte.getName() + " para o "+ tipoDestino.getName());
//		
		
		mapaDeTipos.put(tipoFonte, tipoDestino);
	}

	private boolean verificaCompatibilidade(Class<?> tipoFonte, Class<?> tipoDestino) {
		// Verificação se Tipo Destino é compatível com tipo Fonte na raça
//		boolean compativel;
//		
//		if (tipoFonte.isInterface())
//		{
//			compativel = Stream.of(tipoDestino.getInterfaces())
//				.anyMatch(interfaceImplemenatada -> interfaceImplemenatada.equals(tipoFonte));
//		}
//		else 
//		{
//			compativel = tipoDestino.getSuperclass().equals(tipoFonte)
//					|| tipoDestino.equals(tipoFonte);
//		}
//		
//		return compativel;
		
		// Verificar compatibilidade com a API de Reflection
		return tipoFonte.isAssignableFrom(tipoDestino);
		
	}

}
