package com.glenwood.glaceemr.server.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.json.JSONObject;

public class MailUtil 
{
	/**
	 * To send mails from our own server.
	 * @param subject
	 * @param htmlbody
	 * @param toids
	 * @param ccids
	 * @param bccid
	 * @return
	 */
	public static String sendMail(String subject, String htmlbody, String[] toids, String[] ccids, String[] bccid)
	{
		try
		{
			String URL ="https://mailer1.glaceemr.com/Mailer/sendMail";
			String charSet = "UTF-8";
			String boundary = "===" + System.currentTimeMillis() + "===";
			MultipartUtility multipartUtility = new MultipartUtility(URL, charSet, boundary);
			HttpURLConnection httpURLConnection;
			String accountId="Glenwood";
			String mailpassword="demopwd0";
			int mailtype=4;		
			String plaintext="Please Check";
	
			/*File f3=new File("/mnt/attachments/ChartPlace.java");
			File f4=new File("/mnt/attachments/ChartView.java");
			File f5=new File("/mnt/attachments/computerscience.pdf");
			ArrayList<File> f= new ArrayList<File>();	
			f.add(f5);
			f.add(f3);
			f.add(f4);*/
	
			MailerResponse rb = new MailerResponse(mailtype, "donotreply@glenwoodsystems.com",
					toids, ccids, bccid, subject, htmlbody, plaintext, accountId, mailpassword);
		
			/*ObjectMapper mapper=new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(rb);*/
			
			JSONObject jsonInString=new JSONObject();
	        jsonInString.put("mailtype", rb.getmailtype());
	        jsonInString.put("sender", rb.getSender());
	        jsonInString.put("to", rb.getTo());
	        jsonInString.put("cc", rb.getCc());
	        jsonInString.put("bcc", rb.getBcc());
	        jsonInString.put("subject",rb.getSubject());
	        jsonInString.put("plaintext",rb.getPlaintext());
	        jsonInString.put("htmlbody", rb.getHtmlbody());
	        jsonInString.put("accountId", rb.getAccountId());
	        jsonInString.put("mailpassword", rb.getMailpassword());
	        
			multipartUtility.addFormField("mailerResp", jsonInString.toString());
		    //multipartUtility.addFilePart("attachments",f);
			httpURLConnection = multipartUtility.execute();
			httpURLConnection.connect();
		
			System.out.println(httpURLConnection.getResponseCode());
			System.out.println(httpURLConnection.getResponseMessage());
			if(httpURLConnection.getResponseCode()==200) 
			{
				InputStream inputStream = httpURLConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuilder out = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					out.append(line);
				}
				System.out.println(out.toString());   
				reader.close();
			}
			return "Success";
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return "Failed";
		}
	}
}