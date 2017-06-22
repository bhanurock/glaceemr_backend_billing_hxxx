package com.glenwood.glaceemr.server.application.models;

/**
 * H093
 */

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "generated_bills_history_details")
public class GeneratedBillsHistoryDetails 
{
	@Id
	@Column(name="generated_bills_history_details_billid")
	private Integer generatedBillsHistoryDetailsBillid; 

	@Column(name="generated_bills_history_details_billrefno")
	private String generatedBillsHistoryDetailsBillrefno;
	
	@Column(name="generated_bills_history_details_current_genetateddate")
	private Date generatedBillsHistoryDetailsCurrentGenerateddate; 

	@Column(name="generated_bills_history_details_patientid")
	private Integer generatedBillsHistoryDetailsPatientid; 

	@Column(name="generated_bills_history_details_cptCode")
	private String generatedBillsHistoryDetailsCptCode; 

	@Column(name="generated_bills_history_details_user")
	private String generatedBillsHistoryDetailsUser; 

	@Column(name="generated_bills_history_details_totalbalance")
	private Integer generatedBillsHistoryDetailsTotalbalance; 

	@Column(name="h555555")
	private Integer h555555; 

	@Column(name="generated_bills_history_details_serviceid")
	private String generatedBillsHistoryDetailsServiceid; 

	@Column(name="generated_bills_history_details_billingReason")
	private Integer generatedBillsHistoryDetailsBillingReason; 

	@Column(name="generated_bills_history_details_lastbilleddate")
	private Date generatedBillsHistoryDetailsLastbilleddate; 

	@Column(name="generated_bills_history_details_unknown1")
	private Date generatedBillsHistoryDetailsUnknown1; 

	@Column(name="generated_bills_history_details_unknown2")
	private String generatedBillsHistoryDetailsUnknown2; 
	
	@Column(name="generated_bills_history_details_ens_batch_no")
	private String generatedBillsHistoryDetailsEnsBatchNo;
	
	@Column(name="generated_bills_history_details_mailed_status")
	private Integer generatedBillsHistoryDetailsMailedStatus; 
	
	@Column(name="generated_bills_history_details_file_path")
	private Integer generatedBillsHistoryDetailsFilePath; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="generated_bills_history_details_patientid", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
	PatientRegistration patientRegistration;

	public Integer getGeneratedBillsHistoryDetailsBillid() {
		return generatedBillsHistoryDetailsBillid;
	}

	public void setGeneratedBillsHistoryDetailsBillid(
			Integer generatedBillsHistoryDetailsBillid) {
		this.generatedBillsHistoryDetailsBillid = generatedBillsHistoryDetailsBillid;
	}

	public String getGeneratedBillsHistoryDetailsBillrefno() {
		return generatedBillsHistoryDetailsBillrefno;
	}

	public void setGeneratedBillsHistoryDetailsBillrefno(
			String generatedBillsHistoryDetailsBillrefno) {
		this.generatedBillsHistoryDetailsBillrefno = generatedBillsHistoryDetailsBillrefno;
	}

	public Date getGeneratedBillsHistoryDetailsCurrentGenerateddate() {
		return generatedBillsHistoryDetailsCurrentGenerateddate;
	}

	public void setGeneratedBillsHistoryDetailsCurrentGenerateddate(
			Date generatedBillsHistoryDetailsCurrentGenerateddate) {
		this.generatedBillsHistoryDetailsCurrentGenerateddate = generatedBillsHistoryDetailsCurrentGenerateddate;
	}

	public Integer getGeneratedBillsHistoryDetailsPatientid() {
		return generatedBillsHistoryDetailsPatientid;
	}

	public void setGeneratedBillsHistoryDetailsPatientid(
			Integer generatedBillsHistoryDetailsPatientid) {
		this.generatedBillsHistoryDetailsPatientid = generatedBillsHistoryDetailsPatientid;
	}

	public String getGeneratedBillsHistoryDetailsCptCode() {
		return generatedBillsHistoryDetailsCptCode;
	}

	public void setGeneratedBillsHistoryDetailsCptCode(
			String generatedBillsHistoryDetailsCptCode) {
		this.generatedBillsHistoryDetailsCptCode = generatedBillsHistoryDetailsCptCode;
	}

	public String getGeneratedBillsHistoryDetailsUser() {
		return generatedBillsHistoryDetailsUser;
	}

	public void setGeneratedBillsHistoryDetailsUser(
			String generatedBillsHistoryDetailsUser) {
		this.generatedBillsHistoryDetailsUser = generatedBillsHistoryDetailsUser;
	}

	public Integer getGeneratedBillsHistoryDetailsTotalbalance() {
		return generatedBillsHistoryDetailsTotalbalance;
	}

	public void setGeneratedBillsHistoryDetailsTotalbalance(
			Integer generatedBillsHistoryDetailsTotalbalance) {
		this.generatedBillsHistoryDetailsTotalbalance = generatedBillsHistoryDetailsTotalbalance;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getGeneratedBillsHistoryDetailsServiceid() {
		return generatedBillsHistoryDetailsServiceid;
	}

	public void setGeneratedBillsHistoryDetailsServiceid(
			String generatedBillsHistoryDetailsServiceid) {
		this.generatedBillsHistoryDetailsServiceid = generatedBillsHistoryDetailsServiceid;
	}

	public Integer getGeneratedBillsHistoryDetailsBillingReason() {
		return generatedBillsHistoryDetailsBillingReason;
	}

	public void setGeneratedBillsHistoryDetailsBillingReason(
			Integer generatedBillsHistoryDetailsBillingReason) {
		this.generatedBillsHistoryDetailsBillingReason = generatedBillsHistoryDetailsBillingReason;
	}

	public Date getGeneratedBillsHistoryDetailsLastbilleddate() {
		return generatedBillsHistoryDetailsLastbilleddate;
	}

	public void setGeneratedBillsHistoryDetailsLastbilleddate(
			Date generatedBillsHistoryDetailsLastbilleddate) {
		this.generatedBillsHistoryDetailsLastbilleddate = generatedBillsHistoryDetailsLastbilleddate;
	}

	public Date getGeneratedBillsHistoryDetailsUnknown1() {
		return generatedBillsHistoryDetailsUnknown1;
	}

	public void setGeneratedBillsHistoryDetailsUnknown1(
			Date generatedBillsHistoryDetailsUnknown1) {
		this.generatedBillsHistoryDetailsUnknown1 = generatedBillsHistoryDetailsUnknown1;
	}

	public String getGeneratedBillsHistoryDetailsUnknown2() {
		return generatedBillsHistoryDetailsUnknown2;
	}

	public void setGeneratedBillsHistoryDetailsUnknown2(
			String generatedBillsHistoryDetailsUnknown2) {
		this.generatedBillsHistoryDetailsUnknown2 = generatedBillsHistoryDetailsUnknown2;
	}

	public String getGeneratedBillsHistoryDetailsEnsBatchNo() {
		return generatedBillsHistoryDetailsEnsBatchNo;
	}

	public void setGeneratedBillsHistoryDetailsEnsBatchNo(
			String generatedBillsHistoryDetailsEnsBatchNo) {
		this.generatedBillsHistoryDetailsEnsBatchNo = generatedBillsHistoryDetailsEnsBatchNo;
	}

	public Integer getGeneratedBillsHistoryDetailsMailedStatus() {
		return generatedBillsHistoryDetailsMailedStatus;
	}

	public void setGeneratedBillsHistoryDetailsMailedStatus(
			Integer generatedBillsHistoryDetailsMailedStatus) {
		this.generatedBillsHistoryDetailsMailedStatus = generatedBillsHistoryDetailsMailedStatus;
	}

	public Integer getGeneratedBillsHistoryDetailsFilePath() {
		return generatedBillsHistoryDetailsFilePath;
	}

	public void setGeneratedBillsHistoryDetailsFilePath(
			Integer generatedBillsHistoryDetailsFilePath) {
		this.generatedBillsHistoryDetailsFilePath = generatedBillsHistoryDetailsFilePath;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

}