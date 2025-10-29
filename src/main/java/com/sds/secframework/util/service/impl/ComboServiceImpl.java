package com.sds.secframework.util.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.code.dto.CodeVO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.util.service.ComboService;

public class ComboServiceImpl extends CommonServiceImpl implements ComboService {

	  /**
	  * TB_COM_CD 테이블에서 해당 공통 코드를 가져와서 Select Box의 <OPtion>을 채운다.
	  *
	  * @param     grpCd 코드그룹
	  * @param     selected 디폴트 선택 코드
	  * @param     useYn 사용여부(Y/N)
	  * @param     language 한글영어(KOR:한,ENG:영)
	  * @param     abbr 약어/Full Name(A:약어,F:풀네임)
	  * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getCommonCodeCombo(HashMap hm) throws Exception {
    	String result = "";
		String str = "";

		CodeVO vo = new CodeVO();
    	vo.setSys_cd((String)hm.get("sys_cd"));
    	vo.setGrp_cd((String)hm.get("grp_cd"));
    	vo.setUse_yn((String)hm.get("use_yn"));
    	
    	String selected = "";
    	String language = "";
    	String abbr = "";
    	
    	selected = (String)hm.get("selected");
    	language = (String)hm.get("language");
    	abbr     = (String)hm.get("abbr");
		    	
    	List list = commonDAO.list("secfw.code.listCodeCombo", vo);
    			    	
    	if(list != null && list.size()>0) {
        	for(int i=0; i<list.size(); i++) {
	    		ListOrderedMap lom = (ListOrderedMap)list.get(i);
			    		
	    		String displayNm = "";

				String cd		   = (String)lom.get("cd");		
				String cdNm	       = (String)lom.get("cd_nm");	
				String cdNmEng     = (String)lom.get("cd_nm_eng");	
				String cdAbbrNm	   = (String)lom.get("cd_abbr_nm");	
				String cdAbbrNmEng = (String)lom.get("cd_abbr_nm_eng");	
						
				if( cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}

				//한영 표시문자 선택
				if("ENG".equals(language)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmEng;
					} else {
						displayNm = cdNmEng;
					}
				} else {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNm;
					} else {
						displayNm = cdNm;
					}
				}
				
				result = result+"<option value='"+cd+"' "+str+">"+displayNm+"</option>";
			}
    		
    	}
    	return result;
	}
		
	  /**
	  * 1~12월까지를 셀렉트 박스로 조회한다.
	  *
	  * @param     selected 디폴트 선택 코드
	  * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public static String getCommonMonthCombo(String selected) throws Exception {
		String strRs = "";
		String str   = "";
			
		for( int i=1; i<=9; i++) {
			if( !selected.equals("") && i == Integer.parseInt(selected)){
				str = "selected";
			}else{
				str = "";
			}
			strRs = strRs+"<option value='0"+String.valueOf(i)+"' "+str+">0"+String.valueOf(i)+"</option>";
		}

		for( int i=10; i<=12; i++) {
			if( String.valueOf(i).equals(selected)){
				str = "selected";
			}else{
				str = "";
			}
			strRs = strRs+"<option value='"+String.valueOf(i)+"' "+str+">"+String.valueOf(i)+"</option>";
		}
	return strRs;
	}
}
