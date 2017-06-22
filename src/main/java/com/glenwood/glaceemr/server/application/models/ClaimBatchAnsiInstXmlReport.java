package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_batch_ansi_inst_xml_report")
public class ClaimBatchAnsiInstXmlReport {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="filename")
	private String filename;

	@Column(name="rptdate")
	private Date rptdate;
}
