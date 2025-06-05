package com.sds.secframework.bbs.control;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.bbs.dto.BBSForm;
import com.sds.secframework.bbs.dto.BBSVO;
import com.sds.secframework.bbs.service.BBSMngService;
import com.sds.secframework.bbs.service.BBSService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

public class BBSController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private BBSService bbsService;
	public void setBbsService(BBSService bbsService) {
		this.bbsService = bbsService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private BBSMngService bbsMngService;
	public void setBbsMngService(BBSMngService bbsMngService) {
		this.bbsMngService = bbsMngService;
	}
		
	/**
	 * 게시판 목록조회.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView listBBSDetailPage(HttpServletRequest request, HttpServletResponse response) throws Exception {


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
	        // 게시물 목록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSList.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
	        
	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			List listBBSDetailPage   = null; // 목록리스트
			ListOrderedMap bbsOption = null; //BBS 옵션
	        String returnMessage     = "";	 // 리턴메시지
			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
			String doSearch = StringUtil.bvl(form.getDoSearch(), (String)request.getAttribute("doSearch"));
		    if ("Y".equals(doSearch)) {
	
	        	// 현재페이지를 set
	            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
	
		        /*********************************************************
				 * 파라미터세팅
				**********************************************************/
	    		vo.setSys_cd(sysCd);                             //시스템코드
	    		vo.setStart_index(pageUtil.getStartIndex());     //페이지시작
	    		vo.setEnd_index(pageUtil.getEndIndex());         //페이지마지막
	    		vo.setSrch_start_notice_ymd(StringUtil.replace(vo.getSrch_start_notice_ymd(),"-", ""));
	    		vo.setSrch_end_notice_ymd(StringUtil.replace(vo.getSrch_end_notice_ymd(),"-", ""));

	    		if(form.getSrch_start_notice_ymd() == null || "".equals(form.getSrch_start_notice_ymd())) {
	    			form.setSrch_start_notice_ymd(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),-30),"-"));
	    		}
	    		
	    		if(form.getSrch_end_notice_ymd() == null || "".equals(form.getSrch_end_notice_ymd())) {
	    			form.setSrch_end_notice_ymd(DateUtil.formatDate(DateUtil.today(),"-"));
	    		}

	    		if(vo.getSrch_start_notice_ymd() == null || "".equals(vo.getSrch_start_notice_ymd())) {
	    			vo.setSrch_start_notice_ymd(DateUtil.addDays(DateUtil.today(),-30));
	    		}
	    		if(vo.getSrch_end_notice_ymd() == null || "".equals(vo.getSrch_end_notice_ymd())) {
	    			vo.setSrch_end_notice_ymd(DateUtil.today());
	    		}
	    		
	    		/*********************************************************
				 * 게시글 목록조회
				**********************************************************/
	    		listBBSDetailPage = bbsService.listBBSDetailPage(vo);
	    		
	    		/*********************************************************
				 * 게시판 옵션조회
				**********************************************************/
				HashMap hm = new HashMap();
				hm.put("sys_cd", sysCd);
				hm.put("bbs_id", form.getBbs_id());

				bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);
	            
	            if(listBBSDetailPage != null && listBBSDetailPage.size() > 0) {
	            	ListOrderedMap lm = (ListOrderedMap)listBBSDetailPage.get(0);
	            	
	                pageUtil.setTotalRow(lm.get("total_cnt"));
	                pageUtil.setRowPerPage(10);
	                pageUtil.setGroup();
	                
	                // 메시지처리
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } 
	            } else {
	            	// 메시지처리 - 조회된 결과가 없습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } 
	            }
	            
	        } else {
	            // 페이지 초기화
	        	form.setCurPage("1");
	                            
	            pageUtil.setThisPage(0);
	            pageUtil.setTotalRow(0);
	            pageUtil.setThisPage(0);

	        } 
		    
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", listBBSDetailPage);
			mav.addObject("bbsOption", bbsOption);
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
	 * 게시판 등록(등록하기 위하여 등록페이지로 이동).
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView goInsertBBSDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSInsert.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
			 /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap bbsOption = null; //BBS 옵션

			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());

			bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);        

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("bbsOption", bbsOption);
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
	 * (첨부파일 표시).
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView goFileUploadPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
			 /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String sDir = propertyService.getProperty("secfw.epftp.sDir");
			String serverName = propertyService.getProperty("secfw.epftp.serverName");
			String serverPort = propertyService.getProperty("secfw.epftp.serverPort");
			String epftpdIP   = propertyService.getProperty("secfw.epftp.epftpdIP");
			String fileInfos  = StringUtil.bvl(form.getFileInfos(), "");		
			String fileRefNo  = StringUtil.bvl(form.getFile_ref_no(), "");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/jsp/secfw/common/EPUploadType1.jsp");
			mav.addObject("sDir", sDir);
			mav.addObject("serverName", serverName);
			mav.addObject("serverPort", serverPort);
			mav.addObject("epftpdIP", epftpdIP);
			mav.addObject("fileInfos",URLDecoder.decode(fileInfos, "utf-8"));
			mav.addObject("fileRefNo",fileRefNo);
	    
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
	 * 게시판 등록.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView insertBBSDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시상세 조회 Control로 Forwading
	        String forwardURL = "/secfw/bbs.do?method=detailBBSDetail";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);

		    /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setReg_id(userId);
	        vo.setNotice_start_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_start_ymd(),""),"-"));
			vo.setNotice_end_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_end_ymd(),""),"-"));

			//긴급게시
	        if(form.getUrgency_yn() == null || "".equals(form.getUrgency_yn().trim())) {
	        	vo.setUrgency_yn("N");
	        } else {
	        	vo.setUrgency_yn("Y");
	        }
	        
			// 게시글 등록
	        String noticeId = bbsService.insertBBSDetail(vo);
	        
		    /*********************************************************
			 * 메시지
			**********************************************************/
	        // 메시지처리 - 등록되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
	
		    /*********************************************************
			 * 상세조회 컨트롤러 호출
			**********************************************************/
	    	ModelAndView mav = new ModelAndView(); //상세페이지 이동
			mav.setViewName(forwardURL);
			
			mav.addObject("noticeId", noticeId);
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
	 * 게시판 수정페이지 이동.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView getBBSDetailForUpd(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시물 수정페이지
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSModify.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        
			// 게시글 상세조회
	        HashMap result = new HashMap();
	        result = bbsService.detailBBSDetail(vo);
	        
			ArrayList getBBSDetail     = (ArrayList)result.get("getBBSDetail");
			String    listBBSFile       = (String)result.get("listBBSFile");
			ArrayList listBBSAppend     = (ArrayList)result.get("listBBSAppend");
			ArrayList getBBSPrevNextID = (ArrayList)result.get("getBBSPrevNextID");
			String    getPrevTitle     = (String)result.get("getPrevTitle");
			String    getNextTitle     = (String)result.get("getNextTitle");		
			String getBBSRecommendYn = (String)result.get("getBBSRecommendYn");

	        /*********************************************************
			 * 게시판 옵션조회
			**********************************************************/
			ListOrderedMap bbsOption = null; //BBS 옵션

			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());

			bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);        

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			
			mav.addObject("getBBSDetail", getBBSDetail);
			mav.addObject("listBBSFile", URLDecoder.decode(listBBSFile));
			mav.addObject("listBBSAppend", listBBSAppend);
			mav.addObject("getBBSPrevNextID", getBBSPrevNextID);
	        mav.addObject("getPrevTitle", getPrevTitle);
	        mav.addObject("getNextTitle", getNextTitle);
			mav.addObject("getBBSRecommendYn", getBBSRecommendYn);
			mav.addObject("bbsOption", bbsOption);
			
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
	 * 게시판 수정.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView modifyBBSDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시상세 조회 Control로 Forwading
	        String forwardURL = "/secfw/bbs.do?method=detailBBSDetail";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setMod_id(userId);		
	        vo.setNotice_title(StringUtil.convertHtmlTochars(vo.getNotice_title()));  // 제목
	        vo.setNotice_start_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_start_ymd(),""),"-"));
			vo.setNotice_end_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_end_ymd(),""),"-"));
			//긴급게시
	        if(form.getUrgency_yn() == null || "".equals(form.getUrgency_yn().trim())) {
	        	vo.setUrgency_yn("N");
	        } else {
	        	vo.setUrgency_yn("Y");
	        }
	        
	        /*********************************************************
			 * 게시글 수정
			**********************************************************/
	        int resultUpd = bbsService.modifyBBSDetail(vo);
			
	        /*********************************************************
			 * 메시지
			**********************************************************/
	        // 메시지처리 - 수정되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
            
	    	/*********************************************************
			 * 게시글 상세조회 컨트롤러 호출
			**********************************************************/
	        ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
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
	 * 게시글 삭제.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView deleteBBSDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSList.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);

			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
	        
	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
	        List listBBSDetailPage = new ArrayList();  // 목록리스트
	        String returnMessage   = "";
			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
			String doSearch = StringUtil.bvl(form.getDoSearch(), (String)request.getAttribute("doSearch"));
			if ("Y".equals(doSearch)) {

	        	// 현재페이지를 set
	            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
	
		        /*********************************************************
				 * 파라미터세팅
				**********************************************************/
		        vo.setSys_cd(sysCd);
		        vo.setStart_index(pageUtil.getStartIndex());     //페이지시작
	    		vo.setEnd_index(pageUtil.getEndIndex());         //페이지마지막
	    		vo.setSrch_start_notice_ymd(StringUtil.replace(vo.getSrch_start_notice_ymd(),"-", ""));
	    		vo.setSrch_end_notice_ymd(StringUtil.replace(vo.getSrch_end_notice_ymd(),"-", ""));
	    		
		        /*********************************************************
				 * 게시글 삭제
				**********************************************************/
		        bbsService.deleteBBSDetail(vo);
	    		
		        /*********************************************************
				 * 게시글목록조회
				**********************************************************/
	    		listBBSDetailPage = bbsService.listBBSDetailPage(vo);           
	    		 
	            if(listBBSDetailPage != null && listBBSDetailPage.size() > 0) {
	            	ListOrderedMap lm = (ListOrderedMap)listBBSDetailPage.get(0);
	            	
	                pageUtil.setTotalRow(lm.get("total_cnt"));
	                pageUtil.setRowPerPage(10);
	                pageUtil.setGroup();
	                //result.top();
	                
	                // 메시지처리 - 삭제되었습니다.
	    	    	returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
	    	    	
	            }   

	        } else {

				// 페이지 초기화
	        	form.setCurPage("1");
	                            
	            pageUtil.setThisPage(0);
	            pageUtil.setTotalRow(0);
	            pageUtil.setThisPage(0);

	        }
	        
            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);
            
            mav.addObject("resultList", listBBSDetailPage);
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
	 * 게시판 상세조회.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView detailBBSDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시상세 조회 페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSDetail.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
	        vo.setUser_id(userId);
	        
	        String noticeId = StringUtil.bvl((String)request.getAttribute("noticeId"), "");	        
	        if(form.getNotice_id()==null&&noticeId!=null){
	        	form.setNotice_id(noticeId);
	        	vo.setNotice_id(noticeId);
	        }
	        
			/*********************************************************
			 * 게시글 상세조회
			**********************************************************/
	        HashMap result = bbsService.detailBBSDetail(vo);
	        
			ArrayList getBBSDetail     = (ArrayList)result.get("getBBSDetail");
			String    listBBSFile      = (String)result.get("listBBSFile");
			ArrayList listBBSAppend    = (ArrayList)result.get("listBBSAppend");
			ArrayList getBBSPrevNextID = (ArrayList)result.get("getBBSPrevNextID");
			String    getPrevTitle     = (String)result.get("getPrevTitle");
			String    getNextTitle     = (String)result.get("getNextTitle");		
			String getBBSRecommendYn   = (String)result.get("getBBSRecommendYn");

	        /*********************************************************
			 * 게시판 옵션조회
			**********************************************************/
			ListOrderedMap bbsOption = null; //BBS 옵션

			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());

			bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);        

	        // 메시지처리 - 정상적으로 조회되었습니다.
	    	//String returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());

			String returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
			
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName(forwardURL);
	        
	        mav.addObject("getBBSDetail", getBBSDetail);
	        mav.addObject("listBBSFile", URLDecoder.decode(listBBSFile));
	        mav.addObject("listBBSAppend", listBBSAppend);
	        mav.addObject("getBBSPrevNextID", getBBSPrevNextID);
	        mav.addObject("getPrevTitle", getPrevTitle);
	        mav.addObject("getNextTitle", getNextTitle);
	        mav.addObject("getBBSRecommendYn", getBBSRecommendYn);
	        mav.addObject("bbsOption", bbsOption);
	
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
	 * 게시판 댓글등록하기 위한 조회.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView getBBSDetailForIns(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시물 등록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSInsert.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		
			/*********************************************************
			 * 파라미터 세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
			
			/*********************************************************
			 * 게시판 옵션값 조회
			**********************************************************/
	        ListOrderedMap bbsOption = null; //BBS 옵션

			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());

			bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);        

			/*********************************************************
			 * 게시글 상세조회(댓글 등록하기 위한)
			**********************************************************/
			List detailBBSDetail = bbsService.getBBSDetailForReply(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("bbsOption", bbsOption);
			mav.addObject("detailBBSDetail", detailBBSDetail);
			mav.addObject("isReply", "Y"); //댓글인지 여부
			
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
	 * 게시판 댓글등록.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView insertBBSDetailReply(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        // 게시상세 조회 페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/bbs/BBSDetail.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setReg_id(userId);
	        vo.setNotice_start_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_start_ymd(),""),"-"));
			vo.setNotice_end_ymd(StringUtil.removeChar(StringUtil.bvl(form.getNotice_end_ymd(),""),"-"));
			//긴급게시
	        if(form.getUrgency_yn() == null || "".equals(form.getUrgency_yn().trim())) {
	        	vo.setUrgency_yn("N");
	        } else {
	        	vo.setUrgency_yn("Y");
	        }
	        /*********************************************************
			 * 댓글등록
			**********************************************************/
	        String noticeId = bbsService.insertBBSDetailReply(vo);

	        /*********************************************************
			 * 게시글 상세조회
			**********************************************************/
	        vo.setNotice_id(noticeId);
	        vo.setRef_cnt_apply_yn("N"); //조회카운트 증가시키지 않음
	        
	        HashMap result = bbsService.detailBBSDetail(vo);
	        
			ArrayList getBBSDetail     = (ArrayList)result.get("getBBSDetail");
			String    listBBSFile      = (String)result.get("listBBSFile");
			ArrayList listBBSAppend    = (ArrayList)result.get("listBBSAppend");
			ArrayList getBBSPrevNextID = (ArrayList)result.get("getBBSPrevNextID");
			String    getPrevTitle     = (String)result.get("getPrevTitle");
			String    getNextTitle     = (String)result.get("getNextTitle");		
			String getBBSRecommendYn   = (String)result.get("getBBSRecommendYn");

	        /*********************************************************
			 * 게시판 옵션조회
			**********************************************************/
			ListOrderedMap bbsOption = null; //BBS 옵션

			HashMap hm = new HashMap();
			hm.put("sys_cd", sysCd);
			hm.put("bbs_id", form.getBbs_id());

			bbsOption = (ListOrderedMap)bbsMngService.getBBSMaster(hm);        
			
	        // 메시지처리 - 등록되었습니다.
	    	String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
	
	    	ModelAndView mav = new ModelAndView(); //상세페이지 이동
	    	mav.setViewName(forwardURL);
			
	        mav.addObject("getBBSDetail", getBBSDetail);
	        mav.addObject("listBBSFile", URLDecoder.decode(listBBSFile));
	        mav.addObject("listBBSAppend", listBBSAppend);
	        mav.addObject("getBBSPrevNextID", getBBSPrevNextID);
	        mav.addObject("getPrevTitle", getPrevTitle);
	        mav.addObject("getNextTitle", getNextTitle);
	        mav.addObject("getBBSRecommendYn", getBBSRecommendYn);
	        mav.addObject("bbsOption", bbsOption);
	        	
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
	 * 덧글등록.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView insertBBSAppend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	        // 게시상세 조회 Control로 Forwading
	        String forwardURL = "/secfw/bbs.do?method=detailBBSDetail";
	        
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setReg_id(userId);
	        /*********************************************************
			 * 덧글등록
			**********************************************************/
	        int result1 = bbsService.insertBBSAppend(vo);

	        /*********************************************************
			 * 메시지
			**********************************************************/
	        String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
	    	
	        /*********************************************************
			 * 게시글 상세조회 컨트롤러로 이동
			**********************************************************/
	        ModelAndView mav = new ModelAndView(); //상세페이지 이동
	        mav.setViewName(forwardURL);
	        mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
	
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
	 * 덧글삭제.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView deleteBBSAppend(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
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
	        // 게시상세 조회 Control로 Forwading
	        String forwardURL = "/secfw/bbs.do?method=detailBBSDetail";
	        
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        
	        /*********************************************************
			 * 덧글삭제
			**********************************************************/
	        int result1 = bbsService.deleteBBSAppend(vo);

	        /*********************************************************
			 * 메시지
			**********************************************************/
	        String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
	        
	        /*********************************************************
			 * 상세조회 컨트롤러 호출
			**********************************************************/
	    	ModelAndView mav = new ModelAndView(); //상세페이지 이동
	        mav.setViewName(forwardURL);
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
	 * 추천등록
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView insertBBSRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
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
	        // 게시상세 조회 Control로 Forwading
	        String forwardURL = "/secfw/bbs.do?method=detailBBSDetail";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
		        
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setRecommend_user_id(userId);
	        vo.setRecommend_yn("Y");

	        /*********************************************************
			 * 추천등록
			**********************************************************/
	        int recommendResult = bbsService.insertBBSRecommend(vo);

         	String returnMessage = "";
	    	
	    	if(recommendResult == 0) {
		        // 메시지처리 - 추천하신 내용입니다.
		    	returnMessage = messageSource.getMessage("secfw.msg.succ.recommendDup", null, new RequestContext(request).getLocale());
	    	} else {
		        // 메시지처리 - 추천되었습니다.
		    	returnMessage = messageSource.getMessage("secfw.msg.succ.recommend", null, new RequestContext(request).getLocale());
	    	}	    	
	
	        /*********************************************************
			 * 상세조회 컨트롤러 호출
			**********************************************************/
	    	ModelAndView mav = new ModelAndView(); //상세페이지 이동
	    	mav.setViewName(forwardURL);
	    	
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
	 * 미리보기/인쇄 팝업페이지 이동.
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView listDetailPop(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        BBSForm form = new BBSForm();
	        BBSVO   vo   = new BBSVO();
			bind(request, form);
			bind(request, vo);
	        
			/*********************************************************
			 * 게시글 상세조회
			**********************************************************/
	        HashMap result = bbsService.detailBBSDetail(vo);
	        
			ArrayList getBBSDetail     = (ArrayList)result.get("getBBSDetail");
			String    listBBSFile      = (String)result.get("listBBSFile");
			ArrayList listBBSAppend    = (ArrayList)result.get("listBBSAppend");
			ArrayList getBBSPrevNextID = (ArrayList)result.get("getBBSPrevNextID");
			String    getPrevTitle     = (String)result.get("getPrevTitle");
			String    getNextTitle     = (String)result.get("getNextTitle");		
			String getBBSRecommendYn   = (String)result.get("getBBSRecommendYn");
	
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("/WEB-INF/jsp/secfw/bbs/BBSDetailPop.jsp");
	
	        mav.addObject("getBBSDetail", getBBSDetail);
	        mav.addObject("listBBSFile", URLDecoder.decode(listBBSFile));
	        mav.addObject("listBBSAppend", listBBSAppend);
	        mav.addObject("getBBSPrevNextID", getBBSPrevNextID);
	        mav.addObject("getPrevTitle", getPrevTitle);
	        mav.addObject("getNextTitle", getNextTitle);
	        mav.addObject("getBBSRecommendYn", getBBSRecommendYn);
	
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
}
