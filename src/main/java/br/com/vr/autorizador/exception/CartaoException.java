package br.com.vr.autorizador.exception;

public class CartaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CartaoException() {
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public CartaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CartaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CartaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}