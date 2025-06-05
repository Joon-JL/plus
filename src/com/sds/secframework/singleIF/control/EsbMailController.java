package com.sds.secframework.singleIF.control;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.outldap.samsung.net.Employee;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailForm;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.service.UserService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.review.dto.ConsultationForm;
import com.sec.clm.review.dto.ConsultationVO;
import com.sec.clm.review.service.ConsiderationService;
import com.sec.common.util.ClmsDataUtil;

public class EsbMailController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbMailService esbMailService;
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}

	/**
	 * ComUtilService 선언 및 세팅
	 */
	private ComUtilService comUtilService;
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private ConsiderationService considerationService;
	public void setConsiderationService(ConsiderationService considerationService) { 
		this.considerationService = considerationService;
	}

	/**
	 * ComUtilService 선언 및 세팅
	 */
	private MailSendService mailSendService;
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * ESB 메일전송
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void sendMail(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        HttpSession session = request.getSession(false);
	        String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        /*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MailVO vo = new MailVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = sysCd;
			String misId = EsbUtil.generateMisId("MAIL");
			
			vo.setModule_id(moduleId);
			vo.setMis_id(misId);
			
			//보내는 사람
			vo.setSender_mail_addr("selmsplus@samsung.com");
			vo.setSender_single_id("selmsplus");
			
			//로케일, 인코딩 설정, Time Zone
			vo.setLocale(((String)session.getAttribute("secfw.session.locale")).substring(0, ((String)session.getAttribute("secfw.session.locale")).indexOf(".")));
			vo.setEncoding(StringUtil.bvl(vo.getEncoding(),"utf-8"));
			vo.setTime_zone(StringUtil.bvl((String)session.getAttribute("secfw.session.timezone"),"GMT+0"));
			
			String summerTimeYn = (String)session.getAttribute("secfw.session.summertimeyn");
			if("Y".equals(summerTimeYn)) {
				vo.setIs_dst("true");
			} else {
				vo.setIs_dst("false");
			}

			//STATUS 값 설정 - Default "0"
			vo.setStatus("0");

			//받는 사람
			String[] receivers = request.getParameterValues("receivers");

			String[] iseq_ids  = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			if(receivers!=null && receivers.length>0) {
				
				iseq_ids  = new String[receivers.length];
				rec_types = new String[receivers.length];
				rec_addrs = new String[receivers.length];
				
				for(int i=0; i<receivers.length; i++){
					
					String receiver[] = StringUtil.token(receivers[i], "|");

					iseq_ids[i]  = String.valueOf(i+1);
					rec_types[i] = receiver[0];
					rec_addrs[i] = receiver[1];
					
				}
			}
			
			vo.setIseq_ids(iseq_ids);
			vo.setRec_types(rec_types);
			vo.setRec_addrs(rec_addrs);

			//첨부파일 나모저장
	        /**********************************************
	         * 첨부파일 저장 시작
	         **********************************************/
			
			//BODY Type
			String bodyType = vo.getBody_type();
			
			if("1".equals(bodyType)) { //본문형식이 HTML이면
				
				//BODY TYPE 세팅
				vo.setBhtml_content_check("true");
				
				// 나모 Text & 첨부파일 처리
				String decodeText = vo.getBody_mime();
				
		        HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		        String contentHtml = (String)hm.get("CONTENT");
		        
		        //contentHtml.
		        
		        contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		        contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
		        contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
		        
		        // Multipart Form (첨부파일이 있으면)
		        if(hm.get("TYPE").equals("M")) {

		            ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
		        	for(int i=0; i<fileList.size();i++) {
		            	HashMap fileMap = (HashMap)fileList.get(i);
		            	
		            	Integer seqNo  = new Integer(i);
		            	String fileNm  = (String)fileMap.get("FILE_NM");
		            	String filePth = (String)fileMap.get("FILE_PTH");
		            	String fileUrl = (String)fileMap.get("FILE_URL");
		        	    
		            	File f = new File(filePth);
		            	Long fileSize = new Long(f.length());
		            	
		        	}        	
		           vo.setBody(contentHtml);
		        } else {
		          vo.setBody(contentHtml);
		        }	
		        
			} else { //본문형식이  TEXT
				//BODY TYPE 세팅
				vo.setBhtml_content_check("false");
			}
	        
			/*********************************************************
			 * 메일 내역 등록
			**********************************************************/
			esbMailService.insertMail(vo);		

			/*********************************************************
			 * 메일전송
			**********************************************************/
			esbMailService.sendMail(moduleId, misId);
			
    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.sendMail",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
			
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
	 * ESB 메일전송22 [HTML 전용]-CrossEdtitor !
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void sendMail2(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        HttpSession session = request.getSession(false);
	        String sysCd = (String)session.getAttribute("secfw.session.sys_cd");
	        String topRole = "";		// 사용자 Role

			HashMap<String, String> roleListMap = new HashMap<String, String>();
			ListOrderedMap roleListLom = null;
			List<?> roleList = userService.listUserRole(roleListMap);
			ArrayList<String> userRoleList = new ArrayList<String>();

			String localeStr = StringUtil.bvl((session.getValue("secfw.session.locale").toString()).trim(),propertyService.getProperty("secfw.defaultLocale"));
			Locale locale = new Locale(localeStr);
			
			/*********************************************************
			 * Form 및 VO Binding
			 * 계약정보 가져오기 신성우 추가
			 * Notification 메일 내역이 1건일 경우, 해당메일의 Detail 내용을 가져온다.
			 * 여러건일 경우, 메일 수신자 리스트와 Editor 호출
			**********************************************************/
			MailVO vo = new MailVO();
	        //Detail data 호출 위해 추가 신성우 2014/02/10
	        ConsultationVO conVO   = new ConsultationVO();
			ListOrderedMap lomRq = new ListOrderedMap();
			bind(request, vo);
			bind(request, conVO);
			COMUtil.getUserAuditInfo(request, vo);	
			COMUtil.getUserAuditInfo(request, conVO);		
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = sysCd;
			String misId = EsbUtil.generateMisId("MAIL");
			
			vo.setModule_id(moduleId);
			vo.setMis_id(misId);
			
			//보내는 사람
			vo.setSender_mail_addr("selmsplus@samsung.com");
			vo.setSender_single_id("selmsplus");
			
			//로케일, 인코딩 설정, Time Zone
			vo.setLocale(((String)session.getAttribute("secfw.session.locale")).substring(0, ((String)session.getAttribute("secfw.session.locale")).indexOf(".")));
			vo.setEncoding(StringUtil.bvl(vo.getEncoding(),"utf-8"));
			vo.setTime_zone(StringUtil.bvl((String)session.getAttribute("secfw.session.timezone"),"GMT+0"));
			
			String summerTimeYn = (String)session.getAttribute("secfw.session.summertimeyn");
			if("Y".equals(summerTimeYn)) {
				vo.setIs_dst("true");
			} else {
				vo.setIs_dst("false");
			}

			//STATUS 값 설정 - Default "0"
			vo.setStatus("0");

			//받는 사람
			String[] receivers = request.getParameterValues("receivers");

			String[] iseq_ids  = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			if(receivers!=null && receivers.length>0) {
				
				iseq_ids  = new String[receivers.length];
				rec_types = new String[receivers.length];
				rec_addrs = new String[receivers.length];
				
				for(int i=0; i<receivers.length; i++){
					
					String receiver[] = StringUtil.token(receivers[i], "|");

					iseq_ids[i]  = String.valueOf(i+1);
					rec_types[i] = receiver[0];
					rec_addrs[i] = receiver[1];
					
				}
			}
			
			vo.setIseq_ids(iseq_ids);
			vo.setRec_types(rec_types);
			vo.setRec_addrs(rec_addrs);

			
			roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
			roleListMap.put("user_id", StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));

			
			if(roleList != null && roleList.size() > 0){
			    for(int idx = 0; idx < roleList.size(); idx++){
			    	roleListLom = (ListOrderedMap)roleList.get(idx);
			        userRoleList.add((String)roleListLom.get("role_cd"));
			    }
			}
			
			
			if(userRoleList.contains("RA00")){
				topRole = "RA00";
			}else if(userRoleList.contains("RA01")){
				topRole = "RA01";
			}else if(userRoleList.contains("RA02")){
				topRole = "RA02";
			}

			//VO parameter binding
			//소속조직
			conVO.setTop_role(topRole);
			conVO.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
			conVO.setPage_flag("1");
			conVO.setStat_flag(StringUtil.bvl((String)request.getParameter("stat_flag"),"")); 	//좌측메뉴에서 받은 진행상태 플래그
			conVO.setDmstfrgn(StringUtil.bvl((String)request.getParameter("dmstfrgn"),""));	//국내(H), 해외(F)
			conVO.setGbn_last(StringUtil.bvl(conVO.getGbn_last(),"")); 	//최종본 회신 구분
			conVO.setUser_id(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
			
			conVO.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));

			List<?> listDc = considerationService.detailConsideration(conVO);
			if(listDc != null && listDc.size() > 0){
				lomRq= (ListOrderedMap)listDc.get(0);
			}else{
				lomRq = new ListOrderedMap();
			}
			
			lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
			lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
			lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));

			conVO.setCnsdreq_id(StringUtil.bvl((String)lomRq.get("cnsdreq_id"),""));
			conVO.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
			
			lomRq.put("apbt_memo_dp", StringUtil.convertEnterToBR((String)lomRq.get("apbt_memo")));	// 승인자_메모
			
			conVO.setCntrt_oppnt_nm(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_nm"),""));
			
			// 개행문자를 <BR> 로 전환
			lomRq.put("cnsd_demnd_cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("pshdbkgrnd_purps"),"")));
			
			lomRq.put("plndbn_chge_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("plndbn_chge_cont"),"")));
			lomRq.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cntrt_untprc_expl"),"")));
			
			
			// auto_apbt_yn 
			lomRq.put("auto_apbt_yn", StringUtil.bvl((String)lomRq.get("auto_apbt_yn"),"Y"));

			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 

			conVO.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
			conVO.setCnclsnpurps_bigclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_bigclsfcn"),""));
			conVO.setCnclsnpurps_midclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_midclsfcn"),""));
			
			ListOrderedMap mapRm = null;
			
			if(listDc != null && listDc.size() > 0){
				String[] relMan = new String[listDc.size()];
				for(int i=0; i<listDc.size(); i++) {
					mapRm = (ListOrderedMap)listDc.get(i);
					relMan[i] = (String)mapRm.get("cntrt_id");
				}
				
				conVO.setMaster_cntrt_ids(relMan);
			}
			

			//====================================
			//검토의견
			//====================================
			// 2012.03.02 기대효과, 지불/수분조건, 기타주요사항 개행문자 출력 수정 modified by hanjihoon
			// 기대효과
			lomRq.put("antcptnefct_dp", StringUtil.convertEnterToBR((String)lomRq.get("antcptnefct")));
			// 지불/수분조건
			lomRq.put("payment_cond_dp", StringUtil.convertEnterToBR((String)lomRq.get("payment_cond")));
			// 기타주요사항
			lomRq.put("etc_main_cont_dp", StringUtil.convertEnterToBR((String)lomRq.get("etc_main_cont")));
			
			// 책임한도
			lomRq.put("oblgt_lmt_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("oblgt_lmt"),"")));				
			// Special Condition
			lomRq.put("spcl_cond_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));
			
			// 준거법 상세
			lomRq.put("loac_etc_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
			// 분쟁해결방법상세 
			lomRq.put("dspt_resolt_mthd_det_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
			// 비밀유지기간
			lomRq.put("secret_keepperiod_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("secret_keepperiod"),"")));
			
			// 준거법 초기값 설정
			lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"")));
			
			conVO.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			conVO.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));


			conVO.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
			conVO.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
			conVO.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));

			//첨부파일 나모저장
	        /**********************************************
	         * 첨부파일 저장 시작
	         **********************************************/
			
			//BODY Type
			String bodyType = vo.getBody_type();
			if("1".equals(bodyType)) { //본문형식이 HTML이면				
				//BODY TYPE 세팅
				vo.setBhtml_content_check("true");							
		       
			}else{				
				//MailSendService 추가 신성우 2014-02-05
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("mail_type", "24"); // mail type
				hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale));
				hm.put("param1", lomRq.get("req_title"));		//agreeTitletype
				hm.put("param2", vo.getBody());//Contents
								
				vo.setBody(mailSendService.getMailContent(hm));
			}
	        
			/*********************************************************
			 * 메일 내역 등록
			**********************************************************/
			vo.setLocale("en");
			vo.setEncoding("utf-8");
			esbMailService.insertMail(vo);		

			/*********************************************************
			 * 메일전송
			**********************************************************/
			esbMailService.sendMail(moduleId, misId);
			
    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.sendMail",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
			ModelAndView mav = new ModelAndView();
			mav.addObject("lomRq"		, lomRq);				// 검토의뢰
		} catch (Exception e) {
			
			e.printStackTrace();
			
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
	 * ESB 메일발신취소
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cancelMail(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MailVO vo = new MailVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			String moduleId = vo.getModule_id();
			String misId = vo.getMis_id();
			String passwd = vo.getPassword();
			
			/*********************************************************
			 * 메일발송취소
			**********************************************************/
			boolean cancelResult = esbMailService.cancelMail(moduleId, misId, passwd);		
			String result = "";

			if(cancelResult) {
				result = "T"; //발신취소 성공
			} else {
				result = "F"; //발신취소 실패
			}
			
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", result);
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
			
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
	 * (첨부파일 표시).
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView forwardMail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			HttpSession session = request.getSession(false);
	        //-----------BIND:바인드설정 -------------------- 
	        CommonForm form = new CommonForm();
	        bind(request, form);
	        
	        /*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/secfw/singleIF/MailInsertAS.jsp";
	        
			/*********************************************************
			 * Parameter Setting
			**********************************************************/
	        String mailTitle         = StringUtil.bvl((String)request.getParameter("mail_title"), "");
	        String mailContent       = StringUtil.bvl((String)request.getParameter("mail_content"), "");
	        String mailHtmlGbn       = StringUtil.bvl((String)request.getParameter("mail_html_gbn"), "");
	        String mailFileInfos     = StringUtil.bvl((String)request.getParameter("mail_fileInfos"), "");
	        
	        if("".equals(mailTitle)) {
		        mailTitle		= StringUtil.bvl((String)request.getAttribute("mail_title"), "");
		        mailContent		= StringUtil.bvl((String)request.getAttribute("mail_content"), "");
		        mailHtmlGbn		= StringUtil.bvl((String)request.getAttribute("mail_html_gbn"), "");
		        mailFileInfos	= StringUtil.bvl((String)request.getAttribute("mail_fileInfos"), "");
	        }
	        
	        //로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"),"en_US.UTF-8");
			String mailEncoding  = sessionLocale.substring(sessionLocale.indexOf(".")+1);
			    
			 /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("mailTitle"            , mailTitle);
			mav.addObject("mailContent"          , mailContent);
			mav.addObject("mailHtmlGbn"          , mailHtmlGbn);
			mav.addObject("mailFileInfos"        , mailFileInfos);
			mav.addObject("mailEncoding"         , mailEncoding);
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
	 * mail 재전송 리스트
	 */
	public ModelAndView listMailRe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
	        /*********************************************************                                                                          
			 * Forwarding URL                                                                                                                   
			**********************************************************/
	        String forwardURL = "/jsp/adm/mailre/MailReList.jsp";
	        
	        /*********************************************************                                                                          
			 * Form 및 VO Binding                                                                                                               
			**********************************************************/
	        MailForm form = new MailForm();
	        MailVO vo = new MailVO();
	        
	        bind(request, form);
	        bind(request, vo);
	        
	        COMUtil.getUserAuditInfo(request, form);                                                                                              
	        COMUtil.getUserAuditInfo(request, vo);
	        
	        /*********************************************************                                                                          
			 * 페이지 객체                                                                                                                      
			**********************************************************/                                                                         
			PageUtil pageUtil = new PageUtil();                                                                                                 
	                                                                                                                                            
	        // 현재페이지를 set                                                                                                             
            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
            
            /*********************************************************                                                                      
			 * 파라미터세팅                                                                                                                 
			**********************************************************/                                                                     
            vo.setStart_index(pageUtil.getStartIndex());     //페이지시작                                                                   
    		vo.setEnd_index(pageUtil.getEndIndex());         //페이지마지막
    		
    		/*********************************************************                                                                      
			 * 목록조회                                                                                                                     
			**********************************************************/                                                                     
			List<?> resultList = esbMailService.listMail(vo);
			
			if(resultList != null && resultList.size() > 0) {                                                                               
            	ListOrderedMap lm = (ListOrderedMap)resultList.get(0);                                                                      
            	                                                                                                                            
                pageUtil.setTotalRow(lm.get("total_cnt"));                                                        
                pageUtil.setRowPerPage(10);                                                                                                 
                pageUtil.setGroup();                                                                                                                        
            }
    		
    		ModelAndView mav = new ModelAndView();
    		mav.setViewName(forwardURL);
    		
    		mav.addObject("resultList", resultList);
    		mav.addObject("command", form);
    		mav.addObject("pageUtil", pageUtil);
    		
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
	 * 계약담당자 메일발송
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public ModelAndView ContrforwardMail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			HttpSession session = request.getSession(false);
	        String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        String userId = (String)session.getAttribute("secfw.session.user_id");
	        String auth_CompCd = (String)session.getAttribute("secfw.session.comp_cd");
	        String compCd = (String)session.getAttribute("secfw.session.comp_cd");

			String topRole		= "";		// 사용자 Role
	        /*********************************************************
			 * Form 및 VO Binding
			 * 계약정보 가져오기 신성우 추가
			 * Notification 메일 내역이 1건일 경우, 해당메일의 Detail 내용을 가져온다.
			 * 여러건일 경우, 메일 수신자 리스트와 Editor 호출
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
	        MailVO vo = new MailVO();
	        //Detail 계약정보 조회용 VO 추가 신성우
	        ConsultationVO conVO   = new ConsultationVO();
	        bind(request, form);
			bind(request, vo);
			bind(request, conVO);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, conVO);

			HashMap 	   roleListMap = new HashMap();
			ListOrderedMap roleListLom = null;
			
			// 계약 정보
			ListOrderedMap lomRq = null;

			// 관련자 List
			ArrayList lomRm = null;
			ListOrderedMap mapRm = null;
			
			//Iseq_ids check box 카운트
			if(vo.getIseq_ids().length == 1){
				roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
				roleListMap.put("user_id", StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
				
				List 	  roleList 	   = userService.listUserRole(roleListMap);
				ArrayList userRoleList = new ArrayList();
				
				if(roleList != null && roleList.size() > 0){
				    for(int idx = 0; idx < roleList.size(); idx++){
				    	roleListLom = (ListOrderedMap)roleList.get(idx);
				        userRoleList.add((String)roleListLom.get("role_cd"));
				    }
				}
				
				if(userRoleList.contains("RA00")){
					topRole = "RA00";
				}else if(userRoleList.contains("RA01")){
					topRole = "RA01";
				}else if(userRoleList.contains("RA02")){
					topRole = "RA02";
				}
				
				//소속조직
				form.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
				
				//권한직위
				form.setTop_role(topRole);
				form.setStat_flag(conVO.getStat_flag());
				form.setDmstfrgn(conVO.getDmstfrgn());
				
				form.setPage_flag("1");
	
				form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
				
				//VO parameter binding
				//소속조직
				conVO.setTop_role(topRole);
				conVO.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
				conVO.setPage_flag("1");
				conVO.setStat_flag(StringUtil.bvl((String)request.getParameter("stat_flag"),"")); 	//좌측메뉴에서 받은 진행상태 플래그
				conVO.setDmstfrgn(StringUtil.bvl((String)request.getParameter("dmstfrgn"),""));	//국내(H), 해외(F)
				conVO.setGbn_last(StringUtil.bvl(conVO.getGbn_last(),"")); 	//최종본 회신 구분
				conVO.setUser_id(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
				
				conVO.setBlngt_orgnz((String)session.getAttribute("secfw.session.blngt_orgnz"));
	
				List listDc = considerationService.detailConsideration(conVO);
				if(listDc != null && listDc.size() > 0){
					lomRq= (ListOrderedMap)listDc.get(0);
				}else{
					lomRq = new ListOrderedMap();
				}
				
				lomRq.put("vc_cnsd_demnd_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("vc_cnsd_demnd_cont"),"")));
				lomRq.put("req_demnd_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("req_demnd_cause"),"")));
				lomRq.put("res_hndl_cause_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("res_hndl_cause"),"")));
	
				conVO.setCnsdreq_id(StringUtil.bvl((String)lomRq.get("cnsdreq_id"),""));
				conVO.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
				
				lomRq.put("apbt_memo_dp", StringUtil.convertEnterToBR((String)lomRq.get("apbt_memo")));	// 승인자_메모
				
				conVO.setCntrt_oppnt_nm(StringUtil.bvl((String)lomRq.get("cntrt_oppnt_nm"),""));
				
				// 개행문자를 <BR> 로 전환
				lomRq.put("cnsd_demnd_cont", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"),"")));
				lomRq.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("pshdbkgrnd_purps"),"")));
				
				lomRq.put("plndbn_chge_cont_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("plndbn_chge_cont"),"")));
				lomRq.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("cntrt_untprc_expl"),"")));
				
				
				// auto_apbt_yn 
				lomRq.put("auto_apbt_yn", StringUtil.bvl((String)lomRq.get("auto_apbt_yn"),"Y"));
	
				//cntrtreq_id 계약 id 값 추가 신성우
				lomRq.put("cntrtreq_id", (String)conVO.getCntrt_id());
				
				//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
	
				conVO.setCntrt_id(StringUtil.bvl((String)lomRq.get("cntrt_id"),""));
				conVO.setCnclsnpurps_bigclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_bigclsfcn"),""));
				conVO.setCnclsnpurps_midclsfcn(StringUtil.bvl((String)lomRq.get("cnclsnpurps_midclsfcn"),""));
				
				// 관련자 List
				
				if(listDc != null && listDc.size() > 0){
					String[] relMan = new String[listDc.size()];
					String cntrtInfoGbn = "";
					
					for(int i=0; i<listDc.size(); i++) {
						mapRm = (ListOrderedMap)listDc.get(i);
						relMan[i] = (String)mapRm.get("cntrt_id");
						cntrtInfoGbn = (String)mapRm.get("cntrt_info_gbn");
					}
					
					conVO.setMaster_cntrt_ids(relMan);
					lomRm = (ArrayList) considerationService.listRelationman(conVO);													// 관련자 List
				}
				
	
				//====================================
				//검토의견
				//====================================
				// 2012.03.02 기대효과, 지불/수분조건, 기타주요사항 개행문자 출력 수정 modified by hanjihoon
				// 기대효과
				lomRq.put("antcptnefct_dp", StringUtil.convertEnterToBR((String)lomRq.get("antcptnefct")));
				// 지불/수분조건
				lomRq.put("payment_cond_dp", StringUtil.convertEnterToBR((String)lomRq.get("payment_cond")));
				// 기타주요사항
				lomRq.put("etc_main_cont_dp", StringUtil.convertEnterToBR((String)lomRq.get("etc_main_cont")));
				
				// 책임한도
				lomRq.put("oblgt_lmt_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("oblgt_lmt"),"")));				
				// Special Condition
				lomRq.put("spcl_cond_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("spcl_cond"),"")));
				
				// 준거법 상세
				lomRq.put("loac_etc_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac_etc"),"")));
				// 분쟁해결방법상세 
				lomRq.put("dspt_resolt_mthd_det_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("dspt_resolt_mthd_det"),"")));
				// 비밀유지기간
				lomRq.put("secret_keepperiod_dp", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("secret_keepperiod"),"")));
				
				// 준거법 초기값 설정
				lomRq.put("loac", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq.get("loac"),"")));
				
				conVO.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
				conVO.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
	
	
				conVO.setCnsdreq_id((String)lomRq.get("cnsdreq_id"));
				conVO.setMn_cnsd_dept((String)lomRq.get("mn_cnsd_dept"));
				conVO.setVc_cnsd_dept((String)lomRq.get("vc_cnsd_dept"));
			}else{
				lomRq = new ListOrderedMap();
				lomRm = new ArrayList<ArrayList>();
			}
	        /*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/secfw/singleIF/ContrMailInsert.jsp";
	        
			/*********************************************************
			 * Parameter Setting
			**********************************************************/
	        String mailTitle         = StringUtil.bvl((String)request.getParameter("mail_title"), "");
	        String mailContent       = StringUtil.bvl((String)request.getParameter("mail_content"), "");
	        String mailHtmlGbn       = StringUtil.bvl((String)request.getParameter("mail_html_gbn"), "");
	        String mailFileInfos     = StringUtil.bvl((String)request.getParameter("mail_fileInfos"), "");
	        
	        if("".equals(mailTitle)) {
		        mailTitle		= StringUtil.bvl((String)request.getAttribute("mail_title"), "");
		        mailContent		= StringUtil.bvl((String)request.getAttribute("mail_content"), "");
		        mailHtmlGbn		= StringUtil.bvl((String)request.getAttribute("mail_html_gbn"), "");
		        mailFileInfos	= StringUtil.bvl((String)request.getAttribute("mail_fileInfos"), "");
	        }
	        
	        //로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"),"en_US.UTF-8");
			String mailEncoding  = sessionLocale.substring(sessionLocale.indexOf(".")+1);

			/*********************************************************
			 * 메일 수신자 목록가져오기 
			**********************************************************/
			
			// 2012-09-20 김형준 수정
			//메일 수신자 ESB정보 조회를 건별이 아닌 배열 자체로 조회하도록 수정(퍼포먼스 향상을 위해)
			String[] checkedVal = vo.getIseq_ids();
			String arr_rev_epid[] = new String[checkedVal.length];
			
			ArrayList list = new ArrayList();
			
			//중복 epid 제거
			for(int i=0; i < checkedVal.length; i++){
				String temp = checkedVal[i];
				String[] arr_temp = temp.split("`");
				
				if(!list.contains(arr_temp[1])){ //중복건 제거
					if(!"".equals(StringUtil.bvl(arr_temp[1].trim(),""))){ //공백건 제거 
						list.add(arr_temp[1]);
					}
				}
			}
			
			//중복제거된 epid
			String uniqueArray[] = new String[list.size()];
			list.toArray(uniqueArray); 
			
			for(int i=0;i < uniqueArray.length; i++)
			{
				
				ClmsDataUtil.debug("####1 :" + uniqueArray[i]);
			}
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			
			Employee[] employee = null;
			
    		// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25,  2013.10.09 comp_cd 삭제함(by 김재원)
			employee = esbOrgService.userSearchByEpidList(uniqueArray);
			
			Vector UserPathVector  = new Vector();
			
			if (employee != null) {
				ClmsDataUtil.debug("####2 : " + employee.length);
				
				for(int i=0; i < employee.length; i++){
					
					ClmsDataUtil.debug("##  " + employee[i].getUserid());
					ClmsDataUtil.debug("##  " + employee[i].getCn());
					ClmsDataUtil.debug("##  " + employee[i].getTitle());
					ClmsDataUtil.debug("##  " + employee[i].getDepartment());
					ClmsDataUtil.debug("##  " + employee[i].getEpenorganizationname());
					ClmsDataUtil.debug("##  " + employee[i].getMail());
					
					
					//값이 있을때만 put 한다.
					if(!"".equals(StringUtil.bvl(employee[i].getUserid(), ""))){
							
						Hashtable ht = new Hashtable();
						
						ht.put("userid", employee[i].getUserid());
						ht.put("cn", employee[i].getCn());
						ht.put("title", employee[i].getTitle());
						ht.put("department", employee[i].getDepartment());
						ht.put("o", employee[i].getEpenorganizationname());// getO() 한글회사명 대신 영문명
						ht.put("mail", employee[i].getMail());
						
						UserPathVector.add(ht);				
					}
				}
			}
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("mailTitle"            , mailTitle);
			mav.addObject("mailContent"          , mailContent);
			mav.addObject("mailHtmlGbn"          , mailHtmlGbn);
			mav.addObject("mailFileInfos"        , mailFileInfos);
			mav.addObject("mailEncoding"         , mailEncoding);
			mav.addObject("UserPathVector"       , UserPathVector);
			
			//Detail 가져오기 신성우 추가 
			mav.addObject("command", form);
			mav.addObject("lomRq"		, lomRq);				// 검토의뢰
			mav.addObject("lomRm"		, lomRm);				// 관련자 List
			
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
