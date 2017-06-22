package com.glenwood.glaceemr.server.application.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.services.Analytics.AnalyticsService;
import com.wordnik.swagger.annotations.Api;


@Api(value = "Analytics", description = "Analytics")
@RestController
@RequestMapping(value="/Analytics")


public class AnalyticsController {
	
	
	@Autowired
	AnalyticsService analyticsService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value = "/automationBillingData", method = RequestMethod.GET)
	@ResponseBody
	public String automationBillingData(
			
	@RequestParam(value="module",required=true, defaultValue="1") Integer module) throws Exception{
		
		return analyticsService.getAutomationBillingData();
	}
	
	@RequestMapping(value = "/abnormalDashBoard", method = RequestMethod.GET)
	@ResponseBody
	public String abnormalDashBoard(@RequestParam(value = "nonServiceDetailPaidBy", required = true, defaultValue = "1") Integer nonServiceDetailPaidBy)
			throws JSONException {
		return analyticsService.abnormalDashBoard(nonServiceDetailPaidBy).toString();
	}


}