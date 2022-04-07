package br.com.vr.autorizador.exception;

import java.util.Optional;

public class CartaoInexistenteException extends CartaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CartaoInexistenteException() {
	}
	
	/**
	 * @param body
	 */
	public CartaoInexistenteException(Object body) {
		setBody(Optional.of(body));
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public CartaoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public CartaoInexistenteException(Throwable cause) {
		super(cause);
	}


	


}
