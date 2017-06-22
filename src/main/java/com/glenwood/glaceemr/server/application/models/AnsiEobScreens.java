package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ansi_eob_screens")
public class AnsiEobScreens {

	@Id
	@Column(name="screen_id")
	private Integer screenId;

	@Column(name="screen_description")
	private String screenDescription;

	@Column(name="screen_query")
	private String screenQuery;

	@Column(name="isactive")
	private Boolean isactive;

	@Column(name="screen_group_id")
	private Integer screenGroupId;

	@Column(name="enableautoaction")
	private Boolean enableautoaction;

	@Column(name="screen_action_conf_id")
	private Integer screenActionConfId;

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public String getScreenDescription() {
		return screenDescription;
	}

	public void setScreenDescription(String screenDescription) {
		this.screenDescription = screenDescription;
	}

	public String getScreenQuery() {
		return screenQuery;
	}

	public void setScreenQuery(String screenQuery) {
		this.screenQuery = screenQuery;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getScreenGroupId() {
		return screenGroupId;
	}

	public void setScreenGroupId(Integer screenGroupId) {
		this.screenGroupId = screenGroupId;
	}

	public Boolean getEnableautoaction() {
		return enableautoaction;
	}

	public void setEnableautoaction(Boolean enableautoaction) {
		this.enableautoaction = enableautoaction;
	}

	public Integer getScreenActionConfId() {
		return screenActionConfId;
	}

	public void setScreenActionConfId(Integer screenActionConfId) {
		this.screenActionConfId = screenActionConfId;
	}
	
}