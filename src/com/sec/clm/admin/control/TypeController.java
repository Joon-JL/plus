/**
 * Project Name : 계약관리시스템
 * File name	: TypeController.java
 * Description	: 계약유형관리  Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.TypeForm;
import com.sec.clm.admin.dto.TypeVO;
import com.sec.clm.admin.service.TypeService;
import com.sec.common.util.ClmsBoardUtil;

/**
 * Description	: Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class TypeController extends CommonController {
	
	/**
	 * Type 서비스
	 */
	public TypeService typeService;
	
	/**
	 * Type 서비스 세팅
	 * 
	 * @param typeService
	 * @return void
	 * @throws
	 */
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	
	/**
	 * 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
			PageUtil pageUtil   = null;
			List   	 resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Type_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new TypeForm();
			vo   = new TypeVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setSrch_dimension(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_dimension(),"")));
			form.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_word(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			if(form.getCurPage() == null) {
				setInitFormVO(request, form, vo);
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = typeService.listType(vo);

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
			throw new Exception("TypeController.listType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listType() Throwable !!");
		}
	}
	
	/**
	 * Item 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listTypeItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/clm/admin/Type_p.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new TypeForm();
			vo   = new TypeVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setType_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getType_cd(),"")));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = typeService.listTypeItem(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());

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
			throw new Exception("TypeController.listTypeItem() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listTypeItem() Throwable !!");
		}
	}
	
	/**
	 * Item 적용 목록 조회
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listApplyItem(TypeVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List   	   resultList = null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = typeService.listApplyItem(vo);
			
			ArrayList lom = (ArrayList)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.listApplyItem() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listApplyItem() Throwable !!");
		}
	}
	
	/**
	 * 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Type_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new TypeForm();
			vo   = new TypeVO();
			
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
			throw new Exception("TypeController.forwardInsertType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.forwardInsertType() Throwable !!");
		}
	}
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/clm/admin/type.do?method=detailType";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new TypeForm();
			vo   = new TypeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			String type_cd = typeService.insertType(vo);
			
			form.setType_cd(type_cd);
			
			vo.setType_cd(type_cd);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 등록되었습니다.
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
			throw new Exception("TypeController.insertType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.insertType() Throwable !!");
		}
	}

	/**
	 * 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
			List   	 resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Type_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new TypeForm();
			vo   = new TypeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			//forward request 속성 값 가져오기...
			if(request.getAttribute("command")!=null) {
				TypeForm command = (TypeForm)request.getAttribute("command");
				
				form.setType_cd(command.getType_cd());
				
				vo.setType_cd(command.getType_cd());
			}

			// 수정권한 세팅
			form.setAuth_modify(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID
			// 삭제권한 세팅
			form.setAuth_delete(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = typeService.detailType(vo);

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			ArrayList lom_item = listApplyItem(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
			 * Massage
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("lom_item", lom_item);		//적용대상 List
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.detailType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.detailType() Throwable !!");
		}
	}

	/**
	 * 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
			List   	   resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Type_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new TypeForm();
			vo   = new TypeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = typeService.detailType(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			ArrayList lom_item = listApplyItem(vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("lom_item", lom_item);		//적용대상 List
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.forwardModifyType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.forwardModifyType() Throwable !!");
		}
	}

	/**
	 * 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form        = null;
			TypeVO   vo          = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/type.do?method=detailType";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new TypeForm();
			vo   = new TypeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setMod_id(vo.getSession_user_id());
			form.setMod_nm(vo.getSession_user_nm());
			form.setMod_dept_nm(vo.getSession_user_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			int result = typeService.modifyType(vo);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 수정되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
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
			throw new Exception("TypeController.modifyType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.modifyType() Throwable !!");
		}
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			TypeForm form       = null;
			TypeVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/type.do?method=listType";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new TypeForm();
			vo   = new TypeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setMod_id(vo.getSession_user_id());
			form.setMod_nm(vo.getSession_user_nm());
			form.setMod_dept_nm(vo.getSession_user_nm());

			/*********************************************************
			 * Service
			**********************************************************/
			int result = typeService.deleteType(vo);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 삭제되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

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
			throw new Exception("TypeController.deleteType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.deleteType() Throwable !!");
		}
	}

	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	public void setInitFormVO(HttpServletRequest request, TypeForm form, TypeVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
}