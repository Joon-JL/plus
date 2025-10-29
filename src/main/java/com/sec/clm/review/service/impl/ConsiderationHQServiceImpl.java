/**
 * Project Name : 법무시스템
 * File Name : SignServiceImpl.java
 * Description : HQ검토 ServiceImpl
 * Author : 박병주
 * Date : 2014.05
 * Copyright : 2014 by SAMSUNG. All rights reserved.
 */
package com.sec.clm.review.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sec.clm.review.dto.ConsiderationHQVO;
import com.sec.clm.review.service.ConsiderationHQService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class ConsiderationHQServiceImpl extends CommonServiceImpl implements ConsiderationHQService {

	/**
	 * 싱글 메일 서비스
	 */
	private EsbMailService esbMailService = null;
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}	
	
	/**
	 * ROLE 구분을 위한 변수
	 */
	static String HQ01 = "HQ01"; // 본사검토 어드민
	static String HQ02 = "HQ02"; // 본사검토 변호사
	
	/**
	 * HQ검토 목록 조회
	 * @param ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	public List listConsiderationHQ(ConsiderationHQVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setSrch_type_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_type_cd(),"")));
		//vo.setSrch_req_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_req_title(),"")));
		vo.setSrch_req_title(StringUtil.bvl(vo.getSrch_req_title(),""));
		vo.setSrch_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_orgnz(),"")));
		vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));
		vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
		vo.setSrch_reqman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reqman_id(),"")));
		vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reqman_nm(),"")));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prgrs_status(),"")));
		vo.setSrch_owner_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_owner_dept(),"")));
		vo.setSrch_law_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_law_status(),"")));
		vo.setSrch_ip_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_ip_status(),"")));
		vo.setSrch_respman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_respman_id(),"")));
		vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_respman_nm(),"")));
		vo.setSrch_biz_depth(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_biz_depth(),"")));
		vo.setSrch_cnclsnpurps(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cnclsnpurps(),"")));
		vo.setSrch_cntrt_oppnt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cntrt_oppnt_nm(),"")));
		vo.setSrch_cnsd_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cnsd_cont(),"")));
		vo.setSrch_closed_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_closed_yn(),"")));
		
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(),"")));	//소속조직
		vo.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTop_role(),"")));			//권한직위
		vo.setPage_flag(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPage_flag(),"")));		//페이지구분
		
		vo.setUser_id(vo.getSession_user_id());
		
		vo.setSrch_cnsdman_nm(StringUtil.bvl(vo.getSrch_cnsdman_nm(),""));
		vo.setSrch_review_title(StringUtil.bvl(vo.getSrch_review_title(),""));
		vo.setSrch_resp_dept(StringUtil.bvl(vo.getSrch_resp_dept(),""));
		vo.setSrch_biz_clsfcn(StringUtil.bvl(vo.getSrch_biz_clsfcn(),""));
		vo.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(vo.getSrch_cnclsnpurps_bigclsfcn(),""));
		vo.setSrch_step(StringUtil.bvl(vo.getSrch_step(),""));
		vo.setSrch_state(StringUtil.bvl(vo.getSrch_state(),""));
		vo.setSrch_if_sys_cd(StringUtil.bvl(vo.getSrch_if_sys_cd(),""));
		vo.setSrch_oppnt_cd(StringUtil.bvl(vo.getSrch_oppnt_cd(),""));
		vo.setSrch_start_reqday(StringUtil.replace(StringUtil.bvl(vo.getSrch_start_reqday(),""),"-",""));
		vo.setSrch_end_reqday(StringUtil.replace(StringUtil.bvl(vo.getSrch_end_reqday(),""),"-",""));
		vo.setSrch_start_cnlsnday(StringUtil.replace(StringUtil.bvl(vo.getSrch_start_cnlsnday(),""),"-",""));
		vo.setSrch_end_cnlsnday(StringUtil.replace(StringUtil.bvl(vo.getSrch_end_cnlsnday(),""),"-",""));
		
		if(vo.getPage_flag().equals("5")) {
/*			if(vo.getList_mode().equals("cnsdreq")) {
				return commonDAO.list("las.contractmanager.listCnsdReqTConsideration", vo);
			} else {
				return commonDAO.list("las.contractmanager.listContractTConsideration", vo);
			}*/
			return commonDAO.list("las.contractmanager.listConsiderationHQ", vo);
		} else {
			return commonDAO.list("las.contractmanager.listConsiderationHQ", vo);
		}
		
	}
	
	/**
	 * HQ검토 상세 조회
	 * @param ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	public List detailConsiderationHQ(ConsiderationHQVO vo) throws Exception{
		
		vo.setHq_cnsdreq_id(StringUtil.bvl(vo.getHq_cnsdreq_id(),""));
		
		return commonDAO.list("las.contractmanager.detailConsiderationHQ", vo);		
	}
		
	/**
	 * HQ 담당자 리스트 (배정자 리스트 )
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public List listRespmanHQ (ConsiderationHQVO vo) throws Exception {
		vo.setHq_cnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_cnsdreq_id(),"")));		// HQ 검토의뢰ID	
		return commonDAO.list("las.contractmanager.listRespmanHQ", vo);
	}
	
	/**
	 * 담당자 리스트[팝업]
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public List popRespmanHQ(ConsiderationHQVO vo) throws Exception {
		vo.setHq_cnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_cnsdreq_id(),"")));		// HQ 검토의뢰ID		
		return commonDAO.list("las.contractmanager.popRespmanHQ", vo);
	}
	
	/**
	 * HQ reviewer 지정
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int confirmRespmanHQ(ConsiderationHQVO vo) throws Exception {

		int resultcnt = 0;
		List mail_user_list = new ArrayList();			

		//Cross-site Scripting 방지
		vo.setHq_cnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_cnsdreq_id(),"")));
		
		//등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setList_respmanHQ_id("");			//초기값
		vo.setApbt_memo(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_memo(),"")));			// 그룹장 메세지
		
		//법무담당자 등록
		String list_respman_ids[] = vo.getList_respmanHQ_ids();
		
		//법무담당자 삭제
		commonDAO.delete("las.contractmanager.deleteRespmanHQ", vo);
		
		if(vo.getList_respmanHQ_ids() != null){			
			for(int i=0; i<list_respman_ids.length; i++) {
				if(!list_respman_ids[i].equals("")){
					vo.setList_respmanHQ_id(StringUtil.convertHtmlTochars(StringUtil.bvl(list_respman_ids[i],"")));
					commonDAO.insert("las.contractmanager.insertRespmanHQ", vo);					
				}
			}
		}
		
		if("RP030".equals(vo.getHq_rel_pro())){ // CE + IM 인 경우
		
			if(!"0".equals(vo.getCe_resp_yn()) && !"0".equals(vo.getIm_resp_yn()) ){
				vo.setHq_cnsd_status("C16203");	// 미배정 -> 검토중 상태
			} else {
				vo.setHq_cnsd_status("C16202");	// 미배정 상태
			}
		
		} else {
			vo.setHq_cnsd_status("C16203");	// 미배정 -> 검토중 상태
		}
		

		// HQ TN_CLM_HQ_CONT_CNSDREQ 상태 변경
		resultcnt = commonDAO.modify("las.contractmanager.updateHQstatus2", vo);		
		
		if(resultcnt > 0){
			mail_user_list = commonDAO.list("las.contractmanager.getRESPMANaddr", vo); // 배정자 정보 취득
			
			if(mail_user_list.size()!=0)			
				sendHQMail(vo,mail_user_list,"ASSIGN");		
			
		}		

		return resultcnt;
		
	}
	
	/**
	 * rtnReturn  처리
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int rtnReturn(ConsiderationHQVO vo) throws Exception {
		
		int mod_cnt = 0;
		
		// XSS 처리
		vo.setRtn_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRtn_cont(),"")));			//  HQ_그룹장  메세지
		
		if("ADMIN_REPLY".equals(vo.getRtnMode())){
			
			vo.setHq_cnsd_status("C16207");	// 검토중 ->  HQ_그룹장 승인(회신) 상태
		
			if(commonDAO.modify("las.contractmanager.updateHQstatus", vo) > 0 
				//	&& commonDAO.modify("las.contractmanager.set_rtn_reason_hq", vo) > 0  //   HQ 의뢰 마스터 상태 변경 ( C02609) && 어드민 리턴 테이블 에 사유 등록	
					){	
				
				vo.setHq_rel_pro("ADMIN_REPLY");
				vo.setHq_ce_cnsd_opnn(vo.getRtn_cont());  // 어드민 리턴 사유를 세팅
				mod_cnt = commonDAO.modify("las.contractmanager.insertNewHqRow", vo); //  회신 등록 처리				
				
				if(mod_cnt >= 1){
					List mail_user_list = commonDAO.list("las.contractmanager.getREQMANaddr", vo); // 의뢰자 정보 취득
					
					if(mail_user_list.size()!=0)			
						sendHQMail(vo,mail_user_list,"ADMIN_REPLY");		
					
				}	
								
			}
			
		} else if("APPR".equals(vo.getRtnMode())){
			
			vo.setHq_cnsd_status("C16205");	// C06204 HQ_그룹장 승인 대기 ->  HQ_그룹장 승인(회신) 상태
			
			if(commonDAO.modify("las.contractmanager.updateHQstatus", vo) > 0 ){ //   HQ 의뢰 마스터 상태 변경 ( C02609)
				mod_cnt = commonDAO.modify("las.contractmanager.HQ_APPR", vo); //  어드민 리턴 테이블 에 사유 등록		
			}
			
			if(mod_cnt > 0){ // 의뢰자에게 회신 메일 발송
				
				List user_list = commonDAO.list("las.contractmanager.getREQMANaddr", vo);				
				sendHQMail(vo,user_list,"REPLY");
			}				
			
		} else	 if("REJECT".equals(vo.getRtnMode())){
			
			vo.setHq_cnsd_status("C16206");	// C06204 HQ_그룹장 승인 대기 ->   HQ_그룹장 반려 상태
			
			if(commonDAO.modify("las.contractmanager.updateHQstatus", vo) > 0 ){ //   HQ 의뢰 마스터 상태 변경 ( C02609)
				mod_cnt = commonDAO.modify("las.contractmanager.HQ_REJECT", vo); //  어드민 리턴 테이블 에 사유 등록		
			}
			if(mod_cnt > 0){ // 검토자에게 회신 반려메일  발송				
			
				List mail_user_list = commonDAO.list("las.contractmanager.getRESPMANaddr", vo); // 배정자 정보 취득				
				sendHQMail(vo,mail_user_list,"REJECT");
			}	
				
		}
		
		return mod_cnt;
	}	
	
	/**
	 * 임시 저장 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int tempSave(ConsiderationHQVO vo) throws Exception {
		
		int save_cnt = 0;				
		vo.setHq_cnsd_status("C16203");  // HQ_검토중 (C16203)				
		save_cnt = hqSave(vo);
		
		return save_cnt;
	}
	
	/**
	 * HQ HEAD 회신 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int hq01RESP(ConsiderationHQVO vo) throws Exception {
		
		int save_cnt = 0;				
		vo.setHq_cnsd_status("C16205");  // C16205 : HQ_그룹장 승인
		save_cnt = hqSave(vo);				
		
		if(save_cnt > 0){ // 의뢰자에게 회신 메일 발송
			
			List user_list = commonDAO.list("las.contractmanager.getREQMANaddr");
		
/*			List user_list = new ArrayList();			
			ListOrderedMap user_lom = new ListOrderedMap();
			
			String user_id 	= "jioeun.lee";
			String user_mail 	= "jioeun.lee@stage.samsung.com";
			
			user_lom.put("USER_ID", user_id);
			user_lom.put("EMAIL", user_mail);
			
			user_list.add(user_lom);*/
			
			sendHQMail(vo,user_list,"REPLY");
		}			
		
		return save_cnt;
	}	
	
	
	/**
	 * HQ REVIEWER 회신 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int hq02RESP(ConsiderationHQVO vo) throws Exception {
		
		int save_cnt = 0;				
		vo.setHq_cnsd_status("C16204");  // C16204 : HQ_그룹장 승인 대기			
		save_cnt = hqSave(vo);
		
		return save_cnt;
	}		
	
	
	/**
	 * 본문 (첨부파일 ) 저장 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	private int hqSave(ConsiderationHQVO vo) throws Exception {
		
		String wmode = "";		
		int save_cnt = 0;
				
		// XSS 처리
		vo.setHq_cnsdreq_id(StringUtil.bvl(vo.getHq_cnsdreq_id(),""));		
		vo.setHq_ce_cnsd_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_ce_cnsd_opnn(),"")));			// CE  
		vo.setHq_im_cnsd_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_im_cnsd_opnn(),"")));			//  IM
		vo.setHq_not_cnsd_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHq_not_cnsd_opnn(),"")));		//  NOT
		
		// 임시저장 row 를 insert 할지 update 할지 판단한다.
		List infoList = commonDAO.list("las.contractmanager.countHqRow", vo);		
				
		if(infoList != null && infoList.size() > 0){
			ListOrderedMap info = (ListOrderedMap)infoList.get(0);
			wmode = (String)info.get("WMODE");
		} else {
			return save_cnt;
		}
		
		if("RP010".equals(vo.getHq_rel_pro())){
			
			ClmsFileVO fvo_ce = new ClmsFileVO();
		
			fvo_ce.setSys_cd(vo.getSys_cd());
			fvo_ce.setFile_bigclsfcn("H020");
			fvo_ce.setFile_midclsfcn("H02001");
			fvo_ce.setFile_smlclsfcn("H0200101");
			fvo_ce.setRef_key(vo.getRef_key());
			fvo_ce.setFileInfos(vo.getFileInfosCE());
			fvo_ce.setReg_id(vo.getSession_user_id());			
			
	        clmsFileService.mngClmsAttachFile(fvo_ce);
		
		} else if("RP020".equals(vo.getHq_rel_pro())){
			
			ClmsFileVO fvo_im = new ClmsFileVO();
			
			fvo_im.setSys_cd(vo.getSys_cd());
			fvo_im.setFile_bigclsfcn("H020");
	        fvo_im.setFile_midclsfcn("H02001");
	        fvo_im.setFile_smlclsfcn("H0200102");
	        fvo_im.setRef_key(vo.getRef_key());
	        fvo_im.setFileInfos(vo.getFileInfosIM());
	        fvo_im.setReg_id(vo.getSession_user_id());
			
	        clmsFileService.mngClmsAttachFile(fvo_im);
		
			
		} else if("RP030".equals(vo.getHq_rel_pro())){
			
			ClmsFileVO fvo_ce = new ClmsFileVO();
			ClmsFileVO fvo_im = new ClmsFileVO();
			
			fvo_ce.setSys_cd(vo.getSys_cd());
			fvo_ce.setFile_bigclsfcn("H020");
	        fvo_ce.setFile_midclsfcn("H02001");
	        fvo_ce.setFile_smlclsfcn("H0200101");
	        fvo_ce.setRef_key(vo.getRef_key());
	        fvo_ce.setFileInfos(vo.getFileInfosCE());
	        fvo_ce.setReg_id(vo.getSession_user_id());
	        
	        clmsFileService.mngClmsAttachFile(fvo_ce);
	        
	        fvo_im.setSys_cd(vo.getSys_cd());
			fvo_im.setFile_bigclsfcn("H020");
	        fvo_im.setFile_midclsfcn("H02001");
	        fvo_im.setFile_smlclsfcn("H0200102");
	        fvo_im.setRef_key(vo.getRef_key());
	        fvo_im.setFileInfos(vo.getFileInfosIM());
	        fvo_im.setReg_id(vo.getSession_user_id());
	        
	        clmsFileService.mngClmsAttachFile(fvo_im);
	        
		} else {
			
			ClmsFileVO fvo_not = new ClmsFileVO();
			
			fvo_not.setSys_cd(vo.getSys_cd());
			fvo_not.setFile_bigclsfcn("H020");
			fvo_not.setFile_midclsfcn("H02001");
			fvo_not.setFile_smlclsfcn("H0200103");
			fvo_not.setRef_key(vo.getRef_key());
			fvo_not.setFileInfos(vo.getFileInfosNOT());
			fvo_not.setReg_id(vo.getSession_user_id());
			
	        clmsFileService.mngClmsAttachFile(fvo_not);
			
		}	
		
		if("INSERT".equals(wmode)){
			
			if(commonDAO.modify("las.contractmanager.updateHQstatus", vo) > 0 ){  	
				save_cnt = commonDAO.modify("las.contractmanager.insertNewHqRow", vo); //   INSERT ROW 처리
			}	
			
		} else if("UPDATE".equals(wmode)){
			
			if(commonDAO.modify("las.contractmanager.updateHQstatus", vo) > 0 ){  	
				save_cnt = commonDAO.modify("las.contractmanager.saveHqRow", vo); //   UPDATE ROW 임시저장 처리
			}		
			
		} else {
			return save_cnt;
		}
				
		return save_cnt;
	}	
	
	
	/**
	 * HQ 이력정보 목록을 조회한다. (원시 이력 리스트를 받아서 그 이력 정보를 참조하여 HQ 이력조회 정보와 합친다 ) 
	 * @param  vo ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	public List getHqHistory(ArrayList orginHisroyList , ConsiderationHQVO vo) throws Exception {
		
		List hqHistorytList = null;
		hqHistorytList = new ArrayList();
		
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(),"")));		// 계약 ID		
		
		// 1. 원시 의뢰 이력 리스트 중에서 의뢰 플래그(CONSULT) 인 것들만 추출하여, 새로운 리스트에 담는다. START		
		if(orginHisroyList != null && orginHisroyList.size() > 0){
						
			for(int i = 0 ; i < orginHisroyList.size() ; i++){
				
				ListOrderedMap org_lom = null;
				
				org_lom = new ListOrderedMap();
										
				org_lom = (ListOrderedMap) orginHisroyList.get(i);				
				
				if("CONSULT".equals((String)org_lom.get("CR_FLAG"))){
					org_lom.put("cont", StringUtil.convertEnterToBR((String)org_lom.get("cont")));
					hqHistorytList.add(org_lom);
				}					
			
			}			
		}

		
		// 2. 새로운 원시 의뢰 리스트 중 의뢰아이디 (cnsdreq_id) 가 동일한 row 인 경우, HQ ROW 를 추가 한다. START	
		String org_cnsdreq_id = "";
		
		if(hqHistorytList != null && hqHistorytList.size() > 0){
		
			for(int i = 0 ; i < hqHistorytList.size() ; i++){
				
				List hqList = null;
				ListOrderedMap new_org_lom = null;
				org_cnsdreq_id = null;
				
				new_org_lom = (ListOrderedMap) hqHistorytList.get(i);
				
				org_cnsdreq_id = (String)new_org_lom.get("CNSDREQ_ID");
				
				vo.setCnsdreq_id(org_cnsdreq_id);				
				hqList =  commonDAO.list("las.contractmanager.historyHq", vo); // HQ검토이력 정보 		
				
				if(hqList != null && hqList.size() > 0){
					
					for(int j = 0 ; j < hqList.size() ; j++){
						
						ListOrderedMap hq_lom = null;						
						hq_lom = new ListOrderedMap();
												
						hq_lom = (ListOrderedMap) hqList.get(j);										
						hq_lom.put("cont_td", StringUtil.convertEnterToBR((String)hq_lom.get("cont")));	
						hq_lom.put("cont2_td", StringUtil.convertEnterToBR((String)hq_lom.get("cont2")));			
					
					}			
				}
								
				if(hqList != null && hqList.size() > 0){
					new_org_lom.put("HQ_LIST", hqList);;
				}				
			}			
		}		
	
		return hqHistorytList;
	}

	
	
	/**
	 * 메일 발송
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public int sendHQMail(ConsiderationHQVO vo, List user_list, String mode ) throws Exception {
		try {
			
			int sent_cnt = 0;

			ListOrderedMap lom					= null;

			String mainTitle						= "";
			String contents							= "";
			String user_id	="";
			String user_mail = "";
			String today = DateUtil.dateIn(DateUtil.today());
			
			String contHtml = "";
			
			HashMap<String,String> hm = new HashMap<String,String>();

			MailVO mailVo = new MailVO();
			
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
		
			String moduleId = StringUtil.nvl(vo.getSys_cd(),"");
			
			if("".equals(moduleId)){
				moduleId = "LAS";
			}
			
			String misId = EsbUtil.generateMisId("MAIL");
			
			Locale locale1 = new Locale((String)vo.getSession_user_locale() );
			
			// 메일 내용 계약 및 HQ 의뢰정보를 취득한다.
			List reqInfo = commonDAO.list("las.contractmanager.HQmailcont", vo);
			
			ListOrderedMap info_lom = null; 
			
			if(reqInfo!=null && reqInfo.size() > 0){				
				info_lom = (ListOrderedMap)reqInfo.get(0);				
			} else {
				return 0;
			}
			
			// clm.page.field.mailsend.HQREQSend01=[ECMS]계약검토 요청이 접수되었습니다.
			// clm.page.field.mailsend.HQREQSend02=법인 검토자로부터 위 건에 대하여 검토 요청이 접수되었습니다.<br>확인 후 검토자를 배정하여 주십시오.
			// clm.page.field.mailsend.HQASSIGNSend01=[ECMS] 계약검토 담당자가 지정되었습니다.
			// clm.page.field.mailsend.HQASSIGNSend02=검토 의뢰하신 계약에 대한 검토자가 지정되었습니다.<br>검토 회신 일정 등 문의사항이 있는 경우 지정된 검토자와 직접 협의하시기 바랍니다.<br>검토 회신 완료된 경우 싱글 메일로 자동 통보됩니다.
			// clm.page.field.mailsend.HQREPLYSend01=[ECMS HQ 계약검토 결과가 회신되었습니다. 
			// clm.page.field.mailsend.HQREPLYSend02=검토 의뢰하신 계약에 대한 HQ 회신을 드렸으니 ECMS을 통하여 확인하시기 바랍니다.<br>회신 의견에 대하여 추가적인 질의사항이 있는 경우 재의뢰를 하시기 바랍니다.
			
			if("REQ".equals(mode)){

				mainTitle	= (String)messageSource.getMessage("clm.page.field.mailsend.HQREQSend01", null, locale1); 
				contents 	= (String)messageSource.getMessage("clm.page.field.mailsend.HQREQSend02", null, locale1); 
				
/*				의뢰명
				계약명
				상태
				의뢰자                        
				의뢰일
				법인 검토자
				내 용
*/
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchStatus", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("HQ_CNSD_STATUS")+ "</td></tr>"; // 상태
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqmanNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQMAN_NM")+ "</td></tr>"; // 의뢰자
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCnsdmanNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("SUB_REQMAN_NM")+ "</td></tr>"; // 법인 검토자
				contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";
				contHtml += "</table>";
				
			} else if("ASSIGN".equals(mode)){
			
				mainTitle = (String)messageSource.getMessage("clm.page.field.mailsend.HQASSIGNSend01", null, locale1);
				contents	 = (String)messageSource.getMessage("clm.page.field.mailsend.HQASSIGNSend02", null, locale1);
				
				/*				
				의뢰명
				계약명
				HQ검토자
				의뢰일
				회신일
				내 용 
				*/		
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				contHtml += "<tr><th> HQ "+(String)messageSource.getMessage("clm.page.msg.manage.reviewer", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("HQ_RESPMAN_NM") + "</td></tr>"; // HQ검토자 HQ_RESPMAN_NM
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";
				contHtml += "</table>";
				
			} else if("REPLY".equals(mode)){
				
				mainTitle = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend01", null, locale1); 
				contents	 = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend02", null, locale1);
				
				/*				
				의뢰명
				계약명
				HQ검토자
				의뢰일
				회신일
				내 용 
				*/		
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				contHtml += "<tr><th> HQ "+(String)messageSource.getMessage("clm.page.msg.manage.reviewer", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("HQ_RESPMAN_NM") + "</td></tr>"; // HQ검토자 HQ_RESPMAN_NM
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.msg.manage.replyDate", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("RESP_DT")+ "</td></tr>"; // 회신일
				contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";
				contHtml += "</table>";
				
			} else if("REJECT".equals(mode)){
				
				mainTitle = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend03", null, locale1); 
				// contents	 = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend03", null, locale1);
				
				String rtn_cont = StringUtil.convertEnterToBR(StringUtil.bvl(vo.getRtn_cont(),"")); // 배정의견
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				contHtml += "<tr><th> HQ "+(String)messageSource.getMessage("clm.page.msg.manage.reviewer", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("HQ_RESPMAN_NM") + "</td></tr>"; // HQ검토자 HQ_RESPMAN_NM
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("las.page.field.contractManager.reject", null, locale1)+" Date </th><td colspan='3'>" + (String)info_lom.get("RESP_DT")+ "</td></tr>"; // 반려일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("las.page.field.lawconsult.rejctcont", null, locale1)+"</th><td colspan='3'>" + rtn_cont + "</td></tr>"; //  반려사유
			//	contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";
				contHtml += "</table>";
				
			} else  if("ADMIN_REPLY".equals(mode)){
				
				mainTitle = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend01", null, locale1); // [ECMS]Reply to Contract HQ's Review has been sent.
				
				String rtn_cont = StringUtil.convertEnterToBR(StringUtil.bvl(vo.getRtn_cont(),"")); // 의견
			//	String rtn_user_nm =  vo.getSession_user_nm() + "/" + vo.getSession_jikgup_nm() + "/" + vo.getSession_dept_nm(); // 세션 이름 / 직급 / 부서  
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				contHtml += "<tr><th> HQ "+(String)messageSource.getMessage("clm.page.msg.manage.reviewer", null, locale1)+"</th><td colspan='3'>"  + (String)info_lom.get("SESSION_MAN_NM") + "</td></tr>"; // HQ검토자 HQ_RESPMAN_NM
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("las.page.field.contractManager.reject", null, locale1)+" Date </th><td colspan='3'>" + (String)info_lom.get("RESP_DT")+ "</td></tr>"; // 반려일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("las.page.field.lawconsult.rejctcont", null, locale1)+"</th><td colspan='3'>" + rtn_cont + "</td></tr>"; //  반려사유
				contHtml += "</table>";
				
			} else if("RE_REQ".equals(mode)){
				
				/*				의뢰명
				계약명
				상태
				의뢰자                        
				의뢰일
				법인 검토자
				내 용
                */
				
				mainTitle = (String)messageSource.getMessage("clm.page.field.mailsend.HQREPLYSend04", null, locale1); // [ECMS] Re-request for Contract has been submitted.
				
				contHtml += "<table class='list_basic mt20'>";
				contHtml += "<colgroup>";
				contHtml += "<col width='20%' />";
				contHtml += "<col width='30%'/>";
				contHtml += "<col width='14%' />";
				contHtml += "<col width='36%'/>";
				contHtml += "</colgroup>";
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqTitle", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_TITLE") + "</td></tr>"; // 의뢰명
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCntrtNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("CNTRT_NM") + "</td></tr>"; // 계약명
				//contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchStatus", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("HQ_CNSD_STATUS")+ "</td></tr>"; // 상태 
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchStatus", null, locale1)+"</th><td colspan='3'>HQ Review in Progress</td></tr>"; // 상태 the result of the SQL is already fixed stmt, so no problem even it is fixed text.
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqmanNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQMAN_NM")+ "</td></tr>"; // 의뢰자
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchReqDay", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("REQ_DT")+ "</td></tr>"; // 의뢰일
				contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.manageCommon.srchCnsdmanNm", null, locale1)+"</th><td colspan='3'>" + (String)info_lom.get("SUB_REQMAN_NM")+ "</td></tr>"; // 법인 검토자
				contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";  // 메일 내용
				contHtml += "</table>";				
			}else {
				throw new Exception("sendHQMail : parameta mode is null");				
			}
			
			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject(mainTitle);
			//보내는 사람
			//mailVo.setSender_mail_addr(vo.getSession_email());
			//mailVo.setSender_single_id(vo.getSession_single_id());
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));
			
			//로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");
			
			//메일 대상자 조회
			// List user_list = listOpinionAcceptanceUser(vo);
			
			if(user_list != null && user_list.size() > 0 ) {
				
				int mail_cnt = user_list.size();
				
				//받는 사람
				String[] iseq_ids  = null;
				String[] rec_types = null;
				String[] rec_addrs = null;
				
				iseq_ids  = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];
	
				for (int i = 0; i < user_list.size(); i++) {
					lom 		= (ListOrderedMap)user_list.get(i);
					user_id 	= (String)lom.get("USER_ID");
					user_mail 	= (String)lom.get("EMAIL");
					
					//상신자 정보 조회{EpId가 없는 경우는 관리자에게 메일 전송)
					if(user_mail == null || user_mail == ""){
						/*
						user_id 	= propertyService.getProperty("clms.admin.id");
						user_mail 	= propertyService.getProperty("clms.admin.email");
						*/
						//에러발생시 개발자에게 전달되도록 변경처리 신성우
						user_id 	= "ecms.sec";
						user_mail 	= "ecms.sec@samsung.com";
					}
					
					//STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");
					
					//iseq_ids.push("12");
					iseq_ids[i]  = "1";
					rec_types[i] = "t";
					rec_addrs[i] = user_mail;				
			        hm.put("main_title",mainTitle);
			        hm.put("main_link", propertyService.getProperty("secfw.url.domain")+"/login.do" ); 

			        hm.put("mainTitle", mainTitle);	// 메일 타이틀
			        hm.put("subject", (String)vo.getHq_req_title());	// 본사 검토 의뢰 명
			        hm.put("requester", (String)vo.getSession_user_nm());	// 요청자
			        hm.put("date", today);		//발송날짜
			        hm.put("contents", contents);		//내용
			        
			        hm.put("contHtml", contHtml);		//내용HTML

					String contentHtml = this.getMailContent(hm);
				   
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
				    mailVo.setRec_addrs(rec_addrs);
				    mailVo.setBody(contentHtml.toString());
				} //end for
				
			    if(user_mail != null && !"".equals(user_mail)) {
				    /*********************************************************
					 * 메일 내역 등록
					**********************************************************/
					esbMailService.insertMail(mailVo);
		
					/*********************************************************
					 * 메일전송
					**********************************************************/
					try {
				
						if(esbMailService.sendMail(moduleId, misId))
							sent_cnt = user_list.size();
					
					} catch (Exception e) {
				    	misId = EsbUtil.generateMisId("MAIL");
				    	mailVo.setMis_id(misId);
				    	esbMailService.insertAdminMail(mailVo);
				    	
				    	esbMailService.sendMail(moduleId, misId);
				    	
				    	misId = EsbUtil.generateMisId("MAIL");
				    	mailVo.setMis_id(misId);
				    	esbMailService.insertAdminMailSub(mailVo);
				    	
				    	esbMailService.sendMail(moduleId, misId);
					}
			    }
			
			} 
			
			return sent_cnt;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 메일 컨텐츠 생성
	 * @param HashMap
	 * @return String
	 * @throws Exception
	 */
	private String getMailContent(HashMap<String,String> hm) throws Exception{
		
		String content= "";
		StringBuffer topHtml =  new StringBuffer();	
		StringBuffer bottomHtml = new StringBuffer();
		String contHtml = "";
		
		String last_locale = StringUtil.bvl((String)hm.get("last_locale"), "en");
		
		Locale locale1 = new Locale(last_locale);
		
		String strUrl = "http://"+propertyService.getProperty("secfw.url.lasdomain");		

		String main_title =  (String)hm.get("main_title");
		String subject =  (String)hm.get("subject");
		String requester =  (String)hm.get("requester");		
		String request_date =  (String)hm.get("date");	
		String contents =  (String)hm.get("contents");
		
		contHtml = (String)hm.get("contHtml");
		
		//상단 구성
		topHtml.append("<!DOCTYPE html>");
		topHtml.append("<html>");
		topHtml.append("<head>");
		topHtml.append("<meta charset='utf-8' />");
		//topHtml.append("<meta http-equiv='X-UA-Compatible' content='IE=8; IE=9' />");

		topHtml.append("<title>"+(String)messageSource.getMessage("clm.main.title", null, locale1)+"</title>");
	
		topHtml.append("<link href='"+strUrl+"/style/las/"+locale1+"/mail.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<link href='"+strUrl+"/style/las/"+locale1+"/las.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<script language=\"javascript\" src=\""+strUrl+"/script/clms/common.js\" type=\"text/javascript\"></script>");
		//topHtml.append("<!--[if IE]> <script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script> <![endif]-->");
		topHtml.append("</head>");
		
		topHtml.append("<body>");
		topHtml.append("<div class=\"mailWrap\">");
		topHtml.append("<div class=\"mail_top\"></div>");
		topHtml.append("<div class=\"mail_mid\">");	
		
		//제목
		topHtml.append("<DIV class=page_list>");
		topHtml.append("<DIV class=in><span>" + main_title + "</span></DIV>");
		topHtml.append("</DIV>");		
		
		// 하단 SELMS+ 바로가기
		
		String slasDomain = (String)propertyService.getProperty("secfw.url.domain");
		String pageLink	= (String)messageSource.getMessage("las.mail.field.lawconsultImpl.sysLink", null, locale1);  //  SELMS+ 바로가기
		
		bottomHtml.append("<div class='tC mt20'>");
		bottomHtml.append("<span class=\"btn_mail_gosys\"><a href=\"" + slasDomain +  "\" target=_blank>"+pageLink+"</a></span>");
		bottomHtml.append("</div>");
		
		bottomHtml.append("</div>");
		bottomHtml.append("<div class=\"mail_btm\"></div>");
		bottomHtml.append("</div>");
		bottomHtml.append("</body>");
		bottomHtml.append("</html>");
		
		/** 기본구성 End **/
		
		content = topHtml.toString() + contHtml + bottomHtml.toString();	
		return content;
	
	}
}