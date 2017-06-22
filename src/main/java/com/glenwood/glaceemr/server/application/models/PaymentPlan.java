package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payment_plan")
public class PaymentPlan {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_plan_idseq")
		@SequenceGenerator(name ="payment_plan_idseq", sequenceName="payment_plan_idseq", allocationSize=1)
		@Column(name="payment_plan_id")
		private Integer paymentPlanId;
		
		@Column(name="payment_plan_service_id")
		private Integer paymentPlanServiceId;

		@Column(name="payment_plan_patient_id")
		private Integer paymentPlanPatientId;

		@Column(name="payment_plan_isactive")
		private Boolean paymentPlanIsactive;

		@Column(name="payment_plan_amount")
		private Integer paymentPlanAmount;
		
		@Column(name="payment_plan_payment_amount")
		private Integer paymentPlanPaymentAmount;

		@Column(name="payment_plan_weekly")
		private Boolean paymentPlanWeekly;

		@Column(name="payment_plan_monthly")
		private Boolean paymentPlanMonthly;

		@Column(name="payment_plan_notes")
		private String paymentPlanNotes;
		
		@Column(name="payment_plan_from_dos")
		private Date paymentPlanFromDos;

		@Column(name="payment_plan_to_dos")
		private Date paymentPlanToDos;

		@Column(name="payment_plan_created_date")
		private Date paymentPlanCreatedDate;
		
		@Column(name="payment_plan_created_by")
		private Integer paymentPlanCreatedBy;

		@Column(name="payment_plan_modified_date")
		private Date paymentPlanModifiedDate;

		@Column(name="payment_plan_modified_by")
		private Integer paymentPlanModifiedBy;
		
		@Column(name="payment_plan_firstpayment_date")
		private Date paymentPlanFirstpaymentDate ;
		
		@Column(name="payment_plan_recentlypayed_date")
		private Date paymentPlanRecentlypayedDate ;

		public Integer getPaymentPlanId() {
			return paymentPlanId;
		}

		public void setPaymentPlanId(Integer paymentPlanId) {
			this.paymentPlanId = paymentPlanId;
		}

		public Integer getPaymentPlanServiceId() {
			return paymentPlanServiceId;
		}

		public void setPaymentPlanServiceId(Integer paymentPlanServiceId) {
			this.paymentPlanServiceId = paymentPlanServiceId;
		}

		public Integer getPaymentPlanPatientId() {
			return paymentPlanPatientId;
		}

		public void setPaymentPlanPatientId(Integer paymentPlanPatientId) {
			this.paymentPlanPatientId = paymentPlanPatientId;
		}

		public Boolean getPaymentPlanIsactive() {
			return paymentPlanIsactive;
		}

		public void setPaymentPlanIsactive(Boolean paymentPlanIsactive) {
			this.paymentPlanIsactive = paymentPlanIsactive;
		}

		public Integer getPaymentPlanAmount() {
			return paymentPlanAmount;
		}

		public void setPaymentPlanAmount(Integer paymentPlanAmount) {
			this.paymentPlanAmount = paymentPlanAmount;
		}

		public Integer getPaymentPlanPaymentAmount() {
			return paymentPlanPaymentAmount;
		}

		public void setPaymentPlanPaymentAmount(Integer paymentPlanPaymentAmount) {
			this.paymentPlanPaymentAmount = paymentPlanPaymentAmount;
		}

		public Boolean getPaymentPlanWeekly() {
			return paymentPlanWeekly;
		}

		public void setPaymentPlanWeekly(Boolean paymentPlanWeekly) {
			this.paymentPlanWeekly = paymentPlanWeekly;
		}

		public Boolean getPaymentPlanMonthly() {
			return paymentPlanMonthly;
		}

		public void setPaymentPlanMonthly(Boolean paymentPlanMonthly) {
			this.paymentPlanMonthly = paymentPlanMonthly;
		}

		public String getPaymentPlanNotes() {
			return paymentPlanNotes;
		}

		public void setPaymentPlanNotes(String paymentPlanNotes) {
			this.paymentPlanNotes = paymentPlanNotes;
		}

		public Date getPaymentPlanFromDos() {
			return paymentPlanFromDos;
		}

		public void setPaymentPlanFromDos(Date paymentPlanFromDos) {
			this.paymentPlanFromDos = paymentPlanFromDos;
		}

		public Date getPaymentPlanToDos() {
			return paymentPlanToDos;
		}

		public void setPaymentPlanToDos(Date paymentPlanToDos) {
			this.paymentPlanToDos = paymentPlanToDos;
		}

		public Date getPaymentPlanCreatedDate() {
			return paymentPlanCreatedDate;
		}

		public void setPaymentPlanCreatedDate(Date paymentPlanCreatedDate) {
			this.paymentPlanCreatedDate = paymentPlanCreatedDate;
		}

		public Integer getPaymentPlanCreatedBy() {
			return paymentPlanCreatedBy;
		}

		public void setPaymentPlanCreatedBy(Integer paymentPlanCreatedBy) {
			this.paymentPlanCreatedBy = paymentPlanCreatedBy;
		}

		public Date getPaymentPlanModifiedDate() {
			return paymentPlanModifiedDate;
		}

		public void setPaymentPlanModifiedDate(Date paymentPlanModifiedDate) {
			this.paymentPlanModifiedDate = paymentPlanModifiedDate;
		}

		public Integer getPaymentPlanModifiedBy() {
			return paymentPlanModifiedBy;
		}

		public void setPaymentPlanModifiedBy(Integer paymentPlanModifiedBy) {
			this.paymentPlanModifiedBy = paymentPlanModifiedBy;
		}

		public Date getPaymentPlanFirstpaymentDate() {
			return paymentPlanFirstpaymentDate;
		}

		public void setPaymentPlanFirstpaymentDate(Date paymentPlanFirstpaymentDate) {
			this.paymentPlanFirstpaymentDate = paymentPlanFirstpaymentDate;
		}

		public Date getPaymentPlanRecentlypayedDate() {
			return paymentPlanRecentlypayedDate;
		}

		public void setPaymentPlanRecentlypayedDate(Date paymentPlanRecentlypayedDate) {
			this.paymentPlanRecentlypayedDate = paymentPlanRecentlypayedDate;
		}
		
}
