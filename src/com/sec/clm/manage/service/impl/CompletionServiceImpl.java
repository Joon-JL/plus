package com.sec.clm.manage.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.CompletionService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Description	: 이행정보 Service Impl
 * Author 		: 신승완
 * Date			: 2011. 09. 05
 */
public class CompletionServiceImpl extends CommonServiceImpl implements CompletionService {

	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	private ConsultationService consultationService ;

	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContract", vo);
	}
	
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract2(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContract2", vo);
	}
	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailContract(CompletionVO vo) throws Exception {
		List<List<?>> resultList = null;
		resultList = new ArrayList();
		
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConsultationExec", vo));
		resultList.add(commonDAO.list("clm.manage.listConsultationSpecial", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelay", vo));
		//연관계약정보
		resultList.add(commonDAO.list("clm.manage.listConsultationRelation", vo));
		
		return resultList;
	}
	
	/**
	 * 이행및종료이력을 조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List listExecutionHis(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listExecution", vo);		
	}
	
	/**
	 * 이행정보 수정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyExecution(CompletionVO vo) throws Exception {
		int exec_seqno = vo.getExec_seqno();
		String status = vo.getExec_status();
		int result = 0;
		
		//for(int exec_seqno:exec_seqno_arr){
			//행추가로 생성된 로우는 초기 값 -1 로 수정 시 행추가가 되었을경우 error 방지
			if(exec_seqno > 0){
				if(status!=null || !"".equals(status)){
					if ("C03102".equals(status)) {
						//insertExecution(vo);
						if("true".equals(vo.getExec_mod_flag())){
							vo.setExec_status("C03100");						
							commonDAO.insert("clm.manage.addExecution", vo);														
							vo.setExec_status("C03101");
							
						} else {
							vo.setExec_status("C03104");
						}
					} else if ("C03103".equals(status)) {
						vo.setExec_status("C03102");
					} else if ("C03104".equals(status)) {

					} else if ("C03100".equals(status)) {
						vo.setExec_status("C03102");
					}
				}
				int exe_count = Integer.parseInt(vo.getExe_count());   
				vo.setExec_cont(vo.getExec_cont_arr()[exe_count]);
				vo.setExec_amt(BigDecimal.valueOf(Double.parseDouble(vo.getExec_amt_arr()[exe_count])));
				
				if ("C03102".equals(status)) {
					if("true".equals(vo.getExec_mod_flag())){
						vo.setExec_cmpltday("");
						vo.setExec_plndday("Y");	
					} else {
						vo.setExec_cmpltday(vo.getExec_cmpltday().replace("-", ""));
						vo.setExec_plndday(vo.getExec_plndday_arr()[exe_count].replace("-", ""));
					}
				} else {
					vo.setNote(vo.getNote_arr()[exe_count]);
					vo.setExec_cmpltday(vo.getExec_cmpltday_arr()[exe_count].replace("-", ""));
				    vo.setExec_plndday(vo.getExec_plndday_arr()[exe_count].replace("-", ""));
				}
			}
		//}
		
		return commonDAO.modify("clm.manage.modifyExecutionStatus", vo);
	}
	
	/**
	 * 이행 최대 번호를 조회한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public String getMaxExecNum(CompletionVO vo) throws Exception{
		
		String max_exec_num = "1";
		List<?> resultList = commonDAO.list("clm.manage.maxExecSeqno", vo);	

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			max_exec_num = (String) lom.get("max_exec_num");
		}
		return max_exec_num;
	}

	/**
	 * 종료 상세정보를 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailCompletion(CompletionVO vo) throws Exception {
		List resultList = new ArrayList();

		resultList.add(commonDAO.list("clm.manage.detailCompletion", vo));
		resultList.add(commonDAO.list("clm.manage.listCntrtStatus", vo));
		resultList.add(commonDAO.list("clm.manage.listContractRole", vo));
		
		//승인자(결재자)정보
		resultList.add(commonDAO.list("clm.manage.listContractApproveMan", vo));
		
		return resultList;
	}
	
	/**
	 * 종료 상세정보를 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailCompletion2(CompletionVO vo) throws Exception {
		List resultList = new ArrayList();

		resultList.add(commonDAO.list("clm.manage.detailCompletion2", vo));
		resultList.add(commonDAO.list("clm.manage.listCntrtStatus", vo));
		resultList.add(commonDAO.list("clm.manage.listContractRole", vo));
		
		//승인자(결재자)정보
		resultList.add(commonDAO.list("clm.manage.listContractApproveMan", vo));
		
		return resultList;
	}

	/**
	 * 종료정보 임시저장한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int insertTempCompletion(CompletionVO vo) throws Exception {
		
		//첨부파일 Start
		int result=0;
		
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRef_key());
		fvo.setFileInfos(vo.getFileInfos10());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		//첨부파일 End

		result = consultationService.modifyAuthReqClient(vo.getAuthreq_client(), vo.getCntrt_id(), vo.getSession_user_id(), vo.getSession_user_nm(), vo.getSession_dept_nm());
		result = commonDAO.modify("clm.manage.modifyTempCompletion", vo); 
		return result;
	}	
	
	/**
	 * 종료정보 등록한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int insertCompletion(CompletionVO vo) throws Exception {
		//결재상신
		
		//검토의뢰 상태변경
		//첨부파일 Start
		int result=0;
		
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRef_key());
		fvo.setFileInfos(vo.getFileInfos10());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		//첨부파일 End
		
		if((!"".equals(vo.getPrgrs_status())) && vo.getPrgrs_status()!=null) { 
			commonDAO.modify("clm.manage.modifyCnsdreqStatus", vo);
		}
		//기간연장일 경우에 history 관리
		
		if("C02402".equals(vo.getCntrt_status())){
			//히스토리테이블 insert
			List resultList = commonDAO.list("clm.manage.orgMngSeqno", vo);	
			int maxSeqno = 0;
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
				maxSeqno =  ((BigDecimal)lom.get("SEQNO")).intValue();
			}
			//이력이 없으면 현재 master테이블 데이터 insert 후 변경이력 insert
			if(maxSeqno < 1){
				commonDAO.modify("clm.manage.insertCompletionOrgMng", vo);
			}	
			vo.setSeqno(maxSeqno+1);
			vo.setGubun("R");
			vo.setCntrt_respman_id(vo.getSession_user_id());
			vo.setCntrt_respman_nm(vo.getSession_user_nm());
			vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());
			vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());

			commonDAO.modify("clm.manage.insertCompletionHistory", vo);
			//히스토리테이블 insert 끝
		}
		//계약연장일 경우 My Approval 확인 후 변경사유 변경해야함.
		if("C02402".equals(vo.getCntrt_status())){
			vo.setCntrt_chge_demnd_cause("");
		} 	
		result = commonDAO.modify("clm.manage.modifyCompletion", vo);
		result = consultationService.modifyAuthReqClient(vo.getAuthreq_client(), vo.getCntrt_id(), vo.getSession_user_id(), vo.getSession_user_nm(), vo.getSession_dept_nm());

		Locale locale1 = new Locale((String)vo.getSession_user_locale() );

		HashMap<String, Serializable> hm = new HashMap<String, Serializable>();

		hm.put("reqman_info"	, vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("locale", locale1);
		
		//Sungwoo 2014-06-30 email 결재 상신시 Approval in Progress Notification
//		hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
//		hm.put("mail_content"	, (String)messageSource.getMessage("clm.page.field.completion.insertCompletion04", null, locale1));
		
		if("C02402".equals(vo.getCntrt_status())){
			//Extension 
			hm.put("email.subject", messageSource.getMessage("selms.email.contract.termination.extension.subject", null, locale1));
			hm.put("main_title", messageSource.getMessage("selms.email.contract.termination.extension.contents.title", null, locale1));
			hm.put("mail_content", messageSource.getMessage("selms.email.contract.termination.extension.contents.body", null, locale1));
		}else{
			//Termination Request
			hm.put("email.subject", messageSource.getMessage("selms.email.contract.termination.inProcess.subject", null, locale1));
			hm.put("main_title", messageSource.getMessage("selms.email.contract.termination.inProcess.contents.title", null, locale1));
			hm.put("mail_content", messageSource.getMessage("selms.email.contract.termination.inProcess.contents.body", null, locale1));
		}
		
		
		mailSendService.sendAcceptInfoMail(hm);
		
		return result;
	}
	
	/**
	 * 종료정보 종료확인한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int insertCfrmCompletion(CompletionVO vo) throws Exception {
		//검토의뢰 상태변경
		int result = 0;
		
		if((!"".equals(vo.getPrgrs_status())) && vo.getPrgrs_status()!=null) { 
			commonDAO.modify("clm.manage.modifyCnsdreqStatus", vo);
		}
		
		result = commonDAO.modify("clm.manage.modifyCfrmCompletion", vo);
		
		return result;
	}

	/**
	 * 이행정보 수정한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyCompletion(CompletionVO vo) throws Exception {
//		int[] exec_seqno_arr = vo.getExec_seqno_arr();
//		String status = vo.getExec_status();
//		int result = 0;
//		
//		for(int exec_seqno:exec_seqno_arr){
//			//행추가로 생성된 로우는 초기 값 -1 로 수정 시 행추가가 되었을경우 error 방지
//			if(exec_seqno > 0){
//				if(status!=null || !"".equals(status)){
//					if("C03102".equals(status)){
//						vo.setExec_status("C03103");
//					}else if("C03103".equals(status)){
//						vo.setExec_status("C03104");
//					}else if("C03104".equals(status)){
//						
//					}
//				}
//			}
//		}
		
		return commonDAO.modify("clm.manage.modifyExecution", vo);
	}

	/**
	 * 이행정보 삭제한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
//	public int deleteCompletion(CompletionVO vo) throws Exception {
//		// 체크 유무 체크
//		int[] exec_chk_arr = vo.getExec_chk_arr();
//		
//		int result = 0;
//		
//			for(int exec_seqno:exec_chk_arr){
//				if(exec_seqno > 0){
//				vo.setExec_seqno(exec_seqno);
//				vo.setCntrt_id(vo.getCntrt_id());
//				
//				result = commonDAO.delete("clm.manage.deleteExecution", vo);
//				}
//			}
//		return result;
//	}
	
	/**
	 * 이력정보 전체목록을 조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List listHisCompletion(CompletionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		
		// hold 정보 포함 이력정보
		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));
//		resultList.add(commonDAO.list("clm.manage.listContractReview", vo));
		resultList.add(commonDAO.list("clm.manage.listContractApprove", vo));
		resultList.add(commonDAO.list("clm.manage.listContractSign", vo));
		//사전검토정보
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));
		
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelayForMain", vo));
		
		return resultList;
	}
	
	/**
	 * 권한 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray listContractRole(CompletionVO vo) throws Exception {
		
		List<?> list = commonDAO.list("clm.manage.listContractRole", vo);
		List<?> listStatus = null;
		List<?> execCmpltStatus = null;
		ListOrderedMap statuslom = null;
		
		execCmpltStatus = commonDAO.list("clm.manage.contractExecCmpltStatus", vo);
		
		JSONArray  jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		if(list!=null && list.size()>0) {
			listStatus = commonDAO.list("clm.manage.listContractStatusAll", vo);
			
			statuslom = (ListOrderedMap)listStatus.get(0);
			
			for(int i=0; i<list.size(); i++) {

				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				jsonObject = new JSONObject();

				jsonObject.put("role_cd",	 (String)lom.get("role_cd"));
				jsonObject.put("user_id",	 (String)lom.get("user_id"));
				jsonObject.put("user_nm",	 (String)lom.get("user_nm"));
				jsonObject.put("jikgup_nm",  (String)lom.get("jikgup_nm"));
				jsonObject.put("dept_nm",	 (String)lom.get("dept_nm"));
				jsonObject.put("blngt_orgnz",(String)lom.get("blngt_orgnz"));
				jsonObject.put("reqman_id",	 (String)lom.get("reqman_id"));
				jsonObject.put("session_user_id",  vo.getSession_user_id());
				jsonObject.put("cfrmYN", true);
				
				jsonObject.put("cntrt_status",	 (String)statuslom.get("cntrt_status"));
				jsonObject.put("prcs_depth",	 (String)statuslom.get("prcs_depth"));
				jsonObject.put("depth_status",	 (String)statuslom.get("depth_status"));
				jsonObject.put("prgrs_status",	 (String)statuslom.get("prgrs_status"));
				
				jsonObject.put("prgrs_status",	 (String)statuslom.get("prgrs_status"));
				
				if( execCmpltStatus != null && execCmpltStatus.size() > 0 ){
					// C03101 삭제  C03102 미확인  C03103 변경  C03104 완료
					jsonObject.put("execCmpltStatus",	 "N");
				}else{
					jsonObject.put("execCmpltStatus",	 "Y");
				}
				
				jsonArray.add(jsonObject);
			}
		}else{
			listStatus = commonDAO.list("clm.manage.listContractStatusAll", vo);
			statuslom = (ListOrderedMap)listStatus.get(0);
			
			jsonObject = new JSONObject();
			jsonObject.put("session_user_id",  vo.getSession_user_id());
			jsonObject.put("cfrmYN", false);
			
			jsonObject.put("cntrt_status",	 (String)statuslom.get("cntrt_status"));
			jsonObject.put("prcs_depth",	 (String)statuslom.get("prcs_depth"));
			jsonObject.put("depth_status",	 (String)statuslom.get("depth_status"));
			jsonObject.put("prgrs_status",	 (String)statuslom.get("prgrs_status"));
			
			if( execCmpltStatus != null && execCmpltStatus.size() > 0 ){
				// C03101 삭제  C03102 미확인  C03103 변경  C03104 완료
				jsonObject.put("execCmpltStatus",	 "N");
			}else{
				jsonObject.put("execCmpltStatus",	 "Y");
			}
			
			jsonArray.add(jsonObject);
		}
				
		return jsonArray;
	}
	
	/**
	 * 상신-검토의뢰조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailCompletionApprovalRequest(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsiderationApprovalRequest", vo);
	}
	
	/**
	 * 상신-검토의뢰계약목록조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List listCompletionApprovalContract(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsiderationApprovalContract", vo);
	}
	
	/**
	 * 상신-체결목록계약상세정보조회 .
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailConsultationContractMasterApproval(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationContractMasterApproval", vo);
	}
	
	/**
	 * 계약마스터 아이디로 특화 속성정보 가져오기
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List listConsultationSpecial(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationSpecial", vo);		
	}
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionApprovalAttachInfo(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsiderationAttachFileInfo", vo);
	}
	
	/**
	 * 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionAttachInfo(CompletionVO vo) throws Exception{
		return commonDAO.list("clm.manage.listCompletionAttachInfo", vo);
	}
	
	/**
	 * 종료품의시 제목 조회 
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailCntrtTitle(CompletionVO vo) throws Exception{
		return commonDAO.list("clm.manage.detailCntrtTitle", vo);
	}
	
	/**
	 * 종료품의 완료 결재상황 조회
	 * @param  vo CompletionVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionApprovalHis(CompletionVO vo) throws Exception{
		return commonDAO.list("clm.manage.listCompletionApprovalHis", vo);
	}
	
	
	/**
	 * 의뢰별 계약Id조회
	 */
	public List listCntrtId(CompletionVO vo) throws Exception{
		return commonDAO.list("clm.manage.listCntrtId", vo);
	}
	
	/**
	 * 검토의뢰 정보 조회
	 */
	public List detailConsideration(CompletionVO vo) throws Exception{
		return commonDAO.list("clm.manage.listConsiderationInCompletion", vo);
	}
	
	/**
	 * 관련자 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listRelationman(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listRelationman", vo);
	}
	/**
	 * 종료정보 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailManageCompletion(CompletionVO vo) throws Exception {
		List resultList = new ArrayList();
		
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));//계약정보
		resultList.add(commonDAO.list("clm.manage.listConsultationRelation", vo));//연관계약정보
		resultList.add(commonDAO.list("clm.manage.listContractApproveMan", vo));//승인자(결재자)정보
		resultList.add(commonDAO.list("clm.manage.detailCompletion", vo));//이행및 종료이력
		
		vo.setGubun("'R','A','J'");
		resultList.add(commonDAO.list("clm.manage.listOrgMngHistory", vo));//계약자동연장 이력
		resultList.add(commonDAO.list("clm.manage.listContractAuthreq", vo));//관련자정보
		
		return resultList;
	}
	/**
	 * 기간연장 승인/반려.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyAutoRenewApproval(CompletionVO vo) throws Exception {
		int result =0;
		
		//1. master 테이블 변경
		commonDAO.modify("clm.manage.modifyCfrmRenewCompletion", vo);
		
		//2. history 테이블 변경
		result = commonDAO.modify("clm.manage.modifyCfrmRenewHistory", vo);
		
		//3. 검토의뢰 테이블 변경
		if("C02504".equals(vo.getPrcs_depth())){//기간연장 승인일 때만 변경
			commonDAO.modify("clm.manage.modifyCnsdreqStatus", vo);
		}
		
		String mailContents = "";
		HashMap<String, String> hm = new HashMap<String, String>();		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );

		if("Y".equals(vo.getCntrt_chge_conf_yn())){//승인
			//메시지 처리 - 위 계약 건에 대한 자동연장 요청이 승인되었습니다.<br>계계약관리시스템을 통하여 확인하시기 바랍니다.
			mailContents = (String)messageSource.getMessage("clm.page.field.completion.insertCompletion01", null, locale1);
		}else{//반려
			//메시지 처리 - 위 계약 건에 대한 자동연장 요청이 위와 같은 사유로 반려되었습니다.<br>이후 계약관리시스템 상 프로세스 진행과 관련하여서는 계약관리자에게 문의하시기 바랍니다.
			mailContents = (String)messageSource.getMessage("clm.page.field.completion.modifyAutoRenewApproval04", null, locale1);
		}
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("receiver_id"	, vo.getReqman_id());
		hm.put("main_title"		, messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1).toString());
		hm.put("mail_content"	, mailContents);
		hm.put("req_title"		, vo.getReq_title());
		hm.put("cntrt_nm"		, vo.getCntrt_nm());
		hm.put("respman_info"	, vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
		hm.put("app_day"		, vo.getReq_dt());
		hm.put("locale"			, vo.getSession_user_locale());
		hm.put("request"		, "request");
		mailSendService.sendReqInfoMail(hm);
		
		//Changed Reviewer text
		if("Y".equals(vo.getCntrt_chge_conf_yn())){//승인
			//메시지 처리 - 위 계약 건에 대한 자동연장 요청이 승인되었습니다.<br>계계약관리시스템을 통하여 확인하시기 바랍니다.
			mailContents = (String)messageSource.getMessage("clm.page.field.completion.insertCompletion02", null, locale1);
		}

		hm.put("request"		, "review");
		hm.put("mail_content"	, mailContents);
		mailSendService.sendReqInfoMail(hm);
		
		return result;
	}
	/**
	 * 계약단계 변경
	 * @param  vo RegistrationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyCompletionStatus(CompletionVO vo) throws Exception {
		int result = 0;
		
		result = commonDAO.modify("clm.manage.modifyCompletionStatus", vo);

		//cnsdreq 상태정보 수정
		result = commonDAO.modify("clm.manage.modifyCnsdreqStatus", vo);
		 	
		return result;
	}

	
	/**
	 * close 이력 조회
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List closeRTNView(CompletionVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("las.contractmanager.close_rtn_view", vo);
	}
	
	/**
	 * TNC와 연동을 하기 위한 목록 조회
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List tncListContract(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.tnclistContract", vo);
	}

	@Override
	public List listTncLink(ConsultationVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.manage.getTnclist", vo);
	}

	@SuppressWarnings("rawtypes")
	public int countResponseManId(CompletionVO vo) throws Exception {
		List resultList=commonDAO.list("clm.manage.count.responseManId", vo);
		ListOrderedMap result=(ListOrderedMap) resultList.get(0);
		return Integer.parseInt(result.get("cnt").toString());
	}
}