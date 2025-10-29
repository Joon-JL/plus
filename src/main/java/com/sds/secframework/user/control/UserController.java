package com.sds.secframework.user.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sec.clm.manage.service.MailSendService;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.sso.EpTrayUtil;
import com.sds.secframework.common.sso.Utils;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.log.dto.LogVO;
import com.sds.secframework.log.service.LogService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserForm;
import com.sds.secframework.user.dto.UserVO;
import com.sds.secframework.user.service.UserService;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.service.CompletionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserController extends CommonController {

    // SSO TIMESTAMP CHECK를 위한 전역변수
    private String trayLoginTimeformis = "";

    /**
     * Business Logic 서비스 호출을 위한 선언 및 세팅
     */
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Business Logic 서비스 호출을 위한 선언 및 세팅 (Logging Service)
     */
    private LogService logService;
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    /**
     * Business Logic 서비스 호출을 위한 선언 및 세팅
     */
    private EsbOrgService esbOrgService;
    public void setEsbOrgService(EsbOrgService esbOrgService) {
        this.esbOrgService = esbOrgService;
    }

    public void setMailSendService(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    private MailSendService mailSendService;


    /**
     * 로그아웃 처리
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView clmsLogOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        updateLogoutLog( request,  session);
        session.invalidate(); // 세션 삭제

        ModelAndView mav = null;
        return mav;
    }

    /**
     * Login처리 - SSO, Session세팅
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView clmsLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = null;

        try {
            // 시스템 작업 공지 페이지로 바로 이동한다.
            String x = StringUtil.bvl((String)request.getParameter("x"), "");


            if ("o".equals(x)) {
                mav = new ModelAndView();
                mav.setViewName("/WEB-INF/jsp/common/SystemAlert.jsp"); // 공지 페이지로 이동
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute("secfw.session.sys_cd", "LAS");

                // Forwarding하는 페이지 정보를 가져온다.
                String forwardURL = (StringUtil.bvl(((String)request.getParameter("secfw.forwardURL")),"")).trim();
                getLogger().debug("forwardURL : " + forwardURL);

                // 12개 자회사 임직원에 대하여 SSO 정보를 읽어서 세션에 저장하며 SSO가능하면 true를 리턴
                boolean isValidSSO = processTrayInfo(request, session);
                String userId     = (String)session.getAttribute("secfw.session.user_id"); // UserId
                String sysCd      = (String)session.getAttribute("secfw.session.sys_cd");  // SysCd
                String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd"); // authCompCd
                String compCd = (String)session.getAttribute("secfw.session.comp_cd");
                if (isValidSSO) { //정상
                    //SSO Timestamp 체크
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

                    Date beginDate = formatter.parse(trayLoginTimeformis);
                    Date endDate = new Date(System.currentTimeMillis()); // 시스템 접속 시간

                    // 시스템 접속 시간 - Tray Login 시간 차 계산
                    long Diff = (endDate.getTime() - beginDate.getTime()) / (60 * 60 * 1000);

                    // 시스템 보안 정책에 맞게 시간차 설정(24시간)
                    if (Diff < 24) { // 접속 허용!!
                        // 시스템에 들어올 수 있는 사용자 인지 판별 - 회사정보 및 DB에 기등록된 사람
                        List userInfo = null;

                        try {
                            userInfo = userService.getClmsUserInfo(userId, sysCd);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                        boolean loginAccess = false;
                        if (userInfo != null && userInfo.size() > 0) { // 사용자 정보가 있으면 로그인 가능
                            loginAccess = true;
                        }

                        if (loginAccess) { // 사용자 정보가 있을 경우 Update! 후 로그인 진행
                            // 사용자 정보 UPDATE
                            try {
                                userInfo = userService.processClmsUserInfo(userId, (String)session.getAttribute("secfw.session.sys_cd"), "U");
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            if (userInfo != null) {
                                /***********************************************************************************
                                 * 역할정보 세팅
                                 ***********************************************************************************/
                                //역할(role) 정보 셋팅(기본 RZ00)으로 셋팅!!
                                try {
                                    setUserRole(userId, "RZ00", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loginAccess = false;
                            }
                        } else { // 없을 경우 경우의 수 계산
                            // 사용자 정보 INSERT
                            try {
                                userInfo = userService.processClmsUserInfo(userId, (String)session.getAttribute("secfw.session.sys_cd"), "I");
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            if (userInfo.size()>0) {
                                /***********************************************************************************
                                 * 역할정보 세팅
                                 ***********************************************************************************/
                                //역할(role) 정보 셋팅(계약담당자(RD02) role로 셋팅!!)
                                try {
                                    setUserRole(userId, "RZ00", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }

                                loginAccess = true; // insert 후 로그인 진행
                            } else {
                                loginAccess = false;
                            }
                        }

                        /*******************************************************
                         * 2013.12.06
                         * 법인코드별 다국어 셋팅
                         * SEG		- "de"
                         * SEF		- "fr"
                         * 이외		- "en"
                         *******************************************************/
                        // 2014-02-19 Kevin. SEG 오픈으로 주석처리 된 부분 해제.
                        if("SEF".equals(compCd)){
                            session.setAttribute("secfw.session.locale",          "fr_FR.EUC-KR");
                        }
					/*	else if("SEG".equals(compCd)){
							session.setAttribute("secfw.session.locale",          "de_DE.EUC-KR");
						}
					*/	else{
                            session.setAttribute("secfw.session.locale",          "en_US.EUC-KR");
                        }

                        /*********************************************************/

                        if (loginAccess) {
                            // 다국어처리를 위한 Locale정보를 사용자테이블에 업데이트처리 추가 2013.02.17
                            HashMap localeMap = new HashMap();
                            UserVO vo = new UserVO();
                            String slocale = (String)session.getAttribute("secfw.session.locale");

                            if (slocale != null && slocale.length() >= 3)
                                slocale = slocale.substring(0,2);

                            vo.setUser_id(userId);
                            localeMap.put("last_locale",slocale);
                            localeMap.put("vo",vo);
                            userService.changeLocale(localeMap);

                            ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);

                            // SSO에서 영문부서명이 unknown일 경우 부서명(dbpt_nm)의 값은 넣는다.
                            String dept_nm_en = (String)session.getAttribute("secfw.session.dept_nm_en");

                            if (dept_nm_en != null && dept_nm_en.equals("Unknown")) {
                                if (lom.get("dept_nm_eng") != null && lom.get("dept_nm_eng").toString().length() > 0) {
                                    session.setAttribute("secfw.session.dept_nm_en", (String)lom.get("dept_nm_eng"));
                                } else {
                                    session.setAttribute("secfw.session.dept_nm_en", (String)lom.get("dept_nm"));
                                }
                            }

                            String related_products = (String)lom.get("related_products");
                            session.setAttribute("secfw.session.related_products", related_products);

                            // 2011-11-29 김형준 작성
                            //테스트를 위해 세션에 부서코드 및 부서명을 EPTRAY 에서 읽어오는 값 말고 DB에서 조회한 값으로 새로 세션 세팅한다.
                            //현업 테스트가 끝났을 경우 아래는 삭제하여야 한다.
                            //session.setAttribute("secfw.session.dept_cd",   (String)lom.get("dept_cd"));
                            //session.setAttribute("secfw.session.dept_nm",   (String)lom.get("dept_nm"));
                            // 2012.05.29 사업부 담당자 관련 세션 추가 added by hanjihoon
                            session.setAttribute("secfw.session.clms_user_orgnz",   (String)lom.get("clms_user_orgnz"));
                            session.setAttribute("secfw.session.resp_operdiv",   (String)lom.get("resp_operdiv"));


                            //테스트을 위하여 생성해놓았지만 지우지 않아도 큰 문제는 없다.

                            /***********************************************************************************
                             * 사용자 정보 추가 Session Setting
                             ***********************************************************************************/
                            // 사업부코드 : 신남원 : 2011.09.05
                            session.setAttribute("secfw.session.division_cd", StringUtil.bvl((String)lom.get("division_cd"),""));
                            // 내부부서코드
                            session.setAttribute("secfw.session.in_dept_cd", StringUtil.bvl((String)lom.get("in_dept_cd"), ""));

                            /***********************************************************************************
                             * 사용자 로케일정보 세팅
                             ***********************************************************************************/
                            setUserLoginLocale(request, response, session);

                            /***********************************************************************************
                             * 역할(Role)정보 세션에 셋팅
                             ***********************************************************************************/
                            HashMap roleListMap = new HashMap();
                            roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                            roleListMap.put("user_id", userId);

                            List roleUserList = userService.listUserRole(roleListMap);
                            ArrayList roleList   = new ArrayList();
                            ArrayList bmRoleList = new ArrayList();

                            if (roleUserList != null && roleUserList.size() > 0) {
                                for (int i=0; i<roleUserList.size(); i++) {
                                    ListOrderedMap roleListLom = (ListOrderedMap)roleUserList.get(i);

                                    HashMap roleMap = new HashMap();
                                    roleMap.put("role_cd", (String)roleListLom.get("role_cd"));
                                    roleMap.put("role_nm", (String)roleListLom.get("role_nm"));

                                    roleList.add(roleMap);
                                    bmRoleList.add((String)roleListLom.get("role_cd"));
                                }
                            }

                            session.setAttribute("secfw.session.roleList", bmRoleList);
                            session.setAttribute("secfw.session.role",  roleList);

                            /***********************************************************************************
                             * 권한(Auth)정보 세션에 셋팅
                             ***********************************************************************************/
                            List authUserList = userService.listUserAuth(roleListMap);
                            ArrayList authList = new ArrayList();

                            if (authUserList != null && authUserList.size() > 0) {
                                for (int i=0; i<authUserList.size(); i++) {
                                    ListOrderedMap authListLom = (ListOrderedMap)authUserList.get(i);

                                    HashMap authMap = new HashMap();
                                    authMap.put("auth_cd", (String)authListLom.get("auth_cd"));
                                    authMap.put("auth_nm", (String)authListLom.get("auth_nm"));

                                    authList.add(authMap);
                                }
                            }

                            session.setAttribute("secfw.session.auth",  authList);

                            /***********************************************************************************
                             * Property 에 설정된 관리자와 동일인이면 세션 사용자레벨에 시스템 관리자 권한으로 세팅
                             ***********************************************************************************/
                            setSystemAdminRole(request, session, userId);

                            String dept_cd        = StringUtil.bvl((String)lom.get("dept_cd"), "");
                            String auth_apnt_yn   = StringUtil.bvl((String)lom.get("auth_apnt_yn"), "");
                            String emp_no         = StringUtil.bvl((String)lom.get("emp_no"), "");
                            String blngt_orgnz    = StringUtil.bvl((String)lom.get("blngt_orgnz"), "");
                            String auth_apnt_dept = StringUtil.bvl((String)lom.get("auth_apnt_dept"), "");

                            // 로그인시 자동 update 여부(특정사용자는 N으로 처리되어있다)
                            String auto_rnew_yn = StringUtil.bvl((String)lom.get("auto_rnew_yn"), "Y");

                            /***********************************************************************************
                             * 현재 소속 부서의 상위 부서들을 구분자(^)를 붙여서 문자열로 세션에 셋팅한다.
                             ***********************************************************************************/
                            setUpDeptInfo(request, session, dept_cd);

                            /***********************************************************************************
                             * 소속 조직 코드를 세션에 셋팅
                             ***********************************************************************************/
                            setBlngtOrgnz(request, session, dept_cd, userId, blngt_orgnz, auto_rnew_yn);

                            /***********************************************************************************
                             * 조직장 여부를 세션에 셋팅
                             ***********************************************************************************/
                            setManagerYN(request, session, auth_apnt_yn, dept_cd, emp_no);

                            /***********************************************************************************
                             * 권한지정여부 및 권한지정부서 세션에 셋팅
                             ***********************************************************************************/
                            session.setAttribute("secfw.session.auth_apnt_yn", auth_apnt_yn);
                            session.setAttribute("secfw.session.auth_apnt_dept", auth_apnt_dept);

                            /***********************************************************************************
                             * 지원부서 여부를 세션에 셋팅
                             ***********************************************************************************/
                            setSupportYN(request, session, StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), ""), dept_cd);

                            /********************************************************************************
                             * 연계시스템 구분 세션에 셋팅
                             *******************************************************************************/
                            session.setAttribute("secfw.session.infsysnm", "");

                            /********************************************************************************
                             * 로그인 사용현황 등록
                             *******************************************************************************/
                            String lastConnectIP   = "";
                            String lastConnectTime = "";

                            try {
                                List recentUserInfoList = logService.listRecentUserInfo(userId, StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""), StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));
                                if (recentUserInfoList != null && recentUserInfoList.size() > 0) {
                                    ListOrderedMap recentUserInfoMap = (ListOrderedMap)recentUserInfoList.get(0);

                                    lastConnectIP   = (String)recentUserInfoMap.get("ip_address");
                                    lastConnectTime = (String)recentUserInfoMap.get("login_dt");
                                }

                                insertLoginLog(request, session);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            /********************************************************************************
                             * PAGE forwarding
                             *******************************************************************************/
                            // 2012-08-24
                            // 사업부코드 못찾아오는 사람들은 로그인을 막는다.
                            // - 해외부서 사용자, 부서테이블에 해당부서코드가 없는 사용자, 기타 불확실한 이유로 사업부코드가 제대로 맵핑이 안된 사용자
                            String blngtOrgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

                            if ("".equals(blngtOrgnz)) {
                                mav = new ModelAndView();
                                mav.setViewName("/las/introNotice.do"); // 미사용자 공지
                            } else {
                                mav = new ModelAndView();

                                if (!"".equals(forwardURL)) { // forwarding URL이 존재
                                    mav.setViewName(forwardURL);
                                } else {
                                    // intro화면에서 바로가기 다운로드 받기위한 설정
                                    String strUrl       = (request.getRequestURL()).toString();
                                    String strAttachUrl = StringUtil.bvl(strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=","");


                                    String accessYn = userService.getAccessYn(userId);


                                    if("Y".equals(accessYn)){
                                        mav.setViewName("/las/intro.do"); //법무 시스템

                                    }else if("X".equals(accessYn)){
                                        mav.setViewName("/las/introSubsidiary.do"); //지법인 오픈 공지

                                    }else{
                                        mav.setViewName("/las/introNotice.do"); // 미사용자 공지
                                        mav.addObject("accessYn", accessYn);
                                        mav.addObject("user_nm", session.getAttribute("secfw.session.user_nm_en"));
                                        mav.addObject("dept_nm_eng", session.getAttribute("secfw.session.dept_nm_en"));
                                        mav.addObject("dept_cd", session.getAttribute("secfw.session.dept_cd"));
                                    }
                                    mav.addObject("strAttachUrl", strAttachUrl);
                                    mav.addObject("lastConnectIP", lastConnectIP);
                                    mav.addObject("lastConnectTime", lastConnectTime);
                                    //}
                                }
                            }
                        } else { // 홈페이지에 들어올 수 없는 사용자

                            mav = new ModelAndView();
                            mav.setViewName("/las/introNotice.do"); // 미사용자 공지

                        }
                    } else { //SSO TIMESTAMP ERROR 발생
                        String alertTitle   = "";
                        String alertMessage = "";

                        // 메시지처리 - 마이싱글 로그인 후 24시간이 경과되어 시스템 접속이 불가합니다. 마이싱글 또는 전자통합인증(SSO) 재로그인 후 시스템을 사용하시기 바랍니다.
                        alertTitle = messageSource.getMessage("secfw.page.field.alert.ssoTimeStampError", null, new RequestContext(request).getLocale());
                        // 메시지처리 - 시스템 관리자에게 문의하십시오.
                        alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

                        mav = new ModelAndView();
                        mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                        mav.addObject("secfw.alert.title", alertTitle);
                        mav.addObject("secfw.alert.message", alertMessage);
                    }
                } else { //SSO CHECK ERROR 발생
                    // 메시지처리 - SSO CHECK시 ERROR가 발생하였습니다.
                    String alertTitle = messageSource.getMessage("secfw.page.field.alert.ssoError", null, new RequestContext(request).getLocale());
                    // 메시지처리 - 시스템 관리자에게 문의하십시오.
                    String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

                    mav = new ModelAndView();
                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                    mav.addObject("secfw.alert.title", alertTitle);
                    mav.addObject("secfw.alert.message", alertMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    /**
     * 2014-05-14 Kevin added.
     */
    private CompletionService completionService;
    public void setCompletionService(CompletionService completionService){
        this.completionService = completionService;
    }

    /**
     * 2014-05-14 Kevin added.
     * This is for external access from outside such as GERP, Outlook-plug in, etc.
     * As SELMS+ has got a couple of interactions like GERP, Outlook-plugin, this function allows outside user over SELMS+ to have access to SELMS+ so that they can go through specific contract detail.
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView showDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = null;
        String redirectUrl = "";

        try {

            String sID = request.getParameter("id");
            String from = request.getParameter("t");
            String mtype = request.getParameter("mt");

            if(sID == null || sID == ""){
                mav = new ModelAndView();
                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.invalidID", null, new RequestContext(request).getLocale()));
                mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale()));
                return mav;
            }

            HttpSession session = request.getSession(true);

            // SSO 정보를 읽어서 세션에 저장하며 SSO가능하면 true를 리턴
            boolean isValidSSO = processTrayInfo(request, session);

            if(!isValidSSO){
                mav = new ModelAndView();
                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.ssoError", null, new RequestContext(request).getLocale()));
                mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale()));
                return mav;
            }

            //SSO Timestamp 체크
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

            Date beginDate = formatter.parse(trayLoginTimeformis);
            Date endDate = new Date(System.currentTimeMillis()); // 시스템 접속 시간

            // 시스템 접속 시간 - Tray Login 시간 차 계산
            long Diff = (endDate.getTime() - beginDate.getTime()) / (60 * 60 * 1000);
            // 시스템 보안 정책에 맞게 시간차 설정(24시간)
            if(Diff >= 24){
                mav = new ModelAndView();
                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.ssoTimeStampError", null, new RequestContext(request).getLocale()));
                mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale()));
                return mav;
            }

            String userId     = (String)session.getAttribute("secfw.session.user_id"); // UserId
            String sysCd      = (String)session.getAttribute("secfw.session.sys_cd");  // SysCd
            String compCd = (String)session.getAttribute("secfw.session.comp_cd");

            List userInfo = null;

            try {

                // get user information
                userInfo = userService.getClmsUserInfo(userId, sysCd);

                if (userInfo == null || userInfo.size() == 0) {
                    mav = new ModelAndView();
                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                    mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.invalidUser", null, new RequestContext(request).getLocale()));
                    mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.invalidUserMessage", null, new RequestContext(request).getLocale()));

                    return mav;
                }

                // get contract detail
                CompletionVO cVO = new CompletionVO();
                cVO.setCntrt_id(sID);
                List contract = this.completionService.listContract2(cVO);
                if(contract == null || contract.size() == 0){
                    mav = new ModelAndView();
                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                    mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noContract", null, new RequestContext(request).getLocale()));
                    mav.addObject("secfw.alert.message", messageSource.getMessage("sedfw.page.field.alert.noContractMessage", null, new RequestContext(request).getLocale()));

                    return mav;
                }

                // compare comp_cd
                ListOrderedMap c = (ListOrderedMap)contract.get(0);
                ListOrderedMap o = (ListOrderedMap)userInfo.get(0);
                String cCompCd = c.get("COMP_CD").toString();
                String oCompCd = o.get("COMP_CD").toString();

                if(!cCompCd.equals(oCompCd)){
                    mav = new ModelAndView();
                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                    mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noPermision", null, new RequestContext(request).getLocale()));
                    mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale()));

                    return mav;
                }

                // get user role
                HashMap hm = new HashMap();
                hm.put("sys_cd", "LAS");
                hm.put("user_id", userId);
                List userRoleList = userService.listUserRole(hm);
                if(userRoleList == null || userRoleList.size() == 0){
                    mav = new ModelAndView();
                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                    mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noPermision", null, new RequestContext(request).getLocale()));
                    mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale()));

                    return mav;
                }

                String cnsdreq_id = c.get("CNSDREQ_ID").toString();
                redirectUrl = "/clm/manage/completion.do?method=getContractDetail&menu_id=20130319154642301_0000000355&cnsdreq_id="+cnsdreq_id;
                mav = new ModelAndView("redirect:"+redirectUrl);

                if(from != null && from != ""){
                    session.setAttribute("sys_type", from);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

            if("SEF".equals(compCd)){
                session.setAttribute("secfw.session.locale",          "fr_FR.EUC-KR");
			/* } else if("SEG".equals(compCd)){
				session.setAttribute("secfw.session.locale",          "de_DE.EUC-KR");   */
            } else{
                session.setAttribute("secfw.session.locale",          "en_US.EUC-KR");
            }

            /***********************************************************************************
             * 사용자 로케일정보 세팅
             ***********************************************************************************/
            setUserLoginLocale(request, response, session);

            try {
                insertLoginLog(request, session);
            } catch(Exception ex) {}

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    /**
     * 개인정보활용 동의 후 intro 화면 이동
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView goIntroAfterTermsAgreeCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = null;

        try {
            HttpSession session = request.getSession(true);

            String user_id = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
            String sys_cd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

            // 사용자 정보 업데이트
            userService.upateGthrCnstYn(user_id);

            /********************************************************************************
             * 로그인 현황
             *******************************************************************************/
            String lastConnectIP   = "";
            String lastConnectTime = "";

            try {
                List recentUserInfoList = logService.listRecentUserInfo(user_id, sys_cd, StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));

                if (recentUserInfoList != null && recentUserInfoList.size() > 0) {
                    ListOrderedMap recentUserInfoMap = (ListOrderedMap)recentUserInfoList.get(0);

                    lastConnectIP   = (String)recentUserInfoMap.get("ip_address");
                    lastConnectTime = (String)recentUserInfoMap.get("login_dt");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

            mav = new ModelAndView();

            // intro화면에서 바로가기 다운로드 받기위한 설정
            String strUrl       = (request.getRequestURL()).toString();
            String strAttachUrl = StringUtil.bvl(strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=","");

//			String accessYn = userService.GetAccessYn();
            mav.setViewName("/las/intro.do"); //법무 시스템
//			mav.setViewName("/las/introNotice.do"); //법무 시스템
            mav.addObject("strAttachUrl", strAttachUrl);
            mav.addObject("lastConnectIP", lastConnectIP);
            mav.addObject("lastConnectTime", lastConnectTime);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    /**
     * Link 시 Login처리 - SSO, Session세팅
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView clmsLinkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = null;

        try {
            //시스템 작업 공지 페이지로 바로 이동한다.
            String x = StringUtil.bvl((String)request.getParameter("x"), "");
            if("o".equals(x)){
                mav = new ModelAndView();
                mav.setViewName("/WEB-INF/jsp/common/SystemAlert.jsp"); //공지페이지로 이동
            }else{

                mav = new ModelAndView();

                String cntrtGbn = (String)request.getParameter("cntrt_gbn");
                String SysNm = "";
                String KeyId = "";
                String CntrtId = "";
                String CntrtNo = "";

                boolean linkYn = true;
                boolean paramYn = true;

                String forwardURL = "";

                /**
                 * 검토 : Consideration
                 * 등록 : Conclusion
                 * 체결 : Consultation
                 * 이행 : Execution
                 * 종료 : Completion
                 * 계약상세 : ContractD
                 * 계약목록(접속자) : Contract
                 * 계약목록(사업부) : ContractBu
                 * 나의계약건 : myContract
                 * 파일다운로드시 세션정보가 없을경우 세션처리 : filedown_login
                 */

                String sReturnUrl = "";  // 임직원 포탈에서의 연계시 리턴 URL


                //연계시스템 구분
                String infsysnm = StringUtil.bvl((String)request.getParameter("infsysnm"), "");

                if("Consideration".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/consideration.do?method=listManageConsideration&menu_id=20110803091536879_0000000048";
                }else if("Conclusion".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/conclusion.do?method=listManageConclusion&menu_id=20110803091537216_0000000051";
                }else if("Consultation".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/consultation.do?method=listManageConsultation&menu_id=20110803091537108_0000000050";
                }else if("Execution".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/execution.do?method=listManageExecution&menu_id=20110803091537331_0000000052";
                }else if("Completion".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/completion.do?method=listManageCompletion&menu_id=20110803091537445_0000000053";
                }else if("Contract".equals(cntrtGbn)){//조회조건이 계약명 한가지
                    sReturnUrl = (String)request.getParameter("returnURL");

                    forwardURL = "/clm/manage/contract.do?method=listContractPop&menu_id=20120125133142318_0000000300&sReturnUrl="+sReturnUrl;
                }else if("ContractBu".equals(cntrtGbn)){//조회조건이 많음
                    sReturnUrl = (String)request.getParameter("returnURL");

                    //운영에서 e-생기와 임직원포탈로 호출되고있음...
                    ///////////////////////////////////////////////

                    // 입력받는 URL은 미리 정해진 URL의 order로 받는다.
                    if(sReturnUrl!=null
                            &&!"".equals(sReturnUrl)
                            &&!(sReturnUrl.contains("http://portal.secportal.co.kr")
                            ||sReturnUrl.contains("http://mtech.sec.samsung.net"))){
                        linkYn = false;
                    }

                    ////////////////////////////////////////////////

                    forwardURL = "/clm/manage/contract.do?method=linkContractPop&menu_id=20120112203227505_0000000288&sReturnUrl="+sReturnUrl;
                }else if("ShortContractIRP".equals(cntrtGbn)){
                    KeyId = (String)request.getParameter("key_id");

                    //조회조건 디폴트(srch_state) => 계약체결(2)
                    forwardURL = "/clm/manage/contract.do?method=listContractIRPPop&menu_id=20120125133142318_0000000300&key_id="+KeyId+"&srch_state=2";
                }else if("ShortContractIF".equals(cntrtGbn)){
                    /**
                     * SSEMS 인터페이스를 간결화시키기 위해서 추가한 로직
                     * 기존의 SSEMS 일반계약방식을 폐기하고 프로세스를 틀기로했음
                     * 1. CLM 화면에서 처음부터 진행함
                     * 2. CLM 화면에서 체결본등록이 끝남
                     * 3. 그때 SSEMS에서 링크를 걸어서 본 URL을 호출(http://clm.sec.samsung.net:8080/link.do?method=linkSsoCheck&cntrt_gbn=ShortContractIF&cntrt_no=HHP50_311202&ssems_oppnt_cd=KOR&infsysnm=SSEMS)
                     * 4. SSEMS로 계약서 가져오기 버튼을 누르면 체결본저장정보를 TN_INF_COPY_SENDINFO 에 쌓는다, 단 KEY_ID는 없으므로 CNTRT_NO로 대체한다
                     */

                    CntrtNo = StringUtil.bvl((String)request.getParameter("cntrt_no"), "");

                    //CntrtNo 로 계약id 가져오기
                    HashMap hmCntrtNo = new HashMap();
                    hmCntrtNo.put("cntrt_no", CntrtNo);

                    HashMap cntrtIdMap = this.getCntrtIDByCntrtNO(hmCntrtNo);

                    if(cntrtIdMap != null && !cntrtIdMap.isEmpty() && cntrtIdMap.size()>0){

                        CntrtId = (String)cntrtIdMap.get("cntrt_id");
                        String ssems_oppnt_cd = StringUtil.bvl((String)request.getParameter("ssems_oppnt_cd"), "");
                        String prcsDepth = (String)cntrtIdMap.get("prcs_depth");

                        //파라미터가 없을 경우 에러
                        //SSEMS샘플의뢰상태: http://clm.sec.samsung.net:8080/link.do?method=linkSsoCheck&cntrt_gbn=ShortContractIF&cntrt_id=20120624181065400&infsysnm=SSEMS
                        //원본미등록상태: http://clm.sec.samsung.net:8080/link.do?method=linkSsoCheck&cntrt_gbn=ShortContractIF&cntrt_id=20120403210075621&infsysnm=SSEMS
                        if(!"SSEMS".equals(infsysnm) || "".equals(CntrtId)){
                            linkYn = false;

                        }else{

                            HashMap hm = new HashMap();
                            hm.put("cntrt_id", CntrtId);

                            HashMap cntrt = getCnsdReqIdByCntrtID(hm);
                            String cnsdReqId = (String)cntrt.get("cnsdreq_id");

                            //forwardURL = "/clm/manage/conclusion.do?method=detailConclusion&menu_id=20110803091537216_0000000051&cnsdreq_id="+cnsdReqId+"&ssems_oppnt_cd="+ssems_oppnt_cd;


                            //해당 의뢰ID를 찾지 못한 경우 에러
                            if("".equals(cnsdReqId)){
                                linkYn = false;
                            }else{
                                //등록
                                if ("C02503".equals(prcsDepth)){
                                    forwardURL = "/clm/manage/conclusion.do?method=detailConclusionNew&menu_id=20110803091537216_0000000051&cnsdreq_id="+cnsdReqId+"&ssems_oppnt_cd="+ssems_oppnt_cd;
                                    //이행
                                }else if ("C02504".equals(prcsDepth)){
                                    forwardURL = "/clm/manage/execution.do?method=listContract&menu_id=20110803091537331_0000000052&cnsdreq_id="+cnsdReqId+"&ssems_oppnt_cd="+ssems_oppnt_cd;
                                    //종료
                                }else if ("C02505".equals(prcsDepth)){
                                    forwardURL = "/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&cnsdreq_id="+cnsdReqId+"&ssems_oppnt_cd="+ssems_oppnt_cd;
                                }else{
                                    linkYn = false;
                                }
                            }

                        }
                    }else{
                        linkYn = false;
                    }

                }else if("ContractD".equals(cntrtGbn)){
                    SysNm = StringUtil.bvl((String)request.getParameter("sys_nm"), "");
                    KeyId = StringUtil.bvl((String)request.getParameter("key_id"), "");

                    //파라미터가 없을 경우 에러
                    if("".equals(SysNm) || "".equals(KeyId)){
                        linkYn = false;
                    }else{
                        //의뢰ID찾기
                        HashMap hm = new HashMap();
                        hm.put("sys_nm", SysNm);
                        hm.put("key_id", KeyId);
                        HashMap cntrt = getCnsdReqId(hm);
                        String cnsdReqId = (String)cntrt.get("cnsdreq_id");
                        String prcsDepth = (String)cntrt.get("prcs_depth");

                        //해당 의뢰ID를 찾지 못한 경우 에러
                        if("".equals(cnsdReqId)){
                            linkYn = false;
                        }else{
                            //검토
                            if("C02501".equals(prcsDepth)){
                                forwardURL = "/clm/manage/consideration.do?method=detailConsiderationNew&menu_id=20110803091536879_0000000048&cnsdreq_id="+cnsdReqId;
                                //체결
                            }else if ("C02502".equals(prcsDepth)){
                                forwardURL = "/clm/manage/consultation.do?method=detailConsultationNew&menu_id=20110803091537108_0000000050&cnsdreq_id="+cnsdReqId;
                                //등록
                            }else if ("C02503".equals(prcsDepth)){
                                forwardURL = "/clm/manage/conclusion.do?method=detailConclusionNew&menu_id=20110803091537216_0000000051&cnsdreq_id="+cnsdReqId;
                                //이행
                            }else if ("C02504".equals(prcsDepth)){
                                forwardURL = "/clm/manage/execution.do?method=listContract&menu_id=20110803091537331_0000000052&cnsdreq_id="+cnsdReqId;
                                //종료
                            }else if ("C02505".equals(prcsDepth)){
                                forwardURL = "/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&cnsdreq_id="+cnsdReqId;
                            }
                        }
                    }
                }else if("myContract".equals(cntrtGbn)){
                    forwardURL = "/clm/manage/myContract.do?method=listMyContract&menu_id=20110803091536533_0000000045&linkViewFlag=self";
                }else if("filedown_login".equals(cntrtGbn)){
                    forwardURL = (String)request.getParameter("secfw.forwardURL");
                }else{
                    linkYn = false;
                }

                if(linkYn){
                    HttpSession session = request.getSession(true);

                    //시스템
                    session.setAttribute("secfw.session.sys_cd",  "CLM");


                    /**
                     * 삼성전자 임직원에 대하여 SSO 정보를 읽어서, 세션에 저장
                     * SSO가능하면 true를 리턴.
                     */
                    boolean isValidSSO = processTrayInfo(request, session);
                    String userId     = (String)session.getAttribute("secfw.session.user_id");    // UserId
                    String sysCd      = (String)session.getAttribute("secfw.session.sys_cd");     // SysCd
                    String authCompCd = (String)session.getAttribute("secfw.session.authCompCd"); // AuthCompCd
                    String compCd = (String)session.getAttribute("secfw.session.comp_cd");

                    if(isValidSSO) { //정상


                        //SSO Timestamp 체크
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

                        Date beginDate = formatter.parse(trayLoginTimeformis);
                        SimpleDateFormat formatter1;

                        String pattern = "yyyy년 M월 d일 a h시 m분";
                        formatter1 = new SimpleDateFormat(pattern, new Locale("ko","KOREA")); // GMT → KST시간으로 변환

                        String traylogintime = "";
                        String syslogintime = "";

                        Date endDate = new Date(System.currentTimeMillis()); // 시스템 접속 시간
                        traylogintime = formatter1.format(beginDate); // Tray Login 시간
                        syslogintime = formatter1.format(endDate);

                        // 시스템 접속 시간 - Tray Login 시간 차 계산
                        long Diff = (endDate.getTime() - beginDate.getTime()) / (60 * 60 * 1000);

                        //시스템 보안 정책에 맞게 시간차 설정(24시간)
                        if(Diff < 24){ // 접속 허용!!

                            /********************************************************************************
                             * 사용자테이블에 사용자가 있는 경우에 한하여 로그인 처리(1차오픈범위)
                             *******************************************************************************/

                            /**
                             *  시스템에 들어올 수 있는 사용자 인지 판별
                             * - 회사정보 및 디비에 기등록된 사람
                             */
                            List userInfo = null;
                            try{
                                userInfo = userService.getClmsUserInfo(userId, sysCd);
                            }catch(Exception e) {
                                e.printStackTrace();
                            }

                            boolean loginAccess = false;
                            if(userInfo != null && userInfo.size() > 0){ //사용자 정보가 있으면 로그인 가능
                                loginAccess = true;
                            }

                            if(loginAccess){ //사용자 정보가 있을 경우 Update! 후 로그인 진행

                                //사용자 정보 UPDATE
                                try{
                                    userInfo = userService.processClmsUserInfo(userId, (String)session.getAttribute("secfw.session.sys_cd"), "U");
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                                if(userInfo != null){

                                    /***********************************************************************************
                                     * 역할정보 세팅
                                     ***********************************************************************************/
                                    //역할(role) 정보 셋팅(기본 RZ00)으로 셋팅!!
                                    try{
                                        setUserRole(userId, "RZ00", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                }else{
                                    loginAccess = false;
                                }

                            }else{ //없을 경우 경우의 수 계산

                                /**
                                 * EP_TRAY의 부서코드를 가지고 소속조직을 구한다.
                                 */
                                String blngtOrgnz = "";
                                blngtOrgnz = searchBlngtOrgnz(request, StringUtil.bvl(session.getAttribute("secfw.session.dept_cd"), ""), userId);

//								//단계적 오픈을 위해 정해진 사업부서만 로그인을 허용한다.
//								//VD사업부, 무선사업부, 메모리사업부, Media Solution 센터 이면  insert!
//								if("B00000001".equals(blngtOrgnz) || "B00000002".equals(blngtOrgnz) || "B00000003".equals(blngtOrgnz) || "B00000017".equals(blngtOrgnz)){
//
//									//사용자 정보 INSERT
//									try{
//										userInfo = userService.processClmsUserInfo(userId, (String)session.getAttribute("secfw.session.sys_cd"), propertyService.getProperty("secfw.admin.epid"), "I");
//									}catch(Exception e){
//										e.printStackTrace();
//									}
//
//									/***********************************************************************************
//									 * 역할정보 세팅
//									 ***********************************************************************************/
//									//역할(role) 정보 셋팅(계약담당자(RD02) role로 셋팅!!)
//									try{
//										setUserRole(userId, "RD02", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
//									}catch(Exception e){
//										e.printStackTrace();
//									}
//
//									loginAccess = true; // insert 후 로그인 진행
//								}

                                //2012-04-01 전사 오픈!!
                                //국내사업장이 아닌 단위조직은 접근을 통제한다.
                                //북미총괄,구주총괄,중국총괄,동남아총괄,서남아총괄,CIS총괄,중동총괄,아프리카총괄,중남미총괄,SEJ,
                                //미주총괄(DS),구주총괄(DS),중국총괄(DS),동남아총괄(DS),SSW(DS),베트남복합단지,중국본사,일본총괄(DS), Open Innovation센터 접근통제
                                if("B00000070".equals(blngtOrgnz) || "B00000071".equals(blngtOrgnz) || "B00000072".equals(blngtOrgnz) || "B00000073".equals(blngtOrgnz) || "B00000074".equals(blngtOrgnz) ||
                                        "B00000075".equals(blngtOrgnz) || "B00000076".equals(blngtOrgnz) || "B00000077".equals(blngtOrgnz) || "B00000078".equals(blngtOrgnz) || "B00000079".equals(blngtOrgnz) ||
                                        "B00000080".equals(blngtOrgnz) || "B00000081".equals(blngtOrgnz) || "B00000082".equals(blngtOrgnz) || "B00000083".equals(blngtOrgnz) || "B00000084".equals(blngtOrgnz) ||
                                        "B00000085".equals(blngtOrgnz) || "B00000086".equals(blngtOrgnz) || "B00000088".equals(blngtOrgnz) || "B00000089".equals(blngtOrgnz) ){

                                    loginAccess = false; //로그인 통제

                                }else{
                                    //사용자 정보 INSERT
                                    try{
                                        userInfo = userService.processClmsUserInfo(userId, (String)session.getAttribute("secfw.session.sys_cd"), "I");
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                    if(userInfo != null){

                                        /***********************************************************************************
                                         * 역할정보 세팅
                                         ***********************************************************************************/
                                        //역할(role) 정보 셋팅(계약담당자(RD02) role로 셋팅!!)
                                        try{
                                            setUserRole(userId, "RD02", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }

                                        loginAccess = true; // insert 후 로그인 진행

                                    }else{
                                        loginAccess = false;
                                    }
                                }
                            }


                            if(loginAccess) {

                                ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);

                                /***********************************************************************************
                                 * 사용자 정보 추가 Session Setting
                                 ***********************************************************************************/
                                //사업부코드: 신남원 : 2011.09.05
                                session.setAttribute("secfw.session.division_cd", StringUtil.bvl((String)lom.get("division_cd"),""));
                                //내부 부서코드
                                session.setAttribute("secfw.session.in_dept_cd", StringUtil.bvl((String)lom.get("in_dept_cd"), ""));

                                // 2012.05.29 담당사업부코드 세션 추가 added by hanjihoon
                                session.setAttribute("secfw.session.clms_user_orgnz",   (String)lom.get("clms_user_orgnz"));
                                session.setAttribute("secfw.session.resp_operdiv",   (String)lom.get("resp_operdiv"));

                                /***********************************************************************************
                                 * 사용자 로케일정보 세팅
                                 ***********************************************************************************/
                                setUserLoginLocale(request, response, session);

                                /***********************************************************************************
                                 * 역할(Role)정보 세션에 셋팅
                                 ***********************************************************************************/
                                HashMap roleListMap = new HashMap();
                                roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                                roleListMap.put("user_id", userId);

                                List roleUserList = userService.listUserRole(roleListMap);
                                ArrayList roleList = new ArrayList();
                                ArrayList bmRoleList = new ArrayList();

                                if(roleUserList != null && roleUserList.size() >0) {
                                    for(int i=0;i<roleUserList.size();i++){
                                        ListOrderedMap roleListLom = (ListOrderedMap)roleUserList.get(i);

                                        HashMap roleMap = new HashMap();

                                        roleMap.put("role_cd", (String)roleListLom.get("role_cd"));
                                        roleMap.put("role_nm", (String)roleListLom.get("role_nm"));

                                        roleList.add(roleMap);
                                        bmRoleList.add((String)roleListLom.get("role_cd"));
                                    }
                                }
                                session.setAttribute("secfw.session.roleList", bmRoleList);
                                session.setAttribute("secfw.session.role",  roleList);

                                /***********************************************************************************
                                 * 권한(Auth)정보 세션에 셋팅
                                 ***********************************************************************************/
                                List authUserList = userService.listUserAuth(roleListMap);
                                ArrayList authList = new ArrayList();

                                if(authUserList != null && authUserList.size() >0) {
                                    for(int i=0; i<authUserList.size(); i++) {
                                        ListOrderedMap authListLom = (ListOrderedMap)authUserList.get(i);

                                        HashMap authMap = new HashMap();

                                        authMap.put("auth_cd", (String)authListLom.get("auth_cd"));
                                        authMap.put("auth_nm", (String)authListLom.get("auth_nm"));

                                        authList.add(authMap);
                                    }
                                }

                                session.setAttribute("secfw.session.auth",  authList);

                                /***********************************************************************************
                                 * Property 에 설정된 관리자와 동일인이면 세션 사용자레벨에 시스템 관리자 권한으로 세팅
                                 ***********************************************************************************/
                                setSystemAdminRole(request, session, userId);

                                String dept_cd = StringUtil.bvl((String)lom.get("dept_cd"), "");
                                String auth_apnt_yn = StringUtil.bvl((String)lom.get("auth_apnt_yn"), "");
                                String comp_cd = StringUtil.bvl((String)lom.get("comp_cd"), "");
                                String emp_no = StringUtil.bvl((String)lom.get("emp_no"), "");
                                String blngt_orgnz = StringUtil.bvl((String)lom.get("blngt_orgnz"), "");
                                String auth_apnt_dept = StringUtil.bvl((String)lom.get("auth_apnt_dept"), "");

                                //로그인시 자동update여부(특정사용자는 N으로 처리되어있다)
                                String auto_rnew_yn = StringUtil.bvl((String)lom.get("auto_rnew_yn"), "Y");

                                /***********************************************************************************
                                 * 현재 소속 부서의 상위 부서들을 구분자(^)를 붙여서 문자열로 세션에 셋팅한다.
                                 ***********************************************************************************/
                                setUpDeptInfo(request, session, dept_cd);

                                /***********************************************************************************
                                 * 소속 조직 코드를 세션에 셋팅
                                 ***********************************************************************************/
                                setBlngtOrgnz(request, session, dept_cd, userId, blngt_orgnz, auto_rnew_yn);

                                /***********************************************************************************
                                 * 조직장 여부를 세션에 셋팅
                                 ***********************************************************************************/
                                setManagerYN(request, session, auth_apnt_yn, dept_cd, emp_no);

                                /***********************************************************************************
                                 * 권한지정여부 및 권한지정부서 세션에 셋팅
                                 ***********************************************************************************/
                                session.setAttribute("secfw.session.auth_apnt_yn", auth_apnt_yn);
                                session.setAttribute("secfw.session.auth_apnt_dept", auth_apnt_dept);

                                /***********************************************************************************
                                 * 지원부서 여부를 세션에 셋팅
                                 ***********************************************************************************/
                                setSupportYN(request, session, StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), ""), dept_cd);

                                /********************************************************************************
                                 * 연계시스템 구분 세션에 셋팅
                                 *******************************************************************************/
                                session.setAttribute("secfw.session.infsysnm", infsysnm);

                                /********************************************************************************
                                 * 로그인 사용현황 등록
                                 *******************************************************************************/
                                String lastConnectIP   = "";
                                String lastConnectTime = "";

                                try{

                                    List recentUserInfoList = logService.listRecentUserInfo(userId, StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""), StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));

                                    if(recentUserInfoList != null && recentUserInfoList.size()>0) {

                                        ListOrderedMap recentUserInfoMap = (ListOrderedMap)recentUserInfoList.get(0);

                                        lastConnectIP   = (String)recentUserInfoMap.get("ip_address");
                                        lastConnectTime = (String)recentUserInfoMap.get("login_dt");
                                    }

                                    insertLoginLog(request, session);

                                }catch(Exception e) {
                                    e.printStackTrace();
                                }

                                ///////////////////////////////////////////////////////
                                // 2012-08-24
                                // 사업부코드 못찾아오는 사람들은 로그인을 막는다.
                                // - 해외부서 사용자, 부서테이블에 해당부서코드가 없는 사용자, 기타 불확실한 이유로 사업부코드가 제대로 맵핑이 안된 사용자
                                String blngtOrgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
                                if("".equals(blngtOrgnz)){
                                    /*********************************************************
                                     * ModelAndView
                                     **********************************************************/
                                    mav = new ModelAndView();

                                    /** 구 법무로 이동 **/
                                    //mav.setViewName("/WEB-INF/jsp/secfw/user/ForwardOldLegal.jsp");

                                    String alertTitle = "";
                                    String alertMessage = "";

                                    // 메시지처리 - 사용자 정보가 없습니다.
                                    alertTitle = messageSource.getMessage("secfw.page.field.alert.accessBarredTitle", null, new RequestContext(request).getLocale());
                                    // 메시지처리 - 본시스템은 임직원을 대상으로 하는 시스템입니다. 접속이 차단되는 임직원께서는 아래 연락처로 문의하시기 바랍니다.
                                    alertMessage = messageSource.getMessage("secfw.page.field.alert.accessBarredBody", null, new RequestContext(request).getLocale());


                                    /*********************************************************
                                     * ModelAndView
                                     **********************************************************/
                                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                                    mav.addObject("secfw.alert.title", alertTitle);
                                    mav.addObject("secfw.alert.message", alertMessage);
                                }else{


                                    //개인정보 활용동의 화면으로 이동
                                    //if("N".equals(StringUtil.bvl((String)lom.get("gthr_cnst_yn"), ""))){
                                    //	mav.setViewName("/WEB-INF/jsp/secfw/user/TermsAgreeCheck.jsp");
                                    //	mav.addObject("cntrt_gbn", cntrtGbn);
                                    //	mav.addObject("sys_nm", SysNm);
                                    //	mav.addObject("key_id", KeyId);
                                    //}else{
                                    mav.setViewName(forwardURL);
                                    //}
                                }
                            } else {// 홈페이지에 들어올 수 없는 사용자

                                /*********************************************************
                                 * ModelAndView
                                 **********************************************************/
                                mav = new ModelAndView();

                                /** 구 법무로 이동 **/
                                //mav.setViewName("/WEB-INF/jsp/secfw/user/ForwardOldLegal.jsp");

                                String alertTitle = "";
                                String alertMessage = "";

                                // 메시지처리 - 사용자 정보가 없습니다.
                                alertTitle = messageSource.getMessage("secfw.page.field.alert.accessBarredTitle", null, new RequestContext(request).getLocale());
                                // 메시지처리 - 본시스템은 임직원을 대상으로 하는 시스템입니다. 접속이 차단되는 임직원께서는 아래 연락처로 문의하시기 바랍니다.
                                alertMessage = messageSource.getMessage("secfw.page.field.alert.accessBarredBody", null, new RequestContext(request).getLocale());


                                /*********************************************************
                                 * ModelAndView
                                 **********************************************************/
                                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                                mav.addObject("secfw.alert.title", alertTitle);
                                mav.addObject("secfw.alert.message", alertMessage);

                            }

                        }else{ //SSO TimeStamp Error
                            String alertTitle = "";
                            String alertMessage = "";

                            // 메시지처리 - 마이싱글 로그인 후 24시간이 경과되어 시스템 접속이 불가합니다. 마이싱글 또는 전자통합인증(SSO) 재로그인 후 시스템을 사용하시기 바랍니다.
                            alertTitle = messageSource.getMessage("secfw.page.field.alert.ssoTimeStampError", null, new RequestContext(request).getLocale());
                            // 메시지처리 - 시스템 관리자에게 문의하십시오.
                            alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

                            /*********************************************************
                             * ModelAndView
                             **********************************************************/
                            mav = new ModelAndView();

                            mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                            mav.addObject("secfw.alert.title", alertTitle);
                            mav.addObject("secfw.alert.message", alertMessage);
                        }
                    } else { //SSO CHECK Error 발생

                        // 메시지처리 - SSO CHECK시 Error가 발생하였습니다.
                        String alertTitle = messageSource.getMessage("secfw.page.field.alert.ssoError", null, new RequestContext(request).getLocale());
                        // 메시지처리 - 시스템 관리자에게 문의하십시오.
                        String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

                        /*********************************************************
                         * ModelAndView
                         **********************************************************/
                        mav = new ModelAndView();

                        mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                        mav.addObject("secfw.alert.title", alertTitle);
                        mav.addObject("secfw.alert.message", alertMessage);
                    }
                    //설정되지 않은 링크 정보인 경우
                }else{
                    String alertTitle = "";
                    String alertMessage = "";

                    // 메시지처리 - 링크 정보가 없습니다.
                    alertTitle = messageSource.getMessage("secfw.page.field.alert.noLinkTitle", null, new RequestContext(request).getLocale());
                    // 메시지처리 - 시스템 관리자에게 문의하십시오.
                    alertMessage = messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale());

                    mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                    mav.addObject("secfw.alert.title", alertTitle);
                    mav.addObject("secfw.alert.message", alertMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;

    }

    /**
     * 개인정보활용 동의 후 각 개별 링크 화면 이동
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView goLinkAfterTermsAgreeCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = null;
        try{
            HttpSession session = request.getSession(true);

            String user_id = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
            String sys_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

            //사용자 정보 업데이트
            userService.upateGthrCnstYn(user_id);

            String cntrtGbn = (String)request.getParameter("cntrt_gbn");
            String SysNm = (String)request.getParameter("sys_nm");
            String KeyId = (String)request.getParameter("key_id");

            boolean linkYn = true;
            String forwardURL = "";

            /**
             * 검토 : Consideration
             * 등록 : Conclusion
             * 체결 : Consultation
             * 이행 : Execution
             * 종료 : Completion
             * 계약상세 : ContractD
             * 계약목록(접속자) : Contract
             * 계약목록(사업부) : ContractBu
             * 나의계약건 : myContract
             */

            if("Consideration".equals(cntrtGbn)){
                forwardURL = "/clm/manage/consideration.do?method=listManageConsideration&menu_id=20110803091536879_0000000048";
            }else if("Conclusion".equals(cntrtGbn)){
                forwardURL = "/clm/manage/conclusion.do?method=listManageConclusion&menu_id=20110803091537216_0000000051";
            }else if("Consultation".equals(cntrtGbn)){
                forwardURL = "/clm/manage/consultation.do?method=listManageConsultation&menu_id=20110803091537108_0000000050";
            }else if("Execution".equals(cntrtGbn)){
                forwardURL = "/clm/manage/execution.do?method=listManageExecution&menu_id=20110803091537331_0000000052";
            }else if("Completion".equals(cntrtGbn)){
                forwardURL = "/clm/manage/completion.do?method=listManageCompletion&menu_id=20110803091537445_0000000053";
            }else if("Contract".equals(cntrtGbn)){
                forwardURL = "/clm/manage/contract.do?method=listContractPop&menu_id=20110802182454521_0000000036";
            }else if("ContractBu".equals(cntrtGbn)){
                forwardURL = "/clm/manage/contract.do?method=listContractPop&menu_id=20110802182454521_0000000036";
            }else if("ContractD".equals(cntrtGbn)){
                SysNm = StringUtil.bvl((String)request.getParameter("sys_nm"), "");
                KeyId = StringUtil.bvl((String)request.getParameter("key_id"), "");

                //파라미터가 없을 경우 에러
                if("".equals(SysNm) || "".equals(KeyId)){
                    linkYn = false;
                }else{
                    //의뢰ID찾기
                    HashMap hm = new HashMap();
                    hm.put("sys_nm", SysNm);
                    hm.put("key_id", KeyId);
                    HashMap cntrt = getCnsdReqId(hm);
                    String cnsdReqId = (String)cntrt.get("cnsdreq_id");
                    String prcsDepth = (String)cntrt.get("prcs_depth");
                    //해당 의뢰ID를 찾지 못한 경우 에러
                    if("".equals(cnsdReqId)){
                        linkYn = false;
                    }else{
                        //검토
                        if("C02501".equals(prcsDepth)){
                            forwardURL = "/clm/manage/consideration.do?method=detailConsideration&menu_id=20110803091536879_0000000048&cnsdreq_id="+cnsdReqId;
                            //체결
                        }else if ("C02502".equals(prcsDepth)){
                            forwardURL = "/clm/manage/consultation.do?method=detailConsultation&menu_id=20110803091537108_0000000050&cnsdreq_id="+cnsdReqId;
                            //등록
                        }else if ("C02503".equals(prcsDepth)){
                            forwardURL = "/clm/manage/conclusion.do?method=detailConclusion&menu_id=20110803091537216_0000000051&cnsdreq_id="+cnsdReqId;
                            //이행
                        }else if ("C02504".equals(prcsDepth)){
                            forwardURL = "/clm/manage/execution.do?method=listContract&menu_id=20110803091537331_0000000052&cnsdreq_id="+cnsdReqId;
                            //종료
                        }else if ("C02505".equals(prcsDepth)){
                            forwardURL = "/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&cnsdreq_id="+cnsdReqId;
                        }
                    }
                }
            }else if("myContract".equals(cntrtGbn)){
                forwardURL = "/clm/manage/myContract.do?method=listMyContract&menu_id=20110803091536533_0000000045";
            }else{
                linkYn = false;
            }
            mav = new ModelAndView();

            if(linkYn){
                mav.setViewName(forwardURL);
            }else{
                String alertTitle = "";
                String alertMessage = "";

                // 메시지처리 - 링크 정보가 없습니다.
                alertTitle = messageSource.getMessage("secfw.page.field.alert.noLinkTitle", null, new RequestContext(request).getLocale());
                // 메시지처리 - 시스템 관리자에게 문의하십시오.
                alertMessage = messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale());

                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

                mav.addObject("secfw.alert.title", alertTitle);
                mav.addObject("secfw.alert.message", alertMessage);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    /**
     * 상세화면 연계시 시스템과 키값으로 의뢰 ID 찾기
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private HashMap getCnsdReqId(HashMap hm) throws Exception {
        return userService.getCnsdReqId(hm);
    }

    /**
     * 상세화면 연계시 계약id값으로 의뢰 ID 찾기
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private HashMap getCnsdReqIdByCntrtID(HashMap hm) throws Exception {
        return userService.getCnsdReqIdByCntrtID(hm);
    }

    /**
     * 상세화면 연계시 계약NO값으로 계약 ID 찾기
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private HashMap getCntrtIDByCntrtNO(HashMap hm) throws Exception {
        return userService.getCntrtIDByCntrtNO(hm);
    }

    /**
     * 사용자선택 로그인
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView clmsSelLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardURL = "/WEB-INF/jsp/secfw/common/ClmsSelLoginPage.jsp";

        //Session 정보가 없는 경우, login.do 로 redirect
        HttpSession session=request.getSession(false);

        if(session==null || StringUtils.isEmpty((String)session.getAttribute("secfw.session.user_id"))){
            return new ModelAndView("redirect:/login.do");
        }

        // Get roles from session
        List <Map<String, String>> roleList=(List<Map<String, String>>) session.getAttribute("secfw.session.role");


        boolean hasSystemAdminRole=false;

        for(Map<String, String> role: roleList){
            if("RA00".equals(role.get("role_cd"))){
                hasSystemAdminRole=true;
                break;
            }
        }

        /*********************************************************
         * Return Value
         **********************************************************/
        ModelAndView mav = new ModelAndView();
        String f = StringUtil.bvl((String)request.getParameter("f"), "");

        if (hasSystemAdminRole) {
            mav.setViewName(forwardURL);
            mav.addObject("f", f);
        } else {
            mav.setViewName("redirect:/login.do");
        }

        return mav;
    }

    /**
     * 사용자선택 로그인 처리
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView clmsSelLoginView(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = null;
        HttpSession session=request.getSession(false);

        if(session==null || StringUtils.isEmpty((String)session.getAttribute("secfw.session.user_id"))){
            return new ModelAndView("redirect:/login.do");
        }
        boolean hasSystemAdminRole=false;

        // Get roles from session
        @SuppressWarnings("unchecked")
        List <Map<String, String>> roleList=(List<Map<String, String>>) session.getAttribute("secfw.session.role");



        for(Map<String, String> role: roleList){
            if("RA00".equals(role.get("role_cd"))){
                hasSystemAdminRole=true;
                break;
            }
        }

        if (!hasSystemAdminRole) {
            return new ModelAndView("redirect:/login.do");
        }

        try {
            //시스템 작업 공지 페이지로 바로 이동한다.
            String x = StringUtil.bvl((String)request.getParameter("x"), "");
            if("o".equals(x)){
                mav = new ModelAndView();
                mav.setViewName("/WEB-INF/jsp/common/SystemAlert.jsp"); //공지페이지로 이동
            }else{
                String loginId = (String)request.getParameter("login_id");
                String loginPwd = (String)request.getParameter("login_pwd");

                /*********************************************************
                 * Forwarding URL
                 **********************************************************/
                String forwardURL = "";

                mav = new ModelAndView();

                String id = "selmsplus";
                String pwd = "godnth";

                if(id.equals(loginId) && pwd.equals(loginPwd)){
                    forwardURL = "/secfw/ssoCheck.do?method=clmsSelLoginList";
                }else{
                    forwardURL = propertyService.getProperty("secfw.url.alertPage");
                    // 메시지처리 - 아이디 또는 패스워드가 틀렸습니다.
                    String alertTitle = messageSource.getMessage("secfw.page.field.alert.noIdPwdMessage", null, new RequestContext(request).getLocale());
                    // 메시지처리 - 시스템 관리자에게 문의하십시오.
                    String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
                    mav.addObject("secfw.alert.title", alertTitle);
                    mav.addObject("secfw.alert.message", alertMessage);
                }

                mav.setViewName(forwardURL);
                String f = StringUtil.bvl((String)request.getParameter("f"), "");
                mav.addObject("f", f);

            }
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
     * 사용자선택 로그인 - 사용자 조회
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView clmsSelLoginList (HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            UserForm form = new UserForm();
            UserVO vo = new UserVO();

            bind(request, form);
            bind(request, vo);

            form.setSearch_name(StringUtil.bvl(form.getSearch_name(), ""));
            vo.setSearch_name(form.getSearch_name());

            /*********************************************************
             * Forwarding URL
             **********************************************************/
            String forwardURL = "/WEB-INF/jsp/secfw/common/ClmsSelUserList.jsp";

            ModelAndView mav = new ModelAndView();

            /*********************************************************
             * 페이지 객체
             **********************************************************/
            PageUtil pageUtil = new PageUtil();

            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

            vo.setStart_index(pageUtil.getStartIndex());
            vo.setEnd_index(pageUtil.getEndIndex());

            List resultList = userService.listTotalUser(vo);
            //조회 데이터 처리
            if (resultList != null && resultList.size() > 0) {
                ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

                pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
                pageUtil.setRowPerPage(10);
                pageUtil.setGroup();
            }

            mav.setViewName(forwardURL);
            mav.addObject("userList", resultList);
            mav.addObject("pageUtil", pageUtil);
            mav.addObject("command", form);
            String f = StringUtil.bvl((String)request.getParameter("f"), "");
            mav.addObject("f", f);

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
     * 사용자선택 로그인 처리
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView clmsSelLoginPrcs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = null;

        try {
            UserForm form = new UserForm();
            UserVO vo     = new UserVO();

            bind(request, form);
            bind(request, vo);

            HttpSession session = request.getSession(true);
            session.setAttribute("secfw.session.sys_cd",  "LAS");

			/*
			String f = StringUtil.bvl((String)request.getParameter("f"), "");
			if("c".equals(f)){
				session.setAttribute("secfw.session.sys_cd",  "CLM");
			}else{
				session.setAttribute("secfw.session.sys_cd",  "LAS");
			}
			*/

            String userId = StringUtil.bvl((String)request.getParameter("user_id"), ""); // userId
            String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");        // SysCd

            /********************************************************************************
             * 사용자테이블에 사용자가 있는 경우에 한하여 로그인 처리(1차오픈범위)
             *******************************************************************************/
            // 시스템에 들어올 수 있는 사용자 인지 판별 - 회사정보 및 디비에 기등록된 사람
            List userInfo = null;

            try {
                userInfo = userService.getClmsUserInfo(userId, sysCd);
            } catch(Exception e) {
                e.printStackTrace();
            }

            // 시스템에 들어올 수 있는 사용자
            if (userInfo != null && userInfo.size() > 0) {
                ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);

                /***********************************************************************************
                 * 사용자 Session Setting
                 ***********************************************************************************/
                session.setAttribute("secfw.session.user_id",         (String)lom.get("user_id"));
                session.setAttribute("secfw.session.emp_no",          (String)lom.get("emp_no"));
                session.setAttribute("secfw.session.comp_cd",         (String)lom.get("comp_cd"));
                session.setAttribute("secfw.session.comp_nm",         (String)lom.get("comp_nm"));
                session.setAttribute("secfw.session.comp_nm_en",      (String)lom.get("comp_nm_en"));
                session.setAttribute("secfw.session.dept_cd",         (String)lom.get("dept_cd"));
                session.setAttribute("secfw.session.dept_nm",         (String)lom.get("dept_nm"));
                session.setAttribute("secfw.session.dept_nm_en",      (String)lom.get("dept_nm_eng"));
                session.setAttribute("secfw.session.grade_cd",        (String)lom.get("jikgup_cd"));
                session.setAttribute("secfw.session.grade_nm",        (String)lom.get("jikgup_nm"));
                session.setAttribute("secfw.session.grade_nm_en",     (String)lom.get("jikgup_nm_eng"));
                session.setAttribute("secfw.session.user_nm",         (String)lom.get("user_nm_eng"));
                session.setAttribute("secfw.session.user_nm_en",      (String)lom.get("user_nm_eng"));
                session.setAttribute("secfw.session.email",           (String)lom.get("email"));
                session.setAttribute("secfw.session.single_id",       (String)lom.get("single_id"));

                /*******************************************************
                 * 2013.12.06
                 * 법인코드별 다국어 셋팅
                 * SEG		- "de"
                 * SEF		- "fr"
                 * 이외		- "en"
                 *******************************************************/
                // 2014-02-19 SEG 오픈으로, 주석처리된 부분 해제함.
                if("SEF".equals((String)lom.get("comp_cd"))){
                    session.setAttribute("secfw.session.locale",          "fr_FR.EUC-KR");
                }
		/*		else if("SEG".equals((String)lom.get("comp_cd"))){
					session.setAttribute("secfw.session.locale",          "de_DE.EUC-KR");
				}  */
                else{
                    session.setAttribute("secfw.session.locale",          "en_US.EUC-KR");
                }

                session.setAttribute("secfw.session.loginIP",         "1.1.1.1");
                session.setAttribute("secfw.session.timezone",        "GMT+0");
                session.setAttribute("secfw.session.summertimeyn",    "N");
                session.setAttribute("secfw.session.session_id",      StringUtil.bvl(session.getId(),""));
                session.setAttribute("secfw.session.comp_tel",        (String)lom.get("office_tel_no"));
                session.setAttribute("secfw.session.mobile",          (String)lom.get("mobile_no"));

                // 2012.05.29 담당사업부코드 세션 추가 added by hanjihoon
                session.setAttribute("secfw.session.clms_user_orgnz", (String)lom.get("clms_user_orgnz"));
                session.setAttribute("secfw.session.resp_operdiv",    (String)lom.get("resp_operdiv"));

                /***********************************************************************************
                 * 사용자 정보 추가 Session Setting
                 ***********************************************************************************/
                // 사업부코드 : 신남원 : 2011.09.05
                session.setAttribute("secfw.session.division_cd", StringUtil.bvl((String)lom.get("division_cd"),""));
                // 내부 부서코드
                session.setAttribute("secfw.session.in_dept_cd", StringUtil.bvl((String)lom.get("in_dept_cd"), ""));

                // 2013.03.28 사별 메뉴-권한에 따른 자회사 구분을 위한 세션 세팅 추가 by 전종효
                List listComps = userService.isExist_comp_cd(StringUtil.bvl((String)lom.get("comp_cd"), ""),userId);

                // 2014.0617 박병주 추가
                session.setAttribute("secfw.session.related_products",       (String)lom.get("related_products"));

                if (listComps.size() > 0) {// 12개사에 속하는 사람인 경우
                    ListOrderedMap listOrderMap = (ListOrderedMap)listComps.get(0);
                    String abbr_comp_nm = (String)listOrderMap.get("cd_abbr_nm");
                    String abbr_comp_nm_en = (String)listOrderMap.get("cd_abbr_nm_en");

                    session.setAttribute("secfw.session.auth_comp_cd", "'" + StringUtil.bvl((String)lom.get("comp_cd") + "'",""));
                    session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(abbr_comp_nm, ""));
                    session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(abbr_comp_nm_en, ""));
                    session.setAttribute("secfw.session.exist_comp_yn", "Y");
                } else {// 12개사에 속하지 않는 사람인 경우
                    HashMap<String, String> hMap = new HashMap<String, String>();
                    hMap.put("sys_cd", "LAS");
                    hMap.put("user_id", StringUtil.bvl((String)lom.get("user_id"),""));

                    // 2013.06.18 전자(C10) 계약지정인인 경우 모든 자회사의 계약건을 볼 수 있도록 추가 by 전종효
                    if ("C10".equals(session.getAttribute("secfw.session.comp_cd").toString())) {
                        HashMap authApntYnMap = userService.getAuthApntYn(hMap);

                        // 계약지정인인지 여부
                        if ("Y".equals((String)authApntYnMap.get("auth_apnt_yn"))) {
                            List listMngComps = userService.getMngComps();

                            if (listMngComps.size() > 0) {
                                ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                getLogger().info("mngCompCds(1) : [" + mngCompCds + "]");

                                session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                                session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
                                session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
                            }
                        } else {
                            List listMngComps = userService.getMngComps(hMap);

                            if (listMngComps.size() > 0) {// 전자검토자인 경우
                                List listRoles = userService.getUserSecRole(hMap);

                                if (listRoles.size() > 0) {// 전자임원인 경우
                                    listMngComps = userService.getMngComps();

                                    if (listMngComps.size() > 0) {
                                        ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                        String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                        getLogger().debug("mngCompCds(2) : [" + mngCompCds + "]");

                                        session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                                    }

                                    session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
                                    session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
                                } else {
                                    ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                    String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                    getLogger().info("mngCompCds(3) : [" + mngCompCds + "]");

                                    session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                                    session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
                                    session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
                                }
                            } else {// 2013.06.27 전자검토자이나 담당회사가 없거나 혹은 전자임원인 경우
                                List listRoles = userService.getUserSecRole(hMap);

                                if (listRoles.size() > 0) {// 전자임원인 경우
                                    listMngComps = userService.getMngComps();

                                    if (listMngComps.size() > 0) {
                                        ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                        String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                        getLogger().debug("mngCompCds(4) : [" + mngCompCds + "]");

                                        session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                                    }

                                    session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
                                    session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
                                } else {
                                    session.setAttribute("secfw.session.auth_comp_cd", "'C10'");
                                    session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
                                    session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
                                }
                            }
                        }
                    } else {// 기타 - 12개 회사에서 존재 하지 않으면 기존엔 '*'으로 처리하다가 테스트를 위해서 'EHQ' DS EHQ - Samsung Semiconductor Europe GmbH로 변경 처리함.
                        session.setAttribute("secfw.session.auth_comp_cd", "'EHQ'");
                        session.setAttribute("secfw.session.comp_cd", "EHQ");
                        session.setAttribute("secfw.session.abbr_comp_nm", "DS EHQ - Samsung Semiconductor Europe GmbH");
                        session.setAttribute("secfw.session.abbr_comp_nm_en", "EHQ");
                    }
					/*
					List listMngComps = userService.getMngComps(hMap);

					if (listMngComps.size() > 0) {// 전자검토자인 경우
						ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
						String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
						session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
						session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
						session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl((String)lom.get("comp_nm_eng"),""));
					} else {// 기타 - 12개 회사에서 존재 하지 않으면 기존엔 '*'으로 처리하다가 테스트를 위해서 'M20' 경제연구소로 변경 처리함. 김재원.!@#.20130415
						session.setAttribute("secfw.session.auth_comp_cd", "'M20'");
						session.setAttribute("secfw.session.comp_cd", "M20");
						session.setAttribute("secfw.session.abbr_comp_nm", "경제연구소");
						session.setAttribute("secfw.session.abbr_comp_nm_en", "SERI");
					}
					*/
                    session.setAttribute("secfw.session.exist_comp_yn", "N");
                }

                /***********************************************************************************
                 * 사용자 로케일정보 세팅
                 ***********************************************************************************/
                setUserLoginLocale(request, response, session);

                /***********************************************************************************
                 * 역할정보 세팅
                 ***********************************************************************************/
                try {
                    /********************************************************************************
                     * 사용자 역활 등록(역할이 없을경우 )
                     *******************************************************************************/
                    //String employee_type = (String)lom.get("employee_type");
                    setUserRole(userId, "RZ00", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                } catch(Exception e) {
                    e.printStackTrace();
                }

                // 역할정보 세션에 세팅
                HashMap roleListMap = new HashMap();
                roleListMap.put("sys_cd", StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
                roleListMap.put("user_id", userId);

                List roleUserList = userService.listUserRole(roleListMap);

                ArrayList roleList   = new ArrayList();
                ArrayList bmRoleList = new ArrayList();

                if (roleUserList != null && roleUserList.size() > 0) {
                    for (int i=0; i<roleUserList.size(); i++) {
                        ListOrderedMap roleListLom = (ListOrderedMap)roleUserList.get(i);

                        HashMap roleMap = new HashMap();
                        roleMap.put("role_cd", (String)roleListLom.get("role_cd"));
                        roleMap.put("role_nm", (String)roleListLom.get("role_nm"));

                        roleList.add(roleMap);
                        bmRoleList.add((String)roleListLom.get("role_cd"));
                    }
                }

                session.setAttribute("secfw.session.roleList", bmRoleList);
                session.setAttribute("secfw.session.role",  roleList);

                /***********************************************************************************
                 * 권한정보 세팅
                 ***********************************************************************************/
                List authUserList = userService.listUserAuth(roleListMap);
                ArrayList authList = new ArrayList();

                if (authUserList != null && authUserList.size() > 0) {
                    for (int i=0; i<authUserList.size(); i++) {
                        ListOrderedMap authListLom = (ListOrderedMap)authUserList.get(i);

                        HashMap authMap = new HashMap();
                        authMap.put("auth_cd", (String)authListLom.get("auth_cd"));
                        authMap.put("auth_nm", (String)authListLom.get("auth_nm"));

                        authList.add(authMap);
                    }
                }

                session.setAttribute("secfw.session.auth",  authList);

                /***********************************************************************************
                 * Property 에 설정된 관리자와 동일인이면 세션 사용자레벨에 시스템 관리자 권한으로 세팅
                 ***********************************************************************************/
                setSystemAdminRole(request, session, userId);

                String dept_cd        = StringUtil.bvl((String)lom.get("dept_cd"), "");
                String auth_apnt_yn   = StringUtil.bvl((String)lom.get("auth_apnt_yn"), "");
                //String comp_cd        = StringUtil.bvl((String)lom.get("comp_cd"), "");
                String emp_no         = StringUtil.bvl((String)lom.get("emp_no"), "");
                String blngt_orgnz    = StringUtil.bvl((String)lom.get("blngt_orgnz"), "");
                String auth_apnt_dept = StringUtil.bvl((String)lom.get("auth_apnt_dept"), "");

                // 로그인시 자동update여부(특정사용자는 N으로 처리되어있다)
                String auto_rnew_yn = StringUtil.bvl((String)lom.get("auto_rnew_yn"), "Y");

                /***********************************************************************************
                 * 현재 소속 부서의 상위 부서들을 구분자(^)를 붙여서 문자열로 세션에 셋팅한다.
                 ***********************************************************************************/
                setUpDeptInfo(request, session, dept_cd);

                /***********************************************************************************
                 * 소속 조직 코드를 세션에 셋팅
                 ***********************************************************************************/
                setBlngtOrgnz(request, session, dept_cd, userId, blngt_orgnz, auto_rnew_yn);

                /***********************************************************************************
                 * 조직장 여부를 세션에 셋팅
                 ***********************************************************************************/
                setManagerYN(request, session, auth_apnt_yn, dept_cd, emp_no);

                /***********************************************************************************
                 * 권한지정여부 및 권한지정부서 세션에 셋팅
                 ***********************************************************************************/
                session.setAttribute("secfw.session.auth_apnt_yn", auth_apnt_yn);
                session.setAttribute("secfw.session.auth_apnt_dept", auth_apnt_dept);

                /***********************************************************************************
                 * 지원부서 여부를 세션에 셋팅
                 ***********************************************************************************/
                setSupportYN(request, session, StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), ""), dept_cd);

                /********************************************************************************
                 * 연계시스템 구분 세션에 셋팅
                 *******************************************************************************/
                session.setAttribute("secfw.session.infsysnm", "");

                /********************************************************************************
                 * 로그인 사용현황 조회
                 *******************************************************************************/
                String lastConnectIP   = "";
                String lastConnectTime = "";

                try {
                    List recentUserInfoList = logService.listRecentUserInfo(userId, StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""), StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));

                    if (recentUserInfoList != null && recentUserInfoList.size() > 0) {
                        ListOrderedMap recentUserInfoMap = (ListOrderedMap)recentUserInfoList.get(0);

                        lastConnectIP   = (String)recentUserInfoMap.get("ip_address");
                        lastConnectTime = (String)recentUserInfoMap.get("login_dt");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

                /*********************************************************
                 * ModelAndView
                 **********************************************************/
                mav = new ModelAndView();
                String forwardURL = "";

                // intro화면에서 바로가기 다운로드 받기위한 설정
                String strUrl       = (request.getRequestURL()).toString();
                String strAttachUrl = StringUtil.bvl(strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=","");

                mav.setViewName("/las/intro.do"); //법무 시스템
//				mav.setViewName("/las/introNotice.do"); //법무 시스템
                mav.addObject("strAttachUrl", strAttachUrl);
                mav.addObject("lastConnectIP", lastConnectIP);
                mav.addObject("lastConnectTime", lastConnectTime);

            } else {// 홈페이지에 들어올 수 없는 사용자
                String alertTitle   = "";
                String alertMessage = "";

                // 메시지처리 - 사용자 정보가 없습니다.
                alertTitle = messageSource.getMessage("secfw.page.field.alert.noUserInfo", null, new RequestContext(request).getLocale());
                // 메시지처리 - 시스템 관리자에게 문의하십시오.
                alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

                mav = new ModelAndView();
                mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
                mav.addObject("secfw.alert.title", alertTitle);
                mav.addObject("secfw.alert.message", alertMessage);
            }

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
     * 사용자 검색
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Forwarding URL
             **********************************************************/
            String forwardURL = "";
            String multiYn = StringUtil.bvl((String)request.getParameter("multiChk"), "N");

            // 멀티로사용자를  체크할 수 있는 페이지로 갈지 결정
            if("N".equals(multiYn)) {
                forwardURL = "/WEB-INF/jsp/secfw/search/UserListPopup.jsp";
            } else {
                forwardURL = "/WEB-INF/jsp/secfw/search/UserListMultiCheckPopup.jsp";
            }

            /*********************************************************
             * Form 및 VO Binding
             **********************************************************/
            UserForm form = new UserForm();
            bind(request, form);

            UserVO vo = new UserVO();
            bind(request, vo);

            /*********************************************************
             * 검색여부 판별
             **********************************************************/
            boolean flag = false;
            String doUserSearch  = StringUtil.bvl((String)request.getParameter("doUserSearch"), "N");

            if("Y".equals(doUserSearch)) {
                flag = true;
            }

            /*********************************************************
             * 목록조회
             **********************************************************/
            List listUser = null;
            if(flag) {
                listUser = userService.listUser(vo);
            }
            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);

            mav.addObject("listUser", listUser);
            mav.addObject("command", form);

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
     * 사용자정보 검색-ESB
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getUserInfoESB(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Forwarding URL
             **********************************************************/
            String forwardURL = "/WEB-INF/jsp/secfw/user/UserInfo.jsp";

            /*********************************************************
             * Parameter Setting
             **********************************************************/
            HttpSession session = request.getSession(false);
            String userId     = StringUtil.bvl((String)request.getParameter("srch_user_id"), (String)session.getAttribute("secfw.session.user_id"));
            String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
            Locale lc         = (Locale)localeResolver.resolveLocale(request);
            String compCd = (String)session.getAttribute("secfw.session.comp_cd");

            /*********************************************************
             * 사용자정보 조회
             **********************************************************/
            Hashtable userInfoTable = null;
            if(userId != null && !"".equals(userId)) {
                // !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
                Vector drafterUserVector = esbOrgService.userSearchByEpid(userId, lc);

                if(drafterUserVector != null && drafterUserVector.size()>0) {
                    userInfoTable = (Hashtable)drafterUserVector.get(0);
                }
            }


            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);

            mav.addObject("userInfoTable", userInfoTable);

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
     * 부서 클릭시 인력 조회(JSON형식)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void listUserByDeptCd(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            /*********************************************************
             * Form 및 VO Binding
             **********************************************************/
            DeptVO vo = new DeptVO();
            bind(request, vo);

            /*********************************************************
             * 파라미터세팅
             **********************************************************/
            vo.setMenu_id(vo.getSelect_dept_cd());

            /*********************************************************
             * 목록 조회
             **********************************************************/
            JSONArray jsonArray = userService.listUserByDeptCd(vo);

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
     * 페이지 포워드.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView forwardPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            /*********************************************************
             * Forwarding URL
             **********************************************************/
            // 등록페이지로 Forwading
            String forwardURL = "";

            String pageNm = StringUtil.bvl((String)request.getParameter("forwardPage"), "");

            if("UserListByDept".equals(pageNm)) {
                forwardURL = "/WEB-INF/jsp/secfw/search/UserListByDept.jsp";
            }

            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);

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
     * SSO정보를 읽어서, 세션에 저장
     * 2013.03.28 사별 메뉴-권한 관리에 필요한 comp_cd 값 추가
     *            comp_cd의 중복을 피하기 위하여 사별 메뉴-권한 관리의 조건으로 사용되는 변수는 auth_comp_cd로 사용
     *            또한 12개 자회사 이외의 회사는 디폴트 값('*')으로 대체
     * <p>
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return SSO 정보가 존재하면 true, 존재하지 않으면 false
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private boolean processTrayInfo(HttpServletRequest request, HttpSession session) throws Exception {
        boolean flag = true;

        // mySingle tray 정보
        String strTotalData = request.getParameter("totaldata");
        String adminflag = request.getParameter("adminflag");
        if(adminflag != null && "localhost".equals(adminflag)){
            strTotalData="+8aXYGLvK4xhX1A5kFQCv1x3KkJwvFYzEfUXVgcwyxRLNE6g1+MuRN0otTx4f2KEg0sB16XtgHWfa4MS7WlkoilvgcRz3babs/H1CRHD9oxEIHdyVoIbF/N2eH74Nrap86Geslqnd/srFcFu5UghbwqggkmmoIE4XP+HLF6yGgBrPjGrAoPyIzBKog9L3Hwiy/QKTg0EgmLRQ3eOkw67dU2F8HQAV9Ai0MMGBIBFLRPfR/0gm3FfJGCnthxWLsObek+mumevUoc5FkNVeWb3oc7PuIhStPfBno9vgqZmqBG6UdhAGzb8rKda9DjEMgCZJ8Y8+xV4+OZf2b5jl61sxUws0bCHxahL8bE7Fjn0ToEuNNgJrwvBsan0JHKy7c1qcuBpQG108Q2PI91MNqcKVeyUru+ICAKKPnA4U51wW7dDBdGg9dKm89+XtnqGY3MRUSaCvafOxdW2UF2Y8VHy/k/Uj1Dp3q6+6N+dX3V3XGYwSqIPS9x8IlvDQgwuUkLiaD5XXp8oesRtrhLuJyY4CugaceprvcdfpHYkmBp814YKgm9FlabsFG2uEu4nJjgK6Bpx6mu9x1+zEayZTYd8p+mX6v/tSyfK7StqaRkuNGri51HtuYT/Jv37XhWEWHFzV/kM9KZY0MXs+gL3YBTuUQ7bcJZxt5TiJ9W5N2TTsal8vB0aypZzsJ0qrnlrY3MX8WwSVTeJmQBtsMj1pfmYb4yKW2HdVmTCDm73JAFKwoDouVCd+QCuzSWlppWOrCJ7U8Mw68Jv0kr8JS/klAmNaMHJMA+j5Y5rhvoT4eqTY8mS1KXthqvyfrGbcFBDjF7kW6zZvIjJx9rGTzdIbG+rSt+aH6UEw4FjlxzWdtDVHbkR3xrbr7CoPnlHsBFONWPtWdv8J0FI3MLk+Lp3pnlSd88/8cIpES4U6yPEMmXS/fTPjEyjbzgVEFg7akF6QeukifvIFwdIpie7L3tGUJuK245UOk3/eT08uJ5DrSUKH/vqE99+ty4mopJrr/YjX02dU22fsP69EBZdGJVCCIvANgJbj4ZCul1jwXUs8OFyZf/E5z/kDc/yJiddR9nEowwtqH+maYcUhQd9QHZq6VxXsi2/h/2VvdWKpcOu3y+RtxSv/ltvDyQsRqj+latJ66bd7pqM8nsMLilRGrIt1KN4u1JNt6HkIaFGiYjJqyy0RJE9fkuQb03eNzIL7sBigK0qbA8DzJogZwbEGHeYtSuuIHYfGZNvRxWmR30IX5OuTTBNn75CCyFxSgLOUrBrtJrohCy6T/cwIzp9mdIxPhnmPi+ozd77WJvBH2jj+U2stfxSMyefQCtccndpcxxANvMRRK3j62hWxqyrpBS+W96BT0tSjDqp8c1w5NB+lzD4fUs/aTT46DxORe2rxPhy37hlna7yZhvf6DHPUoZIyZAG9ZNojDId7NEjq695iF0ttEC0tNq+HYRT7SVKdBvZdmBlZrSiRD3DhcsUwxWXpyeA4pxSqEUHBrh+hwk6xsYi5ta/j1OFIaOIwRTDFZenJ4DiEyjumNiHW6PgC99yqIdMNJ6MEe4CgJp6rD8w3SD3Uxf9yLsPche+N/e6N+PjJquVqWXKXYfVNTc/fg6DR7ODk0ydOz4tHqstYjmhK/FQHcy0KcUXb2DHpi/CI8sOs2+ericMvJ5SBYdfoejzOh9PY3W69m+LGRbXibwv1fwV+YuYtwxUHVoWHGne8eFJ4SKmMc+rgD0id2vtkg2iQqx3UCNSOz2FfaY83SWsnS5C8hYv3PvI7xDrkMpXpegXlr3uQBrDomEn1ffIYXVaRcruPQ1Pt1qejyHY7jBfORdU65b7xpdgYu8rjCx8x3cKzorZAavJCItDZAFXWuNdpuhjMi8uUoJnLE2/NDCbCIODk1pR5cZ6P3A72gFXmCaDCDcvdHzvl/E/igULBzvupAqRcr8fx1nULjo1nbCeTNiBVrxPKY8cXQcIHJ85s5E3axLG0aWbp74YbqZmDPDY2B+l/1bh8PMgklXXVOJMAkvbt8jlbh3BHYb+KKBCP11hmaCxsLR/KLmINsr6BowVEzzeC+aSKtT0BPGPBG6VL3Z2z0UDo3l+m2IKeKy5zqmThXk0KrOKlbp36o71x0RHz323u4IDmHYV7Dbb2fLnZy+2soblVohNd0kUkORSwhmOkYdBShdb2baj5in8mp/i4NQa9z5l0jm9qwsskbEJ7Hu0SirIotEVsMmDpITwOu93kRl1hH0ZMU5oUo2yUQ/7cXJXi7rT0ZvObRcNOYqVfTNhU5gA2NvbTqr1H7yK54r8P6xPqlZcthLU9zxVgHJZr9J8xvqyFPO5pnsUcDpPMMxiDcJ9ZztQP4bgdZnb/fbOTjb/22ufdY51QMrueTEVpaXxDtc2Dc0ot54mBETVpBmaeDpn3vHA1CLemfnhAfApXbpYJ2S6eiMrL6fhscYmoXO4qYM0utj+ojf6g+AlynBkMEjsJwyGUzbF8xPSnE3cJvVtngjSseaCgf0f3MzkU1XzqcfNAi1tWd/1d6kq51yR7xQHL7oxvRmuZe2iYbe9o0KO25mFGsUeNb3TpSocxICG5HepKudcke8US8nMD14sbaTuBKXYclN39BwnsW7o/WXHOhvP7pFBneFF5avOgCCBmXmEV8GktQCR3CMlPNBSkL/CajoqUPJJeG2DrdDgDHxic7T+y9HgPGj7W0thIsumKlchp8TxusZXm1Q5YSC28uX276Fpm9NxCPsXsvyvc7+bSISe9ZEosjew2pQm033KnFTysiFeUIhhXhL0gGtciZjcRQaSP9RrK15N+4Qq6NEY0rKwpofGOoajjUevNxOvvA90zhQZlui8rTed7Yux+lHuSmUfSkNzjqp+C9zbzSJJqJY6DBFIQ1U4r07PbuZuzPvj3Xpi1Rq+8QHlHK6lPjlpmbDT6PrP/DNDS5c4OIKPIvDoGgfDzKkDOUGmu/L25mZCfZKw6FpH+x/xDjXhqaTp+1sACBxrFeC/bHndyNJ1DvZacRYEvt4299mDo1PqY2PAUCjroZUGRDr4+yeRlHglVzEv1f+/qoiu8WNBpx9Zb9iXwYEowLuF+aSk6z/BbHhyaB/ko4hNa2Sha7MCUDFo+sAzBIJ4PBpPSmd8LNTNZShO57s4j1+jQlN5Uzb601TPBnYS4jAxBUw5YksO3AIT0hXEsoMxcyzURSI7EOZnmPYBWQHVkEAwl241jc+X0zmUS/z+LPI7iUqi/xz8zoeFT9M4W9e15L2JVnNMDP1K1stqk8rXlG/seGFYRbMiUbhShTHh9VfkXx9c+ESAlzczktCuT8pEQu3AkDw+q/1WVTCQOjIreX2z74MuKo+g5/7/OLSWHbMGaDXy5w7dTgLcpezJ+mRRJJKVAL+PBrpSCgiyaIH1ib+zsrKXedhJihaQFVwNPYcuRx7djTaACApok/esF3YWA0bG3bQocQgrZAEBPM0mJpKK7gfnC+CJwXTXfowdoaI1bW+3AlxvbqBFG1O4RFjQdmOLOx6Yq/SHpdX1Cyv4/FHsLG/6geOaXnNfbiQOpfsXqM4lozQg3ErCc+LYfBHW/kSXLZWDO95L/atV2tOGT+c=;j4hepIFoKTHZE59I7UHwACnxzqwpGGkKHUEnGSIdLR1q27N1XTE7TTCcOMN5cJI4yyS0YQWN/xp7A5QQefY1eCKpsiIeEf3/iopyyYfcIcPKBQ7Iru67QoJWGonuhawb0QY+iGXaECjhB6CWamN3ilLrAQckOJosFLYEEjFbXfs=;GROUP;";
        }

        StringTokenizer token  = new StringTokenizer(strTotalData , ";");
        String strNewDataList  = token.nextToken();
        String strMD5SecureKey = token.nextToken();
        String strKeyFolder    = token.nextToken();

        // mySingle key 위치 설정
        byte[] baPublicKey = null;//Utils.getPublicKey(propertyService.getProperty("secfw.url.mySingleKey") + strKeyFolder + "/mySingle_key");
        baPublicKey =new byte[] {77, 73, 71, 102, 77, 65, 48, 71, 67, 83, 113, 71, 83, 73, 98, 51, 68, 81, 69, 66, 65, 81, 85, 65, 65, 52, 71, 78, 65, 68, 67, 66, 105, 81, 75, 66, 103, 81, 67, 102, 76, 107, 90, 48, 76, 116, 77, 101, 43, 108, 89, 57, 121, 73, 97, 80, 77, 110, 50, 85, 47, 112, 43, 68, 52, 100, 108, 120, 52, 98, 111, 76, 111, 85, 84, 103, 52, 113, 118, 75, 53, 54, 97, 109, 54, 55, 118, 80, 119, 103, 83, 89, 84, 70, 85, 67, 70, 104, 97, 66, 68, 107, 49, 78, 72, 67, 86, 116, 48, 77, 67, 69, 53, 49, 53, 81, 122, 99, 101, 115, 101, 43, 112, 111, 114, 112, 121, 68, 119, 57, 115, 104, 106, 87, 54, 75, 81, 49, 103, 70, 83, 74, 78, 119, 89, 70, 84, 70, 121, 109, 52, 52, 104, 85, 80, 120, 52, 86, 107, 76, 74, 47, 101, 72, 78, 88, 86, 122, 53, 69, 111, 118, 55, 52, 82, 86, 48, 115, 85, 89, 100, 56, 73, 66, 87, 86, 51, 84, 43, 86, 120, 56, 57, 47, 68, 108, 57, 110, 111, 109, 83, 80, 117, 73, 118, 103, 84, 90, 77, 81, 73, 68, 65, 81, 65, 66};

        String trayInfo = EpTrayUtil.DecryptDataList(new String(baPublicKey),strMD5SecureKey,strNewDataList);

        String trayUserId       = "";
        String traySabun        = "";
        String trayCompCd       = "";
        String trayCompNm       = "";
        String trayCompNm_En    = "";
        String trayDeptCd       = "";
        String trayDeptNm       = "";
        String trayDeptNm_En    = "";
        String trayGradeCd      = "";
        String trayGradeNm      = "";
        String trayGradeNm_En   = "";
        String trayUserNm       = "";
        String trayUserNm_En    = "";
        String trayEpMail       = "";
        String trayLoginIP      = "";
        String trayLocale       = "";
        String trayLoginId      = "";
        String trayCompTel      = "";
        String trayMobile       = "";
        String trayTimezone     = "";
        String traySummertimeyn = "";




        StringTokenizer st = new StringTokenizer(trayInfo, ";");
        for ( ; st.hasMoreTokens(); ) {
            String info = st.nextToken();

            if (info != null) {
                String infoKey   = info.substring(0, info.indexOf ("=")).trim();
                String infoValue = info.substring(info.indexOf ("="), info.length()).trim();

                if (infoKey.equals("EP_RETURNCODE") && infoValue.equals("=0")) { // Get EP_RETURNCODE
                    flag = false; // error발생
                    break;
                }

                if (infoKey.equals("EP_USERID"))      // Get USERID
                    trayUserId          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_SABUN"))       // Get SABUN
                    traySabun           = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_COMPID"))      // Get EP_COMPID
                    trayCompCd          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_COMPNAME"))    // Get EP_COMPNAME
                    trayCompNm          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_COMPNAME_EN")) // Get EP_COMPNAME_EN
                    trayCompNm_En       = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_DEPTID"))      // Get EP_DEPTID
                    trayDeptCd          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_DEPTNAME"))    // Get EP_DEPTNAME
                    trayDeptNm          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_DEPTNAME_EN")) // Get EP_DEPTNAME_EN
                    trayDeptNm_En       = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_GRDID"))       // Get EP_GRDID
                    trayGradeCd         = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_GRDNAME"))     // Get EP_GRDNAME
                    trayGradeNm         = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_GRDNAME_EN"))  // Get EP_GRDNAME_EN
                    trayGradeNm_En      = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_USERNAME"))    // Get EP_USERNAME
                    trayUserNm          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_USERNAME_EN")) // Get EP_USERNAME_EN
                    trayUserNm_En       = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_MAIL"))        // Get EP_MAIL
                    trayEpMail          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_LOGINIP"))     // Get LOGIN IP
                    trayLoginIP         = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1, infoValue.length( ));
                if (infoKey.equals("EP_LOCALE"))
                    trayLocale          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_LOGINID"))
                    trayLoginId         = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_COMPTEL"))
                    trayCompTel         = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_MOBILE"))
                    trayMobile          = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_TIMEZONE"))
                    trayTimezone        = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_SUMMERTIMEYN"))
                    traySummertimeyn    = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
                if (infoKey.equals("EP_LOGINTIMEFORMIS"))
                    trayLoginTimeformis = infoValue.equals("=") ? "" : infoValue.substring(infoValue.indexOf("=") + 1,  infoValue.length());
            }
        }//end for(TRAY 정보 For Looping)
        if(adminflag != null && "localhost".equals(adminflag)){

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            trayLoginTimeformis = formatter.format(new Date());

        }
        // mySingle Tray 에서 필요한 정보를 추출


        /************************************************************************************************
         * 세션정보 세팅
         *   아래 항목은 필수적으로 세팅 해주시기 바랍니다
         *   각종 사용현황 및 사용자정보 세팅에 사용됩니다
         *
         *   - 사용자아이디 : secfw.session.user_id   (시스템 사용자아이디)
         *   - 사번          : secfw.session.emp_no	  (시스템 사용자사번)
         *   - 회사명       : secfw.session.comp_nm   (사용자 표시시 필요)
         *   - 부서명       : secfw.session.dept_nm   (로그인/메뉴사용현황등에 표시)
         *   - 직급명       : secfw.session.grade_nm  (로그인/메뉴사용현황등에 표시)
         *   - 사용자명     : secfw.session.user_nm   (사용자명)
         *   - 싱글아이디   : secfw.session.single_id
         *   - 싱글이메일   : secfw.session.email
         *   - 사용자레벨   : secfw.session.user_level(사용자레벨 A:Admin) : 사용자 레벨값으로 확장사용 가능
         *************************************************************************************************/
        session.setAttribute("secfw.session.user_id",      StringUtil.bvl(trayUserId,""));
        session.setAttribute("secfw.session.emp_no",       StringUtil.bvl(traySabun,""));
        session.setAttribute("secfw.session.comp_cd",      StringUtil.bvl(trayCompCd,""));
        session.setAttribute("secfw.session.comp_nm",      StringUtil.bvl(trayCompNm,""));
        session.setAttribute("secfw.session.comp_nm_en",   StringUtil.bvl(trayCompNm_En,""));
        session.setAttribute("secfw.session.dept_cd",      StringUtil.bvl(trayDeptCd,""));
        session.setAttribute("secfw.session.dept_nm",      StringUtil.bvl(trayDeptNm,""));
        session.setAttribute("secfw.session.dept_nm_en",   StringUtil.bvl(trayDeptNm_En,""));
        session.setAttribute("secfw.session.grade_cd",     StringUtil.bvl(trayGradeCd,""));
        session.setAttribute("secfw.session.grade_nm",     StringUtil.bvl(trayGradeNm,""));
        session.setAttribute("secfw.session.grade_nm_en",  StringUtil.bvl(trayGradeNm_En,""));
        session.setAttribute("secfw.session.user_nm",      StringUtil.bvl(trayUserNm_En,""));
        session.setAttribute("secfw.session.user_nm_en",   StringUtil.bvl(trayUserNm_En,""));
        session.setAttribute("secfw.session.email",        StringUtil.bvl(trayEpMail,""));
        session.setAttribute("secfw.session.single_id",    StringUtil.bvl(trayLoginId,""));
        session.setAttribute("secfw.session.loginIP",      StringUtil.bvl(trayLoginIP, ""));
        session.setAttribute("secfw.session.locale",       StringUtil.bvl(trayLocale, ""));
        session.setAttribute("secfw.session.timezone",     StringUtil.bvl(trayTimezone, ""));
        session.setAttribute("secfw.session.summertimeyn", StringUtil.bvl(traySummertimeyn, ""));
        session.setAttribute("secfw.session.session_id",   StringUtil.bvl(session.getId(),""));
        session.setAttribute("secfw.session.comp_tel",     StringUtil.bvl(trayCompTel, ""));
        session.setAttribute("secfw.session.mobile",       StringUtil.bvl(trayMobile, ""));
        getLogger().debug("trayLocale : " + StringUtil.bvl(trayLocale, ""));
        getLogger().info("secfw.session.user_id , single_id try : [" + session.getAttribute("secfw.session.user_id") + ", " + session.getAttribute("secfw.session.single_id") + "]");

        // 2013.03.28 사별 메뉴-권한에 따른 자회사 구분을 위한 세션 세팅 추가 by 전종효
        // 2013.05.14 로케일정보에 따라 abbr_comp_nm이 영문 또는 한글로 세팅 by 전종효
        List listComps = userService.isExist_comp_cd(StringUtil.bvl(trayCompCd, ""),trayUserId);

        if (listComps.size() > 0) {// 12개사에 속하는 사람인 경우
            ListOrderedMap listOrderMap = (ListOrderedMap)listComps.get(0);
            String abbr_comp_nm    = (String)listOrderMap.get("cd_abbr_nm");
            String abbr_comp_nm_en = (String)listOrderMap.get("cd_abbr_nm_en");
            String except_man = (String)listOrderMap.get("except_man");
            String u_comp_cd = (String)listOrderMap.get("comp_cd");	//사용자테이블의 회사코드

            if(except_man.equals("Y")){
                session.setAttribute("secfw.session.auth_comp_cd", "'" + u_comp_cd + "'");
                session.setAttribute("secfw.session.comp_cd", u_comp_cd);
            }else{
                session.setAttribute("secfw.session.auth_comp_cd", "'" + StringUtil.bvl(trayCompCd, "") + "'");
            }

            session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(abbr_comp_nm, ""));
            session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(abbr_comp_nm_en, ""));
            session.setAttribute("secfw.session.exist_comp_yn", "Y");
        } else {// 12개사에 속하지 않는 사람인 경우
            String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");// SysCd

            HashMap<String, String> hMap = new HashMap<String, String>();
            hMap.put("sys_cd", "LAS");
            hMap.put("user_id", StringUtil.bvl(trayUserId,""));

            // 2013.06.18 전자(C10) 계약지정인인 경우 모든 자회사의 계약건을 볼 수 있도록 추가 by 전종효
            if ("C10".equals(session.getAttribute("secfw.session.comp_cd").toString())) {
                HashMap authApntYnMap = userService.getAuthApntYn(hMap);

                // 계약지정인인지 여부
                if ("Y".equals((String)authApntYnMap.get("auth_apnt_yn"))) {
                    List listMngComps = userService.getMngComps();

                    if (listMngComps.size() > 0) {
                        ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                        String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                        getLogger().debug("mngCompCds(1) : [" + mngCompCds + "]");

                        session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                        session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(trayCompNm, ""));
                        session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(trayCompNm_En, ""));
                    }
                } else {
                    List listMngComps = userService.getMngComps(hMap);

                    if (listMngComps.size() > 0) {// 전자검토자인 경우
                        List listRoles = userService.getUserSecRole(hMap);

                        if (listRoles.size() > 0) {// 전자임원인 경우
                            listMngComps = userService.getMngComps();

                            if (listMngComps.size() > 0) {
                                ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                getLogger().debug("mngCompCds(2) : [" + mngCompCds + "]");

                                session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                            }

                            session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(trayCompNm, ""));
                            session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(trayCompNm_En, ""));
                        } else {
                            // 2013.07.11 전자검토자인 경우 본인 이름이 보이면 안됨 by 전종효
                            List userInfo = userService.getClmsUserInfo(StringUtil.bvl(trayUserId,""), sysCd);
                            if (userInfo != null && userInfo.size() > 0) {
                                ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);

                                session.setAttribute("secfw.session.user_nm", (String)lom.get("user_nm_eng"));
                                session.setAttribute("secfw.session.user_nm_en", (String)lom.get("user_nm_eng"));
                            }

                            ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                            String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                            getLogger().info("mngCompCds(3) : [" + mngCompCds + "]");

                            session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                            session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(trayCompNm, ""));
                            session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(trayCompNm_En, ""));
                        }
                    } else {// 전자검토자이나 담당회사가 없거나 혹은 전자임원인 경우
                        List listRoles = userService.getUserSecRole(hMap);

                        if (listRoles.size() > 0) {// 전자임원인 경우
                            listMngComps = userService.getMngComps();

                            if (listMngComps.size() > 0) {
                                ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
                                String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
                                getLogger().debug("mngCompCds(3) : [" + mngCompCds + "]");

                                session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
                            }

                            session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(trayCompNm, ""));
                            session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(trayCompNm_En, ""));
                        } else {
                            // 2013.07.11 전자검토자인 경우 본인 이름이 보이면 안됨 by 전종효
                            List userInfo = userService.getClmsUserInfo(StringUtil.bvl(trayUserId,""), sysCd);
                            if (userInfo != null && userInfo.size() > 0) {
                                ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);

                                session.setAttribute("secfw.session.user_nm", (String)lom.get("user_nm_eng"));
                                session.setAttribute("secfw.session.user_nm_en", (String)lom.get("user_nm_eng"));
                            }

                            session.setAttribute("secfw.session.auth_comp_cd", "'C10'");
                            session.setAttribute("secfw.session.abbr_comp_nm", StringUtil.bvl(trayCompNm, ""));
                            session.setAttribute("secfw.session.abbr_comp_nm_en", StringUtil.bvl(trayCompNm_En, ""));
                        }
                    }
                }
            } else {// 기타 - 12개 회사에서 존재 하지 않으면 기존엔 '*'으로 처리하다가 테스트를 위해서 'EHQ' DS EHQ - Samsung Semiconductor Europe GmbH로 변경 처리함.
                session.setAttribute("secfw.session.auth_comp_cd", "'EHQ'");
                session.setAttribute("secfw.session.comp_cd", "EHQ");
                session.setAttribute("secfw.session.abbr_comp_nm", "DS EHQ - Samsung Semiconductor Europe GmbH");
                session.setAttribute("secfw.session.abbr_comp_nm_en", "EHQ");
            }

            session.setAttribute("secfw.session.exist_comp_yn", "N");
        }

        getLogger().debug("secfw.session.auth_comp_cd : [" + session.getAttribute("secfw.session.auth_comp_cd") + "]");
        getLogger().debug("secfw.session.abbr_comp_nm : [" + session.getAttribute("secfw.session.abbr_comp_nm") + "]");
        getLogger().debug("secfw.session.abbr_comp_nm_en : [" + session.getAttribute("secfw.session.abbr_comp_nm_en") + "]");
        /********************************************************************************
         * 부가적 세션정보 세팅(종료)
         *******************************************************************************/

        return flag;
    }

    /**
     * 사용자 로그인시 로케일정보 세팅(SSO정보)
     * @param request
     * @param response
     * @param session
     * @return USER_ID
     * @throws Exception
     */
    private void setUserLoginLocale(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String first2Str = StringUtil.bvl(((String)session.getAttribute("secfw.session.locale")).substring(0,2),propertyService.getProperty("secfw.defaultLocale"));
        Locale locale = null;

        // 현재는 영문으로 셋팅한다.
        locale = new Locale(first2Str);

        //싱글 선택 언어(ep_local)임의값을 세션에 적용.
        session.setAttribute("secfw.session.language_flag", first2Str);
        session.setAttribute("secfw.server.division_gbn",			propertyService.getProperty("secfw.server.division")); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.
        //localeResolver에 locale 셋팅
        localeResolver.setLocale(request, response, locale);

    }

    /**
     * 임직원 권한세팅
     * 일반임직원 : RZ00
     * @param userId
     * @param sys_cd
     * @return
     * @throws Exception
     */
	/*private void setUserRole(String userId, String sys_cd) throws Exception{

		AuthVO authVo = new AuthVO();

		authVo.setSys_cd(sys_cd);
		authVo.setUser_id(userId);
		authVo.setRole_cd("RZ00"); //일반 임직원

		List existsRoleUser = userService.existsRoleUser(authVo);
		if(existsRoleUser != null && existsRoleUser.size() > 0){
			ListOrderedMap existsRoleMap = (ListOrderedMap)existsRoleUser.get(0);

			String existsRoleUserYn = (String)existsRoleMap.get("exists_yn");	// 조회 시 : 'Y'

			if("N".equals(existsRoleUserYn)){
				userService.insertRoleUser(authVo);
			}
		}
	}*/
    private void setUserRole(String userId, String role_cd, String sys_cd) throws Exception{

        AuthVO authVo = new AuthVO();

        authVo.setSys_cd(sys_cd);
        authVo.setUser_id(userId);
        authVo.setRole_cd(role_cd);

        List existsRoleUser = userService.existsRoleUser(authVo);
        if(existsRoleUser != null && existsRoleUser.size() > 0){
            ListOrderedMap existsRoleMap = (ListOrderedMap)existsRoleUser.get(0);

            String existsRoleUserYn = (String)existsRoleMap.get("exists_yn");	// 조회 시 : 'Y'

            if("N".equals(existsRoleUserYn)){
                userService.insertRoleUser(authVo);
            }
        }
    }

    /**
     * 시스템 관리자 권한세팅
     * @param request
     * @param response
     * @param userId
     * @return USER_ID
     * @throws Exception
     */
    private void setSystemAdminRole(HttpServletRequest request, HttpSession session, String userId) throws Exception {
        String adminEPID = propertyService.getProperty("secfw.admin.epid");
        StringTokenizer adminStrToken = new StringTokenizer(adminEPID, ",");

        while(adminStrToken.hasMoreTokens()) {

            String adminTokenStr = adminStrToken.nextToken();

            if(userId.equals(adminTokenStr)) {
                session.setAttribute("secfw.session.user_level", "A");
                break;
            }
        }

//		String sys_cd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
//
//		AuthVO authVo = new AuthVO();
//
//		authVo.setSys_cd(sys_cd);
//		authVo.setUser_id(userId);
//		authVo.setRole_cd("RA00");
//
//		List existsRoleUser = userService.existsRoleUser(authVo);
//		if(existsRoleUser != null && existsRoleUser.size() > 0){
//			ListOrderedMap existsRoleMap = (ListOrderedMap)existsRoleUser.get(0);
//
//			String existsRoleUserYn = (String)existsRoleMap.get("exists_yn");	// 조회 시 : 'Y'
//
//			if("Y".equals(existsRoleUserYn)){
//				session.setAttribute("secfw.session.user_level", "A");
//			}
//		}
    }

    /**
     * 로그인 로그 등록
     * @param request
     * @param response
     * @return USER_ID
     * @throws Exception
     */
    private void insertLoginLog(HttpServletRequest request, HttpSession session) throws Exception {
        LogVO vo = new LogVO();

        vo.setSys_cd(StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
        vo.setUser_id(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
        vo.setUser_nm(StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), ""));
        vo.setDept_nm(StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), ""));
        vo.setYear(DateUtil.getTime("yyyy"));
        vo.setMonth(DateUtil.getTime("MM"));
        vo.setDay(DateUtil.getTime("dd"));
        vo.setHour(DateUtil.getTime("HH"));
        vo.setLogin_dt(DateUtil.getTime("yyyyMMddHHmmss"));
        vo.setIp_address(StringUtil.bvl((String)session.getAttribute("secfw.session.loginIP"), ""));
        vo.setBrowser_type(StringUtil.bvl(request.getHeader("User-Agent"), ""));
        // 2014-05-14 Kevin added.
        if(session.getAttribute("sys_type") != null){
            vo.setBrowser_type(vo.getBrowser_type()+"["+session.getAttribute("sys_type").toString()+"]");
        }
        vo.setSession_id(StringUtil.bvl(session.getId(), ""));

        logService.insertLoginLog(vo);
    }

    /**2011.04.08 김정곤
     * 로그아웃 로그 등록
     * @param request
     * @param response
     * @return USER_ID
     * @throws Exception
     */
    private void updateLogoutLog(HttpServletRequest request, HttpSession session) throws Exception {
        LogVO vo = new LogVO();
        vo.setSys_cd(StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), ""));
        vo.setUser_id(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""));
        vo.setUser_nm(StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), ""));
        vo.setDept_nm(StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), ""));
        vo.setYear(DateUtil.getTime("yyyy"));
        vo.setMonth(DateUtil.getTime("MM"));
        vo.setDay(DateUtil.getTime("dd"));
        vo.setHour(DateUtil.getTime("HH"));
        vo.setLogin_dt(DateUtil.getTime("yyyyMMddHHmmss"));
        vo.setIp_address(StringUtil.bvl((String)session.getAttribute("secfw.session.loginIP"), ""));
        vo.setBrowser_type(StringUtil.bvl(request.getHeader("User-Agent"), ""));
        vo.setSession_id(StringUtil.bvl(session.getId(), ""));
        logService.updateLogoutLog(vo);
    }

    /**
     * 상위 부서들 검색
     * 현부서부터 상위부서 코드를 구분자(^)로 붙인 문자열로 세션에 셋팅한다.
     * @param dept_cd
     * @throws Exception
     */
    private void setUpDeptInfo(HttpServletRequest request, HttpSession session, String dept_cd) throws Exception {
        String up_menu_cds = "";

        HashMap hm = new HashMap();
        hm.put("dept_cd", dept_cd);

        List upDeptList = userService.listUpDeptInfo(hm);

        if(upDeptList!=null && upDeptList.size()>0) {

            for(int i=0;i<upDeptList.size(); i++){
                ListOrderedMap upDeptMap = (ListOrderedMap)upDeptList.get(i);
                String deptCd = (String)upDeptMap.get("dept_cd");

                if(!"TOP".equals(deptCd)){
                    up_menu_cds += deptCd + "^";
                }
            }
        }

        session.setAttribute("secfw.session.up_level_dept_cd", up_menu_cds);
    }

    /**
     * 소속조직 코드 구하기
     *
     * 시스템관리자,법무관리자,법무담당자,법무일반담당자 사용자일 경우
     * tb_com_user의 blngt_orgnz 을 셋팅한다.
     * @param request
     * @param session
     * @param dept_cd
     * @throws Exception
     */
    private void setBlngtOrgnz(HttpServletRequest request, HttpSession session, String dept_cd, String user_id, String blngt_orgnz, String auto_rnew_yn) throws Exception{

        Locale lc = new RequestContext(request).getLocale();
        String locale = StringUtil.bvl(lc.getLanguage(), "en");

        //사용자 role 가져온다.
        ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
            for(int idx=0; idx<roleList.size();idx++) {
                HashMap roleListMap = (HashMap)roleList.get(idx);

                userRoleList.add((String)roleListMap.get("role_cd"));
            }
        }

        boolean isSearch = true;
        if(userRoleList!=null && userRoleList.size()>0) {
            // 시스템관리자, 법무관리자, 법무담당자, 법무일반 사용자, auto_rnew_yn=N 인 경우 소속조직코드를 구하는 로직을 태우지 않고
            // tb_com_user 의 blngt_orgnz 값으로 세션에 셋팅한다.
            //if(userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03") || "N".equals(auto_rnew_yn)) {
            if(userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || "N".equals(auto_rnew_yn)) {
                isSearch = false;
            }
        }


        //소속조직코드 및 조직명을 조회 후 tb_com_user 의 blngt_orgnz 에 update!
        if(isSearch){

            HashMap hm = new HashMap();
            hm.put("dept_cd", dept_cd);
            hm.put("user_id", user_id);
            hm.put("locale", locale);

            HashMap map = userService.listOrgnzList(hm);

            session.setAttribute("secfw.session.blngt_orgnz", StringUtil.bvl(map.get("blngt_orgnz"), ""));//소속 조직 코드
            session.setAttribute("secfw.session.blngt_orgnz_nm", StringUtil.bvl(map.get("blngt_orgnz_nm"), "")); //소속 조직명
            session.setAttribute("secfw.session.blngt_orgnz_nm_abbr", StringUtil.bvl(map.get("blngt_orgnz_nm_abbr"), "")); //소속 조직명 약어

            //소속조직코드명으로 조직명을 조회한다.
        }else{

            //String blngt_orgnz_nm =
            HashMap hm = new HashMap();
            hm.put("orgnz_cd", blngt_orgnz);
            hm.put("locale", locale);

            HashMap map = userService.listOrgnzName(hm);

            session.setAttribute("secfw.session.blngt_orgnz", blngt_orgnz);//소속 조직 코드
            session.setAttribute("secfw.session.blngt_orgnz_nm", StringUtil.bvl(map.get("blngt_orgnz_nm"), "")); //소속 조직명
            session.setAttribute("secfw.session.blngt_orgnz_nm_abbr", StringUtil.bvl(map.get("blngt_orgnz_nm_abbr"), "")); //소속 조직명 약어
        }
    }

    /**
     * 조직장 여부 조회
     * @param request
     * @param session
     * @param auth_apmt_yn
     * @param dept_cd
     * @param comp_cd
     * @param emp_no
     * @throws Exception
     */
    private void setManagerYN(HttpServletRequest request, HttpSession session, String auth_apnt_yn, String dept_cd, String emp_no) throws Exception{

//		권한지정여부를 따로 세션으로 빼서 여기선 기능을 제거한다. 2012.1.4
//		if("Y".equals(auth_apnt_yn)){ //tb_com_user의 auth_apmt_yn 가 Y이면 조직장 여부도 Y로 셋팅
//			session.setAttribute("secfw.session.manager_yn", "Y");
//		}else{
        HashMap hm = new HashMap();
        hm.put("dept_cd", dept_cd);
        hm.put("dept_mgr_emp_no", emp_no);

        List deptList = userService.listManagerYNList(hm);

        if(deptList != null && deptList.size() > 0){
            session.setAttribute("secfw.session.manager_yn", "Y");
        }else{
            session.setAttribute("secfw.session.manager_yn", "N");
        }
//		}
    }

    /**
     * 지원 부서 여부 조회
     * @param request
     * @param session
     * @param blngt_orgnz
     * @param dept_cd
     * @throws Exception
     */
    private void setSupportYN(HttpServletRequest request, HttpSession session, String blngt_orgnz, String dept_cd) throws Exception{

        HashMap hm = new HashMap();
        hm.put("blngt_orgnz", blngt_orgnz);
        hm.put("dept_cd", dept_cd);

        List orgnzList = userService.listSupportYNList(hm);

        if(orgnzList != null && orgnzList.size() > 0){
            session.setAttribute("secfw.session.support_yn", "Y");
        }else{
            session.setAttribute("secfw.session.support_yn", "N");
        }

    }

    //소속조직코드 조회
    private String searchBlngtOrgnz(HttpServletRequest request, String dept_cd, String user_id) throws Exception{
        Locale lc = new RequestContext(request).getLocale();
        String locale = StringUtil.bvl(lc.getLanguage(), "en");

        HashMap hm = new HashMap();
        hm.put("dept_cd", dept_cd);
        hm.put("user_id", user_id);
        hm.put("locale", locale);

        HashMap map = userService.searchOrgnz(hm);

        return StringUtil.bvl(map.get("blngt_orgnz"), "");
    }

    /**
     * 지원 부서 여부 조회
     * @param request
     * @param session
     * @param blngt_orgnz
     * @param dept_cd
     * @throws Exception
     */
    public void requestAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Parameter
             **********************************************************/
//			String forwardURL = "";

            UserForm form = null;
            UserVO vo = null;

            /*********************************************************
             * Session Check
             **********************************************************/
            HttpSession session = request.getSession(false);

            if(session==null)
                throw new Exception("##### Session is null #####");

            /*********************************************************
             * Parameter setting
             **********************************************************/

            /*********************************************************
             * Form 및 VO Binding
             **********************************************************/
            form = new UserForm();
            vo = new UserVO();

            bind(request, form);
            bind(request, vo);

            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            String userId     = vo.getSession_user_id(); // user_id

            vo.setUser_id(userId);

            int update = userService.requestAccess(vo);

            if(update<1){
                //등록된 사용자가 아닐경우 사용자로 등록, 의로자 Role 기본 등록
                update = userService.insertUserInfo(userId);
            }

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(update);
            out.flush();
            out.close();


        }catch (Exception e) {
            JSONObject jo = new JSONObject();
            jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jo);
            out.flush();
            out.close();
        }catch (Throwable t) {
            JSONObject jo = new JSONObject();
            jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jo);
            out.flush();
            out.close();
        }

    }

    /**
     * 사용자정보 이관-ESB
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView migUserInfoByESB(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Forwarding URL
             **********************************************************/
            String forwardURL = "/WEB-INF/jsp/secfw/user/UserInfo.jsp";
            forwardURL = "/las/intro.do";

            /*********************************************************
             * Parameter Setting
             **********************************************************/
            HttpSession session = request.getSession(false);
            String userId     = StringUtil.bvl((String)request.getParameter("srch_user_id"), (String)session.getAttribute("secfw.session.user_id"));
            String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
            Locale lc         = (Locale)localeResolver.resolveLocale(request);
            String compCd = (String)session.getAttribute("secfw.session.comp_cd");
            String ecms_tran_yn = StringUtil.bvl((String)request.getParameter("ecms_tran_yn"), "N");

            HashMap hListMap = new HashMap();
            hListMap.put("ecms_tran_yn", ecms_tran_yn);
            List oldUserList = userService.listOldUser(hListMap);
            Hashtable userInfoTable = null;
            String oldrole = "";
            int iTargetCount = 0; 	//대상카운트
            int iMigCount = 0; 		//이관처리한 카운트

            if (oldUserList != null && oldUserList.size() > 0) {
                iTargetCount = oldUserList.size();
                for (int i=0; i<oldUserList.size(); i++) {
                    ListOrderedMap oldListLom = (ListOrderedMap)oldUserList.get(i);

                    userId = (String)oldListLom.get("epid");

                    try{
                        userService.insertUserInfo(userId);
                    }catch(Exception e){
                        continue;
                    }
					/*
					 * <TSY_PERM (구 SELMS 법무팀 권한)>
					      CF1 : Legal Admin    	== selmsplus에서 ==> 법무그룹장(RA01), 계약관리자(RD01), Admin(RM00)
					      ADM  : System admin	== selmsplus에서 ==> System Admin(RA00)
					      MF1  : Lawyer			== selmsplus에서 ==> 법무검토자(RA02)

					      TSY_PERM : 권한관리
					      TSY_USER : 법무팀 사용자
					      TSY_USER_INFO : 의뢰인
					 * */
                    oldrole = (String)oldListLom.get("role_cd");
                    AuthVO vo = new AuthVO();

                    if(oldrole !=null && oldrole.equals("CF1")){
                        vo.setSys_cd("LAS");
                        vo.setRole_cd("RA01");
                        vo.setUser_id(userId);
                        userService.insertRoleUser(vo);

                        vo.setRole_cd("RD01");
                        userService.insertRoleUser(vo);

                        vo.setRole_cd("RM00");
                        userService.insertRoleUser(vo);
                    }else if(oldrole !=null && oldrole.equals("ADM")){
                        vo.setSys_cd("LAS");
                        vo.setRole_cd("RA00");
                        vo.setUser_id(userId);
                        userService.insertRoleUser(vo);
                    }else if(oldrole !=null && oldrole.equals("MF1")){
                        vo.setSys_cd("LAS");
                        vo.setRole_cd("RA02");
                        vo.setUser_id(userId);
                        userService.insertRoleUser(vo);
                    }
                    vo.setUser_id(userId);

                    if(ecms_tran_yn.equals("N"))
                        userService.updateMigStatus(vo);
                    else
                        userService.updateEcmsMigStatus(vo);


                    iMigCount ++;
                }
            }




            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);

            mav.addObject("sTargetCount", String.valueOf(iTargetCount));
            mav.addObject("sMigCount", String.valueOf(iMigCount));
            mav.addObject("userInfoTable", userInfoTable);

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
     * 사용자정보 직급 업데이트 처리 -ESB
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView migUserUpdJikgubByESB(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Forwarding URL
             **********************************************************/
            String forwardURL = "/WEB-INF/jsp/secfw/user/UserInfo.jsp";
            forwardURL = "/las/intro.do";

            /*********************************************************
             * Parameter Setting
             **********************************************************/
            HttpSession session = request.getSession(false);
            String userId     = StringUtil.bvl((String)request.getParameter("srch_user_id"), (String)session.getAttribute("secfw.session.user_id"));
            String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
            Locale lc         = (Locale)localeResolver.resolveLocale(request);
            String compCd = (String)session.getAttribute("secfw.session.comp_cd");

            HashMap hListMap = new HashMap();
            List oldUserList = userService.listUserUpdJikgubByESB(hListMap);
            Hashtable userInfoTable = null;
            String oldrole = "";
            int iTargetCount = 0; 	//대상카운트
            int iMigCount = 0; 		//이관처리한 카운트

            if (oldUserList != null && oldUserList.size() > 0) {
                iTargetCount = oldUserList.size();
                for (int i=0; i<oldUserList.size(); i++) {
                    ListOrderedMap oldListLom = (ListOrderedMap)oldUserList.get(i);

                    userId = (String)oldListLom.get("user_id");
                    AuthVO vo = new AuthVO();

                    vo.setUser_id(userId);
                    userService.updUserUpdJikgubByESB(vo);
                    iMigCount ++;
                }
            }

            ModelAndView mav = new ModelAndView();
            mav.setViewName(forwardURL);

            mav.addObject("sTargetCount", String.valueOf(iTargetCount));
            mav.addObject("sMigCount", String.valueOf(iMigCount));
            mav.addObject("userInfoTable", userInfoTable);

            return mav;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new Exception(t);
        }
    }
}
