package com.pearson.ed.prospero.ws;

import com.pearson.ed.prospero.exceptions.EnviroException;
import com.pearson.ed.prospero.exceptions.BufferException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * Base exception handler for all controller classes
 */
public class BaseEndPointExceptionHandler 
{
	private final Logger logger = Logger.getLogger(getClass());
	
	
	@ExceptionHandler(BufferException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleClientErrors2(Exception ex) 
	{
        logger.error(ex.getMessage(), ex);
    }
	
	@ExceptionHandler(EnviroException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleClientErrors(Exception ex) 
	{
        logger.error(ex.getMessage(), ex);
    }
	

//	 // handle required object not found and return 404
//	 @ExceptionHandler(RequiredObjectNotFoundException.class)
//	 @ResponseBody
//	 public SmsUserProfileError handleInternalErrors(Exception ex) {
//		logger.error(ex.toString(), ex);
//		SmsUserProfileError smsUserProfileError = new SmsUserProfileError();
//		smsUserProfileError.setErrorCode("NOT_FOUND");
//		smsUserProfileError.setErrorDescription(ex.getMessage());
//		return smsUserProfileError;
//	}
//    
	
	// handle all others and return 500	 
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleServerErrors(Exception ex) {
        logger.error(ex.toString(), ex);
    }
	
}
