package br.com.vr.autorizador.exception;

import java.util.Optional;

public class CartaoInexistenteSaldoException extends CartaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CartaoInexistenteSaldoException() {
	}
	
	/**
	 * @param body
	 */
	public CartaoInexistenteSaldoException(Object body) {
		setBody(Optional.of(body));
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public CartaoInexistenteSaldoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public CartaoInexistenteSaldoException(Throwable cause) {
		super(cause);
	}


	


}
