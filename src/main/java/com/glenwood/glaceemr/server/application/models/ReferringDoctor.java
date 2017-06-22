package com.glenwood.glaceemr.server.application.models;

/**
 * H076
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referring_doctor")
public class ReferringDoctor 
{
	@Id
	@Column(name="referring_doctor_uniqueid")
	private Integer referringDoctorUniqueid;
	
	@Column(name="referring_doctor_rdoctorid")
	private String referringDoctorRdoctorid;
	
	@Column(name="referring_doctor_lastname")
	private String referringDoctorLastname;
	
	@Column(name="referring_doctor_midinitial")
	private String referringDoctorMidinitial; 
	
	@Column(name="referring_doctor_firstname")
	private String referringDoctorFirstname; 
	
	@Column(name="referring_doctor_address")
	private String referringDoctorAddress; 
	
	@Column(name="referring_doctor_city")
	private String referringDoctorCity; 
	
	@Column(name="referring_doctor_state")
	private String referringDoctorState; 
	
	@Column(name="referring_doctor_zip")
	private String referringDoctorZip; 
	
	@Column(name="referring_doctor_phoneno")
	private String referringDoctorPhoneno; 
	
	@Column(name="referring_doctor_fax_number")
	private String referringDoctorFaxNumber; 
	
	@Column(name="referring_doctor_upin_no")
	private String referringDoctorUpinNo; 
	
	@Column(name="referring_doctor_medicare_pin")
	private String referringDoctorMedicarePin; 
	
	@Column(name="referring_doctor_npi")
	private String referringDoctorNpi; 
	
	@Column(name="referring_doctor_isactive")
	private Boolean referringDoctorIsactive; 
	
	@Column(name="referring_doctor_type")
	private Short referringDoctorType; 
	
	@Column(name="referring_doctor_hitcount")
	private Integer referringDoctorHitcount; 
	
	@Column(name="referring_doctor_iserphysician")
	private Boolean referringDoctorIserphysician; 
	
	@Column(name="referring_doctor_speciality_id")
	private Integer referringDoctorSpecialityId;
	
	@Column(name="referring_doctor_referringdoctor")
	private String referringDoctorReferringdoctor; 
	
	@Column(name="h555555")
	private Integer h555555; 
	
	@Column(name="referring_doctor_credential")
	private String referringDoctorCredential; 
	
	@Column(name="referring_doctor_mailid")
	private String referringDoctorMailid; 
	
	@Column(name="referring_doctor_prefix")
	private String referringDoctorPrefix; 
	
	@Column(name="taxonomy_code")
	private String taxonomyCode; 
	
	@Column(name="direct_email_address")
	private String directEmailAddress; 
	
	@Column(name="practice_name")
	private String practiceName; 
	
	@Column(name="facility_flag")
	private String facilityFlag; 
	
	@Column(name="ref_title")
	private String refTitle;

	public Integer getReferringDoctorUniqueid() {
		return referringDoctorUniqueid;
	}

	public void setReferringDoctorUniqueid(Integer referringDoctorUniqueid) {
		this.referringDoctorUniqueid = referringDoctorUniqueid;
	}

	public String getReferringDoctorRdoctorid() {
		return referringDoctorRdoctorid;
	}

	public void setReferringDoctorRdoctorid(String referringDoctorRdoctorid) {
		this.referringDoctorRdoctorid = referringDoctorRdoctorid;
	}

	public String getReferringDoctorLastname() {
		return referringDoctorLastname;
	}

	public void setReferringDoctorLastname(String referringDoctorLastname) {
		this.referringDoctorLastname = referringDoctorLastname;
	}

	public String getReferringDoctorMidinitial() {
		return referringDoctorMidinitial;
	}

	public void setReferringDoctorMidinitial(String referringDoctorMidinitial) {
		this.referringDoctorMidinitial = referringDoctorMidinitial;
	}

	public String getReferringDoctorFirstname() {
		return referringDoctorFirstname;
	}

	public void setReferringDoctorFirstname(String referringDoctorFirstname) {
		this.referringDoctorFirstname = referringDoctorFirstname;
	}

	public String getReferringDoctorAddress() {
		return referringDoctorAddress;
	}

	public void setReferringDoctorAddress(String referringDoctorAddress) {
		this.referringDoctorAddress = referringDoctorAddress;
	}

	public String getReferringDoctorCity() {
		return referringDoctorCity;
	}

	public void setReferringDoctorCity(String referringDoctorCity) {
		this.referringDoctorCity = referringDoctorCity;
	}

	public String getReferringDoctorState() {
		return referringDoctorState;
	}

	public void setReferringDoctorState(String referringDoctorState) {
		this.referringDoctorState = referringDoctorState;
	}

	public String getReferringDoctorZip() {
		return referringDoctorZip;
	}

	public void setReferringDoctorZip(String referringDoctorZip) {
		this.referringDoctorZip = referringDoctorZip;
	}

	public String getReferringDoctorPhoneno() {
		return referringDoctorPhoneno;
	}

	public void setReferringDoctorPhoneno(String referringDoctorPhoneno) {
		this.referringDoctorPhoneno = referringDoctorPhoneno;
	}

	public String getReferringDoctorFaxNumber() {
		return referringDoctorFaxNumber;
	}

	public void setReferringDoctorFaxNumber(String referringDoctorFaxNumber) {
		this.referringDoctorFaxNumber = referringDoctorFaxNumber;
	}

	public String getReferringDoctorUpinNo() {
		return referringDoctorUpinNo;
	}

	public void setReferringDoctorUpinNo(String referringDoctorUpinNo) {
		this.referringDoctorUpinNo = referringDoctorUpinNo;
	}

	public String getReferringDoctorMedicarePin() {
		return referringDoctorMedicarePin;
	}

	public void setReferringDoctorMedicarePin(String referringDoctorMedicarePin) {
		this.referringDoctorMedicarePin = referringDoctorMedicarePin;
	}

	public String getReferringDoctorNpi() {
		return referringDoctorNpi;
	}

	public void setReferringDoctorNpi(String referringDoctorNpi) {
		this.referringDoctorNpi = referringDoctorNpi;
	}

	public Boolean getReferringDoctorIsactive() {
		return referringDoctorIsactive;
	}

	public void setReferringDoctorIsactive(Boolean referringDoctorIsactive) {
		this.referringDoctorIsactive = referringDoctorIsactive;
	}

	public Short getReferringDoctorType() {
		return referringDoctorType;
	}

	public void setReferringDoctorType(Short referringDoctorType) {
		this.referringDoctorType = referringDoctorType;
	}

	public Integer getReferringDoctorHitcount() {
		return referringDoctorHitcount;
	}

	public void setReferringDoctorHitcount(Integer referringDoctorHitcount) {
		this.referringDoctorHitcount = referringDoctorHitcount;
	}

	public Boolean getReferringDoctorIserphysician() {
		return referringDoctorIserphysician;
	}

	public void setReferringDoctorIserphysician(Boolean referringDoctorIserphysician) {
		this.referringDoctorIserphysician = referringDoctorIserphysician;
	}

	public Integer getReferringDoctorSpecialityId() {
		return referringDoctorSpecialityId;
	}

	public void setReferringDoctorSpecialityId(Integer referringDoctorSpecialityId) {
		this.referringDoctorSpecialityId = referringDoctorSpecialityId;
	}

	public String getReferringDoctorReferringdoctor() {
		return referringDoctorReferringdoctor;
	}

	public void setReferringDoctorReferringdoctor(
			String referringDoctorReferringdoctor) {
		this.referringDoctorReferringdoctor = referringDoctorReferringdoctor;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getReferringDoctorCredential() {
		return referringDoctorCredential;
	}

	public void setReferringDoctorCredential(String referringDoctorCredential) {
		this.referringDoctorCredential = referringDoctorCredential;
	}

	public String getReferringDoctorMailid() {
		return referringDoctorMailid;
	}

	public void setReferringDoctorMailid(String referringDoctorMailid) {
		this.referringDoctorMailid = referringDoctorMailid;
	}

	public String getReferringDoctorPrefix() {
		return referringDoctorPrefix;
	}

	public void setReferringDoctorPrefix(String referringDoctorPrefix) {
		this.referringDoctorPrefix = referringDoctorPrefix;
	}

	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	public String getDirectEmailAddress() {
		return directEmailAddress;
	}

	public void setDirectEmailAddress(String directEmailAddress) {
		this.directEmailAddress = directEmailAddress;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getFacilityFlag() {
		return facilityFlag;
	}

	public void setFacilityFlag(String facilityFlag) {
		this.facilityFlag = facilityFlag;
	}

	public String getRefTitle() {
		return refTitle;
	}

	public void setRefTitle(String refTitle) {
		this.refTitle = refTitle;
	}

}