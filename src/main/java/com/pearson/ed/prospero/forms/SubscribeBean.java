package com.pearson.ed.prospero.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class SubscribeBean 
{
	@NotEmpty
	private String searchString;
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}


	public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("properties");
        
        sb.append("clientString=");
        if (searchString != null) {
        	sb.append("'").append(searchString).append("', ");
        } else {
        	sb.append(searchString).append(", ");
        }
        
        return sb.toString();
    }
}
