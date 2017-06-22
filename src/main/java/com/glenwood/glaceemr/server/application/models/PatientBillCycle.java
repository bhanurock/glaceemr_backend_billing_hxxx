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
@Table(name = "patientBillCycle")
public class PatientBillCycle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_billcycle_seq")
	@SequenceGenerator(name ="patient_billcycle_seq", sequenceName="patient_billcycle_seq", allocationSize=1)
	@Column(name="patient_billcycle_id")
	private Integer patientBillcycleId;

	@Column(name="patient_billcycle_patientid")
	private Integer patientBillcyclePatientid;

	@Column(name="patient_billcycle_frequency")
	private Integer patientBillcycleFrequency;

	@Column(name="patient_billcycle_createdon")
	private Timestamp patientBillcycleCreatedon;

	@Column(name="patient_billcycle_createdby")
	private String patientBillcycleCreatedby;

	@Column(name="patient_billcycle_last_modifiedby")
	private String patientBillcycleLastModifiedby;
	
	@Column(name="patient_billcycle_last_modifiedon")
	private Timestamp patientBillcycleLastModifiedon;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="patient_billcycle_patientid", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
	PatientRegistration patientRegistration;
	
	public Integer getPatientBillcycleId() {
		return patientBillcycleId;
	}

	public void setPatientBillcycleId(Integer patientBillcycleId) {
		this.patientBillcycleId = patientBillcycleId;
	}

	public Integer getPatientBillcyclePatientid() {
		return patientBillcyclePatientid;
	}

	public void setPatientBillcyclePatientid(Integer patientBillcyclePatientid) {
		this.patientBillcyclePatientid = patientBillcyclePatientid;
	}

	public Integer getPatientBillcycleFrequency() {
		return patientBillcycleFrequency;
	}

	public void setPatientBillcycleFrequency(Integer patientBillcycleFrequency) {
		this.patientBillcycleFrequency = patientBillcycleFrequency;
	}

	public Timestamp getPatientBillcycleCreatedon() {
		return patientBillcycleCreatedon;
	}

	public void setPatientBillcycleCreatedon(Timestamp patientBillcycleCreatedon) {
		this.patientBillcycleCreatedon = patientBillcycleCreatedon;
	}

	public String getPatientBillcycleCreatedby() {
		return patientBillcycleCreatedby;
	}

	public void setPatientBillcycleCreatedby(String patientBillcycleCreatedby) {
		this.patientBillcycleCreatedby = patientBillcycleCreatedby;
	}

	public String getPatientBillcycleLastModifiedby() {
		return patientBillcycleLastModifiedby;
	}

	public void setPatientBillcycleLastModifiedby(
			String patientBillcycleLastModifiedby) {
		this.patientBillcycleLastModifiedby = patientBillcycleLastModifiedby;
	}

	public Timestamp getPatientBillcycleLastModifiedon() {
		return patientBillcycleLastModifiedon;
	}

	public void setPatientBillcycleLastModifiedon(
			Timestamp patientBillcycleLastModifiedon) {
		this.patientBillcycleLastModifiedon = patientBillcycleLastModifiedon;
	}
	
}
