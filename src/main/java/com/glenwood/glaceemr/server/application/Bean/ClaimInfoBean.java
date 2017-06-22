package com.glenwood.glaceemr.server.application.Bean;

public class ClaimInfoBean {
	
	Integer serviceId;
	String dos;
	String cptCode;
	Integer units;
	String dx1;
	String dx2;
	String dx3;
	String dx4;
	Double charges;
	Double copay;
	Double priPaidAmount;
	Double secPaidAmount;
	
	public ClaimInfoBean(Integer serviceId, String dos, String cptCode,
			Integer units, String dx1, String dx2, String dx3, String dx4,
			Double charges, Double copay, Double priPaidAmount,
			Double secPaidAmount) {
		super();
		this.serviceId = serviceId;
		this.dos = dos;
		this.cptCode = cptCode;
		this.units = units;
		this.dx1 = dx1;
		this.dx2 = dx2;
		this.dx3 = dx3;
		this.dx4 = dx4;
		this.charges = charges;
		this.copay = copay;
		this.priPaidAmount = priPaidAmount;
		this.secPaidAmount = secPaidAmount;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}

	public String getCptCode() {
		return cptCode;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public String getDx1() {
		return dx1;
	}

	public void setDx1(String dx1) {
		this.dx1 = dx1;
	}

	public String getDx2() {
		return dx2;
	}

	public void setDx2(String dx2) {
		this.dx2 = dx2;
	}

	public String getDx3() {
		return dx3;
	}

	public void setDx3(String dx3) {
		this.dx3 = dx3;
	}

	public String getDx4() {
		return dx4;
	}

	public void setDx4(String dx4) {
		this.dx4 = dx4;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Double getCopay() {
		return copay;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public Double getPriPaidAmount() {
		return priPaidAmount;
	}

	public void setPriPaidAmount(Double priPaidAmount) {
		this.priPaidAmount = priPaidAmount;
	}

	public Double getSecPaidAmount() {
		return secPaidAmount;
	}

	public void setSecPaidAmount(Double secPaidAmount) {
		this.secPaidAmount = secPaidAmount;
	}
	
}
