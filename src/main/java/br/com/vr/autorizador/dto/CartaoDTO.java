package br.com.vr.autorizador.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String senha;
	private String numeroCartao;
	private BigDecimal saldo;
	
	
	
}
