package it.sincrono.services.exceptions;

import it.sincrono.services.costants.ServiceMessages;

public class ServiceException extends Exception {

	protected Integer code = null;

	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param serviceMessages
	 */
	public ServiceException(ServiceMessages serviceMessages) {
		super(serviceMessages.name());
		this.code = serviceMessages.getCode();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
	}
}