package br.com.vr.autorizador.exception;

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
