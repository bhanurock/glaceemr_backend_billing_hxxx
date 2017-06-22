package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.services.era.ERADetailsBean;
import com.glenwood.glaceemr.server.application.services.era.ERAResponseBean;
import com.glenwood.glaceemr.server.application.services.era.ERAService;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "ERA", description = "ERA")
@RestController
@RequestMapping(value="/ERA")
public class ERAController 
{
	
	@Autowired ERAService eraService;
	
	@Autowired ObjectMapper objectMapper;
	
	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param mode indicates unposted, partial or pending ERA; 1 - unposted; 2 - partial; 3 - pending
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllERA",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getAllERA(@RequestParam(value="fromDate", required=false, defaultValue="") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="") String toDate,
			@RequestParam(value="mode", required=false, defaultValue="") Integer mode) throws Exception
	{
		BillingResponseBean finalResponseList = new BillingResponseBean();
		ERAResponseBean eraDataBean = new ERAResponseBean();
		List<ERADetailsBean> resultSet = null;
		if(mode == 1)
			resultSet = eraService.getERADetails(fromDate, toDate);
		else if(mode == 2)
			resultSet = eraService.getAllPartialERA(fromDate, toDate);
		else if(mode == 3)
			resultSet = eraService.getAllPendingERA(fromDate, toDate);
		
		if(resultSet == null)
			finalResponseList.setSuccess(false);
		else
			finalResponseList.setSuccess(true);
		
		eraDataBean.setStatus(true);
		eraDataBean.setEraResponse(resultSet);
		finalResponseList.setData(eraDataBean);
		return finalResponseList;
	}
	
}