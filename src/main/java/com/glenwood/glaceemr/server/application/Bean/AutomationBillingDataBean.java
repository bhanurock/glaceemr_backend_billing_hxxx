package com.glenwood.glaceemr.server.application.Bean;

public class AutomationBillingDataBean {

	String runTime;
	Integer automationCount;
	String automationDetailsDesc;
	String status;
	public AutomationBillingDataBean(String runTime, Integer automationCount, String automationDetailsDesc,
			String status) {
		super();
		this.runTime = runTime;
		this.automationCount = automationCount;
		this.automationDetailsDesc = automationDetailsDesc;
		this.status = status;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public Integer getAutomationCount() {
		return automationCount;
	}
	public void setAutomationCount(Integer automationCount) {
		this.automationCount = automationCount;
	}
	public String getAutomationDetailsDesc() {
		return automationDetailsDesc;
	}
	public void setAutomationDetailsDesc(String automationDetailsDesc) {
		this.automationDetailsDesc = automationDetailsDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}