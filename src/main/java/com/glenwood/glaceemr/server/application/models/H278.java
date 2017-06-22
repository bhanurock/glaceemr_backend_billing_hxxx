package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "h278")
public class H278 {

	@Column(name="h278002")
	private Double h278002;

	@Column(name="h278003")
	private String h278003;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="h278_h278001_seq")
	@SequenceGenerator(name ="h278_h278001_seq", sequenceName="h278_h278001_seq", allocationSize=1)
	@Column(name="h278001")
	private Integer h278001;

	public Double getH278002() {
		return h278002;
	}

	public void setH278002(Double h278002) {
		this.h278002 = h278002;
	}

	public String getH278003() {
		return h278003;
	}

	public void setH278003(String h278003) {
		this.h278003 = h278003;
	}

	public Integer getH278001() {
		return h278001;
	}

	public void setH278001(Integer h278001) {
		this.h278001 = h278001;
	}
}