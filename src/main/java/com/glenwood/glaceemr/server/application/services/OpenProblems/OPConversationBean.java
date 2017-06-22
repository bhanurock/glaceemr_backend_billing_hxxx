package com.glenwood.glaceemr.server.application.services.OpenProblems;

/**
 * @author Ananth
 * To get the info about OP conversation for particular ticket.
 */

public class OPConversationBean 
{
	String messageContent;
	String sentTime;
	String senderType;
	String senderName;
	Double minutesBetween;
	String minutesAgo;
	Integer messageMode; //Distinguish between sender & receiver.
	Integer messageSeq;
	String receiverName;
	
	public OPConversationBean(Integer messageSeq, String senderName, String receiverName, String sentTime, 
			String messageContent, Double minutesBetween) 
	{
		super();
		this.messageSeq = messageSeq;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.sentTime = sentTime;
		this.messageContent = messageContent;
		this.minutesBetween = minutesBetween;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getSentTime() {
		return sentTime;
	}
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	public String getSenderType() {
		return senderType;
	}
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Double getMinutesBetween() {
		return minutesBetween;
	}
	public void setMinutesBetween(Double minutesBetween) {
		this.minutesBetween = minutesBetween;
	}
	public String getMinutesAgo() {
		return minutesAgo;
	}
	public void setMinutesAgo(String minutesAgo) {
		this.minutesAgo = minutesAgo;
	}
	public Integer getMessageMode() {
		return messageMode;
	}
	public void setMessageMode(Integer messageMode) {
		this.messageMode = messageMode;
	}
	
}