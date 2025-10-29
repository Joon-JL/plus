/**
 * Project Name : 법무시스템
 * File name	: ConsiderationController.java
 * Description	: 검토의뢰  Controller
 * Author		: 김재원
 * Date			: 2013. 04
 * Copyright	:
 */
package com.sec.clm.review.control;

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
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.service.UserService;
import com.sec.clm.manage.dto.CompletionForm;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.review.dto.ConsiderationForm;
import com.sec.clm.review.dto.ConsiderationHQVO;
import com.sec.clm.review.dto.ConsiderationVO;
import com.sec.clm.review.dto.ConsultationForm;
import com.sec.clm.review.dto.ConsultationVO;
import com.sec.clm.review.service.ConsiderationHQService;
import com.sec.clm.review.service.ConsiderationService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.common.util.ClmsDataUtil;

/**
 * Description	: Controller
 * Author		: 제이남
 * Date			: 2013. 04
 */
public class ConsiderationController extends CommonController {
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * Consideration 서비스
	 */
	private ConsiderationService considerationService;
	
	/**
	 * Consideration 서비스 세팅
	 * 
	 * @param ConsiderationService
	 * @return void
	 * @throws
	 */
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
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
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
			
			ConsiderationVO vo = new ConsiderationVO();		
			ConsiderationForm form = new ConsiderationForm();		
			
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
			int return_val = considerationService.setMarkUpAJAX(vo);	
		
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
	 * 의뢰내역 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listConsideration(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			boolean viewOldContractBtnFlag = false; //사업부이관 등록버튼 view 여부
			String forwardURL    = "";
			String returnMessage = "";
		
			ConsiderationForm form 	= null;	
			ConsiderationVO   vo   	= null;	
			PageUtil 	pageUtil   	= null;
			List   		resultList 	= null;
			String 	 	topRole		= "";		// 사용자 Role
			boolean		elUserlYn	= false;
			String 		sElComp 	= null;
			String 		sMultComp  	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ConsiderationForm();
			vo 	 = new ConsiderationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(form.getSrch_start_dt() == null || "".equals(form.getSrch_start_dt())){
				form.setSrch_start_dt(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
				vo.setSrch_start_dt(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
			}
			if(form.getSrch_end_dt() == null || "".equals(form.getSrch_end_dt())){
				form.setSrch_end_dt(DateUtil.formatDate(DateUtil.today(), "-"));
				vo.setSrch_end_dt(DateUtil.formatDate(DateUtil.today(), "-"));
			}
			
			sElComp = StringUtil.bvl(form.getsElComp(), "");  // 회사 선택 조회 조건(전자 변호사일 경우에만 나타나게 된다.)
			sMultComp = StringUtil.bvl(form.getsMultComp(), "");  // 회사 선택 조회 조건(전자 변호사일 경우에만 나타나게 된다.)
			
			//Cross-site Scripting 방지
			form.setSrch_type_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_type_cd(),"")));
			form.setSrch_req_title(StringUtil.bvl(form.getSrch_req_title(),""));
			form.setSrch_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_orgnz(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_reqman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reqman_id(),"")));
			form.setSrch_reqman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reqman_nm(),"")));
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
			form.setSrch_gerp_code(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_gerp_code(),"")));
			// 전자 변호사일 경우 각 회사를 조회 할 수 있게 처리 됨.
			
			if("".equals(sMultComp)){
				form.setsElComp(sElComp);
				vo.setSElComp(sElComp);
			} else {
				form.setsElComp(sMultComp);
				vo.setSElComp(sMultComp);
			}
			
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			if(tmpSessionRoleList.contains("RB01")){
				topRole = "RB01";
				elUserlYn = true;
			} 
			// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
			if(tmpSessionRoleList.contains("RB02")){
				form.setsSel_grd("Y");
				elUserlYn = true;
			} else {
				form.setsSel_grd("N");
			}
			
			List multYn = null;
			ListOrderedMap lm = new ListOrderedMap();
			String multCompList = "";
			
			multYn = considerationService.listMultYn(vo);
			
			if(multYn.size() > 0){
				form.setsMultYn("Y");
				
				for(int i=0; i < multYn.size(); i++){
					lm = (ListOrderedMap)multYn.get(i);
					
					multCompList += "'"+(String)lm.get("comp_cd")+"',";
				}
				
				multCompList = multCompList + "'"+(String)form.getSession_comp_cd()+"'";
				
				vo.setSession_auth_comp_cd(multCompList);       
				
			} else {
				form.setsMultYn("N");
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
			
			if(vo.getPage_flag().equals("5")) {		// 전사계약List 일 경우
				
				String page_mode = StringUtil.bvl(request.getParameter("page_mode"), "");
				
				if("rel".equals(page_mode)){
					form.setList_mode("notCnsdreq");
					vo.setList_mode("notCnsdreq");
					forwardURL = "/WEB-INF/jsp/las/contractManager/RelConsideration_total_l.jsp";  // 연관계약 리스트 팝업
				} else {
					forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_total_l.jsp";     // 전사계약List
				}
				
				if("CLM".equals((String)session.getAttribute("secfw.session.sys_cd"))){
					if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RD01")){
						viewOldContractBtnFlag = true;
					}
				//법무
				}else{
					if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RA01") || tmpSessionRoleList.contains("RA02")){
						viewOldContractBtnFlag = true;
					}					
				}				
			} else {
				if(("1".equals(vo.getPage_flag()) || "2".equals(vo.getPage_flag())) && "".equals(StringUtil.bvl(vo.getSrch_reqman_nm(),"")) && "RA02".equals(topRole)) {
					form.setSrch_respman_nm(vo.getSession_user_nm());
					vo.setSrch_respman_nm(vo.getSession_user_nm());
				}
			}
			form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
			vo.setList_mode(form.getList_mode());
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listConsideration(vo);

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
			
			ArrayList<?> lom = (ArrayList<?>)resultList;

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("elUserlYn", elUserlYn);
			
			mav.addObject("viewOldContractBtnFlag", viewOldContractBtnFlag);

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
	* 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listConsiderationExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ConsiderationForm form = new ConsiderationForm();
			ConsiderationVO vo 	 = new ConsiderationVO();
			
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
					
			List<?> resultList = null;
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listConsideration(vo);
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String		fileNm			= "";
			String[]	sheetNmAry		= new String[1];
			String[]	titleInfo		= new String[1];
			
			String[]	subTitleInfo	= null;
			String[]	columnInfo		= null;
			short[]		columnAlign		= null;
			
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
			//메시지 처리 - close YN
			String close_yn = messageSource.getMessage("clm.page.field.qna.pubYnN", null, new RequestContext(request).getLocale());
			
			fileNm			= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
			sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
			//메시지 처리 - 의뢰별 목록
			titleInfo[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale());
			subTitleInfo	= new String[]{"Type", req_title, cntrt_oppnt_nm, req_dept_nm, reqman_nm, req_dt, law_respman_nm, law_status_nm, prgrs_status_nm, close_yn};
			columnInfo		= new String[]{"type_nm_1","req_title", "cntrt_oppnt_nm", "req_dept_nm_1", "reqman_nm", "req_dt_1", "law_respman_nm", "law_status_nm", "prgrs_status_nm_1","close_yn"};
			columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};
			
	        
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
	 * 계약검토 상세
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
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			List<?>   	 	resultList 	= null;
			String topRole = "";		// 사용자 Role
			ListOrderedMap lomMn= new ListOrderedMap();
			ExecutionForm exForm    = null;
			ExecutionVO   exVo      = null;
			List<?> cnsdList			= null;	// 담당자의견 및 반려 이력
			List<?> relationList 		= null; //연관계약 list
			List<?> chekItemList 		= null; //필수항목 체크 list
			List<?> hqchekItemList     = null; // hq 검토의뢰 요청 여부
			List<?> hqchekItemList2    = null; // hq 검토의뢰 요청 여부
			
			ArrayList<?> review 		= null;
			ArrayList<?> approve 		= null;
			ArrayList<?> info 			= null;
			
			int chekItemListSize = 0;
			int hqchekItemListSize = 0;
			int hqchekItemListSize2 = 0;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_d.jsp";		// 일반의뢰

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        form = new ConsultationForm();
			vo   = new ConsultationVO();
			exForm = new ExecutionForm();
			exVo = new ExecutionVO();
						
			bind(request, form);
			bind(request, vo);
			bind(request, exForm);
			bind(request, exVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, exForm);
			COMUtil.getUserAuditInfo(request, exVo);
			
			HashMap<String, String> roleListMap = new HashMap<String, String>();
			ListOrderedMap roleListLom = null;
			
			roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
			roleListMap.put("user_id", StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
			
			List<?> roleList = userService.listUserRole(roleListMap);
			ArrayList<String> userRoleList = new ArrayList();
			
			if(roleList != null && roleList.size() > 0){
			    for(int idx = 0; idx < roleList.size(); idx++){
			    	roleListLom = (ListOrderedMap)roleList.get(idx);
			        userRoleList.add((String)roleListLom.get("role_cd"));
			    }
			}
			
			if(userRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(userRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(userRoleList.contains("RA02")){
				topRole = "RA02";
			}
			
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
			
			List<?> listDc = considerationService.detailConsideration(vo);
			ListOrderedMap lomRq = null;
			if(listDc != null && listDc.size() > 0){
				lomRq= (ListOrderedMap)listDc.get(0);
			}else{
				lomRq = new ListOrderedMap();
			}
			
			lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
			lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
			lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));
			
			// 2014-07-23 Kevin added.
			lomRq.remove("plndbn_req_yn");
			lomRq.put("plndbn_req_yn", StringUtil.bvl(vo.getPlndbn_req_yn(), ""));
			
			// HQ REQUEST 판단을 위한 값을 가지오 오기
			// 1 유형, 2 30대 거래선
			lomRq.put("cnsl_mid_cd3", (String)lomRq.get("cnsl_mid_cd2"));	// 게약 유형 판별
			lomRq.put("top_30_cus", (String)lomRq.get("top_30_cus"));	// 30대 거래선
			lomRq.put("cont_kind_type", (String)lomRq.get("cont_kind_type"));	// 어디 계약서를 사용 했는지 묻기
			lomRq.put("hq_req_yn", (String)lomRq.get("hq_req_yn"));	// 병행 검도 여부
			lomRq.put("msys_nm", (String)lomRq.get("msys_nm"));	// TCMS 데이터
			
			// 상세 화면에 나오기 위한 값을 세팅 함.
			lomRq.put("cont_draft", (String)lomRq.get("CONT_DRAFT"));	// 게약 유형 판별
			lomRq.put("top_30_cus", (String)lomRq.get("TOP_30_CUS"));	// 30대 거래선
			lomRq.put("related_products", (String)lomRq.get("RELATED_PRODUCTS"));	// 어디 계약서를 사용 했는지 묻기
			
			
			vo.setCnsdreq_id(StringUtil.bvl((String)lomRq.get("cnsdreq_id"),""));
			vo.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
			lomRq.put("apbt_memo_dp", StringUtil.convertEnterToBR((String)lomRq.get("apbt_memo")));	// 승인자_메모
			
			ArrayList<?> lomResp = listRespman(vo);									// 담당자 List
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
			ArrayList<?> lomRm = null;
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
			// Special Condition
			lomRq.put("spcl_cond_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));
			
			// 준거법 상세
			lomRq.put("loac_etc_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
			// 분쟁해결방법상세 
			lomRq.put("dspt_resolt_mthd_det_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
			// 비밀유지기간
			lomRq.put("secret_keepperiod_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("secret_keepperiod"),"")));
			
			// 준거법 초기값 설정
			lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"")));
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			vo.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			vo.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
			
			List<?> listMn = considerationService.returnListMndeptcnsd(vo);//일반조항 검토의견
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
			// history 시작
			//====================================
			exVo.setDept_div("MN");;
			exVo.setCntrt_id(vo.getCntrt_id());
			resultList = considerationService.listHisExecution(exVo);
			
			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList<?>)resultList.get(0);
				approve = (ArrayList<?>)resultList.get(1);
				info = (ArrayList<?>)resultList.get(2);
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
			
			// 부서 검토 승인, 반려 이력 조회
			cnsdList = considerationService.listHisDeptCnsdRejct(exVo); //김재원 20130912 START WIDTH
			
			ConsiderationHQVO hqVo = new ConsiderationHQVO();
			
			// HQ 검토이력
			hqVo.setCntrt_id(vo.getCntrt_id()); // 계약 ID 세팅
			List hqHistory = considerationHQService.getHqHistory(review, hqVo);
			
			
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
			
			// 필수 항목 체크 내용 - 15개
			chekItemList = considerationService.listCheckList(vo);
			chekItemListSize = chekItemList.size();
			
			ListOrderedMap lomCheck = new ListOrderedMap();
			
			if(0 < chekItemListSize){
				
				for(int j = 0 ; j < chekItemListSize ; j++){
					lomCheck = (ListOrderedMap) chekItemList.get(j);
					
					lomCheck.put("no", lomCheck.get("no"));
					lomCheck.put("cd_nm", StringUtil.convertEnterToBR((String)lomCheck.get("cd_nm")));
					lomCheck.put("remark", StringUtil.convertEnterToBR((String)lomCheck.get("remark")));
				}
			}
			
			// hq 병행 검토 여부 판단 체크
			// 병행 검토 여부 판단
			hqchekItemList = considerationService.listHqCheckList(vo);
			hqchekItemListSize = hqchekItemList.size();	
			
			if(0 < hqchekItemListSize){
				hqchekItemList2 = considerationService.listHqCheckList(vo);
			} else {
				hqchekItemList2 = considerationService.listHqCheckList2(vo);
			}
			
			// hq 병행 검토 결과 값 가지고 오기
			resultList = considerationService.selectHqRequest(vo);
			int resultListSize = resultList.size();
			
			String cntrt_id = StringUtil.nvl(form.getCntrt_id(), "");
			String cnsdreq_id = StringUtil.nvl(form.getCnsdreq_id(), "");
			String hq_cnsdreq_id = StringUtil.nvl(form.getHq_cnsdreq_id(), "");
			
			String db_cntrt_id = "";
			String db_cnsdreq_id = "";
			String db_hq_cnsdreq_id = "";
			String db_cnsd_level = "";
			String db_cnsd_status = "";
			
			if(0 < resultListSize){
				ListOrderedMap result = (ListOrderedMap)resultList.get(0);
				db_cntrt_id = (String)result.get("cntrt_id");
				db_cnsdreq_id = (String)result.get("cnsdreq_id");
				db_hq_cnsdreq_id = (String)result.get("hq_cnsdreq_id");
				db_cnsd_level    =  (BigDecimal)result.get("cnsd_level")+"";
				db_cnsd_status = (String)result.get("hq_cnsd_status");
			}
			
			if("".equals(db_cntrt_id) || "".equals(db_cnsdreq_id)){
				form.setCntrt_id(cntrt_id);
				form.setCnsdreq_id(cnsdreq_id);
				form.setHq_cnsdreq_id(hq_cnsdreq_id);
			} else {
				form.setCntrt_id(db_cntrt_id);
				form.setCnsdreq_id(db_cnsdreq_id);
				form.setHq_cnsdreq_id(db_hq_cnsdreq_id);
				form.setCnsd_level(db_cnsd_level);
				form.setHq_cnsd_status(db_cnsd_status);
			}
			
			hqchekItemListSize2 = hqchekItemList2.size();
			
			String comp_cd = "";
			
			comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			
			form.setComp_cd(comp_cd);
			
			mav.setViewName(forwardURL);
			mav.addObject("lomRq"		, lomRq);				// 검토의뢰
			mav.addObject("lomResp"		, lomResp);			// 담당자 List
			mav.addObject("lomDcd"		, lomDcd);			// B.계약별_부서검토반려
			mav.addObject("lomRm"		, lomRm);				// 관련자 List
			mav.addObject("lomMn"		, lomMn);
			mav.addObject("listDc"		, listDc);
			mav.addObject("command"		, form);
			//연관계약
			mav.addObject("relationList"	, relationList);
			mav.addObject("contRc"			, contRc);
			//history
			mav.addObject("review"		, review);
			mav.addObject("approve"		, approve);
			mav.addObject("info"		, info);
			mav.addObject("cnsdList"	, cnsdList);		// 부서 검토 승인, 반려 이력
			
			mav.addObject("hqHistory"	,hqHistory);		// HQ 의뢰 이력

			if(lomRq.get("sys_nm")!=null){
				mav.addObject("sys_nm", (String)lomRq.get("sys_nm"));
			}
			mav.addObject("modYn"		, modYn);
			mav.addObject("respYn"		, respYn);
			
			// 필수 항목 체크
			mav.addObject("chekItemList"		, chekItemList);
			mav.addObject("chekItemListSize"	, chekItemListSize);
			
			//hq 검토 의뢰
			mav.addObject("hqchekItemList"		, hqchekItemList2);
			mav.addObject("hqchekItemListSize"	, hqchekItemListSize2);
			
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
	 * 상대회사관련 계약건 팝업
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listContractPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ConsultationForm form  	= null;
			ConsultationVO   vo    	= null;
			PageUtil 	pageUtil   	= null;
			List<?>   		resultList 	= null;
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_p.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCntrt_id(),"")));
			
			ArrayList<?> tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
		
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
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
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage_pop(), "1")));

			vo.setStart_index_pop(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index_pop(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listConsiderationPop(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
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
			
			ArrayList<?> lom = (ArrayList<?>)resultList;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			form.setReturn_message(returnMessage);
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
			throw new Exception("ConsiderationController.listConsiderationPop() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listConsiderationPop() Throwable !!");
		}
	}
	
	/**
	 * 계약검토 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailConsiderationPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			List<?>   	 	resultList 	= null;
			String 	 	topRole		= "";		// 사용자 Role
			ListOrderedMap lomMn= new ListOrderedMap();
			ListOrderedMap lomVc= new ListOrderedMap();
			List<?>   		resultAttachList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_d.jsp";		// 일반의뢰

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
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
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			List<?> listDc = considerationService.detailConsideration(vo);
			List<?> listDc2 = considerationService.detailContractMaster(vo);	
			resultAttachList = considerationService.listCompletionAttachInfo(vo);
			
			ListOrderedMap lomRq = null;
			if(listDc != null && listDc.size() > 0){
				lomRq= (ListOrderedMap)listDc.get(0);
			}else{
				lomRq = new ListOrderedMap();
			}
			
			lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
			lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
			lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));
			
			// 관련회사 조회건수가 없으면 팝업 없음
			if(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_cd"),"") != ""){
				vo.setCntrt_oppnt_cd(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_cd"),""));
				vo.setCntrt_oppnt_nm(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_nm"),""));
				resultList = considerationService.listConsiderationPop(vo);
			}
			
			if(resultList != null && resultList.size() > 0) {
				form.setCntrt_srch_yn("Y");
			}else{
				form.setCntrt_srch_yn("N");
			}
			
			ListOrderedMap lomRq2= (ListOrderedMap)listDc2.get(0);
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq2.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq2.get("cnclsnpurps_midclsfcn"));
			
			ListOrderedMap lomMn2 = new ListOrderedMap();
			
			vo.setMn_cnsd_dept((String)lomRq2.get("mn_cnsd_dept"));
			List listMn2 = considerationService.returnListMndeptcnsd(vo);	
			
			if(listMn2 !=null && !listMn2.isEmpty()){
				lomMn2= (ListOrderedMap)listMn2.get(0);
			}	
			
			//나모 잔여 태그 제거 
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR((String)lomRq.get("plndbn_chge_cont")));
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 

			vo.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
			vo.setCnclsnpurps_bigclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_bigclsfcn"),""));
			vo.setCnclsnpurps_midclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_midclsfcn"),""));

			List<?> listTs = considerationService.listTypeSpclinfoCntr(vo);	// TN_CLM_TYPE_SPCLINFO 특화정보[계약관리]
			List<?> listCs = considerationService.listTypeSpclinfoCnsd(vo);	// TN_CLM_TYPE_SPCLINFO 특화정보[법무]
						 
			vo.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			vo.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
			 
			List listMn = considerationService.returnListMndeptcnsd(vo);;
			List listVc = considerationService.returnListVcdeptcnsd(vo);
			
			if(!listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}
			if(!listVc.isEmpty()){
				lomVc= (ListOrderedMap)listVc.get(0);
			}
			
			//master_cnt, master_id
			ArrayList<?> lomMt = (ArrayList<?>)listDc;
			ArrayList<?> lomTs = (ArrayList<?>)listTs;
			ArrayList<?> lomCs = (ArrayList<?>)listCs;
			
			// 관련자 List
			ArrayList<?> lomRm = null;
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
				if(cntrtInfoGbn.equals("C05402") || cntrtInfoGbn.equals("C05403")) {
					forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_p.jsp";	// (구)법무시스템 검토 데이터 조회 페이지로 변경
				}
			}
			
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			ArrayList resultAttachArrList = new ArrayList();
			resultAttachArrList = (ArrayList)resultAttachList;
			
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);
			mav.addObject("lomRq2", lomRq2);
			mav.addObject("lomMt", lomMt);
			mav.addObject("lomMt2", lomMt);
			
			mav.addObject("lomTs", lomTs);		//특화속성[계약관리]
			mav.addObject("lomTs", lomCs);		//특화속성[법무]
			mav.addObject("lomRm", lomRm);		// 관련자 List
			
			mav.addObject("lomMn", lomMn);		// 관련자 List
			mav.addObject("lomMn2", lomMn2);		// 관련자 List
			mav.addObject("lomVc", lomVc);		// 관련자 List
			
			mav.addObject("listDc", listDc);
			mav.addObject("listDc2", listDc2);
			mav.addObject("listTs", listTs);	//특화속성[계약관리]
			mav.addObject("listCs", listCs);	//특화속성[법무]
			mav.addObject("command", form);
			mav.addObject("attachinfo",strAttachUrl);
			mav.addObject("resultAttachArrList",resultAttachArrList);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailConsideration() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailConsideration() Throwable !!");
		}
	}
	
	/*
	 * 프린트 팝업
	 * */
	public ModelAndView forwardPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL 		= "/WEB-INF/jsp/las/contractManager/print_preview_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			String contents = request.getParameter("contents");
			String contentHtml = contents.toString();
		    contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
		    
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("content", contentHtml);
			
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
	 * 검토의뢰 상세정보 리턴 -- (구)법무시스템 검토정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailOldCnsdreq(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    	= "";
		
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_info.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			List dcList = considerationService.detailConsideration(vo);
			ListOrderedMap lomRq= (ListOrderedMap)dcList.get(0);					// 검토의뢰
			
			lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
			lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
			lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));
			
			ArrayList<?> lomResp = listRespman(vo);									// 담당자 List
			
			ArrayList<?> lomRespSub = listRespmanSub(vo);								// 담당자 List[협업부서]
			
			ListOrderedMap lomCd= considerationService.contractCnsdrejct(vo);		// A.부서검토반려
					
			ListOrderedMap lomDcd= considerationService.contractDeptcnsdrejct(vo);	// B.계약별_부서검토반려
			lomDcd.put("apbt_memo", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_memo")));		// 승인자_메모
			lomDcd.put("apbt_opnn", StringUtil.convertEnterToBR((String)lomDcd.get("apbt_opnn")));		// 승인의견
			lomDcd.put("rejct_opnn", StringUtil.convertEnterToBR((String)lomDcd.get("rejct_opnn")));	// 반려의견
			
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);				// 검토의뢰
			mav.addObject("lomResp", lomResp);			// 담당자 List
			mav.addObject("lomRespSub", lomRespSub);	// 담당자 List[협업부서]
			mav.addObject("lomCd", lomCd);				// A.부서검토반려
			mav.addObject("lomDcd", lomDcd);			// B.계약별_부서검토반려
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailOldCnsdreq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailOldCnsdreq() Throwable !!");
		}
	}
	
	/**
	 * 담당자 리스트[팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popRespman(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    	= "";
			String returnMessage 	= "";
			
			ConsultationForm form  	= null;
			ConsultationVO   vo    	= null;
			List<?>   		resultList 	= null;
			List<?> 		apbtMemoList= null;
			ListOrderedMap lomAm	= new ListOrderedMap();
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_resp.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ConsultationForm();
			vo   = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));	// 그룹장 화면 구분
			
			resultList = considerationService.popRespman(vo);

			apbtMemoList = considerationService.listApbtMemo(vo);
			
			if(apbtMemoList != null && apbtMemoList.size() > 0){
				lomAm = (ListOrderedMap)apbtMemoList.get(0);
			}
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			form.setReturn_message(returnMessage);
			
			mav.setViewName(forwardURL);

			mav.addObject("resultList", resultList);
			mav.addObject("lomAm", lomAm);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
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
			List<?>   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listRespman(vo);
			
			ArrayList<?> lom = (ArrayList<?>)resultList;
			
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
	 * 담당자 리스트[협업부서]
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listRespmanSub(ConsultationVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List<?>   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listRespmanSub(vo);
			
			ArrayList<?> lom = (ArrayList<?>)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listRespmanSub() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listRespmanSub() Throwable !!");
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
			List<?>   	resultList 	= null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listRelationman(vo);
			
			ArrayList<?> lom = (ArrayList<?>)resultList;
			
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
	 * 계약건 상세 정보 수정 - 팝업으로 폼 진행 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			ClmsDataUtil.debug("detailContractMaster   >>>   form.getCntrt_id() >> " );
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session 	= request.getSession(false);
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL ;				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList<?> tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			//정검토부서
			form.setMn_cnsd_dept(vo.getMn_cnsd_dept());
			//부검토부서
			form.setVc_cnsd_dept(vo.getVc_cnsd_dept());
			//예정본의뢰여부
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			List listDc = considerationService.forwardContractMaster(vo);			
				
			// 2012.03.12 관련자 리스트 기능 추가 added by hanjihoon
			List listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			// 2012.03.02 기대효과, 지불/수분조건, 기타주요사항 개행문자 출력 수정 modified by hanjihoon
			// 기대효과
			lomRq.put("antcptnefct_dp", StringUtil.convertEnterToBR((String)lomRq.get("antcptnefct")));
			// 지불/수분조건
			lomRq.put("payment_cond_dp", StringUtil.convertEnterToBR((String)lomRq.get("payment_cond")));
			// 기타주요사항
			lomRq.put("etc_main_cont_dp", StringUtil.convertEnterToBR((String)lomRq.get("etc_main_cont")));
			
			// 책임한도
			lomRq.put("oblgt_lmt_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("oblgt_lmt"),"")));				
			// Special Condition
			lomRq.put("spcl_cond_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));
			
			// 준거법 상세
			lomRq.put("loac_etc_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
			// 분쟁해결방법상세 
			lomRq.put("dspt_resolt_mthd_det_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
			// 비밀유지기간
			lomRq.put("secret_keepperiod_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("secret_keepperiod"),"")));
			
			// 준거법 초기값 설정
			lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"C02210")));
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			List<?> listTs = considerationService.listTypeSpclinfoCntr(vo);// TN_CLM_TYPE_SPCLINFO 특화정보[계약관리]
			List<?> listCs = considerationService.listTypeSpclinfoCnsd(vo);// TN_CLM_TYPE_SPCLINFO 특화정보[법무]
			
			//나모 잔여 태그 제거 
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("pshdbkgrnd_purps"),"")));
			
			ListOrderedMap lomMn = new ListOrderedMap();
			ListOrderedMap lomVc = new ListOrderedMap();
			ListOrderedMap lomPrevMn = new ListOrderedMap();
			
			vo.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			vo.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
			List<?> listMn = considerationService.returnListMndeptcnsd(vo);	
			List<?> listVc = considerationService.returnListVcdeptcnsd(vo);			
			
			if(listMn !=null && !listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}else{
				//최종본의뢰인 경우 이전 의뢰 에 정검토 부서에 사안개요 및 담당자 메모 표시 하기
				if(!"".equals(vo.getPrev_cnsdreq_id()) && "C04301".equals(lomRq.get("cnsd_status"))){
					vo.setSubmit_status("prev");
					vo.setPrev_cnsdreq_id(vo.getPrev_cnsdreq_id());
					List listPrevMn = considerationService.returnListMndeptcnsd(vo);
					if(listPrevMn !=null && !listPrevMn.isEmpty()){
						lomPrevMn= (ListOrderedMap)listPrevMn.get(0);
						lomMn.put("main_matr_cont", StringUtil.convertNamoReplaceAll((String)lomPrevMn.get("main_matr_cont")));
					}
				}
			}
			if(listVc != null && !listVc.isEmpty()){
				lomVc= (ListOrderedMap)listVc.get(0);
			}
			
			if("N".equals(vo.getPlndbn_req_yn())){
				ListOrderedMap returnLom= considerationService.getFilevalidate(vo);
				form.setFile_validate((String)returnLom.get("fileValidate"));
			}else{
				form.setFile_validate("N");  //최종본 회신일경우 처리 필요	
			}
			ArrayList<?> lomTs = (ArrayList<?>)listTs;
			ArrayList<?> lomCs = (ArrayList<?>)listCs;	
			
			form.setTop_role(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTop_role(),"")));
			form.setBlngt_orgnz(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBlngt_orgnz(),"")));
			form.setMn_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getMn_cnsd_dept(),"")));
			form.setVc_cnsd_dept(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getVc_cnsd_dept(),"")));
			form.setCnsd_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCnsd_status(),"")));
			form.setRespman_apnt_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRespman_apnt_yn(),"")));
			
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_inner_i.jsp";   //계약 상세 변경
			
			vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
			String contRc = (String)((HashMap)considerationService.listRelationContract(vo)).get("contRc") ;
			
			// 새로 추가된 내용 top30, Source of contract drafts,  Related products
			// TOP_30_CUS
			HashMap comboMap = new HashMap() ;
			
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("top_30_cus"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			comboMap.put("DEL_YN", "N");
			String cntrt_top30_cus = clmsComService.listComCdByGrpCd(comboMap) ;	
			
			// Source of contract drafts
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("GRP_CD", "SOC01");
			comboMap.put("SELECTED", lomRq.get("cont_draft"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			comboMap.put("DEL_YN", "N");
			String srcConDraft = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// Related products
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("GRP_CD", "RP01");
			comboMap.put("SELECTED", lomRq.get("related_products"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			comboMap.put("DEL_YN", "N");
			String relPrdCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			lomRq.put("srcConDraft", srcConDraft) ; //  Source of contract drafts
			lomRq.put("relPrdCombo", relPrdCombo) ; // Related products
			lomRq.put("cntrt_top30_cus", cntrt_top30_cus) ; // Related products
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);		
			
			mav.addObject("lomTs", lomTs);	//특화속성[계약관리]
			mav.addObject("lomCs", lomCs);	//특화속성[계약관리]
			
			mav.addObject("lomMn", lomMn); //일반조항 검토의견
			mav.addObject("lomVc", lomVc); //IP 조항 검토의견
			mav.addObject("listCa", listCa);	// 관련자 리스트
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCs", listCs);
			mav.addObject("contRc", contRc);
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailContractMaster() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailContractMaster() Throwable !!");
		}
	}
	
	/**
	 * 계약 상세 내역 수정 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void modifyContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			JSONObject jo = new JSONObject();
			HashMap mapRt = new HashMap();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setReg_operdiv(vo.getSession_blngt_orgnz());
			
			// 2014-04-17 Kevin added.
			if(request.getParameter("gerpCostType") != null){
				vo.setGERPCostType(request.getParameter("gerpCostType").toString());
			}
			if(request.getParameter("gerpContractType") != null){
				vo.setGERPContractType(request.getParameter("gerpContractType").toString());
			}
			if(request.getParameter("gerpDivCode") != null){
				vo.setGERPDivCode(request.getParameter("gerpDivCode").toString());
			}
						
			//계약 상세 내역 변경 
			mapRt= considerationService.modifyContractMaster(vo);
						
    		jo.put("returnVal", 	mapRt.get("returnVal"));
    		jo.put("returnMsg", 	mapRt.get("returnMsg"));
			
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
	 * 마스터테이블정보 리턴 -- (구)법무시스템 검토의뢰 데이터
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailOldContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session 	= request.getSession(false);
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_inner_d.jsp";				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;

			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			//정검토부서
			form.setMn_cnsd_dept(vo.getMn_cnsd_dept());
			//부검토부서
			form.setVc_cnsd_dept(vo.getVc_cnsd_dept());
			//예정본의뢰여부
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			List listDc = considerationService.detailContractMaster(vo);			
				
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			ListOrderedMap lomMn = new ListOrderedMap();
			ListOrderedMap lomVc = new ListOrderedMap();
			
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			List listMn = considerationService.returnListMndeptcnsd(vo);	
			
			if(listMn !=null && !listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}			
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);		
			
			mav.addObject("lomMn", lomMn); //일반조항 검토의견
			
			mav.addObject("listDc", listDc);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Throwable !!");
		}
	}
	
	/**
	 * 마스터테이블정보 리턴 -- (구)법무시스템 검토의뢰 데이터_print 화면
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailOldContractMasterPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session 	= request.getSession(false);
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_inner_p.jsp";				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			//정검토부서
			form.setMn_cnsd_dept(vo.getMn_cnsd_dept());
			//부검토부서
			form.setVc_cnsd_dept(vo.getVc_cnsd_dept());
			//예정본의뢰여부
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			List listDc = considerationService.detailContractMaster(vo);			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			ListOrderedMap lomMn = new ListOrderedMap();
			ListOrderedMap lomVc = new ListOrderedMap();
			
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			List listMn = considerationService.returnListMndeptcnsd(vo);	
			
			if(listMn !=null && !listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}			
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);		
			
			mav.addObject("lomMn", lomMn); //일반조항 검토의견
			
			mav.addObject("listDc", listDc);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Throwable !!");
		}
	}
	
	/**
	 * 이관요청
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void applyTrans(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.applyTrans(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 법무부서 이관
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void transLawDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.transLawDept(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 유관부서검토요청
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void applyResp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.applyResp(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 이관불필요
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void denyTrans(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			considerationService.denyTrans(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 이관승인
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void approveTrans(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.approveTrans(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 이관거부
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void disapproveTrans(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.disapproveTrans(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 담당자 지정
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void confirmRespman(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			//법무담당자 등록
			String list_respman_ids[] = vo.getList_respman_ids();
			
			String respman = "";
			
			if(vo.getList_respman_ids() != null){
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
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));			// 그룹장 화면 구분
			
			
			JSONObject jo 		= new JSONObject();
			int result = 0;
			result = considerationService.confirmRespman(vo);
			if(result > 0){
				if(vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())){						// 정 검토부서 담당자 지정
					mailSendService.sendConsiderationRespmanMailSend(vo);
				}
			}
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
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
	 * 담당자 의견 승인 처리
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void approvalOpnn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			String 	 		 topRole= "";		// 사용자 Role
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;

			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//전자검토자 회신 가능토록 변경(2013.07.24)
			if(tmpSessionRoleList.contains("RB01")){
				topRole = "RB01";
			}
			
			//권한직위
			form.setTop_role(topRole);
			
			/*********************************************************
			 * Service
			**********************************************************/
			if("RESP".equals(vo.getStat_flag())){
				vo.setEvent_but("[B04]");
			}
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));				// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.approvalOpnn(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.approve",  null, new RequestContext(request).getLocale()));
			
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
	 * 담당자 의견 반려 처리
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void rejctOpnn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service 
			**********************************************************/
			if("REJECT".equals(vo.getStat_flag()) && vo.getMn_cnsd_dept().equals(vo.getBlngt_orgnz())){			// 정_부서 검토의견 반려
				vo.setEvent_but("[B04]");	// 회신
			}else if("REJECT".equals(vo.getStat_flag()) && vo.getVc_cnsd_dept().equals(vo.getBlngt_orgnz())){	// 부_부서 검토의견 반려
				vo.setEvent_but("[B02]");	// 의견전달
			}else{
				vo.setEvent_but("");
			}
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));					// 그룹장 화면 구분
			
			JSONObject jo 		= new JSONObject();

			considerationService.rejctOpnn(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.return",  null, new RequestContext(request).getLocale()));
			
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
	 * 선택 되지 않은 계약건의 필수 조건 체크
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void requiredValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			ListOrderedMap requiredLom = new ListOrderedMap();
			
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/	
			requiredLom = considerationService.searchRequired(vo);
			if("OK".equals(requiredLom.get("ret_msg"))){
				requiredLom.put("result", "Y");
				requiredLom.put("message", (String)messageSource.getMessage("las.page.field.consideration.requiredValidation01", null, new RequestContext(request).getLocale()));//정상처리 되었습니다.
				
			}else{//필수 체크에 걸림 				
				requiredLom.put("result", "N");
				requiredLom.put("message", (String)messageSource.getMessage("las.page.field.consideration.requiredValidation02", null, new RequestContext(request).getLocale())+" ");//다른 계약건에 입력되지 않은 필수 입력 항목이 존재 합니다.
			}
			
			JSONObject jo = new JSONObject();
			jo.put("result", requiredLom.get("result"));
			jo.put("message",requiredLom.get("message"));
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
	 * 임시저장/의견전달/발신  - 계약관리_계약별_부서검토	TN_CLM_CONTRACT_DEPTCNSD
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void returnConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			HttpSession session = request.getSession(false);
			
			if(session==null) throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/		
			JSONObject jo = new JSONObject();
			HashMap<String, Comparable> mapRt = new HashMap<String, Comparable>();			
			
			/*********************************************************
			 * 접근 권한 설정 
			 * 임시저장([B01]), 의견전달([B02]), 발신([B03]), 회신([B04]) 버튼, 유관부서검토요청[B06], 기타버튼[B99]	
			**********************************************************/
			
			if("SAVE".equals(vo.getStat_flag())){
				vo.setEvent_but("[B01]");
			}else if("TABSAVE".equals(vo.getStat_flag())){
				vo.setEvent_but("[B01]");
			}else if("DELIVERY".equals(vo.getStat_flag())){
				vo.setEvent_but("[B02]");
			}else if("DISPATCH".equals(vo.getStat_flag())){
				vo.setEvent_but("[B03]");
			}
			
			// 2014-07-23 Kevin added.
			// if the request is final review then update the contract as final review.
			if(vo.getStat_flag().equals("DISPATCH")){
				if((request.getParameter("plndbn_req_yn") != null && request.getParameter("plndbn_req_yn").equals("Y"))){
					considerationService.updateContractAsFinalReview(vo);
				}
			}
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));
			
			//계약 상태 재조회 후 보류 상태이면 메시지 전달
			List list = considerationService.detailContractMaster(vo);
			String depthStatus = "";
			if(null != list && list.size() > 0){
				ListOrderedMap lomRq = null;
				if(list != null && list.size() > 0){
					lomRq= (ListOrderedMap)list.get(0);
					depthStatus = (String)lomRq.get("depth_status");
					
					if("C02607".equals(depthStatus)){ //보류
						mapRt.put("returnVal", 2);
						mapRt.put("returnMsg", messageSource.getMessage("las.msg.error.hold",  null, new RequestContext(request).getLocale()));
					}else{
						if("TABSAVE".equals(vo.getStat_flag())){
							mapRt= considerationService.returnTabConsideration(vo);
						}else{
							mapRt= considerationService.returnConsideration(vo);
						}
					}
				}
			}
			
			//법무 검토회신 발신
			//1.전결 계약일 경우 의뢰자에게 메일 전송
			if(mapRt.get("mail_send_yn").equals("Y")){
				//mailSendService.sendConsiderationReplyMailSend(vo);
				mailSendService.sendEmailReviewReplied(vo);
			}
			
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
	
	/**유관부서팝업  
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listConsideration_under_d(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String 	forwardURL    	= "/WEB-INF/jsp/las/contractManager/Consideration_under_p.jsp";
			String 	returnMessage 	= "";
			List 	resultList		= null;
		
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			bind(request, form);
			bind(request, vo);
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			vo.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = considerationService.listConsideration_under_d(vo);
			
			ListOrderedMap lom = null;
			if(resultList != null && resultList.size() > 0){
				lom= (ListOrderedMap)resultList.get(0);
			}else{
				lom = new ListOrderedMap();
			}

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
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
	 * 이력정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listContractHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    	= "";
			ExecutionForm form      = null;
			ExecutionVO   vo        = null;
			List resultList			= null;
			List cnsdList			= null;		// 담당자의견 및 반려 이력
			
			ArrayList review 		= null;
			ArrayList approve 		= null;
			ArrayList info 			= null;
			/*********************************************************
			 * Forward setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/common/historyInfo.jsp";		//Sungwoo Replaced 2014-09-12
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ExecutionForm();
			vo = new ExecutionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setDept_div("MN");
			
			resultList = considerationService.listHisExecution(vo);
			
			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList)resultList.get(0);
				approve = (ArrayList)resultList.get(1);
				info = (ArrayList)resultList.get(2);
			}
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			// 부서 검토 승인, 반려 이력 조회
			cnsdList = considerationService.listHisDeptCnsdRejct(vo);
			
			mav.setViewName(forwardURL);
			
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("info", info);
			mav.addObject("cnsdList", cnsdList);		// 부서 검토 승인, 반려 이력
			mav.addObject("hisCommand", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContractHis() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContractHis() Throwable !!");
		}
	}
	
	/**
	 * 의견전달, 발신 취소
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			form.setPage_div(StringUtil.bvl(form.getPage_div(),""));		// 그룹장 화면 구분
			
			considerationService.processCancel(vo);
			return detailConsideration(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.processCancel() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.processCancel() Throwable !!");
		}
	}
	
	/**
	 * 연관계약 insert/delete
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void actionRelationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		try {
			HttpSession session 	= request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/	        
			int returnVal = -1;		
	        /*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/						
			
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			returnVal = considerationService.actionRelationContract(vo);
			    		
			JSONObject jo = new JSONObject();
			jo.put("returnVal", returnVal);
    		
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
	 * 연관계약 정보 select
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listRelationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			//계약 id 전달
			HashMap result = considerationService.listRelationContract(vo);
	
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print((String)result.get("contRc"));
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
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
	 * 권한체크
	 * @param vo ConsultationVO
	 * @return Boolean
	 * @throws Exception
	 */
	public String checkAuth(ConsultationVO vo, String btn_div, String status_div, String respman, String page_div) throws Exception {
		/*********************************************************
		 * roleCheck
		**********************************************************/
		String checkYN = "";
		HashMap chkHm = new HashMap();
		
		if(!page_div.equals("")){
			page_div = "_" + page_div;
		}
		
		chkHm.put("SYS_CD", vo.getSys_cd());
		chkHm.put("USER_ID",vo.getSession_user_id());
		chkHm.put("CNSDREQ_ID",vo.getCnsdreq_id() );
		chkHm.put("CNTRT_ID", vo.getCntrt_id());
		chkHm.put("RC_FLAG", "R");
		chkHm.put("EVENT_BUT", btn_div);
		chkHm.put("MENU_ID", vo.getMenu_id() + page_div);
		chkHm.put("LAS_MEM", respman);
		
		checkYN = clmsComService.getTrCheck2(chkHm, status_div);

		return checkYN;
	}
	
	/**
	 * 사업부이관 수정이동
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailConsiderationForModify(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			String 	 	topRole		= "";		// 사용자 Role
			ListOrderedMap lomMn= new ListOrderedMap();
			ListOrderedMap lomVc= new ListOrderedMap();
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_d.jsp";		// 일반의뢰

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
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
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
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
			
			form.setCntrt_srch_yn("N");
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 

			vo.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
			vo.setCnclsnpurps_bigclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_bigclsfcn"),""));
			vo.setCnclsnpurps_midclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_midclsfcn"),""));
			
			vo.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			vo.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
			 
			List listMn = considerationService.returnListMndeptcnsd(vo);;
			List listVc = considerationService.returnListVcdeptcnsd(vo);
			
			if(!listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}
			if(!listVc.isEmpty()){
				lomVc= (ListOrderedMap)listVc.get(0);
			}
			
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
				if(cntrtInfoGbn.equals("C05402") || cntrtInfoGbn.equals("C05403")) {
					forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_u.jsp";	// (구)법무시스템 검토 데이터 조회 페이지로 변경
				}
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);
			mav.addObject("lomRm", lomRm);		// 관련자 List
			
			mav.addObject("lomMn", lomMn);		// 관련자 List
			mav.addObject("lomVc", lomVc);		// 관련자 List
			
			mav.addObject("listDc", listDc);
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailConsideration() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailConsideration() Throwable !!");
		}
	}
	
	/**
	 * 사업부 이관 - 계약마스터 조회
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailOldContractMasterForUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session 	= request.getSession(false);
			String 	 	topRole		= "";		// 사용자 Role
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_inner_u.jsp";				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
		
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(tmpSessionRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(tmpSessionRoleList.contains("RA02")){
				topRole = "RA02";
			}else if(tmpSessionRoleList.contains("RA03")){
				topRole = "RA03";
			}
			
			//소속조직
			form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			//권한직위
			form.setTop_role(topRole);
			//정검토부서
			form.setMn_cnsd_dept(vo.getMn_cnsd_dept());
			//부검토부서
			form.setVc_cnsd_dept(vo.getVc_cnsd_dept());
			//예정본의뢰여부
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			List listDc = considerationService.detailContractMaster(vo);			
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			ListOrderedMap lomMn = new ListOrderedMap();
			ListOrderedMap lomVc = new ListOrderedMap();
			
			vo.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			List listMn = considerationService.returnListMndeptcnsd(vo);	
			
			if(listMn !=null && !listMn.isEmpty()){
				lomMn= (ListOrderedMap)listMn.get(0);
			}			
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			mav.addObject("lomRq", lomRq);		
			
			mav.addObject("lomMn", lomMn); //일반조항 검토의견
			
			mav.addObject("listDc", listDc);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.detailOldContractMaster() Throwable !!");
		}
	}
	
	/**
	 *  사업부이관 데이터 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyOldConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String forwardURL	= "";
			
			/*********************************************************
			 * form binding
			**********************************************************/
			ConsultationForm form	= new ConsultationForm();
			ConsultationVO vo		= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			ModelAndView mav = new ModelAndView();

			if(form.getStatus_mode() != null && "myContract".equals(form.getStatus_mode())) { //계약으로 이동
				forwardURL = "/clm/manage/myContract.do?method=listMyContract";
			}else{ //법부로 이동
				forwardURL = "/las/contractmanager/consideration.do?method=listConsideration";
			}
			
			int result = considerationService.modifyOldConsideration(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
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
	 * 사별 이관 수동 등록 이동
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertyOldConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			String forwardURL	= "";
			
			/*********************************************************
			 * form binding
			**********************************************************/
			ConsultationForm form	= new ConsultationForm();
			ConsultationVO vo		= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			ModelAndView mav = new ModelAndView();

			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_old_i.jsp";
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
	 * 사별이관건 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertOldConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String forwardURL	= "";
			
			/*********************************************************
			 * form binding
			**********************************************************/
			ConsultationForm form	= new ConsultationForm();
			ConsultationVO vo		= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			ModelAndView mav = new ModelAndView();
			
			String comp_cd = "";
			
			comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			
			vo.setReg_operdiv(comp_cd);
				
			int result = considerationService.insertOldConsideration(vo);
			
			
			// 추후 어디로 이동하게 될지 검토 후 작업 김재원.!@#.20130408
			if(form.getStatus_mode() != null && "myContract".equals(form.getStatus_mode())) { //계약으로 이동
				forwardURL = "/clm/manage/myContract.do?method=listMyContract";
			}else{ //법부로 이동
				forwardURL = "/las/contractmanager/consideration.do?method=listConsideration";
			}
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
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
	 * 사업부이관 계약건 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteOldConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String forwardURL	= "";
			
			/*********************************************************
			 * form binding
			**********************************************************/
			ConsultationForm form	= new ConsultationForm();
			ConsultationVO vo		= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			ModelAndView mav = new ModelAndView();

			forwardURL = "/las/contractmanager/consideration.do?method=listConsideration";
			
			int result = considerationService.deleteOldConsideration(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
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
	 * 과거 계약검토 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailConsideration_old(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String page_div		 = "";
			
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;	
			PageUtil 	pageUtil	= null;
			String 	 	topRole		= "";		// 사용자 Role
			ListOrderedMap lomMn= new ListOrderedMap();
			ListOrderedMap lomVc= new ListOrderedMap();
			List relationList 		= null; //연관계약 list
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/review/Consideration_d_old.jsp";		// 일반의뢰

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();
						
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
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
			
			if(userRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(userRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(userRoleList.contains("RA02")){
				topRole = "RA02";
			}
			
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
			lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"")));
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
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
			mav.addObject("lomRq"		, lomRq);				// 검토의뢰
			mav.addObject("lomResp"		, lomResp);			// 담당자 List
			mav.addObject("lomDcd"		, lomDcd);			// B.계약별_부서검토반려
			mav.addObject("lomRm"		, lomRm);				// 관련자 List
			mav.addObject("lomMn"		, lomMn);
			mav.addObject("listDc"		, listDc);
			mav.addObject("command"		, form);
			
			//연관계약
			mav.addObject("relationList"	, relationList);
			mav.addObject("contRc"			, contRc);
			
			if(lomRq.get("sys_nm")!=null){
				mav.addObject("sys_nm", (String)lomRq.get("sys_nm"));
			}
			mav.addObject("modYn"		, modYn);
			mav.addObject("respYn"		, respYn);
			
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
	 * Return 사유 입력 [팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_opnn_p.jsp";
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

			form.setCnsdreq_id(vo.getCnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			//mav.addObject("lomRq", lomRq);
			//mav.addObject("listDc", listDc);
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
	 * return 의뢰건 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void returnRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			ConsiderationVO vo = new ConsiderationVO();		
			ConsiderationForm form = new ConsiderationForm();		
			
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
			int return_val = considerationService.returnRequest(vo);	
			
			/*********************************************************
			 * Admin Mail Reply added Sungwoo 2014-07-23 
			**********************************************************/
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("main_title", messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, new RequestContext(request).getLocale()));
			hm.put("reqman_info", vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
			hm.put("cnsdreq_id", vo.getCnsdreq_id());
			hm.put("cntrt_id", vo.getCntrt_id()); 
			hm.put("locale", vo.getSession_user_locale());
			hm.put("mail_content", messageSource.getMessage("clm.page.field.mailsend.sendConsiderationReplyMailSend06", null, new RequestContext(request).getLocale()));
			hm.put("request", "request");					//Sungwoo added 2014-06-26
			mailSendService.sendReqInfoMail(hm);
				
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
	 * Close 사유 조회 [팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popCloseCont(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_closeRTN_p.jsp";
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

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			resultList = considerationService.closeRTNView(vo);
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * Close 사유 입력 [팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popCloseReq(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_close_p.jsp";
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

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			
			form.setCnsdreq_id(vo.getCnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * CLOSE 의뢰건 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void closeRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			ConsiderationVO vo = new ConsiderationVO();		
			ConsiderationForm form = new ConsiderationForm();		
			
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
			int return_val = considerationService.closeRequest(vo);	
		
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
	 * 검토 상세에서 상시 관련자 추가 기능 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void modifyRefCCAJAX(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			ConsultationVO vo = new ConsultationVO();		
			ConsultationForm form = new ConsultationForm();		
			
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
			int return_val = considerationService.modifyRefCCAJAX(vo);	
		
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
	 * 수정할 최종 legal opinion을 가지고 와서 popup화면에서 보여준다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLegalOpinion(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_addLegalOpinion_p.jsp";
			List resultList = null;
			String topRole = "";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
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
			
			if(userRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(userRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(userRoleList.contains("RA02")){
				topRole = "RA02";
			}
			
			vo.setTop_role(topRole);
			vo.setDepth_status(form.getCon_depth_status());

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			resultList = considerationService.forwardLegalOpinion(vo);
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			
			
			form.setTop_role(topRole);
			
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
	 * 검토의견을 추가로 입력할수 있다. 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateLegOpinion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			List resultList = null;
			ListOrderedMap lom = new ListOrderedMap();
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			ConsultationVO    vo = new ConsultationVO();
			ConsultationForm  form = new ConsultationForm();
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//검토의견 관련 세팅
			vo.setCnsd_opnn(form.getCnsd_opnn()); // 검토 의견
			vo.setCntrt_id(form.getCntrt_id());   // 계약 ID
			vo.setCnsdreq_id(form.getCnsdreq_id()); // 의뢰 ID
			vo.setDepth_status(form.getCon_depth_status()); // update 분기를 위한 상태값.
			vo.setTop_role(form.getTop_role());
			
			//메일 내용 추가
			resultList = considerationService.forwardLegalOpinion(vo);
			
			if(resultList.size() > 0){
				lom = (ListOrderedMap)resultList.get(0);
				
				vo.setCnsdreq_id((String)lom.get("cnsdreq_id"));
				vo.setReq_title((String)lom.get("req_title"));
				vo.setCntrt_nm((String)lom.get("cntrt_nm"));
			}
			
			
			//XSS 처리	

			/*********************************************************
			 * Service
			**********************************************************/
			int return_val = considerationService.updateLegOpinion(vo);	
		
			/*********************************************************
			 * AJAX 세팅
			**********************************************************/
			
			//법무 검토회신 발신
			//1.검토의견 변경 되면 의뢰자에게 메일 전송   
			if(return_val > 0){
				mailSendService.sendMailSendModiLegalOpinion(vo);
				
			}
			
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
	 * 계약에 대한 체크 리스트 항목을 보여 준다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView actionCheckItem(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_action_CheckItem_p.jsp";
			
			List insertResultList = null;
			String rea_item_cd = "";
			String remark = "";
			ListOrderedMap lom = null;
			String cnt = "";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setCntrt_id(form.getCntrt_id());

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			
			insertResultList = considerationService.selectCheckItem(vo);
			
			int insertResultListSize = insertResultList.size();
			lom = new ListOrderedMap();
			
			ModelAndView mav = new ModelAndView();
			
			if(0 < insertResultListSize){
				for(int i=0; i < insertResultListSize; i++){
					lom = (ListOrderedMap)insertResultList.get(0);
					
					cnt = (Integer)lom.get("cnt") + "";
				}
				
			}

			mav.setViewName(forwardURL);	
			
			mav.addObject("insertResultList", insertResultList);
			mav.addObject("insertResultListSize", insertResultListSize);
			mav.addObject("cnt", cnt);
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
	 * HQ로 의뢰 할 수 있는 팝업을 띄어 준다. 임시 저장 건도 있기 때문에 내용을 조회해야 한다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardRequestHQ(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_requestHQ_p.jsp";
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
			
			String cntrt_id = StringUtil.nvl(form.getCntrt_id(), "");
			String cnsdreq_id = StringUtil.nvl(form.getCnsdreq_id(), "");
			String hq_cnsdreq_id = StringUtil.nvl(form.getHq_cnsdreq_id(), "");
			String prev_cnsdreq_id = StringUtil.nvl(form.getPrev_cnsdreq_id(),"");
			
			String db_cntrt_id = "";
			String db_cnsdreq_id = "";
			String db_hq_cnsdreq_id = "";
			String db_cnsd_level = "";
			String db_hq_cnsd_status = "";
			String db_hq_req_yn  = "";
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			resultList = considerationService.forwardRERequestHQ(vo);
			
			int resultListSize = resultList.size();
			
			if(0 < resultListSize){
				ListOrderedMap result = (ListOrderedMap)resultList.get(0);
				db_cntrt_id = (String)result.get("cntrt_id");
				db_cnsdreq_id = (String)result.get("cnsdreq_id");
				db_hq_cnsdreq_id = (String)result.get("hq_cnsdreq_id");
				db_cnsd_level    =  (BigDecimal)result.get("cnsd_level")+"";
				db_hq_cnsd_status = (String)result.get("hq_cnsd_status");
				db_hq_req_yn = (String)result.get("hq_req_yn");
			}
			
			if("".equals(db_cntrt_id) || "".equals(db_cnsdreq_id)){
				form.setCntrt_id(cntrt_id);
				form.setCnsdreq_id(cnsdreq_id);
				form.setHq_cnsdreq_id(hq_cnsdreq_id);
				form.setHq_cnsd_status(db_hq_cnsd_status);
				form.setPrev_cnsdreq_id(prev_cnsdreq_id);
			} else {
				form.setCntrt_id(db_cntrt_id);
				form.setCnsdreq_id(db_cnsdreq_id);
				form.setHq_cnsdreq_id(db_hq_cnsdreq_id);
				form.setCnsd_level(db_cnsd_level);
				form.setHq_cnsd_status(db_hq_cnsd_status);
				form.setPrev_cnsdreq_id(prev_cnsdreq_id);
			}
			
			form.setHq_req_title(form.getHq_req_title());
			form.setHq_req_yn(db_hq_req_yn);

			mav.setViewName(forwardURL);	
			
			mav.addObject("resultList", resultList);
			mav.addObject("resultListSize", resultListSize);
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
	 * HQ로 재 의뢰 할 수 있는 팝업을 띄어 준다. 임시 저장 건도 있기 때문에 내용을 조회해야 한다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardRERequestHQ(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/review/Consideration_requestHQ_p.jsp";
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
			
			String cntrt_id = StringUtil.nvl(form.getCntrt_id(), "");
			String cnsdreq_id = StringUtil.nvl(form.getCnsdreq_id(), "");
			String hq_cnsdreq_id = StringUtil.nvl(form.getHq_cnsdreq_id(), "");
			
			String db_cntrt_id = "";
			String db_cnsdreq_id = "";
			String db_hq_cnsdreq_id = "";
			String db_hq_cnsd_status = "";
			String db_hq_req_yn = "";
			BigDecimal db_cnsd_level = new BigDecimal(0);
			BigDecimal ass = new BigDecimal(1);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			resultList = considerationService.forwardRERequestHQ(vo);
			
			int resultListSize = resultList.size();
			
			if(0 < resultListSize){
				ListOrderedMap result = (ListOrderedMap)resultList.get(0);
				db_cntrt_id = (String)result.get("cntrt_id");
				db_cnsdreq_id = (String)result.get("cnsdreq_id");
				db_hq_cnsdreq_id = (String)result.get("hq_cnsdreq_id");
				db_cnsd_level = (BigDecimal)result.get("cnsd_level");
				db_hq_cnsd_status = (String)result.get("hq_cnsd_status");
				db_hq_req_yn = (String)result.get("hq_req_yn");
			}
			
			if("C16201".equals(db_hq_cnsd_status) ){
				form.setCnsd_level(db_cnsd_level+"");
			} else {
				form.setCnsd_level(db_cnsd_level.add(ass)+"");
			}
			
			if("".equals(db_cntrt_id) || "".equals(db_cnsdreq_id)){
				form.setCntrt_id(cntrt_id);
				form.setCnsdreq_id(cnsdreq_id);
				form.setHq_cnsdreq_id(hq_cnsdreq_id);
				form.setHq_cnsd_status("C16203");
			} else {
				form.setCntrt_id(db_cntrt_id);
				form.setCnsdreq_id(db_cnsdreq_id);
				form.setHq_cnsdreq_id(db_hq_cnsdreq_id);
				form.setHq_cnsd_status("C16203");
			}
			
			form.setHq_req_yn(db_hq_req_yn);

			mav.setViewName(forwardURL);	
			
			mav.addObject("resultList", resultList);
			mav.addObject("resultListSize", resultListSize);
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
	 * HQ로 의뢰 임시 저장을 한다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public void insertHqRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsiderationForm form = new ConsiderationForm();
			ConsiderationVO vo = new ConsiderationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			vo.setHq_cnsd_status("C16201");   // 법인 검토자(변호사)가 임시 저장을 할 경우 상태 값.
			
			int result = considerationService.insertHqRequest(vo);
			
			if(result > 0){
				
			} else {
				
			}
			
            JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("return_val", String.valueOf(result));
			
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
	 * HQ로 의뢰를 한다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public void modifyHqRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsiderationForm form = new ConsiderationForm();
			ConsiderationVO vo = new ConsiderationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			int result = considerationService.insertHqRequest(vo);
			
			vo.setCnsd_level((String)form.getCnsd_level());
			
			if(result > 0){
				
			} else {
				
			}
			
            JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("return_val", String.valueOf(result));
			
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
	 * 필수 항목 중 No로 체크한 내용을 저장을 한다.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public void insertCheckReson(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsiderationForm form = new ConsiderationForm();
			ConsiderationVO vo = new ConsiderationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			
			int result = considerationService.insertCheckReson(vo);
			
			if(result > 0){
				
			} else {
				
			}
			
            JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("return_val", String.valueOf(result));
			
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
	 * 필수 항목 15개 바로 조회 해오기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//public void returnConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
	public void listCheckListReal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		try {		
			List   		resultList 	= null;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session 	= request.getSession(false);
			String 	 	topRole		= "";		// 사용자 Role
			String 		authYN    	= "";
			List chekItemList 		= null; //필수항목 체크 list
			int chekItemListSize    = 0;
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL ;				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			JSONObject jo = new JSONObject();
			HashMap mapRt = new HashMap();
			JSONArray cell = new JSONArray();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			
			/*********************************************************
			 * Service
			**********************************************************/
			//계약 상세 내역 변경 
			chekItemList = considerationService.listCheckList(vo);
			chekItemListSize = chekItemList.size();
						
    		ListOrderedMap lom = new ListOrderedMap();
    		
    		if(chekItemListSize > 0){
    			for(int i=0;i<chekItemListSize;i++){
    				lom = (ListOrderedMap)chekItemList.get(i);
    				
    				JSONObject jo2 = new JSONObject();
    				//jo2.put("NO", (BigDecimal)lom.get("NO"));
    				jo2.put("CD_NM",  (String)lom.get("CD_NM"));
    				jo2.put("REMARK",StringUtil.convertEnterToBR((String)lom.get("REMARK")));
    				
    				cell.add(jo2);
    				
    			}
    		}
    		
    		// 필수 항목 체크
			mav.addObject("chekItemList2"		, chekItemList);
			mav.addObject("chekItemListSize2"		, chekItemListSize);
			
//			jo.put("returnVal", 	mapRt.get("returnVal"));
//    		jo.put("returnMsg", 	mapRt.get("returnMsg"));
    		//jo.add(cell);
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(cell);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception((String)e.toString());
			
//			JSONObject jo 		= new JSONObject();
//    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
//    		
//			response.setContentType("application/json; charset=UTF-8");
//    		PrintWriter out 	= response.getWriter();
//    		out.print(jo);
//    		out.flush();
//    		out.close();
    		
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
	
}