package com.glenwood.glaceemr.server.application.models;
/**
 * H062
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billing_reason")
public class BillingReason 
{
	@Id
	@Column(name="billing_reason_id")
	private Integer billingReasonId;

	@Column(name="billing_reason_desc")
	private String billingReasonDescription;

	@Column(name="billing_reason_isactive")
	private Boolean billingReasonIsactive;

	public Integer getBillingReasonId() {
		return billingReasonId;
	}

	public void setBillingReasonId(Integer billingReasonId) {
		this.billingReasonId = billingReasonId;
	}

	public String getBillingReasonDescription() {
		return billingReasonDescription;
	}

	public void setBillingReasonDescription(String billingReasonDescription) {
		this.billingReasonDescription = billingReasonDescription;
	}

	public Boolean getBillingReasonIsactive() {
		return billingReasonIsactive;
	}

	public void setBillingReasonIsactive(Boolean billingReasonIsactive) {
		this.billingReasonIsactive = billingReasonIsactive;
	}
	
}