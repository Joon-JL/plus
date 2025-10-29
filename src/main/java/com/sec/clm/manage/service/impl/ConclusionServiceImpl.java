/**
* Project Name : 계약관리시스템
* File Name :ConculsionServiceImpl.java
* Description : 체결본등록ServiceImpl
* Author : 심주완
* Date : 2011.09.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.ConclusionDelayVO;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.ConclusionService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.tnc.service.LegacyInterfaceService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.util.ClmsDataUtil;

public class ConclusionServiceImpl extends CommonServiceImpl implements ConclusionService {
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	private ConsultationService consultationService ;

	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	private LegacyInterfaceService legacyInterfaceService;
	public void setLegacyInterfaceService(LegacyInterfaceService legacyInterfaceService) {
		this.legacyInterfaceService = legacyInterfaceService;
	}
	
	/**
	 * 체결본목록조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConclusion(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConclusion", vo);
	}
	
	/**
	 * 체결본등록 정보등록 
	 * @param  vo ConclsionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyConclusion(ConclusionVO vo) throws Exception {
		int result = 0;
		ListOrderedMap lom 	= null;
		String cntrt_no = "";
		String fileAdd = "";  
		
		//체결정보입력(TN_CLM_CONTRACT_MASTER)-Update
		if("C02641".equals(vo.getDepth_status())) {	//미등록상태
			
			if("Y".equals(vo.getCntrt_cnclsn_yn())) {
				vo.setCntrt_cnclsnday(StringUtil.convertHtmlTochars(vo.getCntrt_cnclsnday().replace("-",""))); 	//계약체결일
				//vo.setExprt_notiday(StringUtil.convertHtmlTochars(vo.getExprt_notiday().replace("-",""))); 		//만료사전알림일	//신성우 주석처리 2014-04-04
				
				if("C02102".equals(vo.getSeal_mthd())) {	//날인방식이 서명일경우 
					vo.setOppnt_signman_nm(StringUtil.convertHtmlTochars(vo.getOppnt_signman_nm()));           //서명자(상대)
					vo.setOppnt_signman_dept(StringUtil.convertHtmlTochars(vo.getOppnt_signman_dept()));		//부서명
					vo.setOppnt_signman_jikgup(StringUtil.convertHtmlTochars(vo.getOppnt_signman_jikgup()));	//직급
					vo.setOppnt_signday(StringUtil.convertHtmlTochars(vo.getOppnt_signday().replace("-","")));	//서명일(상대)
					
					vo.setSignman_id(StringUtil.convertHtmlTochars(vo.getSignman_id()));						//서명자(당사)
					vo.setSignman_nm(StringUtil.convertHtmlTochars(vo.getSignman_nm()));						//서명자(당사)
					vo.setSignman_jikgup_nm(StringUtil.convertHtmlTochars(vo.getSignman_jikgup_nm()));			//서명자(당사) 직급
					vo.setSign_dept_nm(StringUtil.convertHtmlTochars(vo.getSign_dept_nm()));					//서명자(당사) 부서
					vo.setSignday(StringUtil.convertHtmlTochars(vo.getSignday().replace("-","")));				//서명일(당사)
				}
			}
			
			HashMap hm = new HashMap();
			hm.put("cntrt_id", vo.getCntrt_id());
			List list = commonDAO.list("clm.manage.selectCntrtNo", hm);
			if (list != null && list.size() > 0) {
				lom = (ListOrderedMap)list.get(0);
				
				//연도 4자리중 2자리만 넣도록 수정
				if(vo.getCntrt_cnclsnday() !=null && vo.getCntrt_cnclsnday().length()>=8) fileAdd = vo.getCntrt_cnclsnday().substring(2);
				fileAdd = fileAdd + "_" + StringUtil.bvl((String)lom.get("auto_cntrd_nm"), "");
			}
			 
			/*************************************************
			 * 첨부파일 저장
			 *************************************************/
			ClmsFileVO fvo = new ClmsFileVO();

			fvo.setSys_cd(vo.getSys_cd());
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01203");
			fvo.setFile_smlclsfcn("");
			fvo.setRef_key(vo.getCntrt_id());
			fvo.setFileInfos(vo.getFileInfos6());
			fvo.setFinalAdd(fileAdd); //날짜4자리_일련번호3자리
			fvo.setReg_id(vo.getSession_user_id());

			//신성우 변경처리 2014-04-07 true / false 여부에 따라
			if(!clmsFileService.mngClmsAttachFile(fvo, vo)){
				return 0;
			}							
		} else if("C02642".equals(vo.getDepth_status())) {	//원본등록확인
			vo.setOrg_hdovman_id(StringUtil.convertHtmlTochars(vo.getOrg_hdovman_id()));
			vo.setOrg_hdovman_nm(StringUtil.convertHtmlTochars(vo.getOrg_hdovman_nm()));
			vo.setOrg_hdov_dept_nm(StringUtil.convertHtmlTochars(vo.getOrg_hdov_dept_nm()));
			vo.setOrg_hdovman_jikgup_nm(StringUtil.convertHtmlTochars(vo.getOrg_hdovman_jikgup_nm()));
			vo.setOrg_acpt_dlay_cause(StringUtil.convertHtmlTochars(vo.getOrg_acpt_dlay_cause()));
			vo.setOrg_acptday(StringUtil.convertHtmlTochars(vo.getOrg_acptday().replace("-","")));
			vo.setOrg_strg_pos(StringUtil.convertHtmlTochars(vo.getOrg_strg_pos()));
		}
		
		
		
		//===========================제한된 길이의 계약상대명 구하기======================
		HashMap hm = new HashMap();
		hm.put("cntrt_id", vo.getCntrt_id());
		List list = commonDAO.list("clm.manage.listConclusionMaster", hm);
		 
		ListOrderedMap  keyLom = null;
		String cntrt_oppnt_nm = ""; //계약_상대_명 
		String cntrt_oppnt_nm_limit = ""; //제한길이로 자른 계약_상대_명 
		String region_gbn = ""; //지역_구분
		if(list != null && list.size() > 0){
			keyLom = (ListOrderedMap)list.get(0);
			cntrt_oppnt_nm  = (String)keyLom.get("cntrt_oppnt_nm");
			region_gbn  = (String)keyLom.get("region_gbn"); //C01801(국내), C01802(해외)
		}
		
		int icntrt_oppnt_len = 0; int iMaxLen = region_gbn.trim().equals("C01801") ? 16 : 12;
	    for ( int i=0; i<cntrt_oppnt_nm.length(); i++) {
	      char c = cntrt_oppnt_nm.charAt(i);
	       
	      String stemp = String.valueOf(c);
	      icntrt_oppnt_len = icntrt_oppnt_len + stemp.getBytes().length;   
	      cntrt_oppnt_nm_limit = cntrt_oppnt_nm_limit + stemp;
	    
	      if(iMaxLen <= icntrt_oppnt_len)break;
	    }
	    vo.setCntrt_oppnt_nm(cntrt_oppnt_nm_limit); 
	  //===========================제한된 길이의 계약상대명 구하기=end=====================
	     
		
		result = commonDAO.modify("clm.manage.modifyConclusion", vo);
		result = consultationService.modifyAuthReqClient(vo.getAuthreq_client(), vo.getCntrt_id(), vo.getSession_user_id(), vo.getSession_user_nm(), vo.getSession_dept_nm());
		
		if(!"temp".equals(vo.getWork_type())) {
			List listContractStatus = commonDAO.list("clm.manage.listConclusionMinStatus", vo);
			
			if(listContractStatus != null && listContractStatus.size() > 0) {
				ListOrderedMap tempLom = (ListOrderedMap)listContractStatus.get(0);
				//int cntrtCnt = Integer.parseInt(tempLom.get("cntrt_cnt").toString());
				//int depthStatusCnt = Integer.parseInt(tempLom.get("depth_status_cnt").toString());
				
				if("C02642".equals((String)tempLom.get("depth_status_min"))) {
					vo.setPrgrs_status("C04218");
					result = commonDAO.modify("clm.manage.modifyConclusionReqStatus", vo);
					
					/* =========== tnc 로직 추가 Start ================ */
					/* Made Comment as the Table (insertInfCntTncMatch) has already got the contract when one came from TNC. At the moment, duplicated error
					List tncInfo = commonDAO.list("common.legacyintf.getTncMatchInfo", vo);
					
					if(tncInfo.size()>0){
						
						InfContractInfoVO infContractInfoVO = new InfContractInfoVO();
						
						ListOrderedMap tncLom = (ListOrderedMap)tncInfo.get(0);
						
						infContractInfoVO.setTcomp_cd((String)tncLom.get("tcomp_cd"));
						infContractInfoVO.setKey_id((String)tncLom.get("key_id"));
						infContractInfoVO.setCntrt_no((String)tncLom.get("cntrt_no"));
						infContractInfoVO.setCntrt_nm((String)tncLom.get("cntrt_nm"));
						infContractInfoVO.setCont_draft((String)tncLom.get("cont_draft"));
						infContractInfoVO.setSys_nm("TNC");
						infContractInfoVO.setEcms_result_flag("N");
						infContractInfoVO.setReg_id("SYSTEM_BATCH");
						infContractInfoVO.setCntrt_id((String)tncLom.get("cntrt_id"));
						infContractInfoVO.setComp_cd((String)tncLom.get("comp_cd"));
						
						List tempList = commonDAO.list("common.legacyintf.countRequiredItemInfo", infContractInfoVO);
						
						if(tempList.size()>0){
							ListOrderedMap tmLom = (ListOrderedMap)tempList.get(0); 
							if("Y".equals((String)tmLom.get("check_list_yn"))){
							
								List reqItemInfo = commonDAO.list("common.legacyintf.getRequiredItemInfo", infContractInfoVO);
		
								if(reqItemInfo.size()>0){
									infContractInfoVO.setItem_cd1((String)((ListOrderedMap)reqItemInfo.get(0)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd2((String)((ListOrderedMap)reqItemInfo.get(1)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd3((String)((ListOrderedMap)reqItemInfo.get(2)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd4((String)((ListOrderedMap)reqItemInfo.get(3)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd5((String)((ListOrderedMap)reqItemInfo.get(4)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd6((String)((ListOrderedMap)reqItemInfo.get(5)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd7((String)((ListOrderedMap)reqItemInfo.get(6)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd8((String)((ListOrderedMap)reqItemInfo.get(7)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd9((String)((ListOrderedMap)reqItemInfo.get(8)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd10((String)((ListOrderedMap)reqItemInfo.get(9)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd11((String)((ListOrderedMap)reqItemInfo.get(10)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd12((String)((ListOrderedMap)reqItemInfo.get(11)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd13((String)((ListOrderedMap)reqItemInfo.get(12)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd14((String)((ListOrderedMap)reqItemInfo.get(13)).get("ITEM_YN"));
									infContractInfoVO.setItem_cd15((String)((ListOrderedMap)reqItemInfo.get(14)).get("ITEM_YN"));
									
									commonDAO.insert("common.legacyintf.insertInfCntTncMatch", infContractInfoVO);
								}
							}else{
								commonDAO.insert("common.legacyintf.insertInfCntTncMatch", infContractInfoVO);
							}
						}
						
					}
					
					// txt 파일 만들기 
					
					int exeSize = 0;
					String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.send.data.dir");
					
					if (!new File(serverDir).exists()) {
						new File(serverDir).mkdirs();
					}
				
					InfContractInfoVO infContractInfoVO = new InfContractInfoVO();
					
					infContractInfoVO.setSys_nm("TNC");
					
					List copySendInfoForList = legacyInterfaceService.loadInfCopySendinfo(infContractInfoVO);		
					
					getLogger().info("TN_INF_COPY_SENDINFO의  'SEND' 대상 수:"+copySendInfoForList.size());	
					
					exeSize = legacyInterfaceService.writeFileForCopySendInfo(copySendInfoForList,serverDir);	
					
					*/
					/* =========== tnc 로직 추가 End ================ */
				} else if("C02662".equals((String)tempLom.get("depth_status_min")))	{
					vo.setPrgrs_status("C04219");
					result = commonDAO.modify("clm.manage.modifyConclusionReqStatus", vo);
				}
			}
			
		}	
		
		return result;
	}
	
	/**
	 * 체결본등록 목록상세조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConclusion(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConclusion", vo);
	}
	
	/**
	 * 체결본등록 목록상세조회  : C02503 계약등록 만 조회
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailOnlyConclusion(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailOnlyConclusion", vo);
	}
	
	/**
	 * 체결본등록 계약목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConclusionContract(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConclusionnContract", vo);
	}
	
	/**
	 * 체결목록계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConclusionContractMaster(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConclusionContractMaster", vo);
	}
	
	/**
	 * 체결본등록 체결지연목록조회 
	 * @param  vo ConclusionDelayVO
	 * @return list
	 * @throws Exception
	 */
	public List listConclusionDelay(ConclusionDelayVO delayVo) throws Exception {
		return commonDAO.list("clm.manage.listConclusionDelay", delayVo);
	}
	
	public ListOrderedMap dropDefer(ConsultationVO vo) throws Exception {
		
		int returnVal = -1;
		ListOrderedMap returnMap = new ListOrderedMap();
		String returnStr = "N";
		
		vo.setHold_seqno(getHoldSeqno(vo));			
		vo.setHold_cause(StringUtil.convertCharsToHtml(StringUtil.bvl(vo.getChage_cause(), "")));
		
		//보류
		if("deferRequest".equals(vo.getSubmit_status())){
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());	
			vo.setDepth_status("C02647"); //보류
			//returnVal = commonDAO.modify("clm.manage.deferContCnsdreq.insert", vo);
			returnVal = commonDAO.insert("clm.manage.deferConclusion.insert", vo);
		}
		
		//보류해제
		else if("deferCancelRequest".equals(vo.getSubmit_status())){			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());	
			vo.setDepth_status("C02641"); //체결미확인
			returnVal = commonDAO.modify("clm.manage.deferContCnsdReq.update",vo);
			returnVal = commonDAO.insert("clm.manage.deferCancelConclusion.insert", vo);
			//returnVal = commonDAO.modify("clm.manage.deferContCnsdReqHold.update", vo);
		}
		
		returnVal = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus2", vo); //계약건보류

		ClmsDataUtil.debug("returnVal   >>>>   "+returnVal);
		if(returnVal > -1){ returnStr = "Y";}
		
		returnMap.put("cd", returnStr);
		returnMap.put("msg", "");
		
		return returnMap;
	}
	
	/**
	 * 의뢰건 보류  의뢰건별 SEQ 가져오기 
	 * @param cntrt_id
	 * @return
	 * @throws Exception
	 */
	public int getHoldSeqno(ConsultationVO vo) throws Exception {
		int seqNo = -1;
		List resultList = null;
		
		ClmsDataUtil.debug("==========cnsdreq_id==========>>" + vo.getCnsdreq_id());
		
		
		resultList = commonDAO.list("clm.manage.deferContCnsdreq.seqno",vo); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			
			seqNo = ((BigDecimal)lom.get("SEQNO")).intValue();			
			
			//의뢰 보류가 seqNo가 1이기때문에 seqNo가 1이면 1더한다.
			if(seqNo == 1){
				seqNo++;
			}
		} 		
		return seqNo;
	}
	
	
	/**
	 * 체결미확인에서 회신상태로 프로세스 복원
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int cancelConclusion(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(),"")));
		return commonDAO.modify("clm.manage.cancelConclusion", vo);
	}
	
	
	/**
	 * 체결목록계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConclusionContractMasterNew(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConclusionContractMaster.new", vo);
	}
	
	/**
	 * 체결본사본 수정
	 */
	public void modifyConclusionCopy(ConclusionVO vo) throws Exception {
		ListOrderedMap lom 	= null;
		String fileAdd = "";
		HashMap hm = new HashMap();
		hm.put("cntrt_id", vo.getCntrt_id());
		List list = commonDAO.list("clm.manage.selectCntrtNo", hm);
		if (list != null && list.size() > 0) {
			lom = (ListOrderedMap)list.get(0);
			
			//연도 4자리중 2자리만 넣도록 수정
			if(vo.getCntrt_cnclsnday() !=null && vo.getCntrt_cnclsnday().length()>=8) fileAdd = vo.getCntrt_cnclsnday().substring(2);
			fileAdd = fileAdd + "_" + StringUtil.bvl((String)lom.get("auto_cntrd_nm"), "");
		}
		 
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01203");
		fvo.setFile_smlclsfcn("");
		fvo.setRef_key(vo.getCntrt_id());
		fvo.setFileInfos(vo.getFileInfos6());
		fvo.setFinalAdd(fileAdd); //날짜4자리_일련번호3자리
		fvo.setReg_id(vo.getSession_user_id());
		clmsFileService.mngClmsAttachFile(fvo, vo);
	}
}