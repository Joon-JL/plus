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
import javax.swing.text.html.HTML;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.util.ClmsDataUtil;

public class EsbApprovalController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalService esbApprovalService;
	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
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
	private MailSendService mailSendService;
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
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
	 * ESB 결재상신
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void submit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템코드 및 사용자아이디
			**********************************************************/
			HttpSession session = request.getSession(false);
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
			String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ApprovalVO vo = new ApprovalVO();
			MailVO mailVo = new MailVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);
			bind(request, mailVo);
			COMUtil.getUserAuditInfo(request, mailVo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/

			/** 1. 결재내역 정보 **/
			// 모듈아이디 및 MISID 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = "";
			String misId    = "";

			if ("".equals(StringUtil.bvl((String)request.getParameter("module_id"), ""))) {
				moduleId = sysCd;
			} else {
				moduleId = StringUtil.bvl((String)request.getParameter("module_id"), "");
			}

			if ("".equals(StringUtil.bvl((String)request.getParameter("mis_id"), ""))) {
				misId = EsbUtil.generateMisId("APPR");
			} else {
				misId = StringUtil.bvl((String)request.getParameter("mis_id"), "");
			}

			vo.setModule_id(moduleId);
			vo.setMis_id(misId);
			
			// 로케일, 인코딩, TIME_ZONE 설정
			String sessionLocale = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"), "en_US.UTF-8");
			String setEncoding   = vo.getEncoding();
			String locale_info   = sessionLocale.substring(0, sessionLocale.indexOf(".")) + "." + setEncoding;

			vo.setLocale_info(locale_info);
			vo.setTime_zone(StringUtil.bvl((String)session.getAttribute("EP_TIMEZONE"), "GMT+0"));

			// STATUS 값 설정 - Default "0"
			vo.setStatus("0");

			// BODY Type
			String bodyType = vo.getBody_type();

			// Approval Post Method
			vo.setMethod(StringUtil.bvl((String)request.getParameter("approvalPostProcess"), ""));

			//결재 타이틀
			mailVo.setSubject(vo.getTitle());
			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setLocale(vo.getSession_user_locale());
			String contentHtml = "";
			contentHtml += "<html xmlns='http://www.w3.org/1999/xhtml'>";
			contentHtml += vo.getBody_mime();
			contentHtml += "</html>";
			mailVo.setBody(contentHtml);
			
			// 결재상신 의견
			String opinion = StringUtil.bvl(vo.getOpinion(), "");
			// 예약상신 시간
			String reserved = StringUtil.bvl(vo.getReserved(),"");

			/** 2. 결재경로 정보 **/
			String[] approvalRoutes      = request.getParameterValues("receivers");
			String[] approvalRouteRights = request.getParameterValues("receiversRight");

			getLogger().debug("approvalRoutes : " + approvalRoutes);
			getLogger().debug("approvalRouteRights : " + approvalRouteRights);
			ClmsDataUtil.debug("##Route : " + approvalRoutes);
			ClmsDataUtil.debug("##Right : " + approvalRouteRights);

			String[] activitys    = null; // 설정구분
			String[] actionTypes  = null; // 처리구분
			//String[] mailAddresss = null; // 메일주소
			String[] userIds      = null; // EPID
			String[] userNms      = null;
			String[] jikgupCds    = null;
			String[] jikgupNms    = null;
			String[] deptCds      = null;
			String[] deptNms      = null;
			String[] compCds      = null;
			String[] compNms      = null;
			String[] grpCds       = null;
			String[] grpNms       = null;
			String[] mailAddress  = null;
			String[] routeModifys = null; // 경로변경 권한
			String[] bodyModifys  = null; // 본문수정 권한
			String[] arbitrarys   = null; // 전결 권한
			int approvalRoutesLength = approvalRoutes.length;

			//받는 사람 이메일 수신자 리스트 추가 신성우
			String[] iseq_ids  = null;
			String[] rec_types = null;
			
			iseq_ids  = new String[approvalRoutesLength];
			rec_types = new String[approvalRoutesLength];
			
			if (approvalRoutes != null && approvalRoutesLength > 0) {
				activitys    = new String[approvalRoutesLength];
				actionTypes  = new String[approvalRoutesLength];
				//mailAddresss = new String[approvalRoutesLength];
				userIds      = new String[approvalRoutesLength];
				userNms      = new String[approvalRoutesLength];
				jikgupCds    = new String[approvalRoutesLength];
				jikgupNms    = new String[approvalRoutesLength];
				deptCds      = new String[approvalRoutesLength];
				deptNms      = new String[approvalRoutesLength];
				grpCds       = new String[approvalRoutesLength];
				grpNms       = new String[approvalRoutesLength];
				mailAddress  = new String[approvalRoutesLength];
				routeModifys = new String[approvalRoutesLength];
				bodyModifys  = new String[approvalRoutesLength];
				arbitrarys   = new String[approvalRoutesLength];

				for (int i=0; i<approvalRoutesLength; i++) {
//					String approvalRoute[]      = StringUtil.token(approvalRoutes[i], "|");
//					String approvalRouteRight[] = StringUtil.token(approvalRouteRights[i], "|");
					String approvalRoute[] = approvalRoutes[i].split("\\|",15);
					String approvalRouteRight[] = StringUtil.split(approvalRouteRights[i], "\\|");
					
					activitys[i]   = approvalRoute[0];
					actionTypes[i] = "0";
					//mailAddresss[i] = approvalRoute[2];
					userIds[i]     = approvalRoute[1];
					userNms[i]     = approvalRoute[2];
					jikgupCds[i]   = approvalRoute[3];
					jikgupNms[i]   = approvalRoute[4];
					deptCds[i]     = approvalRoute[5];
					deptNms[i]     = approvalRoute[6];
					grpCds[i]      = approvalRoute[7];
					grpNms[i]      = approvalRoute[8];
					mailAddress[i] = approvalRoute[11];	//mail address 추가 신성우 2014-03-17

					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i]  = approvalRouteRight[2];
					arbitrarys[i]   = approvalRouteRight[3];

					//신성우 결재대상자 이메일 발송용 vo binding
					iseq_ids[i] = "1";
					rec_types[i] = "t";
				}
				
				//신성우 결재대상자 이메일 발송용 vo binding
				mailVo.setIseq_ids(iseq_ids);
				mailVo.setRec_types(rec_types);
				mailVo.setRec_addrs(mailAddress);
				
				//STATUS 값 설정 - Default "0"
				//mailVo.setStatus("1");
				// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.
				mailVo.setStatus("0");
			}

			vo.setActivitys(activitys);
			vo.setAction_types(actionTypes);
			//vo.setMail_addresss(mailAddresss);
			vo.setUser_ids(userIds);
			vo.setUser_names(userNms);
			vo.setDuty_codes(jikgupCds);
			vo.setDutys(jikgupNms);
			vo.setDept_codes(deptCds);
			vo.setDept_names(deptNms);
			vo.setGroup_codes(grpCds);
			vo.setGroup_names(grpNms);
			vo.setMail_addresss(mailAddress);

			vo.setRoute_modifys(routeModifys);
			vo.setBody_modifys(bodyModifys);
			vo.setArbitrarys(arbitrarys);

			// 결재상신 의견
			if (opinion != null) {
				String[] opinions = new String[approvalRoutesLength];

				for (int i=0; i<approvalRoutesLength; i++) {
					opinions[i] = "";
				}

				opinions[0] = opinion;
				vo.setOpinions(opinions);
			}

			// 예약 상신 시간
			if (!"".equals(reserved)) {
				String[] reserveds = new String[approvalRoutesLength];

				for (int i=0; i<approvalRoutesLength; i++) {
					reserveds[i] = "";
				}

				reserveds[0] = reserved;
				vo.setReserveds(reserveds);
			}

			/*********************************************************
			 * 결재상신
			**********************************************************/
			boolean isSubmit = esbApprovalService.submit(vo);

			JSONObject jo = new JSONObject();

			if (isSubmit) {
				jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit",  null, new RequestContext(request).getLocale()));
				jo.put("returnValue", "Y");
				
				//메일발송 로직 추가 2014-03-18 신성우
				mailSendService.sendApprovalMail(mailVo);
			} else {
				jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
				jo.put("returnValue", "N");
			}

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "N");

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "N");

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * ESB 결재 상신 (DB저장만 하고 실제 ESB상신은 하지 않음)
	 * 2013.05.22 현재 사용되지 않고 있음
	 * <p>
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void preSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        HttpSession session = request.getSession(false);
	        String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        String userId = (String)session.getAttribute("secfw.session.user_id");

	       /*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ApprovalVO vo = new ApprovalVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/

			/** 1. 결재내역 정보 **/
	        //모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = "";
			String misId    = "";

			if("".equals(StringUtil.bvl((String)request.getParameter("module_id"), ""))) {
				moduleId = sysCd;
			} else {
				moduleId = StringUtil.bvl((String)request.getParameter("module_id"), "");
			}

			if("".equals(StringUtil.bvl((String)request.getParameter("mis_id"), ""))) {
				misId = EsbUtil.generateMisId("APPR");
			} else {
				misId    = StringUtil.bvl((String)request.getParameter("mis_id"), "");
			}

			vo.setModule_id(moduleId);
			vo.setMis_id(misId);

			//로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"),"en_US.UTF-8");
			String setEncoding   = vo.getEncoding();
			String locale_info   = sessionLocale.substring(0, sessionLocale.indexOf(".")) + "." + setEncoding;

			vo.setLocale_info(locale_info);
			vo.setTime_zone(StringUtil.bvl((String)session.getAttribute("EP_TIMEZONE"),"GMT+0"));

			//STATUS 값 설정 - Default "0"
			vo.setStatus("0");

			//BODY Type
			String bodyType = vo.getBody_type();

			//Approval Post Method
			vo.setMethod(StringUtil.bvl((String)request.getParameter("approvalPostProcess"),""));

			if("1".equals(bodyType)) { //본문형식이 HTML이면
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
		           // vo.setBody(StringUtil.convertHtmlTochars((String)hm.get("CONTENT")));
		           vo.setBody(contentHtml);
		        } else {
		          // vo.setBody(StringUtil.convertHtmlTochars((String)hm.get("CONTENT")));
		          vo.setBody(contentHtml);
		        }

			}

			//결재상신 의견
	        String opinion = StringUtil.bvl(vo.getOpinion(),"");
	        //예약 상신 시간
	        String reserved = StringUtil.bvl(vo.getReserved(),"");

			/** 2. 결재경로 정보 **/
	        String[] approvalRoutes       = request.getParameterValues("receivers");
	        String[] approvalRouteRights  = request.getParameterValues("receiversRight");

	        ClmsDataUtil.debug("## : " + approvalRoutes);
	        ClmsDataUtil.debug("## : " + approvalRouteRights);

	        String[] activitys    	= null; // 설정구분
			String[] actionTypes  	= null; // 처리구분
			//String[] mailAddresss = null; // 메일주소
			String[] userIds      	= null; // EPID
			String[] userNms	  	= null;
			String[] jikgupCds	  	= null;
			String[] jikgupNms	  	= null;
			String[] deptCds		= null;
			String[] deptNms	  	= null;
			String[] compCds		= null;
			String[] compNms		= null;
			String[] grpCds			= null;
			String[] grpNms			= null;
			String[] mailAddress 	= null;
			String[] arbitrarys   	= null; // 전결권한
			String[] bodyModifys  	= null; // 본문수정권한
			String[] routeModifys 	= null; // 경로변경 권한

			int approvalRoutesLength = approvalRoutes.length;

			if(approvalRoutes != null && approvalRoutesLength > 0) {

				activitys    		= new String[approvalRoutesLength];
				actionTypes  		= new String[approvalRoutesLength];
				//mailAddresss = new String[approvalRoutesLength];
				userIds      		= new String[approvalRoutesLength];
				userNms      		= new String[approvalRoutesLength];
				jikgupCds	 		= new String[approvalRoutesLength];
				jikgupNms	  		= new String[approvalRoutesLength];
				deptCds				= new String[approvalRoutesLength];
				deptNms	  			= new String[approvalRoutesLength];
				compCds				= new String[approvalRoutesLength];
				compNms				= new String[approvalRoutesLength];
				grpCds				= new String[approvalRoutesLength];
				grpNms				= new String[approvalRoutesLength];
				mailAddress			= new String[approvalRoutesLength];
				arbitrarys   		= new String[approvalRoutesLength];
				bodyModifys  		= new String[approvalRoutesLength];
				routeModifys 		= new String[approvalRoutesLength];

				for(int i = 0; i < approvalRoutesLength; i++){

					String[] approvalRoute      = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");


					activitys[i]    		= approvalRoute[0];
					actionTypes[i]  		= "0";
					userIds[i]      		= approvalRoute[1];
					userNms[i]      		= approvalRoute[2];
					jikgupCds[i]	 		= approvalRoute[3];
					jikgupNms[i]	  		= approvalRoute[4];
					deptCds[i]				= approvalRoute[5];
					deptNms[i]	  			= approvalRoute[6];
					compCds[i]				= approvalRoute[7];
					compNms[i]				= approvalRoute[8];
					grpCds[i]				= approvalRoute[9];
					grpNms[i]				= approvalRoute[10];
					mailAddress[i]			= approvalRoute[11];

					routeModifys[i] 		= approvalRouteRight[1];
					bodyModifys[i]  		= approvalRouteRight[2];
					arbitrarys[i]   		= approvalRouteRight[3];

				}
			}

		    vo.setActivitys(activitys);
		    vo.setAction_types(actionTypes);

		    vo.setUser_ids(userIds);
		    vo.setUser_names(userNms);
		    vo.setDuty_codes(jikgupCds);
			vo.setDutys(jikgupNms);
			vo.setDept_codes(deptCds);
			vo.setDept_names(deptNms);
			vo.setComp_codes(compCds);
			vo.setComp_names(compNms);
			vo.setGroup_codes(grpCds);
			vo.setGroup_names(grpNms);
			vo.setMail_addresss(mailAddress);
		    vo.setRoute_modifys(routeModifys);
		    vo.setBody_modifys(bodyModifys);
		    vo.setArbitrarys(arbitrarys);

		  //결재상신 의견
		    if(!"".equals(opinion)) {
		    	String[] opinions = new String[approvalRoutesLength];
		    	for(int i = 0; i < approvalRoutesLength; i++) {
		    		opinions[i] = "";
		    	}
		    	opinions[0] = opinion;
		    	vo.setOpinions(opinions);
		    }
		  //예약 상신 시간
		    if(!"".equals(reserved)) {
		    	String[] reserveds = new String[approvalRoutesLength];
		    	for(int i=0; i<approvalRoutesLength; i++) {
		    		reserveds[i] = "";
		    	}
		    	reserveds[0] = reserved;
		    	vo.setReserveds(reserveds);
		    }

		    /*********************************************************
			 * 결재상신
			**********************************************************/
	        esbApprovalService.preSubmit(vo);

    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "Y");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();

		} catch (Exception e) {

			e.printStackTrace();

			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}

	}

	/**
	 * ESB 결재 상신(이미 DB에 등록 되어 건 실제 ESB상신)
	 * 2013.05.22 현재 사용되지 않고 있음
	 * <p>
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void afterSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{

		try{
			ApprovalVO vo = new ApprovalVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);


			esbApprovalService.afterSubmit(vo);

			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "Y");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();

		} catch (Exception e) {

			e.printStackTrace();

			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnValue", "N");
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}

	}

	/**
	 * ESB 메일발신취소
	 * 2013.05.22 현재 사용되지 않고 있음
	 * <p>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cancelApproval(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
	        HttpSession session = request.getSession(false);
	        String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ApprovalVO vo = new ApprovalVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			String moduleId = vo.getModule_id();
			String misId    = vo.getMis_id();
			String opinion  = vo.getOpinion();

			/*********************************************************
			 * 메일발송취소
			**********************************************************/
			String cancelResult = esbApprovalService.cancelApproval(moduleId, misId, opinion);
			String result = "";

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
	 * 결재창으로 이동
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView forwardApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템코드 및 사용자아이디
			**********************************************************/

			HttpSession session = request.getSession(false);
			String sysCd      = (String)session.getAttribute("secfw.session.sys_cd");
			String userId     = (String)session.getAttribute("secfw.session.user_id");
			String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
			String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
			String forwardURL = "";

			String approval_option = StringUtil.bvl((String)request.getParameter("approval_option"), StringUtil.bvl((String)request.getAttribute("approval_option"), ""));

			if (!"".equals(approval_option)) {
				forwardURL = "/WEB-INF/jsp/secfw/singleIF/ApprovalInsertAS_p.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/secfw/singleIF/ApprovalInsertAS.jsp";
			}

			CommonForm form = new CommonForm();
			bind(request, form);
			COMUtil.getUserAuditInfo(request, form);

			/*********************************************************
			 * Parameter Setting
			**********************************************************/
			String approvalModuleID     = StringUtil.bvl((String)request.getParameter("approval_module_id"), "");
			String approvalMisID        = StringUtil.bvl((String)request.getParameter("approval_mis_id"), "");
			String approvalDrafterID    = StringUtil.bvl((String)request.getParameter("approval_draft_id"), userId);
			String approvalTitle        = StringUtil.bvl((String)request.getParameter("approval_title"), "");
			String approvalContent      = StringUtil.bvl((String)request.getParameter("approval_content"), "");
			String approvalHtmlGbn      = StringUtil.bvl((String)request.getParameter("approval_html_gbn"), "");
			String approvalFileInfos    = StringUtil.bvl((String)request.getParameter("approval_fileInfos"), "");
			String approvalPostProcess  = StringUtil.bvl((String)request.getParameter("approval_post_process"), "");
			String apprvl_clsfcn        = StringUtil.bvl((String)request.getParameter("apprvl_clsfcn"), "");
			String ref_key              = StringUtil.bvl((String)request.getParameter("ref_key"), "");
			
			//계약ID
			String cntrt_id             = StringUtil.bvl((String)request.getParameter("cntrt_id"), "");
			

			// 2011.10.20 심주완추가 계약의뢰상신시 사용
			String approvalAuthorizerID = StringUtil.bvl((String)request.getParameter("approval_auth_id"), "");
			// 2011.10.13 심주완추가 계약체결품의시 사용
			String approvalAgreeID      = StringUtil.bvl((String)request.getParameter("approval_agree_id"), "");

			if ("".equals(approvalTitle)) {
				approvalModuleID     = StringUtil.bvl((String)request.getAttribute("approval_module_id"), "");
				approvalMisID        = StringUtil.bvl((String)request.getAttribute("approval_mis_id"), "");
				approvalDrafterID    = StringUtil.bvl((String)request.getAttribute("approval_draft_id"), userId);
				approvalTitle        = StringUtil.bvl((String)request.getAttribute("approval_title"), "");
				approvalContent      = StringUtil.bvl((String)request.getAttribute("approval_content"), "");
				approvalHtmlGbn      = StringUtil.bvl((String)request.getAttribute("approval_html_gbn"), "");
				approvalFileInfos    = StringUtil.bvl((String)request.getAttribute("approval_fileInfos"), "");
				approvalPostProcess  = StringUtil.bvl((String)request.getAttribute("approval_post_process"), "");
				apprvl_clsfcn        = StringUtil.bvl((String)request.getAttribute("apprvl_clsfcn"), "");
				ref_key              = StringUtil.bvl((String)request.getAttribute("ref_key"), "");

				// 2011.10.20 심주완추가 계약의뢰품의시 사용
				approvalAuthorizerID = StringUtil.bvl((String)request.getAttribute("approval_auth_id"),"");
				// 2011.10.13 심주완추가 계약체결품의시 사용
				approvalAgreeID      = StringUtil.bvl((String)request.getAttribute("approval_agree_id"),"");
			}

			String approvalDrafterUserPath     = "";
			String approvalDrafterUserRight    = "";

			// 2011.10.13 심주완추가 계약체결품의용(합의자정보)
			String approvalAgreeUserPath       = "";
			String approvalAgreeUserRight      = "";

			// 2011.10.20 심주완추가 계약의뢰품의용(결재자정보)
			String approvalAuthorizerUserPath  = "";
			String approvalAuthorizerUserRight = "";

			// 로케일, 인코딩, TIME_ZONE 설정
			String sessionLocale    = StringUtil.bvl((String)session.getAttribute("EP_LOCALE"), "en_US.UTF-8");
			String approvalEncoding = sessionLocale.substring(sessionLocale.indexOf(".") + 1);

			Locale lc = (Locale)localeResolver.resolveLocale(request);

			String all_approvalDrafterID = ""; //전체 결재 승인자들
			
			// 상신자 정보 조회
			if (approvalDrafterID != null && !"".equals(approvalDrafterID)) {
				// 기안
				String typeNm     = messageSource.getMessage("secfw.page.field.approval.draft",  null, new RequestContext(request).getLocale());
				// 경로변경
				String pathModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.changeLine",  null, new RequestContext(request).getLocale());
				// 본문수정
				String bodyModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.modifyText",  null, new RequestContext(request).getLocale());

				// 2012.07.23 삼성전자 외 타사임직원(삼성그룹내) 결재 사용 허용여부 확인
				HashMap hm = new HashMap();
				hm.put("user_id", approvalDrafterID);
				hm.put("new_flag", "Y");
				hm.put("compCd", compCd);
				hm.put("cntrt_id",cntrt_id);
				
				//구주요청에 의한 수정 : 기안자, 기안자의 부서장, 결재가능자를 조회 
				//List exceptWsvoList = esbApprovalService.listExceptWsvoList(hm);
				
				//approval path 조건에 부합하는 기안자, 기안자의 부서장, 결재가능자를 조회 
				List exceptWsvoList = esbApprovalService.listExceptWsvoListPath(hm);

				//신성우 주석처리 결제 경로 탈출문자 테스트
				//예시 : StringUtil.StringSlashReplace("'") => \'로 처리
				/*
				// 기안자정보        아이디 | 이름 | 직급코드 | 직급명 |
				approvalDrafterUserPath = approvalDrafterUserPath + "<option value=\""+"0"+"|" + "D100204085355C101377" + "|" + StringUtil.StringReplace(StringUtil.bvl("Zsuzsanna Holló", "") )  + "|"+ StringUtil.bvl("V2", "")+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl("President's secretary", "")) + "|";
				// 기안자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
				approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl("C10CA06CA020256", "") + "|" + StringUtil.bvl("SEH-P-GA Part", "") + "|" + StringUtil.bvl("SEH-P", "") + "|"+ StringUtil.bvl("SEH-P", "") +"|";
				// 기안자정보(계속)  총괄코드 | 총괄명 | 메일주소 | 결재 사용 허용여부(approval_yn)
				approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl("V2", "A")+ "|"  + StringUtil.bvl("Y", "") + "\">"
										+ "test"
										+ "</option>";
				*/

				for(int irow=0;irow<exceptWsvoList.size();irow++){
					ListOrderedMap lom = (ListOrderedMap)exceptWsvoList.get(irow);
					String flag = StringUtil.bvl((String)lom.get("flag"), "");
					String t_approvalDrafterID = StringUtil.bvl((String)lom.get("user_id"), "");
					all_approvalDrafterID = all_approvalDrafterID + t_approvalDrafterID + ",";
					
					String APPROVAL_YN = StringUtil.bvl((String)lom.get("APPROVAL_YN"), "");
					
					String draft_or_approval_flag = irow==0 ? "0" : "1"; //기안자(0), 결재자(1)
					if(irow == 1) typeNm = messageSource.getMessage("secfw.page.field.approval.approval",  null, new RequestContext(request).getLocale());
					
					if (flag.equals("EXCEPT_ID")) {// 결재 ESB조회 예외자가 존재(ESB 조회 안함)
						
						// 기안자정보        아이디 | 이름 | 직급코드 | 직급명 |
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ "<option value=\\\""
												+ draft_or_approval_flag
												+ "|" + t_approvalDrafterID 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), "")) 
												+ "|" + StringUtil.bvl((String)lom.get("jikgup_cd"), "")
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ "|";
						// 기안자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ StringUtil.bvl((String)lom.get("dept_cd"), "") 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("dept_nm"), "")) 
												+ "|" + StringUtil.bvl((String)lom.get("comp_cd"), "") 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("comp_nm"), "")) 
												+ "|";
						// 기안자정보(계속)  총괄코드 | 총괄명 | 메일주소 | 결재 사용 허용여부(approval_yn)
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ StringUtil.bvl((String)lom.get("division_cd"), "A")
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("division_nm"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale()))) 
												+ "|" + StringUtil.bvl((String)lom.get("email"), "")
												+ "|"+ StringUtil.bvl(APPROVAL_YN, "") 
												+ "\\\">"
												+ typeNm 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), "") )
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("dept_nm"), ""))
												+ StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("comp_nm"), "")) 
												+ " / "  + "</option>";
	
						approvalDrafterUserRight = approvalDrafterUserRight
												+ "<option value=\\\"" + t_approvalDrafterID + "|-1|-1|-1\\\">"
												+ typeNm 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), ""))
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ " : " + StringUtil.StringSlashReplace(pathModify)
												+ " / " + StringUtil.StringSlashReplace(bodyModify)
												+ " / " + "</option>";
					}else if(flag.equals("APP_Y_ID")) {// 결재 Flag가 APP_Y_ID일 경우 |Y 추가 13번째 열로 이동여부 구분 신성우 추가 2014-03-13
						Vector drafterUserVector = null;
	
						// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
						drafterUserVector = esbOrgService.userSearchByEpid(t_approvalDrafterID, lc);
						//drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);

						if (drafterUserVector != null && drafterUserVector.size() > 0) {
							Hashtable ht = (Hashtable)drafterUserVector.get(0);
	
							// 기안자정보        아이디 | 이름 | 직급코드 | 직급명 |
							//approvalDrafterUserPath = approvalDrafterUserPath + "<option value='0|" + t_approvalDrafterID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ "<option value=\\\""
													+ draft_or_approval_flag
													+ "|" + t_approvalDrafterID 
													+ "|" + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ "|" + ht.get("eptitlenumber")
													+ "|" + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ "|";
							// 기안자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ ht.get("departmentnumber")
													+ "|" + StringUtil.StringSlashReplace(ht.get("department").toString()) 
													+ "|" + ht.get("eporganizationnumber") 
													+ "|" + StringUtil.StringSlashReplace(ht.get("o").toString()) 
													+ "|";
							// 기안자정보(계속)  총괄코드 | 총괄명 | 메일주소 | 결재 사용 허용여부(approval_yn)
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ StringUtil.bvl(ht.get("epsuborgcode"), "A")
													+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl(ht.get("epsuborgname"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale()))) 
													+ "|" + ht.get("mail")													
													+ "|" + StringUtil.bvl(APPROVAL_YN, "")
													+ "|" + flag	//신성우 추가 2014-03-13 13번째 라인 추가
													+ "\\\">"
													+ typeNm 
													+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("department").toString())
													+ " / " + StringUtil.StringSlashReplace(ht.get("o").toString())
													+ " / " + "</option>";
	
							approvalDrafterUserRight = approvalDrafterUserRight
													+"<option value=\"" + t_approvalDrafterID + "|-1|-1|-1\\\">"
													+ typeNm 
													+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ " : " + StringUtil.StringSlashReplace(pathModify)
													+ " / " + StringUtil.StringSlashReplace(bodyModify)
													+ " / " + "</option>";
						}
					}else if (flag.equals("CONDITION_ID")) {// approval path 조건에 맞는 결재자 정보 setting 2014.06
						
						
						String APPROVAL_TYPE = StringUtil.bvl((String)lom.get("APPROVAL_TYPE"), "");
						
						if("1".equals(APPROVAL_TYPE)){
							typeNm = "Approval";
						}else if("2".equals(APPROVAL_TYPE)){
							typeNm = "Consent";
						}else if("9".equals(APPROVAL_TYPE)){
							typeNm = "Notification";
						}
						// 기안자정보        아이디 | 이름 | 직급코드 | 직급명 |
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ "<option value=\\\""
												+ APPROVAL_TYPE
												+ "|" + t_approvalDrafterID 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), "")) 
												+ "|" + StringUtil.bvl((String)lom.get("jikgup_cd"), "")
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ "|";
						// 기안자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ StringUtil.bvl((String)lom.get("dept_cd"), "") 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("dept_nm"), "")) 
												+ "|" + StringUtil.bvl((String)lom.get("comp_cd"), "") 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("comp_nm"), "")) 
												+ "|";
						// 기안자정보(계속)  총괄코드 | 총괄명 | 메일주소 | 결재 사용 허용여부(approval_yn)
						approvalDrafterUserPath = approvalDrafterUserPath 
												+ StringUtil.bvl((String)lom.get("division_cd"), "A")
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("division_nm"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale()))) 
												+ "|" + StringUtil.bvl((String)lom.get("email"), "")
												+ "|"+ StringUtil.bvl(APPROVAL_YN, "") 
												+ "\\\">"
												+ typeNm 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), "") )
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("dept_nm"), ""))
												+ StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("comp_nm"), "")) 
												+ " / "  + "</option>";
	
						approvalDrafterUserRight = approvalDrafterUserRight
												+ "<option value=\\\"" + t_approvalDrafterID + "|-1|-1|-1\\\">"
												+ typeNm 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("user_nm"), ""))
												+ " / " + StringUtil.StringSlashReplace(StringUtil.bvl((String)lom.get("jikgup_nm"), "")) 
												+ " : " + StringUtil.StringSlashReplace(pathModify)
												+ " / " + StringUtil.StringSlashReplace(bodyModify)
												+ " / " + "</option>";
					}
					else {
						Vector drafterUserVector = null;

						// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
						drafterUserVector = esbOrgService.userSearchByEpid(t_approvalDrafterID, lc);
						//drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);
	
						if (drafterUserVector != null && drafterUserVector.size() > 0) {
							Hashtable ht = (Hashtable)drafterUserVector.get(0);
	
							// 기안자정보        아이디 | 이름 | 직급코드 | 직급명 |
							//approvalDrafterUserPath = approvalDrafterUserPath + "<option value='0|" + t_approvalDrafterID + "|" + ht.get("cn") + "|"+ ht.get("eptitlenumber")+ "|" + ht.get("title") + "|";
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ "<option value=\\\""
													+ draft_or_approval_flag
													+ "|" + t_approvalDrafterID 
													+ "|" + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ "|" + ht.get("eptitlenumber")
													+ "|" + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ "|";
							// 기안자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ ht.get("departmentnumber")
													+ "|" + StringUtil.StringSlashReplace(ht.get("department").toString()) 
													+ "|" + ht.get("eporganizationnumber") 
													+ "|" + StringUtil.StringSlashReplace(ht.get("o").toString()) 
													+ "|";
							// 기안자정보(계속)  총괄코드 | 총괄명 | 메일주소 | 결재 사용 허용여부(approval_yn)
							approvalDrafterUserPath = approvalDrafterUserPath 
													+ StringUtil.bvl(ht.get("epsuborgcode"), "A")
													+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl(ht.get("epsuborgname"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale()))) 
													+ "|" + ht.get("mail")
													+ "|" + StringUtil.bvl(APPROVAL_YN, "")
													+ "\\\">"
													+ typeNm 
													+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("department").toString())
													+ " / " + StringUtil.StringSlashReplace(ht.get("o").toString())
													+ " / " + "</option>";
	
							approvalDrafterUserRight = approvalDrafterUserRight
													+"<option value=\"" + t_approvalDrafterID + "|-1|-1|-1\\\">"
													+ typeNm 
													+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
													+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
													+ " : " + StringUtil.StringSlashReplace(pathModify)
													+ " / " + StringUtil.StringSlashReplace(bodyModify)
													+ " / " + "</option>";
						}
					}

				}
			}
			// 2011.10.20 심주완추가 - 결재자정보 조회때문에 추가
			if (approvalAuthorizerID != null && !"".equals(approvalAuthorizerID) && all_approvalDrafterID.indexOf( approvalAuthorizerID+"," )==-1) {
				Vector authorizerUserVector = null;

				// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
				authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);
				//authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);

				if (authorizerUserVector != null && authorizerUserVector.size() > 0) {
					Hashtable ht = (Hashtable)authorizerUserVector.get(0);

					// 결재
					String typeNm     = messageSource.getMessage("secfw.page.field.approval.approval",  null, new RequestContext(request).getLocale());
					// 경로변경
					String pathModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.changeLine",  null, new RequestContext(request).getLocale());
					// 본문수정
					String bodyModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.modifyText",  null, new RequestContext(request).getLocale());
					// 결재자정보        아이디 | 이름 | 직급코드 | 직급명 |
					approvalAuthorizerUserPath = "<option value=\\\"1|" 
												+ approvalAuthorizerID 
												+ "|" + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", "")) 
												+ "|" + ht.get("eptitlenumber")
												+ "|" + StringUtil.StringSlashReplace(ht.get("title").toString()) 
												+ "|";
					// 결재자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
					approvalAuthorizerUserPath = approvalAuthorizerUserPath 
												+ ht.get("departmentnumber") 
												+ "|" + StringUtil.StringSlashReplace(ht.get("department").toString()) 
												+ "|" + ht.get("eporganizationnumber") 
												+ "|" + StringUtil.StringSlashReplace(ht.get("o").toString())
												+ "|";
					// 결재자정보(계속)  총괄코드 | 총괄명 | 메일주소
					approvalAuthorizerUserPath = approvalAuthorizerUserPath 
												+ StringUtil.bvl(ht.get("epsuborgcode"), "A")
												+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl(ht.get("epsuborgname"), (String)messageSource.getMessage("clm.page.field.regist.makeApprovalHtmlDirectNew02", null, new RequestContext(request).getLocale()))) 
												+ "|" + ht.get("mail") + "\\\">"
												+ StringUtil.StringSlashReplace(typeNm) 
												+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", ""))  
												+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
												+ " / " + StringUtil.StringSlashReplace(ht.get("department").toString()) 
												+ " / " + StringUtil.StringSlashReplace(ht.get("o").toString())
												+ " / "
												+ "</option>";
					approvalAuthorizerUserRight = "<option value=\\\"" + approvalAuthorizerID + "|-1|-1|-1\\\">"
												+ StringUtil.StringSlashReplace(typeNm)
												+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", ""))
												+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
												+ " : " + StringUtil.StringSlashReplace(pathModify)
												+ " / " + StringUtil.StringSlashReplace(bodyModify)
												+ " / " + "</option>";
				}
			}
			// 2011.10.13 심주완추가 - 합의자정보 조회때문에 추가
			if (approvalAgreeID != null && !"".equals(approvalAgreeID)) {
				Vector agreeUserVector = null;

				// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
				agreeUserVector = esbOrgService.userSearchByEpid(approvalAgreeID, lc);
				//agreeUserVector = esbOrgService.userSearchByEpid(approvalAgreeID, lc);

				if (agreeUserVector != null && agreeUserVector.size() > 0) {
					Hashtable ht = (Hashtable)agreeUserVector.get(0);

					// 합의
					String typeNm     = messageSource.getMessage("secfw.page.field.approval.consent",  null, new RequestContext(request).getLocale());
					//경로변경
					String pathModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.changeLine",  null, new RequestContext(request).getLocale());
					//본문수정
					String bodyModify = messageSource.getMessage("secfw.page.field.approval.grantApprovalRight.modifyText",  null, new RequestContext(request).getLocale());
					//합의자정보        아이디 | 이름 | 직급코드 | 직급명 |
					approvalAgreeUserPath = "<option value=\\\"2|" 
											+ approvalAgreeID 
											+ "|" + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", ""))
											+ "|" + ht.get("eptitlenumber")
											+ "|" + StringUtil.StringSlashReplace(ht.get("title").toString()) + "|";
					//합의자정보(계속)  부서코드 | 부서명 | 회사코드 | 회사명 |
					approvalAgreeUserPath = approvalAgreeUserPath 
											+ ht.get("departmentnumber")
											+ "|" + StringUtil.StringSlashReplace(ht.get("department").toString()) 
											+ "|" + ht.get("eporganizationnumber") 
											+ "|" + StringUtil.StringSlashReplace(ht.get("o").toString())
											+ "|";
					//합의자정보(계속)  총괄코드 | 총괄명 | 메일주소
					approvalAgreeUserPath = approvalAgreeUserPath 
											+ StringUtil.bvl(ht.get("epsuborgcode"), "A")
											+ "|" + StringUtil.StringSlashReplace(StringUtil.bvl(ht.get("epsuborgname"), "None"))
											+ "|" + ht.get("mail") + "\\\">"
											+ typeNm 
											+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", ""))
											+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
											+ " / " + StringUtil.StringSlashReplace(ht.get("department").toString()) 
											+ " / " + StringUtil.StringSlashReplace(ht.get("o").toString()) 
											+ " /" + "</option>";
					approvalAgreeUserRight = "<option value=\\\"" + approvalAgreeID + "|-1|-1|-1\\\">"
											+ typeNm 
											+ " / " + StringUtil.StringSlashReplace(StringUtil.replace((String)ht.get("cn"), "'", ""))
											+ " / " + StringUtil.StringSlashReplace(ht.get("title").toString()) 
											+ " : " + StringUtil.StringSlashReplace(pathModify)
											+ " / " + StringUtil.StringSlashReplace(bodyModify)
											+ " / " + "</option>";
				}
			}
			 /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("approvalModuleID"         , approvalModuleID);
			mav.addObject("approvalMisID"            , approvalMisID);
			mav.addObject("approvalTitle"            , approvalTitle);
			mav.addObject("approvalContent"          , approvalContent);
			mav.addObject("approvalDrafterID"        , approvalDrafterID);
			mav.addObject("approvalDrafterUserPath"  , approvalDrafterUserPath);
			mav.addObject("approvalDrafterUserRight" , approvalDrafterUserRight);
			mav.addObject("approvalHtmlGbn"          , approvalHtmlGbn);
			mav.addObject("approvalFileInfos"        , approvalFileInfos);
			mav.addObject("approvalPostProcess"      , approvalPostProcess);
			mav.addObject("approvalEncoding"         , approvalEncoding);
			mav.addObject("form"                     , form);

			// CLM 사용 변수 추가
			mav.addObject("approval_option", approval_option);
			mav.addObject("apprvl_clsfcn", apprvl_clsfcn);
			mav.addObject("ref_key", ref_key);
			mav.addObject("approvalAgreeID"            , approvalAgreeID);
			mav.addObject("approvalAgreeUserPath"      , approvalAgreeUserPath);
			mav.addObject("approvalAgreeUserRight"     , approvalAgreeUserRight);
			mav.addObject("approvalAuthorizerID"       , approvalAuthorizerID);
			mav.addObject("approvalAuthorizerUserPath" , approvalAuthorizerUserPath);
			mav.addObject("approvalAuthorizerUserRight", approvalAuthorizerUserRight);

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
