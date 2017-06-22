package com.glenwood.glaceemr.server.application.services.ar;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.models.AccountType;
import com.glenwood.glaceemr.server.application.models.AccountType_;
import com.glenwood.glaceemr.server.application.models.AdActionhistory;
import com.glenwood.glaceemr.server.application.models.AdActionhistory_;
import com.glenwood.glaceemr.server.application.models.ArActionList;
import com.glenwood.glaceemr.server.application.models.ArActionList_;
import com.glenwood.glaceemr.server.application.models.ArProblemList;
import com.glenwood.glaceemr.server.application.models.ArProblemList_;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails_;
import com.glenwood.glaceemr.server.application.models.AuthorizationReferral;
import com.glenwood.glaceemr.server.application.models.AuthorizationReferral_;
import com.glenwood.glaceemr.server.application.models.BillingReason;
import com.glenwood.glaceemr.server.application.models.BillingReason_;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.H172;
import com.glenwood.glaceemr.server.application.models.H172_;
import com.glenwood.glaceemr.server.application.models.H222;
import com.glenwood.glaceemr.server.application.models.H222_;
import com.glenwood.glaceemr.server.application.models.H277;
import com.glenwood.glaceemr.server.application.models.H277_;
import com.glenwood.glaceemr.server.application.models.H278;
import com.glenwood.glaceemr.server.application.models.H279;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.InsCompany_;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PosType;
import com.glenwood.glaceemr.server.application.models.PosType_;
import com.glenwood.glaceemr.server.application.models.ProblemReport;
import com.glenwood.glaceemr.server.application.models.ProblemReport_;
import com.glenwood.glaceemr.server.application.models.ProblemType;
import com.glenwood.glaceemr.server.application.models.ProblemType_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ServiceBalances;
import com.glenwood.glaceemr.server.application.models.ServiceBalances_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;
import com.glenwood.glaceemr.server.application.repositories.AdActionhistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.ArProblemListRepository;
import com.glenwood.glaceemr.server.application.repositories.BillingReasonRepository;
import com.glenwood.glaceemr.server.application.repositories.BillinglookupRepository;
import com.glenwood.glaceemr.server.application.repositories.NonServiceDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemReportRepository;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.Utilities;

@Service
@Transactional
public class ArActionsServiceImpl implements ArActionsServices{

	@Autowired
	EntityManager em;

	@Autowired
	NonServiceDetailsRepository nonServiceDetailRepository;

	@Autowired
	BillingReasonRepository billingReasonRepository;

	@Autowired
	AdActionhistoryRepository adActionHistoryRepository;

	@Autowired
	BillinglookupRepository billinglookupRepository;

	@Autowired
	ArProblemListRepository arProblemListRepository;
	
	@Autowired
	ProblemReportRepository problemReportRepository;

	String actionDesc;
	String currentStatus;
	String ticketNo="";
	public static Integer ref = 0;

	/**
	 * @author venu
	 */
	@Override
	public List<ArDetailBean> getAllArFollowUP(String fromDate,String toDate)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ArDetailBean> cq = builder.createQuery(ArDetailBean.class);
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
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		predicateList.add(builder.equal(cpt.get(Cpt_.cptCpttype),1));
		predicateList.add(builder.not(cpt.get(Cpt_.cptCptcode).in("PTSXS","PTREF","INSXS","PT000")));
		predicateList.add(builder.not(root.get(ServiceDetail_.serviceDetailSubmitStatus).in("Y","C","X","Z","A")));
		predicateList.add(builder.notLike(builder.coalesce(/*builder.lower*/(arActionList.get(ArActionList_.arActionListActionName)),"''"),"%missing eob%"));
		
		if(!fromDate.equals("") && !toDate.equals(""))
			predicateList.add(builder.between(root.get(ServiceDetail_.serviceDetailPlaceddate),convertToDate(fromDate),convertToDate(toDate)));
		
		Predicate[] predicateArray = new Predicate[predicateList.size()];
		predicateList.toArray(predicateArray);
		
		cq.distinct(true);
		cq.select(builder.construct(ArDetailBean.class,
						patientReg.get(PatientRegistration_.patientRegistrationAccountno),
						patientReg.get(PatientRegistration_.patientRegistrationLastName),
						patientReg.get(PatientRegistration_.patientRegistrationFirstName),
						patientReg.get(PatientRegistration_.patientRegistrationMaidenName),
						builder.function("to_char",String.class,patientReg.get(PatientRegistration_.patientRegistrationDob),builder.literal("mm/dd/yyyy")),
						patientReg.get(PatientRegistration_.patientRegistrationState),
						root.get(ServiceDetail_.serviceDetailId),
						patientReg.get(PatientRegistration_.patientRegistrationId),
						cpt.get(Cpt_.cptCptcode),
						root.get(ServiceDetail_.serviceDetailCharges),
						serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailId),
						serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailRemarkCode),
						serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailComments),
						primaryIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
						secondIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
						insCompany1.get(InsCompany_.insCompanyName),
						insCompany2.get(InsCompany_.insCompanyName),
						root.get(ServiceDetail_.serviceDetailDx1),
						root.get(ServiceDetail_.serviceDetailDx2),
						root.get(ServiceDetail_.serviceDetailDx3),
						root.get(ServiceDetail_.serviceDetailDx4),
						root.get(ServiceDetail_.serviceDetailModifier1),
						root.get(ServiceDetail_.serviceDetailModifier2),
						root.get(ServiceDetail_.serviceDetailModifier3),
						root.get(ServiceDetail_.serviceDetailModifier4),
						root.get(ServiceDetail_.serviceDetailSubmitStatus),
						root.get(ServiceDetail_.serviceDetailCopay),
						builder.function("to_char", String.class,root.get(ServiceDetail_.serviceDetailDop),builder.literal("mm/dd/yyyy")),
						root.get(ServiceDetail_.serviceDetailUnit),
						insCompAddr1.get(InsCompAddr_.insCompAddrAddress),
						root.get(ServiceDetail_.serviceDetailReference),
						servicegDoctor.get(EmployeeProfile_.empProfileFullname),
						billingDoctor.get(EmployeeProfile_.empProfileFullname),
						authorizationJoin.get(AuthorizationReferral_.authorizationReferralAuthNo),
						builder.function("to_char", String.class,root.get(ServiceDetail_.serviceDetailDos),builder.literal("mm/dd/yyyy")),
						builder.function("to_char", String.class,adAction.get(AdActionhistory_.adAhNextfollowupactiondate),builder.literal("mm/dd/yyyy")),
						builder.function("to_char", String.class,adAction.get(AdActionhistory_.adAhNextfollowupactiondate),builder.literal("mm/dd/yyyy")),
						builder.function("to_char", String.class,adAction.get(AdActionhistory_.adAhActiontakendate),builder.literal("mm/dd/yyyy")),
						adAction.get(AdActionhistory_.adAhActiondescription),
						builder.function("to_char", String.class,adAction.get(AdActionhistory_.adAhNextfollowupactiondate),builder.literal("mm/dd/yyyy")),
						accountType.get(AccountType_.accType),
						posType.get(PosType_.posTypeTypeName),
						arProblem.get(ArProblemList_.arProblemListProblem),
						builder.function("to_char", String.class,builder.function("max", Date.class, h1.get(H172_.h172005)),builder.literal("mm/dd/yyyy")),
						root.get(ServiceDetail_.serviceDetailExpectedPayments),
						posDetails.get(PosTable_.posTablePlaceId),
						serviceBalance.get(ServiceBalances_.insuranceBalance),
						posDetails.get(PosTable_.posTableFacilityComments),
						serviceBalance.get(ServiceBalances_.primaryInsurancePayment),
						serviceBalance.get(ServiceBalances_.secondaryInsurancePayment),
						billingLookup.get(Billinglookup_.blookIntid),
						adAction.get(AdActionhistory_.adAhDenialtype),
						root.get(ServiceDetail_.serviceDetailArStatus))); 
		
		cq.where(predicateArray);
		
		cq.groupBy(patientReg.get(PatientRegistration_.patientRegistrationAccountno),
				patientReg.get(PatientRegistration_.patientRegistrationLastName),
				patientReg.get(PatientRegistration_.patientRegistrationFirstName),
				patientReg.get(PatientRegistration_.patientRegistrationMaidenName),
				patientReg.get(PatientRegistration_.patientRegistrationDob),
				patientReg.get(PatientRegistration_.patientRegistrationState),
				root.get(ServiceDetail_.serviceDetailId),
				patientReg.get(PatientRegistration_.patientRegistrationId),
				cpt.get(Cpt_.cptCptcode),
				root.get(ServiceDetail_.serviceDetailCharges),
				serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailId),
				serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailRemarkCode),
				serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailComments),
				primaryIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
				secondIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
				insCompany1.get(InsCompany_.insCompanyName),
				insCompany2.get(InsCompany_.insCompanyName),
				root.get(ServiceDetail_.serviceDetailDx1),
				root.get(ServiceDetail_.serviceDetailDx2),
				root.get(ServiceDetail_.serviceDetailDx3),
				root.get(ServiceDetail_.serviceDetailDx4),
				root.get(ServiceDetail_.serviceDetailModifier1),
				root.get(ServiceDetail_.serviceDetailModifier2),
				root.get(ServiceDetail_.serviceDetailModifier3),
				root.get(ServiceDetail_.serviceDetailModifier4),
				root.get(ServiceDetail_.serviceDetailSubmitStatus),
				root.get(ServiceDetail_.serviceDetailCopay),
				root.get(ServiceDetail_.serviceDetailDop),
				root.get(ServiceDetail_.serviceDetailUnit),
				insCompAddr1.get(InsCompAddr_.insCompAddrAddress),
				root.get(ServiceDetail_.serviceDetailReference),
				servicegDoctor.get(EmployeeProfile_.empProfileFullname),
				billingDoctor.get(EmployeeProfile_.empProfileFullname),
				authorizationJoin.get(AuthorizationReferral_.authorizationReferralAuthNo),
				root.get(ServiceDetail_.serviceDetailDos),
				adAction.get(AdActionhistory_.adAhNextfollowupactiondate),
				adAction.get(AdActionhistory_.adAhActiontakendate),
				adAction.get(AdActionhistory_.adAhActiondescription),
				adAction.get(AdActionhistory_.adAhNextfollowupactiondate),
				accountType.get(AccountType_.accType),
				posType.get(PosType_.posTypeTypeName),
				arProblem.get(ArProblemList_.arProblemListProblem),
				h1.get(H172_.h172005),
				posDetails.get(PosTable_.posTablePlaceId),
				root.get(ServiceDetail_.serviceDetailExpectedPayments),
				posDetails.get(PosTable_.posTablePlaceOfService),
				serviceBalance.get(ServiceBalances_.insuranceBalance),
				posDetails.get(PosTable_.posTableFacilityComments),
				serviceBalance.get(ServiceBalances_.primaryInsurancePayment),
				serviceBalance.get(ServiceBalances_.secondaryInsurancePayment),
				billingLookup.get(Billinglookup_.blookIntid),
				adAction.get(AdActionhistory_.adAhDenialtype),
				root.get(ServiceDetail_.serviceDetailArStatus));
		
		List<ArDetailBean> resultList = em.createQuery(cq).getResultList();
		
		HashMap<String, ArDetailBean> arHashMap= new HashMap<String, ArDetailBean>();
		
		for(int i=0;i<resultList.size();i++)
		{
			ArDetailBean arDetailBean=(ArDetailBean) resultList.get(i);
			arHashMap.put(arDetailBean.getServiceId().toString(), arDetailBean);
		}
		
		List<ArDetailBean> distinctResultList = new ArrayList<ArDetailBean>();
		
		for ( String key : arHashMap.keySet() ) {
			ArDetailBean arDetailBean=(ArDetailBean) arHashMap.get(key);
			distinctResultList.add(arDetailBean);
		}
		
		return distinctResultList;
	}
	
	/**
	 * @author naveen
	 */
	@Override
	public CommonResponseBean billToPatAction(CommonActionBean billToPatBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "T");
			criteriaUpdate.set("serviceDetailBillingReason", getBillingReasonId(billToPatBean));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), billToPatBean.getServiceId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			actionDesc = "Action Type:"+billToPatBean.getActionDescription()+" Billing Reason: "+billToPatBean.getBillingReason();
			currentStatus = "Action Type: "+billToPatBean.getActionDescription();
			adActionHistorySave(actionDesc,currentStatus,billToPatBean);
			return setResponse(1, billToPatBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, billToPatBean, e);
		}
	}
	
	/**
	 * @author venu
	 * **/
	@Override
	public CommonResponseBean changeArCategoryAction(CommonActionBean changeArCategoryBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus", changeArCategoryBean.getArCategory());
			criteriaUpdate.set("serviceDetailModifiedby",changeArCategoryBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.where((root.get("serviceDetailId").in(changeArCategoryBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+changeArCategoryBean.getActionDescription()+" Comments:"+changeArCategoryBean.getNotes();
			currentStatus = "Action Type: "+changeArCategoryBean.getActionDescription()+"; Notes: "+changeArCategoryBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,changeArCategoryBean);
			return setResponse(1, changeArCategoryBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeArCategoryBean, e);
		}
	}

	@Override
	public CommonResponseBean changeCptAction(CommonActionBean changeCptBean) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * author Anil
	 * 
	 */
	@Override
	public CommonResponseBean changeCptChargesAction(CommonActionBean changeCptChargesBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailCharges", changeCptChargesBean.getNewCptCharges());
			criteriaUpdate.set("serviceDetailSubmitStatus", "P");

			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changeCptChargesBean.getServiceId()));		
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changeCptChargesBean.getActionDescription()+" from "+changeCptChargesBean.getOldCptCharges()+" to "+changeCptChargesBean.getNewCptCharges();
			currentStatus = "Action Type: "+changeCptChargesBean.getActionDescription();
			adActionHistorySave(actionDesc, currentStatus, changeCptChargesBean);
			return setResponse(1, changeCptChargesBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeCptChargesBean, e);
		}
	}

	/**
	 * @author Ananth
	 */
	@Override
	public CommonResponseBean changeDxAction(CommonActionBean changeDxBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailPatientid", changeDxBean.getPatientId());
			criteriaUpdate.set("serviceDetailDx1", changeDxBean.getDx1());
			criteriaUpdate.set("serviceDetailDx2", changeDxBean.getDx2() == null ? "" : changeDxBean.getDx2());
			criteriaUpdate.set("serviceDetailDx3", changeDxBean.getDx3() == null ? "" : changeDxBean.getDx3());
			criteriaUpdate.set("serviceDetailDx4", changeDxBean.getDx4() == null ? "" : changeDxBean.getDx4());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailModifiedby", changeDxBean.getModifiedBy());

			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changeDxBean.getServiceId()));		
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changeDxBean.getActionDescription()+"; Comments: "+changeDxBean.getNotes();
			currentStatus = "Action Type: "+changeDxBean.getActionDescription()+"; Notes: "+changeDxBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, changeDxBean);
			return setResponse(1, changeDxBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeDxBean, e);
		}
	}

	/**
	 * Auhor ANil
	 */
	@Override
	public CommonResponseBean changeMod1Action(CommonActionBean changeMod1Bean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailModifier1", changeMod1Bean.getNewMod1());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailModifiedby", changeMod1Bean.getModifiedBy());
			if(changeMod1Bean.getSubmitStatus().equals("R"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "P");
			else if(changeMod1Bean.getSubmitStatus().equals("S"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "Q");
			else if(changeMod1Bean.getSubmitStatus().equals("H"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "G");

			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changeMod1Bean.getServiceId()));		
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changeMod1Bean.getActionDescription()+"; Comments: "+changeMod1Bean.getNotes();
			currentStatus = "Action Type: "+changeMod1Bean.getActionDescription()+"; Notes: "+changeMod1Bean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, changeMod1Bean);
			return setResponse(1, changeMod1Bean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeMod1Bean, e);
		}
	}
	
	/**
	 * Auhor ANil
	 */
	@Override
	public CommonResponseBean changeMod2Action(CommonActionBean changeMod2Bean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailModifier2", changeMod2Bean.getNewMod2());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailModifiedby", changeMod2Bean.getModifiedBy());
			if(changeMod2Bean.getSubmitStatus().equals("R"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "P");
			else if(changeMod2Bean.getSubmitStatus().equals("S"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "Q");
			else if(changeMod2Bean.getSubmitStatus().equals("H"))
				criteriaUpdate.set("serviceDetailSubmitStatus", "G");

			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changeMod2Bean.getServiceId()));		
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changeMod2Bean.getActionDescription()+"; Comments: "+changeMod2Bean.getNotes();
			currentStatus = "Action Type: "+changeMod2Bean.getActionDescription()+"; Notes: "+changeMod2Bean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, changeMod2Bean);
			return setResponse(1, changeMod2Bean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeMod2Bean, e);
		}
	}

	/**
	 * @author Ananth
	 */
	@Override
	public CommonResponseBean changePrimaryInsuranceAction(CommonActionBean changePrimaryInsuranceBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailPrimaryins", Integer.parseInt(HUtil.Nz(getInsurenceId(changePrimaryInsuranceBean.getPrimaryInsuranceId(),1)+"","0")));
			criteriaUpdate.set("serviceDetailSubmitStatus", "P");
			criteriaUpdate.set("serviceDetailModifiedby", changePrimaryInsuranceBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changePrimaryInsuranceBean.getServiceId()));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changePrimaryInsuranceBean.getActionDescription()+"; Comments: "+changePrimaryInsuranceBean.getPrimaryInsurance();
			currentStatus = "Action Type: "+changePrimaryInsuranceBean.getActionDescription();
			adActionHistorySave(actionDesc, currentStatus, changePrimaryInsuranceBean);
			return setResponse(1, changePrimaryInsuranceBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changePrimaryInsuranceBean, e);
		}
	}

	/**
	 * @author Ananth
	 */
	@Override
	public CommonResponseBean changeSecondaryInsuranceAction(CommonActionBean changeSecondaryInsuranceBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSecondaryins", Integer.parseInt(HUtil.Nz(getInsurenceId(changeSecondaryInsuranceBean.getSecondaryInsurance(),2)+"","0")));
			criteriaUpdate.set("serviceDetailSubmitStatus", "Q");
			criteriaUpdate.set("serviceDetailModifiedby", changeSecondaryInsuranceBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), changeSecondaryInsuranceBean.getServiceId()));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+changeSecondaryInsuranceBean.getActionDescription()+"; Comments: "+changeSecondaryInsuranceBean.getSecondaryInsurance();
			currentStatus = "Action Type: "+changeSecondaryInsuranceBean.getActionDescription();
			adActionHistorySave(actionDesc, currentStatus, changeSecondaryInsuranceBean);
			return setResponse(1, changeSecondaryInsuranceBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeSecondaryInsuranceBean, e);
		}
	}

	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean changeSubmitStatusAction(CommonActionBean changeSubmitToStatusBean) {
		
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", changeSubmitToStatusBean.getResubmitTo());
			criteriaUpdate.set("serviceDetailModifiedby",changeSubmitToStatusBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(changeSubmitToStatusBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+HUtil.Nz(changeSubmitToStatusBean.getActionDescription()," ")+" Reason:"+HUtil.Nz(changeSubmitToStatusBean.getReason(), " ")+" Comments:"+HUtil.Nz(changeSubmitToStatusBean.getNotes(), " ");
			currentStatus = "Action Type: "+changeSubmitToStatusBean.getActionDescription()+"; Notes: "+changeSubmitToStatusBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,changeSubmitToStatusBean);
			return setResponse(1, changeSubmitToStatusBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, changeSubmitToStatusBean, e);
		}
	}

	/**
	 * @author Ananth
	 */
	@Override
	public CommonResponseBean claimToPrimaryAction(CommonActionBean claimToPrimaryBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "P");
			criteriaUpdate.set("serviceDetailModifiedby", claimToPrimaryBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			if(claimToPrimaryBean.getServiceAsPaperClaim()==1){
				criteriaUpdate.set("serviceDetailIspaperclaim", claimToPrimaryBean.getServiceAsPaperClaim());
			}
			if(claimToPrimaryBean.getServiceWholeClaims()==1){
				criteriaUpdate.set("serviceDetailIswholeclaimActive",claimToPrimaryBean.getServiceWholeClaims());
			}
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), claimToPrimaryBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+claimToPrimaryBean.getActionDescription()+"; Comments: "+claimToPrimaryBean.getNotes();
			currentStatus = "Action Type: "+claimToPrimaryBean.getActionDescription()+"; Notes: "+claimToPrimaryBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, claimToPrimaryBean);
			return setResponse(1, claimToPrimaryBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, claimToPrimaryBean, e);
		}
	}

	/**
	 * @author Ananth
	 */
	@Override
	public CommonResponseBean claimToSecondaryAction(CommonActionBean claimToSecondaryBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "Q");
			criteriaUpdate.set("serviceDetailModifiedby", claimToSecondaryBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new Timestamp(new java.util.Date().getTime()));
			if(claimToSecondaryBean.getServiceAsPaperClaim()==1){
				criteriaUpdate.set("serviceDetailIspaperclaim", claimToSecondaryBean.getServiceAsPaperClaim());
			}
			if(claimToSecondaryBean.getServiceWholeClaims()==1){
				criteriaUpdate.set("serviceDetailIswholeclaimActive",claimToSecondaryBean.getServiceWholeClaims());
			}
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), claimToSecondaryBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+claimToSecondaryBean.getActionDescription()+"; Comments: "+claimToSecondaryBean.getNotes();
			currentStatus = "Action Type: "+claimToSecondaryBean.getActionDescription()+"; Notes: "+claimToSecondaryBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, claimToSecondaryBean);
			return setResponse(1, claimToSecondaryBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, claimToSecondaryBean, e);
		}
	}

	/**
	 * Author anil
	 */
	@Override
	public CommonResponseBean followUpAction(CommonActionBean followUpBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailModifiedby", followUpBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), followUpBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+followUpBean.getActionDescription()+"; Comments: "+followUpBean.getNotes();
			currentStatus = "Action Type: "+followUpBean.getActionDescription()+"; Notes: "+followUpBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, followUpBean);
			return setResponse(1, followUpBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, followUpBean, e);
		}
	}

	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean markAsApppealAction(CommonActionBean markAsApppealBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus",getArReasonId(markAsApppealBean) );
			criteriaUpdate.set("serviceDetailModifiedby", markAsApppealBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.set("serviceDetailSubmitStatus", markAsApppealBean.getResubmitTo());
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsApppealBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			currentStatus="Notes:"+markAsApppealBean.getNotes();
			actionDesc = "Action Type:"+HUtil.Nz(markAsApppealBean.getActionDescription(),"")+" Reason:"+HUtil.Nz(markAsApppealBean.getDenialReason() ,"")+" Comments:"+HUtil.Nz(markAsApppealBean.getNotes(), "");
			adActionHistorySave(actionDesc,currentStatus, markAsApppealBean);
			return setResponse(1, markAsApppealBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsApppealBean, e);
		}
	}
	
	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean markAsBadDebtAction(CommonActionBean markAsBadDebtBean) 
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus",getArReasonId(markAsBadDebtBean) );
			criteriaUpdate.set("serviceDetailSubmitStatus", "B");
			criteriaUpdate.set("serviceDetailModifiedby",markAsBadDebtBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(markAsBadDebtBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			currentStatus="Notes:"+markAsBadDebtBean.getNotes();
			actionDesc = "Action Type:"+markAsBadDebtBean.getActionDescription()+" Reason:"+markAsBadDebtBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,markAsBadDebtBean);
			return setResponse(1, markAsBadDebtBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsBadDebtBean, e);
		}
		
	}

	/**
	 * author anil
	 */
	@Override
	public CommonResponseBean markAsCapitationAction(CommonActionBean markAsCapitationBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus","C");
			criteriaUpdate.set("serviceDetailModifiedby", markAsCapitationBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsCapitationBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+markAsCapitationBean.getActionDescription()+"; Comments: "+markAsCapitationBean.getNotes();
			currentStatus = "Action Type: "+markAsCapitationBean.getActionDescription()+"; Notes: "+markAsCapitationBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsCapitationBean);
			return setResponse(1, markAsCapitationBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsCapitationBean, e);
		}
	}

	/**
	 * @author venu
	 * **/
	@Override
	public CommonResponseBean markAsDuplicateAction(CommonActionBean markAsDuplicateBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus", 8);
			criteriaUpdate.set("serviceDetailModifiedby", markAsDuplicateBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsDuplicateBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+markAsDuplicateBean.getActionDescription()+"; Comments: "+markAsDuplicateBean.getNotes();
			currentStatus = "Action Type: "+markAsDuplicateBean.getActionDescription()+"; Check details: "+markAsDuplicateBean.getCheckNo()+"; Check: "+markAsDuplicateBean.getCheckDate()+"; Check Amount: "+markAsDuplicateBean.getCheckAmount()+"; Copay: "+markAsDuplicateBean.getCopay()+"; Coins: "+markAsDuplicateBean.getCoins()+"; Deductible: "+markAsDuplicateBean.getDeductible()+"; Comments: "+markAsDuplicateBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsDuplicateBean);
			return setResponse(1, markAsDuplicateBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsDuplicateBean, e);
		}
	}
	
	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean markAsFaxAction(CommonActionBean markAsFaxBean) {

		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			
			criteriaUpdate.set("serviceDetailSubmitStatus", markAsFaxBean.getResubmitTo());
			criteriaUpdate.set("serviceDetailModifiedby",markAsFaxBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(markAsFaxBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			currentStatus="Notes:"+markAsFaxBean.getNotes();
			actionDesc = "Action Type:"+markAsFaxBean.getActionDescription()+" Comments:"+markAsFaxBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,markAsFaxBean);
			return setResponse(1, markAsFaxBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsFaxBean, e);
		}	
	}

	/**
	 * @author venu
	 * */
	@Override
	public CommonResponseBean markAsFullySettledAction(CommonActionBean markAsFullySettledBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		NonServiceDetails nonServiceDetail = new NonServiceDetails();
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "X");
			criteriaUpdate.set("serviceDetailModifiedby", markAsFullySettledBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new java.util.Date());
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsFullySettledBean.getServiceId()));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			nonServiceDetail.setNonServiceDetailId(Long.parseLong("-1"));
			nonServiceDetail.setNonServiceDetailPatientId(Long.parseLong(markAsFullySettledBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailServiceId(Long.parseLong(markAsFullySettledBean.getServiceId().toString()));
			nonServiceDetail.setNonServiceDetailDateOfPosting(convertToDate(markAsFullySettledBean.getDop()));
			//nonServiceDetail.setNonServiceDetailPaymentCptId(Integer.parseInt(markAsFullySettledBean.getPaymentCptId()));
			nonServiceDetail.setNonServiceDetailComments(markAsFullySettledBean.getNotes());
			nonServiceDetail.setNonServiceDetailPaymentMethod(1);
			nonServiceDetail.setNonServiceDetailDeductible(0.00);
			nonServiceDetail.setNonServiceDetailCoins(0.00);
			nonServiceDetail.setNonServiceDetailDatePaid(convertToDate(markAsFullySettledBean.getDop()));
			nonServiceDetail.setNonServiceDetailRiskWithheld(0.00);
			//nonServiceDetail.setNonServiceDetailPlacedBy(markAsFullySettledBean.getPlacedBy());	
			nonServiceDetail.setNonServiceDetailLastModifiedBy(markAsFullySettledBean.getModifiedBy());
			nonServiceDetail.setNonServiceDetailLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailPayerId(Long.parseLong(markAsFullySettledBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailCheckId((long)-1);
			nonServiceDetail.setH555555(1);
			nonServiceDetail.setNonServiceDetailIsValid(false);
			nonServiceDetail.setNonServiceDetailRefundStatus((short)-1);
			nonServiceDetail.setNonServiceDetailReverseId((long)-1);
			nonServiceDetail.setNonServiceDetailEntryType(Short.parseShort(String.valueOf(21)));
			//nonServiceDetail.setNonServiceDetailReceiptDate(convertToDate(markAsFullySettledBean.getReceiptDate()));
			nonServiceDetail.setNonServiceDetailPaidBy(4);
			nonServiceDetailRepository.saveAndFlush(nonServiceDetail);
			
			actionDesc = "Action Type: "+markAsFullySettledBean.getActionDescription()+"; Comments: "+markAsFullySettledBean.getNotes();
			currentStatus = "Action Type: "+markAsFullySettledBean.getActionDescription()+"; Comments: "+markAsFullySettledBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsFullySettledBean);

			return setResponse(1, markAsFullySettledBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsFullySettledBean, e);

		}
	}

	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean markAsOnHoldAction(CommonActionBean markAsOnHoldBean) 
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "A");
			criteriaUpdate.set("serviceDetailModifiedby", markAsOnHoldBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date())	;
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsOnHoldBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+markAsOnHoldBean.getActionDescription();
			adActionHistorySave(actionDesc,currentStatus, markAsOnHoldBean);
			return setResponse(1, markAsOnHoldBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsOnHoldBean, e);
		}
	}

	@Override
	public CommonResponseBean markAsUncollectableAction(CommonActionBean markAsUncollectableBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean markAsWebAction(CommonActionBean markAsWebBean) 
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", markAsWebBean.getResubmitTo());
			criteriaUpdate.set("serviceDetailModifiedby",markAsWebBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(markAsWebBean.getServiceId())));			
			this.em.createQuery(criteriaUpdate).executeUpdate();
	
			actionDesc = "Action Type:"+markAsWebBean.getActionDescription()+" Comments:"+markAsWebBean.getNotes();
			adActionHistorySave(actionDesc,currentStatus, markAsWebBean);
			return setResponse(1, markAsWebBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsWebBean, e);
		}
	}

	/**
	 * @author venu
	 */
	@Override
	public CommonResponseBean reportAProblem(CommonActionBean reportAProblemBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailModifiedby", reportAProblemBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), reportAProblemBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			problemReportSave(reportAProblemBean);
			actionDesc = "Reported a problem";
			reportAProblemBean.setReference(reportAProblemBean.getModifiedBy().toUpperCase()+"-"+1+"-"+ref+"L");
			reportAProblemBean.setTicketNo(reportAProblemBean.getModifiedBy().toUpperCase()+"-"+1+"-"+ref+"L");
			reportAProblemBean.setProblemId(ref);
			adActionHistorySave(actionDesc, actionDesc, reportAProblemBean);
			ref=0;
			return setResponse(1, reportAProblemBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, reportAProblemBean, e);
		}
	}

	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean toBeCalledAction(CommonActionBean toBeCalledBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailArStatus), String.valueOf(11));
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifiedby), toBeCalledBean.getModifiedBy());
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifieddate),new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), toBeCalledBean.getServiceId()));
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+toBeCalledBean.getActionDescription()+" Problem:"+toBeCalledBean.getProblemNotes()+" Next Followup Date:"+toBeCalledBean.getNextFollowupDate();
			currentStatus="Notes:"+toBeCalledBean.getNotes();
			adActionHistorySave(actionDesc,currentStatus, toBeCalledBean);
			return setResponse(1, toBeCalledBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, toBeCalledBean, e);
		}
	}
	
	/*
	*@author naveeen
	*/
	@Override
	public CommonResponseBean toBeCalledCompletedAction(
			CommonActionBean toBeCalledCompletedBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailArStatus), String.valueOf(13));
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifiedby), toBeCalledCompletedBean.getModifiedBy());
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifieddate),new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), toBeCalledCompletedBean.getServiceId()));
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+toBeCalledCompletedBean.getActionDescription()+" Solution:"+toBeCalledCompletedBean.getProblemNotes();
			currentStatus="Notes:"+toBeCalledCompletedBean.getNotes();
			adActionHistorySave(actionDesc,currentStatus, toBeCalledCompletedBean);
			return setResponse(1, toBeCalledCompletedBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, toBeCalledCompletedBean, e);
		}
	}

	/**
	 * @author naveen
	 */
	@Override
	public CommonResponseBean writeoffAction(CommonActionBean writeoffBean) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<ServiceBalances> rt=criteriaQuery.from(ServiceBalances.class);
		criteriaQuery.select(rt.get(ServiceBalances_.serviceBalance));
		criteriaQuery.where(builder.equal(rt.get(ServiceBalances_.serviceId), writeoffBean.getServiceId()));
		List<Object> result = em.createQuery(criteriaQuery).getResultList();
		double serviceBalance=0;
		for(int i=0;i<result.size();i++){
			 serviceBalance=(double) result.get(0);
		}
		
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		NonServiceDetails nonServiceDetail = new NonServiceDetails();
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
		
			if(serviceBalance==writeoffBean.getWriteOffAmount()){
			criteriaUpdate.set("serviceDetailSubmitStatus", "X");
			}
			criteriaUpdate.set("serviceDetailModifiedby", writeoffBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), writeoffBean.getServiceId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+HUtil.Nz(writeoffBean.getActionDescription()," ")+" Reason:"+HUtil.Nz(writeoffBean.getReason(), " ")+"Notes:"+HUtil.Nz(writeoffBean.getNotes()," ");
			currentStatus = "Action Type: "+writeoffBean.getActionDescription()+"; Notes: "+writeoffBean.getNotes();
			nonServiceDetail.setNonServiceDetailId(Long.parseLong("-1"));
			nonServiceDetail.setNonServiceDetailPatientId(Long.parseLong(writeoffBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailServiceId(Long.parseLong(writeoffBean.getServiceId().toString()));
			nonServiceDetail.setNonServiceDetailDateOfPosting(convertToDate(writeoffBean.getDop()));
			nonServiceDetail.setNonServiceDetailAmount(writeoffBean.getWriteOffAmount());
			nonServiceDetail.setNonServiceDetailPaymentCptId(getPaymentCptId("0000W"));
			nonServiceDetail.setNonServiceDetailComments(actionDesc+":"+HUtil.Nz(writeoffBean.getNotes(),""));
			nonServiceDetail.setNonServiceDetailPaymentMethod(1);
			nonServiceDetail.setNonServiceDetailDeductible(0.00);
			nonServiceDetail.setNonServiceDetailCoins(0.00);
			nonServiceDetail.setNonServiceDetailDatePaid(convertToDate(writeoffBean.getDop()));
			nonServiceDetail.setNonServiceDetailRiskWithheld(0.00);
			nonServiceDetail.setNonServiceDetailPlacedBy(writeoffBean.getModifiedBy());	
			nonServiceDetail.setNonServiceDetailPlacedDate(new Timestamp(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailTransferredBy(writeoffBean.getModifiedBy());
			nonServiceDetail.setNonServiceDetailTransferredFrom((long) -1);
			nonServiceDetail.setNonServiceDetailTransferredTo((long)-1);
			nonServiceDetail.setNonServiceDetailRemarkCode(actionDesc+":"+HUtil.Nz(writeoffBean.getNotes(),""));
			nonServiceDetail.setNonServiceDetailLastModifiedBy(writeoffBean.getModifiedBy());
			nonServiceDetail.setNonServiceDetailLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailPayerId(Long.parseLong(writeoffBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailCheckId((long)-1);
			nonServiceDetail.setH555555(1);
			nonServiceDetail.setNonServiceDetailIsValid(false);
			nonServiceDetail.setNonServiceDetailRefundStatus((short)-1);
			nonServiceDetail.setNonServiceDetailReverseId((long)-1);
			nonServiceDetail.setNonServiceDetailEntryType(Short.parseShort(String.valueOf(21)));
			nonServiceDetail.setNonServiceDetailPaidBy(4);
			nonServiceDetailRepository.saveAndFlush(nonServiceDetail);

			adActionHistorySave(actionDesc,currentStatus, writeoffBean);
			return setResponse(1, writeoffBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, writeoffBean, e);
		}

	}

	public CommonResponseBean setResponse(int mode, CommonActionBean commonActionBean, Exception exception)
	{
		CommonResponseBean finalResponseBean = new CommonResponseBean();
		if(mode == 1)
		{
			finalResponseBean.setResponseStatus("Success");
			finalResponseBean.setPatientAccountId(commonActionBean.getPatientAccountId());
			finalResponseBean.setAccountServerIp(commonActionBean.getAccountServerIp());
			finalResponseBean.setServiceId(commonActionBean.getServiceId());
			finalResponseBean.setRowId(commonActionBean.getRowId());
			finalResponseBean.setInnerRowId(commonActionBean.getInnerRowId());
			finalResponseBean.setTaskId(commonActionBean.getTaskId());
			finalResponseBean.setEmpId(commonActionBean.getModifiedBy());
			finalResponseBean.setActionId(commonActionBean.getActionId()+"");
			finalResponseBean.setArId(commonActionBean.getArId());
			finalResponseBean.setTicketNo(HUtil.Nz(commonActionBean.getTicketNo(), ""));
			finalResponseBean.setFailedReason("--");
		}
		else
		{
			finalResponseBean.setResponseStatus("Failed");
			finalResponseBean.setPatientAccountId(commonActionBean.getPatientAccountId());
			finalResponseBean.setAccountServerIp(commonActionBean.getAccountServerIp());
			finalResponseBean.setServiceId(commonActionBean.getServiceId());
			finalResponseBean.setRowId(commonActionBean.getRowId());
			finalResponseBean.setInnerRowId(commonActionBean.getInnerRowId());
			finalResponseBean.setTaskId(commonActionBean.getTaskId());
			finalResponseBean.setEmpId(commonActionBean.getModifiedBy());
			finalResponseBean.setActionId(commonActionBean.getActionId()+"");
			finalResponseBean.setArId(commonActionBean.getArId());
			finalResponseBean.setFailedReason(exception.getMessage());
		}
		return finalResponseBean;
	}

	public void adActionHistorySave(String actionDesc,String currentStatus, CommonActionBean commonActionBean) throws ParseException
	{
		AdActionhistory actionHistory = new AdActionhistory();
		actionHistory.setAdAhId(-1);
		actionHistory.setAdAhServiceid((commonActionBean.getServiceId()));
		actionHistory.setAdAhServicestatuscode(commonActionBean.getServiceStatusCode());
		actionHistory.setAdAhActionid(commonActionBean.getActionId());
		actionHistory.setAdAhActiontakendate(new Timestamp(new java.util.Date().getTime()));
		actionHistory.setAdAhActiontakenby(commonActionBean.getModifiedBy());
		actionHistory.setAdAhNextfollowupactiondate(convertToTimestamp(HUtil.Nz(commonActionBean.getNextFollowupDate(), "01/01/1900")));
		actionHistory.setAdAhNextfollowupactionid(getFollowupActionId(commonActionBean));
		if(commonActionBean.getProblemType()!=null)
		{
			if(commonActionBean.getActionId() == 9 || commonActionBean.getActionId() == 16 || commonActionBean.getActionId() == 20 || commonActionBean.getActionId() == 8)
				actionHistory.setAdAhProblemid(getProblemTypeIdDesc(commonActionBean));
			else
				actionHistory.setAdAhProblemid(-1);
		}
		actionHistory.setAdAhIsrecent(checkIsRecent(commonActionBean));
		actionHistory.setAdAhActionreason(Integer.parseInt(getArReasonId(commonActionBean)+""));
		actionHistory.setAdAhActiondescription(actionDesc);
		actionHistory.setAdAhModuleid(1);
		actionHistory.setAdAhDenialid(-1);
		actionHistory.setAdAhServicestatusid(-1);
		actionHistory.setAdAhInsuranceid(-1);
		actionHistory.setAdAhActiontrackerid(-1);
		actionHistory.setAdAhActionreference(commonActionBean.getReference());
		actionHistory.setAdAhNotes(actionDesc);
		actionHistory.setAdAhCheckid(commonActionBean.getCheckNo());
		actionHistory.setAdAhCopay(Double.parseDouble(HUtil.Nz(commonActionBean.getCopay(),"0.0")));
		actionHistory.setAdAhCoins(Double.parseDouble(HUtil.Nz(commonActionBean.getCoins(),"0.0")));
		actionHistory.setAdAhDenialreason(-1);
		actionHistory.setAdAhDenialtype(-1);
		actionHistory.setAdAhDenialCategory(-1);
		try 
		{
			actionHistory.setAdAhPayment(Double.parseDouble(HUtil.Nz(commonActionBean.getPayment(),"0.0")));
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}
		actionHistory.setAdAhDeductible(Double.parseDouble(HUtil.Nz(commonActionBean.getDeductible(),"0.0")));
		actionHistory.setAdAhAmount(Double.parseDouble(HUtil.Nz(commonActionBean.getCheckAmount(),"0.0")));
		adActionHistoryRepository.saveAndFlush(actionHistory);

		updateOtherServiceDetail(currentStatus, commonActionBean);
	}
	
	public void problemReportSave(CommonActionBean commonActionBean) throws ParseException
	{
		//DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		//java.util.Date dateofPosting = (java.util.Date)formatter.parse(commonActionBean.getModifiedDate());
		//SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");

		//Timestamp currentTime = new Timestamp(dateofPosting.getTime());
		ProblemReport problemReport = new ProblemReport();
		ref=Integer.parseInt((HUtil.Nz(getMaxProblemId(commonActionBean.getModifiedBy()),"0")))+1;
		problemReport.setProblemReportUniqueid(-1);
		problemReport.setProblemReportPracticeId(1);
		problemReport.setProblemReportComplexity(1);
		problemReport.setProblemReportTicketNo("");
		problemReport.setProblemReportProblemId(ref);
		problemReport.setProblemReportPatientName(commonActionBean.getPatientName());
		problemReport.setProblemReportAccountNo(commonActionBean.getPatientAccountId());
		problemReport.setProblemReportProblem(commonActionBean.getProblem());

		Integer status=7;
		if(commonActionBean.getProblemStatus().equalsIgnoreCase("Open")){
			status=1;
		}
		problemReport.setProblemReportProblemstatus(status);
		problemReport.setProblemReportPriority(1);
		problemReport.setProblemReportReportedBy(commonActionBean.getModifiedBy());
		problemReport.setProblemReportReportedOn(new Timestamp(new java.util.Date().getTime()));
		problemReport.setProblemReportReportedTo(commonActionBean.getTo());
		problemReport.setProblemReportLoginUser(commonActionBean.getModifiedBy());
		problemReport.setProblemReportLastModified(new Timestamp(new java.util.Date().getTime()));
		problemReport.setProblemReportPatientcall(2);
		problemReport.setProblemReportSubject(commonActionBean.getSubject());
		problemReport.setProblemReportPatientid(commonActionBean.getPatientId());
		problemReport.setProblemReportFilepaths("");
		problemReport.setProblemReportFilenames("");
		problemReport.setProblemReportPatientInsId(commonActionBean.getPrimaryInsuranceId());
		problemReport.setProblemReportProblemDenialrulevalidatorId(-1);
		//problemReport.setProblemReportProblemType(Integer.parseInt(getDenialTypeId(commonActionBean)+""));
		problemReport.setProblemReportProblemTypedesc(commonActionBean.getProblemType()); 
		problemReportRepository.saveAndFlush(problemReport);
	}

	public void updateOtherServiceDetail(String popupCurrentStatus, CommonActionBean commonActionBean){

		String query="update associate_service_detail set associate_service_detail_currentstatus = coalesce(associate_service_detail_currentstatus,'') || case when length(associate_service_detail_currentstatus)>0 then ';' else '' end || ' ' || to_mmddyyyy(now()) || ' - "+
				""+Utilities.handleSymbols(popupCurrentStatus)+"'";

		if(commonActionBean.getProblemType()!=null)
		{
			if(commonActionBean.getActionId() == 9 || commonActionBean.getActionId() == 16 || commonActionBean.getActionId() == 20 || commonActionBean.getActionId() == 8)
				query=query+","+"associate_service_detail_problem_id = "+getProblemTypeIdDesc(commonActionBean);
		}
		query=query+ " where associate_service_detail_service_id="+commonActionBean.getServiceId();
		actionDesc = "";
		currentStatus = "";
		try 
		{
			int result=em.createNativeQuery(query).executeUpdate();
			System.out.println("updateOtherServiceDetail--Result----->"+result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Integer getMaxProblemId(String user) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery(Object.class);
		Root<ProblemReport> root = criteriaQuery.from(ProblemReport.class);
		;


		TypedQuery<Object> typedQuery = em.createQuery(criteriaQuery
				.select(builder.max(root.get(ProblemReport_.problemReportProblemId)))
				.where(builder.like(root.get(ProblemReport_.problemReportTicketNo), user+"%")));

		List<Object> resultList = typedQuery.getResultList();

		return (Integer)resultList.get(0);
	}

	@Override
	public Integer getBillingReasonId(CommonActionBean commonAction) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery();
		Root<BillingReason> root = criteriaQuery.from(BillingReason.class);
		criteriaQuery.select(root.get(BillingReason_ .billingReasonId));
		criteriaQuery.where(builder.isTrue(root.get(BillingReason_.billingReasonIsactive)),builder.like(builder.coalesce(builder.lower(root.get(BillingReason_.billingReasonDescription)),"-1"),HUtil.Nz(commonAction.getBillingReason(),"-1").toLowerCase()));
		Integer id=0;
		List<Object> result = em.createQuery(criteriaQuery).getResultList();
		for(int i=0;i<result.size();i++){
			id=Integer.parseInt(result.get(0).toString());
		}

		if(id==0){
			builder = em.getCriteriaBuilder();
			criteriaQuery = builder.createQuery();
			root = criteriaQuery.from(BillingReason.class);
			criteriaQuery.select(builder.max(root.get(BillingReason_.billingReasonId)));
			result = em.createQuery(criteriaQuery).getResultList();
			for(int i=0;i<result.size();i++){
				System.out.println(">>>>???????????<<<<size============>"+result.size());
				id=Integer.parseInt(result.get(0).toString());
				id=id+1;
			}

			insertBillingReason(HUtil.Nz(commonAction.getBillingReason(),"-1"), id); 
		}
		return id;

	}

	public void insertBillingReason(String reason, Integer maxId) 
	{
		BillingReason billingReason = new BillingReason();
		billingReason.setBillingReasonId(maxId);
		billingReason.setBillingReasonDescription(reason);
		billingReason.setBillingReasonIsactive(true);
		billingReasonRepository.saveAndFlush(billingReason);
	}

	private Integer getPaymentCptId(String cpt) {

		Integer cptId=0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery();
		Root<Cpt> root = criteriaQuery.from(Cpt.class);
		criteriaQuery.select(root.get(Cpt_.cptId));
		criteriaQuery.where(builder.like(builder.lower(root.get(Cpt_.cptCptcode)),cpt.toLowerCase()));
		List<Object> result = em.createQuery(criteriaQuery).getResultList();
		for(int i=0;i<result.size();i++){
			cptId=Integer.parseInt(result.get(0).toString());
		}
		return cptId;

	}

	@Override
	public Integer getProblemTypeId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<ProblemType> root=criteriaQuery.from(ProblemType.class);
		criteriaQuery.select(root.get(ProblemType_.problemTypeId));
		criteriaQuery.where(builder.like(builder.coalesce(builder.lower(root.get(ProblemType_.problemTypeName)),"-1"),HUtil.Nz(commonAction.getProblemType(),"-1").toLowerCase()));
		List<Object> result=em.createQuery(criteriaQuery).getResultList();
		Integer id=0;
		for(int i=0;i<result.size();i++){
			id=Integer.parseInt(result.get(0).toString());
		}
		return id;
	}

	@Override
	public Short getArReasonId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub.
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=criteriaBuilder.createQuery();
		Root<Billinglookup> root=criteriaQuery.from(Billinglookup.class);
		criteriaQuery.select(root.get(Billinglookup_.blookIntid));
		criteriaQuery.where(criteriaBuilder.equal(root.get(Billinglookup_.blookGroup),123),criteriaBuilder.like(criteriaBuilder.coalesce(criteriaBuilder.lower(root.get(Billinglookup_.blookDesc)),"-1"),HUtil.Nz(commonAction.getDenialReason(),"-1").toLowerCase()));
		//criteriaQuery.where(criteriaBuilder.equal(root.get(Billinglookup_.blookGroup),123),criteriaBuilder.like(criteriaBuilder.lower(root.get(Billinglookup_.blookDesc)),HUtil.Nz(commonAction.getDenialReason(),"-1").toLowerCase()));
		List<Object> result=em.createQuery(criteriaQuery).getResultList();
		Short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			commonAction.setDenialReason(""+id);
		}
		return id;
	}

	@Override
	public Short getArTypeId(CommonActionBean commonAction) {
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<Billinglookup> root=criteriaQuery.from(Billinglookup.class);
		criteriaQuery.select(root.get(Billinglookup_.blookIntid));
		criteriaQuery.where(builder.equal(root.get(Billinglookup_.blookGroup),121),builder.like(builder.coalesce(builder.lower(root.get(Billinglookup_.blookDesc)),"-1"),HUtil.Nz(commonAction.getDenialType(),"-1").toLowerCase()));
		List<Object> result=em.createQuery(criteriaQuery).getResultList();
		short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			commonAction.setDenialType(""+id);
		}
		if(id==0){
			Integer blookId=0;
			Integer ID=0;
			builder=em.getCriteriaBuilder();
			criteriaQuery=builder.createQuery();
			root=criteriaQuery.from(Billinglookup.class);
			criteriaQuery.select(builder.max(root.get(Billinglookup_.blookIntid)));
			criteriaQuery.where(builder.equal(root.get(Billinglookup_.blookGroup), 121));
			result=em.createQuery(criteriaQuery).getResultList();
			for(int i=0;i<result.size();i++){
				ID=Integer.parseInt(result.get(0).toString());
				ID=ID+1;
			}
			criteriaQuery.select(builder.max(root.get(Billinglookup_.blookId)));
			result=em.createQuery(criteriaQuery).getResultList();
			for(int i=0;i<result.size();i++){
				blookId=Integer.parseInt(result.get(0).toString());
				blookId=blookId+1;
			}
			Short group=121;
			id=ID.shortValue();
			insertIntoBillingLookup(blookId,HUtil.Nz(commonAction.getDenialType(),"-1"),group,id);
		}
		return id;
	}

	/*@Override
	public Short getArCategoryId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<Billinglookup> root=criteriaQuery.from(Billinglookup.class);
		criteriaQuery.select(root.get(Billinglookup_.blookIntid));
		criteriaQuery.where(builder.equal(root.get(Billinglookup_.blookGroup),124),builder.like(builder.coalesce(builder.lower(root.get(Billinglookup_.blookDesc)),"-1"),HUtil.Nz(commonAction.getDenialCategory(), "-1").toLowerCase()));
		List<Object> result=em.createQuery(criteriaQuery).getResultList();
		short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			commonAction.setDenialCategory(""+id);
		}
		if(id==0){
			Integer blookId=0;
			Integer ID=0;
			builder=em.getCriteriaBuilder();
			 criteriaQuery=builder.createQuery();
			 root=criteriaQuery.from(Billinglookup.class);
			criteriaQuery.select(builder.max(root.get(Billinglookup_.blookIntid)));
			criteriaQuery.where(builder.equal(root.get(Billinglookup_.blookGroup), 124));
			result=em.createQuery(criteriaQuery).getResultList();
			for(int i=0;i<result.size();i++){
				ID=Integer.parseInt(result.get(0).toString());
				ID=ID+1;
			}
			criteriaQuery.select(builder.max(root.get(Billinglookup_.blookId)));
			for(int i=0;i<result.size();i++){
				blookId=Integer.parseInt(result.get(0).toString());
				blookId=blookId+1;
			}
			Short group=124;
			//id=Short.parseShort(""+ID);
			id=ID.shortValue();
			insertIntoBillingLookup(blookId,HUtil.Nz(commonAction.getDenialType(),"-1"),group,id);
		}
		return id;
	}*/

	public Integer getFollowupActionId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object> cq=builder.createQuery();
		Root<H222> root=cq.from(H222.class); 
		cq.select(root.get(H222_.h222001));
		cq.where(builder.like(builder.coalesce(builder.lower(root.get(H222_.h222002)),"-1"), HUtil.Nz(commonAction.getNextFollowupAction(),"-1").toLowerCase()));
		//cq.where(builder.like(builder.lower(root.get(H222_.h222002)),HUtil.Nz(commonAction.getNextFollowupAction(),"-1").toLowerCase()));
		List<Object> result=em.createQuery(cq).setMaxResults(1).getResultList();
		Integer id=0;
		for(int i=0;i<result.size();i++){
			id=Integer.parseInt(result.get(0).toString());
			//commonAction.setProblemType(""+id);
		}
		return id;
	}

	public Integer getProblemTypeIdDesc(CommonActionBean commonAction) 
	{
		Integer id=0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
		Root<ArProblemList> rt = criteriaQuery.from(ArProblemList.class);
		criteriaQuery.select(rt.get(ArProblemList_.arProblemListId));
		criteriaQuery.where(builder.like(builder.coalesce(builder.lower(rt.get(ArProblemList_.arProblemListProblem)),"-1"), HUtil.Nz(commonAction.getProblemType(),"-1" ).toLowerCase()));
		List<Integer> resultList = em.createQuery(criteriaQuery).getResultList();
		if(resultList.size() > 0)
			id = resultList.get(0);
		
		if(id==0)
		{
			CriteriaBuilder maxBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Integer> maxCriteriaQuery = maxBuilder.createQuery(Integer.class);
			Root<ArProblemList> maxRoot = maxCriteriaQuery.from(ArProblemList.class);
			maxCriteriaQuery.select(maxBuilder.max(maxRoot.get(ArProblemList_.arProblemListId)));
			id = em.createQuery(maxCriteriaQuery).getResultList().get(0);
			id = id + 1;
			System.out.println("Problem id-->"+id+" Problem desc--->"+HUtil.Nz(commonAction.getProblemType(),"-1")+" *****going to insert..");
			
			insertIntoArProblemList(id,HUtil.Nz(commonAction.getProblemType(),"-1"));
		}
		return id;
	}

	public void insertIntoArProblemList(Integer arProblemListId,String arProblemListProblem) {

		ArProblemList  arProblemList = new ArProblemList();

		System.out.println("Creating Problem Type IDDDD");
		arProblemList.setArProblemListId(arProblemListId);
		arProblemList.setArProblemListProblem(arProblemListProblem);
		System.out.println("Creating Problem Type=====>>>"+arProblemListId+"\n\n\n====>arProblemListProblem"+arProblemListProblem);
		arProblemListRepository.saveAndFlush(arProblemList);
	}

	public void insertIntoBillingLookup(Integer maxId,String description,Short groupId,Short blookInId){

		Billinglookup billinglookup = new Billinglookup();

		billinglookup.setBlookId(maxId);
		billinglookup.setBlookGroup(groupId);
		billinglookup.setBlookDesc(description);
		billinglookup.setBlookIntid(blookInId);
		billinglookup.setBlookIsactive(true);
		billinglookup.setBlookName(description);
		billinglookup.setBillinglookupKeycode("");
		billinglookup.setBlookOrderby(Short.parseShort("1"));
		billinglookup.setBlookSubgroup(Short.parseShort("1"));
		billinglookup.setBlookExtra("");
		billinglookupRepository.saveAndFlush(billinglookup);
	}
	
	public BigInteger getInsurenceId(String patientInsId,Integer type){

		BigInteger insId= new BigInteger("0");
		;
		String qry="select patient_ins_detail_id from patient_ins_detail where patient_ins_detail_patientinsuranceid ='"+patientInsId+"' and patient_ins_detail_instype="+type+" limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();

		for(int i=0;i<result.size();i++){
			insId=(BigInteger)result.get(0);
			//commonAction.setProblemType(""+id);
		}
		return insId;
	}
	
	public Boolean checkIsRecent(CommonActionBean commonActionBean) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaUpdate<AdActionhistory> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(AdActionhistory.class);
		Root<AdActionhistory> rt = criteriaUpdate.from(AdActionhistory.class);
		try 
		{	
			criteriaUpdate.set("adAhIsrecent", false);
			criteriaUpdate.where(criteriaBuilder.equal(rt.get(AdActionhistory_.adAhServiceid), commonActionBean.getServiceId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public Date convertToDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlStartDate =null;
		try{
			java.util.Date d = df.parse(date);
			sqlStartDate =  new java.sql.Date(d.getTime()); 
			System.out.println(sqlStartDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sqlStartDate;
	}
	
	public Timestamp convertToTimestamp(String timeStampString)
	{
		Timestamp resultTimestamp = null;
		try 
		{
			if(timeStampString.contains("/"))
			{
				java.util.Date timeStamp1 = new SimpleDateFormat("MM/dd/yyyy").parse(timeStampString);
				String tsString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp1);
				resultTimestamp = Timestamp.valueOf(tsString);
			}
			else
			{
				java.util.Date timeStamp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStampString);
				String tsString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp1);
				resultTimestamp = Timestamp.valueOf(tsString);
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return resultTimestamp;
	}
	
}