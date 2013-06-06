package com.pearson.ed.prospero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnviroException extends RuntimeException 
{

	private static final long serialVersionUID = -5659278428252281289L;

	public EnviroException() {
        super();
    }

    public EnviroException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EnviroException(final String message) {
        super(message);
    }

    public EnviroException(final Throwable cause) {
        super(cause);
    }
}
