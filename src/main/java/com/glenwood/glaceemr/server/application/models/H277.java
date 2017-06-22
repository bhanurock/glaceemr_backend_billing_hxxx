package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "h277")
public class H277 implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="h277_h277001_seq")
	@SequenceGenerator(name ="h277_h277001_seq", sequenceName="h277_h277001_seq", allocationSize=1)
	@Column(name="h277001")
	private Integer h277001;

	@Column(name="h277002")
	private Integer h277002;

	@Column(name="h277003")
	private Integer h277003;

	@Column(name="h277004")
	private String h277004;

	@Column(name="h277005")
	private String h277005;

	@Column(name="h277006")
	private Boolean h277006;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	//@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula=@JoinFormula(value="SUBSTR(product_idnf, 1, 3)",referencedColumnName="product_idnf")) })
	//@JoinColumn(name = "h277005", referencedColumnName = "h278002", insertable = false, updatable = false)
	@JoinColumnsOrFormulas({
		  @JoinColumnOrFormula(formula=@JoinFormula(value="(h277005::int)", referencedColumnName="h278002")),
		})
	H278 h278;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "h277004", referencedColumnName = "h279002", insertable = false, updatable = false)
	H279 h279;

	

	public H279 getH279() {
		return h279;
	}

	public void setH279(H279 h279) {
		this.h279 = h279;
	}

	public Integer getH277001() {
		return h277001;
	}

	public void setH277001(Integer h277001) {
		this.h277001 = h277001;
	}

	public Integer getH277002() {
		return h277002;
	}

	public void setH277002(Integer h277002) {
		this.h277002 = h277002;
	}

	public Integer getH277003() {
		return h277003;
	}

	public void setH277003(Integer h277003) {
		this.h277003 = h277003;
	}

	public String getH277004() {
		return h277004;
	}

	public void setH277004(String h277004) {
		this.h277004 = h277004;
	}

	public String getH277005() {
		return h277005;
	}

	public void setH277005(String h277005) {
		this.h277005 = h277005;
	}

	public H278 getH278() {
		return h278;
	}

	public void setH278(H278 h278) {
		this.h278 = h278;
	}

	public Boolean getH277006() {
		return h277006;
	}

	public void setH277006(Boolean h277006) {
		this.h277006 = h277006;
	}

}