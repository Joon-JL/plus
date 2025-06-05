package com.sds.secframework.util.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 우편번호검색 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class ZipForm extends CommonForm {

	/**********************************************************************
	 * 우편번호 조회 속성
	 **********************************************************************/
	/**우편번호 */
	String zipcode;			
	/**일련번호 */
	String seq;				
	/**시도 */
	String sido;			
	/**시군구 */
	String sigungu;			
	/**읍면동 */
	String dong;			
	/**리 */
	String ri;				
	/**도서 */
	String doseo;			
	/**번지 */
	String bunji;			
	/**아파트/건물명 */
	String bldg;			
	/**변경일 */
	String change_ymd;		
	/**주소 */
	String address;			
	
	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	/**동명 */
	String dongnm;			

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getRi() {
		return ri;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}

	public String getDoseo() {
		return doseo;
	}

	public void setDoseo(String doseo) {
		this.doseo = doseo;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	public String getBldg() {
		return bldg;
	}

	public void setBldg(String bldg) {
		this.bldg = bldg;
	}

	public String getChange_ymd() {
		return change_ymd;
	}

	public void setChange_ymd(String change_ymd) {
		this.change_ymd = change_ymd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDongnm() {
		return dongnm;
	}

	public void setDongnm(String dongnm) {
		this.dongnm = dongnm;
	}	
}
