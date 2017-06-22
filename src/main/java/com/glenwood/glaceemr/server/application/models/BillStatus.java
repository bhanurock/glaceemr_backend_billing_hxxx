package com.glenwood.glaceemr.server.application.models;

/**
 * H092
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_status")
public class BillStatus 
{
	@Id
	@Column(name="bill_status_billid")
	private Integer billStatusBillid; 

	@Column(name="bill_status_nextbillid")
	private Integer billStatusNextbillid; 

	@Column(name="bill_status_status")
	private String billStatusStatus; 

	@Column(name="bill_status_scpt_id")
	private Integer billStatusScptId; 

	@Column(name="bill_status_description")
	private String billStatusDescription; 

	@Column(name="bill_status_billintervel")
	private Integer billStatusBillintervel; 

	@Column(name="h555555")
	private Integer h555555;

	public Integer getBillStatusBillid() {
		return billStatusBillid;
	}

	public void setBillStatusBillid(Integer billStatusBillid) {
		this.billStatusBillid = billStatusBillid;
	}

	public Integer getBillStatusNextbillid() {
		return billStatusNextbillid;
	}

	public void setBillStatusNextbillid(Integer billStatusNextbillid) {
		this.billStatusNextbillid = billStatusNextbillid;
	}

	public String getBillStatusStatus() {
		return billStatusStatus;
	}

	public void setBillStatusStatus(String billStatusStatus) {
		this.billStatusStatus = billStatusStatus;
	}

	public Integer getBillStatusScptId() {
		return billStatusScptId;
	}

	public void setBillStatusScptId(Integer billStatusScptId) {
		this.billStatusScptId = billStatusScptId;
	}

	public String getBillStatusDescription() {
		return billStatusDescription;
	}

	public void setBillStatusDescription(String billStatusDescription) {
		this.billStatusDescription = billStatusDescription;
	}

	public Integer getBillStatusBillintervel() {
		return billStatusBillintervel;
	}

	public void setBillStatusBillintervel(Integer billStatusBillintervel) {
		this.billStatusBillintervel = billStatusBillintervel;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}
	
	
}