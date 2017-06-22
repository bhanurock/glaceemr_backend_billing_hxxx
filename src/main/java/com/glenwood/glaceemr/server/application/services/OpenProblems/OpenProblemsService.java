package com.glenwood.glaceemr.server.application.services.OpenProblems;

import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;

public interface OpenProblemsService 
{
	List<ServiceDetail> serviceDetailId(String serviceDetailId);
	List<OpenProblemsBean> getAllOpenPRoblems(String fromDate,String toDate);
	CommonResponseBean editOPAction(CompleteOPBean openProblemsRequestBean);
	CommonResponseBean forwardOPAction(CompleteOPBean openProblemsRequestBean);
	List<OPConversationBean> getOPConversations(Integer problemUniqueId);
	CommonResponseBean replyAction(CompleteOPBean openProblemsRequestBean);
	CommonResponseBean closeAction(CompleteOPBean openProblemsRequestBean);
	List<Object> getReptdToOffice1();
	
}