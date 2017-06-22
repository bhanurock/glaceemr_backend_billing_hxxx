package com.glenwood.glaceemr.server.application.services.onHold;

public class OnHoldDetailBean {

	String patAccountno;
	String patLastName;
	String patFirstName;
	String patMiddleName;
	String patDob;
	String state;
	Integer serviceId;
	Integer ptregid;
	String cpt;
	Double Charges;
	Long denialId;
	String denialReasonCode;
	String denialDescription;
	String primaryPolicyNumber;
	String secondaryPolicyNumber;
	String primaryInsurance;
	String secondaryInsurance;
	String dx1;
	String dx2;
	String dx3;
	String dx4;
	String mod1;
	String mod2;
	String mod3;
	String mod4;
	String submitStatus;
	Double Copay;
	String denialDOP;
	Integer units;
	String primaryInsuranceAddress;
	String sreviceRef;
	String serviceDrName;
	String billingDoctor;
	String authNO;
	String dos;
	String lastActionType;
	String nextFollowupDate;
	String lastActionTakenDate;
	String actionDesc;
	String followUpDate;
	String accountType;
	String posTypeDesc;
	String problemId;
	String claimDate;
	Double expectedPayments;
	Integer pos;
	Double insuranceBal;
	String posName;
	Double primaryPaidAmount;
	Double secPaidAmount;
	Short  blookInitId;
	Integer arDenialType;
	String arCategoryId;

	public OnHoldDetailBean(String patAccountno, String patLastName,
			String patFirstName, String patMiddleName, String patDob,
			String state, Integer serviceId, Integer ptregid, String cpt,
			Double charges, Long denialId, String denialReasonCode,
			String denialDescription, String primaryPolicyNumber,
			String secondaryPolicyNumber, String primaryInsurance,
			String secondaryInsurance, String dx1, String dx2, String dx3,
			String dx4, String mod1, String mod2, String mod3, String mod4,
			String submitStatus, Double copay, String denialDOP, Integer units,
			String primaryInsuranceAddress, String sreviceRef,
			String serviceDrName, String billingDoctor, String authNO,
			String dos, String lastActionType, String nextFollowupDate,
			String lastActionTakenDate, String actionDesc, String followUpDate,
			String accountType, String posTypeDesc, String problemId,
			String claimDate, Double expectedPayments, Integer pos,
			Double insuranceBal, String posName, Double primaryPaidAmount,
			Double secPaidAmount,Short blookInitId,Integer arDenialType, String arCategoryId) {
		super();
		this.patAccountno = patAccountno;
		this.patLastName = patLastName;
		this.patFirstName = patFirstName;
		this.patMiddleName = patMiddleName;
		this.patDob = patDob;
		this.state = state;
		this.serviceId = serviceId;
		this.ptregid = ptregid;
		this.cpt = cpt;
		Charges = charges;
		this.denialId = denialId;
		this.denialReasonCode = denialReasonCode;
		this.denialDescription = denialDescription;
		this.primaryPolicyNumber = primaryPolicyNumber;
		this.secondaryPolicyNumber = secondaryPolicyNumber;
		this.primaryInsurance = primaryInsurance;
		this.secondaryInsurance = secondaryInsurance;
		this.dx1 = dx1;
		this.dx2 = dx2;
		this.dx3 = dx3;
		this.dx4 = dx4;
		this.mod1 = mod1;
		this.mod2 = mod2;
		this.mod3 = mod3;
		this.mod4 = mod4;
		this.submitStatus = submitStatus;
		Copay = copay;
		this.denialDOP = denialDOP;
		this.units = units;
		this.primaryInsuranceAddress = primaryInsuranceAddress;
		this.sreviceRef = sreviceRef;
		this.serviceDrName = serviceDrName;
		this.billingDoctor = billingDoctor;
		this.authNO = authNO;
		this.dos = dos;
		this.lastActionType = lastActionType;
		this.nextFollowupDate = nextFollowupDate;
		this.lastActionTakenDate = lastActionTakenDate;
		this.actionDesc = actionDesc;
		this.followUpDate = followUpDate;
		this.accountType = accountType;
		this.posTypeDesc = posTypeDesc;
		this.problemId = problemId;
		this.claimDate = claimDate;
		this.expectedPayments = expectedPayments;
		this.pos = pos;
		this.insuranceBal = insuranceBal;
		this.posName = posName;
		this.primaryPaidAmount = primaryPaidAmount;
		this.secPaidAmount = secPaidAmount;
		this.blookInitId=blookInitId;
		this.arDenialType=arDenialType;
		this.arCategoryId = arCategoryId;
	}
	public String getPatAccountno() {
		return patAccountno;
	}
	public void setPatAccountno(String patAccountno) {
		this.patAccountno = patAccountno;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatDob() {
		return patDob;
	}
	public void setPatDob(String patDob) {
		this.patDob = patDob;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getPtregid() {
		return ptregid;
	}
	public void setPtregid(Integer ptregid) {
		this.ptregid = ptregid;
	}
	public String getCpt() {
		return cpt;
	}
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	public Double getCharges() {
		return Charges;
	}
	public void setCharges(Double charges) {
		Charges = charges;
	}
	public Long getDenialId() {
		return denialId;
	}
	public void setDenialId(Long denialId) {
		this.denialId = denialId;
	}
	public String getDenialReasonCode() {
		return denialReasonCode;
	}
	public void setDenialReasonCode(String denialReasonCode) {
		this.denialReasonCode = denialReasonCode;
	}
	public String getDenialDescription() {
		return denialDescription;
	}
	public void setDenialDescription(String denialDescription) {
		this.denialDescription = denialDescription;
	}
	public String getPrimaryPolicyNumber() {
		return primaryPolicyNumber;
	}
	public void setPrimaryPolicyNumber(String primaryPolicyNumber) {
		this.primaryPolicyNumber = primaryPolicyNumber;
	}
	public String getSecondaryPolicyNumber() {
		return secondaryPolicyNumber;
	}
	public void setSecondaryPolicyNumber(String secondaryPolicyNumber) {
		this.secondaryPolicyNumber = secondaryPolicyNumber;
	}
	public String getPrimaryInsurance() {
		return primaryInsurance;
	}
	public void setPrimaryInsurance(String primaryInsurance) {
		this.primaryInsurance = primaryInsurance;
	}
	public String getSecondaryInsurance() {
		return secondaryInsurance;
	}
	public void setSecondaryInsurance(String secondaryInsurance) {
		this.secondaryInsurance = secondaryInsurance;
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
	public String getMod1() {
		return mod1;
	}
	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}
	public String getMod2() {
		return mod2;
	}
	public void setMod2(String mod2) {
		this.mod2 = mod2;
	}
	public String getMod3() {
		return mod3;
	}
	public void setMod3(String mod3) {
		this.mod3 = mod3;
	}
	public String getMod4() {
		return mod4;
	}
	public void setMod4(String mod4) {
		this.mod4 = mod4;
	}
	public String getSubmitStatus() {
		return submitStatus;
	}
	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}
	public Double getCopay() {
		return Copay;
	}
	public void setCopay(Double copay) {
		Copay = copay;
	}
	public String getDenialDOP() {
		return denialDOP;
	}
	public void setDenialDOP(String denialDOP) {
		this.denialDOP = denialDOP;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public String getPrimaryInsuranceAddress() {
		return primaryInsuranceAddress;
	}
	public void setPrimaryInsuranceAddress(String primaryInsuranceAddress) {
		this.primaryInsuranceAddress = primaryInsuranceAddress;
	}
	public String getSreviceRef() {
		return sreviceRef;
	}
	public void setSreviceRef(String sreviceRef) {
		this.sreviceRef = sreviceRef;
	}
	public String getServiceDrName() {
		return serviceDrName;
	}
	public void setServiceDrName(String serviceDrName) {
		this.serviceDrName = serviceDrName;
	}
	public String getBillingDoctor() {
		return billingDoctor;
	}
	public void setBillingDoctor(String billingDoctor) {
		this.billingDoctor = billingDoctor;
	}
	public String getAuthNO() {
		return authNO;
	}
	public void setAuthNO(String authNO) {
		this.authNO = authNO;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getLastActionType() {
		return lastActionType;
	}
	public void setLastActionType(String lastActionType) {
		this.lastActionType = lastActionType;
	}
	public String getNextFollowupDate() {
		return nextFollowupDate;
	}
	public void setNextFollowupDate(String nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}
	public String getLastActionTakenDate() {
		return lastActionTakenDate;
	}
	public void setLastActionTakenDate(String lastActionTakenDate) {
		this.lastActionTakenDate = lastActionTakenDate;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getPosTypeDesc() {
		return posTypeDesc;
	}
	public void setPosTypeDesc(String posTypeDesc) {
		this.posTypeDesc = posTypeDesc;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	public Double getExpectedPayments() {
		return expectedPayments;
	}
	public void setExpectedPayments(Double expectedPayments) {
		this.expectedPayments = expectedPayments;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Double getInsuranceBal() {
		return insuranceBal;
	}
	public void setInsuranceBal(Double insuranceBal) {
		this.insuranceBal = insuranceBal;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public Double getPrimaryPaidAmount() {
		return primaryPaidAmount;
	}
	public void setPrimaryPaidAmount(Double primaryPaidAmount) {
		this.primaryPaidAmount = primaryPaidAmount;
	}
	public Double getSecPaidAmount() {
		return secPaidAmount;
	}
	public void setSecPaidAmount(Double secPaidAmount) {
		this.secPaidAmount = secPaidAmount;
	}
	public Short getBlookInitId() {
		return blookInitId;
	}
	public void setBlookInitId(Short blookInitId) {
		this.blookInitId = blookInitId;
	}
	public Integer getArDenialType() {
		return arDenialType;
	}
	public void setArDenialType(Integer arDenialType) {
		this.arDenialType = arDenialType;
	}
	public String getArCategoryId() {
		return arCategoryId;
	}
	public void setArCategoryId(String arCategoryId) {
		this.arCategoryId = arCategoryId;
	}

}