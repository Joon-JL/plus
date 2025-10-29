/**
 * Project Name : 계약관리시스템
 * File name	: DimensionController.java
 * Description	: 계약분류관리  Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.control;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.DimensionForm;
import com.sec.clm.admin.dto.DimensionVO;
import com.sec.clm.admin.service.DimensionService;
import com.sec.common.util.ClmsBoardUtil;

/**
 * Description	: Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class DimensionController extends CommonController {
	
	/**
	 * Dimension 서비스
	 */
	public DimensionService dimensionService;

	/**
	 * Dimension 서비스 세팅
	 * 
	 * @param dimensionService
	 * @return void
	 * @throws
	 */
	public void setDimensionService(DimensionService dimensionService) {
		this.dimensionService = dimensionService;
	}
	
	/**
	 * 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/clm/admin/Dimension_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new DimensionForm();
			vo   = new DimensionVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
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
			resultList = dimensionService.listDimension(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(tmp.get("total_cnt"))));
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
			throw new Exception("DimensionController.listDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.listDimension() Throwable !!");
		}
	}
	
	/**
	 * 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Dimension_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new DimensionForm();
			vo   = new DimensionVO();
			
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
			throw new Exception("DimensionController.forwardInsertDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.forwardInsertDimension() Throwable !!");
		}
	}
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/clm/admin/dimension.do?method=detailDimension";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new DimensionForm();
			vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			String type_cd = dimensionService.insertDimension(vo);
			
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
			throw new Exception("DimensionController.insertDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.insertDimension() Throwable !!");
		}
	}
	
	/**
	 * 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/clm/admin/Dimension_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new DimensionForm();
			vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			//forward request 속성 값 가져오기...
			if(request.getAttribute("command")!=null) {
				DimensionForm command = (DimensionForm)request.getAttribute("command");
				
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
			resultList = dimensionService.detailDimension(vo);

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
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
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("DimensionController.detailDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.detailDimension() Throwable !!");
		}
	}

	/**
	 * 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/clm/admin/Dimension_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new DimensionForm();
			vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = dimensionService.detailDimension(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("DimensionController.forwardModifyDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.forwardModifyDimension() Throwable !!");
		}
	}

	/**
	 * 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/dimension.do?method=detailDimension";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new DimensionForm();
			vo   = new DimensionVO();

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
			int result = dimensionService.modifyDimension(vo);
			
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
			throw new Exception("DimensionController.modifyDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.modifyDimension() Throwable !!");
		}
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteDimension(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			DimensionForm form       = null;
			DimensionVO   vo         = null;
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
			forwardURL = "/clm/admin/dimension.do?method=listDimension";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new DimensionForm();
			vo   = new DimensionVO();

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
			int result = dimensionService.deleteDimension(vo);
			
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
			throw new Exception("DimensionController.deleteDimension() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("DimensionController.deleteDimension() Throwable !!");
		}
	}

	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	public void setInitFormVO(HttpServletRequest request, DimensionForm form, DimensionVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
	
	/**
	 * 유형 관리 Tree 조회
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 * 작성자 : 김재원
	 * 내  역  : 추가 개발로 인하여 유형관리를 Tree로 볼 수 있는 메소드 추가 함. 2011.10.24
	 */
	public void listDimensionTree(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
	        String sysCd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        DimensionVO   vo  = new DimensionVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = dimensionService.listDimensionTree(vo);	

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
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
	* 계약유형별정의
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDimensionInfolist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/share/DimensionInfoList_l.jsp";
			String srch_type_cd = StringUtil.bvl((String)request.getParameter("srch_type_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form       = new DimensionForm();
			DimensionVO   vo         = new DimensionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setType_cd(srch_type_cd);//검색시넘어오는 typ_cd
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			//List resultList = dimensionService.listDimensionChart(vo);
			
			List resultList = null;

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	* 계약유형별 정의
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDimensionChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/share/Dimension_l.jsp";
			String srch_type_cd = StringUtil.bvl((String)request.getParameter("srch_type_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form       = new DimensionForm();
			DimensionVO   vo         = new DimensionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setType_cd(srch_type_cd);//검색시넘어오는 typ_cd
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			List resultList = dimensionService.listDimensionChart(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	* 계약용어집
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDimensionWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/share/DimensionWord_l.jsp";
			String srch_rule_no = StringUtil.bvl((String)request.getParameter("rule_no"), "0");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form       = new DimensionForm();
			DimensionVO   vo         = new DimensionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}
			
			form.setSrch_rule_no(Integer.parseInt(srch_rule_no));
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			List resultList = dimensionService.listDimensionWord(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	* 계약 용어집, 계약유형별 이해 리스트
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDimensionWordAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/DimensionWord_l.jsp";//목록페이지로 이동.
			
			DimensionForm cmd = (DimensionForm)request.getAttribute("command");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO vo = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){//저장 후 데이터 호출을 위해.
				form = cmd;
				vo.setRule_no(form.getRule_no());
			}
			
			form.setSrch_one_rule_depth(StringUtil.bvl(form.getSrch_one_rule_depth(), ""));//1단계
			form.setSrch_two_rule_depth(StringUtil.bvl(form.getSrch_two_rule_depth(), ""));//2단계
			form.setSrch_cont(StringUtil.bvl(form.getSrch_cont(), ""));//내용
			form.setSrch_rule_choice(StringUtil.bvl(form.getSrch_rule_choice(), ""));//최상위
			
			vo.setSrch_one_rule_depth(StringUtil.bvl(form.getSrch_one_rule_depth(), ""));//1단계
			vo.setSrch_two_rule_depth(StringUtil.bvl(form.getSrch_two_rule_depth(), ""));//2단계
			vo.setSrch_cont(StringUtil.bvl(form.getSrch_cont(), ""));//내용
			vo.setSrch_rule_choice(StringUtil.bvl(form.getSrch_rule_choice(), ""));//최상위
			
//			vo.setMenuGubun(StringUtil.bvl(form.getMenuGubun(), ""));//메뉴구분
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			/*********************************************************
			 * 검색처리
			**********************************************************/
			List ruleListODepth = dimensionService.listODepthDimesionWord(vo);//1단계 조회
			List resultList = dimensionService.listDimesionWordAdmin(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(lom.get("total_cnt"))));
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

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("ruleListODepth", ruleListODepth);//1단계 조회
			mav.addObject("pageUtil", pageUtil);
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
	* 계약용어집 입력 창으로 이동
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertDimensionAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/DimensionWord_i.jsp";// 입력페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}
			
			List upRuleList = dimensionService.listUpRule(vo);//전체depth selectbox 조회.

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("upRuleList", upRuleList);
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
	* 계약용어집 등록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertDimensionWordAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/dimension.do?method=listDimensionWordAdmin"; //목록페이지로 이동.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setOne_rule_title_input(StringUtil.bvl(form.getOne_rule_title_input(), ""));//최상위제목
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278") || vo.getMenu_id().equals("20130321153745987_0000000437")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = dimensionService.insertDimensionWordAdmin(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

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
	* 계약용어집 상세
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailDimensionWordAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/DimensionWord_d.jsp";//상세페이지로 이동.
			DimensionForm cmd = (DimensionForm)request.getAttribute("command");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){//저장 후 데이터 호출을 위해.
				form = cmd;
				vo.setRule_no(form.getRule_no());
			}
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = dimensionService.detailDimensionWordAdmin(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	* 계약용어집 수정 화면 이동
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyDimensionWordAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/DimensionWord_u.jsp";//수정페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = dimensionService.detailDimensionWordAdmin(vo);

			ModelAndView mav = new ModelAndView();
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	* 계약용어집 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyDimensionWordAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/dimension.do?method=detailDimensionWordAdmin";//상세페이지로 이동.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(form.getSession_user_id());
			vo.setMod_nm(form.getSession_user_nm());
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = dimensionService.modifyDimensionWordAdmin(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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
	* 계약용어집 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteDimensionWordAmdin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/dimension.do?method=listDimensionWordAdmin"; //목록페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DimensionForm form = new DimensionForm();
			DimensionVO   vo   = new DimensionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setDel_id(form.getSession_user_id());
			vo.setDel_nm(form.getSession_user_nm());
			
			if(vo.getMenu_id().equals("20111101205129964_0000000282") || vo.getMenu_id().equals("20130319155933166_0000000380") || vo.getMenu_id().equals("20111028162847369_0000000278")|| vo.getMenu_id().equals("20130321153745987_0000000437")){ // 계약 용어집
				vo.setMenuGubun("C05302");//메뉴 구분
			} else if(vo.getMenu_id().equals("20111106105940035_0000000283") || vo.getMenu_id().equals("20111021143617885_0000000236") || vo.getMenu_id().equals("20111028162812818_0000000277") ) {  // 계약 유형별 이해
				vo.setMenuGubun("C05303");//메뉴 구분
			} else {
				vo.setMenuGubun("");//메뉴 구분
			}

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = dimensionService.deleteDimensionWordAmdin(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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
	
}