package com.glenwood.glaceemr.server.application.services.OpenProblems;

import java.util.List;

public class OpenProblemsResponseBean {
	
	List<OpenProblemsBean> problemsResponse;
	boolean status;
	
	public List<OpenProblemsBean> getDenialResponse() {
		return problemsResponse;
	}
	
	public void setDenialResponse(List<OpenProblemsBean> problemsResponse) {
		this.problemsResponse = problemsResponse;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
