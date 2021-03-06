package br.com.vr.autorizador.exception;

import java.util.Optional;

public class CartaoSaldoException extends CartaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CartaoSaldoException() {
	}
	
	
	/**
	 * @param body
	 */
	public CartaoSaldoException(Object body) {
		setBody(Optional.of(body));
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public CartaoSaldoException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param cause
	 */
	public CartaoSaldoException(Throwable cause) {
		super(cause);
	}
}
