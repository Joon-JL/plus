/**"
 * Project Name : 법무시스템 이식
 * File name	: BbsController.java
 * Description	: 게시판(열린마당/시스템Q&A) Controller
 * Author		: 
 * Date			: 2011. 08
 * Copyright 	: 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.board.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.las.board.dto.BbsForm;
import com.sec.las.board.dto.BbsVO;
import com.sec.las.board.service.BbsService;

/**
* Description : 게시판(열린마당/시스템Q&A) Controller
* Author : 
* Date : 2011.08
*/
public class BbsController extends CommonController {
	
	/**
	 * ROLE 구분을 위한 변수
	 */
	static String ROLE_0 = "RA00"; // 시스템관리자
	static String ROLE_1 = "RA01"; // 법무관리자
	static String ROLE_2 = "RA02"; // 법무담당자
	static String ROLE_3 = "RA03"; // 법무일반사용자
	static String ROLE_4 = "RC01"; // CP관리자
	static String ROLE_5 = "RD01"; // 사업부계약관리자
	static String ROLE_6 = "RD02"; // 사업부계약담당자
	static String ROLE_7 = "RZ00"; // 일반임직원
	static String ROLE_8 = "RM00"; // 시스템Admin
	
	/**
	* 등록/답변등록/수정 구분자
	*/
	private static String NORMAL = "0";
	private static String REPLY = "1";
	private static String RE = "[Re:]";

	/**
	* BBS 서비스
	*/
	private BbsService bbsService;
	
	/**
	* BBS 서비스 세팅
	*
	* @param bbsService
	* @return void
	* @throws 
	*/
	public void setbbsService(BbsService bbsService) {
		this.bbsService = bbsService;
	}
	
	
	private ComUtilService comUtilService;
	
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	public ModelAndView listPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			BbsForm form       = null;
			BbsVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
 			
	 		if ("A00000002".equals(vo.getSession_blngt_orgnz())){
		 		if("C01204".equals(vo.getBbs_knd())){ //표준계약서
		 			vo.setBbs_knd("C01205"); form.setBbs_knd("C01205");
		 		}
		 		else if("C01206".equals(vo.getBbs_knd())){ //조합 checklist
		 			vo.setBbs_knd("C01207"); form.setBbs_knd("C01207");
		 		}
		 		else if("C01208".equals(vo.getBbs_knd())){ //주요 용어 정리
		 			vo.setBbs_knd("C01209"); form.setBbs_knd("C01209");
		 		}
				else if("C01210".equals(vo.getBbs_knd())){ //교육 메뉴얼
					vo.setBbs_knd("C01211"); form.setBbs_knd("C01211");
				}
	 			else if("C01212".equals(vo.getBbs_knd())){ //기타 공유 자료실
	 				vo.setBbs_knd("C01213"); form.setBbs_knd("C01213");
	 			}
 			}
	 		
	 		// 조회조건 세팅
	 		if(form.getSrch_dmstfrgn_gbn() == null){
	 			if ("A00000001".equals(vo.getSession_blngt_orgnz())){
	 				form.setSrch_dmstfrgn_gbn("dmst");
	 				vo.setSrch_dmstfrgn_gbn("dmst");
	 			}
	 			else{
	 				form.setSrch_dmstfrgn_gbn("frgn");
	 				vo.setSrch_dmstfrgn_gbn("frgn");
	 			}
 			}
 			
 			
//			부서 공유 자료실 구분자 세팅 // Pds_l.jsp 미사용으로 주석처리 함 
//			form.setIsPds("Y");
//			vo.setIsPds("Y");

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = bbsService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
 			
			return listBbs(request, response, form, vo);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.listOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.listOpenBbs() Throwable !!");
		}
	}

	/**
	* 자유게시판 목록 조회
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			BbsForm form       = null;
			BbsVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
// 				
// 			if ("A00000002".equals(vo.getSession_blngt_orgnz())){
// 				// 해외법무팀
//	 	 		form.setBbs_knd("C01203");
//	 	 		vo.setBbs_knd("C01203");
// 			} else{
//	 	 		form.setBbs_knd("C01202");
//	 	 		vo.setBbs_knd("C01202");
// 			}
 			
			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = bbsService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
 			
			return listBbs(request, response, form, vo);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.listOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.listOpenBbs() Throwable !!");
		}
	}

	/**
	* 시스템Q&A 목록 조회
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listSysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			BbsForm form       = null;
			BbsVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
 			
// 			form.setBbs_knd("C01201");
// 	 		vo.setBbs_knd("C01201");
 	 		setTopRole(request, form, vo);
 				
 			// 등록권한 SETTING
			form.setAuth_insert(true);
			
			return listBbs(request, response, form, vo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.listSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.listSysQnA() Throwable !!");
		}
	}
	
	/**
	 * 자유게시판 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardInsertOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardInsertOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertSysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardInsertSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardInsertSysQnA() Throwable !!");
		}
	}
	
	/**
	 * 자유게시판 답변등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertReplyOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertReplyBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardInsertReplyOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardInsertReplyOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 답변등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertReplySysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertReplyBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardInsertReplySysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardInsertReplySysQnA() Throwable !!");
		}
	}
	
	/**
	 * 자유게시 등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertBbs(request, response, "detailOpenBbs");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertBbs(request, response, "listPds");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertSysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertBbs(request, response, "detailOpenBbs");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertSysQnA() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 답변등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertReplyOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertReplyBbs(request, response, "detailOpenBbs");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertReplyOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertReplyOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 답변등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertReplySysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertReplyBbs(request, response, "detailOpenBbs");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertReplySysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertReplySysQnA() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 답변등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertReplyPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertReplyBbs(request, response, "listPds");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.insertReplySysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.insertReplySysQnA() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 상세정보 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return detailBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.detailOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.detailOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 상세정보 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailSysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return detailBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.detailSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.detailSysQnA() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardModifyBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardModifyOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardModifyOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifySysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardModifyBbs(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.forwardModifySysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.forwardModifySysQnA() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyBbs(request, response, "listSysQnA");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.modifyOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.modifyOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifySysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyBbs(request, response, "listSysQnA");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.modifySysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.modifySysQnA() Throwable !!");
		}
	}
	
	/**
	 * 부서공유자료실 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyBbs(request, response, "listPds");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.modifyOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.modifyOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 열린마당 삭제 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteOpenBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteBbs(request, response, "listSysQnA");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.deleteOpenBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.deleteOpenBbs() Throwable !!");
		}
	}
	
	/**
	 * 시스템 Q&A 삭제 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteSysQnA(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteBbs(request, response, "listSysQnA");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.deleteSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.deleteSysQnA() Throwable !!");
		}
	}
	
	/**
	 * 부서공유자료실 삭제 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deletePds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteBbs(request, response, "listPds");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("BbsController.deleteSysQnA() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("BbsController.deleteSysQnA() Throwable !!");
		}
	}

	/**
	* BBS 목록 조회
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listBbs(HttpServletRequest request, HttpServletResponse response, BbsForm form, BbsVO vo) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Bbs_l.jsp";
			
			if("Y".equals(vo.getIsPds())){
				forwardURL = "/WEB-INF/jsp/las/board/Pds_l.jsp";
			}

			//Cross-site Scripting 방지
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_word(),"")));
			form.setSrch_type(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_type(),"")));
			form.setSrch_dmstfrgn_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_dmstfrgn_gbn(),""))); //국내/해외
			
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			vo.setSrch_word(StringUtil.bvl(vo.getSrch_word(),""));
			vo.setSrch_type(StringUtil.bvl(vo.getSrch_type(),""));
			vo.setSrch_dmstfrgn_gbn(StringUtil.bvl(vo.getSrch_dmstfrgn_gbn(),"")); // 국내/해외

			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
		
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());		
			vo.setGrp_no(form.getGrp_no());

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = bbsService.listbbs(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}else {
					//메뉴 최초 진입 시 메세지 여부
					if("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			}else {
				// 메시지처리 - 조회된 결과가 없습니다.
                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
                	returnMessage = (String)request.getAttribute("returnMessage");
                } else {
                	//메뉴 최초 진입 시 메세지 여부
                	if("Y".equals(vo.getDoSearch()))
                		returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
                }
			}
			
			ArrayList lom = (ArrayList)resultList;
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}

	/**
	* BBS 등록페이지 호출
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			
			BbsForm form = null;
			BbsVO vo = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();			
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
 			
 			if("C01201".equals(vo.getBbs_knd()) || "C01217".equals(vo.getBbs_knd()) || "C01214".equals(vo.getBbs_knd()) || "C01215".equals(vo.getBbs_knd()) ){
 				forwardURL = "/WEB-INF/jsp/las/board/Bbs_i_noNamo.jsp";
 			}else{
 				
 				forwardURL = "/WEB-INF/jsp/las/board/Bbs_i.jsp";
 			}


			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* BBS 등록페이지 호출
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			
			BbsForm form = null;
			BbsVO vo = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Pds_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();			
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* BBS 답변 등록페이지 호출
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertReplyBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			
			BbsForm form = null;
			BbsVO vo = null;
			List resultList 	= null;
			ListOrderedMap lom 	= new ListOrderedMap();

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
//			forwardURL = "/WEB-INF/jsp/las/board/Bbs_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();			
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
 			
 			if("C01201".equals(vo.getBbs_knd()) || "C01217".equals(vo.getBbs_knd()) || "C01214".equals(vo.getBbs_knd()) || "C01215".equals(vo.getBbs_knd()) ){
 				forwardURL = "/WEB-INF/jsp/las/board/Bbs_i_noNamo.jsp";
 			}else{
 				
 				forwardURL = "/WEB-INF/jsp/las/board/Bbs_i.jsp";
 			}
 			
 			// 답변인 경우 원본글 제목에 "[Re:]"를 붙임.
 			//form.setTitle(RE.concat(form.getTitle()));

 			
 			resultList = bbsService.detailbbs(vo);
			lom = (ListOrderedMap)resultList.get(0);
			
//			form.setPostup_depth(Integer.parseInt(String.valueOf(lom.get("postup_srt"))));
//			form.setPostup_srt(Integer.parseInt(String.valueOf(lom.get("postup_depth"))));
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("title", "[Re:]" + (String)lom.get("title"));

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* BBS 답변 등록페이지 호출
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertReplyPds(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			
			BbsForm form = null;
			BbsVO vo = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Pds_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();			
			
			bind(request, form);
			bind(request, vo);
			
 			COMUtil.getUserAuditInfo(request, form);
 			COMUtil.getUserAuditInfo(request, vo);
 			
 			// 답변인 경우 원본글 제목에 "[Re:]"를 붙임.
 			form.setTitle(RE.concat(form.getTitle()));

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* BBS 등록
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertBbs(HttpServletRequest request, HttpServletResponse response, String divBbs) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			BbsForm form = null;
			BbsVO vo = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/las/board/bbs.do?method="+divBbs;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			form.setReg_id(vo.getSession_user_id());
			form.setReg_nm(vo.getSession_user_nm());
			form.setReg_dept(vo.getSession_dept_cd());
			form.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
//			String decodeText = vo.getBody_mime();
//			
//			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//			
//			String cont = StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"));
//			String cont = vo.getBody_mime();
//			
//			if(!(StringUtil.checkScript(vo.getTitle()) && StringUtil.checkScript(cont))){
//				ModelAndView mav = new ModelAndView();
//				forwardURL = "/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";		
//				mav.setViewName(forwardURL);
//				form.setReturn_message(messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
//				form.setReturn_title(messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale()));
//				mav.addObject("command", form);
//				
//				return mav;
//			}
			
			
			vo.setPostup_depth(0);			
			
			String accessLevel = bbsService.checkAuthByRole(vo);
			int total_seqno =0;
		    
		    if ("A".equals(accessLevel)){
		    	total_seqno = bbsService.insertbbs(vo, NORMAL);
		    }else{
		    	throw new Exception(messageSource.getMessage("secfw.page.field.alert.noAuth", null, new RequestContext(request).getLocale()));
		    }
			
			form.setTotal_seqno(total_seqno);
			
			
			/*********************************************************
			 * form  setting for forwarding - grp_no, grp_seqno
			 * ************************************/
			
			
			form.setGrp_no(vo.getGrp_no());
			form.setGrp_seqno(vo.getGrp_seqno());
			
			/*********************************************************
			 * Massage 처리 - 등록되었습니다.
			**********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* BBS Reply 등록
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertReplyBbs(HttpServletRequest request, HttpServletResponse response, String divBbs) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			BbsForm form = null;
			BbsVO vo = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/las/board/bbs.do?method="+divBbs;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			form = new BbsForm();
			vo = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			form.setReg_id(vo.getSession_user_id());
			form.setReg_nm(vo.getSession_user_nm());
			form.setReg_dept(vo.getSession_dept_cd());
			form.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
//			String decodeText = vo.getBody_mime();
//			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//			String cont = StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"));
//			
//			if(!(StringUtil.checkScript(vo.getTitle()) && StringUtil.checkScript(cont))){
//				ModelAndView mav = new ModelAndView();
//				forwardURL = "/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";		
//				mav.setViewName(forwardURL);
//				form.setReturn_message(messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
//				form.setReturn_title(messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale()));
//				mav.addObject("command", form);
//				
//				return mav;
//			}
			
			
			bbsService.updatePos(vo);			
//			bbsService.insertbbs(vo, REPLY);		
			
			int total_seqno = bbsService.insertbbs(vo, REPLY);
			
			form.setTotal_seqno(total_seqno);

			/*********************************************************
			 * form  setting for forwarding - grp_no, grp_seqno
			 * ************************************/
			
			form.setGrp_no(vo.getGrp_no());
			form.setGrp_seqno(vo.getGrp_seqno());
			form.setPostup_srt(vo.getPostup_srt()+1);
			form.setPostup_depth(vo.getPostup_depth()+1);
			
			/*********************************************************
			 * Massage 처리 - 등록되었습니다.
			**********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	

	/**
	* BBS 상세
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			BbsForm cmd = (BbsForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			//상세항목
			List resultList = null;
			//하단리스트
			List underList = null;
			ArrayList al = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/WEB-INF/jsp/las/board/Bbs_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BbsForm form = new BbsForm();
			BbsVO   vo   = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			
			if(cmd != null){
				form = cmd;
				vo.setGrp_no(form.getGrp_no());
				vo.setGrp_seqno(form.getGrp_seqno());
			}
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			**********************************************************/
			
			resultList = bbsService.detailbbs(vo);
			ListOrderedMap lom = null;
			
			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			}         
			/*********************************************************
             * 권한 처리
            **********************************************************/     
            boolean modifyAuth 	= bbsService.authBbs(MODIFY, vo);
            boolean deleteAuth 	= bbsService.authBbs(DELETE, vo);
            boolean replyAuth 	= bbsService.authBbs(MODIFY, vo);
//            String accessLevel 	= bbsService.checkAuthByRole(vo);
            form.setAuth_modify(modifyAuth);
            form.setAuth_delete(deleteAuth);
            form.setAuth_reply(replyAuth);

			/*********************************************************
             * 본인 글이 아닐 경우 조회수 증가	
            **********************************************************/   
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				bbsService.increseRdcnt(vo) ;				
			}			

			/*********************************************************
             * 하단 상세글 리스트 정보취득
            **********************************************************/   
			underList = bbsService.detailListbbs(vo);
			al = (ArrayList)underList;
			
			int totalCnt = 0;
			totalCnt = underList.size();
			
			form.setTotalCnt(totalCnt);

			/*********************************************************
			 * Massage 처리
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("al", al);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bbsController.detailBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("bbsController.detailBbs() Throwable !!");
		}
	}

	/**
	* BBS 수정페이지 호출
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyBbs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			BbsForm form       	= null;
			BbsVO vo         	= null;
			List resultList 	= null;
			ListOrderedMap lom 	= new ListOrderedMap();
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
//			forwardURL = "/WEB-INF/jsp/las/board/Bbs_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new BbsForm();
			vo   = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if("C01201".equals(vo.getBbs_knd()) || "C01217".equals(vo.getBbs_knd()) || "C01214".equals(vo.getBbs_knd()) || "C01215".equals(vo.getBbs_knd()) ){
				forwardURL = "/WEB-INF/jsp/las/board/Bbs_u_noNamo.jsp";
 			}else{
 				
 				forwardURL = "/WEB-INF/jsp/las/board/Bbs_u.jsp";
 			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = bbsService.detailbbs(vo);
			lom = (ListOrderedMap)resultList.get(0);

			ModelAndView mav = new ModelAndView();

			if(lom != null){
				/*********************************************************
				 * 수정권한 Check
				**********************************************************/
				boolean auth = bbsService.authBbs(MODIFY, vo);
				
				// 권한이 있는경우, 수정폼 return
				if(auth){
					mav.setViewName(forwardURL);
					mav.addObject("lom", lom);
					mav.addObject("command", form);
				}else{
					// 권한이 없는경우, 메시지 처리 - 수정권한이 없습니다. 
    				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
    		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
    		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
    				mav.addObject("secfw.alert.title", alertTitle);
    				mav.addObject("secfw.alert.message", alertMessage);  					
				}
				
			}else{
				// 메시지처리 - 사용자 정보가 없습니다. 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);
			}

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* BBS 수정
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyBbs(HttpServletRequest request, HttpServletResponse response, String divBbs) throws Exception {
		
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			BbsForm form       = null;
			BbsVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/las/board/bbs.do?method="+divBbs;

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new BbsForm();
			vo   = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 수정권한 Check
			**********************************************************/
			boolean auth = bbsService.authBbs(MODIFY, vo);
			
			if(auth){
				//Cross-site Scripting 방지
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				form.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
	
				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
//				String decodeText = vo.getBody_mime();
//				HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
				
//				String Cont = StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")); 
				
//				String Cont = vo.getBody_mime();
//				
//				if(!(StringUtil.checkScript(vo.getTitle()) && StringUtil.checkScript(Cont))){
//					mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
//			    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
//			    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
//					mav.addObject("secfw.alert.title", alertTitle);
//					mav.addObject("secfw.alert.message", alertMessage); 
//					return mav;
//				}
				
				/*********************************************************
				 * Service
				**********************************************************/
				int total_seqno = bbsService.modifybbs(vo);
				
//				int total_seqno = bbsService.insertbbs(vo, NORMAL);
				
				form.setTotal_seqno(total_seqno);
			
				/*********************************************************
				 * Massage 처리 - 수정되었습니다.
				**********************************************************/
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale()) ;

				/*********************************************************
				 * ModelAndView
				**********************************************************/
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
				
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 수정권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);  					
			}
						
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("modifyBbs() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("modifyBbs() Throwable !!");
		}
	}

	/**
	* BBS 삭제
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteBbs(HttpServletRequest request, HttpServletResponse response, String divBbs) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			BbsForm form = null;
			BbsVO vo = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/las/board/bbs.do?method="+divBbs;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new BbsForm();
			vo = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 삭제 권한 Check
			**********************************************************/
			boolean auth = bbsService.authBbs(DELETE, vo);
			ModelAndView mav = new ModelAndView();
			
			if(auth){
				/*********************************************************
				 * Service
				**********************************************************/
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				
				bbsService.deletebbs(vo);
				
				bbsService.updatePosDel(vo);
				/*********************************************************
				 * Massage 처리 - 삭제되었습니다.
				**********************************************************/
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale()) ;

				/*********************************************************
				 * ModelAndView
				**********************************************************/
				mav.setViewName(forwardURL);
	           	mav.addObject("returnMessage", returnMessage);
	           	mav.addObject("command", form);

	       	}else {

	        	mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	            // 메시지처리 - 삭제권한이 없습니다. 
	        	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale());
	            // 메시지처리 - 시스템 관리자에게 문의하십시오.
	        	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

	            mav.addObject("secfw.alert.title", alertTitle);
	            mav.addObject("secfw.alert.message", alertMessage);                     
	        }

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* BBS 삭제
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public void delCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
//			String forwardURL = "";
//			String returnMessage = "";

			BbsForm form = null;
			BbsVO vo = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new BbsForm();
			vo = new BbsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
		
			List resultList = bbsService.delCheck(vo);
			
			ListOrderedMap lom = null;
			
			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 
			
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(lom.get("CNT"));
    		out.flush();
    		out.close();
			
			
		}catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
	
	/**
	 * 권한에 따른 vo의 top_role 변수 값 세팅. 
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setTopRole(HttpServletRequest request, BbsForm form, BbsVO vo) throws Exception {
		
		if (ClmsBoardUtil.searchRole(request, ROLE_1)) {		//그룹장
			vo.setTop_role("grpmgr");
			form.setTop_role("grpmgr");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_2)) {		//담당자
			vo.setTop_role("respman");
			form.setTop_role("respman");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_3)) {		//법무일반사용자
			vo.setTop_role("normal");
			form.setTop_role("normal");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_0) || ClmsBoardUtil.searchRole(request, ROLE_8)) {		//관리자
			vo.setTop_role("admin");
			form.setTop_role("admin");
		}else if ( ClmsBoardUtil.searchRole(request, ROLE_4) 
				|| ClmsBoardUtil.searchRole(request, ROLE_5) 
				|| ClmsBoardUtil.searchRole(request, ROLE_6)
				|| ClmsBoardUtil.searchRole(request, ROLE_7)) {		//etc
			vo.setTop_role("etc");
			form.setTop_role("etc");
		}else {
			// 해당 권한이 아닐 시 Exception을 날린다
			throw new Exception();
		}
	}

}