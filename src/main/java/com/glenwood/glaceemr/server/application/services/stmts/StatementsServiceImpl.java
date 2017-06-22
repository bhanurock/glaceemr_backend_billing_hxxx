package com.glenwood.glaceemr.server.application.services.stmts;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AccountType;
import com.glenwood.glaceemr.server.application.models.AccountType_;
import com.glenwood.glaceemr.server.application.models.BillStatus;
import com.glenwood.glaceemr.server.application.models.BillStatusReason;
import com.glenwood.glaceemr.server.application.models.BillStatusReason_;
import com.glenwood.glaceemr.server.application.models.BillStatus_;
import com.glenwood.glaceemr.server.application.models.BillingReason;
import com.glenwood.glaceemr.server.application.models.BillingReason_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.GeneratedBillsHistoryDetails;
import com.glenwood.glaceemr.server.application.models.GeneratedBillsHistoryDetails_;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.InsCompany_;
import com.glenwood.glaceemr.server.application.models.PatientBillCycle;
import com.glenwood.glaceemr.server.application.models.PatientBillCycle_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PaymentPlan;
import com.glenwood.glaceemr.server.application.models.PaymentPlanService;
import com.glenwood.glaceemr.server.application.models.PaymentPlanService_;
import com.glenwood.glaceemr.server.application.models.PaymentPlan_;
import com.glenwood.glaceemr.server.application.models.ReleaseStatementLog;
import com.glenwood.glaceemr.server.application.models.ReleaseStatementLog_;
import com.glenwood.glaceemr.server.application.models.ServiceBalances;
import com.glenwood.glaceemr.server.application.models.ServiceBalances_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;

@Service
public class StatementsServiceImpl implements StatementsServices{

	@Autowired
	EntityManager em;
	
	@Override
	public List<StatementsBean> getStatementData(String fromDate, String toDate) {
		
	CriteriaBuilder builder = em.getCriteriaBuilder();
	CriteriaQuery<Object> createquery = builder.createQuery();
	Root<PaymentPlanService> rt = createquery.from(PaymentPlanService.class);
	Join<PaymentPlanService, PaymentPlan> paymt=rt.join(PaymentPlanService_.paymentPlan,JoinType.INNER);
	createquery.distinct(true);
	createquery.select(rt.get(PaymentPlanService_.paymentPlanServiceId));
	createquery.where(builder.equal(paymt.get(PaymentPlan_.paymentPlanIsactive),true));
	List<Object> tempBeanList = em.createQuery(createquery).getResultList();
	System.out.println("ressult of payment"+tempBeanList);
	
	 builder = em.getCriteriaBuilder();
	 CriteriaQuery<Object> cq = builder.createQuery();
	
		
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		Join<PatientRegistration, PatientInsDetail> patIns=root.join(PatientRegistration_.patInsDetails,JoinType.INNER);
		patIns.on(builder.and(builder.equal(patIns.get(PatientInsDetail_.patientInsDetailInstype),1),builder.equal(patIns.get(PatientInsDetail_.patientInsDetailIsactive), true)));
		Join<PatientInsDetail, InsCompAddr> patInsAddr=patIns.join(PatientInsDetail_.insCompAddr,JoinType.INNER);
		Join<InsCompAddr, InsCompany> patInsCmp=patInsAddr.join(InsCompAddr_.insCompany,JoinType.INNER);
		Join<PatientRegistration, PatientInsDetail> patInsDtl=root.join(PatientRegistration_.patInsDetails,JoinType.LEFT);
		patInsDtl.on(builder.and(builder.equal(patInsDtl.get(PatientInsDetail_.patientInsDetailInstype), 2),builder.equal(patInsDtl.get(PatientInsDetail_.patientInsDetailIsactive), true)));
		Join<PatientInsDetail, InsCompAddr> insPat=patInsDtl.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> insComPat=insPat.join(InsCompAddr_.insCompany,JoinType.LEFT);
		Join<PatientRegistration,ServiceDetail> patSer=root.join(PatientRegistration_.serviceDetail,JoinType.INNER);
		patSer.on(builder.and(builder.equal(patSer.get(ServiceDetail_.serviceDetailSubmitStatus), "T"),builder.equal(patSer.get(ServiceDetail_.serviceDetailDontbill), "1"),builder.not(patSer.get(ServiceDetail_.serviceDetailId).in(tempBeanList))));
		Join<ServiceDetail,ServiceBalances> srBla=patSer.join(ServiceDetail_.serviceBalances,JoinType.INNER);
		Join<ServiceDetail, Cpt> serCpt=patSer.join(ServiceDetail_.cpt,JoinType.INNER);
		serCpt.on(builder.equal(serCpt.get(Cpt_.cptIsactive),true ));
		Join<ServiceDetail,BillingReason> serBillingReason=patSer.join(ServiceDetail_.billingReason,JoinType.INNER);
		serBillingReason.on(builder.equal(serBillingReason.get(BillingReason_.billingReasonIsactive), true));
		Join<PatientRegistration, BillStatus> patBillStatus = root.join(PatientRegistration_.billStatus,JoinType.INNER);
		Join<PatientRegistration, PatientBillCycle> patbillcyl=root.join(PatientRegistration_.patientBillCycle,JoinType.LEFT);
		Join<PatientRegistration, AccountType> patientacct=root.join(PatientRegistration_.accType,JoinType.LEFT);
		Join<PatientRegistration, GeneratedBillsHistoryDetails> patBillsHis=root.join(PatientRegistration_.generatedBillsHistoryDetails,JoinType.LEFT);
		Join<PatientRegistration,ReleaseStatementLog> patRel=root.join(PatientRegistration_.releaseStatementLog,JoinType.LEFT);
		patRel.on(builder.equal(patRel.get(ReleaseStatementLog_.releaseStatementLogType), 0));
		Join<PatientRegistration,BillStatusReason> patBillStatusReason=root.join(PatientRegistration_.billStatusReason,JoinType.LEFT);
		patBillStatusReason.on(builder.equal(patBillStatusReason.get(BillStatusReason_.billstatusReasonActivestatus), "A"));
		
		
		cq.distinct(true);
		cq.select(
				builder.construct(StatementsBean.class,
						root.get(PatientRegistration_.patientRegistrationId),
						root.get(PatientRegistration_.patientRegistrationAccountno),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationLastName),""),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationFirstName),""),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationMidInitial),""),
						builder.function("to_char", String.class,root.get(PatientRegistration_.patientRegistrationDob),builder.literal("mm/dd/yyyy")),
						builder.coalesce(patInsCmp.get(InsCompany_.insCompanyName),""),
						builder.coalesce(insComPat.get(InsCompany_.insCompanyName),""),
						builder.coalesce(builder.sum(builder.coalesce(srBla.get(ServiceBalances_.patientBalance),0)),0),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationPhoneNo),""),
						builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(patRel.get(ReleaseStatementLog_.releaseStatementLogDate),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
						builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(patRel.get(ReleaseStatementLog_.releaseStatementLogDate),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
						builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(patBillsHis.get(GeneratedBillsHistoryDetails_.generatedBillsHistoryDetailsCurrentGenerateddate),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
						builder.coalesce(patBillStatusReason.get(BillStatusReason_.billstatusReasonNotes),""),
						builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(patBillStatusReason.get(BillStatusReason_.billstatusReasonDate),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
						builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(patBillStatusReason.get(BillStatusReason_.billstatusReasonDate),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
						patBillStatusReason.get(BillStatusReason_.billstatusReasonOldstatus),
						builder.coalesce(patBillStatusReason.get(BillStatusReason_.billstatusReasonUrlstring),""),
						builder.coalesce(patBillStatusReason.get(BillStatusReason_.billstatusReasonValidationids),"1"),
						builder.coalesce(builder.function("to_char",String.class,patBillsHis.get(GeneratedBillsHistoryDetails_.generatedBillsHistoryDetailsCurrentGenerateddate),builder.literal("mm/dd/yyyy") ),""),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationCredits),0.00),
						builder.coalesce(patientacct.get(AccountType_.accTypeDesc),"1"),
						builder.coalesce(root.get(PatientRegistration_.patientRegistrationUnknown5),2),
						patIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
						patInsDtl.get(PatientInsDetail_.patientInsDetailPatientinsuranceid)
						
						));
		cq.having(
				builder.greaterThanOrEqualTo(builder.toLong(builder.function("date_diff", Double.class,builder.literal(4),builder.coalesce(root.get(PatientRegistration_.patientRegistrationDatelastbilled),convertToDate("1900-01-01")),builder.literal(new java.util.Date()))),builder.toLong(builder.coalesce(patbillcyl.get(PatientBillCycle_.patientBillcycleFrequency),patBillStatus.get(BillStatus_.billStatusBillintervel)))),
				builder.equal(root.get(PatientRegistration_.patientRegistrationActive), true),
				builder.not(root.get(PatientRegistration_.patientRegistrationId).in(-1)),
				builder.equal(patSer.get(ServiceDetail_.serviceDetailDontbill), 1),
				builder.greaterThanOrEqualTo(builder.toInteger(builder.sum(builder.coalesce(srBla.get(ServiceBalances_.patientBalance),0))),10),
				builder.coalesce(builder.trim(builder.upper(root.get(PatientRegistration_.patientRegistrationBillingstatus))),"").in("HA"),
				builder.between(patBillsHis.get(GeneratedBillsHistoryDetails_.generatedBillsHistoryDetailsCurrentGenerateddate),convertToDate(fromDate),convertToDate(toDate))
				);
		cq.groupBy(
				root.get(PatientRegistration_.patientRegistrationId),
				root.get(PatientRegistration_.patientRegistrationAccountno),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				root.get(PatientRegistration_.patientRegistrationMidInitial),
				root.get(PatientRegistration_.patientRegistrationDob),
				patInsCmp.get(InsCompany_.insCompanyName),
				insComPat.get(InsCompany_.insCompanyName),
				root.get(PatientRegistration_.patientRegistrationPhoneNo),
				patRel.get(ReleaseStatementLog_.releaseStatementLogDate),
				patRel.get(ReleaseStatementLog_.releaseStatementLogDate),
				patBillsHis.get(GeneratedBillsHistoryDetails_.generatedBillsHistoryDetailsCurrentGenerateddate),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonNotes),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonDate),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonDate),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonOldstatus),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonUrlstring),
				patBillStatusReason.get(BillStatusReason_.billstatusReasonValidationids),
				patBillsHis.get(GeneratedBillsHistoryDetails_.generatedBillsHistoryDetailsCurrentGenerateddate),
				root.get(PatientRegistration_.patientRegistrationCredits),
				patientacct.get(AccountType_.accTypeDesc),
				root.get(PatientRegistration_.patientRegistrationUnknown5),
				root.get(PatientRegistration_.patientRegistrationDatelastbilled),
				patbillcyl.get(PatientBillCycle_.patientBillcycleFrequency),
				patBillStatus.get(BillStatus_.billStatusBillintervel),
				root.get(PatientRegistration_.patientRegistrationActive),
				patSer.get(ServiceDetail_.serviceDetailDontbill),
				root.get(PatientRegistration_.patientRegistrationBillingstatus),
				patIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
				patInsDtl.get(PatientInsDetail_.patientInsDetailPatientinsuranceid)
				);
		cq.orderBy(builder.desc(root.get(PatientRegistration_.patientRegistrationId)),builder.desc(builder.coalesce(builder.sum(builder.coalesce(srBla.get(ServiceBalances_.patientBalance),0)),0)));
		
		List<Object> results=em.createQuery(cq).getResultList();
		List<StatementsBean> resultList = new ArrayList<StatementsBean>();
		for(int i=0;i<results.size();i++)
		{
			StatementsBean statementsBean=(StatementsBean) results.get(i);
			if(statementsBean.getLastbillingstatus().equalsIgnoreCase("NN")||statementsBean.getLastbillingstatus().equalsIgnoreCase("MM"))
			{
				statementsBean.setLastbillingstatus("Pt. Statement");
			}
			else if(statementsBean.getLastbillingstatus().equalsIgnoreCase("EE"))
			{
				statementsBean.setLastbillingstatus("Pt. Reminder");
			}
			else if(statementsBean.getLastbillingstatus().equalsIgnoreCase("FF"))
			{
				statementsBean.setLastbillingstatus("Final Notice");
			}
			else if(statementsBean.getLastbillingstatus().equalsIgnoreCase("CC"))
			{
				statementsBean.setLastbillingstatus("Collection List");
			}
			else
			{
				statementsBean.setLastbillingstatus("-1");
			}
			resultList.add(statementsBean); 
		}
		
		
		HashMap<String, StatementsBean> tmp= new HashMap<String, StatementsBean>();
		
		for(int i=0;i<resultList.size();i++){
			StatementsBean selectionBean=(StatementsBean) results.get(i);
			tmp.put(selectionBean.getPatid().toString(), selectionBean);
		}
		System.out.println("Temp size is====>"+tmp.size());
		
		List<StatementsBean> resultListForTemp = new ArrayList<StatementsBean>();
		/*for(int i=0;i<tmp.size();i++){
			StatementsBean statementsBean=(StatementsBean) tmp.keySet();
			resultListForTemp.add(statementsBean);
			
		}*/
		for ( String key : tmp.keySet() ) {
			StatementsBean statementsBean=(StatementsBean) tmp.get(key);
			resultListForTemp.add(statementsBean);
		}
		
		return resultListForTemp;
	}
	public Date convertToDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlStartDate =null;
		try{
			java.util.Date d = df.parse(date);
			sqlStartDate =  new java.sql.Date(d.getTime()); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return sqlStartDate;
	}
}
