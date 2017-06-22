package com.glenwood.glaceemr.server.application.services.stmts;

public class StatementsBean {

	Integer patid;
	String acctno;
	String lname;
	String fname;
	String mname;
	String dob;
	String primary;
	String secondary;
	Double ptbalance;
	String homephone;
	String releasedatesort;
	String releasedate;
	String laststmtdate;
	String holdreason;
	String holddatesort;
	String holddate;
	String lastbillingstatus;
	String holdurlstring;
	String validationids;
	String stmtdate;
	Double escrow;
	String acctDesc;
	Integer validated;
	String priInsId;
	String secInsId;

	public StatementsBean(Integer patid, String acctno, String lname,
			String fname, String mname, String dob, String primary,
			String secondary, Double ptbalance, String homephone,
			String releasedatesort, String releasedate, String laststmtdate,
			String holdreason,String holddatesort, String holddate, String lastbillingstatus,
			String holdurlstring, String validationids, String stmtdate,
			Double escrow, String acctDesc, Integer validated,String priInsId,String secInsId) {
		super();
		this.patid = patid;
		this.acctno = acctno;
		this.lname = lname;
		this.fname = fname;
		this.mname = mname;
		this.dob = dob;
		this.primary = primary;
		this.secondary = secondary;
		this.ptbalance = ptbalance;
		this.homephone = homephone;
		this.releasedatesort = releasedatesort;
		this.releasedate = releasedate;
		this.laststmtdate = laststmtdate;
		this.holdreason = holdreason;
		this.holddatesort = holddatesort;
		this.holddate = holddate;
		this.lastbillingstatus = lastbillingstatus;
		this.holdurlstring = holdurlstring;
		this.validationids = validationids;
		this.stmtdate = stmtdate;
		this.escrow = escrow;
		this.acctDesc = acctDesc;
		this.validated = validated;
		this.priInsId=priInsId;
		this.secInsId=secInsId;
	}
	
	public Integer getPatid() {
		return patid;
	}
	public void setPatid(Integer patid) {
		this.patid = patid;
	}
	public String getAcctno() {
		return acctno;
	}
	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public String getSecondary() {
		return secondary;
	}
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	public Double getPtbalance() {
		return ptbalance;
	}
	public void setPtbalance(Double ptbalance) {
		this.ptbalance = ptbalance;
	}
	public String getHomephone() {
		return homephone;
	}
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
	public String getReleasedatesort() {
		return releasedatesort;
	}
	public void setReleasedatesort(String releasedatesort) {
		this.releasedatesort = releasedatesort;
	}
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	public String getLaststmtdate() {
		return laststmtdate;
	}
	public void setLaststmtdate(String laststmtdate) {
		this.laststmtdate = laststmtdate;
	}
	public String getHoldreason() {
		return holdreason;
	}
	public void setHoldreason(String holdreason) {
		this.holdreason = holdreason;
	}
	public String getHolddatesort() {
		return holddatesort;
	}
	public void setHolddatesort(String holddatesort) {
		this.holddatesort = holddatesort;
	}
	public String getHolddate() {
		return holddate;
	}
	public void setHolddate(String holddate) {
		this.holddate = holddate;
	}
	public String getLastbillingstatus() {
		return lastbillingstatus;
	}
	public void setLastbillingstatus(String lastbillingstatus) {
		this.lastbillingstatus = lastbillingstatus;
	}
	public String getHoldurlstring() {
		return holdurlstring;
	}
	public void setHoldurlstring(String holdurlstring) {
		this.holdurlstring = holdurlstring;
	}
	public String getValidationids() {
		return validationids;
	}
	public void setValidationids(String validationids) {
		this.validationids = validationids;
	}
	public String getStmtdate() {
		return stmtdate;
	}
	public void setStmtdate(String stmtdate) {
		this.stmtdate = stmtdate;
	}
	public String getAcctDesc() {
		return acctDesc;
	}
	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}
	public Integer getValidated() {
		return validated;
	}
	public void setValidated(Integer validated) {
		this.validated = validated;
	}
	public Double getEscrow() {
		return escrow;
	}
	public void setEscrow(Double escrow) {
		this.escrow = escrow;
	}
	public String getPriInsId() {
		return priInsId;
	}
	public void setPriInsId(String priInsId) {
		this.priInsId = priInsId;
	}
	public String getSecInsId() {
		return secInsId;
	}
	public void setSecInsId(String secInsId) {
		this.secInsId = secInsId;
	}
	
}