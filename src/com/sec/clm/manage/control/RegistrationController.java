package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.ManageForm;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.dto.RegistrationForm;
import com.sec.clm.manage.dto.RegistrationVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.RegistrationService; 
import com.sec.clm.manage.service.ExecutionService;
import com.sec.common.clmscom.service.CLMSCommonService;

//결재관련추가
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.common.util.ClmsDataUtil;
/**
 * 계약관리 >My Contract>검토의뢰
 * @author HP
 * @
 */
public class RegistrationController extends CommonController {
	
	private RegistrationService registrationService;
	/**
	 * 
	 * @param considerationService
	 */
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	private ManageController manageControl;
		public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	private ConsiderationService considerationService;
	/**
	 * @param considerationService
	 */
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}
	
	private ExecutionService executionService;
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;	
	/**
	 * ComUtil 서비스 세팅
	 * @param comUtilService
	 * @return void
	 * @throws
	 */
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
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	/**
	* 계약이행 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listManageRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "registration");
		mav = manageControl.listManage(request, response);
		
		return mav; 
	}
	/**
	* 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listRegistrationExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			ManageForm form = new ManageForm();
			ManageVO vo = new ManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index("1");		//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index("300");		//현재 페이지의 마지막 게시물번호  set
			
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
			vo.setList_mode(form.getList_mode());
			//15. 계약체결일
			form.setSrch_start_cnlsnday(StringUtil.bvl(form.getSrch_start_cnlsnday(), ""));
			vo.setSrch_start_cnlsnday(form.getSrch_start_cnlsnday().replace("-", ""));
			form.setSrch_end_cnlsnday(StringUtil.bvl(form.getSrch_end_cnlsnday(), ""));
			vo.setSrch_end_cnlsnday(form.getSrch_end_cnlsnday().replace("-", ""));
			form.setSrch_if_sys_cd(StringUtil.bvl(form.getSrch_if_sys_cd(), ""));
			vo.setSrch_if_sys_cd(form.getSrch_if_sys_cd());
			
			List resultList = null;
			/*********************************************************
			 * Service
			**********************************************************/
			/* JOON S */

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
				resultList = registrationService.listRegistrationExcelForLegalAdmin(vo);			// 체결 후 등록 건의 쿼리
			}else{
				resultList = registrationService.listRegistrationExcel(vo);			// 체결 후 등록 건의 쿼리
			}
			
			/* JOON E */
			
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
			//메시지 처리 - 담당자
			String cntrt_respman_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel08", null, new RequestContext(request).getLocale());
			//메시지 처리 - 담당부서
			String cntrt_resp_dept_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel09", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약상대방
			String cntrt_oppnt_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel10", null, new RequestContext(request).getLocale());
			//메시지 처리 - 계약ID
			String cntrt_no = messageSource.getMessage("clm.page.field.manage.listManageExcel12", null, new RequestContext(request).getLocale());
			//메시지 처리 - 단계
			String prcs_depth_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel13", null, new RequestContext(request).getLocale());
			//메시지 처리 - 상태
			String depth_status_nm = messageSource.getMessage("clm.page.field.manage.listManageExcel14", null, new RequestContext(request).getLocale());
			fileNm			= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
			sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
			//메시지 처리 - 의뢰별 목록
			titleInfo[0]	= (String)messageSource.getMessage("clm.page.title.aboutCntrtReview.listTitle", null, new RequestContext(request).getLocale());
			subTitleInfo	= new String[]{req_title, cntrt_nm, reqman_nm, cntrt_respman_nm, req_dt, cntrt_resp_dept_nm, cntrt_oppnt_nm, cntrt_no, prcs_depth_nm, depth_status_nm};
			columnInfo		= new String[]{"req_title", "cntrt_nm", "reqman_nm", "cntrt_respman_nm", "req_dt", "cntrt_resp_dept_nm", "cntrt_oppnt_nm", "cntrt_no", "prcs_depth_nm", "depth_status_nm"};
			columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT};
			
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
	 * @param request
	 * @param response
	 * @return 연관계약 팝업 목록 
	 * @throws Exception
	 */
	public ModelAndView popupListContract(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{			
			/*********************************************************
			 * Parameter 
			**********************************************************/		
			String forwardURL = "";
			String returnMessage = "";
		
			ConsultationForm form = new ConsultationForm();		
			ConsultationVO vo = new ConsultationVO();
			
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			
			forwardURL = "/WEB-INF/jsp/clm/manage/ContractList_p.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);		
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex()); //현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex()); //현재 페이지의 마지막 게시물번호  set
			
			form.setSearch_cntrt_nm(form.getSearch_cntrt_nm());
			form.setSearch_req_nm(form.getSearch_req_nm());
			form.setArg(form.getArg());
			
			/*********************************************************
			 * Service
			**********************************************************/
			 resultList = considerationService.popupListContract(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			

				pageUtil.setTotalRow(((Integer) lom.get("total_cnt")) .intValue());
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
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
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
	 * 
	 */	
	public ModelAndView forwardInsertContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ListOrderedMap lomRq = new ListOrderedMap();
			List listTs = new ArrayList();
			List listRc = new ArrayList();
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL  = "/WEB-INF/jsp/clm/manage/Consideration_inner_i.jsp";		
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form  = new ConsultationForm();
			ConsultationVO   vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * Service
			**********************************************************/
			vo.setCntrt_id(form.getMod_cntrt_id());
			
			List listDc = considerationService.detailForwardContractMaster(vo);
			
			if (listDc != null && listDc.size() > 0) {
				lomRq= (ListOrderedMap)listDc.get(0);
				
				vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
				vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
				
				listTs = considerationService.listTypeSpclinfoMod(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
				lomRq.put("total_cnt", form.getTab_cnt());		//계약 Cnt
				
				
			}else{
				lomRq.put("cntrt_id", considerationService.getId());
				lomRq.put("cntrtperiod_startday", "");
				lomRq.put("cntrtperiod_endday", "");
				
				lomRq.put("bfhdcstn_apbtday", "");
				//지불/수금정보
				lomRq.put("payment_gbn",  form.getPayment_gbn());
				if("C02004".equals(form.getPayment_gbn())){	//해당없음
					lomRq.put("crrncy_unit", "");
				}else{
					lomRq.put("crrncy_unit", "");
				}
				lomRq.put("total_cnt", form.getTab_cnt());		//계약 Cnt
				//cntrt_nm 계약명
				lomRq.put("cntrt_nm", form.getCntrt_nm());
				//거래 상대방 설정
				lomRq.put("cntrt_oppnt_cd", form.getTmp_cntrt_oppnt_cd());
				lomRq.put("region_gbn",  form.getTmp_region_gbn());
				lomRq.put("cntrt_oppnt_nm",  form.getTmp_cntrt_oppnt_nm());
				lomRq.put("cntrt_oppnt_rprsntman",  form.getTmp_cntrt_oppnt_rprsntman());
				lomRq.put("cntrt_oppnt_type",  form.getTmp_cntrt_oppnt_type());
				lomRq.put("cntrt_oppnt_respman",  form.getTmp_cntrt_oppnt_respman());
				lomRq.put("cntrt_oppnt_telno",  form.getTmp_cntrt_oppnt_telno());
				lomRq.put("cntrt_oppnt_email",  form.getTmp_cntrt_oppnt_email());
				//계약유형
				lomRq.put("region_gbn", form.getRegion_gbn());
				lomRq.put("biz_clsfcn",  form.getBiz_clsfcn());
				lomRq.put("depth_clsfcn",  form.getDepth_clsfcn());
				lomRq.put("cnclsnpurps_midclsfcn",  form.getCnclsnpurps_midclsfcn());
				lomRq.put("cnclsnpurps_bigclsfcn",  form.getCnclsnpurps_bigclsfcn());
				lomRq.put("biz_clsfcn_nm",  form.getBiz_clsfcn_nm());
				lomRq.put("depth_clsfcn_nm",  form.getDepth_clsfcn_nm());
				lomRq.put("cnclsnpurps_bigclsfcn_nm",  form.getCnclsnpurps_bigclsfcn_nm());
				lomRq.put("cnclsnpurps_midclsfcn_nm",  form.getCnclsnpurps_midclsfcn_nm());
				//계약대상
				lomRq.put("cntrt_trgt",  form.getCntrt_trgt());
			}
			//의뢰정보
			lomRq.put("prev_cnsdreq_id",  form.getPrev_cnsdreq_id());
			lomRq.put("plndbn_req_yn",  form.getPlndbn_req_yn());
			
			lomRq.put("status", form.getStatus());						
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			//최종본 , 계약 여러건인경우 필구 첨부파일 체크 
			ListOrderedMap returnLom= considerationService.getFilevalidate(vo);
			form.setFile_validate((String)returnLom.get("fileValidate"));
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);			
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listRc", listRc);
			mav.addObject("command", form);
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("tabConsMasterExecution() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("tabConsMasterExecution() Throwable !!");
		}
	}
	
	/**
	* 계약 탭 클릭시  데이타 저장 master tb
	* 
	* @param request
	* @param response	
	* @throws Exception
	*/
	public void insertContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/	        
			ListOrderedMap returnLom = new ListOrderedMap();
			
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
			 * 내역 등록
			**********************************************************/	    		
			returnLom = considerationService.insertConsideration(vo);
			
			JSONObject jo = new JSONObject();
			jo.put("returnCd", returnLom.get("cd"));
    		jo.put("returnCnsdReqId", returnLom.get("cnsdreq_id"));
    		jo.put("returnCntrtId", returnLom.get("cntrt_id"));
    		jo.put("returnMsg", returnLom.get("msg"));
    		jo.put("exeStatus", returnLom.get("exe_status"));
    		
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
	
	public void prevCnsdReqCopyConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		try {
			String dup_div = "Y";	// 중복 의뢰 구분값
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
			 * 내역 등록
			**********************************************************/	    		
			//재의뢰 , 최종본의뢰시 의뢰정보을 insert 하고 임시저장 상태로 변경후 수정 폼으로 이동 하게 전달
			if("forwardReq".equals(form.getStatus()) || "forwardLast".equals(form.getStatus())){
				
				// 2012.06.13 중복의뢰 방지 added by hanjihoon
				List listPd = considerationService.preventDuplication(vo);
				ListOrderedMap lomPd = (ListOrderedMap)listPd.get(0);
				
				if("N".equals((String)lomPd.get("dup_div"))) {
					dup_div = "N";
					
					vo.setPrev_cnsdreq_id(vo.getCnsdreq_id());//이전의뢰아이디 설절
					vo.setRe_demndday(considerationService.getReDemndday());//회신요청일
					vo.setReq_dt(DateUtil.getTime("yyyy-MM-dd"));//의뢰일
					
					if("forwardLast".equals(form.getStatus())){
						vo.setPlndbn_req_yn("Y");
					}else{
						vo.setPlndbn_req_yn("N");
					}
					ListOrderedMap returnLom = considerationService.prevCnsdReqCopyConsideration(vo);
					vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id"));
				}
			}
			
			ClmsDataUtil.debug("vo.getCnsdreq_id() >>>" + vo.getCnsdreq_id());			
    		
			JSONObject jo = new JSONObject();
			if("N".equals(dup_div)) {
				jo.put("cnsdreqId", vo.getCnsdreq_id());
			}else{
				jo.put("cnsdreqId", "");
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
	
	public ModelAndView detailContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_inner_d.jsp";				
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/			
			List listDc = considerationService.detailContractMaster(vo);			
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			//나모 잔여 태그 제거			
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
			
			
			//기대효과  <BR>antcptnefctANTCPTNEFCT
			lomRq.put("antcptnefct", StringUtil.convertEnterToBR((String)lomRq.get("antcptnefct")));
			//지불/수분조건 <BR>PAYMENT_COND
			lomRq.put("payment_cond", StringUtil.convertEnterToBR((String)lomRq.get("payment_cond")));
			//추진목적 및 배경
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR((String)lomRq.get("pshdbkgrnd_purps")));
			//기타주요사항 <BR>ETC_MAIN_CONT
			lomRq.put("etc_main_cont", StringUtil.convertEnterToBR((String)lomRq.get("etc_main_cont")));			
			//
			lomRq.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String)lomRq.get("cntrt_chge_demnd_cause")));
			//단가내역 요약cntrt_untprc_expl
			lomRq.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cntrt_untprc_expl"),"")));			
			//책임한도   oblgt_lmt
			lomRq.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("oblgt_lmt"),"")));				
			//Special Condition  spcl_cond
			lomRq.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));			
			//loac_etc 준거법 상세
			lomRq.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
			//dspt_resolt_mthd_det 분쟁해결방법상세 
			lomRq.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
			
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			vo.setPlndbn_req_yn((String)lomRq.get("plndbn_req_yn"));
			vo.setCnsd_status((String)lomRq.get("cnsd_status"));
			
			List listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			
			//추진목적 
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("pshdbkgrnd_purps"),"")));
			
			//종합검토의견
			if(lomRq.get("cnsd_opnn") != null){
			lomRq.put("cnsd_opnn", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_opnn"),"")));
			}
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));		
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			ArrayList lomTs = (ArrayList)listTs;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			mav.addObject("lomRq", lomRq);		
			mav.addObject("lomTs", lomTs);	//특화속성
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("tabConsMasterExecution() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("tabConsMasterExecution() Throwable !!");
		}
	}

	/**
	 * 계약관리 의뢰 테이블 데이타 저장 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {								
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnLom = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);				
			
			/*********************************************************
			 * Service
			**********************************************************/	
			returnLom = considerationService.insertConsideration(vo);
			vo.setCnsdreq_id((String)returnLom.get("returnVal")); //의뢰테이블 저장
			request.setAttribute("f_cnsdreq_id", (String)returnLom.get("returnVal")); //의뢰테이블 저장
	
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					
			//2011.10.15 심주완수정
			String forwardURL = "";
			if(vo.getSubmit_status().equals("req")) {
				forwardURL = "/clm/manage/consideration.do?method=makeApprovalHtmlDirect";	//결재창없이 다이렉트콜....윗줄과 교체하면 됨
			} else {
				forwardURL = "/clm/manage/consideration.do?method=listManageConsideration";
			}
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * 신규 의뢰 시 폼 기본 데이타 설정 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView forwardInsertConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i.jsp";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomRq= new ListOrderedMap(); 
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
			/*********************************************************
			 * Service
			**********************************************************/
			lomRq.put("total_cnt", "1");	//lomRq.total_cnt
			lomRq.put("req_dept", vo.getSession_dept_cd());
			lomRq.put("req_dept_nm", vo.getSession_dept_nm());
			lomRq.put("reqman_jikgup_nm", vo.getSession_jikgup_nm());	//직급
			lomRq.put("reqman_id", vo.getSession_user_id());
			lomRq.put("reqman_nm", vo.getSession_user_nm());
			lomRq.put("req_dt", DateUtil.getTime("yyyy-MM-dd"));
			lomRq.put("re_dt", DateUtil.getTime("yyyy-MM-dd"));
	
			String curYMD = DateUtil.today();
			
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if(DateUtil.isDayOfWeek(curYMD, 7)) {
			    // 토요일이면 1일 증가          : 3 + 1 = 4
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),4),"-"));
			} else if(DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
			    // 수, 목, 금요일이면 2일 증가      : 3 + 2 = 5
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),5),"-"));
			} else {
			    // 그외 (일, 월, 화요일)이면 증가 없음  : 3 + 0 = 3
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),3),"-"));
			}
			
			lomRq.put("rn", "1");
			lomRq.put("cntrt_id", considerationService.getId());
			lomRq.put("cnsdreq_id", "");	//2011.11.01 의뢰 아이디 새 포맷으로 변경 
			lomRq.put("cntrtperiod_startday", "");
			lomRq.put("cntrtperiod_endday", "");
			
			lomRq.put("bfhdcstn_apbtday", DateUtil.getTime("yyyy-MM-dd"));
			lomRq.put("cnsd_demnd_cont", "");
			lomRq.put("status","forwardInsert");
			
			lomRq.put("prev_cnsdreq_id","");
			lomRq.put("plndbn_req_yn","N");
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.put("prev_cnsdreq_id",""),(String)lomRq.put("plndbn_req_yn","N"), vo));
			
			ArrayList listDc = new ArrayList();
			ArrayList listTs = new ArrayList();
			ArrayList listRc = new ArrayList();
			ArrayList listCa = new ArrayList();
			
			listDc.add(lomRq);

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);			
			mav.addObject("listDc", listDc);			
			mav.addObject("listTs", listTs);
			mav.addObject("listRc", listRc);
			mav.addObject("listCa", listCa);
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
	 * 재검토의뢰 정보 임시저장 / 검토의뢰  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void modifyAgainReqConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
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
			 * 내역 등록
			**********************************************************/	    		
			ListOrderedMap returnLom = considerationService.modifyAgainReConsideration(vo);
			
			if(1 == Integer.parseInt((String)returnLom.get("cd"))){
				returnLom.put("val", "SC");
				//정상 처리 되었습니다.
				returnLom.put("msg", (String)messageSource.getMessage("clm.page.field.regist.modifyAgainReqConsideration01", null, new RequestContext(request).getLocale()));
			}else{
				returnLom.put("val", "FA");
				//정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
				returnLom.put("msg", (String)messageSource.getMessage("clm.page.field.regist.modifyAgainReqConsideration02", null, new RequestContext(request).getLocale()));
			}
    		
			JSONObject jo = new JSONObject();
			jo.put("returnCd", returnLom.get("cd"));
			jo.put("returnVal", returnLom.get("val"));
    		jo.put("returnMsg", returnLom.get("msg"));
    		
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
	 * 재 검토 의뢰 작성 폼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardAgainReqConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_again.jsp";
			ListOrderedMap lomCh = new ListOrderedMap();
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service++
			**********************************************************/
			List listDc = considerationService.detailConsideration(vo);
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			//나모 잔여 태그 제거			
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"), "")));			
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			List listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			List listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자 
			List listCh = considerationService.listCnsdreqHold(vo); //보류사유
			if(!listCh.isEmpty()){
				lomCh= (ListOrderedMap)listCh.get(0);
				lomCh.put("hold_cause", StringUtil.convertEnterToBR((String)lomCh.get("hold_cause")));
			}
			
			//변경사항 
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String)lomRq.get("plndbn_chge_cont"),"")));
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			ArrayList lomTs = (ArrayList)listTs;
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);			
			
			mav.addObject("lomTs", lomTs);	//특화속성
			
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCa", listCa);
			mav.addObject("lomCh", lomCh);		//보류사유
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
	 * 계약건 수정 폼으로 전달 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i.jsp";			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomCh = new ListOrderedMap(); 
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List listDc = considerationService.detailConsideration(vo);			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			lomRq.put("cnsd_demnd_cont", StringUtil.convertCharsToHtml((String)lomRq.get("cnsd_demnd_cont")));			//검토_요청_내용
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertCharsToHtml((String)lomRq.get("pshdbkgrnd_purps")));		//추진배경_목적			
			lomRq.put("status", form.getStatus());
			
            String curYMD = DateUtil.today();
			
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if(DateUtil.isDayOfWeek(curYMD, 7)) {
			    // 토요일이면 1일 증가          : 3 + 1 = 4
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),4),"-"));
			} else if(DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
			    // 수, 목, 금요일이면 2일 증가      : 3 + 2 = 5
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),5),"-"));
			} else {
			    // 그외 (일, 월, 화요일)이면 증가 없음  : 3 + 0 = 3
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),3),"-"));
			}
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			List listTs = considerationService.listTypeSpclinfoMod(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			
			vo.setStatus("consider");
			List listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자 
			List listCh = considerationService.listCnsdreqHold(vo); //보류사유
			if(!listCh.isEmpty()){
				lomCh= (ListOrderedMap)listCh.get(0);
			}
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);
			
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCa", listCa);
			//lomCh
			mav.addObject("lomCh", lomCh);
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
	 *  첨부파일 멀티 테스트 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			ConsultationForm form = new ConsultationForm();		
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			ClmsDataUtil.debugParamByRequest(request, true);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			int result = considerationService.saveFileUpload(vo);

			String returnMessage = messageSource.getMessage( "secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * 계약 상세내역 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_d.jsp";
			ListOrderedMap lomCh = new ListOrderedMap();
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service++
			**********************************************************/
			List listDc = considerationService.detailConsideration(vo);
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			//나모 잔여 태그 제거			
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"), "")));			
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			List listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			
			String arr_cntrt_id[] = new String[listDc.size()];
			for(int j=0; j<listDc.size(); j++){
				int k =j+1;
				
				ListOrderedMap lomRq_cntrt_id= (ListOrderedMap)listDc.get(j);
				arr_cntrt_id[j] = (String)lomRq_cntrt_id.get("cntrt_id");
				vo.setArr_cntrt_id(arr_cntrt_id);
				vo.setStatus(Integer.toString(k));
			}
			
			vo.setStatus("consider");
			List listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자 
			List listCh = considerationService.listCnsdreqHold(vo); //보류사유
			if(!listCh.isEmpty()){
				lomCh= (ListOrderedMap)listCh.get(0);
				lomCh.put("hold_cause", StringUtil.convertEnterToBR((String)lomCh.get("hold_cause")));
			}
			
			//변경사항 
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String)lomRq.get("plndbn_chge_cont"),"")));
			
			//최초 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			ArrayList lomTs = (ArrayList)listTs;
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);			
			mav.addObject("lomTs", lomTs);	//특화속성
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCa", listCa);
			mav.addObject("lomCh", lomCh);		//보류사유
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
	 * 보류 /drop 팝업
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView opnnConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_opnn_p.jsp";
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

			List listDc = considerationService.detailConsideration(vo);			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			form.setCnsdreq_id(vo.getCnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq);
			mav.addObject("listDc", listDc);
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
	 * 검토의뢰 보류 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deferRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
			
			/*********************************************************
			 * Service
			**********************************************************/	    		
			returnMap = considerationService.deleteDropDefer(vo);
			
			jo.put("returnCd", returnMap.get("cd"));    		
			jo.put("returnMsg", returnMap.get("msg"));
			
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
	
	public void detailDropDefer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/	        
			List listOpnn = null;			
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
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
						
			
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			listOpnn = considerationService.detailDropDefer(vo);
			
			ListOrderedMap lomOpnn= (ListOrderedMap)listOpnn.get(0);
			
			if("deferRequest".equals(vo.getSubmit_status())){//의뢰 보류 사유
				lomOpnn.put("opnn", StringUtil.convertEnterToBR((String)lomOpnn.get("hold_cause")));
			}else if("dropContract".equals(vo.getSubmit_status())){//계약 드랍 사유
				lomOpnn.put("opnn", StringUtil.convertEnterToBR((String)lomOpnn.get("cntrt_chge_demnd_cause")));
			}
			
			JSONObject jo = new JSONObject();
			jo.put("returnVal", (String)lomOpnn.get("opnn"));    		
			
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
	 * Drop
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
			
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			returnMap = considerationService.deleteDropDefer(vo);
			
    		jo.put("returnCd", returnMap.get("cd"));    		
    		jo.put("returnMsg", returnMap.get("msg"));
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
	 * 검토의뢰 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ListOrderedMap returnMap = new ListOrderedMap();
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
						
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			returnMap = considerationService.deleteDropDefer(vo);
			
    		jo.put("returnCd", returnMap.get("cd"));    		
    		jo.put("returnMsg", returnMap.get("msg"));    		
    		
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
	 * 계약건삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ListOrderedMap returnMap = new ListOrderedMap();
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
						
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			returnMap = considerationService.deleteDropDefer(vo);
			
    		jo.put("returnCd", returnMap.get("cd"));    		
    		jo.put("returnMsg", returnMap.get("msg"));    		
    		
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
	 * 계약건 drop
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ListOrderedMap returnMap = new ListOrderedMap();
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
			
			/*********************************************************
			 * 내역 등록
			**********************************************************/	    		
			returnMap = considerationService.deleteDropDefer(vo);
			
    		jo.put("returnCd", returnMap.get("cd"));    		
    		jo.put("returnMsg", returnMap.get("msg"));   		

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
	 * 연관계약 insert/delete
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void actionRelationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		try {
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
	 * 특화속성 내용 표시 하기 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listSpecialAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			/**
			 * 파라미터 셋팅
			 */
			HashMap map = new HashMap();
			
			map.put("CNCLSNPURPS_BIGCLSFCN", StringUtil.bvl((String)request.getParameter("bigclsfcn"), ""));
			map.put("CNCLSNPURPS_MIDCLSFCN", StringUtil.bvl((String)request.getParameter("midclsfcn"), ""));		
			
			String result = considerationService.listSpecialAttr(map);
	
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
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
	
	/*결재관련추가*/
	//2011.10.12 심주완추가 결재상신관련
	public void makeApprovalHtmlDirect(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			HttpSession session = request.getSession(false);
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        String userId = (String)session.getAttribute("secfw.session.user_id");
	        String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			ApprovalVO	apprVo		= new ApprovalVO();
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
	
			String f_cnsdreq_id = (String)request.getAttribute("f_cnsdreq_id"); //최초 의뢰시 임시저장없이 바로 검토의뢰를 하는 경우에 처리하기위해
			if(f_cnsdreq_id !=null && !f_cnsdreq_id.equals(""))	vo.setCnsdreq_id(f_cnsdreq_id);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List reqList				= null;		//의뢰정보
			List contractList			= null;
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			StringBuffer reqAuthInfo	= new StringBuffer();
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i == 0) {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					} else if(i % 2 == 0 && authReqList.size() > 2) {
						reqAuthInfo.append(",<br/>"+ StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					} else {
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			String reqAuthString = "";
			if(reqAuthInfo != null) reqAuthString = reqAuthInfo.toString();
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)),locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, locale);
				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, locale);
				makeApprovalFooter(sbContent);
			}
			 
			/** 1. 결재내역 정보 **/
	        //모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = StringUtil.bvl(sysCd, "LAS");
			String misId    = "";
			misId = EsbUtil.generateMisId("APPR");
			apprVo.setModule_id("LAS");
			apprVo.setSys_cd(moduleId);
			apprVo.setMis_id(misId);
			
			//로케일, 인코딩 설정, TIME_ZONE
			String locale_info   = "en_US.UTF-8"; 
			
			apprVo.setLocale_info(locale_info);
			apprVo.setTime_zone(StringUtil.bvl((String)session.getAttribute("EP_TIMEZONE"),"GMT+0"));
			
			//STATUS 값 설정 - Default "0"
			apprVo.setStatus("0");
			
			//BODY Type
			String bodyType = "1";
			apprVo.setBody_type("1");
			//[계약검토의뢰]
			apprVo.setTitle(StringUtil.bvl((String)request.getAttribute("approval_title"), messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirect01", null, new RequestContext(request).getLocale()) + vo.getReq_title()));
			//Approval Post Method
			apprVo.setMethod("postAppContStatus");

			String contentHtml = sbContent.toString();
		    contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
		    apprVo.setBody(contentHtml);
		    
		    //로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale    = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"),"en_US.UTF-8");
			String approvalEncoding = sessionLocale.substring(sessionLocale.indexOf(".")+1);
			
	        String approvalDrafterID 			= userId;
	        String approvalDrafterUserPath	 	= "";
	        String approvalDrafterUserRight  	= "";
	        
	        //2011.10.20 심주완추가 계약의뢰상신시 사용
	        String approvalAuthorizerID= StringUtil.bvl((String)request.getAttribute("approval_auth_id"), form.getApprovalman_id());
	        //2011.10.13 심주완추가 계약체결품의시 사용
	        String approvalAuthorizerUserPath	 	= "";
	        String approvalAuthorizerUserRight  	= "";
	        
    		String none_str = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirect02", null, new RequestContext(request).getLocale());
	        
	        if(approvalDrafterID != null && !"".equals(approvalDrafterID)) {
	        	Vector drafterUserVector = null;
	        	
	        	// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
	        	drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);
	        		        	 	
	        	if(drafterUserVector != null && drafterUserVector.size()>0) {
	        		Hashtable ht = (Hashtable)drafterUserVector.get(0);
	        		//기안자정보                                                                                                            아이디                              |      이름                            |             직급코드                      |    직급명                                    |  
		        	approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
		           	//기안자정보 계속                                                                                                                         부서코드                                 |         부서명                                    |               회사코드                                         |	  회사명                       |
		        	approvalDrafterUserPath = approvalDrafterUserPath + ht.get("departmentnumber")+ "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|"+ ht.get("o") +"|";
		        	//기안자정보계속                                                                                                                                총괄코드                 |               총괄명                         |         메일주소
		        	approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")+ "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail"); 
		        	approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";
	        	}
	        }
	        
	        //2011.10.20  심주완추가-결재자정보조회때문에 추가
	        if(approvalAuthorizerID != null && !"".equals(approvalAuthorizerID)) {
		        
	        	Vector authorizerUserVector = null;
	        	authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);
	        		        	 	
	        	if(authorizerUserVector != null && authorizerUserVector.size()>0) {
	        		Hashtable ht = (Hashtable)authorizerUserVector.get(0);
	        		approvalAuthorizerUserPath = "1|" + approvalAuthorizerID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
		        	approvalAuthorizerUserPath = approvalAuthorizerUserPath + ht.get("departmentnumber")+ "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|"+ ht.get("o") +"|";
		        	approvalAuthorizerUserPath = approvalAuthorizerUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")+ "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
		        	approvalAuthorizerUserRight = approvalAuthorizerID + "|-1|-1|-1";
	                                 
	        	}
	        }

			/** 2. 결재경로 정보 **/
	        String[] approvalRoutes       = new String[2];
	        approvalRoutes[0]			  = approvalDrafterUserPath;
	        approvalRoutes[1]			  = approvalAuthorizerUserPath;
	        String[] approvalRouteRights  = new String[2];
	        approvalRouteRights[0]		  = approvalDrafterUserRight;
	        approvalRouteRights[1]		  = approvalAuthorizerUserRight;
	        
			String[] activitys    = null; // 설정구분
			String[] actionTypes  = null; // 처리구분
			String[] userIds      = null; // EPID
			String[] userNms	  = null;
			String[] jikgupCds	  = null;  
			String[] jikgupNms	  = null;
			String[] deptCds	  = null;
			String[] deptNms	  = null;
			String[] compCds	  = null;
			String[] compNms	  = null;
			String[] grpCds		  = null;
			String[] grpNms		  = null;
			String[] mailAddress  = null;
			String[] arbitrarys   = null; // 전결권한
			String[] bodyModifys  = null; // 본문수정권한
			String[] routeModifys = null; // 경로변경 권한
			
			if(approvalRoutes!=null && approvalRoutes.length>0) {
				
				activitys    = new String[approvalRoutes.length];
				actionTypes  = new String[approvalRoutes.length];
				userIds      = new String[approvalRoutes.length];
				userNms      = new String[approvalRoutes.length];
				jikgupCds	 = new String[approvalRoutes.length];  
				jikgupNms	 = new String[approvalRoutes.length];
				deptCds		 = new String[approvalRoutes.length];
				deptNms	  	 = new String[approvalRoutes.length];
				compCds		 = new String[approvalRoutes.length];
				compNms		 = new String[approvalRoutes.length];
				grpCds		 = new String[approvalRoutes.length];
				grpNms		 = new String[approvalRoutes.length];
				mailAddress	 = new String[approvalRoutes.length];
				
				arbitrarys   = new String[approvalRoutes.length];
				bodyModifys  = new String[approvalRoutes.length];
				routeModifys = new String[approvalRoutes.length];
				
				for(int i=0; i<approvalRoutes.length; i++){
		
					String[] approvalRoute      = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");
					
					activitys[i]    		= approvalRoute[0];
					actionTypes[i]  		= "1";
					userIds[i]      		= approvalRoute[1];
					userNms[i]      		= approvalRoute[2];
					jikgupCds[i]	 		= approvalRoute[3];  
					jikgupNms[i]	  		= approvalRoute[4];
					deptCds[i]				= approvalRoute[5];
					deptNms[i]	  			= approvalRoute[6];
					compCds[i]				= approvalRoute[7];
					compNms[i]				= approvalRoute[8];
					grpCds[i]				= approvalRoute[9];
					grpNms[i]				= approvalRoute[10];
					mailAddress[i]			= approvalRoute[11];
					
					
					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i]  = approvalRouteRight[2];
					arbitrarys[i]   = approvalRouteRight[3];
					
				}
			}
			apprVo.setRef_key(vo.getCnsdreq_id());
			apprVo.setApprvl_clsfcn("C03001");
			apprVo.setMethod("postAppContStatus");
		    apprVo.setActivitys(activitys);
		    apprVo.setAction_types(actionTypes);
		    apprVo.setUser_ids(userIds);
		    apprVo.setUser_names(userNms);
		    apprVo.setDuty_codes(jikgupCds);  
		    apprVo.setDutys(jikgupNms);
		    apprVo.setDept_codes(deptCds);
		    apprVo.setDept_names(deptNms);
		    apprVo.setComp_codes(compCds);
		    apprVo.setComp_names(compNms);
		    apprVo.setGroup_codes(grpCds);
		    apprVo.setGroup_names(grpNms);
		    apprVo.setMail_addresss(mailAddress);
		    
		    apprVo.setRoute_modifys(routeModifys);
		    apprVo.setBody_modifys(bodyModifys);
		    apprVo.setArbitrarys(arbitrarys);
		    
		    //결재상신 의견
		   	String[] opinions = new String[2];
	    	for(int i=0; i<approvalRoutes.length; i++) {
	    		opinions[i] = "";
	    	}
	    	//계약검토의뢰입니다.
	    	opinions[0] = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirect03", null, new RequestContext(request).getLocale());
	    	apprVo.setOpinions(opinions);
		   //예약 상신 시간
	    	String[] reserveds = new String[2];
	    	for(int i=0; i<approvalRoutes.length; i++) {
	    		reserveds[i] = "";
	    	}
	    	reserveds[0] = "";
	    	apprVo.setReserveds(reserveds);
	
		    
		    /*********************************************************
			 * 결재상신
			**********************************************************/
	    	boolean isSuccess = false;
	    	isSuccess = esbApprovalService.submit(apprVo);		

			String result = "N";
	    	if(isSuccess) {
	    		result = "Y";
	    		if(vo.getSubmit_status().equals("req")) {
	    			//의뢰상신건에 대한 정상결재상신후 상태코드 업데이트 처리
	    			modifyConsiderationStatus(request, response);
	    		}
	    	}
    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", result);
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
	/*결재관련추가*/
	//2011.10.12 심주완추가 결재상신관련
	public ModelAndView makeApprovalHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {
			String forwardURL = "";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
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
			forwardURL = "/secfw/esbApproval.do?method=forwardApproval";

			List reqList				= null;		//의뢰정보
			List contractList			= null;
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			ModelAndView mav = new ModelAndView();
			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			StringBuffer reqAuthInfo	= new StringBuffer();
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i > 0){
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), "")); 
					} else {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			String reqAuthString = "";
			if(reqAuthInfo != null) reqAuthString = reqAuthInfo.toString();
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)),locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, locale);
				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, locale);
				makeApprovalFooter(sbContent);
			}
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("form", form);
			mav.addObject("approval_content", sbContent.toString());
			//[계약검토의뢰]
			mav.addObject("approval_title", messageSource.getMessage("clm.page.field.regist.makeApprovalHtml01",  null, new RequestContext(request).getLocale()) + form.getReq_title());
			mav.addObject("approval_option", "A");
			mav.addObject("apprvl_clsfcn", "C03001");
			mav.addObject("approval_auth_id", form.getApprovalman_id());	//결재자정보 2011.10.20 심주완추가
			mav.addObject("approval_post_process", "postAppContStatus");
			mav.addObject("ref_key",vo.getCnsdreq_id());
			mav.addObject("approval_opinion", form.getApproval_opinion());

			return mav; 
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//결재문서 스타일정보설정
	public void makeApprovalHeader(StringBuffer sb, String url, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);
			sb.append("<!DOCTYPE html>\n")
			  .append("<html>\n")
			  .append("<head>\n")
			//  .append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8; IE=9\" />\n")
			  .append("<title>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalHeader", null, locale1)+"</title>\n")
			  .append("<LINK href=\""+url+"/style/las/"+locale1+"/las.css\"   type=\"text/css\" rel=\"stylesheet\">")
			  .append("<LINK href=\""+url+"/style/las/"+locale1+"/mail.css\"   type=\"text/css\" rel=\"stylesheet\">")
			  .append("</head>\n")
			  .append("<body>");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//결재문서 스타일정보설정
	public void makeApprovalFooter(StringBuffer sb) throws Exception {
		try {
			  sb.append("</div>")
			  .append("</div>")
			  .append("</div>")
		      .append("</body>\n")
		     .append("</html>\n");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	//검토의뢰정보셋팅
	public void makeApprovalReqInfo(StringBuffer sb, ListOrderedMap lom, ConsultationForm form, String reqAuthInfo, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);
			
			sb.append("<div id=\"m_wrap\">\n")
			  .append("    <div class=\"m_header menu2\">\n")
			  .append("    <h1></h1>\n")
			  //계약검토의뢰
			  .append("    <h2>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo01", null, locale1)+"<span class=\"confidential\"></SPAN></h2>\n")
			  .append("    </div>\n")
			  .append("    <div id=\"m_container\">\n")
			  .append("        <div class=\"contents\">\n")
			  .append("        <!-- title -->\n")
			  .append("        <h3>\n")
			  //계약검토의뢰결재를 상신하오니 재가하여 주시기 바랍니다.
			  .append("        <p>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo02", null, locale1)+"</p></h3>\n")
			  .append("        <!-- title --> \n")
			  .append("        <div class=\"title_basic\">\n")
			  //검토의뢰정보
			  .append("          <h4>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo03", null, locale1)+"</h4>\n")
			  .append("        </div>\n")
			  .append("        <!-- //title -->\n")
			  .append("        <!-- table -->\n")
			  .append("        <table cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
			  .append("            <colgroup>\n")
			  .append("                <col width=\"13%\" />\n")
			  .append("                <col width=\"12%\" />\n")
			  .append("                <col width=\"10%\" />\n")
			  .append("                <col width=\"15%\" />\n")
			  .append("                <col width=\"13%\" />\n")
			  .append("                <col width=\"12%\" />\n")
			  .append("                <col width=\"13%\" />\n")
			  .append("                <col width=\"14%\" />\n")
			  .append("            </colgroup>              \n")
	          .append("	<tr>\n")
	          //검토의뢰제목
	          .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo04", null, locale1)+"</th>\n")
	          .append("    	<td colspan=\"7\">"); 
			  //[계약검토의뢰]
		    sb.append((String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo05", null, locale1) + lom.get("req_title"));
		    sb.append("		</td>\n")
			  .append("	</tr>\n")
			  .append("  <tr class=\"lineAdd\">\n")
			  //의뢰자
	          .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo06", null, locale1)+"</th>\n")
	          .append("    	<td colspan=\"3\">" + lom.get("reqman_nm") + "/" + lom.get("reqman_jikgup_nm") + "/" + lom.get("req_dept_nm") + "</td>\n")
	          //의뢰일
	          .append("     <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo07", null, locale1)+"</th>\n")
	          .append("		<td>");
	        sb.append(lom.get("req_dt"));
	        sb.append("		</td>\n")
	          //회신요청일
	          .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo08", null, locale1)+"</th>\n")
	          .append("     <td>");
	        sb.append(lom.get("re_demndday"));
	        sb.append("		</td>\n")
			  .append("	</tr>\n")
			  .append("	<tr class=\"lineAdd\">\n")
			  //관련자
	          .append("  	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo09", null, locale1)+"</th>\n")
	          .append("     <td colspan=\"7\">" + reqAuthInfo + "</td>\n")
	          .append(" </tr>\n")
	          .append("	<tr class=\"lineAdd\">\n")
	          //검토요청 내용
	          .append("  	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo10", null, locale1)+"</th>\n")
	          .append("      <td colspan=\"7\">");
	        sb.append(StringUtil.bvl(lom.get("cnsd_demnd_cont"), ""));
	        sb.append("		</td>\n")
			  .append("	</tr>\n")
		      .append("</table>\n");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//계약상세정보셋팅
	public void makeApprovalContractInfo(StringBuffer sb, List list, String attachinfo, ConsultationVO vo, String last_locale) throws Exception {
		try {
			ListOrderedMap lom	= null;
			ListOrderedMap tempLom		= null;
			
			int iSize = list.size();
			Locale locale1 = new Locale(last_locale);
			
			for(int i=0; i < list.size(); i++) {
				String titleIndex = "";
				//계약
				if(iSize > 1) titleIndex = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo01", null, locale1) + (i+1)+ " - ";
				lom = (ListOrderedMap)list.get(i);
				vo.setCntrt_id((String)lom.get("cntrt_id"));
				
				List detailContract = considerationService.detailContractMaster(vo);
				List listAttachInfo = considerationService.listConsiderationApprovalAttachInfo(vo);
			
				ArrayList listContractAttach = new ArrayList();
				ArrayList listEtcAttach		 = new ArrayList();
				ArrayList listPreAttach		 = new ArrayList();
				ArrayList listRestAttach	 = new ArrayList();
				if(listAttachInfo != null && listAttachInfo.size() > 0) {
					for(int j=0; j < listAttachInfo.size(); j++) {
						tempLom = (ListOrderedMap)listAttachInfo.get(j);
						if("1".equals((String)tempLom.get("filetype"))) {
							listContractAttach.add(listAttachInfo.get(j));
						} else if("2".equals((String)tempLom.get("filetype"))) {
							listEtcAttach.add(listAttachInfo.get(j));
						} else if("3".equals((String)tempLom.get("filetype"))) {
							listPreAttach.add(listAttachInfo.get(j));
						} else {
							listRestAttach.add(listAttachInfo.get(j));
						}
					}
				} 
				
				ListOrderedMap lomDetail = (ListOrderedMap)detailContract.get(0);
					sb.append("<div class=\"mt5\"></div>\n")
					  //계약기본정보
					  .append("<div class=\"title_basic\"><h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo02", null, locale1)+"</h4></div>\n")
					  .append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("<colgroup>\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("	   <col width=\"9%\" />\n")
					  .append("	   <col width=\"18%\" />\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("    <col width=\"16%\" />\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("    <col width=\"18%\" />\n")
					  .append("</colgroup>\n") 
			          .append("    <tr>\n")
			          //계약명
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo03", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"6\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") + "</span></td>\n")
				      .append("    </tr>\n")
				      .append("    <tr>\n")
				      //계약상대방
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
				      //대표자명
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_rprsntman"), "") + "</td>\n")
				      //고객유형
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") +"</td>\n")
				      .append("	   </tr>\n")
				      .append("    	<tr>\n")
				      //상대방담당자
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo07", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), "") + "</td>\n")
				      //전화번호
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo08", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_telno"), "") + "</td>\n")
				      .append("        <th>E-mail</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_email"), "") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("	   <tr class=\"slide-target02 slide-area\">\n")
				      //계약유형
				      .append("	       <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo09", null, locale1)+"</th>\n")
				      .append("    	   <td colspan=\"6\">");
				    sb.append(lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_midclsfcn_nm"));
					sb.append("		   </td>\n")
				      .append("	   </tr>\n")
				      .append("    <tr>\n")
				      //계약대상
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo10", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">");
					sb.append(lomDetail.get("cntrt_trgt_nm"));
					sb.append("		   </td>\n")
					  //계약대상상세
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo11", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\">");
					sb.append(lomDetail.get("cntrt_trgt_det"));
					sb.append("		   </td>\n")
					  .append("	   </tr>\n")
					  .append("    <tr>\n")
					  //추진목적 및 배경
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo12", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"6\"><span class=\"fL\">");
					sb.append(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""));
					sb.append("            </span></td>\n")
				      .append("    </tr>\n")
				      .append("    <tr>\n")
				      //기대효과
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo13", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"6\"><span class=\"fL\">");
					sb.append(StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("antcptnefct"), "")));
					sb.append("            </span></td>\n")
				      .append("    </tr>\n")  
				      .append("    <tr>\n")
				      .append("	</table>\n")
				      .append("<div class=\"mt5\"></div>\n")
				      //계약상세정보
				      .append("<div class=\"title_basic\"><h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo14", null, locale1)+"</h4></div>\n")
				      .append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("<colgroup>\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("	   <col width=\"9%\" />\n")
					  .append("	   <col width=\"18%\" />\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("    <col width=\"16%\" />\n")
					  .append("    <col width=\"13%\" />\n")
					  .append("    <col width=\"18%\" />\n")
					  .append("</colgroup>\n")
				      .append("     <tr>\n")
				      //계약기간
					  .append(" 	    <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo15", null, locale1)+"</th>\n")
					  .append("	        <td colspan=\"5\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") +"</td>\n")
					  .append("     </tr>\n")
					  .append("     <tr>\n")
					  //지불/수불 구분
					  .append("	    	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo16", null, locale1)+"</th>\n")
					  .append(" 	    <td colspan=\"2\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
					  //계약금액
				      .append("        	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo17", null, locale1)+"</th>\n")
					  .append("        	<td colspan=\"2\" class=\"tR\">" + StringUtil.commaIn(StringUtil.bvl(lomDetail.get("cntrt_amt"), "0")) + "</td>\n")
					  .append("    </tr>\n")
				      .append("	   <tr>\n")
				      //통화(단위)
				      .append("        	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo18", null, locale1)+"</th>\n")
					  .append("        	<td colspan=\"5\">" + lomDetail.get("crrncy_unit") + "</td>\n")
				      .append("    </tr>\n");
					if(!"".equals(lomDetail.get("cntrt_untprc_expl"))) {
					  sb.append("     <tr>\n");
					  //단가내역
					  sb.append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo19", null, locale1)+"</th>\n")
					  .append("         <td colspan=\"5\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n")
					  .append("     </tr>\n");
					}  
					  sb.append("     <tr>\n")
					  //지불/수불 조건
					  .append("	    	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo20", null, locale1)+"</th>\n")
					  .append("	        <td colspan=\"5\">" + StringUtil.bvl(lomDetail.get("payment_cond"), "") + "</td>\n")
					  .append("     </tr>\n");
					if(!"".equals((String)lomDetail.get("etc_main_cont")) || !"".equals((String)lomDetail.get("if_sys_cd"))) {
					  sb.append("     <tr>\n")
					    //기타 주요사항
					    .append("	    	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo21", null, locale1)+"</th>\n");
						if(!"".equals((String)lomDetail.get("if_sys_cd"))) {
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + " [" + lomDetail.get("if_sys_cd") + "]</td>\n");
						}else{
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + "</td>\n");
						}
					  sb.append("     </tr>\n");						
					}
					
				    sb.append("    </table>\n")
					  .append("<div class=\"mt5\"></div>\n")
					  //첨부파일
				      .append("<div class=\"title_basic\"><h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo22", null, locale1)+"</h4></div>\n")
					  .append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("<colgroup>\n")
					  .append("    	<col width=\"13%\" />\n")
					  .append("	   	<col width=\"9%\" />\n")
					  .append("	   	<col width=\"18%\" />\n")
					  .append("    	<col width=\"13%\" />\n")
					  .append("    	<col width=\"16%\" />\n")
					  .append("    	<col width=\"13%\" />\n")
					  .append("    	<col width=\"18%\" />\n")
					  .append("</colgroup>\n")
				      .append("	 	<tr>\n")
				      //계약서
				      .append("       	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo23", null, locale1)+"</th>\n")
				      .append("        	<td colspan=\"5\">");
			    	if(listContractAttach != null && listContractAttach.size() > 0) {
			    		tempLom = (ListOrderedMap)listContractAttach.get(0);
						sb.append("<a href=\"" + attachinfo + (String)tempLom.get("file_id")+ "\">" + (String)tempLom.get("org_file_nm") + "</a>");
					} else {
						//첨부파일없음
						sb.append((String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo24", null, locale1));
					}
						sb.append("			</td>\n")
					      .append("    </tr>\n");
						
				    if(listEtcAttach != null && listEtcAttach.size() > 0) {
				    	for(int k=0; k < listEtcAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtcAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtcAttach.size()==1) {
				    				//첨부/별첨
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo25", null, locale1)+"</td>\n");
				    			} else {
				    				//첨부/별첨
				    				sb.append("<th rowspan=\"" + listEtcAttach.size()+ "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo25", null, locale1)+"</td>\n");
				    			}
				    			sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		} else {
				    			sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    if(listPreAttach != null && listPreAttach.size() > 0) {
				    	for(int k=0; k < listPreAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPreAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPreAttach.size()==1) {
				    				//사전승인
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo26", null, locale1)+"</td>\n");
				    			} else {
				    				//사전승인
				    				sb.append("<th rowspan=\"" + listPreAttach.size()+1 + "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo26", null, locale1)+"</td>\n");
				    			}
					    		sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		} else {
				    			sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    if(listRestAttach != null && listRestAttach.size() > 0) {
				    	for(int k=0; k < listRestAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listRestAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPreAttach.size()==1) {
				    				//기타
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo27", null, locale1)+"</td>\n");
				    			} else {
				    				//기타
				    				sb.append("<th rowspan=\"" + listRestAttach.size()+1 + "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo27", null, locale1)+"</td>\n");
				    			}
				    			sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		} else {
				    			sb.append("<td colspan=\"5\" class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    sb.append("</table>\n");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}	
	}
	
	/**
	 * 체결품의상신 후 상태변경
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsiderationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			String forwardURL = "/clm/manage/consideration.do?method=listManageConsideration";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			String f_cnsdreq_id = (String)request.getAttribute("f_cnsdreq_id"); //최초 의뢰시 임시저장없이 바로 검토의뢰를 하는 경우에 처리하기위해
			if(f_cnsdreq_id !=null && !f_cnsdreq_id.equals(""))	vo.setCnsdreq_id(f_cnsdreq_id);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setPrgrs_status("C04214"); //검토의뢰
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setDepth_status("C02632");//단계상태 
			vo.setCntrt_status("C02401");
			
			int result = considerationService.modifyConsiderationStatus(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	
	public ModelAndView forwardPreviewPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/Consideration_preview_p.jsp";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO   vo   = new ConsultationVO();
			
			String f_cnsdreq_id = (String)request.getAttribute("f_cnsdreq_id"); //최초 의뢰시 임시저장없이 바로 검토의뢰를 하는 경우에 처리하기위해
			if(f_cnsdreq_id !=null && !f_cnsdreq_id.equals(""))	vo.setCnsdreq_id(f_cnsdreq_id);
		
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
		
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List reqList				= null;		//의뢰정보
			List contractList			= null;
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			StringBuffer reqAuthInfo	= new StringBuffer();
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i > 0){
						if(i % 2 == 0 && authReqList.size() > 2) {
							reqAuthInfo.append(",<br/>"+ StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
						} else {
							reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
						}
						
					} else {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			String reqAuthString = "";
			if(reqAuthInfo != null) reqAuthString = reqAuthInfo.toString();
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)),locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, locale);
				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, locale);
				makeApprovalFooter(sbContent);
			}
			
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("content", sbContent.toString());
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
	 * 계약의뢰 상세화면 인쇄
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPrintPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/report/ConsiderationPrint.jsp";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO 	 vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			form.setReport_url(propertyService.getProperty("secfw.url.domain"));
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("command", form);
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * 체결후등록 승인목록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listRegistrationApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "registApproval");
		mav = manageControl.listManage(request, response);
		return mav; 
	}//
	/**
	 * 계약정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			RegistrationForm form       = null;
			RegistrationVO   vo         = null;
			List   		resultList = new ArrayList();
			List   		detailList = new ArrayList();
			List 		tempList = null;
			ListOrderedMap initLom = null;
			ArrayList lom = null;		
			/*********************************************************
			 * Forward setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/RegistrationApproval_d.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new RegistrationForm();
			vo = new RegistrationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			// 연관 계약 관련하여 Cntrt_id 값을 받아서 condseq_id 값을 찾아 오는 내용입니다.
			// 여기서의 Key는 계약 아이디 입니다. 
			tempList = registrationService.listContract(vo);
			/*********************************************************
			 * Service
			**********************************************************/
			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					resultList.add(tempList.get(i));
				}	
				
				if(resultList.size() > 0){
					initLom = (ListOrderedMap)resultList.get(0);
					form.setCnsdreq_id((String)initLom.get("cnsdreq_id"));
					form.setCntrt_id((String)initLom.get("cntrt_id"));
					form.setCntrt_status((String)initLom.get("cntrt_status"));
					form.setPrcs_depth((String)initLom.get("prcs_depth"));
					form.setDepth_status((String)initLom.get("depth_status"));
					form.setReqman_id((String)initLom.get("reqman_id"));
					form.setReqman_nm((String)initLom.get("reqman_nm"));
					form.setCntrt_respman_id((String)initLom.get("cntrt_respman_id"));
					form.setCntrt_respman_nm((String)initLom.get("cntrt_respman_nm"));
				}
				lom = (ArrayList)resultList;
			}
			vo.setCntrt_id(form.getCntrt_id());
			detailList = registrationService.detailManageCompletion(vo);
			
			ListOrderedMap contractMstLom = null;
			ArrayList contractRelationLom = null;
			
			
			if (detailList != null && detailList.size() > 0) {
				//1. 계약정보
				if(detailList.get(0) != null &&((ArrayList)detailList.get(0)).size() > 0) {
					contractMstLom  = (ListOrderedMap)((ArrayList)detailList.get(0)).get(0);
					contractMstLom.put("antcptnefct", StringUtil.convertEnterToBR((String)contractMstLom.get("antcptnefct")));
					//지불/수분조건 <BR>PAYMENT_COND
					contractMstLom.put("payment_cond", StringUtil.convertEnterToBR((String)contractMstLom.get("payment_cond")));
					//기타주요사항 <BR>ETC_MAIN_CONT
					contractMstLom.put("etc_main_cont", StringUtil.convertEnterToBR((String)contractMstLom.get("etc_main_cont")));			
					//CNTRT_CHGE_DEMND_CAUSE
					contractMstLom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String)contractMstLom.get("cntrt_chge_demnd_cause")));
					//단가내역 요약cntrt_untprc_expl  CNTRT_UNTPRC_EXPL
					contractMstLom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String)contractMstLom.get("cntrt_untprc_expl")));			
					//책임한도   oblgt_lmt    OBLGT_LMT
					contractMstLom.put("oblgt_lmt", StringUtil.convertEnterToBR((String)contractMstLom.get("oblgt_lmt")));				
					//Special Condition  spcl_cond   SPCL_COND
					contractMstLom.put("spcl_cond", StringUtil.convertEnterToBR((String)contractMstLom.get("spcl_cond")));			
					//loac_etc 준거법 상세  LOAC_ETC
					contractMstLom.put("loac_etc", StringUtil.convertEnterToBR((String)contractMstLom.get("loac_etc")));
					//dspt_resolt_mthd_det 분쟁해결방법상세    DSPT_RESOLT_DET
					contractMstLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR((String)contractMstLom.get("dspt_resolt_mthd_det")));
					//메모
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR((String)contractMstLom.get("org_acpt_dlay_cause")));
					
				}
				//2. 연관계약정보
				if(detailList.get(1) != null &&((ArrayList)detailList.get(1)).size() > 0) {
					contractRelationLom  = (ArrayList)detailList.get(1);
				}
			}
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("contractLom"			, lom);
			mav.addObject("contractReqLom"		, initLom);
			mav.addObject("contractMstLom"		, contractMstLom);
			mav.addObject("contractRelationLom"	, contractRelationLom);
			mav.addObject("command"				, form);
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}
	/**
	 * 체결후등록 승인/반려
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			RegistrationForm form       = new RegistrationForm();
			RegistrationVO   vo         = new RegistrationVO();
			boolean bSuccess = false;
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			HashMap hm = new HashMap();
			hm.put("SYS_CD", "LAS");
			hm.put("USER_ID", form.getSession_user_id());
			hm.put("CNSDREQ_ID", vo.getCnsdreq_id());
			hm.put("CNTRT_ID", vo.getCntrt_id());
			hm.put("RC_FLAG", "C");
			hm.put("EVENT_BUT", "");
			hm.put("MENU_ID", form.getMenu_id());
			
			ModelAndView mav = new ModelAndView();
			
			//1.승인처리일 경우
			if("Y".equals(vo.getAgree_yn())){
				vo.setCntrt_status("C02402");
				vo.setPrcs_depth("C02504");
				vo.setDepth_status("C02662");
				vo.setPrgrs_status("C04219");
			//2.반려처리일 경우	
			}else if("N".equals(vo.getAgree_yn())){
				vo.setPrgrs_status("C04216");
			}
			
			int result = registrationService.modifyContract(vo);
			
			if(result > 0){
				//처리되었습니다.
				request.setAttribute("returnMessage", messageSource.getMessage("clm.page.field.regist.modifyContract", null, new RequestContext(request).getLocale()));
			}
			//승인목록조회 페이지로 이동
			request.setAttribute("status_mode", "registApproval");
			return manageControl.listManage(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 신규 의뢰 시 폼 기본 데이타 설정 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Forward setting
			**********************************************************/
			String forwardURL = "";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomRq= new ListOrderedMap(); 
			ListOrderedMap lomMt= new ListOrderedMap();
			ListOrderedMap lomCh= new ListOrderedMap();
			
			bind(request, form);
			bind(request, vo);
			
			form.setPageGbn(StringUtil.nvl((String)form.getPageGbn(),""));
			
			getLogger().debug("##### pageGbn = " + form.getPageGbn()) ; // 등록 : ""
			
			forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_i_new.jsp"; 
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
			/*********************************************************
			 * Service
			**********************************************************/
			lomRq.put("total_cnt", "1");	//lomRq.total_cnt. 계약 개수
			lomRq.put("req_dept", vo.getSession_dept_cd()); // 의뢰 부서
			lomRq.put("req_dept_nm", vo.getSession_dept_nm()); // 의뢰 부서명
			lomRq.put("reqman_jikgup_nm", vo.getSession_jikgup_nm());	//의뢰자 직급
			lomRq.put("reqman_id", vo.getSession_user_id()); // 의뢰자 id
			lomRq.put("reqman_nm", vo.getSession_user_nm()); // 의뢰자 명
			lomRq.put("req_dt", DateUtil.getTime("yyyy-MM-dd")); // 의뢰 일자
			lomRq.put("re_dt", DateUtil.getTime("yyyy-MM-dd")); //  회신 일자			
			//계약담당자
			lomRq.put("cntrt_respman_id"		, vo.getSession_user_id());
			lomRq.put("cntrt_respman_nm"		, vo.getSession_user_nm());
			lomRq.put("cntrt_respman_jikgup_nm"	, vo.getSession_jikgup_nm());
			lomRq.put("cntrt_resp_dept"			, vo.getSession_dept_cd());
			lomRq.put("cntrt_resp_dept_nm"		, vo.getSession_dept_nm());
			//사본등록자
			lomRq.put("cpy_regman_id"			, vo.getSession_user_id());
			lomRq.put("cpy_regman_nm"			, vo.getSession_user_nm());
			lomRq.put("cpy_regman_jikgup_nm"	, vo.getSession_jikgup_nm());
			lomRq.put("cpy_reg_dept_nm"			, vo.getSession_dept_nm());
			//의뢰명
			lomRq.put("prefix", (String)messageSource.getMessage("clm.page.field.registration.forwardInsertRegistration01", null, new RequestContext(request).getLocale()));
			String curYMD = DateUtil.today();
			
			// 회신 요청일
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if(DateUtil.isDayOfWeek(curYMD, 7)) {
			    // 토요일이면 1일 증가          : 3 + 1 = 4
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),4),"-"));
			} else if(DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
			    // 수, 목, 금요일이면 2일 증가      : 3 + 2 = 5
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),5),"-"));
			} else {
			    // 그외 (일, 월, 화요일)이면 증가 없음  : 3 + 0 = 3
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),3),"-"));
			}
			
			lomRq.put("rn", "1"); // 사용 안하는듯...
			lomRq.put("cntrt_id", considerationService.getId()); // 계약 ID
			lomRq.put("cnsdreq_id", "");	//2011.11.01 의뢰 아이디 새 포맷으로 변경 
			lomRq.put("cntrtperiod_startday", ""); // 계약기간(시작)
			lomRq.put("cntrtperiod_endday", ""); // 계약기간(종료)
			lomRq.put("bfhdcstn_apbtday", ""); // 사전승인승인일
			lomRq.put("cnsd_demnd_cont", ""); // 의뢰내용(검토 요청내용)
			lomRq.put("status","forwardInsert"); // 상태(JSP에서 사용하는 상태)
			
			lomRq.put("prev_cnsdreq_id",""); // 이전의뢰ID
			lomRq.put("plndbn_req_yn","N"); // 예정본 의뢰 여부
			
			lomRq.put("crrncy_unit", ""); // 화폐단위
			lomRq.put("cntrt_nm", form.getCntrt_nm());
			lomRq.put("cntrt_oppnt_cd", "");
			lomRq.put("region_gbn",  "");
			lomRq.put("cntrt_oppnt_nm",  "");
			lomRq.put("cntrt_oppnt_nm",  "");
			lomRq.put("cntrt_oppnt_rprsntman",  "") ;
			lomRq.put("cntrt_oppnt_type",  "");
			lomRq.put("cntrt_oppnt_respman", "");
			lomRq.put("cntrt_oppnt_telno", "");
			lomRq.put("cntrt_oppnt_email", "");
			lomRq.put("region_gbn", form.getRegion_gbn());
			
			//사본등록정보 세팅
			lomRq.put("cpy_regday"			, DateUtil.getTime("yyyy-MM-dd"));
			lomRq.put("cpy_regman_nm"		, form.getSession_user_nm());
			lomRq.put("cpy_regman_jikgup_nm", form.getSession_jikgup_nm());
			lomRq.put("cpy_reg_dept_nm"		, form.getSession_dept_nm());

			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.put("prev_cnsdreq_id",""),(String)lomRq.put("plndbn_req_yn","N"), vo));
			
			// 연관계약정보
			vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
			String contRc = (String)((HashMap)considerationService.listRelationContract(vo)).get("contRc") ;
			
			// 이행정보 조회
			ExecutionVO executionVo = new ExecutionVO() ;
			executionVo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			executionVo.setCnsdreq_id("");
			ArrayList executionLom = (ArrayList)executionService.listExecution(executionVo);
			
			ArrayList listDc = new ArrayList();
			ArrayList listTs = new ArrayList();
			ArrayList listRc = new ArrayList();
			ArrayList listCa = new ArrayList();
			
			listDc.add(lomRq);
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			// 공통코드 콤보 세팅
			HashMap comboMap = new HashMap() ;
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("SELECT", "#");
			comboMap.put("LOCALE", locale);
			comboMap.put("DEL_YN", "N");
			comboMap.put("USEMAN_MNG_ITM1", "");
			comboMap.put("USEMAN_MNG_ITM2", "");
			comboMap.put("USEMAN_MNG_ITM3", "");
			comboMap.put("USEMAN_MNG_ITM4", "");
			comboMap.put("USEMAN_MNG_ITM5", "");
			
			// 지불 구분
			comboMap.put("GRP_CD", "C020");
			comboMap.put("SELECTED", StringUtil.bvl(form.getPayment_gbn(), ""));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String paymentGbnCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 화폐 단위
			comboMap.put("GRP_CD", "C5");
			comboMap.put("SELECTED", (String)lomRq.get("crrncy_unit"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String crrncyUnitCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 업체 타입
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", "C05601");
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String cntrtOppntTypeCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 사전승인 승인방식
			comboMap.put("GRP_CD", "C028");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String bfhdcstnApbtMthdCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 연관계약 관계유형
			comboMap.put("GRP_CD", "C032");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relYypeCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 계약대상
			comboMap.put("GBN", "CONTRACTTYPE");
			comboMap.put("UP_CD", "");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "T");
			comboMap.put("ADD_YN", "");
			String cntrtTrgtCombo = clmsComService.getComboHTML(comboMap) ;
			
			// TOP_30_CUS
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("top_30_cus"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String cntrt_top30_cus = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// Source of contract drafts
			comboMap.put("GRP_CD", "SOC01");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String srcConDraft = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// Related products
			comboMap.put("GRP_CD", "RP01");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relPrdCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			
			HashMap combo = new HashMap() ;
			combo.put("paymentGbn", paymentGbnCombo) ; // 지불구분 콤보
			combo.put("crrncyUnit", crrncyUnitCombo) ; // 화폐단위 콤보
			combo.put("cntrtOppntType", cntrtOppntTypeCombo) ; // 업체타입 콤보
			combo.put("bfhdcstnApbtMthd", bfhdcstnApbtMthdCombo) ; // 사전승인 승인방식 콤보
			combo.put("relType", relYypeCombo) ; // 연관계약 관계유형 콤보
			combo.put("cntrtTrgt", cntrtTrgtCombo) ; // 계약대상 콤보
			combo.put("srcConDraft", srcConDraft) ; // Source of contract drafts
			combo.put("relPrdCombo", relPrdCombo) ; // Related products
			combo.put("cntrt_top30_cus", cntrt_top30_cus) ; // Top30 Customer
			
			// 로그인한 사람의 회사약어명을 세팅한다.(session에서 가지고 옴.)
			String abbr_comp_nm = (String)session.getAttribute("secfw.session.abbr_comp_nm");
			form.setAbbr_comp_nm(abbr_comp_nm);

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("combo", combo) ;
			mav.addObject("lomRq", lomRq);			
			mav.addObject("listDc", listDc);			
			mav.addObject("listTs", listTs);
			mav.addObject("listRc", listRc);
			mav.addObject("listCa", listCa);
			mav.addObject("contRc", contRc);
			mav.addObject("command", form);
			mav.addObject("executionLom", executionLom);
			

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
	 * 계약 상세내역 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "";
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setPageGbn(StringUtil.nvl((String)form.getPageGbn(),""));
			
			forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_d_new.jsp";
			
			/*********************************************************
			 * Service++
			**********************************************************/
			
			List listDc = null;
			
			listDc = registrationService.detailConsiderationReg(vo);
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			//나모 잔여 태그 제거			
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"), "")));			
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			ArrayList deferList = new ArrayList();
			
			String arr_cntrt_id[] = new String[listDc.size()];
			for(int j=0; j<listDc.size(); j++){
				int k =j+1;
				
				ListOrderedMap lomRq_cntrt_id= (ListOrderedMap)listDc.get(j);
				arr_cntrt_id[j] = (String)lomRq_cntrt_id.get("cntrt_id");
				vo.setArr_cntrt_id(arr_cntrt_id);
				vo.setStatus(Integer.toString(k));
				
				//보류 / 보류 해제 버튼 활성화을 위한  
				deferList.add(lomRq_cntrt_id.get("depth_status"));				
			}
			
			if(deferList.contains("C02607")){ 	//보류상태
				lomRq.put("defer_btn", "cancel");
			}else{
				lomRq.put("defer_btn","defer");
			}
			
			vo.setStatus("consider");
			List<?> listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자  Brice add 11/09
			//변경사항 
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String)lomRq.get("plndbn_chge_cont"),"")));		
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			// 2012.03.07 타이틀을 목록의 상태명으로 나오도록 수정 added by hanjihoon
			form.setReq_status_title(registrationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			/************************************************** 
			 * 계약정보 상세
			 * 기존 detailContractMaster() 메소드에 있는 정보
			 **************************************************/
			
			/******* 1. 첫번째 계약ID의 계약 상세 조회한다. *******/
			List listDc2 = registrationService.detailContractMaster(vo);
			
			ListOrderedMap lomRq2= (ListOrderedMap)listDc2.get(0);
			
			//나모 잔여 태그 제거			
			lomRq2.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq2.get("cnsd_demnd_cont"),"")));
			
			//기대효과  <BR>antcptnefctANTCPTNEFCT
			lomRq2.put("antcptnefct", StringUtil.convertEnterToBR((String)lomRq2.get("antcptnefct")));
			//지불/수분조건 <BR>PAYMENT_COND
			lomRq2.put("payment_cond", StringUtil.convertEnterToBR((String)lomRq2.get("payment_cond")));
			//추진목적 및 배경
			lomRq2.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR((String)lomRq2.get("pshdbkgrnd_purps")));
			//기타주요사항 <BR>ETC_MAIN_CONT
			lomRq2.put("etc_main_cont", StringUtil.convertEnterToBR((String)lomRq2.get("etc_main_cont")));			
			//
			lomRq2.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String)lomRq2.get("cntrt_chge_demnd_cause")));
			//단가내역 요약cntrt_untprc_expl
			lomRq2.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("cntrt_untprc_expl"),"")));			
			//책임한도   oblgt_lmt
			lomRq2.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("oblgt_lmt"),"")));				
			//Special Condition  spcl_cond
			lomRq2.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("spcl_cond"),"")));			
			//loac_etc 준거법 상세
			lomRq2.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("loac_etc"),"")));
			//dspt_resolt_mthd_det 분쟁해결방법상세 
			lomRq2.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("dspt_resolt_mthd_det"),"")));
			//종합검토의견
			if(lomRq2.get("cnsd_opnn") != null){
				lomRq2.put("cnsd_opnn", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq2.get("cnsd_opnn"),"")));
			}
			
			/******* 2. 특화정보를 조회한다. *******/
			vo.setCnclsnpurps_bigclsfcn((String)lomRq2.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq2.get("cnclsnpurps_midclsfcn"));
			
			vo.setPlndbn_req_yn((String)lomRq2.get("plndbn_req_yn"));
			vo.setCnsd_status((String)lomRq2.get("cnsd_status"));
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(registrationService.getReqStatus((String)lomRq2.get("prev_cnsdreq_id"),(String)lomRq2.get("plndbn_req_yn"), vo));		
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			ListOrderedMap returnLom= registrationService.getFilevalidate(vo);
			form.setFile_validate((String)returnLom.get("fileValidate"));
			
			/************************************************** 
			 * 연관 계약 정보
			 * 기존 listRelationContract() 메소드에 있는 정보
			 **************************************************/
			vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
			vo.setSubmit_status("");
			String contRc = (String)((HashMap)registrationService.listRelationContract(vo)).get("contRc") ;
			
			// 이행정보 조회
			ExecutionVO executionVo = new ExecutionVO() ;
			executionVo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			executionVo.setCnsdreq_id("");
			ArrayList executionLom = (ArrayList)executionService.listExecution(executionVo);
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("listCa", listCa); // brice add 11/09 add cc
			
			mav.addObject("lomRq", lomRq); // 의뢰 정보
			mav.addObject("lomRq2", lomRq2); // 계약 정보
			mav.addObject("contRc", contRc);	//연관계약
			mav.addObject("listDc", listDc); // 의뢰 정보 LIST
			mav.addObject("listDc2", listDc2); // 계약 정보 LIST
			mav.addObject("executionLom", executionLom);
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
	 * 계약건 수정 폼으로 전달 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			HttpSession session = request.getSession(false);
			
			String forwardURL = "";		
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomCh = new ListOrderedMap(); 
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_i_new.jsp";		
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List listDc = null; 
			
			listDc =registrationService.detailConsiderationReg(vo);	
				
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			lomRq.put("cnsd_demnd_cont", StringUtil.convertCharsToHtml((String)lomRq.get("cnsd_demnd_cont")));			//검토_요청_내용
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertCharsToHtml((String)lomRq.get("pshdbkgrnd_purps")));		//추진배경_목적			
			lomRq.put("status", form.getStatus());
			
            String curYMD = DateUtil.today();
			
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if(DateUtil.isDayOfWeek(curYMD, 7)) {
			    // 토요일이면 1일 증가          : 3 + 1 = 4
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),4),"-"));
			} else if(DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
			    // 수, 목, 금요일이면 2일 증가      : 3 + 2 = 5
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),5),"-"));
			} else {
			    // 그외 (일, 월, 화요일)이면 증가 없음  : 3 + 0 = 3
			    lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),3),"-"));
			}
			
			// 2014-07-04 Kevin added.
			String req_title = (String)lomRq.get("req_title");
			String prefix = (String)messageSource.getMessage("clm.page.field.registration.forwardInsertRegistration01", null, new RequestContext(request).getLocale());
			
			
			//2015-02-04 by JOON 
			String prefixTNC = "[T&C Post-Conclusion Registration]";
			
			if ( req_title != null && req_title.indexOf(prefixTNC) != 0 ) {
				lomRq.remove("req_title");
				lomRq.put("req_title", req_title.replace(prefix, ""));
				lomRq.put("prefix", prefix);
			} 
						
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			List listTs = registrationService.listTypeSpclinfoMod(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			
			List listCa = null;
			List listCh = null;
			
			listCa = registrationService.listContractAuthreq(vo);//권한요청자 -관련자 
			listCh = registrationService.listCnsdreqHold(vo); //보류사유

			if(!listCh.isEmpty()){
				 lomCh= (ListOrderedMap)listCh.get(0);
			}

			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(registrationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			// 연관계약정보
			vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
			String contRc = (String)((HashMap)registrationService.listRelationContract(vo)).get("contRc") ;
			
			// 이행정보 조회
			ExecutionVO executionVo = new ExecutionVO() ;
			executionVo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			executionVo.setCnsdreq_id("");
			ArrayList executionLom = (ArrayList)executionService.listExecution(executionVo);
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			// 공통코드 콤보 세팅
			HashMap comboMap = new HashMap() ;
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("SELECT", "#");
			comboMap.put("LOCALE", locale);
			comboMap.put("DEL_YN", "N");
			comboMap.put("USEMAN_MNG_ITM1", "");
			comboMap.put("USEMAN_MNG_ITM2", "");
			comboMap.put("USEMAN_MNG_ITM3", "");
			comboMap.put("USEMAN_MNG_ITM4", "");
			comboMap.put("USEMAN_MNG_ITM5", "");
			
			// 지불 구분
			comboMap.put("GRP_CD", "C020");
			comboMap.put("SELECTED", lomRq.get("payment_gbn"));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String paymentGbnCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 화폐 단위
			comboMap.put("GRP_CD", "C5");
			comboMap.put("SELECTED", (String)lomRq.get("crrncy_unit"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String crrncyUnitCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 업체 타입
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("cntrt_oppnt_type"));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String cntrtOppntTypeCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 사전승인 승인방식
			comboMap.put("GRP_CD", "C028");
			comboMap.put("SELECTED", lomRq.get("bfhdcstn_apbt_mthd"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String bfhdcstnApbtMthdCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 연관계약 관계유형
			comboMap.put("GRP_CD", "C032");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relYypeCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 계약대상
			comboMap.put("GBN", "CONTRACTTYPE");
			comboMap.put("UP_CD", lomRq.get("cnclsnpurps_midclsfcn"));
			comboMap.put("SELECTED", lomRq.get("cntrt_trgt"));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "T");
			comboMap.put("ADD_YN", "");
			String cntrtTrgtCombo = clmsComService.getComboHTML(comboMap) ;
					
			// 새로 추가된 내용 top30, Source of contract drafts,  Related products
			// TOP_30_CUS
			
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
			
			HashMap combo = new HashMap() ;
			combo.put("paymentGbn", paymentGbnCombo) ; // 지불구분 콤보
			combo.put("crrncyUnit", crrncyUnitCombo) ; // 화폐단위 콤보
			combo.put("cntrtOppntType", cntrtOppntTypeCombo) ; // 업체타입 콤보
			combo.put("bfhdcstnApbtMthd", bfhdcstnApbtMthdCombo) ; // 사전승인 승인방식 콤보
			combo.put("relType", relYypeCombo) ; // 연관계약 관계유형 콤보
			combo.put("cntrtTrgt", cntrtTrgtCombo) ; // 계약대상 콤보
			
			combo.put("srcConDraft", srcConDraft) ; //  Source of contract drafts
			combo.put("relPrdCombo", relPrdCombo) ; // Related products
			combo.put("cntrt_top30_cus", cntrt_top30_cus) ; // Related products

			// 로그인한 사람의 회사약어명을 세팅한다.(session에서 가지고 옴.)
			String abbr_comp_nm = (String)session.getAttribute("secfw.session.abbr_comp_nm");
			form.setAbbr_comp_nm(abbr_comp_nm);
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("combo", combo) ; // 콤보
			mav.addObject("lomRq", lomRq); // 의뢰 정보
			mav.addObject("listDc", listDc); // 의뢰 정보 리스트
			mav.addObject("listTs", listTs); // 특화 정보 리스트
			mav.addObject("listCa", listCa); // 관련자 정보
			mav.addObject("contRc", contRc); // 연관 계약 정보
			mav.addObject("lomCh", lomCh); // 보류 정보
			mav.addObject("command", form);
			mav.addObject("executionLom", executionLom);

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
	 * 체결후등록 테이블 데이타 저장 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertRegistration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {				
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnLom = new ListOrderedMap();
			ListOrderedMap requiredLom = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setAttr_cd(StringUtil.bvl(form.getAttr_cd(), ""));
			vo.setAttr_cd(StringUtil.bvl(form.getAttr_cd(), ""));
			
			form.setAttr_cont(StringUtil.bvl(form.getAttr_cont(), ""));
			vo.setAttr_cont(StringUtil.bvl(form.getAttr_cont(), ""));

			/*********************************************************
			 * Service   clm.manage.requiredInfo
			**********************************************************/
			returnLom = registrationService.insertRegistration(vo);

			vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id")); //의뢰테이블 저장
			/********************************************************
			 * a멀티 계약건 필수 입력 항목 체크 후 결과 리턴 
			 ********************************************************/
			
			if(!"".equals(vo.getCnsdreq_id()) && !"save".equals(vo.getSubmit_status())){
				
				requiredLom = considerationService.searchRequired(vo);		
				
				if("OK".equals(requiredLom.get("ret_msg"))){
					requiredLom.put("result", "Y");
					//정상처리 되었습니다.
					requiredLom.put("msg", messageSource.getMessage("clm.page.field.regist.insertRegistration01",  null, new RequestContext(request).getLocale()));
					requiredLom.put("cnsdreq_id", vo.getCnsdreq_id());
					requiredLom.put("cntrt_id", vo.getCntrt_id());
					
				}else{//필수 체크에 걸림 				
					requiredLom.put("result", "N");
					//다른 계약건에 입력되지 않은 필수 입력 항목이 존재 합니다.
					requiredLom.put("msg", messageSource.getMessage("clm.page.field.regist.insertRegistration02",  null, new RequestContext(request).getLocale()));
					requiredLom.put("cnsdreq_id", vo.getCnsdreq_id());
					requiredLom.put("cntrt_id", vo.getCntrt_id());
				}
			}else{
				requiredLom.put("result", "Y");
				//정상처리 되었습니다.
				requiredLom.put("msg", messageSource.getMessage("clm.page.field.regist.insertRegistration01",  null, new RequestContext(request).getLocale()));
				requiredLom.put("cnsdreq_id", vo.getCnsdreq_id());
				requiredLom.put("cntrt_id", vo.getCntrt_id());
			}
					

			JSONObject jo = new JSONObject();
			jo.put("ret_msg",requiredLom.get("ret_msg"));
			jo.put("returnValue", requiredLom.get("result"));
			jo.put("returnApproval", "");

			jo.put("msg", requiredLom.get("msg"));			
    		jo.put("cnsdreq_id", requiredLom.get("cnsdreq_id"));
    		jo.put("cntrt_id", requiredLom.get("cntrt_id"));
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
	//*결재관련시작
	//결재상신문서 내용을 생성한다.
	public ModelAndView makeApprovalHtmlNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "";
			String strUrl = "http://"+propertyService.getProperty("secfw.url.lasdomain");
			String strAttachUrl = strUrl + "/clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			// Form 및 VO Binding
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			forwardURL = "/secfw/esbApproval.do?method=forwardApproval";
			
			List reqList				= null;		//의뢰정보
			List contractList			= null;
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			/******************************************************
			 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
			 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
			 **************************************************/
			
			String buGubn = "";   // 인쇄 버튼의 구분
			
			buGubn = StringUtil.nvl((String)request.getParameter("buGubn"),"");
			
			vo.setBuGubn(buGubn);
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			String agreeUserId 		= "";
			String approvalTitle 	= "";
			reqList = registrationService.detailConsultationApprovalRequest(vo);
			contractList = registrationService.listConsiderationApprovalContract(vo);
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				agreeUserId = (String)reqLom.get("agree_user_id");
				approvalTitle	= (String)reqLom.get("req_title");
				makeApprovalHeader(sbContent, strUrl, locale);
				makeApprovalReqInfo(sbContent, reqLom, strAttachUrl,locale);
				makeApprovalContractInfoNew(sbContent, contractList, strAttachUrl, vo, locale);
				makeApprovalFooter(sbContent);
			}
			ModelAndView mav = new ModelAndView();
							
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("form", form);
			
			mav.addObject("approval_content"		, sbContent.toString());
			//[체결후등록품의]
			mav.addObject("approval_title"			, messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlNew",  null, new RequestContext(request).getLocale())+approvalTitle );
			mav.addObject("approval_option"			, "B");	
			mav.addObject("apprvl_clsfcn"			,"C03004");
			mav.addObject("approval_post_process"	, "postAppContStatus");
			mav.addObject("ref_key"					,vo.getCnsdreq_id());
			mav.addObject("approval_agree_id"		,agreeUserId);

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
	 * 결재관련 - 체결 후 등록 화면 변경
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void makeApprovalHtmlDirectNew(HttpServletRequest request, HttpServletResponse response, JSONObject jo) throws Exception {		
		
		try {

			HttpSession session = request.getSession(false);
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        String userId = (String)session.getAttribute("secfw.session.user_id");
	        String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
	        String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			ApprovalVO	apprVo		= new ApprovalVO();
			List resultList = null;
			
			// Form 및 VO Binding
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			
			bind(request, form);
			bind(request, vo);
	
			String f_cnsdreq_id = (String)request.getAttribute("f_cnsdreq_id"); //최초 의뢰시 임시저장없이 바로 검토의뢰를 하는 경우에 처리하기위해
			if(f_cnsdreq_id !=null && !f_cnsdreq_id.equals(""))	vo.setCnsdreq_id(f_cnsdreq_id);
						
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 파라미터세팅
			List reqList				= null;		//의뢰정보
			List contractList			= null;
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			StringBuffer reqAuthInfo	= new StringBuffer();
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i == 0) {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					} else if(i % 2 == 0 && authReqList.size() > 2) {
						reqAuthInfo.append(",<br/>"+ StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					} else {
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			String reqAuthString = "";
			if(reqAuthInfo != null) reqAuthString = reqAuthInfo.toString();			
						
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)),locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, locale);
				makeApprovalContractInfoNew(sbContent, contractList, strAttachUrl, vo, locale);  // 체결 후 등록과 관련된 내용을 가지고 옵니다
				makeApprovalFooter(sbContent);
			}
			 
			// 1. 결재내역 정보 
	        //모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = StringUtil.bvl(sysCd, "LAS");
			String misId    = "";
			misId = EsbUtil.generateMisId("APPR");
			apprVo.setModule_id("LAS");
			apprVo.setSys_cd(moduleId);
			apprVo.setMis_id(misId);
			
			//로케일, 인코딩 설정, TIME_ZONE
			String locale_info   = "en_US.UTF-8"; 
			
			apprVo.setLocale_info(locale_info);
			apprVo.setTime_zone(StringUtil.bvl((String)session.getAttribute("EP_TIMEZONE"),"GMT+0"));
			
			//STATUS 값 설정 - Default "0"
			apprVo.setStatus("0");
			
			//BODY Type
			String bodyType = "1";
			apprVo.setBody_type("1");
			//[계약검토의뢰]
			apprVo.setTitle(StringUtil.bvl((String)request.getAttribute("approval_title"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew01",  null, new RequestContext(request).getLocale()) + vo.getReq_title()));
			//Approval Post Method
			apprVo.setMethod("postAppContStatus");

			String contentHtml = sbContent.toString();
		    contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		    contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
		    apprVo.setBody(contentHtml);
		    
		    //로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale    = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"),"en_US.UTF-8");
			String approvalEncoding = sessionLocale.substring(sessionLocale.indexOf(".")+1);
			
	        String approvalDrafterID 			= userId;
	        String approvalDrafterUserPath	 	= "";
	        String approvalDrafterUserRight  	= "";
	        
	        //2011.10.20 심주완추가 계약의뢰상신시 사용
	        String approvalAuthorizerID= StringUtil.bvl((String)request.getAttribute("approval_auth_id"), form.getApprovalman_id());
	        //2011.10.13 심주완추가 계약체결품의시 사용
	        String approvalAuthorizerUserPath	 	= "";
	        String approvalAuthorizerUserRight  	= "";
	        //없음
    		String none_str = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale());
	        
	        //상신자 정보 조회
	        if(approvalDrafterID != null && !"".equals(approvalDrafterID)) {
	        	Vector drafterUserVector = null;
	        	
	        	// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
	        	drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);
	        		        	 	
	        	if(drafterUserVector != null && drafterUserVector.size()>0) {
	        		Hashtable ht = (Hashtable)drafterUserVector.get(0);
	        		//기안자정보                                                                                                            아이디                              |      이름                            |             직급코드                      |    직급명                                    |  
		        	approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
		           	//기안자정보 계속                                                                                                                         부서코드                                 |         부서명                                    |               회사코드                                         |	  회사명                       |
		        	approvalDrafterUserPath = approvalDrafterUserPath + ht.get("departmentnumber")+ "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|"+ ht.get("o") +"|";
		        	//기안자정보계속                                                                                                                                총괄코드                 |               총괄명                         |         메일주소
		        	approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")+ "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail"); 
		        	approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";
	        	}
	        }
	        
	        //2011.10.20  심주완추가-결재자정보조회때문에 추가
	        if(approvalAuthorizerID != null && !"".equals(approvalAuthorizerID)) {
		        
	        	Vector authorizerUserVector = null;
	        	
	        	// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
	        	authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);
	        		        	 	
	        	if(authorizerUserVector != null && authorizerUserVector.size()>0) {
	        		Hashtable ht = (Hashtable)authorizerUserVector.get(0);
	        		approvalAuthorizerUserPath = "1|" + approvalAuthorizerID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
		        	approvalAuthorizerUserPath = approvalAuthorizerUserPath + ht.get("departmentnumber")+ "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|"+ ht.get("o") +"|";
		        	approvalAuthorizerUserPath = approvalAuthorizerUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")+ "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
		        	approvalAuthorizerUserRight = approvalAuthorizerID + "|-1|-1|-1";
	                                 
	        	}
	        }
				        
			// 2. 결재경로 정보 
	        String[] approvalRoutes       = new String[2];
	        approvalRoutes[0]			  = approvalDrafterUserPath;
	        approvalRoutes[1]			  = approvalAuthorizerUserPath;
	        String[] approvalRouteRights  = new String[2];
	        approvalRouteRights[0]		  = approvalDrafterUserRight;
	        approvalRouteRights[1]		  = approvalAuthorizerUserRight;
	        
			String[] activitys    = null; // 설정구분
			String[] actionTypes  = null; // 처리구분
			String[] userIds      = null; // EPID
			String[] userNms	  = null;
			String[] jikgupCds	  = null;  
			String[] jikgupNms	  = null;
			String[] deptCds	  = null;
			String[] deptNms	  = null;
			String[] compCds	  = null;
			String[] compNms	  = null;
			String[] grpCds		  = null;
			String[] grpNms		  = null;
			String[] mailAddress  = null;
			String[] arbitrarys   = null; // 전결권한
			String[] bodyModifys  = null; // 본문수정권한
			String[] routeModifys = null; // 경로변경 권한
			
			if(approvalRoutes!=null && approvalRoutes.length>0) {
				
				activitys    = new String[approvalRoutes.length];
				actionTypes  = new String[approvalRoutes.length];
				userIds      = new String[approvalRoutes.length];
				userNms      = new String[approvalRoutes.length];
				jikgupCds	 = new String[approvalRoutes.length];  
				jikgupNms	 = new String[approvalRoutes.length];
				deptCds		 = new String[approvalRoutes.length];
				deptNms	  	 = new String[approvalRoutes.length];
				compCds		 = new String[approvalRoutes.length];
				compNms		 = new String[approvalRoutes.length];
				grpCds		 = new String[approvalRoutes.length];
				grpNms		 = new String[approvalRoutes.length];
				mailAddress	 = new String[approvalRoutes.length];
				
				arbitrarys   = new String[approvalRoutes.length];
				bodyModifys  = new String[approvalRoutes.length];
				routeModifys = new String[approvalRoutes.length];
				
				for(int i=0; i<approvalRoutes.length; i++){

					String[] approvalRoute      = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");
					
					activitys[i]    		= approvalRoute[0];
					actionTypes[i]  		= "1";
					userIds[i]      		= approvalRoute[1];
					userNms[i]      		= approvalRoute[2];
					jikgupCds[i]	 		= approvalRoute[3];  
					jikgupNms[i]	  		= approvalRoute[4];
					deptCds[i]				= approvalRoute[5];
					deptNms[i]	  			= approvalRoute[6];
					compCds[i]				= approvalRoute[7];
					compNms[i]				= approvalRoute[8];
					grpCds[i]				= approvalRoute[9];
					grpNms[i]				= approvalRoute[10];
					mailAddress[i]			= approvalRoute[11];
					
					
					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i]  = approvalRouteRight[2];
					arbitrarys[i]   = approvalRouteRight[3];
					
				}
			}
			apprVo.setRef_key(vo.getCnsdreq_id());
			apprVo.setApprvl_clsfcn("C03001");
			apprVo.setMethod("postAppContStatus");
		    apprVo.setActivitys(activitys);
		    apprVo.setAction_types(actionTypes);
		    apprVo.setUser_ids(userIds);
		    apprVo.setUser_names(userNms);
		    apprVo.setDuty_codes(jikgupCds);  
		    apprVo.setDutys(jikgupNms);
		    apprVo.setDept_codes(deptCds);
		    apprVo.setDept_names(deptNms);
		    apprVo.setComp_codes(compCds);
		    apprVo.setComp_names(compNms);
		    apprVo.setGroup_codes(grpCds);
		    apprVo.setGroup_names(grpNms);
		    apprVo.setMail_addresss(mailAddress);
		    
		    apprVo.setRoute_modifys(routeModifys);
		    apprVo.setBody_modifys(bodyModifys);
		    apprVo.setArbitrarys(arbitrarys);
		    
		    //결재상신 의견
		   	String[] opinions = new String[2];
	    	for(int i=0; i<approvalRoutes.length; i++) {
	    		opinions[i] = "";
	    	}
	    	//계약검토의뢰입니다.
	    	opinions[0] = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew03", null, new RequestContext(request).getLocale());
	    	apprVo.setOpinions(opinions);
		   //예약 상신 시간
	    	String[] reserveds = new String[2];
	    	for(int i=0; i<approvalRoutes.length; i++) {
	    		reserveds[i] = "";
	    	}
	    	reserveds[0] = "";
	    	apprVo.setReserveds(reserveds);
	
		    
			// 결재상신
	    	boolean isSuccess = false;
	    	isSuccess = esbApprovalService.submit(apprVo);		

			String result = "N";
	    	if(isSuccess) {
	    		result = "Y";
	    		if(vo.getSubmit_status().equals("req")) {
	    			//의뢰상신건에 대한 정상결재상신후 상태코드 업데이트 처리
	    			modifyConsiderationStatus(request, response);
	    		}
	    	}
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit",  null, new RequestContext(request).getLocale()));    		
    		jo.put("returnValue", "Y");
    		jo.put("returnApproval", result);
		} catch (Exception e) {
			e.printStackTrace();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "Y");
    		jo.put("returnApproval", "N");
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "Y");
    		jo.put("returnApproval", "N");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
	/**
	 * 체결품의상신 후 상태변경
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyRegistrationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			int result = registrationService.insertRegistrationStatus(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			request.setAttribute("returnMessage",returnMessage);
			
			return listManageRegistration(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 *계약상세정보셋팅 - 체결 후 등록
	 */
	public void makeApprovalContractInfo2(StringBuffer sb, List list, String attachinfo, ConsultationVO vo, String last_locale) throws Exception {
		try {
			ListOrderedMap lom				= null;
			ListOrderedMap tempLom			= null;
			ConsultationSpecialVO specialVo	= new ConsultationSpecialVO();	 
			ConsultationExecVO	  execVo	= new ConsultationExecVO();
			ChooseContractVO contractVo     = new ChooseContractVO();
			Locale locale1 = new Locale(last_locale);
			
			int iSize = contractVo.getApproval_yn_arr().length;
			for(int i=0; i < contractVo.getApproval_yn_arr().length; i++) {
				if("Y".equals(contractVo.getApproval_yn_arr()[i])) {
					vo.setCntrt_id(contractVo.getCntrt_id_arr()[i]);
					List detailContract = registrationService.detailContractMaster(vo);
					specialVo.setCntrt_id(vo.getCntrt_id());
					List listSpecial 	= registrationService.listConsultationSpecial(specialVo);
					ArrayList special1List = new ArrayList();
					ArrayList special2List = new ArrayList();
					if(listSpecial.size() >0 && listSpecial != null) {
						 for(int j=0; j < listSpecial.size(); j++) {
							 ListOrderedMap specialLom = (ListOrderedMap)listSpecial.get(j);
							 if("C04002".equals(specialLom.get("crtn_depth"))) {
								 special1List.add(listSpecial.get(j));
							 } else if("C04005".equals(specialLom.get("crtn_depth"))) {
								 special2List.add(listSpecial.get(j));
							 }
						 }
					 }
					
					//  1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
					//  2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
					
					String buGubn = "";   // 인쇄 버튼의 구분
					
					buGubn = (String)vo.getBuGubn(); // 버튼의 구분 여부를 체크 합니다.
					
					//이행정보조회
					execVo.setCntrt_id(vo.getCntrt_id());
					List listExec		= registrationService.listConsultationExec(execVo);
					
					ArrayList listContractAttach = new ArrayList();		//의뢰계약서
					ArrayList listEtcAttach		 = new ArrayList();		//의뢰기타
					ArrayList listPreAttach		 = new ArrayList();		//사전검토
					ArrayList listPriceAttach	 = new ArrayList();		//단가내역
					ArrayList listEtc2Attach	 = new ArrayList();		//의뢰별첨
					
					List listAttachInfo = registrationService.listConsultationApprovalAttachInfo(vo);
					if(listAttachInfo != null && listAttachInfo.size() > 0) {
						for(int j=0; j < listAttachInfo.size(); j++) {
							tempLom = (ListOrderedMap)listAttachInfo.get(j);
							if("1".equals((String)tempLom.get("filetype"))) {
								listContractAttach.add(listAttachInfo.get(j));
							} else if("2".equals((String)tempLom.get("filetype"))) {
								listEtcAttach.add(listAttachInfo.get(j));
							} else if("3".equals((String)tempLom.get("filetype"))) {
								listPreAttach.add(listAttachInfo.get(j));
							} else if("4".equals((String)tempLom.get("filetype"))) {
								listPriceAttach.add(listAttachInfo.get(j));
							} else if("5".equals((String)tempLom.get("filetype"))) {
								listEtc2Attach.add(listAttachInfo.get(j));
							}
						}
					}
					
					String titleIndex = "";
					//계약
					if(iSize > 1) titleIndex = (String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo201", null, locale1) +" "+ (i+1)+" "+ " - ";
					ListOrderedMap lomDetail = (ListOrderedMap)detailContract.get(0);
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div width=\"100%\" class=\"title_basic\">\n")
				      .append("	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n")
				      .append("	<colgroup>\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("	</colgroup>\n") 
				      .append("	    <tr>\n")
				      //계약기본정보
				      .append("	        <td><h4>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo202", null, locale1)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></td>\n");
if("" != lomDetail.get("cntrt_no")){				      
				    sb.append("		    <td align=\"right\"> ID : "+ lomDetail.get("cntrt_no") +"</td>\n");
}
				    sb.append("	    </tr>\n")
				      .append("	</table>\n")
					  .append("</div>\n")
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("		<col width=\"11%\" />\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("		<col width=\"14%\" />\n")
					  .append("		<col width=\"12%\" />\n")
					  .append("		<col width=\"12%\" />\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("	</colgroup>\n") 
			          .append("    	<tr>\n")
			          //계약명
					  .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo203", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"6\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //계약상대방
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
				      //대표자명
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("cntrt_oppnt_rprsntman") + "</td>\n")
				      //업체Type
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("    	<tr>\n")
				      //상대방담당자
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo207", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), "") + "</td>\n")
				      //전화번호
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo208", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_telno"), "") + "</td>\n")
				      .append("        <th>E-mail</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_email"), "") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("	   	<tr class=\"slide-target02 slide-area\">\n")
				      //계약유형
				      .append("	       <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo209", null, locale1)+"</th>\n")
				      .append("    	   <td colspan=\"6\">" + lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_midclsfcn_nm") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("    	<tr>\n")
				      //계약대상
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo210", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"2\">" + lomDetail.get("cntrt_trgt_nm") + "</td>\n")
				      //계약대상상세
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo211", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\">" + lomDetail.get("cntrt_trgt_det")+ "</td>\n")
					  .append("		</tr>\n");
				    if(!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))){
				    sb.append("		<tr>\n")
				      //추진목적 및 배경
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo212", null, locale1)+"</th>\n")
					  .append("			<td colspan=\"6\">" + StringUtil.bvl((String)lomDetail.get("pshdbkgrnd_purps"), "") + "</td>\n")
					  .append("		</tr>\n");
				    }
				    if(!"".equals(StringUtil.bvl(lomDetail.get("antcptnefct"), ""))){
					sb.append("		<tr>\n")
					  //기대효과
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo213", null, locale1)+"</th>\n")
					  .append("			<td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl((String)lomDetail.get("antcptnefct"),"")) + "</td>\n")
					  .append("  	</tr>\n");
				    }
					sb.append("		<tr>\n")
					  //계약기간
					  .append(" 	    <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo214", null, locale1)+"</th>\n")
					  .append("	        <td colspan=\"6\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") +"</td>\n")
					  .append("		</tr>\n")
					  .append("     <tr>\n")
					  //지불/수금 구분
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo215", null, locale1)+"</th>\n");
					if("C02004".equals((String)lomDetail.get("payment_gbn"))) {
						  sb.append("   <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n");
					} else {	  
						  sb.append("   <td colspan=\"2\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
						    //계약금액
						  	.append("   <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo216", null, locale1)+"</th>\n")
					        .append("  	<td align=\"right\" class=\"tR\">" + StringUtil.bvl(lomDetail.get("cntrt_amt"), "0") + "</td>\n")
					        //통화(단위)
					        .append("	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo217", null, locale1)+"</th>\n")
					        .append("	<td>" + lomDetail.get("crrncy_unit") + "</td>\n");
					}      
					sb.append("		</tr>\n");
					if(!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))){
					sb.append("     <tr>\n")
					  //단가내역
					  .append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo218", null, locale1)+"</th>\n")
					  .append("         <td colspan=\"6\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n")
					  .append("     </tr>\n");
					} 
					if(!"".equals(StringUtil.bvl(lomDetail.get("payment_cond"), ""))){
					sb.append("     <tr>\n")
					  //지불/수금 조건
					  .append("	    	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo219", null, locale1)+"</th>\n")
					  .append("	        <td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("payment_cond"), "")) + "</td>\n")
					  .append("     </tr>\n");
					}
					sb.append("	</table>\n")
					  .append("	<div class=\"mt5\"></div>\n")
					  //계약상세정보
				      .append("	<div class=\"title_basic\"><h4>"+" "+ titleIndex +" " +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo220", null, locale1)+"</h4></div>\n")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"20%\" />\n")
				      .append("		<col width=\"80%\" />\n")
				      .append("	</colgroup>\n");
					if(!"".equals(StringUtil.bvl(lomDetail.get("secret_keepperiod"), ""))){
				    sb.append("     <tr>\n")
				      //비밀유지기간
					  .append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo221", null, locale1)+"</th>\n")
					  .append("	        <td> " + StringUtil.bvl(lomDetail.get("secret_keepperiod"), "") + "</td>\n")
					  .append("     </tr>\n");
					}
					//특화조건시작
					if(special1List != null && special1List.size() > 0) {
						if(special1List.size() > 0 ) {
							for(int k=0; k < special1List.size(); k++) {
								ListOrderedMap s1Lom = (ListOrderedMap)special1List.get(k);
								if(!"".equals(StringUtil.bvl(s1Lom.get("attr_value"), ""))){
									sb.append("	<tr>\n")	
									  .append("		<th>" +s1Lom.get("attr_nm") + "</th>\n")
									  .append("		<td>" +  s1Lom.get("attr_value") + "</td>\n")
									  .append("	</tr>\n");
								}
							}	
						}
					}
					
					if(special2List != null && special2List.size() > 0) {
						if(special2List.size() > 0 ) {
							
							for(int k=0; k < special2List.size(); k++) {
								ListOrderedMap s2Lom = (ListOrderedMap)special2List.get(k);
								if(!"".equals(StringUtil.bvl(s2Lom.get("attr_value"), ""))){
								sb.append("<tr>")
								  .append("		<th>" + s2Lom.get("attr_nm") + "</th>\n")
								  .append("		<td>" +  StringUtil.bvl(s2Lom.get("attr_value"), "") + "</td>\n")
								  .append("</tr>\n");
								}
							}
							
						}
					}
					//특화정보끝
					
					
					if(!"".equals(StringUtil.bvl(lomDetail.get("auto_rnew_yn"), ""))){
					sb.append("<tr>\n")
					  //자동연장여부
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo222", null, locale1)+"</th>\n");
						if("Y".equals((String)lomDetail.get("auto_rnew_yn"))) {
					sb.append("	<td>YES</td>\n");
						}else{
					sb.append("	<td>NO</td>\n");
						}
					sb.append("</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("oblgt_lmt"), ""))){
					sb.append("<tr>\n")
					  //배상책임한도
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo223", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("oblgt_lmt"), "") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("spcl_cond"), ""))){				  
					sb.append("	<tr>\n")
					  //기타 특약사항
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo224", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("spcl_cond"), "") + "</td>\n")
					  .append("	</tr>\n");
					}					
					if(!"".equals(StringUtil.bvl(lomDetail.get("loac"), ""))){	
					sb.append("	<tr>\n")
					  //준거법
					  .append("	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo225", null, locale1)+"</th>\n")
					  .append("		<td>" + lomDetail.get("loac") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("loac_etc"), ""))){					
					sb.append("	<tr>\n")
					  //준거법상세
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo226", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("loac_etc"), "")) + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd"), ""))){
					sb.append("	<tr>\n")
					  //분쟁해결방법
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo227", null, locale1)+"</th>\n")
					  .append(" 	<td>" + lomDetail.get("dspt_resolt_mthd") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), ""))){					  
					sb.append("	<tr>\n")
					  //분쟁해결방법상세
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo228", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), "")) + "</td>\n")
					  .append("	</tr>\n");
					}
					sb.append(" <tr>\n")
					  //법무팀검토담당자
					  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo229", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("cnsdman_nm"), "") + 
							  			"/" + StringUtil.bvl(lomDetail.get("cnsd_jikgup_nm"), "")+ 
							  			"/" + StringUtil.bvl(lomDetail.get("cnsd_dept_nm"), "")+ 
					  					"</td>\n")
					  .append("	</tr>\n")
					  .append(" <tr>\n")
					  //최종법무검토의견
					  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo230", null, locale1)+"</th>\n")
					  .append("		<td>" + StringUtil.convertCharsToHtml(StringUtil.bvl((String)lomDetail.get("cnsd_opnn"), "")) + "</td>\n")
					  .append("	</tr>\n")
					  .append("</table>\n");
					
					// TODO 2011-12-05 김형준 : 고객요청으로 체결 품의상신시 주요이행사항, 지불계획, 기타이행계획 안보이게 한다.
					if("C04211".equals((String)lom.get("prgrs_status")) || "C04212".equals((String)lom.get("prgrs_status")) || 
						"C04213".equals((String)lom.get("prgrs_status")) || "C04214".equals((String)lom.get("prgrs_status")) || 
						"C04215".equals((String)lom.get("prgrs_status")) || "C04216".equals((String)lom.get("prgrs_status")) ||
						"C04211".equals((String)lom.get("prgrs_status"))) 
					{
						
					}else{
						
						int iC05501Size = 0;	//지불계획
						int iC05502Size = 0;	//수금계획
						int iC05503Size = 0;	//기타이행계획
						for(int j=0; j < listExec.size();j++) {
							ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
							if("C05501".equals(execLom.get("exec_gbn"))) {
								iC05501Size++;
							} else if("C05502".equals(execLom.get("exec_gbn"))) {
								iC05502Size++;
							} else {
								iC05503Size++;
							}
						}
						
					if(iC05501Size != 0 || iC05502Size != 0 || iC05503Size != 0){
						
						
						//이행사항시작					
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic\">\n")
					      //주요이행사항
					      .append("	<h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo231", null, locale1)+"</h4>\n")
					      .append(" </div>");
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02002".equals((String)lomDetail.get("payment_gbn")) || iC05501Size > 0) {	//지불
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic\">\n")
						      //지불계획
						      .append("	<h5 class=\"ntitle05\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo232", null, locale1)+" </h5>\n")
						      .append(" </div>")
						      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("	</colgroup>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n")
							  .append("	</colgroup>\n");
}
							sb.append("	<thead>\n")
							  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo235", null, locale1)+"</th>\n")
							  //지불조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo236", null, locale1)+"</th>\n");
} else {                 // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo235", null, locale1)+"</th>\n")
							  //지불조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo236", null, locale1)+"</th>\n")
							  //완료일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo237", null, locale1)+"</th>\n")
							  //비고
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo241", null, locale1)+"</th>\n")
							  //확인여부
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo238", null, locale1)+"</th>\n");
}
							sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
						    if(iC05501Size > 0) {
						    	int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05501".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(												
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
								    	  .append("	</tr>\n");
} else {
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n")
										  .append("	</tr>\n");
}
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						}
						
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02003".equals((String)lomDetail.get("payment_gbn")) || iC05502Size > 0) {	//수금
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic\">\n")
						      //수금계획
						      .append("	<h5 class=\"ntitle05\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo239", null, locale1)+"</h5>\n")
						      .append(" </div>")
							  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n")
							  .append("	<colgroup>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우			  
						    sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n");
}
							sb.append("	</colgroup>\n")
							  .append("	<thead>\n")
							  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
							  //완료예정일 
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo235", null, locale1)+"</th>\n")
							  //수금조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo240", null, locale1)+"</th>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo235", null, locale1)+"</th>\n")
							  //수금조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo240", null, locale1)+"</th>\n")
							  //완료일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo237", null, locale1)+"</th>\n")
							  //비고
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo241", null, locale1)+"</th>\n")
							  //확인여부
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo238", null, locale1)+"</th>\n");
}
							sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
							if(iC05502Size > 0) {
								int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05502".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
}
								    	sb.append("	</tr>\n");
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						} 
						if(iC05503Size > 0) {
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic\">\n")
					      //기타이행계획
					      .append("	<h5 class=\"ntitle05\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo242", null, locale1)+"</h5>\n")
					      .append(" </div>")
						  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n")
						  .append("	<colgroup>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
					    sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"20%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"13%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"16%\"/>\n")
						  .append("		<col width=\"10%\"/>\n");
}
						sb.append("	</colgroup>\n")
						  .append("	<thead>\n")
						  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
						  //완료예정일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
						  .append(" 	<th>Description</th>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo233", null, locale1)+"</th>\n")
						  //완료예정일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo234", null, locale1)+"</th>\n")
						  .append(" 	<th>Description</th>\n")
						  //완료일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo237", null, locale1)+"</th>\n")
						  //비고
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo241", null, locale1)+"</th>\n")
						  //확인여부
						  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo238", null, locale1)+"</th>\n");
}
						sb.append("	</tr>\n")
						  .append("	</thead>\n")
						  .append("	<tbody>\n");
						
							int k = 1;
					    	for(int j = 0; j < listExec.size(); j++) {
								ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
								if("C05503".equals(execLom.get("exec_gbn"))) {
									sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우									
							    	sb.append("		<td align=\"center\">" + k + "</td>\n")
							    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
							    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<td align=\"center\">" + k + "</td>\n")
									  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
									  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
}
								    sb.append("	</tr>\n");							 		
									k++;
								}
							}
						}
					}
					
					sb.append("</tbody>\n")
					  .append("</table>\n")
					  .append("</div>\n");
// 이행사항 종료
					}
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //체결정보
				      .append("	<h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo243", null, locale1)+"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"20%\" />\n")
				      .append("	</colgroup>\n")
				      .append("		<tr>\n")
				      //
				      .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo244", null, locale1)+"</th>\n");
				    if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
				    	//직인
				    	sb.append("	<td colspan=\"2\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo245", null, locale1)+"</td>\n");
				    } else {
				    	//서명
				    	sb.append("	<td colspan=\"2\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo246", null, locale1)+"</td>\n");
				    }
				    //만료사전알림일
				      sb.append("		</tr>\n");
				      sb.append("   	<tr>\n");
				      
				    if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
				    	//날인담당자
				    	sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo248", null, locale1)+"</th>")
				          .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmt_dept_nm"), "") + "</td>\n");
				    } else {
				    	//서명예정자
				    	sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo249", null, locale1)+"</th>")
				    	  .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("sign_plndman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plndman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plnd_dept_nm"), "") + "</td>\n");
				    }
				    //체결예정일
				    sb.append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo250", null, locale1)+"</th>\n")
				      .append("		<td colspan=\"2\">" + lomDetail.get("cnclsn_plndday") + "</td>\n")
				  	  .append("</tr>\n")
				  	  .append("	<tr>\n")
				  	  //계약담당자
				  	  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo251", null, locale1)+"</th>\n")
				  	  .append("		<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_resp_dept_nm"), "") + "</td>\n")
				  	  .append("	</tr>\n")
				  	  .append("</table>\n");
				    
				    sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //첨부파일
				      .append("	<h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo252", null, locale1)+"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"14%\" />\n")
				      .append("		<col />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
				      //계약서
				      .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo253", null, locale1)+"</th>\n");
				      if(listContractAttach != null && listContractAttach.size() > 0) {
				    	  tempLom = (ListOrderedMap)listContractAttach.get(0);
				    	  sb.append("<td><a href=\"" + attachinfo + (String)tempLom.get("file_id")+ "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				      } else {
						sb.append("");
				      }
				    sb.append("</tr>\n");
				    
				    if(listEtc2Attach != null && listEtc2Attach.size() > 0) {
				    	for(int k=0; k < listEtc2Attach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtc2Attach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtc2Attach.size()==1) {
				    				//첨부/별첨
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo254", null, locale1)+"</td>\n");
				    			} else {
				    				//첨부/별첨
				    				sb.append("<th rowspan=\"" + listEtc2Attach.size()+ "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo254", null, locale1)+"</td>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    if(listPreAttach != null && listPreAttach.size() > 0) {
				    	for(int k=0; k < listPreAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPreAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPreAttach.size()==1) {
				    				//사전승인
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo255", null, locale1)+"</td>\n");
				    			} else {
				    				//사전승인
				    				sb.append("<th rowspan=\"" + listPreAttach.size() + "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo255", null, locale1)+"</td>\n");
				    			}
					    		sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    if(listEtcAttach != null && listEtcAttach.size() > 0) {
				    	for(int k=0; k < listEtcAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtcAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtcAttach.size()==1) {
				    				//기타
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo256", null, locale1)+"</th>\n");
				    			} else {
				    				//기타
				    				sb.append("<th rowspan=\"" + listEtcAttach.size()+ "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo256", null, locale1)+"</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    if(listPriceAttach != null && listPriceAttach.size() > 0) {
				    	for(int k=0; k < listPriceAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPriceAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPriceAttach.size()==1) {
				    				//단가정보
				    				sb.append("<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo257", null, locale1)+"</th>\n");
				    			} else {
				    				//단가정보
				    				sb.append("<th rowspan=\"" + listPriceAttach.size()+ "\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo257", null, locale1)+"</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    sb.append("</table>\n");
				}	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}	
	}
	
	//계약상세정보셋팅 
	public void makeApprovalContractInfoNew(StringBuffer sb, List list, String attachinfo, ConsultationVO vo, String last_locale) throws Exception {
		try {
			ListOrderedMap lom				= null;
			ListOrderedMap tempLom			= null;
			ConsultationSpecialVO specialVo	= new ConsultationSpecialVO();	 
			ConsultationExecVO	  execVo	= new ConsultationExecVO();
			Locale locale1 = new Locale(last_locale);
					vo.setSession_user_locale(last_locale);
					List detailContract = registrationService.detailConsiderationReg(vo);
					specialVo.setCntrt_id(vo.getCntrt_id());
					List listSpecial 	= registrationService.listConsultationSpecial(specialVo);
					ArrayList special1List = new ArrayList();
					ArrayList special2List = new ArrayList();
					if(listSpecial.size() >0 && listSpecial != null) {
						 for(int j=0; j < listSpecial.size(); j++) {
							 ListOrderedMap specialLom = (ListOrderedMap)listSpecial.get(j);
							 if("C04002".equals(specialLom.get("crtn_depth"))) {
								 special1List.add(listSpecial.get(j));
							 } else if("C04005".equals(specialLom.get("crtn_depth"))) {
								 special2List.add(listSpecial.get(j));
							 }
						 }
					 }

					/******************************************************
					 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
					 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
					 **************************************************/
					String buGubn = "";   // 인쇄 버튼의 구분
					buGubn = (String)vo.getBuGubn(); // 버튼의 구분 여부를 체크 합니다.
					
					//연관계약정보
					List relationList = registrationService.listContractRelation(vo);
					
					//이행정보조회
					execVo.setCntrt_id(vo.getCntrt_id());
					List listExec		= registrationService.listConsultationExec(execVo);
					
					ArrayList listAttach	 	= new ArrayList();		//사본등록첨부
					
					List listAttachInfo = registrationService.listConsultationApprovalAttachInfo(vo);
					if(listAttachInfo != null && listAttachInfo.size() > 0) {
						for(int j=0; j < listAttachInfo.size(); j++) {
							tempLom = (ListOrderedMap)listAttachInfo.get(j);
							if("hisSign".equals((String)tempLom.get("filetype"))) {
								listAttach.add(listAttachInfo.get(j));
							}
						}
					}
					
					String titleIndex = "";
					ListOrderedMap lomDetail = (ListOrderedMap)detailContract.get(0);
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("<div class=\"title_basic\">\n")
				      .append("<h4>"+(String)messageSource.getMessage("clm.page.msg.manage.aftConclReg", null, locale1)+"</h4>") //체결후등록
				      .append("</div>")
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
                      .append("	  <col width='13%' />\n")
	                  .append("   <col width='54%' />\n")
	                  .append("   <col width='12%' />\n")
	                  .append("   <col width='21%' />\n")
					  .append("	</colgroup>\n")
					  .append("    	<tr>\n")
					  //의뢰명
					  .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew01", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\"><span class=\"fL\">" + lomDetail.get("req_title") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //의뢰자
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew02", null, locale1)+"</th>\n")
				      .append("        <td><span class=\"fL\">" + lomDetail.get("reqman_nm") +" / " + lomDetail.get("reqman_jikgup_nm") + "/ " + lomDetail.get("req_dept_nm") + "</span></td>\n")
				      //의뢰일
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew03", null, locale1)+"</th>\n")
				      .append("        <td><span class=\"fL\">" + lomDetail.get("req_dt") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("	</table>\n")
				      .append("<div class=\"title_basic\">\n")
				      .append("<h4>"+(String)messageSource.getMessage("clm.page.msg.manage.contInfo", null, locale1)+"</h4>")//계약정보
				      .append("</div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
                      .append("	  <col width='13%' />\n")
	                  .append("   <col width='21%' />\n")
	                  .append("   <col width='14%' />\n")
	                  .append("   <col width='19%' />\n")
	                  .append("   <col width='12%' />\n")
	                  .append("   <col width='21%' />\n")
					  .append("	</colgroup>\n")
			          .append("    	<tr>\n")
			          //계약명
					  .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew04", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"5\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //계약상대방
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
				      //대표자명
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("cntrt_oppnt_rprsntman") + "</td>\n")
				      //상대방유형
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n")
				      .append("	   	</tr>\n")
					  .append("	   	<tr class=\"slide-target02 slide-area\">\n")
					  //계약유형
				      .append("	       <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew08", null, locale1)+"</th>\n")
				      .append("    	   <td colspan=\"5\">" + lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_midclsfcn_nm") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("    	<tr>\n")
				      //계약대상
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew09", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("cntrt_trgt_nm") + "</td>\n")
				      //계약대상상세
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew10", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\">" + lomDetail.get("cntrt_trgt_det")+ "</td>\n")
					  .append("		</tr>\n")
					  .append("		<tr>\n")
					  

					   .append("    	<tr>\n")
				      // 30대 거래선
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.manage.top30Cus", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("top_30_cus_nm"),"") + "</td>\n")
				      //관련상품
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.manage.relatedPrd", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("related_products_nm"),"") + "</td>\n")
				      //cont_draft
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.msg.manage.srcContDraft", null, locale1)+"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cont_draft_nm"),"") + "</td>\n")
				 
				      .append("	   	</tr>\n")				  
					  
					  
					  //계약기간
					  .append(" 	    <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew11", null, locale1)+"</th>\n")
					  .append("	        <td colspan=\"3\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") +"</td>\n")
					  //지불/수금 구분
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew12", null, locale1)+"</th>\n")
					  .append("         <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
					  .append("		</tr>\n") ;
					if(!"C02004".equals((String)lomDetail.get("payment_gbn"))) {
					sb.append("		<tr>\n")
					  //계약금액
					  .append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew13", null, locale1)+"</th>\n")
					  //(단가로 체결)
					  .append("  	    <td colspan=\"3\">" + lomDetail.get("cntrt_amt")+ (!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "")) ? " "+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew14", null, locale1) : "") +  "</td>\n")
					  //통화(단위)
					  .append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew15", null, locale1)+"</th>\n")
			          .append("         <td>" + lomDetail.get("crrncy_unit") + "</td>\n")
					  .append("		</tr>\n") ;
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))){
						sb.append("     <tr>\n")
						  //단가내역
						  .append("         <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew16", null, locale1)+"</th>\n")
						  .append("         <td colspan=\"5\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n")
						  .append("     </tr>\n");
						}
				    if(!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))){
					sb.append("		<tr>\n")
					  //추진목적 및 배경
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew17", null, locale1)+"</th>\n")
					  .append("			<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("pshdbkgrnd_purps"), "") + "</td>\n")
					  .append("		</tr>\n");
				    }
					if(!"".equals(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl(lomDetail.get("if_sys_cd"), ""))) {
					  sb.append("     <tr>\n")
					    //기타 주요사항
					    .append("	    	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew18", null, locale1)+"</th>\n");
						if(!"".equals((String)lomDetail.get("if_sys_cd"))) {
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.bvl(lomDetail.get("etc_main_cont"), "") + " [" + lomDetail.get("if_sys_cd") + "]</td>\n");
						}else{
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.bvl(lomDetail.get("etc_main_cont"), "") + "</td>\n");
						}
					  sb.append("     </tr>\n");						
					}

					  sb.append("	</table>\n")
					  .append("<h5 class=\"title_basic3\">"+(String)messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml01", null, locale1)+"</h5>")//사전승인정보
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
                      .append("	  <col width='13%' />\n")
	                  .append("   <col width='21%' />\n")
	                  .append("   <col width='14%' />\n")
	                  .append("   <col width='19%' />\n")
	                  .append("   <col width='12%' />\n")
	                  .append("   <col width='21%' />\n")
					  .append("	</colgroup>\n")
			          .append("    	<tr>\n")
			          //승인일자
					  .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew19", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\"><span class=\"fL\">" + (String)lomDetail.get("bfhdcstn_apbtday") +"</span></td>\n")
				      //승인방식
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew20", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("bfhdcstn_apbt_mthd_nm") + "</td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //발의자
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew21", null, locale1)+"</th>\n")
				      .append("        <td colspan=\"3\">" + lomDetail.get("bfhdcstn_mtnman_nm") +"/"+ lomDetail.get("bfhdcstn_mtnman_jikgup_nm") +"/"+ lomDetail.get("bfhdcstn_mtn_dept_nm") +"</td>\n")
				      //승인자
				      .append("        <th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew22", null, locale1)+"</th>\n")
				      .append("        <td>" + lomDetail.get("bfhdcstn_apbtman_nm") +"/"+ lomDetail.get("bfhdcstn_apbtman_jikgup_nm") +"/"+ lomDetail.get("bfhdcstn_apbt_dept_nm") +"</td>\n")
				      .append("	   	</tr>\n")
				      .append("	</table>\n")
				      .append("<h5 class=\"title_basic3\">"+(String)messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml01", null, locale1)+"</h5>\n")//연관계약정보
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("		<colgroup>\n")
					  .append("			<col width=\"13%\" />\n")
					  .append("			<col width=\"50%\" />\n")
					  .append("			<col width=\"10%\" />\n")
					  .append("			<col/>\n")
					  .append("		</colgroup>\n")
					  .append("		<tr>\n")
					  //관계
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew23", null, locale1)+"</th>\n")
					  //연관계약
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew24", null, locale1)+"</th>\n")
					  //세부정의
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew25", null, locale1)+"</th>\n")
					  //관계상세
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew26", null, locale1)+"</th>\n")
					  .append("		</tr>\n");
					  if(relationList != null && relationList.size() > 0){
						  for(int i = 0; i < relationList.size(); i++){
							  ListOrderedMap relationLom = (ListOrderedMap)relationList.get(i);
							  sb.append("<tr>\n")
							  .append("<td>" + relationLom.get("rel_type_nm")+"</td>\n")
							  .append("<td>" + relationLom.get("relation_cntrt_nm")+"</td>\n")
							  .append("<td>" + relationLom.get("rel_defn")+"</td>\n")
							  .append("<td>" + relationLom.get("expl")+"</td>\n")
							  .append("</tr>\n");
						  }
						  
					  }else{
						  sb.append("<tr>\n")
						  //등록된 연관계약이 없습니다. 
						  .append("<td colspan=\"4\" align=\"center\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew27", null, locale1)+"</td>\n")
						  .append("</tr>\n");
					  }
					  sb.append(" </table>\n");
					
					  
					  sb.append("<div class=\"mt5\"></div>\n")
					  .append("	<div class=\"title_basic\">\n")
					  .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml01", null, locale1)+"</h4>\n")//체결정보
					  .append(" </div>")
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
                      .append("	  <col width='13%' />\n")
	                  .append("   <col width='21%' />\n")
	                  .append("   <col width='14%' />\n")
	                  .append("   <col width='19%' />\n")
	                  .append("   <col width='12%' />\n")
	                  .append("   <col width='21%' />\n")
					  .append("	</colgroup>\n")
					  .append("		<tr>\n")
					  //직인서명구분
					  .append("			<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew31", null, locale1)+"</th>\n");
					  if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
						  //사용인감날인
						  sb.append("	<td colspan=\"3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew29", null, locale1)+"</td>\n");
					  }else if("C02103".equals((String)lomDetail.get("seal_mthd"))){
						    //법인인감날인
					    	sb.append("	<td colspan=\"3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew30", null, locale1)+"</td>\n");
					  } else {
						    //서명
					    	sb.append("	<td colspan=\"3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew31", null, locale1)+"</td>\n");
					  }
					  //만료사전알림일
					  sb.append("		</tr>\n");
					  sb.append("   	<tr>\n");
					  //체결일
					  sb.append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew35", null, locale1)+"</th>\n")
					  .append("		<td colspan=\"5\">" + lomDetail.get("cntrt_cnclsnday") + "</td>\n")
					  .append("</tr>\n")
					  .append("	<tr>\n")
					  //계약담당자
					  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew36", null, locale1)+"</th>\n")
					  .append("		<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_resp_dept_nm"), "") + "</td>\n")
					  .append("	</tr>\n")
					  .append("</table>\n");
					int iC05501Size = 0;	//지불계획
					int iC05502Size = 0;	//수금계획
					int iC05503Size = 0;	//기타이행계획
					for(int j=0; j < listExec.size();j++) {
						ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
						if("C05501".equals(execLom.get("exec_gbn"))) {
							iC05501Size++;
						} else if("C05502".equals(execLom.get("exec_gbn"))) {
							iC05502Size++;
						} else {
							iC05503Size++;
						}
					}
						
					if(iC05501Size != 0 || iC05502Size != 0 || iC05503Size != 0){
						
						
						//이행사항시작					
					      sb.append("	<div class=\"title_basic\">\n")
					      //주요이행사항
					      .append("	<h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew37", null, locale1)+"</h4>\n")
					      .append(" </div>");
						
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02002".equals((String)lomDetail.get("payment_gbn")) || iC05501Size > 0) {	//지불
							  //지불계획
						      sb.append("	<h5 class=\"title_basic3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew38", null, locale1)+" </h5>\n")
						      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("	</colgroup>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n")
							  .append("	</colgroup>\n");
	}
								sb.append("	<thead>\n")
							  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew41", null, locale1)+"</th>\n")
							  //지불조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew42", null, locale1)+"</th>\n");
	} else {                 // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew41", null, locale1)+"</th>\n")
							  //지불조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew42", null, locale1)+"</th>\n")
							  //완료일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew43", null, locale1)+"</th>\n")
							  //비고
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew44", null, locale1)+"</th>\n")
							  //확인여부
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew45", null, locale1)+"</th>\n");
	}
		
								sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
						    if(iC05501Size > 0) {
						    	int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05501".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(												
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
								    	  .append("	</tr>\n");
	} else {
											sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n")
										  .append("	</tr>\n");
	}
									 		k++;
										}
									}
							    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						}
						
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02003".equals((String)lomDetail.get("payment_gbn")) || iC05502Size > 0) {	//수금
						    //수금계획  
							sb.append("	<h5 class=\"title_basic3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew46", null, locale1)+"</h5>\n")
							  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
							  .append("	<colgroup>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우			  
						    sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n");
	}
								sb.append("	</colgroup>\n")
							  .append("	<thead>\n")
							  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew41", null, locale1)+"</th>\n")
							  //수금조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew47", null, locale1)+"</th>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
							  //완료예정일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
							  //금액
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew41", null, locale1)+"</th>\n")
							  //수금조건
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew47", null, locale1)+"</th>\n")
							  //완료일
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew43", null, locale1)+"</th>\n")
							  //비고
							  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew44", null, locale1)+"</th>\n")
							  //확인여부
							  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew45", null, locale1)+"</th>\n");
	}
								sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
							if(iC05502Size > 0) {
								int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05502".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
	}
									    	sb.append("	</tr>\n");
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						} 
						if(iC05503Size > 0) {
						  //기타이행계획	
					      sb.append("	<h5 class=\"title_basic3\">"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew48", null, locale1)+"</h5>\n")
						  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
						  .append("	<colgroup>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
					    sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"20%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"13%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"16%\"/>\n")
						  .append("		<col width=\"10%\"/>\n");
	}
							sb.append("	</colgroup>\n")
						  .append("	<thead>\n")
						  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
						  //완료예정일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
						  .append(" 	<th>Description</th>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew39", null, locale1)+"</th>\n")
						  //완료예정일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew40", null, locale1)+"</th>\n")
						  .append(" 	<th>Description</th>\n")
						  //완료일
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew43", null, locale1)+"</th>\n")
						  //비고
						  .append(" 	<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew44", null, locale1)+"</th>\n")
						  //확인여부
						  .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew45", null, locale1)+"</th>\n");
	}
							sb.append("	</tr>\n")
						  .append("	</thead>\n")
						  .append("	<tbody>\n");
							int k = 1;
					    	for(int j = 0; j < listExec.size(); j++) {
								ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
								if("C05503".equals(execLom.get("exec_gbn"))) {
									sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우	
							    	sb.append("		<td align=\"center\">" + k + "</td>\n")
							    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
							    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<td align=\"center\">" + k + "</td>\n")
									  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
									  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
	}
									    sb.append("	</tr>\n");							 		
									k++;
								}
							}
					    }
					}
					sb.append("</tbody>\n")
					  .append("</table>\n") ;
	// 이행사항 종료
				    sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //첨부파일
				      .append("	<h4>" + titleIndex +(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew49", null, locale1)+"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"14%\" />\n")
				      .append("		<col />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
				      //사본등록
				      .append("		<th>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfoNew50", null, locale1)+"</th>\n");
				      if(listAttach != null && listAttach.size() > 0) {
				    	  tempLom = (ListOrderedMap)listAttach.get(0);
				    	  sb.append("<td><a href=\"" + attachinfo + (String)tempLom.get("file_id")+ "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				      } else {
						sb.append("");
				      }
				    sb.append("</tr>\n");
				    sb.append("</table>\n");
				//}
			//}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}	
	}
	//검토의뢰정보셋팅
	public void makeApprovalReqInfo(StringBuffer sb, ListOrderedMap lom, String attachinfo, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);
			sb.append("<div id=\"wrap\">\n")
			  .append("    <div id=\"container\">\n")
			  .append("    <div class=\"m_header menu2\">\n")
			  .append("    <h1></h1>\n")
			  .append("    <h2><span class=\"confidential\"></SPAN></h2>\n")
			  .append("    </div>\n")
			  .append("        <div id=\"m_container\">\n")
			  .append("        <div class=\"contents\">\n")
			  //체결후등록 품의작성결재를 상신하오니 재가하여 주시기 바랍니다.
			  .append("        <h3><p>"+(String)messageSource.getMessage("clm.page.field.regist.makeApprovalReqInfo11", null, locale1)+"</p></h3>\n");
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
}