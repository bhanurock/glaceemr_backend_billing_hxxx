package com.glenwood.glaceemr.server.application.services.stmts;

import java.util.List;

public interface StatementsServices 
{
	public List<StatementsBean> getStatementData(String fromDate, String toDate); 
}
