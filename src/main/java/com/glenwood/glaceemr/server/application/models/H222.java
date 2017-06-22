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
@Table(name = "h222")
public class H222 {

	@Id
	@Column(name="h222001")
	private Integer h222001;

	@Column(name="h222002")
	private String h222002;

	public Integer getH222001() {
		return h222001;
	}

	public void setH222001(Integer h222001) {
		this.h222001 = h222001;
	}

	public String getH222002() {
		return h222002;
	}

	public void setH222002(String h222002) {
		this.h222002 = h222002;
	}
	
	
}