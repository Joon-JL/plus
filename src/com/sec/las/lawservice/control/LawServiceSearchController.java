/**
 * Project Name : 법무시스템
 * File Name : LawServiceSearchController.java
 * Description : 로펌서비스 검색 Controller
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.control;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LwsSearchForm;
import com.sec.las.lawservice.dto.LwsSearchVO;
import com.sec.las.lawservice.service.EventManageService;
import com.sec.las.lawservice.service.LawServiceSearchService;
import com.sec.las.lawservice.service.LawyerManageService;

public class LawServiceSearchController extends CommonController {
	private LawServiceSearchService lawServiceSearchService;
	public void setLawServiceSearchService(LawServiceSearchService lawServiceSearchService) {
		this.lawServiceSearchService = lawServiceSearchService;
	}
	
	private LawyerManageService lawyerManageService;	
	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
	
	private EventManageService eventManageService;
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * 검색
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawServiceSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			ModelAndView mav = new ModelAndView();

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceSearch_l.jsp";

			LwsSearchForm form = new LwsSearchForm();
			LwsSearchVO vo = new LwsSearchVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			check_XSS_form(form);
			check_XSS_vo(vo);		
				
			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List srch_event_list = lawyerManageService.listLawyerManageGetEventList(evo);
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			List srch_lawfirm_list = lawyerManageService.getListLawfirm(lvo);
			
			// 총괄 comboListBox
			List srch_dept_list = eventManageService.getListDept();
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

			String returnMessage = "";			
			
			if(!check_form(form)){
				mav.setViewName(forwardURL);
				mav.addObject("resultList", null);
				mav.addObject("pageUtil", pageUtil);
				mav.addObject("command", form);
				mav.addObject("srch_event_list", srch_event_list);
				mav.addObject("srch_dept_list", srch_dept_list);
				mav.addObject("srch_lawfirm_list", srch_lawfirm_list);
				return mav;
			}

			List resultList = lawServiceSearchService.listLawServiceSearch(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("srch_event_list", srch_event_list);
			mav.addObject("srch_lawfirm_list", srch_lawfirm_list);
			mav.addObject("srch_dept_list", srch_dept_list);

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
	 * 검색결과 품의양식
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popLawServiceSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			ModelAndView mav = new ModelAndView();

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceSearch_p.jsp";

			LwsSearchForm form = new LwsSearchForm();
			LwsSearchVO vo = new LwsSearchVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			check_XSS_form(form);
			check_XSS_vo(vo);		

			String returnMessage = "";			
			
			if(!check_form(form)){
				mav.setViewName(forwardURL);
				mav.addObject("resultList", null);
				mav.addObject("command", form);
				//검색 옵션을 입력해 주십시오.
				mav.addObject("returnMessage", messageSource.getMessage("las.page.field.lawservicesearch.popLawServiceSearch01", null, new RequestContext(request).getLocale()));
				return mav;
			}

			List resultList = lawServiceSearchService.excelLawServiceSearch(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	 * 검색결과  EXCEL 다운로드
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			DecimalFormat df = new  DecimalFormat("#,##0.00");
			
			double x = 0.00;
			
			ListOrderedMap lom = null;
			
			LwsSearchForm form = new LwsSearchForm();
			LwsSearchVO vo = new LwsSearchVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			check_XSS_form(form);
			check_XSS_vo(vo);
			
			//검색옵션 체크
			if(!check_form(form)){
				return;
			}
			
			/*********************************************************
			 * 목록조회
			**********************************************************/
	        List resultList = lawServiceSearchService.excelLawServiceSearch(vo); 
	        
	        // 엑셀출력시 <br>제거
	        for(int i=0; i<resultList.size(); i++){
				lom = (ListOrderedMap)resultList.get(i);
		        lom.put("dept_nm", StringUtil.replace((String)lom.get("dept_nm"),"<br>","\r\n"));
		        lom.put("event_nm", StringUtil.replace((String)lom.get("event_nm"),"<br>","\r\n"));
		        lom.put("lawfirm_nm", StringUtil.replace((String)lom.get("lawfirm_nm"),"<br>","\r\n"));
		        lom.put("crrncy_unit", StringUtil.replace((String)lom.get("crrncy_unit"),"<br>","\r\n"));
		        BigDecimal dec_claim_amt = (BigDecimal)lom.get("claim_amt");
		        if(dec_claim_amt == null){
		        	dec_claim_amt = new BigDecimal("0.00");
		        }
		        String str_claim_amt = df.format(dec_claim_amt.doubleValue());
		        //lom.put("claim_amt", str_claim_amt); 금액을 문자열에서 Decimal 형태로 수정처리  세자리수 컴마처리를 할려면 현재 프레임워크에서는 문자열로 처리할수 밖에 없음 <프레임워크 수정필요>
		        lom.put("claim_amt", dec_claim_amt);
		        lom.put("invoice_no", StringUtil.replace((String)lom.get("invoice_no"),"<br>","\r\n"));
		        lom.put("srvcday", StringUtil.replace((String)lom.get("srvcday"),"<br>","\r\n"));
		        lom.put("acptday", StringUtil.replace((String)lom.get("acptday"),"<br>","\r\n"));
		        lom.put("unpayday", StringUtil.replace((String)lom.get("unpayday"),"<br>","\r\n"));
		        lom.put("remitplndday", StringUtil.replace((String)lom.get("remitplndday"),"<br>","\r\n"));
		        lom.put("remitday", StringUtil.replace((String)lom.get("remitday"),"<br>","\r\n"));
		        BigDecimal dec_remit_amt = (BigDecimal)lom.get("remit_amt");
		        if(dec_remit_amt == null){
		        	dec_remit_amt = new BigDecimal("0.00");
		        }
		        String str_remit_amt = df.format(dec_remit_amt.doubleValue());
		        // lom.put("remit_amt", str_remit_amt);금액을 문자열에서 Decimal 형태로 수정처리 
		        lom.put("remit_amt", dec_remit_amt);
		        BigDecimal dec_usd_amt = (BigDecimal)lom.get("usd_amt");
		        if(dec_usd_amt == null){
		        	dec_usd_amt = new BigDecimal("0.00");
		        }
		        String str_usd_amt = df.format(dec_usd_amt.doubleValue());
		        // lom.put("usd_amt", str_usd_amt);금액을 문자열에서 Decimal 형태로 수정처리
		        lom.put("usd_amt", dec_usd_amt);

	        }
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
	        String ExelNm  = request.getParameter("exel_nm");
	        String ExelVel = request.getParameter("exel_vel");
	        //품의양식
	        String T1 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad01", null, new RequestContext(request).getLocale());
	        //대외비
	        String T2 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad02", null, new RequestContext(request).getLocale());
	        //총괄
	        String S01 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad03", null, new RequestContext(request).getLocale());
	        //사건명
	        String S02 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad04", null, new RequestContext(request).getLocale());
	        //화폐단위
	        String S03 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad05", null, new RequestContext(request).getLocale());
	        //청구액
	        String S04 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad06", null, new RequestContext(request).getLocale());
	        //용역기간
	        String S05 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad07", null, new RequestContext(request).getLocale());
	        //미지급일자
	        String S06 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad08", null, new RequestContext(request).getLocale());
	        //송금일
	        String S07 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad09", null, new RequestContext(request).getLocale());
	        //송금액
	        String S08 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad10", null, new RequestContext(request).getLocale());
	        //접수일
	        String S09 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad11", null, new RequestContext(request).getLocale());
	        //지급기일
	        String S10 = (String)messageSource.getMessage("las.page.field.lawservicesearch.excelDownLoad12", null, new RequestContext(request).getLocale());
	        
	        //품의양식
	        String fileNm  = T1 + "_" + DateUtil.today() + ".xls";
			// String strTitleInfo = vo.getYear()+"년 "+vo.getWeekseq()+"주차 ( "+form.getWeekFirstDay()+" ~ "+form.getWeekLastDay()+") ";
	        //품의양식
			String[] sheetNmAry = new String[] {T1+DateUtil.today()};			
			
			// 송금일경우      미지급일자, 송금일, 송금액
			// 미송금일경우  접수일, 지급기일
			// 전체일경우     접수일,미지급일자, 지급기일, 송금일, 송금액
			String[] subTitleInfo;
			String[] columnInfo;
			
			//대외비
			String[] titleInfo   = new String[] {T2};		//ExelNm.split(",");
			if ( vo.getSrch_remit_yn().equals("Y")){
				subTitleInfo  = new String[]{S01,S02,"LAWFIRM",S03,S04,"INVOICE",S05,S06,S07,S08,"USD"};		//ExelNm.split(",");
				columnInfo  = new String[] {"dept_nm","event_nm","lawfirm_nm","crrncy_unit","claim_amt","invoice_no","srvcday","unpayday","remitplndday","remit_amt","usd_amt"};//ExelVel.split(",");
			}else if( vo.getSrch_remit_yn().equals("N")){
				subTitleInfo  = new String[]{S01,S02,"LAWFIRM",S03,S04,"INVOICE",S05,S06,S09,S10,"USD"};		//ExelNm.split(",");
				columnInfo  = new String[] {"dept_nm","event_nm","lawfirm_nm","crrncy_unit","claim_amt","invoice_no","srvcday","unpayday","acptday","remitplndday","usd_amt"};//ExelVel.split(",");
			}else{
				subTitleInfo  = new String[]{S01,S02,"LAWFIRM",S03,S04,"INVOICE",S05,S09,S06,S10,S07,S08,"USD"};		//ExelNm.split(",");
				columnInfo  = new String[] {"dept_nm","event_nm","lawfirm_nm","crrncy_unit","claim_amt","invoice_no","srvcday","acptday","unpayday","remitplndday","remitday","remit_amt","usd_amt"};//ExelVel.split(",");
			}
			
			//String[] columnInfo  = new String[] {"dept_nm","event_nm","lawfirm_nm","crrncy_unit","claim_amt","invoice_no","srvcday","acptday","unpayday","remitplndday","remitday","remit_amt",""};//ExelVel.split(",");
			short[]  columnAlign = new short[] {ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT};
	        			
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
            // 두개의 Sheet를 생성한다.
			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수			

			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop				
				
				/*
				excel.setFontName	("맑은 고딕");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11); 
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}	
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_BLACK);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.				
				excel.setBgColor	(ExcelBuilder.COLOR_PALE_BLUE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true); 
				// Row의 모든 Cell의 테두리를 설정한다.
				excel.addRow(i, subTitleInfo);	
				excel.setBold		(false);  
				excel.setBgColor	(ExcelBuilder.COLOR_WHITE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.                                            		
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, resultList);                        		// 데이타 엑셀에 박기										
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
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
    		
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		} 
    }
	
	/**
	 * 검색 옵션을 체크한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public boolean check_form(LwsSearchForm form) throws Exception {
		boolean check_flag = true;
		String str_test = "";
		
		str_test += form.getSrch_event_no();
		str_test += form.getSrch_event_nm();
		str_test += form.getSrch_lawfirm_id();
		str_test += form.getSrch_kbn1();
		str_test += form.getSrch_kbn2();
		str_test += form.getSrch_lawsuit_trgt_cd();			
		str_test += form.getSrch_remit_yn();
		str_test += form.getSrch_unpay_yn();
		str_test += form.getSrch_review_yn();			
		str_test += form.getSrch_group_cd();			
		str_test += form.getSrch_acpt_start_dt();
		str_test += form.getSrch_acpt_end_dt();
		str_test += form.getSrch_unpay_start_dt();
		str_test += form.getSrch_unpay_end_dt();
		str_test += form.getSrch_remit_start_dt();
		str_test += form.getSrch_remit_end_dt();
		
		if(str_test.length() == 0){
			check_flag = false;
		}		
		return check_flag;
	}
	
	/**
	 * XSS 를 체크한다.
	 * @param LwsSearchForm
	 * @throws Exception
	 */
	public LwsSearchForm check_XSS_form(LwsSearchForm form) throws Exception {
		
		form.setSrch_event_no(StringUtil.bvl(form.getSrch_event_no(),""));
		form.setSrch_event_nm(StringUtil.bvl(form.getSrch_event_nm(),""));
		form.setSrch_lawfirm_id(StringUtil.bvl(form.getSrch_lawfirm_id(),""));
		form.setSrch_kbn1(StringUtil.bvl(form.getSrch_kbn1(),""));
		form.setSrch_kbn2(StringUtil.bvl(form.getSrch_kbn2(),""));
		form.setSrch_lawsuit_trgt_cd(StringUtil.bvl(form.getSrch_lawsuit_trgt_cd(),""));			
		form.setSrch_remit_yn(StringUtil.bvl(form.getSrch_remit_yn(),""));
		form.setSrch_unpay_yn(StringUtil.bvl(form.getSrch_unpay_yn(),""));
		form.setSrch_review_yn(StringUtil.bvl(form.getSrch_review_yn(),""));			
		form.setSrch_group_cd(StringUtil.bvl(form.getSrch_group_cd(),""));			
		form.setSrch_acpt_start_dt(StringUtil.bvl(form.getSrch_acpt_start_dt(),""));
		form.setSrch_acpt_end_dt(StringUtil.bvl(form.getSrch_acpt_end_dt(),""));
		form.setSrch_unpay_start_dt(StringUtil.bvl(form.getSrch_unpay_start_dt(),""));
		form.setSrch_unpay_end_dt(StringUtil.bvl(form.getSrch_unpay_end_dt(),""));
		form.setSrch_remit_start_dt(StringUtil.bvl(form.getSrch_remit_start_dt(),""));
		form.setSrch_remit_end_dt(StringUtil.bvl(form.getSrch_remit_end_dt(),""));

		return form;
	}
	
	/**
	 * XSS 를 체크한다.
	 * @param LwsSearchVO
	 * @throws Exception
	 */
	public LwsSearchVO check_XSS_vo(LwsSearchVO vo) throws Exception {
		
		vo.setSrch_event_no(StringUtil.bvl(vo.getSrch_event_no(),""));
		vo.setSrch_event_nm(StringUtil.bvl(vo.getSrch_event_nm(),""));
		vo.setSrch_lawfirm_id(StringUtil.bvl(vo.getSrch_lawfirm_id(),""));
		vo.setSrch_kbn1(StringUtil.bvl(vo.getSrch_kbn1(),""));
		vo.setSrch_kbn2(StringUtil.bvl(vo.getSrch_kbn2(),""));
		vo.setSrch_lawsuit_trgt_cd(StringUtil.bvl(vo.getSrch_lawsuit_trgt_cd(),""));			
		vo.setSrch_remit_yn(StringUtil.bvl(vo.getSrch_remit_yn(),""));
		vo.setSrch_unpay_yn(StringUtil.bvl(vo.getSrch_unpay_yn(),""));
		vo.setSrch_review_yn(StringUtil.bvl(vo.getSrch_review_yn(),""));			
		vo.setSrch_group_cd(StringUtil.bvl(vo.getSrch_group_cd(),""));			
		vo.setSrch_acpt_start_dt(StringUtil.bvl(vo.getSrch_acpt_start_dt(),""));
		vo.setSrch_acpt_end_dt(StringUtil.bvl(vo.getSrch_acpt_end_dt(),""));
		vo.setSrch_unpay_start_dt(StringUtil.bvl(vo.getSrch_unpay_start_dt(),""));
		vo.setSrch_unpay_end_dt(StringUtil.bvl(vo.getSrch_unpay_end_dt(),""));
		vo.setSrch_remit_start_dt(StringUtil.bvl(vo.getSrch_remit_start_dt(),""));
		vo.setSrch_remit_end_dt(StringUtil.bvl(vo.getSrch_remit_end_dt(),""));

		return vo;
	}
	

}