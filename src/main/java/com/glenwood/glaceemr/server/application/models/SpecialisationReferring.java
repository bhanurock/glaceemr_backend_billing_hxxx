package com.glenwood.glaceemr.server.application.models;

/**
 * H077
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialisation_referring")
public class SpecialisationReferring 
{
	@Id
	@Column(name="specialisation_referring_id")
	private Integer specialisationReferringId; 

	@Column(name="specialisation_referring_name")
	private String specialisationReferringName; 

	@Column(name="specialisation_referring_description")
	private String specialisationReferringDescription; 

	@Column(name="nucc_code")
	private String nuccCode;

	public Integer getSpecialisationReferringId() {
		return specialisationReferringId;
	}

	public void setSpecialisationReferringId(Integer specialisationReferringId) {
		this.specialisationReferringId = specialisationReferringId;
	}

	public String getSpecialisationReferringName() {
		return specialisationReferringName;
	}

	public void setSpecialisationReferringName(String specialisationReferringName) {
		this.specialisationReferringName = specialisationReferringName;
	}

	public String getSpecialisationReferringDescription() {
		return specialisationReferringDescription;
	}

	public void setSpecialisationReferringDescription(
			String specialisationReferringDescription) {
		this.specialisationReferringDescription = specialisationReferringDescription;
	}

	public String getNuccCode() {
		return nuccCode;
	}

	public void setNuccCode(String nuccCode) {
		this.nuccCode = nuccCode;
	} 
	
}