package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "era_file_details")
public class EraFileDetails {

	@Id
	@Column(name="era_file_details_id")
	private Integer eraFileDetailsId;

	@Column(name="era_file_details_checkno")
	private String eraFileDetailsCheckno;

	@Column(name="era_file_details_amount")
	private String eraFileDetailsAmount;
	
	@Formula(value="numeric (era_file_details_amount)")
	private double neweraFileDetailsAmount;

	@Column(name="era_file_details_filename")
	private String eraFileDetailsFilename;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="era_file_details_era_date")
	private Timestamp eraFileDetailsEraDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="era_file_details_downloaded_date")
	private Timestamp eraFileDetailsDownloadedDate;

	@Column(name="era_file_details_isparsed")
	private Integer eraFileDetailsIsparsed;

	@Column(name="era_file_details_isduplicate")
	private Integer eraFileDetailsIsduplicate;

	@Column(name="era_file_details_isread")
	private Integer eraFileDetailsIsread;

	@Column(name="era_file_details_ispdfcreated")
	private Integer eraFileDetailsIspdfcreated;

	@Column(name="era_file_details_ins_name")
	private String eraFileDetailsInsName;

	@Column(name="era_file_details_isfrom_ens")
	private Integer eraFileDetailsIsfromEns;

	@Column(name="era_file_details_error")
	private String eraFileDetailsError;

	public Integer getEraFileDetailsId() {
		return eraFileDetailsId;
	}

	public void setEraFileDetailsId(Integer eraFileDetailsId) {
		this.eraFileDetailsId = eraFileDetailsId;
	}

	public String getEraFileDetailsCheckno() {
		return eraFileDetailsCheckno;
	}

	public void setEraFileDetailsCheckno(String eraFileDetailsCheckno) {
		this.eraFileDetailsCheckno = eraFileDetailsCheckno;
	}
	
	public double getNeweraFileDetailsAmount() {
		return neweraFileDetailsAmount;
	}

	public void setNeweraFileDetailsAmount(double neweraFileDetailsAmount) {
		this.neweraFileDetailsAmount = neweraFileDetailsAmount;
	}

	public String getEraFileDetailsAmount() {
		return eraFileDetailsAmount;
	}

	public void setEraFileDetailsAmount(String eraFileDetailsAmount) {
		this.eraFileDetailsAmount = eraFileDetailsAmount;
	}

	public String getEraFileDetailsFilename() {
		return eraFileDetailsFilename;
	}

	public void setEraFileDetailsFilename(String eraFileDetailsFilename) {
		this.eraFileDetailsFilename = eraFileDetailsFilename;
	}

	public Timestamp getEraFileDetailsEraDate() {
		return eraFileDetailsEraDate;
	}

	public void setEraFileDetailsEraDate(Timestamp eraFileDetailsEraDate) {
		this.eraFileDetailsEraDate = eraFileDetailsEraDate;
	}

	public Timestamp getEraFileDetailsDownloadedDate() {
		return eraFileDetailsDownloadedDate;
	}

	public void setEraFileDetailsDownloadedDate(Timestamp eraFileDetailsDownloadedDate) {
		this.eraFileDetailsDownloadedDate = eraFileDetailsDownloadedDate;
	}

	public Integer getEraFileDetailsIsparsed() {
		return eraFileDetailsIsparsed;
	}

	public void setEraFileDetailsIsparsed(Integer eraFileDetailsIsparsed) {
		this.eraFileDetailsIsparsed = eraFileDetailsIsparsed;
	}

	public Integer getEraFileDetailsIsduplicate() {
		return eraFileDetailsIsduplicate;
	}

	public void setEraFileDetailsIsduplicate(Integer eraFileDetailsIsduplicate) {
		this.eraFileDetailsIsduplicate = eraFileDetailsIsduplicate;
	}

	public Integer getEraFileDetailsIsread() {
		return eraFileDetailsIsread;
	}

	public void setEraFileDetailsIsread(Integer eraFileDetailsIsread) {
		this.eraFileDetailsIsread = eraFileDetailsIsread;
	}

	public Integer getEraFileDetailsIspdfcreated() {
		return eraFileDetailsIspdfcreated;
	}

	public void setEraFileDetailsIspdfcreated(Integer eraFileDetailsIspdfcreated) {
		this.eraFileDetailsIspdfcreated = eraFileDetailsIspdfcreated;
	}

	public String getEraFileDetailsInsName() {
		return eraFileDetailsInsName;
	}

	public void setEraFileDetailsInsName(String eraFileDetailsInsName) {
		this.eraFileDetailsInsName = eraFileDetailsInsName;
	}

	public Integer getEraFileDetailsIsfromEns() {
		return eraFileDetailsIsfromEns;
	}

	public void setEraFileDetailsIsfromEns(Integer eraFileDetailsIsfromEns) {
		this.eraFileDetailsIsfromEns = eraFileDetailsIsfromEns;
	}

	public String getEraFileDetailsError() {
		return eraFileDetailsError;
	}

	public void setEraFileDetailsError(String eraFileDetailsError) {
		this.eraFileDetailsError = eraFileDetailsError;
	}
	
}