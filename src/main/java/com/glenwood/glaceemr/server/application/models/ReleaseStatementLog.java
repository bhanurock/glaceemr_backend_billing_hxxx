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
@Table(name = "release_statement_log")
public class ReleaseStatementLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="release_statement_log_release_statement_log_id_seq")
	@SequenceGenerator(name ="release_statement_log_release_statement_log_id_seq", sequenceName="release_statement_log_release_statement_log_id_seq", allocationSize=1)
	@Column(name="release_statement_log_id")
	private Integer releaseStatementLogId;

	@Column(name="release_statement_log_serviceid")
	private Integer releaseStatementLogServiceid;

	@Column(name="release_statement_log_patientid")
	private Integer releaseStatementLogPatientid;

	@Column(name="release_statement_log_type")
	private Integer releaseStatementLogType;

	@Column(name="release_statement_log_billingstatus")
	private String releaseStatementLogBillingstatus;

	@Column(name="release_statement_log_releasedby")
	private String releaseStatementLogReleasedby;

	@Column(name="release_statement_log_date")
	private Timestamp releaseStatementLogDate;

	@Column(name="flag")
	private Integer flag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="release_statement_log_patientid", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
	PatientRegistration patientRegistration;

	public Integer getReleaseStatementLogId() {
		return releaseStatementLogId;
	}

	public void setReleaseStatementLogId(Integer releaseStatementLogId) {
		this.releaseStatementLogId = releaseStatementLogId;
	}

	public Integer getReleaseStatementLogServiceid() {
		return releaseStatementLogServiceid;
	}

	public void setReleaseStatementLogServiceid(Integer releaseStatementLogServiceid) {
		this.releaseStatementLogServiceid = releaseStatementLogServiceid;
	}

	public Integer getReleaseStatementLogPatientid() {
		return releaseStatementLogPatientid;
	}

	public void setReleaseStatementLogPatientid(Integer releaseStatementLogPatientid) {
		this.releaseStatementLogPatientid = releaseStatementLogPatientid;
	}

	public Integer getReleaseStatementLogType() {
		return releaseStatementLogType;
	}

	public void setReleaseStatementLogType(Integer releaseStatementLogType) {
		this.releaseStatementLogType = releaseStatementLogType;
	}

	public String getReleaseStatementLogBillingstatus() {
		return releaseStatementLogBillingstatus;
	}

	public void setReleaseStatementLogBillingstatus(
			String releaseStatementLogBillingstatus) {
		this.releaseStatementLogBillingstatus = releaseStatementLogBillingstatus;
	}

	public String getReleaseStatementLogReleasedby() {
		return releaseStatementLogReleasedby;
	}

	public void setReleaseStatementLogReleasedby(
			String releaseStatementLogReleasedby) {
		this.releaseStatementLogReleasedby = releaseStatementLogReleasedby;
	}

	public Timestamp getReleaseStatementLogDate() {
		return releaseStatementLogDate;
	}

	public void setReleaseStatementLogDate(Timestamp releaseStatementLogDate) {
		this.releaseStatementLogDate = releaseStatementLogDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
