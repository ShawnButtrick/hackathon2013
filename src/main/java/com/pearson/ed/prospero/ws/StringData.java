package com.pearson.ed.prospero.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "string"
})
@XmlRootElement(name = "StringData")
public class StringData {
	   private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}	   	

}
