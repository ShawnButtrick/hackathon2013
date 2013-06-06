package com.pearson.ed.prospero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnviroSubscribePathException extends RuntimeException 
{

	private static final long serialVersionUID = 5005736899726843265L;

	public EnviroSubscribePathException() {
        super();
    }

    public EnviroSubscribePathException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EnviroSubscribePathException(final String message) {
        super(message);
    }

    public EnviroSubscribePathException(final Throwable cause) {
        super(cause);
    }
}
