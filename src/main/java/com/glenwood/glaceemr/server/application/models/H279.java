package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "h279")
public class H279 {

	@Column(name="h279002")
	private String h279002;

	@Column(name="h279003")
	private String h279003;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="h279_h279001_seq")
	@SequenceGenerator(name ="h279_h279001_seq", sequenceName="h279_h279001_seq", allocationSize=1)
	@Column(name="h279001")
	private Integer h279001;

	public String getH279002() {
		return h279002;
	}

	public void setH279002(String h279002) {
		this.h279002 = h279002;
	}

	public String getH279003() {
		return h279003;
	}

	public void setH279003(String h279003) {
		this.h279003 = h279003;
	}

	public Integer getH279001() {
		return h279001;
	}

	public void setH279001(Integer h279001) {
		this.h279001 = h279001;
	}
}