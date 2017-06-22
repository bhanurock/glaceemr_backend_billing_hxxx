package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ens_automation_details")
public class EnsAutomationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ens_automation_details_id_seq")
	@SequenceGenerator(name ="ens_automation_details_id_seq", sequenceName="ens_automation_details_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="module")
	private Integer module;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="runtime")
	private Timestamp runtime;

	@Column(name="automation_count")
	private Integer automationCount;

	@Column(name="description")
	private String description;

	@Column(name="status")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "module", referencedColumnName = "automation_details_moduleid", insertable = false, updatable = false)
	private AutomationDetails automationDetails;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModule() {
		return module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public Timestamp getRuntime() {
		return runtime;
	}

	public void setRuntime(Timestamp runtime) {
		this.runtime = runtime;
	}

	public Integer getAutomationCount() {
		return automationCount;
	}

	public void setAutomationCount(Integer automationCount) {
		this.automationCount = automationCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AutomationDetails getAutomationDetails() {
		return automationDetails;
	}

	public void setAutomationDetails(AutomationDetails automationDetails) {
		this.automationDetails = automationDetails;
	}
	
}