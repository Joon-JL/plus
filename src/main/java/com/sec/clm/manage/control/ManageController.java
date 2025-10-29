/**
* Project Name : 계약관리시스템
* File Name : ManageController.java
* Description : 계약공통 Controller
* Author : 신남원
* Date : 2010.09.27
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ManageForm;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.service.ManageService;
import com.sec.common.util.ClmsDataUtil;
// 2014-05-08 Kevin added.
import com.sec.GFI_ECC_FI.FIA.SELMSPlusDataService;
import com.sec.GFI_ECC_FI.FIA.SELMSPlusDataVO;

public class ManageController extends CommonController {
	private ManageService manageService;
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	
	/**
	* 계약 공통 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CommonForm cmd =  (CommonForm)request.getAttribute("command");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			HttpSession session = request.getSession(false);
			
			// 전자 변호사인 경우 체크
			boolean elUserlYn ;
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setStatus_mode((String)request.getAttribute("status_mode"));
			vo.setStatus_mode(form.getStatus_mode());
			
			//1. 의뢰명 또는 계약명
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
			// 1. 계약명
			form.setSrch_cntrt_nm(StringUtil.bvl(form.getSrch_cntrt_nm(), ""));
			vo.setSrch_cntrt_nm(form.getSrch_cntrt_nm().toUpperCase());
			
			//2. 의뢰자 명
			form.setSrch_reqman_nm(StringUtil.bvl(form.getSrch_reqman_nm(), ""));
			vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(form.getSrch_reqman_nm().toUpperCase()));
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			//4. 계약담당자 명
			form.setSrch_respman_nm(StringUtil.bvl(form.getSrch_respman_nm(), ""));
			vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm().toUpperCase()));
			//5. 계약담당부서
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), ""));
			vo.setSrch_resp_dept(StringUtil.convertHtmlTochars(form.getSrch_resp_dept()));
			form.setSrch_resp_dept_nm(StringUtil.bvl(form.getSrch_resp_dept_nm(), ""));
			vo.setSrch_resp_dept_nm(StringUtil.convertHtmlTochars(form.getSrch_resp_dept_nm()));
			//6. 계약상대방
			form.setSrch_oppnt_cd(StringUtil.bvl(form.getSrch_oppnt_cd(), ""));
			vo.setSrch_oppnt_cd(StringUtil.convertHtmlTochars(form.getSrch_oppnt_cd()));
			form.setSrch_oppnt_nm(StringUtil.bvl(form.getSrch_oppnt_nm(), ""));
			vo.setSrch_oppnt_nm(StringUtil.convertHtmlTochars(form.getSrch_oppnt_nm()));
			//7. 법무검토자
			form.setSrch_cnsdman_nm(StringUtil.bvl(form.getSrch_cnsdman_nm(), ""));
			vo.setSrch_cnsdman_nm(StringUtil.convertHtmlTochars(form.getSrch_cnsdman_nm().toUpperCase()));
			//8. 비즈니스 단계
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
			//9. 대분류
			form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(), ""));
			vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
			
			if(("20130319155330501_0000000362").equals(form.getMenu_id())){//원본접수일경우
				//10. 단계
				form.setSrch_step("C02503");
				vo.setSrch_step("C02503");
				//11. 상태
				form.setSrch_state("C02642");
				vo.setSrch_state(form.getSrch_state());
			}else{ 
				//10. 단계
				form.setSrch_step(StringUtil.bvl(form.getSrch_step(), ""));
				vo.setSrch_step(form.getSrch_step());
				//11. 상태
				form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
				vo.setSrch_state(form.getSrch_state());
			}
			
			//12. 리스트구분
			form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
			vo.setList_mode(form.getList_mode());
			//15. 계약체결일
			form.setSrch_start_cnlsnday(StringUtil.bvl(form.getSrch_start_cnlsnday(), ""));
			vo.setSrch_start_cnlsnday(form.getSrch_start_cnlsnday().replace("-", ""));
			form.setSrch_end_cnlsnday(StringUtil.bvl(form.getSrch_end_cnlsnday(), ""));
			vo.setSrch_end_cnlsnday(form.getSrch_end_cnlsnday().replace("-", ""));
			form.setSrch_if_sys_cd(StringUtil.bvl(form.getSrch_if_sys_cd(), ""));
			vo.setSrch_if_sys_cd(form.getSrch_if_sys_cd());
			
			// 16. 계약번호
			form.setSrch_cntrt_no(StringUtil.bvl(form.getSrch_cntrt_no(), "")) ;
			vo.setSrch_cntrt_no(form.getSrch_cntrt_no()) ;
			
			//17.closed_YN
			form.setClosed_yn(StringUtil.bvl(form.getClosed_yn(), ""));
			vo.setClosed_yn(form.getClosed_yn());
			
			// 상세 검색 6 체결 예정 계약서 최종 확인 시작
			form.setSrch_str_org_acptday(StringUtil.bvl(form.getSrch_str_org_acptday(), ""));
			vo.setSrch_str_org_acptday(form.getSrch_str_org_acptday().replace("-", ""));
			
			// 상세 검색 6-2 체결 예정 계약서 최종 확인 종료
			form.setSrch_end_org_acptday(StringUtil.bvl(form.getSrch_end_org_acptday(), ""));
			vo.setSrch_end_org_acptday(form.getSrch_end_org_acptday().replace("-", ""));
			
			//각 단계별 모드 조회를 위해
			if(form.getStatus_mode().indexOf("C02") != -1){
				form.setSrch_step(form.getStatus_mode());
				vo.setSrch_step(form.getSrch_step());
			}
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "";
			
			 if("renewApproval".equals(form.getStatus_mode())){	
				// 자동연장 승인
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageRenewApproval_l.jsp";
			 } else if("change".equals(form.getStatus_mode())){	
				 //담당자변경
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageChange_l.jsp";
			 } else if("mail".equals(form.getStatus_mode())){	         
				 // Admin>계약담당자 메일발송 메뉴 페이지용(2012.07.09) 
				 forwardURL = "/WEB-INF/jsp/clm/admin/ManageMailContract.jsp";
			 } else if("request".equals(form.getStatus_mode())){
				 //담당자변경요청
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageRequest_l.jsp";
			 } else if("myApproval".equals(form.getStatus_mode())){	
				 // MyApproval
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageMyApproval_l.jsp";
			 } else if("registration".equals(form.getStatus_mode())){	
				 vo.setIsExcelFlag("N");
				 // 체결후 등록건
				 forwardURL = "/WEB-INF/jsp/clm/manage/RegManageRegistration_l_new.jsp";
			 } else if("registApproval".equals(form.getStatus_mode())){	
				 // 체결후 등록 승인
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageRegistrationApproval_l.jsp";
			 } else if("stdContract".equals(form.getStatus_mode())){
				 //표준계약서 승인 메뉴 추가
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageStdContract_l.jsp";
			 } else if("rel".equals(form.getStatus_mode())){	         
				 String except_cntrt_id = (String)request.getParameter("except_cntrt_id");
				 if(except_cntrt_id!=null && !except_cntrt_id.equals("")) vo.setExcept_cntrt_id(except_cntrt_id);
				 
				 //연관 계약 관리
				 forwardURL = "/WEB-INF/jsp/clm/manage/RelMMContract_l.jsp";				
				 form.setList_mode("contract");                       // 화면에서 계약을 선택하기 위한 세팅값
				 vo.setList_mode("contract");                         // 화면에서 계약을 선택하기 위한 세팅값
				 form.setArg(form.getArg());
			 } else if("samplecontract".equals(form.getStatus_mode())){//SampleContract
				 //SampleContract
				 forwardURL = "/WEB-INF/jsp/clm/manage/ManageSampleContract_p.jsp";
			 } else if("StatisticsListManage".equals(form.getStatus_mode())){
				 // 통계 리스트
				 forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_ManageMyContract_l_rel.jsp";
			 } else {
				// mycontract list
				forwardURL = "/WEB-INF/jsp/clm/manage/ManageMyContract_l.jsp";
				
				/// Fernando 30-08-2022 (start) ///
				if(form.getSrch_start_reqday() == null || "".equals(form.getSrch_start_reqday())){
					form.setSrch_start_reqday(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
					vo.setSrch_start_reqday(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-"));
				}
				if(form.getSrch_end_reqday() == null || "".equals(form.getSrch_end_reqday())){
					form.setSrch_end_reqday(DateUtil.formatDate(DateUtil.today(), "-"));
					vo.setSrch_end_reqday(DateUtil.formatDate(DateUtil.today(), "-"));
				}
				/// Fernando 30-08-2022 (end) ///
			 }
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			// 메일발송인 경우 한페이지에 20개씩 표시
			if("mail".equals(form.getStatus_mode())){
				int srch_page_count = Integer.parseInt(StringUtil.bvl(form.getSrch_page_count(),"10"));
				pageUtil.setRowPerPage(srch_page_count) ;
			}
			
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			String returnMessage = "";
			List resultList = null;
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			// 법무 Lite 버젼에선 사업부 이관 자체가 없음. 김재원.!@#.20130422
			boolean viewOldContractBtnFlag = false; //사업부이관 등록버튼 view 여부
			
			if("CLM".equals((String)session.getAttribute("secfw.session.sys_cd"))){
				if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RD01")){
					viewOldContractBtnFlag = false;
				}
			//법무 (시스템 담당자, 법무 그룹장, 법무 검토자, 계약관리자, Admin은 사업부 이관 등록 버튼이 보인다.)
			}else{
				if(tmpSessionRoleList.contains("RA01")){
					viewOldContractBtnFlag = true;
				}					
			}
			
			// RB01 : 전자 검토자 RB02 : 전자 임원 
			if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				elUserlYn = true;
				vo.setElUserlYn("Y");
			} else {
				elUserlYn = false;
				vo.setElUserlYn("N");
			}
			
			if(tmpSessionRoleList.contains("RB02")){
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
			} else {
				form.setsSel_grd("N");
			}
			
			//계약관리자/담당자변경시에서만 사용되는 분기조건(담당자변경 계약관리자,admin 인 경우는 모두 조회가능)  
			if(tmpSessionRoleList.contains("RD01") || tmpSessionRoleList.contains("RM00")){
				vo.setRequest_Yn("Y");	
			}else{
				vo.setRequest_Yn("N");
			}
			
			if("renewApproval".equals(form.getStatus_mode())){				
				//표준계약서 승인
				resultList = manageService.listAutoRenewApproval(vo);
			} else if("change".equals(form.getStatus_mode())){		
				//담당자변경
				resultList = manageService.listChange(vo);
			} else if("request".equals(form.getStatus_mode())){		
				//담당자변경요청
				resultList = manageService.listRequest(vo);
			} else if("myApproval".equals(form.getStatus_mode())){	
				//MyApproval
				resultList = manageService.listMyApproval(vo);
			} else if("registApproval".equals(form.getStatus_mode())){	
				//체결후등록 승인
				resultList = manageService.listRegistrationApproval(vo);
            } else if("stdContract".equals(form.getStatus_mode())){
				//표준계약서 승인
				resultList = manageService.listStdContract(vo);
			} else if("registration".equals(form.getStatus_mode())){	
				//체결후 등록건
				vo.setList_mode("registration");    // 서비스단에서 쿼리를 분리하기 위한 세팅 값
				
				String role_cd = "";
				boolean checkAdmin = false;
				//legal admin 1,2,3 , System admin 일 경우, List 분기처리
				for (int i = 0; i < form.getSession_user_role_cds().size(); i++) {
					role_cd = ((HashMap<String,String>) form.getSession_user_role_cds().get(i)).get("role_cd").toString();
					if (role_cd.equals("RA00")
						|| role_cd.equals("RA01")
						|| role_cd.equals("RD01")
						|| role_cd.equals("RM00")
						) {
						checkAdmin = true;
						// 2014-05-29 Kevin added.
						break;
					}
				}
				
				if (checkAdmin) {
					vo.setComp_cd(form.getSession_comp_cd());
					resultList = manageService.listManage_legalAdmin(vo);			// 체결 후 등록 건의 쿼리
				}else{
					resultList = manageService.listManage(vo);			// 체결 후 등록 건의 쿼리
				}
			} else if("registApproval".equals(form.getStatus_mode())){	
				//체결후등록 승인
				resultList = manageService.listRegistrationApproval(vo);
			} else if("StatisticsListManage".equals(form.getStatus_mode())){
				// StatisticsListManage 목록을 조회 해 온다. 김재원.!@#.20130417
				
				// 2014-02-17 Kevin. 통계의 경우 EHQ 사용자는 SEUK 통계 Detail을 볼 수 있는데 이때는 comp 값이 비어서 온다. 결과적으로 EHQ 사용자가 통계를 볼 때는
				// EHQ, SEUK 디테일이 동일하게 나타나기 때문에 Request로 넘어온 Comp 값을 사용해서 comp 파라미터를 설정해 줘야 한다.
				String requestedComp = request.getParameter("sElCompe");
				String sessionCompCd = null;
				if(vo.getSElComp() == "" && (requestedComp != null && requestedComp != "")){
					// 기존 세션 comp 코드 저장
					sessionCompCd = vo.getSession_auth_comp_cd();
					// session comp code를 request querystring으로 전달된 코드로 변경
					vo.setSession_auth_comp_cd("'"+requestedComp+"'");
				}
				
				resultList = manageService.StatisticsListManagelistMyContract(vo);
				// session comp code를 강제로 변경했다면 다시 원복
				if(sessionCompCd != null){
					vo.setSession_auth_comp_cd(sessionCompCd);
				}
			} else {
				// mycontract 목록을 조회 해 온다. 김재원.!@#.20130417
				resultList = manageService.listMyContract(vo);
			}
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
				pageUtil.setGroup();
			}
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			/*********************************************************
			 * Return
			**********************************************************/			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("viewOldContractBtnFlag", viewOldContractBtnFlag);
			mav.addObject("elUserlYn", elUserlYn);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info("listManage Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public void listManageExcelWrapper(HttpServletRequest req, HttpServletResponse res) throws Exception{
		listManageExcel(req, res);
		
	}
	
	/**
	* 계약 공통 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listManageExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			CommonForm cmd =  (CommonForm)request.getAttribute("command");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			HttpSession session = request.getSession(false);
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setStatus_mode((String)request.getParameter("status_mode"));
			vo.setStatus_mode(form.getStatus_mode());
			
			//1. 의뢰명 또는 계약명
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
			//2. 의뢰자 명
			form.setSrch_reqman_nm(StringUtil.bvl(form.getSrch_reqman_nm(), ""));
			vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(form.getSrch_reqman_nm().toUpperCase()));
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			//4. 계약담당자 명
			form.setSrch_respman_nm(StringUtil.bvl(form.getSrch_respman_nm(), ""));
			vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm().toUpperCase()));
			//5. 계약담당부서
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), ""));
			vo.setSrch_resp_dept(StringUtil.convertHtmlTochars(form.getSrch_resp_dept()));
			form.setSrch_resp_dept_nm(StringUtil.bvl(form.getSrch_resp_dept_nm(), ""));
			vo.setSrch_resp_dept_nm(StringUtil.convertHtmlTochars(form.getSrch_resp_dept_nm()));
			//6. 계약상대방
			form.setSrch_oppnt_cd(StringUtil.bvl(form.getSrch_oppnt_cd(), ""));
			vo.setSrch_oppnt_cd(StringUtil.convertHtmlTochars(form.getSrch_oppnt_cd()));
			form.setSrch_oppnt_nm(StringUtil.bvl(form.getSrch_oppnt_nm(), ""));
			vo.setSrch_oppnt_nm(StringUtil.convertHtmlTochars(form.getSrch_oppnt_nm()));
			//7. 법무검토자
			form.setSrch_cnsdman_nm(StringUtil.bvl(form.getSrch_cnsdman_nm(), ""));
			vo.setSrch_cnsdman_nm(StringUtil.convertHtmlTochars(form.getSrch_cnsdman_nm().toUpperCase()));
			//8. 비즈니스 단계
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
			//9. 대분류
			form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(), ""));
			vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
			
			// 2014-02-05 Kevin. 엑셀 다운로드 시 step, status 조건 설정 부분이 누락되어 모든 데이터가 조회 됐음. 이를 수정하기 위해 아래 조건 추가 함.
			if(("20130319155330501_0000000362").equals(form.getMenu_id())){//원본접수일경우
				//10. 단계
				form.setSrch_step("C02503");
				vo.setSrch_step("C02503");
				//11. 상태
				form.setSrch_state("C02642");
				vo.setSrch_state(form.getSrch_state());
			}else{ 
				//10. 단계
				form.setSrch_step(StringUtil.bvl(form.getSrch_step(), ""));
				vo.setSrch_step(form.getSrch_step());
				//11. 상태
				form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
				vo.setSrch_state(form.getSrch_state());
			}
			
			//12. 리스트구분
			form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
			vo.setList_mode(form.getList_mode());
			//15. 계약체결일
			form.setSrch_start_cnlsnday(StringUtil.bvl(form.getSrch_start_cnlsnday(), ""));
			vo.setSrch_start_cnlsnday(form.getSrch_start_cnlsnday().replace("-", ""));
			form.setSrch_end_cnlsnday(StringUtil.bvl(form.getSrch_end_cnlsnday(), ""));
			vo.setSrch_end_cnlsnday(form.getSrch_end_cnlsnday().replace("-", ""));
			form.setSrch_if_sys_cd(StringUtil.bvl(form.getSrch_if_sys_cd(), ""));
			vo.setSrch_if_sys_cd(form.getSrch_if_sys_cd());
			
			// 16. 계약번호
			form.setSrch_cntrt_no(StringUtil.bvl(form.getSrch_cntrt_no(), "")) ;
			vo.setSrch_cntrt_no(form.getSrch_cntrt_no()) ;
	
			// 상세 검색 6 체결 예정 계약서 최종 확인 시작
			form.setSrch_str_org_acptday(StringUtil.bvl(form.getSrch_str_org_acptday(), ""));
			vo.setSrch_str_org_acptday(form.getSrch_str_org_acptday().replace("-", ""));
			
			// 상세 검색 6-2 체결 예정 계약서 최종 확인 종료
			form.setSrch_end_org_acptday(StringUtil.bvl(form.getSrch_end_org_acptday(), ""));
			vo.setSrch_end_org_acptday(form.getSrch_end_org_acptday().replace("-", ""));
			
			//각 단계별 모드 조회를 위해
			if(form.getStatus_mode() != null && form.getStatus_mode().indexOf("C02") != -1){
				form.setSrch_step(form.getStatus_mode());
				vo.setSrch_step(form.getSrch_step());
			}
			
			vo.setIsExcelFlag("Y");
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			// 법무 Lite 버젼에선 사업부 이관 자체가 없음. 김재원.!@#.20130422
			boolean viewOldContractBtnFlag = false; //사업부이관 등록버튼 view 여부
			
			if("CLM".equals((String)session.getAttribute("secfw.session.sys_cd"))){
				if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RD01")){
					viewOldContractBtnFlag = false;
				}
			//법무 (시스템 담당자, 법무 그룹장, 법무 검토자, 계약관리자, Admin은 사업부 이관 등록 버튼이 보인다.)
			}else{
				if(tmpSessionRoleList.contains("RA00") || tmpSessionRoleList.contains("RA01") || tmpSessionRoleList.contains("RA02") || tmpSessionRoleList.contains("RD01") || tmpSessionRoleList.contains("RM00")){
					viewOldContractBtnFlag = true;
				}					
			}
			
			// 전자 변호사인 경우 체크
			boolean elUserlYn ;
			// RB01 : 전자 검토자 RB02 : 전자 임원 
			
			if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				elUserlYn = true;
				vo.setElUserlYn("Y");
			} else {
				elUserlYn = false;
				vo.setElUserlYn("N");
			}
			
			if(tmpSessionRoleList.contains("RB02")){
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
			} else {
				form.setsSel_grd("N");
			}
			
			//계약관리자/담당자변경시에서만 사용되는 분기조건(담당자변경 계약관리자,admin 인 경우는 모두 조회가능)  
			if(tmpSessionRoleList.contains("RD01") || tmpSessionRoleList.contains("RM00")){
				vo.setRequest_Yn("Y");	
			}else{
				vo.setRequest_Yn("N");
			}
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			List resultList = null;
			
			if("renewApproval".equals(form.getStatus_mode())){				
				resultList = manageService.listAutoRenewApproval(vo);
			}else if("registApproval".equals(form.getStatus_mode())){	
				resultList = manageService.listRegistrationApproval(vo);
			}else if("myApproval".equals(form.getStatus_mode())){
				resultList = manageService.listMyApproval(vo);
			}else if("change".equals(form.getStatus_mode())){
				resultList = manageService.listChange(vo);
			}else if("stdContract".equals(form.getStatus_mode())){
				resultList = manageService.listStdContract(vo);
			}else{
				resultList = manageService.listMyContract(vo);
			}
			
			resultList = ManageController.shortenNames(resultList);
			
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
			//메시지 처리 - 계약명
			String cntrt_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel04", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰자
			String reqman_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel05", null, new RequestContext(request).getLocale());
			//메시지 처리 - 1차 의뢰일
			String req_dt = messageSource.getMessage("clm.page.field.manage.listManageExcel06", null, new RequestContext(request).getLocale());
			//메시지 처리 - 원본접수일
			String org_acptday = messageSource.getMessage("clm.page.field.manage.listManageExcel07", null, new RequestContext(request).getLocale());
			//메시지 처리 - 담당자
			String cntrt_respman_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel08", null, new RequestContext(request).getLocale());
			//메시지 처리 - 담당부서
			String cntrt_resp_dept_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel09", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약상대방
			String cntrt_oppnt_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel10", null, new RequestContext(request).getLocale());
			//메시지 처리 - 검토자
			String cnsdmans = messageSource.getMessage("clm.page.field.manage.listManageExcel11", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약ID
			String cntrt_no = messageSource.getMessage("clm.page.field.manage.listManageExcel12", null, new RequestContext(request).getLocale());
			//메시지 처리 - 단계
			String prcs_depth_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel13", null, new RequestContext(request).getLocale());
			//메시지 처리 - 상태
			String depth_status_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel14", null, new RequestContext(request).getLocale());
			//메시지 처리 - HQ 변호사 검토 상태
			String hq_cnsd_status = messageSource.getMessage("clm.page.field.manage.listManageExcel14", null, new RequestContext(request).getLocale());
			//메시지 처리 - 최근수정일시
			String mod_dt = messageSource.getMessage("clm.page.field.manage.listManageExcel15", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰일
			String req_day = messageSource.getMessage("clm.page.field.manage.listManageExcel18", null, new RequestContext(request).getLocale());
			//메시지 처리 -체결일
			String cntrt_cnclsnday = messageSource.getMessage("clm.page.field.manage.listManageExcel19", null, new RequestContext(request).getLocale());
			//메시지 처리 - 최초회신일
			String firstr_dt = messageSource.getMessage("clm.page.field.manage.listManageExcel20", null, new RequestContext(request).getLocale());
			//메시지 처리 - 최종회신일
			String finalr_dt = messageSource.getMessage("clm.page.field.manage.listManageExcel21", null, new RequestContext(request).getLocale());
			//메시지 처리 - 최종 회신요청일
			String re_demndday = messageSource.getMessage("las.page.field.contractManager.reqRtDate", null, new RequestContext(request).getLocale());
			//메시지 처리 - closed_YN
			String closed_yn = messageSource.getMessage("clm.page.field.qna.pubYnN", null, new RequestContext(request).getLocale());
			
			if("cnsdreq".equals(vo.getList_mode())){
				//메시지 처리 - 의뢰별목록_
				fileNm			= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
				sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today();
				//메시지 처리 - 의뢰별 목록
				titleInfo[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel02", null, new RequestContext(request).getLocale());
				subTitleInfo	= new String[]{req_title,cntrt_nm, reqman_nm, req_dt, org_acptday, cntrt_respman_nm, cntrt_resp_dept_nm, cntrt_oppnt_nm, cnsdmans, cntrt_no, prcs_depth_nm, depth_status_nm, hq_cnsd_status, mod_dt,closed_yn,re_demndday,firstr_dt,finalr_dt,"cntrtperiod_startday","cntrtperiod_endday","cntrt_cnclsnday"};
				columnInfo		= new String[]{"req_title", "cntrt_nm", "reqman_nm", "req_dt", "org_acptday", "cntrt_respman_nm", "cntrt_resp_dept_nm", "cntrt_oppnt_nm", "cnsdmans", "cntrt_no", "prcs_depth_nm", "depth_status_nm", "hq_cnsd_status_nm", "mod_dt","close_yn", "re_demndday","firstr_dt","finalr_dt","cntrtperiod_startday","cntrtperiod_endday","cntrt_cnclsnday"};
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};
			} else {
				//메시지 처리 - 계약별목록_
				fileNm			= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel16", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
				sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel16", null, new RequestContext(request).getLocale()) + DateUtil.today();
				//메시지 처리 - 계약별 목록
				titleInfo[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel17", null, new RequestContext(request).getLocale());
				subTitleInfo	= new String[]{cntrt_nm, cntrt_respman_nm, cntrt_resp_dept_nm, req_day, cnsdmans, cntrt_cnclsnday, prcs_depth_nm, depth_status_nm, mod_dt,closed_yn,re_demndday,firstr_dt,finalr_dt,"cntrtperiod_startday","cntrtperiod_endday","cntrt_cnclsnday"};
				columnInfo		= new String[]{"cntrt_nm", "cntrt_respman_nm", "cntrt_resp_dept_nm", "req_dt", "cnsdmans", "cntrt_cnclsnday", "prcs_depth_nm", "depth_status_nm", "mod_dt","close_yn", "re_demndday","firstr_dt","finalr_dt","cntrtperiod_startday","cntrtperiod_endday","cntrt_cnclsnday"};
				columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};
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
			
			excel.download(fileNm.trim(), response);
			
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info("listManageExcel Exception : " +e.toString());
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
	/**
	* 계약 공통 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listContractPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/			
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/LinkContractList_p.jsp";	
			String sReturnUrl = "";
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
	        sReturnUrl = StringUtil.bvl((String)request.getParameter("sReturnUrl"), "");
	        
			form.setsReturnUrl(sReturnUrl);
			
			/*********************************************************
			 * Service
			**********************************************************/
			 List resultList = manageService.listContract(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			

				pageUtil.setTotalRow(((BigDecimal) lom.get("total_cnt")) .intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
                 
			}
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* 타시스템(계약관리 및 법무시스템 이외에 연계와 필요한 화면 입니다.)에 제공이 되는 의뢰 기준 팝업 화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView linkContractPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CommonForm cmd =  (CommonForm)request.getAttribute("command");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			String sReturnUrl = "";    // 임직원 포탈에서의 연계시 리턴 URL
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setStatus_mode((String)request.getAttribute("status_mode"));
			vo.setStatus_mode(form.getStatus_mode());
			
			sReturnUrl = StringUtil.bvl((String)request.getParameter("sReturnUrl"), "");
			
			form.setsReturnUrl(sReturnUrl);
			
			//1. 의뢰명 또는 계약명
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
			//2. 의뢰자 명
			form.setSrch_reqman_nm(StringUtil.bvl(form.getSrch_reqman_nm(), ""));
			vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(form.getSrch_reqman_nm().toUpperCase()));
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			//4. 계약담당자 명
			form.setSrch_respman_nm(StringUtil.bvl(form.getSrch_respman_nm(), ""));
			vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm().toUpperCase()));
			//5. 계약담당부서
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), ""));
			vo.setSrch_resp_dept(StringUtil.convertHtmlTochars(form.getSrch_resp_dept()));
			form.setSrch_resp_dept_nm(StringUtil.bvl(form.getSrch_resp_dept_nm(), ""));
			vo.setSrch_resp_dept_nm(StringUtil.convertHtmlTochars(form.getSrch_resp_dept_nm()));
			//6. 계약상대방
			form.setSrch_oppnt_cd(StringUtil.bvl(form.getSrch_oppnt_cd(), ""));
			vo.setSrch_oppnt_cd(StringUtil.convertHtmlTochars(form.getSrch_oppnt_cd()));
			form.setSrch_oppnt_nm(StringUtil.bvl(form.getSrch_oppnt_nm(), ""));
			vo.setSrch_oppnt_nm(StringUtil.convertHtmlTochars(form.getSrch_oppnt_nm()));
			//7. 법무검토자
			form.setSrch_cnsdman_nm(StringUtil.bvl(form.getSrch_cnsdman_nm(), ""));
			vo.setSrch_cnsdman_nm(StringUtil.convertHtmlTochars(form.getSrch_cnsdman_nm().toUpperCase()));
			//8. 비즈니스 단계
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
			//9. 대분류
			form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(), ""));
			vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
			//10. 단계
			form.setSrch_step(StringUtil.bvl(form.getSrch_step(), ""));
			vo.setSrch_step(form.getSrch_step());
			//11. 상태
			form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
			vo.setSrch_state(form.getSrch_state());
			//12. 리스트구분
			form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
			vo.setList_mode("cnsdreq1");
			//15. 계약체결일
			form.setSrch_start_cnlsnday(StringUtil.bvl(form.getSrch_start_cnlsnday(), ""));
			vo.setSrch_start_cnlsnday(form.getSrch_start_cnlsnday().replace("-", ""));
			form.setSrch_end_cnlsnday(StringUtil.bvl(form.getSrch_end_cnlsnday(), ""));
			vo.setSrch_end_cnlsnday(form.getSrch_end_cnlsnday().replace("-", ""));
			
			// 상세 검색 2 계약대상 상세
			form.setSrch_cntrt_trgt_det2(StringUtil.bvl(form.getSrch_cntrt_trgt_det2(), ""));
			vo.setSrch_cntrt_trgt_det2(form.getSrch_cntrt_trgt_det2());
			
			// 상세 검색 6 체결 예정 계약서 최종 확인 시작
			form.setSrch_str_org_acptday(StringUtil.bvl(form.getSrch_str_org_acptday(), ""));
			vo.setSrch_str_org_acptday(form.getSrch_str_org_acptday().replace("-", ""));
			
			// 상세 검색 6-2 체결 예정 계약서 최종 확인 종료
			form.setSrch_end_org_acptday(StringUtil.bvl(form.getSrch_end_org_acptday(), ""));
			vo.setSrch_end_org_acptday(form.getSrch_end_org_acptday().replace("-", ""));
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "";
			forwardURL = "/WEB-INF/jsp/clm/manage/linkContractPop_p.jsp";	// 의뢰 기준 검색 화면
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			vo.setSrch_state("C02608");

			/*********************************************************
			 * 검색처리
			**********************************************************/
			String returnMessage = "";
			List resultList = null;

			resultList = manageService.listManage(vo);			
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			/*********************************************************
			 * Return
			**********************************************************/			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 계약 Layer 출력데이터 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCntrtHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			HashMap hm = new HashMap();
			
			/**
			 * 파라미터 셋팅
			 */
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			hm.put("locale",   locale);
			hm.put("cnsdreq_id", StringUtil.bvl((String)request.getParameter("cnsdreq_id"), ""));
			/**
			 * 목록조회
			 */
			String result = manageService.getCntrtHTML(hm);	
			
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
    		
		}catch (Exception e) {
			this.getLogger().info(" Exception : " +e.toString());
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
	 * 상세 검색 페이지
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailTypePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*********************************************************
		 * Parameter 
		**********************************************************/
		String forwardURL    = "";
		
		ManageForm form       = null;
		ManageVO   vo         = null;

		List resultList = null;
		HttpSession session = null;
		ArrayList lom = null;

		try {
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			session = request.getSession(false);
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_srh_p.jsp";	//총 화면
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form= new ManageForm();	//수정
			vo= new ManageVO();		//수정
			
			bind(request, form);
			bind(request, vo);
			
			
			String str_org_acptday = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -7), "-");
			String end_org_acptday = DateUtil.formatDate(DateUtil.today(), "-");
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_str_org_acptday(str_org_acptday);
			form.setSrch_end_org_acptday(end_org_acptday);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = manageService.listType(vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("forwardInsertCompletion() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("forwardInsertCompletion() Throwable !!");
		}
	}
	
	/**
	 * 유형페이지
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardTypePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*********************************************************
		 * Parameter 
		**********************************************************/
		String forwardURL    = "";

		ManageForm form       = null;
		ManageVO   vo         = null;

		List resultList = null;
		HttpSession session = null;
		ArrayList lom = null;

		try {
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			session = request.getSession(false);
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/listType_p.jsp";	//수정

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form= new ManageForm();	//수정
			vo= new ManageVO();		//수정
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = manageService.listType(vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("forwardInsertCompletion() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("forwardInsertCompletion() Throwable !!");
		}
	}
	
	/**
	 * 의뢰현황 엑셀다운 forward 페이지
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardConsiderationExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManageForm form       = null;
		ManageVO   vo         = null;
		
		try{
			String forwardURL = "";

			forwardURL = "/WEB-INF/jsp/clm/manage/DownConsideration_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form= new ManageForm();	//수정
			vo= new ManageVO();		//수정
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
				
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 의뢰현황 Excel 다운
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void considerationExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//1. 의뢰명 또는 계약명
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			//vo.setSrch_review_title(StringUtil.convertHtmlTochars(form.getSrch_review_title().toUpperCase()));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
			//2. 의뢰자 명
			form.setSrch_reqman_nm(StringUtil.bvl(form.getSrch_reqman_nm(), ""));
			vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(form.getSrch_reqman_nm().toUpperCase()));
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			//4.사업부코드(없을시 세션값 셋팅) - dto 객체에 사업부코드명이 없어서 임시로 srch_resp_dept를 사업부코드로 쓴다.
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), form.getSession_blngt_orgnz()));
			vo.setSrch_resp_dept(StringUtil.bvl(vo.getSrch_resp_dept(), vo.getSession_blngt_orgnz()));
			//5.구분코드
			form.setSrch_mn_cnsd(StringUtil.bvl(form.getSrch_mn_cnsd(), form.getSession_blngt_orgnz()));
			vo.setSrch_mn_cnsd(StringUtil.bvl(vo.getSrch_mn_cnsd(), vo.getSession_blngt_orgnz()));
			
			
			/*********************************************************
			 * 검색
			**********************************************************/
			List resultList = null;
			resultList = manageService.listConsiderationExcel(vo);
			
			resultList = shortenNames(resultList);
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String	fileNm = "";
			String[] sheetNmAry	= new String[1];
			String[] titleInfo	= new String[1];

			String[] subTitleInfo	= null;
			String[] columnInfo		= null;
			short[]	columnAlign		= null;
			
			//메시지 처리 - 구분
			String dept_nm = messageSource.getMessage("clm.page.field.manage.considerationExcel03", null, new RequestContext(request).getLocale());
			//메시지 처리 - 유형1
			String biz_clsfcn1 = messageSource.getMessage("clm.page.field.manage.considerationExcel04", null, new RequestContext(request).getLocale());
			//메시지 처리 - 유형2
			String biz_clsfcn2 = messageSource.getMessage("clm.page.field.manage.considerationExcel05", null, new RequestContext(request).getLocale());
			//메시지 처리 - 유형
			String biz_clsfcn = messageSource.getMessage("clm.page.field.manage.considerationExcel06", null, new RequestContext(request).getLocale());
			//메시지 처리 - 유형3
			String biz_clsfcn3 = messageSource.getMessage("clm.page.field.manage.considerationExcel07", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰명
			String req_title = messageSource.getMessage("clm.page.field.manage.considerationExcel08", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약명
			String cntrt_nm = messageSource.getMessage("clm.page.field.manage.considerationExcel09", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰인
			String cntrt_respman_nm = messageSource.getMessage("clm.page.field.manage.considerationExcel10", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰부서
			String cntrt_resp_dept_nm = messageSource.getMessage("clm.page.field.manage.considerationExcel11", null, new RequestContext(request).getLocale());
			//메시지 처리 - 사업부
			String cntrt_resp_dept_nm2 = messageSource.getMessage("clm.page.field.manage.considerationExcel12", null, new RequestContext(request).getLocale());
			//메시지 처리 - 의뢰일시
			String origin_acpt_dt = messageSource.getMessage("clm.page.field.manage.considerationExcel13", null, new RequestContext(request).getLocale());
			//메시지 처리 - 회신일시
			String origin_re_dt = messageSource.getMessage("clm.page.field.manage.considerationExcel14", null, new RequestContext(request).getLocale());

			//메시지 처리 - 의뢰현황_
			fileNm			= (String)messageSource.getMessage("clm.page.field.manage.considerationExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
			sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.field.manage.considerationExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today();
			//메시지 처리 - 의뢰 현황
			titleInfo[0]	= (String)messageSource.getMessage("clm.page.field.manage.considerationExcel02", null, new RequestContext(request).getLocale());
			subTitleInfo	= new String[]{
				dept_nm, biz_clsfcn1, biz_clsfcn2, biz_clsfcn, biz_clsfcn3, req_title, cntrt_nm, cntrt_respman_nm, cntrt_resp_dept_nm, cntrt_resp_dept_nm2, origin_acpt_dt, origin_re_dt};
			columnInfo		= new String[]{
				"dept_nm","biz_clsfcn1","biz_clsfcn2", "biz_clsfcn", "biz_clsfcn3", "req_title",
				"cntrt_nm", "cntrt_respman_nm", "cntrt_resp_dept_nm", "cntrt_resp_dept_nm2", "origin_acpt_dt","origin_re_dt"};
			columnAlign		= new short[]{
				ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,
				ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER, 
				ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER, 
				ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER};
			
			ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

			int sheetsCount = excel.getSheetsCount();	//Sheet 갯수

			for(int i=0; i < sheetsCount; i++){
				/*
				excel.setFontName("맑은 고딕");
				excel.setFontSize((short)11);
				if(i == 0){
					excel.addTitleRow(i, titleInfo);
				}
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_BLACK);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.				
				excel.setBgColor	(ExcelBuilder.COLOR_YELLOW);                     		// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true); 
				// Row의 모든 Cell의 테두리를 설정한다.
				excel.addRow(i, subTitleInfo);	
				excel.setBold		(false);  
				excel.setBgColor	(ExcelBuilder.COLOR_WHITE);                     		// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.                                            		
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, resultList);                        		// 데이타 엑셀에 박기										
				}
				excel.setDefaultStyle();                                             		// Row의 모든 S
				*/
				
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
			this.getLogger().info(" Exception : " +e.toString());
			e.printStackTrace();
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * 의뢰현황 엑셀다운 forward 페이지(New)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardNConsiderationExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManageForm form       = null;
		ManageVO   vo         = null;
		
		try{
			String forwardURL = "";

			forwardURL = "/WEB-INF/jsp/clm/manage/DownNConsideration_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form= new ManageForm();	//수정
			vo= new ManageVO();		//수정
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
				
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	*  의뢰현황 조회(사업장 구분별)
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listConsiderationMnCnsdDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/			
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/DownNConsideration_l.jsp";	
			String sReturnUrl = "";
			
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			
			//4.사업부코드(없을시 세션값 셋팅) - dto 객체에 사업부코드명이 없어서 임시로 srch_resp_dept를 사업부코드로 쓴다.
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), form.getSession_blngt_orgnz()));
			vo.setSrch_resp_dept(StringUtil.bvl(vo.getSrch_resp_dept(), vo.getSession_blngt_orgnz()));
			
			/*********************************************************
			 * Service
			**********************************************************/
			List resultList = manageService.listConsiderationMnCnsdDept(vo);
		
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	*  의뢰현황 조회(사업부별)
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listConsiderationMnCnsdOrgnz(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/			
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/DownNConsideration_l.jsp";	
			String sReturnUrl = "";
			
			//3. 의뢰일자
			form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
			vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
			
			form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
			vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
			
			//4.사업부코드(없을시 세션값 셋팅) - dto 객체에 사업부코드명이 없어서 임시로 srch_resp_dept를 사업부코드로 쓴다.
			form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), form.getSession_blngt_orgnz()));
			vo.setSrch_resp_dept(StringUtil.bvl(vo.getSrch_resp_dept(), vo.getSession_blngt_orgnz()));
			
			String ViewFlag = "N"; //사업부현황 권한
			if(!StringUtil.bvl(vo.getDept_gbn(),"").equals("")){
				ViewFlag = "Y";
			}
			 
			/*********************************************************
			 * Service
			**********************************************************/
			List resultList = manageService.listConsiderationMnCnsdOrgnz(vo);
		
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);
			mav.addObject("ViewFlag", ViewFlag);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* IRP 연계팝업(계약리스트)
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listContractIRPPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/			
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
	
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/LinkContractListToIRP_p.jsp";	
			String key_id = "";
			String key_nm = "";
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
	
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
	
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			//계약명
			form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
			vo.setSrch_review_title(form.getSrch_review_title().toUpperCase());
			
			//계약체결일
			form.setSrch_start_cnlsnday(StringUtil.bvl(form.getSrch_start_cnlsnday(), ""));
			vo.setSrch_start_cnlsnday(form.getSrch_start_cnlsnday().replace("-", ""));
			form.setSrch_end_cnlsnday(StringUtil.bvl(form.getSrch_end_cnlsnday(), ""));
			vo.setSrch_end_cnlsnday(form.getSrch_end_cnlsnday().replace("-", ""));
			
			//계약상태코드
			form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
			vo.setSrch_state(form.getSrch_state().toUpperCase());
			
			key_id = StringUtil.bvl((String)request.getParameter("key_id"), "");
			key_nm = StringUtil.bvl((String)request.getParameter("key_nm"), "");					
	        
			//form.setsReturnUrl(sReturnUrl);
			
			ConclusionVO conclusionVO = manageService.getConclusionVObyKeyId(key_id,"IRP");

			/*********************************************************
			 * Service
			**********************************************************/
			 List resultList = manageService.listContractIRP(vo);
	
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			
	
				pageUtil.setTotalRow(((BigDecimal) lom.get("total_cnt")) .intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
	             
			}
			
			ModelAndView mav = new ModelAndView();
	
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("conclusionVOByIF", conclusionVO);
			mav.addObject("key_id", key_id);
			mav.addObject("key_nm", key_nm);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	/**
	* 통계에서 넘어와서 팝업으로 리스트 만들어 주는 부분
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView StatisticsListManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CommonForm cmd =  (CommonForm)request.getAttribute("command");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			HttpSession session = request.getSession(false);
			
			String sElComp = null;  // 전자 변호사
			
			// 전자 변호사인 경우 체크
			boolean elUserlYn ;
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// CombBox로 넘어오는 값들의 변환 작업
			sElComp = StringUtil.bvl(form.getsElComp(), "");  // 회사 선택 조회 조건(전자 변호사일 경우에만 나타나게 된다.)
			
			if(("20130319155330501_0000000362").equals(form.getMenu_id())){//원본접수일경우 
				//10. 단계
				form.setSrch_step("C02503");
				vo.setSrch_step("C02503");
				//11. 상태
				form.setSrch_state("C02642");
				vo.setSrch_state(form.getSrch_state());
			}else{ 
				//10. 단계
				form.setSrch_step(StringUtil.bvl(form.getSrch_step(), ""));
				vo.setSrch_step(form.getSrch_step());
				//11. 상태
				form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
				vo.setSrch_state(form.getSrch_state());
			}
			
			// 전자 변호사일 경우 각 회사를 조회 할 수 있게 처리 됨.
			form.setsElComp(sElComp);
			vo.setSElComp(sElComp);
			
			/*********************************************************
			 * JSP 반환변수  - 통계 리스트 화면으로 이동 합니다.
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_ManageMyContract_l.jsp"; 
			
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			

			/*********************************************************
			 * 검색처리
			**********************************************************/
			String returnMessage = "";
			List resultList = null;
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;

			// RB01 : 전자 검토자 RB02 : 전자 임원 
			
			if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				elUserlYn = true;
				vo.setElUserlYn("Y");
			} else {
				elUserlYn = false;
				vo.setElUserlYn("N");
			}
			
			
			if(tmpSessionRoleList.contains("RB02")){
				
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				form.setsSel_grd("Y");
				
			} else {
				
				form.setsSel_grd("N");
				
			}
			
			//계약관리자/담당자변경시에서만 사용되는 분기조건(담당자변경 계약관리자,admin 인 경우는 모두 조회가능)  
			if(tmpSessionRoleList.contains("RD01") || tmpSessionRoleList.contains("RM00")){
				vo.setRequest_Yn("Y");	
			}else{
				vo.setRequest_Yn("N");
			}
			
			// mycontract 목록을 조회 해 온다. 김재원.!@#.20130417
			// 여기 리스트 부분 수정해야 함.
			resultList = manageService.listMyContract(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
				//pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			/*********************************************************
			 * Return
			**********************************************************/			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			mav.addObject("elUserlYn", elUserlYn);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info(" Exception : " +e.toString());
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	private SELMSPlusDataService selmsPlusDataService = null;
	public void setSELMSPlusDataService(SELMSPlusDataService selmsPlusDataService){
		this.selmsPlusDataService = selmsPlusDataService;
	}
	
	/**
	 * 2014-05-12 Kevin added.
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void LinkToGERP(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		try
		{
			int result = 0;
			String cnsdReqID = req.getParameter("cnsdreqid");
			if(cnsdReqID == null || cnsdReqID == ""){
				result = -1;
			}
			
			if(result != -1){
				SELMSPlusDataVO vo = new SELMSPlusDataVO();
				vo.setTargetDate("");			
				vo.setTargetHour(0);
				vo.setTargetInterval(0);
				
				HashMap<String, String> compCdList = new HashMap<String, String>();
				compCdList.put("C480", "480A");
				vo.setTargetCompCdList(compCdList);
				
				// Set CNSDREQID
				vo.setCNSDReqID(cnsdReqID);
				
				int effectedCnt = this.selmsPlusDataService.SendData(vo);
				result = effectedCnt;
			}
			
			PrintWriter writer = res.getWriter();
			writer.print("{result:"+String.valueOf(result)+"}");
			writer.flush();
			writer.close();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			this.getLogger().info(" Exception : " +ex.toString());
			throw new Exception("Error");
		}
		catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public static List shortenNames(List input)
	{
		/*List output = new ArrayList();
		
		for(Object item : input)
		{
			if (item instanceof Map)
			{
				Map map = (Map)item;
				
			    for (Object row : map.entrySet()) 
			    {
			    	String key = ((Map.Entry)row).getKey().toString();
			    	Object value = ((Map.Entry)row).getValue();
			    	
			        if (key.toUpperCase().endsWith("_NM") && value != null && !value.toString().trim().isEmpty()) 
			        {
			        	int firstSpace = value.toString().indexOf(" ");
			        	String new_value;
			        	if (firstSpace > 0) {
			        		new_value = value.toString().substring(0, firstSpace);
			        	} else {
			        		new_value = value.toString();
			        	}
			        	map.put(key, new_value);
			        }
			    }		
			}
			
			output.add(item);
		}
		
		return output;*/
		return input;
	}
	
	
}