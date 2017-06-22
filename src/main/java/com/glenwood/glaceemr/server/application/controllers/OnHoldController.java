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
import com.glenwood.glaceemr.server.application.services.onHold.OnHoldActionsServices;
import com.glenwood.glaceemr.server.application.services.onHold.OnHoldDetailBean;
import com.glenwood.glaceemr.server.application.services.onHold.OnHoldResponseBean;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "OnHold", description = "OnHold")
@RestController
@RequestMapping(value="/OnHold")

public class OnHoldController {

	@Autowired
	OnHoldActionsServices onHoldServices;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value="/getAllOnHoldData",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getAllOnHoldData(@RequestParam(value="fromDate", required=false, defaultValue="2016-01-01") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception{

		List<OnHoldDetailBean> resultList=onHoldServices.getAllArFollowUP(fromDate, toDate);
		OnHoldResponseBean onHoldResponseBean = new OnHoldResponseBean();
		onHoldResponseBean.setDenialResponse(resultList);
		onHoldResponseBean.setStatus(true);
		BillingResponseBean response=new BillingResponseBean();
		response.setData(onHoldResponseBean);
		return response;  
	}
	
	@RequestMapping(value="/actions",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponseBean actionSaveAndUpdate(@RequestBody CommonActionBean commonActionBean) throws Exception
	{
		System.out.println("<--------Request------->"+objectMapper.writeValueAsString(commonActionBean));
			
		onHoldServices.getArReasonId(commonActionBean);
		onHoldServices.getBillingReasonId(commonActionBean);
		onHoldServices.getArTypeId(commonActionBean);
		switch(commonActionBean.getActionId())
		{
			case 9:return onHoldServices.billToPatAction(commonActionBean);
			
			case 10:return onHoldServices.changeArCategoryAction(commonActionBean);
			
			case 15:return onHoldServices.changeCptAction(commonActionBean);
			
			case 19:return onHoldServices.changeCptChargesAction(commonActionBean);
			
			case 32:return onHoldServices.changeDxAction(commonActionBean);
			
			case 39:return onHoldServices.changeMod1Action(commonActionBean);
			
			case 40:return onHoldServices.changeMod2Action(commonActionBean);
			
			case 6:return onHoldServices.changePrimaryInsuranceAction(commonActionBean);
			
			case 20:return onHoldServices.changeSecondaryInsuranceAction(commonActionBean);
			
			case 31:return onHoldServices.changeSubmitStatusAction(commonActionBean);
			
			case 1:return onHoldServices.claimToPrimaryAction(commonActionBean);
			
			case 2:return onHoldServices.claimToSecondaryAction(commonActionBean);
			
			case 27:return onHoldServices.followUpAction(commonActionBean);
			
			case 7:break;
			
			case 22:return onHoldServices.markAsApppealAction(commonActionBean);
			
			case 23:return onHoldServices.markAsBadDebtAction(commonActionBean);
			
			case 5:return onHoldServices.markAsCapitationAction(commonActionBean);
			
			case 24:return onHoldServices.markAsDuplicateAction(commonActionBean);
			
			case 28:return onHoldServices.markAsFaxAction(commonActionBean);
			
			case 37:return onHoldServices.markAsFullySettledAction(commonActionBean);
			
			case 26:return onHoldServices.markAsOnHoldAction(commonActionBean);
			
			case 25:return onHoldServices.markAsUncollectableAction(commonActionBean);
			
			case 29:return onHoldServices.markAsWebAction(commonActionBean);
			
			case 18:return onHoldServices.reportAProblem(commonActionBean);
			
			case 36:return onHoldServices.toBeCalledAction(commonActionBean);
			
			case 52:return onHoldServices.toBeCalledCompletedAction(commonActionBean);
		
			case 4:return onHoldServices.writeoffAction(commonActionBean);
				
		}
		return null;
	}
}