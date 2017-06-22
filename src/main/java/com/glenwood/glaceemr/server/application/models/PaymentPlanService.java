package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "payment_plan_service")
public class PaymentPlanService {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_plan_service_idseq")
	@SequenceGenerator(name ="payment_plan_service_idseq", sequenceName="payment_plan_service_idseq", allocationSize=1)
	@Column(name="payment_plan_service_id")
	private Integer paymentPlanServiceId;
	
	@Column(name="payment_plan_service_serviceid")
	private Integer paymentPlanServiceServiceid;

	@Column(name="payment_plan_service_planid")
	private Integer paymentPlanServicePlanid;

	@Column(name="payment_plan_service_balance")
	private Integer paymentPlanServiceBalance;

	@Column(name="payment_plan_service_dos")
	private Date paymentPlanServiceDos;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_plan_service_planid", referencedColumnName="payment_plan_id", insertable=false, updatable=false)
	@JsonManagedReference
    PaymentPlan paymentPlan;
	
	
	public Integer getPaymentPlanServiceId() {
		return paymentPlanServiceId;
	}

	public void setPaymentPlanServiceId(Integer paymentPlanServiceId) {
		this.paymentPlanServiceId = paymentPlanServiceId;
	}

	public Integer getPaymentPlanServiceServiceid() {
		return paymentPlanServiceServiceid;
	}

	public void setPaymentPlanServiceServiceid(Integer paymentPlanServiceServiceid) {
		this.paymentPlanServiceServiceid = paymentPlanServiceServiceid;
	}

	public Integer getPaymentPlanServicePlanid() {
		return paymentPlanServicePlanid;
	}

	public void setPaymentPlanServicePlanid(Integer paymentPlanServicePlanid) {
		this.paymentPlanServicePlanid = paymentPlanServicePlanid;
	}

	public Integer getPaymentPlanServiceBalance() {
		return paymentPlanServiceBalance;
	}

	public void setPaymentPlanServiceBalance(Integer paymentPlanServiceBalance) {
		this.paymentPlanServiceBalance = paymentPlanServiceBalance;
	}

	public Date getPaymentPlanServiceDos() {
		return paymentPlanServiceDos;
	}

	public void setPaymentPlanServiceDos(Date paymentPlanServiceDos) {
		this.paymentPlanServiceDos = paymentPlanServiceDos;
	}

	public PaymentPlan getPaymentPlan() {
		return paymentPlan;
	}

	public void setPaymentPlan(PaymentPlan paymentPlan) {
		this.paymentPlan = paymentPlan;
	}

	

	
}
