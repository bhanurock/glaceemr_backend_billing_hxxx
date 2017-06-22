package com.glenwood.glaceemr.server.application.services.Denial;

import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.ClaimInfoBean;
import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.Bean.ManagerInfoBean;
import com.glenwood.glaceemr.server.application.Bean.PatientInsuranceInfoBean;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;

public interface DenialService {

	List<ServiceDetail> serviceDetailId(String serviceDetailId);
	List<DenialBean> getAllDenial(String fromDate,String toDate);
	
	List<BulkDenialBean> getAllCommentDenial(String fromDate,String toDate);
	
	//Denial Actions
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
	CommonResponseBean readyForOtherInsuranceAction(CommonActionBean readyForOtherInsuranceBean);
	CommonResponseBean reportAProblem(CommonActionBean reportAProblemBean);
	CommonResponseBean toBeCalledAction(CommonActionBean toBeCalledBean);
	CommonResponseBean toBeCalledCompletedAction(CommonActionBean toBeCalledCompletedBean);
	CommonResponseBean writeoffAction(CommonActionBean writeoffBean);
	
	public List<PatientInsuranceInfoBean> getPatientInsInfo(Integer patientId,Integer type);
	public List<ClaimInfoBean> getServicesByClaim(Integer patientId, String type);
	
	Short getDenialReasonId(CommonActionBean commonAction);
	Integer getBillingReasonId(CommonActionBean commonAction);
	Short getDenialTypeId(CommonActionBean commonAction);
	Short getDenialCategoryId(CommonActionBean commonAction);
	Integer getProblemTypeId(CommonActionBean commonAction);
	Integer isMarkAsActionTaken(CommonActionBean commonActionBean);
	Integer getProblemTypeIdDesc(CommonActionBean commonAction);
	List<ManagerInfoBean> getMarkAsCompleted(ManagerInfoBean managerInfoBean);
}
