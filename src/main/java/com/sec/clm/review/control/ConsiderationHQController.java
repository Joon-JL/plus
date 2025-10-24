package com.sec.clm.review.control;
/**
 * Project Name : 법무시스템
 * File Name : ConsiderationHQController.java
 * Description : HQ 검토 Controller
 * Author : 
 * Date : 2014.04
 * Copyright : 2014 by SAMSUNG. All rights reserved.
 */

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.service.UserService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.review.dto.ConsiderationHQForm;
import com.sec.clm.review.dto.ConsiderationHQVO;
import com.sec.clm.review.dto.ConsultationForm;
import com.sec.clm.review.dto.ConsultationVO;
import com.sec.clm.review.service.ConsiderationHQService;
import com.sec.clm.review.service.ConsiderationService;
import com.sec.common.clmscom.service.CLMSCommonService;

public class ConsiderationHQController extends CommonController  {
	
	/**
	 * ROLE 구분을 위한 변수
	 */
	static String HQ01 = "HQ01"; // HQ어드민
	static String HQ02 = "HQ02"; // HQ검토변호사
	
	/**
	 * Consideration 서비스
	 */
	private ConsiderationService considerationService;
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}

	/**
	 * ConsiderationHQ 서비스
	 */
	private ConsiderationHQService considerationHQService;
	public void setConsiderationHQService(ConsiderationHQService considerationHQService) {
		this.considerationHQService = considerationHQService;
	}
		
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalService esbApprovalService;
	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	/**
	 * HQ 의뢰내역 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listConsiderationHQ(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			// boolean viewOldContractBtnFlag = false; //사업부이관 등록버튼 view 여부
			String forwardURL    = "";
			String returnMessage = "";
		
			ConsiderationHQForm form 	= null;	
			ConsiderationHQVO   vo   	= null;	
			PageUtil 	pageUtil   	= null;
			List   		resultList 	= null;
			String 	 	topRole		= "";		// 사용자 Role
			boolean		elUserlYn	= false;
			String 		sElComp 	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ConsiderationHQForm();
			vo 	 = new ConsiderationHQVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
/*			if(form.getSrch_start_dt() == null || "".equals(form.getSrch_start_dt())){
				form.setSrch_start_dt(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
				vo.setSrch_start_dt(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
			}
			if(form.getSrch_end_dt() == null || "".equals(form.getSrch_end_dt())){
				form.setSrch_end_dt(DateUtil.formatDate(DateUtil.today(), "-"));
				vo.setSrch_end_dt(DateUtil.formatDate(DateUtil.today(), "-"));
			}*/
			
			sElComp = StringUtil.bvl(form.getsElComp(), "");  // 회사 선택 조회 조건(전자 변호사일 경우에만 나타나게 된다.)
			
			//Cross-site Scripting 방지
			form.setSrch_type_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_type_cd(),"")));
			//form.setSrch_req_title(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_req_title(),"")));
			form.setSrch_req_title(StringUtil.bvl(form.getSrch_req_title(),""));
			form.setSrch_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_orgnz(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_reqman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reqman_id(),"")));
			form.setSrch_reqman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reqman_nm(),"")));
			
			form.setSrch_hq_reqman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_hq_reqman_nm(),"")));
						
			form.setSrch_prgrs_status_hq(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prgrs_status_hq(),"")));
			form.setSrch_prgrs_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prgrs_status(),"")));
			form.setSrch_owner_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_owner_dept(),"")));
			form.setSrch_law_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_law_status(),"")));
			form.setSrch_ip_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_ip_status(),"")));
			form.setSrch_respman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_respman_id(),"")));
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_respman_nm(),"")));
			form.setSrch_biz_depth(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_biz_depth(),"")));
			form.setSrch_cnclsnpurps(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_cnclsnpurps(),"")));
			form.setSrch_cntrt_oppnt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_cntrt_oppnt_nm(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			form.setSrch_closed_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_closed_yn(),"")));
			// 전자 변호사일 경우 각 회사를 조회 할 수 있게 처리 됨.
			form.setsElComp(sElComp);
			vo.setSElComp(sElComp);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("HQ01")){
				topRole = "HQ01";
			}else if(tmpSessionRoleList.contains("HQ02")){
				topRole = "HQ02";
			}

			elUserlYn = true;
			form.setsSel_grd("Y");		
			
			if(!"".equals(vo.getsElComp())){
				form.setSession_auth_comp_cd(form.getsElComp());
				vo.setSession_auth_comp_cd(vo.getsElComp());
			} else {
				vo.setSession_auth_comp_cd("");
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			vo.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
		
			//권한직위
			form.setTop_role(topRole);
			vo.setTop_role(topRole);
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set
			
			vo.setStat_flag(StringUtil.bvl((String)request.getParameter("stat_flag"),"")); 	//좌측메뉴에서 받은 진행상태 플래그
			vo.setDmstfrgn(StringUtil.bvl((String)request.getParameter("dmstfrgn"),""));	//국내(H), 해외(F)
			form.setStat_flag(vo.getStat_flag());
			form.setDmstfrgn(vo.getDmstfrgn());
			
			if(vo.getPage_flag() == null || vo.getPage_flag().equals("")) {
				vo.setPage_flag("1");
				form.setPage_flag("1");
			}
/*			
			if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RA01") || tmpSessionRoleList.contains("RA02")){
				viewOldContractBtnFlag = true;
			}		*/
			
			if(("1".equals(vo.getPage_flag()) || "2".equals(vo.getPage_flag())) && "".equals(StringUtil.bvl(vo.getSrch_reqman_nm(),"")) && "HQ02".equals(topRole)) {
				form.setSrch_hq_reqman_nm(vo.getSession_user_nm());
				vo.setSrch_hq_reqman_nm(vo.getSession_user_nm());
			}
			
			form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
			vo.setList_mode(form.getList_mode());
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationHQService.listConsiderationHQ(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap) resultList.get(0);
			

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

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("elUserlYn", elUserlYn);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listConsideration() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listConsideration() Throwable !!");
		}
	}
	
	/**
	* HQ 검토 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listConsiderationExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			// 권한 체크
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;			
			
			CommonForm cmd =  (CommonForm)request.getAttribute("command");			
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ConsiderationHQForm form = new ConsiderationHQForm();
			ConsiderationHQVO vo 	 = new ConsiderationHQVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Page  setting
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index("1");	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index("300");		//현재 페이지의 마지막 게시물번호  set
			
			vo.setPage_flag("2");
			form.setPage_flag("2");					
			
			if(!"".equals(vo.getsElComp())){
				form.setSession_auth_comp_cd(form.getsElComp());
				vo.setSession_auth_comp_cd(vo.getsElComp());
			} else {
				vo.setSession_auth_comp_cd("");
			}
			
			List resultList = null;
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationHQService.listConsiderationHQ(vo);
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String		fileNm			= "";
			String[]	sheetNmAry		= new String[1];
			String[]	titleInfo		= new String[1];
			
			String[]	subTitleInfo	= null;
			String[]	columnInfo		= null;
			short[]		columnAlign		= null;
			
			//메시지 처리 - 법인
			String co_ltd = messageSource.getMessage("las.page.field.contractmanager.consideration.company_nm", null, new RequestContext(request).getLocale());
			
			//메시지 처리 - 의뢰명
			String req_title = messageSource.getMessage("clm.page.field.manage.listManageExcel03", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약상대방
			String cntrt_oppnt_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel10", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰부서
			String req_dept_nm = messageSource.getMessage("las.page.field.contractmanager.consideration.req_dept_nm", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰자
			String reqman_nm = messageSource.getMessage("las.page.field.contractmanager.consideration.reqman_nm", null, new RequestContext(request).getLocale());
			//메시지 처리 - 1차 의뢰일
			String req_dt = messageSource.getMessage("clm.page.field.manage.listManageExcel06", null, new RequestContext(request).getLocale());
			//메시지 처리 - 검토자
			String law_respman_nm = messageSource.getMessage("las.page.field.contractManager.reviewer", null, new RequestContext(request).getLocale());
			//메시지 처리 - 상태
			String law_status_nm = messageSource.getMessage("las.page.field.contractManager.state", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰상태
			String prgrs_status_nm = messageSource.getMessage("las.page.field.contractmanager.consideration.prgrs_status", null, new RequestContext(request).getLocale());			
			
			//메시지 처리 -  HQ 검토자
			String hq_reviewer = "HQ " + messageSource.getMessage("las.page.field.contractManager.reviewer", null, new RequestContext(request).getLocale());
			//메시지 처리 -HQ 검토 상태
			String hq_status = "HQ " + messageSource.getMessage("las.page.field.contractManager.state", null, new RequestContext(request).getLocale());
			
			fileNm			= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
			sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
			//메시지 처리 - 의뢰별 목록
			titleInfo[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale());
		
			// 계약 타입 적용된 엑셀 양식
			//subTitleInfo	= new String[]{"Type", req_title, cntrt_oppnt_nm, req_dept_nm, reqman_nm, req_dt, law_respman_nm, law_status_nm, prgrs_status_nm, hq_reviewer,hq_status};
			//columnInfo		= new String[]{"type_nm_1","req_title", "cntrt_oppnt_nm", "req_dept_nm_1", "reqman_nm", "req_dt_1", "law_respman_nm", "law_status_nm_1", "prgrs_status_nm_1","HQ_RESPMAN_NM","HQ_CNSD_STATUS_NM"};
			
			// HQ01 일 경우 각 법인 명 적용된 엑셀 양식			
		/*	if(tmpSessionRoleList.contains("HQ01")){*/
				subTitleInfo	= new String[]{co_ltd, req_title, cntrt_oppnt_nm, req_dept_nm, reqman_nm, req_dt, law_respman_nm, law_status_nm, prgrs_status_nm, hq_reviewer,hq_status};
				columnInfo		= new String[]{"comp_nm","req_title", "cntrt_oppnt_nm", "req_dept_nm_1", "reqman_nm", "req_dt_1", "law_respman_nm", "law_status_nm_1", "prgrs_status_nm_1","HQ_RESPMAN_NM","HQ_CNSD_STATUS_NM"};							
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};
			
		/*	}	else {
				
				subTitleInfo	= new String[]{req_title, cntrt_oppnt_nm, req_dept_nm, reqman_nm, req_dt, law_respman_nm, law_status_nm, prgrs_status_nm, hq_reviewer,hq_status};
				columnInfo		= new String[]{"req_title", "cntrt_oppnt_nm", "req_dept_nm_1", "reqman_nm", "req_dt_1", "law_respman_nm", "law_status_nm_1", "prgrs_status_nm_1","HQ_RESPMAN_NM","HQ_CNSD_STATUS_NM"};							
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};
						
			}			*/
				
	        
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
	 * HQ 계약검토 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String authYN    	 = "";
			String page_div		 = "";
			
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			
			ConsiderationHQForm hqForm = null;	
			ConsiderationHQVO hqVo = null;	
			
			PageUtil 	pageUtil	= null;
			List   	 	resultList 	= null;
			
			String 	 	topRole		= "";		// 사용자 Role
			String	 	hasH0102		=  "N";		// 사용자 Role
			
			ListOrderedMap lomMn= new ListOrderedMap();
			ListOrderedMap lomVc= new ListOrderedMap();
			
			ExecutionForm exForm    = null;
			ExecutionVO   exVo      = null;
			List exList				= null;
			List cnsdList			= null;	// 담당자의견 및 반려 이력
			List relationList 		= null; //연관계약 list
			
			ArrayList review 		= null;
			ArrayList approve 		= null;
			ArrayList info 			= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_d.jsp";		// 일반의뢰

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        form = new ConsultationForm();
			vo   = new ConsultationVO();
			exForm = new ExecutionForm();
			exVo = new ExecutionVO();
			
			hqForm = new ConsiderationHQForm();
			hqVo = new ConsiderationHQVO();
						
			bind(request, form);
			bind(request, vo);
			bind(request, exForm);
			bind(request, exVo);
			bind(request, hqForm);
			bind(request, hqVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, exForm);
			COMUtil.getUserAuditInfo(request, exVo);
			COMUtil.getUserAuditInfo(request, hqForm);
			COMUtil.getUserAuditInfo(request, hqVo);
			
			HashMap 	   roleListMap = new HashMap();
			ListOrderedMap roleListLom = null;
			
			roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
			roleListMap.put("user_id", StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
			
			List 	  roleList 	   = userService.listUserRole(roleListMap);
			ArrayList userRoleList = new ArrayList();
			
			if(roleList != null && roleList.size() > 0){
			    for(int idx = 0; idx < roleList.size(); idx++){
			    	roleListLom = (ListOrderedMap)roleList.get(idx);
			        userRoleList.add((String)roleListLom.get("role_cd"));
			    }
			}
			
			if(userRoleList.contains("HQ01")){
				topRole = "HQ01";
						
					if(userRoleList.contains("HQ02")){
						hasH0102 = "Y";
					}			
			
			}else if(userRoleList.contains("HQ02")){
				topRole = "HQ02";
			}		
			
/*			if(userRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(userRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(userRoleList.contains("RA02")){
				topRole = "RA02";
			}*/
			
/*			if(userRoleList.contains("RB01")){//2013.04 jnam 전자검토자 추가
				topRole = "RB01";
			}*/
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			vo.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			vo.setTop_role(topRole);
			
			if(vo.getPage_flag() == null || vo.getPage_flag().equals("")) {
				vo.setPage_flag("1");
				form.setPage_flag("1");
			}
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));	// 그룹장 화면 구분
			
			if(form.getPage_div().equals("RESP")){						// 그룹장이 담당자 검토 실행
				form.setTop_role("RA02");
			}else if(form.getPage_div().equals("") && userRoleList.contains("RA01") && userRoleList.contains("RA02")){	// 그룹장, 담당자 권한이 있고 초기화면일 경우
				form.setPage_div("APPR");
			}
			
			//정검토부서
			form.setMn_cnsd_dept(vo.getMn_cnsd_dept());
			//부검토부서
			form.setVc_cnsd_dept(vo.getVc_cnsd_dept());
			//예정본의뢰여부
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			vo.setStat_flag(StringUtil.bvl((String)request.getParameter("stat_flag"),"")); 	//좌측메뉴에서 받은 진행상태 플래그
			vo.setDmstfrgn(StringUtil.bvl((String)request.getParameter("dmstfrgn"),""));	//국내(H), 해외(F)
			form.setStat_flag(vo.getStat_flag());
			form.setDmstfrgn(vo.getDmstfrgn());
			vo.setGbn_last(StringUtil.bvl(vo.getGbn_last(),"")); 	//최종본 회신 구분
			vo.setUser_id(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			//authYN = checkAuth(vo, "[B99]", "S", "", form.getPage_div());	//기타
			authYN = "Y";
			if(!"Y".equals(authYN)) {
				forwardURL="/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";
				mav.setViewName(forwardURL);
				
				form.setReturn_title(messageSource.getMessage("las.page.field.consideration.detailConsideration01", null, new RequestContext(request).getLocale())); //권한없음
				form.setReturn_message(messageSource.getMessage("las.page.field.consideration.detailConsideration02", null, new RequestContext(request).getLocale()));//본페이지의 권한이 없습니다.
				mav.addObject("command", form);
			}else{
	
				List listDc = considerationService.detailConsideration(vo);
				ListOrderedMap lomRq = null;
				if(listDc != null && listDc.size() > 0){
					lomRq= (ListOrderedMap)listDc.get(0);
				}else{
					lomRq = new ListOrderedMap();
				}
				
				lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
				lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
				lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));
				
				vo.setCnsdreq_id(StringUtil.bvl((String)lomRq.get("cnsdreq_id"),""));
				vo.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
				lomRq.put("apbt_memo_dp", StringUtil.convertEnterToBR((String)lomRq.get("apbt_memo")));	// 승인자_메모
				
				ArrayList lomResp = listRespman(vo);									// 담당자 List
				//로그인한 id로 검토담당자인지 체크
				String respYn = "N";
				if(null != lomResp && lomResp.size() > 0 && form.getTop_role().equals("RA02")){
					ListOrderedMap lomR = null;
					for(int i = 0; i < lomResp.size(); i++){
						lomR = (ListOrderedMap)lomResp.get(i);
						if(form.getSession_user_id().equals(lomR.get("LIST_RESPMAN_ID"))){
							respYn = "Y";
						}
					}
				}
				
				ListOrderedMap lomDcd= considerationService.contractDeptcnsdrejct(vo);	// B.계약별_부서검토반려
				lomDcd.put("apbt_memo_dp", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_memo")));	// 승인자_메모
				lomDcd.put("apbt_opnn_dp", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_opnn")));	// 승인의견
				lomDcd.put("rejct_opnn_dp", StringUtil.convertEnterToBR((String)lomDcd.get("rejct_opnn")));	// 반려의견
				lomDcd.put("apbt_memo", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_memo")));	// 승인자_메모
				lomDcd.put("apbt_opnn", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_opnn")));	// 승인의견
				lomDcd.put("rejct_opnn", StringUtil.convertEnterToBR((String)lomDcd.get("rejct_opnn")));	// 반려의견
				
				form.setCnsd_status((String)lomDcd.get("cnsd_status"));
				//------------------------------------------------------------------------------------------------------------------------
				
				vo.setCntrt_oppnt_nm(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_nm"),""));
				
				// 개행문자를 <BR> 로 전환
				lomRq.put("cnsd_demnd_cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
				lomRq.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("pshdbkgrnd_purps"),"")));
				
				lomRq.put("plndbn_chge_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("plndbn_chge_cont"),"")));
				lomRq.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cntrt_untprc_expl"),"")));
				
				
				// auto_apbt_yn 
				lomRq.put("auto_apbt_yn", StringUtil.bvl((String)lomRq.get("auto_apbt_yn"),"Y"));
				
				//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
	
				vo.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
				vo.setCnclsnpurps_bigclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_bigclsfcn"),""));
				vo.setCnclsnpurps_midclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_midclsfcn"),""));
				
				// 관련자 List
				ArrayList lomRm = null;
				ListOrderedMap mapRm = null;
				
				if(listDc != null && listDc.size() > 0){
					String[] relMan = new String[listDc.size()];
					String cntrtInfoGbn = "";
					
					for(int i=0; i<listDc.size(); i++) {
						mapRm = (ListOrderedMap)listDc.get(i);
						relMan[i] = (String)mapRm.get("cntrt_id");
						cntrtInfoGbn = (String)mapRm.get("cntrt_info_gbn");
					}
					
					vo.setMaster_cntrt_ids(relMan);
					lomRm = listRelationman(vo);													// 관련자 List
					
					if(!StringUtil.isNull(cntrtInfoGbn) && (cntrtInfoGbn.equals("C05402") || cntrtInfoGbn.equals("C05403"))) {
						forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_d.jsp";	// (구)법무시스템 검토 데이터 조회 페이지로 변경
					}
				}
				
				//====================================
				//검토의견
				//====================================
				// 2012.03.02 기대효과, 지불/수분조건, 기타주요사항 개행문자 출력 수정 modified by hanjihoon
				// 기대효과
				lomRq.put("antcptnefct_dp", StringUtil.convertEnterToBR((String)lomRq.get("antcptnefct")));
				// 지불/수분조건
				lomRq.put("payment_cond_dp", StringUtil.convertEnterToBR((String)lomRq.get("payment_cond")));
				// 기타주요사항
				lomRq.put("etc_main_cont_dp", StringUtil.convertEnterToBR((String)lomRq.get("etc_main_cont")));
				
				// 책임한도
				lomRq.put("oblgt_lmt_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("oblgt_lmt"),"")));				
				// Specila Condition
				lomRq.put("spcl_cond_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));
				
				// 준거법 상세
				lomRq.put("loac_etc_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
				// 분쟁해결방법상세 
				lomRq.put("dspt_resolt_mthd_det_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
				// 비밀유지기간
				lomRq.put("secret_keepperiod_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("secret_keepperiod"),"")));
				
				// 준거법 초기값 설정
				//lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"C02210"))); 준거법 초기값을 한국에서 빈값으로 교체설정
				lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"")));
				
				vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
				vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
				List listTs = considerationService.listTypeSpclinfoCntr(vo);// TN_CLM_TYPE_SPCLINFO 특화정보[계약관리]
				List listCs = considerationService.listTypeSpclinfoCnsd(vo);// TN_CLM_TYPE_SPCLINFO 특화정보[법무]
				//List listRc = considerationService.listRelationContract(vo);// TN_CLM_RELATION_CONTRACT 연관계약	
				
				vo.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
				vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
				vo.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
				
				
				List listMn = considerationService.returnListMndeptcnsd(vo);//일반조항 검토의견
				ListOrderedMap lomPrevMn = new ListOrderedMap();
				
				if(listMn !=null && !listMn.isEmpty()){
					lomMn= (ListOrderedMap)listMn.get(0);
					lomMn.put("main_matr_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomMn.get("main_matr_cont"),""))); 	// 개행문자를 <BR> 로 전환
				}else{
					//최종본의뢰인 경우 이전 의뢰 에 정검토 부서에 사안개요 및 담당자 메모 표시 하기
					if(!"".equals(vo.getPrev_cnsdreq_id()) && "C04301".equals(lomRq.get("cnsd_status"))){
						vo.setSubmit_status("prev");
						vo.setPrev_cnsdreq_id((String)lomRq.get("prev_cnsdreq_id"));
						List listPrevMn = considerationService.returnListMndeptcnsd(vo);
						if(listPrevMn !=null && !listPrevMn.isEmpty()){
							lomPrevMn= (ListOrderedMap)listPrevMn.get(0);
							lomMn.put("main_matr_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomPrevMn.get("main_matr_cont"),""))); 	// 개행문자를 <BR> 로 전환
							
						}
					}
				}
				
				if("N".equals(vo.getPlndbn_req_yn())){
					ListOrderedMap returnLom= considerationService.getFilevalidate(vo);
					form.setFile_validate((String)returnLom.get("fileValidate"));
				}else{
					form.setFile_validate("N");  //최종본 회신일경우 처리 필요	
				}
				
				//====================================
				// HQ 의뢰 검토 정보 시작
				//====================================
				
				List resultListHQ = considerationHQService.detailConsiderationHQ(hqVo);
				
				ListOrderedMap lomHQ= new ListOrderedMap();
				
				if(resultListHQ != null && resultListHQ.size() > 0){
					
					for(int i=0; i<resultListHQ.size(); i++) {
						lomHQ = (ListOrderedMap)resultListHQ.get(i);
					}
				}				
				
				lomHQ.put("hq_cnsd_demnd_cont_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_cnsd_demnd_cont")));	// CE 검토 내용
				// 개행을 -> </BR> 로 전환처리 
				lomHQ.put("hq_ce_cnsd_opnn_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_ce_cnsd_opnn")));	// CE 검토 내용
				lomHQ.put("hq_im_cnsd_opnn_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_im_cnsd_opnn")));	//  IM 검토 내용
				lomHQ.put("hq_not_cnsd_opnn_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_not_cnsd_opnn")));	//  NOT 검토 내용
				lomHQ.put("hq_apbt_opnn_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_apbt_opnn")));	// 승인의견
				lomHQ.put("hq_rej_opnn_td", StringUtil.convertEnterToBR((String)lomHQ.get("hq_rej_opnn")));	// 반려의견
				lomHQ.put("hq_apbt_memo_td", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomHQ.get("hq_apbt_memo"),""))); // 배정의견
								
				ArrayList lomRespHQ = listRespmanHQ(hqVo);									// 담당자 List
				//로그인한 id로 검토담당자인지 체크
				String respYnHQ = "N";
			
				if(null != lomRespHQ && lomRespHQ.size() > 0 ){
					ListOrderedMap lomRHQ = null;
					for(int i = 0; i < lomRespHQ.size(); i++){
						lomRHQ = (ListOrderedMap)lomRespHQ.get(i);
						if(form.getSession_user_id().equals(lomRHQ.get("LIST_RESPMAN_ID"))){
							respYnHQ = "Y";
						}
					}
				}				
				
				
				// 필수 항목 체크 내용 - 15개				
				List chekItemList = considerationService.listCheckList(vo);
				
				if(chekItemList != null){
					ListOrderedMap chk_lom = new ListOrderedMap();
					for(int j = 0 ; j < chekItemList.size() ; j++){
						chk_lom = (ListOrderedMap) chekItemList.get(j);						
						chk_lom.put("REMARK", StringUtil.convertEnterToBR((String)chk_lom.get("REMARK")));
					}
				}
				
				// 병행 검토 여부 판단
				List hqchekItemList = considerationService.listHqCheckList(vo);
				
				//====================================
				// history 시작
				//====================================
				exVo.setDept_div("MN");;
				exVo.setCntrt_id(vo.getCntrt_id());
				resultList = considerationService.listHisExecution(exVo);
				
				if (resultList != null && resultList.size() > 0) {
					review = (ArrayList)resultList.get(0);
					approve = (ArrayList)resultList.get(1);
					info = (ArrayList)resultList.get(2);
				} 
				
				if(review != null){
					ListOrderedMap _lom = new ListOrderedMap();
					for(int j = 0 ; j < review.size() ; j++){
						_lom = (ListOrderedMap) review.get(j);
						
						_lom.put("cont", StringUtil.convertEnterToBR((String)_lom.get("cont")));
						_lom.put("plndbn_chge_cont", StringUtil.convertEnterToBR((String)_lom.get("plndbn_chge_cont")));
						_lom.put("vc_apbt_opnn", StringUtil.convertEnterToBR((String)_lom.get("vc_apbt_opnn")));
						_lom.put("mn_apbt_opnn", StringUtil.convertEnterToBR((String)_lom.get("mn_apbt_opnn")));						

					}
				}
				
				// HQ 검토이력
				hqVo.setCntrt_id(vo.getCntrt_id()); // 계약 ID 세팅
				List hqHistory = considerationHQService.getHqHistory(review, hqVo);

				// 부서 검토 승인, 반려 이력 조회
				cnsdList = considerationService.listHisDeptCnsdRejct(exVo); //김재원 20130912 START WIDTH
				
				//====================================
				// history 끝
				//====================================
				
				//consider_d.jap에서 처리한 내용을 여기로 옮김.
				form.setRespman_apnt_yn("N");
				if(form.getBlngt_orgnz().equals(lomRq.get("mn_cnsd_dept"))){
					form.setRespman_apnt_yn("Y");
				}
				
				//검토의견 조회 수정 여부
				String modYn = "N";
				form.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTop_role(),"")));
				form.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBlngt_orgnz(),"")));
				form.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getMn_cnsd_dept(),"")));
				form.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getVc_cnsd_dept(),"")));
				form.setCnsd_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCnsd_status(),"")));
				form.setRespman_apnt_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRespman_apnt_yn(),"")));

				if( ( (form.getTop_role().equals("RA01") || form.getTop_role().equals("RB01")) && form.getRespman_apnt_yn().equals("Y") && !form.getCnsd_status().equals("C04305")) //!최종검토완료
					|| ( form.getTop_role().equals("RA02") && respYn.equals("Y") && form.getRespman_apnt_yn().equals("Y") && (form.getCnsd_status().equals("") || form.getCnsd_status().equals("C04302"))) ){//작성중
					modYn = "Y";
				}
				
				// 2012.05.24 계약마스터 테이블의 단계상태가 DROP, 보류인 경우 view화면 전환 added by hanjihoon
				if(lomRq.get("depth_status").equals("C02603") || lomRq.get("depth_status").equals("C02607")){
					//forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_inner_d_view.jsp";
					modYn = "N";
				}
				
				//====================================
				// 연관계약 정보
				//====================================		
				
				String contRc = "";				
				
				if("Y".equals(modYn)){
					vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
					vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
					contRc = (String)((HashMap)considerationService.listRelationContract(vo)).get("contRc") ;
				}else{
					relationList = considerationService.listConsultationRealtion(vo);
				}
				
				mav.setViewName(forwardURL);
				
				mav.addObject("topRole"		, topRole);				// HQ 검토 의뢰
				mav.addObject("hasH0102"		, hasH0102);		// HQ 담당자 List		
				mav.addObject("respYnHQ"		, respYnHQ);		// HQ 담당자 List				
				mav.addObject("hqHistory"		, hqHistory);		// HQ 검토 이력		
				mav.addObject("chekItemList"		, chekItemList);		// 15개 체크항목 (최종본 검토시)			
				
				mav.addObject("hqchekItemList"		, hqchekItemList);		// 병행검토 여부 판단.			
			
				mav.addObject("lomHq"		, lomHQ);				// HQ 검토 의뢰
				mav.addObject("lomRespHQ"		, lomRespHQ);		// HQ 담당자 List
								
				mav.addObject("lomRq"		, lomRq);				// 검토의뢰
				mav.addObject("lomResp"		, lomResp);			// 담당자 List

				mav.addObject("lomDcd"		, lomDcd);			// B.계약별_부서검토반려
				//mav.addObject("btRollList"	, btRollList);	// 권한별 버튼 컨트롤
				mav.addObject("lomRm"		, lomRm);				// 관련자 List
				
				mav.addObject("lomMn"		, lomMn);
				//mav.addObject("lomVc"		, lomVc);
				
				mav.addObject("listDc"		, listDc);
				mav.addObject("command"		, form);
				//
				mav.addObject("listTs"		, listTs);
				mav.addObject("listCs"		, listCs);
				//연관계약
				mav.addObject("relationList"	, relationList);
				mav.addObject("contRc"			, contRc);
				//history
				mav.addObject("review"		, review);
				mav.addObject("approve"		, approve);
				mav.addObject("info"		, info);
				mav.addObject("cnsdList"	, cnsdList);		// 부서 검토 승인, 반려 이력
				
				if(lomRq.get("sys_nm")!=null){
					mav.addObject("sys_nm", (String)lomRq.get("sys_nm"));
				}
				mav.addObject("modYn"		, modYn);
				mav.addObject("respYn"		, respYn);
			}
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailConsiderationNew() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailConsiderationNew() Throwable !!");
		}
	}
	
	/**
	 * 담당자 리스트
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listRespman(ConsultationVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listRespman(vo);
			
			ArrayList lom = (ArrayList)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listRespman() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listRespman() Throwable !!");
		}
	}
	
	/**
	 * HQ 담당자 리스트
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listRespmanHQ(ConsiderationHQVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationHQService.listRespmanHQ(vo);
			
			ArrayList lom = (ArrayList)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listRespman() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listRespman() Throwable !!");
		}
	}
	
	/**
	 * HQ 담당자 리스트[팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popRespmanHQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    	= "";
			String returnMessage 	= "";
			
			ConsiderationHQForm form  	= null;
			ConsiderationHQVO   vo    	= null;
		
			PageUtil 	pageUtil   	= null;
			List   		resultList 	= null;
			List 		apbtMemoList= null;
			
			String 		authYN    	= "";
			ListOrderedMap lomAm	= new ListOrderedMap();
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
						
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ConsiderationHQForm();
			vo   = new ConsiderationHQVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			if("RP030".equals(form.getHq_rel_pro())) {
				forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_resp2.jsp";
			} else if("RP040".equals(form.getHq_rel_pro())) {
				forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_resp2.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_resp.jsp";
			}		
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			//form.setApbt_memo(StringUtil.convertEnterToBR(StringUtil.bvl(form.getApbt_memo(),""))); // 배정의견
			
			ModelAndView mav = new ModelAndView();
			
			// form.setPage_div(StringUtil.bvl(form.getPage_div(),""));	// 그룹장 화면 구분
			
			//authYN = checkAuth(vo, "[B99]", "S", "", form.getPage_div());	//기타
			authYN = "Y";
			if(!"Y".equals(authYN)) {
				forwardURL="/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";
				mav.setViewName(forwardURL);
				
				form.setReturn_title(messageSource.getMessage("las.page.field.consideration.popRespman01", null, new RequestContext(request).getLocale()));//권한없음
				form.setReturn_message(messageSource.getMessage("las.page.field.consideration.popRespman02", null, new RequestContext(request).getLocale()));//본페이지의 권한이 없습니다.
				mav.addObject("command", form);
			}else{
				resultList = considerationHQService.popRespmanHQ(vo);
	
				// apbtMemoList = considerationService.listApbtMemo(vo);
				
				if(apbtMemoList != null && apbtMemoList.size() > 0){
					lomAm = (ListOrderedMap)apbtMemoList.get(0);
				}
				
				ArrayList lom = (ArrayList)resultList;
				/*********************************************************
				 * ModelAndView
				**********************************************************/
				form.setReturn_message(returnMessage);
				
				mav.setViewName(forwardURL);
	
				mav.addObject("resultList", resultList);
				mav.addObject("lomAm", lomAm);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			}
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.listTypeItem() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listTypeItem() Throwable !!");
		}
	}
	
	/**
	 * 담당자 지정
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void confirmRespmanHQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsiderationHQForm form  	= null;	
			ConsiderationHQVO   vo    	= null;
			String 			 authYN	= "";
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsiderationHQForm();
			vo   = new ConsiderationHQVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			//법무담당자 등록
			String list_respman_ids[] = vo.getList_respmanHQ_ids();
			String respman = "";
			
			if(vo.getList_respmanHQ_ids() != null){
				for(int i=0; i<list_respman_ids.length; i++) {
					if(!list_respman_ids[i].equals("")){
						if(list_respman_ids.length-1 == i){
							respman += StringUtil.bvl(list_respman_ids[i],"");
						}else{
							respman += StringUtil.bvl(list_respman_ids[i],"") + ",";
						}
					}
				}
			}
			
			// form.setPage_div(StringUtil.bvl(form.getPage_div(),""));			// 그룹장 화면 구분			

			authYN = "Y";
			
			JSONObject jo 		= new JSONObject();

			int result = 0;
			
			if(!"Y".equals(authYN)) {
	    		jo.put("returnMessage", messageSource.getMessage("secfw.page.field.alert.noAuth",  null, new RequestContext(request).getLocale()));
			}else{
				result = considerationHQService.confirmRespmanHQ(vo);

				// TODO: ESB 연결시 작업해야 함 
				/*				if(result > 0){
					// 배정 알림 메일 전송
						mailSendService.sendConsiderationRespmanMailSend(vo);
				}*/
				
				jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			}
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}
	
	/**
	 * 승인 /  반려 / 어드민 리턴 사유 입력 [팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popRTN(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			//String prcsDepth = StringUtil.bvl(request.getParameter("prcsDepth"),"");
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/reviewHQ/ConsiderationHQ_opnn_p.jsp";
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsiderationHQForm form = new ConsiderationHQForm();
			ConsiderationHQVO vo = new ConsiderationHQVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setHq_cnsdreq_id(vo.getHq_cnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
//			this.getLogger().debug("forwardURL: " +mav.getViewName());

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
	 *  HQ 그룹장 승인 /  반려  / ADMIN REPLY 처리 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void rtnReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			ConsiderationHQVO vo = new ConsiderationHQVO();		
			ConsiderationHQForm form = new ConsiderationHQForm();		
			
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
			int return_val = considerationHQService.rtnReturn(vo);	
		
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
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	
	
	
	/**
	 *  회신 / 임시저장 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void returnConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			String topRole = "";
			String hasH0102 = "N";
			
			ConsiderationHQVO vo = new ConsiderationHQVO();		
			ConsiderationHQForm form = new ConsiderationHQForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 회신 권한 체크
			**********************************************************/
					
			HashMap 	   roleListMap = new HashMap();
			ListOrderedMap roleListLom = null;
			
			roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
			roleListMap.put("user_id", StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
			
			List 	  roleList 	   = userService.listUserRole(roleListMap);
			ArrayList userRoleList = new ArrayList();
			
			if(roleList != null && roleList.size() > 0){
			    for(int idx = 0; idx < roleList.size(); idx++){
			    	roleListLom = (ListOrderedMap)roleList.get(idx);
			        userRoleList.add((String)roleListLom.get("role_cd"));
			    }
			}
			
			if(userRoleList.contains("HQ01")){
				topRole = "HQ01";
						
					if(userRoleList.contains("HQ02")){
						hasH0102 = "Y";
					}			
			
			}else if(userRoleList.contains("HQ02")){
				topRole = "HQ02";
			}

			/*********************************************************
			 * Service
			**********************************************************/
			
			int return_val = 0;
			
			if("SAVE".equals(vo.getStat_flag())){ // 임시저장 플래그 인 경우				
			
				return_val = considerationHQService.tempSave(vo);				
			
			} else if("SEND".equals(vo.getStat_flag())){ // 회신 인 경우				
			
				if("Y".equals(hasH0102)){ // HQ01 HQ02 두권한을 모두 가진 경우 회신과 동시에 승인 처리 한다.
					return_val = considerationHQService.hq01RESP(vo);	
				} else {
					
					if("HQ01".equals(topRole)){
						return_val = considerationHQService.hq01RESP(vo);	
					} else{
						return_val = considerationHQService.hq02RESP(vo);	
					}				
				}				
			}
		
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
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	
	
	
	/**
	 * 관련자 리스트
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listRelationman(ConsultationVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listRelationman(vo);
			
			ArrayList lom = (ArrayList)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listRelationman() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listRelationman() Throwable !!");
		}
	}
	
	
	/**
	 *  AJAX SAMPLE
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public void reqToHQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			
			
			String returnMessage = "";
			String page_div	= "";
			
			JSONObject jo = new JSONObject();
			HashMap mapRt = new HashMap();	
			
			HttpSession session = request.getSession(false);
			
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);	
			
			//Cross-site Scripting 방지
/*			form.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.q(),"")));				
			vo.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_sqn(),"")));			*/
			
			// ##############################  PRC1 START
			// TODO 1. 임시저장 처리
			// 임시저장후 화면처리를 위한 변수 세팅
			// ##############################  			
			
			vo.setEvent_but("[B01]");
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));
			
			page_div = form.getPage_div();
			
			if(!page_div.equals("")){
				page_div = "_" + page_div;
			}		
			
			vo.setStat_flag("SAVE");			
			mapRt= considerationService.returnConsideration(vo);
			
			// ##############################  PRC1 END
			
			// TODO 2. 원시 검토 상태값 갱신 처리
			
/*			2014-04-29 11:23:19,097  INFO [jdbc.sqlonly] UPDATE TN_CLM_CONT_CNSDREQ SET PRGRS_STATUS = CASE WHEN 'C04204' = 'C04204' THEN PRGRS_STATUS 
					ELSE 'C04204' END WHERE 1=1 AND CNSDREQ_ID = 'H20140121003' */
			
			// TODO 3. HQ 검토 의뢰 처리 	
			
			// TODO 4. 메일 발송 처리 ( IM / CE ) 경우
			//메일 발송 처리 ( IM / CE ) 경우 메일 전송
			//	if(mapRt.get("mail_send_yn").equals("Y")){
			//		mailSendService.sendConsiderationReplyMailSend(vo);
			//	}
			
    		jo.put("returnVal", 	mapRt.get("returnVal"));
    		jo.put("returnMsg", 	mapRt.get("returnMsg"));
    		jo.put("depthStatus",	mapRt.get("depthStatus"));
    		jo.put("prgrsStatus",	mapRt.get("prgrsStatus"));
    		jo.put("cnsdStatus",	mapRt.get("cnsdStatus"));
    		jo.put("deptcnsdCnsdStatus",mapRt.get("deptcnsdCnsdStatus"));    		
	    	
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	
	}
	
	/**
	 * 15개 항목 체크
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView getCheckList(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			//String prcsDepth = StringUtil.bvl(request.getParameter("prcsDepth"),"");
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/reviewHQ/Consideration_chk.jsp";
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
	        ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setHq_cnsdreq_id(vo.getHq_cnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());
			
			String cntrt_id2 = request.getParameter("cntrt_id2");
			String cntrt_id3 = request.getParameter("cntrt_id3");
			
			vo.setCntrt_id(cntrt_id3);
			
			// 필수 항목 체크 내용 - 15개				
			List chekItemList = considerationService.listCheckList(vo);
			
			if(chekItemList != null){
				ListOrderedMap chk_lom = new ListOrderedMap();
				for(int j = 0 ; j < chekItemList.size() ; j++){
					chk_lom = (ListOrderedMap) chekItemList.get(j);						
					chk_lom.put("REMARK", StringUtil.convertEnterToBR((String)chk_lom.get("REMARK")));
				}
			}
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
//			this.getLogger().debug("forwardURL: " +mav.getViewName());

			mav.addObject("command", form);		
			mav.addObject("chekItemList2", chekItemList);		

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

}