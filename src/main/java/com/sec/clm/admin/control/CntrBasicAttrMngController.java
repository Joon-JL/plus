/**
* Project Name : 계약관리체계 강화 프로젝트
* File Name : CntrBasicAttrMngController
* Description : 계약속성관리 Controller
* Author : 김형준
* Date : 2011. 08. 30
* Copyright : 
*/
package com.sec.clm.admin.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.CntrBasicAttrMngForm;
import com.sec.clm.admin.dto.CntrBasicAttrMngVO;
import com.sec.clm.admin.service.CntrBasicAttrMngService;
import com.sec.common.util.ClmsDataUtil;


/**
 * Description	: Controller
 * Author		: 김형준
 * Date			: 2011. 08. 30
 */
public class CntrBasicAttrMngController extends CommonController{
	
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	public CntrBasicAttrMngService cntrBasicAttrMngService;
	public void setCntrBasicAttrMngService(CntrBasicAttrMngService cntrBasicAttrMngService) {
		this.cntrBasicAttrMngService = cntrBasicAttrMngService;
	}


	/**
	 * 계약 속성 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listCntrBasicAttrMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/clm/admin/CntrBasicAttrMng_l.jsp";
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        CntrBasicAttrMngForm form  = new CntrBasicAttrMngForm();
	        CntrBasicAttrMngVO vo  = new CntrBasicAttrMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************                                                                      
			 * 파라미터세팅                                                                                                                 
			**********************************************************/
			if(form.getSrch_info_itm() == null){
				form.setSrch_info_itm("");
				vo.setSrch_info_itm("");
			}else{
				//xss 처리
				form.setSrch_info_itm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_info_itm(), "")));
				vo.setSrch_info_itm(form.getSrch_info_itm());
			}
			
			/*********************************************************                                                                          
			 * 페이지 객체                                                                                                                      
			**********************************************************/ 
			PageUtil pageUtil = new PageUtil();
			
			// 현재페이지를 set                                                                                                             
            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			vo.setStart_index(pageUtil.getStartIndex()); //페이지시작        
			vo.setEnd_index(pageUtil.getEndIndex()); //페이지마지막
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage    = "";	// 리턴메시지		
			List resultList    = null;
			
			
			/*********************************************************                                                                      
			 * 목록조회                                                                                                                     
			**********************************************************/
			resultList = cntrBasicAttrMngService.listCntrBasicAttrMng(vo);
			
			/*********************************************************
			 * 총개수조회
			**********************************************************/
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
				
			}else{
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
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);

			//mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("CntrBasicAttrMngController.listCntrBasicAttrMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrBasicAttrMngController.listCntrBasicAttrMng() Throwable !!");
		}
	}
	
	
	/**
	 * 계약 속성 상세조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailCntrBasicAttrMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/clm/admin/CntrBasicAttrMng_d.jsp";
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        CntrBasicAttrMngForm form  = new CntrBasicAttrMngForm();
	        CntrBasicAttrMngVO vo  = new CntrBasicAttrMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage    = "";	// 리턴메시지		
			List resultList    = null;

			
			/*********************************************************                                                                      
			 * 상세조회                                                                                                                     
			**********************************************************/
			resultList = cntrBasicAttrMngService.detailCntrBasicAttrMng(vo);
			
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			// 메시지처리 - 정상적으로 조회되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("CntrBasicAttrMngController.detailCntrBasicAttrMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrBasicAttrMngController.detailCntrBasicAttrMng() Throwable !!");
		}		
	}
	
	

}
