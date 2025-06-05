package com.sds.secframework.log.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.log.dto.LogForm;
import com.sds.secframework.log.dto.LogVO;
import com.sds.secframework.log.service.LogService;

public class LogController extends CommonController {
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	// 로그인 현황 영역 시작 ==========================================================================
	/**
	 * 로그인 현황 조회
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView listLoginLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			// 시스템 코드
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        LogForm form = new LogForm();
	        LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
	        
	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			List loginList       = null; // 목록리스트
	        String returnMessage = "";	 // 리턴메시지
			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
        	// 현재 페이지를 SET
            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
            
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setStart_index(pageUtil.getStartIndex()); // 페이지 시작
    		vo.setEnd_index(pageUtil.getEndIndex());     // 페이지 마지막
    		vo.setSys_cd(sysCd);
    		
    		loginList = logService.listLoginLog(vo);         
            
    		if (loginList != null && loginList.size() > 0) {
            	ListOrderedMap lm = (ListOrderedMap)loginList.get(0);
            	
                pageUtil.setTotalRow(lm.get("total_cnt"));
                pageUtil.setRowPerPage(10);
                pageUtil.setGroup();
                
                // 메시지처리 - 정상적으로 조회되었습니다.
                if ((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1) {
                	returnMessage = (String)request.getAttribute("returnMessage");
                } else {       
                	returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
                }
            } else {
            	// 메시지처리 - 조회된 결과가 없습니다.
                if ((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1) {
                	returnMessage = (String)request.getAttribute("returnMessage");
                } else {       
	            	returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
                }
            }
    		
    		form.setReturn_message(returnMessage) ;
    		form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/jsp/secfw/log/LoginLogList.jsp");
			
			mav.addObject("resultList", loginList);
			mav.addObject("command", form);
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
	 * 로그인 현황 엑셀다운로드
	 * 첫번째 탭에 회사별 로그인 현황, 사용자별 로그인 현황
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void excelDownLoadLoginLog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		// 시스템 코드
		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

		LogForm form = new LogForm();
        LogVO   vo   = new LogVO();
		
        bind(request, form);
		bind(request, vo);
		
		vo.setTable_nm("TB_COM_LOGIN_STAT") ;
		vo.setSys_cd(sysCd);
		
		String[] sheetArray = new String[]{"회사별 로그인 현황", "사용자별 로그인 현황"} ;
		String fileNm = "로그인 현황.xls" ;
		String[][] titleArray = null ;
		String[] columnArray = null ;
		List list = null ;
		Map columnInfo = null ;
		short[]  columnAlign = null ;
		
		ExcelBuilder excel = new ExcelBuilder(sheetArray);
        // 두개의 Sheet를 생성한다.
		int sheetsCount = excel.getSheetsCount();
		
		for(int i=0; i<sheetsCount; i++) {
			// 회사별 로그인 현황 현황
			if(i==0) {
				columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER};
				titleArray = new String[][]{new String[]{"회사명", "당일:" + DateUtil.getTime("yyyy-MM-dd"), ExcelBuilder.MERGE_LEFT, "누적:"+form.getStart_datetime() + "~" + form.getEnd_datetime() , ExcelBuilder.MERGE_LEFT},
											new String[]{ExcelBuilder.MERGE_UP, "접속자수", "접속횟수", "접속자수", "접속횟수"}} ;
				columnArray = new String[]{"comp_nm", "today_user_cnt", "today_login_cnt", "srch_user_cnt", "srch_login_cnt"} ;
				//list = logService.listLoginLogDept(vo) ;
				list = logService.listLoginLogComp(vo) ;
			}
			// 사용자별 로그인 현황
			else {
				columnAlign = new short[] {ExcelBuilder.ALIGN_CENTER, // 시스템명
											ExcelBuilder.ALIGN_CENTER, // 사용자아이디
											ExcelBuilder.ALIGN_CENTER, // 사용자명
											ExcelBuilder.ALIGN_LEFT,   // 부서명
											ExcelBuilder.ALIGN_CENTER, // 접속년
											ExcelBuilder.ALIGN_CENTER, // 접속월
											ExcelBuilder.ALIGN_CENTER, // 접속일
											ExcelBuilder.ALIGN_CENTER, // 접속시간
											ExcelBuilder.ALIGN_CENTER, // 접속일시
											ExcelBuilder.ALIGN_CENTER, // 로그아웃일시
											ExcelBuilder.ALIGN_CENTER, // 아피주소
											ExcelBuilder.ALIGN_LEFT,   // 브라우저 타입
											ExcelBuilder.ALIGN_LEFT,   // 세션 아이디
											ExcelBuilder.ALIGN_CENTER, // 사번
											ExcelBuilder.ALIGN_LEFT,   // 영문명
											ExcelBuilder.ALIGN_LEFT,   // 싱글아이디
											ExcelBuilder.ALIGN_CENTER, // 싱글EP아이디
											ExcelBuilder.ALIGN_CENTER, // 회사코드
											ExcelBuilder.ALIGN_LEFT,   // 회사명
											ExcelBuilder.ALIGN_LEFT,   // 회사영문명
											ExcelBuilder.ALIGN_CENTER, // 부문코드
											ExcelBuilder.ALIGN_LEFT,   // 부문명
											ExcelBuilder.ALIGN_LEFT,   // 부문영문명
											ExcelBuilder.ALIGN_CENTER, // 사업부코드
											ExcelBuilder.ALIGN_LEFT,   // 사업부명
											ExcelBuilder.ALIGN_LEFT,   // 부서코드
											ExcelBuilder.ALIGN_LEFT,   // 내부부서코드(AA)
											ExcelBuilder.ALIGN_CENTER, // 직급코드
											ExcelBuilder.ALIGN_LEFT,   // 직급명
											ExcelBuilder.ALIGN_LEFT,   // 직급영문명
											ExcelBuilder.ALIGN_CENTER, // 직무코드
											ExcelBuilder.ALIGN_LEFT,   // 직무명
											ExcelBuilder.ALIGN_CENTER, // 지역코드
											ExcelBuilder.ALIGN_LEFT,   // 지역명
											ExcelBuilder.ALIGN_LEFT,   // 지역영문명
											ExcelBuilder.ALIGN_LEFT,   // 이메일
											ExcelBuilder.ALIGN_LEFT,   // 회사전화번호
											ExcelBuilder.ALIGN_LEFT,   // 집전화번호
											ExcelBuilder.ALIGN_LEFT,   // 핸드폰번호(M)
											ExcelBuilder.ALIGN_CENTER, // 재직상태
											ExcelBuilder.ALIGN_CENTER  // 사용자구분
										  };
				list = logService.listLoginLogNoPage(vo);
				columnInfo = getColumnInfo(vo, list);
				titleArray = new String[][]{(String[])columnInfo.get("comment")};
				columnArray = (String[])columnInfo.get("column");
			}
			
			// 제목 세팅
			setExcelTitle(excel, i, titleArray);
			
			// 내용 세팅
			excel.setFontName("굴림체");                  // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
			excel.setAlign(columnAlign);                 // 셀 정렬
			excel.setVAlign(ExcelBuilder.VALIGN_CENTER); // Row의 모든 Cell을 Vertical Center 정렬한다.
			excel.setBorder(new boolean[] {true});       // Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)
			excel.addRows(i, columnArray, list);         // 데이타 엑셀에 박기
			
			// 회사별 현황일 경우 총계 세팅
			if (i==0) {
				setExcelTotal(excel, i, columnArray, list, "business_nm") ; // 데이타 엑셀에 박기
			}
			
			excel.setDefaultStyle(); // Row의 모든 Style을 기본값으로 복원한다.
		}
		
		excel.download(fileNm, response);
	}
	// 로그인 현황 영역 종료 ==========================================================================
	
	// 시스템사용 현황 영역 시작 ======================================================================
	/**
	 * 시스템사용 현황 조회
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView listMenuUseLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			// 시스템 코드
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			//String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LogForm form = new LogForm();
			LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			List menuList        = null; // 목록리스트
			String returnMessage = "";   // 리턴메시지
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			// 현재페이지를 set
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setStart_index(pageUtil.getStartIndex()); // 페이지시작
			vo.setEnd_index(pageUtil.getEndIndex());     // 페이지마지막
			vo.setSys_cd(sysCd);
			//vo.setAuth_comp_cd(auth_comp_cd);
			
			menuList = logService.listMenuUseLog(vo);         
			
			if (menuList != null && menuList.size() > 0) {
				ListOrderedMap lm = (ListOrderedMap)menuList.get(0);
				
				pageUtil.setTotalRow(lm.get("total_cnt"));
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				
				// 메시지처리 - 정상적으로 조회되었습니다.
				if ((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {       
					returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {       
					returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
			}
			
			form.setReturn_message(returnMessage);
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/jsp/secfw/log/MenuUseLogList.jsp");
			
			mav.addObject("resultList", menuList);
			mav.addObject("command", form);
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
	 * 시스템사용 현황 엑셀다운로드
	 * 첫번째 탭에 메뉴별 접속 현황, 사용자별 메뉴 접속 현황
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void excelDownLoadMenuLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		// 시스템 코드
		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		//String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
		
		LogForm form = new LogForm();
        LogVO   vo   = new LogVO();
		
        bind(request, form);
		bind(request, vo);
		
		vo.setTable_nm("TB_COM_MENU_STAT") ;
		vo.setSys_cd(sysCd);
		//vo.setAuth_comp_cd(auth_comp_cd);
		
		String[] sheetArray = new String[]{"메뉴별 접속 현황", "사용자별 메뉴 접속 현황"} ;
		String fileNm = "메뉴별 접속 현황.xls" ;
		String[][] titleArray = null ;
		String[] columnArray = null ;
		List list = null ;
		Map columnInfo = null ;
		short[]  columnAlign = null ;
		
		ExcelBuilder excel = new ExcelBuilder(sheetArray);
        // 두개의 Sheet를 생성한다.
		int sheetsCount = excel.getSheetsCount();
		
		for(int i=0; i<sheetsCount; i++) {
			// 메뉴별 접속 현황
			if(i==0) {
				columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER,
											ExcelBuilder.ALIGN_CENTER};
				titleArray = new String[][]{new String[]{"메뉴명", "당일:" + DateUtil.getTime("yyyy-MM-dd"), ExcelBuilder.MERGE_LEFT, "누적:"+form.getStart_datetime() + "~" + form.getEnd_datetime() , ExcelBuilder.MERGE_LEFT},
											new String[]{ExcelBuilder.MERGE_UP, "접속자수", "접속횟수", "접속자수", "접속횟수"}} ;
				columnArray = new String[]{"menu_nm", "today_user_cnt", "today_contact_cnt", "srch_user_cnt", "srch_contact_cnt"} ;
				list = logService.listMenuLogContact(vo) ;
			}
			// 사용자별 메뉴 접속 현황
			else {
				columnAlign = new short[] {ExcelBuilder.ALIGN_CENTER, // 시스템명
											ExcelBuilder.ALIGN_CENTER, // 메뉴아이디
											ExcelBuilder.ALIGN_LEFT,   // 메뉴명
											ExcelBuilder.ALIGN_LEFT,   // 메뉴영문명
											ExcelBuilder.ALIGN_CENTER, // 상위 메뉴아이디
											ExcelBuilder.ALIGN_CENTER, // 메뉴레벨
											ExcelBuilder.ALIGN_CENTER, // 메뉴순서
											ExcelBuilder.ALIGN_LEFT,   // 메뉴설명(H)
											ExcelBuilder.ALIGN_LEFT,   // 메뉴URL
											ExcelBuilder.ALIGN_CENTER, // 메뉴타입
											ExcelBuilder.ALIGN_CENTER, // 개발자성명
											ExcelBuilder.ALIGN_CENTER, // 사용자ID
											ExcelBuilder.ALIGN_CENTER, // 사용자명
											ExcelBuilder.ALIGN_LEFT,   // 부서명
											ExcelBuilder.ALIGN_CENTER, // 접속년
											ExcelBuilder.ALIGN_CENTER, // 접속월
											ExcelBuilder.ALIGN_CENTER, // 접속년
											ExcelBuilder.ALIGN_CENTER, // 접속시간(R)
											ExcelBuilder.ALIGN_CENTER, // 사번
											ExcelBuilder.ALIGN_LEFT,   // 영문성명
											ExcelBuilder.ALIGN_LEFT,   // 싱글아이디
											ExcelBuilder.ALIGN_CENTER, // 싱글EP아이디
											ExcelBuilder.ALIGN_CENTER, // 회사코드
											ExcelBuilder.ALIGN_LEFT,   // 회사명
											ExcelBuilder.ALIGN_LEFT,   // 회사영문명
											ExcelBuilder.ALIGN_CENTER, // 부문코드(Z)
											ExcelBuilder.ALIGN_LEFT,   // 부문명
											ExcelBuilder.ALIGN_LEFT,   // 부문영문명
											ExcelBuilder.ALIGN_CENTER, // 사업부코드
											ExcelBuilder.ALIGN_LEFT,   // 사업부명
											ExcelBuilder.ALIGN_LEFT,   // 부서코드
											ExcelBuilder.ALIGN_LEFT,   // 내부부서코드
											ExcelBuilder.ALIGN_CENTER, // 직급코드
											ExcelBuilder.ALIGN_LEFT,   // 직급명
											ExcelBuilder.ALIGN_LEFT,   // 직급영문명
											ExcelBuilder.ALIGN_CENTER, // 직무코드
											ExcelBuilder.ALIGN_LEFT,   // 직무명
											ExcelBuilder.ALIGN_CENTER, // 지역코드
											ExcelBuilder.ALIGN_LEFT,   // 지역명
											ExcelBuilder.ALIGN_LEFT,   // 지역영문명
											ExcelBuilder.ALIGN_LEFT,   // 이메일(O)
											ExcelBuilder.ALIGN_LEFT,   // 회사전화번호
											ExcelBuilder.ALIGN_LEFT,   // 집전화번호
											ExcelBuilder.ALIGN_LEFT,   // 핸드폰번호(M)
											ExcelBuilder.ALIGN_CENTER, // 재직상태
											ExcelBuilder.ALIGN_CENTER,  // 사용자구분
											ExcelBuilder.ALIGN_CENTER  // 접속시간
										  };
				list = logService.listMenuLogNoPage(vo) ;
				columnInfo = getColumnInfo(vo, list) ;
				titleArray = new String[][]{(String[])columnInfo.get("comment")} ;
				columnArray = (String[])columnInfo.get("column") ;
			}
			
			// 제목 세팅
			setExcelTitle(excel, i, titleArray) ;
			
			// 내용 세팅
			excel.setFontName("굴림체");                  // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
			excel.setAlign(columnAlign);                 // 셀 정렬
			excel.setVAlign(ExcelBuilder.VALIGN_CENTER); // Row의 모든 Cell을 Vertical Center 정렬한다.
			excel.setBorder(new boolean[] {true});       // Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)
			excel.addRows(i, columnArray, list);         // 데이타 엑셀에 박기
			
			// 메뉴별 접속 현황일 경우 총계 세팅
			if (i==0) {
				setExcelTotal(excel, i, columnArray, list, "menu_nm") ; // 데이타 엑셀에 박기
			}
			
			excel.setDefaultStyle(); // Row의 모든 Style을 기본값으로 복원한다.
		}
		
		excel.download(fileNm, response);
	}
	// 시스템사용 현황 영역 종료 ======================================================================
	
	// 파일다운로드 현황 영역 시작 ====================================================================
	/**
	 * 파일다운로드 현황 조회
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView listFileLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			// 시스템 코드
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			//String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			LogForm form = new LogForm();
			LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			 ************************************* *********************/
			PageUtil pageUtil = new PageUtil();
			
			/*********************************************************
			 * JSP 반환변수
			 **********************************************************/
			List fileList        = null; // 목록리스트
			String returnMessage = "";   // 리턴메시지
			
			/*********************************************************
			 * 검색처리
			 **********************************************************/
			// 현재페이지를 set
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setStart_index(pageUtil.getStartIndex()); // 페이지시작
			vo.setEnd_index(pageUtil.getEndIndex());     // 페이지마지막
			vo.setSys_cd(sysCd);
			//vo.setAuth_comp_cd(auth_comp_cd);
			
			fileList = logService.listFileLog(vo);         
			
			if (fileList != null && fileList.size() > 0) {
				ListOrderedMap lm = (ListOrderedMap)fileList.get(0);
				
				pageUtil.setTotalRow(lm.get("total_cnt"));
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				
				// 메시지처리 - 정상적으로 조회되었습니다.
				if ((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {       
					returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			}else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if ((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {       
					returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
			}
			
			form.setReturn_message(returnMessage);
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/jsp/secfw/log/FileLogList.jsp");
			
			mav.addObject("resultList", fileList);
			mav.addObject("command", form);
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
	 * 파일다운로드 현황 엑셀다운로드
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void excelDownLoadFileLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		// 시스템 코드
		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		//String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
		
		LogForm form = new LogForm();
        LogVO   vo   = new LogVO();
		
        bind(request, form);
		bind(request, vo);
		
		vo.setTable_nm("TB_COM_FILE_STAT");
		vo.setSys_cd(sysCd);
		//vo.setAuth_comp_cd(auth_comp_cd);
		
		String[] sheetArray = new String[]{"사용자별 파일 다운로드 현황"};
		String fileNm = "파일 다운로드 현황.xls";
		String[][] titleArray = null;
		String[] columnArray  = null;
		List list           = null;
		Map columnInfo      = null;
		short[] columnAlign = null;
		
		ExcelBuilder excel = new ExcelBuilder(sheetArray);
        // 한개의 Sheet를 생성한다.
		int sheetsCount = excel.getSheetsCount();
		
		for(int i=0; i<sheetsCount; i++) {
			columnAlign = new short[] {ExcelBuilder.ALIGN_CENTER, // 시스템명
										ExcelBuilder.ALIGN_LEFT,   // 파일경로(경로+서버저장파일명)
										ExcelBuilder.ALIGN_LEFT,   // 파일명(사용자파일명)
										ExcelBuilder.ALIGN_CENTER, // 사용자아이디
										ExcelBuilder.ALIGN_CENTER, // 사용자명
										ExcelBuilder.ALIGN_LEFT,   // 부서명
										ExcelBuilder.ALIGN_CENTER, // 접속년
										ExcelBuilder.ALIGN_CENTER, // 접속월
										ExcelBuilder.ALIGN_CENTER, // 접속년
										ExcelBuilder.ALIGN_CENTER, // 접속시간(R)
										ExcelBuilder.ALIGN_CENTER, // 사번
										ExcelBuilder.ALIGN_LEFT,   // 영문성명
										ExcelBuilder.ALIGN_LEFT,   // 싱글아이디
										ExcelBuilder.ALIGN_CENTER, // 싱글EP아이디
										ExcelBuilder.ALIGN_CENTER, // 회사코드
										ExcelBuilder.ALIGN_LEFT,   // 회사명
										ExcelBuilder.ALIGN_LEFT,   // 회사영문명
										ExcelBuilder.ALIGN_CENTER, // 부문코드(Z)
										ExcelBuilder.ALIGN_LEFT,   // 부문명
										ExcelBuilder.ALIGN_LEFT,   // 부문영문명
										ExcelBuilder.ALIGN_CENTER, // 사업부코드
										ExcelBuilder.ALIGN_LEFT,   // 사업부명
										ExcelBuilder.ALIGN_LEFT,   // 부서코드
										ExcelBuilder.ALIGN_LEFT,   // 내부부서코드
										ExcelBuilder.ALIGN_CENTER, // 직급코드
										ExcelBuilder.ALIGN_LEFT,   // 직급명
										ExcelBuilder.ALIGN_LEFT,   // 직급영문명
										ExcelBuilder.ALIGN_CENTER, // 직무코드
										ExcelBuilder.ALIGN_LEFT,   // 직무명
										ExcelBuilder.ALIGN_CENTER, // 지역코드
										ExcelBuilder.ALIGN_LEFT,   // 지역명
										ExcelBuilder.ALIGN_LEFT,   // 지역영문명
										ExcelBuilder.ALIGN_LEFT,   // 이메일(O)
										ExcelBuilder.ALIGN_LEFT,   // 회사전화번호
										ExcelBuilder.ALIGN_LEFT,   // 집전화번호
										ExcelBuilder.ALIGN_LEFT,   // 핸드폰번호(M)
										ExcelBuilder.ALIGN_CENTER, // 재직상태
										ExcelBuilder.ALIGN_CENTER, // 사용자구분
										ExcelBuilder.ALIGN_CENTER  // 접속시간
									  };
			list = logService.listFileLogNoPage(vo);
			columnInfo = getColumnInfo(vo, list);
			titleArray = new String[][]{(String[])columnInfo.get("comment")};
			columnArray = (String[])columnInfo.get("column");
			
			// 제목 세팅
			setExcelTitle(excel, i, titleArray);
			
			// 내용 세팅
			excel.setFontName("굴림체");                  // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
			excel.setAlign(columnAlign);                 // 셀 정렬
			excel.setVAlign(ExcelBuilder.VALIGN_CENTER); // Row의 모든 Cell을 Vertical Center 정렬한다.
			excel.setBorder(new boolean[] {true});       // Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)
			excel.addRows(i, columnArray, list);         // 데이타 엑셀에 박기
			
			excel.setDefaultStyle(); // Row의 모든 Style을 기본값으로 복원한다.
		}
		
		excel.download(fileNm, response);
	}
	// 파일다운로드 현황 영역 종료 ====================================================================
	
	/**
	 * 파일다운로드 입력.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	 */	
	public void insertFileDownLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd        = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	        String userId       = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),"");
			String userNm       = StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"),"");
			String deptNm       = StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"),"");
			String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			LogVO   vo   = new LogVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setUser_id(userId);
    		vo.setUser_nm(userNm);
    		vo.setDept_nm(deptNm);
    		vo.setAuth_comp_cd(auth_comp_cd);
			
    		logService.insertFileDownLog(vo);		

    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", "SUCC");
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	/**
	 * [CHART] 로그인 현황
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listLoginLogDayChart(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        LogVO vo = new LogVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setAuth_comp_cd(auth_comp_cd);
    		
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONObject jsonObject = logService.listLoginLogDayChart(vo);		
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonObject);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
    }

	/**
	 * [Excel 다운로드] 로그인 현황
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void excelDownLoginLog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        LogForm form = new LogForm();
	        LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			vo.setSys_cd(sysCd);
			vo.setAuth_comp_cd(auth_comp_cd);
			/*********************************************************
			 * 목록조회
			**********************************************************/
	        List loginList = logService.excelDownLoginLog(vo);
	        
	        if (!"".equals(StringUtil.bvl(vo.getStart_date(), ""))) {
	        	vo.setStart_date(vo.getStart_date().replaceAll("-", ""));
	        }
	        if (!"".equals(StringUtil.bvl(vo.getEnd_date(), ""))) {
	        	vo.setEnd_date(vo.getEnd_date().replaceAll("-", ""));
	        }
	        
	        //부서별 일단 막음
	        /* 시작 
	        List loginRegionList = logService.excelDownLoginDeptLog(vo);
	        
	        int totalD7 = 0;
	        int totalD6 = 0;
	        int totalD5 = 0;
	        int totalD4 = 0;
	        int totalD3 = 0;
	        int totalD2 = 0;
	        int totalD1 = 0;
	        int totalD0 = 0;
	        int totalRegion = 0;
	        
	        if (loginRegionList != null && loginRegionList.size() > 0) {
	        	for (int i = 0; i < loginRegionList.size(); i++) {
	        		ListOrderedMap lom = (ListOrderedMap)loginRegionList.get(i);
	        		
	        		totalD7 += (FormatUtil.formatInt(lom.get("d7")));
	        		totalD6 += (FormatUtil.formatInt(lom.get("d6")));
	        		totalD5 += (FormatUtil.formatInt(lom.get("d5")));
	        		totalD4 += (FormatUtil.formatInt(lom.get("d4")));
	        		totalD3 += (FormatUtil.formatInt(lom.get("d3")));
	        		totalD2 += (FormatUtil.formatInt(lom.get("d2")));
	        		totalD1 += (FormatUtil.formatInt(lom.get("d1")));
	        		totalD0 += (FormatUtil.formatInt(lom.get("d0")));
	        		totalRegion += (FormatUtil.formatInt(lom.get("region_tot")));
	        	}
	        }
	        */

			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
	        String ExelNm  = request.getParameter("exel_nm");
	        String ExelVel = request.getParameter("exel_vel");
			String fileNm  = "로그인현황_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			String[] sheetNmAry = new String[] {"로그인현황"};
			String[] titleInfo   = ExelNm.split(",");
			String[] columnInfo  = ExelVel.split(",");
			short[]  columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT};
	        			
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
            // 두개의 Sheet를 생성한다.
			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수
			

			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_WHITE);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.
				excel.setFontName	("굴림체");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11);                                        	// Row의 모든 Cell의 Font크기를 11pt로 설정한다.
				excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_BLUE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true);                                               	// Row의 모든 Cell의 테두리를 설정한다.
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}				
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, loginList);                         		// 데이타 엑셀에 박기
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				excel.addRow(i, new String[] {"", "끝"});                            		// 마지막 Row에 Row 추가. 
			}			
			excel.download(fileNm, response);                           
    		
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		} 
    }
	
	/**
	 * [Excel 다운로드] 파일다운로드 현황
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void excelDownFileDownLoadLog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        LogForm form = new LogForm();
	        LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			vo.setSys_cd(sysCd);
			vo.setAuth_comp_cd(auth_comp_cd);
			/*********************************************************
			 * 목록조회
			**********************************************************/
	        List loginList = logService.excelDownFileDownLoadLog(vo);
	        
	        if (!"".equals(StringUtil.bvl(vo.getStart_date(), ""))) {
	        	vo.setStart_date(vo.getStart_date().replaceAll("-", ""));
	        }
	        if (!"".equals(StringUtil.bvl(vo.getEnd_date(), ""))) {
	        	vo.setEnd_date(vo.getEnd_date().replaceAll("-", ""));
	        }

			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
	        String ExelNm  = request.getParameter("exel_nm");
	        String ExelVel = request.getParameter("exel_vel");
			String fileNm  = "파일다운로드현황_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			String[] sheetNmAry = new String[] {"파일다운로드현황"};
			String[] titleInfo   = ExelNm.split(",");
			String[] columnInfo  = ExelVel.split(",");
			short[]  columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT};
			
			//ExcelBuilder excel = new ExcelBuilder(sheetNm);                          // Sheet명 설정
			ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
            // 두개의 Sheet를 생성한다.
			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수
			
			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_WHITE);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.
				excel.setFontName	("굴림체");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11);                                        	// Row의 모든 Cell의 Font크기를 11pt로 설정한다.
				excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_BLUE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true);                                               	// Row의 모든 Cell의 테두리를 설정한다.
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}				
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, loginList);                         		// 데이타 엑셀에 박기
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				excel.addRow(i, new String[] {"", "끝"});                            		// 마지막 Row에 Row 추가. 
			}			
			excel.download(fileNm, response);  
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		} 			
    }
	
	
	/**
	 * [Excel 다운로드] 파일다운로드 현황
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void excelDownFileMenuUseLog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        LogForm form = new LogForm();
	        LogVO   vo   = new LogVO();
			bind(request, form);
			bind(request, vo);
			
			vo.setSys_cd(sysCd);
			vo.setAuth_comp_cd(auth_comp_cd);
			/*********************************************************
			 * 목록조회
			**********************************************************/
	        List loginList = logService.excelDownMenuUseLog(vo);
	        
	        if (!"".equals(StringUtil.bvl(vo.getStart_date(), ""))) {
	        	vo.setStart_date(vo.getStart_date().replaceAll("-", ""));
	        }
	        if (!"".equals(StringUtil.bvl(vo.getEnd_date(), ""))) {
	        	vo.setEnd_date(vo.getEnd_date().replaceAll("-", ""));
	        }

			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
	        String ExelNm  = request.getParameter("exel_nm");
	        String ExelVel = request.getParameter("exel_vel");
			String fileNm  = "메뉴사용현황_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			String[] sheetNmAry = new String[] {"메뉴사용현황"};
			String[] titleInfo   = ExelNm.split(",");
			String[] columnInfo  = ExelVel.split(",");
			short[]  columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT};
			
			//ExcelBuilder excel = new ExcelBuilder(sheetNm);                          // Sheet명 설정
			ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
            // 두개의 Sheet를 생성한다.
			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수
			
			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_WHITE);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.
				excel.setFontName	("굴림체");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11);                                        	// Row의 모든 Cell의 Font크기를 11pt로 설정한다.
				excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_BLUE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true);                                               	// Row의 모든 Cell의 테두리를 설정한다.
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}				
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, loginList);                         		// 데이타 엑셀에 박기
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
			}			
			excel.download(fileNm, response);  
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		} 			
    }
	
	/**
	 * 로그를 엑셀로 다운 로드 한다.
	 * 해당 로그의 전 항목을 다운로드 한다.
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	public void excelDownLoadLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		//시스템코드
		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		String auth_comp_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), "");
		
		LogForm form = new LogForm();
        LogVO   vo   = new LogVO();
		
        bind(request, form);
		bind(request, vo);
		
		vo.setSys_cd(sysCd);
		vo.setAuth_comp_cd(auth_comp_cd);
		
		String sheetNm = null ;
		String fileNm  = null ;
		List list = null ;
		
		if(form.getExcel_gbn().equals("LOGIN")) {
			vo.setTable_nm("TB_COM_LOGIN_STAT") ;
			sheetNm = "로그인현황" ;
			fileNm = sheetNm + "_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			list = logService.listLoginLogNoPage(vo) ;
		} else if(form.getExcel_gbn().equals("FILE")) {
			vo.setTable_nm("TB_COM_FILE_STAT") ;
			sheetNm = "파일다운로드현황" ;
			fileNm = sheetNm + "_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			list = logService.listFileLogNoPage(vo) ;
		} else if(form.getExcel_gbn().equals("MENU")) {
			vo.setTable_nm("TB_COM_MENU_STAT") ;
			sheetNm = "시스템사용현황" ;
			fileNm = sheetNm + "_" + vo.getStart_date() + "_" + vo.getEnd_date() + ".xls";
			list = logService.listMenuLogNoPage(vo) ;
		} 
		
		// 컬럼 리스트
		Map map = getColumnInfo(vo, list) ;
		String[] titleInfo = (String[])map.get("comment") ;
		String[] columnInfo = (String[])map.get("column") ;
		short[] columnAlign = new short[] {ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT};
		
		ExcelBuilder excel = new ExcelBuilder(sheetNm) ;
		
		excel.setAlign		(ExcelBuilder.ALIGN_RIGHT);        // Row의 모든 Cell을 Center 정렬한다.
		excel.setBold		(true);                            // Row의 모든 Cell을 Bold로 설정한다.
		excel.setFontColor	(ExcelBuilder.COLOR_WHITE);         // Row의 모든 Cell의 글자색을 White로 설정한다.
		excel.setFontName	("굴림체");                         // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
		excel.setFontSize	((short)11);                       // Row의 모든 Cell의 Font크기를 11pt로 설정한다.
		excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_BLUE);    // Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
		excel.setBorder		(true);                            // Row의 모든 Cell의 테두리를 설정한다.
		excel.addTitleRow(0, titleInfo);                        // 타이틀을 설정한다.
		excel.setDefaultStyle();                                // Row의 모든 Style을 기본값으로 복원한다.
		excel.setAlign(columnAlign);                            // 셀 정렬
		excel.setVAlign(ExcelBuilder.VALIGN_CENTER);            // Row의 모든 Cell을 Vertical Center 정렬한다.
		excel.setBorder(new boolean[] {true});                // Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
		excel.addRows(0, columnInfo, list);                     // 데이타 엑셀에 박기
		excel.setDefaultStyle();                                // Row의 모든 Style을 기본값으로 복원한다.
		excel.download(fileNm, response);
	}
	
	/**
	 * DB 컬럼명과 comment를 map으로 리턴
	 * list에 없거나 DB 컬럼이 없을 경우 세팅하지 않는다.
	 * 
	 * @param vo LogVO
	 * @param list
	 * @return column : 컬럼명, comments : 컬럼 코멘트
	 * @throws Exception
	 */
	private Map getColumnInfo(LogVO vo, List list) throws Exception {
		List tableColumnList = logService.listColumnInfo(vo) ;
		List columnList = new ArrayList() ; 
		List commentList = new ArrayList() ; 
		Map map = new HashMap() ;
		
		if(list!=null && list.size()>0) {
			Map dataMap = (Map)list.get(0) ;
			
			for(int i=0; i<tableColumnList.size(); i++) {
				Map columnMap = (Map)tableColumnList.get(i) ;
				if(dataMap.containsKey((String)columnMap.get("column_name"))) {
					columnList.add((String)columnMap.get("column_name")) ;
					commentList.add((String)columnMap.get("comments")) ;
				}
			}
		}
		
		
		map.put("column", columnList.toArray(new String[columnList.size()])) ;
		map.put("comment", commentList.toArray(new String[commentList.size()])) ;
		
		return map ;
	}
	
	/**
	 * 제목을 세팅한다.
	 * @param excel ExcelBuilder
	 * @param sheetIndex 시트 index
	 * @param titleArray 타이틀 배열
	 */
	private void setExcelTitle(ExcelBuilder excel, int sheetIndex, String[][] titleArray) {
		excel.setColumnWidth(new double[]{0, 22, 22, 22, 22}) ;
		excel.setVAlign     (ExcelBuilder.VALIGN_CENTER);
		excel.setAlign		(ExcelBuilder.ALIGN_CENTER);      // Row의 모든 Cell을 Center 정렬한다.
		excel.setBold		(true);                          // Row의 모든 Cell을 Bold로 설정한다.
		excel.setFontName	("굴림체");                       // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
		excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_GREEN);  // Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
		excel.setBorder		(true);                          // Row의 모든 Cell의 테두리를 설정한다.
				
		for(int i=0; i<titleArray.length; i++) {
			excel.addRow(sheetIndex, titleArray[i]);     // 타이틀을 설정한다.
		}
		
		excel.setDefaultStyle();                             // Row의 모든 Style을 기본값으로 복원한다.
	}
	
	
	/**
	 * 엑셀에 총계를 세팅한다.
	 * @param excel ExcelBuilder
	 * @param sheetIndex 시트 index
	 * @param columnArray 컬럼 Array
	 * @param list 데이터 List
	 * @param hapTitle 합계 타이틀이 들어갈 컬럼명
	 */
	private void setExcelTotal(ExcelBuilder excel, int sheetIndex, String[] columnArray, List list, String hapTitle) {
		long[] totalArray = new long[columnArray.length] ;
		List totalList = new ArrayList() ;
		ListOrderedMap totalMap = new ListOrderedMap() ;
		
		for(int i=0; i<list.size(); i++) {
			Map map = (Map)list.get(i) ;
			
			// 부서명이나 메뉴명 제외해야 하므로 1부터 시작
			for(int j=1; j<columnArray.length; j++) {
				Object columnField = map.get(columnArray[j]) ;
				if(columnField!=null) {
					totalArray[j] +=  StringUtil.str2int(StringUtil.bvl(columnField.equals("-")?"0":map.get(columnArray[j]), "0")) ;
				}
				
				if(i==list.size()-1) {
					totalMap.put(columnArray[j], new BigDecimal(totalArray[j])) ;
				}
			}
		}
		
		totalMap.put(hapTitle, "합계") ;
		totalList.add(totalMap) ;
		
		excel.setAlign		(ExcelBuilder.ALIGN_CENTER);      // Row의 모든 Cell을 Center 정렬한다.
		excel.setBold		(true);                          // Row의 모든 Cell을 Bold로 설정한다.
		excel.setFontName	("굴림체");                       // Row의 모든 Cell의 Font명을 굴림체로 설정한다.
		excel.setBgColor	(ExcelBuilder.COLOR_LIGHT_GREEN);  // Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
		excel.setBorder		(true);                          // Row의 모든 Cell의 테두리를 설정한다.의 모든 Cell의 테두리를 설정한다.
		excel.addRows(sheetIndex, columnArray, totalList) ;
		
	}
	
	/**
	 * 엑셀 다운을 위한 인자값 선택 팝업 호출
	 *
	 * @param  
	 * @return 
	 * @throws 
	*/	
	public ModelAndView ExcelPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogForm form = new LogForm();
		LogVO   vo   = new LogVO();
		bind(request, form);
		bind(request, vo);
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/log/Excelpop.jsp";
		//-----------BIND:파라미터 설정 -------------------- 				
		String Excel_nm  =StringUtil.bvl( new String(request.getParameter("exel_nm_pop")),"");
		String Excel_vel =StringUtil.bvl( new String(request.getParameter("exel_vel_pop")),""); 
		
		String[] ExcelNams = Excel_nm .split(new String(","));
		String[] ExcelVels = Excel_vel.split(new String(","));
		
		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		mav.addObject("ExcelNams", ExcelNams);
		mav.addObject("ExcelVels", ExcelVels);
		return mav;
	}	
}
