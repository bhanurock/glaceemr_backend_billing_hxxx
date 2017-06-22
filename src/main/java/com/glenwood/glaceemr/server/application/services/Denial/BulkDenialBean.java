package com.glenwood.glaceemr.server.application.services.Denial;

import java.util.Date;

public class BulkDenialBean {

	Long nonServiceId;
	Integer denialid;
	String accountno;
	String cpt;
	Date dos;
	Integer serviceId;
	
	
	
	public BulkDenialBean(Long nonServiceId, Integer denialid,
			String accountno, String cpt, Date dos, Integer serviceId) {
		this.nonServiceId = nonServiceId;
		this.denialid = denialid;
		this.accountno = accountno;
		this.cpt = cpt;
		this.dos = dos;
		this.serviceId = serviceId;
	}
	public Long getNonServiceId() {
		return nonServiceId;
	}
	public void setNonServiceId(Long nonServiceId) {
		this.nonServiceId = nonServiceId;
	}
	public Integer getDenialid() {
		return denialid;
	}
	public void setDenialid(Integer denialid) {
		this.denialid = denialid;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getCpt() {
		return cpt;
	}
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	public Date getDos() {
		return dos;
	}
	public void setDos(Date dos) {
		this.dos = dos;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	
}
