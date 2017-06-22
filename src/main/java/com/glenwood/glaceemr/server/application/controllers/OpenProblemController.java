package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.services.OpenProblems.CompleteOPBean;
import com.glenwood.glaceemr.server.application.services.OpenProblems.OPConversationBean;
import com.glenwood.glaceemr.server.application.services.OpenProblems.OpenProblemsBean;
import com.glenwood.glaceemr.server.application.services.OpenProblems.OpenProblemsResponseBean;
import com.glenwood.glaceemr.server.application.services.OpenProblems.OpenProblemsService;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "OpenProblem", description = "OpenProblem")
@RestController
@RequestMapping(value="/OpenProblem")
public class OpenProblemController {

	@Autowired
	OpenProblemsService openProblemsService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value="/getAllOpenProblems",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getAllOpenProblems(@RequestParam(value="fromDate", required=false, defaultValue="2016-01-01") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception{

		List<OpenProblemsBean> resultList=openProblemsService.getAllOpenPRoblems(fromDate,toDate);
		OpenProblemsResponseBean denialResponse = new OpenProblemsResponseBean();
		denialResponse.setDenialResponse(resultList);
		denialResponse.setStatus(true);
		BillingResponseBean response=new BillingResponseBean();
		response.setData(denialResponse);
		return response;  
	}
	
	@RequestMapping(value="/opActions",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponseBean opActions(@RequestBody CompleteOPBean openProblemsRequestBean) throws Exception
	{
		System.out.println("request---->"+objectMapper.writeValueAsString(openProblemsRequestBean));
		switch(openProblemsRequestBean.getOpActionId())
		{
			case 1: return openProblemsService.editOPAction(openProblemsRequestBean);
			
			case 2: return openProblemsService.forwardOPAction(openProblemsRequestBean);
			
			case 3: return openProblemsService.replyAction(openProblemsRequestBean);
			
			case 4: return openProblemsService.closeAction(openProblemsRequestBean);
		}
		return null;
		
	}
	
	@RequestMapping(value="/getOPConversations",method=RequestMethod.GET)
	@ResponseBody
	public List<OPConversationBean> getOPConversations(@RequestParam(value="problemUniqueId",required=false, defaultValue="") Integer problemUniqueId) throws Exception
	{
		return openProblemsService.getOPConversations(problemUniqueId);
	}
	
	@RequestMapping(value="/opReported",method=RequestMethod.GET)
	@ResponseBody
	public List<Object> opCounts() throws Exception
	{
		List<Object> reportedToOffice=openProblemsService.getReptdToOffice1();
		
		return reportedToOffice;
	}
}