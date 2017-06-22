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
import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.services.ar.ArActionsServices;
import com.glenwood.glaceemr.server.application.services.ar.ArDetailBean;
import com.glenwood.glaceemr.server.application.services.ar.ArResponseBean;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "ArActions", description = "ArActions")
@RestController
@RequestMapping(value="/ArActions")

public class ArController {

	@Autowired
	ArActionsServices arServices;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value="/getAllArData",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getAllArData(@RequestParam(value="fromDate", required=false, defaultValue="2016-01-01") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception{

		List<ArDetailBean> resultList=arServices.getAllArFollowUP(fromDate,toDate);
		ArResponseBean  arResponseBean= new ArResponseBean();
		arResponseBean.setArResponse(resultList);
		arResponseBean.setStatus(true);
		BillingResponseBean response=new BillingResponseBean();
		response.setData(arResponseBean);
		return response;  
	}
	
	@RequestMapping(value="/actions",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponseBean actionSaveAndUpdate(@RequestBody CommonActionBean commonActionBean) throws Exception
	{
		System.out.println("<--------Request------->"+objectMapper.writeValueAsString(commonActionBean));
			
		arServices.getArReasonId(commonActionBean);
		arServices.getBillingReasonId(commonActionBean);
		arServices.getArTypeId(commonActionBean);
		switch(commonActionBean.getActionId())
		{
			case 9:return arServices.billToPatAction(commonActionBean);
			
			case 10:return arServices.changeArCategoryAction(commonActionBean);
			
			case 15:return arServices.changeCptAction(commonActionBean);
			
			case 19:return arServices.changeCptChargesAction(commonActionBean);
			
			case 32:return arServices.changeDxAction(commonActionBean);
			
			case 39:return arServices.changeMod1Action(commonActionBean);
			
			case 40:return arServices.changeMod2Action(commonActionBean);
			
			case 6:return arServices.changePrimaryInsuranceAction(commonActionBean);
			
			case 20:return arServices.changeSecondaryInsuranceAction(commonActionBean);
			
			case 31:return arServices.changeSubmitStatusAction(commonActionBean);
			
			case 1:return arServices.claimToPrimaryAction(commonActionBean);
			
			case 2:return arServices.claimToSecondaryAction(commonActionBean);
			
			case 27:return arServices.followUpAction(commonActionBean);
			
			case 7:break;
			
			case 22:return arServices.markAsApppealAction(commonActionBean);
			
			case 23:return arServices.markAsBadDebtAction(commonActionBean);
			
			case 5:return arServices.markAsCapitationAction(commonActionBean);
			
			case 24:return arServices.markAsDuplicateAction(commonActionBean);
			
			case 28:return arServices.markAsFaxAction(commonActionBean);
			
			case 37:return arServices.markAsFullySettledAction(commonActionBean);
			
			case 26:return arServices.markAsOnHoldAction(commonActionBean);
			
			case 25:return arServices.markAsUncollectableAction(commonActionBean);
			
			case 29:return arServices.markAsWebAction(commonActionBean);
			
			case 18:return arServices.reportAProblem(commonActionBean);
			
			case 36:return arServices.toBeCalledAction(commonActionBean);
			
			case 52:return arServices.toBeCalledCompletedAction(commonActionBean);
		
			case 4:return arServices.writeoffAction(commonActionBean);
				
		}
		return null;
	}
	
}