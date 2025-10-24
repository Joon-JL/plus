/**
 * 
 * Project Name : 계약관리시스템 구축
 * File name	: CLMSCommonController.java
 * Description	: 공통코드관련 Controller
 * Author		: SDS
 * Date			: 2011. 08. 04
 * Copyright	: 
 */
package com.sec.common.clmscom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmscom.dto.CLMSCommonVO;
import com.sec.common.clmscom.dto.OrgnzVO;
import com.sec.common.clmscom.service.CLMSCommonService;

/**
 * 
 * Description	: 공통코드관련 Interface 구현
 * Author 		: SDS
 * Date			: 2011. 08. 04
 * 
 */
public class CLMSCommonServiceImpl extends CommonServiceImpl implements CLMSCommonService{
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	private PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * @param GRP_CD 그룹코드
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 */
	 public String listComCdByGrpCd(HashMap map) throws Exception {
		
		String result = "";
		String str = "";

		String sysCd = StringUtil.bvl((String)map.get("SYS_CD"), "");
		String grpCd = StringUtil.bvl((String)map.get("GRP_CD"), "");
		String selected = StringUtil.bvl((String)map.get("SELECTED"), "");
		String locale = StringUtil.bvl((String)map.get("LOCALE"), "");
		String abbr = StringUtil.bvl((String)map.get("ABBR"), "");
		String delYn = StringUtil.bvl((String)map.get("DEL_YN"), "");
		String type = StringUtil.bvl((String)map.get("TYPE"), "");
		String selectCd = StringUtil.bvl((String)map.get("SELECT_CD"), "#");
		String usemanMngItm1 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM1"), "");
		String usemanMngItm2 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM2"), "");
		String usemanMngItm3 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM3"), "");
		String usemanMngItm4 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM4"), "");
		String usemanMngItm5 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM5"), "");
		String user_id = StringUtil.bvl((String)map.get("USER_ID"), "");
		String sel_grd = StringUtil.bvl((String)map.get("SEL_GRD"),"");
		String page_gbn = StringUtil.bvl((String)map.get("PAGE_GBN"), "");
		String comp_cd = StringUtil.bvl((String)map.get("COMP_CD"), "");
		String top_role = StringUtil.bvl((String)map.get("TOP_ROLE"), "");
		String limit_cd = StringUtil.bvl((String)map.get("LIMIT_CD"), "#");
		
		List selectCdList = new ArrayList();
		StringTokenizer st = new StringTokenizer(selectCd, ",");
		
		while(st.hasMoreElements()){
			selectCdList.add(st.nextToken());
		}
	
		List limit_cdList = new ArrayList();
		StringTokenizer st2 = new StringTokenizer(limit_cd, ",");
		
		while(st2.hasMoreElements()){
			limit_cdList.add(st2.nextToken());
		}
		
		HashMap iHm = new HashMap();
		iHm.put("sys_cd", sysCd);
		iHm.put("grp_cd", grpCd);
		iHm.put("del_yn", delYn);
		iHm.put("select_cd_list", selectCdList);
		iHm.put("useman_mng_itm1", usemanMngItm1);
		iHm.put("useman_mng_itm2", usemanMngItm2);
		iHm.put("useman_mng_itm3", usemanMngItm3);
		iHm.put("useman_mng_itm4", usemanMngItm4);
		iHm.put("useman_mng_itm5", usemanMngItm5);
		iHm.put("user_id", user_id);
		iHm.put("selectCd", selectCd);
		iHm.put("sel_grd", sel_grd);
		iHm.put("page_gbn", page_gbn);
		iHm.put("comp_cd", comp_cd);
		iHm.put("top_role", top_role);
		iHm.put("limit_cd", limit_cd);
		iHm.put("limit_cd_list", limit_cdList);
		
		List list = null;
		
		if("USERCOMP".equals(grpCd)){
			
			if("Y".equals(sel_grd)){
				
				// 공통 코드 조회 시 코드가 COMP로 들어 올 경우 분기 됨. (전자 변호사 콤보일 경우)
				list = commonDAO.list("common.clmscom.listComCdByGrpAllCd_Comp", iHm);
				
			}  else {
				
				// 공통 코드 조회 시 코드가 COMP로 들어 올 경우 분기 됨. (전자 변호사 콤보일 경우)
				list = commonDAO.list("common.clmscom.listComCdByGrpCd_Comp", iHm);
			}
			
			
			
		} else if("C5".equals(grpCd)){
			
			// 화폐 코드가 들어 올 경우 정렬 조건이 변경 됨.
			list = commonDAO.list("common.clmscom.listComCdByGrpCd_crrncyUnit", iHm);
			
	    }else if("COMP2".equals(grpCd)){
	    	// 지법인 구분 조회
			list = commonDAO.list("common.clmscom.listLocCdByGrpCd", iHm);
	    }		
		else {
			
			if("MOD".equals(page_gbn)){
				
				// 날인 신청 시 신청 유형은 조건에 따라서 가지고 오는 값이 틀림.
				list = commonDAO.list("common.clmscom.listComCdByGrpCdSign", iHm);
				
			} else {
				
				// 모든 정상 적인 경우 공통 코드 조회
				list = commonDAO.list("common.clmscom.listComCdByGrpCd", iHm);
			}
			
		}

		String title = "";

		// 한영 표시문자 선택
		if ("ko".equals(locale)) {
			// 최상위 표시
			if ("S".equals(type)) {
				title = "-- 선택 --";
			}else if ("S2".equals(type)) {
				title = "선택";
			}else if ("T".equals(type)) {
				title = "-- 전체 --";
			}else if ("T2".equals(type)) {
				title = "전체";
			}else {
				title = "";
			}
		}else {
			// 최상위 표시
			if ("S".equals(type)) {
				title = "-- Select --";
			}else if ("T".equals(type)) {
				title = "-- All --";
			}else if ("T2".equals(type)) {
				title = "ALL";
			}else {
				title = "";
			}
		}

		if (!"".equals(title)) {
			result = "<option value=''>" + title + "</option>";
		}

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String cd		   = (String)lom.get("cd");		
				String cdNm	       = (String)lom.get("cd_nm");	
				String cdAbbrNm	   = (String)lom.get("cd_abbr_nm");	
				String cdNmEng     = (String)lom.get("cd_nm_eng");	
				String cdAbbrNmEng = (String)lom.get("cd_abbr_nm_eng");
				String cdNmFra     = (String)lom.get("cd_nm_fra");	
				String cdAbbrNmFra = (String)lom.get("cd_abbr_nm_fra");
				String cdNmDeu     = (String)lom.get("cd_nm_deu");	
				String cdAbbrNmDeu = (String)lom.get("cd_abbr_nm_deu");
				
				String nationCd = (String)lom.get("useman_mng_itm2");
						
				if( cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("ko".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNm;
					} else {
						displayNm = cdNm;
					}
				} else if("fr".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmFra;
					} else {
						displayNm = cdNmFra;
					}
				} else if("de".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmDeu;
					} else {
						displayNm = cdNmDeu;
					}
				} else {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmEng;
					} else {
						displayNm = cdNmEng;
					}
				}
				
				if(nationCd=="" || nationCd==null){					
					result = result+"<option value='"+cd+"' "+str+">"+displayNm+"</option>";
				}else{
					result = result+"<option value='"+cd+"' "+"title='"+nationCd+"' "+str+">"+displayNm+"</option>";
				}
			}
		}

		return result;
	 }
	 
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * 체크박스 타입
	 * @param GRP_CD 그룹코드
	 * @param CHECKEDD 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 */
	 public String checkBoxComCdByGrpCd(HashMap map) throws Exception {
		String result = "";
		String str = "";

		String sysCd = StringUtil.bvl((String)map.get("SYS_CD"), "");
		String grpCd = StringUtil.bvl((String)map.get("GRP_CD"), "");
		String checked = StringUtil.bvl((String)map.get("CHECKED"), "");
		String locale = StringUtil.bvl((String)map.get("LOCALE"), "");
		String abbr = StringUtil.bvl((String)map.get("ABBR"), "");
		String delYn = StringUtil.bvl((String)map.get("DEL_YN"), "");
		String type = StringUtil.bvl((String)map.get("TYPE"), "");
		String name = StringUtil.bvl((String)map.get("NAME"), "");
		String selectCd = StringUtil.bvl((String)map.get("SELECT_CD"), "");
		String usemanMngItm1 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM1"), "");
		String usemanMngItm2 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM2"), "");
		String usemanMngItm3 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM3"), "");
		String usemanMngItm4 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM4"), "");
		String usemanMngItm5 = StringUtil.bvl((String)map.get("USEMAN_MNG_ITM5"), "");
		
	
		HashMap iHm = new HashMap();
		iHm.put("sys_cd", sysCd);
		iHm.put("grp_cd", grpCd);
		iHm.put("del_yn", delYn);
		iHm.put("selectCd", selectCd);
		iHm.put("useman_mng_itm1", usemanMngItm1);
		iHm.put("useman_mng_itm2", usemanMngItm2);
		iHm.put("useman_mng_itm3", usemanMngItm3);
		iHm.put("useman_mng_itm4", usemanMngItm4);
		iHm.put("useman_mng_itm5", usemanMngItm5);

		List list = commonDAO.list("common.clmscom.listComCdByGrpCd", iHm);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String cd		   = (String)lom.get("cd");		
				String cdNm	       = (String)lom.get("cd_nm");	
				String cdAbbrNm	   = (String)lom.get("cd_abbr_nm");	
				String cdNmEng     = (String)lom.get("cd_nm_eng");	
				String cdAbbrNmEng = (String)lom.get("cd_abbr_nm_eng");
				String cdNmFra     = (String)lom.get("cd_nm_fra");	
				String cdAbbrNmFra = (String)lom.get("cd_abbr_nm_fra");
				String cdNmDeu     = (String)lom.get("cd_nm_deu");	
				String cdAbbrNmDeu = (String)lom.get("cd_abbr_nm_deu");
						
				if( cd.equals(checked)){
					str = "checked";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("ko".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNm;
					} else {
						displayNm = cdNm;
					}
				} else if("fr".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmFra;
					} else {
						displayNm = cdNmFra;
					}
				} else if("de".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmDeu;
					} else {
						displayNm = cdNmDeu;
					}
				} else {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmEng;
					} else {
						displayNm = cdNmEng;
					}
				}
				
				result = result + "<input type='checkBox'  class='pL10' name='"+name+"' value='"+cd+"' "+str+">"+" "+displayNm+"</input>";
			}
		}

		return result;
	 }	 
	 
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다.(List 반환)
	 * @param SYS_CD 시스템코드
	 * @param GRP_CD 그룹코드
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 */	 
	 public List listComCdByGrpCd2(CLMSCommonVO vo) throws Exception{

		String sysCd = StringUtil.bvl(vo.getSys_cd(), "");
		String grpCd = StringUtil.bvl(vo.getGrp_cd(), "");
		String delYn = StringUtil.bvl(vo.getDel_yn(), "");
		List selectCdList = new ArrayList();
		selectCdList.add("#");
		
		 HashMap iHm = new HashMap();
		 iHm.put("sys_cd", sysCd);
		 iHm.put("grp_cd", grpCd);
		 iHm.put("del_yn", delYn);
		 iHm.put("select_cd_list", selectCdList);
		 iHm.put("useman_mng_itm1", StringUtil.bvl(vo.getUseman_mng_itm1(),""));
		 iHm.put("useman_mng_itm2","");
		 iHm.put("useman_mng_itm3","");
		 iHm.put("useman_mng_itm4","");
		 iHm.put("useman_mng_itm5","");
		 
		 return commonDAO.list("common.clmscom.listComCdByGrpCd", iHm);
	}
	 
	 
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.
	  *
	  * @param     GBN   구분(CONTRACTTYPE:계약유형,  )
	  * @param     UP_CD 상위코드
	  * @param     SELECTED 디폴트 선택 코드
	  * @param     DEL_YN 삭제여부(N:삭제아닌 것들만, Y:전부다)
	  * @param     LOCALE 한글영어(ko:한,en:영)
	  * @param     ABBR 약어/Full Name(A:약어,F:풀네임)
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getComboHTML(HashMap hm) throws Exception{
		String result = "";
		String str = "";
		
		String gbn      = (String)hm.get("GBN");
		String upCd     = StringUtil.bvl((String)hm.get("UP_CD"), "");
		String selected = StringUtil.bvl((String)hm.get("SELECTED"),"");
		String locale   = StringUtil.bvl((String)hm.get("LOCALE"),"en");
		String abbr     = StringUtil.bvl((String)hm.get("ABBR"),"A");
		String delYn    = StringUtil.bvl((String)hm.get("DEL_YN"),"N");
		String type     = StringUtil.bvl((String)hm.get("TYPE"), "");
		//사업부코드일경우 사용
		String addYn    = StringUtil.bvl((String)hm.get("ADD_YN"),"N");
		
		HashMap iHm = new HashMap();
		iHm.put("up_cd", upCd);
		iHm.put("del_yn", delYn);
		
		List list = null;
		
		if("CONTRACTTYPE".equals(gbn)){ //계약유형 리스트 콤보
			list = commonDAO.list("common.clmscom.getContracttypeCombo", iHm);
		}else if("OPERDIV".equals(gbn)){ //사업부 코드리스트
			list = commonDAO.list("common.clmcom.listOperdivCd", iHm);
		}
		
		String title       = "";
		
		//한영 표시문자 선택
		if("ko".equals(locale)) {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- 선택 --";
			} else if("T".equals(type)) {
				title     = "-- 전체 --";
			} else if("N".equals(type)) {
				title     = "-- 사용하지않음 --";
			} else {
				title     = "";
			}
		} else {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- Select --";
			} else if("T".equals(type)) {
				title     = "-- All --";
			} else if("N".equals(type)) {
				title     = "-- None --";
			} else {
				title     = "";
			}						
		}
		
		if(!"".equals(title)) {
			result = "<option value=''>"+title+"</option>";
		}
		
		//사업부코드 일 경우
		if("OPERDIV".equals(gbn) && "Y".equals(addYn)){
			String selectChk = "";
			if("total".equals(selected)){
				selectChk = "selected";
			}
			if("ko".equals(locale)) {
				result = result + "<option value='total' "+selectChk+">전사</option>";
			} else {
				result = result + "<option value='total' "+selectChk+">TOTAL</option>";
			}		

		}
		
		if(list != null && list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String cd		   = (String)lom.get("cd");		
				String cdNm	       = (String)lom.get("cd_nm");	
				String cdAbbrNm	   = (String)lom.get("cd_nm_abbr");	
				String cdNmEng     = (String)lom.get("cd_nm_eng");	
				String cdAbbrNmEng = (String)lom.get("cd_nm_abbr_eng");
				
				/*** 계약유형 다국어 적용 (2013.11.27 이종민) ***/
				String cdNmFra     = (String)lom.get("cd_nm_fra");	
				String cdAbbrNmFra = (String)lom.get("cd_nm_abbr_fra");
				String cdNmDeu     = (String)lom.get("cd_nm_deu");	
				String cdAbbrNmDeu = (String)lom.get("cd_nm_abbr_deu");
				
				if( cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("ko".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNm;
					} else {
						displayNm = cdNm;
					}
				} else if("fr".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmFra;
					} else {
						displayNm = cdNmFra;
					}
				} else if("de".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmDeu;
					} else {
						displayNm = cdNmDeu;
					}
				}  else {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmEng;
					} else {
						displayNm = cdNmEng;
					}
				}
				result = result+"<option value='"+cd+"' "+str+">"+displayNm+"</option>";
			}
		}
		
		
		return result;
	}

	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param SELECTED 디폴트 선택 코드
	  * @param UP_ORGNZ_CD 상위조직코드
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getUnitOrgnzComboHTML(HashMap hm) throws Exception{
		String result = "";
		String str = "";
		
		String up_orgnz_cd 	= StringUtil.bvl((String)hm.get("UP_ORGNZ_CD"),"");
		//String locale   	= StringUtil.bvl((String)hm.get("LOCALE"),"en");
		String locale     	= StringUtil.bvl((String)hm.get("LOCALE"), "H");
		String abbr     	= StringUtil.bvl((String)hm.get("ABBR"),"F");
		String type     	= StringUtil.bvl((String)hm.get("TYPE"), "");
		String selected 	= StringUtil.bvl((String)hm.get("SELECTED"),"");
		
		HashMap iHm = new HashMap();
		iHm.put("up_orgnz_cd", up_orgnz_cd);
		
		List list = null;
		
		list = commonDAO.list("common.clmscom.getUnitOrgnzCombo", iHm);
		
		String title       = "";
		
		//한영 표시문자 선택
		if("H".equals(locale)) {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- 선택 --";
			} else if("T".equals(type)) {
				title     = "-- 전체 --";
			} else if("N".equals(type)) {
				title     = "-- 사용하지않음 --";
			} else {
				title     = "";
			}
		} else {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- Select --";
			} else if("T".equals(type)) {
				title     = "-- All --";
			} else if("N".equals(type)) {
				title     = "-- None --";
			} else {
				title     = "";
			}						
		}
		
		if(!"".equals(title)) {
			result = "<option value=''>"+title+"</option>";
		}
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String orgnz_cd		   = (String)lom.get("orgnz_cd");		
				String orgnz_nm	       = (String)lom.get("orgnz_nm");	
				String orgnz_nm_eng	   = (String)lom.get("orgnz_nm_eng");
				String orgnz_nm_abbr   = (String)lom.get("orgnz_nm_abbr");
				String orgnz_nm_abbr_eng = (String)lom.get("orgnz_nm_abbr_eng");
						
				if( orgnz_cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("H".equals(locale)) {
					if("A".equals(abbr)) {
						displayNm = orgnz_nm_abbr;
					} else {
						displayNm = orgnz_nm;
					}
				} else {
					if("A".equals(abbr)) {
						displayNm = orgnz_nm_abbr_eng;
					} else {
						displayNm = orgnz_nm_eng;
					}
				}
				result = result+"<option value='"+orgnz_cd+"' "+str+">"+displayNm+"</option>";
				
			}
		}
		
		return result;		
	}
	
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.(법무담당자용)
	  * @param SYS_CD 시스템코드 
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param BLNGT_ORGNZ 소속조직코드
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @param SELECTED 디폴트 선택 코드
	  * @param STATS 담당자 선택 코드
	  * @param CNSDREQ_ID 검토의뢰ID
	  * @param OPP_DEPT_DIV 상대소속부서 여부
	  * @param SRCH_DEPT 검색부서
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getLasPersonComboHTML(HashMap hm) throws Exception{
		String result = "";
		String str = "";
		
		String sys_cd = StringUtil.bvl((String)hm.get("SYS_CD"),"");
		String locale   = StringUtil.bvl((String)hm.get("LOCALE"),"en");
		String blngt_orgnz     = StringUtil.bvl((String)hm.get("BLNGT_ORGNZ"),"");
		String type     = StringUtil.bvl((String)hm.get("TYPE"), "");
		String selected = StringUtil.bvl((String)hm.get("SELECTED"),"");
		String stats = StringUtil.bvl((String)hm.get("STATS"),"");
		String cnsdreq_id = StringUtil.bvl((String)hm.get("CNSDREQ_ID"),"");
		String opp_dept_div = StringUtil.bvl((String)hm.get("OPP_DEPT_DIV"),"");
		String srch_dept = StringUtil.bvl((String)hm.get("SRCH_DEPT"),"");
		
		HashMap iHm = new HashMap();
		iHm.put("sys_cd", sys_cd);
		iHm.put("blngt_orgnz", blngt_orgnz);
		iHm.put("cnsdreq_id", cnsdreq_id);
		iHm.put("opp_dept_div", opp_dept_div);
		iHm.put("srch_dept", srch_dept);
		
		List list = null;
		
		// stats파라미터 값이 y 혹은 Y이면 담당자 이름 (일, 월, 년, 전체 담당자 지정 카운트) 쿼리 실행
		if("Y".equals(stats) || "y".equals(stats)){
			list = commonDAO.list("common.clmscom.getLasPersonCombo2", iHm);
		}
		else{
			if(cnsdreq_id != ""){
				list = commonDAO.list("common.clmscom.getLasCnsdreqPersonCombo", iHm);		// 담당자 리스트[배정]
			}else{
				list = commonDAO.list("common.clmscom.getLasPersonCombo", iHm);				// 담당자 리스트[전체]
			}
		}
		String title       = "";
		
		//한영 표시문자 선택
		if("ko".equals(locale)) {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- 선택 --";
			} else if("T".equals(type)) {
				title     = "-- 전체 --";
			} else if("N".equals(type)) {
				title     = "-- 사용하지않음 --";
			} else {
				title     = "";
			}
		} else {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- Select --";
			} else if("T".equals(type)) {
				title     = "-- All --";
			} else if("N".equals(type)) {
				title     = "-- None --";
			} else {
				title     = "";
			}						
		}
		
		if(!"".equals(title)) {
			result = "<option value=''>"+title+"</option>";
		}
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String user_id		   = (String)lom.get("user_id");		
				String user_nm	       = (String)lom.get("user_nm");	
				String user_nm_eng	   = (String)lom.get("user_nm_eng");	
				String user_stats	   = (String)lom.get("user_stats");
						
				if( user_id.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("ko".equals(locale)) {
					displayNm = user_nm;
				} else {
					displayNm = user_nm_eng;
				}
				if("Y".equals(stats) || "y".equals(stats)){
					result = result+"<option value='"+user_id+"' "+str+">"+displayNm+" "+user_stats+"</option>";
				}
				else{
					result = result+"<option value='"+user_id+"' "+str+">"+displayNm+"</option>";
				}
				
			}
		}
		
		return result;		
	}
	
	
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.(법무시스템 총괄 콤보박스용)
	  * @param SYS_CD 시스템코드 
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param BLNGT_ORGNZ 소속조직코드
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getLasEpsuborgComboHTML(HashMap hm) throws Exception{
		String result = "";
		String str = "";
		
		String locale   = StringUtil.bvl((String)hm.get("LOCALE"),"en");
		String type     = StringUtil.bvl((String)hm.get("TYPE"), "");
		String selected = StringUtil.bvl((String)hm.get("SELECTED"),"");
		
		HashMap iHm = new HashMap();
		
		List list = null;
		
		list = commonDAO.list("common.clmscom.getLasEpsuborgCombo", iHm);
		
		String title       = "";
		
		//한영 표시문자 선택
		if("ko".equals(locale)) {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- 선택 --";
			} else if("T".equals(type)) {
				title     = "-- 전체 --";
			} else if("N".equals(type)) {
				title     = "-- 사용하지않음 --";
			} else {
				title     = "";
			}
		} else {
			//최상위표시
			if("S".equals(type)) {
				title     = "-- Select --";
			} else if("T".equals(type)) {
				title     = "-- All --";
			} else if("N".equals(type)) {
				title     = "-- None --";
			} else {
				title     = "";
			}						
		}
		
		if(!"".equals(title)) {
			result = "<option value=''>"+title+"</option>";
		}
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String dept_nm = (String)lom.get("dept_nm");
				String grp_dept_cd = (String)lom.get("grp_dept_cd");
				String intnl_dept_cd = (String)lom.get("intnl_dept_cd");
				String crtrnday = (String)lom.get("crtrnday");
				String dept_lvl = (String)lom.get("dept_lvl");
						
				if( intnl_dept_cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				result = result+"<option value='"+intnl_dept_cd+"' "+str+">"+dept_nm+"</option>";
				
			}
		}
		
		return result;		
	}
	/**
	 * 부서 리스트를 반환한다 (트리 구조)
	 */
	public List listDeptTree(CLMSCommonVO vo) throws Exception {
		return commonDAO.list("common.clmscom.listDeptTree", vo);
	}
	
	/**
	 * 본인이 속한 부서 및 검색된 부서의 트리리스트를 반환한다. (수직계층만)
	 */
	public List userDeptTree(CLMSCommonVO vo) throws Exception {
		if(vo.getDept_nm() != null && !vo.getDept_nm().equals("")) {
			List al = commonDAO.list("common.clmscom.searchDeptCd", vo);
			if(al != null && al.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)al.get(0);
				
				vo.setDept_cd((String)lom.get("dept_cd"));
			}
		}
		return commonDAO.list("common.clmscom.userDeptTree", vo);
	}
	
	/**
	 * 하위부서 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray listDeptTreeAjax(CLMSCommonVO vo) throws Exception {
		List al =  commonDAO.list("common.clmscom.listDeptTree", vo);

		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("dept_cd", (String)lom.get("dept_cd"));
				jsonObject.put("dept_nm", (String)lom.get("dept_nm"));
				jsonObject.put("dept_level", ((Integer)lom.get("dept_level")).toString());
				jsonObject.put("dept_order", ((Integer)lom.get("dept_order")).toString());
				jsonObject.put("down_dept_yn", (String)lom.get("down_dept_yn"));
				jsonObject.put("up_dept_cd", (String)lom.get("up_dept_cd"));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

	/**
	 * 일부이름으로 부서명을 조회한다.
	 */
	public List searchDept(CLMSCommonVO vo) throws Exception {
		return commonDAO.list("common.clmscom.searchDept", vo);
	}
	
	/**
	 * TB_COM_CD 정보조회
	 */
	public JSONArray listTbComCd(HashMap hm) throws Exception {
		List list =  commonDAO.list("common.clmscom.listTbComCd", hm);
		
		JSONArray  jsonArray = new JSONArray();		
		JSONObject jsonObject = null;
		
		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {

				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				jsonObject = new JSONObject();

				jsonObject.put("sys_cd",			StringUtil.bvl((String)lom.get("sys_cd"),""));
				jsonObject.put("grp_cd",			StringUtil.bvl((String)lom.get("grp_cd"),""));
				jsonObject.put("cd",	 			StringUtil.bvl((String)lom.get("cd"),""));
				jsonObject.put("cd_nm",  			StringUtil.bvl((String)lom.get("cd_nm"),""));
				jsonObject.put("cd_abbr_nm",		StringUtil.bvl((String)lom.get("cd_abbr_nm"),""));
				jsonObject.put("cd_nm_eng",			StringUtil.bvl((String)lom.get("cd_nm_eng"),""));
				jsonObject.put("cd_abbr_nm_eng",	StringUtil.bvl((String)lom.get("cd_abbr_nm_eng"),""));
				jsonObject.put("cd_nm_fra",			StringUtil.bvl((String)lom.get("cd_nm_fra"),""));
				jsonObject.put("cd_abbr_nm_fra",	StringUtil.bvl((String)lom.get("cd_abbr_nm_fra"),""));
				jsonObject.put("cd_nm_deu",			StringUtil.bvl((String)lom.get("cd_nm_deu"),""));
				jsonObject.put("cd_abbr_nm_deu",	StringUtil.bvl((String)lom.get("cd_abbr_nm_deu"),""));
				jsonObject.put("useman_mng_itm1",	StringUtil.bvl((String)lom.get("useman_mng_itm1"),"N"));
				jsonObject.put("useman_mng_itm2",	StringUtil.bvl((String)lom.get("useman_mng_itm2"),""));
				jsonObject.put("useman_mng_itm3",	StringUtil.bvl((String)lom.get("useman_mng_itm3"),""));
				jsonObject.put("useman_mng_itm4",	StringUtil.bvl((String)lom.get("useman_mng_itm4"),""));
				jsonObject.put("useman_mng_itm5",	StringUtil.bvl((String)lom.get("useman_mng_itm5"),""));
				
				jsonArray.add(jsonObject);
			}
		}else{
			
			jsonObject = new JSONObject();
			
			jsonObject.put("sys_cd",			"");
			jsonObject.put("grp_cd",			"");
			jsonObject.put("cd",	 			"");
			jsonObject.put("cd_nm",  			"");
			jsonObject.put("cd_abbr_nm",		"");
			jsonObject.put("cd_nm_eng",			"");
			jsonObject.put("cd_abbr_nm_eng",	"");
			jsonObject.put("cd_nm_fra",			"");
			jsonObject.put("cd_abbr_nm_fra",	"");
			jsonObject.put("cd_nm_deu",			"");
			jsonObject.put("cd_abbr_nm_deu",	"");
			jsonObject.put("useman_mng_itm1",	"");
			jsonObject.put("useman_mng_itm2",	"");
			jsonObject.put("useman_mng_itm3",	"");
			jsonObject.put("useman_mng_itm4",	"");
			jsonObject.put("useman_mng_itm5",	"");
						
			jsonArray.add(jsonObject);
		}
				
		return jsonArray;
	}
	
	/*
	 * CLM 시스템의 현재, 페이지에서 사용가능한 수정, 조회 권한이 있는지 체크한다.
	 * @param hm 조회조건 
	 * @param chkSM(S:조회권한여부,  M:수정권한여부)
	 * @return Y or N
	 * */
	public String getTrCheck(HashMap hm, String chkSM) throws Exception{
		String result = "N";
		String str = "";
		
		String sys_cd   	= StringUtil.bvl((String)hm.get("SYS_CD"),"");		//필수값 : CLM(계약시스템), LAS(법무시스템)
		//String sys_cd = "CLM";
		String user_id  	= StringUtil.bvl((String)hm.get("USER_ID"), "");	//필수값
		String cnsdreq_id 	= StringUtil.bvl((String)hm.get("CNSDREQ_ID"),"");	//Optional 값: rc_flag가 R일경우 필수값
		String cntrt_id 	= StringUtil.bvl((String)hm.get("CNTRT_ID"),"");	//Optional 값: rc_flag가 C일경우 필수값
		String rc_flag  	= StringUtil.bvl((String)hm.get("RC_FLAG"), "");	//필수값 - 체크범위 : 의뢰건(R) or 계약건(C)
		
		/***************************************************************************************************
		 * 계약시스템일경우 공백으로 하면 됨.
		 * 법무시스템에서 화면단에서 사용한 클릭한 버튼 : 주의 -  아래 버튼값에  꺽쇠([])도 포함시킬것 
		 * 			임시저장([B01]), 의견전달([B02]), 발신([B03]), 회신([B04]) 버튼, 유관부서검토요청([B06]), 
		 * 			배정([B10]), 이관승인([B11]), 이관불필요([B12]), 이관반려([B13]),  이관요청([B14])
		 * 			기타버튼([B99])	<==B99 버튼의 경우 추후 상세 정의필요								 
		 ***************************************************************************************************/
		String event_but 	= StringUtil.bvl((String)hm.get("EVENT_BUT"),"");	 //Optional 값: 법무시스템일경우 필수값
		String menu_id 		= StringUtil.bvl((String)hm.get("MENU_ID"),"");		 //필수값
		//Optional 값: 법무시스템이면서 배정버튼(B10)일경우만  필수값 , 값은 이런식으로 넘겨주면 됨==> 김담당,박담당,이담당
		String las_mem  	= StringUtil.bvl((String)hm.get("LAS_MEM"), "");	 
		  
	    HashMap iHm = new HashMap();
		iHm.put("sys_cd", sys_cd);
		iHm.put("user_id", user_id);
		iHm.put("cnsdreq_id", cnsdreq_id);
		iHm.put("cntrt_id", cntrt_id);
		iHm.put("rc_flag", rc_flag);
		iHm.put("event_but", event_but);
		iHm.put("menu_id", menu_id);
		iHm.put("las_mem", las_mem);
		
		List list = null;		

		list = commonDAO.list("common.clmscom.getTrCheck", iHm);

		if (list != null && list.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)list.get(0);
			String ret1 = (String)lom.get("ret1");
			
			//조회권한 체크
			if(chkSM.equals("S") && ret1.indexOf("[R]")>=0){
				result = "Y";
			//수정권한 체크	
			}else if(chkSM.equals("M") && ret1.indexOf("[U]")>=0){
				result = "Y";
			}
		}
		
		return result;		
	}

	/*
	 * LAS 시스템의 현재, 페이지에서 사용가능한 수정, 조회 권한이 있는지 체크한다.
	 * @param hm 조회조건 
	 * @param chkSM(S:조회권한여부,  M:수정권한여부)
	 * @return Y or N
	 * */
	public String getTrCheck2(HashMap hm, String chkSM) throws Exception{
		String result = "N";
		String str = "";
		
		String sys_cd   	= StringUtil.bvl((String)hm.get("SYS_CD"),"");		//필수값 : CLM(계약시스템), LAS(법무시스템)
		String user_id  	= StringUtil.bvl((String)hm.get("USER_ID"), "");	//필수값
		String cnsdreq_id 	= StringUtil.bvl((String)hm.get("CNSDREQ_ID"),"");	//Optional 값: rc_flag가 R일경우 필수값
		String cntrt_id 	= StringUtil.bvl((String)hm.get("CNTRT_ID"),"");	//Optional 값: rc_flag가 C일경우 필수값
		String rc_flag  	= StringUtil.bvl((String)hm.get("RC_FLAG"), "");	//필수값 - 체크범위 : 의뢰건(R) or 계약건(C)
		
		/***************************************************************************************************
		 * 계약시스템일경우 공백으로 하면 됨.
		 * 법무시스템에서 화면단에서 사용한 클릭한 버튼 : 주의 -  아래 버튼값에  꺽쇠([])도 포함시킬것 
		 * 			임시저장([B01]), 의견전달([B02]), 발신([B03]), 회신([B04]) 버튼, 유관부서검토요청([B06]), 
		 * 			배정([B10]), 이관승인([B11]), 이관불필요([B12]), 이관반려([B13]),  이관요청([B14])
		 * 			기타버튼([B99])	<==B99 버튼의 경우 추후 상세 정의필요								 
		 ***************************************************************************************************/
		String event_but 	= StringUtil.bvl((String)hm.get("EVENT_BUT"),"");	 //Optional 값: 법무시스템일경우 필수값
		
		String menu_id 		= StringUtil.bvl((String)hm.get("MENU_ID"),"");		 //필수값 
		
		//Optional 값: 법무시스템이면서 배정버튼(B10)일경우만  필수값 , 값은 이런식으로 넘겨주면 됨==> 김담당,박담당,이담당
		String las_mem  	= StringUtil.bvl((String)hm.get("LAS_MEM"), "");	 
		  
	    HashMap iHm = new HashMap();
		iHm.put("sys_cd", sys_cd);
		iHm.put("user_id", user_id);
		iHm.put("cnsdreq_id", cnsdreq_id);
		iHm.put("cntrt_id", cntrt_id);
		iHm.put("rc_flag", rc_flag);
		iHm.put("event_but", event_but);
		iHm.put("menu_id", menu_id);
		iHm.put("las_mem", las_mem);
		
		List list = null;
		
		list = commonDAO.list("common.clmscom.getTrCheck2", iHm);
		 
		if (list != null && list.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)list.get(0);
			String ret1 = (String)lom.get("ret1");
			
			//조회권한 체크
			if(chkSM.equals("S") && ret1.indexOf("[R]")>=0){
				result = "Y";
				
			//수정권한 체크	
			}else if(chkSM.equals("M") && ret1.indexOf("[U]")>=0){
				result = "Y";
			}
		}
		
		return result;		
	}
	
	/**
	 * 단위조직 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List searchOrgnz(OrgnzVO vo) throws Exception{
		return commonDAO.list("common.clmscom.searchOrgnz", vo);
	}
	
	/**
	 * 법무담당자,관리자 alert리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public HashMap searchAdminAlert(CLMSCommonVO vo, String role) throws Exception{
		List cntrtResultList = null;
		List cnsltResultList = null;
		
		HashMap returnMap = new HashMap();
		
		if("RA01".equals(role)){
			cntrtResultList = commonDAO.list("common.clmscom.getAdminCntrtCnt", vo);	
			cnsltResultList = commonDAO.list("common.clmscom.getAdminCnsltCnt", vo);
		}else if("RA02".equals(role)){
			cntrtResultList = commonDAO.list("common.clmscom.getCntrtCnt", vo);	
			cnsltResultList = commonDAO.list("common.clmscom.getCnsltCnt", vo);
		}
		
		if(null != cntrtResultList){
			Map map =(Map)cntrtResultList.get(0);
			if(null != map){
				returnMap.put("cntrt_reqs_cnt", map.get("reqs_cnt"));
				returnMap.put("cntrt_returns_cnt", map.get("returns_cnt"));
			}
			
		}
		if(null != cnsltResultList){
			Map map =(Map)cnsltResultList.get(0);
			if(null != map){
				returnMap.put("cnslt_reqs_cnt", map.get("reqs_cnt"));
				returnMap.put("cnslt_returns_cnt", map.get("returns_cnt"));
			}
		}
		
		return returnMap;
	}
	/**
	 * 사업부담당자,관리자 alert리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public HashMap getApprovalCnt(CLMSCommonVO vo) throws Exception{
		List cntrtResultList1 = null;
		List cntrtResultList2 = null;
		List cntrtResultList3 = null;
		List cntrtResultList4 = null;
		
		HashMap returnMap = new HashMap();
		
		cntrtResultList1 = commonDAO.list("common.clmscom.getCntrtAdiminCnt1", vo);	
		cntrtResultList2 = commonDAO.list("common.clmscom.getCntrtAdiminCnt2", vo);
		cntrtResultList3 = commonDAO.list("common.clmscom.getCntrtAdiminCnt3", vo);	
		cntrtResultList4 = commonDAO.list("common.clmscom.getCntrtAdiminCnt4", vo);

		//원본접수건수
		if(null != cntrtResultList1){
			Map map =(Map)cntrtResultList1.get(0);
			if(null != map){
				returnMap.put("cntrt_cnt_1", map.get("cnt"));
			}
		}
		//자동연장승인
		if(null != cntrtResultList2){
			Map map =(Map)cntrtResultList2.get(0);
			if(null != map){
				returnMap.put("cntrt_cnt_2", map.get("cnt"));
			}
		}
		//체결후등록 건수
		if(null != cntrtResultList3){
			Map map =(Map)cntrtResultList3.get(0);
			if(null != map){
				returnMap.put("cntrt_cnt_3", map.get("cnt"));
			}
		}
		//표준계약서건수
		if(null != cntrtResultList4){
			Map map =(Map)cntrtResultList4.get(0);
			if(null != map){
				returnMap.put("cntrt_cnt_4", map.get("cnt"));
			}
		}
		
		return returnMap;
	}
	

	/**
	 * 필수 항목에 대한 체크 리스트를 가지고 온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List checkList(CLMSCommonVO vo) throws Exception{
		
		List checkList = commonDAO.list("common.clmscom.checkList", vo);
		
		return checkList;
	}
	
	/**
	 * 필수 항목에 대한 체크 리스트를 가지고 온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyCheckItem(CLMSCommonVO vo) throws Exception{
		
		String all_sum = vo.getAll_sum();
		String spl_item_cd = "";
		String spl_dis_yn = "";
		String item_cd = "";
		String dis_yn = "";
		int modifyResult = 0;
		int spl = 0;
		
		if(!"".equals(all_sum)){
			spl_item_cd = all_sum.split(":")[0];
			spl_dis_yn = all_sum.split(":")[1];
			spl = spl_item_cd.split(",").length;
			
			for(int i=0;i < spl;i++){
				
				item_cd = spl_item_cd.split(",")[i];
				dis_yn = spl_dis_yn.split(",")[i];
				
				vo.setItem_cd(item_cd);
				vo.setDis_yn(dis_yn);
				
				modifyResult= commonDAO.modify("common.clmscom.modifyCheckItem", vo);
				
			}
			
		}
		
		return modifyResult;
		
	}
}
