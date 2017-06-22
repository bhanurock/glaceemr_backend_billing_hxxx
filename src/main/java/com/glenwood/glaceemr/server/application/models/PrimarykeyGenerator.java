package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//h213
@Entity
@Table(name = "primarykey_generator")
public class PrimarykeyGenerator{
	@Id
	@Column(name="primarykey_generator_tableid")
	private Integer primarykeyGeneratorTableid;

	@Column(name="primarykey_generator_tablename")
	private String 	primarykeyGeneratorTablename;

	@Column(name="primarykey_generator_rowcount")
	private Integer primarykeyGeneratorRowcount;

	@Column(name="primarykey_generator_minval")
	private Integer primarykeyGeneratorMinval;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="primarykey_generator_maxval2")
	private Integer	primarykeyGeneratorMaxval2;

	@Column(name="primarykey_generator_isactive",columnDefinition="integer default 0")
	private Integer	primarykeyGeneratorIsactive;

	public Integer getPrimarykeyGeneratorTableid() {
		return primarykeyGeneratorTableid;
	}

	public void setPrimarykeyGeneratorTableid(Integer primarykeyGeneratorTableid) {
		this.primarykeyGeneratorTableid = primarykeyGeneratorTableid;
	}

	public String getPrimarykeyGeneratorTablename() {
		return primarykeyGeneratorTablename;
	}

	public void setPrimarykeyGeneratorTablename(String primarykeyGeneratorTablename) {
		this.primarykeyGeneratorTablename = primarykeyGeneratorTablename;
	}

	public Integer getPrimarykeyGeneratorRowcount() {
		return primarykeyGeneratorRowcount;
	}

	public void setPrimarykeyGeneratorRowcount(Integer primarykeyGeneratorRowcount) {
		this.primarykeyGeneratorRowcount = primarykeyGeneratorRowcount;
	}

	public Integer getPrimarykeyGeneratorMinval() {
		return primarykeyGeneratorMinval;
	}

	public void setPrimarykeyGeneratorMinval(Integer primarykeyGeneratorMinval) {
		this.primarykeyGeneratorMinval = primarykeyGeneratorMinval;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getPrimarykeyGeneratorMaxval2() {
		return primarykeyGeneratorMaxval2;
	}

	public void setPrimarykeyGeneratorMaxval2(Integer primarykeyGeneratorMaxval2) {
		this.primarykeyGeneratorMaxval2 = primarykeyGeneratorMaxval2;
	}

	public Integer getPrimarykeyGeneratorIsactive() {
		return primarykeyGeneratorIsactive;
	}

	public void setPrimarykeyGeneratorIsactive(Integer primarykeyGeneratorIsactive) {
		this.primarykeyGeneratorIsactive = primarykeyGeneratorIsactive;
	}


	
}
