/**
* Project Name : 계약관리체계 강화 프로젝트
* File Name : CntrBasicAttrMngController
* Description : 계약유형별 속성관리 Controller
* Author : 김형준
* Date : 2011. 08. 30
* Copyright : 
*/
package com.sec.clm.admin.control;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.sec.clm.admin.dto.CntrTypeBasicInfoMngForm;
import com.sec.clm.admin.dto.CntrTypeBasicInfoMngVO;
import com.sec.clm.admin.service.CntrTypeBasicInfoMngService;
import com.sec.common.util.ClmsDataUtil;


/**
 * Description	: Controller
 * Author		: 김형준
 * Date			: 2011. 09. 01
 */
public class CntrTypeBasicInfoMngController extends CommonController{
	
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	public CntrTypeBasicInfoMngService cntrTypeBasicInfoMngService;
	public void setCntrTypeBasicInfoMngService(CntrTypeBasicInfoMngService cntrTypeBasicInfoMngService) {
		this.cntrTypeBasicInfoMngService = cntrTypeBasicInfoMngService;
	}

	/**
	 * 계약 유형별 속성 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listCntrTypeBasicInfoMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			 String iframe_yn = StringUtil.bvl((String)request.getParameter("iframe_yn"), "N");
			 
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
			String forwardURL = "";
			if ("Y".equals(iframe_yn)) {
				forwardURL = "/WEB-INF/jsp/clm/admin/CntrTypeBasicInfoMngIF_l.jsp";
	        }else{
	        	forwardURL = "/WEB-INF/jsp/clm/admin/CntrTypeBasicInfoMng_l.jsp";
	        }
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        CntrTypeBasicInfoMngForm form  = new CntrTypeBasicInfoMngForm();
	        CntrTypeBasicInfoMngVO vo  = new CntrTypeBasicInfoMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************                                                                      
			 * 파라미터세팅                                                                                                                 
			**********************************************************/
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
			form.setSrch_depth_clsfcn(StringUtil.bvl(form.getSrch_depth_clsfcn(),""));
			vo.setSrch_depth_clsfcn(form.getSrch_depth_clsfcn());
			form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(),""));
			vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
			form.setSrch_cnclsnpurps_midclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_midclsfcn(),""));
			vo.setSrch_cnclsnpurps_midclsfcn(form.getSrch_cnclsnpurps_midclsfcn());
			
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
			
			HashMap resultMap = null;
			List infoList = null;
			
			/*********************************************************                                                                      
			 * 목록조회                                                                                                                     
			**********************************************************/
			if ("Y".equals(iframe_yn)) { //iframe 
				resultMap = cntrTypeBasicInfoMngService.listCntrTypeBasicInfoMngIF(vo);
			}else{
				infoList = cntrTypeBasicInfoMngService.listCntrTypeBasicInfoMng(vo);
				
				/*********************************************************
				 * 총개수조회
				**********************************************************/
				if (infoList != null && infoList.size() > 0) {
					ListOrderedMap tmp = (ListOrderedMap)infoList.get(0); //총갯수 셋팅
					
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
			}
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			
			if ("Y".equals(iframe_yn)) { //iframe 
				mav.addObject("resultMap", resultMap);
			}else{
				ArrayList lom = (ArrayList)infoList;
				
				mav.addObject("lom", lom);
				mav.addObject("pageUtil", pageUtil); //
				mav.addObject("returnMessage", returnMessage);
			}
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.listCntrTypeBasicInfoMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.listCntrTypeBasicInfoMng() Exception !!");
		}
	}
	
	
	/**
	 *계약 유형별 속성 상세조회detailCntrTypeBasicInfoMng
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailCntrTypeBasicInfoMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/clm/admin/CntrTypeBasicInfoMng_d.jsp";
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
			CntrTypeBasicInfoMngForm form  = new CntrTypeBasicInfoMngForm();
	        CntrTypeBasicInfoMngVO vo  = new CntrTypeBasicInfoMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage    = "";	// 리턴메시지		
			HashMap resultMap = null;
			
			/*********************************************************                                                                      
			 * 상세조회                                                                                                                     
			**********************************************************/
			resultMap = cntrTypeBasicInfoMngService.detailCntrTypeBasicInfoMng(vo);
			
			String modifyRole = StringUtil.bvl(getAccessLevel(form), "");
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			// 메시지처리 - 정상적으로 조회되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultMap", resultMap);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("modifyRole", modifyRole);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.detailCntrBasicAttrMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.detailCntrBasicAttrMng() Throwable !!");
		}		
	}
	
	/**
	 *계약 유형별 속성 수정 이동
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyTypeBasicInfoMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/clm/admin/CntrTypeBasicInfoMng_u.jsp";
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        CntrTypeBasicInfoMngForm form  = new CntrTypeBasicInfoMngForm();
	        CntrTypeBasicInfoMngVO vo  = new CntrTypeBasicInfoMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage    = "";	// 리턴메시지		
			HashMap resultMap = null;

			/*********************************************************                                                                      
			 * 상세조회                                                                                                                     
			**********************************************************/
			resultMap = cntrTypeBasicInfoMngService.detailCntrTypeBasicInfoMng(vo);
			
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			// 메시지처리 - 정상적으로 조회되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultMap", resultMap);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.forwardModifyTypeBasicInfoMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.forwardModifyTypeBasicInfoMng() Throwable !!");
		}			
	}	

	/**
	 *계약 유형별 속성 수
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyTypeBasicInfoMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
			String forwardURL = "/clm/admin/cntrTypeBasicInfoMng.do?method=detailCntrTypeBasicInfoMng";
			
			/*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        CntrTypeBasicInfoMngForm form  = new CntrTypeBasicInfoMngForm();
	        CntrTypeBasicInfoMngVO vo  = new CntrTypeBasicInfoMngVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage    = "";	// 리턴메시지		
			int result    = 0;
			
			/*********************************************************
			 * 수정
			**********************************************************/
			result = cntrTypeBasicInfoMngService.modifyCntrTypeBasicInfoMng(vo);
			
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
			throw new Exception("CntrTypeBasicInfoMngController.modifyTypeBasicInfoMng() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("CntrTypeBasicInfoMngController.modifyTypeBasicInfoMng() Throwable !!");
		}	
	}
	
	/**
	 * 수정권한 설정
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String getAccessLevel(CntrTypeBasicInfoMngForm form) throws Exception{
		/*
			RA01 : 법무관리자
			RA02 : 법무담당자
			RA03 : 법무일반사용자
			RA00 : 시스템 관리자
			
			외엔 수정 불가능
			
			A : 수정 가능
		*/
		String result = "";
        ArrayList roleList = form.getSession_user_role_cds();
        
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
        	for(int i=0; i < roleList.size() ;i++){
        		HashMap hm = (HashMap)roleList.get(i);
        		String roleCd = (String)hm.get("role_cd");
        		userRoleList.add(roleCd);
        	}
        }
        
        if(userRoleList != null && userRoleList.size()>0) {
            if(userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")) {	
                result = "A";
            }
        }
        return result;
	}
}
