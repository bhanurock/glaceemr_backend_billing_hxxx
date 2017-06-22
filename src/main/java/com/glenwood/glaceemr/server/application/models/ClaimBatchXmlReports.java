package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_batch_xml_reports")
public class ClaimBatchXmlReports {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="filename")
	private String filename;

	@Column(name="rptdate")
	private Date rptdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getRptdate() {
		return rptdate;
	}

	public void setRptdate(Date rptdate) {
		this.rptdate = rptdate;
	}
	
	
}
