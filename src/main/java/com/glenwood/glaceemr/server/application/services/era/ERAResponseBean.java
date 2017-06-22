package com.glenwood.glaceemr.server.application.services.era;

import java.util.List;

public class ERAResponseBean 
{
	List<ERADetailsBean> eraResponse;
	boolean status;
	
	public List<ERADetailsBean> getEraResponse() {
		return eraResponse;
	}
	public void setEraResponse(List<ERADetailsBean> eraResponse) {
		this.eraResponse = eraResponse;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}