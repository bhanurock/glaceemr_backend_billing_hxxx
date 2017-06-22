package com.glenwood.glaceemr.server.application.services.OpenProblems;

import java.sql.Timestamp;

public class OpenProblemsBean {

	Integer uniqueId;
	Integer practiceId;
	Integer problemId;
	Integer problemStatus;
	String ticketno;
	String problem;
	String solution;
	String reportedBy;
	String reportedTo;
	String patientName;
	String socSecNo;
	String loginUser;
	String reportedOn;
	String patientDob;
	String lastModifiedOn;
	Integer patientId;
	Integer callMood;
	String resolvedBy;
	String resolvedOn;
	String probSub;
	Integer denialRuleValidatorId;
	String filePath;
	String fileNames;
	String replyFilePaths;
	String replyFileNames;
	String forwardedTo;
	//String forwardedBy;
	Integer historyId;
	String historyLastModifiedOn;
	String historyLastLoggedIn;
	String problemHistoryMessage;
	Integer sequence;
	String pblmTypeDesc;
	Boolean reviewed;
	String reportForwardedTo;
	String reportForwardedBy;
	String reportForwardedOn;
	String fname;
	String lname;
	String mname;
	
	String userName;
	
	public OpenProblemsBean(String userName) {
		super();
		this.userName = userName;
	}
	
	
	public OpenProblemsBean(Integer uniqueId, Integer practiceId,
			Integer problemId, Integer problemStatus, String ticketno,
			String problem, String solution, String reportedBy,
			String reportedTo, String patientName, String socSecNo,
			String loginUser, String reportedOn, String patientDob,
			String lastModifiedOn, Integer patientId, Integer callMood,
			String resolvedBy, String resolvedOn, String probSub,
			Integer denialRuleValidatorId, String filePath, String fileNames,
			String replyFilePaths, String replyFileNames, String forwardedTo,
			Integer historyId, String historyLastModifiedOn,
			String historyLastLoggedIn, String problemHistoryMessage,
			Integer sequence, String pblmTypeDesc, Boolean reviewed,
			String reportForwardedTo, String reportForwardedBy,
			String reportForwardedOn,String fname,String lname,String mname) {
		super();
		this.uniqueId = uniqueId;
		this.practiceId = practiceId;
		this.problemId = problemId;
		this.problemStatus = problemStatus;
		this.ticketno = ticketno;
		this.problem = problem;
		this.solution = solution;
		this.reportedBy = reportedBy;
		this.reportedTo = reportedTo;
		this.patientName = patientName;
		this.socSecNo = socSecNo;
		this.loginUser = loginUser;
		this.reportedOn = reportedOn;
		this.patientDob = patientDob;
		this.lastModifiedOn = lastModifiedOn;
		this.patientId = patientId;
		this.callMood = callMood;
		this.resolvedBy = resolvedBy;
		this.resolvedOn = resolvedOn;
		this.probSub = probSub;
		this.denialRuleValidatorId = denialRuleValidatorId;
		this.filePath = filePath;
		this.fileNames = fileNames;
		this.replyFilePaths = replyFilePaths;
		this.replyFileNames = replyFileNames;
		this.forwardedTo = forwardedTo;
		this.historyId = historyId;
		this.historyLastModifiedOn = historyLastModifiedOn;
		this.historyLastLoggedIn = historyLastLoggedIn;
		this.problemHistoryMessage = problemHistoryMessage;
		this.sequence = sequence;
		this.pblmTypeDesc = pblmTypeDesc;
		this.reviewed = reviewed;
		this.reportForwardedTo = reportForwardedTo;
		this.reportForwardedBy = reportForwardedBy;
		this.reportForwardedOn = reportForwardedOn;
		this.fname=fname;
		this.lname=lname;
		this.mname=mname;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}
	public Integer getProblemId() {
		return problemId;
	}
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	public Integer getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(Integer problemStatus) {
		this.problemStatus = problemStatus;
	}
	public String getTicketno() {
		return ticketno;
	}
	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getReportedTo() {
		return reportedTo;
	}
	public void setReportedTo(String reportedTo) {
		this.reportedTo = reportedTo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getSocSecNo() {
		return socSecNo;
	}
	public void setSocSecNo(String socSecNo) {
		this.socSecNo = socSecNo;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getReportedOn() {
		return reportedOn;
	}
	public void setReportedOn(String reportedOn) {
		this.reportedOn = reportedOn;
	}
	public String getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}
	public String getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(String lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getCallMood() {
		return callMood;
	}
	public void setCallMood(Integer callMood) {
		this.callMood = callMood;
	}
	public String getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public String getResolvedOn() {
		return resolvedOn;
	}
	public void setResolvedOn(String resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	public String getProbSub() {
		return probSub;
	}
	public void setProbSub(String probSub) {
		this.probSub = probSub;
	}
	public Integer getDenialRuleValidatorId() {
		return denialRuleValidatorId;
	}
	public void setDenialRuleValidatorId(Integer denialRuleValidatorId) {
		this.denialRuleValidatorId = denialRuleValidatorId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public String getReplyFilePaths() {
		return replyFilePaths;
	}
	public void setReplyFilePaths(String replyFilePaths) {
		this.replyFilePaths = replyFilePaths;
	}
	public String getReplyFileNames() {
		return replyFileNames;
	}
	public void setReplyFileNames(String replyFileNames) {
		this.replyFileNames = replyFileNames;
	}
	public String getForwardedTo() {
		return forwardedTo;
	}
	public void setForwardedTo(String forwardedTo) {
		this.forwardedTo = forwardedTo;
	}
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public String getHistoryLastModifiedOn() {
		return historyLastModifiedOn;
	}
	public void setHistoryLastModifiedOn(String historyLastModifiedOn) {
		this.historyLastModifiedOn = historyLastModifiedOn;
	}
	public String getHistoryLastLoggedIn() {
		return historyLastLoggedIn;
	}
	public void setHistoryLastLoggedIn(String historyLastLoggedIn) {
		this.historyLastLoggedIn = historyLastLoggedIn;
	}
	public String getProblemHistoryMessage() {
		return problemHistoryMessage;
	}
	public void setProblemHistoryMessage(String problemHistoryMessage) {
		this.problemHistoryMessage = problemHistoryMessage;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getPblmTypeDesc() {
		return pblmTypeDesc;
	}
	public void setPblmTypeDesc(String pblmTypeDesc) {
		this.pblmTypeDesc = pblmTypeDesc;
	}
	public Boolean getReviewed() {
		return reviewed;
	}
	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}
	public String getReportForwardedTo() {
		return reportForwardedTo;
	}
	public void setReportForwardedTo(String reportForwardedTo) {
		this.reportForwardedTo = reportForwardedTo;
	}
	public String getReportForwardedBy() {
		return reportForwardedBy;
	}
	public void setReportForwardedBy(String reportForwardedBy) {
		this.reportForwardedBy = reportForwardedBy;
	}
	public String getReportForwardedOn() {
		return reportForwardedOn;
	}
	public void setReportForwardedOn(String reportForwardedOn) {
		this.reportForwardedOn = reportForwardedOn;
	}
	public Integer getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}
	/*public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}
	
}
