package br.com.vr.autorizador.exception;

public class CartaoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codStatus;
	
	private Object body;
	
	/**
	 * 
	 */
	public CartaoException() {
	}
	
	/**
	 * @param codStatus
	 * @param body
	 */
	public CartaoException(Integer codStatus, Object body) {
		this.codStatus = codStatus;
		this.body = body;
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

	/**
	 * @return the codStatus
	 */
	public Integer getCodStatus() {
		return codStatus;
	}

	/**
	 * @param codStatus the codStatus to set
	 */
	public void setCodStatus(Integer codStatus) {
		this.codStatus = codStatus;
	}

	/**
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
	}
}
