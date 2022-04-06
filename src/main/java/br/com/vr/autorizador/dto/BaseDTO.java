package br.com.vr.autorizador.dto;

import java.io.Serializable;

public interface BaseDTO<T> extends Serializable{
	
	public Object toDTO(T t);
	
	public T fromDTO();
}
