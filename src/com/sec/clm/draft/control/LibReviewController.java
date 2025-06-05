/**
 * Project Name : 계약서Library 유형별 조회
 * File name	: LibReviewController.java
 * Description	: 계약서Library 유형별 조회  Controller
 * Author		: 신승완
 * Date			: 2011. 08. 29
 * Copyright	:
 */
package com.sec.clm.draft.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.util.service.ComUtilService;

import com.sds.secframework.common.dao.CommonDAO;

import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sds.secframework.common.control.CommonController;

import com.sec.clm.draft.dto.LibReviewForm;
import com.sec.clm.draft.dto.LibReviewVO;
import com.sec.clm.draft.service.LibReviewService;

/**
 * Description	: 국내/해외계약서  Controller
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public class LibReviewController extends CommonController {
	
	/**
	 * Library 서비스
	 */
	private LibReviewService libReviewService;
	
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;
	
	/**
	 * Library 서비스 세팅
	 * 
	 * @param libReviewService
	 * @return void
	 * @throws
	 */
	public void setLibReviewService(LibReviewService libReviewService) {
		this.libReviewService = libReviewService;
	}
	
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
	 * 계약서Library 유형별  목록 전체조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLibReview1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibReviewForm form       = null;
			LibReviewVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/draft/LibReview1_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new LibReviewForm();
			vo   = new LibReviewVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 라이브러리 구분 : C035 - C03501(Reference계약서), C03502(표준Template), C03503(계약조항Library)
			vo.setLib_gbn(StringUtil.bvl(form.getLib_gbn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getLib_gbn : " + form.getLib_gbn());
			getLogger().debug("############################");
			// 지역 구분 : C018 - C01801(국내), C01802(해외)
			vo.setRegion_gbn(StringUtil.bvl(form.getRegion_gbn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getRegion_gbn : " + vo.getRegion_gbn());
			getLogger().debug("############################");
			// 비즈니스 분류 : T01
			vo.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_biz_clsfcn : " + vo.getSrch_biz_clsfcn());
			getLogger().debug("############################");
			// 단계 분류 : T02
			vo.setSrch_depth_clsfcn(StringUtil.bvl(form.getSrch_depth_clsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_depth_clsfcn : " + vo.getSrch_depth_clsfcn());
			getLogger().debug("############################");
			// 체결목적 대 분류 : T03
			vo.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_cnclsnpurps_bigclsfcn : " + vo.getSrch_cnclsnpurps_bigclsfcn());
			getLogger().debug("############################");
			// 체결목적 중 분류 : 체결목적 대분류에 대한 ...
			vo.setSrch_cnclsnpurps_midclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_midclsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_cnclsnpurps_midclsfcn : " + vo.getSrch_cnclsnpurps_midclsfcn());
			getLogger().debug("############################");

			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			/*********************************************************
			 * Service
			**********************************************************/
			resultList       = libReviewService.listLibReview1(vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listLibReview() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listLibReview() Throwable !!");
		}
	}

	/**
	 * 계약서Library 유형별  목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLibReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibReviewForm form       = null;
			LibReviewVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/draft/LibReview_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new LibReviewForm();
			vo   = new LibReviewVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 라이브러리 구분 : C035 - C03501(Reference계약서), C03502(표준Template), C03503(계약조항Library)
			vo.setLib_gbn(StringUtil.bvl(form.getLib_gbn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getLib_gbn : " + form.getLib_gbn());
			getLogger().debug("############################");
			// 지역 구분 : C018 - C01801(국내), C01802(해외)
			vo.setRegion_gbn(StringUtil.bvl(form.getRegion_gbn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getRegion_gbn : " + vo.getRegion_gbn());
			getLogger().debug("############################");
			// 비즈니스 분류 : T01
			vo.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_biz_clsfcn : " + vo.getSrch_biz_clsfcn());
			getLogger().debug("############################");
			// 단계 분류 : T02
			vo.setSrch_depth_clsfcn(StringUtil.bvl(form.getSrch_depth_clsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_depth_clsfcn : " + vo.getSrch_depth_clsfcn());
			getLogger().debug("############################");
			// 체결목적 대 분류 : T03
			vo.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_cnclsnpurps_bigclsfcn : " + vo.getSrch_cnclsnpurps_bigclsfcn());
			getLogger().debug("############################");
			// 체결목적 중 분류 : 체결목적 대분류에 대한 ...
			vo.setSrch_cnclsnpurps_midclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_midclsfcn(),""));
			getLogger().debug("############################");
			getLogger().debug("#######getSrch_cnclsnpurps_midclsfcn : " + vo.getSrch_cnclsnpurps_midclsfcn());
			getLogger().debug("############################");

			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = libReviewService.listLibReview(vo);

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
			
			ArrayList lom = (ArrayList)resultList;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
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
			throw new Exception("listLibReview() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listLibReview() Throwable !!");
		}
	}

	/**
	 * 계약서Library 유형별   상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLibReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibReviewForm form       = null;
			LibReviewVO   vo         = null;
			List   		resultList = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * ForwardURL setting
			**********************************************************/
			forwardURL =  "/WEB-INF/jsp/clm/draft/LibReview_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LibReviewForm();
			vo   = new LibReviewVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = libReviewService.getLibReview(vo);
			
			if(resultList==null)
				throw new Exception("########### detailLibReview resultList null !! ############### ");

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			// 본인 글이 아닐 경우 조회수 증가			
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				getLogger().debug("###### Increase Rdcnt #####");
				libReviewService.increseRdcnt(vo) ;				
			}
			
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
			throw new Exception("detailLibReview() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("detailLibReview() Throwable !!");
		}
	}

}