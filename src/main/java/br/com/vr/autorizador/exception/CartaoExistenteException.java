package br.com.vr.autorizador.exception;

import br.com.vr.autorizador.dto.CartaoDTO;

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
	
	public CartaoExistenteException(Integer codStatus) {
		super(codStatus, null);
	}
	
	public CartaoExistenteException(Integer codStatus, CartaoDTO cartaoDto) {
		super(codStatus, cartaoDto);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartaoExistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CartaoExistenteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CartaoExistenteException(Throwable cause) {
		super(cause);
	}


	


}
