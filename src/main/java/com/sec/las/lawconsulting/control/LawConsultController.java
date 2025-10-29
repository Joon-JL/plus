/** 
 * Project Name : 법무시스템 이식
 * File Name : LawConsultController.java
 * Description : 법률자문 컨트롤러
 * Author : 김현구
 * Date : 2011. 11. 04
 * Copyright : 
 */

package com.sec.las.lawconsulting.control;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sec.common.clmscom.dto.CLMSCommonVO;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.las.lawconsulting.dto.LawConsultForm;
import com.sec.las.lawconsulting.dto.LawConsultVO;
import com.sec.las.lawconsulting.service.LawConsultService;

/**
 * Description : 법률자문 컨트롤러 클래스 Author : 김현구 Date : 2011.11.04
 */
public class LawConsultController extends CommonController {

	/**
	 * ROLE 구분을 위한 변수
	 */
	static String ROLE_0 = "RA00"; // 시스템관리자
	static String ROLE_1 = "RA01"; // 법무관리자
	static String ROLE_2 = "RA02"; // 법무담당자
	//static String ROLE_3 = "RA03"; // 법무일반사용자
	//static String ROLE_4 = "RC01"; // CP관리자
	static String ROLE_5 = "RD01"; // 사업부계약관리자
	//static String ROLE_6 = "RD02"; // 사업부계약담당자
	static String ROLE_7 = "RZ00"; // 일반임직원
	static String ROLE_8 = "RB01"; //전자검토자
	static String ROLE_9 = "RB02"; //전자임원
	static String ROLE_10 = "RM00"; // 어드민

	private CLMSCommonService clmsComService;

	public void setClmsComService(CLMSCommonService clmsComService) {
		this.clmsComService = clmsComService;
	}

	/**
	 * 법률자문 서비스
	 */
	private LawConsultService lawConsultService;

	/**
	 * 법률자문 서비스 세팅
	 * 
	 * @param lawConsultService
	 */
	public void setLawConsultService(LawConsultService lawConsultService) {
		this.lawConsultService = lawConsultService;
	}

	/**
	 * 클래스 메소드 권한 서비스
	 */
	private ClassMethodAuthService classMethodAuthService = null;

	/**
	 * 클래스 메소드 권한 서비스 세팅
	 * 
	 * @param classMethodAuthService
	 */
	public void setClassMethodAuthService(ClassMethodAuthService classMethodAuthService) {
		this.classMethodAuthService = classMethodAuthService;
	}

	/**
	 * 싱글 메일 서비스
	 */
	private EsbMailService esbMailService = null;

	/**
	 * 싱글 메일 서비스 세팅
	 * 
	 * @param esbMailService
	 */
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}

	
	
	
	
	/**
	* 검토 목록에서 해당 의뢰의 중요도를 체크 처리 한다.
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public void setMarkUpAJAX(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			LawConsultVO vo = new LawConsultVO();		
			LawConsultForm form = new LawConsultForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
	

			/*********************************************************
			 * Service
			**********************************************************/
			int return_val = lawConsultService.setMarkUpAJAX(vo);	
		
			/*********************************************************
			 * AJAX 세팅
			**********************************************************/			
			JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("return_val", String.valueOf(return_val));
			
			jsonArray.add(jsonObject);
			
			response.setContentType("application/json; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.print(jsonArray);
		    out.flush();
		    out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
//			JSONObject jo = new JSONObject();
//    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
//    		jo.put("returnCnt", 0);
//
//    		response.setContentType("application/json; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(jo);
//			out.flush();
//			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
//			response.setContentType("application/json; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
//			out.flush();
//			out.close();
		}
	}	

	
	
	/**
	 * 자문의뢰 for 그룹장, 담당자
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();

		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		// 등록자 정보 세션에서 받아와 vo에 세팅
		setUserInfo(form, vo);

		mav = listLawConsultReqman(request, vo, form);

		// 권한에 따른 top_role 값 세팅
		setTopRole(request, form, vo);

		return mav;
	}

	/**
	 * 자문의뢰 for 그룹장, 담당자
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listStaLawConsultRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();

		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		// 등록자 정보 세션에서 받아와 vo에 세팅
		setUserInfo(form, vo);

		mav = listStaLawConsultReqman(request, vo, form);

		// 권한에 따른 top_role 값 세팅
		setTopRole(request, form, vo);

		return mav;
	}

	/**
	 * 자문 리스트 조회 - 사용자 역할(그룹장, 담당자, 의뢰인)에 따른 리스트 함수 호출 수행
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession(false);

		// 권한이 담당자일시 검색필드에 자신의 이름을 넣어준다. (default로 자신의 업무만을 리스트로 뽑기 위함)
		if (ClmsBoardUtil.searchRole(request, ROLE_2) || ClmsBoardUtil.searchRole(request, ROLE_8) || ClmsBoardUtil.searchRole(request, ROLE_9)) {

			if (!ClmsBoardUtil.searchRole(request, ROLE_1) && !ClmsBoardUtil.searchRole(request, ROLE_9)) { // RA01이면 검색필더에
																// 자신의 이름을 넣지
																// 않는다.(RA01,RA02,RB02
																// 둘다 가지고 있을 경우)
				form.setSrch_respman_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
				vo.setSrch_respman_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
				vo.setIsRespman("Y");
			}
		}

		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// srch_reception 값이 세팅되지 않았을 시 디폴트 값 세팅
		if (form.getSrch_reception() == null || form.getSrch_reception().equals("")) {
			form.setSrch_reception("N");
			vo.setSrch_reception("N");
		}

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		// 등록자 정보 세션에서 받아와 vo에 세팅
		setUserInfo(form, vo);

		// 권한에 따른 top_role 값 세팅
		setTopRole(request, form, vo);
		
		if("req".equals(vo.getFwd_gbn())){
			
			// role 에 상관없이 의뢰페이지에서 등록한 뒤 목록이동이면 의뢰목록으로 이동
			mav = listLawConsultReqman(request, vo, form); // 의뢰인 페이지
			
		}else if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
			mav = listLawConsultGrpmgr(request, vo, form); // 그룹장 페이지

		} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
			mav = listLawConsultRespman(request, vo, form); // 담당자 페이지

		} else if ("etc".equals(vo.getTop_role())) {

			mav = listLawConsultReqman(request, vo, form); // 의뢰인 페이지
		}
		return mav;
	}

	/**
	 * 리스트 조회 - 의뢰인
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultReqman(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";
		try {

			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			// 리스트 조회 서비스 호출
			List resultList = lawConsultService.listLawConsultReqman(vo);
			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			// 검색어 XXS 처리
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd()));
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd()));
			form.setPrgrs_status(StringUtil.convertHtmlTochars(form.getPrgrs_status()));
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	
	/**
	 * CC된 리스트 조회. 2014-03-03 Kevin.
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultCCed(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();

		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		// 등록자 정보 세션에서 받아와 vo에 세팅
		setUserInfo(form, vo);
		
		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_l.jsp";
		PageUtil pageUtil = new PageUtil();
		
		String returnMessage = "";
		
		try {

			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			// 리스트 조회 서비스 호출
			List resultList = lawConsultService.listLawConsultCCed(vo);
			form.setLawconsult_list(resultList);
			
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			//페이지에서 CC된 리스트임을 구분하는 구분자.
			mav.addObject("isCopied", true);
			
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	

	/**
	 * 자문 리스트 조회 - 그룹장
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultGrpmgr(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultGrpmgr_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";
		String isRespman = "N";
		List resultList;

		try {
			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			setConsultType(form, vo, 1);
			
			// 리스트 조회 서비스 호출
			resultList = lawConsultService.listLawConsultGrpmgr(vo);

			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}
			
			
			// Legal Admin 이 검토자의 권한(RA02)을 동시에 가지고 있는지 확인
			if(ClmsBoardUtil.searchRole(request, ROLE_2)){
				isRespman = "Y";
			}
			

			// 검색어 XXS 처리 (그룹장 필드 - 의뢰일, 담당자, 제목, 내용, 의뢰인, 상담종류)
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd())); // 검색의뢰일-처음
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd())); // 검색의뢰일-끝
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목
			form.setSrch_cont(StringUtil.convertHtmlTochars(form.getSrch_cont())); // 내용
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(form.getSrch_reg_nm())); // 의뢰인
			form.setSrch_consult_type_name(StringUtil.convertHtmlTochars(form.getSrch_consult_type_name())); // 자문유형
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(form.getSrch_prgrs_status())); // 진행상태
			form.setSrch_reg_id(StringUtil.convertHtmlTochars(form.getSrch_reg_id())); // 의뢰인
																						// id
			
			
			if(ClmsBoardUtil.searchRole(request, ROLE_9)){
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
			} else {
				form.setsSel_grd("N");
			}
			
			String elUserYn = "N"; 
			if(ClmsBoardUtil.searchRole(request, ROLE_8)){
				elUserYn = "Y";
			}

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("isRespman", isRespman);
			mav.addObject("elUserYn",elUserYn);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문 리스트 조회 - 담당자
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultRespman(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultGrpmgr_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";

		try {
			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			setConsultType(form, vo, 1);

			// 리스트 조회 서비스 호출
			List resultList = lawConsultService.listLawConsultGrpmgr(vo);
			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			// 검색어 XXS 처리 (그룹장 필드 - 의뢰일, 담당자, 제목, 내용, 의뢰인, 상담종류)
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd())); // 검색의뢰일-처음
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd())); // 검색의뢰일-끝
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목
			form.setSrch_cont(StringUtil.convertHtmlTochars(form.getSrch_cont())); // 내용
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(form.getSrch_reg_nm())); // 의뢰인
			form.setSrch_consult_type_name(StringUtil.convertHtmlTochars(form.getSrch_consult_type_name())); // 자문유형
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(form.getSrch_prgrs_status())); // 진행상태
			form.setSrch_reg_id(StringUtil.convertHtmlTochars(form.getSrch_reg_id())); // 의뢰인
																						// id
			
			if(ClmsBoardUtil.searchRole(request, ROLE_9)){
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
			} else {
				form.setsSel_grd("N");
			}
			
			String elUserYn = "N"; 
			if(ClmsBoardUtil.searchRole(request, ROLE_8)){
				elUserYn = "Y";
			}
			mav.setViewName(forwardURL);
			mav.addObject("isRespman", "Y");
			mav.addObject("elUserYn",elUserYn);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 표준 게약서 자문 리스트 조회 - 사용자 역할(그룹장, 담당자, 의뢰인)에 따른 리스트 함수 호출 수행
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession(false);

		// 권한이 담당자일시 검색필드에 자신의 이름을 넣어준다. (default로 자신의 업무만을 리스트로 뽑기 위함)
		if (ClmsBoardUtil.searchRole(request, ROLE_2) || ClmsBoardUtil.searchRole(request, ROLE_8) || ClmsBoardUtil.searchRole(request, ROLE_9)) {

			if (!ClmsBoardUtil.searchRole(request, ROLE_1) && !ClmsBoardUtil.searchRole(request, ROLE_9)) { // RA01이면 검색필더에
																	// 검색필더에 자신의
																	// 이름을 넣지
				                                                    // 않는다.(RA01,RA02,RB02
																	// 둘다 가지고 있을
																	// 경우)
					form.setSrch_respman_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
					vo.setSrch_respman_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
					vo.setIsRespman("Y");
				}
		}

		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// srch_reception 값이 세팅되지 않았을 시 디폴트 값 세팅
		if (form.getSrch_reception() == null || form.getSrch_reception().equals("")) {
			form.setSrch_reception("N");
			vo.setSrch_reception("N");
		}

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		// 등록자 정보 세션에서 받아와 vo에 세팅
		setUserInfo(form, vo);

		// 권한에 따른 top_role 값 세팅
		setTopRole(request, form, vo);
		
		
		if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
			mav = listStaLawConsultGrpmgr(request, vo, form); // 표준 계약서 그룹장 페이지

		} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
			mav = listStaLawConsultRespman(request, vo, form); // 표준 계약서 담당자 페이지

		} else if ("etc".equals(vo.getTop_role())) {

			mav = listStaLawConsultReqman(request, vo, form); // 표준 계약서 의뢰인 페이지
		}
		return mav;
	}

	/**
	 * 자문(표준계약서) 리스트 조회 - 그룹장
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listStaLawConsultGrpmgr(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultGrpmgr_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";
		List resultList;

		try {
			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			setConsultType(form, vo, 1);
			
			// 리스트 조회 서비스 호출
			resultList = lawConsultService.listLawStaConsultGrpmgr(vo);

			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			// 검색어 XXS 처리 (그룹장 필드 - 의뢰일, 담당자, 제목, 내용, 의뢰인, 상담종류)
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd())); // 검색의뢰일-처음
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd())); // 검색의뢰일-끝
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목
			form.setSrch_cont(StringUtil.convertHtmlTochars(form.getSrch_cont())); // 내용
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(form.getSrch_reg_nm())); // 의뢰인
			form.setSrch_consult_type_name(StringUtil.convertHtmlTochars("A20")); // 자문유형
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(form.getSrch_prgrs_status())); // 진행상태
			form.setSrch_reg_id(StringUtil.convertHtmlTochars(form.getSrch_reg_id())); // 의뢰인
																						
			String elUserYn = "N"; 
			if(ClmsBoardUtil.searchRole(request, ROLE_8)){
				elUserYn = "Y";
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("elUserYn", elUserYn);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문(표준 계약서) 리스트 조회 - 의뢰인
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listStaLawConsultReqman(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {
		//setTopRole(request, form, vo);
		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";
		try {

			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());
			
			//if (ClmsBoardUtil.searchRole(request, ROLE_10)) {
				//vo.setTop_role("adminRole");
			//}

			// 리스트 조회 서비스 호출
			List resultList = lawConsultService.listLawStaConsultReqman(vo);
			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			// 검색어 XXS 처리
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd()));
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd()));
			form.setPrgrs_status(StringUtil.convertHtmlTochars(form.getPrgrs_status()));
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문(표준계약서) 리스트 조회 - 담당자
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listStaLawConsultRespman(HttpServletRequest request, LawConsultVO vo, LawConsultForm form) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultGrpmgr_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";

		try {
			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			setConsultType(form, vo, 1);

			// 리스트 조회 서비스 호출
			List resultList = lawConsultService.listLawStaConsultGrpmgr(vo);
			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}

			// 검색어 XXS 처리 (그룹장 필드 - 의뢰일, 담당자, 제목, 내용, 의뢰인, 상담종류)
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd())); // 검색의뢰일-처음
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd())); // 검색의뢰일-끝
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm())); // 담당자
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title())); // 제목
			form.setSrch_cont(StringUtil.convertHtmlTochars(form.getSrch_cont())); // 내용
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(form.getSrch_reg_nm())); // 의뢰인
			form.setSrch_consult_type_name(StringUtil.convertHtmlTochars(form.getSrch_consult_type_name())); // 자문유형
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(form.getSrch_prgrs_status())); // 진행상태
			form.setSrch_reg_id(StringUtil.convertHtmlTochars(form.getSrch_reg_id())); // 의뢰인
																						// id
			
			if(ClmsBoardUtil.searchRole(request, ROLE_9)){
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
			} else {
				form.setsSel_grd("N");
			}
			
			String elUserYn = "N"; 
			if(ClmsBoardUtil.searchRole(request, ROLE_8)){
				elUserYn = "Y";
			}
			mav.setViewName(forwardURL);
			mav.addObject("elUserYn",elUserYn);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문 유형 popup 화면으로 이동(팝업창)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPopupLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsult_p.jsp";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			CLMSCommonVO cvo = new CLMSCommonVO();

			bind(request, form);
			bind(request, vo);

			// 코드 세팅 - LAS
			cvo.setSys_cd("LAS");
			// 국내, 해외,IP 판별해서 그룹코드 세팅
			if (form.getIsForeign().equals("F")) // 해외
				cvo.setGrp_cd("N3");
			else if (form.getIsForeign().equals("H")) // 국내
				cvo.setGrp_cd("N2");
			else { // IP
				cvo.setGrp_cd("N2");
				cvo.setUseman_mng_itm1("IP");
			}
			cvo.setDel_yn("N");

			List cdList = clmsComService.listComCdByGrpCd2(cvo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);

			mav.setViewName(forwardURL);
			mav.addObject("cdList", cdList);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

	}

	/**
	 * 인쇄 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPrintLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultPrint.jsp";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = new ListOrderedMap();
			//List extnlcompList;
			String returnMessage = "";

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			setTopRole(request, form, vo);
			
			form.setContents(form.getContents().toString());
			
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			//lom = lawConsultService.detailLawConsultGrpmgr(vo);

			// 조회결과가 없는 경우
//			if (lom.size() == 0) {
//				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
//			}

			//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
			// form.setMain_matr_cont(StringUtil.replace((String)
			// lom.get("main_matr_cont"), "<br>", "\r\n"));

			mav.setViewName(forwardURL);
			//mav.addObject("lom", lom);
			mav.addObject("command", form);
			//mav.addObject("extnlcompList", extnlcompList);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

	}
	
	
	/**
	 * 인쇄 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPrintDetailLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultPrintDetail.jsp";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = new ListOrderedMap();
			//List extnlcompList;
			String returnMessage = "";

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			setTopRole(request, form, vo);

			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = lawConsultService.detailLawConsultGrpmgr(vo);

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}else{
				// 의뢰 내용 <br> 전환
				lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));
				
				if(lom.get("file_name") != null){
					lom.put("file_name", lom.get("file_name").toString().replace("/", "<BR>"));
				}
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

	}
	

	/**
	 * 보류 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardHoldLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultHold_i.jsp";
			String returnMessage = "";
			ListOrderedMap lom = new ListOrderedMap();

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			if ("Y".equals(form.getIsGrpmgr())) {
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
			} else {
				lom = lawConsultService.detailLawConsultReqman(vo);
			}

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 보류 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardHoldStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultHold_i.jsp";
			String returnMessage = "";
			ListOrderedMap lom = new ListOrderedMap();

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			if ("Y".equals(form.getIsGrpmgr())) {
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
			} else {
				lom = lawConsultService.detailLawConsultReqman(vo);
			}

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 검토회신 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardReviewLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReview_i.jsp";
			String returnMessage = "";
			ListOrderedMap lom = new ListOrderedMap();
			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			String isModify = "";
			
			// 2014-02-25 Kevin Added
			List<?> listCa = null;

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = lawConsultService.detailLawConsultGrpmgr(vo);

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}else{
				//  2014-02-25 Kevin Added.
				listCa = lawConsultService.listContractAuthreq(vo);
			}

			// 상태가 의뢰or회신시 DETAIL 화면에서 검토회신 삽입
			if (form.getPrgrs_status().equals("S02") || form.getPrgrs_status().equals("S04") || form.getPrgrs_status().equals("S03")) {
				isModify = "";
				// EXTNL_YN 값을 N으로 세팅
				lom.setValue(16, "N");
			}
			else {
				// 상태가 임시저장시 DETAIL 화면에서는 검토회신 수정
				isModify = "1";
			}
			form.setReturn_message(returnMessage);
			mav.setViewName(forwardURL);
			mav.addObject("isModify", isModify);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			// 2014-02-25 Kevin Added.
			mav.addObject("listCa", listCa);
			
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 검토회신(표준계약서) 화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardStaReviewLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultStaReview_i.jsp";
			String returnMessage = "";
			ListOrderedMap lom = new ListOrderedMap();
			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			String isModify = "";
			//List extnlcompList = null;

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, vo);
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = lawConsultService.detailLawConsultGrpmgr(vo);

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}

			// 상태가 의뢰or회신시 DETAIL 화면에서 검토회신 삽입
			if (form.getPrgrs_status().equals("S02") || form.getPrgrs_status().equals("S04") || form.getPrgrs_status().equals("S03")) {
				isModify = "";
				// EXTNL_YN 값을 N으로 세팅
				lom.setValue(16, "N");
			}
			// 상태가 임시저장시 DETAIL 화면에서는 검토회신 수정
			else {
				isModify = "1";
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
				// textarea 줄바꿈 적용
				// form.setMain_matr_cont(StringUtil.replace((String)
				// lom.get("main_matr_cont"), "<br>", "\r\n"));
			}
			form.setReturn_message(returnMessage);
			mav.setViewName(forwardURL);
			mav.addObject("isModify", isModify);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			//mav.addObject("extnlcompList", extnlcompList);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문 의뢰 등록화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = new ListOrderedMap();
			
			List<?> listCa = null;

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);

			setTopRole(request, form, vo);

			form.setReg_id(form.getSession_user_id());
			form.setReg_nm(form.getSession_user_nm());
			form.setReg_dept(form.getSession_dept_cd());
			form.setReg_dept_nm(form.getSession_dept_nm());

			if (form.getHstry_no() > 0) {
				// vo.setHstry_no(form.getHstry_no() - 1);
				lom = lawConsultService.detailLawConsultReqman(vo);
				listCa = lawConsultService.listContractAuthreq(vo);
			}

			// // 조회결과가 있는 경우 재의뢰
			if (lom.size() > 0) {
				// // 조회결과가 있으면 받아온 데이터 중 수정이 가능한 데이터를 FORM에 세팅한다
				form.setCnslt_no((String)lom.get("cnslt_no"));
				form.setTitle((String) lom.get("title"));
				// form.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
				// form.setReg_nm((String) lom.get("reg_nm"));
				// form.setReg_dept_nm((String) lom.get("reg_dept_nm"));
				form.setConsult_type((String) lom.get("consult_type"));
				form.setConsult_type_name((String) lom.get("consult_type_name"));
				form.setCont((String)lom.get("cont"));
				// form.setPrgrs_status((String) lom.get("prgrs_status"));
			}else{
				
				// 조회결과가 없는경우 신규 요청이므로 회신요청일 현재일+3일로 설정
				SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				
				cal.add(Calendar.DATE,3);
				
				// 회신요청일 기본값 세팅
				form.setReq_reply_dt(sdfmt.format(cal.getTime()));
				
			}

			forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_i.jsp";

			mav.setViewName(forwardURL);
			mav.addObject("listCa", listCa);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자문의뢰(표준계약서) 등록화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = new ListOrderedMap();

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);

			setTopRole(request, form, vo);

			form.setReg_id(form.getSession_user_id());
			form.setReg_nm(form.getSession_user_nm());
			form.setReg_dept(form.getSession_dept_cd());
			form.setReg_dept_nm(form.getSession_dept_nm());

			if (form.getHstry_no() > 0) {
				// vo.setHstry_no(form.getHstry_no() - 1);
				lom = lawConsultService.detailLawConsultReqman(vo);
			}

			// // 조회결과가 있는 경우 재의뢰
			if (lom.size() > 0) {
				// // 조회결과가 있으면 받아온 데이터 중 수정이 가능한 데이터를 FORM에 세팅한다
				form.setTitle((String) lom.get("title"));
				// form.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
				// form.setReg_nm((String) lom.get("reg_nm"));
				// form.setReg_dept_nm((String) lom.get("reg_dept_nm"));
				form.setConsult_type((String) lom.get("consult_type"));
				form.setConsult_type_name((String) lom.get("consult_type_name"));
				// form.setPrgrs_status((String) lom.get("prgrs_status"));
			}

			forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_i.jsp";

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 글 등록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();

		try {

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);

			// 의뢰자가 의뢰하는 상태가 아닌 모든 작업의 경우
			if (vo.getCnslt_no() == null || "".equals(vo.getCnslt_no())) {
				// 국내/해외 구분 변수 세팅
				if (vo.getIsForeign().equals("F"))
					vo.setDmstfrgn_gbn("F");
				else if (vo.getIsForeign().equals("H"))
					vo.setDmstfrgn_gbn("H");
				else {
					vo.setDmstfrgn_gbn("IP");
				}

				// 등록자 정보 세션에서 받아와 vo에 세팅
				setUserInfo(form, vo);

			} else {

				lom = lawConsultService.detailLawConsult(vo);

				if (lom.size() == 0) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
				// vo 세팅
				setVO(lom, vo, form);
			}

			setConsultType(form, vo, 0);
			setPrgrsStatus(form, vo);

			// Double submit 방지(중복 등록 체크)
			if (Token.isValid(request)) {

				// 중복 등록이 아니면 등록 서비스 호출
				result = lawConsultService.insertLawConsult(request, vo);
				form.setHstry_no(vo.getHstry_no());
			} else {
				// 중복 등록시 result값 세팅
				result = 100;
			}

			// 등록이 되었으면 검색조건을 초기화 한 후 리스트로 이동
			if (result > 0) {

				setInitFormVO(request, form, vo);

				// 그룹장이면 그룹장 리스트로
				if ("Y".equals(vo.getIsGrpmgr())) {
					mav = listLawConsultGrpmgr(request, vo, form);
				}
				// 일반사용자
				else {
					mav = listLawConsultReqman(request, vo, form);
				}

				// 중복 등록이 아니고, 입력이 성공했을 시 메시지 세팅
				if (result == 1) {
					if ("hold".equals(form.getCheck_prgrs_status())) { // 보류
						returnMessage = messageSource.getMessage("secfw.msg.succ.hold", null, new RequestContext(request).getLocale());
					} else if ("reply".equals(form.getCheck_prgrs_status())) { // 회신
						returnMessage = messageSource.getMessage("secfw.msg.succ.send", null, new RequestContext(request).getLocale());
					} else if ("refer".equals(form.getCheck_prgrs_status()) || "referGrpmgr".equals(form.getCheck_prgrs_status())) { // 반려,
																																		// 그룹장반려
						returnMessage = messageSource.getMessage("las.page.field.statistics.contlegaladvice.returned", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					}
					Token.resetToken(request, "TOKEN");
				}

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			// view 설정 - insert가 실패했으므로 insert 페이지 재 호출
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_i.jsp");
			form.setCont(vo.getCont());
			e.printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);

		return mav;
	}
	
	/**
	 * 글 등록
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();

		try {

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);

			// 의뢰자가 의뢰하는 상태가 아닌 모든 작업의 경우
			if (vo.getCnslt_no() == null || "".equals(vo.getCnslt_no())) {
				// 국내/해외 구분 변수 세팅
				if (vo.getIsForeign().equals("F"))
					vo.setDmstfrgn_gbn("F");
				else if (vo.getIsForeign().equals("H"))
					vo.setDmstfrgn_gbn("H");
				else {
					vo.setDmstfrgn_gbn("IP");
				}

				// 등록자 정보 세션에서 받아와 vo에 세팅
				setUserInfo(form, vo);

			} else {

				lom = lawConsultService.detailLawConsult(vo);

				if (lom.size() == 0) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
				// vo 세팅
				setVO(lom, vo, form);

				// bind(request, vo);
			}

			setConsultType(form, vo, 0);
			setPrgrsStatus(form, vo);

			// Double submit 방지(중복 등록 체크)
			if (Token.isValid(request)) {

				// 중복 등록이 아니면 등록 서비스 호출
				result = lawConsultService.insertLawConsult(request, vo);
				form.setHstry_no(vo.getHstry_no());
			} else {
				// 중복 등록시 result값 세팅
				result = 100;
			}

			// 등록이 되었으면 검색조건을 초기화 한 후 리스트로 이동
			if (result > 0) {

				setInitFormVO(request, form, vo);

				// 그룹장이면 그룹장 리스트로
				if ("Y".equals(vo.getIsGrpmgr())) {
					mav = listStaLawConsultGrpmgr(request, vo, form);
				}
				// 일반사용자
				else {
					mav = listStaLawConsultReqman(request, vo, form);
				}

				// 중복 등록이 아니고, 입력이 성공했을 시 메시지 세팅
				if (result == 1) {
					if ("hold".equals(form.getCheck_prgrs_status())) { // 보류
						returnMessage = messageSource.getMessage("secfw.msg.succ.hold", null, new RequestContext(request).getLocale());
					} else if ("reply".equals(form.getCheck_prgrs_status())) { // 회신
						returnMessage = messageSource.getMessage("secfw.msg.succ.send", null, new RequestContext(request).getLocale());
					} else if ("refer".equals(form.getCheck_prgrs_status()) || "referGrpmgr".equals(form.getCheck_prgrs_status())) { // 반려,
																																		// 그룹장반려
						returnMessage = messageSource.getMessage("las.page.field.statistics.contlegaladvice.returned", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					}
					Token.resetToken(request, "TOKEN");
				}

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			// view 설정 - insert가 실패했으므로 insert 페이지 재 호출
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_i.jsp");
			form.setCont(vo.getCont());
			e.printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);

		return mav;
	}
	

	/**
	 * 검토회신
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertReviewLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		try {

			//vo.setExtnlList(setExtnlList(form.getExtnl_list(), vo)); // 외부기관 리스트
			setConsultType(form, vo, 0); // 자문유형 세팅
			setPrgrsStatus(form, vo); // 진행상태 세팅

			lom = lawConsultService.detailLawConsult(vo);
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}
			// vo 세팅
			setVO(lom, vo, form);
			vo.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
			vo.setApbt_opnn(StringUtil.bvl((String) lom.get("apbt_opnn"), ""));
			vo.setOrdr_cont(StringUtil.bvl((String) lom.get("ordr_cont"), ""));

			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.insertReviewLawConsult(request, vo);
				form.setHstry_no(vo.getHstry_no());
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {
				mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화

				if (result == 1) {
					if (form.getCheck_prgrs_status().equals("tempSave")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.page.button.resolved", null, new RequestContext(request).getLocale());
					}

					Token.resetToken(request, "TOKEN");
				}

			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			// mav.setViewName("/las/lawconsulting/lawConsult.do?method=forwardReviewLawConsult");
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultReview_i.jsp");
			lom = lawConsultService.detailLawConsultGrpmgr(vo);
			mav.addObject("lom", lom);
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	 * 검토회신
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertReviewStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		try {

			//vo.setExtnlList(setExtnlList(form.getExtnl_list(), vo)); // 외부기관 리스트
			setConsultType(form, vo, 0); // 자문유형 세팅
			setPrgrsStatus(form, vo); // 진행상태 세팅

			lom = lawConsultService.detailLawConsult(vo);
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}
			// vo 세팅
			setVO(lom, vo, form);
			vo.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
			vo.setApbt_opnn(StringUtil.bvl((String) lom.get("apbt_opnn"), ""));
			vo.setOrdr_cont(StringUtil.bvl((String) lom.get("ordr_cont"), ""));
			
			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.insertReviewLawConsult(request, vo);
				form.setHstry_no(vo.getHstry_no());
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {
				mav = forwardDetailStaLawConsult(request, form, vo, new RequestContext(request).getLocale());

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화

				if (result == 1) {
					if (form.getCheck_prgrs_status().equals("tempSave")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.msg.succ.send", null, new RequestContext(request).getLocale());
					}

					Token.resetToken(request, "TOKEN");
				}

			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			// mav.setViewName("/las/lawconsulting/lawConsult.do?method=forwardReviewLawConsult");
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultStaReview_i.jsp");
			lom = lawConsultService.detailLawConsultGrpmgr(vo);
			mav.addObject("lom", lom);
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return mav;
	}
	

	/**
	 * 담당자 추가
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertRespmanLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		vo.setGrpmgr_re_yn(vo.getGrpmgr_re_yn_value());

		// setUserInfo(form, vo);

		try {
			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)

				result = lawConsultService.insertRespmanLawConsult(request, vo);

			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {

				mav = listLawConsultGrpmgr(request, vo, form);

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultGrpmgr_d.jsp");
			ListOrderedMap lom = new ListOrderedMap();
			List historyList = lawConsultService.listLawConsultHistory(vo);
			List respmanList = lawConsultService.listLawConsultRespman2(vo);
			lom = lawConsultService.detailLawConsultGrpmgr(vo);
			mav.addObject("lom", lom);
			mav.addObject("respmanList", respmanList);
			mav.addObject("historyList", historyList);
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 표준계약서 담당자 추가
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertRespmanStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		vo.setGrpmgr_re_yn(vo.getGrpmgr_re_yn_value());

		// setUserInfo(form, vo);

		try {
			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)

				result = lawConsultService.insertRespmanLawConsult(request, vo);

			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {

				mav = listStaLawConsultGrpmgr(request, vo, form);

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawStaConsultGrpmgr_d.jsp");
			ListOrderedMap lom = new ListOrderedMap();
			List historyList = lawConsultService.listLawConsultHistory(vo);
			List respmanList = lawConsultService.listLawConsultRespman2(vo);
			lom = lawConsultService.detailLawConsultGrpmgr(vo);
			mav.addObject("lom", lom);
			mav.addObject("respmanList", respmanList);
			mav.addObject("historyList", historyList);
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	 * 자문 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());

		return mav;
	}

	/**
	 * 자문 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailLawConsultReqman(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		mav = forwardDetailLawConsultReqman(request, form, vo, new RequestContext(request).getLocale());

		return mav;
	}

	/**
	 * 자문 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailLawConsultReqman(HttpServletRequest request, LawConsultForm form, LawConsultVO vo, Locale locale) throws Exception {
		
		ModelAndView mav = new ModelAndView();

		String forwardURL 		= "";
		String returnMessage 	= "";
		String ccYn				= "";		// 참조인의 조회인지, 본인의 조회인지 화면에서 구분
		
		ListOrderedMap lom = new ListOrderedMap();
		List<?> historyList;
		//List extnlcompList = null;
		//List<?> cnsdrejctList = null;
		List<?> listCa = null;
		
		try {
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);

			forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_d.jsp";
			lom = lawConsultService.detailLawConsultReqman(vo); // 상세화면에 필요한 정보

			String regId = StringUtil.bvl((String) lom.get("reg_id"), "");

			// 자신이 등록한 건이 아닌 다른 건에 접근하려는 시도를 방지하기 위한 코드
			// Detail에서 조회하려는 건의 reg_id와 현재 session의 user_id를 비교
			
			// 글의 등록자와 현재 세션의 유저가 다르면 참조인(CC) 의 조회임
			if (!(regId.equals(vo.getSession_user_id()))) {
				ccYn = "Y";
			}

			historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)
			//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo); // 외부기관

			ListOrderedMap hisLom = null;
			// 보류 버튼을 제어하기 위해 가장 마지막 history를 구한다
			hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

			// history에서 가장 큰 hstryNo 값
			mav.addObject("maxHstryNo", hisLom.get("hstry_no"));

			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, locale);
			}else{
				listCa = lawConsultService.listContractAuthreq(vo);
			}

			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));
			// 의뢰 내용 <br> 전환
			lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		//mav.addObject("auth", auth);
		//mav.addObject("extnlcompList", extnlcompList);		

		mav.setViewName(forwardURL);
		mav.addObject("historyList", historyList);
		mav.addObject("lom", lom);
		mav.addObject("listCa", listCa);
		mav.addObject("ccYn", ccYn);
		mav.addObject("command", form);
		
		return mav;
	}
	
	
	/**
	 * 표준계약서등록요청 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStaLawConsultReqman(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		mav = forwardDetailStaLawConsultReqman(request, form, vo, new RequestContext(request).getLocale());

		return mav;
	}

	/**
	 * 표준계약서등록요청 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStaLawConsultReqman(HttpServletRequest request, LawConsultForm form, LawConsultVO vo, Locale locale) throws Exception {
		ModelAndView mav = new ModelAndView();
		String forwardURL = "";

		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		List<?> historyList;
		//List extnlcompList = null;
		//List<?> cnsdrejctList = null;

		try {
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);

			forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_d.jsp";
			lom = lawConsultService.detailLawConsultReqman(vo); // 상세화면에 필요한 정보

			String regId = StringUtil.bvl((String) lom.get("reg_id"), "");

			// 자신이 등록한 건이 아닌 다른 건에 접근하려는 시도를 방지하기 위한 코드
			// Detail에서 조회하려는 건의 reg_id와 현재 session의 user_id를 비교
			if (!(regId.equals(vo.getSession_user_id()))) {
				throw new Exception();
			}

			historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)
			//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo); // 외부기관

			ListOrderedMap hisLom = null;
			// 보류 버튼을 제어하기 위해 가장 마지막 history를 구한다
			hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

			// history에서 가장 큰 hstryNo 값
			mav.addObject("maxHstryNo", hisLom.get("hstry_no"));

			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, locale);
			}

			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));
			// 의뢰 내용 <br> 전환
			lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		//mav.addObject("auth", auth);
		mav.addObject("historyList", historyList);
		//mav.addObject("extnlcompList", extnlcompList);
		mav.addObject("lom", lom);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 자문 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailLawConsult(HttpServletRequest request, LawConsultForm form, LawConsultVO vo, Locale locale) throws Exception {
		ModelAndView mav = new ModelAndView();
		String forwardURL = "";

		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		List<?> historyList;
		//List extnlcompList = null;
		List<?> respmanList = null;
		//List<?> cnsdrejctList = null;
		List<?> listCa = null;
		String btnAuthYn = "N";

		try {
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);
			
			// 유저 권한에 상관없이 요청페이지에서 작성/수정시 요청상세페이지로 이동
			if("req".equals(vo.getFwd_gbn()) && !"etc".equals(vo.getTop_role())){
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_d.jsp";
				lom = lawConsultService.detailLawConsultReqman(vo);
				historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)

				ListOrderedMap hisLom = null;
				
				// 보류 버튼을 제어하기 위해 가장 마지막 history 를 구한다
				hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

				// history 에서 가장 큰 hstryNo 값
				mav.addObject("maxHstryNo", hisLom.get("hstry_no"));
			}
			// 그룹장 & 관리자
			else if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultGrpmgr_d.jsp";
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
				historyList = lawConsultService.listLawConsultHistory(vo);
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
				respmanList = lawConsultService.listLawConsultRespman(vo);
				
				if("grpmgr".equals(vo.getTop_role())){
					btnAuthYn = "Y";
				}
				
				String elUserYn = "N"; 
				if(ClmsBoardUtil.searchRole(request, ROLE_8)){
					elUserYn = "Y";
				}
				String isRespman = lawConsultService.findLawConsultRespman(vo);
				
				mav.addObject("isRespman", isRespman);
				mav.addObject("elUserYn",elUserYn);

				// 담당자
			} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultRespman_d.jsp";
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
				historyList = lawConsultService.listLawConsultHistory(vo);
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
				respmanList = lawConsultService.listLawConsultRespman(vo);
				String isRespman = lawConsultService.findLawConsultRespman(vo);
				mav.addObject("isRespman", isRespman);

				// 일반
			} else if ("etc".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_d.jsp";
				lom = lawConsultService.detailLawConsultReqman(vo); // 상세화면에 필요한
																	// 정보

				String regId = StringUtil.bvl((String) lom.get("reg_id"), "");

				// 자신이 등록한 건이 아닌 다른 건에 접근하려는 시도를 방지하기 위한 코드
				// Detail 에서 조회하려는 건의 reg_id와 현재 session 의 user_id를 비교
				if (!(regId.equals(vo.getSession_user_id()))) {
					throw new Exception();
				}

				historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo); // 외부기관
				// respmanList = lawConsultService.listLawConsultRespman(vo); //
				// 담당자

				ListOrderedMap hisLom = null;
				// 보류 버튼을 제어하기 위해 가장 마지막 history 를 구한다
				hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

				// history 에서 가장 큰 hstryNo 값
				mav.addObject("maxHstryNo", hisLom.get("hstry_no"));
			} else {
				throw new Exception();
			}

			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, locale);
			}else{
				// CC(자문 참조자) 정보 조회
				listCa = lawConsultService.listContractAuthreq(vo);
			}

			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));
			
			// 의뢰 내용 <br> 전환
			lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));
			
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("historyList", historyList);
		//mav.addObject("extnlcompList", extnlcompList);
		mav.addObject("btnAuthYn", btnAuthYn);
		mav.addObject("respmanList", respmanList);
		mav.addObject("lom", lom);
		mav.addObject("listCa", listCa);
		mav.addObject("command", form);
		
		return mav;
	}
	
	/**
	 * 자문 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		mav = forwardDetailStaLawConsult(request, form, vo, new RequestContext(request).getLocale());

		return mav;
	}
	

	/**
	 * 자문(표준계약서) 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStaLawConsult(HttpServletRequest request, LawConsultForm form, LawConsultVO vo, Locale locale) throws Exception {

		ModelAndView mav = new ModelAndView();
		String forwardURL = "";

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		List<?> historyList;
		//List extnlcompList = null;
		List<?> respmanList = null;
		//List<?> cnsdrejctList = null;
		String btnAuthYn = "N";

		try {
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);
			
			// 권한에 상관없이 의뢰시에는 의뢰페이지로 이동
			if("req".equals(vo.getFwd_gbn()) && !"etc".equals(vo.getTop_role())){
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_d.jsp";
				lom = lawConsultService.detailLawConsultReqman(vo); // 상세화면에 필요한
																	// 정보

				historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)

				ListOrderedMap hisLom = null;
				// 보류 버튼을 제어하기 위해 가장 마지막 history 를 구한다
				hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

				// history 에서 가장 큰 hstryNo 값
				mav.addObject("maxHstryNo", hisLom.get("hstry_no"));
			}
			// 그룹장 & 관리자
			else if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultGrpmgr_d.jsp";
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
				historyList = lawConsultService.listLawConsultHistory(vo);
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
				respmanList = lawConsultService.listLawConsultRespman(vo);
				
				if("grpmgr".equals(vo.getTop_role())){
					btnAuthYn = "Y";
				}
				
				String elUserYn = "N"; 
				if(ClmsBoardUtil.searchRole(request, ROLE_8)){
					elUserYn = "Y";
				}
				
				mav.addObject("elUserYn",elUserYn);

				// 담당자
			} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultRespman_d.jsp";
				lom = lawConsultService.detailLawConsultGrpmgr(vo);
				historyList = lawConsultService.listLawConsultHistory(vo);
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
				respmanList = lawConsultService.listLawConsultRespman(vo);
				String isRespman = lawConsultService.findLawConsultRespman(vo);
				mav.addObject("isRespman", isRespman);

				// 일반
			} else if ("etc".equals(vo.getTop_role())) {
				forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_d.jsp";
				lom = lawConsultService.detailLawConsultReqman(vo); // 상세화면에 필요한
																	// 정보

				String regId = StringUtil.bvl((String) lom.get("reg_id"), "");

				// 자신이 등록한 건이 아닌 다른 건에 접근하려는 시도를 방지하기 위한 코드
				// Detail에서 조회하려는 건의 reg_id와 현재 session의 user_id를 비교
				if (!(regId.equals(vo.getSession_user_id()))) {
					throw new Exception();
				}

				historyList = lawConsultService.listLawConsultHistoryReqman(vo); // history(이력)
				//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo); // 외부기관
				// respmanList = lawConsultService.listLawConsultRespman(vo); //
				// 담당자

				ListOrderedMap hisLom = null;
				// 보류 버튼을 제어하기 위해 가장 마지막 history를 구한다
				hisLom = (ListOrderedMap) historyList.get(historyList.size() - 1);

				// history에서 가장 큰 hstryNo 값
				mav.addObject("maxHstryNo", hisLom.get("hstry_no"));
			} else {
				throw new Exception();
			}

			if (lom.size() == 0) {
				// returnMessage =
				// messageSource.getMessage("secfw.msg.succ.noResult", null,
				// locale);
			}

			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));
			// 의뢰 내용 <br> 전환
			lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("btnAuthYn", btnAuthYn);
		mav.addObject("historyList", historyList);
		//mav.addObject("extnlcompList", extnlcompList);
		mav.addObject("respmanList", respmanList);
		mav.addObject("lom", lom);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 수정 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_i.jsp";
		String returnMessage = "";
		ModelAndView mav = new ModelAndView();
		ListOrderedMap lom = new ListOrderedMap();
		
		List<?> listCa = null;
		
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		setTopRole(request, form, vo);

		try {
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = lawConsultService.detailLawConsultReqman(vo);

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}

			// 조회결과가 있으면 받아온 데이터 중 수정이 가능한 데이터를 FORM에 세팅한다
			else {
				form.setTitle((String) lom.get("title"));
				form.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
				form.setReg_nm((String) lom.get("reg_nm"));
				form.setReg_dept_nm((String) lom.get("reg_dept_nm"));
				form.setConsult_type((String) lom.get("consult_type"));
				form.setConsult_type_name((String) lom.get("consult_type_name"));
				form.setPrgrs_status((String) lom.get("prgrs_status"));
				form.setReq_reply_dt((String) lom.get("req_reply_dt"));
				// CC(자문 참조자) 정보 조회
				listCa = lawConsultService.listContractAuthreq(vo);
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("otherModify", request.getParameter("otherModify"));
		mav.addObject("command", form);
		mav.addObject("listCa", listCa);
		return mav;
	}
	
	/**
	 * 수정 페이지 이동 - 표준계약서
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_i.jsp";
		String returnMessage = "";
		ModelAndView mav = new ModelAndView();
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		setTopRole(request, form, vo);

		try {
			// 선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = lawConsultService.detailLawConsultReqman(vo);

			// 조회결과가 없는 경우
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
			}

			// 조회결과가 있으면 받아온 데이터 중 수정이 가능한 데이터를 FORM에 세팅한다
			else {
				form.setTitle((String) lom.get("title"));
				form.setCont(StringUtil.bvl((String) lom.get("cont"), ""));
				form.setReg_nm((String) lom.get("reg_nm"));
				form.setReg_dept_nm((String) lom.get("reg_dept_nm"));
				form.setConsult_type((String) lom.get("consult_type"));
				form.setConsult_type_name((String) lom.get("consult_type_name"));
				form.setPrgrs_status((String) lom.get("prgrs_status"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("otherModify", request.getParameter("otherModify"));
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		try {

			setConsultType(form, vo, 0);

			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.modifyLawConsult(vo);
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {

				mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_i.jsp");
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	 * 표준계약서 수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		try {

			setConsultType(form, vo, 0);

			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.modifyLawConsult(vo);
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {

				mav = forwardDetailStaLawConsult(request, form, vo, new RequestContext(request).getLocale());

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_i.jsp");
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	

	/**
	 * 수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyStatusLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		vo.setNow_intnl_prgrs_status(vo.getPrgrs_status());
		setPrgrsStatus(form, vo);

		try {
			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.modifyStatusLawConsult(request, vo);
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {
				// 그룹장 상세화면에서 회신을 누를경우 리스트로 이동한다.

				if (form.getIsGrpmgr().equals("Y") && form.getIsReview().equals("N") && vo.getPrgrs_status().equals("S03")) {
					mav = listLawConsultGrpmgr(request, vo, form);
				} else {
					mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());
				}

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					if ("hold".equals(form.getCheck_prgrs_status())) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.hold", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S03")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.send", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S11") || vo.getPrgrs_status().equals("S07")) {
						returnMessage = messageSource.getMessage("las.page.field.statistics.contlegaladvice.returned", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S09")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.cancel", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					}
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_l.jsp");
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 자문요청 완료 상태값 변경 (미완료-> 완료)
	 * 2013.11.05 자문요청의 완료상태 추가 요청
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView updateCompleteStatusLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LawConsultForm 	form 			= new LawConsultForm();
		LawConsultVO 	vo 				= new LawConsultVO();
		String 			returnMessage 	= messageSource.getMessage("las.msg.succ.complete", null, new RequestContext(request).getLocale());
		ModelAndView 	mav 			= new ModelAndView();
		int result =	0;

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		setTopRole(request, form, vo);

		try {
			result = lawConsultService.updateCompleteStatusLawConsult(vo);
			
			//Parameter changed 2014-07-30 Sungwoo
			if(result == -1){
				returnMessage 	= messageSource.getMessage("las.msg.error.error", null, new RequestContext(request).getLocale());
			}
				
			mav = listLawConsultRespman(request, vo, form);

		} catch (Exception e) {
			e.printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	
	
	
	/**
	 * 표준계약서 수정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyStatusStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		vo.setNow_intnl_prgrs_status(vo.getPrgrs_status());
		setPrgrsStatus(form, vo);

		try {
			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				result = lawConsultService.modifyStatusLawConsult(request, vo);
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {
				// 그룹장 상세화면에서 회신을 누를경우 리스트로 이동한다.

				if (form.getIsGrpmgr().equals("Y") && form.getIsReview().equals("N") && vo.getPrgrs_status().equals("S03")) {
					mav = listStaLawConsultGrpmgr(request, vo, form);
				} else {
					mav = forwardDetailStaLawConsult(request, form, vo, new RequestContext(request).getLocale());
				}

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					if ("hold".equals(form.getCheck_prgrs_status())) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.hold", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S03")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.send", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S11") || vo.getPrgrs_status().equals("S07")) {
						returnMessage = messageSource.getMessage("las.page.field.statistics.contlegaladvice.returned", null, new RequestContext(request).getLocale());
					} else if (vo.getPrgrs_status().equals("S09")) {
						returnMessage = messageSource.getMessage("secfw.msg.succ.cancel", null, new RequestContext(request).getLocale());
					} else {
						returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					}
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawStaConsultReqman_l.jsp");
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}	
	
	
	/**
	 * 이관 - 현재 사용안함
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	
	public ModelAndView transferLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		String returnMessage = null;
		int result = 0;

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, vo);
		// 사용자 정보 세팅
		setPrgrsStatus(form, vo);

		try {

			// 1. 중복 수정 체크
			if (Token.isValid(request)) {
				ListOrderedMap lom;
				lom = lawConsultService.detailLawConsult(vo);
				if (lom.size() == 0) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}

				// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
				vo.setCnslt_amt(StringUtil.bvl((String) lom.get("cnslt_amt"), "0"));
				vo.setWeekdutyyn(StringUtil.bvl((String) lom.get("weekdutyyn"), "Y"));
				vo.setMonthdutyyn(StringUtil.bvl((String) lom.get("monthdutyyn"), "Y"));
				vo.setConf_weekdutyyn(StringUtil.bvl((String) lom.get("conf_weekdutyyn"), "Y"));
				vo.setConf_monthdutyyn(StringUtil.bvl((String) lom.get("conf_monthdutyyn"), "Y"));
				vo.setGrpmgr_re_yn(StringUtil.bvl((String) lom.get("grpmgr_re_yn"), "N"));
				vo.setPub_yn(StringUtil.bvl((String) lom.get("pub_yn"), "Y"));
				vo.setReg_dt_ts(Timestamp.valueOf(StringUtil.bvl(String.valueOf(lom.get("reg_dt")), "")));
				vo.setReg_id(StringUtil.bvl((String) lom.get("reg_id"), ""));
				vo.setReg_nm(StringUtil.bvl((String) lom.get("reg_nm"), ""));
				vo.setReg_telno(StringUtil.bvl((String) lom.get("reg_telno"), ""));
				vo.setReg_dept(StringUtil.bvl((String) lom.get("reg_dept"), ""));
				vo.setReg_dept_nm(StringUtil.bvl((String) lom.get("reg_dept_nm"), ""));
				vo.setDel_yn(StringUtil.bvl((String) lom.get("del_yn"), "N"));
				vo.setRegman_jikgup_nm(StringUtil.bvl((String) lom.get("regman_jikgup_nm"), "N"));
				vo.setReg_operdiv(StringUtil.bvl((String) lom.get("reg_operdiv"), ""));
				vo.setIf_key_no(StringUtil.bvl((String) lom.get("if_key_no"), ""));
				vo.setIf_flag(StringUtil.bvl((String) lom.get("if_flag"), ""));

				result = lawConsultService.transferLawConsult(vo);
			} else {
				// 1-2. 중복 등록시 : result = 100 으로 세팅
				result = 100;
			}

			// 2. 성공시(result > 100) 글보기 페이지로 이동
			if (result > 0) {

				mav = listLawConsultGrpmgr(request, vo, form);

				// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
				if (result == 1) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.transfer", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			}
			// 3. 실패시 Exception 발생
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav.setViewName("/WEB-INF/jsp/las/lawconsulting/LawConsultReqman_l.jsp");
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	 */
	

	/**
	 * 전체삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteAllLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		setUserInfo(form, vo);
		setPrgrsStatus(form, vo);

		try {

			if (Token.isValid(request)) {
				// 중복 삭제가 아닐 시
				result = lawConsultService.deleteAllLawConsult(vo);
			} else {
				// 중복 삭제 시
				result = 100;
			}
			// 성공 시
			if (result > 0) {
				// 삭제 성공 시 검색조건 및 페이지 초기화 후 리스트로 이동
				// setInitFormVO(request, form, vo);
				mav = listLawConsultGrpmgr(request, vo, form);

				// 삭제 성공 & 중복 삭제가 아닐 시 삭제 성공 메시지 세팅
				if (result != 100) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			// 삭제 도중 에러 발생시 에러 메시지 세팅 & 조회하던 상세페이지로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		setUserInfo(form, vo);
		setPrgrsStatus(form, vo);

		try {

			if (Token.isValid(request)) {
				// 중복 삭제가 아닐 시
				result = lawConsultService.deleteLawConsult(vo);
			} else {
				// 중복 삭제 시
				result = 100;
			}
			// 성공 시
			if (result > 0) {
				// 삭제 성공 시 검색조건 및 페이지 초기화 후 리스트로 이동
				// setInitFormVO(request, form, vo);

				setTopRole(request, form, vo);

				// 그룹장 & 관리자
				if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
					mav = listLawConsultGrpmgr(request, vo, form);

					// 담당자
				} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
					mav = listLawConsultRespman(request, vo, form);

					// 일반
				} else if ("etc".equals(vo.getTop_role())) {
					mav = listLawConsultReqman(request, vo, form);
				}

				// 삭제 성공 & 중복 삭제가 아닐 시 삭제 성공 메시지 세팅
				if (result != 100) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			// 삭제 도중 에러 발생시 에러 메시지 세팅 & 조회하던 상세페이지로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	 * 표준계약서 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteStaLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		setUserInfo(form, vo);
		setPrgrsStatus(form, vo);

		try {

			if (Token.isValid(request)) {
				// 중복 삭제가 아닐 시
				result = lawConsultService.deleteLawConsult(vo);
			} else {
				// 중복 삭제 시
				result = 100;
			}
			// 성공 시
			if (result > 0) {
				// 삭제 성공 시 검색조건 및 페이지 초기화 후 리스트로 이동
				// setInitFormVO(request, form, vo);

				setTopRole(request, form, vo);

				// 그룹장 & 관리자
				if ("grpmgr".equals(vo.getTop_role()) || "admin".equals(vo.getTop_role())) {
					mav = listStaLawConsultGrpmgr(request, vo, form);

					// 담당자
				} else if ("respman".equals(vo.getTop_role()) || "normal".equals(vo.getTop_role())) {
					mav = listStaLawConsultRespman(request, vo, form);

					// 일반
				} else if ("etc".equals(vo.getTop_role())) {
					mav = listStaLawConsultReqman(request, vo, form);
				}

				// 삭제 성공 & 중복 삭제가 아닐 시 삭제 성공 메시지 세팅
				if (result != 100) {
					returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			// 삭제 도중 에러 발생시 에러 메시지 세팅 & 조회하던 상세페이지로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			mav = forwardDetailLawConsult(request, form, vo, new RequestContext(request).getLocale());
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command", form);
		return mav;
	}
	

	/**
	 * 자문 유형 popup 화면으로 이동(팝업창)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardRespmanPopupLawConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultRespman_p.jsp";

			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			List resultList = lawConsultService.listLawConsultRespmanPop(vo);

			form.setOrdr_cont(StringUtil.replace(form.getOrdr_cont(), "<br>", "\r\n"));
			
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

	}

	/**
	 * row_cnt를 제외한 form, vo 초기화 FORM 과 VO 의 검색관련(SRCH_*) 변수를 초기화 한다.
	 * 
	 * @param form
	 *            NoticeForm
	 * @param vo
	 *            NoticeVO
	 * @throws Exception
	 */
	private void setInitFormVO(HttpServletRequest request, LawConsultForm form, LawConsultVO vo) throws Exception {

		// form 검색관련 변수 및 페이지 변수 초기화
		form.setSrch_respman_nm(null); // 담당자 이름
		form.setSrch_title(null); // 제목
		form.setSrch_cont(null); // 내용
		form.setSrch_reg_nm(null); // 등록자 이름
		form.setSrch_reg_id(null); // 등록자 id
		form.setSrch_prgrs_status(null); // 진행상태
		form.setSrch_reg_dept(null); // 등록자 부서
		form.setSrch_start_ymd(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-")); // 조회시작날짜
		form.setSrch_end_ymd(DateUtil.formatDate(DateUtil.today(), "-")); // 조회끝날짜
		form.setCurPage("1"); // 현재 페이지 번호

		// vo 검색관련 변수 및 페이지 변수 초기화
		vo.setSrch_respman_nm(null);
		vo.setSrch_title(null);
		vo.setSrch_cont(null);
		vo.setSrch_reg_id(null);
		vo.setSrch_reg_nm(null);
		vo.setSrch_prgrs_status(null);
		vo.setSrch_reg_dept(null);
		vo.setSrch_start_ymd(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
		vo.setSrch_end_ymd(DateUtil.formatDate(DateUtil.today(), "-"));
		vo.setCurPage("1");
	}

	/**
	 * 세션에 담겨있는 User정보를 vo객체에 세팅
	 * 
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setUserInfo(LawConsultForm form, LawConsultVO vo) throws Exception {
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setReg_dept(vo.getSession_dept_cd());
		vo.setReg_intnl_dept(vo.getSession_in_dept_cd());
		vo.setReg_dept_nm(vo.getSession_dept_nm());
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());
		// //회사번호 세팅, 없으면 모바일 번호 세팅
		vo.setReg_telno(vo.getSession_comp_tel());
		// if (vo.getReg_telno().equals("")) {
		// vo.setReg_telno(vo.getSession_mobile());
		// }
		vo.setRegman_jikgup_nm(vo.getSession_jikgup_nm());

		form.setReg_id(vo.getSession_user_id());
		form.setReg_nm(vo.getSession_user_nm());
		form.setReg_dept(vo.getSession_dept_cd());
		form.setReg_dept_nm(vo.getSession_dept_nm());
		form.setReg_intnl_dept(vo.getSession_in_dept_cd());
		form.setReg_operdiv(vo.getSession_blngt_orgnz());
		// //회사번호 세팅, 없으면 모바일 번호 세팅
		form.setReg_telno(vo.getSession_comp_tel());
		// if (form.getReg_telno().equals("")) {
		// form.setReg_telno(vo.getSession_mobile());
		// }
		form.setRegman_jikgup_nm(form.getSession_jikgup_nm());
	}

	/**
	 * 자문유형 세팅 mode 0 - consult_type mode 1 - srch_consult_type 화면에서 넘어온
	 * ConsultType혹은 검색할 ConsultType을 ',' 토큰으로 구분하여 리스트에 저장한다.
	 * 
	 * @param form
	 * @param vo
	 * @param mode
	 * @throws Exception
	 */
	private void setConsultType(LawConsultForm form, LawConsultVO vo, int mode) throws Exception {
		String str = "";

		if (mode == 0)
			str = form.getConsult_type();
		else if (mode == 1)
			str = form.getSrch_consult_type();

		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, ",");
			List consult_type_list = new ArrayList();

			while (st.hasMoreElements()) {
				consult_type_list.add(st.nextToken());
			}

			if (mode == 0)
				vo.setConsult_type_list(consult_type_list);
			else if (mode == 1)
				vo.setSrch_consult_type_list(consult_type_list);
		}

	}

	/**
	 * 화면에서 넘어온 진행상태 값을 vo의 진행상태 변수 prgrs_status 에 세팅한다.
	 * 
	 * tempSaveReqman - 임시저장(의뢰인) - S01 send - 의뢰 - S02 reply - 회신 - S03 resend
	 * - 재의뢰 - S04 hold - 보류 - S05 refer - 반려 - S07 undecided - 미결 - S08
	 * tempSave - 임시저장(법무팀) - S09 referGrpmgr - 법무장반려 - S11 transfer - 이관 - S12
	 * 
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setPrgrsStatus(LawConsultForm form, LawConsultVO vo) throws Exception {
		String prgrs_status = form.getCheck_prgrs_status();
		prgrs_status = StringUtil.convertHtmlTochars(prgrs_status);

		if ("tempSaveReqman".equals(prgrs_status)) { // 임시저장(의뢰인)
			vo.setPrgrs_status("S01");
		} else if ("send".equals(prgrs_status)) { // 의뢰
			vo.setPrgrs_status("S02");
		} else if ("reply".equals(prgrs_status)) { // 회신
			vo.setPrgrs_status("S03");
		} else if ("resend".equals(prgrs_status)) { // 재의뢰
			vo.setPrgrs_status("S04");
		} else if ("hold".equals(prgrs_status)) { // 보류(의뢰인)
			vo.setPrgrs_status("S05");
		} else if ("refer".equals(prgrs_status)) { // 반려
			vo.setPrgrs_status("S07");
		} else if ("undecided".equals(prgrs_status)) { // 미결(법무팀)
			vo.setPrgrs_status("S08");
		} else if ("tempSave".equals(prgrs_status)) { // 임시저장(법무팀(그룹장 혹은 담당자))
			vo.setPrgrs_status("S09");
		} else if ("referGrpmgr".equals(prgrs_status)) {// 법무장 반려
			vo.setPrgrs_status("S11");
		} else if ("transfer".equals(prgrs_status)) {// 이관
			vo.setPrgrs_status("S12");
		} else {
			vo.setPrgrs_status(form.getPrgrs_status());
		}
	}

	/**
	 * 화면에서 넘어온 외부기관 정보를 vo객체의 해당 List 변수에 세팅
	 * 
	 * @param extnl_list
	 * @return
	 * @throws Exception
	 
	private List setExtnlList(String[] extnl_list, LawConsultVO vo) throws Exception {
		List extnlList = new ArrayList();
		Map map = null;
		StringTokenizer st;
		// int cnslt_amt = 0;
		// BigDecimal cnslt_amt = new BigDecimal(0);
		long cnslt_amt = 0;

		if (extnl_list != null) {
			for (int i = 0; i < extnl_list.length; i++) {
				extnl_list[i] = StringUtil.convertHtmlTochars(extnl_list[i]);
				st = new StringTokenizer(extnl_list[i], "^");

				map = new HashMap();
				map.put("comp_nm", st.nextToken());
				map.put("cnslt_amt", st.nextToken());
				map.put("crrncy_unit", st.nextToken());
				map.put("seqno", Integer.toString(i));
				extnlList.add(map);

				cnslt_amt += Long.parseLong((String) map.get("cnslt_amt"));

				// cnslt_amt = cnslt_amt.add(new
				// BigDecimal((String)map.get("cnslt_amt")));
				// cnslt_amt +=
				// Integer.parseInt(String.valueOf(map.get("cnslt_amt")));
			}
		}
		vo.setCnslt_amt(String.valueOf(cnslt_amt));
		// vo.setCnslt_amt(Integer.toString(cnslt_amt));
		return extnlList;
	}
	 */
	
	/**
	 * 권한에 따른 vo의 top_role 변수 값 세팅.
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setTopRole(HttpServletRequest request, LawConsultForm form, LawConsultVO vo) throws Exception {

		if (ClmsBoardUtil.searchRole(request, ROLE_1) || ClmsBoardUtil.searchRole(request, ROLE_8)) { // 그룹장
			vo.setTop_role("grpmgr");
			form.setTop_role("grpmgr");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_2) || ClmsBoardUtil.searchRole(request, ROLE_9)) { // 담당자
			vo.setTop_role("respman");
			form.setTop_role("respman");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_0) || ClmsBoardUtil.searchRole(request, ROLE_10)) { // 관리자
			vo.setTop_role("admin");
			form.setTop_role("admin");
		} else if (ClmsBoardUtil.searchRole(request, ROLE_5) || ClmsBoardUtil.searchRole(request, ROLE_7)) { // etc
			vo.setTop_role("etc");
			form.setTop_role("etc");
		} else {
			// 해당 권한이 아닐 시 Exception을 날린다
			throw new Exception();
		}
	}

	/**
	 * 조회한 ROW의 값을 VO에 세팅 (NOT NULL 값)
	 * 
	 * @param lom
	 * @param vo
	 */
	private void setVO(ListOrderedMap lom, LawConsultVO vo, LawConsultForm form) {
		vo.setCnslt_no(StringUtil.bvl((String) lom.get("cnslt_no"), ""));
		vo.setHstry_no(Integer.parseInt(StringUtil.bvl(String.valueOf(lom.get("hstry_no")), "0")));
		vo.setCnslt_pos(Integer.parseInt(StringUtil.bvl(String.valueOf(lom.get("cnslt_pos")), "0")));
		vo.setCnslt_srt(Integer.parseInt(StringUtil.bvl(String.valueOf(lom.get("cnslt_srt")), "0")));
		vo.setDmstfrgn_gbn(StringUtil.bvl((String) lom.get("dmstfrgn_gbn"), ""));

		// 재의뢰가 아닐시 title 값 set
		if (!("resend".equals(form.getCheck_prgrs_status()))) {
			if (!("tempSaveReqman".equals(form.getCheck_prgrs_status()) && form.getHstry_no() > 0))
				vo.setTitle(StringUtil.bvl((String) lom.get("title"), ""));
		}
		vo.setNow_intnl_prgrs_status(StringUtil.bvl((String) lom.get("intnl_prgrs_status"), ""));
		vo.setExtnl_prgrs_status(StringUtil.bvl((String) lom.get("extnl_prgrs_status"), ""));
		vo.setCnslt_amt(StringUtil.bvl((String) lom.get("cnslt_amt"), "0"));
		vo.setWeekdutyyn(StringUtil.bvl((String) lom.get("weekdutyyn"), "Y"));
		vo.setMonthdutyyn(StringUtil.bvl((String) lom.get("monthdutyyn"), "Y"));
		vo.setConf_weekdutyyn(StringUtil.bvl((String) lom.get("conf_weekdutyyn"), "Y"));
		vo.setConf_monthdutyyn(StringUtil.bvl((String) lom.get("conf_monthdutyyn"), "Y"));
		vo.setGrpmgr_re_yn(StringUtil.bvl((String) lom.get("grpmgr_re_yn"), "N"));
		vo.setSolo_yn(StringUtil.bvl((String) lom.get("solo_yn"), "3"));
		vo.setPub_yn(StringUtil.bvl((String) lom.get("pub_yn"), "Y"));

		if (!("resend".equals(form.getCheck_prgrs_status()))) {
			vo.setReg_dt(StringUtil.bvl(String.valueOf(lom.get("reg_dt")), ""));
		}

		vo.setReg_id(StringUtil.bvl((String) lom.get("reg_id"), ""));
		vo.setReg_nm(StringUtil.bvl((String) lom.get("reg_nm"), ""));
		vo.setReg_telno(StringUtil.bvl((String) lom.get("reg_telno"), ""));
		vo.setReg_dept(StringUtil.bvl((String) lom.get("reg_dept"), ""));
		vo.setReg_dept_nm(StringUtil.bvl((String) lom.get("reg_dept_nm"), ""));
		vo.setDel_yn(StringUtil.bvl((String) lom.get("del_yn"), "N"));
		vo.setRegman_jikgup_nm(StringUtil.bvl((String) lom.get("regman_jikgup_nm"), "N"));
		vo.setReg_operdiv(StringUtil.bvl((String) lom.get("reg_operdiv"), ""));
	}
	
	
	/**
	* 자문검토 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listLawconsultExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			
			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, form);

			// 등록자 정보 세션에서 받아와 vo에 세팅
			setUserInfo(form, vo);
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index("1");		//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index("1000");		//현재 페이지의 마지막 게시물번호  set
			
						
			List<?> resultList = null;
			/*********************************************************
			 * Service
			**********************************************************/
			
			// 엑셀목록 호출 페이지 구분
			// req 		: 의뢰목록 
			// grpmgr 	: 자문리뷰 목록
			
			if(!"req".equals(vo.getFwd_gbn().toString())){
				resultList = lawConsultService.listLawConsultGrpmgr(vo);
			}else{
				resultList = lawConsultService.listLawConsultReqman(vo);
			}
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String		fileNm			= "";
			String[]	sheetNmAry		= new String[1];
			String[]	titleInfo		= new String[1];
			
			String[]	subTitleInfo	= null;
			String[]	columnInfo		= null;
			short[]		columnAlign		= null;
			
			//메시지 처리 - 제목
			String title = messageSource.getMessage("las.page.field.lawconsult.title", null, new RequestContext(request).getLocale());
			//메시지 처리 - 담당부서
			String reg_dept_nm = messageSource.getMessage("las.page.field.lawconsideration.dept", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰자
			String reg_nm = messageSource.getMessage("las.page.field.contractManager.requBy", null, new RequestContext(request).getLocale());
			//메시지 처리 - 검토자
			String respman_nm = messageSource.getMessage("las.page.field.manageCommon.srchLasCnsdmanNm", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰일
			String reg_dt_date = messageSource.getMessage("las.page.field.lawconsult.reg_dt", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰상태
			String prgrs_status_name = messageSource.getMessage("las.page.field.lawconsult.status", null, new RequestContext(request).getLocale());
			//회신요청일자
			String req_reply_dt = messageSource.getMessage("clm.page.field.contract.request.returndt", null, new RequestContext(request).getLocale());
			//메시지 처리 - 검토완료여부
			String complete_status = messageSource.getMessage("las.page.field.lawconsult.completYnList", null, new RequestContext(request).getLocale());
			//검토완료 여부 프로퍼티의 <BR> 태그 삭제
			complete_status = complete_status.replace("<BR>", " ");
			
			
			// 호출 페이지 구분 
			
			if(!"req".equals(vo.getFwd_gbn().toString())){
			
				// 리뷰목록
				
				fileNm			= (String)messageSource.getMessage("las.page.field.lawconsulting.lawAdvReview", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
				sheetNmAry[0]	= (String)messageSource.getMessage("las.page.field.lawconsulting.lawAdvReview", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
				//메시지 처리 - 의뢰별 목록
				titleInfo[0]	= (String)messageSource.getMessage("las.page.field.lawconsulting.lawAdvReview", null, new RequestContext(request).getLocale());
				subTitleInfo	= new String[]{title, reg_dept_nm, reg_nm, respman_nm, reg_dt_date, prgrs_status_name, complete_status, req_reply_dt};
				columnInfo		= new String[]{"title", "reg_dept_nm", "reg_nm", "respman_nm", "reg_dt_date", "prgrs_status_name", "complete_status","req_reply_dt"};
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER};
			} else {
				
				// 의뢰목록 
				
				fileNm			= (String)messageSource.getMessage("las.page.field.lawconsulting.lawReqAdv", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
				sheetNmAry[0]	= (String)messageSource.getMessage("las.page.field.lawconsulting.lawReqAdv", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
				//메시지 처리 - 의뢰별 목록
				titleInfo[0]	= (String)messageSource.getMessage("las.page.field.lawconsulting.lawReqAdv", null, new RequestContext(request).getLocale());
				subTitleInfo	= new String[]{title, reg_nm, respman_nm, reg_dt_date, prgrs_status_name, req_reply_dt };
				columnInfo		= new String[]{"title", "reg_nm", "respman_nm", "reg_dt_date", "prgrs_status_name", "req_reply_dt"};
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER};
			}
	        
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수			

			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop				
				
				excel.setBold(true);
				excel.setFontColor(ExcelBuilder.COLOR_BLACK);
				excel.setFontName("맑은 고딕");
				excel.setFontSize((short)11);
				excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
				excel.setBorder(true);
				
				excel.addTitleRow(i, subTitleInfo);
				excel.setDefaultStyle();
				
				excel.setAlign(columnAlign);
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
				excel.setBorder(new boolean[]{true});
				
				excel.addRows(i, columnInfo, resultList);
				
				excel.setDefaultStyle();
			
			}			
			
			excel.download(fileNm, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	/**
	 * 자문 리스트 조회 - 그룹장
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listLawConsultStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawconsultStatistics_p_l.jsp";
		ModelAndView mav = new ModelAndView();
		PageUtil pageUtil = new PageUtil();
		String returnMessage = "";

		List<?> resultList;

		try {
			
			LawConsultForm form = new LawConsultForm();
			LawConsultVO vo = new LawConsultVO();
			
			bind(request, form);
			bind(request, vo);
			
			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, form);

			// 등록자 정보 세션에서 받아와 vo에 세팅
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);
			
			// 페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			setConsultType(form, vo, 1);
			
			// 리스트 조회 서비스 호출
			resultList = lawConsultService.listLawConsultGrpmgr(vo);

			form.setLawconsult_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setGroup();
			}

			// 메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}
		
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(form.getSrch_prgrs_status())); // 진행상태

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 자문 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailLawConsultStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String forwardURL = "";

		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		List<?> historyList;
		//List extnlcompList = null;
		List<?> respmanList = null;
		//List<?> cnsdrejctList = null;
		List<?> listCa = null;
		String btnAuthYn = "N";
		
		LawConsultForm form = new LawConsultForm();
		LawConsultVO vo = new LawConsultVO();
		
		
		try {
			
			bind(request, form);
			bind(request, vo);
			
			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, form);

			// 등록자 정보 세션에서 받아와 vo에 세팅
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);
			
			
			setUserInfo(form, vo);

			// 권한에 따른 top_role 값 세팅
			setTopRole(request, form, vo);
			
		
			forwardURL = "/WEB-INF/jsp/las/lawconsulting/LawConsultStatistics_p_d.jsp";
			lom = lawConsultService.detailLawConsultGrpmgr(vo);
			historyList = lawConsultService.listLawConsultHistory(vo);
			//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
			respmanList = lawConsultService.listLawConsultRespman(vo);
			
			if("grpmgr".equals(vo.getTop_role())){
				btnAuthYn = "Y";
			}
			
			String elUserYn = "N"; 
			if(ClmsBoardUtil.searchRole(request, ROLE_8)){
				elUserYn = "Y";
			}
			String isRespman = lawConsultService.findLawConsultRespman(vo);
			
			mav.addObject("isRespman", isRespman);
			mav.addObject("elUserYn",elUserYn);

			

			if (lom.size() == 0) {
//				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, "en");
			}else{
				// CC(자문 참조자) 정보 조회
				listCa = lawConsultService.listContractAuthreq(vo);
			}

			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));
			
			// 의뢰 내용 <br> 전환
			lom.put("cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cont"),"")));
			
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}

		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("historyList", historyList);
		mav.addObject("btnAuthYn", btnAuthYn);
		mav.addObject("respmanList", respmanList);
		mav.addObject("lom", lom);
		mav.addObject("listCa", listCa);
		mav.addObject("command", form);
		
		return mav;
	}
	
	
}