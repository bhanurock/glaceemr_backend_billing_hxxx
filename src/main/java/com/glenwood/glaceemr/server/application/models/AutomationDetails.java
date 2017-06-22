package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "automation_details")
public class AutomationDetails {

	@Id
	@Column(name="automation_details_id")
	private Integer automationDetailsId;

	@Column(name="automation_details_moduleid")
	private Integer automationDetailsModuleid;

	@Column(name="automation_details_name")
	private String automationDetailsName;
		
	@Column(name="automation_details_desc")
	private String automationDetailsDesc;

	@Column(name="automation_details_isenabled")
	private Integer automationDetailsIsenabled;

	public Integer getAutomationDetailsId() {
		return automationDetailsId;
	}

	public void setAutomationDetailsId(Integer automationDetailsId) {
		this.automationDetailsId = automationDetailsId;
	}

	public Integer getAutomationDetailsModuleid() {
		return automationDetailsModuleid;
	}

	public void setAutomationDetailsModuleid(Integer automationDetailsModuleid) {
		this.automationDetailsModuleid = automationDetailsModuleid;
	}

	public String getAutomationDetailsName() {
		return automationDetailsName;
	}

	public void setAutomationDetailsName(String automationDetailsName) {
		this.automationDetailsName = automationDetailsName;
	}

	public String getAutomationDetailsDesc() {
		return automationDetailsDesc;
	}

	public void setAutomationDetailsDesc(String automationDetailsDesc) {
		this.automationDetailsDesc = automationDetailsDesc;
	}

	public Integer getAutomationDetailsIsenabled() {
		return automationDetailsIsenabled;
	}

	public void setAutomationDetailsIsenabled(Integer automationDetailsIsenabled) {
		this.automationDetailsIsenabled = automationDetailsIsenabled;
	}
}