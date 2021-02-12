package com.affinitynow.app.exceptions;

public class UserNotFoundException extends Exception{

	/**
     *
     */
    private static final long serialVersionUID = 8343024257933843182L;

    public UserNotFoundException(String string) {
        super(string);
	}
}
