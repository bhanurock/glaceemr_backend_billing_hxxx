package com.glenwood.glaceemr.server.application.services.Denial;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.ClaimInfoBean;
import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.Bean.ManagerInfoBean;
import com.glenwood.glaceemr.server.application.Bean.PatientInsuranceInfoBean;
import com.glenwood.glaceemr.server.application.models.AccountType;
import com.glenwood.glaceemr.server.application.models.AccountType_;
import com.glenwood.glaceemr.server.application.models.AdActionhistory;
import com.glenwood.glaceemr.server.application.models.AdActionhistory_;
import com.glenwood.glaceemr.server.application.models.ArProblemList;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails;
import com.glenwood.glaceemr.server.application.models.AssociateServiceDetails_;
import com.glenwood.glaceemr.server.application.models.BillingReason;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.H172;
import com.glenwood.glaceemr.server.application.models.H172_;
import com.glenwood.glaceemr.server.application.models.H222;
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
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.ProblemReport;
import com.glenwood.glaceemr.server.application.models.ProblemReport_;
import com.glenwood.glaceemr.server.application.models.ServiceBalances;
import com.glenwood.glaceemr.server.application.models.ServiceBalances_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;
import com.glenwood.glaceemr.server.application.repositories.AdActionhistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.ArProblemListRepository;
import com.glenwood.glaceemr.server.application.repositories.AssociateServiceDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.BillingReasonRepository;
import com.glenwood.glaceemr.server.application.repositories.BillinglookupRepository;
import com.glenwood.glaceemr.server.application.repositories.H222Repository;
import com.glenwood.glaceemr.server.application.repositories.NonServiceDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PrimarykeyGeneratorRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemReportRepository;
import com.glenwood.glaceemr.server.application.repositories.ServiceDetailRepository;
import com.glenwood.glaceemr.server.application.specifications.ChargesSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.Utilities;

@Service
@Transactional
public class DenialServiceImpl implements DenialService 
{

	@Autowired
	PatientRegistrationRepository patregRepo;

	@Autowired
	AdActionhistoryRepository adActionHistoryRepository;

	@Autowired
	NonServiceDetailsRepository nonServiceDetailRepository;

	@Autowired
	ProblemReportRepository problemReportRepository;
	
	@Autowired
	BillingReasonRepository billingReasonRepository;
	
	@Autowired
	H222Repository h222Repository;
	
	@Autowired
	PrimarykeyGeneratorRepository primarykeyGeneratorRepository;
	
	@Autowired
	BillinglookupRepository billinglookupRepository;
	
	@Autowired
	ArProblemListRepository arProblemListRepository;
	
	@Autowired
	ServiceDetailRepository  serviceDetailRepository;
	
	@Autowired
	AssociateServiceDetailsRepository associateServiceDetailsRepository;

	@Autowired
	EntityManager em;
	
	@Autowired
	ObjectMapper objectMapper;

	String actionDesc;
	String currentStatus;
	String ticketNo="";
	public static Integer ref = 0;

	@Override
	public List<ServiceDetail> serviceDetailId(String serviceDetailId) {
		return null;
	}


	@Override
	public List<DenialBean> getAllDenial(String fromDate,String toDate)
	{
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<DenialBean> cq = builder.createQuery(DenialBean.class);
			Root<ServiceDetail> root = cq.from(ServiceDetail.class);
			Join<ServiceDetail, PatientRegistration> patientReg=root.join(ServiceDetail_.patientRegistration,JoinType.INNER);
			Join<PatientRegistration, AccountType> accountType = patientReg.join(PatientRegistration_.accType, JoinType.LEFT);
			Join<ServiceDetail, PatientInsDetail> primaryIns=root.join(ServiceDetail_.patientInsDetail,JoinType.LEFT);
			Join<ServiceDetail, PatientInsDetail> secondIns=root.join(ServiceDetail_.secInsDetail,JoinType.LEFT);
			Join<PatientInsDetail, InsCompAddr> insCompAddr1=primaryIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
			Join<PatientInsDetail, InsCompAddr> insCompAddr2=secondIns.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
			Join<InsCompAddr, InsCompany> insCompany1=insCompAddr1.join(InsCompAddr_.insCompany,JoinType.LEFT);
			Join<InsCompAddr, InsCompany> insCompany2=insCompAddr2.join(InsCompAddr_.insCompany,JoinType.LEFT);
			Join<ServiceDetail, EmployeeProfile> billingDoctor=root.join(ServiceDetail_.bdoctors,JoinType.LEFT);
			Join<ServiceDetail, EmployeeProfile> servicegDoctor=root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
			Join<ServiceDetail, NonServiceDetails> serviceDetailJoin=root.join(ServiceDetail_.nonService,JoinType.INNER);
			Join<ServiceDetail, Cpt> cpt=root.join(ServiceDetail_.cpt, JoinType.INNER);
			Join<NonServiceDetails, Cpt> paymentCpt=serviceDetailJoin.join(NonServiceDetails_.cpt, JoinType.INNER);
			Join<NonServiceDetails, AdActionhistory> adAction=serviceDetailJoin.join(NonServiceDetails_.adActionHistory, JoinType.LEFT);
			Join<ServiceDetail, AssociateServiceDetails> associateDetails=root.join(ServiceDetail_.associateDetails,JoinType.LEFT);
			Join<AssociateServiceDetails, ArProblemList> arProblem=associateDetails.join(AssociateServiceDetails_.arProblem, JoinType.LEFT);
			Join<ServiceDetail, PosTable> posDetails=root.join(ServiceDetail_.posTable,JoinType.LEFT); 
			Join<ServiceDetail, Billinglookup> billingLookup=root.join(ServiceDetail_.bllok,JoinType.LEFT);
			Join<ServiceDetail, H172> h1=root.join(ServiceDetail_.h172,JoinType.LEFT);
			Join<ServiceDetail, ServiceBalances> serviceBalance=root.join(ServiceDetail_.serviceBalances,JoinType.LEFT);
			Join<ServiceDetail, PosType> posTypeJoin = root.join(ServiceDetail_.posType, JoinType.LEFT);
			
			List<Predicate> predicateList = new ArrayList<Predicate>();
			predicateList.add(builder.equal(paymentCpt.get(Cpt_.cptCptcode),"DENIAL"));
			predicateList.add(builder.notEqual(root.get(ServiceDetail_.serviceDetailSubmitStatus), "X"));
			predicateList.add(builder.isNull(adAction.get(AdActionhistory_.adAhActionid)));
			
			if(!fromDate.equals("") && !toDate.equals(""))
				predicateList.add(builder.between(serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailDateOfPosting),convertToDate(fromDate),convertToDate(toDate)));
			
			Predicate[] predicateArray = new Predicate[predicateList.size()];
			predicateList.toArray(predicateArray);
			
			cq.distinct(true);
			cq.select(
					builder.construct(DenialBean.class,
							patientReg.get(PatientRegistration_.patientRegistrationId),
							root.get(ServiceDetail_.serviceDetailId),
							root.get(ServiceDetail_.serviceDetailSubmitStatus),
							patientReg.get(PatientRegistration_.patientRegistrationAccountno),
							patientReg.get(PatientRegistration_.patientRegistrationLastName),
							patientReg.get(PatientRegistration_.patientRegistrationFirstName),
							patientReg.get(PatientRegistration_.patientRegistrationMaidenName),
							patientReg.get(PatientRegistration_.patientRegistrationState),
							serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailRemarkCode),
							serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailComments),
							builder.function("to_char", String.class,serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailDateOfPosting),builder.literal("mm/dd/yyyy")),//serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailDateOfPosting),
							builder.function("count", Long.class, root.get(ServiceDetail_.serviceDetailId)),
							root.get(ServiceDetail_.serviceDetailSubmitStatus),
							builder.function("to_char", String.class,root.get(ServiceDetail_.serviceDetailDos),builder.literal("mm/dd/yyyy")),//root.get(ServiceDetail_.serviceDetailDos),
							root.get(ServiceDetail_.serviceDetailCharges),
							root.get(ServiceDetail_.serviceDetailCopay),
							insCompany1.get(InsCompany_.insCompanyName),
							primaryIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
							insCompany2.get(InsCompany_.insCompanyName),
							secondIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
							posDetails.get(PosTable_.posTablePlaceOfService),
							posDetails.get(PosTable_.posTableFacilityComments),
							builder.function("count", Long.class, h1.get(H172_.h172005)),
							builder.function("count", Long.class, h1.get(H172_.h172005)),
							builder.function("count", Long.class, paymentCpt.get(Cpt_.cptCptcode)),
							serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailId),
							cpt.get(Cpt_.cptCptcode),
							root.get(ServiceDetail_.serviceDetailModifier1),
							root.get(ServiceDetail_.serviceDetailDx1),
							root.get(ServiceDetail_.serviceDetailDx2),
							root.get(ServiceDetail_.serviceDetailDx3),
							root.get(ServiceDetail_.serviceDetailDx4),
							billingDoctor.get(EmployeeProfile_.empProfileFullname),
							servicegDoctor.get(EmployeeProfile_.empProfileFullname),
							associateDetails.get(AssociateServiceDetails_.associateServiceDetailMedNotes),
							adAction.get(AdActionhistory_.adAhActiondescription),
							adAction.get(AdActionhistory_.adAhNotes),
							builder.function("to_char", String.class,adAction.get(AdActionhistory_.adAhActiontakendate),builder.literal("mm/dd/yyyy")),//adAction.get(AdActionhistory_.adAhActiontakendate),
							serviceBalance.get(ServiceBalances_.primaryInsurancePayment),
							h1.get(H172_.claimReferenceClaimid),
							serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailReference),
							cpt.get(Cpt_.cptDescription),
							root.get(ServiceDetail_.serviceDetailUnit),
							root.get(ServiceDetail_.serviceDetailUnknown2),
							serviceBalance.get(ServiceBalances_.secondaryInsurancePayment),
							serviceBalance.get(ServiceBalances_.insuranceBalance),
							accountType.get(AccountType_.accType),
							posTypeJoin.get(PosType_.posTypeUniqueId)));
			cq.where(predicateArray);
			cq.groupBy(patientReg.get(PatientRegistration_.patientRegistrationId),
					root.get(ServiceDetail_.serviceDetailId),
					root.get(ServiceDetail_.serviceDetailSubmitStatus),
					patientReg.get(PatientRegistration_.patientRegistrationAccountno),
					patientReg.get(PatientRegistration_.patientRegistrationLastName),
					patientReg.get(PatientRegistration_.patientRegistrationFirstName),
					patientReg.get(PatientRegistration_.patientRegistrationMaidenName),
					patientReg.get(PatientRegistration_.patientRegistrationState),
					serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailRemarkCode),
					serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailComments),
					serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailDateOfPosting),
					root.get(ServiceDetail_.serviceDetailSubmitStatus),
					root.get(ServiceDetail_.serviceDetailDos),
					root.get(ServiceDetail_.serviceDetailCharges),
					root.get(ServiceDetail_.serviceDetailCopay),
					insCompany1.get(InsCompany_.insCompanyName),
					primaryIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
					insCompany2.get(InsCompany_.insCompanyName),
					secondIns.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
					posDetails.get(PosTable_.posTablePlaceOfService),
					posDetails.get(PosTable_.posTableFacilityComments),
					serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailId),
					cpt.get(Cpt_.cptCptcode),
					root.get(ServiceDetail_.serviceDetailModifier1),
					root.get(ServiceDetail_.serviceDetailDx1),
					root.get(ServiceDetail_.serviceDetailDx2),
					root.get(ServiceDetail_.serviceDetailDx3),
					root.get(ServiceDetail_.serviceDetailDx4),
					billingDoctor.get(EmployeeProfile_.empProfileFullname),
					servicegDoctor.get(EmployeeProfile_.empProfileFullname),
					associateDetails.get(AssociateServiceDetails_.associateServiceDetailMedNotes),
					adAction.get(AdActionhistory_.adAhActiondescription),
					adAction.get(AdActionhistory_.adAhNotes),
					adAction.get(AdActionhistory_.adAhActiontakendate),
					h1.get(H172_.claimReferenceClaimid),
					cpt.get(Cpt_.cptDescription),
					root.get(ServiceDetail_.serviceDetailUnit),
					root.get(ServiceDetail_.serviceDetailUnknown2),
					serviceBalance.get(ServiceBalances_.insuranceBalance),
					serviceBalance.get(ServiceBalances_.primaryInsurancePayment),
					serviceBalance.get(ServiceBalances_.secondaryInsurancePayment),
					accountType.get(AccountType_.accType),
					posTypeJoin.get(PosType_.posTypeUniqueId));
			
			List<DenialBean> resultList = em.createQuery(cq).getResultList();
			System.out.println("Size>>>>>>>>"+resultList.size());
			for(int i=0; i<resultList.size(); i++)
			{
				CriteriaBuilder maxBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Object> maxCq = builder.createQuery(Object.class);
				Root<AdActionhistory> maxRoot = maxCq.from(AdActionhistory.class);
				maxCq.select(maxBuilder.function("max", Integer.class, maxBuilder.coalesce(maxRoot.get(AdActionhistory_.adAhId), 0)));
				maxCq.where(maxBuilder.equal(maxRoot.get(AdActionhistory_.adAhServiceid), resultList.get(i).getServiceId()));
				Integer maxAdId = Integer.parseInt(HUtil.Nz(em.createQuery(maxCq).getSingleResult(), "-1"));
				if(maxAdId != -1)
				{
					maxCq.multiselect(maxBuilder.coalesce(maxRoot.get(AdActionhistory_.adAhActionid), 0),
							maxBuilder.coalesce(maxRoot.get(AdActionhistory_.adAhArcategoryid), 0),
							maxBuilder.coalesce(maxRoot.get(AdActionhistory_.adAhProblemid), 0));
					maxCq.where(maxBuilder.equal(maxRoot.get(AdActionhistory_.adAhId), maxAdId));
					
					Object resultSet = em.createQuery(maxCq).getSingleResult();
					if(resultSet != null)
					{
						JSONArray resultJson = new JSONArray(objectMapper.writeValueAsString(resultSet));
						resultList.get(i).setLastActionTaken(Integer.parseInt(HUtil.Nz(resultJson.get(0).toString(), "-1")));
						resultList.get(i).setArCategoryId(Integer.parseInt(HUtil.Nz(resultJson.get(1).toString(), "-1")));
						resultList.get(i).setProblemId(Integer.parseInt(HUtil.Nz(resultJson.get(2).toString(), "-1")));
					}
				}
			}
			return resultList;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
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
	
	@Override
	public List<BulkDenialBean> getAllCommentDenial(String fromDate,String toDate){
		List<BulkDenialBean> result=new ArrayList<BulkDenialBean>();
		try {
			
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<BulkDenialBean> cq = builder.createQuery(BulkDenialBean.class);
			Root<ServiceDetail> root = cq.from(ServiceDetail.class);
			Join<ServiceDetail, PatientRegistration> patientReg=root.join(ServiceDetail_.patientRegistration,JoinType.INNER);
			Join<ServiceDetail, NonServiceDetails> serviceDetailJoin=root.join(ServiceDetail_.nonService,JoinType.INNER);
			Join<ServiceDetail, Cpt> cpt=root.join(ServiceDetail_.cpt, JoinType.INNER);
			Join<NonServiceDetails, Cpt> paymentCpt=serviceDetailJoin.join(NonServiceDetails_.cpt, JoinType.INNER);
			Join<NonServiceDetails, AdActionhistory> adAction=serviceDetailJoin.join(NonServiceDetails_.adActionHistory, JoinType.LEFT);
			
			cq.distinct(true);
			cq.select(
					builder.construct(BulkDenialBean.class,
							serviceDetailJoin.get(NonServiceDetails_.nonServiceDetailId),
							adAction.get(AdActionhistory_.adAhActionid),
							patientReg.get(PatientRegistration_.patientRegistrationAccountno),
							cpt.get(Cpt_.cptCptcode),
							root.get(ServiceDetail_.serviceDetailDos),
							root.get(ServiceDetail_.serviceDetailId)));
			cq.where(builder.equal(paymentCpt.get(Cpt_.cptCptcode),"DENIAL"),
					builder.notEqual(root.get(ServiceDetail_.serviceDetailSubmitStatus), "X"),
					builder.isNull(adAction.get(AdActionhistory_.adAhActionid)),
					builder.or(root.get(ServiceDetail_.serviceDetailSubmitStatus).in("Y","H","K","0"),builder.and(builder.equal(root.get(ServiceDetail_.serviceDetailCharges),0),builder.equal(root.get(ServiceDetail_.serviceDetailCopay),0))),
					builder.between(root.get(ServiceDetail_.serviceDetailDos),convertToDate(fromDate),convertToDate(toDate))
					);
			
			/*String qry="select non_service_detail_id as id,ad_ah_denialid as denialid,patient_registration_accountno as accountno,c1.cpt_cptcode as "
					+ "cpt,service_detail_dos as dos from non_service_detail inner join patient_registration on "
					+ "patient_registration_id=non_service_detail_patient_id inner join service_detail on service_detail_id="
					+ "non_service_detail_service_id inner join cpt c1 on c1.cpt_id =service_detail_cptid inner join cpt c2 on "
					+ "non_service_detail_payment_cpt_id=c2.cpt_id left join ad_actionhistory on ad_ah_denialid=non_service_detail_id "
					+ "where c2.cpt_cptcode ilike 'DENIAL' and ad_ah_denialid is null and c1.cpt_cptcode in('PTDEMO','MEDNT','00COM','NOCPT',"
					+ "'PTDEMO') and service_detail_dos between '"+fromDate+"' and '"+toDate+"';";*/
			List<BulkDenialBean> tmp = em.createQuery(cq).getResultList();
			
			System.out.println("Result====>"+result.size());
			System.out.println("Object ====>"+objectMapper.writeValueAsString(result) );
			for(int i=0;i<tmp.size();i++){
				BulkDenialBean tmpBean=(BulkDenialBean) tmp.get(i);
				result.add(tmpBean);
			}
			System.out.println("Result====>"+result.size());
			System.out.println("Object ====>"+objectMapper.writeValueAsString(result) );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public CommonResponseBean billToPatAction(CommonActionBean billToPatBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "T");
			criteriaUpdate.set("serviceDetailBillingReason", getBillingReasonId(billToPatBean));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), billToPatBean.getServiceId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+billToPatBean.getActionDescription()+"; Billing Reason: "+billToPatBean.getBillingReason();
			currentStatus = "Action Type: "+billToPatBean.getActionDescription();
			adActionHistorySave(actionDesc, currentStatus, billToPatBean);
			return setResponse(1, billToPatBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, billToPatBean, e);
		}
	}
	
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

	/**
	 * @author Govind
	 */
	@Override
	public CommonResponseBean changeCptAction(CommonActionBean changeCPTBean) {
		try
		{
			System.out.println(">came>");
			int serviceId = changeCPTBean.getServiceId();
			int cptId=getCPTId(changeCPTBean.getNewCptCode());
			System.out.println(">12345>");
			double newCptCharges = changeCPTBean.getNewCptCharges(); 
			ServiceDetail servicedetail=getServiceDetailsForOldCPT(serviceId,cptId,newCptCharges);
			System.out.println("ServiceId >>>"+servicedetail.getServiceDetailId());
			List<NonServiceDetails> result = nonServiceDetailRepository.findAll(DenialServiceImpl.getNonServiceDetail(serviceId, 4));
			CommonActionBean writeOffBean = setwriteOffBean(servicedetail, changeCPTBean);
			writeoffAction(writeOffBean);
			entityCreationServiceDetail(servicedetail,cptId,newCptCharges);
			System.out.println("ServiceId >>>"+servicedetail.getServiceDetailId());
			saveNonServiceDetailRecordForOldAndNewService(result);
			actionDesc = "Action Type: "+changeCPTBean.getActionDescription();
			currentStatus = "Action Type: "+changeCPTBean.getActionDescription();
			adActionHistorySave(actionDesc, currentStatus, changeCPTBean);
			return setResponse(1, changeCPTBean, null);

		}catch(Exception e){
			e.printStackTrace();
			return setResponse(2, changeCPTBean, e);
		}

	}

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

	@Override
	public CommonResponseBean changeDxAction(CommonActionBean changeDxBean) {
		// TODO Auto-generated method stub
		//Update Query
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

			actionDesc = "Action Type:"+changeSubmitToStatusBean.getActionDescription()+" Comments:"+changeSubmitToStatusBean.getNotes();
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

	@Override
	public CommonResponseBean markAsApppealAction(CommonActionBean markAsAppealBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailModifiedby", markAsAppealBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.set("serviceDetailArStatus", getArCategoryId("Mark As Appeal"));
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsAppealBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+markAsAppealBean.getActionDescription()+"; Comments: "+markAsAppealBean.getNotes();
			currentStatus = "Action Type: "+markAsAppealBean.getActionDescription()+"; Notes: "+markAsAppealBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsAppealBean);
			return setResponse(1, markAsAppealBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsAppealBean, e);
		}
	}
	
	@Override
	public CommonResponseBean markAsBadDebtAction(CommonActionBean markAsBadDebtBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", markAsBadDebtBean.getResubmitTo());
			criteriaUpdate.set("serviceDetailModifiedby",markAsBadDebtBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(markAsBadDebtBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+markAsBadDebtBean.getActionDescription()+" Comments:"+markAsBadDebtBean.getNotes();
			currentStatus = "Action Type: "+markAsBadDebtBean.getActionDescription()+"; Notes: "+markAsBadDebtBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,markAsBadDebtBean);
			
			return setResponse(1, markAsBadDebtBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsBadDebtBean, e);
		}
	}

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
			criteriaUpdate.set("serviceDetailArStatus", "-1");
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

	@Override
	public CommonResponseBean markAsDuplicateAction(CommonActionBean markAsDuplicateBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus", 8);
			criteriaUpdate.set("serviceDetailModifiedby", markAsDuplicateBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate", new java.util.Date());
			criteriaUpdate.set("serviceDetailArStatus", "-1");
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
			System.out.println("servicedetailId>>>"+markAsFaxBean.getServiceId());
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+markAsFaxBean.getActionDescription()+" Comments:"+markAsFaxBean.getNotes();
			currentStatus = "Action Type: "+markAsFaxBean.getActionDescription()+"; Notes: "+markAsFaxBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,markAsFaxBean);
			return setResponse(1, markAsFaxBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsFaxBean, e);
		}
	}

	@Override
	public CommonResponseBean markAsFullySettledAction(CommonActionBean markAsFullySettledBean) {
		// TODO Auto-generated method stub
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

	@Override
	public CommonResponseBean markAsOnHoldAction(CommonActionBean markAsOnHoldBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", "A");
			criteriaUpdate.set("serviceDetailModifiedby", markAsOnHoldBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.set("serviceDetailArStatus", "-1");
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsOnHoldBean.getServiceId()));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+markAsOnHoldBean.getActionDescription()+"; Problem Type: "+markAsOnHoldBean.getProblemType()+"; Comments: "+markAsOnHoldBean.getNotes();
			currentStatus = "Action Type: "+markAsOnHoldBean.getActionDescription()+"; Notes: "+markAsOnHoldBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsOnHoldBean);
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
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailArStatus", 7);
			criteriaUpdate.set("serviceDetailModifiedby", markAsUncollectableBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new java.util.Date());
			criteriaUpdate.set("serviceDetailArStatus", getArCategoryId("UnCollectable"));
			criteriaUpdate.where(builder.equal(root.get("serviceDetailId"), markAsUncollectableBean.getServiceId()));	
			criteriaUpdate.set("serviceDetailSubmitStatus", "Y");
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+markAsUncollectableBean.getActionDescription()+"; Reason: "+markAsUncollectableBean.getReason()+"; Comments: "+markAsUncollectableBean.getNotes();
			currentStatus = "Action Type: "+markAsUncollectableBean.getActionDescription()+"; Notes: "+markAsUncollectableBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus, markAsUncollectableBean);
			return setResponse(1, markAsUncollectableBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsUncollectableBean, e);
		}
	}
	
	@Override
	public CommonResponseBean markAsWebAction(CommonActionBean markAsWebBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", markAsWebBean.getSubmitStatus());
			criteriaUpdate.set("serviceDetailModifiedby",markAsWebBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(markAsWebBean.getServiceId())));			
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+markAsWebBean.getActionDescription()+" Comments:"+markAsWebBean.getNotes();
			currentStatus = "Action Type: "+markAsWebBean.getActionDescription()+"; Notes: "+markAsWebBean.getNotes();
			adActionHistorySave(actionDesc,currentStatus, markAsWebBean);
			return setResponse(1, markAsWebBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, markAsWebBean, e);
		}
	}
	
	@Override
	public CommonResponseBean readyForOtherInsuranceAction(CommonActionBean readyForOtherInsuranceBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set("serviceDetailSubmitStatus", readyForOtherInsuranceBean.getResubmitTo());
			criteriaUpdate.set("serviceDetailModifiedby",readyForOtherInsuranceBean.getModifiedBy());
			criteriaUpdate.set("serviceDetailModifieddate",new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailIspaperclaim", -1);
			criteriaUpdate.where((root.get("serviceDetailId").in(readyForOtherInsuranceBean.getServiceId())));		
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type:"+readyForOtherInsuranceBean.getActionDescription()+" Comments:"+readyForOtherInsuranceBean.getNotes();
			currentStatus = "Action Type: "+readyForOtherInsuranceBean.getActionDescription()+"; Notes: "+readyForOtherInsuranceBean.getNotes();
			adActionHistorySave(actionDesc, currentStatus,readyForOtherInsuranceBean);
			return setResponse(1, readyForOtherInsuranceBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, readyForOtherInsuranceBean, e);
		}
	}

	@Override
	public CommonResponseBean reportAProblem(CommonActionBean reportAProblemBean) {
		// TODO Auto-generated method stub
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

	@Override
	public CommonResponseBean toBeCalledAction(CommonActionBean toBeCalledBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailArStatus), String.valueOf(11));
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifiedby), toBeCalledBean.getModifiedBy());
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifieddate),new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailArStatus", getArCategoryId("To Be Called"));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), toBeCalledBean.getServiceId()));
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+toBeCalledBean.getActionDescription()+"; Problem: "+toBeCalledBean.getProblemNotes()+"; Next Followup Date: "+toBeCalledBean.getNextFollowupDate();
			currentStatus = "Action Type: "+toBeCalledBean.getActionDescription()+"; Notes: "+toBeCalledBean.getProblemNotes();
			adActionHistorySave(actionDesc, currentStatus, toBeCalledBean);
			return setResponse(1, toBeCalledBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, toBeCalledBean, e);
		}
	}

	@Override
	public CommonResponseBean toBeCalledCompletedAction(CommonActionBean toBeCalledCompletedBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailArStatus), String.valueOf(13));
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifiedby), toBeCalledCompletedBean.getModifiedBy());
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifieddate),new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.set("serviceDetailArStatus", getArCategoryId("To Be Called - Completed"));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), toBeCalledCompletedBean.getServiceId()));
			em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+toBeCalledCompletedBean.getActionDescription()+"; Solution: "+toBeCalledCompletedBean.getProblemNotes();
			currentStatus = "Action Type: "+toBeCalledCompletedBean.getActionDescription()+"; Notes: "+toBeCalledCompletedBean.getProblemNotes();
			adActionHistorySave(actionDesc, currentStatus, toBeCalledCompletedBean);
			return setResponse(1, toBeCalledCompletedBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, toBeCalledCompletedBean, e);
		}
	}

	@Override
	public CommonResponseBean writeoffAction(CommonActionBean writeoffBean) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ServiceDetail> criteriaUpdate = builder.createCriteriaUpdate(ServiceDetail.class);
		NonServiceDetails nonServiceDetail = new NonServiceDetails();
		Root<ServiceDetail> root = criteriaUpdate.from(ServiceDetail.class);
		try 
		{
			if(writeoffBean.getInsBal().equals(writeoffBean.getWriteOffAmount()))
			{
				criteriaUpdate.set("serviceDetailSubmitStatus", "X");
			}
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifiedby), writeoffBean.getModifiedBy());
			criteriaUpdate.set(root.get(ServiceDetail_.serviceDetailModifieddate),new Timestamp(new java.util.Date().getTime()));
			criteriaUpdate.where(builder.equal(root.get(ServiceDetail_.serviceDetailId), writeoffBean.getServiceId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();

			actionDesc = "Action Type: "+writeoffBean.getActionDescription()+"; Notes: "+writeoffBean.getNotes();
			currentStatus = "Action Type: "+writeoffBean.getActionDescription()+"; Notes: "+writeoffBean.getNotes();
			
			nonServiceDetail.setNonServiceDetailId(Long.parseLong("-1"));
			nonServiceDetail.setNonServiceDetailPatientId(Long.parseLong(writeoffBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailServiceId(Long.parseLong(writeoffBean.getServiceId().toString()));
			nonServiceDetail.setNonServiceDetailDateOfPosting(new Date(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailAmount(writeoffBean.getWriteOffAmount());
			nonServiceDetail.setNonServiceDetailPaymentCptId(getPaymentCptId("0000W"));
			nonServiceDetail.setNonServiceDetailComments(actionDesc);
			nonServiceDetail.setNonServiceDetailPaymentMethod(1);
			nonServiceDetail.setNonServiceDetailDeductible(0.00);
			nonServiceDetail.setNonServiceDetailCoins(0.00);
			nonServiceDetail.setNonServiceDetailDatePaid(new Date(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailRiskWithheld(0.00);
			nonServiceDetail.setNonServiceDetailPlacedBy(writeoffBean.getModifiedBy());	
			nonServiceDetail.setNonServiceDetailLastModifiedBy(writeoffBean.getModifiedBy());
			nonServiceDetail.setNonServiceDetailLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
			nonServiceDetail.setNonServiceDetailPayerId(Long.parseLong(writeoffBean.getPatientId().toString()));
			nonServiceDetail.setNonServiceDetailCheckId((long)-1);
			nonServiceDetail.setH555555(1);
			nonServiceDetail.setNonServiceDetailIsValid(false);
			nonServiceDetail.setNonServiceDetailRefundStatus((short)-1);
			nonServiceDetail.setNonServiceDetailReverseId((long)-1);
			nonServiceDetail.setNonServiceDetailEntryType(Short.parseShort(String.valueOf(21)));
			nonServiceDetail.setNonServiceDetailSource("Billing Central::"+writeoffBean.getModifiedBy());
			//nonServiceDetail.setNonServiceDetailReceiptDate(convertToDate(writeoffBean.getReceiptDate()));
			nonServiceDetail.setNonServiceDetailPaidBy(4);
			nonServiceDetailRepository.saveAndFlush(nonServiceDetail);

			adActionHistorySave(actionDesc, currentStatus, writeoffBean);
			return setResponse(1, writeoffBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, writeoffBean, e);
		}
	}
	
	public Double serviceBalance(CommonActionBean commonAction)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery(Object.class);
		Root<ServiceBalances> root = criteriaQuery.from(ServiceBalances.class);

		TypedQuery<Object> typedQuery = em.createQuery(criteriaQuery
				.select(root.get(ServiceBalances_.serviceBalance ))
				.where(builder.equal(root.get(ServiceBalances_.serviceId), commonAction.getServiceId())));

		List<Object> resultList = typedQuery.getResultList();

		Double result=0.00;
		for(int i=0;i<resultList.size();i++){
			result=(Double)resultList.get(0);
		}
		
		return result;
	}

	public void adActionHistorySave(String actionDesc, String currentStatus, CommonActionBean commonActionBean) throws ParseException
	{
		AdActionhistory actionHistory = new AdActionhistory();
		Timestamp currentTime=null;
		if(commonActionBean.getNextFollowupDate()!=null){
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dateofPosting = (java.util.Date)formatter.parse(commonActionBean.getNextFollowupDate());
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
			currentTime = new Timestamp(dateofPosting.getTime());
		}

		actionHistory.setAdAhId(-1);
		actionHistory.setAdAhServiceid((commonActionBean.getServiceId()));
		actionHistory.setAdAhServicestatuscode(commonActionBean.getServiceStatusCode());
		actionHistory.setAdAhActionid(commonActionBean.getActionId());
		actionHistory.setAdAhActiontakendate(new Timestamp(new java.util.Date().getTime()));
		actionHistory.setAdAhActiontakenby(commonActionBean.getModifiedBy());
		actionHistory.setAdAhNextfollowupactiondate(currentTime);
		actionHistory.setAdAhNextfollowupactionid(getFollowupActionId(commonActionBean));
		if(commonActionBean.getProblemType()!=null){
			actionHistory.setAdAhProblemid(getProblemTypeIdDesc(commonActionBean));
		}
		actionHistory.setAdAhProblemid(getProblemTypeId(commonActionBean));
		actionHistory.setAdAhIsrecent(commonActionBean.getIsRecent());
		actionHistory.setAdAhActionreason(Integer.parseInt(getDenialReasonId(commonActionBean)+""));
		actionHistory.setAdAhActiondescription(actionDesc);
		actionHistory.setAdAhModuleid(2);
		actionHistory.setAdAhDenialid(commonActionBean.getNonServiceId());
		actionHistory.setAdAhActionreference(commonActionBean.getReference());
		actionHistory.setAdAhNotes(actionDesc);
		actionHistory.setAdAhCheckid(commonActionBean.getCheckNo());
		actionHistory.setAdAhCopay(commonActionBean.getCopay());
		actionHistory.setAdAhCoins(commonActionBean.getCoins());
		actionHistory.setAdAhDenialreason(Integer.parseInt(getBillingReasonId(commonActionBean)+""));
		actionHistory.setAdAhDenialtype(Integer.parseInt(getDenialTypeId(commonActionBean)+""));
		actionHistory.setAdAhDenialCategory(Integer.parseInt(getDenialCategoryId(commonActionBean)+""));
		try {
			actionHistory.setAdAhPayment(Double.parseDouble(HUtil.Nz(commonActionBean.getPayment(),"0.0")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		actionHistory.setAdAhDeductible(commonActionBean.getDeductible());
		actionHistory.setAdAhAmount(commonActionBean.getCheckAmount());
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
		problemReport.setProblemReportProblemType(Integer.parseInt(getDenialTypeId(commonActionBean)+""));
		problemReport.setProblemReportProblemTypedesc(commonActionBean.getProblemType()); 
		problemReportRepository.saveAndFlush(problemReport);
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
			finalResponseBean.setDenialId(commonActionBean.getDenialId());
			finalResponseBean.setTicketNo(HUtil.Nz(commonActionBean.getTicketNo(), ""));
			finalResponseBean.setEmpId(commonActionBean.getModifiedBy());
			finalResponseBean.setActionId(HUtil.Nz(commonActionBean.getActionId(), "-1"));
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
			finalResponseBean.setDenialId(commonActionBean.getDenialId());
			finalResponseBean.setActionId(HUtil.Nz(commonActionBean.getActionId(), "-1"));
			finalResponseBean.setFailedReason(exception.getMessage());
		}
		return finalResponseBean;
	}

	@Override
	public List<PatientInsuranceInfoBean> getPatientInsInfo(Integer patientId,Integer type) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PatientInsuranceInfoBean> criteriaQuery = builder.createQuery(PatientInsuranceInfoBean.class);
		Root<PatientInsDetail> root = criteriaQuery.from(PatientInsDetail.class);
		Join<PatientInsDetail, InsCompAddr> insCompAddr=root.join(PatientInsDetail_.insCompAddr,JoinType.INNER);
		Join<InsCompAddr, InsCompany> insComp=insCompAddr.join(InsCompAddr_.insCompany,JoinType.INNER);

		TypedQuery<PatientInsuranceInfoBean> typedQuery = em.createQuery(criteriaQuery
				.select(builder.construct(PatientInsuranceInfoBean.class,
						root.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
						root.get(PatientInsDetail_.patientInsDetailIsactive),
						insComp.get(InsCompany_.insCompanyName),
						root.get(PatientInsDetail_.patientInsDetailInsno)))
						.where(builder.equal(root.get(PatientInsDetail_.patientInsDetailPatientid), patientId),
								builder.equal(root.get(PatientInsDetail_.patientInsDetailInstype), type))
								.distinct(true));

		List<PatientInsuranceInfoBean> resultList = typedQuery.getResultList();

		return resultList;
	}


	@Override
	public List<ClaimInfoBean> getServicesByClaim(Integer patientId, String claimNo) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ClaimInfoBean> criteriaQuery = builder.createQuery(ClaimInfoBean.class);
		Root<ServiceDetail> root = criteriaQuery.from(ServiceDetail.class);
		Join<ServiceDetail, H172> serviceClaimJoin = root.join(ServiceDetail_.h172,JoinType.INNER);
		Join<ServiceDetail, Cpt> cptServiceJoin = root.join(ServiceDetail_.cpt, JoinType.INNER);
		Join<ServiceDetail, ServiceBalances> serviceBalancesJoin = root.join(ServiceDetail_.serviceBalances, JoinType.INNER);
		
		TypedQuery<ClaimInfoBean> typedQuery = em.createQuery(criteriaQuery
				.select(builder.construct(ClaimInfoBean.class,
						root.get(ServiceDetail_.serviceDetailId),
						builder.function("to_char", String.class, root.get(ServiceDetail_.serviceDetailDos), builder.literal("mm/dd/yyyy")),
						cptServiceJoin.get(Cpt_.cptCptcode),
						root.get(ServiceDetail_.serviceDetailUnit),
						root.get(ServiceDetail_.serviceDetailDx1),
						root.get(ServiceDetail_.serviceDetailDx2),
						root.get(ServiceDetail_.serviceDetailDx3),
						root.get(ServiceDetail_.serviceDetailDx4),
						root.get(ServiceDetail_.serviceDetailCharges),
						root.get(ServiceDetail_.serviceDetailCopay),
						serviceBalancesJoin.get(ServiceBalances_.primaryInsurancePayment),
						serviceBalancesJoin.get(ServiceBalances_.secondaryInsurancePayment)))
						
				.where(builder.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientId),
						builder.like(serviceClaimJoin.get(H172_.claimReferenceClaimid), claimNo),
						builder.isNotNull(serviceClaimJoin.get(H172_.claimReferenceClaimid)))
						
				.distinct(true));

		List<ClaimInfoBean> resultList = typedQuery.getResultList();

		return resultList;
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
	public Short getDenialReasonId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub.
		String qry="select blook_intid from billinglookup where blook_group=123 and blook_desc ilike '"+HUtil.Nz(commonAction.getDenialReason(),"-1")+"' limit 1";

		//Query nativeQuery= (Query) em.createNativeQuery(qry);
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			commonAction.setDenialReason(""+id);
		}
		return id;
	}


	@Override
	public Integer getBillingReasonId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub

		String qry="select billing_reason_id as billingreasonId from billing_reason where billing_reason_isactive is true  and billing_reason_desc ilike '"+HUtil.Nz(commonAction.getBillingReason(),"-1")+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Integer id=0;
		for(int i=0;i<result.size();i++){
			id=(Integer)result.get(0);
		}
		if(id==0){
			
			qry="select max(billing_reason_id)+1 from billing_reason";
			result=em.createNativeQuery(qry).getResultList();
			
			for(int i=0;i<result.size();i++){
				id=(Integer)result.get(0);
			}
			
			insertBillingReason(HUtil.Nz(commonAction.getBillingReason(),"-1"), id); 
		}
		return id;
	}

	public void insertBillingReason(String reason,Integer maxId)
	{
		BillingReason billingReason = new BillingReason();
		billingReason.setBillingReasonId(maxId);
		billingReason.setBillingReasonDescription(reason);
		billingReason.setBillingReasonIsactive(true);
		billingReasonRepository.saveAndFlush(billingReason);
	}

	@Override
	public Short getDenialTypeId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub
		// no need of another things
		String qry="select blook_intid from billinglookup where blook_group=121 and blook_desc ilike '"+HUtil.Nz(commonAction.getDenialType(),"-1")+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			//commonAction.setDenialType(""+id);
		}
		
		if(id==0){
			Integer blookId=0;
			Integer ID=0;
			qry="select max(blook_intid)+1 from billinglookup where blook_group=121";
			result=em.createNativeQuery(qry).getResultList();
			for(int i=0;i<result.size();i++){
				ID=(Integer)result.get(0);
			}
			qry="select max(blook_id)+1 from billinglookup";
			result=em.createNativeQuery(qry).getResultList();
			for(int i=0;i<result.size();i++){
				blookId=(Integer)result.get(0);
			}
			Short group=121;
			id=Short.parseShort(""+ID);
			insertIntoBillingLookup(blookId,HUtil.Nz(commonAction.getDenialType(),"-1"),group,id);
		}
		return id;
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

	@Override
	public Short getDenialCategoryId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub

		String qry="select blook_intid from billinglookup where blook_group=124 and blook_desc ilike '"+HUtil.Nz(commonAction.getDenialCategory(),"-1")+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Short id=0;
		for(int i=0;i<result.size();i++){
			id=(Short)result.get(0);
			//commonAction.setDenialCategory(""+id);
		}
		
		if(id==0){
			Integer blookId=0;
			id=0;
			Integer ID=0;
			qry="select max(blook_intid)+1 from billinglookup where blook_group=124";
			result=em.createNativeQuery(qry).getResultList();
			for(int i=0;i<result.size();i++){
				ID=(Integer)result.get(0);
			}
			qry="select max(blook_id)+1 from billinglookup";
			result=em.createNativeQuery(qry).getResultList();
			for(int i=0;i<result.size();i++){
				blookId=(Integer)result.get(0);
			}
			Short group=124;
			id=Short.parseShort(""+HUtil.Nz(ID, "-1"));
			insertIntoBillingLookup(blookId,HUtil.Nz(commonAction.getDenialCategory(),"-1"),group,id);
		}
		return id;
	}


	@Override
	public Integer getProblemTypeId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub

		String qry="select problem_type_id from problem_type where problem_type_name ilike '"+HUtil.Nz(commonAction.getProblemType(),"-1")+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Integer id=0;
		for(int i=0;i<result.size();i++){
			id=(Integer)result.get(0);
			//commonAction.setProblemType(""+id);
		}
		return id;
	}

	public Integer getPaymentCptId(String cpt){

		Integer cptId=0;

		String qry="select cpt_id from cpt where cpt_cptcode  ilike '"+cpt+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();

		for(int i=0;i<result.size();i++){
			cptId=(Integer)result.get(0);
			//commonAction.setProblemType(""+id);
		}
		return cptId;
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

	public Integer getFollowupActionId(CommonActionBean commonAction) {
		// TODO Auto-generated method stub

		String qry="select h222001 from h222 where h222002 ilike '"+HUtil.Nz(commonAction.getNextFollowupAction(),"-1")+"' limit 1";
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Integer id=0;
		for(int i=0;i<result.size();i++){
			id=(Integer)result.get(0);
			//commonAction.setProblemType(""+id);
		}
		return id;
	}
	
	public void insertFollowupActionId(Integer maxId,String description){
		
		H222 h222 = new H222();
		
		h222.setH222001(maxId);
		h222.setH222002(description);
		
		h222Repository.saveAndFlush(h222);
	}
	public void updateOtherServiceDetail(String popupCurrentStatus,CommonActionBean commonActionBean)
	{
		String query="update associate_service_detail set associate_service_detail_currentstatus = coalesce(associate_service_detail_currentstatus,'') || case when length(associate_service_detail_currentstatus)>0 then ';' else '' end || ' ' || to_mmddyyyy(now()) || ' - "+
		             ""+Utilities.handleSymbols(popupCurrentStatus)+"'";
		
		if(commonActionBean.getProblemType()!=null)
		{
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

	@Override
	public Integer getProblemTypeIdDesc(CommonActionBean commonAction) {
		// TODO Auto-generated method stub

		String qry="select ar_problem_list_id from ar_problem_list where ar_problem_list_problem ilike '"+HUtil.Nz(commonAction.getProblemType(),"-1")+"' limit 1";
		System.out.println("qry for problem type===========>"+qry);
		List<Object> result=em.createNativeQuery(qry).getResultList();
		Integer id=0;
		System.out.println("result.size()==========>"+result.size());
		for(int i=0;i<result.size();i++){
			id=(Integer)result.get(0);
			//commonAction.setDenialCategory(""+id);
		}
		System.out.println("Now id is==============>"+id);
		
		System.out.println(">>condn>>>>>>"+(id==0));
		if(id==0)
		{
			System.out.println(">>came inside loop id==0");
			Integer arproblemlistId=0;
			Integer ID=0;
			qry="select max(ar_problem_list_id)+1 from ar_problem_list";
			result=em.createNativeQuery(qry).getResultList();
			for(int i=0;i<result.size();i++){
				ID=(Integer)result.get(0);
			}
			/*qry="select max(ar_problem_list_id)+1 from ar_problem_list";
			
			for(int i=0;i<result.size();i++){
				arproblemlistId=(Integer)result.get(0);
			}*/
			id=Integer.parseInt(""+ID);
			//insertIntoBillingLookup(arproblemlistId,HUtil.Nz(commonAction.getDenialType(),"-1"));
			insertIntoArProblemList(id,HUtil.Nz(commonAction.getProblemType(),"-1"));
		}
		return id;
	}
	
	public void insertIntoArProblemList(Integer arProblemListId,String arProblemListProblem) {
		
		ArProblemList  arProblemList = new ArProblemList();
		
		arProblemList.setArProblemListId(arProblemListId);
		arProblemList.setArProblemListProblem(arProblemListProblem);
		/*arProblemList.setArProblemListProblemDescription(arProblemListProblemDescription);
		arProblemList.setArProblemListToBeSynchronized(arProblemListToBeSynchronized);
		arProblemList.setArProblemListProblemGroupid(arProblemListProblemGroupid);
		arProblemList.setArProblemListIsfreqentlyused(arProblemListIsFreqentlyused);
		arProblemList.setArProblemListIscustom(arProblemListIscustom);*/
		arProblemListRepository.saveAndFlush(arProblemList);
		
	}

	@Override
	public Integer isMarkAsActionTaken(CommonActionBean commonActionBean) {
		// TODO Auto-generated method stub
		System.out.println(">>>we are in isMarkAsActionTaken>>>");
		try
		{
			System.out.println(">>>> in try block>>>");
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<AdActionhistory> root = criteriaQuery.from(AdActionhistory.class);
			
			criteriaQuery.select(root.get(AdActionhistory_.adAhActionid));
			criteriaQuery.where(builder.equal(root.get(AdActionhistory_.adAhDenialid), commonActionBean.getNonServiceId()));
			
			List<Object> resultSet = em.createQuery(criteriaQuery).getResultList();
			
			System.out.println("Result"+objectMapper.writeValueAsString(resultSet));
			
			if(resultSet.size() == 0)
				return -1;
			else
				return (Integer) resultSet.get(0);
		}
		catch(Exception exception)
		{
			System.out.println(">>> in catch block>>>");
			exception.printStackTrace();
			return null;
		}
	}
	
	public int getArCategoryId(String categoryName) 
	{
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery(Object.class);
		Root<Billinglookup> root = criteriaQuery.from(Billinglookup.class);
		
		TypedQuery<Object> typedQuery = em.createQuery(criteriaQuery
				.select(builder.max(root.get(Billinglookup_.blookIntid)))
				.where(builder.equal(root.get(Billinglookup_.blookGroup), 11),builder.like(builder.lower(root.get(Billinglookup_.blookDesc)),categoryName.toLowerCase() )));

		List<Object> resultList = typedQuery.getResultList();
		return Integer.parseInt(resultList.get(0).toString());
	}
	
	public int getCPTId(String newCpt){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Cpt> root = cq.from(Cpt.class);
		cq.select(root.get(Cpt_.cptId));
		cq.where(builder.equal(root.get(Cpt_.cptCptcode), newCpt));
		int cptId =em.createQuery(cq).getFirstResult(); 
		return cptId;
	}
	
	public ServiceDetail getServiceDetailsForOldCPT(int serviceId,int cptId,double charges){
		System.out.println(">>>>>>>>>>>>>>>>>>>"+serviceId);
		ServiceDetail serviceDetails = serviceDetailRepository.findAll(DenialServiceImpl.getServiceDetail(serviceId)).get(0);
		//ServiceDetail serviceDetails = new ServiceDetail();
		return serviceDetails;
	}
	
	public static Specification<NonServiceDetails> getNonServiceDetail(final int  serviceId,final int paymentMethod){
		return new Specification<NonServiceDetails>() {

			@Override
			public Predicate toPredicate(Root<NonServiceDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(NonServiceDetails_.nonServiceDetailId)));
				Predicate serviceIdCond = cb.equal(root.get(NonServiceDetails_.nonServiceDetailServiceId),serviceId);
				Predicate paymentCond = cb.equal(root.get(NonServiceDetails_.nonServiceDetailPaymentMethod),paymentMethod);
				Predicate finalCondition=cb.and(serviceIdCond,paymentCond);
				return finalCondition;
			}
		};	
	}
	
	public static Specification<ServiceDetail> getServiceDetail(final int  serviceId){
		return new Specification<ServiceDetail>() {

			@Override
			public Predicate toPredicate(Root<ServiceDetail> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				Predicate serviceIdCond = cb.equal(root.get(ServiceDetail_.serviceDetailId),serviceId);
				return serviceIdCond;
			}
		};	
	}
	
	private CommonActionBean setwriteOffBean(ServiceDetail serviceDetail,CommonActionBean cptRequestBean){
		CommonActionBean writeOffBean = new CommonActionBean();
		writeOffBean.setServiceId(serviceDetail.getServiceDetailId());
		writeOffBean.setPatientId(serviceDetail.getServiceDetailPatientid());
		writeOffBean.setServiceId(serviceDetail.getServiceDetailId());
		writeOffBean.setDop(convertDateToString(serviceDetail.getServiceDetailDop()));
		writeOffBean.setNotes(cptRequestBean.getActionDescription());
		writeOffBean.setModifiedBy(cptRequestBean.getModifiedBy());
		return writeOffBean;
	}
	
	private String convertDateToString(Date sqlDate){
		String date ="";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.format(sqlDate);
		return date;
	}
	
	public void entityCreationServiceDetail(ServiceDetail serviceDetails,int cptId,double charges){
		ServiceDetail serviceDetail=new ServiceDetail();
		serviceDetail.setServiceDetailId(-1);
		serviceDetail.setServiceDetailPatientid(serviceDetails.getServiceDetailPatientid());
		serviceDetail.setServiceDetailSubmitStatus("P");
		serviceDetail.setServiceDetailDos(serviceDetails.getServiceDetailDos());
		Calendar calendar = Calendar.getInstance();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
		serviceDetail.setServiceDetailDop(currentDate);
		serviceDetail.setServiceDetailDosFrom(serviceDetails.getServiceDetailDosFrom());
		serviceDetail.setServiceDetailCptid(cptId);
		serviceDetail.setServiceDetailModifier1(serviceDetails.getServiceDetailModifier1());
		serviceDetail.setServiceDetailComments(serviceDetails.getServiceDetailComments());
		serviceDetail.setServiceDetailModifier2(serviceDetails.getServiceDetailModifier2());
		serviceDetail.setServiceDetailSdoctorid(serviceDetails.getServiceDetailSdoctorid());
		serviceDetail.setServiceDetailBdoctorid(serviceDetails.getServiceDetailBdoctorid());
		serviceDetail.setServiceDetailRdoctorid(serviceDetails.getServiceDetailRdoctorid());
		serviceDetail.setServiceDetailAuthid(serviceDetails.getServiceDetailAuthid());
		serviceDetail.setServiceDetailReferralid(serviceDetails.getServiceDetailReferralid());
		serviceDetail.setServiceDetailPosid(serviceDetails.getServiceDetailPosid());
		serviceDetail.setServiceDetailUnit(serviceDetails.getServiceDetailUnit());
		serviceDetail.setServiceDetailCharges(charges);
		serviceDetail.setServiceDetailCopay(serviceDetails.getServiceDetailCopay());
		serviceDetail.setServiceDetailCredits(serviceDetails.getServiceDetailCredits());
		serviceDetail.setServiceDetailExpectedPayments(serviceDetails.getServiceDetailExpectedPayments());
		serviceDetail.setServiceDetailExpectedDate(serviceDetails.getServiceDetailExpectedDate());
		serviceDetail.setServiceDetailReference(serviceDetails.getServiceDetailReference());
		serviceDetail.setServiceDetailBookmark(serviceDetails.getServiceDetailBookmark());
		serviceDetail.setServiceDetailSource(serviceDetails.getServiceDetailSource());
		serviceDetail.setServiceDetailServer(serviceDetails.getServiceDetailServer());
		serviceDetail.setServiceDetailDx1(serviceDetails.getServiceDetailDx1());
		serviceDetail.setServiceDetailDx2(serviceDetails.getServiceDetailDx2());
		serviceDetail.setServiceDetailDx3(serviceDetails.getServiceDetailDx3());
		serviceDetail.setServiceDetailDx4(serviceDetails.getServiceDetailDx4());
		serviceDetail.setServiceDetailDx5(serviceDetails.getServiceDetailDx5());
		serviceDetail.setServiceDetailDx6(serviceDetails.getServiceDetailDx6());
		serviceDetail.setServiceDetailDx7(serviceDetails.getServiceDetailDx7());
		serviceDetail.setServiceDetailDx8(serviceDetails.getServiceDetailDx8());
		serviceDetail.setServiceDetailPlacedby("Billing Central");
		serviceDetail.setServiceDetailPlaceddate(currentDate);
		serviceDetail.setServiceDetailModifiedby("Billing Central");
		serviceDetail.setServiceDetailModifieddate(serviceDetails.getServiceDetailModifieddate());
		serviceDetail.setServiceDetailPrimaryins(serviceDetails.getServiceDetailPrimaryins());
		serviceDetail.setServiceDetailSecondaryins(serviceDetails.getServiceDetailSecondaryins());
		serviceDetail.setServiceDetailUnknown2(serviceDetails.getServiceDetailUnknown2());
		serviceDetail.setServiceDetailPosid(serviceDetails.getServiceDetailPosid());
		serviceDetail.setServiceDetailGuarantorId(serviceDetails.getServiceDetailGuarantorId());
		serviceDetail.setServiceDetailAnesStarttime(serviceDetails.getServiceDetailAnesStarttime());
		serviceDetail.setServiceDetailAnesEndtime(serviceDetails.getServiceDetailAnesEndtime());
		serviceDetail.setServiceDetailDontbill(serviceDetails.getServiceDetailDontbill());
		serviceDetail.setServiceDetailBillingReason(serviceDetails.getServiceDetailBillingReason());
		serviceDetail.setServiceDetailDx9(serviceDetails.getServiceDetailDx9());
		serviceDetail.setServiceDetailDx10(serviceDetails.getServiceDetailDx10());
		serviceDetail.setServiceDetailDx11(serviceDetails.getServiceDetailDx11());
		serviceDetail.setServiceDetailDx12(serviceDetails.getServiceDetailDx12());
		serviceDetail.setServiceDetailDx13(serviceDetails.getServiceDetailDx13());
		serviceDetail.setServiceDetailDx14(serviceDetails.getServiceDetailDx14());
		serviceDetail.setServiceDetailDx15(serviceDetails.getServiceDetailDx15());
		serviceDetail.setServiceDetailDx16(serviceDetails.getServiceDetailDx16());
		serviceDetail.setServiceDetailDx17(serviceDetails.getServiceDetailDx17());
		serviceDetail.setServiceDetailDx18(serviceDetails.getServiceDetailDx18());
		serviceDetail.setServiceDetailDx19(serviceDetails.getServiceDetailDx19());
		serviceDetail.setServiceDetailDx20(serviceDetails.getServiceDetailDx20());
		serviceDetail.setServiceDetailDx1desc(serviceDetails.getServiceDetailDx1desc());
		serviceDetail.setServiceDetailDx2desc(serviceDetails.getServiceDetailDx2desc());
		serviceDetail.setServiceDetailDx3desc(serviceDetails.getServiceDetailDx3desc());
		serviceDetail.setServiceDetailDx4desc(serviceDetails.getServiceDetailDx4desc());
		serviceDetail.setServiceDetailDx5desc(serviceDetails.getServiceDetailDx5desc());
		serviceDetail.setServiceDetailDx6desc(serviceDetails.getServiceDetailDx6desc());
		serviceDetail.setServiceDetailDx7desc(serviceDetails.getServiceDetailDx7desc());
		serviceDetail.setServiceDetailDx8desc(serviceDetails.getServiceDetailDx8desc());
		serviceDetail.setServiceDetailDx9desc(serviceDetails.getServiceDetailDx9desc());
		serviceDetail.setServiceDetailDx10desc(serviceDetails.getServiceDetailDx10desc());
		serviceDetail.setServiceDetailDx11desc(serviceDetails.getServiceDetailDx11desc());
		serviceDetail.setServiceDetailDx12desc(serviceDetails.getServiceDetailDx12desc());
		serviceDetail.setServiceDetailDx13desc(serviceDetails.getServiceDetailDx13desc());
		serviceDetail.setServiceDetailDx14desc(serviceDetails.getServiceDetailDx14desc());
		serviceDetail.setServiceDetailDx15desc(serviceDetails.getServiceDetailDx15desc());
		serviceDetail.setServiceDetailDx16desc(serviceDetails.getServiceDetailDx16desc());
		serviceDetail.setServiceDetailDx17desc(serviceDetails.getServiceDetailDx17desc());
		serviceDetail.setServiceDetailDx18desc(serviceDetails.getServiceDetailDx18desc());
		serviceDetail.setServiceDetailDx19desc(serviceDetails.getServiceDetailDx19desc());
		serviceDetail.setServiceDetailDx20desc(serviceDetails.getServiceDetailDx20desc());
		serviceDetail.setServiceDetailDxsystem(serviceDetails.getServiceDetailDxsystem());
		requestToSaveService(serviceDetail);
		List<PrimarykeyGenerator> h213=primarykeyGeneratorRepository.findAll(ChargesSpecification.getServiceDetailMaxId());
		System.out.println("service max id >>"+h213.get(0).getPrimarykeyGeneratorRowcount());
	}
	
	public void requestToSaveService(ServiceDetail createdEntity) {
		try {
			AssociateServiceDetails associateServiceInsert=new AssociateServiceDetails();
			serviceDetailRepository.flush();
			serviceDetailRepository.save(createdEntity);
			List<PrimarykeyGenerator> h213=primarykeyGeneratorRepository.findAll(ChargesSpecification.getServiceDetailMaxId());
			associateServiceInsert.setAssociateServiceDetailId(Long.valueOf(-1));
			associateServiceInsert.setAssociateServiceDetailServiceId(h213.get(0).getPrimarykeyGeneratorRowcount());
			associateServiceInsert.setAssociateServiceDetailSpecialDx("");
			associateServiceDetailsRepository.save(associateServiceInsert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveNonServiceDetailRecordForOldAndNewService(List<NonServiceDetails> result){
		//New Service 
		Long newServiceId = getMaxServiceId();
		System.out.println("newServiceId>>>>"+newServiceId);
		for(int index=0;index<result.size();index++){
			NonServiceDetails nonServiceDetail = result.get(index);
			Calendar calendar = Calendar.getInstance();
			java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			entityCreationNonServiceDetail(nonServiceDetail,newServiceId, currentDate,"NewService");
		}
		//Old Service 
		for(int index=0;index<result.size();index++){
			NonServiceDetails nonServiceDetail = result.get(index);
			Long serviceId =nonServiceDetail.getNonServiceDetailServiceId();
			Date date = nonServiceDetail.getNonServiceDetailDateOfPosting();
			entityCreationNonServiceDetail(nonServiceDetail,serviceId,date,"OldService");
		}
	}
	
	public Long getMaxServiceId(){
		List<Object> serviceId=null;
		Long maxServiceId=0L;
		try{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<ServiceDetail> root = cq.from(ServiceDetail.class);
			cq.select(builder.construct(Integer.class, builder.max(root.get(ServiceDetail_.serviceDetailId))));
			serviceId=em.createQuery(cq).getResultList();
			
			for(Object obj :serviceId){

				maxServiceId = (Long)obj;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return maxServiceId;
	}
	
	public boolean entityCreationNonServiceDetail(NonServiceDetails nonServiceDetails,Long newServiceId,Date date,String flag){
		NonServiceDetails nonServiceDetail = new NonServiceDetails();
		if(flag.equals("OldService")){
			nonServiceDetail.setNonServiceDetailAmount(-nonServiceDetail.getNonServiceDetailAmount());
			nonServiceDetail.setNonServiceDetailEntryType(Short.parseShort("15"));
			nonServiceDetail.setNonServiceDetailReverseId(nonServiceDetail.getNonServiceDetailId());
		}else{
			nonServiceDetail.setNonServiceDetailAmount(nonServiceDetails.getNonServiceDetailAmount());
			nonServiceDetail.setNonServiceDetailEntryType(nonServiceDetails.getNonServiceDetailEntryType());
			nonServiceDetail.setNonServiceDetailReverseId(nonServiceDetails.getNonServiceDetailReverseId());
		}
		nonServiceDetail.setNonServiceDetailId(Long.parseLong("-1"));
		nonServiceDetail.setNonServiceDetailId(nonServiceDetails.getNonServiceDetailServiceId());
		nonServiceDetail.setNonServiceDetailPatientId(nonServiceDetails.getNonServiceDetailPatientId());
		nonServiceDetail.setNonServiceDetailServiceId(newServiceId);
		nonServiceDetail.setNonServiceDetailDateOfPosting(date);
		
		nonServiceDetail.setNonServiceDetailPaymentCptId(nonServiceDetails.getNonServiceDetailPaymentCptId());
		nonServiceDetail.setNonServiceDetailComments(nonServiceDetails.getNonServiceDetailComments());
		nonServiceDetail.setNonServiceDetailPaymentMethod(nonServiceDetails.getNonServiceDetailPaymentMethod());
		nonServiceDetail.setNonServiceDetailDeductible(nonServiceDetails.getNonServiceDetailDeductible());
		nonServiceDetail.setNonServiceDetailCoins(nonServiceDetails.getNonServiceDetailCoins());
		nonServiceDetail.setNonServiceDetailDatePaid(date);
		nonServiceDetail.setNonServiceDetailRiskWithheld(0.00);
		nonServiceDetail.setNonServiceDetailPlacedBy("Billing Central");   
		nonServiceDetail.setNonServiceDetailLastModifiedBy("Billing Central");
		nonServiceDetail.setNonServiceDetailLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
		nonServiceDetail.setNonServiceDetailPayerId(nonServiceDetails.getNonServiceDetailPatientId());
		nonServiceDetail.setNonServiceDetailCheckId(nonServiceDetails.getNonServiceDetailCheckId());
		nonServiceDetail.setH555555(nonServiceDetails.getH555555());
		nonServiceDetail.setNonServiceDetailIsValid(nonServiceDetails.getNonServiceDetailIsValid());
		nonServiceDetail.setNonServiceDetailRefundStatus(nonServiceDetails.getNonServiceDetailRefundStatus());
		nonServiceDetail.setNonServiceDetailReceiptDate(nonServiceDetails.getNonServiceDetailReceiptDate());
		nonServiceDetail.setNonServiceDetailPaidBy(nonServiceDetails.getNonServiceDetailPaidBy());
		nonServiceDetailRepository.saveAndFlush(nonServiceDetail);
		return true;
	}
	
	@Override
	public List<ManagerInfoBean> getMarkAsCompleted(ManagerInfoBean managerInfoBean) 
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ManagerInfoBean> criteriaQuery = builder.createQuery(ManagerInfoBean.class);
		Root<AdActionhistory> root = criteriaQuery.from(AdActionhistory.class);
		List<Integer> array = new ArrayList<Integer>();
		if (!managerInfoBean.getNonServiceDetailId().equals("")) 
		{
			String[] denialIdList = managerInfoBean.getNonServiceDetailId().split(",");
			for (int i = 0; i < denialIdList.length; i++) 
				array.add(Integer.parseInt(denialIdList[i]));
		}
		criteriaQuery.distinct(true);
		criteriaQuery.select(builder.construct(ManagerInfoBean.class,root.get(AdActionhistory_.adAhDenialid),
				root.get(AdActionhistory_.adAhActionid),
				builder.function("to_char",String.class, root.get(AdActionhistory_.adAhActiontakendate),builder.literal("mm/dd/yyyy"))));
		criteriaQuery.where(root.get(AdActionhistory_.adAhDenialid).in(array));
		List<ManagerInfoBean> resultSet = em.createQuery(criteriaQuery).getResultList();
		return resultSet;
	}
	
}