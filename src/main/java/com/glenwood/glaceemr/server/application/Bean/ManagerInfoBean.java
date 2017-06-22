package com.glenwood.glaceemr.server.application.Bean;

public class ManagerInfoBean 
{
	String nonServiceDetailId;
	String denialIds;
	String accServerIp;
	String status;
	Integer adHistoryId;
	String arId;
	String lastModDate;
	String serviceId;
	Integer adAhServiceId;
	String  adAhActionTakenDate;
	Integer intServiceId;
	Integer arIdInteger;
	Integer taskId;
	Integer moduleId;
	String insName;
	
	Integer actionId;
	Integer denialId;
	Integer accountId;
	
	public ManagerInfoBean() {
		super();
	}
	
	public ManagerInfoBean(Integer adHistoryId, Integer actionId, String adAhActionTakenDate) {
		super();
		this.adHistoryId = adHistoryId;
		this.actionId = actionId;
		this.adAhActionTakenDate = adAhActionTakenDate;
	}

	public ManagerInfoBean(Integer adAhServiceId, String adAhActionTakenDate) {
		super();
		this.adAhServiceId = adAhServiceId;
		this.adAhActionTakenDate = adAhActionTakenDate;
	}

	public String getNonServiceDetailId() {
		return nonServiceDetailId;
	}

	public void setNonServiceDetailId(String nonServiceDetailId) {
		this.nonServiceDetailId = nonServiceDetailId;
	}

	public String getDenialIds() {
		return denialIds;
	}

	public void setDenialIds(String denialIds) {
		this.denialIds = denialIds;
	}

	public String getAccServerIp() {
		return accServerIp;
	}

	public void setAccServerIp(String accServerIp) {
		this.accServerIp = accServerIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAdHistoryId() {
		return adHistoryId;
	}

	public void setAdHistoryId(Integer adHistoryId) {
		this.adHistoryId = adHistoryId;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getAdAhServiceId() {
		return adAhServiceId;
	}

	public void setAdAhServiceId(Integer adAhServiceId) {
		this.adAhServiceId = adAhServiceId;
	}

	public String getAdAhActionTakenDate() {
		return adAhActionTakenDate;
	}

	public void setAdAhActionTakenDate(String adAhActionTakenDate) {
		this.adAhActionTakenDate = adAhActionTakenDate;
	}

	public Integer getIntServiceId() {
		return intServiceId;
	}

	public void setIntServiceId(Integer intServiceId) {
		this.intServiceId = intServiceId;
	}

	public Integer getArIdInteger() {
		return arIdInteger;
	}

	public void setArIdInteger(Integer arIdInteger) {
		this.arIdInteger = arIdInteger;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getDenialId() {
		return denialId;
	}

	public void setDenialId(Integer denialId) {
		this.denialId = denialId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
}