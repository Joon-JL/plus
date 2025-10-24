package com.sds.secframework.bbs.control;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.bbs.dto.BBSMngForm;
import com.sds.secframework.bbs.dto.BBSMngVO;
import com.sds.secframework.bbs.service.BBSMngService;
import com.sds.secframework.code.service.CodeService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;

public class BBSMngController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private BBSMngService bbsMngService;
	public void setBbsMngService(BBSMngService bbsMngService) {
		this.bbsMngService = bbsMngService;
	}
	
	/**
	 * CodeService 선언 및 세팅(공통코드 사용시)
	 */
	private CodeService codeService;
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	/**
	 * 페이지 처리 리스트 정보
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listBBSMasterPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 목록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSMngList.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BBSMngForm form = new BBSMngForm();
			bind(request, form);
						
			PageUtil pageUtil = new PageUtil();		
			
			List   listBBSMaster = null;
			String returnMessage = "";			
			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
			if ("N".equals(form.getDoSearch())) {
	
	            // 페이지 초기화
				form.setCurPage("1");
	                            
				pageUtil.setThisPage(0);
				pageUtil.setTotalRow(0);
				pageUtil.setThisPage(0);
	            
			} else {
	
	        	// 현재페이지를 set
				pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
	
		        /*********************************************************
				 * 파라미터세팅
				**********************************************************/
	    		HashMap hm = new HashMap();
	    		
	    		hm.put("sys_cd", sysCd);
	    		hm.put("srch_bbs_nm",    form.getSrch_bbs_nm());
	    		hm.put("srch_use_yn",    form.getSrch_use_yn());
	    		hm.put("start_index", ""+pageUtil.getStartIndex());
	    		hm.put("end_index",   ""+pageUtil.getEndIndex());
	    		hm.put("gSortStat",   form.getGSortStat());
	
	    		 /*********************************************************
				 * 게시판 목록 조회
				**********************************************************/
	    		listBBSMaster = bbsMngService.listBBSMasterPage(hm);		
	
	    		if(listBBSMaster != null && listBBSMaster.size() > 0) {
	            	ListOrderedMap lm = (ListOrderedMap)listBBSMaster.get(0);
	            	
	                pageUtil.setTotalRow(lm.get("total_cnt"));
	                pageUtil.setRowPerPage(10);
	                pageUtil.setGroup();
	                
	                // 메시지처리 - 정상적으로 조회되었습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } 
	            } else {
	            	// 메시지처리 - 조회된 결과가 없습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } else {       
		            	returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());	                
		            }
	            }
           
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", listBBSMaster);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			
			mav.addObject("returnMessage", returnMessage);
						
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
    }
	
	/**
	 * 게시판관리 상세내역 정보를 가져온다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailBBSMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 상세로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSMngDetail.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BBSMngForm form = new BBSMngForm();
			bind(request, form);
			
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		HashMap hm = new HashMap();
    		
    		hm.put("sys_cd", sysCd);
	    	hm.put("bbs_id", form.getBbs_id());
	
	    	/*********************************************************
			 * 게시판관리 상세내역 조회
			**********************************************************/
	    	List detailBBSMaster = bbsMngService.detailBBSMaster(hm);		
	    	
	        // 메시지처리 - 정상적으로 조회되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
	    	
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", detailBBSMaster);
			mav.addObject("returnMessage",returnMessage);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
		
    }

	/**
	 * 게시판관리 등록 페이지로 이동.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goInsertBBSMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 등록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSMngInsert.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BBSMngForm form = new BBSMngForm();
			bind(request, form);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
		
    }

	/**
	 * 게시판 관리 정보를 등록한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertBBSMaster(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 목록 Control로 Forwading
	        String forwardURL = "/secfw/bbsMng.do?method=listBBSMasterPage&amp;doSearch=Y";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BBSMngVO vo = new BBSMngVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);   //시스템코드
			vo.setReg_id(userId);  //등록자아이디
			
			/*********************************************************
			 * 게시판 내역 등록
			**********************************************************/
			int result = -1;
			String returnMessage = "";
			
			if(Token.isValid(request)) {
				result = bbsMngService.insertBBSMaster(vo);		
			}
			
			// 메시지처리 - 등록되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("returnMessage",returnMessage);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}

    }

	/**
	 * 게시판 관리 정보를 수정한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyBBSMaster(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 목록 Control로 Forwading
	        String forwardURL = "/secfw/bbsMng.do?method=listBBSMasterPage&amp;doSearch=Y";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BBSMngVO vo = new BBSMngVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);  //시스템코드
			vo.setMod_id(userId); //수정자아이디
			
	    	/*********************************************************
			 * 게시판관리 내역 수정
			**********************************************************/
			int result = bbsMngService.modifyBBSMaster(vo);		
		    	  
	        // 메시지처리 - 수정되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("returnMessage",returnMessage);
			mav.addObject("command", vo);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
    }
	
	/**
	 * 게시판 관리 정보를 삭제한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteBBSMaster(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 목록 Control로 Forwading
	        String forwardURL = "/secfw/bbsMng.do?method=listBBSMasterPage&amp;doSearch=Y";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSMngForm form = new BBSMngForm();
			bind(request, form);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());
			
	    	/*********************************************************
			 * 게시판관리 내역 삭제
			**********************************************************/
	    	int result = bbsMngService.deleteBBSMaster(hm);		
		    	  
	        // 메시지처리 - 삭제되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
	    	
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("returnMessage",returnMessage);
			mav.addObject("command", form);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
    }
	
}
