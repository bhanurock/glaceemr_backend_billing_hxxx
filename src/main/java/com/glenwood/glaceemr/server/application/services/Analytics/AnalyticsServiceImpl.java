package com.glenwood.glaceemr.server.application.services.Analytics;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.Bean.AutomationBillingDataBean;
import com.glenwood.glaceemr.server.application.models.AccountType;
import com.glenwood.glaceemr.server.application.models.AdActionhistory;
import com.glenwood.glaceemr.server.application.models.AdActionhistory_;
import com.glenwood.glaceemr.server.application.models.ArActionList;
import com.glenwood.glaceemr.server.application.models.ArActionList_;
import com.glenwood.glaceemr.server.application.models.ArProblemList;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails_;
import com.glenwood.glaceemr.server.application.models.AuthorizationReferral;
import com.glenwood.glaceemr.server.application.models.AuthorizationReferral_;
import com.glenwood.glaceemr.server.application.models.AutomationDetails;
import com.glenwood.glaceemr.server.application.models.AutomationDetails_;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.ClaimBatchAnsiInstXmlReport;
import com.glenwood.glaceemr.server.application.models.ClaimBatchAnsiInstXmlReport_;
import com.glenwood.glaceemr.server.application.models.ClaimBatchXmlReports;
import com.glenwood.glaceemr.server.application.models.ClaimBatchXmlReports_;
import com.glenwood.glaceemr.server.application.models.ClaimsTracker;
import com.glenwood.glaceemr.server.application.models.ClaimsTracker_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EnsAutomationDetails;
import com.glenwood.glaceemr.server.application.models.EnsAutomationDetails_;
import com.glenwood.glaceemr.server.application.models.EnsBillsDetails;
import com.glenwood.glaceemr.server.application.models.EnsBillsDetails_;
import com.glenwood.glaceemr.server.application.models.EraFileDetails;
import com.glenwood.glaceemr.server.application.models.EraFileDetails_;
import com.glenwood.glaceemr.server.application.models.H172;
import com.glenwood.glaceemr.server.application.models.H277;
import com.glenwood.glaceemr.server.application.models.H277_;
import com.glenwood.glaceemr.server.application.models.H278;
import com.glenwood.glaceemr.server.application.models.H279;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PosType;
import com.glenwood.glaceemr.server.application.models.ProblemReport;
import com.glenwood.glaceemr.server.application.models.ProblemReport_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ServiceBalances;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	EntityManager em;

	public JSONObject getAutomationBillingData(Integer module) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EnsAutomationDetails> root = cq.from(EnsAutomationDetails.class);
		Join<EnsAutomationDetails, AutomationDetails> ensAutomationJoin=root.join(EnsAutomationDetails_.automationDetails,JoinType.INNER);
		cq.select(
				builder.construct(AutomationBillingDataBean.class,
						builder.function("to_char", String.class, root.get(EnsAutomationDetails_.runtime),builder.literal("MM/dd/yyyy")),
						root.get(EnsAutomationDetails_.automationCount),
						ensAutomationJoin.get(AutomationDetails_.automationDetailsDesc),
						root.get(EnsAutomationDetails_.status)));
		Predicate condition = null;
		condition = builder.and(builder.equal(root.get(EnsAutomationDetails_.module),module));		
		cq.where(condition);
		cq.orderBy(builder.desc(root.get(EnsAutomationDetails_.runtime)));
		List<Object> obj=em.createQuery(cq).setMaxResults(1).getResultList();
		List<AutomationBillingDataBean> automationBillingDataBean = new ArrayList<AutomationBillingDataBean>();
		JSONObject jsonData=null;
		try
		{
			for(int i=0;i<obj.size();i++)
			{
				AutomationBillingDataBean ensDataBean=(AutomationBillingDataBean)obj.get(i);
				automationBillingDataBean.add(ensDataBean);
			}
			for(int i=0;i<automationBillingDataBean.size();i++)
			{
				jsonData = new JSONObject();
				jsonData.put("jobName", automationBillingDataBean.get(i).getAutomationDetailsDesc());
				jsonData.put("lastRunTime", automationBillingDataBean.get(i).getRunTime());
				jsonData.put("status", automationBillingDataBean.get(i).getStatus());
				jsonData.put("count", automationBillingDataBean.get(i).getAutomationCount());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonData;

	}

	@Override
	public String getAutomationBillingData() throws JSONException {
		JSONArray ja1 = new JSONArray();
		JSONObject jb1 = new JSONObject();
		JSONObject jb2 = new JSONObject();
		int temp=0;
		for(int i=1;i<11;i++){
			JSONObject tmp=getAutomationBillingData(i);
			if(tmp != null && (!tmp.toString().equalsIgnoreCase("null"))){
				ja1.put(temp,tmp);
				temp++;
			}
		}
		jb2.put("job", ja1);
		jb1.put("BillingData", jb2);
		return jb1.toString();
	}

	/**
	 * This Method shows abnormal release
	 * @return
	 */

	public Boolean abnormalRelease() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		cq.select(builder.count(root.get(ServiceDetail_.serviceDetailPatientid)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -6);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.notEqual(root.get(ServiceDetail_.serviceDetailReleaseStage),2);
		predList.add(p1);
		Predicate p2 = builder.lessThan(root.get(ServiceDetail_.serviceDetailPlaceddate),date);
		predList.add(p2);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalReleaseQuery = em.createQuery(cq).getResultList();
		String releaseValues = "";
		for(int i=0;i<abnormalReleaseQuery.size();i++)
		{
			releaseValues = abnormalReleaseQuery.get(i).toString();
		}

		if(Integer.parseInt(releaseValues)>0)
		{
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This Method shows abnormal charges
	 * @return
	 */
	public Boolean abnormalCharges() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		cq.select(builder.count(root.get(ServiceDetail_.serviceDetailPatientid)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -5);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.greaterThan(root.get(ServiceDetail_.serviceDetailPlaceddate),date);
		predList.add(p1);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalReleaseQuery = em.createQuery(cq).getResultList();
		String chargeValues = "";
		for(int i=0;i<abnormalReleaseQuery.size();i++)
		{
			chargeValues=abnormalReleaseQuery.get(i).toString();
		}
		if(Integer.parseInt(chargeValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method shows abnormal Onhold
	 * @return
	 */
	public Boolean abnormalOnHold() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		Join<ServiceDetail, AdActionhistory> serviceDetail = root.join(ServiceDetail_.adActionHistory,JoinType.LEFT);
		Join<ServiceDetail, NonServiceDetails> nonServiceDetail=root.join(ServiceDetail_.nonServiceDetails,JoinType.LEFT);
		cq.select(
				builder.function("count", Long.class, builder.function("distinct", String.class, root.get(ServiceDetail_.serviceDetailId))));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();

		now.add(Calendar.DATE, -7);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ServiceDetail_.serviceDetailSubmitStatus),"A");
		predList.add(p1);
		Predicate p2=builder.lessThanOrEqualTo(builder.function("Date", Date.class,builder.coalesce(builder.selectCase().when(builder.greaterThan(builder.coalesce(serviceDetail.get(AdActionhistory_.adAhActiontakendate),Timestamp.valueOf("1900-01-01 12:00:00")), 
				builder.coalesce(nonServiceDetail.get(NonServiceDetails_.nonServiceDetailPlacedDate),Timestamp.valueOf("1900-01-01 12:00:00"))),
				serviceDetail.get(AdActionhistory_.adAhActiontakendate))
				.otherwise(nonServiceDetail.get(NonServiceDetails_.nonServiceDetailPlacedDate)),root.get(ServiceDetail_.serviceDetailDop))),date);
		predList.add(p2);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalOnHoldQuery = em.createQuery(cq).getResultList();
		String onHoldValues = "";
		for(int i=0;i<abnormalOnHoldQuery.size();i++)
		{
			onHoldValues=abnormalOnHoldQuery.get(i).toString();
		}
		if(Integer.parseInt(onHoldValues)>0)
		{
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This Method Shows Abnormal Noresponse
	 */
	/*public List<String> abnormalNoResponse() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		//CriteriaQuery<AbnormalNoResponseBean> cq =builder.createQuery(AbnormalNoResponseBean.class);
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);

		Join<ServiceDetail, Cpt> cptJoin=root.join(ServiceDetail_.cpt,JoinType.INNER);
		Short tmp=1;
		Predicate p1 = builder.equal(cptJoin.get(Cpt_.cptCpttype),tmp);
		//Predicate p2=builder.not(root.get(ServiceDetail_.serviceDetailSubmitStatus).in('0','A','P','R','Q','S','T','G','H','B'));
		//	Predicate p3 = builder.not(cptJoin.get(Cpt_.cptCptcode).in("PTSXS","PTEXS","INSXS"));
		cptJoin.on(p1);
		Join<ServiceDetail, PatientRegistration> ServiceDetailJoin=root.join(ServiceDetail_.patientRegistration,JoinType.INNER);

		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -120);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		Predicate p4 = builder.lessThan(root.get(ServiceDetail_.serviceDetailDos),date);
		ServiceDetailJoin.on(p4);

		Join<ServiceDetail, EmployeeProfile> sDoctorJoin = root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
		Join<ServiceDetail, SubmitStatus> submitStatus=root.join(ServiceDetail_.submitStatus,JoinType.INNER);
		Join<ServiceDetail, AdActionhistory>adHistoryJoin=root.join(ServiceDetail_.adActionHistory,JoinType.LEFT);
		Predicate p5 = builder.equal(adHistoryJoin.get(AdActionhistory_.adAhModuleid),1);
		Predicate p6 = builder.equal(adHistoryJoin.get(AdActionhistory_.adAhIsrecent),true);
		adHistoryJoin.on(p5,p6);

		Join<ServiceDetail, PatientInsDetail> primaryIns=root.join(ServiceDetail_.patientInsDetail,JoinType.LEFT);
		Join<PatientInsDetail, InsCompAddr> comAddJoin= primaryIns.join(PatientInsDetail_.insCompAddr,JoinType.INNER);
		Join<InsCompAddr, InsCompany> insCompanyJoin=comAddJoin.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<ServiceDetail, Billinglookup> billingJoin = root.join(ServiceDetail_.bllok,JoinType.LEFT);
		Predicate p7=builder.equal(billingJoin.get(Billinglookup_.blookGroup),11);
		billingJoin.on(p7);

		cq.select(


				builder.selectCase()
				        .when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("0","A","P","R"),"primary").otherwise(builder.selectCase()
						.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("Q","S"),"secondary").otherwise(builder.selectCase()
						.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("G","H"),"teritary").otherwise(builder.selectCase()
						.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("NN","MM"),"Patient pending").otherwise(builder.literal("0"))))),


				builder.function("count", Long.class, builder.function("distinct", String.class, root.get(ServiceDetail_.serviceDetailId))));



		List<Predicate> predList = new ArrayList<>();
		Expression<Object> p8 = builder.selectCase()
		        .when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("0","A","P","R"),"primary").otherwise(builder.selectCase()
						.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("Q","S"),"secondary").otherwise(builder.selectCase()
								.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("G","H"),"teritary").otherwise(builder.selectCase()
								.when(builder.coalesce(root.get(ServiceDetail_.serviceDetailSubmitStatus),"").in("NN","MM"),"Patient pending").otherwise(builder.literal("0")))));
		Predicate p9 = builder.isNotNull(p8);
		predList.add(p9);

		Predicate p8=root.get(ServiceDetail_.serviceDetailSubmitStatus).in("0","A","P","R","Q","S","G","H","T","B","NN","MM");

		predList.add(p8);



		cq.where(predList.toArray(new Predicate[] {}));


		List<Object> abnormalResponseQuery = em.createQuery(cq).getResultList();
		List<String> responseValues = new ArrayList<String>();
		for(int i=0;i<abnormalResponseQuery.size();i++)
		{

			responseValues.add(abnormalResponseQuery.get(i).toString());
		}
		if(Integer.parseInt(responseValues.get(0))>1)
		{
			responseValues.clear();
			responseValues.add("Yes");
		}else {
			responseValues.clear();
			responseValues.add("No");
		}

		return responseValues;

	}*/

	public Boolean abnormalNoResponse() {
		// TODO Auto-generated method stub
		String qry="";
		qry="select cast(count(distinct service_detail_id) as varchar) as srvcnt  from service_detail s inner join cpt scpt on scpt.cpt_id = s.service_detail_cptid and scpt.cpt_cpttype=1 and service_detail_submit_status in ('0','A','P','R','Q','S','T','G','H','B') and scpt.cpt_cptcode not in ('PTSXS','PTEXS','INSXS') inner join patient_registration pt on pt.patient_registration_id = s.service_detail_patientid and cast(service_detail_dos as date) < cast(current_date as date) -120 left join emp_profile on emp_profile_empid=service_detail_sdoctorid inner join submit_status ss on ss.submit_status_code = s.service_detail_submit_status left join ad_actionhistory act on act.ad_ah_serviceid = s.service_detail_id and act.ad_ah_moduleid = 1 and ad_ah_isrecent is true left join patient_ins_detail id1 on id1.patient_ins_detail_id = s.service_detail_primaryins left join ins_comp_addr ica1 on ica1.ins_comp_addr_id = id1.patient_ins_detail_insaddressid left join ins_company ic1 on ica1.ins_comp_addr_inscompany_id = ic1.ins_company_id left join billinglookup bl on bl.blook_intid = cast(coalesce(service_detail_ar_status,'-1')  as integer) and bl.blook_group=11 where case when coalesce(service_detail_submit_status,'') in ('0','A','P','R') then 'Primary' else case when coalesce(service_detail_submit_status,'') in ('Q','S') then 'Secondary' else case when coalesce(service_detail_submit_status,'') in ('G','H') then 'Tertiary' else case when (coalesce(service_detail_submit_status,'') in ('T','B') and coalesce(patient_registration_billingstatus,'') in ('NN','MM')) then 'Patient pending' end end end end notnull";
		List<Object> resultList = em.createNativeQuery(qry).getResultList();
		String responseValues = "";

		for(int i=0;i<resultList.size();i++)
		{
			responseValues=resultList.get(i).toString();
		}
		if(Integer.parseInt(responseValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}


	/**
	 * This Method Is used for statementGeneration
	 * @return
	 */
	public Boolean statementGeneration() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimsTracker> root = cq.from(ClaimsTracker.class);
		Join<ClaimsTracker, EnsBillsDetails>claimstracker = root.join(ClaimsTracker_.ensBillsDetails,JoinType.INNER);
		cq.select(builder.count(root.get(ClaimsTracker_.claimstrackerBatchno)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ClaimsTracker_.claimstrackerTrackertype),2);
		predList.add(p1);
		Predicate p2 = builder.greaterThanOrEqualTo(root.get(ClaimsTracker_.claimstrackerGenerateddate),date);
		predList.add(p2);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> statementGeneration = em.createQuery(cq).getResultList();
		String generationValues = "";
		for(int i=0;i<statementGeneration.size();i++)
		{
			generationValues=statementGeneration.get(i).toString();
		}
		if(Integer.parseInt(generationValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method is Used for statement Upload
	 * @return
	 */
	public Boolean statementUploadToENS() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimsTracker> root = cq.from(ClaimsTracker.class);
		Join<ClaimsTracker, EnsBillsDetails>claimstracker = root.join(ClaimsTracker_.ensBillsDetails,JoinType.INNER);
		cq.select(builder.count(root.get(ClaimsTracker_.claimstrackerBatchno)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ClaimsTracker_.claimstrackerTrackertype),2);
		predList.add(p1);
		Predicate p2 = builder.notEqual(claimstracker.get(EnsBillsDetails_.uploaded),-1);
		predList.add(p2);
		Predicate p3 = builder.greaterThanOrEqualTo(root.get(ClaimsTracker_.claimstrackerGenerateddate),date);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> statementUploadToENS = em.createQuery(cq).getResultList();
		String uploadValues="";
		for(int i=0;i<statementUploadToENS.size();i++)
		{
			uploadValues=statementUploadToENS.get(i).toString();
		}
		if(Integer.parseInt(uploadValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method used for statement reponse
	 * @return
	 */
	public Boolean statementResponseFromENS() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimsTracker> root = cq.from(ClaimsTracker.class);
		Join<ClaimsTracker, EnsBillsDetails>claimstracker = root.join(ClaimsTracker_.ensBillsDetails,JoinType.INNER);
		cq.select(builder.count(root.get(ClaimsTracker_.claimstrackerBatchno)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ClaimsTracker_.claimstrackerTrackertype),2);
		predList.add(p1);
		Predicate p2 = builder.notEqual(claimstracker.get(EnsBillsDetails_.reportReceived),-1);
		predList.add(p2);
		Predicate p3 = builder.greaterThanOrEqualTo(root.get(ClaimsTracker_.claimstrackerGenerateddate),date);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> statementResponseFromENS = em.createQuery(cq).getResultList();
		String responseValues = "";
		for(int i=0;i<statementResponseFromENS.size();i++)
		{
			responseValues=statementResponseFromENS.get(i).toString();
		}
		if(Integer.parseInt(responseValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * THis Method shows abnormalERA
	 * @return
	 */
	public Boolean abnormalEra() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EraFileDetails> root = cq.from(EraFileDetails.class);
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		cq.select(builder.count(root.get(EraFileDetails_.eraFileDetailsId)));
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 =builder.greaterThanOrEqualTo(root.get(EraFileDetails_.eraFileDetailsDownloadedDate), date);
		predList.add(p1);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalEra = em.createQuery(cq).getResultList();
		String eraValues = "";
		for(int i=0;i<abnormalEra.size();i++)
		{
			eraValues=abnormalEra.get(i).toString();
		}
		if(Integer.parseInt(eraValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method shows abnormalDenials
	 * @return
	 */
	public Boolean abnormalDenials() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<AdActionhistory> root = cq.from(AdActionhistory.class);
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -7);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		cq.select(builder.count(root.get(AdActionhistory_.adAhActionid)));
		List<Predicate> predList  = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(AdActionhistory_.adAhModuleid),2);
		predList.add(p1);
		Predicate p2 = builder.greaterThanOrEqualTo(root.get(AdActionhistory_.adAhActiontakendate),date);
		predList.add(p2);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalDenials = em.createQuery(cq).getResultList();
		String denialValues = "";
		for(int i=0;i<abnormalDenials.size();i++)
		{
			denialValues=abnormalDenials.get(i).toString();
		}
		if(Integer.parseInt(denialValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}
	/**
	 * This method is used for claimgeneration	
	 * @return
	 */
	public Boolean claimGeneration() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimsTracker> root = cq.from(ClaimsTracker.class);
		cq.select(builder.count(root.get(ClaimsTracker_.claimstrackerBatchno)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ClaimsTracker_.claimstrackerTrackertype),1);
		predList.add(p1);
		Predicate p2 = builder.greaterThanOrEqualTo(root.get(ClaimsTracker_.claimstrackerGenerateddate),date);
		predList.add(p2);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> claimGeneration = em.createQuery(cq).getResultList();
		String generationValues = "";
		for(int i=0;i<claimGeneration.size();i++)
		{
			generationValues=claimGeneration.get(i).toString();
		}
		if(Integer.parseInt(generationValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This method is used for ClaimUpload
	 * @return
	 */
	public Boolean claimUpload() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimsTracker> root = cq.from(ClaimsTracker.class);
		cq.select(builder.count(root.get(ClaimsTracker_.claimstrackerBatchno)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(root.get(ClaimsTracker_.claimstrackerTrackertype),1);
		predList.add(p1);
		Predicate p2 = builder.greaterThanOrEqualTo(root.get(ClaimsTracker_.claimstrackerGenerateddate),date);
		predList.add(p2);
		Predicate p3 = builder.equal(root.get(ClaimsTracker_.claimstrackerFileUploadedPrinted),0);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> claimUpload = em.createQuery(cq).getResultList();
		String uploadValues = "";
		for(int i=0;i<claimUpload.size();i++)
		{
			uploadValues=claimUpload.get(i).toString();
		}
		if(Integer.parseInt(uploadValues)>0)
		{
			return false;
		}else {
			return true;

		}
	}

	/**
	 * This Method shows abnormalOp
	 * @return
	 */
	public Boolean abnormalOp() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ProblemReport> root = cq.from(ProblemReport.class);
		cq.select(
				builder.count(root.get(ProblemReport_.problemReportUniqueid)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -7);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1  = builder.lessThanOrEqualTo(root.get(ProblemReport_.problemReportReportedOn),date);
		predList.add(p1);
		Predicate p2 = root.get(ProblemReport_.problemReportProblemstatus).in(1,5);
		predList.add(p2);
		Predicate p3 = root.get(ProblemReport_.problemReportReportedTo).in("%GW%");
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalOp = em.createQuery(cq).getResultList();
		String opValues = "";
		for(int i=0;i<abnormalOp.size();i++)
		{
			opValues=abnormalOp.get(i).toString();
		}
		if(Integer.parseInt(opValues)>0)
		{
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This Method gives the level1Report
	 * @return
	 */
	public Boolean level1() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimBatchXmlReports> root = cq.from(ClaimBatchXmlReports.class);
		cq.select(
				builder.count(root.get(ClaimBatchXmlReports_.id)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1  = builder.greaterThanOrEqualTo(root.get(ClaimBatchXmlReports_.rptdate),date);
		predList.add(p1);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalLevel1 = em.createQuery(cq).getResultList();
		String levelValues = "";
		for(int i=0;i<abnormalLevel1.size();i++)
		{
			levelValues=abnormalLevel1.get(i).toString();
		}
		if(Integer.parseInt(levelValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method gives level2Report
	 * @return
	 */
	public Boolean level2() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClaimBatchAnsiInstXmlReport> root = cq.from(ClaimBatchAnsiInstXmlReport.class);
		cq.select(
				builder.count(root.get(ClaimBatchAnsiInstXmlReport_.id)));
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -3);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		List<Predicate> predList = new ArrayList<>();
		Predicate p1  = builder.greaterThanOrEqualTo(root.get(ClaimBatchAnsiInstXmlReport_.rptdate),date);
		predList.add(p1);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalLevel2 = em.createQuery(cq).getResultList();
		String levelValues="";
		for(int i=0;i<abnormalLevel2.size();i++)
		{
			levelValues=abnormalLevel2.get(i).toString();
		}
		if(Integer.parseInt(levelValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}
	/**
	 * This method shows abnormalTobeCalled
	 * @return
	 */
	public Boolean abnormalToBeCalled() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		Join<ServiceDetail, Billinglookup> serviceDetail = root.join(ServiceDetail_.bllok,JoinType.INNER);
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -6);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		cq.select(
				builder.count(root.get(ServiceDetail_.serviceDetailId)));
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(serviceDetail.get(Billinglookup_.blookGroup),11);
		predList.add(p1);
		Predicate p2 = serviceDetail.get(Billinglookup_.blookDesc).in("To Be Called");
		predList.add(p2);
		Predicate p3 = builder.greaterThanOrEqualTo(root.get(ServiceDetail_.serviceDetailDop),date);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalCalled = em.createQuery(cq).getResultList();
		String calledValues ="";
		for(int i=0;i<abnormalCalled.size();i++)
		{
			calledValues=abnormalCalled.get(i).toString();
		}
		if(Integer.parseInt(calledValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method shows abnormalTObeCompleted
	 * @return
	 */
	public Boolean abnormalToBeCompleted() {
		// TODO Auto-generated method stub

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		Join<ServiceDetail, Billinglookup> serviceDetail = root.join(ServiceDetail_.bllok,JoinType.INNER);
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -6);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		cq.select(
				builder.count(root.get(ServiceDetail_.serviceDetailId)));
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 = builder.equal(serviceDetail.get(Billinglookup_.blookGroup),11);
		predList.add(p1);
		Predicate p2 = serviceDetail.get(Billinglookup_.blookDesc).in("To Be Called - Completed");
		predList.add(p2);
		Predicate p3 = builder.greaterThanOrEqualTo(root.get(ServiceDetail_.serviceDetailDop),date);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalCompleted = em.createQuery(cq).getResultList();
		String completedValues ="";
		for(int i=0;i<abnormalCompleted.size();i++)
		{
			completedValues=abnormalCompleted.get(i).toString();
		}

		if(Integer.parseInt(completedValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}

	/**
	 * This Method is gives PrimaryInsurance
	 * @return
	 */
	public Boolean abnormalInsurance(Integer nonServiceDetailPaidBy) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<NonServiceDetails> root = cq.from(NonServiceDetails.class);
		java.util.Date today = new java.util.Date();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -6);
		Date date= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		cq.select(
				builder.count(root.get(NonServiceDetails_.nonServiceDetailId)));
		List<Predicate> predList = new ArrayList<>();
		Predicate p1 =builder.equal(root.get(NonServiceDetails_.nonServiceDetailPaidBy),nonServiceDetailPaidBy);
		predList.add(p1);
		Predicate p2 = root.get(NonServiceDetails_.nonServiceDetailEntryType).in(11,41);
		predList.add(p2);
		Predicate p3=builder.greaterThanOrEqualTo(root.get(NonServiceDetails_.nonServiceDetailDateOfPosting), date);
		predList.add(p3);
		cq.where(predList.toArray(new Predicate[] {}));
		List<Object> abnormalInsurance = em.createQuery(cq).getResultList();
		String insuranceValues = "";
		for(int i=0;i<abnormalInsurance.size();i++)
		{
			insuranceValues=abnormalInsurance.get(i).toString();
		}
		if(Integer.parseInt(insuranceValues)>0)
		{
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * This Method is gives AbnormalAr120
	 * @return
	 */
	public Boolean abnormalAr120() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		
		Join<ServiceDetail, PatientRegistration> patientReg=root.join(ServiceDetail_.patientRegistration,JoinType.INNER);
		Join<PatientRegistration, AccountType> accountType=patientReg.join(PatientRegistration_.accType,JoinType.LEFT);
		Join<ServiceDetail, PatientInsDetail> primaryIns=root.join(ServiceDetail_.patientInsDetail,JoinType.LEFT);
		Join<ServiceDetail, PatientInsDetail> secondIns=root.join(ServiceDetail_.secInsDetail,JoinType.LEFT);
		Join<PatientInsDetail, InsCompAddr> insCompAddr1=primaryIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<PatientInsDetail, InsCompAddr> insCompAddr2=secondIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> insCompany1=insCompAddr1.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> insCompany2=insCompAddr2.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<ServiceDetail, EmployeeProfile> billingDoctor=root.join(ServiceDetail_.bdoctors,JoinType.LEFT);
		Join<ServiceDetail, EmployeeProfile> servicegDoctor=root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
		Join<ServiceDetail, NonServiceDetails> serviceDetailJoin=root.join(ServiceDetail_.nonService,JoinType.LEFT);
		Join<ServiceDetail, Cpt> cpt=root.join(ServiceDetail_.cpt, JoinType.INNER);
		Join<NonServiceDetails, Cpt> paymentCpt=serviceDetailJoin.join(NonServiceDetails_.cpt, JoinType.LEFT);
		Join<NonServiceDetails, AdActionhistory> adAction=serviceDetailJoin.join(NonServiceDetails_.adActionHistory, JoinType.LEFT);
		Join<ServiceDetail, AssociateServiceDetails> associateDetails=root.join(ServiceDetail_.associateDetails,JoinType.LEFT);
		Join<AssociateServiceDetails, ArProblemList> arProblem=associateDetails.join(AssociateServiceDetails_.arProblem, JoinType.LEFT);
		Join<AdActionhistory, ArActionList>arActionList=adAction.join(AdActionhistory_.arActionList,JoinType.LEFT);
		Join<ServiceDetail, PosTable> posDetails=root.join(ServiceDetail_.posTable,JoinType.LEFT); 
		Join<PosTable, PosType> posType=posDetails.join(PosTable_.posType,JoinType.LEFT);
		Join<ServiceDetail, Billinglookup> billingLookup=root.join(ServiceDetail_.bllok,JoinType.LEFT);
		Join<ServiceDetail, H172> h1=root.join(ServiceDetail_.h172,JoinType.LEFT);
		Join<ServiceDetail, ServiceBalances> serviceBalance=root.join(ServiceDetail_.serviceBalances,JoinType.LEFT);
		Join<ServiceDetail,H277> h277join = root.join(ServiceDetail_.h277,JoinType.LEFT);
		Join<H277, H278> h278Join =h277join.join(H277_.h278,JoinType.LEFT);
		Join<H277, H279> h279Join= h277join.join(H277_.h279,JoinType.LEFT);
		Join<ServiceDetail, AuthorizationReferral> authorizationJoin=root.join(ServiceDetail_.authorizationReferral,JoinType.LEFT);
		Join<AuthorizationReferral, ReferringDoctor> referingDoctorJoin = authorizationJoin.join(AuthorizationReferral_.referringDoctorTable,JoinType.LEFT);
		
		
		Expression<Double> ex3 =builder.coalesce(root.get(ServiceDetail_.serviceDetailCharges),Double.parseDouble("0"));
		Expression<Double> ex4=builder.coalesce(root.get(ServiceDetail_.serviceDetailCopay), Double.parseDouble("0"));
		
		cq.distinct(true);
		
		cq.select(builder.sum(builder.sum(ex3,ex4)));
		
		final SimpleDateFormat df = new SimpleDateFormat( "MM/dd/yyyy" );
		java.util.Date date=new java.util.Date();
		Calendar now = Calendar.getInstance();
		Date currentDate= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		final java.util.Calendar cal1 = GregorianCalendar.getInstance();
		cal1.setTime(date);
		cal1.add( GregorianCalendar.DATE, -120 );
		
		List<Predicate> predList = new ArrayList<>();
		Predicate p1= builder.equal(cpt.get(Cpt_.cptCpttype),1);
		predList.add(p1);
		Predicate p2 = builder.not(cpt.get(Cpt_.cptCptcode).in("PTSXS","PTREF","INSXS","PT000"));
		predList.add(p2);
		Predicate p3 = builder.not(root.get(ServiceDetail_.serviceDetailSubmitStatus).in("Y","C","X","Z","A"));
		predList.add(p3);
		Predicate p4 =builder.notLike(builder.coalesce(/*builder.lower*/(arActionList.get(ArActionList_.arActionListActionName)),""),"%missing eob%");
		predList.add(p4);
		
		Expression<java.util.Date> exp = builder.coalesce(adAction.get(AdActionhistory_.adAhActiontakendate),root.get(ServiceDetail_.serviceDetailDos));
		Predicate p5 = builder.between(exp, convertToDate(df.format( cal1.getTime() )),currentDate);
		predList.add(p5);
		
		cq.where(predList.toArray(new Predicate[] {}));
		
		Object abnormalAr= em.createQuery(cq).getSingleResult();
		
		Double amount= (Double)abnormalAr;
		DecimalFormat test = new DecimalFormat("#0.00");
		
		if(Double.parseDouble(test.format(amount))>(double)15000)
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * This Method is gives AbnormalAr180
	 * @return
	 */
	
	public Boolean abnormalAr180() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ServiceDetail> root = cq.from(ServiceDetail.class);
		
		
		final SimpleDateFormat df = new SimpleDateFormat( "MM/dd/yyyy" );
		java.util.Date date=new java.util.Date();
		Calendar now = Calendar.getInstance();
		Date currentDate= new Date(now.getTime().getYear(), now.getTime().getMonth(), now.getTime().getDate());
		final java.util.Calendar cal1 = GregorianCalendar.getInstance();
		cal1.setTime(date);
		cal1.add( GregorianCalendar.DATE, -180 );
		
		Join<ServiceDetail, PatientRegistration> patientReg=root.join(ServiceDetail_.patientRegistration,JoinType.INNER);
		Join<PatientRegistration, AccountType> accountType=patientReg.join(PatientRegistration_.accType,JoinType.LEFT);
		Join<ServiceDetail, PatientInsDetail> primaryIns=root.join(ServiceDetail_.patientInsDetail,JoinType.LEFT);
		Join<ServiceDetail, PatientInsDetail> secondIns=root.join(ServiceDetail_.secInsDetail,JoinType.LEFT);
		Join<PatientInsDetail, InsCompAddr> insCompAddr1=primaryIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<PatientInsDetail, InsCompAddr> insCompAddr2=secondIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> insCompany1=insCompAddr1.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> insCompany2=insCompAddr2.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<ServiceDetail, EmployeeProfile> billingDoctor=root.join(ServiceDetail_.bdoctors,JoinType.LEFT);
		Join<ServiceDetail, EmployeeProfile> servicegDoctor=root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
		Join<ServiceDetail, NonServiceDetails> serviceDetailJoin=root.join(ServiceDetail_.nonService,JoinType.LEFT);
		Join<ServiceDetail, Cpt> cpt=root.join(ServiceDetail_.cpt, JoinType.INNER);
		Join<NonServiceDetails, Cpt> paymentCpt=serviceDetailJoin.join(NonServiceDetails_.cpt, JoinType.LEFT);
		Join<NonServiceDetails, AdActionhistory> adAction=serviceDetailJoin.join(NonServiceDetails_.adActionHistory, JoinType.LEFT);
		Join<ServiceDetail, AssociateServiceDetails> associateDetails=root.join(ServiceDetail_.associateDetails,JoinType.LEFT);
		Join<AssociateServiceDetails, ArProblemList> arProblem=associateDetails.join(AssociateServiceDetails_.arProblem, JoinType.LEFT);
		Join<AdActionhistory, ArActionList>arActionList=adAction.join(AdActionhistory_.arActionList,JoinType.LEFT);
		Join<ServiceDetail, PosTable> posDetails=root.join(ServiceDetail_.posTable,JoinType.LEFT); 
		Join<PosTable, PosType> posType=posDetails.join(PosTable_.posType,JoinType.LEFT);
		Join<ServiceDetail, Billinglookup> billingLookup=root.join(ServiceDetail_.bllok,JoinType.LEFT);
		Join<ServiceDetail, H172> h1=root.join(ServiceDetail_.h172,JoinType.LEFT);
		Join<ServiceDetail, ServiceBalances> serviceBalance=root.join(ServiceDetail_.serviceBalances,JoinType.LEFT);
		Join<ServiceDetail,H277> h277join = root.join(ServiceDetail_.h277,JoinType.LEFT);
		Join<H277, H278> h278Join =h277join.join(H277_.h278,JoinType.LEFT);
		Join<H277, H279> h279Join= h277join.join(H277_.h279,JoinType.LEFT);
		Join<ServiceDetail, AuthorizationReferral> authorizationJoin=root.join(ServiceDetail_.authorizationReferral,JoinType.LEFT);
		Join<AuthorizationReferral, ReferringDoctor> referingDoctorJoin = authorizationJoin.join(AuthorizationReferral_.referringDoctorTable,JoinType.LEFT);
		Expression<Double> ex3 =builder.coalesce(root.get(ServiceDetail_.serviceDetailCharges),Double.parseDouble("0"));
		Expression<Double> ex4=builder.coalesce(root.get(ServiceDetail_.serviceDetailCopay), Double.parseDouble("0"));
		cq.distinct(true);
		cq.select(builder.sum(builder.sum(ex3,ex4)));
		
		List<Predicate> predList = new ArrayList<>();
		Predicate p1= builder.equal(cpt.get(Cpt_.cptCpttype),1);
		predList.add(p1);
		Predicate p2 = builder.not(cpt.get(Cpt_.cptCptcode).in("PTSXS","PTREF","INSXS","PT000"));
		predList.add(p2);
		Predicate p3 = builder.not(root.get(ServiceDetail_.serviceDetailSubmitStatus).in("Y","C","X","Z","A"));
		predList.add(p3);
		Predicate p4 =builder.notLike(builder.coalesce(/*builder.lower*/(arActionList.get(ArActionList_.arActionListActionName)),""),"%missing eob%");
		predList.add(p4);
		Expression<java.util.Date> exp = builder.coalesce(adAction.get(AdActionhistory_.adAhActiontakendate),root.get(ServiceDetail_.serviceDetailDos));
		Predicate p5 = builder.between(exp, convertToDate(df.format( cal1.getTime() )),currentDate);
		predList.add(p5);
		cq.where(predList.toArray(new Predicate[] {}));
		
		Object abnormalReleaseQuery2 = em.createQuery(cq).getSingleResult();
		Double amount= (Double)abnormalReleaseQuery2;
		DecimalFormat test = new DecimalFormat("#0.00");
		if(Double.parseDouble(test.format(amount))>(double)0)
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * This Is Main Method Which Gives AbnormalDashboard Json
	 */
	@Override
	public JSONObject abnormalDashBoard(Integer nonServiceDetailPaidBy) throws JSONException  {
		// TODO Auto-generated method stub
		Boolean abnormalRelease = abnormalRelease();
		Boolean abnormalCharges = abnormalCharges();
		Boolean abnormalOnHold = abnormalOnHold();
		Boolean abnormalNoResponse = abnormalNoResponse();
		Boolean statementGeneration = statementGeneration();
		Boolean abnormalDenials = abnormalDenials();
		Boolean statementUploadToENS = statementUploadToENS();
		Boolean statementResponseFromENS = statementResponseFromENS();
		Boolean abnormalEra = abnormalEra();
		Boolean claimGeneration =claimGeneration();
		Boolean claimUpload = claimUpload();
		Boolean abnormalOp = abnormalOp();
		Boolean level2 = level1();
		Boolean level3 = level2();
		Boolean abnormalToBeCalled = abnormalToBeCalled();
		Boolean abnormalToBeCompleted = abnormalToBeCompleted();
		Boolean abnormalInsurance = abnormalInsurance(nonServiceDetailPaidBy);
		Boolean abnormalAr120 = abnormalAr120();
		Boolean abnormalAr180 = abnormalAr180();

		JSONObject mainObject = new JSONObject();
		JSONObject releaseObject = new JSONObject();
		JSONArray mainArray = new JSONArray();


		releaseObject.put("feedName", "Release");
		releaseObject.put("abnormal", abnormalRelease);
		mainArray.put(releaseObject);
		mainObject.put("billingFeedData", mainArray);

		JSONObject chargesObj=new JSONObject();
		chargesObj.put("feedName", "Charges");
		chargesObj.put("abnormal", abnormalCharges);
		mainArray.put(chargesObj);


		JSONObject responseObj=new JSONObject();
		responseObj.put("feedName", "Response");
		responseObj.put("abnormal", abnormalNoResponse);
		mainArray.put(responseObj);

		JSONObject onHoldObj=new JSONObject();
		onHoldObj.put("feedName", "Onhold");
		onHoldObj.put("abnormal", abnormalOnHold);
		mainArray.put(onHoldObj);

		JSONObject stmtGenObj=new JSONObject();
		stmtGenObj.put("feedName", "Statement Generation");
		stmtGenObj.put("abnormal", statementGeneration);
		mainArray.put(stmtGenObj);

		JSONObject denailsObj=new JSONObject();
		denailsObj.put("feedName", "Denial");
		denailsObj.put("abnormal", abnormalDenials);
		mainArray.put(denailsObj);

		JSONObject stmtUploadObj=new JSONObject();
		stmtUploadObj.put("feedName", "Statement Upload");
		stmtUploadObj.put("abnormal", statementUploadToENS);
		mainArray.put(stmtUploadObj);

		JSONObject stmtResObj=new JSONObject();
		stmtResObj.put("feedName", "Statement Response");
		stmtResObj.put("abnormal", statementResponseFromENS);
		mainArray.put(stmtResObj);


		JSONObject eraObj=new JSONObject();
		eraObj.put("feedName", "Electronic Remittance Advice");
		eraObj.put("abnormal", abnormalEra);
		mainArray.put(eraObj);


		JSONObject claimGenObj=new JSONObject();
		claimGenObj.put("feedName", "Claim Generation");
		claimGenObj.put("abnormal", claimGeneration);
		mainArray.put(claimGenObj);


		JSONObject claimUploadObj=new JSONObject();
		claimUploadObj.put("feedName", "Claim Upload");
		claimUploadObj.put("abnormal", claimUpload);
		mainArray.put(claimUploadObj);

		JSONObject opObj=new JSONObject();
		opObj.put("feedName", "Open Problem");
		opObj.put("abnormal", abnormalOp);
		mainArray.put(opObj);

		JSONObject level2Obj=new JSONObject();
		level2Obj.put("feedName", "Level2");
		level2Obj.put("abnormal", level2);
		mainArray.put(level2Obj);

		JSONObject level3Obj=new JSONObject();
		level3Obj.put("feedName", "Level3");
		level3Obj.put("abnormal", level3);
		mainArray.put(level3Obj);

		JSONObject tbcObj=new JSONObject();
		tbcObj.put("feedName", "To Be Called");
		tbcObj.put("abnormal", abnormalToBeCalled);
		mainArray.put(tbcObj);

		JSONObject tbccObj=new JSONObject();
		tbccObj.put("feedName", "To Be Called Completed");
		tbccObj.put("abnormal", abnormalToBeCompleted);
		mainArray.put(tbccObj);

		JSONObject insObj=new JSONObject();
		insObj.put("feedName", "Insurance Payment");
		insObj.put("abnormal", abnormalInsurance);
		mainArray.put(insObj);
		
		JSONObject ar120Obj=new JSONObject();
		ar120Obj.put("feedName", "Ar120");
		ar120Obj.put("abnormal", abnormalAr120);
		mainArray.put(ar120Obj);
		
		JSONObject ar180Obj=new JSONObject();
		ar180Obj.put("feedName", "Ar180");
		ar180Obj.put("abnormal", abnormalAr180);
		mainArray.put(ar180Obj);
		
		
		return mainObject;
	}
	
	public java.util.Date convertToDate(String date)
	{
		java.sql.Date sqlStartDate=null;
		try 
		{
			java.util.Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(date);
			String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sqlStartDate = null;
			java.util.Date d = df.parse(dateString2);
			sqlStartDate =  new java.sql.Date(d.getTime()); 
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlStartDate;
	}
	
}