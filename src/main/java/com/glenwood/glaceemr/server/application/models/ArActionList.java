package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ar_action_list")
public class ArActionList {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ar_action_list_ar_action_list_id_seq")
	@SequenceGenerator(name ="ar_action_list_ar_action_list_id_seq", sequenceName="ar_action_list_ar_action_list_id_seq", allocationSize=1)
	@Column(name="ar_action_list_id")
	private Integer arActionListId;

	@Column(name="ar_action_list_action_name")
	private String arActionListActionName;

	@Column(name="ar_action_list_is_active")
	private Boolean arActionListIsActive;

	public Integer getArActionListId() {
		return arActionListId;
	}

	public void setArActionListId(Integer arActionListId) {
		this.arActionListId = arActionListId;
	}

	public String getArActionListActionName() {
		return arActionListActionName;
	}

	public void setArActionListActionName(String arActionListActionName) {
		this.arActionListActionName = arActionListActionName;
	}

	public Boolean getArActionListIsActive() {
		return arActionListIsActive;
	}

	public void setArActionListIsActive(Boolean arActionListIsActive) {
		this.arActionListIsActive = arActionListIsActive;
	}
	
	
}