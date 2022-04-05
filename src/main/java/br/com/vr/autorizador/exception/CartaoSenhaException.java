package br.com.vr.autorizador.exception;

public class CartaoSenhaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartaoSenhaException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartaoSenhaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CartaoSenhaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CartaoSenhaException(Throwable cause) {
		super(cause);
	}
}
