package com.glenwood.glaceemr.server.application.services.ar;

import java.util.List;

public class ArResponseBean 
{
	List<ArDetailBean> arResponse;
	boolean status;
	
	public List<ArDetailBean> getArResponse() {
		return arResponse;
	}
	public void setArResponse(List<ArDetailBean> arResponse) {
		this.arResponse = arResponse;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}