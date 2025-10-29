/**
 * Project Name : 법무시스템 이식
 * File name	: ConsiderationServiceImpl.java
 * Description	: 검토의뢰 Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.clm.review.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.review.dto.ConsiderationHQVO;
import com.sec.clm.review.dto.ConsiderationVO;
import com.sec.clm.review.dto.ConsultationVO;
import com.sec.clm.review.service.ConsiderationHQService;
import com.sec.clm.review.service.ConsiderationService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.util.ClmsDataUtil;

/**
 * Description : Service impl(concrete class) Author : 한지훈 Date : 2011. 09. 06
 */
public class ConsiderationServiceImpl extends CommonServiceImpl implements ConsiderationService {

	private MailSendService mailSendService;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * ConsiderationHQ 서비스
	 */
	private ConsiderationHQService considerationHQService;

	public void setConsiderationHQService(ConsiderationHQService considerationHQService) {
		this.considerationHQService = considerationHQService;
	}

	/**
	 * 의뢰 중요도 체크 처리
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int setMarkUpAJAX(ConsiderationVO vo) throws Exception {
		return commonDAO.modify("las.contractmanager.listConsideration_mark", vo);
	}

	/**
	 * ADMIN REPLIED 처리
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int returnRequest(ConsiderationVO vo) throws Exception {

		int mod_cnt = 0;

		if (commonDAO.modify("las.contractmanager.rtn_cnsd", vo) > 0
				&& commonDAO.modify("las.contractmanager.rtn_cnsd_req", vo) > 0) { // 계약마스터
																					// /
																					// 의뢰
																					// 마스터
																					// 상태
																					// 변경
																					// (DEPTH_STARTUS
																					// C02609)
			mod_cnt = commonDAO.modify("las.contractmanager.set_rtn_reason", vo); // 어드민
																					// 리턴
																					// 테이블
																					// 에
																					// 사유
																					// 등록
		}

		return mod_cnt;
	}

	/**
	 * CLOSE 사유 조회
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List closeRTNView(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.close_rtn_view", vo);
	}

	/**
	 * CLOSE 처리
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int closeRequest(ConsiderationVO vo) throws Exception {
		return commonDAO.modify("las.contractmanager.close_cnsd", vo);
	}

	/**
	 * 의뢰내역 조회
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listConsideration(ConsiderationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setSrch_type_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_type_cd(), "")));
		// vo.setSrch_req_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_req_title(),"")));
		vo.setSrch_req_title(StringUtil.bvl(vo.getSrch_req_title(), ""));
		vo.setSrch_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_orgnz(), "")));
		vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(), "")));
		vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(), "")));
		vo.setSrch_reqman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reqman_id(), "")));
		vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reqman_nm(), "")));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prgrs_status(), "")));
		vo.setSrch_owner_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_owner_dept(), "")));
		vo.setSrch_law_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_law_status(), "")));
		vo.setSrch_ip_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_ip_status(), "")));
		vo.setSrch_respman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_respman_id(), "")));
		vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_respman_nm(), "")));
		vo.setSrch_biz_depth(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_biz_depth(), "")));
		vo.setSrch_cnclsnpurps(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cnclsnpurps(), "")));
		vo.setSrch_cntrt_oppnt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cntrt_oppnt_nm(), "")));
		vo.setSrch_cnsd_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cnsd_cont(), "")));
		vo.setSrch_closed_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_closed_yn(), "")));

		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTop_role(), ""))); // 권한직위
		vo.setPage_flag(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPage_flag(), ""))); // 페이지구분

		vo.setUser_id(vo.getSession_user_id());

		vo.setSrch_cnsdman_nm(StringUtil.bvl(vo.getSrch_cnsdman_nm(), ""));
		vo.setSrch_review_title(StringUtil.bvl(vo.getSrch_review_title(), ""));
		vo.setSrch_resp_dept(StringUtil.bvl(vo.getSrch_resp_dept(), ""));
		vo.setSrch_biz_clsfcn(StringUtil.bvl(vo.getSrch_biz_clsfcn(), ""));
		vo.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(vo.getSrch_cnclsnpurps_bigclsfcn(), ""));
		vo.setSrch_step(StringUtil.bvl(vo.getSrch_step(), ""));
		vo.setSrch_state(StringUtil.bvl(vo.getSrch_state(), ""));
		vo.setSrch_if_sys_cd(StringUtil.bvl(vo.getSrch_if_sys_cd(), ""));
		vo.setSrch_oppnt_cd(StringUtil.bvl(vo.getSrch_oppnt_cd(), ""));
		vo.setSrch_start_reqday(StringUtil.replace(StringUtil.bvl(vo.getSrch_start_reqday(), ""), "-", ""));
		vo.setSrch_end_reqday(StringUtil.replace(StringUtil.bvl(vo.getSrch_end_reqday(), ""), "-", ""));
		vo.setSrch_start_cnlsnday(StringUtil.replace(StringUtil.bvl(vo.getSrch_start_cnlsnday(), ""), "-", ""));
		vo.setSrch_end_cnlsnday(StringUtil.replace(StringUtil.bvl(vo.getSrch_end_cnlsnday(), ""), "-", ""));

		// 2014-07-18 Kevin commented. in any case page_flag has got only "2"
		// value.
		// TODO Below two unusing query has to be removed.
		/*
		 * if(vo.getPage_flag().equals("5")) {
		 * if(vo.getList_mode().equals("cnsdreq")) { return
		 * commonDAO.list("las.contractmanager.listCnsdReqTConsideration", vo);
		 * } else { return
		 * commonDAO.list("las.contractmanager.listContractTConsideration", vo);
		 * } } else { return
		 * commonDAO.list("las.contractmanager.listConsideration", vo); }
		 */

		return commonDAO.list("las.contractmanager.listConsideration", vo);

	}

	/**
	 * 상대회사관련 계약건 팝업
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listConsiderationPop(ConsultationVO vo) throws Exception {
		vo.setSrch_cntrt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_cntrt_nm(), "")));
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setCntrt_oppnt_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_oppnt_cd(), "")));
		vo.setCntrt_oppnt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_oppnt_nm(), "")));
		vo.setBiz_clsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBiz_clsfcn(), "")));
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTop_role(), ""))); // 권한직위

		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.contractmanager.listConsiderationPop", vo);
	}

	/**
	 * 유관부서 팝업
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listConsideration_under_d(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.listConsideration_under_d", vo);
	}

	/**
	 * 계약검토 상세
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List detailConsideration(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setBiz_clsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBiz_clsfcn(), "")));
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTop_role(), ""))); // 권한직위

		if ("Y".equals(vo.getGbn_last())) {// 사업부이관데이터 수정시 널포인트 에러나서 수정함
											// 20121109 윤석정
			commonDAO.modify("clm.manage.lastchangeCnsdreqStatus", vo); // 이전의뢰에
																		// 관계된
																		// 계약건
																		// 상태 변경
																		// 임시저장
																		// 상태로

			/*************************************************
			 * 계약서파일명 UPDATE(_FINAL)
			 *************************************************/
			ClmsFileVO fvo = new ClmsFileVO();

			fvo.setSys_cd(vo.getSys_cd());
			fvo.setReg_id(vo.getSession_user_id());
			// #1 계약서 첨부파일
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120201");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id()); // key
																			// 설정
																			// ~
			fvo.setFinalVersion("Y");

			clmsFileService.modifyStdCntrtFile(fvo);
		}
		return commonDAO.list("las.contractmanager.detailConsideration", vo);
	}

	/*
	 * 2014-07-23 Kevin made. Actually these logics were into
	 * detailConsideration method above and refactored it This method sets
	 * specified contract to final review in progress.
	 */
	public void updateContractAsFinalReview(ConsultationVO vo) throws Exception {

		if ("N".equals(vo.getGbn_last())) {
			vo.setGbn_last("Y");
		}

		commonDAO.modify("clm.manage.lastchangeCnsdreqStatus", vo); // 이전의뢰에 관계된
																	// 계약건 상태 변경
																	// 임시저장 상태로

		/*************************************************
		 * 계약서파일명 UPDATE(_FINAL)
		 *************************************************/
		/*
		 * Not update by EHQ and SEUK's request. ClmsFileVO fvo = new
		 * ClmsFileVO();
		 * 
		 * fvo.setSys_cd(vo.getSys_cd());
		 * fvo.setReg_id(vo.getSession_user_id()); //#1 계약서 첨부파일
		 * fvo.setFile_bigclsfcn("F012"); fvo.setFile_midclsfcn("F01202");
		 * fvo.setFile_smlclsfcn("F0120201");
		 * fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id()); //key 설정 ~
		 * fvo.setFinalVersion("Y");
		 * 
		 * clmsFileService.modifyStdCntrtFile(fvo);
		 */
	}

	/**
	 * 검토의뢰 요약 정보[검토이력 사용]
	 * 
	 * @param vo
	 *            ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List detailConsiderationCnsdInfo(ExecutionVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.detailConsiderationCnsdInfo", vo);
	}

	/**
	 * A.부서검토반려
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ListOrderedMap contractCnsdrejct(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));

		List list = commonDAO.list("las.contractmanager.contractCnsdrejct", vo);
		ListOrderedMap lom = new ListOrderedMap();

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		return lom;
	}

	/**
	 * B.계약별_부서검토반려
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ListOrderedMap contractDeptcnsdrejct(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		List list = commonDAO.list("las.contractmanager.contractDeptcnsdrejct", vo);
		ListOrderedMap lom = new ListOrderedMap();

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		return lom;
	}

	/**
	 * 담당자 리스트[팝업]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List popRespman(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토의뢰ID
		vo.setReq_operdiv(StringUtil.bvl(vo.getReq_operdiv(), "")); // 검토요청_사업부
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.popRespman", vo);
	}

	/**
	 * 그룹장 메세지
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listApbtMemo(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토의뢰ID
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.listApbtMemo", vo);
	}

	/**
	 * 담당자 리스트
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listRespman(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토의뢰ID
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.listRespman", vo);
	}

	/**
	 * 담당자 리스트[협업부서]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listRespmanSub(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토의뢰ID
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		return commonDAO.list("las.contractmanager.listRespmanSub", vo);
	}

	/**
	 * 관련자 리스트
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listRelationman(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));

		return commonDAO.list("las.contractmanager.listRelationman", vo);
	}

	/**
	 * 마스터테이블정보 리턴
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List detailContractMaster(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));
		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.contractmanager.detailContractMaster", vo);
	}

	@SuppressWarnings("rawtypes")
	public List forwardContractMaster(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("tabConsMaster   >>>>>>>>>>  ");
		// return commonDAO.list("clm.manage.detailForwardContractMaster", vo);
		return commonDAO.list("las.popupConsideration.detail", vo);
	}

	/**
	 * 권한요청자 -관련자 select
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listContractAuthreq(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContractAuthreq", vo);
	}

	/**
	 * 첨부파일목록
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listCompletionAttachInfo(ConsultationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.listCompletionAttachInfo", vo);
	}

	/**
	 * 특화정보 리턴[계약관리]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listTypeSpclinfoCntr(ConsultationVO vo) throws Exception {
		List resultList = null;

		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));

		resultList = commonDAO.list("las.contractmanager.listTypeSpclinfoCntr", vo);

		return resultList;
	}

	/**
	 * 특화정보 리턴[법무]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listTypeSpclinfoCnsd(ConsultationVO vo) throws Exception {
		List resultList = null;

		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));

		resultList = commonDAO.list("las.contractmanager.listTypeSpclinfoCnsd", vo);

		return resultList;
	}

	/**
	 * 연관계약 리턴
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String, Comparable> listRelationContract(ConsultationVO vo) throws Exception {
		StringBuffer resultSb = new StringBuffer();
		HashMap<String, Comparable> resultMap = new HashMap<String, Comparable>();

		ClmsDataUtil.debug("listRelationContract  >>>   " + vo.getCnsdreq_id());
		ClmsDataUtil.debug("listRelationContract  >>>   " + vo.getCntrt_id());
		List<?> list = commonDAO.list("las.contractmanager.listRelationContract", vo);

		/*
		 * TN_CLM_RELATION_CONTRACT 계약_ID CNTRT_ID TN_CLM_RELATION_CONTRACT
		 * 모_계약_ID PARENT_CNTRT_ID TN_CLM_RELATION_CONTRACT 관련_유형 REL_TYPE
		 * TN_CLM_RELATION_CONTRACT 관련_정의 REL_DEFN TN_CLM_RELATION_CONTRACT 설명
		 * EXPL TN_CLM_RELATION_CONTRACT 등록_일시 REG_DT TN_CLM_RELATION_CONTRACT
		 * 등록자_ID REG_ID TN_CLM_RELATION_CONTRACT 등록자_명 REG_NM
		 */

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap) list.get(i);

				resultSb.append("<tr id=\"trRelationContractCont\">");
				resultSb.append(" <td> " + (String) lom.get("real_type_nm") + "</td>");
				// resultSb.append(" <td> " + (String)lom.get("req_title") +
				// "</td>" );
				// Sungwoo Replacement 2014-07-11
				resultSb.append(" <td> <a href=\"javascript:goDetail('"
						+ StringUtil.convertHtmlTochars((String) lom.get("cnsdreq_id")) + "');\">"
						+ StringUtil.convertHtmlTochars((String) lom.get("req_title")) + "</a></td>");
				resultSb.append(" <td> " + (String) lom.get("rel_defn") + "</td>");
				resultSb.append(
						" <td> " + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) lom.get("expl")))
								+ "</td>");
				if (!"".equals(vo.getSubmit_status())) {
					resultSb.append(
							" <td><span id=\"id_relCImg\"><a href=\"javascript:actionRelationContract('delete','"
									+ (String) lom.get("parent_cntrt_id")
									+ "');\"><img src=\"/script/secfw/jquery/uploadify/cancel_new_en.gif\"></a></span></td>");
				} else {
					resultSb.append("<td></td>");
				}
				resultSb.append("</tr>");
			} // end for
		} else {
			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			// 등록된 연관계약이 없습니다.
			resultSb.append(
					"<tr id=\"trRelationContractCont\"><td colspan=\"5\">"
							+ (String) messageSource.getMessage(
									"las.page.field.considerationImpl.listRelationContract01", null, locale1)
							+ "</td></tr>");
		} // end if(list != null)

		resultMap.put("cntRc", list.size());
		resultMap.put("contRc", resultSb.toString());
		return resultMap;
	}

	/**
	 * 의견전달, 발신 취소
	 */
	public int processCancel(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setChg_prgrs_status("C04204"); // 검토
		vo.setCnsd_status("C04302"); // 작성중

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setCnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), "")));

		if (vo.getCnsd_dept().equals(vo.getMn_cnsd_dept())) { // 정부서 상태 변경
			// 계약관리_계약별_검토 진행상태 변경
			commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);
		}

		return commonDAO.modify("las.contractmanager.changeContractDeptCnsdStatus", vo);
	}

	/**
	 * 연관계약 insert/delete
	 */
	@SuppressWarnings("rawtypes")
	public int actionRelationContract(ConsultationVO vo) throws Exception {
		int returnVal = -1;

		if ("insert".equals(vo.getSubmit_status())) {
			HashMap map = listRelationContract(vo);

			if (((Integer) map.get("cntRc")) > 0) { // 0 보다 크면 이미 입력된 데이타 있음
				returnVal = 2;
			} else {
				returnVal = commonDAO.insert("las.contractmanager.insertRelationContract", vo);
			}
		} else if ("delete".equals(vo.getSubmit_status())) {
			returnVal = commonDAO.delete("las.contractmanager.deleteRelationContract", vo);
		} else {
			returnVal = -1;
		}

		return returnVal;
	}

	/**
	 * 이관요청
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int applyTrans(ConsultationVO vo) throws Exception {
		String seqno = "1";
		List resultList = null;

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));

		resultList = commonDAO.list("las.contractmanager.makeTransSeqno", vo);

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

			vo.setParam_seqno(lom.get("nextTransSeqno").toString());
		} else {
			vo.setParam_seqno(seqno);
		}

		// Cross-site Scripting 방지
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setTrnsf_demnd_cause(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTrnsf_demnd_cause(), "")));
		vo.setTrnsf_trans_status("C03801"); // 이관상태(요청)
		vo.setChg_prgrs_status("C04206"); // 진행상태(이관요청)

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		// 계약관리_계약별_검토 진행상태 변경
		commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);

		return commonDAO.modify("las.contractmanager.applyTrans", vo);
	}

	/**
	 * 법무부서 이관
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int transLawDept(ConsultationVO vo) throws Exception {

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getVc_cnsd_dept(), "")));

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setCnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), "")));
		vo.setCnsd_status("");

		if (vo.getBlngt_orgnz().equals("A00000001")) {
			vo.setCngebfr_cnsd_dept("A00000002");
		} else if (vo.getBlngt_orgnz().equals("A00000002")) {
			vo.setCngebfr_cnsd_dept("A00000001");
		}

		if (vo.getMn_cnsd_dept().equals("A00000001") || vo.getMn_cnsd_dept().equals("A00000002")) { // 법무부서가
																									// 정_검토부서일
																									// 경우
			// 1. 계약관리_계약_검토의뢰_이관(TN_CLM_CONT_CNSDREQ_TRANSFER) 테이블 UPDATE -->
			// 이관 요청중일 때만 업데이트
			if (vo.getPrgrs_status() == "C04206"
					&& (vo.getMn_cnsd_dept().equals("A00000001") || vo.getMn_cnsd_dept().equals("A00000002"))) {
				commonDAO.modify("las.contractmanager.transLawDeptTransfer", vo); // 계약관리_계약_검토의뢰_이관(TN_CLM_CONT_CNSDREQ_TRANSFER)
																					// 테이블
																					// UPDATE
			}

			// 2. 계약관리_계약_검토의뢰_담당자(TN_CLM_CONT_CNSDREQ_RESPMAN) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteRespman", vo);

			// 3. 계약관리_계약별_검토(TN_CLM_CONTRACT_CNSD) 테이블 UPDATE
			commonDAO.modify("las.contractmanager.transLawDeptContractCnsd", vo);

			// 4. 계약관리_계약별_검토반려_이력(TH_CLM_CONTRACT_CNSDREJCT) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteContractCnsdrejct", vo);

			// 5. 계약관리_계약별_부서검토(TN_CLM_CONTRACT_DEPTCNSD) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteContractDeptcnsd", vo);
			// commonDAO.modify("las.contractmanager.changeContractDeptCnsdStatus",
			// vo);

			// 6. 계약관리_계약별_부서검토반려_이력(TH_CLM_CONTRACT_DEPTCNSDREJCT) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteContractDeptcnsdrejct", vo);
		} else if (vo.getVc_cnsd_dept().equals("A00000001") || vo.getVc_cnsd_dept().equals("A00000002")) { // 법무부서가
																											// 부_검토부서일
																											// 경우
			// 1. 계약관리_계약_검토의뢰_이관(TN_CLM_CONT_CNSDREQ_TRANSFER) 테이블 UPDATE -->
			// 이관 요청중일 때만 업데이트
			// 해당없음

			// 2. 계약관리_계약_검토의뢰_담당자(TN_CLM_CONT_CNSDREQ_RESPMAN) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteRespman", vo);

			// 3. 계약관리_계약별_검토(TN_CLM_CONTRACT_CNSD) 테이블 UPDATE
			// 해당없음

			// 4. 계약관리_계약별_검토반려_이력(TH_CLM_CONTRACT_CNSDREJCT) 테이블 DELETE
			// 해당없음

			// 5. 계약관리_계약별_부서검토(TN_CLM_CONTRACT_DEPTCNSD) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteContractDeptcnsd", vo);
			// commonDAO.modify("las.contractmanager.changeContractDeptCnsdStatus",
			// vo);

			// 6. 계약관리_계약별_부서검토반려_이력(TH_CLM_CONTRACT_DEPTCNSDREJCT) 테이블 DELETE
			commonDAO.delete("las.contractmanager.deleteContractDeptcnsdrejct", vo);
		}

		// 7. 계약관리_계약_검토의뢰(TN_CLM_CONT_CNSDREQ) 테이블 UPDATE
		return commonDAO.modify("las.contractmanager.transLawDept", vo);
	}

	/**
	 * 이관불필요
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int denyTrans(ConsultationVO vo) throws Exception {
		String seqno = "1";
		List resultList = null;

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));

		resultList = commonDAO.list("las.contractmanager.makeTransSeqno", vo);

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

			vo.setParam_seqno(lom.get("nextTransSeqno").toString());
		} else {
			vo.setParam_seqno(seqno);
		}

		// Cross-site Scripting 방지
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setTrnsf_demnd_cause(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTrnsf_demnd_cause(), "")));
		vo.setTrnsf_trans_status("C03804"); // 이관상태(불필요)
		vo.setChg_prgrs_status("C04203"); // 진행상태(의뢰)

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직

		// 계약관리_계약별_검토 진행상태 변경
		commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);

		return commonDAO.modify("las.contractmanager.applyTrans", vo);
	}

	/**
	 * 이관승인
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int approveTrans(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getVc_cnsd_dept(), "")));
		vo.setTrnsf_hndl_cause(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTrnsf_hndl_cause(), ""))); // 처리의견
		vo.setChg_prgrs_status("C04203"); // 검토의뢰
		vo.setTrnsf_trans_status("C03802"); // 승인

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_dept_cd(vo.getSession_dept_cd());

		commonDAO.modify("las.contractmanager.approveTrans", vo);

		return commonDAO.modify("las.contractmanager.updateApproveTrans", vo);
	}

	/**
	 * 이관거부
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int disapproveTrans(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getVc_cnsd_dept(), "")));
		vo.setChg_prgrs_status("C04203"); // 의뢰
		vo.setTrnsf_trans_status("C03803"); // 거절
		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_dept_cd(vo.getSession_dept_cd());

		// 계약관리_계약별_검토 진행상태 변경
		commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);

		return commonDAO.modify("las.contractmanager.updateApproveTrans", vo);
	}

	/**
	 * 유관부서검토요청
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int applyResp(ConsultationVO vo) throws Exception {
		// Cross-site Scripting 방지
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setVc_cnsd_demnd_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getVc_cnsd_demnd_cont(), "")));
		vo.setChg_depth_status("C02606"); // 검토진행
		vo.setReq_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReq_title(), "")));

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_dept_cd(vo.getSession_dept_cd());

		// 계약_마스터_계약_ID
		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		for (int i = 0; i < master_cntrt_ids.length; i++) {
			if (!master_cntrt_ids[i].equals("")) {
				vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));

				// 계약관리_계약_마스터 단계상태 변경
				commonDAO.modify("las.contractmanager.changeMasterStatus", vo);
			}
		}

		// 법무 검토요청 발신 - 2012.01.04 서유강대리 요청[유관부서 관련 메일 없음]
		// mailSendService.sendConsiderationApplyMailSend(vo);

		return commonDAO.modify("las.contractmanager.applyResp", vo);
	}

	/**
	 * 담당자 의견 승인 처리
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int approvalOpnn(ConsultationVO vo) throws Exception {
		int returnValue = -1;

		@SuppressWarnings("unused")
		String decodeText = "";
		@SuppressWarnings("unused")
		HashMap hm = new HashMap();
		ConsultationVO hisMasterVo = null;
		ConsultationVO hisReqVo = null;
		ConsultationVO hisDeptVo = null;

		vo.setPage_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPage_div(), ""))); // 그룹장
																								// 권한
																								// 화면
																								// 구분값

		/*************************************************
		 * 파일첨부하기 -
		 *************************************************/

		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFinalVersion(vo.getPlndbn_req_yn());

		if (null != vo.getFileInfosContract() && !vo.getFileInfosContract().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120201");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosEtc() && !vo.getFileInfosEtc().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120208");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosGita() && !vo.getFileInfosGita().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120205");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReContract() && !vo.getFileInfosReContract().isEmpty()) {
			// 회신 - 정_계약서
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120202");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReEtc() && !vo.getFileInfosReEtc().isEmpty()) {
			// 회신 - 정_첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120209");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReGita() && !vo.getFileInfosReGita().isEmpty()) {
			// 회신 - 정_기타
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120206");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setApbt_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_opnn(), "")));
		vo.setApbt_memo(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_memo(), ""))); // 승인자_메모
		vo.setCnsd_dept(vo.getSession_blngt_orgnz()); // 검토부서
		vo.setReq_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReq_title(), ""))); // 의뢰제목

		vo.setLoac(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac(), ""))); // 준거법
		vo.setLoac_etc(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac_etc(), ""))); // 준거법
																								// 기타
		vo.setDspt_resolt_mthd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd(), ""))); // 분쟁해결방법
		vo.setDspt_resolt_mthd_det(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd_det(), ""))); // 분쟁해결방법상세
		vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMaster_cntrt_id(), ""))); // 계약ID
		vo.setActive_cntrt_id(vo.getMaster_cntrt_id()); // 활성화계약ID

		vo.setDeptcnsd_cnsd_dept(vo.getSession_blngt_orgnz());
		vo.setDeptcnsd_cnsdman_id(vo.getSession_user_id());
		vo.setDeptcnsd_cnsdman_nm(vo.getSession_user_nm());
		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());

		// 계약관리_계약별_검토 진행상태 변경
		vo.setChg_prgrs_status("C04207"); // 회신완료
		// 계약관리_계약_마스터 단계상태 변경
		vo.setChg_depth_status("C02608"); // 회신완료
		vo.setCnsd_status("C04305");
		vo.setPage_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPage_div(), ""))); // 그룹장
																								// 권한
																								// 화면
																								// 구분값

		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		if (vo.getPage_div().equals("RESP") && vo.getPlndbn_req_yn().equals("Y")) {
			if (vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz()) && "RA02".equals(vo.getTop_role())) { // 정검토부서
																										// 담당자
																										// 인경우
																										// 만
																										// 업데이트
				returnValue = commonDAO.modify("las.returnContractMaster.modify", vo);

				// TN_CLM_CONTRACT_MASTER (jnam history 처리)
				if (returnValue > 0) {
					if (null == hisMasterVo)
						hisMasterVo = new ConsultationVO();
					hisMasterVo.setSecret_keepperiod(vo.getSecret_keepperiod());
					hisMasterVo.setAuto_rnew_yn(vo.getAuto_rnew_yn());
					hisMasterVo.setOblgt_lmt(vo.getOblgt_lmt());
					hisMasterVo.setSpcl_cond(vo.getSpcl_cond());
					hisMasterVo.setLoac(vo.getLoac());
					hisMasterVo.setLoac_etc(vo.getLoac_etc());
					hisMasterVo.setDspt_resolt_mthd(vo.getDspt_resolt_mthd());
					hisMasterVo.setDspt_resolt_mthd_det(vo.getDspt_resolt_mthd_det());
					hisMasterVo.setCntrt_id(vo.getMaster_cntrt_id());
				}

			}
		}

		if (vo.getPage_div().equals("RESP") && vo.getPlndbn_req_yn().equals("Y")) { // 예정본임
																					// -
																					// master
																					// tb
			// 계약 유형특화정보 변경
			if (vo.getArr_attr_seqno() != null) {
				for (int j = 0; j < vo.getArr_attr_seqno().length; j++) {

					String[] arrTmpSeqno = vo.getArr_attr_seqno();
					String[] arrTmpCd = vo.getArr_attr_cd();
					String[] arrTmpCont = vo.getArr_attr_cont();
					vo.setAttr_seqno(arrTmpSeqno[j]);
					vo.setAttr_cd(arrTmpCd[j]);
					vo.setAttr_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpCont[j], "")));
					vo.setMod_id(vo.getSession_user_id());
					vo.setMod_nm(vo.getSession_user_nm());

					returnValue = commonDAO.modify("las.returnTypeSpclinfo.modify", vo); // 유형특화정보
				}
			}
		}

		// -2012.02.01 다수계약시 검토하지 않는 계약건을 TN_CLM_CONTRACT_DEPTCNSD 테이블에 INSERT
		// modified by hanjihoon
		for (int i = 0; i < master_cntrt_ids.length; i++) {
			vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));
			vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));

			if (getDeptCnsdCnt(vo) > 0) {// 계약별_부서_검토 reco 있으면 modify
				returnValue = commonDAO.modify("las.contractmanager.approvalOpnnDeptCnsd", vo);

				// TN_CLM_CONTRACT_DEPTCNSD (jnam history 처리)
				if (returnValue > 0) {
					if (null == hisDeptVo)
						hisDeptVo = new ConsultationVO();
					hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
					hisDeptVo.setCnsd_status(vo.getCnsd_status());
					if (vo.getTop_role().equals("RA02") || vo.getPage_div().equals("RESP")) {
						hisDeptVo.setCnsdman_id(vo.getDeptcnsd_cnsdman_id());
						hisDeptVo.setCnsdman_nm(vo.getDeptcnsd_cnsdman_nm());
					}
					if (vo.getActive_cntrt_id().equals(vo.getMaster_cntrt_id())) {
						hisDeptVo.setCnsd_opnn(vo.getDeptcnsd_cnsd_opnn());
						hisDeptVo.setMain_matr_cont(vo.getMain_matr_cont());
					}

					// if(vo.getTop_role().equals("RA01")){
					if (vo.getNot_appr_yn().equals("Y")) { // 전자검토자에게도 미결건에 대한
															// 회신 가능하도록
															// 변경(2013.07.25)
						hisDeptVo.setApbtman_id(vo.getMod_id());
						hisDeptVo.setApbtman_nm(vo.getMod_nm());
						hisDeptVo.setApbt_opnn(vo.getApbt_opnn());
					}
					hisDeptVo.setCntrt_id(vo.getMaster_cntrt_id());
					hisDeptVo.setCnsd_dept(vo.getCnsd_dept());
				}
			} else {// 없으면 insert
				returnValue = commonDAO.insert("las.contractmanager.approvalOpnnDeptCnsdinsert", vo);
				// TN_CLM_CONTRACT_DEPTCNSD (jnam history 처리)
				if (returnValue > 0) {
					if (null == hisDeptVo)
						hisDeptVo = new ConsultationVO();
					hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
					hisDeptVo.setCntrt_id(vo.getMaster_cntrt_id());
					hisDeptVo.setCnsd_dept(vo.getCnsd_dept());
					hisDeptVo.setCnsdman_id(vo.getDeptcnsd_cnsdman_id());
					hisDeptVo.setCnsdman_nm(vo.getDeptcnsd_cnsdman_nm());
					if (vo.getActive_cntrt_id().equals(vo.getMaster_cntrt_id())) {
						hisDeptVo.setCnsd_opnn(vo.getDeptcnsd_cnsd_opnn());
						hisDeptVo.setMain_matr_cont(vo.getMain_matr_cont());
					}
					// if(vo.getTop_role().equals("RA01")){
					if (vo.getNot_appr_yn().equals("Y")) { // 전자검토자에게도 미결건에 대한
															// 회신 가능하도록
															// 변경(2013.07.25)
						hisDeptVo.setApbtman_id(vo.getMod_id());
						hisDeptVo.setApbtman_nm(vo.getMod_nm());
						hisDeptVo.setApbt_opnn(vo.getApbt_opnn());
					}
					hisDeptVo.setCnsd_status(vo.getCnsd_status());
				}
			}
		}

		// 활성화 계약 데이터 수정
		// -2012.02.02 계약ID vo객체 재설정 modified by hanjihoon
		vo.setCntrt_id(vo.getActive_cntrt_id());
		vo.setCnsdman_id(vo.getSession_user_id()); // 검토자
		vo.setCnsdman_nm(vo.getSession_user_nm()); // 검토자
		returnValue = commonDAO.modify("las.returnContractCnsd.modify", vo);
		// TN_CLM_CONTRACT_DEPTCNSD (jnam history 처리)
		if (returnValue > 0) {
			if (null == hisDeptVo)
				hisDeptVo = new ConsultationVO();
			hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
			hisDeptVo.setCntrt_id(vo.getCntrt_id());
			hisDeptVo.setCnsd_dept(vo.getDeptcnsd_cnsd_dept());
			hisDeptVo.setCnsdman_id(vo.getDeptcnsd_cnsdman_id());
			hisDeptVo.setCnsdman_nm(vo.getDeptcnsd_cnsdman_nm());
			hisDeptVo.setCnsd_opnn(vo.getDeptcnsd_cnsd_opnn());
			hisDeptVo.setCnsd_status(vo.getDeptcnsd_cnsd_status());
		}
		returnValue = commonDAO.modify("las.contractmanager.approvalOpnnCnsd", vo);

		// 의뢰테이블 상태 변경
		returnValue = commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);
		// TN_CLM_CONT_CNSDREQ (jnam history 처리)
		if (returnValue > 0) {
			if (null == hisReqVo)
				hisReqVo = new ConsultationVO();
			hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
			if (vo.getChg_prgrs_status().equals("C04205") || vo.getChg_prgrs_status().equals("C04206")
					|| vo.getChg_prgrs_status().equals("C04207")) {
				hisReqVo.setPrgrs_status(vo.getChg_prgrs_status());
			} else {
				hisReqVo.setPrgrs_status("C04204");
			}
		}

		// 계약테이블 상태 변경
		for (int i = 0; i < master_cntrt_ids.length; i++) {
			if (!master_cntrt_ids[i].equals("")) {
				vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));
				returnValue = commonDAO.modify("las.contractmanager.changeMasterStatus", vo);
				// TN_CLM_CONTRACT_MASTER (jnam history 처리)
				if (returnValue > 0) {
					if (null == hisMasterVo)
						hisMasterVo = new ConsultationVO();
					hisMasterVo.setDepth_status(vo.getChg_depth_status());
					hisMasterVo.setCntrt_id(vo.getMaster_cntrt_id());
				}
			}
		}

		vo.setDeptcnsd_apbtman_id(vo.getSession_user_id());
		vo.setDeptcnsd_apbtman_nm(vo.getSession_user_nm());
		vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
		vo.setDeptcnsd_cnsd_status("C04305");

		returnValue = commonDAO.modify("las.returnDeptCnsdApbt.modifyAll", vo);
		// TN_CLM_CONTRACT_DEPTCNSD (jnam history 처리)
		if (returnValue > 0) {
			if (null == hisDeptVo)
				hisDeptVo = new ConsultationVO();
			hisDeptVo.setApbtman_id(vo.getDeptcnsd_apbtman_id());
			hisDeptVo.setApbtman_nm(vo.getDeptcnsd_apbtman_nm());
			hisDeptVo.setApbt_opnn(vo.getDeptcnsd_apbt_opnn());
			hisDeptVo.setCnsd_status(vo.getDeptcnsd_cnsd_status());
			hisDeptVo.setCnsd_dept(vo.getDeptcnsd_cnsd_dept());
			hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
		}
		// History Table insert
		// 1.TH_CLM_CONT_CNSDREQ
		if (null != hisReqVo) {
			hisReqVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisReqVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisReqVo.setSession_user_id(vo.getSession_user_id());
			hisReqVo.setSession_user_nm(vo.getSession_user_nm());
		}
		// 2.TH_CLM_CONTRACT_MASTER
		if (null != hisMasterVo) {
			hisMasterVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisMasterVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisMasterVo.setSession_user_id(vo.getSession_user_id());
			hisMasterVo.setSession_user_nm(vo.getSession_user_nm());
		}
		// 3.TH_CLM_CONTRACT_DEPTCNSD
		if (null != hisDeptVo) {
			hisDeptVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisDeptVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisDeptVo.setSession_user_id(vo.getSession_user_id());
			hisDeptVo.setSession_user_nm(vo.getSession_user_nm());
		}

		// 법무 검토회신 메일전송
		mailSendService.sendConsiderationReplyMailSend(vo);

		return returnValue;
	}

	/**
	 * 담당자 의견 반려 처리
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int rejctOpnn(ConsultationVO vo) throws Exception {
		int returnValue = 0;
		List resultList = null;
		List resultListDept = null;
		ListOrderedMap lom = null;

		ConsultationVO hisReqVo = null;
		ConsultationVO hisDeptVo = null;

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setTable_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTable_div(), "")));
		vo.setCnsd_opnn("");
		vo.setRejct_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRejct_opnn(), "")));
		vo.setCnsd_dept(vo.getSession_blngt_orgnz()); // 검토부서
		vo.setMn_cnsd_dept(vo.getMn_cnsd_dept()); // 정부서

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());

		// 계약_마스터_계약_ID
		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		for (int i = 0; i < master_cntrt_ids.length; i++) {
			if (!master_cntrt_ids[i].equals("")) {
				vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));

				if (vo.getTable_div().equals("A")) {
					resultList = commonDAO.list("las.contractmanager.makeOpnnHistSeqno", vo);
					lom = (ListOrderedMap) resultList.get(0);
					vo.setParam_seqno(lom.get("nextTransSeqno").toString());

					commonDAO.modify("las.contractmanager.OpnnHist", vo);
					resultListDept = commonDAO.list("las.contractmanager.makeOpnnHistDeptSeqno", vo);
					lom = (ListOrderedMap) resultListDept.get(0);
					vo.setParam_seqno(lom.get("nextTransSeqno").toString());

					commonDAO.modify("las.contractmanager.OpnnHistDept", vo);
				} else if (vo.getTable_div().equals("B")) {
					resultListDept = commonDAO.list("las.contractmanager.makeOpnnHistDeptSeqno", vo);
					lom = (ListOrderedMap) resultListDept.get(0);
					vo.setParam_seqno(lom.get("nextTransSeqno").toString());

					commonDAO.modify("las.contractmanager.OpnnHistDept", vo);
				}
			}
		}

		if (vo.getTable_div().equals("A")) {
			vo.setCnsd_status("C04301");
			returnValue = commonDAO.modify("las.contractmanager.changeContractCnsdStatus", vo);
			vo.setCnsd_status("C04302");
			returnValue = commonDAO.modify("las.contractmanager.changeContractDeptCnsdStatus", vo);
			if (returnValue > 0) {
				if (null == hisDeptVo)
					hisDeptVo = new ConsultationVO();
				hisDeptVo.setDepth_status(vo.getChg_depth_status());
				hisDeptVo.setCntrt_id(vo.getMaster_cntrt_id());
				hisDeptVo.setCnsd_status(vo.getCnsd_status());
				hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
				hisDeptVo.setCnsd_dept(vo.getCnsd_dept());
			}

		} else if (vo.getTable_div().equals("B")) {
			vo.setCnsd_status("C04302");
			returnValue = commonDAO.modify("las.contractmanager.changeContractDeptCnsdStatus", vo);
			if (returnValue > 0) {
				if (null == hisDeptVo)
					hisDeptVo = new ConsultationVO();
				hisDeptVo.setDepth_status(vo.getChg_depth_status());
				hisDeptVo.setCntrt_id(vo.getMaster_cntrt_id());
				hisDeptVo.setCnsd_status(vo.getCnsd_status());
				hisDeptVo.setCnsdreq_id(vo.getCnsdreq_id());
				hisDeptVo.setCnsd_dept(vo.getCnsd_dept());
			}
		}

		vo.setChg_prgrs_status("C04204"); // 검토

		// 계약관리_계약별_검토 진행상태 변경
		int result = commonDAO.modify("las.contractmanager.changeCnsdreqStatus", vo);
		if (result > 0) {
			if (null == hisReqVo)
				hisReqVo = new ConsultationVO();
			hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
			if (vo.getChg_prgrs_status().equals("C04205") || vo.getChg_prgrs_status().equals("C04206")
					|| vo.getChg_prgrs_status().equals("C04207")) {
				hisReqVo.setPrgrs_status(vo.getChg_prgrs_status());
			} else {
				hisReqVo.setPrgrs_status("C04204");
			}
		}

		// History Table insert
		// 1.TH_CLM_CONT_CNSDREQ
		if (null != hisReqVo) {
			hisReqVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisReqVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisReqVo.setSession_user_id(vo.getSession_user_id());
			hisReqVo.setSession_user_nm(vo.getSession_user_nm());
			try {
				// commonDAO.insert("las.contractmanager.insertContCnsdreqHistory",hisReqVo);
			} catch (Exception e) {
			}
		}
		// 2.TH_CLM_CONTRACT_DEPTCNSD
		if (null != hisDeptVo) {
			hisDeptVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisDeptVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisDeptVo.setSession_user_id(vo.getSession_user_id());
			hisDeptVo.setSession_user_nm(vo.getSession_user_nm());
			try {
				// commonDAO.insert("las.contractmanager.insertContractDeptcnsdHistory",hisDeptVo);
			} catch (Exception e) {
			}
		}

		return returnValue;
	}

	/**
	 * 법무담당자 지정
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int confirmRespman(ConsultationVO vo) throws Exception {
		int result = 0;
		ConsultationVO hisVo = null;
		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));

		// 등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMn_cnsd_dept(), "")));
		vo.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getVc_cnsd_dept(), "")));
		vo.setChg_prgrs_status("C04204"); // 검토
		vo.setChg_depth_status("C02606"); // 검토중
		vo.setList_respman_id(""); // 초기값
		vo.setMain_man_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMain_man_id(), ""))); // 정_검토담당자
		vo.setApbt_memo(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_memo(), ""))); // 그룹장
																								// 메세지
		vo.setAuto_apbt_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAuto_apbt_yn(), ""))); // 전결지정여부
		// vo.setFast_track_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFast_track_div(),"")));
		// // FAST_TRACK 여부

		// 법무담당자 등록
		String list_respman_ids[] = vo.getList_respman_ids();

		// 계약_마스터_계약_ID
		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		// FAST_TRACK_DIV UPDATE
		// commonDAO.modify("las.contractmanager.updateFastTrackDiv", vo);

		// 법무담당자 삭제
		commonDAO.delete("las.contractmanager.deleteRespman", vo);

		if (vo.getList_respman_ids() != null) {
			for (int i = 0; i < list_respman_ids.length; i++) {
				if (!list_respman_ids[i].equals("")) {
					vo.setList_respman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(list_respman_ids[i], "")));
					// 2012.08.08 그룹장 메세지, 전결지정여부 데이터 입력 modified by hanjihoon
					commonDAO.insert("las.contractmanager.insertRespman", vo);
				}
			}
		}
		int modcnt = 0;
		for (int i = 0; i < master_cntrt_ids.length; i++) {
			if (!master_cntrt_ids[i].equals("")) {
				vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));

				// 계약관리_계약_마스터 단계상태 변경
				modcnt = commonDAO.modify("las.contractmanager.changeMasterStatusAsign", vo);
				if (modcnt > 0) {
					hisVo = new ConsultationVO();
					hisVo.setDepth_status(vo.getChg_depth_status());
					hisVo.setCntrt_id(vo.getMaster_cntrt_id());
					hisVo.setSession_comp_cd(vo.getSession_comp_cd());
					hisVo.setSession_comp_nm(vo.getSession_comp_nm());
					hisVo.setSession_user_id(vo.getSession_user_id());
					hisVo.setSession_user_nm(vo.getSession_user_nm());
					try {
						// commonDAO.insert("las.contractmanager.insertContractMasterHistory",
						// hisVo);//TN_CLM_CONTRACT_MASTER (jnam history처리)
					} catch (Exception e) {
					}
				}
				modcnt = 0;
			}
		}

		// 계약관리_계약별_검토 진행상태 변경
		result = commonDAO.modify("las.contractmanager.modifyRespmanCnsdreq", vo);
		if (result > 0) {
			hisVo = new ConsultationVO();
			hisVo.setPrgrs_status(vo.getChg_prgrs_status());
			hisVo.setCnsdreq_id(vo.getCnsdreq_id());
			hisVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisVo.setSession_user_id(vo.getSession_user_id());
			hisVo.setSession_user_nm(vo.getSession_user_nm());
			if (StringUtil.isNull(vo.getList_respman_id())) {
				hisVo.setMn_respman_apnt_yn("N");
			} else {
				hisVo.setMn_respman_apnt_yn("Y");
			}
		}
		return result;

	}

	/**
	 * 인터페이스 연계 프로세스 실행
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return int
	 * @throws Exception
	 */

	public int insertInterfaceInfo(ConsultationVO vo) throws Exception {

		@SuppressWarnings("unused")
		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		// Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));

		return 1;
	}

	// returnTabConsideration
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap returnTabConsideration(ConsultationVO vo) throws Exception {

		ClmsDataUtil.debug(" vo.getBody_mime5()  >>" + vo.getBody_mime_dept_main());
		ClmsDataUtil.debug(" vo.getBody_mime6()  >>" + vo.getBody_mime_dept_cnsd());
		ClmsDataUtil.debug(" vo.getBody_mime7()  >>" + vo.getBody_mime_cnsd_opnn());
		ClmsDataUtil.debug(" vo.getBody_mime7()  >>" + vo.getBody_mime_cnsd_memo());

		@SuppressWarnings("unused")
		String decodeText = "";
		@SuppressWarnings("unused")
		HashMap hm = new HashMap();

		/*************************************************
		 * 파일첨부하기 -
		 *************************************************/

		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());

		if (null != vo.getFileInfosReContract() && !vo.getFileInfosReContract().isEmpty()) {
			// 회신 - 정_계약서
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120202");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id()); // 설정되는
																				// 값은
																				// 사용자에
																				// 따라
																				// 다름
			fvo.setFileInfos(vo.getFileInfosReContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReEtc() && !vo.getFileInfosReEtc().isEmpty()) {
			// 회신 - 정_첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120209");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReGita() && !vo.getFileInfosReGita().isEmpty()) {
			// 회신 - 정_기타
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120206");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcContract() && !vo.getFileInfosReVcContract().isEmpty()) {
			// 회신 - 부_계약서
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120203");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcEtc() && !vo.getFileInfosReVcEtc().isEmpty()) {
			// 회신 - 부_첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120210");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcGita() && !vo.getFileInfosReVcGita().isEmpty()) {
			// 회신 - 부_기타
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120207");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		/*********************************************************
		 * 파라미터세팅
		 **********************************************************/
		vo.setReq_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReq_title(), "")));
		// secret_keepperiod 비밀유지기간
		vo.setSecret_keepperiod(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSecret_keepperiod(), "")));

		vo.setCnsdreq_id(vo.getCnsdreq_id());
		vo.setCntrt_id(vo.getCntrt_id());
		vo.setDeptcnsd_cnsd_dept(vo.getSession_blngt_orgnz()); // 검토부서
		vo.setDeptcnsd_cnsdman_id(vo.getSession_user_id()); // 검토자
		vo.setDeptcnsd_cnsdman_nm(vo.getSession_user_nm()); // 검토자

		// vo.setCnsd_dept(vo.getSession_blngt_orgnz()); //검토부서
		vo.setCnsdman_id(vo.getSession_user_id()); // 검토자
		vo.setCnsdman_nm(vo.getSession_user_nm()); // 검토자

		// 예정본이 Y
		vo.setOblgt_lmt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getOblgt_lmt(), "")));
		vo.setSpcl_cond(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSpcl_cond(), "")));

		vo.setLoac(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac(), "")));
		vo.setLoac_etc(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac_etc(), "")));
		vo.setDspt_resolt_mthd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd(), "")));
		vo.setDspt_resolt_mthd_det(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd_det(), "")));
		vo.setApbt_memo(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_memo(), ""))); // 승인자_메모
		vo.setAuto_apbt_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAuto_apbt_yn(), ""))); // 전결지정여부

		ClmsDataUtil.debug("returnConsideration getCnsdreq_id : " + vo.getCnsdreq_id());
		ClmsDataUtil.debug("returnConsideration getCntrt_id : " + vo.getCntrt_id());
		ClmsDataUtil.debug("returnConsideration getSession_blngt_orgnz : " + vo.getDeptcnsd_cnsd_dept());
		ClmsDataUtil.debug("returnConsideration getStat_flag : " + vo.getStat_flag());
		/*********************************************************
		 * 저장/변경
		 **********************************************************/
		HashMap mapRt = new HashMap();
		int rtVal = -1;
		ClmsDataUtil.debug(">>>>>>>>>getMn_cnsd_dept : " + vo.getMn_cnsd_dept());
		ClmsDataUtil.debug(">>>>>>>>>getBlngt_orgnz : " + vo.getBlngt_orgnz());
		ClmsDataUtil.debug(">>>>>>>>>getPlndbn_req_yn : " + vo.getPlndbn_req_yn());

		if (vo.getPlndbn_req_yn().equals("Y")) {
			if (vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz()) && "RA02".equals(vo.getTop_role())) { // 정검토부서
																										// 담당자
																										// 인경우
																										// 만
																										// 업데이트
				rtVal = commonDAO.modify("las.returnContractMaster.modify", vo);
			}
		}

		if ("Y".equals(vo.getPlndbn_req_yn())) { // 예정본임 - master tb
			// 계약 유형특화정보 변경
			if (vo.getArr_attr_seqno() != null) {
				for (int j = 0; j < vo.getArr_attr_seqno().length; j++) {

					String[] arrTmpSeqno = vo.getArr_attr_seqno();
					String[] arrTmpCd = vo.getArr_attr_cd();
					String[] arrTmpCont = vo.getArr_attr_cont();
					vo.setAttr_seqno(arrTmpSeqno[j]);
					vo.setAttr_cd(arrTmpCd[j]);
					vo.setAttr_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpCont[j], "")));
					vo.setMod_id(vo.getSession_user_id());
					vo.setMod_nm(vo.getSession_user_nm());

					rtVal = commonDAO.modify("las.returnTypeSpclinfo.modify", vo); // 유형특화정보
				}
			}
		}

		// 임시저장 SAVE
		if ("TABSAVE".equals(vo.getStat_flag())) {
			// 부그룹장이 의견전달시 그룹장확인 내용
			if ("RA01".equals(vo.getTop_role()) && vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())) { // 로그인
																										// 한
																										// 사람이
																										// 정검토부서
																										// 이면서
																										// 그룹장인
																										// 경우
				// vo.setDeptcnsd_apbtman_id(vo.getSession_user_id());
				// vo.setDeptcnsd_apbtman_nm(vo.getSession_user_nm());
				vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
				vo.setDepth_status("");
				vo.setPrgrs_status("");
				vo.setCnsd_status("C04303"); // 그룹장에 회신임시저장 미결
				vo.setDeptcnsd_cnsd_status("");
			} else if ("RA01".equals(vo.getTop_role()) && vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 부
																												// 검토부서이면서
																												// 그룹장인
																												// 경우
				vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
			} else if ("RA02".equals(vo.getTop_role()) && vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 정검토부서
																												// 이면서
																												// -담당자인경우
				// C04204 검토 C04302 작성중 -- 해당없음
				vo.setDepth_status("");
				vo.setPrgrs_status("C04204");
				vo.setCnsd_status("C04302");
				vo.setDeptcnsd_cnsd_status("C04302");

			} else if ("RA02".equals(vo.getTop_role()) && vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 부검토부서
																												// 이면서
																												// 담당자인경우
				// C04204 검토 C04301 미작성 C04302 작성중
				vo.setDepth_status("");
				vo.setPrgrs_status("C04204");
				vo.setCnsd_status("C04301");
				vo.setDeptcnsd_cnsd_status("C04302");
			}
		}

		ClmsDataUtil.debug("##############################################");
		ClmsDataUtil.debug("vo.getStat_flag()      	>>>   " + vo.getStat_flag()); // 마스터테이블
																					// 회신
																					// 완료
		ClmsDataUtil.debug("vo.getDepth_status()      	>>>   " + vo.getDepth_status()); // 마스터테이블
																							// 회신
																							// 완료
		ClmsDataUtil.debug("vo.getPrgrs_status()   		>>>   " + vo.getPrgrs_status());
		ClmsDataUtil.debug("vo.getCnsd_status()   		>>>   " + vo.getCnsd_status());
		ClmsDataUtil.debug("vo.getDeptcnsd_cnsd_status()>>>   " + vo.getDeptcnsd_cnsd_status());
		ClmsDataUtil.debug("##############################################");

		// 계약별_부서_검토 reco
		if (!"".equals(vo.getDeptcnsd_cnsd_status())) {
			// 부그룹장
			if ("RA01".equals(vo.getTop_role()) && !"SAVE".equals(vo.getStat_flag())) {
				rtVal = commonDAO.modify("las.returnDeptCnsdApbt.modify", vo); // 상태값
																				// 완료
																				// 검토의견없뎃
																				// 의뢰에
																				// 계약
																				// 여러건
																				// 상태
																				// 업데이트
				// 부담당자
			} else if ("RA02".equals(vo.getTop_role())) {
				if (getDeptCnsdCnt(vo) > 0) {// 계약별_부서_검토 reco 있으면 modify
					rtVal = commonDAO.modify("las.returnDeptCnsd.modify", vo); // 상태값
																				// 완료
																				// 검토의견없뎃
																				// 의뢰에
																				// 계약
																				// 여러건
																				// 상태
																				// 업데이트
				} else {// 없으면 insert
					rtVal = commonDAO.insert("las.returnDeptcnsd.insert", vo); // 상태값
																				// 완료
																				// 검토의견없뎃
																				// 의뢰에
																				// 계약
																				// 여러건
																				// 상태
																				// 업데이트
				}
			}
		}

		// 계약별_검토
		if (vo.getBlngt_orgnz().equals(vo.getMn_cnsd_dept()) && !"".equals(vo.getCnsd_status())) {
			rtVal = commonDAO.modify("las.returnContractCnsd.modify", vo); // 상태값
																			// 완료
																			// 검토의견없뎃
																			// 의뢰에
																			// 계약
																			// 여러건
																			// 상태
																			// 업데이트
		}

		// 마스터 테이블 상태값 변경
		if (!"".equals(vo.getDepth_status())) {
			if (("A00000003".equals(vo.getMn_cnsd_dept()) && "A00000003".equals(vo.getBlngt_orgnz())
					&& "Y".equals(vo.getPlndbn_req_yn())) || "Y".equals(vo.getAuto_apbt_yn())) { // 아이피센터
				rtVal = commonDAO.modify("las.returnDepthStatus.modify", vo); // 상태값
																				// 완료
																				// 검토의견없뎃
																				// 의뢰에
																				// 계약
																				// 여러건
																				// 상태
																				// 업데이트
			}
		}

		mapRt.put("returnVal", rtVal);
		Locale locale1 = new Locale(vo.getSession_user_locale());

		// 정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
		mapRt.put("returnMsg", (String) messageSource
				.getMessage("las.page.field.considerationImpl.returnTabConsideration01", null, locale1));
		mapRt.put("depthStatus", vo.getDepth_status());
		mapRt.put("prgrsStatus", vo.getPrgrs_status());
		mapRt.put("cnsdStatus", vo.getCnsd_status());
		mapRt.put("deptcnsdCnsdStatus", vo.getDeptcnsd_cnsd_status());

		return mapRt;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap modifyContractMaster(ConsultationVO vo) throws Exception {

		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFinalVersion(vo.getPlndbn_req_yn());
		if (null != vo.getFileInfos1() && "" != vo.getFileInfos1()) {
			// #1 계약서 첨부파일
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120201");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id()); // key
																			// 설정
																			// ~
			fvo.setFileInfos(vo.getFileInfos1());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosEtc() && "" != vo.getFileInfosEtc()) {
			// fileListEtc 첨부파일 - 첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120208");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id()); // key
																			// 설정
																			// ~
			fvo.setFileInfos(vo.getFileInfosEtc());
			clmsFileService.mngClmsAttachFile(fvo);
		}
		if (null != vo.getFileInfos2() && "" != vo.getFileInfos2()) {
			// #2 계약서 기타첨부파일
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120205");
			fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id()); // key
																			// 설정
																			// ~
			fvo.setFileInfos(vo.getFileInfos2());
			clmsFileService.mngClmsAttachFile(fvo);
		}

		if (null != vo.getFileInfos3() && "" != vo.getFileInfos3()) {
			// #3 단가첨부파일
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120211");
			fvo.setRef_key(vo.getCntrt_id()); // key 설정 ~
			fvo.setFileInfos(vo.getFileInfos3());
			clmsFileService.mngClmsAttachFile(fvo);
		}
		if (null != vo.getFileInfos4() && "" != vo.getFileInfos4()) {
			// #4사전검토정보첨부파일
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01201");
			fvo.setFile_smlclsfcn("F0120101");
			fvo.setRef_key(vo.getCntrt_id()); // key 설정 ~
			fvo.setFileInfos(vo.getFileInfos4());
			clmsFileService.mngClmsAttachFile(fvo);
		}

		/*********************************************************
		 * 파라미터세팅
		 **********************************************************/

		// 의뢰정보
		vo.getDmstfrgn_gbn();
		vo.setReq_title(StringUtil.convertHtmlTochars(vo.getReq_title()));
		vo.setPlndbn_chge_cont(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getPlndbn_chge_cont(), "")));// 예정본_변경_내용

		// 거래 상대방 내역
		vo.setCntrt_oppnt_rprsntman(
				StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_oppnt_rprsntman(), "")));
		vo.setCntrt_oppnt_cd(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_oppnt_cd(), "")));
		vo.setCntrt_oppnt_email(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_oppnt_email(), "")));
		vo.setCntrt_oppnt_telno(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_oppnt_telno(), "")));
		vo.setCntrt_oppnt_respman(
				StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_oppnt_respman(), "")));

		// 기대효과 <BR>ANTCPTNEFCT
		vo.setAntcptnefct(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getAntcptnefct(), "")));
		// 지불/수분조건 <BR>PAYMENT_COND
		vo.setPayment_cond(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getPayment_cond(), "")));
		// 기타주요사항 <BR>ETC_MAIN_CONT
		vo.setEtc_main_cont(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getEtc_main_cont(), "")));
		// 단가내역cntrt_untprc_expl
		vo.setCntrt_untprc_expl(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_untprc_expl(), "")));
		// 계약대상 상세내역cntrt_trgt_det
		vo.setCntrt_trgt_det(StringUtil.convertHtmlTochars(StringUtil.bvl((String) vo.getCntrt_trgt_det(), "")));

		vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(), '-'));

		vo.setAuto_rnew_yn("Y");// not null 없어질것
		vo.setCnclsn_plndday(DateUtil.getTime("yyyyMMdd")); // 체결_예정일 수정 요망

		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-')); // 사전품의_승인일

		// 계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));

		vo.setSecret_keepperiod(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSecret_keepperiod(), "")));

		vo.setCnsdreq_id(vo.getCnsdreq_id());
		vo.setCntrt_id(vo.getCntrt_id());

		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());

		// 예정본이 Y
		vo.setOblgt_lmt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getOblgt_lmt(), "")));
		vo.setSpcl_cond(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSpcl_cond(), "")));

		vo.setLoac(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac(), "")));
		vo.setLoac_etc(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac_etc(), "")));
		vo.setDspt_resolt_mthd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd(), "")));
		vo.setDspt_resolt_mthd_det(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd_det(), "")));

		/*********************************************************
		 * 저장/변경
		 **********************************************************/
		HashMap mapRt = new HashMap();
		int rtVal = -1;

		vo.getCntrt_oppnt_cd();

		vo.setCnsd_demnd_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsd_demnd_cont(), "")));
		vo.setPlndbn_chge_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPlndbn_chge_cont(), "")));

		// 의뢰 수정
		rtVal = commonDAO.modify("las.popupConsideration.modify", vo);
		// CLM_CONT_CNSDREQ history table insert
		if (rtVal > 0) {
			ConsultationVO hisVo = new ConsultationVO();
			hisVo.setCnsdreq_id(vo.getCnsdreq_id());
			hisVo.setDmstfrgn_gbn(vo.getDmstfrgn_gbn());
			hisVo.setReq_title(vo.getReq_title());
			hisVo.setLastbn_chge_yn(vo.getLastbn_chge_yn());
			hisVo.setCnsd_demnd_cont((vo.getCnsd_demnd_cont()));
			hisVo.setPlndbn_chge_cont(vo.getPlndbn_chge_cont());
			hisVo.setSession_comp_cd(vo.getSession_comp_cd());
			hisVo.setSession_comp_nm(vo.getSession_comp_nm());
			hisVo.setSession_user_id(vo.getSession_user_id());
			hisVo.setSession_user_nm(vo.getSession_user_nm());
		}

		// 계약마스터 수정
		rtVal = commonDAO.modify("las.popupContractMaster.modify", vo);

		// 2012.03.12 관련자 리스트 기능 추가 added by hanjihoon
		// 관련자 수정여부
		vo.setClient_modify_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getClient_modify_div(), "")));

		if (vo.getClient_modify_div().equals("Y")) {

			// 계약_마스터_계약_ID
			String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

			for (int i = 0; i < master_cntrt_ids.length; i++) {
				if (!master_cntrt_ids[i].equals("")) {
					vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));
					rtVal = commonDAO.delete("las.contractmanager.deleteContractAuthreq", vo); // 권한요청
																								// 관련자
																								// 초기화

					for (int j = 0; j < vo.getArr_trgtman_id().length; j++) {
						String[] arrTmpId = vo.getArr_trgtman_id();
						String[] arrTmpNm = vo.getArr_trgtman_nm();
						String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
						String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();

						vo.setDemnd_seqno(Integer.toString(j + 1));
						vo.setDemndman_id(vo.getSession_user_id()); // 요청자 아이디
						vo.setDemndman_nm(vo.getSession_user_nm());
						vo.setDemndman_dept_nm(vo.getSession_dept_nm());
						vo.setTrgtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpId[j], "")));
						vo.setTrgtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpNm[j], "")));
						vo.setTrgtman_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpDeptNm[j], "")));
						vo.setTrgtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpJikgupNm[j], "")));
						vo.setRd_auth("C03601");
						vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
						vo.setDemnd_gbn("C04601");
						vo.setDemnd_status("C03702"); // 승인처리 해야 법무에서 확인 가능

						rtVal = commonDAO.insert("las.contractmanager.insertContractAuthreq", vo);
					}
				}
			}
		}

		Locale locale1 = new Locale((String) vo.getSession_user_locale());

		if (rtVal > -1) {
			mapRt.put("returnVal", rtVal);
			// 정상적으로 처리 되었습니다.
			mapRt.put("returnMsg", (String) messageSource
					.getMessage("las.page.field.considerationImpl.modifyContractMaster01", null, locale1));
			mapRt.put("depthStatus", vo.getDepth_status());
			mapRt.put("prgrsStatus", vo.getPrgrs_status());
			mapRt.put("cnsdStatus", vo.getCnsd_status());
			mapRt.put("deptcnsdCnsdStatus", vo.getDeptcnsd_cnsd_status());
		} else {

			mapRt.put("returnVal", rtVal);
			// 정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
			mapRt.put("returnMsg", (String) messageSource
					.getMessage("las.page.field.considerationImpl.modifyContractMaster02", null, locale1));
			mapRt.put("depthStatus", vo.getDepth_status());
			mapRt.put("prgrsStatus", vo.getPrgrs_status());
			mapRt.put("cnsdStatus", vo.getCnsd_status());
			mapRt.put("deptcnsdCnsdStatus", vo.getDeptcnsd_cnsd_status());
		}
		return mapRt;
	}

	/**
	 * 검토 상세에서 상시 관련자 추가 기능
	 * 
	 * @param vo
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	public int modifyRefCCAJAX(ConsultationVO vo) throws Exception {

		int rtVal = 0;

		vo.setClient_modify_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getClient_modify_div(), "")));

		if (vo.getClient_modify_div().equals("Y")) {

			if (!vo.getMaster_cntrt_id().equals("")) {
				rtVal = commonDAO.delete("las.contractmanager.deleteContractAuthreq", vo); // 권한요청
																							// 관련자
																							// 초기화

				for (int j = 0; j < vo.getArr_trgtman_id().length; j++) {
					String[] arrTmpId = vo.getArr_trgtman_id();
					String[] arrTmpNm = vo.getArr_trgtman_nm();
					String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
					String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();

					vo.setDemnd_seqno(Integer.toString(j + 1));
					vo.setDemndman_id(vo.getSession_user_id()); // 요청자 아이디
					vo.setDemndman_nm(vo.getSession_user_nm());
					vo.setDemndman_dept_nm(vo.getSession_dept_nm());
					vo.setTrgtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpId[j], "")));
					vo.setTrgtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpNm[j], "")));
					vo.setTrgtman_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpDeptNm[j], "")));
					vo.setTrgtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpJikgupNm[j], "")));
					vo.setRd_auth("C03601");
					vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
					vo.setDemnd_gbn("C04601");
					vo.setDemnd_status("C03702"); // 승인처리 해야 법무에서 확인 가능

					rtVal = commonDAO.insert("las.contractmanager.insertContractAuthreq", vo);
				}
			}

		}

		return rtVal;

	}

	/**
	 * 선택 되지 않은 계약건의 필수 조건 체크
	 * 
	 * @param vo
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ListOrderedMap searchRequired(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));
		vo.setUser_id(vo.getSession_user_id());
		List returnList = commonDAO.list("clm.manage.searchRequired", vo);

		if (returnList != null && returnList.size() > 0) {// RET_MSG ret_msg
			returnLom = (ListOrderedMap) returnList.get(0);
			returnLom.put("ret_msg", StringUtil.replace((String) returnLom.get("ret_msg"), "|*|", "\n\r"));
		}
		return returnLom;
	}

	/**
	 * 임시저장 , 의견전달, 발신
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap returnConsideration(ConsultationVO vo) throws Exception {

		String mailSendYn = "N";
		vo.setActive_cntrt_id(vo.getCntrt_id());

		/*************************************************
		 * 파일첨부하기 -
		 *************************************************/

		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFinalVersion(vo.getPlndbn_req_yn());
		fvo.setView_gbn(vo.getView_gbn()); // Sungwoo added 2014-09-25 reAttach
											// 구분 처리위해

		if (null != vo.getFileInfosContract() && !vo.getFileInfosContract().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120201");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosEtc() && !vo.getFileInfosEtc().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120208");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosGita() && !vo.getFileInfosGita().isEmpty()) {
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120205");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReContract() && !vo.getFileInfosReContract().isEmpty()) {
			// 회신 - 정_계약서
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120202");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReEtc() && !vo.getFileInfosReEtc().isEmpty()) {
			// 회신 - 정_첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120209");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReGita() && !vo.getFileInfosReGita().isEmpty()) {
			// 회신 - 정_기타
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120206");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id());
			fvo.setFileInfos(vo.getFileInfosReGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcContract() && !vo.getFileInfosReVcContract().isEmpty()) {
			// 회신 - 부_계약서
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120203");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcContract());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcEtc() && !vo.getFileInfosReVcEtc().isEmpty()) {
			// 회신 - 부_첨부/별첨
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120210");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcEtc());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		if (null != vo.getFileInfosReVcGita() && !vo.getFileInfosReVcGita().isEmpty()) {
			// 회신 - 부_기타
			fvo.setFile_bigclsfcn("F012");
			fvo.setFile_midclsfcn("F01202");
			fvo.setFile_smlclsfcn("F0120207");
			fvo.setRef_key(vo.getMaster_cntrt_id() + "@" + vo.getCnsdreq_id() + "@" + vo.getVc_cnsd_dept());
			fvo.setFileInfos(vo.getFileInfosReVcGita());
			clmsFileService.mngClmsAttachFile(fvo, vo);
		}

		/*********************************************************
		 * 파라미터세팅
		 **********************************************************/
		vo.setReq_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReq_title(), "")));
		// secret_keepperiod 비밀유지기간
		vo.setSecret_keepperiod(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSecret_keepperiod(), "")));

		vo.setCnsdreq_id(vo.getCnsdreq_id());
		vo.setCntrt_id(vo.getCntrt_id());
		vo.setDeptcnsd_cnsd_dept(vo.getSession_blngt_orgnz()); // 검토부서
		vo.setDeptcnsd_cnsdman_id(vo.getSession_user_id()); // 검토자
		vo.setDeptcnsd_cnsdman_nm(vo.getSession_user_nm()); // 검토자
		vo.setCnsdman_id(vo.getSession_user_id()); // 검토자
		vo.setCnsdman_nm(vo.getSession_user_nm()); // 검토자

		// 예정본이 Y
		vo.setOblgt_lmt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getOblgt_lmt(), "")));
		vo.setSpcl_cond(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSpcl_cond(), "")));

		vo.setLoac(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac(), "")));
		vo.setLoac_etc(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac_etc(), "")));
		vo.setDspt_resolt_mthd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd(), "")));
		vo.setDspt_resolt_mthd_det(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd_det(), "")));
		vo.setApbt_memo(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_memo(), ""))); // 승인자_메모

		/*********************************************************
		 * 저장/변경
		 **********************************************************/
		HashMap mapRt = new HashMap();
		int rtVal = -1;

		if (vo.getPlndbn_req_yn().equals("Y")) {
			if (vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())
					&& ("RA02".equals(vo.getTop_role()) || "RB01".equals(vo.getTop_role()))) { // 정검토부서
																								// 담당자
																								// 인경우
																								// 만
																								// 업데이트
				// 2014-07-23 Kevin added.
				if (vo.getAuto_rnew_yn() == null) {
					vo.setAuto_rnew_yn("N");
				}
				rtVal = commonDAO.modify("las.returnContractMaster.modify", vo); // 계약
																					// 마스터
																					// 테이블에
																					// update
																					// 쿼리
			}
		}

		if ("Y".equals(vo.getPlndbn_req_yn())) { // 예정본임 - master tb
			// 계약 유형특화정보 변경
			if (vo.getArr_attr_seqno() != null) {
				for (int j = 0; j < vo.getArr_attr_seqno().length; j++) {

					String[] arrTmpSeqno = vo.getArr_attr_seqno();
					String[] arrTmpCd = vo.getArr_attr_cd();
					String[] arrTmpCont = vo.getArr_attr_cont();
					vo.setAttr_seqno(arrTmpSeqno[j]);
					vo.setAttr_cd(arrTmpCd[j]);
					vo.setAttr_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(arrTmpCont[j], "")));
					vo.setMod_id(vo.getSession_user_id());
					vo.setMod_nm(vo.getSession_user_nm());

					rtVal = commonDAO.modify("las.returnTypeSpclinfo.modify", vo); // 유형특화정보
				}
			}
		}

		// 임시저장 SAVE
		if ("SAVE".equals(vo.getStat_flag())) {

			// 부그룹장이 의견전달시 그룹장확인 내용
			if ("RA01".equals(vo.getTop_role()) && vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())) { // 로그인
																										// 한
																										// 사람이
																										// 정검토부서
																										// 이면서
																										// 그룹장인
																										// 경우
				vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
				vo.setDepth_status("");
				vo.setPrgrs_status("");
				vo.setCnsd_status("C04303"); // 그룹장에 회신임시저장 미결
				vo.setDeptcnsd_cnsd_status("");
			} else if ("RA01".equals(vo.getTop_role()) && vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 부
																												// 검토부서이면서
																												// 그룹장인
																												// 경우
				vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
			} else if ("RA02".equals(vo.getTop_role()) && vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 정검토부서
																												// 이면서
																												// -담당자인경우
				// C04204 검토 C04302 작성중 -- 해당없음
				vo.setDepth_status("");
				vo.setPrgrs_status("C04204");
				vo.setCnsd_status("C04302");
				vo.setDeptcnsd_cnsd_status("C04302");
			} else if ("RB01".equals(vo.getTop_role())) {// 로그인 한 사람이 전자검토자인 경우
				// C04204 검토 C04302 작성중 -- 해당없음
				vo.setDepth_status("");
				vo.setPrgrs_status("C04204");
				vo.setCnsd_status("C04302");
				vo.setDeptcnsd_cnsd_status("C04302");
			} else if ("RA02".equals(vo.getTop_role()) && vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인
																												// 한
																												// 사람이
																												// 부검토부서
																												// 이면서
																												// 담당자인경우
				// C04204 검토 C04301 미작성 C04302 작성중
				vo.setDepth_status("");
				vo.setPrgrs_status("C04204");
				vo.setCnsd_status("C04301");
				vo.setDeptcnsd_cnsd_status("C04302");
			}
		}

		// 의견전달 DELIVERY - 로그인 한 사람이 부검토부서 인경우-검토의견
		if ("DELIVERY".equals(vo.getStat_flag())) {

			if (vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인 한 사람이
																	// 부검토부서
																	// 인경우-검토의견

				vo.setPage_div(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPage_div(), ""))); // 그룹장
																										// 권한
																										// 화면
																										// 구분값

				// 부그룹장이 의견전달시 그룹장확인 내용 부 그룹장 의견전달
				if ("RA01".equals(vo.getTop_role()) || "RESP".equals(vo.getPage_div())
						|| "Y".equals(vo.getAuto_apbt_yn())) {

					vo.setDeptcnsd_apbtman_id(vo.getSession_user_id());
					vo.setDeptcnsd_apbtman_nm(vo.getSession_user_nm());
					vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
					vo.setDepth_status("");
					vo.setPrgrs_status("C04204");
					vo.setCnsd_status("C04301");
					vo.setDeptcnsd_cnsd_status("C04305");

					// 부검토부서에 담당자 일때
				} else if ("RA02".equals(vo.getTop_role()) || "RB01".equals(vo.getTop_role())) {
					// C04305 최종 검토완료 - 검토부서가 아이피 센터 인 경우
					// C04204 검토 C04301 미작성 C04303 미결 - 검토부서가 법무인경우
					if ("A00000003".equals(vo.getBlngt_orgnz())) { // 아이피센터
						vo.setDepth_status("");
						vo.setPrgrs_status("C04204");
						vo.setCnsd_status("C04301");
						vo.setDeptcnsd_cnsd_status("C04305");
					} else { // 법무인경우
						vo.setDepth_status("");
						vo.setPrgrs_status("C04204");
						vo.setCnsd_status("C04301");
						vo.setDeptcnsd_cnsd_status("C04303");
					}
				}
			}
		}

		// 발신DISPATCH - 종합 검토의견
		if ("DISPATCH".equals(vo.getStat_flag())) {

			if (vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())) {// 로그인 한 사람이
																	// 정검토부서 이면서
																	// -종합검토의견
				// C02606 검토중 -- 상태변화없음 (기존 상태 유지) C04303 미결 -- 해당없음
				// C04205 미결 -- 해당없음
				if ("Y".equals(vo.getAuto_apbt_yn())) { // 전결여부
					vo.setDepth_status("C02608"); // 마스터테이블 회신 완료
					vo.setPrgrs_status("C04207");
					vo.setCnsd_status("C04305");
					vo.setDeptcnsd_cnsd_status("C04305");
					mailSendYn = "Y";

				} else {
					// 의뢰에포함된 모든계약이 담당자 검토가 완료되면 미결 =C04205 / C04303 미결
					vo.setDepth_status("");
					vo.setPrgrs_status("C04205"); // 미결
					vo.setCnsd_status("C04303"); // 미결
					vo.setDeptcnsd_cnsd_status("C04303");
				}
			}
		}

		String master_cntrt_ids[] = vo.getMaster_cntrt_ids();

		// -2012.02.01 다수계약 검토의뢰일 경우, 검토하지 않는 계약건을 TN_CLM_CONTRACT_DEPTCNSD 테이블에
		// INSERT[의견전달, 발신] modified by hanjihoon
		if (!"".equals(vo.getDeptcnsd_cnsd_status())) {
			if ("RA01".equals(vo.getTop_role())
					&& ("DELIVERY".equals(vo.getStat_flag()) || "DISPATCH".equals(vo.getStat_flag()))) {
				// 저장
				rtVal = commonDAO.modify("las.returnDeptCnsdApbt.modifyAll", vo);
				// 담당자 인경우
			} else if ("RA02".equals(vo.getTop_role()) || "RB01".equals(vo.getTop_role())) {

				// 저장
				if (getDeptCnsdCnt(vo) > 0) { // modify
					rtVal = commonDAO.modify("las.returnDeptCnsd.modify", vo);
				} else { // insert
					rtVal = commonDAO.insert("las.returnDeptcnsd.insert", vo);
				}

				// 의견전달, 발신
				if ("DELIVERY".equals(vo.getStat_flag()) || "DISPATCH".equals(vo.getStat_flag())) { // 의견전달,
																									// 발신
					for (int i = 0; i < master_cntrt_ids.length; i++) {
						vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));
						vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i], "")));

						if (!vo.getActive_cntrt_id().equals(vo.getMaster_cntrt_id()) && getDeptCnsdCnt(vo) == 0) { // insert
							rtVal = commonDAO.insert("las.returnDeptcnsd.newInsert", vo);
						}
					}
				}

				if ("RESP".equals(vo.getPage_div())) {// 검토회신
					rtVal = commonDAO.modify("las.returnDeptCnsdApbt.modifyAll", vo);
				} else {
					rtVal = commonDAO.modify("las.returnDeptCnsd.status.modifyAll", vo);
				}
			}
		}

		// 계약별_검토
		if (vo.getBlngt_orgnz().equals(vo.getMn_cnsd_dept()) && !"".equals(vo.getCnsd_status())) {
			vo.setCntrt_id(vo.getActive_cntrt_id());

			vo.setCnsd_memo(StringUtil.nvl(vo.getCnsd_memo(), ""));

			rtVal = commonDAO.modify("las.returnContractCnsd.modify", vo); // 활성화
																			// 계약
																			// 개별
																			// 업데이트
			rtVal = commonDAO.modify("las.returnContractCnsd.status.modifyAll", vo); // 검토의뢰
																						// 해당
																						// 계약건
																						// 전체
																						// 업데이트
		}

		// 마스터 테이블 상태값 변경
		if (!"".equals(vo.getDepth_status())) {
			if ("Y".equals(vo.getAuto_apbt_yn())) { // 전결
				for (int i = 0; i < master_cntrt_ids.length; i++) {
					vo.setMaster_cntrt_id((String) master_cntrt_ids[i]);
					rtVal = commonDAO.modify("las.returnDepthStatus.modify", vo); // 상태값
																					// 완료
																					// 검토의견없뎃
																					// 의뢰에
																					// 계약
																					// 여러건
																					// 상태
																					// 업데이트
				}
			}
		}

		// 의뢰테이블 상태값 변경
		if (!"".equals(vo.getPrgrs_status())) {
			rtVal = commonDAO.modify("las.returnPrgrsStatus.modify", vo);
		}

		// 병행 검토 여부 입력 로직 추가 김재원 2014.05.23
		List sel_result = null;

		int del_result = 0;
		@SuppressWarnings("unused")
		int in_result = 0;
		String check_value = StringUtil.nvl(vo.getHqreqcheck(), "");
		String spl_check[] = null;
		String spl_check_value[] = null;
		String spl_key = "";
		String spl_value = "";

		if (!"".equals(check_value)) {

			sel_result = commonDAO.list("las.returnPrgrsStatus.reqCheck", vo); // 값의
																				// 존재
																				// 여부
																				// 확인

			if (0 < sel_result.size()) { // 테이블에 값이 있는 경우 del 후 insert

				del_result = commonDAO.modify("las.returnPrgrsStatus.delReqCheck", vo); // 삭제
																						// 하기

				if (0 < del_result) {

					spl_check = check_value.split("\\;");

					for (int i = 0; i < spl_check.length; i++) {

						spl_check_value = spl_check[i].split("\\,");
						spl_value = spl_check_value[0];
						spl_key = spl_check_value[1];

						vo.setItem_cd(spl_key);
						vo.setCheck_yn(spl_value);

						in_result = commonDAO.modify("las.returnPrgrsStatus.insertReqCheck", vo); // 데이터
																									// 저장

					}
				}

			} else { // 테이블에 값이 없는 경우 바로 insert

				spl_check = check_value.split("\\;");

				for (int i = 0; i < spl_check.length; i++) {
					spl_check_value = spl_check[i].split("\\,");
					spl_key = spl_check_value[1];
					spl_value = spl_check_value[0];

					vo.setItem_cd(spl_key);
					vo.setCheck_yn(spl_value);

					in_result = commonDAO.modify("las.returnPrgrsStatus.insertReqCheck", vo); // 데이터
																								// 저장
				}
			}

		} else {
		}

		Locale locale1 = new Locale((String) vo.getSession_user_locale());

		mapRt.put("returnVal", rtVal);
		// 정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
		mapRt.put("returnMsg", (String) messageSource
				.getMessage("las.page.field.considerationImpl.returnConsideration01", null, locale1));
		mapRt.put("depthStatus", vo.getDepth_status());
		mapRt.put("prgrsStatus", vo.getPrgrs_status());
		mapRt.put("cnsdStatus", vo.getCnsd_status());
		mapRt.put("deptcnsdCnsdStatus", vo.getDeptcnsd_cnsd_status());
		mapRt.put("mail_send_yn", mailSendYn);

		return mapRt;
	}

	@SuppressWarnings("rawtypes")
	public int getDeptCnsdCnt(ConsultationVO vo) throws Exception {

		Integer init_valut = new Integer(-1);

		int returnVal = -1;
		List resultList = null;

		try {

			resultList = commonDAO.list("las.returnDeptCnsd.cntNo", vo);
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

				init_valut = (Integer) lom.get("CNTNO");
				returnVal = init_valut.intValue();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnVal;
	}

	@SuppressWarnings("rawtypes")
	public boolean getChkDeptCnsd(ConsultationVO vo) throws Exception {
		boolean returnVal = false;
		List resultList = null;

		// 임시저장
		if ("SAVE".equals(vo.getStat_flag())) {
			returnVal = true;
		} else {
			resultList = commonDAO.list("las.returnChkDeptCnsd.list", vo);
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

				if (((BigDecimal) lom.get("CNT")).intValue() != 0) {
					returnVal = false;
				} else {
					returnVal = true;
				}
			}
		}
		return returnVal;
	}
	@SuppressWarnings("rawtypes")
	public List returnListMndeptcnsd(ConsiderationVO vo) throws Exception { //
		return commonDAO.list("las.returnContractDeptcnsd.listMndeptcnsd", vo);
	}
	@SuppressWarnings("rawtypes")
	public List returnListVcdeptcnsd(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.returnContractDeptcnsd.listVcdeptcnsd", vo);
	}

	/**
	 * 계약검토 건 카운트 (메인화면 출력용)
	 */
	// @Cacheable(cacheName = "messagesCache" )
	@SuppressWarnings("rawtypes")
	public List countConsideration(ConsiderationVO vo) throws Exception {
		String sql = "las.contractmanager.countConsideration";
		String topRole = (String) vo.getTop_role();
		if (topRole != null && (topRole.equals("HQ01") || topRole.equals("HQ02"))) {
			sql = "las.contractmanager.countConsiderationHQ_cnt";
			vo.setSrch_start_dt(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
			vo.setSrch_end_dt(DateUtil.formatDate(DateUtil.today(), "-"));
		}

		if (topRole != null && topRole.equals("HQ02")) {
			vo.setSrch_hq_reviewer_nm(vo.getSession_user_nm());
		}
		return commonDAO.list(sql, vo);
	}

	/**
	 * 계약관리 건 카운트 (메인화면 출력용)
	 */
	@SuppressWarnings("rawtypes")
	public List countContractMngNew(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.countContractMngNew", vo);
	}

	/**
	 * 계약관리 건 카운트 (메인화면 출력용)
	 */
	@SuppressWarnings("rawtypes")
	public List countContractMng(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.countContractMng", vo);
	}

	/**
	 * 증명서류건 카운트 (메인화면 출력용)
	 */
	@SuppressWarnings("rawtypes")
	public List countSigndoc(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.countSigndoc", vo);
	}

	/**
	 * 날인접수 건 카운트 (메인화면 출력용)
	 */
	@SuppressWarnings("rawtypes")
	public List countSeal(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.countSeal", vo);
	}

	/**
	 * 권한별 버튼 컨트롤 (메인화면 출력용)
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String[] getButtonControl(ConsiderationVO vo) throws Exception {

		String strTest = null;
		ListOrderedMap lom_btControl = null;

		List resultList = commonDAO.list("clm.manage.getButtonControl", vo);
		if (resultList != null && resultList.size() > 0) {
			lom_btControl = (ListOrderedMap) resultList.get(0);
		}
		strTest = (String) lom_btControl.toString();
		String return_str[] = strTest.replace("{", "").replace("}", "").split(",");

		return return_str;
	}

	/**
	 * 검토이력 리스트 기준 부서 조회
	 * 
	 * @param vo
	 *            ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listContractDeptDiv(ExecutionVO vo) throws Exception {
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토ID

		return commonDAO.list("las.contractmanager.listContractDeptDiv", vo);
	}

	/**
	 * 이력정보 전체목록을 조회한다.
	 * 
	 * @param vo
	 *            ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List listHisExecution(ExecutionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토ID
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), ""))); // 계약ID

		resultList.add(commonDAO.list("las.contractmanager.listContractReview", vo)); // 검토이력
																						// 정보
		resultList.add(commonDAO.list("las.contractmanager.listContractApprove", vo)); // 승인이력
																						// 정보
		// resultList.add(commonDAO.list("las.contractmanager.listContractSign",
		// vo)); // 체결이력 정보
		resultList.add(commonDAO.list("las.contractmanager.listContractInfo", vo)); // 사전검토이력
																					// 정보
		// 2012.05.24 보류이력을 검토이력에서 출력 modified by hanjihoon
		// resultList.add(commonDAO.list("las.contractmanager.listPendingHis",
		// vo)); // 보류이력 정보

		return resultList;
	}

	/**
	 * 부서 검토 승인, 반려 이력 조회
	 * 
	 * @param vo
	 *            ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listHisDeptCnsdRejct(ExecutionVO vo) throws Exception {
		@SuppressWarnings("unused")
		List resultList = null;
		resultList = new ArrayList();
		vo.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBlngt_orgnz(), ""))); // 소속조직
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), ""))); // 검토ID
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), ""))); // 계약ID

		return commonDAO.list("las.contractmanager.listHisDeptCnsdRejct", vo);
	}

	/**
	 * (구)법무시스템에서 이관된 데이터의 이력정보 전체목록을 조회한다.
	 * 
	 * @param vo
	 *            ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List listOldHisExecution(ExecutionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();

		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));

		return resultList;
	}

	// las.contractmanager.listFilevalidate
	/**
	 * 첨부 필수 사항 체크
	 */
	@SuppressWarnings("rawtypes")
	public ListOrderedMap getFilevalidate(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();

		List returnList = commonDAO.list("las.contractmanager.listFilevalidate", vo);
		if (returnList != null && returnList.size() > 0) {
			returnLom = (ListOrderedMap) returnList.get(0);

			if (returnLom.get("FILE_VALIDATE").equals("N")) {
				returnLom.put("fileValidate", "N");
			} else {
				returnLom.put("fileValidate", "Y");
			}
		} else {
			returnLom.put("fileValidate", "Y");
		}
		return returnLom;
	}

	/**
	 * 연관계약정보조회
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listConsultationRealtion(ConsultationVO vo) throws Exception {
		vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCntrt_id(), "")));

		return commonDAO.list("clm.manage.listConsultationRelation", vo);
	}

	/**
	 * 보류이력 조회
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listConsiderationHold(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(), "")));

		return commonDAO.list("las.contractmanager.listConsiderationHold", vo);
	}

	/**
	 * 사업부이관데이터 수정
	 */
	public int modifyOldConsideration(ConsultationVO vo) throws Exception {
		int result = 0;

		vo.setCntrtperiod_startday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_startday().replace("-", ""))); // 계약기간
																													// -
																													// 시작일
		vo.setCntrtperiod_endday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_endday().replace("-", ""))); // 계약기간
																												// -
																												// 종료일

		if (vo.getCntrt_amt() != null) {
			vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", "")); // 계약기간
																						// 콤마
																						// 제거
		} else {
			vo.setCntrt_amt(null);
		}

		// vo.setCnclsn_plndday(StringUtil.convertHtmlTochars(vo.getCnclsn_plndday().replace("-",
		// ""))); //체결예정일
		vo.setCntrt_cnclsnday(StringUtil.convertHtmlTochars(vo.getCntrt_cnclsnday().replace("-", ""))); // 계약체결일

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd("LAS"); // 사업부이관건 - 계약,법무 같은 소스 쓰기 때문에 강제 셋팅한다.
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id());
		fvo.setFileInfos(vo.getFileInfos1());

		clmsFileService.mngClmsAttachFile(fvo, vo);

		/// 수정
		result = commonDAO.modify("las.contractmanager.modifyOldConsideration", vo); // 계약
																						// 마스터
																						// 수정
		// 계약명, 계약번호 수정
		result = commonDAO.modify("las.contractmanager.modifyOldCntrtNm_U", vo);

		return result;
	}

	/**
	 * 사별이관데이터 등록
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int insertOldConsideration(ConsultationVO vo) throws Exception {

		int result1 = 0; // 계약 마스터 등록 결과
		int result2 = 0; // 계약명, 계약번호 수정
		int result3 = 0; // 계약 검토의뢰 테이블 등록
		int result4 = 0; // 계약별 검토 테이블 등록
		int result5 = 0; // 계약별 부서검토 테이블 등록

		vo.setCntrtperiod_startday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_startday().replace("-", ""))); // 계약기간
																													// -
																													// 시작일
		vo.setCntrtperiod_endday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_endday().replace("-", ""))); // 계약기간
																												// -
																												// 종료일
		vo.setRegion_gbn("C01801");

		if (vo.getCntrt_amt() != null) {
			vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", "")); // 계약기간
																						// 콤마
																						// 제거
		} else {
			vo.setCntrt_amt(null);
		}

		vo.setCntrt_cnclsnday(StringUtil.convertHtmlTochars(vo.getCntrt_cnclsnday().replace("-", ""))); // 계약체결일

		// 계약ID 구하기
		String cntrt_id = getId();

		vo.setCntrt_id(cntrt_id);

		// 계약담당자 상위부서명 구하기
		String up_menu_cds = "";
		HashMap hm = new HashMap();
		hm.put("dept_cd", vo.getCntrt_resp_dept());
		List upDeptList = commonDAO.list("secfw.user.listUpDeptInfo", hm);
		if (upDeptList != null && upDeptList.size() > 0) {
			for (int i = 0; i < upDeptList.size(); i++) {
				ListOrderedMap upDeptMap = (ListOrderedMap) upDeptList.get(i);
				String deptCd = (String) upDeptMap.get("dept_cd");

				if (!"TOP".equals(deptCd)) {
					up_menu_cds += deptCd + "^";
				}
			}
		}
		vo.setCntrt_resp_up_dept(up_menu_cds);

		// 등록
		result1 = commonDAO.insert("las.contractmanager.insertOldContractMaster", vo); // 계약
																						// 마스터
																						// 등록

		if (result1 > 0) {

			// 계약명, 계약번호 수정
			result2 = commonDAO.modify("las.contractmanager.modifyOldCntrtNm", vo);

			if (result2 > 0) {

				// 계약 검토의뢰 테이블 등록
				result3 = commonDAO.insert("las.contractmanager.insertOldCnsdReq", vo);

				if (result3 > 0) {

					// 계약별 검토 테이블 등록
					result4 = commonDAO.insert("las.contractmanager.insertOldContractCnsd", vo);

					if (result4 > 0) {

						// 계약별 부서검토 테이블 등록
						result5 = commonDAO.insert("las.contractmanager.insertOldContractDeptCnsd", vo);

						if (result5 > 0) {

							result1 = 1;

						} else {

							result1 = -1;

						}

					}

				}

			}

		}

		// 의뢰ID구하기
		String cnsdreq_id = "";
		List cnsdreqIdList = commonDAO.list("las.contractmanager.searchCnsdReqId", vo);
		if (cnsdreqIdList != null && cnsdreqIdList.size() > 0) {
			ListOrderedMap map = (ListOrderedMap) cnsdreqIdList.get(0);

			cnsdreq_id = StringUtil.bvl((String) map.get("cnsdreq_id"), "");

		}
		vo.setCnsdreq_id(cnsdreq_id);

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd("LAS"); // 사업부이관건 - 계약,법무 같은 소스 쓰기 때문에 강제 셋팅한다.
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setOcr_work_target("N");
		fvo.setOcr_work_lang(vo.getSession_user_locale());
		fvo.setRef_key(vo.getCntrt_id() + "@" + vo.getCnsdreq_id());
		fvo.setFileInfos(vo.getFileInfos1());
		fvo.setSession_user_locale((String) vo.getSession_user_locale());

		clmsFileService.mngClmsAttachFile(fvo, vo);

		return result1;
	}

	// 계약ID가져오기
	@SuppressWarnings("rawtypes")
	public String getId() throws Exception {
		String getId = "";
		List resultList = null;

		resultList = commonDAO.list("clm.manage.getId");

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

			getId = (String) lom.get("GET_ID");
		}
		return getId;
	}

	public int deleteOldConsideration(ConsultationVO vo) throws Exception {
		int result = 0;

		/// 삭제
		result = commonDAO.modify("las.contractmanager.deleteOldConsideration", vo); // 계약
																						// 마스터
																						// 수정

		return result;
	}

	/**
	 * 수정할 최종 legal opinion을 가지고 와서 popup화면에서 보여준다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List forwardLegalOpinion(ConsultationVO vo) throws Exception {

		if ("C02609".equals(vo.getDepth_status()) && "RA01".equals(vo.getTop_role())) {

			// legal admin reply인 경우
			return commonDAO.list("las.contractmanager.forwardLegalAdminOpinion", vo);

		} else {

			return commonDAO.list("las.contractmanager.forwardLegalOpinion", vo);
		}
	}

	/**
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	
	public int updateLegOpinion(ConsultationVO vo) throws Exception {

		int cnsdreq = 0;
		@SuppressWarnings("unused")
		int cnsd = 0;

		if ("C02609".equals(vo.getDepth_status()) && "RA01".equals(vo.getTop_role())) {

			vo.setCnsd_opnn(StringUtil.bvl(vo.getCnsd_opnn(), ""));

			// legal admin reply인 경우
			cnsdreq = commonDAO.modify("las.contractmanager.updateLegReplyOpinion", vo);

			return cnsdreq;

		} else {

			return commonDAO.modify("las.contractmanager.updateLegOpinion", vo);

		}

	}

	/**
	 * 법인에 대한 체크 리스트 항목을 보여 준다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List forwardCheckItem(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.forwardCheckItem", vo);
	}

	/**
	 * 계약에 대한 체크 리스트 항목을 보여 준다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectCheckItem(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.selectCheckItem", vo);
	}

	/**
	 * HQ로 의뢰 할 수 있는 팝업을 띄어 준다. 임시 저장 건도 있기 때문에 내용을 조회해야 한다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List forwardRequestHQ(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.forwardRequestHQ", vo);
	}

	/**
	 * HQ로 재의뢰 할 수 있는 팝업을 띄어 준다. 가장 최근에 의뢰한 내용을 보여 준다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List forwardRERequestHQ(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.contractmanager.forwardRERequestHQ", vo);
	}

	/**
	 * HQ로 의뢰 임시 저장 및 의뢰를 한다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int insertHqRequest(ConsiderationVO vo) throws Exception {

		int update_result = 0;
		int reviewer_result = 0;
		int result = 0;
		@SuppressWarnings("unused")
		String req_head = "HQ";
		@SuppressWarnings("unused")
		String newHQreqKey = "";
		List key = null;

		// 첨부 파일 관련
		ClmsFileVO fvo = new ClmsFileVO();
		ConsiderationHQVO mvo = new ConsiderationHQVO();

		// hq 의뢰 아이디가 화면에서 넘어와서 있는 경우 key를 다시 사용하고, 없는 경우만 hq의뢰 키를 DB에서 만들어서
		// 사용한다.
		// if("".equals(sKey_yn)){
		//
		// key = commonDAO.list("las.contractmanager.searchHQCnsdReqId", vo);
		//
		// if(0 < key.size() && key != null){
		//
		// ListOrderedMap keyMap = (ListOrderedMap)key.get(0);
		//
		// newHQreqKey = (String)keyMap.get("HQ_CNSDREQ_ID");
		//
		// }
		//
		// vo.setHq_cnsdreq_id(newHQreqKey);
		//
		// } else {
		//
		// vo.setHq_cnsdreq_id(sKey_yn);
		// }

		String sKey_yn = vo.getHq_cnsdreq_id(); // 화면에서 넘어 오는 HQ 의뢰 키
		String create_Key = StringUtil.nvl(vo.getCnsdreq_id(), ""); // 화면에서 넘어오는
																	// 원 의뢰 키
		String create_Key2 = StringUtil.nvl(vo.getCnsdreq_id(), ""); // 화면에서
																		// 넘어오는
																		// 원 의뢰
																		// 키(오염되지
																		// 않은 값)
		String db_cnsdreq_id = ""; // DB에서 값을 가지고 와서 비교하기 위해서 만듬.

		if (!"".equals(create_Key)) {

			vo.setCnsdreq_id(create_Key);

			key = commonDAO.list("las.contractmanager.selectCnsdReqId", vo);

			if (0 < key.size() && key != null) {
				ListOrderedMap keyMap = (ListOrderedMap) key.get(0);
				db_cnsdreq_id = (String) keyMap.get("cnsdreq_id");
			} else {

			}
		}

		// 화면에서 넘어오는 HQ 의뢰 아이디가 없을 경우 최초 생성
		// 생성 규칙은 원 의뢰 아이디에서 H --> HQ로 변경 처리 함.
		// 또는 원 의뢰 아이디가 변경 되었을 경우에는 키를 새로 만들어 준다.
		// 변경에 대한 내용을 알기 위해서 DB 조회 후 화면에서 넘어온 값과 비교를 한다.
		if ("".equals(sKey_yn) || create_Key2 != db_cnsdreq_id) {
			create_Key = create_Key.replaceAll("H", "HQ");

		} else {
			// 값이 넘어 오게 되는 경우 기존에 값을 그대로 사용 하게 된다.
			create_Key = sKey_yn;
		}

		String status = StringUtil.nvl((String) vo.getHq_cnsd_status(), "");

		// System.out.println("status = " + status);
		// System.out.println("db_cnsdreq_id = "+db_cnsdreq_id);
		// System.out.println("create_Key = "+create_Key);
		// System.out.println("sKey_yn = "+sKey_yn);

		List insertYn = null;

		vo.setHq_req_yn("Y");

		// 원 의뢰를 다시 하게 될 경우엔 아래와 같이 HQ 의뢰 값이 존재 하지 않고, DB의 값고 화면에서 넘어온 값이 틀릴 경우
		// 판단 함.
		// admin reply일 경우 자동 배정이 되면 안된다.
		if (create_Key2 != db_cnsdreq_id) {

			vo.setDb_cnsdreq_id(db_cnsdreq_id.replaceAll("H", "HQ"));
			vo.setResp_key(create_Key);

			insertYn = commonDAO.list("las.contractmanager.selectHqreview", vo); // executed
			mvo.setSession_user_locale(vo.getSession_user_locale());
			mvo.setHq_cnsdreq_id(create_Key);
			
//			if (0 < insertYn.size()) { // Re request
//				reviewer_result = commonDAO.modify("las.contractmanager.insertHqRevier", vo); // 배정
//																								// 테이블에
//																								// 행
//																								// 추가
//				vo.setHq_cnsd_status("C16203");
//
//				// 배정 테이블에 행 추가 후 다시 의뢰가 왔다는 메일을 발송 처리 함.
//				// 배정 된 테이블에서 사람을 가지고 와야 한다.
//				if (reviewer_result > 0) {
//					List mail_user_list = commonDAO.list("las.contractmanager.selectHqreview", vo);
//					considerationHQService.sendHQMail(mvo, mail_user_list, "RE_REQ");
//				} else {
//					considerationHQService.sendHQMail(mvo, insertYn, "RE_REQ"); // in
//																				// most
//																				// case
//				}
//
//			} else { // Inintial request
//				vo.setHq_cnsd_status("C16202");
//				considerationHQService.sendHQMail(mvo, insertYn, "RE_REQ");
//			}

		}

		if (!"".equals(status)) {

			if ("C16205".equals(status) || "C16207".equals(status)) {

				// System.out.println("C16203");

				vo.setHq_cnsd_status("C16203");
			} else if ("C16202".equals(status)) {

				// System.out.println("C16202");

				update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);

				vo.setHq_cnsd_status("C16202");

			} else if ("C16203".equals(status)) {
				vo.setHq_cnsd_status("C16203");
			} else {
				vo.setHq_cnsd_status("C16201");
			}

		} else {

		}

		vo.setHq_cnsdreq_id(create_Key);

		// hq로 의뢰 여부에 대해서 TN_CLM_CONTRACT_CNSD의 HQ_REQ_YN 값을 Y로 update한다
		if ("C16202".equals(status) || "C16203".equals(status) || "C16207".equals(status) || "".equals(status)) {

			update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);

			if (0 < update_result) {

			}

		}

		// if("C16201".equals(status)){
		// vo.setCnsd_level("1");
		// } else {
		// vo.setCnsd_level(vo.getCnsd_level());
		// }

		vo.setCnsd_level(vo.getCnsd_level());

		/*************************************************
		 * 첨부파일 추가
		 *************************************************/
		String file_key = "";
		String cnsd_level = vo.getCnsd_level();

		// 파일 키 구조 : file_key =
		
		// frm.cntrt_id.value+"@"+frm.hq_cnsdreq_id.value+"_"+cnsd_level;
		int int_cnsd_level = Integer.parseInt(cnsd_level);

		if ("".equals(cnsd_level)) {
			cnsd_level = "1";
			vo.setCnsd_level(cnsd_level);

		// If cnsd_level is even number then +1
		} else if (int_cnsd_level % 2 == 0) {
			cnsd_level = Integer.toString(int_cnsd_level+1);
			vo.setCnsd_level(cnsd_level);
		}
		

		file_key = vo.getCntrt_id() + "@" + create_Key + "_" + cnsd_level;

		fvo.setSys_cd("LAS");
		fvo.setReg_id(vo.getSession_user_id());
		fvo.setFile_bigclsfcn("H010");
		fvo.setFile_midclsfcn("H01001");
		fvo.setFile_smlclsfcn("H0100101");
		fvo.setFile_info(vo.getFileInfos());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setSession_user_locale((String) vo.getSession_user_locale());
		fvo.setRef_key(file_key);

		clmsFileService.mngClmsAttachFile(fvo);

		result = commonDAO.modify("las.contractmanager.insertHqRequest", vo);

		if (0 < result) {
			update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);
		}

		// 메일 보내기
//		if ("C16202".equals(status)) {
//
//			// 최초 의뢰 할때 HQ01(HQ Admin)에게 의뢰 했다는 메일 발송 하게 됨.
//			List mail_user_list = null;
//
//			mail_user_list = commonDAO.list("las.contractmanager.selectMailRevList", vo);
//
//			mvo.setSession_user_locale(vo.getSession_user_locale());
//			mvo.setHq_cnsdreq_id(create_Key);
//
//			considerationHQService.sendHQMail(mvo, mail_user_list, "REQ");
//
//			vo.setHq_cnsd_status("C16202");
//
//		}

		update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);

		// Insert SO Parallel review info to TN_INF_HQ_ECMS_STATUS table for batch
		if (!"C16201".equals(status)) commonDAO.insert("insertSOReviewForBatch", vo);

		return result;
	}

	/**
	 * 필수 항목 중 No로 체크한 내용을 저장을 한다.
	 * 
	 * @param vo
	 *            ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int insertCheckReson(ConsiderationVO vo) throws Exception {

		int del_result = 0; // 삭제 결과
		int result = 0; // 입력 결과
		List dbYn = null;

		String sum_text = vo.getSum_text(); // 키와 함께 사유가 뭉처 있는 그룹
		@SuppressWarnings("unused")
		String splitText = ""; // 최초 뭉처 있는 내용을 분리
		String spl_item_cd = ""; // 뭉처 있는 내용에서 키만 추출
		String spl_remark = ""; // 뭉처 있는 내용에서 사유만 추출
		String item_cd = ""; // 분리한 내용에서 키만 추출
		String remark = ""; // 분리한 내용에서 사유만 추출
		int textSplitCount = 0;
		@SuppressWarnings("unused")
		int splitTextCount = 0;

		dbYn = commonDAO.list("las.contractmanager.itemCheckResonDBYn", vo);

		if (0 < dbYn.size()) { // DB에 내용이 있으면 삭제 후 입력
			del_result = commonDAO.modify("las.contractmanager.deleteCheckReson", vo);
		} else {
			del_result = 0;
		}

		if (!"".equals(sum_text) && null != sum_text && !"||".equals(sum_text)) {

			spl_item_cd = sum_text.split("\\|\\|")[0];
			spl_remark = sum_text.split("\\|\\|")[1];
			textSplitCount = spl_item_cd.split("\\^").length;

			if (0 < del_result) {
				for (int i = 0; i < textSplitCount; i++) {

					item_cd = spl_item_cd.split("\\^")[i];
					remark = spl_remark.split("\\^")[i];

					vo.setItem_cd(item_cd);
					vo.setRemark(remark);

					result = commonDAO.modify("las.contractmanager.insertCheckReson", vo);
				}
			} else { // 내용이 없으면 바로 입력 처리

				for (int i = 0; i < textSplitCount; i++) {

					item_cd = spl_item_cd.split("\\^")[i];
					remark = spl_remark.split("\\^")[i];

					vo.setItem_cd(item_cd);
					vo.setRemark(StringUtil.convertEnterToBR(remark));

					result = commonDAO.modify("las.contractmanager.insertCheckReson", vo);

				}
			}

		} else {
			// result = 1;
		}

		// if(result > 0){
		result = commonDAO.modify("las.contractmanager.updateCheck_yn", vo);
		// }

		return result;

	}

	/**
	 * 필수항목 체크 사유 목록
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listCheckList(ConsultationVO vo) throws Exception {

		vo.setCntrt_id(StringUtil.bvl(vo.getCntrt_id(), ""));

		if ("HQ".equals((String) vo.getSession_comp_cd())) {
			vo.setComp_cd("");
		} else {
			vo.setComp_cd(vo.getSession_comp_cd());
		}

		return commonDAO.list("las.contractmanager.itemCheckResonDBYn", vo);
	}

	/**
	 * 필수항목 체크 사유 목록
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listHqCheckList(ConsultationVO vo) throws Exception {

		if ("HQ".equals((String) vo.getSession_comp_cd())) {
			vo.setComp_cd("");
		} else {
			vo.setComp_cd(vo.getSession_comp_cd());
		}

		vo.setCntrt_id(StringUtil.bvl(vo.getCntrt_id(), ""));

		return commonDAO.list("las.returnPrgrsStatus.reqCheck", vo);
	}

	/**
	 * 필수항목 체크 사유 목록
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listHqCheckList2(ConsultationVO vo) throws Exception {

		vo.setCntrt_id(StringUtil.bvl(vo.getCntrt_id(), ""));

		return commonDAO.list("las.returnPrgrsStatus.reqCheck3", vo);
	}

	/**
	 * 필수항목 체크 사유 목록
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectHqRequest(ConsultationVO vo) throws Exception {

		vo.setCntrt_id(StringUtil.bvl(vo.getCntrt_id(), ""));

		return commonDAO.list("las.returnPrgrsStatus.selectHqRequest", vo);
	}

	/**
	 * 복합 법인 검토자 여부 판단
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List listMultYn(ConsiderationVO vo) throws Exception {
		return commonDAO.list("las.returnPrgrsStatus.listMultYn", vo);
	}

}