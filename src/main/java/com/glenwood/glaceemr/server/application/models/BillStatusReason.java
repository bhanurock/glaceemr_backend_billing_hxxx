package com.glenwood.glaceemr.server.application.models;

import java.security.Timestamp;

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

@Entity
@Table(name = "billstatus_reason")
public class BillStatusReason {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="billstatus_reason_seq")
		@SequenceGenerator(name ="billstatus_reason_seq", sequenceName="billstatus_reason_seq", allocationSize=1)
		@Column(name="billstatus_reason_id")
		private Integer billstatusReasonId;

		@Column(name="billstatus_reason_patientid")
		private Integer billstatusReasonPatientid ;

		@Column(name="billstatus_reason_desc")
		private Integer billstatusReasonDesc;

		@Column(name="billstatus_reason_notes")
		private String billstatusReasonNotes;

		@Column(name="billstatus_reason_oldstatus")
		private String billstatusReasonOldstatus;

		@Column(name="billstatus_reason_username")
		private String billstatusReasonUsername;

		@Column(name="billstatus_reason_date")
		private Timestamp billstatusReasonDate;

		@Column(name="billstatus_reason_activestatus")
		private String billstatusReasonActivestatus;

		@Column(name="billstatus_reason_validationids")
		private String billstatusReasonValidationids;
		
		@Column(name="billstatus_reason_holdclearusername")
		private Timestamp billstatusReasonHoldclearusername;

		@Column(name="billstatus_reason_urlstring")
		private String billstatusReasonUrlstring;

		@Column(name="billstatus_reason_cleareddate")
		private Timestamp billstatusReasonCleareddate;

		@ManyToOne(fetch=FetchType.LAZY)
		@JsonBackReference
		@JoinColumn(name="billstatus_reason_patientid", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
		PatientRegistration patientRegistration;
		
		public Integer getBillstatusReasonId() {
			return billstatusReasonId;
		}

		public void setBillstatusReasonId(Integer billstatusReasonId) {
			this.billstatusReasonId = billstatusReasonId;
		}

		public Integer getBillstatusReasonPatientid() {
			return billstatusReasonPatientid;
		}

		public void setBillstatusReasonPatientid(Integer billstatusReasonPatientid) {
			this.billstatusReasonPatientid = billstatusReasonPatientid;
		}

		public Integer getBillstatusReasonDesc() {
			return billstatusReasonDesc;
		}

		public void setBillstatusReasonDesc(Integer billstatusReasonDesc) {
			this.billstatusReasonDesc = billstatusReasonDesc;
		}

		public String getBillstatusReasonNotes() {
			return billstatusReasonNotes;
		}

		public void setBillstatusReasonNotes(String billstatusReasonNotes) {
			this.billstatusReasonNotes = billstatusReasonNotes;
		}

		public String getBillstatusReasonOldstatus() {
			return billstatusReasonOldstatus;
		}

		public void setBillstatusReasonOldstatus(String billstatusReasonOldstatus) {
			this.billstatusReasonOldstatus = billstatusReasonOldstatus;
		}

		public String getBillstatusReasonUsername() {
			return billstatusReasonUsername;
		}

		public void setBillstatusReasonUsername(String billstatusReasonUsername) {
			this.billstatusReasonUsername = billstatusReasonUsername;
		}

		public Timestamp getBillstatusReasonDate() {
			return billstatusReasonDate;
		}

		public void setBillstatusReasonDate(Timestamp billstatusReasonDate) {
			this.billstatusReasonDate = billstatusReasonDate;
		}

		public String getBillstatusReasonActivestatus() {
			return billstatusReasonActivestatus;
		}

		public void setBillstatusReasonActivestatus(String billstatusReasonActivestatus) {
			this.billstatusReasonActivestatus = billstatusReasonActivestatus;
		}

		public String getBillstatusReasonValidationids() {
			return billstatusReasonValidationids;
		}

		public void setBillstatusReasonValidationids(
				String billstatusReasonValidationids) {
			this.billstatusReasonValidationids = billstatusReasonValidationids;
		}

		public Timestamp getBillstatusReasonHoldclearusername() {
			return billstatusReasonHoldclearusername;
		}

		public void setBillstatusReasonHoldclearusername(
				Timestamp billstatusReasonHoldclearusername) {
			this.billstatusReasonHoldclearusername = billstatusReasonHoldclearusername;
		}

		public String getBillstatusReasonUrlstring() {
			return billstatusReasonUrlstring;
		}

		public void setBillstatusReasonUrlstring(String billstatusReasonUrlstring) {
			this.billstatusReasonUrlstring = billstatusReasonUrlstring;
		}

		public Timestamp getBillstatusReasonCleareddate() {
			return billstatusReasonCleareddate;
		}

		public void setBillstatusReasonCleareddate(Timestamp billstatusReasonCleareddate) {
			this.billstatusReasonCleareddate = billstatusReasonCleareddate;
		}
		  
}
