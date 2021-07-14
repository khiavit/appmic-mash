package com.acciona.aqsw.mash.api.exception;

/**
 * The Class PlayerNotFoundException.
 */
public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2851666364445260802L;

	/**
	 * Instantiates a new player not found exception.
	 */
	public PlayerNotFoundException() {
		super();
	}

	/**
	 * Instantiates a new player not found exception.
	 *
	 * @param message the message
	 */
	public PlayerNotFoundException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new player not found exception.
	 *
	 * @param newOriginalException the new original exception
	 */
	public PlayerNotFoundException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	/**
	 * Instantiates a new player not found exception.
	 *
	 * @param message              the message
	 * @param newOriginalException the new original exception
	 */
	public PlayerNotFoundException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}
}
