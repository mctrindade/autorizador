package br.com.vr.autorizador.exception;

import java.util.Optional;

public class CartaoSenhaInvalidaException extends CartaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CartaoSenhaInvalidaException() {
	}
	
	/**
	 * @param body
	 */
	public CartaoSenhaInvalidaException(Object body) {
		setBody(Optional.of(body));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartaoSenhaInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param cause
	 */
	public CartaoSenhaInvalidaException(Throwable cause) {
		super(cause);
	}
}
