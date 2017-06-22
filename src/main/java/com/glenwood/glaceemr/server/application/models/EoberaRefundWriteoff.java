package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "eobera_refund_writeoff")
public class EoberaRefundWriteoff {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="eobera_refund_writeoff_eobera_refund_writeoff_id_seq")
	@SequenceGenerator(name ="eobera_refund_writeoff_eobera_refund_writeoff_id_seq", sequenceName="eobera_refund_writeoff_eobera_refund_writeoff_id_seq", allocationSize=1)
	@Column(name="eobera_refund_writeoff_id")
	private Integer eoberaRefundWriteoffId;

	@Column(name="eobera_refund_writeoff_type")
	private Integer eoberaRefundWriteoffType;

	@Column(name="eobera_refund_writeoff_masterid")
	private Integer eoberaRefundWriteoffMasterid;

	@Column(name="eobera_refund_writeoff_dceid")
	private String eoberaRefundWriteoffDceid;

	@Column(name="eobera_refund_writeoff_receiptid")
	private Integer eoberaRefundWriteoffReceiptid;

	@Column(name="eobera_refund_writeoff_nonserviceid")
	private Integer eoberaRefundWriteoffNonserviceid;

	@Column(name="eobera_refund_writeoff_amount")
	private Double eoberaRefundWriteoffAmount;

	@Column(name="eobera_refund_writeoff_reason")
	private String eoberaRefundWriteoffReason;

	@Column(name="eobera_refund_writeoff_user")
	private Integer eoberaRefundWriteoffUser;

	@Column(name="eobera_refund_writeoff_date")
	private Date eoberaRefundWriteoffDate;

	@Column(name="eobera_refund_writeoff_reference")
	private String eoberaRefundWriteoffReference;

	public Integer getEoberaRefundWriteoffId() {
		return eoberaRefundWriteoffId;
	}

	public void setEoberaRefundWriteoffId(Integer eoberaRefundWriteoffId) {
		this.eoberaRefundWriteoffId = eoberaRefundWriteoffId;
	}

	public Integer getEoberaRefundWriteoffType() {
		return eoberaRefundWriteoffType;
	}

	public void setEoberaRefundWriteoffType(Integer eoberaRefundWriteoffType) {
		this.eoberaRefundWriteoffType = eoberaRefundWriteoffType;
	}

	public Integer getEoberaRefundWriteoffMasterid() {
		return eoberaRefundWriteoffMasterid;
	}

	public void setEoberaRefundWriteoffMasterid(Integer eoberaRefundWriteoffMasterid) {
		this.eoberaRefundWriteoffMasterid = eoberaRefundWriteoffMasterid;
	}

	public String getEoberaRefundWriteoffDceid() {
		return eoberaRefundWriteoffDceid;
	}

	public void setEoberaRefundWriteoffDceid(String eoberaRefundWriteoffDceid) {
		this.eoberaRefundWriteoffDceid = eoberaRefundWriteoffDceid;
	}

	public Integer getEoberaRefundWriteoffReceiptid() {
		return eoberaRefundWriteoffReceiptid;
	}

	public void setEoberaRefundWriteoffReceiptid(
			Integer eoberaRefundWriteoffReceiptid) {
		this.eoberaRefundWriteoffReceiptid = eoberaRefundWriteoffReceiptid;
	}

	public Integer getEoberaRefundWriteoffNonserviceid() {
		return eoberaRefundWriteoffNonserviceid;
	}

	public void setEoberaRefundWriteoffNonserviceid(
			Integer eoberaRefundWriteoffNonserviceid) {
		this.eoberaRefundWriteoffNonserviceid = eoberaRefundWriteoffNonserviceid;
	}

	public Double getEoberaRefundWriteoffAmount() {
		return eoberaRefundWriteoffAmount;
	}

	public void setEoberaRefundWriteoffAmount(Double eoberaRefundWriteoffAmount) {
		this.eoberaRefundWriteoffAmount = eoberaRefundWriteoffAmount;
	}

	public String getEoberaRefundWriteoffReason() {
		return eoberaRefundWriteoffReason;
	}

	public void setEoberaRefundWriteoffReason(String eoberaRefundWriteoffReason) {
		this.eoberaRefundWriteoffReason = eoberaRefundWriteoffReason;
	}

	public Integer getEoberaRefundWriteoffUser() {
		return eoberaRefundWriteoffUser;
	}

	public void setEoberaRefundWriteoffUser(Integer eoberaRefundWriteoffUser) {
		this.eoberaRefundWriteoffUser = eoberaRefundWriteoffUser;
	}

	public Date getEoberaRefundWriteoffDate() {
		return eoberaRefundWriteoffDate;
	}

	public void setEoberaRefundWriteoffDate(Date eoberaRefundWriteoffDate) {
		this.eoberaRefundWriteoffDate = eoberaRefundWriteoffDate;
	}

	public String getEoberaRefundWriteoffReference() {
		return eoberaRefundWriteoffReference;
	}

	public void setEoberaRefundWriteoffReference(
			String eoberaRefundWriteoffReference) {
		this.eoberaRefundWriteoffReference = eoberaRefundWriteoffReference;
	}
	
}