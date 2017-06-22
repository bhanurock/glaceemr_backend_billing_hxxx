package com.glenwood.glaceemr.server.application.Bean;

public class PatientInsuranceInfoBean {
	
	String insuranceId;
    Boolean status;
    String insuranceName;
    Short insuranceNumber;
    
	public PatientInsuranceInfoBean(String insuranceId, Boolean status,
			String insuranceName, Short insuranceNumber) {
		super();
		this.insuranceId = insuranceId;
		this.status = status;
		this.insuranceName = insuranceName;
		this.insuranceNumber = insuranceNumber;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public Short getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(Short insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
    
}
