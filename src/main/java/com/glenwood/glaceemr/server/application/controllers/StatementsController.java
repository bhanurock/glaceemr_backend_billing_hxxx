package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.stmts.StatementsBean;
import com.glenwood.glaceemr.server.application.services.stmts.StatementsResponseBean;
import com.glenwood.glaceemr.server.application.services.stmts.StatementsServices;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "Statements", description = "Statements")
@RestController
@RequestMapping(value="/Statements")

public class StatementsController {
	
	@Autowired
	StatementsServices statementsServices;
	
	/**
	* @author S.Naveen Kumar
	**/
	@RequestMapping(value="/getStatementData",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getOnholdData(@RequestParam(value="fromDate", required=false, defaultValue="2016-01-01") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception{

		List<StatementsBean> resultList=statementsServices.getStatementData(fromDate,toDate);
		StatementsResponseBean  statementsResponseBean= new StatementsResponseBean();
		statementsResponseBean.setSelectionResponse(resultList);
		statementsResponseBean.setStatus(true);
		BillingResponseBean response=new BillingResponseBean();
		response.setData(statementsResponseBean);
		return response;  
	}
}
