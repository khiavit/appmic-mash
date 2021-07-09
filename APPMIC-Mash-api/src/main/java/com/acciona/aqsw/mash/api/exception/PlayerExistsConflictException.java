package com.acciona.aqsw.mash.api.exception;

public class PlayerExistsConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271354310573444163L;

	public PlayerExistsConflictException() {
	}

	public PlayerExistsConflictException(final String message) {
		super(message);
	}

	public PlayerExistsConflictException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	public PlayerExistsConflictException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}

}
