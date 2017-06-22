package com.glenwood.glaceemr.server.application.services.stmts;

import java.util.List;

public class StatementsResponseBean {

	List<StatementsBean> statementsResponse;
	boolean status;
	
	public List<StatementsBean> getStatementsResponse() {
		return statementsResponse;
	}
	public void setSelectionResponse(List<StatementsBean> statementsResponse) {
		this.statementsResponse = statementsResponse;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}