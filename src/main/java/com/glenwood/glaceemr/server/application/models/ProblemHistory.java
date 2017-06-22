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
@Table(name = "problem_history")
public class ProblemHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="problem_history_id_seq")
	@SequenceGenerator(name ="problem_history_id_seq", sequenceName="problem_history_id_seq", allocationSize=1)
	@Column(name="problem_history_id")
	private Integer problemHistoryId;

	@Column(name="problem_history_problem_uniqueid")
	private Integer problemHistoryProblemUniqueid;

	@Column(name="problem_history_sequence")
	private Integer problemHistorySequence;

	@Column(name="problem_history_forwarded_flag")
	private Boolean problemHistoryForwardedFlag;

	@Column(name="problem_history_forwarded_by")
	private String problemHistoryForwardedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_history_modified_on")
	private Timestamp problemHistoryModifiedOn;

	@Column(name="problem_history_forwarded_to")
	private String problemHistoryForwardedTo;

	@Column(name="problem_history_message")
	private String problemHistoryMessage;

	public Integer getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Integer problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}

	public Integer getProblemHistoryProblemUniqueid() {
		return problemHistoryProblemUniqueid;
	}

	public void setProblemHistoryProblemUniqueid(
			Integer problemHistoryProblemUniqueid) {
		this.problemHistoryProblemUniqueid = problemHistoryProblemUniqueid;
	}

	public Integer getProblemHistorySequence() {
		return problemHistorySequence;
	}

	public void setProblemHistorySequence(Integer problemHistorySequence) {
		this.problemHistorySequence = problemHistorySequence;
	}

	public Boolean getProblemHistoryForwardedFlag() {
		return problemHistoryForwardedFlag;
	}

	public void setProblemHistoryForwardedFlag(Boolean problemHistoryForwardedFlag) {
		this.problemHistoryForwardedFlag = problemHistoryForwardedFlag;
	}

	public String getProblemHistoryForwardedBy() {
		return problemHistoryForwardedBy;
	}

	public void setProblemHistoryForwardedBy(String problemHistoryForwardedBy) {
		this.problemHistoryForwardedBy = problemHistoryForwardedBy;
	}

	public Timestamp getProblemHistoryModifiedOn() {
		return problemHistoryModifiedOn;
	}

	public void setProblemHistoryModifiedOn(Timestamp problemHistoryModifiedOn) {
		this.problemHistoryModifiedOn = problemHistoryModifiedOn;
	}

	public String getProblemHistoryForwardedTo() {
		return problemHistoryForwardedTo;
	}

	public void setProblemHistoryForwardedTo(String problemHistoryForwardedTo) {
		this.problemHistoryForwardedTo = problemHistoryForwardedTo;
	}

	public String getProblemHistoryMessage() {
		return problemHistoryMessage;
	}

	public void setProblemHistoryMessage(String problemHistoryMessage) {
		this.problemHistoryMessage = problemHistoryMessage;
	}
	
}