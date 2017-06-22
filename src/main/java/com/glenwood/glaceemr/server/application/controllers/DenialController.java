package com.glenwood.glaceemr.server.application.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.ClaimInfoBean;
import com.glenwood.glaceemr.server.application.Bean.CommonActionBean;
import com.glenwood.glaceemr.server.application.Bean.CommonResponseBean;
import com.glenwood.glaceemr.server.application.Bean.ManagerInfoBean;
import com.glenwood.glaceemr.server.application.Bean.PatientInsuranceInfoBean;
import com.glenwood.glaceemr.server.application.services.Denial.BulkDenialBean;
import com.glenwood.glaceemr.server.application.services.Denial.DenialBean;
import com.glenwood.glaceemr.server.application.services.Denial.DenialResponseBean;
import com.glenwood.glaceemr.server.application.services.Denial.DenialService;
import com.glenwood.glaceemr.server.application.services.Denial.DenialServiceImpl;
import com.glenwood.glaceemr.server.utils.BillingResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.MailUtil;
import com.glenwood.glaceemr.server.utils.Utilities;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value = "Denials", description = "Denial")
@RestController
@RequestMapping(value="/Denials")
public class DenialController {

	@Autowired
	DenialService dservice;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value="/getAllDenials",method=RequestMethod.GET)
	@ResponseBody
	public BillingResponseBean getAllDenail(@RequestParam(value="fromDate", required=false, defaultValue="") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception{

		List<DenialBean> resultList=dservice.getAllDenial(fromDate,toDate);
		DenialResponseBean denialResponse = new DenialResponseBean();
		denialResponse.setDenialResponse(resultList);
		denialResponse.setStatus(true);
		BillingResponseBean response=new BillingResponseBean();
		response.setData(denialResponse);
		return response;  
	}
	
	@RequestMapping(value="/actions",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponseBean actionSaveAndUpdate(@RequestBody CommonActionBean commonActionBean) throws Exception
	{
		try
		{
			System.out.println("<--------Request------->"+objectMapper.writeValueAsString(commonActionBean));
			
			Integer temp = dservice.isMarkAsActionTaken(commonActionBean);
			if(temp != -1)
			{
				System.out.println("Mark as Action taken");
				DenialServiceImpl denialServiceImpl = new DenialServiceImpl();
				commonActionBean.setActionId(temp);
				return denialServiceImpl.setResponse(1, commonActionBean, null);
			}
			else
			{
				System.out.println(">>>Non service>>>>>>"+commonActionBean.getNonServiceId());
				dservice.getDenialReasonId(commonActionBean);
				dservice.getBillingReasonId(commonActionBean);
				dservice.getDenialTypeId(commonActionBean);
				dservice.getDenialCategoryId(commonActionBean);
				switch(Integer.parseInt(HUtil.Nz(commonActionBean.getActionId(), "0")))
				{
					case 9:return dservice.billToPatAction(commonActionBean);
					
					case 10:return dservice.changeArCategoryAction(commonActionBean);
					
					case 15:return dservice.changeCptAction(commonActionBean);
					
					case 19:return dservice.changeCptChargesAction(commonActionBean);
					
					case 32:return dservice.changeDxAction(commonActionBean);
					
					case 39:return dservice.changeMod1Action(commonActionBean);
					
					case 40:return dservice.changeMod2Action(commonActionBean);
					
					case 6:return dservice.changePrimaryInsuranceAction(commonActionBean);
					
					case 20:return dservice.changeSecondaryInsuranceAction(commonActionBean);
					
					case 31:return dservice.changeSubmitStatusAction(commonActionBean);
					
					case 1:return dservice.claimToPrimaryAction(commonActionBean);
					
					case 2:return dservice.claimToSecondaryAction(commonActionBean);
					
					case 27:return dservice.followUpAction(commonActionBean);
					
					case 7:break;
					
					case 22:return dservice.markAsApppealAction(commonActionBean);
					
					case 23:return dservice.markAsBadDebtAction(commonActionBean);
					
					case 5:return dservice.markAsCapitationAction(commonActionBean);
					
					case 24:return dservice.markAsDuplicateAction(commonActionBean);
					
					case 28:return dservice.markAsFaxAction(commonActionBean);
					
					case 37:return dservice.markAsFullySettledAction(commonActionBean);
					
					case 26:return dservice.markAsOnHoldAction(commonActionBean);
					
					case 25:return dservice.markAsUncollectableAction(commonActionBean);
					
					case 29:return dservice.markAsWebAction(commonActionBean);
					
					case 18:return dservice.reportAProblem(commonActionBean);
					
					case 36:return dservice.toBeCalledAction(commonActionBean);
					
					case 52:return dservice.toBeCalledCompletedAction(commonActionBean);
				
					case 4:return dservice.writeoffAction(commonActionBean);
					
					default: return null;
				}
			}		
		}
		catch(Exception exception)
		{
			CommonResponseBean commonResponseBean = new CommonResponseBean();
			commonResponseBean.setAccountServerIp(commonActionBean.getAccountServerIp());
			commonResponseBean.setResponseStatus("Failed");
			commonResponseBean.setFailedReason("Connection Failed");
			
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			String[] toids = {"shadab@glenwoodsystems.com", "ananth@glenwoodsystems.com"};
			String[] ccids = {""};
			String[] bccid = {""};
			MailUtil.sendMail("BCException - Glace actions - Acct: "+HUtil.Nz(commonActionBean.getAccountServerIp(), ""), stringWriter.toString(), toids, ccids, bccid);
			return commonResponseBean;
		}
		return null;
	}
	
	@RequestMapping(value="/bulkActions",method=RequestMethod.GET)
	@ResponseBody
	public List<BulkDenialBean> bulkActionSaveAndUpdate(@RequestParam(value="fromDate", required=false, defaultValue="2016-01-01") String fromDate,
			@RequestParam(value="toDate",required=false, defaultValue="") String toDate) throws Exception
	{
		String s="{\"accountId\":777, \"actionId\":27, \"accountServerIp\":\"dev2\", "
				+ "\"modifiedBy\":\"GW159\", \"modifiedDate\":null, \"rowId\":null,"
				+ " \"innerRowId\":null, \"patientAccountId\":\"1000415\", \"taskId\":26, "
				+ "\"denialId\":88797, \"arCategory\":null, \"billingReason\":null, "
				+ "\"checkAmount\":null, \"checkDate\":null, \"checkNo\":null, \"coins\":null, "
				+ "\"copay\":null, \"cptCode\":null, \"deductible\":null, \"denialCategory\":"
				+ "\"Insurance\", \"denialReason\":\"Others\", \"denialType\":\"Others\", \"dx1\":"
				+ "null, \"dx2\":null, \"dx3\":null, \"dx4\":null, \"fileUpload\":null, "
				+ "\"icnNo\":null, \"insuranceBalance\":null, \"insuranceName\":null, "
				+ "\"newCptCharges\":null, \"newCpt\":null, \"newCptCode\":null, \"newMod1\":null, "
				+ "\"newMod2\":null, \"nextFollowupAction\":\"CP_Kenpac/Medipass\", "
				+ "\"nextFollowupDate\":\"05/03/2017\", \"notes\":\"\", \"oldCpt\":null, "
				+ "\"oldCptCharges\":null, \"primaryInsurance\":\"ACMG\", \"primaryInsuranceId\":"
				+ "\"12345\", \"payment\":null, \"problem\":null, \"problemStatus\":null,"
				+ " \"problemType\":\"Appeal\", \"problemNotes\":null, \"reason\":null, "
				+ "\"reference\":null, \"resubmitTo\":null, \"secondaryInsurance\":\"null\", \"secondaryInsuranceId\":\"null\", \"selectionValue\":null, \"subject\":null, \"to\":null, \"fileNames\":null, \"filePath\":null, \"writeOffReason\":null, \"writeOffAmount\":null, \"submitStatus\":\"R\", \"dos\":\"09/01/2016\", \"denialCpt\":\"90713\", \"serviceDoctor\":null, \"charges\":12.0, \"allowed\":null, \"insBal\":-19.56, \"denialCode\":null, \"postStatus\":null, \"units\":null, \"dxCodes\":null, \"serviceId\":36315, \"dop\":\"10/05/2016\", \"patientId\":1000415, \"modifier\":null, \"nonServiceId\":512831, \"actionDescription\":\"Followup\", \"actionReason\":43, \"isRecent\":true, \"moduleId\":null, \"problemId\":4116, \"serviceStatusCode\":\"R\", \"patientName\":\"TEST,TEST\", \"flag\":null, \"isFromAr\":null, \"denialTaskCount\":null, \"serviceAsPaperClaim\":0, \"serviceAsCorrectedClaim\":0, \"serviceWholeClaims\":0, \"ticketNo\":null, \"arId\":null }";
		
		System.out.println(">>>>>>>>>"+objectMapper.writeValueAsString(s));
		
		JSONObject obj=new JSONObject(s);
		System.out.println(">>>Obj===>"+obj.toString());
		
		List<BulkDenialBean> result=dservice.getAllCommentDenial(fromDate, toDate);
		
		CommonActionBean tmp =objectMapper.readValue(s, CommonActionBean.class);
		for(int i=0;i<result.size();i++){
			
			tmp.setNonServiceId(Integer.parseInt(result.get(i).getNonServiceId()+""));
			tmp.setServiceId(result.get(i).getServiceId());
			dservice.followUpAction(tmp);
		}
			
		System.out.println("Request=======>"+objectMapper.writeValueAsString(tmp));
		return result;
	}
	
	@RequestMapping(value="/getInsid",method=RequestMethod.GET)
	@ResponseBody
	public List<PatientInsuranceInfoBean> getPrimaryInsurance(@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId, 
			@RequestParam(value="insType", required=false, defaultValue="") Integer insType) throws Exception{

		return dservice.getPatientInsInfo(patientId,insType);  
	}
	
	@RequestMapping(value="/getClaimInfo",method=RequestMethod.GET)
	@ResponseBody
	public List<ClaimInfoBean> getClaimInfo(@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId, 
			@RequestParam(value="claimNo", required=false, defaultValue="") String claimNo) throws Exception{

		List<ClaimInfoBean> result=dservice.getServicesByClaim(patientId, claimNo);
		return result;
	}
	

	@RequestMapping(value="/upload/fileUpload", method = RequestMethod.POST, produces= "text/html")	
	@ResponseBody
	public String uploadFile(
			@ApiParam(name="file", value="") 
			@RequestParam(value="file", required=false, defaultValue="") MultipartFile file,
			@ApiParam(name="patientId", value="") 
			@RequestParam(value="patientId", required=false, defaultValue="0") Integer patientId,
			@ApiParam(name="accountId", value="") 
			@RequestParam(value="accountId", required=false, defaultValue="0") String accountId)
	{ 
		HttpStatus responseCode=null;
		try{Assert.notNull(file, "uploaded file is empty");
		Assert.notNull(patientId, "entity Id is not passed");
		String connectionpath="https://sso.glaceemr.com/TestSSOAccess?accountId="+accountId;
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(connectionpath, HttpMethod.GET, entity, String.class, params);
		responseCode=result.getStatusCode();
		String reasonsListBuffer = null;
		if(result.hasBody())
			reasonsListBuffer=result.getBody();

		JSONObject responseObject=new JSONObject(reasonsListBuffer.toString());
		String absolutePath = responseObject.getString("shared_folder_path")+"BlackBoard/";
		absolutePath="/home/software/Desktop/";
		Assert.notNull(absolutePath, "absolute path is empty");
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(absolutePath+patientId+Utilities.getResponseContentType(file.getContentType()))));
		stream.write(bytes);
		stream.close();
		System.out.println("File uploaded successfully!");
		return "<html> uploaded successfully!</html>";

		}catch(Exception ex){
			ex.printStackTrace();
			return " upload failed";
		}
	}
	
	@RequestMapping(value="/getMarkAsCompleted",method=RequestMethod.POST)
	@ResponseBody
	public List<ManagerInfoBean> getMarkAsCompleted(@RequestBody List<ManagerInfoBean> managerInfoBean) throws Exception
	{
		List<ManagerInfoBean> finalResult = new ArrayList<ManagerInfoBean>();
		System.out.println(">>mark as completed bean size>>>>"+managerInfoBean.size());
		for(int i=0 ;i<managerInfoBean.size();i++)
		{
			ManagerInfoBean result=managerInfoBean.get(i);
			List<ManagerInfoBean>  finalList=	dservice.getMarkAsCompleted(result);
			finalResult.addAll(finalList);
		}
	
		return finalResult;
	}
	
}