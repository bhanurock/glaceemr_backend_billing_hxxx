package com.glenwood.glaceemr.server.application.services.onHold;

import java.util.List;

public class OnHoldResponseBean {
	
	List<OnHoldDetailBean> denialResponse;
	boolean status;
	
	public List<OnHoldDetailBean> getDenialResponse() {
		return denialResponse;
	}
	
	public void setDenialResponse(List<OnHoldDetailBean> denialResponse) {
		this.denialResponse = denialResponse;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
