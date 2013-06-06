package com.pearson.ed.prospero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BufferException extends RuntimeException 
{


	private static final long serialVersionUID = -399001264200373120L;

	public BufferException() {
        super();
    }

    public BufferException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BufferException(final String message) {
        super(message);
    }

    public BufferException(final Throwable cause) {
        super(cause);
    }
}
