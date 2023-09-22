package it.sincrono.repositories.exceptions;

@SuppressWarnings("serial")
public class RepositoryException extends Exception {

	public RepositoryException() {
		super();
	}

	/**
	 * @param message
	 */
	public RepositoryException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public RepositoryException(Throwable cause) {
		super(cause);
	}
}