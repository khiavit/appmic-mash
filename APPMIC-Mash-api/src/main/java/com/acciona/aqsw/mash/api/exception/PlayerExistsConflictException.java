package com.acciona.aqsw.mash.api.exception;

/**
 * The Class PlayerExistsConflictException.
 */
public class PlayerExistsConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271354310573444163L;

	/**
	 * Instantiates a new player exists conflict exception.
	 */
	public PlayerExistsConflictException() {
	}

	/**
	 * Instantiates a new player exists conflict exception.
	 *
	 * @param message the message
	 */
	public PlayerExistsConflictException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new player exists conflict exception.
	 *
	 * @param newOriginalException the new original exception
	 */
	public PlayerExistsConflictException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	/**
	 * Instantiates a new player exists conflict exception.
	 *
	 * @param message the message
	 * @param newOriginalException the new original exception
	 */
	public PlayerExistsConflictException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}

}
