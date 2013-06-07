package com.pearson.ed.prospero.result;

import java.util.List;

public class Results 
{
	private List<Result> resultsList;
	
	private String errorString;
	
	

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public List<Result> getResultsList() {
		return resultsList;
	}

	public void setResultsList(
			List<Result> resultsList) {
		this.resultsList = resultsList;
	}
	
	
}
