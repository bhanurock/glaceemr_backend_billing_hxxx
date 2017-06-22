/*
    PLEASE USE HUtil.java
 *********************
 */
package com.glenwood.glaceemr.server.utils;

public class Utilities {

	public static String handleSymbols(String xValue){
		String newVal     = "";
		String newChar    = "";
		int initPosition  = 0;
		int finalPosition = xValue.length();
		for ( int i = initPosition; i<finalPosition; i++ ){
			newChar = xValue.substring(i,i+1);
			if  (newChar.equals("'") )
				newVal = newVal + "''";
			else if (newChar.equals("\\") ){
				newVal = newVal + "\\";
				newVal = newVal + newChar ; 
			}
			else
				newVal = newVal + newChar ; 
		}	
		return newVal;
	}

	public static String getResponseContentType(String fileType) {
		if(fileType.toLowerCase().equals("image/tiff"))
			return ".tif";
		else if(fileType.toLowerCase().equals("text/html"))
			return ".html";
		/*  else if(fileType.toLowerCase().equals("htm"))
	            return "text/html";*/
		else if(fileType.toLowerCase().equals("application/pdf"))
			return ".pdf";
		else if(fileType.toLowerCase().equals("text/xml"))
			return ".xsl";
		else if(fileType.toLowerCase().equals("text/xml"))
			return ".cda";
		else if(fileType.toLowerCase().equals("image/jpeg"))
			return ".jpg";
		else if(fileType.toLowerCase().equals("audio/wav"))
			return ".wav";
		else if(fileType.toLowerCase().equals("application/rtf"))
			return ".rtf";
		else if(fileType.toLowerCase().equals("image/png"))
			return ".png";
		else if(fileType.toLowerCase().equals("image/gif"))
			return ".gif";
		else if(fileType.toLowerCase().equals("application/x-java-jnlp-file"))
			return ".jnlp";
		else if(fileType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
			return ".docx";
		else if(fileType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
			return ".xlsx";
		else if(fileType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
			return ".pptx";
		else if(fileType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.template"))
			return ".dotx";
		else if(fileType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.template"))
			return ".xltx";
		else if(fileType.toLowerCase().equals("application/vnd.oasis.opendocument.spreadsheet"))
			return ".ods";
		else if(fileType.toLowerCase().equals("application/vnd.ms-excel"))
			return ".xls";
		else
			return ".txt";        
	}

}
