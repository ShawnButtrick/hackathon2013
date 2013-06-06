package com.pearson.ed.prospero.model.v1.summary;

import java.util.ArrayList;
import java.util.List;

public class StatusesSummary 
{
	private List<ProsperoStatus> prosperoStatuses;

	
	public List<ProsperoStatus> getProsperoStatuses() {
		return prosperoStatuses;
	}
	public void setProsperoStatuses(List<ProsperoStatus> prosperoStatuses) {
		this.prosperoStatuses = prosperoStatuses;
	}


	public StatusesSummary()
	{
		prosperoStatuses = new ArrayList<ProsperoStatus>();
	}
	
}
