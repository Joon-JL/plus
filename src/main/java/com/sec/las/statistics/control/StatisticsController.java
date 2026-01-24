/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsController.java
 * Description	: 법무통계  Controller
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.las.statistics.dto.StatisticsForm;
import com.sec.las.statistics.dto.StatisticsVO;
import com.sec.las.statistics.service.StatisticsService;

public class StatisticsController extends CommonController {

    /**
     * ROLE 구분을 위한 변수
     */
    static String ROLE_0 = "RA00"; // 시스템관리자
    static String ROLE_1 = "RA01"; // 법무관리자
    static String ROLE_2 = "RA02"; // 법무담당자
    static String ROLE_3 = "RA03"; // 법무일반사용자
    static String ROLE_7 = "RZ00"; // 일반임직원

    /**
     * ComUtil 서비스
     */
    private ComUtilService comUtilService;

    /**
     * ComUtil 서비스 세팅
     * @param comUtilService
     */
    public void setComUtilService(ComUtilService comUtilService) {
        this.comUtilService = comUtilService;
    }
    private StatisticsService statisticsService;
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    /**
     * 통계 목록 조회
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView listStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HttpSession session = request.getSession(false);

            String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsResAsgn_l.jsp";
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            Date d = new Date();
            String sYear = (1900+ d.getYear()) +"";
            String sMonth = Integer.toString(d.getMonth()+1) + "";
            String sDate = Integer.toString(d.getDate()) + "";

            sMonth = (sMonth.length() == 1)?"0"+sMonth:sMonth;
            sDate = (sDate.length() == 1)?"0"+sDate:sDate;
            form.setReport_url(propertyService.getProperty("secfw.url.domain"));
            if(vo.getSrch_regdt1()==null){
                vo.setSrch_regdt1(sYear+"-01-01");
                vo.setSrch_regdt2(sYear+"-"+sMonth+"-"+sDate);
                form.setSrch_regdt1(sYear+"-01-01");
                form.setSrch_regdt2(sYear+"-"+sMonth+"-"+sDate);
            }else{
            }

            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);
            if(request.getParameter("locale") != null){
                if(request.getParameter("locale").equals("F")){
                    vo.setDmstfrgn_gbn("F");
                    form.setDmstfrgn_gbn("F");
                }else{
                    vo.setDmstfrgn_gbn("H");
                    form.setDmstfrgn_gbn("H");
                }
            }else if(vo.getDmstfrgn_gbn() == null){
                if(request.getParameter("locale") != null && request.getParameter("locale").equals("F")){
                    vo.setDmstfrgn_gbn("F");
                    form.setDmstfrgn_gbn("F");
                }else{
                    String locale = (String)session.getAttribute("secfw.session.blngt_orgnz");
                    if(locale != null && locale.equals("A00000001")){ // 국내
                        locale = "H";
                    }else if(locale != null && locale.equals("A00000002")){ // 해외
                        locale = "F";
                    }else{
                        locale = "";
                    }
                    vo.setDmstfrgn_gbn(locale);
                    form.setDmstfrgn_gbn(locale);
                }
            }
            String returnMessage = "";

            List resultList = statisticsService.listStatistics(vo);

            if (resultList != null && resultList.size() > 0) {
                ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
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
     * 통계 forward
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView forwardStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HttpSession session = request.getSession(false);

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            form.setReport_url(propertyService.getProperty("secfw.url.domain"));
            String forwardURL = "";
            Date d = new Date();
            String sYear = (1900+ d.getYear()) +"";
            String sMonth = Integer.toString(d.getMonth()+1) + "";
            String sDate = Integer.toString(d.getDate()) + "";

            sMonth = (sMonth.length() == 1)?"0"+sMonth:sMonth;
            sDate = (sDate.length() == 1)?"0"+sDate:sDate;
            if(vo.getSrch_regdt1()==null){
                vo.setSrch_regdt1(sYear+"-01-01");
                vo.setSrch_regdt2(sYear+"-"+sMonth+"-"+sDate);
                form.setSrch_regdt1(sYear+"-01-01");
                form.setSrch_regdt2(sYear+"-"+sMonth+"-"+sDate);
            }else{
            }

            if(vo.getTransfer() != null && vo.getTransfer().equals("person")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsPerson_l.jsp";
            }else if(vo.getTransfer() != null && vo.getTransfer().equals("dept")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsTeam_l.jsp";
            }else if(vo.getTransfer() != null && vo.getTransfer().equals("dept1")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsOperdiv_l.jsp";
            }

            if(request.getParameter("transfer") != null && request.getParameter("transfer").equals("person")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsPerson_l.jsp";
            }else if(request.getParameter("transfer") != null && request.getParameter("transfer").equals("dept")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsTeam_l.jsp";
            }else if(request.getParameter("transfer") != null && request.getParameter("transfer").equals("dept1")){
                forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsOperdiv_l.jsp";
            }

            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);


            if("Y".equals(vo.getDoSearch())){
                //nothing
            }
            else{
                if(vo.getSession_blngt_orgnz().equals("A00000001")){
                    vo.setDmstfrgn_gbn("H");
                    form.setDmstfrgn_gbn("H");
                }
                else {
                    vo.setDmstfrgn_gbn("F");
                    form.setDmstfrgn_gbn("F");
                }
            }

			/*
			if(request.getParameter("locale") != null){
				if(request.getParameter("locale").equals("F")){
					vo.setDmstfrgn_gbn("F");
					form.setDmstfrgn_gbn("F");
				}else{
					vo.setDmstfrgn_gbn("H");
					form.setDmstfrgn_gbn("H");
				}
			}else if(vo.getDmstfrgn_gbn() == null){
				if(request.getParameter("locale") != null && request.getParameter("locale").equals("F")){
					vo.setDmstfrgn_gbn("F");
					form.setDmstfrgn_gbn("F");
				}else{
					String locale = (String)session.getAttribute("secfw.session.blngt_orgnz");
					if(locale != null && locale.equals("A00000001")){ // 국내
						locale = "H";
					}else if(locale != null && locale.equals("A00000002")){ // 해외
						locale = "F";
					}else{
						locale = "";
					}
					vo.setDmstfrgn_gbn(locale);
					form.setDmstfrgn_gbn(locale);
				}
			}
			*/

            PageUtil pageUtil = new PageUtil();

            pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

            vo.setStart_index(pageUtil.getStartIndex());
            vo.setEnd_index(pageUtil.getEndIndex());

            String returnMessage = "";

            List resultList = null;
            ListOrderedMap lom1 = statisticsService.selectStatisticsSearch(vo);
            vo.setSrch_beforeweek_s(lom1.get("beforeweek_s").toString());
            vo.setSrch_beforeweek_e(lom1.get("beforeweek_e").toString());
            vo.setSrch_beforemonth_s(lom1.get("beforemonth_s").toString());
            vo.setSrch_beforemonth_e(lom1.get("beforemonth_e").toString());
            vo.setSrch_curweek_s(lom1.get("curweek_s").toString());
            vo.setSrch_curweek_e(lom1.get("curweek_e").toString());
            vo.setSrch_curmonth_s(lom1.get("curmonth_s").toString());
            vo.setSrch_curmonth_e(lom1.get("curmonth_e").toString());
            form.setSrch_beforeweek_s(lom1.get("beforeweek_s").toString());
            form.setSrch_beforeweek_e(lom1.get("beforeweek_e").toString());
            form.setSrch_beforemonth_s(lom1.get("beforemonth_s").toString());
            form.setSrch_beforemonth_e(lom1.get("beforemonth_e").toString());
            form.setSrch_curweek_s(lom1.get("curweek_s").toString());
            form.setSrch_curweek_e(lom1.get("curweek_e").toString());
            form.setSrch_curmonth_s(lom1.get("curmonth_s").toString());
            form.setSrch_curmonth_e(lom1.get("curmonth_e").toString());

            if(vo.getTransfer() != null && vo.getTransfer().equals("person")){
                resultList = statisticsService.listPersonStatistics(vo);
            }else if(vo.getTransfer() != null && vo.getTransfer().equals("dept")){
                resultList = statisticsService.listDeptStatistics(vo);
            }else if(vo.getTransfer() != null && vo.getTransfer().equals("dept1")){
                resultList = statisticsService.listDeptStatistics1(vo);
            }
            if (resultList != null && resultList.size() > 0) {
                ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

                pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
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
     * 리포트 열기
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView reportPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HttpSession session = request.getSession(false);

            String forwardURL = "/WEB-INF/jsp/las/report/ReportPop.jsp";
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);
            // form.setReport_url(propertyService.getProperty("secfw.url.domain"));



            ModelAndView mav = new ModelAndView();
            mav.addObject("command", form);
            mav.setViewName(forwardURL);

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
     * 검토현황
     * gubun 값에 따라 현황페이지가 다르다
     *   assign : 담당자배정현황
     *   pserson : 담당자별통계현황
     *   dept : 사업부별통계현황
     *   law : 법무팀별통계현황
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            HttpSession session = request.getSession(false);
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_" + form.getGubun() + "_l.jsp" ;

            // 검색기간 초기값 세팅
            form.setSrch_start_dt(StringUtil.nvl(form.getSrch_start_dt(), "").equals("") ? DateUtil.formatDate(DateUtil.year() + "0101", "-") : form.getSrch_start_dt()) ;
            form.setSrch_end_dt(StringUtil.nvl(form.getSrch_end_dt(), "").equals("") ? DateUtil.formatDate(DateUtil.today(), "-") : form.getSrch_end_dt()) ;

            // 구분 초기값 세팅
            if("c_l_advice".equals(form.getGubun())){
                form.setSrch_type(StringUtil.nvl(form.getSrch_type(), "").equals("") ? "PC" : form.getSrch_type()) ; // PC:SELMSPLUS계약, PL:SELMSPLUS법률자문, C:SELMS계약, L:SELMS법률자문
            }else{
                form.setSrch_type(StringUtil.nvl(form.getSrch_type(), "").equals("") ? "C" : form.getSrch_type()) ; // C:계약, L:법률자문
            }

            // 검토주관부서 초기값 세팅
            //form.setSrch_dmstfrgn_gbn((StringUtil.nvl(form.getSrch_dmstfrgn_gbn(), "").equals("") ? "H" : form.getSrch_dmstfrgn_gbn())) ; //H:국내, F:해외, IP:IP
            String[] searchR = searchBlngtAndRole(request, form.getSession_blngt_orgnz());
            form.setSrch_dmstfrgn_gbn((StringUtil.nvl(form.getSrch_dmstfrgn_gbn(), "").equals("") ? searchR[0] : form.getSrch_dmstfrgn_gbn())) ; //H:국내, F:해외, IP:IP

            // 분류 초기값 세팅
            form.setSrch_div(StringUtil.nvl(form.getSrch_div(), "").equals("") ? "T03" : form.getSrch_div()) ; // T01:비즈니스단계, T02:계약단계, T03:체결목적단계

            // 검색기간 설정1 초기값 세팅
            form.setSrch_period_gbn1(StringUtil.nvl(form.getSrch_period_gbn1(), "").equals("") ? "1" : form.getSrch_period_gbn1()) ; // 1:현재기준, 2:기간별 실적

            // 검색기간 설정2 초기값 세팅
            form.setSrch_period_gbn2(StringUtil.nvl(form.getSrch_period_gbn2(), "").equals("") ? "1" : form.getSrch_period_gbn2()) ; // 1:연도별, 2:분기별, 3:월별, 4:검색기간설정

            // 검색년도 초기값 세팅
            form.setSrch_year(StringUtil.nvl(form.getSrch_year(), "").equals("") ? DateUtil.year() : form.getSrch_year()) ;

            // 검색분기 초기값 세팅
            form.setSrch_quarter(StringUtil.nvl(form.getSrch_quarter(), "").equals("") ?
                    (Integer.parseInt(DateUtil.month())%3==0 ? String.valueOf(Integer.parseInt(DateUtil.month()) / 3) : String.valueOf((Integer.parseInt(DateUtil.month())/3)+1)) :
                    form.getSrch_quarter()) ;

            // 검색월 초기값 세팅
            form.setSrch_month(StringUtil.nvl(form.getSrch_month(), "").equals("") ? DateUtil.month() : form.getSrch_month()) ;

            SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            String[] this_mon = {"",""};
            this_mon[1] = sdfmt.format(cal.getTime());
            cal.set(Calendar.DATE, 1);
            this_mon[0] = sdfmt.format(cal.getTime());
            form.setThis_mon(this_mon);	//이번달

            cal = Calendar.getInstance();
            String[] one_mon = {"",this_mon[1]};
            cal.add(Calendar.DATE, -30);
            one_mon[0] = sdfmt.format(cal.getTime());
            form.setOne_mon(one_mon);	//1month

            cal = Calendar.getInstance();
            String[] three_mon = {"",this_mon[1]};
            cal.add(Calendar.DATE, -90);
            three_mon[0] = sdfmt.format(cal.getTime());
            form.setThree_mon(three_mon);	//3month

            cal = Calendar.getInstance();
            String[] one_year = {"",this_mon[1]};
            cal.add(Calendar.DATE, -365);
            one_year[0] = sdfmt.format(cal.getTime());
            form.setOne_year(one_year);	//1년
            String sday_set = form.getDay_set();
            String chk_sday = "";
            String chk_eday = "";
            if(sday_set != null && sday_set.equals("T1")){
                chk_sday = this_mon[0];
                chk_eday = this_mon[1];
            }else if(sday_set != null && sday_set.equals("T2")){
                chk_sday = one_mon[0];
                chk_eday = one_mon[1];
            }else if(sday_set != null && sday_set.equals("T3")){
                chk_sday = three_mon[0];
                chk_eday = three_mon[1];
            }else if(sday_set != null && sday_set.equals("T4")){
                chk_sday = one_year[0];
                chk_eday = one_year[1];
            }

            //선택한 라디오버튼에서 시간옵션의 날짜가 동일한 경우만, 라디오 버튼이 선택된상태로 보여주기위해
            if(form.getSrch_start_dt()!=null && form.getSrch_start_dt().equals(chk_sday) &&
                    form.getSrch_end_dt()!=null && form.getSrch_end_dt().equals(chk_eday) && !chk_sday.equals("")){
                form.setDay_set(sday_set);
            }else{
                form.setDay_set("");
            }

            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;

            // 담당자배정현황
            if("assign".equals(form.getGubun())){
                mav = listStatisticsAssign(vo);
            }
            // 담당자별통계현황
            else if("person".equals(form.getGubun())){
                mav = listStatisticsPerson(vo) ;
            }
            // 사업부별통계현황
            else if("dept".equals(form.getGubun())){
                mav = listStatisticsDept(vo) ;
            }
            // 법무팀별통계현황
            else if("law".equals(form.getGubun())){
                vo.setSrch_dmstfrgn_gbn(searchR[1]);
                mav = listStatisticsLaw(vo);
                // 구주통계1
            }else if("guju1".equals(form.getGubun())){

                mav = listStatisticsGuju_law1(vo);
            }
            // 구주 기존통계 화면 : Contract & Legal Advice Statistics
            else if("c_l_advice".equals(form.getGubun())){
                ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
                // RB01 : 전자 검토자 RB02 : 전자 임원
                if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
                    vo.setElUserlYn("Y");
                } else {
                    vo.setElUserlYn("N");
                }
                mav = listStatisticsContLegalAdvice(vo);
            }
            // Lapsed time by Request
            else if("lap_time".equals(form.getGubun())){
                mav = listStatisticsLap_time(vo);
            }

            mav.setViewName(forwardURL);
            mav.addObject("command", form) ;
            mav.addObject("searchAuth", searchR[1]);

            return mav;
        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }

    }

    /**
     * 담당자배정현황
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsAssign(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 검토현황
        Map resultMap = statisticsService.listStatisticsAssign(vo) ;

        Locale locale1 = new Locale(vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;
        if(vo.getSrch_type().equals("C")) {
            if(vo.getSrch_div().equals("T01")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsAssign01", null, locale1);	//비지니스단계
            else if(vo.getSrch_div().equals("T02")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsAssign02", null, locale1);//계약단계
            else if(vo.getSrch_div().equals("T03")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsAssign03", null, locale1);//체결목적
        } else {
            divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsAssign04", null, locale1);//법률자문
        }

        // 결과 세팅
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("title_list", resultMap.get("div_list")) ; // 현황의 title
        mav.addObject("title_colspan", resultMap.get("div_cnt"));
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * 담당자별통계현황
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsPerson(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 통계현황
        List resultList = statisticsService.listStatisticsPerson(vo) ;
        Locale locale1 = new Locale((String)vo.getSession_user_locale());
        // 타이틀
        String divTitle = "" ;
        if(vo.getSrch_period_gbn1().equals("2")) {
            if(vo.getSrch_period_gbn2().equals("1")){
                String[] amsg = {vo.getSrch_year()};
                //vo.getSrch_year() + "년도 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson01", amsg, locale1) ;
            }
            else if(vo.getSrch_period_gbn2().equals("2")){
                String[] amsg = {vo.getSrch_year(),vo.getSrch_quarter()};
                //vo.getSrch_year() + "년 " + vo.getSrch_quarter() + "분기 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson02", amsg, locale1) ;
            }
            else if(vo.getSrch_period_gbn2().equals("3")){
                String[] amsg = {vo.getSrch_year(),vo.getSrch_month()};

                //vo.getSrch_year() + "년 " + vo.getSrch_month() + "월 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson03", amsg, locale1) ;
            }
            else {
                //실적
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson04", null, locale1) ;
            }
        } else {
            //전주실적
            divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson05", null, locale1) ;
        }

        mav.addObject("result_list", resultList) ;
        mav.addObject("title_srch", divTitle) ;
        return mav ;
    }

    /**
     * 사업부별통계현황
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsDept(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 통계현황
        Map resultMap = statisticsService.listStatisticsDept(vo) ;
        Locale locale1 = new Locale((String)vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;
        if(vo.getSrch_type().equals("C")) {
            if(vo.getSrch_div().equals("T01")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsDept01", null, locale1) ;//비지니스단계
            else if(vo.getSrch_div().equals("T02")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsDept02", null, locale1) ;//계약단계
            else if(vo.getSrch_div().equals("T03")) divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsDept03", null, locale1) ;//체결목적
        } else {
            divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsDept04", null, locale1) ;//법률자문
        }

        // 결과 세팅
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("title_list", resultMap.get("div_list")) ; // 현황의 title
        mav.addObject("title_colspan", (resultMap.get("div_cnt")==null ? 0 : (Integer)resultMap.get("div_cnt") * 3 + 3));
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * 법무팀별통계현황
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsLaw(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 통계현황
        List resultList = statisticsService.listStatisticsLaw(vo) ;
        Locale locale1 = new Locale((String)vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;
        if(vo.getSrch_period_gbn1().equals("2")) {
            if(vo.getSrch_period_gbn2().equals("1")){
                String[] amsg = {vo.getSrch_year()};
                //vo.getSrch_year() + "년도 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw01", amsg, locale1) ;
            }
            else if(vo.getSrch_period_gbn2().equals("2")){
                String[] amsg = {vo.getSrch_year(),vo.getSrch_quarter()};
                //vo.getSrch_year() + "년 " + vo.getSrch_quarter() + "분기 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw02", amsg, locale1) ;
            }
            else if(vo.getSrch_period_gbn2().equals("3")){
                String[] amsg = {vo.getSrch_year(),vo.getSrch_month()};
                //vo.getSrch_year() + "년 " + vo.getSrch_month() + "월 실적"
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw03", amsg, locale1) ;
            }
            else {
                //실적
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw04", null, locale1) ;
            }
        } else {
            //전주실적
            divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw05", null, locale1) ;
        }

        // 결과 세팅
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("result_list", resultList) ;
        return mav ;
    }

    /**
     * Lapsed time by Request
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsLap_time(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();
        Locale locale1 = new Locale((String)vo.getSession_user_locale());

        // 통계현황
        List resultList = statisticsService.listStatisticsLap_time(vo) ;
        String divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsLaw05", null, locale1) ;

        // 결과 세팅
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("result_list", resultList) ;
        return mav ;
    }


    /**
     * 검토현황 엑셀 다운로드
     * gubun 값에 따라 현황페이지가 다르다
     *   assign : 담당자배정현황
     *   pserson : 담당자별통계현황
     *   dept : 사업부별통계현황
     *   law : 법무팀별통계현황
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_" + form.getGubun() + "_excel.jsp" ;

            String fileName = "" ;
            String divTitle = "" ;

            // 검색기간 초기값 세팅
            form.setSrch_start_dt(StringUtil.nvl(form.getSrch_start_dt(), "").equals("") ? DateUtil.formatDate(DateUtil.year() + "0101", "-") : form.getSrch_start_dt()) ;
            form.setSrch_end_dt(StringUtil.nvl(form.getSrch_end_dt(), "").equals("") ? DateUtil.formatDate(DateUtil.today(), "-") : form.getSrch_end_dt()) ;

            // 구분 초기값 세팅
            form.setSrch_type(StringUtil.nvl(form.getSrch_type(), "").equals("") ? "C" : form.getSrch_type()) ; // C:게약, L:법률자문

            // 검토주관부서 초기값 세팅
            //form.setSrch_dmstfrgn_gbn((StringUtil.nvl(form.getSrch_dmstfrgn_gbn(), "").equals("") ? "H" : form.getSrch_dmstfrgn_gbn())) ; //H:국내, F:해외, IP:IP
            String[] searchR = searchBlngtAndRole(request, form.getSession_blngt_orgnz());
            form.setSrch_dmstfrgn_gbn((StringUtil.nvl(form.getSrch_dmstfrgn_gbn(), "").equals("") ? searchR[0] : form.getSrch_dmstfrgn_gbn())) ; //H:국내, F:해외, IP:IP

            // 분류 초기값 세팅
            form.setSrch_div(StringUtil.nvl(form.getSrch_div(), "").equals("") ? "T03" : form.getSrch_div()) ; // T01:비즈니스단계, T02:계약단계, T03:체결목적단계

            // 검색기간 설정1 초기값 세팅
            form.setSrch_period_gbn1(StringUtil.nvl(form.getSrch_period_gbn1(), "").equals("") ? "1" : form.getSrch_period_gbn1()) ; // 1:현재기준, 2:기간별 실적

            // 검색기간 설정2 초기값 세팅
            form.setSrch_period_gbn2(StringUtil.nvl(form.getSrch_period_gbn2(), "").equals("") ? "1" : form.getSrch_period_gbn2()) ; // 1:연도별, 2:분기별, 3:월별, 4:검색기간설정

            // 검색년도 초기값 세팅
            form.setSrch_year(StringUtil.nvl(form.getSrch_year(), "").equals("") ? DateUtil.year() : form.getSrch_year()) ;

            // 검색분기 초기값 세팅
            form.setSrch_quarter(StringUtil.nvl(form.getSrch_quarter(), "").equals("") ?
                    (Integer.parseInt(DateUtil.month())%3==0 ? String.valueOf(Integer.parseInt(DateUtil.month()) / 3) : String.valueOf((Integer.parseInt(DateUtil.month())/3)+1)) :
                    form.getSrch_quarter()) ;

            // 검색월 초기값 세팅
            form.setSrch_month(StringUtil.nvl(form.getSrch_month(), "").equals("") ? DateUtil.month() : form.getSrch_month()) ;

            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;

            // 담당자배정현황
            if("assign".equals(form.getGubun())){
                mav = listStatisticsAssign(vo);
                //담당자배정현황
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown01", null, new RequestContext(request).getLocale()) ;
            }
            // 담당자별통계현황
            else if("person".equals(form.getGubun())){
                mav = listStatisticsPerson(vo) ;
                //담당자별통계현황
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown02", null, new RequestContext(request).getLocale()) ;
            }
            // 사업부별통계현황
            else if("dept".equals(form.getGubun())){
                mav = listStatisticsDept(vo) ;
                //사업부별통계현황
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown03", null, new RequestContext(request).getLocale()) ;
            }
            // 법무팀별통계현황
            else if("law".equals(form.getGubun())){
                vo.setSrch_dmstfrgn_gbn(searchR[1]);
                mav = listStatisticsLaw(vo);
                //법무팀별통계현황
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown04", null, new RequestContext(request).getLocale()) ;
            }
            // 전자 검토자 통계
            else if("ElecReviewer".equals(form.getGubun())){

                mav = listStatisticsElecReviewer(request,response);
                // 타이틀
                if(vo.getSrch_period_gbn1().equals("2")) {
                    if(vo.getSrch_period_gbn2().equals("1")){
                        String[] amsg = {vo.getSrch_year()};
                        //vo.getSrch_year() + "년도 실적"
                        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson01", amsg, new RequestContext(request).getLocale()) ;
                    }
                    else if(vo.getSrch_period_gbn2().equals("2")){
                        String[] amsg = {vo.getSrch_year(),vo.getSrch_quarter()};
                        //vo.getSrch_year() + "년 " + vo.getSrch_quarter() + "분기 실적"
                        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson02", amsg, new RequestContext(request).getLocale()) ;
                    }
                    else if(vo.getSrch_period_gbn2().equals("3")){
                        String[] amsg = {vo.getSrch_year(),vo.getSrch_month()};

                        //vo.getSrch_year() + "년 " + vo.getSrch_month() + "월 실적"
                        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson03", amsg, new RequestContext(request).getLocale()) ;
                    }
                    else {
                        //실적
                        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson04", null, new RequestContext(request).getLocale()) ;
                    }
                } else {
                    //금주실적
                    divTitle = (String)messageSource.getMessage("las.page.field.statistics.perform2", null, new RequestContext(request).getLocale()) ;
                }
                //전자 검토자 통계
                fileName = "전자 검토자 통계";

            }
            // 구주통계1 : Run-Time by Request
            else if("guju1".equals(form.getGubun())){
                // 구주통계1
                mav = listStatisticsGuju_law1_Excel(vo) ;
                //구주통계1 Run-Time by Request
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown05", null, new RequestContext(request).getLocale()) ;

            }
            // 구주 기존통계 화면 : Contract & Legal Advice Statistics
            else if("c_l_advice".equals(form.getGubun())){
                // 구주통계1
                mav = listStatisticsContLegalAdvice_Excel(vo) ;
                //구주통계1 Run-Time by Request
                fileName = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown05", null, new RequestContext(request).getLocale()) ;

            }
            // Lapsed time by Request
            else if("lap_time".equals(form.getGubun())){
                mav = listStatisticsLap_time_Excel(vo) ;
                //Lapsed time by Request
                fileName = (String)messageSource.getMessage("las.page.field.statistics.lap.title", null, new RequestContext(request).getLocale()) ;

            }

            mav.setViewName(forwardURL);
            mav.addObject("title_srch", divTitle) ;
            mav.addObject("command", form) ;

            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("EUC-KR"), "8859_1") + ".xls");
            response.setHeader("Content-Description", "JSP Generated Data");
            response.setContentType("application/vnd.ms-excel");

            return mav;
        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }
    }

    /**
     * 통계용 권한 조회 함수
     * @param request
     * @param session_blngt_orgnz
     * @return
     * @throws Exception
     */
    private String[] searchBlngtAndRole(HttpServletRequest request, String session_blngt_orgnz) throws Exception{

        String[] result = new String[2];

        //H:국내, F:해외, IP:IP

        if(ClmsBoardUtil.searchRole(request, ROLE_0) || ("A00000001".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_1))){ //시스템 관리자, 국내법무 그룹장
            result[0] = "H";
            result[1] = "H1";
        }else if("A00000001".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_2)){ //국내법무 담당자
            result[0] = "H";
            result[1] = "H2";
        }else if("A00000002".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_1)){ //해외법무 그룹장
            result[0] = "F";
            result[1] = "F1";
        }else if("A00000002".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_2)){ //해외법무 담당자
            result[0] = "F";
            result[1] = "F2";
        }else if("A00000003".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_1)){ //IP 그룹장
            result[0] = "IP";
            result[1] = "IP1";
        }else if("A00000003".equals(session_blngt_orgnz) && ClmsBoardUtil.searchRole(request, ROLE_2)){ //IP 담당자
            result[0] = "IP";
            result[1] = "IP2";
        }else{
            result[0] = "H";
            result[1] = "H2"; //아무조건을 충족시키지 않을 떄는 국내 조회조건만 있다.
        }

        return result;
    }


    /**
     * 검토 의뢰 건수
     *
     * @param
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{

            /*********************************************************
             * Parameter
             **********************************************************/
            List resultList = null;
            int resultsize = 0;

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();

            /*********************************************************
             * Parameter setting
             **********************************************************/
            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_consideration_l.jsp" ;


            form.setSrch_div(StringUtil.nvl(form.getSrch_div(), "").equals("") ? "M" : form.getSrch_div()) ;

            /*********************************************************
             * Form & VO Binding
             **********************************************************/
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);
            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;

            /*********************************************************
             * Service
             **********************************************************/
            resultList = statisticsService.listStatisticsConsideration(vo);


            if(resultList != null){
                resultsize = resultList.size();
            }

            /*********************************************************
             * ModelAndView
             **********************************************************/
            mav.setViewName(forwardURL);
            mav.addObject("command", form) ;
            mav.addObject("ylist", getYearList()) ;
            mav.addObject("wlist", getWeekList()) ;

            Calendar c = Calendar.getInstance();

            mav.addObject("resultsize", resultsize) ;
            mav.addObject("currentW", c.get(Calendar.WEEK_OF_YEAR)) ;

            if("M".equals(vo.getSrch_div())){
                mav.addObject("MresultList", resultList);
            }else if("W".equals(vo.getSrch_div())){
                mav.addObject("WresultList", resultList);
            }

            return mav;

        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }
    }


    /**
     * 전자 검토자 통계
     *
     * @param
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsElecReviewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            /*********************************************************
             * Parameter
             **********************************************************/
            String forwardURL    = "";

            StatisticsForm 	form       = new StatisticsForm();
            StatisticsVO   	vo         = new StatisticsVO();
            List   			resultList = null;

            /*********************************************************
             * Parameter setting
             **********************************************************/
            //jsp 지정해야합니다
            forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_ElecReviewer_l.jsp";

            /*********************************************************
             * Form & VO Binding
             **********************************************************/


            bind(request, form);
            bind(request, vo);

            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);


            Locale locale1 = new Locale(vo.getSession_user_locale());

            form.setSrch_dmstfrgn_gbn(StringUtil.nvl(form.getSrch_dmstfrgn_gbn(), "").equals("") ? "H" : form.getSrch_dmstfrgn_gbn()) ;

            // 검색기간 설정1 초기값 세팅
            form.setSrch_period_gbn1(StringUtil.nvl(form.getSrch_period_gbn1(), "").equals("") ? "1" : form.getSrch_period_gbn1()) ; // 1:현재기준, 2:기간별 실적

            // 검색기간 설정2 초기값 세팅
            form.setSrch_period_gbn2(StringUtil.nvl(form.getSrch_period_gbn2(), "").equals("") ? "1" : form.getSrch_period_gbn2()) ; // 1:연도별, 2:분기별, 3:월별, 4:검색기간설정

            // 검색년도 초기값 세팅
            form.setSrch_year(StringUtil.nvl(form.getSrch_year(), "").equals("") ? DateUtil.year() : form.getSrch_year()) ;

            // 검색분기 초기값 세팅
            form.setSrch_quarter(StringUtil.nvl(form.getSrch_quarter(), "").equals("") ?
                    (Integer.parseInt(DateUtil.month())%3==0 ? String.valueOf(Integer.parseInt(DateUtil.month()) / 3) : String.valueOf((Integer.parseInt(DateUtil.month())/3)+1)) :
                    form.getSrch_quarter()) ;

            // 검색월 초기값 세팅
            form.setSrch_month(StringUtil.nvl(form.getSrch_month(), "").equals("") ? DateUtil.month() : form.getSrch_month()) ;

            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;


            ListOrderedMap lom1 = statisticsService.selectStatisticsSearch(vo);
            vo.setSrch_beforeweek_s(lom1.get("beforeweek_s").toString());
            vo.setSrch_beforeweek_e(lom1.get("beforeweek_e").toString());
            vo.setSrch_beforemonth_s(lom1.get("beforemonth_s").toString());
            vo.setSrch_beforemonth_e(lom1.get("beforemonth_e").toString());
            vo.setSrch_curweek_s(lom1.get("curweek_s").toString());
            vo.setSrch_curweek_e(lom1.get("curweek_e").toString());
            vo.setSrch_curmonth_s(lom1.get("curmonth_s").toString());
            vo.setSrch_curmonth_e(lom1.get("curmonth_e").toString());
            form.setSrch_beforeweek_s(lom1.get("beforeweek_s").toString());
            form.setSrch_beforeweek_e(lom1.get("beforeweek_e").toString());
            form.setSrch_beforemonth_s(lom1.get("beforemonth_s").toString());
            form.setSrch_beforemonth_e(lom1.get("beforemonth_e").toString());
            form.setSrch_curweek_s(lom1.get("curweek_s").toString());
            form.setSrch_curweek_e(lom1.get("curweek_e").toString());
            form.setSrch_curmonth_s(lom1.get("curmonth_s").toString());
            form.setSrch_curmonth_e(lom1.get("curmonth_e").toString());

            // 타이틀
            String divTitle = "" ;
            if(vo.getSrch_period_gbn1().equals("2")) {
                if(vo.getSrch_period_gbn2().equals("1")){
                    String[] amsg = {vo.getSrch_year()};
                    //vo.getSrch_year() + "년도 실적"
                    divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson01", amsg, locale1) ;
                }
                else if(vo.getSrch_period_gbn2().equals("2")){
                    String[] amsg = {vo.getSrch_year(),vo.getSrch_quarter()};
                    //vo.getSrch_year() + "년 " + vo.getSrch_quarter() + "분기 실적"
                    divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson02", amsg, locale1) ;
                }
                else if(vo.getSrch_period_gbn2().equals("3")){
                    String[] amsg = {vo.getSrch_year(),vo.getSrch_month()};

                    //vo.getSrch_year() + "년 " + vo.getSrch_month() + "월 실적"
                    divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson03", amsg, locale1) ;
                }
                else {
                    //실적
                    divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsPerson04", null, locale1) ;
                }
            } else {
                //금주실적
                divTitle = (String)messageSource.getMessage("las.page.field.statistics.perform2", null, locale1) ;
            }



            /*********************************************************
             * Service
             **********************************************************/
            resultList = statisticsService.listStatisticsElecReviewer(vo);

            /*********************************************************
             * ModelAndView
             **********************************************************/

            ModelAndView mav = new ModelAndView();

            mav.setViewName(forwardURL);

            mav.addObject("title_srch", divTitle) ;
            mav.addObject("resultList", resultList);
            mav.addObject("command", form);

            return mav;

        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("NoticeController.listNotice() Exception !!");
        }catch (Throwable t) {
            t.printStackTrace();
            throw new Exception("NoticeController.listNotice() Throwable !!");
        }
    }


    /**
     * 계약 단계별 평균 소요시간 ( 단계별소요시간 )
     *
     * @param
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsLeadtimeByStep(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            /*********************************************************
             * Parameter
             **********************************************************/
            String forwardURL    = "";
            String returnMessage = "";
            String accessLevel	 = "";

            StatisticsForm 	form       = null;
            StatisticsVO   	vo         = null;
            List   			resultList = null;


            /*********************************************************
             * Session Check
             **********************************************************/
            HttpSession session = request.getSession(false);

            if(session==null)
                throw new Exception("##### Session is null #####");

            /*********************************************************
             * Parameter setting
             **********************************************************/
            forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsLtByStep_l.jsp";


            /*********************************************************
             * Form & VO Binding
             **********************************************************/
            Date d = new Date();
            form = new StatisticsForm();
            vo   = new StatisticsVO();

            bind(request, form);
            bind(request, vo);

            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);


            if(vo.getSrch_year()==null){
                vo.setSrch_year(1900+d.getYear()+"");
                form.setSrch_year(1900+d.getYear()+"");
            }
            /*********************************************************
             * Service
             **********************************************************/
            resultList = statisticsService.listStatisticsLeadtimeByStep(vo);

            if (resultList != null && resultList.size() > 0) {
                ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

                // 메시지처리 - 정상적으로 조회되었습니다.
                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
                    returnMessage = (String)request.getAttribute("returnMessage");//??
                }else {
                    //메뉴 최초 진입 시 메세지 여부
                    if("Y".equals(vo.getDoSearch()))
                        returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
                    //if else 조건에 따라 다른 메세지
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
            mav.addObject("lom", lom);
            mav.addObject("command", form);
            mav.addObject("returnMessage", returnMessage);

            return mav;

        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("NoticeController.listNotice() Exception !!");
        }catch (Throwable t) {
            t.printStackTrace();
            throw new Exception("NoticeController.listNotice() Throwable !!");
        }
    }


    /**
     * 계약체결건수
     *
     * @param
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsConsultation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            List resultList = null;
            int resultsize = 0;

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();

            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_consultation_l.jsp" ;

            form.setSrch_div(StringUtil.nvl(form.getSrch_div(), "").equals("") ? "M" : form.getSrch_div()) ;

            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);
            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;

            resultList = statisticsService.listStatisticsConsultation(vo);

            if(resultList != null){
                resultsize = resultList.size();
            }

            mav.setViewName(forwardURL);
            mav.addObject("command", form) ;
            mav.addObject("ylist", getYearList()) ;
            mav.addObject("wlist", getWeekList()) ;

            Calendar c = Calendar.getInstance();
            c.get(Calendar.WEEK_OF_YEAR);

            mav.addObject("currentW", c.get(Calendar.WEEK_OF_YEAR)) ;
            mav.addObject("resultsize", resultsize) ;

            if("M".equals(vo.getSrch_div())){
                mav.addObject("MresultList", resultList);
            }else if("W".equals(vo.getSrch_div())){
                mav.addObject("WresultList", resultList);
            }

            return mav;

        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("NoticeController.listNotice() Exception !!");
        }catch (Throwable t) {
            t.printStackTrace();
            throw new Exception("NoticeController.listNotice() Throwable !!");
        }
    }



    /**
     * 통계 엑셀 다운로드
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void listStatisticsExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{

            List resultList = null;

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;


            if("consideration".equals(form.getGubun())){//검토의뢰건수
                resultList = statisticsService.listStatisticsConsideration(vo);
            }else if("consultation".equals(form.getGubun())){//계약체결건수
                resultList = statisticsService.listStatisticsConsultation(vo);
            }

            /*********************************************************
             * Excel DownLoad
             **********************************************************/
            String		fileNm			= "";
            String[]	sheetNmAry		= new String[1];
            String[]	titleInfo		= new String[1];

            String[]	subTitleInfo	= null;
            String[]	columnInfo		= null;
            short[]		columnAlign		= null;

            if("consideration".equals(form.getGubun())){//검토의뢰건수
                fileNm		= (String)messageSource.getMessage("las.page.field.statistics.consider", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
                sheetNmAry[0]	= (String)messageSource.getMessage("las.page.field.statistics.consider", null, new RequestContext(request).getLocale()).replaceAll("\\p{Space}", "");
                titleInfo[0]	= (String)messageSource.getMessage("las.page.field.statistics.consider", null, new RequestContext(request).getLocale());
            }else if("consultation".equals(form.getGubun())){//계약체결건수
                fileNm		= (String)messageSource.getMessage("las.page.field.statistics.consultation", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
                sheetNmAry[0]	= (String)messageSource.getMessage("las.page.field.statistics.consultation", null, new RequestContext(request).getLocale()).replaceAll("\\p{Space}", "");
                titleInfo[0]	= (String)messageSource.getMessage("las.page.field.statistics.consultation", null, new RequestContext(request).getLocale());
            }


            subTitleInfo		= ((String)messageSource.getMessage("las.page.field.statistics.complist", null, new RequestContext(request).getLocale())).split("\\|");
            columnInfo		= new String[]{"gubun", "a1", "b2", "c3", "d4", "e5", "f6", "g7", "h8", "i9", "j10", "k11", "l12","total"};
            columnAlign		= new short[]{ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER};

            ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

            int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수

            List rrow = new ArrayList<Object>();


            if("M".equals(vo.getSrch_div())){
                rrow.add(0, resultList.get(12));
                for(int i =0;i<12;i++){
                    rrow.add(i+1, resultList.get(i));
                }
                resultList = rrow;
            }


            for(int i=0;i<sheetsCount;i++) {
                excel.setBold(true);
                excel.setFontColor(ExcelBuilder.COLOR_BLACK);
                excel.setFontName("맑은 고딕");
                excel.setFontSize((short)11);
                excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
                excel.setBorder(true);

                excel.addTitleRow(i, subTitleInfo);
                excel.setDefaultStyle();

                excel.setAlign(columnAlign);
                excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
                excel.setBorder(new boolean[]{true});
                excel.addRows(i, columnInfo, resultList);
                excel.setDefaultStyle();
            }
            excel.download(fileNm, response);

        }catch (Exception e) {
            e.printStackTrace();
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }


    /**
     * 년간 부서별 계약현황
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();

            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_contract_l.jsp" ;

            // 구분 초기값 세팅
            form.setSrch_type("C") ; //C:계약, L:법률자문
            vo.setSrch_type(form.getSrch_type()) ;
            vo.setSrch_div("T03");

            if(StringUtil.isNull(form.getSrch_year())){
                form.setSrch_year(DateUtil.year());
                vo.setSrch_year(DateUtil.year());
            }
            // 계약유형 목록(화면에서 목록 타이틀)
            List divList = statisticsService.listCntrtDiv(vo) ;

            // 검토현황
            List resultList = statisticsService.listStatisticsContract(vo) ;

            // 결과 세팅
            mav.setViewName(forwardURL);
            mav.addObject("title_list"		, divList) ; // 현황의 title
            mav.addObject("title_colspan"	, divList==null ? 0 : divList.size());
            mav.addObject("result_list"		, resultList) ;
            mav.addObject("command"			, form) ;
            return mav ;
        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }
    }

    /**
     * 월간 부서별 계약현황
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsContractByMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();

            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            if(StringUtil.isNull(form.getSrch_year())){
                form.setSrch_year(DateUtil.year());
                vo.setSrch_year(DateUtil.year());
            }

            String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_contract_l.jsp" ;

            // 검토현황
            List list = statisticsService.listStatisticsContractByMonth(vo) ;

            Locale locale1 = new Locale(vo.getSession_user_locale());


            // 결과 세팅
            mav.setViewName(forwardURL);
            mav.addObject("list"		, list) ;
            mav.addObject("command"		, form) ;
            return mav ;
        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }
    }

    /**
     * 전사 부서별 자문현황
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView listStatisticsLawCnslt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{
            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();

            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            // 구분 초기값 세팅
            form.setSrch_type("L") ;
            vo.setSrch_type(form.getSrch_type()) ;
            vo.setSrch_div("T03");
            vo.setSrch_grp_cd("N2") ; // 해외인경우 N3, 국내/ip는 N2
            vo.setSrch_dmstfrgn_gbn("H");//H:국내, F:해외, IP:IP

            if(StringUtil.isNull(form.getSrch_year())){
                form.setSrch_year(DateUtil.year());
                vo.setSrch_year(DateUtil.year());
            }

            String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_lawCnslt_l.jsp" ;

            // 법률유형 목록(화면에서 목록 타이틀)
            List divList = statisticsService.listCntrtDiv(vo) ;

            // 검토현황
            List resultList = statisticsService.listStatisticsLawCnslt(vo) ;

            // 결과 세팅
            mav.setViewName(forwardURL);
            mav.addObject("title_list"		, divList) ; // 현황의 title
            mav.addObject("title_colspan"	, divList==null ? 0 : divList.size());
            mav.addObject("result_list"		, resultList) ;
            mav.addObject("command"			, form) ;

            return mav ;
        } catch (Exception e) {
            e.printStackTrace() ;
            throw e ;
        }
    }


    /**
     * 전사 계약/자문현황 엑셀 다운로드
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void listStatisticsContractExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{

            List resultList = null;
            List divList = null;

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            /*********************************************************
             * Excel DownLoad
             **********************************************************/
            String 		fileTitle		= "";
            String		fileNm			= "";
            String[]	sheetNmAry		= new String[1];
            String[]	titleInfo		= new String[1];

            String[]	subTitleInfo	= null;
            String[]	columnInfo		= null;
            short[]		columnAlign		= null;

            String 		locale			= vo.getSession_user_locale();

            if("C".equals(form.getSrch_gbn())){ //년간 부서별현황

                vo.setSrch_type("C") ; //C:계약, L:법률자문
                vo.setSrch_div("T03");

                resultList = statisticsService.listStatisticsContract(vo) ;

                divList = statisticsService.listCntrtDiv(vo) ; //현황 타이틀 리스트

                fileTitle 		= (String)messageSource.getMessage("las.page.field.statistics.contract01", null, new RequestContext(request).getLocale());

            }else if("M".equals(form.getSrch_gbn())){ //월간 부서별현황
                divList = new ArrayList();
                ListOrderedMap monMap = null;
                String day = "";
                String[] dayArray = new String[]{	(String)messageSource.getMessage("clm.field.signmng.m1", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m2", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m3", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m4", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m5", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m6", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m7", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m8", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m9", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m10", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m11", null, new RequestContext(request).getLocale())
                        ,(String)messageSource.getMessage("clm.field.signmng.m12", null, new RequestContext(request).getLocale())
                };
                for(int i = 0;i < 12;i++){
                    monMap = new ListOrderedMap();
                    day = StringUtil.lpad(StringUtil.int2Str(i+1),2,"0".charAt(0));
                    monMap.put("cd"		, "MON_"+day);
                    monMap.put("cd_nm"	, dayArray[i]);
                    divList.add(monMap);
                }
                resultList = statisticsService.listStatisticsContractByMonth(vo) ;

                fileTitle 		= (String)messageSource.getMessage("las.page.field.statistics.contract02", null, new RequestContext(request).getLocale());

            }else if("L".equals(form.getSrch_gbn())){ //전사자문현황
                vo.setSrch_type("L") ; //C:계약, L:법률자문
                vo.setSrch_div("T03");
                vo.setSrch_grp_cd("N2") ; // 해외인경우 N3, 국내/ip는 N2
                vo.setSrch_dmstfrgn_gbn("H");//H:국내, F:해외, IP:IP

                divList = statisticsService.listCntrtDiv(vo) ; //현황 타이틀 리스트

                // 검토현황
                resultList = statisticsService.listStatisticsLawCnslt(vo) ;

                fileTitle 		= (String)messageSource.getMessage("las.page.field.statistics.contract03", null, new RequestContext(request).getLocale());
            }

            fileNm 			= fileTitle + DateUtil.today() + ".xls";
            sheetNmAry[0]	= fileTitle + DateUtil.today();
            titleInfo[0]	= fileTitle;

            if(null != divList){
                subTitleInfo	= new String[divList.size()+2];
                columnInfo		= new String[divList.size()+2];
                columnAlign		= new short[divList.size()+2];
            }else{
                subTitleInfo	= new String[2];
                columnInfo		= new String[2];
                columnAlign		= new short[2];
            }

            subTitleInfo[0] = (String)messageSource.getMessage("las.page.field.mainproject.operdiv_respman_dept_nm", null, new RequestContext(request).getLocale());//부서
            subTitleInfo[1] = "ko".equals(locale.toLowerCase()) ? "계" : "TOTAL";
            columnInfo[0] 	= "REQ_DEPT_NM";
            columnInfo[1] 	= "TOTAL_CNT";
            columnAlign[0]	= ExcelBuilder.ALIGN_LEFT;
            columnAlign[1]	= ExcelBuilder.ALIGN_RIGHT;

            if(null != divList){
                ListOrderedMap lom = null ;
                for(int i =0; i < divList.size(); i++){
                    lom = (ListOrderedMap)divList.get(i);

                    subTitleInfo[i+2] 	= (String)lom.get("cd_nm");
                    columnInfo[i+2]		= (String)lom.get("cd");
                    columnAlign[i+2]	= ExcelBuilder.ALIGN_RIGHT;
                }
            }

            ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

            int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수

            for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop

                excel.setBold(true);
                excel.setFontColor(ExcelBuilder.COLOR_BLACK);
                excel.setFontName("맑은 고딕");
                excel.setFontSize((short)11);
                excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
                excel.setBorder(true);

                excel.addTitleRow(i, subTitleInfo);
                excel.setDefaultStyle();

                excel.setAlign(columnAlign);
                excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
                excel.setBorder(new boolean[]{true});

                excel.addRows(i, columnInfo, resultList);

                excel.setDefaultStyle();

            }

            excel.download(fileNm, response);

        }catch (Exception e) {
            e.printStackTrace();
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * 회사별 계약처리 소요시간(단계별소요시간) 엑셀 다운로드
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void listStatisticsLeadTimeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        try{

            List resultList = null;

            StatisticsForm form = new StatisticsForm();
            StatisticsVO vo = new StatisticsVO();
            bind(request, form);
            bind(request, vo);
            COMUtil.getUserAuditInfo(request, form);
            COMUtil.getUserAuditInfo(request, vo);

            // form -> vo 값 복사
            ObjectCopyUtil.copyValueObject(form, vo) ;

            resultList = statisticsService.listStatisticsLeadtimeByStep(vo);

            /*********************************************************
             * Excel DownLoad
             **********************************************************/
            String 		fileTitle		= "";
            String		fileNm			= "";
            String[]	sheetNmAry		= new String[1];
            String[]	titleInfo		= new String[1];

            String[]	subTitleInfo	= null;
            String[]	columnInfo		= null;
            short[]		columnAlign		= null;

            String 		locale			= "";

            //fileNm			= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today() + ".xls";
            //sheetNmAry[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel01", null, new RequestContext(request).getLocale()) + DateUtil.today();
            //titleInfo[0]	= (String)messageSource.getMessage("clm.page.field.manage.listManageExcel02", null, new RequestContext(request).getLocale());

            locale 			= vo.getSession_user_locale();

            fileTitle 		= (String)messageSource.getMessage("las.page.field.statistics.leadTimeByStep", null, new RequestContext(request).getLocale());
            fileNm 			= fileTitle + DateUtil.today() + ".xls";
            sheetNmAry[0]	= fileTitle + DateUtil.today();
            titleInfo[0]	= fileTitle;

            subTitleInfo	= new String[]{ (String)messageSource.getMessage("las.page.field.user.comp_nm", null, new RequestContext(request).getLocale()),
                    (String)messageSource.getMessage("clm.page.field.srch.consideration", null, new RequestContext(request).getLocale()),
                    (String)messageSource.getMessage("clm.page.field.srch.consultation", null, new RequestContext(request).getLocale()),
                    (String)messageSource.getMessage("clm.page.field.srch.conclusion", null, new RequestContext(request).getLocale())
            };

            if(locale.equals("ko")){
                columnInfo		= new String[]{"COMP_NM","REQ_LT","CNT_LT","REG_LT"};
            }else{
                columnInfo		= new String[]{"COMP_NM_ENG","REQ_LT","CNT_LT","REG_LT"};
            }

            columnAlign		= new short[]{ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_RIGHT,ExcelBuilder.ALIGN_RIGHT, ExcelBuilder.ALIGN_RIGHT};

            ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

            int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수

            List rrow = new ArrayList<Object>();


            if("M".equals(vo.getSrch_div())){
                rrow.add(0, resultList.get(12));
                for(int i =0;i<12;i++){
                    rrow.add(i+1, resultList.get(i));
                }
                resultList = rrow;
            }


            for(int i=0;i<sheetsCount;i++) {
                excel.setBold(true);
                excel.setFontColor(ExcelBuilder.COLOR_BLACK);
                excel.setFontName("맑은 고딕");
                excel.setFontSize((short)11);
                excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
                excel.setBorder(true);

                excel.addTitleRow(i, subTitleInfo);
                excel.setDefaultStyle();

                excel.setAlign(columnAlign);
                excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
                excel.setBorder(new boolean[]{true});
                excel.addRows(i, columnInfo, resultList);
                excel.setDefaultStyle();
            }
            excel.download(fileNm, response);

        }catch (Exception e) {
            e.printStackTrace();
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * 년(year)도 가져오기
     * 현재 기준 과거 5년 가져온다
     * @param
     * @return
     * @throws Exception
     */
    private String[] getYearList() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        String ydate = sdf.format(new java.util.Date());

        int currentYear = Integer.parseInt(ydate);
        String s[] = new String[6];
        int idx = 0;
        for (int i=currentYear; i>=currentYear-5; i--) {
            s[idx++] = String.valueOf(i);
        }
        return s;
    }

    /**
     * 주(week) 가져오기
     * @param
     * @return
     * @throws Exception
     */
    private String[] getWeekList() {

        String s[] = new String[52];
        for (int i=0;i<52;i++)
            s[i] = String.valueOf((i+1));

        return s;
    }

    /**
     * 구주통계1 : Run-Time by Request
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsGuju_law1(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        /*********************************************************
         * Page  setting
         **********************************************************/
        PageUtil 	pageUtil   	= null;
        pageUtil = new PageUtil();

        pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(vo.getCurPage(), "1")));

        vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
        vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

        // 구주통계1
        Map resultMap = statisticsService.listStatisticsGuju_law1(vo) ;

        List resultList = (List)resultMap.get("result_list");
        if (resultList != null && resultList.size() > 0) {
            ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

            pageUtil.setTotalRow(lom.get("total_cnt"));
            pageUtil.setRowPerPage(10);
            pageUtil.setGroup();
        }

        Locale locale1 = new Locale(vo.getSession_user_locale());

        // 타이틀
        String divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown05", null, locale1);

        // 결과 세팅
        mav.addObject("pageUtil", pageUtil);
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("title_list", resultMap.get("div_list")) ; // 현황의 title
        mav.addObject("title_colspan", resultMap.get("div_cnt"));
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * 구주통계1 : Run-Time by Request Excel download
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsGuju_law1_Excel(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 검토현황
        Map resultMap = statisticsService.listStatisticsGuju_law1_Excel(vo) ;

        Locale locale1 = new Locale(vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;//Run-Time by Request
        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown05", null, locale1);

        // 결과 세팅
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * 구주 기존통계 화면 : Contract & Legal Advice Statistics
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsContLegalAdvice(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 구주통계1
        Map resultMap = statisticsService.listStatisticsContLegalAdvice(vo) ;

        List resultList = (List)resultMap.get("result_list");

        Locale locale1 = new Locale(vo.getSession_user_locale());

        String user_comp = (String)vo.getSession_comp_cd();

        if("EHQ".equals(user_comp) ){
            user_comp = "EHQSEUK";
        }

        //System.out.println("user_comp = " + user_comp);

        // 타이틀
        String divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown07", null, locale1);

        // 결과 세팅
        mav.addObject("user_info", user_comp);
        mav.addObject("title_srch", divTitle) ;
        mav.addObject("title_list", resultMap.get("div_list")) ; // 현황의 title
        mav.addObject("title_colspan", resultMap.get("div_cnt"));
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * 구주 기존통계 화면 : Contract & Legal Advice Statistics Excel download
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsContLegalAdvice_Excel(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 검토현황
        Map resultMap = statisticsService.listStatisticsContLegalAdvice_Excel(vo) ;

        Locale locale1 = new Locale(vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;//Contract & Legal Advice Statistics
        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown07", null, locale1);

        // 결과 세팅
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }

    /**
     * Lapsed time by Request Excel download
     *
     * @param vo
     * @return
     * @throws Exception
     */
    private ModelAndView listStatisticsLap_time_Excel(StatisticsVO vo) throws Exception {
        ModelAndView mav = new ModelAndView();

        // 검토현황
        Map resultMap = statisticsService.listStatisticsLap_time_Excel(vo) ;

        Locale locale1 = new Locale(vo.getSession_user_locale());

        // 타이틀
        String divTitle = "" ;//Lapsed time by Request
        divTitle = (String)messageSource.getMessage("las.page.field.statistics.listStatisticsExcelDown05", null, locale1);

        // 결과 세팅
        mav.addObject("result_list", resultMap.get("result_list")) ;

        return mav ;
    }


    /**
     * Lapsed time by Request Statistics_Chart
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView Statistics_Chart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        StatisticsForm form = new StatisticsForm();
        StatisticsVO vo = new StatisticsVO();
        bind(request, form);
        bind(request, vo);
        COMUtil.getUserAuditInfo(request, form);
        COMUtil.getUserAuditInfo(request, vo);

        String forwardURL = "/WEB-INF/jsp/las/statistics/Statistics_Chart_l.jsp";

        // 결과 세팅
        mav.setViewName(forwardURL);
        mav.addObject("command", form);
        return mav ;
    }

    /**
     * Lapsed time by Request Statistics_Chart data
     * @param request
     * @param response
     * @throws Exception
     */
    public void statistics_List(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            StatisticsForm form = createAndBindForm(request);
            StatisticsVO vo = createAndBindVO(request, form);

            setDefaultValues(form, request);
            copyFormToVO(form, vo);

            ModelAndView listStatisticsMav = listStatisticsContLegalAdvice(vo);
            JSONObject listStatisticsObj = processStatisticsData(request, listStatisticsMav, form);

            sendResponse(response, listStatisticsObj);
//                listStatisticsObj.put("rows", listStatisticsObj);

        } catch (Exception e) {
            handleException(response, e, request);
        } catch (Throwable t) {
            handleThrowable(response, t, request);
        }
    }

    private StatisticsForm createAndBindForm(HttpServletRequest request) throws Exception {
        StatisticsForm form = new StatisticsForm();
        bind(request, form);
        COMUtil.getUserAuditInfo(request, form);
        return form;
    }

    private StatisticsVO createAndBindVO(HttpServletRequest request, StatisticsForm form) throws Exception {
        StatisticsVO vo = new StatisticsVO();
        bind(request, vo);
        COMUtil.getUserAuditInfo(request, vo);
        vo.setSort_yn("Y");
        return vo;
    }

    private void setDefaultValues(StatisticsForm form, HttpServletRequest request) throws Exception {
        // 검색기간 초기값 세팅
        form.setSrch_start_dt(getDefaultDate(form.getSrch_start_dt(), DateUtil.year() + "0101"));
        form.setSrch_end_dt(getDefaultDate(form.getSrch_end_dt(), DateUtil.today()));

        // 구분 초기값 세팅
        String srchType = form.getSrch_type();
        if ("c_l_advice".equals(form.getGubun())) {
            form.setSrch_type(StringUtil.nvl(srchType, "").equals("") ? "PC" : srchType);
        } else {
            form.setSrch_type(StringUtil.nvl(srchType, "").equals("") ? "C" : srchType);
        }

        // 검토주관부서 초기값 세팅
        String[] searchR = searchBlngtAndRole(request, form.getSession_blngt_orgnz());
        String srchDmstfrgnGbn = form.getSrch_dmstfrgn_gbn();
        form.setSrch_dmstfrgn_gbn(StringUtil.nvl(srchDmstfrgnGbn, "").equals("") ? searchR[0] : srchDmstfrgnGbn);

        // 분류 초기값 세팅
        form.setSrch_div(StringUtil.nvl(form.getSrch_div(), "").equals("") ? "T03" : form.getSrch_div());

        // 검색기간 설정1 초기값 세팅
        form.setSrch_period_gbn1(StringUtil.nvl(form.getSrch_period_gbn1(), "").equals("") ? "1" : form.getSrch_period_gbn1());

        // 검색기간 설정2 초기값 세팅
        form.setSrch_period_gbn2(StringUtil.nvl(form.getSrch_period_gbn2(), "").equals("") ? "1" : form.getSrch_period_gbn2());

        // 검색년도 초기값 세팅
        form.setSrch_year(StringUtil.nvl(form.getSrch_year(), "").equals("") ? DateUtil.year() : form.getSrch_year());

        // 검색분기 초기값 세팅
        form.setSrch_quarter(getDefaultQuarter(form.getSrch_quarter()));

        // 검색월 초기값 세팅
        form.setSrch_month(StringUtil.nvl(form.getSrch_month(), "").equals("") ? DateUtil.month() : form.getSrch_month());
    }

    private String getDefaultDate(String value, String defaultDate) {
        return StringUtil.nvl(value, "").equals("") ? DateUtil.formatDate(defaultDate, "-") : value;
    }

    private String getDefaultQuarter(String value) {
        String month = DateUtil.month();
        int quarter = Integer.parseInt(month) % 3 == 0 ?
                Integer.parseInt(month) / 3 :
                (Integer.parseInt(month) / 3) + 1;
        return StringUtil.nvl(value, "").equals("") ? String.valueOf(quarter) : value;
    }

    private void copyFormToVO(StatisticsForm form, StatisticsVO vo) {
        try {
            ObjectCopyUtil.copyValueObject(form, vo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject processStatisticsData(HttpServletRequest request, ModelAndView listStatisticsMav, StatisticsForm form) throws Exception {
        JSONObject listStatisticsObj = new JSONObject();

        int startnum = getStartNumber(form.getSrch_type());
        String cols = buildColumns(listStatisticsMav, form, startnum);
        listStatisticsObj.put("cols", cols);

        String rowsList = buildRows(request, listStatisticsMav, form, startnum);
        listStatisticsObj.put("rows", rowsList);

        return listStatisticsObj;
    }

    private int getStartNumber(String srchType) {
        return "PL".equals(srchType) ? 0 : 1;
    }

    private String buildColumns(ModelAndView listStatisticsMav, StatisticsForm form, int startnum) throws Exception {
        StringBuilder colsBuilder = new StringBuilder();
        ArrayList<Object> listStatisticsArray = (ArrayList<Object>) listStatisticsMav.getModel().get("result_list");
        ListOrderedMap listOrderMap = (ListOrderedMap) listStatisticsArray.get(0);

        for (int i = startnum; i < listOrderMap.size(); i++) {
            String key = listOrderMap.get(i).toString();
            String labelname = getLabelName(key, form.getSrch_type());

            JSONObject colsObjectList = new JSONObject();
            colsObjectList.put("label", labelname);

            // total과 draftsaved 제외
            if (!key.equals("total") && !key.equals("draftsaved")) {
                // parameter type 분기처리
                String type = (i <= startnum) ? "string" : "number";
                colsObjectList.put("type", type);

                colsBuilder.append(colsObjectList.toString());
                if (i < listOrderMap.size() - 1) {
                    colsBuilder.append(",");
                }
            }
        }

        return colsBuilder.toString();
    }

    private String getLabelName(String key, String srchType) {
        if ("PC".equals(srchType)) {
            switch (key) {
                case "review1": return "Review in Progress(Review)";
                case "review2": return "Holding(Review)";
                case "review3": return "Admin Replied(Review)";
                case "review4": return "Replied(Review)";
                case "conclusion1": return "Approval in Progress(Conclusion)";
                case "conclusion2": return "Draft Saved(Conclusion)";
                case "conclusion3": return "Holding(Conclusion)";
                case "conclusion4": return "Cancel Approval(Conclusion)";
                case "registration1": return "Registration Signing(Registration)";
                case "registration2": return "Hard copy not Delivered(Registration)";
                case "execution1": return "Execution In Progress";
                case "termination1": return "Cancelled(Termination)";
                case "termination2": return "Expired(Termination)";
                case "termination3": return "Will be terminated(Termination)";
                case "termination4": return "Approval in Progress(Termination)";
                case "termination5": return "Extension Review(Termination)";
                case "adminreplied": return "Admin Replied(Termination)";
                case "ExecutedContract": return "Executed Contract";
                default: return key;
            }
        } else if ("PL".equals(srchType)) {
            switch (key) {
                case "col1": return "Not Assigned";
                case "col2": return "Review in Progress";
                case "col3": return "Replied";
                case "col4": return "Holding";
                case "col5": return "Returned";
                case "col6": return "Saved";
                default: return key;
            }
        }
        return key;
    }

    private String buildRows(HttpServletRequest request, ModelAndView listStatisticsMav, StatisticsForm form, int startnum) throws Exception {
        ArrayList<Object> listStatisticsArray = (ArrayList<Object>) listStatisticsMav.getModel().get("result_list");
        ListOrderedMap listOrderMap = (ListOrderedMap) listStatisticsArray.get(0);

//        String ChartType = getChartType(form);
        String ChartType = request.getParameter("ChartType") == null ? "Top10":request.getParameter("ChartType");

        JSONObject rowsObjectValue = new JSONObject();

        if ("Top10".equals(ChartType)) {
            return buildTop10Rows(listStatisticsArray, listOrderMap, form, startnum, rowsObjectValue);
        } else {
            return buildOtherRows(listStatisticsArray, listOrderMap, form, startnum, rowsObjectValue);
        }
    }

    private String buildTop10Rows(ArrayList<Object> listStatisticsArray, ListOrderedMap listOrderMap,
                                  StatisticsForm form, int startnum, JSONObject rowsObjectValue) throws Exception {
        StringBuilder rowsListBuilder = new StringBuilder();
        double[] otherstotal = new double[listOrderMap.size()];

        for (int i = 0; i < listStatisticsArray.size(); i++) {
            ListOrderedMap listStatisticsMap = (ListOrderedMap) listStatisticsArray.get(i);
            StringBuilder rowsBuilder = new StringBuilder();

            if (i < 10) {
                // Process first 10 items
                for (int j = startnum; j < listStatisticsMap.size(); j++) {
                    String key = listOrderMap.get(j).toString();
                    if (!key.equals("total") && !key.equals("draftsaved")) {
                        processRowValue(rowsObjectValue, listStatisticsMap, listOrderMap, j, key, startnum);
                        rowsBuilder.append(rowsObjectValue.toString());
                        if (j < listStatisticsMap.size() - 1) {
                            rowsBuilder.append(",");
                        }
                    }
                }
                rowsListBuilder.append("{\"c\":[").append(rowsBuilder).append("]}");
                if (i < listStatisticsArray.size() - 1) {
                    rowsListBuilder.append(",");
                }
            } else {
                // Accumulate values for "Others"
                for (int j = 0; j < listStatisticsMap.size(); j++) {
                    String key = listOrderMap.get(j).toString();
                    if (!key.equals("total") && !key.equals("draftsaved")) {
                        if (j > startnum) {
                            otherstotal[j] += Math.floor(Double.parseDouble(
                                    listStatisticsMap.get(listStatisticsMap.get(j)).toString().replace("-", "0")));
                        }
                    }
                }
            }
        }

        // Add "Others" row
        StringBuilder othersBuilder = new StringBuilder();
        for (int j = startnum; j < otherstotal.length; j++) {
            String key = listOrderMap.get(j).toString();
            if (!key.equals("total") && !key.equals("draftsaved")) {
                if (j <= startnum) {
                    rowsObjectValue.put("v", "Others");
                } else {
                    rowsObjectValue.put("v", otherstotal[j]);
                }
                othersBuilder.append(rowsObjectValue.toString());
                if (j < otherstotal.length - 1) {
                    othersBuilder.append(",");
                }
            }
        }
        rowsListBuilder.append(",{\"c\":[").append(othersBuilder).append("]}");

        return rowsListBuilder.toString();
    }

    private String buildOtherRows(ArrayList<Object> listStatisticsArray, ListOrderedMap listOrderMap,
                                  StatisticsForm form, int startnum, JSONObject rowsObjectValue) throws Exception {
        StringBuilder rowsListBuilder = new StringBuilder();

        for (int i = 0; i < listStatisticsArray.size(); i++) {
            ListOrderedMap listStatisticsMap = (ListOrderedMap) listStatisticsArray.get(i);
            StringBuilder rowsBuilder = new StringBuilder();

            if (i >= 10) {
                for (int j = startnum; j < listStatisticsMap.size(); j++) {
                    String key = listOrderMap.get(j).toString();
                    if (!key.equals("total") && !key.equals("draftsaved")) {
                        processRowValue(rowsObjectValue, listStatisticsMap, listOrderMap, j, key, startnum);
                        rowsBuilder.append(rowsObjectValue.toString());
                        if (j < listStatisticsMap.size() - 1) {
                            rowsBuilder.append(",");
                        }
                    }
                }
                rowsListBuilder.append("{\"c\":[").append(rowsBuilder).append("]}");
                if (i < listStatisticsArray.size() - 1) {
                    rowsListBuilder.append(",");
                }
            }
        }

        return rowsListBuilder.toString();
    }

    private void processRowValue(JSONObject rowsObjectValue, ListOrderedMap listStatisticsMap,
                                 ListOrderedMap listOrderMap, int j, String key, int startnum) throws JSONException {
        if (j <= startnum) {
            rowsObjectValue.put("v", listStatisticsMap.get(listOrderMap.get(j)));
        } else {
            rowsObjectValue.put("v", Math.floor(Double.parseDouble(
                    listStatisticsMap.get(listStatisticsMap.get(j)).toString().replace("-", "0"))));
        }
    }

    private void sendResponse(HttpServletResponse response, JSONObject listStatisticsObj) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print("{\"cols\":[" + listStatisticsObj.get("cols") + "],");
            out.print("\"rows\":[" + listStatisticsObj.get("rows") + "]}");
            out.flush();
            out.close();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void handleException(HttpServletResponse response, Exception e, HttpServletRequest request) throws IOException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jo);
        out.flush();
        out.close();
    }

    private void handleThrowable(HttpServletResponse response, Throwable t, HttpServletRequest request) throws IOException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jo);
        out.flush();
        out.close();
    }

    public ModelAndView listContracts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HttpSession session = request.getSession(false);

            StatisticsVO vo = new StatisticsVO();

            // 2. Set Default Values (Applied if request is empty)
            LocalDate today = LocalDate.now();
            LocalDate lastYear = today.minusYears(1).withDayOfYear(1);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            vo.setFromDate(lastYear.format(dtf));
            vo.setToDate(today.format(dtf));
            vo.setSrch_comp_cd("SESK"); // Default Company Code
            vo.setExcelDownload(false);

            // 1. Bind request parameters to VO
            bind(request, vo);

            // 2. Determine if this is an AJAX request for the table or a full page load
            boolean isAjax = "true".equals(request.getParameter("isAjax"));
            boolean isInitialLoad = (request.getParameter("srch_comp_cd") == null);

            List resultList = new ArrayList();
            if (!isInitialLoad) {
                resultList = statisticsService.listContracts(vo);
            }

            // 3. Handle Excel Download (Standard Request)
            if (vo.isExcelDownload()) {
                exportToCsv(response, resultList, vo.getQueryCode());
                return null;
            }

            // 4. Select the view:
            // If AJAX, return ONLY the table fragment.
            // If Initial, return the full page container.
            String viewName = isAjax ? "/WEB-INF/jsp/las/statistics/Contracts_l.jsp"
                    : "/WEB-INF/jsp/las/statistics/Contracts.jsp";

            ModelAndView mav = new ModelAndView(viewName);
            mav.addObject("resultList", resultList);
            mav.addObject("command", vo); // Still passed for initial defaults

            return mav;



            /*

            List resultList = null;

            // 1. Create a single Value Object (VO)
            StatisticsVO vo = new StatisticsVO();

            // 2. Set Default Values (Applied if request is empty)
            LocalDate today = LocalDate.now();
            LocalDate lastYear = today.minusYears(1).withDayOfYear(1);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            vo.setFromDate(lastYear.format(dtf));
            vo.setToDate(today.format(dtf));
            vo.setSrch_comp_cd("SESK"); // Default Company Code
            vo.setExcelDownload(false);


            // 3. THE BINDING (Single Source of Truth)
            // This captures JSP parameters (fromDate, toDate, excelDownload, srch_comp_cd)
            // and overwrites the defaults in the 'vo' object.
            bind(request, vo);

            // 4. DATA RETRIEVAL
            // The service calls the SQL XML which uses vo.isExcelDownload() for dynamic limits.

            boolean isInitialLoad = (request.getParameter("srch_comp_cd") == null);
            if (!isInitialLoad) {
                // Only execute query if it's NOT the initial load
                resultList = statisticsService.listContracts(vo);
            } else {
                // On initial load, we provide an empty list to the JSP
                resultList = new ArrayList();
            }

            // 4. RESPONSE HANDLING
            if (vo.isExcelDownload()) {
//				exportToCsv(response, resultList);
                exportToCsv(response, resultList, vo.getQueryCode());
                return null;
            } else {
                ModelAndView mav = new ModelAndView("/WEB-INF/jsp/las/statistics/Contracts.jsp");
                mav.addObject("resultList", resultList);
                mav.addObject("command", vo);
                return mav;
            }
			*/


        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error");
        }catch (Throwable t) {
            t.printStackTrace();
            throw new Exception("Error");
        }
    }

    /**
     * Streams data as CSV with dynamic headers based on the queryCode.
     * @param response
     * @param dataList
     * @param queryCode
     * @throws Exception
     */
    private void exportToCsv(HttpServletResponse response, List dataList, String queryCode) throws Exception {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"Contract_Statistics_" + queryCode + ".csv\"");

        // Write UTF-8 Byte Order Mark (BOM) for Excel compatibility
        response.getOutputStream().write(new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF });

        StringBuilder sb = new StringBuilder();

        if (dataList == null || dataList.isEmpty()) {
            response.getOutputStream().write("No data found".getBytes("UTF-8"));
            return;
        }

        // Define Headers and Keys based on queryCode
        String[] headers;
        String[] keys;

        if ("sesk".equals(queryCode)) {
            headers = new String[]{"Registration Year", "Registration Month", "Registration Date", "NERP Subsidiary", "SELMS+ Subsidiary", "Contract ID", "Request Title", "Contract Name", "Requesting dept.", "ESBO dept.", "Requester (In Charge)", "Request Date", "Area of contract", "Contract Type", "Contract Matter", "Contract start date", "Contract end date", "Contract amount", "Currency Uinit", "Counterparty", "Conclusion date", "Step", "Status", "Reviewer", "GERP_CUSTOMER_NM", "GERP_CONTRACT_REQUIRED", "GERP_VENDOR_TYPE", "GERP_CD", "GERP_DIVISION", "GERP_COST_TYPE", "Last Legal Opinion" };
            keys = new String[]{"Registration Year", "Registration Month", "Registration Date", "NERP Subsidiary", "SELMS+ Subsidiary", "Contract ID", "Request Title", "Contract Name","Requesting dept.", "ESBO dept.", "Requester (In Charge)", "Request Date", "Area of contract", "Contract Type", "Contract Matter", "Contract start date", "Contract end date", "Contract amount", "Currency Uinit", "Counterparty", "Conclusion date", "Step", "Status", "Reviewer", "GERP_CUSTOMER_NM", "GERP_CONTRACT_REQUIRED", "GERP_VENDOR_TYPE", "GERP_CD", "GERP_DIVISION", "GERP_COST_TYPE", "Last Legal Opinion"};
        } else if ("l1".equals(queryCode)) {
            headers = new String[]{"Registration Year", "Registration Month", "Registration Date", "NERP Subsidiary", "SELMS+ Subsidiary", "Contract ID", "Request Title", "Contract Name", "Requesting dept.", "ESBO dept.", "Requester (In Charge)", "Request Date", "Area of contract", "Contract Type", "Contract Matter", "Contract start date", "Contract end date", "Contract amount", "Currency Uinit", "Counterparty", "Conclusion date", "Step", "Status"};
            keys = new String[]{"Registration Year", "Registration Month", "Registration Date", "NERP Subsidiary", "SELMS+ Subsidiary", "Contract ID", "Request Title", "Contract Name","Requesting dept.", "ESBO dept.", "Requester (In Charge)", "Request Date", "Area of contract", "Contract Type", "Contract Matter", "Contract start date", "Contract end date", "Contract amount", "Currency Uinit", "Counterparty", "Conclusion date", "Step", "Status"};
        } else if ("l2".equals(queryCode)) {
            headers = new String[]{"Registration Year", "Registration Date", "NERP Subsidiary", "Request Title",
                    "Contract Name", "Requester", "Date of the first request", "Original Copy Receipt Date",
                    "In Charge", "Requesting Dept.", "Counterparty", "Counterparty Code", "Reviewer", "Contract ID",
                    "Step", "Status", "Last Modification Date", "Closed", "Requested Date for Reply", "First Replied Date",
                    "Final Replied Date", "Contract start date", "Contract end date", "Conclusion Date",
                    "DP Agreement", "Area of contract", "CCed"};
            keys = new String[]{"Registration Year", "Registration Date", "NERP Subsidiary", "Request Title",
                    "Contract Name", "Requester", "Date of the first request", "Original Copy Receipt Date",
                    "In Charge", "Requesting Dept.", "Counterparty", "Counterparty Code", "Reviewer", "Contract ID",
                    "Step", "Status", "Last Modification Date", "Closed", "Requested Date for Reply", "First Replied Date",
                    "Final Replied Date", "Contract start date", "Contract end date", "Conclusion Date",
                    "DP Agreement", "Area of contract", "CCed"};
        } else {
            // Default: "basic" layout
            headers = new String[]{
                    "Company Code", "Contract ID", "Contract Start Date", "Contract End Date", "Contract Status",
                    "Requester (In Charge)", "Requesting dept.", "Request Title", "Contract Name", "Request Date",
                    "Counterparty Code", "DP Agreement", "CCed"
            };
            keys = new String[]{
                    "Company Code", "Contract ID", "Contract Start Date", "Contract End Date", "Contract Status",
                    "Requester (In Charge)", "Requesting dept.", "Request Title", "Contract Name", "Request Date",
                    "Counterparty Code", "DP Agreement", "CCed"
            };
        }

        // 1. APPEND HEADER ROW
        for (int i = 0; i < headers.length; i++) {
            sb.append(headers[i]).append(i == headers.length - 1 ? "" : ",");
        }
        sb.append("\n");

        // 2. APPEND DATA ROWS
        for (Object obj : dataList) {
            Map row = (Map) obj;
            for (int i = 0; i < keys.length; i++) {
                sb.append(formatCsvValue(row.get(keys[i]))).append(i == keys.length - 1 ? "" : ",");
            }
            sb.append("\n");
        }

        response.getOutputStream().write(sb.toString().getBytes("UTF-8"));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    /**
     * Helper to handle null values and wrap strings in quotes if they contain commas.
     */
    private String formatCsvValue(Object value) {

        if (value == null) return "";

        String str = value.toString();

        // FIX: Remove all Carriage Returns and Newlines from HTML/Text content.
        // This is the most important step for content containing <br> or <div> tags
        // which often have hidden \r\n characters in the raw string.
        str = str.replace("\r\n", " ")
                .replace("\r", " ")
                .replace("\n", " ");

        // Standard CSV escaping
        if (str.contains(",") || str.contains("\"")) {
            str = "\"" + str.replace("\"", "\"\"") + "\"";
        }

        return str;
    }
    
}
	

