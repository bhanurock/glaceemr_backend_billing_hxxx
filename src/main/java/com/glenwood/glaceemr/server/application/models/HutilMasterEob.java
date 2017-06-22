package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hutil_master_eob")
public class HutilMasterEob {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hutil_master_eob_id_seq")
	@SequenceGenerator(name ="hutil_master_eob_id_seq", sequenceName="hutil_master_eob_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="isa06")
	private String isa06;

	@Column(name="isa08")
	private String isa08;

	@Column(name="isa09")
	private Date isa09;

	@Column(name="isa13")
	private String isa13;

	@Column(name="gs06")
	private String gs06;

	@Column(name="st02")
	private String st02;

	@Column(name="bpr01")
	private String bpr01;

	@Column(name="bpr02")
	private Double bpr02;

	@Column(name="bpr04")
	private String bpr04;

	@Column(name="bpr16")
	private Date bpr16;

	@Column(name="trn02")
	private String trn02;

	@Column(name="trn03")
	private String trn03;

	@Column(name="prdtm02")
	private Date prdtm02;

	@Column(name="prn102")
	private String prn102;

	@Column(name="prref02")
	private String prref02;

	@Column(name="prn301")
	private String prn301;

	@Column(name="prn401")
	private String prn401;

	@Column(name="prn402")
	private String prn402;

	@Column(name="prn403")
	private String prn403;

	@Column(name="prper02")
	private String prper02;

	@Column(name="prper04")
	private String prper04;

	@Column(name="pen102")
	private String pen102;

	@Column(name="pen104")
	private String pen104;

	@Column(name="pen301")
	private String pen301;

	@Column(name="pen401")
	private String pen401;

	@Column(name="pen402")
	private String pen402;

	@Column(name="pen403")
	private String pen403;

	@Column(name="peref02")
	private String peref02;

	@Column(name="lx01")
	private Integer lx01;

	@Column(name="filename")
	private String filename;

	@Column(name="ts301")
	private String ts301;

	@Column(name="ts302")
	private String ts302;

	@Column(name="ts303")
	private Date ts303;

	@Column(name="ts304")
	private Float ts304;

	@Column(name="ts305")
	private Double ts305;

	@Column(name="ts306")
	private Double ts306;

	@Column(name="ts307")
	private Double ts307;

	@Column(name="ts308")
	private Double ts308;

	@Column(name="ts309")
	private Double ts309;

	@Column(name="ts3010")
	private Double ts3010;

	@Column(name="ts3011")
	private Double ts3011;

	@Column(name="ts3012")
	private Double ts3012;

	@Column(name="ts3013")
	private Double ts3013;

	@Column(name="ts3014")
	private Double ts3014;

	@Column(name="ts3015")
	private Double ts3015;

	@Column(name="ts3016")
	private Double ts3016;

	@Column(name="ts3017")
	private Double ts3017;

	@Column(name="ts3018")
	private Double ts3018;

	@Column(name="ts3019")
	private Double ts3019;

	@Column(name="ts3020")
	private Double ts3020;

	@Column(name="ts3021")
	private Double ts3021;

	@Column(name="ts3022")
	private Double ts3022;

	@Column(name="ts3023")
	private Float ts3023;

	@Column(name="ts3024")
	private Double ts3024;

	@Column(name="ts201")
	private Double ts201;

	@Column(name="ts202")
	private Double ts202;

	@Column(name="ts203")
	private Double ts203;

	@Column(name="ts204")
	private Double ts204;

	@Column(name="ts205")
	private Double ts205;

	@Column(name="ts206")
	private Double ts206;

	@Column(name="ts207")
	private Float ts207;

	@Column(name="ts208")
	private Double ts208;

	@Column(name="ts209")
	private Double ts209;

	@Column(name="ts2010")
	private Float ts2010;

	@Column(name="ts2011")
	private Float ts2011;

	@Column(name="ts2012")
	private Float ts2012;

	@Column(name="ts2013")
	private Float ts2013;

	@Column(name="ts2014")
	private Float ts2014;

	@Column(name="ts2015")
	private Double ts2015;

	@Column(name="ts2016")
	private Float ts2016;

	@Column(name="ts2017")
	private Double ts2017;

	@Column(name="ts2018")
	private Double ts2018;

	@Column(name="ts2019")
	private Double ts2019;

	@Column(name="bpr05")
	private String bpr05;

	@Column(name="bpr06")
	private String bpr06;

	@Column(name="bpr07")
	private String bpr07;

	@Column(name="bpr08")
	private String bpr08;

	@Column(name="bpr09")
	private String bpr09;

	@Column(name="bpr010")
	private String bpr010;

	@Column(name="bpr011")
	private String bpr011;

	@Column(name="bpr012")
	private String bpr012;

	@Column(name="bpr013")
	private String bpr013;

	@Column(name="bpr014")
	private String bpr014;

	@Column(name="bpr015")
	private String bpr015;

	@Column(name="cur01")
	private String cur01;

	@Column(name="cur02")
	private String cur02;

	@Column(name="cur03")
	private String cur03;

	@Column(name="ref02")
	private String ref02;

	@Column(name="vref02")
	private String vref02;

	@Column(name="isposted")
	private Integer isposted;

	@Column(name="pinrefcode")
	private String pinrefcode;

	@Column(name="prn1003")
	private String prn1003;

	@Column(name="prn1004")
	private String prn1004;

	@Column(name="prref001")
	private String prref001;

	@Column(name="sourcefile")
	private String sourcefile;

	@Column(name="placedate")
	private Date placedate;

	@Column(name="dop")
	private Date dop;

	@Column(name="temp")
	private String temp;

	@Column(name="ptper02")
	private String ptper02;

	@Column(name="ptper04")
	private String ptper04;

	@Column(name="pwper02")
	private String pwper02;

	@Column(name="pwper04")
	private String pwper04;

	@Column(name="rdm01")
	private String rdm01;

	@Column(name="rdm02")
	private String rdm02;

	@Column(name="rdm03")
	private String rdm03;

	@Column(name="rdm04")
	private String rdm04;

	@Column(name="ref2u02")
	private String ref2u02;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsa06() {
		return isa06;
	}

	public void setIsa06(String isa06) {
		this.isa06 = isa06;
	}

	public String getIsa08() {
		return isa08;
	}

	public void setIsa08(String isa08) {
		this.isa08 = isa08;
	}

	public Date getIsa09() {
		return isa09;
	}

	public void setIsa09(Date isa09) {
		this.isa09 = isa09;
	}

	public String getIsa13() {
		return isa13;
	}

	public void setIsa13(String isa13) {
		this.isa13 = isa13;
	}

	public String getGs06() {
		return gs06;
	}

	public void setGs06(String gs06) {
		this.gs06 = gs06;
	}

	public String getSt02() {
		return st02;
	}

	public void setSt02(String st02) {
		this.st02 = st02;
	}

	public String getBpr01() {
		return bpr01;
	}

	public void setBpr01(String bpr01) {
		this.bpr01 = bpr01;
	}

	public Double getBpr02() {
		return bpr02;
	}

	public void setBpr02(Double bpr02) {
		this.bpr02 = bpr02;
	}

	public String getBpr04() {
		return bpr04;
	}

	public void setBpr04(String bpr04) {
		this.bpr04 = bpr04;
	}

	public Date getBpr16() {
		return bpr16;
	}

	public void setBpr16(Date bpr16) {
		this.bpr16 = bpr16;
	}

	public String getTrn02() {
		return trn02;
	}

	public void setTrn02(String trn02) {
		this.trn02 = trn02;
	}

	public String getTrn03() {
		return trn03;
	}

	public void setTrn03(String trn03) {
		this.trn03 = trn03;
	}

	public Date getPrdtm02() {
		return prdtm02;
	}

	public void setPrdtm02(Date prdtm02) {
		this.prdtm02 = prdtm02;
	}

	public String getPrn102() {
		return prn102;
	}

	public void setPrn102(String prn102) {
		this.prn102 = prn102;
	}

	public String getPrref02() {
		return prref02;
	}

	public void setPrref02(String prref02) {
		this.prref02 = prref02;
	}

	public String getPrn301() {
		return prn301;
	}

	public void setPrn301(String prn301) {
		this.prn301 = prn301;
	}

	public String getPrn401() {
		return prn401;
	}

	public void setPrn401(String prn401) {
		this.prn401 = prn401;
	}

	public String getPrn402() {
		return prn402;
	}

	public void setPrn402(String prn402) {
		this.prn402 = prn402;
	}

	public String getPrn403() {
		return prn403;
	}

	public void setPrn403(String prn403) {
		this.prn403 = prn403;
	}

	public String getPrper02() {
		return prper02;
	}

	public void setPrper02(String prper02) {
		this.prper02 = prper02;
	}

	public String getPrper04() {
		return prper04;
	}

	public void setPrper04(String prper04) {
		this.prper04 = prper04;
	}

	public String getPen102() {
		return pen102;
	}

	public void setPen102(String pen102) {
		this.pen102 = pen102;
	}

	public String getPen104() {
		return pen104;
	}

	public void setPen104(String pen104) {
		this.pen104 = pen104;
	}

	public String getPen301() {
		return pen301;
	}

	public void setPen301(String pen301) {
		this.pen301 = pen301;
	}

	public String getPen401() {
		return pen401;
	}

	public void setPen401(String pen401) {
		this.pen401 = pen401;
	}

	public String getPen402() {
		return pen402;
	}

	public void setPen402(String pen402) {
		this.pen402 = pen402;
	}

	public String getPen403() {
		return pen403;
	}

	public void setPen403(String pen403) {
		this.pen403 = pen403;
	}

	public String getPeref02() {
		return peref02;
	}

	public void setPeref02(String peref02) {
		this.peref02 = peref02;
	}

	public Integer getLx01() {
		return lx01;
	}

	public void setLx01(Integer lx01) {
		this.lx01 = lx01;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTs301() {
		return ts301;
	}

	public void setTs301(String ts301) {
		this.ts301 = ts301;
	}

	public String getTs302() {
		return ts302;
	}

	public void setTs302(String ts302) {
		this.ts302 = ts302;
	}

	public Date getTs303() {
		return ts303;
	}

	public void setTs303(Date ts303) {
		this.ts303 = ts303;
	}

	public Float getTs304() {
		return ts304;
	}

	public void setTs304(Float ts304) {
		this.ts304 = ts304;
	}

	public Double getTs305() {
		return ts305;
	}

	public void setTs305(Double ts305) {
		this.ts305 = ts305;
	}

	public Double getTs306() {
		return ts306;
	}

	public void setTs306(Double ts306) {
		this.ts306 = ts306;
	}

	public Double getTs307() {
		return ts307;
	}

	public void setTs307(Double ts307) {
		this.ts307 = ts307;
	}

	public Double getTs308() {
		return ts308;
	}

	public void setTs308(Double ts308) {
		this.ts308 = ts308;
	}

	public Double getTs309() {
		return ts309;
	}

	public void setTs309(Double ts309) {
		this.ts309 = ts309;
	}

	public Double getTs3010() {
		return ts3010;
	}

	public void setTs3010(Double ts3010) {
		this.ts3010 = ts3010;
	}

	public Double getTs3011() {
		return ts3011;
	}

	public void setTs3011(Double ts3011) {
		this.ts3011 = ts3011;
	}

	public Double getTs3012() {
		return ts3012;
	}

	public void setTs3012(Double ts3012) {
		this.ts3012 = ts3012;
	}

	public Double getTs3013() {
		return ts3013;
	}

	public void setTs3013(Double ts3013) {
		this.ts3013 = ts3013;
	}

	public Double getTs3014() {
		return ts3014;
	}

	public void setTs3014(Double ts3014) {
		this.ts3014 = ts3014;
	}

	public Double getTs3015() {
		return ts3015;
	}

	public void setTs3015(Double ts3015) {
		this.ts3015 = ts3015;
	}

	public Double getTs3016() {
		return ts3016;
	}

	public void setTs3016(Double ts3016) {
		this.ts3016 = ts3016;
	}

	public Double getTs3017() {
		return ts3017;
	}

	public void setTs3017(Double ts3017) {
		this.ts3017 = ts3017;
	}

	public Double getTs3018() {
		return ts3018;
	}

	public void setTs3018(Double ts3018) {
		this.ts3018 = ts3018;
	}

	public Double getTs3019() {
		return ts3019;
	}

	public void setTs3019(Double ts3019) {
		this.ts3019 = ts3019;
	}

	public Double getTs3020() {
		return ts3020;
	}

	public void setTs3020(Double ts3020) {
		this.ts3020 = ts3020;
	}

	public Double getTs3021() {
		return ts3021;
	}

	public void setTs3021(Double ts3021) {
		this.ts3021 = ts3021;
	}

	public Double getTs3022() {
		return ts3022;
	}

	public void setTs3022(Double ts3022) {
		this.ts3022 = ts3022;
	}

	public Float getTs3023() {
		return ts3023;
	}

	public void setTs3023(Float ts3023) {
		this.ts3023 = ts3023;
	}

	public Double getTs3024() {
		return ts3024;
	}

	public void setTs3024(Double ts3024) {
		this.ts3024 = ts3024;
	}

	public Double getTs201() {
		return ts201;
	}

	public void setTs201(Double ts201) {
		this.ts201 = ts201;
	}

	public Double getTs202() {
		return ts202;
	}

	public void setTs202(Double ts202) {
		this.ts202 = ts202;
	}

	public Double getTs203() {
		return ts203;
	}

	public void setTs203(Double ts203) {
		this.ts203 = ts203;
	}

	public Double getTs204() {
		return ts204;
	}

	public void setTs204(Double ts204) {
		this.ts204 = ts204;
	}

	public Double getTs205() {
		return ts205;
	}

	public void setTs205(Double ts205) {
		this.ts205 = ts205;
	}

	public Double getTs206() {
		return ts206;
	}

	public void setTs206(Double ts206) {
		this.ts206 = ts206;
	}

	public Float getTs207() {
		return ts207;
	}

	public void setTs207(Float ts207) {
		this.ts207 = ts207;
	}

	public Double getTs208() {
		return ts208;
	}

	public void setTs208(Double ts208) {
		this.ts208 = ts208;
	}

	public Double getTs209() {
		return ts209;
	}

	public void setTs209(Double ts209) {
		this.ts209 = ts209;
	}

	public Float getTs2010() {
		return ts2010;
	}

	public void setTs2010(Float ts2010) {
		this.ts2010 = ts2010;
	}

	public Float getTs2011() {
		return ts2011;
	}

	public void setTs2011(Float ts2011) {
		this.ts2011 = ts2011;
	}

	public Float getTs2012() {
		return ts2012;
	}

	public void setTs2012(Float ts2012) {
		this.ts2012 = ts2012;
	}

	public Float getTs2013() {
		return ts2013;
	}

	public void setTs2013(Float ts2013) {
		this.ts2013 = ts2013;
	}

	public Float getTs2014() {
		return ts2014;
	}

	public void setTs2014(Float ts2014) {
		this.ts2014 = ts2014;
	}

	public Double getTs2015() {
		return ts2015;
	}

	public void setTs2015(Double ts2015) {
		this.ts2015 = ts2015;
	}

	public Float getTs2016() {
		return ts2016;
	}

	public void setTs2016(Float ts2016) {
		this.ts2016 = ts2016;
	}

	public Double getTs2017() {
		return ts2017;
	}

	public void setTs2017(Double ts2017) {
		this.ts2017 = ts2017;
	}

	public Double getTs2018() {
		return ts2018;
	}

	public void setTs2018(Double ts2018) {
		this.ts2018 = ts2018;
	}

	public Double getTs2019() {
		return ts2019;
	}

	public void setTs2019(Double ts2019) {
		this.ts2019 = ts2019;
	}

	public String getBpr05() {
		return bpr05;
	}

	public void setBpr05(String bpr05) {
		this.bpr05 = bpr05;
	}

	public String getBpr06() {
		return bpr06;
	}

	public void setBpr06(String bpr06) {
		this.bpr06 = bpr06;
	}

	public String getBpr07() {
		return bpr07;
	}

	public void setBpr07(String bpr07) {
		this.bpr07 = bpr07;
	}

	public String getBpr08() {
		return bpr08;
	}

	public void setBpr08(String bpr08) {
		this.bpr08 = bpr08;
	}

	public String getBpr09() {
		return bpr09;
	}

	public void setBpr09(String bpr09) {
		this.bpr09 = bpr09;
	}

	public String getBpr010() {
		return bpr010;
	}

	public void setBpr010(String bpr010) {
		this.bpr010 = bpr010;
	}

	public String getBpr011() {
		return bpr011;
	}

	public void setBpr011(String bpr011) {
		this.bpr011 = bpr011;
	}

	public String getBpr012() {
		return bpr012;
	}

	public void setBpr012(String bpr012) {
		this.bpr012 = bpr012;
	}

	public String getBpr013() {
		return bpr013;
	}

	public void setBpr013(String bpr013) {
		this.bpr013 = bpr013;
	}

	public String getBpr014() {
		return bpr014;
	}

	public void setBpr014(String bpr014) {
		this.bpr014 = bpr014;
	}

	public String getBpr015() {
		return bpr015;
	}

	public void setBpr015(String bpr015) {
		this.bpr015 = bpr015;
	}

	public String getCur01() {
		return cur01;
	}

	public void setCur01(String cur01) {
		this.cur01 = cur01;
	}

	public String getCur02() {
		return cur02;
	}

	public void setCur02(String cur02) {
		this.cur02 = cur02;
	}

	public String getCur03() {
		return cur03;
	}

	public void setCur03(String cur03) {
		this.cur03 = cur03;
	}

	public String getRef02() {
		return ref02;
	}

	public void setRef02(String ref02) {
		this.ref02 = ref02;
	}

	public String getVref02() {
		return vref02;
	}

	public void setVref02(String vref02) {
		this.vref02 = vref02;
	}

	public Integer getIsposted() {
		return isposted;
	}

	public void setIsposted(Integer isposted) {
		this.isposted = isposted;
	}

	public String getPinrefcode() {
		return pinrefcode;
	}

	public void setPinrefcode(String pinrefcode) {
		this.pinrefcode = pinrefcode;
	}

	public String getPrn1003() {
		return prn1003;
	}

	public void setPrn1003(String prn1003) {
		this.prn1003 = prn1003;
	}

	public String getPrn1004() {
		return prn1004;
	}

	public void setPrn1004(String prn1004) {
		this.prn1004 = prn1004;
	}

	public String getPrref001() {
		return prref001;
	}

	public void setPrref001(String prref001) {
		this.prref001 = prref001;
	}

	public String getSourcefile() {
		return sourcefile;
	}

	public void setSourcefile(String sourcefile) {
		this.sourcefile = sourcefile;
	}

	public Date getPlacedate() {
		return placedate;
	}

	public void setPlacedate(Date placedate) {
		this.placedate = placedate;
	}

	public Date getDop() {
		return dop;
	}

	public void setDop(Date dop) {
		this.dop = dop;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getPtper02() {
		return ptper02;
	}

	public void setPtper02(String ptper02) {
		this.ptper02 = ptper02;
	}

	public String getPtper04() {
		return ptper04;
	}

	public void setPtper04(String ptper04) {
		this.ptper04 = ptper04;
	}

	public String getPwper02() {
		return pwper02;
	}

	public void setPwper02(String pwper02) {
		this.pwper02 = pwper02;
	}

	public String getPwper04() {
		return pwper04;
	}

	public void setPwper04(String pwper04) {
		this.pwper04 = pwper04;
	}

	public String getRdm01() {
		return rdm01;
	}

	public void setRdm01(String rdm01) {
		this.rdm01 = rdm01;
	}

	public String getRdm02() {
		return rdm02;
	}

	public void setRdm02(String rdm02) {
		this.rdm02 = rdm02;
	}

	public String getRdm03() {
		return rdm03;
	}

	public void setRdm03(String rdm03) {
		this.rdm03 = rdm03;
	}

	public String getRdm04() {
		return rdm04;
	}

	public void setRdm04(String rdm04) {
		this.rdm04 = rdm04;
	}

	public String getRef2u02() {
		return ref2u02;
	}

	public void setRef2u02(String ref2u02) {
		this.ref2u02 = ref2u02;
	}
	
}