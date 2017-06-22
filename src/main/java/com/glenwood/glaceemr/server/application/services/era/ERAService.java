package com.glenwood.glaceemr.server.application.services.era;

import java.util.List;

public interface ERAService 
{
	List<ERADetailsBean> getERADetails(String fromDate, String toDate);

	List<ERADetailsBean> getAllPendingERA(String fromDate, String toDate);

	List<ERADetailsBean> getAllPartialERA(String fromDate, String toDate);
}