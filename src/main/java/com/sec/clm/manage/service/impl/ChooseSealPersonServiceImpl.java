/**
* Project Name : 계약관리시스템
* File Name :ChooseClientServiceImpl.java
* Description : 의뢰인선택팝업 Service Impl
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.manage.dto.ChooseSealPersonVO;
import com.sec.clm.manage.service.ChooseSealPersonService;

public class ChooseSealPersonServiceImpl extends CommonServiceImpl implements ChooseSealPersonService {

	 /**
	  * 날인담당자 목록
	  * @return   날인담당자 목록
	  * @exception Exception
	  */
	public List listChooseSealPerson(ChooseSealPersonVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseSealPerson", vo);		
	}
	
	 /**
	  * 증명서류 발급 담당자  목록
	  * @return   증명서류 발급 담당자  목록
	  * @exception Exception
	  */
	public List listChooseCertificatIssuer(ChooseSealPersonVO vo) throws Exception {
		return commonDAO.list("clm.manage.listChooseCertificatIssue", vo);		
	}	
	
	 /**
	  * 날인부서정보테이블 Select생성
	  * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getSealDeptComboHTML(HashMap hm) throws Exception {
		String result = "";
		String str = "";
		
		String search_dept_cd      = (String)hm.get("SEARCH_DEPT_CD");
		String last_locale  	   = (String)hm.get("LAST_LOCALE");	//로케일 정보 추가
		
		Locale locale1 = new Locale(last_locale);
		
		HashMap iHm = new HashMap();
		
		List list = null;
		
		list = commonDAO.list("clm.manage.listChooseSealDept", iHm);
		
		String title	= "";
		//메시지 처리 - 전체
		title     		= (String)messageSource.getMessage("clm.page.field.chooseSealPerson.getSealDeptComboHTML01", null, locale1);
		
		result = "<option value='*'>"+title+"</option>";
		
		String selectChk = "";
		if(list != null && list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String cd		   = (String)lom.get("seal_ffmt_search_dept_cd");		
				String cdNm	       = (String)lom.get("seal_ffmt_search_dept_nm");
				
				if( cd.equals(search_dept_cd)){
					str = "selected";
				}else{
					str = "";
				}
				result = result+"<option value='"+cd+"' "+str+">"+cdNm+"</option>";
			}
		}
		
		return result;
	}
}