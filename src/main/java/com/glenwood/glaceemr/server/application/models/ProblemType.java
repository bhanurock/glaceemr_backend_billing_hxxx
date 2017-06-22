package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "problem_type")
public class ProblemType {

	@Id
	@Column(name="problem_type_id")
	private Integer problemTypeId;

	@Column(name="problem_type_name")
	private String problemTypeName;

	@Column(name="problem_type_description")
	private String problemTypeDescription;

	@Column(name="problem_type_color")
	private String problemTypeColor;

	@Column(name="problem_type_group")
	private Integer problemTypeGroup;

	public Integer getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public String getProblemTypeName() {
		return problemTypeName;
	}

	public void setProblemTypeName(String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}

	public String getProblemTypeDescription() {
		return problemTypeDescription;
	}

	public void setProblemTypeDescription(String problemTypeDescription) {
		this.problemTypeDescription = problemTypeDescription;
	}

	public String getProblemTypeColor() {
		return problemTypeColor;
	}

	public void setProblemTypeColor(String problemTypeColor) {
		this.problemTypeColor = problemTypeColor;
	}

	public Integer getProblemTypeGroup() {
		return problemTypeGroup;
	}

	public void setProblemTypeGroup(Integer problemTypeGroup) {
		this.problemTypeGroup = problemTypeGroup;
	}
	
	
}