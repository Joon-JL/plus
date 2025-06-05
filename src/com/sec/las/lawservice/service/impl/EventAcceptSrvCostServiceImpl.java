/**
 * Project Name : 법무시스템
 * File Name : EventAcceptSrvCostServiceImpl.java
 * Description : 용역비  ServiceImpl
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawservice.dto.EventAcceptLawyerVO;
import com.sec.las.lawservice.dto.EventAcceptSrvCostVO;
import com.sec.las.lawservice.dto.EventAcceptVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.service.EventAcceptSrvCostService;

public class EventAcceptSrvCostServiceImpl extends CommonServiceImpl implements EventAcceptSrvCostService {

	/**
	 * 용역비 검색용 사건 목록
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listEventSub(EventManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listEventSub", vo);
	}
	
	/**
	 * 용역비 등록
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	public int insertEventAcceptSrvCost(EventAcceptVO vo) throws Exception {		
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getAcpt_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);	

		// 환율정보 취득
		ListOrderedMap lom = (ListOrderedMap)commonDAO.list("las.lawservice.checkRateAjax", vo).get(0);
		
		int check_rate_cnt = (Integer)lom.get("check_rate_cnt");
		BigDecimal usrate = (BigDecimal)lom.get("usrate");
		BigDecimal exrate = (BigDecimal)lom.get("exrate");		
		String crrncy_unit = StringUtil.bvl(vo.getCrrncy_unit(), "KRW");
		
		// 부서별 용역비 등록
		BigDecimal claim_amt = new BigDecimal(0);
		
		String str_intnl_dept_cd[] = vo.getIntnl_dept_cd();
		String str_grp_dpet_cd[] = vo.getGrp_dept_cd();
		String str_dept_nm[] = vo.getDept_nm();
		BigDecimal bdc_totamt[] = vo.getTotamt();
		BigDecimal bdc_srvc_amt[] = vo.getSrvc_amt();
		BigDecimal bdc_addtnl_amt[] = vo.getAddtnl_amt();
		BigDecimal bdc_dc_rate[] = vo.getDc_rate();
		
		EventAcceptSrvCostVO eascvo = null;
		
		for(int i=0; i < str_dept_nm.length; i++) {
			eascvo = new EventAcceptSrvCostVO();
			
			if(!str_dept_nm[i].equals("") && !bdc_totamt[i].equals(0.00)){
				
				claim_amt = claim_amt.add(bdc_totamt[i]);
				
				eascvo.setAcpt_no(vo.getAcpt_no());
				eascvo.setIntnl_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_intnl_dept_cd[i],"")));
				eascvo.setGrp_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_grp_dpet_cd[i],"")));
				eascvo.setDept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(str_dept_nm[i],"")));

				eascvo.setTotamt(bdc_totamt[i]);
				eascvo.setSrvc_amt(bdc_srvc_amt[i]);
				eascvo.setAddtnl_amt(bdc_addtnl_amt[i]);
				eascvo.setDc_rate(bdc_dc_rate[i]);
				
				if(check_rate_cnt > 0){

					BigDecimal plnd_remit_amt = null;
					
					//엔화일 경우 100을 나눠줘야 원화가 된다.
		 			if("JPY".equals(crrncy_unit)){
		 				
		 				BigDecimal plnd_remit_amt_jpy = bdc_totamt[i].multiply(exrate);
		 				BigDecimal plnd_remit_amt_rate = new BigDecimal("100");
		 				
		 				plnd_remit_amt = plnd_remit_amt_jpy.divide(plnd_remit_amt_rate);		 				
		 			}else if("KRW".equals(crrncy_unit)){
		 				plnd_remit_amt = bdc_totamt[i];
		 			}else{
		 				plnd_remit_amt = bdc_totamt[i].multiply(exrate);
		 			}
					
					BigDecimal usd_amt = plnd_remit_amt.divide(usrate,2,BigDecimal.ROUND_HALF_UP); // 소수3자리에서 반올림
					eascvo.setPlnd_remit_amt(plnd_remit_amt);
					eascvo.setUsd_amt(usd_amt);
				} else {
					Locale locale1 = new Locale(vo.getSession_user_locale());
					
					//해당 날짜의 환율 정보가 없습니다. 시스템 담당자에게 문의바랍니다.
					throw new Exception((String)messageSource.getMessage("las.page.field.eventacceptsrvcost.insertEventAcceptSrvCost01", null, locale1));
				}
				eascvo.setRemitday(vo.getRemitday());
				eascvo.setEvent_no(vo.getEvent_no());
				eascvo.setTot_remit_amt(new BigDecimal((StringUtil.bvl(vo.getTot_remit_amt(),"0"))));
				eascvo.setReg_id(vo.getReg_id());
				eascvo.setReg_nm(vo.getReg_nm());
				
				commonDAO.insert("las.lawservice.insertAcceptSrvCost", eascvo);
			}
			
			eascvo = null;
		}
		
		vo.setClaim_amt(claim_amt.toString());
		claim_amt = null;
		
		// 변호사 정보 등록
		String str_lwr[] = vo.getLwr_no();
		EventAcceptLawyerVO ealvo = null;
		
		for(int i=0; i < str_lwr.length; i++) {
			ealvo = new EventAcceptLawyerVO();			
				if(!str_lwr[i].equals("")){
				
					ealvo.setLwr_no(StringUtil.convertHtmlTochars(StringUtil.bvl(str_lwr[i],"")));
					ealvo.setEvent_no(vo.getEvent_no());
					ealvo.setAcpt_no(vo.getAcpt_no());
					ealvo.setReg_id(vo.getReg_id());
					ealvo.setReg_nm(vo.getReg_nm());
					
					commonDAO.insert("las.lawservice.insertEventAcceptLawer", ealvo);
				}			
			ealvo = null;
		}		
		return commonDAO.insert("las.lawservice.insertEventAccept", vo);
	}
	
	/**
	 * 용역비 수정
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	public int modifyEventAcceptSrvCost(EventAcceptVO vo) throws Exception {
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getAcpt_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);
		
		// 수정일 경우 접수일로 환율정산 날짜 설정
		vo.setC_dt(vo.getAcptday());
	
		// 환율정보 취득
		ListOrderedMap lom = (ListOrderedMap)commonDAO.list("las.lawservice.checkRateAjax", vo).get(0);
		
		int check_rate_cnt = (Integer)lom.get("check_rate_cnt");
		BigDecimal usrate = (BigDecimal)lom.get("usrate");
		BigDecimal exrate = (BigDecimal)lom.get("exrate");
		String crrncy_unit = StringUtil.bvl(vo.getCrrncy_unit(), "KRW");
		
		BigDecimal claim_amt = new BigDecimal(0);
		// 미지급 날짜가 없는 경우만 부서별 용역비 갱신
		if(vo.getUnpayday().equals("")){
			String str_intnl_dept_cd[] = vo.getIntnl_dept_cd();
			String str_grp_dpet_cd[] = vo.getGrp_dept_cd();
			String str_dept_nm[] = vo.getDept_nm();
			BigDecimal bdc_totamt[] = vo.getTotamt();
			BigDecimal bdc_srvc_amt[] = vo.getSrvc_amt();
			BigDecimal bdc_addtnl_amt[] = vo.getAddtnl_amt();
			BigDecimal bdc_dc_rate[] = vo.getDc_rate();
			
			EventAcceptSrvCostVO eascvo = null;
			
			for(int i=0; i < str_dept_nm.length; i++) {
				eascvo = new EventAcceptSrvCostVO();
				
				if(!str_dept_nm[i].equals("")){
					
					claim_amt = claim_amt.add(bdc_totamt[i]);
					
					eascvo.setAcpt_no(vo.getAcpt_no());
					eascvo.setIntnl_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_intnl_dept_cd[i],"")));
					eascvo.setGrp_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_grp_dpet_cd[i],"")));
					eascvo.setDept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(str_dept_nm[i],"")));

					eascvo.setTotamt(bdc_totamt[i]);
					eascvo.setSrvc_amt(bdc_srvc_amt[i]);
					eascvo.setAddtnl_amt(bdc_addtnl_amt[i]);
					eascvo.setDc_rate(bdc_dc_rate[i]);
					
					if(check_rate_cnt > 0){

						BigDecimal plnd_remit_amt = null;
						
						//엔화일 경우 100을 나눠줘야 원화가 된다.
			 			if("JPY".equals(crrncy_unit)){
			 				
			 				BigDecimal plnd_remit_amt_jpy = bdc_totamt[i].multiply(exrate);
			 				BigDecimal plnd_remit_amt_rate = new BigDecimal("100");
			 				
			 				plnd_remit_amt = plnd_remit_amt_jpy.divide(plnd_remit_amt_rate);		 				
			 			}else if("KRW".equals(crrncy_unit)){
			 				plnd_remit_amt = bdc_totamt[i];
			 			}else{
			 				plnd_remit_amt = bdc_totamt[i].multiply(exrate);
			 			}
			 			
						BigDecimal usd_amt = plnd_remit_amt.divide(usrate,2,BigDecimal.ROUND_HALF_UP); // 소수3자리에서 반올림
						eascvo.setPlnd_remit_amt(plnd_remit_amt);
						eascvo.setUsd_amt(usd_amt);
					} else {
						// throw new Exception("해당 날짜의 환율 정보가 없습니다. 시스템 담당자에게 문의바랍니다.");
						BigDecimal plnd_remit_amt = new BigDecimal("0.00");
						BigDecimal usd_amt = new BigDecimal("0.00");
						eascvo.setPlnd_remit_amt(plnd_remit_amt);
						eascvo.setUsd_amt(usd_amt);
					}
					
					eascvo.setRemitday(vo.getRemitday());
					eascvo.setEvent_no(vo.getEvent_no());
					eascvo.setTot_remit_amt(new BigDecimal((StringUtil.bvl(vo.getTot_remit_amt(),"0"))));
					eascvo.setMod_id(vo.getMod_id());
					eascvo.setMod_nm(vo.getMod_nm());
					
					commonDAO.modify("las.lawservice.modifyEventAcceptSrvCost", eascvo);
				}
				eascvo = null;
			}
		}		

		vo.setClaim_amt(claim_amt.toString());
		claim_amt = null;
		
		return commonDAO.modify("las.lawservice.modifyEventAccept", vo);
	}
	
	/**
	 * 용역비 삭제
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	public int deleteEventAcceptSrvCost(EventAcceptVO vo) throws Exception {
		
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getAcpt_no()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);
		
		 return commonDAO.delete("las.lawservice.deleteEventAcceptSrvCost", vo);
	}
	
	/**
	 * 사건접수 상세
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public List detailEventAccept(EventAcceptVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailEventAccept", vo);
	}
	
	/**
	 * INVOICE NO 중복 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray checkInvoiceNoAjax(EventAcceptVO vo) throws Exception {
		List al =  commonDAO.list("las.lawservice.checkInvoiceNo", vo);

		JSONArray jsonArray = new JSONArray();		
		ListOrderedMap lom = (ListOrderedMap)al.get(0);
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("invoice_no", ((Integer)lom.get("invoice_no")).toString());		
		jsonArray.add(jsonObject);
	
		return jsonArray;
	}
	
	/**
	 * INVOICE 검색
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public JSONArray srchInvoiceNo(EventAcceptVO vo) throws Exception {
		
		List al =  commonDAO.list("las.lawservice.srchInvoiceNo", vo);

		JSONArray jsonArray = new JSONArray();
		
		ListOrderedMap lom = (ListOrderedMap)al.get(0);
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("acpt_no", (String)lom.get("acpt_no"));		
		jsonArray.add(jsonObject);
	
		return jsonArray;
	}
	
	/**
	 * 용역비 상세
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public List detailEventAcceptSrvCost(EventAcceptVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailEventAcceptSrvCost", vo);
	}
	/**
	 * 용역비 SEQ
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return String
	 * @throws Exception
	 */
	public String getMaxAcptNo(EventAcceptVO vo) throws Exception {
		List resultList = null;		
		String max_acpt_no = "";
		
		resultList = commonDAO.list("las.lawservice.getMaxAcptNo", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			max_acpt_no = (String)lom.get("acpt_no");
		}
		return max_acpt_no;	
	}
	
	/**
	 * 변호사 comboListBox 
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getLawyerList(LawyerManageVO vo) throws Exception {		
		return commonDAO.list("las.lawservice.getLawyerList", vo);
	}
	
	/**
	 * 사건 담당 변호사 목록 
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public List getListEventAcceptLawyer(EventAcceptVO vo) throws Exception {		
		return commonDAO.list("las.lawservice.listEventAcceptLawyer", vo);
	}
	
	/**
	 * 환율적용정보 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray checkRateAjax(EventAcceptVO vo) throws Exception {
		
		List al =  commonDAO.list("las.lawservice.checkRateAjax", vo);
	
		JSONArray jsonArray = new JSONArray();		
		ListOrderedMap lom = (ListOrderedMap)al.get(0);
		
		int check_rate_cnt = (Integer)lom.get("check_rate_cnt");
		BigDecimal usrate = (BigDecimal)lom.get("usrate");
		BigDecimal exrate = (BigDecimal)lom.get("exrate");
		
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("check_rate_cnt", check_rate_cnt);
		jsonObject.put("usrate", usrate);
		jsonObject.put("exrate", exrate);

		jsonArray.add(jsonObject);
	
		return jsonArray;
	}
	
	/**
	 * 송금일자 갱신
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public int updateRemitday(EventAcceptVO vo) throws Exception{
		return commonDAO.modify("las.lawservice.updateRemitday", vo);
	}
	/**
	 * REVIEW 갱신
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public int updateReviewYn(EventAcceptVO vo) throws Exception{
		return commonDAO.modify("las.lawservice.updateReviewYn", vo);
	}
	
	/**
	 * 송금정보 초기화
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public int updateRemitInit(EventAcceptVO vo) throws Exception{
		return commonDAO.modify("las.lawservice.updateRemitInit", vo);
	}
	
	/**
	 * 미지급일자를 갱신.　
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public int updateUnpayday(EventAcceptVO vo) throws Exception {
		
		// 환율정보 취득
		ListOrderedMap lom = (ListOrderedMap)commonDAO.list("las.lawservice.checkRateAjax", vo).get(0);
		
		int check_rate_cnt = (Integer)lom.get("check_rate_cnt");
		BigDecimal usrate = (BigDecimal)lom.get("usrate");
		BigDecimal exrate = (BigDecimal)lom.get("exrate");
		String crrncy_unit = StringUtil.bvl(vo.getCrrncy_unit(), "KRW");
		
		// 부서별 용역비 갱신
		BigDecimal claim_amt = new BigDecimal(0);
		
		String str_intnl_dept_cd[] = vo.getIntnl_dept_cd();
		String str_grp_dpet_cd[] = vo.getGrp_dept_cd();
		String str_dept_nm[] = vo.getDept_nm();
		BigDecimal bdc_totamt[] = vo.getTotamt();
		BigDecimal bdc_srvc_amt[] = vo.getSrvc_amt();
		BigDecimal bdc_addtnl_amt[] = vo.getAddtnl_amt();
		BigDecimal bdc_dc_rate[] = vo.getDc_rate();
		
		EventAcceptSrvCostVO eascvo = null;
		
		for(int i=0; i < str_dept_nm.length; i++) {
			eascvo = new EventAcceptSrvCostVO();
			
			if(!str_dept_nm[i].equals("")){
				
				claim_amt = claim_amt.add(bdc_totamt[i]);
				
				eascvo.setAcpt_no(vo.getAcpt_no());
				eascvo.setIntnl_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_intnl_dept_cd[i],"")));
				eascvo.setGrp_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_grp_dpet_cd[i],"")));
				eascvo.setDept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(str_dept_nm[i],"")));

				eascvo.setTotamt(bdc_totamt[i]);
				eascvo.setSrvc_amt(bdc_srvc_amt[i]);
				eascvo.setAddtnl_amt(bdc_addtnl_amt[i]);
				eascvo.setDc_rate(bdc_dc_rate[i]);
				
				if(check_rate_cnt > 0){

					BigDecimal plnd_remit_amt = null;
					
					//엔화일 경우 100을 나눠줘야 원화가 된다.
		 			if("JPY".equals(crrncy_unit)){
		 				
		 				BigDecimal plnd_remit_amt_jpy = bdc_totamt[i].multiply(exrate);
		 				BigDecimal plnd_remit_amt_rate = new BigDecimal("100");
		 				
		 				plnd_remit_amt = plnd_remit_amt_jpy.divide(plnd_remit_amt_rate);		 				
		 			}else if("KRW".equals(crrncy_unit)){
		 				plnd_remit_amt = bdc_totamt[i];
		 			}else{
		 				plnd_remit_amt = bdc_totamt[i].multiply(exrate);
		 			}
		 			
					BigDecimal usd_amt = plnd_remit_amt.divide(usrate,2,BigDecimal.ROUND_HALF_UP); // 소수3자리에서 반올림
					eascvo.setPlnd_remit_amt(plnd_remit_amt);
					eascvo.setUsd_amt(usd_amt);
				} else {
					Locale locale1 = new Locale(vo.getSession_user_locale());
					
					//해당 날짜의 환율 정보가 없습니다. 시스템 담당자에게 문의바랍니다.
					throw new Exception((String)messageSource.getMessage("las.page.field.eventacceptsrvcost.updateUnpayday01", null, locale1));
				}
				
				eascvo.setRemitday(vo.getUnpayday());
				eascvo.setEvent_no(vo.getEvent_no());
				eascvo.setTot_remit_amt(new BigDecimal((StringUtil.bvl(vo.getTot_remit_amt(),"0"))));
				eascvo.setMod_id(vo.getMod_id());
				eascvo.setMod_nm(vo.getMod_nm());
				
				commonDAO.modify("las.lawservice.updateRemitAmt", eascvo);
			}
			eascvo = null;
		}
		vo.setClaim_amt(claim_amt.toString());
		claim_amt = null;
		
		return commonDAO.modify("las.lawservice.updateUnpayday", vo);
	}	
}