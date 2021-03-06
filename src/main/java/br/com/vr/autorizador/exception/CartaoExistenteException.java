package br.com.vr.autorizador.exception;

import java.util.Optional;

public class CartaoExistenteException extends CartaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CartaoExistenteException() {
	}
	
	public CartaoExistenteException(Object body) {
		setBody(Optional.of(body));
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartaoExistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public CartaoExistenteException(Throwable cause) {
		super(cause);
	}


	


}
