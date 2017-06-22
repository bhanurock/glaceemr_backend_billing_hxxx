package com.glenwood.glaceemr.server.application.Bean;

public class CommonResponseBean {
	
	String responseStatus;
	Integer serviceId;
	String patientAccountId;
	String accountServerIp;
	String failedReason;
	Integer rowId;
	String innerRowId;
	Integer taskId;
	Integer denialId;
	
	String ticketNo;
	String empId;
	String actionId;
	Integer arId;
	
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getPatientAccountId() {
		return patientAccountId;
	}
	public void setPatientAccountId(String patientAccountId) {
		this.patientAccountId = patientAccountId;
	}
	public String getAccountServerIp() {
		return accountServerIp;
	}
	public void setAccountServerIp(String accountServerIp) {
		this.accountServerIp = accountServerIp;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public String getInnerRowId() {
		return innerRowId;
	}
	public void setInnerRowId(String innerRowId) {
		this.innerRowId = innerRowId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getDenialId() {
		return denialId;
	}
	public void setDenialId(Integer denialId) {
		this.denialId = denialId;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public Integer getArId() {
		return arId;
	}
	public void setArId(Integer arId) {
		this.arId = arId;
	}
	
}