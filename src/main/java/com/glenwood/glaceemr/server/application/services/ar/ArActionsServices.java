package com.glenwood.glaceemr.server.application.services.ar;

import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;

public interface ArActionsServices 
{
	Short getArReasonId(CommonActionBean commonActionBean);

	Integer getBillingReasonId(CommonActionBean commonActionBean);

	Short getArTypeId(CommonActionBean commonActionBean);

	//Short getArCategoryId(CommonActionBean commonActionBean);
	
	Integer getProblemTypeId(CommonActionBean commonAction);
	
	CommonResponseBean billToPatAction(CommonActionBean billToPatBean);
	CommonResponseBean changeArCategoryAction(CommonActionBean changeArCategoryBean);
	CommonResponseBean changeCptAction(CommonActionBean changeCptBean);
	CommonResponseBean changeCptChargesAction(CommonActionBean changeCptChargesBean);
	CommonResponseBean changeDxAction(CommonActionBean changeDxBean);
	CommonResponseBean changeMod1Action(CommonActionBean changeMod1Bean);
	CommonResponseBean changeMod2Action(CommonActionBean changeMod2Bean);
	CommonResponseBean changePrimaryInsuranceAction(CommonActionBean changePrimaryInsuranceBean);
	CommonResponseBean changeSecondaryInsuranceAction(CommonActionBean changeSecondaryInsuranceBean);
	CommonResponseBean changeSubmitStatusAction(CommonActionBean changeSubmitStatusBean);
	CommonResponseBean claimToPrimaryAction(CommonActionBean claimToPrimaryBean);
	CommonResponseBean claimToSecondaryAction(CommonActionBean claimToSecondaryBean);
	CommonResponseBean followUpAction(CommonActionBean followUpBean);
	CommonResponseBean markAsApppealAction(CommonActionBean markAsApppealBean);
	CommonResponseBean markAsBadDebtAction(CommonActionBean markAsBadDebtBean);
	CommonResponseBean markAsCapitationAction(CommonActionBean markAsCapitationBean);
	CommonResponseBean markAsDuplicateAction(CommonActionBean markAsDuplicateBean);
	CommonResponseBean markAsFaxAction(CommonActionBean markAsFaxBean);
	CommonResponseBean markAsFullySettledAction(CommonActionBean markAsFullySettledBean);
	CommonResponseBean markAsOnHoldAction(CommonActionBean markAsOnHoldBean);
	CommonResponseBean markAsUncollectableAction(CommonActionBean markAsUncollectableBean);
	CommonResponseBean markAsWebAction(CommonActionBean markAsWebBean);
	CommonResponseBean reportAProblem(CommonActionBean reportAProblemBean);
	CommonResponseBean toBeCalledAction(CommonActionBean toBeCalledBean);
	CommonResponseBean toBeCalledCompletedAction(CommonActionBean toBeCalledCompletedBean);
	CommonResponseBean writeoffAction(CommonActionBean writeoffBean);

	List<ArDetailBean> getAllArFollowUP(String fromDate, String toDate);
	
}