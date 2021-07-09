package com.acciona.aqsw.mash.api.exception;

public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2851666364445260802L;

	public PlayerNotFoundException() {
		super();
	}

	public PlayerNotFoundException(final String message) {
		super(message);
	}

	public PlayerNotFoundException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	public PlayerNotFoundException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}
}
