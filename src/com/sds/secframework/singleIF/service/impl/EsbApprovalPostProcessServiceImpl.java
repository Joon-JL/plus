package com.sds.secframework.singleIF.service.impl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalPostProcessService;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.InterfaceManageService;
import com.sec.clm.sign.service.SignManageService;
import com.sec.clm.tnc.dto.InfContractInfoVO;
import com.sec.clm.tnc.service.LegacyInterfaceService;
import com.sec.clm.manage.service.MailSendService;

/**
 * <P>Single ESB 결재 후처리 Process Class</P>
 * 결재완료된 건에 대하여 문건 상신시 등록한 메소드명을
 * Java Reflection 기능을 사용하여 동적으로 호출처리<br>
 * <br>
 * 결재완료 후 후속프로세스가 필요한 경우, 
 * 결재상신시 입력하였던 TB_COM_START_PROCESS_WSVO 테이블의 METHOD 컬럼의 값과 동일한
 * 메소드를 본 클래스에 생성하여 필요한 후속 프로세스를 기술한다.
 * 
 * ex)메소드명 :postProcessSample<br>
 * -> 인자값은 argModuleId:모듈아이디, argMisId:MIS ID, argStatus:결재상태코드로 동일.<br>
	public void postProcessSample(String argModuleId, String argMisId, String argStatus) {<br>
		try {<br>
			// 여기에 결재 후 처리 Business Logic 기술 <br>
			getLogger().debug("MODULE_ID=" + argModuleId);<br>
			getLogger().debug("MIS_ID=" + argMisId);<br>
			getLogger().debug("STATUS=" + argStatus);<br>
			<br>
			ApprovalVO vo = new ApprovalVO();<br>
			<br>
			vo.setModule_id(argModuleId);<br>
			vo.setMis_id(argMisId);<br>
			vo.setStatus(argStatus);<br>
			vo.setPost_process_flag(argStatus);<br>
			<br>
			sqlMapClient.update("secfw.singleIF.approval.modifyPostProcessFlag", vo);<br>
			sqlMapClient.commitTransaction();<br>
			<br>
		} catch (Exception e) {<br>
        	getLogger().error( "  Exception : " + e);<br>
        } <br>
	}<br>
 * 
 * 
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class EsbApprovalPostProcessServiceImpl extends CommonServiceImpl implements EsbApprovalPostProcessService {
	
	private ConsultationService consultationService ;

	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	private LegacyInterfaceService legacyInterfaceService;
	public void setLegacyInterfaceService(LegacyInterfaceService legacyInterfaceService) {
		this.legacyInterfaceService = legacyInterfaceService;
	}
	
	/**
	 * <P>결재후처리 프로세스</P>
	 * 결재상태 조회시 진행중이 아닌 문건에 대한 후처리 프로세스 연계토록 지원.
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void postProcess(String argModuleId, String argMisId, String argStatus) {
		try {
			String ls_method = "";
				
			// 메소드명을 가져온다
			HashMap<String, String> getApprovalInfoMap = new HashMap<String, String>();            
			getApprovalInfoMap.put("module_id",argModuleId);
			getApprovalInfoMap.put("mis_id",argMisId);
			ListOrderedMap result = new ListOrderedMap();
			
			List<?> listTemp = commonDAO.list("secfw.singleIF.approval.getApprovalHeader", getApprovalInfoMap);
			if(listTemp != null && listTemp.size()>0) {
				result = (ListOrderedMap)listTemp.get(0);
			}
			
			
			if(result != null && !"".equals(((String)result.get("method")).trim())) {
				ls_method = EsbUtil.bvl((String)result.get("method"),"");
				getLogger().debug( "method Name ===> " + ls_method);

				Class[] parameterTypes = {String.class, String.class, String.class, String.class};
				Object[] args = {argModuleId, argMisId, argStatus, EsbUtil.bvl((String)result.get("ref_key"),"")};
				
				Method method = this.getClass().getMethod(ls_method, parameterTypes);
				method.invoke(this, args);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	/**
	 * <P>결재후프로세스_샘플</P>
	 * 결재처리후 후속 프로세스(샘플).
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void postProcessSample(String argModuleId, String argMisId, String argStatus) {

		// 여기에 결재 후 처리 Business Logic 기술 
		try {
			getLogger().debug("MODULE_ID=" + argModuleId);
			getLogger().debug("MIS_ID=" + argMisId);
			getLogger().debug("STATUS=" + argStatus);
			
				
			ApprovalVO vo = new ApprovalVO();
				
			vo.setModule_id(argModuleId);
			vo.setMis_id(argMisId);
			vo.setStatus(argStatus);
			vo.setPost_process_flag(argStatus);
				
			commonDAO.modify("secfw.singleIF.approval.insertPostProcessSample", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	/**
	 * <P>결재후프로세스_날인신청승인</P>
	 * 결재처리후 후속 프로세스(날인신청승인).
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void postProcessSign(String argModuleId, String argMisId, String argStatus,String refKey) {

		try {
						
			getLogger().debug("MODULE_ID=" + argModuleId);
			getLogger().debug("MIS_ID=" + argMisId);
			getLogger().debug("STATUS=" + argStatus);
				
			ApprovalVO vo = new ApprovalVO();
				
			vo.setModule_id(argModuleId);
			vo.setMis_id(argMisId);
			vo.setStatus(argStatus);
			vo.setPost_process_flag(argStatus);			

			String ls_apl_sqn = "";
			String ls_status = "";
				
			ArrayList<?> targetList = (ArrayList<?>)commonDAO.list("secfw.singleIF.approval.selectPostProcessSign", vo);
			if(targetList != null && targetList.size() > 0) {
				 
				for(int i=0; i<targetList.size(); i++){

					ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);
					ls_apl_sqn 	= (String)resultTargetMap.get("apl_sqn_char");
					ls_status 	= (String)resultTargetMap.get("status");
					HashMap<String, String> updateStatMap = new HashMap<String, String>();
					updateStatMap.put("apl_sqn",ls_apl_sqn);
					
					//2:완결, 5:전결, 6:후완결(원래는 완결만 했으나 전결,후완결도 결재가 완료된걸로 봐야된다는 싱글팀의 의견으로 결재완료로 본다. 2012-05-17)
					if(ls_status.equals("2") || ls_status.equals("5") || ls_status.equals("6")){
						updateStatMap.put("seal_rqst_status", "AP0103"); // 날인신청 상태를 승인으로 .
					} else if(ls_status.equals("3")){
						updateStatMap.put("seal_rqst_status", "AP0104"); // 날인신청 상태를 반려로 ..
					} else if(ls_status.equals("4")){
						updateStatMap.put("seal_rqst_status", "AP0105"); // 날인신청 상태를 취소로 ..
					} else{
						updateStatMap.put("seal_rqst_status", "AP0103"); // 그대로 둔다.
					}
					
					commonDAO.modify("secfw.singleIF.approval.updatePostProcessSignStatus", updateStatMap);
				}
			}			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

	
	/**
	 * <P>결재후프로세스</P>
	 * 결재처리후 후속 프로세스(이슈).
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void concludeIssue(String argModuleId, String argMisId, String argStatus) {

		// 여기에 결재 후 처리 Business Logic 기술 
		try {
	        HashMap<String, String> issueMap = new HashMap<String, String>();
	        
	        if(argStatus.equals("2") || argStatus.equals("5") || argStatus.equals("6")) {
	        	issueMap.put("issue_status", "C09060");
	        } else {
		        issueMap.put("issue_status", "C09020");
	        }
	        issueMap.put("mis_id", argMisId);
	        
        	commonDAO.modify("cpis.trk.issue.concludeIssue", issueMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	/**
	 * <P>계약관리시스템 결재승인후 상태코드 변경처리</P>
	 * 의뢰건별 품의 : 검토 품의(C03001), 체결 품의(C03002), 
	 * 계약건별 품의 : 체결후등록(C03004), 종료품의(C03003)
	 * 
	 * 특히 체결품의(C03002)에서는 합의자가 합의했는지와, 승인되었는지 동시에 체크한후
	 *   TN_CLM_CONTRACT_MASTER테이블에서 
	 *   예 : C03002 건일때 
	 *     계약건의 단계상태(DEPTH_STATUS)가   결재중(C02622) 계약건은 체결미확인(C02641) 업데이트
	 *     나머지 계약건은 자동DROP(C02603)처리   
	 *  
	 *     계약관리_계약_검토의뢰(TN_CLM_CONT_CNSDREQ)의 진행상태(PRGRS_STATUS)는 
	 *     결재중(C04214) 건은 체결미확인(C04217) 업데이트 처리     
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void postAppContStatus(String argModuleId, String argMisId, String argStatus,String refKey) {

		// 여기에 결재 후 처리 Business Logic 기술 
		try {
			getLogger().debug("MODULE_ID=" + argModuleId);
			getLogger().debug("MIS_ID=" + argMisId);
			getLogger().debug("STATUS=" + argStatus);
			getLogger().debug("cnsdreq_id=" + refKey);
			 
			//2:완결, 5:전결, 6:후완결(원래는 완결만 했으나 전결,후완결도 결재가 완료된걸로 봐야된다는 싱글팀의 의견으로 결재완료로 본다. 2012-05-17)
			if(argStatus.equals("2") || argStatus.equals("5") || argStatus.equals("6")){
				String ls_apprvl_clsfcn = "";
				String ls_cnsdreq_id = "";
				String ls_cntrt_id = "";
				String ls_cntrt_id_flag = "";
				String ls_gian_locale = ""; //기안자의 LOCALE 정보
				HashMap<String, String> targetMap = new HashMap<String, String>();
				targetMap.put("module_id", argModuleId);
				targetMap.put("mis_id", argMisId);
				targetMap.put("cnsdreq_id", refKey);
				targetMap.put("status", argStatus);
				
				ArrayList<?> targetList = (ArrayList<?>)commonDAO.list("secfw.singleIF.approval.selectPostAppContStatus", targetMap);
				if(targetList != null && targetList.size() > 0) {
					 
					for(int i=0; i<targetList.size(); i++){

						ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);
						ls_apprvl_clsfcn = ((String)resultTargetMap.get("apprvl_clsfcn")).trim();
						ls_cnsdreq_id 	= (String)resultTargetMap.get("cnsdreq_id");
						ls_cntrt_id = (String)resultTargetMap.get("cntrt_id");
						ls_gian_locale = (String)resultTargetMap.get("gian_locale");
						
						HashMap<String, String> updateDeptMap = new HashMap<String, String>();
						updateDeptMap.put("apprvl_clsfcn",ls_apprvl_clsfcn);
						updateDeptMap.put("cnsdreq_id",ls_cnsdreq_id);
						updateDeptMap.put("cntrt_id",ls_cntrt_id);
						
						commonDAO.modify("secfw.singleIF.approval.updatePostAppContStatus", updateDeptMap);
						
						getLogger().debug("=================== LAS Insert Logic Start ======================");
						getLogger().debug("### cnsdreq_id : " + ls_cnsdreq_id);
						getLogger().debug("### cntrt_id : " + ls_cntrt_id);
						getLogger().debug("=================== LAS Insert Logic End ======================");
						
						
						//계약체결시 Registration Signing
						if(ls_apprvl_clsfcn!=null && ls_apprvl_clsfcn.trim().equals("C03002")){
							//Auto Drop 계약건 메일발송 
							ListOrderedMap paramTargetMap = new ListOrderedMap();
							paramTargetMap.put("cnsdreq_id",ls_cnsdreq_id);
							paramTargetMap.put("postproc_yn","NR");	//후처리Proc 여부
							paramTargetMap.put("rc_flag","R");		//의뢰번호(R), 계약번호(C)
							paramTargetMap.put("gian_locale",ls_gian_locale);	//기안자의 locale 정보
							
							consultationService.sendDropReq(paramTargetMap);
							
						//종료품의시 종료통보메일 발송 Termination (Termination - Terminated )
						}else if(ls_apprvl_clsfcn!=null && ls_apprvl_clsfcn.trim().equals("C03003")){
							
							if(!ls_cntrt_id_flag.equals(ls_cntrt_id)){
								ls_cntrt_id_flag = ls_cntrt_id;
								
								ConsultationVO cvo = new ConsultationVO();
								cvo.setCntrt_id(ls_cntrt_id);
								cvo.setCnsdreq_id(ls_cnsdreq_id);
								cvo.setLoac(ls_gian_locale);
								
								mailSendService.sendCompleteInformMail(cvo);
								
							}
						//체결후등록 품의시 해당사업부 관리자에게 메일 전송 (Post-Conclusion Registration)
						}else if(ls_apprvl_clsfcn!=null && ls_apprvl_clsfcn.trim().equals("C03004")){
							
							HashMap<String, String> hm = new HashMap<String, String>();
							String mail_content = "";
							
							Locale locale = new Locale(ls_gian_locale);
							
							mail_content = (String)messageSource.getMessage("clm.page.field.registration.forwardInsertRegistration02", null, locale);
							mail_content += (String)messageSource.getMessage("clm.page.field.registration.forwardInsertRegistration03", null, locale);
							
							hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale));
							hm.put("mail_content"	, mail_content);
							hm.put("cnsdreq_id"		, ls_cnsdreq_id);
							hm.put("cntrt_id"		, ls_cntrt_id);
							hm.put("locale"			, ls_gian_locale);
							mailSendService.sendAcceptInfoMail(hm);
							
/* =========== tnc 로직 추가 Start ================ */
							
							ConclusionVO vo = new ConclusionVO();
							vo.setCntrt_id(ls_cntrt_id);
							
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
								
								List tempList = commonDAO.list("common.legacyintf.countRequiredItemInfoForPost", infContractInfoVO);
								
								if(tempList.size()>0){
									ListOrderedMap tempLom = (ListOrderedMap)tempList.get(0); 
									if("Y".equals((String)tempLom.get("check_list_yn"))){
								
										List reqItemInfo = commonDAO.list("common.legacyintf.getRequiredItemInfoForPost", infContractInfoVO);

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
							
//							getLogger().info("파일에 작성된 'ECMS' 송신 데이터 수:"+exeSize);	
							
							/* =========== tnc 로직 추가 End ================ */
						}						
					}
				}
			}else if(argStatus.equals("3") || argStatus.equals("4")){ //3:반려, 4:상신취소
				String ls_apprvl_clsfcn = "";
				String ls_cnsdreq_id = "";
				String ls_cntrt_id = "";
				HashMap<String, String> targetMap = new HashMap<String, String>();
				targetMap.put("module_id", argModuleId);
				targetMap.put("mis_id", argMisId);
				targetMap.put("cnsdreq_id", refKey);
				targetMap.put("status", argStatus);
				
				ArrayList<?> targetList = (ArrayList<?>)commonDAO.list("secfw.singleIF.approval.selectPostAppContStatus", targetMap);
				if(targetList != null && targetList.size() > 0) {
					 
					for(int i=0; i<targetList.size(); i++){

						ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);
						ls_apprvl_clsfcn = ((String)resultTargetMap.get("apprvl_clsfcn")).trim();
						ls_cnsdreq_id 	= (String)resultTargetMap.get("cnsdreq_id");
						ls_cntrt_id = (String)resultTargetMap.get("cntrt_id");
						  
						HashMap<String, String> updateDeptMap = new HashMap<String, String>();
						updateDeptMap.put("apprvl_clsfcn",ls_apprvl_clsfcn);
						updateDeptMap.put("cnsdreq_id",ls_cnsdreq_id);
						updateDeptMap.put("cntrt_id",ls_cntrt_id);
						updateDeptMap.put("argstatus",argStatus);
						  
						commonDAO.modify("secfw.singleIF.approval.updateReturnPostAppContStatus", updateDeptMap);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}
