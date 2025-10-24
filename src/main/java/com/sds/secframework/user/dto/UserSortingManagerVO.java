package com.sds.secframework.user.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * 사용자정보 Value Object
 *  
 * @version V1.0
 * @작성일 : 2011/11/10
 * @author 김형준
 */
public class UserSortingManagerVO extends CommonVO {
	
	private String srch_blngt_orgnz;	//검색조건 - 소속조직코드
	private int user_srt; 				//정렬 순서
	private String[] user_id_arr; 		//사용자ID 배열

	public String getSrch_blngt_orgnz() {
		return srch_blngt_orgnz;
	}

	public void setSrch_blngt_orgnz(String srch_blngt_orgnz) {
		this.srch_blngt_orgnz = srch_blngt_orgnz;
	}

	public int getUser_srt() {
		return user_srt;
	}

	public void setUser_srt(int user_srt) {
		this.user_srt = user_srt;
	}

	public String[] getUser_id_arr() {
		return user_id_arr;
	}

	public void setUser_id_arr(String[] user_id_arr) {
		this.user_id_arr = user_id_arr;
	}
	
	
	
	
}
