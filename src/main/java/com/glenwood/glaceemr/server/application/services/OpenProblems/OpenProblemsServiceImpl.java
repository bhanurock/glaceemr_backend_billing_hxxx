package com.glenwood.glaceemr.server.application.services.OpenProblems;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;
import com.glenwood.glaceemr.server.application.models.LoginUsers;
import com.glenwood.glaceemr.server.application.models.LoginUsers_;
import com.glenwood.glaceemr.server.application.models.PatientCallMood;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.ProblemHistory;
import com.glenwood.glaceemr.server.application.models.ProblemHistory_;
import com.glenwood.glaceemr.server.application.models.ProblemReport;
import com.glenwood.glaceemr.server.application.models.ProblemReport_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.repositories.ProblemHistoryRepository;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
@Transactional
public class OpenProblemsServiceImpl implements OpenProblemsService 
{

	@Autowired
	EntityManager em;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ProblemHistoryRepository problemHistoryRepository;

	@Override
	public List<ServiceDetail> serviceDetailId(String serviceDetailId) {
		return null;
	}


	@Override
	public List<OpenProblemsBean> getAllOpenPRoblems(String fromDate,String toDate)
	{
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<ProblemReport> root = cq.from(ProblemReport.class);
			Join<ProblemReport,PatientRegistration>patientRegistration =root.join(ProblemReport_.patientRegistration,JoinType.LEFT);
			Join<ProblemReport,ProblemHistory>problemHistory =root.join(ProblemReport_.problemHistory,JoinType.LEFT);
			Join<ProblemReport,PatientCallMood>patientCallMod=root.join(ProblemReport_.patientCallMood,JoinType.LEFT);
			
			cq.distinct(true);
			cq.select(builder.construct(OpenProblemsBean.class, root.get(ProblemReport_.problemReportUniqueid),
					root.get(ProblemReport_.problemReportPracticeId),
					root.get(ProblemReport_.problemReportProblemId),
					root.get(ProblemReport_.problemReportProblemstatus),
					root.get(ProblemReport_.problemReportTicketNo),
					root.get(ProblemReport_.problemReportProblem),
					root.get(ProblemReport_.problemReportSolution),
					root.get(ProblemReport_.problemReportReportedBy),
					root.get(ProblemReport_.problemReportReportedTo),
					root.get(ProblemReport_.problemReportPatientName),
					root.get(ProblemReport_.problemReportAccountNo),
					root.get(ProblemReport_.problemReportLoginUser),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(root.get(ProblemReport_.problemReportReportedOn),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(root.get(ProblemReport_.problemReportPatientDob),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(root.get(ProblemReport_.problemReportLastModified),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					patientRegistration.get(PatientRegistration_.patientRegistrationId),
					root.get(ProblemReport_.problemReportPatientcall),
					root.get(ProblemReport_.problemReportResolvedBy),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(root.get(ProblemReport_.problemReportResolvedOn),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					root.get(ProblemReport_.problemReportSubject),
					root.get(ProblemReport_.problemReportProblemDenialrulevalidatorId),
					root.get(ProblemReport_.problemReportFilepaths),
					root.get(ProblemReport_.problemReportFilenames),
					root.get(ProblemReport_.problemReportReplyFilepath),
					root.get(ProblemReport_.problemReportReplyFilename),
					problemHistory.get(ProblemHistory_.problemHistoryForwardedTo),
					builder.max(problemHistory.get(ProblemHistory_.problemHistoryProblemUniqueid)),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(problemHistory.get(ProblemHistory_.problemHistoryModifiedOn),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					problemHistory.get(ProblemHistory_.problemHistoryForwardedBy),
					problemHistory.get(ProblemHistory_.problemHistoryMessage),
					problemHistory.get(ProblemHistory_.problemHistorySequence),
					root.get(ProblemReport_.problemReportProblemTypedesc),
					root.get(ProblemReport_.problemReportReviewed),
					root.get(ProblemReport_.problemReportLastForwardedTo),
					root.get(ProblemReport_.problemReportLastForwardedBy),
					builder.function("to_char",String.class,builder.function("date",Date.class,builder.coalesce(root.get(ProblemReport_.problemReportLastForwardedOn),new Date(1900, 01, 01))),builder.literal("mm/dd/yyyy")),
					patientRegistration.get(PatientRegistration_.patientRegistrationFirstName),
					patientRegistration.get(PatientRegistration_.patientRegistrationLastName),
					patientRegistration.get(PatientRegistration_.patientRegistrationMidInitial)
					
					));
			
			cq.groupBy(root.get(ProblemReport_.problemReportUniqueid),
					root.get(ProblemReport_.problemReportPracticeId),
					root.get(ProblemReport_.problemReportProblemId),
					root.get(ProblemReport_.problemReportProblemstatus),
					root.get(ProblemReport_.problemReportTicketNo),
					root.get(ProblemReport_.problemReportProblem),
					root.get(ProblemReport_.problemReportSolution),
					root.get(ProblemReport_.problemReportReportedBy),
					root.get(ProblemReport_.problemReportReportedTo),
					root.get(ProblemReport_.problemReportPatientName),
					root.get(ProblemReport_.problemReportAccountNo),
					root.get(ProblemReport_.problemReportLoginUser),
					root.get(ProblemReport_.problemReportReportedOn),
					root.get(ProblemReport_.problemReportPatientDob),
					root.get(ProblemReport_.problemReportLastModified),
					patientRegistration.get(PatientRegistration_.patientRegistrationId),
					root.get(ProblemReport_.problemReportPatientcall),
					root.get(ProblemReport_.problemReportResolvedBy),
					root.get(ProblemReport_.problemReportResolvedOn),
					root.get(ProblemReport_.problemReportSubject),
					root.get(ProblemReport_.problemReportProblemDenialrulevalidatorId),
					root.get(ProblemReport_.problemReportFilepaths),
					root.get(ProblemReport_.problemReportFilenames),
					root.get(ProblemReport_.problemReportReplyFilepath),
					root.get(ProblemReport_.problemReportReplyFilename),
					problemHistory.get(ProblemHistory_.problemHistoryForwardedTo),
					problemHistory.get(ProblemHistory_.problemHistoryModifiedOn),
					problemHistory.get(ProblemHistory_.problemHistoryForwardedBy),
					problemHistory.get(ProblemHistory_.problemHistoryMessage),
					problemHistory.get(ProblemHistory_.problemHistorySequence),
					root.get(ProblemReport_.problemReportProblemTypedesc),
					root.get(ProblemReport_.problemReportReviewed),
					root.get(ProblemReport_.problemReportLastForwardedTo),
					root.get(ProblemReport_.problemReportLastForwardedBy),
					root.get(ProblemReport_.problemReportLastForwardedOn),
					patientRegistration.get(PatientRegistration_.patientRegistrationFirstName),
					patientRegistration.get(PatientRegistration_.patientRegistrationLastName),
					patientRegistration.get(PatientRegistration_.patientRegistrationMidInitial)
					);
			
			List<Object> results=em.createQuery(cq).getResultList();
			List<OpenProblemsBean> resultList = new ArrayList<OpenProblemsBean>();
			for(int i=0;i<results.size();i++)
			{
				OpenProblemsBean opeanProblemsBean=(OpenProblemsBean) results.get(i);
				resultList.add(opeanProblemsBean);
			}
			//List<OpeanProblemsBean> resultList = em.createQuery(cq).getResultList();
			//cq.subquery(null);
			return resultList;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
		//return null;
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

	/*@Override
	public OpenProblemsResponseBean editOPAction(OpenProblemsRequestBean openProblemsRequestBean) {
		// TODO Auto-generated method stub
		try{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
		Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
		Integer sequence=0;
	    sequence=getSequence(openProblemsRequestBean);
	    sequence+=1;
		criteriaUpdate.set("problemReportReportedTo",openProblemsRequestBean.getReportedTo());
		criteriaUpdate.set("problemReportLastForwardedTo",openProblemsRequestBean.getForwardedTo());
		criteriaUpdate.set("problemReportLastForwardedBy",openProblemsRequestBean.getForwardedBy());
		criteriaUpdate.set("problemReportProblemTypedesc",openProblemsRequestBean.getPblmTypeDesc());
		criteriaUpdate.set("problemReportLastForwardedOn", new java.util.Date());
		criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getUniqueId()));
		this.em.createQuery(criteriaUpdate).executeUpdate();
		
		return setResponse(1, openProblemsRequestBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, openProblemsRequestBean, e);
		}
	}
	
	@Override
	public OpenProblemsResponseBean forwardOPAction(OpenProblemsRequestBean openProblemsRequestBean) {
		// TODO Auto-generated method stub
		try{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
			Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
			Integer sequence=0;
		    sequence=getSequence(openProblemsRequestBean);
		    sequence+=1;
			criteriaUpdate.set("problemReportReportedTo",openProblemsRequestBean.getReportedTo());
			criteriaUpdate.set("problemReportLastForwardedTo",openProblemsRequestBean.getForwardedTo());
			criteriaUpdate.set("problemReportLastForwardedBy",openProblemsRequestBean.getForwardedBy());
			criteriaUpdate.set("problemReportProblemTypedesc",openProblemsRequestBean.getPblmTypeDesc());
			criteriaUpdate.set("problemReportLastForwardedOn", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getUniqueId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			ProblemHistory  problemHistory = new ProblemHistory();
			problemHistory.setProblemHistoryProblemUniqueid(openProblemsRequestBean.getUniqueId());
			problemHistory.setProblemHistorySequence(sequence);
			problemHistory.setProblemHistoryForwardedBy(openProblemsRequestBean.getForwardedBy());
			problemHistory.setProblemHistoryModifiedOn( new Timestamp(System.currentTimeMillis()));
			problemHistory.setProblemHistoryForwardedTo(openProblemsRequestBean.getForwardedTo());
			problemHistory.setProblemHistoryMessage(openProblemsRequestBean.getMessage());
			problemHistory.setProblemHistoryForwardedFlag(true);
			problemHistoryRepository.saveAndFlush(problemHistory);
			
			return setResponse(1, openProblemsRequestBean, null);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return setResponse(2, openProblemsRequestBean, e);
			}
	}*/
	
	@Override
	public CommonResponseBean editOPAction(CompleteOPBean openProblemsRequestBean) {
		// TODO Auto-generated method stub
		try{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
			Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
			Integer sequence=0;
		    sequence=getSequence(openProblemsRequestBean.getProblemUniqueId());
		    sequence+=1;
			criteriaUpdate.set("problemReportReportedTo",openProblemsRequestBean.getReportedTo());
			criteriaUpdate.set("problemReportLastForwardedTo",openProblemsRequestBean.getForwardedTo());
			criteriaUpdate.set("problemReportLastForwardedBy",openProblemsRequestBean.getForwardedBy());
			criteriaUpdate.set("problemReportProblemTypedesc",openProblemsRequestBean.getProblem());
			criteriaUpdate.set("problemReportLastForwardedOn", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getProblemUniqueId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			return setResponse(1, openProblemsRequestBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, openProblemsRequestBean, e);
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public CommonResponseBean forwardOPAction(CompleteOPBean openProblemsRequestBean) 
	{
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
			Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
			Integer sequence=0;
		    sequence=getSequence(openProblemsRequestBean.getProblemUniqueId());
		    sequence+=1;
			criteriaUpdate.set("problemReportReportedTo",openProblemsRequestBean.getReportedTo());
			criteriaUpdate.set("problemReportLastForwardedTo",openProblemsRequestBean.getForwardedTo());
			criteriaUpdate.set("problemReportLastForwardedBy",openProblemsRequestBean.getForwardedBy());
			criteriaUpdate.set("problemReportProblemTypedesc",openProblemsRequestBean.getProblem());
			criteriaUpdate.set("problemReportLastForwardedOn", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getProblemUniqueId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			ProblemHistory  problemHistory = new ProblemHistory();
			problemHistory.setProblemHistoryProblemUniqueid(openProblemsRequestBean.getProblemUniqueId());
			problemHistory.setProblemHistorySequence(sequence);
			problemHistory.setProblemHistoryForwardedBy(openProblemsRequestBean.getForwardedBy());
			problemHistory.setProblemHistoryModifiedOn( new Timestamp(System.currentTimeMillis()));
			problemHistory.setProblemHistoryForwardedTo(openProblemsRequestBean.getForwardedTo());
			problemHistory.setProblemHistoryMessage(openProblemsRequestBean.getNewMessage());
			problemHistory.setProblemHistoryForwardedFlag(true);
			problemHistoryRepository.saveAndFlush(problemHistory);
			
			return setResponse(1, openProblemsRequestBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, openProblemsRequestBean, e);
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public CommonResponseBean replyAction(CompleteOPBean openProblemsRequestBean) 
	{
		try{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
			Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
			Integer sequence=0;
		    sequence=getSequence(openProblemsRequestBean.getProblemUniqueId());
		    sequence+=1;
			criteriaUpdate.set("problemReportReportedTo",openProblemsRequestBean.getReportedTo());
			criteriaUpdate.set("problemReportLastForwardedTo",openProblemsRequestBean.getForwardedTo());
			criteriaUpdate.set("problemReportLastForwardedBy",openProblemsRequestBean.getForwardedBy());
			criteriaUpdate.set("problemReportProblemTypedesc",openProblemsRequestBean.getProblem());
			criteriaUpdate.set("problemReportLastForwardedOn", new java.util.Date());
			criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getProblemUniqueId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			return setResponse(1, openProblemsRequestBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, openProblemsRequestBean, e);
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public CommonResponseBean closeAction(CompleteOPBean openProblemsRequestBean) {
		// TODO Auto-generated method stub
		try{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaUpdate<ProblemReport> criteriaUpdate = builder.createCriteriaUpdate(ProblemReport.class);
			Root<ProblemReport> root = criteriaUpdate.from(ProblemReport.class);
			
			criteriaUpdate.set("problemReportProblemstatus",7);
			criteriaUpdate.where(builder.equal(root.get(ProblemReport_.problemReportUniqueid),openProblemsRequestBean.getProblemUniqueId()));
			this.em.createQuery(criteriaUpdate).executeUpdate();
			
			return setResponse(1, openProblemsRequestBean, null);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setResponse(2, openProblemsRequestBean, e);
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public List<OPConversationBean> getOPConversations(Integer problemUniqueId) 
	{
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<OPConversationBean> criteriaQuery = builder.createQuery(OPConversationBean.class);
			Root<ProblemHistory> root = criteriaQuery.from(ProblemHistory.class);
			
			criteriaQuery.select(builder.construct(OPConversationBean.class, root.get(ProblemHistory_.problemHistorySequence),
					root.get(ProblemHistory_.problemHistoryForwardedBy),
					root.get(ProblemHistory_.problemHistoryForwardedTo),
					builder.function("glace_timezone", String.class, root.get(ProblemHistory_.problemHistoryModifiedOn), builder.literal("EDT"), builder.literal("MM/DD/YYYY HH24:MI:SS AM")),
					root.get(ProblemHistory_.problemHistoryMessage),
					builder.function("date_diff", Double.class, builder.literal(2), root.get(ProblemHistory_.problemHistoryModifiedOn), builder.literal(new java.util.Date(new java.util.Date().getTime())))));
			criteriaQuery.where(builder.equal(root.get(ProblemHistory_.problemHistoryProblemUniqueid), problemUniqueId));
			criteriaQuery.orderBy(builder.desc(root.get(ProblemHistory_.problemHistoryModifiedOn)));
			
			List<OPConversationBean> resultList = em.createQuery(criteriaQuery).getResultList();
			
			for(int beanVar=0; beanVar<resultList.size(); beanVar++)
			{
				if(resultList.get(beanVar).getSenderName().contains("Billing") || resultList.get(beanVar).getSenderName().contains("GW"))
				{
					resultList.get(beanVar).setSenderType("Billing");
					resultList.get(beanVar).setMessageMode(1);
				}
				else
				{
					resultList.get(beanVar).setSenderType("Doctor Office");
					resultList.get(beanVar).setMessageMode(2);
				}
				
				resultList.get(beanVar).setMinutesAgo(setMinutesAgo(resultList.get(beanVar).getMinutesBetween()));
			}
			
			return resultList;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
		finally
		{
			em.close();
		}
	}

	/*private OpenProblemsResponseBean setResponse(int mode,OpenProblemsRequestBean openProblemsRequestBean, Exception exception) {
		// TODO Auto-generated method stub
		OpenProblemsResponseBean finalResponseBean = new OpenProblemsResponseBean();
		if(mode == 1)
		{
			finalResponseBean.setStatus(true);
		}
		else
		{
			finalResponseBean.setStatus(false);
		}
		return finalResponseBean;
	}*/
	
	private CommonResponseBean setResponse(int mode, CompleteOPBean openProblemsRequestBean, Exception exception) {
		// TODO Auto-generated method stub
		CommonResponseBean finalResponseBean = new CommonResponseBean();
		if(mode == 1)
		{
			finalResponseBean.setResponseStatus("Success");
			finalResponseBean.setAccountServerIp(openProblemsRequestBean.getAccServerIp());
			finalResponseBean.setEmpId(openProblemsRequestBean.forwardedBy);
			finalResponseBean.setDenialId(openProblemsRequestBean.getOpId());
			finalResponseBean.setTicketNo(HUtil.Nz(openProblemsRequestBean.getProblemUniqueId(), ""));
			finalResponseBean.setActionId(HUtil.Nz(openProblemsRequestBean.getOpActionId(), "-1"));
			finalResponseBean.setFailedReason("--");
		}
		else
		{
			finalResponseBean.setResponseStatus("Failed");
			finalResponseBean.setAccountServerIp(openProblemsRequestBean.getAccServerIp());
			finalResponseBean.setEmpId(openProblemsRequestBean.forwardedBy);
			finalResponseBean.setDenialId(openProblemsRequestBean.getOpId());
			finalResponseBean.setTicketNo(HUtil.Nz(openProblemsRequestBean.getProblemUniqueId(), ""));
			finalResponseBean.setActionId(HUtil.Nz(openProblemsRequestBean.getOpActionId(), "-1"));
			finalResponseBean.setFailedReason(exception.getMessage());
		}
		return finalResponseBean;
	}

	private Integer getSequence(Integer problemUniqueId) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
		Root<ProblemHistory> root = criteriaQuery.from(ProblemHistory.class);
		
		criteriaQuery.select(builder.max(root.get(ProblemHistory_.problemHistorySequence)));
		criteriaQuery.where(builder.equal(root.get(ProblemHistory_.problemHistoryProblemUniqueid), problemUniqueId));
		Integer result=em.createQuery(criteriaQuery).getMaxResults();
		System.out.println("squence>>>>>>>>."+result);
		return result;
	}

	public String setMinutesAgo(Double minutesBetween) 
	{
		Integer hours = (int) (minutesBetween / 60);
		String minutesAgoString = "";
		if(hours < 1)
			minutesAgoString = minutesBetween.intValue()+" "+"minutes ago";
		else if(hours >= 1 && hours < 24)
			minutesAgoString = hours+" hrs "+ (int) (minutesBetween % 60)+" "+"minutes ago";
		else if(hours > 24)
			minutesAgoString = (hours / 24)+" days "+ (int) (hours % 24)+" hrs "+ (int) (minutesBetween % 60)+" "+"minutes ago";
		
		return minutesAgoString;
	}


	@Override
	public List<Object> getReptdToOffice1() {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> createquery = builder.createQuery();
		Root<InitialSettings> rt = createquery.from(InitialSettings.class);
		createquery.select(rt.get(InitialSettings_.initialSettingsOptionValue));
		createquery.where(builder.isTrue(rt.get(InitialSettings_.initialSettingsVisible)),
				builder.equal(rt.get(InitialSettings_.initialSettingsOptionNameDesc), "DocOffReportedTo"),
				builder.equal(rt.get(InitialSettings_.initialSettingsOptionType), 2));
		List<Object> tempBeanList = em.createQuery(createquery).getResultList();
		
		
		ArrayList<Integer> userIds = new ArrayList<>();
		for(Object x : tempBeanList){
			String usersList = (String)x;
			String[] temp = usersList.split("[,]");
			for(String y : temp)
				userIds.add(Integer.parseInt(y));
		}
		builder = em.getCriteriaBuilder();
		 createquery = builder.createQuery();
		Root<LoginUsers> root = createquery.from(LoginUsers.class);
		createquery.select(builder.upper(root.get(LoginUsers_.loginUsersUsername)));
		createquery.where(root.get(LoginUsers_.loginUsersId).in(userIds));
		List<Object> tempBeanList1 = em.createQuery(createquery).getResultList();
		
		return tempBeanList1;
	}

}