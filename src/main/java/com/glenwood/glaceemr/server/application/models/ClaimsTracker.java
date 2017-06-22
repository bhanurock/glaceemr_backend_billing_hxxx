package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "claimstracker")
public class ClaimsTracker {

	@Id
	@Column(name="claimstracker_batchno")
	private Integer claimstrackerBatchno;

	@Column(name="claimstracker_trackertype")
	private Integer claimstrackerTrackertype;

	@Column(name="claimstracker_deliveryfilepath")
	private String claimstrackerDeliveryfilepath;

	@Column(name="claimstracker_deliveryfilename")
	private String claimstrackerDeliveryfilename;

	@Column(name="claimstracker_billingmethod")
	private String claimstrackerBillingmethod;

	@Column(name="claimstracker_trackercount")
	private Integer claimstrackerTrackercount;

	@Column(name="claimstracker_servicecount")
	private Integer claimstrackerServicecount;

	@Column(name="claimstracker_totalcharges")
	private Double claimstrackerTotalcharges;

	@Column(name="claimstracker_generatedby")
	private String claimstrackerGeneratedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="claimstracker_generateddate")
	private Timestamp claimstrackerGenerateddate;

	@Column(name="claimstracker_deliveredby")
	private String claimstrackerDeliveredby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="claimstracker_delivereddate")
	private Timestamp claimstrackerDelivereddate;

	@Column(name="claimstracker_processedby")
	private String claimstrackerProcessedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="claimstracker_processeddate")
	private Timestamp claimstrackerProcesseddate;

	@Column(name="claimstracker_fileaccepted")
	private Integer claimstrackerFileaccepted;

	@Column(name="claimstracker_acceptedcount")
	private Integer claimstrackerAcceptedcount;

	@Column(name="claimstracker_rejectedcount")
	private Integer claimstrackerRejectedcount;

	@Column(name="claimstracker_file_uploaded_printed")
	private Integer claimstrackerFileUploadedPrinted;

	@Column(name="claimstracker_file_completed")
	private Integer claimstrackerFileCompleted;

	@Column(name="claimstracker_session")
	private Integer claimstrackerSession;

	@Column(name="claimstracker_production_type")
	private Integer claimstrackerProductionType;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="claimstracker_file_id")
	private Integer claimstrackerFileId;

	@Column(name="claimstracker_track_no")
	private String claimstrackerTrackNo;

	@Column(name="claimstracker_transaction_id")
	private Integer claimstrackerTransactionId;

	@Column(name="claimstracker_hcfa_fileprinted")
	private Integer claimstrackerHcfaFileprinted;

	@Column(name="claimstracker_clearinghouse_id")
	private Integer claimstrackerClearinghouseId;

	public Integer getClaimstrackerBatchno() {
		return claimstrackerBatchno;
	}

	public void setClaimstrackerBatchno(Integer claimstrackerBatchno) {
		this.claimstrackerBatchno = claimstrackerBatchno;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="claimstracker_batchno", referencedColumnName="h172002",insertable=false, updatable=false)
	private H172 h172;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="claimstracker_batchno", referencedColumnName="batchno",insertable=false, updatable=false)
	private EnsBillsDetails ensBillsDetails;
	

	public Integer getClaimstrackerTrackertype() {
		return claimstrackerTrackertype;
	}

	public void setClaimstrackerTrackertype(Integer claimstrackerTrackertype) {
		this.claimstrackerTrackertype = claimstrackerTrackertype;
	}

	public String getClaimstrackerDeliveryfilepath() {
		return claimstrackerDeliveryfilepath;
	}

	public void setClaimstrackerDeliveryfilepath(String claimstrackerDeliveryfilepath) {
		this.claimstrackerDeliveryfilepath = claimstrackerDeliveryfilepath;
	}

	public String getClaimstrackerDeliveryfilename() {
		return claimstrackerDeliveryfilename;
	}

	public void setClaimstrackerDeliveryfilename(String claimstrackerDeliveryfilename) {
		this.claimstrackerDeliveryfilename = claimstrackerDeliveryfilename;
	}

	public String getClaimstrackerBillingmethod() {
		return claimstrackerBillingmethod;
	}

	public void setClaimstrackerBillingmethod(String claimstrackerBillingmethod) {
		this.claimstrackerBillingmethod = claimstrackerBillingmethod;
	}

	public Integer getClaimstrackerTrackercount() {
		return claimstrackerTrackercount;
	}

	public void setClaimstrackerTrackercount(Integer claimstrackerTrackercount) {
		this.claimstrackerTrackercount = claimstrackerTrackercount;
	}

	public Integer getClaimstrackerServicecount() {
		return claimstrackerServicecount;
	}

	public void setClaimstrackerServicecount(Integer claimstrackerServicecount) {
		this.claimstrackerServicecount = claimstrackerServicecount;
	}

	public Double getClaimstrackerTotalcharges() {
		return claimstrackerTotalcharges;
	}

	public void setClaimstrackerTotalcharges(Double claimstrackerTotalcharges) {
		this.claimstrackerTotalcharges = claimstrackerTotalcharges;
	}

	public String getClaimstrackerGeneratedby() {
		return claimstrackerGeneratedby;
	}

	public void setClaimstrackerGeneratedby(String claimstrackerGeneratedby) {
		this.claimstrackerGeneratedby = claimstrackerGeneratedby;
	}

	public Timestamp getClaimstrackerGenerateddate() {
		return claimstrackerGenerateddate;
	}

	public void setClaimstrackerGenerateddate(Timestamp claimstrackerGenerateddate) {
		this.claimstrackerGenerateddate = claimstrackerGenerateddate;
	}

	public String getClaimstrackerDeliveredby() {
		return claimstrackerDeliveredby;
	}

	public void setClaimstrackerDeliveredby(String claimstrackerDeliveredby) {
		this.claimstrackerDeliveredby = claimstrackerDeliveredby;
	}

	public Timestamp getClaimstrackerDelivereddate() {
		return claimstrackerDelivereddate;
	}

	public void setClaimstrackerDelivereddate(Timestamp claimstrackerDelivereddate) {
		this.claimstrackerDelivereddate = claimstrackerDelivereddate;
	}

	public String getClaimstrackerProcessedby() {
		return claimstrackerProcessedby;
	}

	public void setClaimstrackerProcessedby(String claimstrackerProcessedby) {
		this.claimstrackerProcessedby = claimstrackerProcessedby;
	}

	public Timestamp getClaimstrackerProcesseddate() {
		return claimstrackerProcesseddate;
	}

	public void setClaimstrackerProcesseddate(Timestamp claimstrackerProcesseddate) {
		this.claimstrackerProcesseddate = claimstrackerProcesseddate;
	}

	public Integer getClaimstrackerFileaccepted() {
		return claimstrackerFileaccepted;
	}

	public void setClaimstrackerFileaccepted(Integer claimstrackerFileaccepted) {
		this.claimstrackerFileaccepted = claimstrackerFileaccepted;
	}

	public Integer getClaimstrackerAcceptedcount() {
		return claimstrackerAcceptedcount;
	}

	public void setClaimstrackerAcceptedcount(Integer claimstrackerAcceptedcount) {
		this.claimstrackerAcceptedcount = claimstrackerAcceptedcount;
	}

	public Integer getClaimstrackerRejectedcount() {
		return claimstrackerRejectedcount;
	}

	public void setClaimstrackerRejectedcount(Integer claimstrackerRejectedcount) {
		this.claimstrackerRejectedcount = claimstrackerRejectedcount;
	}

	public Integer getClaimstrackerFileUploadedPrinted() {
		return claimstrackerFileUploadedPrinted;
	}

	public void setClaimstrackerFileUploadedPrinted(Integer claimstrackerFileUploadedPrinted) {
		this.claimstrackerFileUploadedPrinted = claimstrackerFileUploadedPrinted;
	}

	public Integer getClaimstrackerFileCompleted() {
		return claimstrackerFileCompleted;
	}

	public void setClaimstrackerFileCompleted(Integer claimstrackerFileCompleted) {
		this.claimstrackerFileCompleted = claimstrackerFileCompleted;
	}

	public Integer getClaimstrackerSession() {
		return claimstrackerSession;
	}

	public void setClaimstrackerSession(Integer claimstrackerSession) {
		this.claimstrackerSession = claimstrackerSession;
	}

	public Integer getClaimstrackerProductionType() {
		return claimstrackerProductionType;
	}

	public void setClaimstrackerProductionType(Integer claimstrackerProductionType) {
		this.claimstrackerProductionType = claimstrackerProductionType;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getClaimstrackerFileId() {
		return claimstrackerFileId;
	}

	public void setClaimstrackerFileId(Integer claimstrackerFileId) {
		this.claimstrackerFileId = claimstrackerFileId;
	}

	public String getClaimstrackerTrackNo() {
		return claimstrackerTrackNo;
	}

	public void setClaimstrackerTrackNo(String claimstrackerTrackNo) {
		this.claimstrackerTrackNo = claimstrackerTrackNo;
	}

	public Integer getClaimstrackerTransactionId() {
		return claimstrackerTransactionId;
	}

	public void setClaimstrackerTransactionId(Integer claimstrackerTransactionId) {
		this.claimstrackerTransactionId = claimstrackerTransactionId;
	}

	public Integer getClaimstrackerHcfaFileprinted() {
		return claimstrackerHcfaFileprinted;
	}

	public void setClaimstrackerHcfaFileprinted(Integer claimstrackerHcfaFileprinted) {
		this.claimstrackerHcfaFileprinted = claimstrackerHcfaFileprinted;
	}

	public Integer getClaimstrackerClearinghouseId() {
		return claimstrackerClearinghouseId;
	}

	public void setClaimstrackerClearinghouseId(Integer claimstrackerClearinghouseId) {
		this.claimstrackerClearinghouseId = claimstrackerClearinghouseId;
	}
		
}