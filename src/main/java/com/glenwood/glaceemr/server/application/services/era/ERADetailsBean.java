package com.glenwood.glaceemr.server.application.services.era;

public class ERADetailsBean 
{
	String eraCheckNo;
	String eraInsName;
	String eraDownloadedDate;
	String eraDate;
	String eraFileName;
	Integer isDuplicate;
	Integer isParsed;
	String eraErrorStatus;
	Integer eraDetailsId;
	
	String bpr16;
	String prn02;
	Double bpr02;
	Double payments;
	String exp1;
	String dateOfPosting;
	Long decid1Count;
	Long decid2Count;
	String sourceFile;
	Long message1;
	Long message2;
	Double amount1;
	Double amount2;
	Double amount3;
	
	/**
	 * To get all unposted ERA.
	 * @param eraDetailsId
	 * @param eraCheckNo
	 * @param bpr16
	 * @param prn02
	 * @param bpr02
	 * @param exp1
	 * @param dateOfPosting
	 * @param decid1Count
	 * @param decid2Count
	 * @param sourceFile
	 * @param message1
	 * @param message2
	 * @param amount1
	 * @param amount2
	 * @param amount3
	 */
	public ERADetailsBean(Integer eraDetailsId, String eraCheckNo,
			String bpr16, String prn02, Double bpr02, Double payments,
			String dateOfPosting, Long decid1Count,
			Long decid2Count, Long message1, Long message2, String sourceFile, Double amount1,
			Double amount2, Double amount3) {
		super();
		this.eraDetailsId = eraDetailsId;
		this.eraCheckNo = eraCheckNo;
		this.bpr16 = bpr16;
		this.prn02 = prn02;
		this.bpr02 = bpr02;
		this.dateOfPosting = dateOfPosting;
		this.decid1Count = decid1Count;
		this.decid2Count = decid2Count;
		this.message1 = message1;
		this.message2 = message2;
		this.sourceFile = sourceFile;
		this.amount1 = amount1;
		this.amount2 = amount2;
		this.amount3 = amount3;
	}

	/**
	 * Constructor for getting error ERA files details.
	 * @param eraCheckNo
	 * @param eraInsName
	 * @param eraDownloadedDate
	 * @param eraDate
	 * @param eraFileName
	 * @param isDuplicate
	 * @param isParsed
	 * @param eraErrorStatus
	 * @param eraDetailsId
	 */
	public ERADetailsBean(String eraCheckNo, String eraInsName,
			String eraDownloadedDate, String eraDate, String eraFileName, Integer isDuplicate, Integer isParsed,
			String eraErrorStatus, Integer eraDetailsId) {
		super();
		this.eraCheckNo = eraCheckNo;
		this.eraInsName = eraInsName;
		this.eraDownloadedDate = eraDownloadedDate;
		this.eraDate = eraDate;
		this.eraFileName = eraFileName;
		this.isDuplicate = isDuplicate;
		this.isParsed = isParsed;
		this.eraErrorStatus = eraErrorStatus;
		this.eraDetailsId = eraDetailsId;
	}

	public String getEraCheckNo() {
		return eraCheckNo;
	}

	public void setEraCheckNo(String eraCheckNo) {
		this.eraCheckNo = eraCheckNo;
	}

	public String getEraInsName() {
		return eraInsName;
	}

	public void setEraInsName(String eraInsName) {
		this.eraInsName = eraInsName;
	}

	public String getEraDownloadedDate() {
		return eraDownloadedDate;
	}

	public void setEraDownloadedDate(String eraDownloadedDate) {
		this.eraDownloadedDate = eraDownloadedDate;
	}

	public String getEraDate() {
		return eraDate;
	}

	public void setEraDate(String eraDate) {
		this.eraDate = eraDate;
	}

	public String getEraFileName() {
		return eraFileName;
	}

	public void setEraFileName(String eraFileName) {
		this.eraFileName = eraFileName;
	}

	public Integer getIsDuplicate() {
		return isDuplicate;
	}

	public void setIsDuplicate(Integer isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	public Integer getIsParsed() {
		return isParsed;
	}

	public void setIsParsed(Integer isParsed) {
		this.isParsed = isParsed;
	}

	public String getEraErrorStatus() {
		return eraErrorStatus;
	}

	public void setEraErrorStatus(String eraErrorStatus) {
		this.eraErrorStatus = eraErrorStatus;
	}

	public Integer getEraDetailsId() {
		return eraDetailsId;
	}

	public void setEraDetailsId(Integer eraDetailsId) {
		this.eraDetailsId = eraDetailsId;
	}

	public String getBpr16() {
		return bpr16;
	}

	public void setBpr16(String bpr16) {
		this.bpr16 = bpr16;
	}

	public String getPrn02() {
		return prn02;
	}

	public void setPrn02(String prn02) {
		this.prn02 = prn02;
	}

	public Double getBpr02() {
		return bpr02;
	}

	public void setBpr02(Double bpr02) {
		this.bpr02 = bpr02;
	}

	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}

	public String getExp1() {
		return exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(String dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}

	public Long getDecid1Count() {
		return decid1Count;
	}

	public void setDecid1Count(Long decid1Count) {
		this.decid1Count = decid1Count;
	}

	public Long getDecid2Count() {
		return decid2Count;
	}

	public void setDecid2Count(Long decid2Count) {
		this.decid2Count = decid2Count;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public Long getMessage1() {
		return message1;
	}

	public void setMessage1(Long message1) {
		this.message1 = message1;
	}

	public Long getMessage2() {
		return message2;
	}

	public void setMessage2(Long message2) {
		this.message2 = message2;
	}

	public Double getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	public Double getAmount2() {
		return amount2;
	}

	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	public Double getAmount3() {
		return amount3;
	}

	public void setAmount3(Double amount3) {
		this.amount3 = amount3;
	}

}