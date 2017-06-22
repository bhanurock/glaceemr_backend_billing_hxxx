package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "hutil_eob_sd")
public class HutilEobSd {

	@Id
	@Column(name="dceid")
	private String dceid;

	@Column(name="inserver")
	private Integer inserver;

	@Column(name="screenid")
	private Integer screenid;

	@Column(name="tif_reference")
	private String tifReference;

	@Column(name="scptcode")
	private String scptcode;

	@Column(name="expecteddate")
	private Date expecteddate;

	@Column(name="fromdate")
	private Date fromdate;

	@Column(name="todate")
	private String todate;

	@Column(name="paymentcpt")
	private String paymentcpt;

	@Column(name="billed")
	private Double billed;

	@Column(name="allowed")
	private Double allowed;

	@Column(name="deductible")
	private Double deductible;

	@Column(name="coins")
	private Double coins;

	@Column(name="ineligible")
	private Double ineligible;

	@Column(name="payments")
	private Double payments;

	@Column(name="scomments")
	private String scomments;

	@Column(name="date_of_posting")
	private Date dateOfPosting;

	@Column(name="ssoc_sec_no")
	private String ssocSecNo;

	@Column(name="payinsurance")
	private String payinsurance;

	@Column(name="sicdcode8")
	private String sicdcode8;

	@Column(name="insstatus")
	private Integer insstatus;

	@Column(name="riskwithhold")
	private Double riskwithhold;

	@Column(name="copay")
	private Double copay;

	@Column(name="ssbefore")
	private String ssbefore;

	@Column(name="ssafter")
	private String ssafter;

	@Column(name="bsbefore")
	private String bsbefore;

	@Column(name="bsafter")
	private String bsafter;

	@Column(name="reasonbefore")
	private String reasonbefore;

	@Column(name="reasonafter")
	private String reasonafter;

	@Column(name="totalduebefore")
	private Double totalduebefore;

	@Column(name="totaldueafter")
	private Double totaldueafter;

	@Column(name="bdoctor")
	private String bdoctor;

	@Column(name="deposit_date")
	private Date depositDate;

	@Column(name="paidserviceid")
	private String paidserviceid;

	@Column(name="cfirstname")
	private String cfirstname;

	@Column(name="cmiddlename")
	private String cmiddlename;

	@Column(name="clastname")
	private String clastname;

	@Column(name="cinsuredid")
	private String cinsuredid;

	@Column(name="cprioritypayer")
	private String cprioritypayer;

	@Column(name="grpno")
	private String grpno;

	@Column(name="pin")
	private String pin;

	@Column(name="upin")
	private String upin;

	@Column(name="originalscpt")
	private String originalscpt;

	@Column(name="chequenumber")
	private String chequenumber;

	@Column(name="chequeamt")
	private Double chequeamt;

	@Column(name="masterid")
	private Integer masterid;

	@Column(name="autoforwardedto")
	private String autoforwardedto;

	@Column(name="productiondate")
	private Date productiondate;

	@Column(name="insurancetype")
	private String insurancetype;

	@Column(name="patlastname")
	private String patlastname;

	@Column(name="patfirstname")
	private String patfirstname;

	@Column(name="adjscpt")
	private String adjscpt;

	@Column(name="isposted")
	private Integer isposted;

	@Column(name="placeddate")
	private Date placeddate;

	@Column(name="paymentcptcode")
	private Integer paymentcptcode;

	@Column(name="patid")
	private Integer patid;

	@Column(name="md1")
	private String md1;

	@Column(name="md2")
	private String md2;

	@Column(name="md3")
	private String md3;

	@Column(name="md4")
	private String md4;

	@Column(name="istopost")
	private Integer istopost;

	@Column(name="plb_serviceid")
	private Integer plbServiceid;

	@Column(name="discarded_status")
	private Integer discardedStatus;

	@Column(name="check_settled")
	private Boolean checkSettled;

	@Column(name="last_transaction_login_id")
	private Integer lastTransactionLoginId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="last_transaction_date")
	private Timestamp lastTransactionDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="eob_posted_date")
	private Timestamp eobPostedDate;

	@Column(name="eob_posted_login_id")
	private Integer eobPostedLoginId;

	@Column(name="serviceid")
	private Integer serviceid;

	@Column(name="isduplicateentry")
	private Boolean isduplicateentry;

	@Column(name="claimstatus")
	private Integer claimstatus;

	@Column(name="claimref")
	private String claimref;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "masterid", referencedColumnName = "id", insertable = false, updatable = false)
	private HutilMasterEob hutilMasterEobJoin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "screenid", referencedColumnName = "screen_id", insertable = false, updatable = false)
	private AnsiEobScreens ansiEobScreensJoin;
	
	@OneToOne
	@JoinColumn(name = "dceid", referencedColumnName = "dceid", insertable = false, updatable = false)
	@JsonBackReference
	private HutilEobSd eobSdJoin;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "masterid", referencedColumnName = "eobera_refund_writeoff_masterid", insertable = false, updatable = false)
	@JsonBackReference
	private EoberaRefundWriteoff eobRoundOffJoin;
	
	public String getDceid() {
		return dceid;
	}

	public void setDceid(String dceid) {
		this.dceid = dceid;
	}

	public Integer getInserver() {
		return inserver;
	}

	public void setInserver(Integer inserver) {
		this.inserver = inserver;
	}

	public Integer getScreenid() {
		return screenid;
	}

	public void setScreenid(Integer screenid) {
		this.screenid = screenid;
	}

	public String getTifReference() {
		return tifReference;
	}

	public void setTifReference(String tifReference) {
		this.tifReference = tifReference;
	}

	public String getScptcode() {
		return scptcode;
	}

	public void setScptcode(String scptcode) {
		this.scptcode = scptcode;
	}

	public Date getExpecteddate() {
		return expecteddate;
	}

	public void setExpecteddate(Date expecteddate) {
		this.expecteddate = expecteddate;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getPaymentcpt() {
		return paymentcpt;
	}

	public void setPaymentcpt(String paymentcpt) {
		this.paymentcpt = paymentcpt;
	}

	public Double getBilled() {
		return billed;
	}

	public void setBilled(Double billed) {
		this.billed = billed;
	}

	public Double getAllowed() {
		return allowed;
	}

	public void setAllowed(Double allowed) {
		this.allowed = allowed;
	}

	public Double getDeductible() {
		return deductible;
	}

	public void setDeductible(Double deductible) {
		this.deductible = deductible;
	}

	public Double getCoins() {
		return coins;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	public Double getIneligible() {
		return ineligible;
	}

	public void setIneligible(Double ineligible) {
		this.ineligible = ineligible;
	}

	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}

	public String getScomments() {
		return scomments;
	}

	public void setScomments(String scomments) {
		this.scomments = scomments;
	}

	public Date getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}

	public String getSsocSecNo() {
		return ssocSecNo;
	}

	public void setSsocSecNo(String ssocSecNo) {
		this.ssocSecNo = ssocSecNo;
	}

	public String getPayinsurance() {
		return payinsurance;
	}

	public void setPayinsurance(String payinsurance) {
		this.payinsurance = payinsurance;
	}

	public String getSicdcode8() {
		return sicdcode8;
	}

	public void setSicdcode8(String sicdcode8) {
		this.sicdcode8 = sicdcode8;
	}

	public Integer getInsstatus() {
		return insstatus;
	}

	public void setInsstatus(Integer insstatus) {
		this.insstatus = insstatus;
	}

	public Double getRiskwithhold() {
		return riskwithhold;
	}

	public void setRiskwithhold(Double riskwithhold) {
		this.riskwithhold = riskwithhold;
	}

	public Double getCopay() {
		return copay;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public String getSsbefore() {
		return ssbefore;
	}

	public void setSsbefore(String ssbefore) {
		this.ssbefore = ssbefore;
	}

	public String getSsafter() {
		return ssafter;
	}

	public void setSsafter(String ssafter) {
		this.ssafter = ssafter;
	}

	public String getBsbefore() {
		return bsbefore;
	}

	public void setBsbefore(String bsbefore) {
		this.bsbefore = bsbefore;
	}

	public String getBsafter() {
		return bsafter;
	}

	public void setBsafter(String bsafter) {
		this.bsafter = bsafter;
	}

	public String getReasonbefore() {
		return reasonbefore;
	}

	public void setReasonbefore(String reasonbefore) {
		this.reasonbefore = reasonbefore;
	}

	public String getReasonafter() {
		return reasonafter;
	}

	public void setReasonafter(String reasonafter) {
		this.reasonafter = reasonafter;
	}

	public Double getTotalduebefore() {
		return totalduebefore;
	}

	public void setTotalduebefore(Double totalduebefore) {
		this.totalduebefore = totalduebefore;
	}

	public Double getTotaldueafter() {
		return totaldueafter;
	}

	public void setTotaldueafter(Double totaldueafter) {
		this.totaldueafter = totaldueafter;
	}

	public String getBdoctor() {
		return bdoctor;
	}

	public void setBdoctor(String bdoctor) {
		this.bdoctor = bdoctor;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public String getPaidserviceid() {
		return paidserviceid;
	}

	public void setPaidserviceid(String paidserviceid) {
		this.paidserviceid = paidserviceid;
	}

	public String getCfirstname() {
		return cfirstname;
	}

	public void setCfirstname(String cfirstname) {
		this.cfirstname = cfirstname;
	}

	public String getCmiddlename() {
		return cmiddlename;
	}

	public void setCmiddlename(String cmiddlename) {
		this.cmiddlename = cmiddlename;
	}

	public String getClastname() {
		return clastname;
	}

	public void setClastname(String clastname) {
		this.clastname = clastname;
	}

	public String getCinsuredid() {
		return cinsuredid;
	}

	public void setCinsuredid(String cinsuredid) {
		this.cinsuredid = cinsuredid;
	}

	public String getCprioritypayer() {
		return cprioritypayer;
	}

	public void setCprioritypayer(String cprioritypayer) {
		this.cprioritypayer = cprioritypayer;
	}

	public String getGrpno() {
		return grpno;
	}

	public void setGrpno(String grpno) {
		this.grpno = grpno;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getUpin() {
		return upin;
	}

	public void setUpin(String upin) {
		this.upin = upin;
	}

	public String getOriginalscpt() {
		return originalscpt;
	}

	public void setOriginalscpt(String originalscpt) {
		this.originalscpt = originalscpt;
	}

	public String getChequenumber() {
		return chequenumber;
	}

	public void setChequenumber(String chequenumber) {
		this.chequenumber = chequenumber;
	}

	public Double getChequeamt() {
		return chequeamt;
	}

	public void setChequeamt(Double chequeamt) {
		this.chequeamt = chequeamt;
	}

	public Integer getMasterid() {
		return masterid;
	}

	public void setMasterid(Integer masterid) {
		this.masterid = masterid;
	}

	public String getAutoforwardedto() {
		return autoforwardedto;
	}

	public void setAutoforwardedto(String autoforwardedto) {
		this.autoforwardedto = autoforwardedto;
	}

	public Date getProductiondate() {
		return productiondate;
	}

	public void setProductiondate(Date productiondate) {
		this.productiondate = productiondate;
	}

	public String getInsurancetype() {
		return insurancetype;
	}

	public void setInsurancetype(String insurancetype) {
		this.insurancetype = insurancetype;
	}

	public String getPatlastname() {
		return patlastname;
	}

	public void setPatlastname(String patlastname) {
		this.patlastname = patlastname;
	}

	public String getPatfirstname() {
		return patfirstname;
	}

	public void setPatfirstname(String patfirstname) {
		this.patfirstname = patfirstname;
	}

	public String getAdjscpt() {
		return adjscpt;
	}

	public void setAdjscpt(String adjscpt) {
		this.adjscpt = adjscpt;
	}

	public Integer getIsposted() {
		return isposted;
	}

	public void setIsposted(Integer isposted) {
		this.isposted = isposted;
	}

	public Date getPlaceddate() {
		return placeddate;
	}

	public void setPlaceddate(Date placeddate) {
		this.placeddate = placeddate;
	}

	public Integer getPaymentcptcode() {
		return paymentcptcode;
	}

	public void setPaymentcptcode(Integer paymentcptcode) {
		this.paymentcptcode = paymentcptcode;
	}

	public Integer getPatid() {
		return patid;
	}

	public void setPatid(Integer patid) {
		this.patid = patid;
	}

	public String getMd1() {
		return md1;
	}

	public void setMd1(String md1) {
		this.md1 = md1;
	}

	public String getMd2() {
		return md2;
	}

	public void setMd2(String md2) {
		this.md2 = md2;
	}

	public String getMd3() {
		return md3;
	}

	public void setMd3(String md3) {
		this.md3 = md3;
	}

	public String getMd4() {
		return md4;
	}

	public void setMd4(String md4) {
		this.md4 = md4;
	}

	public Integer getIstopost() {
		return istopost;
	}

	public void setIstopost(Integer istopost) {
		this.istopost = istopost;
	}

	public Integer getPlbServiceid() {
		return plbServiceid;
	}

	public void setPlbServiceid(Integer plbServiceid) {
		this.plbServiceid = plbServiceid;
	}

	public Integer getDiscardedStatus() {
		return discardedStatus;
	}

	public void setDiscardedStatus(Integer discardedStatus) {
		this.discardedStatus = discardedStatus;
	}

	public Boolean getCheckSettled() {
		return checkSettled;
	}

	public void setCheckSettled(Boolean checkSettled) {
		this.checkSettled = checkSettled;
	}

	public Integer getLastTransactionLoginId() {
		return lastTransactionLoginId;
	}

	public void setLastTransactionLoginId(Integer lastTransactionLoginId) {
		this.lastTransactionLoginId = lastTransactionLoginId;
	}

	public Timestamp getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(Timestamp lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public Timestamp getEobPostedDate() {
		return eobPostedDate;
	}

	public void setEobPostedDate(Timestamp eobPostedDate) {
		this.eobPostedDate = eobPostedDate;
	}

	public Integer getEobPostedLoginId() {
		return eobPostedLoginId;
	}

	public void setEobPostedLoginId(Integer eobPostedLoginId) {
		this.eobPostedLoginId = eobPostedLoginId;
	}

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Boolean getIsduplicateentry() {
		return isduplicateentry;
	}

	public void setIsduplicateentry(Boolean isduplicateentry) {
		this.isduplicateentry = isduplicateentry;
	}

	public Integer getClaimstatus() {
		return claimstatus;
	}

	public void setClaimstatus(Integer claimstatus) {
		this.claimstatus = claimstatus;
	}

	public String getClaimref() {
		return claimref;
	}

	public void setClaimref(String claimref) {
		this.claimref = claimref;
	}

	public HutilMasterEob getHutilMasterEobJoin() {
		return hutilMasterEobJoin;
	}

	public void setHutilMasterEobJoin(HutilMasterEob hutilMasterEobJoin) {
		this.hutilMasterEobJoin = hutilMasterEobJoin;
	}

	public AnsiEobScreens getAnsiEobScreensJoin() {
		return ansiEobScreensJoin;
	}

	public void setAnsiEobScreensJoin(AnsiEobScreens ansiEobScreensJoin) {
		this.ansiEobScreensJoin = ansiEobScreensJoin;
	}

	public HutilEobSd getEobSdJoin() {
		return eobSdJoin;
	}

	public void setEobSdJoin(HutilEobSd eobSdJoin) {
		this.eobSdJoin = eobSdJoin;
	}

	public EoberaRefundWriteoff getEobRoundOffJoin() {
		return eobRoundOffJoin;
	}

	public void setEobRoundOffJoin(EoberaRefundWriteoff eobRoundOffJoin) {
		this.eobRoundOffJoin = eobRoundOffJoin;
	}
	
}