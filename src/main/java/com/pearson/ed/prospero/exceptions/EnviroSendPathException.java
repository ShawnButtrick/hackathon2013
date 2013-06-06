package com.pearson.ed.prospero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnviroSendPathException extends RuntimeException 
{

	private static final long serialVersionUID = -8405428179503188129L;

	public EnviroSendPathException() {
        super();
    }

    public EnviroSendPathException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EnviroSendPathException(final String message) {
        super(message);
    }

    public EnviroSendPathException(final Throwable cause) {
        super(cause);
    }
}
