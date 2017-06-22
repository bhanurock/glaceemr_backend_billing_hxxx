package com.glenwood.glaceemr.server.application.services.Analytics;

import org.json.JSONException;
import org.json.JSONObject;

public interface AnalyticsService {
	
	public String  getAutomationBillingData() throws JSONException;
	public JSONObject abnormalDashBoard(Integer nonServiceDetailPaidBy) throws JSONException;

}