package com.acciona.aqsw.mash.api.exception;

public abstract class EntityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2329920266671629627L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(final String message) {
		super(message);
	}

	public EntityNotFoundException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	public EntityNotFoundException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}

}
