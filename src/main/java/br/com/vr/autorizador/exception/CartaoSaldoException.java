package br.com.vr.autorizador.exception;

public class CartaoSaldoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartaoSaldoException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartaoSaldoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CartaoSaldoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CartaoSaldoException(Throwable cause) {
		super(cause);
	}
}
