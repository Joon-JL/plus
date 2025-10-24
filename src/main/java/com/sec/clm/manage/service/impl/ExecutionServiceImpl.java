package com.sec.clm.manage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.common.util.DateUtil;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.ExecutionService;
import com.sec.common.util.ClmsDataUtil;

/**
 * Description	: 이행정보 Service Impl
 * Author 		: 신승완
 * Date			: 2011. 09. 05
 */
public class ExecutionServiceImpl extends CommonServiceImpl implements ExecutionService {

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
	public List listContract(ExecutionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContract", vo);
	}
	
	/**
	 * 이행 최대 번호를 조회한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public String getMaxExecNum(ExecutionVO vo) throws Exception{
		
		String max_exec_num = "1";
		List resultList = null;

		resultList = commonDAO.list("clm.manage.maxExecSeqno", vo);	

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			max_exec_num = (String) lom.get("max_exec_num");
		}
		return max_exec_num;
	}

	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailContract(ExecutionVO vo) throws Exception {
		//return commonDAO.list("clm.manage.detailContract", vo);
		List resultList = null;
		resultList = new ArrayList();
		
		//resultList.add(commonDAO.list("clm.manage.detailContract", vo));
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConsultationExec", vo));
		resultList.add(commonDAO.list("clm.manage.listConsultationSpecial", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelay", vo));
		//연관계약정보
		resultList.add(commonDAO.list("clm.manage.listConsultationRelation", vo));
		
		resultList.add(commonDAO.list("clm.manage.listContract", vo)); //2012-08-01 추가. 의뢰정보를 가져오기 위해...
		return resultList;
	}
	
	/**
	 * 이행정보 전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public List listExecution(ExecutionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listExecution", vo);
	}

	/**
	 * 이행정보 등록한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int insertExecution(ExecutionVO vo) throws Exception {
		int result = 0;

		int[] exec_seqno_arr = vo.getExec_seqno_arr();
		String[] exec_cont_arr = vo.getExec_cont_arr();
		String[] exec_plndday_arr = vo.getExec_plndday_arr();
		
		String[] exec_gbn_arr = vo.getExec_gbn_arr();
		String[] exec_itm_arr = vo.getExec_itm_arr();
		String[] exec_amt_arr = vo.getExec_amt_arr();
		String[] exec_cmpltday_arr = vo.getExec_cmpltday_arr();
		String[] note_arr = vo.getNote_arr();
		
		if(exec_cont_arr != null && exec_seqno_arr.length > 0) {
			
			for(int i=0; i<exec_seqno_arr.length; i++){
				vo.setCntrt_id(vo.getCntrt_id());
				vo.setExec_status("C03102");
				vo.setExec_seqno(exec_seqno_arr[i]);
				vo.setExec_cont(exec_cont_arr[i]);
				vo.setExec_plndday(exec_plndday_arr[i].replace("-", ""));
				
				vo.setExec_gbn(exec_gbn_arr[i]);
				vo.setExec_itm(exec_itm_arr[i]);
				vo.setExec_amt(new BigDecimal(StringUtil.ltrim(exec_amt_arr[i].replaceAll(",", ""))));
				vo.setNote(note_arr[i]);
				vo.setExec_cmpltday(exec_cmpltday_arr[i].replace("-", ""));
				
				if(exec_seqno_arr[i] < 0){
					result = commonDAO.insert("clm.manage.insertExecution", vo);
				}
				
				else{
					result = commonDAO.insert("clm.manage.modifyExecution", vo);
				}
			}
		}
		/* //2011.10.10 추가
		//계약관리_계약_마스터 - 계약상태C02402, 프로세스단계C02504, 단계상태C02662
		commonDAO.modify("clm.manage.modifyMstStatus", vo);
		//계약관리계약검토의뢰C04219-문서상에기존상태유지라 되어있음...필요없음 주석처리할것. 
		commonDAO.modify("clm.manage.modifyCnsdreqStatus", vo);*/
		
		/* 2011.10.18 저장 시 삭제체크된 데이터 삭제*/
		// 체크 유무 체크
//		if(vo.getExec_chk_arr()!=null){
//			int[] exec_chk_arr = vo.getExec_chk_arr();
//			
//			for(int exec_seqno:exec_chk_arr){
//				if(exec_seqno > 0){
//				vo.setExec_seqno(exec_seqno);
//				vo.setCntrt_id(vo.getCntrt_id());
//				
//				result = commonDAO.delete("clm.manage.deleteExecution", vo);
//				}
//			}
//		}
//		
		
		String pageGbn = "";
		
		pageGbn = vo.getPageGbn();
		
		if("registration".equals(pageGbn)){
			
		} else {
			result = consultationService.modifyAuthReqClient(vo.getAuthreq_client(), 
					                                         vo.getCntrt_id(), 
					                                         vo.getSession_user_id(), 
					                                         vo.getSession_user_nm(), 
					                                         vo.getSession_dept_nm());	
		}
		
		
		
		
		
		return result;
	}

	/**
	 * 이행정보 수정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyExecution(ExecutionVO vo) throws Exception {
		/* 	C03101	변경	
		C03102	확인	
		C03103	저장	
		C03104	완료	 */
		//int[] exec_seqno_arr = vo.getExec_seqno_arr();
		int exec_seqno = vo.getExec_seqno();
		String status = vo.getExec_status();
		int result = 0;

		// for(int exec_seqno:exec_seqno_arr){
		// 행추가로 생성된 로우는 초기 값 -1 로 수정 시 행추가가 되었을경우 error 방지
		/**
		 * C03102 - 확인 C03103 - 저장 C03104 - 완료
		 * 
		 * 저장 -> 확인 -> 완료
		 */
		
		if (exec_seqno > 0) {
			if (status != null || !"".equals(status)) {
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
			vo.setExec_amt(new BigDecimal(StringUtil.ltrim(vo.getExec_amt_arr()[exe_count].replaceAll(",", ""))));

			if ("C03102".equals(status)) {
				if("true".equals(vo.getExec_mod_flag())){					
					vo.setExec_cmpltday("");
					vo.setExec_plndday("Y");
				} else {					
					vo.setExec_plndday(vo.getExec_plndday_arr()[exe_count].replace("-", ""));
					vo.setExec_cmpltday(vo.getExec_cmpltday().replace("-", ""));
				}
			} else {			
				vo.setNote(vo.getNote_arr()[exe_count]);
				vo.setExec_cmpltday(vo.getExec_cmpltday_arr()[exe_count].replace("-", ""));
				vo.setExec_plndday(vo.getExec_plndday_arr()[exe_count].replace("-", ""));
			}			
		}
	// }
		

		return commonDAO.modify("clm.manage.modifyExecutionStatus", vo);
	}

	/**
	 * 이행정보 삭제한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteExecution(ExecutionVO vo) throws Exception {
		// 체크 유무 체크
		int[] exec_chk_arr = vo.getExec_chk_arr();
		
		int result = 0;
		
			for(int exec_seqno:exec_chk_arr){
				if(exec_seqno > 0){
				vo.setExec_seqno(exec_seqno);
				vo.setCntrt_id(vo.getCntrt_id());
				
				List list = commonDAO.list("clm.execution.execUpNo", vo);
				ListOrderedMap lom = (ListOrderedMap) list.get(0);
				String execUpSeq = String.valueOf(lom.get("exec_up_no"));
				vo.setExec_up_no(execUpSeq);
				vo.setExec_status("C03102");
				commonDAO.modify("clm.manage.modifyExecutionStatus2", vo);
				
				result = commonDAO.delete("clm.manage.deleteExecution", vo);
				}
			}
		return result;
	}
	
	/**
	 * 이력정보 전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listHisExecution(ExecutionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		
		//resultList.add(commonDAO.list("clm.manage.listContractReview", vo));
		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));//구주요청 검토이후에도 이력에는 보류,회신등의 모든 이력조회되도록
		resultList.add(commonDAO.list("clm.manage.listContractApprove", vo));
		resultList.add(commonDAO.list("clm.manage.listContractSign", vo));
		//사전검토정보
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));
		
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelayForMain", vo));
		
		resultList.add(commonDAO.list("clm.manage.listAgree", vo));
		
		resultList.add(commonDAO.list("clm.manage.listContractAuthreq", vo));//관련자정보
		
		return resultList;
	}
	
	
	/**
	 * 종료관리로 상태 변경
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyContractStatus(ExecutionVO vo) throws Exception {
		int result = 0;
		boolean bSuccess = false;
		List resultList = null;

		// 상태가 이행중일 때
		
		result = commonDAO.modify("clm.execution.modifyContractStatus", vo);

		List listContractStatus = commonDAO.list("clm.manage.listConclusionMinStatus", vo);
		if (listContractStatus != null && listContractStatus.size() > 0) {
			ListOrderedMap tempLom = (ListOrderedMap) listContractStatus.get(0);
			int cntrtCnt = Integer.parseInt(tempLom.get("cntrt_cnt").toString());
			int depthStatusCnt = Integer.parseInt(tempLom.get("depth_status_cnt").toString());
			if (cntrtCnt == depthStatusCnt) {
				if ("C02662".equals((String) tempLom.get("depth_status_min"))) {
					vo.setPrgrs_status("C04220");
					result = commonDAO.modify("clm.manage.modifyConclusionReqStatus", vo);
				}
			}
		}

		return result;
	}
}