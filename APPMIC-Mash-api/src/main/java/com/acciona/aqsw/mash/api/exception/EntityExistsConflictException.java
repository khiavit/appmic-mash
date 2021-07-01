package com.acciona.aqsw.mash.api.exception;

public abstract class EntityExistsConflictException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -389972868687891268L;

	public EntityExistsConflictException() {
		super();
	}

	public EntityExistsConflictException(final String message) {
		super(message);
	}

	public EntityExistsConflictException(final Exception newOriginalException) {
		super(newOriginalException);
	}

	public EntityExistsConflictException(final String message, final Exception newOriginalException) {
		super(message, newOriginalException);
	}

}
