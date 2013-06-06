package com.pearson.ed.prospero.model.v1.buffers;

public class ProsperoBuffer 
{
	private String idString;
	private int capacity;
	private int usage;

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}
	

}
