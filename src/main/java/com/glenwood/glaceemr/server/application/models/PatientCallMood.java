package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_call_mood")
public class PatientCallMood {

	@Id
	@Column(name="patient_call_mood_id")
	private Integer patientCallMoodId;

	@Column(name="patient_call_mood_name")
	private String patientCallMoodName;

	public Integer getPatientCallMoodId() {
		return patientCallMoodId;
	}

	public void setPatientCallMoodId(Integer patientCallMoodId) {
		this.patientCallMoodId = patientCallMoodId;
	}

	public String getPatientCallMoodName() {
		return patientCallMoodName;
	}

	public void setPatientCallMoodName(String patientCallMoodName) {
		this.patientCallMoodName = patientCallMoodName;
	}
	
	
}