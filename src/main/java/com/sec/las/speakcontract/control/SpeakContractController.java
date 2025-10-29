/**
 * Project Name : 법무시스템 이식
 * File Name : SpeakContractController.java
 * Description : 구두계약 컨트롤러
 * Author : 김현구
 * Date : 2011. 08. 05
 * Copyright : 
 */

package com.sec.las.speakcontract.control;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.las.speakcontract.dto.SpeakContractForm;
import com.sec.las.speakcontract.dto.SpeakContractVO;
import com.sec.las.speakcontract.service.SpeakContractService;


/**
* Description : 구두계약 컨트롤러 클래스
* Author : 김현구
* Date : 2011.08.17
*/
public class SpeakContractController extends CommonController {

	/**
	 * 구두계약 서비스
	 */
	private SpeakContractService speakContractService;

	/**
	 * 구두계약 서비스 세팅
	 * 
	 * @param speakContractService
	 */
	public void setSpeakContractService(
			SpeakContractService speakContractService) {
		this.speakContractService = speakContractService;
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
	public void setClassMethodAuthService(
			ClassMethodAuthService classMethodAuthService) {
		this.classMethodAuthService = classMethodAuthService;
	}

//	/**
//	 * ComUtil 서비스
//	 */
//	private ComUtilService comUtilService;
//
//	/**
//	 * ComUtil 서비스 세팅
//	 * 
//	 * @param comUtilService
//	 */
//	public void setComUtilService(ComUtilService comUtilService) {
//		this.comUtilService = comUtilService;
//	}

	/**
	 * 리스트 조회
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listSpeakContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpeakContractForm form = new SpeakContractForm();
		SpeakContractVO vo = new SpeakContractVO();
		// 데이터 바인딩
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		return listSpeakContract(request, vo, form);
	}

	/**
	 * 리스트 조회
	 * 
	 * @param request
	 * @param vo
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listSpeakContract(HttpServletRequest request,
			SpeakContractVO vo, SpeakContractForm form) throws Exception {
			String forwardURL = "/WEB-INF/jsp/las/speakcontract/SpeakContract_l.jsp";
			PageUtil pageUtil = new PageUtil();
			ModelAndView mav = new ModelAndView();
			String returnMessage = "";
		try {
			//페이지 정보 세팅
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(
					form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());
			
			// 리스트 조회 서비스 호출
			List resultList = speakContractService.listSpeakContract(vo);
			form.setSpeakcontract_list(resultList);
			// 리스트가 존재 하면
			if (resultList != null && resultList.size() > 0) {

				// 페이지 처리
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList,
						"total_cnt"));
				pageUtil.setGroup();
			}

			//메시지 세팅
			if ((String) request.getAttribute("returnMessage") != null
					&& ((String) request.getAttribute("returnMessage"))
							.length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}
			
			//검색어 XXS 처리
			form.setSrch_start_ymd(StringUtil.convertHtmlTochars(form.getSrch_start_ymd()));
			form.setSrch_end_ymd(StringUtil.convertHtmlTochars(form.getSrch_end_ymd()));
			form.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm()));
			form.setSrch_title(StringUtil.convertHtmlTochars(form.getSrch_title()));
			
			
			//여기서 등록권한 세팅
			form.setAuth_insert(ClmsBoardUtil.getClassMethodAuth(request, classMethodAuthService, this.getClass().getName(), "insertSpeakContract")) ;

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
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
	 * 등록화면으로 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardSpeakContractInsert(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/las/speakcontract/SpeakContract_i.jsp";

			SpeakContractForm form = new SpeakContractForm();
			SpeakContractVO vo = new SpeakContractVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);
			
			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			form.setRespman_nm(form.getSession_user_nm());
			
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
	public ModelAndView insertSpeakContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SpeakContractForm form = new SpeakContractForm();
		SpeakContractVO vo = new SpeakContractVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";

		try {

			bind(request, form);
			bind(request, vo);

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);

			// Double submit 방지(중복 등록 체크)
			if (Token.isValid(request)) {

				// 중복 등록이 아니면 등록 서비스 호출
				result = speakContractService.insertSpeakContract(vo);
			} else {
				// 중복 등록시 result값 세팅
				result = 100;
			}

			// 등록이 되었으면 검색조건을 초기화 한 후 리스트로 이동
			if (result > 0) {

				setInitFormVO(request, form, vo);
				mav = listSpeakContract(request, vo, form);

				// 중복 등록이 아니고, 입력이 성공했을 시 메시지 세팅
				if (result == 1) {
					returnMessage = messageSource.getMessage(
							"secfw.msg.succ.insert", null, new RequestContext(
									request).getLocale());
					Token.resetToken(request, "TOKEN");
				}
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error",
					null, new RequestContext(request).getLocale());
			// view 설정 - insert가 실패했으므로 insert 페이지 재 호출
			mav.setViewName("/WEB-INF/jsp/las/speakcontract/SpeakContract_i.jsp");
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
	 * 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardSpeakContractDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpeakContractForm form = new SpeakContractForm();
		SpeakContractVO vo = new SpeakContractVO();

		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		return forwardSpeakContractDetail(request, form, vo,
				new RequestContext(request).getLocale());
	}

	/**
	 * 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardSpeakContractDetail(HttpServletRequest request,
			SpeakContractForm form, SpeakContractVO vo, Locale locale)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String forwardURL = "/WEB-INF/jsp/las/speakcontract/SpeakContract_d.jsp";
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		
		try {
			// 조회
			
			lom = speakContractService.detailSpeakContract(vo); 

			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage(
						"secfw.msg.succ.noResult", null, locale);
			} 
			
			// 수정권한 세팅
			form.setAuth_modify(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReqman_id())) ;
			
			// 삭제권한 세팅
			form.setAuth_delete(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReqman_id())) ;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		// 리턴메시지 세팅
		form.setReturn_message(returnMessage) ;
		
		mav.setViewName(forwardURL);
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
	public ModelAndView forwardSpeakContractModify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpeakContractForm form = new SpeakContractForm();
		SpeakContractVO vo = new SpeakContractVO();
		String forwardURL = "/WEB-INF/jsp/las/speakcontract/SpeakContract_i.jsp" ;
		String returnMessage = "" ;
		ModelAndView mav = new ModelAndView() ;
		ListOrderedMap lom = new ListOrderedMap();
		
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		
		try {
			//선택한 데이터가 있는지 확인하기 위해 DB조회
			lom = speakContractService.detailSpeakContract(vo) ;
			
			//권한체크
			ClmsBoardUtil.checkDeleteModifyAuth(request,(String)lom.get("respman_id"), "modify") ;
			
			//조회결과가 없는 경우
			if(lom.size() == 0){
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale()) ;
			}
			
			//조회결과가 있으면 받아온 데이터 중 수정이 가능한 데이터를 FORM에 세팅한다
			else {
				form.setRespman_nm((String)lom.get("respman_nm"));
				form.setReqman_nm((String)lom.get("reqman_nm"));
				form.setTitle((String)lom.get("title"));
				form.setRe_cont((String)lom.get("re_cont"));
				form.setReqman_dept_nm((String)lom.get("reqman_dept_nm"));
			
				//textarea에 줄바꿈을 적용시키기 위함
				form.setRe_cont(StringUtil.replace((String)lom.get("re_cont"),"<br>","\r\n"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		form.setReturn_message(returnMessage) ;
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		return mav;
	}
	
	
	/**
	 * 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifySpeakContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			SpeakContractForm form = new SpeakContractForm();
			SpeakContractVO vo = new SpeakContractVO();
			ModelAndView mav = new ModelAndView();
			String returnMessage = null;
			int result = 0 ;
			ListOrderedMap lom = new ListOrderedMap();
			
			bind(request, form);
			bind(request, vo);
			
			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, vo);
			
			try {	
				lom = speakContractService.detailSpeakContract(vo) ;
				
				ClmsBoardUtil.checkDeleteModifyAuth(request,(String)lom.get("respman_id"), "modify") ;
				
				// 1. 중복 수정 체크
				if(Token.isValid(request)){
					// 1-1. 중복등록이 아닐시 : 등록 서비스 호출(첨부파일도 같이 등록된다.)
					result = speakContractService.modifySpeakContract(vo) ;
				} else {
					// 1-2. 중복 등록시 : result = 100 으로 세팅
					result = 100 ;
				}
				
				// 2. 성공시(result > 100) 글보기 페이지로 이동
				if(result>0){
					
					mav = forwardSpeakContractDetail(request, form, vo, new RequestContext(request).getLocale()) ;
					
					// 2-1 : 수정 성공시에 메시지 세팅/TOKEN 초기화
					if(result==1){
						returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale()) ;
						Token.resetToken(request, "TOKEN") ;
					}
				}
				// 3. 실패시 Exception 발생
				else {
					throw new Exception() ;
				}

		} catch (Exception e) {
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
			mav.setViewName("/WEB-INF/jsp/las/speakcontract/SpeakContract_i.jsp") ;
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage) ;
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteSpeakContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SpeakContractForm form = new SpeakContractForm();
		SpeakContractVO vo = new SpeakContractVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		
		bind(request, form);
		bind(request, vo);
		
		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);

		try {
			//삭제권한 체크를 위해 map에 정보(등록자_id)를 받아옴
			lom = speakContractService.detailSpeakContract(vo);
			
			
			//-----------------삭제권한 체크 (추가해야됨)----------------------
			ClmsBoardUtil.checkDeleteModifyAuth(request,(String)lom.get("respman_id"), "delete") ;
			
			if(Token.isValid(request)){
				//중복 삭제가 아닐 시
				result = speakContractService.deleteSpeakContract(vo);
			} else {
				//중복 삭제 시
				result = 100;
			}
			//성공 시
			if(result > 0){
				//삭제 성공 시 검색조건 및 페이지 초기화 후 리스트로 이동
				setInitFormVO(request, form, vo);
				mav = listSpeakContract(request, vo, form);
				
				//삭제 성공 & 중복 삭제가 아닐 시 삭제 성공 메시지 세팅
				if(result != 100){
					returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale()) ;
					Token.resetToken(request, "TOKEN") ;
				}
			}
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			//삭제 도중 에러 발생시 에러 메시지 세팅 & 조회하던 상세페이지로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
			mav = forwardSpeakContractDetail(request, form, vo, new RequestContext(request).getLocale()) ;
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		form.setReturn_message(returnMessage) ;
		mav.addObject("command", form);
		return mav;
	}

	
	/**
	 * row_cnt를 제외한 form, vo 초기화 
	 * 
	 * @param form
	 *            NoticeForm
	 * @param vo
	 *            NoticeVO
	 * @throws Exception
	 */
	private void setInitFormVO(HttpServletRequest request,
			SpeakContractForm form, SpeakContractVO vo) throws Exception {

		//form 검색관련 변수 및 페이지 변수 초기화
		form.setSrch_respman_nm(null);
		form.setSrch_title(null);
		form.setSrch_start_ymd(DateUtil.formatDate(
				DateUtil.addDays(DateUtil.today(), -365), "-"));
		form.setSrch_end_ymd(DateUtil.formatDate(DateUtil.today(), "-"));
		form.setCurPage("1");

		//vo 검색관련 변수 및 페이지 변수 초기화
		vo.setSrch_respman_nm(null);
		vo.setSrch_title(null);
		vo.setSrch_start_ymd(DateUtil.formatDate(
				DateUtil.addDays(DateUtil.today(), -365), "-"));
		vo.setSrch_end_ymd(DateUtil.formatDate(DateUtil.today(), "-"));
		vo.setCurPage("1");
	}
}