<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	List 	menuList 	 	=  (List)request.getAttribute("menuList");
	String	topMenuLevel 	=  (String)request.getAttribute("topMenuLevel");
	String	targetMenuId	=  (String)request.getAttribute("targetMenuId");
	int     menuSize        =  Integer.parseInt((String)request.getAttribute("menuSize"));  
	int 	menuIdCnt 		= 0;
	int 	menuMax 		= 1;
	
	//계약별 건수
	HashMap clmStatusCount = (HashMap)request.getAttribute("clmStatusCount");
	//공지사항 리스트
	List    clmBoardList	= (List)request.getAttribute("clmBoardList"); 
	//MyApproval 리스트
	List    clmsApprovalList	= (List)request.getAttribute("clmsApprovalList");
	//MyNotice 리스트
	List    clmMyNoticeList	= (List)request.getAttribute("clmMyNoticeList");
	
	String  lasLoginUrl			= (String)request.getAttribute("lasLoginUrl");
	String  cpmsLoginUrl			= (String)request.getAttribute("cpmsLoginUrl");
	String  itvocUrl 		= (String)request.getAttribute("itvocUrl");
	
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	
	
	String titleImage = "";
	String bannerMsg1 = ""; //배너 문구1
	String bannerMsg2 = ""; //배너 문구2
	String bannerMsg3 = ""; //배너 문구3
	boolean viewQnA = true;
	boolean banner2BoldFlag = false;
	boolean banner3BoldFlag = false;
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	
	if("B00000003".equals(blngt_orgnz)){ //메모리사업부
		titleImage = "system_logo1";		
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031)8000-0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000002".equals(blngt_orgnz)){ //무선사업부
		titleImage = "system_logo3";
		
		bannerMsg1 = langCd.equals("ko") ? "지원팀 법무지원그룹" : "Support Team Legal Support Group";
		bannerMsg2 = "hong978.kim@samsung.com (공지사항 매뉴얼참조) <br>jinsook0218.kim@samsung.com";	
	}else if("B00000001".equals(blngt_orgnz)){ //영상디스플레이사업부
		titleImage = "system_logo4";

		bannerMsg1 = langCd.equals("ko") ? "지원팀 법무지원그룹" : "Support Team Legal Support Group";
		bannerMsg2 = "031)277-1826/1529/8822";
		bannerMsg3 = "vdcontract@samsung.com";
	}else if("B00000014".equals(blngt_orgnz)){ //DS부문(Device Solution Division)
		titleImage = "system_logo5";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031) 8000 - 0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000015".equals(blngt_orgnz)){ //System LSI사업부
		titleImage = "system_logo6";
	
		bannerMsg1 = langCd.equals("ko") ? "기획팀 대외협력그룹" : "Planning Team Foreign Cooperation Group";
		bannerMsg2 = "031) 209 - 8385";
		bannerMsg3 = "lsi.contract@samsung.com";
	}else if("B00000016".equals(blngt_orgnz)){ //LCD사업부
		titleImage = "system_logo7";
	}else if("B00000007".equals(blngt_orgnz)){ //DMC부문(Degital Media & Communications Division)
		titleImage = "system_logo8";
		
		bannerMsg1 = langCd.equals("ko") ? "법무팀" : "Corporate Legal Team";
		bannerMsg2 = "02) 2255 - 8320";
		bannerMsg3 = "dmc.contract@samsung.com";
	}else if("B00000008".equals(blngt_orgnz)){ //CE담당(Comsumer Electronics Division)
		titleImage = "system_logo9";
	}else if("B00000009".equals(blngt_orgnz)){ //생활가전사업부
		titleImage = "system_logo10";
		
		bannerMsg1 = langCd.equals("ko") ? "인사팀 총무        031)200-7740" : "HR General Affairs        031)200-7740";
		bannerMsg2 = langCd.equals("ko") ? "광주지원팀총무그룹 062)950-6027" : "Gwangju Support Team and General Affairs Group 062)950-6027";
		bannerMsg3 = "";
		banner2BoldFlag = true;
	}else if("B00000010".equals(blngt_orgnz)){ //IM담당(IT & Mobile Comunications Division)
		titleImage = "system_logo11";
	}else if("B00000011".equals(blngt_orgnz)){ //B00000011
		titleImage = "system_logo12";
	
		bannerMsg1 = langCd.equals("ko") ? "PC개발팀 개발기획그룹" : "PC Development Team";
		bannerMsg2 = "031)277-6232(PC) / 5652(PRT)";
		bannerMsg3 = "it.contract@samsung.com";
	}else if("B00000012".equals(blngt_orgnz)){ //네트워크사업부
		titleImage = "system_logo13";
	
		bannerMsg1 = langCd.equals("ko") ? "지원팀 인사그룹" : "Support Team Personnel Group";
		bannerMsg2 = "031) 279 - 0502";
		bannerMsg3 = "nw.contract@samsung.com";
	}else if("B00000013".equals(blngt_orgnz)){ //디지털이미징사업부
		titleImage = "system_logo14";
	
		bannerMsg1 = langCd.equals("ko") ? "개발   :개발팀  031)277-3920" : "Development: Development Team  031)277-3920";
		bannerMsg2 = langCd.equals("ko") ? "개발 外 :지원그룹031)277-3477" : "Excluding Development: Support Group031)277-3477";
		bannerMsg3 = "img.contract@samsung.com";
		banner2BoldFlag = true;
	}else if("B00000017".equals(blngt_orgnz)){ //Media Solution Center 
		titleImage = "system_logo15";
		
		bannerMsg1 = langCd.equals("ko") ? "지원그룹" : "Support Group";
		bannerMsg2 = "010-8632-0129";
		bannerMsg3 = "msc.con@samsung.com";
	}else if("B00000018".equals(blngt_orgnz)){ //의료기기사업팀
		titleImage = "system_logo18";
	
		bannerMsg1 = langCd.equals("ko") ? "지원팀" : "Support Team";
		bannerMsg2 = "031) 200 - 1788";
		bannerMsg3 = "hme.contract@samsung.com";
	}else if("B00000019".equals(blngt_orgnz)){ //한국총괄
		titleImage = "system_logo19";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원팀 법무지원그룹 (김희정 과장) " : "EN_경영지원팀 법무지원그룹 (김희정 과장) ";
		bannerMsg2 = "02) 2255 - 6943";
		bannerMsg3 = "kr.contract@samsung.com";
	}else if("B00000020".equals(blngt_orgnz)){ //소프트웨어센터
		titleImage = "system_logo20";
	
		bannerMsg1 = langCd.equals("ko") ? "DMC연구소 기술전략팀" : "DMC R&D Center Technology Strategy Team";
		bannerMsg2 = "031) 279 - 4517";
		bannerMsg3 = "dmcrnd.con@samsung.com";
	}else if("B00000021".equals(blngt_orgnz)){ //DMC연구소
		titleImage = "system_logo21";
	
		bannerMsg1 = langCd.equals("ko") ? "DMC연구소 기술전략팀" : "DMC R&D Center Technology Strategy Team";
		bannerMsg2 = "031) 279 - 5103";
		bannerMsg3 = "dmcrnd.con@samsung.com";
	}else if("B00000022".equals(blngt_orgnz)){ //글로벌마케팅실
		titleImage = "system_logo22";
		
		bannerMsg1 = langCd.equals("ko") ? "마케팅전략팀 마케팅기획그룹" : "Marketing Strategy Team Marketing Planning Group";
		bannerMsg2 = "031) 277 - 7929";
		bannerMsg3 = "gmo.contract@samsung.com";
	}else if("B00000023".equals(blngt_orgnz)){ //디자인경영센터
		titleImage = "system_logo23";
	
		bannerMsg1 = langCd.equals("ko") ? "디자인전략팀 지원그룹" : "Design Strategy Team Support Group";
		bannerMsg2 = "02) 2255 - 6215";
		bannerMsg3 = "design.con@samsung.com";
	}else if("B00000024".equals(blngt_orgnz)){ //CS환경센터
		titleImage = "system_logo24";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원그룹" : "Management Support Group";
		bannerMsg2 = "031) 200 - 2136";
		bannerMsg3 = "cs.contract@samsung.com";
	}else if("B00000025".equals(blngt_orgnz)){ //제조기술센터
		titleImage = "system_logo25";
	
		bannerMsg1 = langCd.equals("ko") ? "요소기술팀" : "Element Technology Team";
		bannerMsg2 = "031) 200 - 2876 / 1827";
		bannerMsg3 = "pt.contract@samsung.com";
	}else if("B00000026".equals(blngt_orgnz)){ //글로벌B2B센터
		titleImage = "system_logo26";
	
		bannerMsg1 = langCd.equals("ko") ? "B2B인프라그룹 02)2255-7151" : "B2B Infra Group 02)2255-7151";
		bannerMsg2 = langCd.equals("ko") ? "B2B운영그룹 02)2255-7167" : "B2B Operation Group 02)2255-7167";
		bannerMsg3 = "b2b.contract@samsung.com";
		banner2BoldFlag = true;
	}else if("B00000027".equals(blngt_orgnz)){ //환경안전센터
		titleImage = "system_logo27";
	
		bannerMsg1 = langCd.equals("ko") ? "Global ESH기획그룹" : "Global Environment Safety & Health Planning Group";
		bannerMsg2 = "031) 200 - 1735";
		bannerMsg3 = "es.contract@samsung.com";
	}else if("B00000028".equals(blngt_orgnz)){ //수원지원센터
		titleImage = "system_logo28";
	
		bannerMsg1 = langCd.equals("ko") ? "재무지원팀 계약지원" : "Financial Support Team Contract Support";
		bannerMsg2 = "031) 277 - 0292";
		bannerMsg3 = "suwon.con@samsung.com";
	}else if("B00000029".equals(blngt_orgnz)){ //구미지원센터
		titleImage = "system_logo29";
	
		bannerMsg1 = langCd.equals("ko") ? "인사팀 총무그룹" : "HR General Affairs Group";
		bannerMsg2 = "054) 479 - 5044";
		bannerMsg3 = "gumi.con@samsung.com";
	}else if("B00000030".equals(blngt_orgnz)){ //인재개발센터
		titleImage = "system_logo30";
	
		bannerMsg1 = langCd.equals("ko") ? "인재개발그룹   031)200-5914" : "HR Development Group   031)200-5914";
		bannerMsg2 = langCd.equals("ko") ? "SADI 02)3438-0306 / 첨단기술연수소 031)210-2290" : "SADI 02)3438-0306 / Samsung Advanced Technology Training Institute 031)210-2290";
		bannerMsg3 = langCd.equals("ko") ? "첨단기술연수소 031)210-2290" : "Samsung Advanced Technology Training Institute 031)210-2290";
		banner2BoldFlag = true;
		//banner3BoldFlag = true;
	}else if("B00000033".equals(blngt_orgnz)){ //반도체연구소
		titleImage = "system_logo33";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031) 8000 - 0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000034".equals(blngt_orgnz)){ //Test&Package센터
		titleImage = "system_logo34";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031) 8000 - 0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000035".equals(blngt_orgnz)){ //Infra기술센터
		titleImage = "system_logo35";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031) 8000 - 0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000036".equals(blngt_orgnz)){ //생산기술연구소
		titleImage = "system_logo36";
	
		bannerMsg1 = langCd.equals("ko") ? "기획지원팀 기술기획그룹/구매그룹" : "Planning Support Team Technology Planning Group/Procurement Group";
		bannerMsg2 = "031) 200 - 2610/2306";
		bannerMsg3 = "mt.contract@samsung.com";
	}else if("B00000040".equals(blngt_orgnz)){ //LED사업부
		titleImage = "system_logo40";
	
		bannerMsg1 = langCd.equals("ko") ? "경영지원실 법무지원팀(DS)" : "DS Management Office Legal Support Team";
		bannerMsg2 = "031) 8000 - 0246";
		bannerMsg3 = "dscontract.sec@samsung.com";
	}else if("B00000041".equals(blngt_orgnz)){ //종합기술원
		titleImage = "system_logo41";
	
		bannerMsg1 = langCd.equals("ko") ? "CTO전략팀 IP출원그룹" : "CTO Strategy Team IP Application Group";
		bannerMsg2 = "031) 280 - 9145 / 9147";
		bannerMsg3 = "sait.con@samsung.com";
	}else if("B00000042".equals(blngt_orgnz)){ //상생협력센터
		titleImage = "system_logo42";
	
		bannerMsg1 = langCd.equals("ko") ? "구매전략팀   031)277-0369" : "Strategic Procurement Group   031)277-0369";
		bannerMsg2 = langCd.equals("ko") ? "시너지구매팀 031)277-1063 / 상생협력팀   031)277-0473" : "Synergy Procurement Group 031)277-1063 / Partner Collaboration Team   031)277-0473";
	//	bannerMsg3 = "상생협력팀   031)277-0473";
		banner2BoldFlag = true;
		//banner3BoldFlag = true;
	}else if("B00000043".equals(blngt_orgnz)){ //서초지원센터
		titleImage = "system_logo43";
	
		bannerMsg1 = langCd.equals("ko") ? "총무그룹" : "General Affairs Group";
		bannerMsg2 = "02) 2255 - 7907";
		bannerMsg3 = "seocho.con@samsung.com";
	}else if("B00000044".equals(blngt_orgnz)){ //정보보호센터
		titleImage = "system_logo44";
	
		bannerMsg1 = langCd.equals("ko") ? "법무팀" : "Corporate Legal Team";
		bannerMsg2 = "02) 2255 - 8320";
		bannerMsg3 = "infosecu.con@samsung.com";
	}else if("B00000045".equals(blngt_orgnz)){ //경영지원실
		titleImage = "system_logo45";
	
		bannerMsg1 = langCd.equals("ko") ? "법무팀" : "Corporate Legal Team";
		bannerMsg2 = "02) 2255 - 8320";
		bannerMsg3 = "dmc.contract@samsung.com";
	}else if("B00000046".equals(blngt_orgnz)){ //전자스포츠구단
		titleImage = "system_logo46";
	
		bannerMsg1 = langCd.equals("ko") ? "농구단 031) 260 - 7634" : "Basketball Team 031) 260 - 7634";
		bannerMsg2 = langCd.equals("ko") ? "육상단 031) 209 - 5011" : "Athletics Team 031) 209 - 5011";
		bannerMsg3 = "sports.con@samsung.com";
		banner2BoldFlag = true;
	}else if("B00000047".equals(blngt_orgnz)){ //삼성사회공헌위원회
		titleImage = "system_logo47";
	
		bannerMsg1 = langCd.equals("ko") ? "삼성사회봉사단" : "Samsung Volunteer Corps";
		bannerMsg2 = "02) 3458 - 7855";
		bannerMsg3 = "scc.contract@samsung.com";
	}else if("B00000048".equals(blngt_orgnz)){ //창의개발센터
		titleImage = "system_logo48";
	
		bannerMsg1 = langCd.equals("ko") ? "법무팀" : "Corporate Legal Team";
		bannerMsg2 = "02) 2255 - 8320";

	}else if("A00000003".equals(blngt_orgnz)){ //IP센터
		titleImage = "system_logo_ip";
	
		bannerMsg1 = langCd.equals("ko") ? "IP전략팀 계약그룹" : "IP Strategy Team IP Transactions Group";
		bannerMsg2 = "031) 279 - 7589 / 5354";
		bannerMsg3 = "ip.contract@samsung.com";
	}else{ //기본
		titleImage = "system_logo";
		//viewQnA = false;
		
		bannerMsg1 = langCd.equals("ko") ? "법무팀" : "Corporate Legal Team";
		bannerMsg2 = "02) 2255 - 8320";
		bannerMsg3 = "dmc.contract@samsung.com";
	}
	
	//my approval view 여부
	boolean personalFlag = personalFlag(session);
	//계약서담당자변경
	boolean chgPersonflag = chgPersonflag(session);
	
	ClmsDataUtil.debug("############## :" + localeCode);
%>	

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet">

<!-- js  -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>
<!--  통합검색관련 추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<!-- 통합검색관련 추가끝 -->
<script language="javascript">

/**
 * 전체공지사항 상세
 */
function getBoardDetail(seqno) {
	var frm = document.frm;

    PopUpWindowOpen('', 800, 575, true);
	frm.target = "PopUpWindow";
	
	frm.action = "/clm/admin/board.do?seqno="+seqno;
	frm.method.value = "detailBoardByMain";
	frm.menu_id.value="20110803092210347_0000000062";	

	frm.submit();
}

/**
 * My Notice 상세
 */
function getMyNoticeDetail(mis_id, module_id){
	
	var frm = document.frm;
	
	PopUpWindowOpen('', 1100, 700, true);
	frm.target = "PopUpWindow";
	
	frm.action = "/clm/manage/myNotice.do?mis_id="+mis_id+"&module_id="+module_id;
	frm.method.value = "detailMyNoticeByMain";
	frm.menu_id.value="20110803091536762_0000000047";
	
	frm.submit();	
}

//통합검색관련 스크립트 시작

$(document).ready(function(){
	initCal("srch_start_dt");	
	initCal("srch_end_dt");
	getCodeSelectByUpCd("searchfrm", "biz_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getBiz_clsfcn&flag=C');
	getCodeSelectByUpCd("searchfrm", "depth_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getDepth_clsfcn&flag=C');
	getCodeSelectByUpCd("searchfrm", "cnclsnpurps_bigclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_bigclsfcn&flag=C');
	getCodeSelectByUpCd("searchfrm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=C');
});

$(function() {
	$("#cnclsnpurps_bigclsfcn").change(function() {
		var upCd = $("#cnclsnpurps_bigclsfcn").val();
		getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=C&upCd=' + upCd);
	});

});

/**
* 버튼 동작 부분
*/ 
function pageAction(){
	
	var frm = document.searchfrm;
	frm.target = "_self";
	frm.action = "<c:url value='/clm/search/search.do' />";
	frm.flag.value = "C"; //Url통합관련
	
	var flag = "";
	if(frm.dbsrh[0].disabled == false) {
		flag = "searchdtl";
	} else {
		flag = "search";
	}
	
	if(flag == "search"){
		frm.queryText.value = frm.query.value;
		tmpquery = frm.query.value;
		if(frm.queryText.value == "") {
			alert("<spring:message code="clm.msg.alert.srch.noQuery" />");
			return;
		} else if(tmpquery.length > 100) {
			alert("<spring:message code="clm.msg.alert.srch.valQuery" />");
			return;
		}
	    frm.method.value = "listSearch";		    
		frm.curPage.value = "1";
// 2011.12.21 clm_share_customer 제거		
//		frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
		frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_master_orglastransfer,clm_master_divisiontransfer,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
		frm.queryField.value = "";
		frm.submit();
		viewHiddenProgress(true) ;
	} else if(flag == "searchdtl"){
		frm.queryText.value = frm.query.value;
		tmpquery = frm.query.value;
		if(frm.queryText.value == "") {
			alert("<spring:message code="clm.msg.alert.srch.noQuery" />");
			return;
		} else if(tmpquery.length > 100) {
			alert("<spring:message code="clm.msg.alert.srch.valQuery" />");
			return;
		}
	    frm.method.value = "listSearch";		    
		frm.curPage.value = "1";
		frm.srch_index_db.value = "";
		frm.queryField.value = "";
		frm.fieldText.value = "";
		frm.srchMinDate.value = "";
		frm.srchMaxDate.value = "";
		
		var checkdbnum = 0;
		for(i = 0; i < frm.dbsrh.length; i++)
		{
			if(frm.dbsrh[i].checked == true)
			{
				if(frm.srch_index_db.value == "") {
					frm.srch_index_db.value = frm.dbsrh[i].value;
				} else {
					frm.srch_index_db.value = frm.srch_index_db.value + "," + frm.dbsrh[i].value;
				}
				checkdbnum++;
			}	
		}
		
		if(checkdbnum < 1 ) {
			alert("<spring:message code="clm.msg.alert.srch.noCate" />");
			return;
		}
		
		if(frm.dbsrh[0].checked != true & frm.dbsrh[5].checked != true) {
			if(checkdbnum == 1) frm.method.value = "listDetailSearch";
		}
		
		if(frm.srch_index_db.value == "") {
//			frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
			frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_master_orglastransfer,clm_master_divisiontransfer,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
		}
		
		var checknum = 0;
		for(i = 0; i < frm.zsrh.length; i++)
		{
			if(frm.zsrh[i].checked == true)
			{
				if(frm.queryField.value == "") {
					frm.queryField.value = frm.zsrh[i].value;
				} else {
					frm.queryField.value = frm.queryField.value + ":" + frm.zsrh[i].value;
				}
				checknum++;
			}
		}
		
		if(checknum == 2) frm.queryField.value = "";
		
		if(frm.finfo.checked == true) {
			var fieldText = "";
			if(frm.region_gbn.options[frm.region_gbn.selectedIndex].value != "") {
				fieldText = "MATCH{"+frm.region_gbn.options[frm.region_gbn.selectedIndex].value+"}:MF_REGION_GBN";
			}
			if(frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value + "}:MF_BIZ_CLSFCN";
				} else {
					fieldText = "MATCH{" + frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value + "}:MF_BIZ_CLSFCN";
				}
			}
			if(frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value + "}:MF_DEPTH_CLSFCN";
				} else {
					fieldText = "MATCH{" + frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value + "}:MF_DEPTH_CLSFCN";
				}
			}
			if(frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_BIGCLSFCN";
				} else {
					fieldText = "MATCH{" + frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_BIGCLSFCN";
				}
			}
			if(frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_MIDCLSFCN";
				} else {
					fieldText = "MATCH{" + frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_MIDCLSFCN";
				}
			}
			if(frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value + "}:MF_PAYMENT_GBN";
				} else {
					fieldText = "MATCH{" + frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value + "}:MF_PAYMENT_GBN";
				}
			}
			if(frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value + "}:MF_CNTRT_CNCLSN_YN";
				} else {
					fieldText = "MATCH{" + frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value + "}:MF_CNTRT_CNCLSN_YN";
				}
			}
			if(frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value != "") {
				if(fieldText != "") {
					fieldText = fieldText + " AND MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_CNTRT_STATUS";
				} else {
					fieldText = "MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_CNTRT_STATUS";
				}
			}
			if(fieldText != "") frm.fieldText.value = fieldText;
			//날짜체크
			if(frm.srch_start_dt.value > frm.srch_end_dt.value & frm.srch_start_dt.value != "" & frm.srch_end_dt.value != "") {
				alert("<spring:message code="clm.msg.alert.srch.valDate" />");
				frm.srch_end_dt.value = "";
				return;
			}
			if(frm.srch_start_dt.value != "") frm.srchMinDate.value = frm.srch_start_dt.value;
			if(frm.srch_end_dt.value != "") frm.srchMaxDate.value = frm.srch_end_dt.value;
			
		}
		
		frm.submit();
		viewHiddenProgress(true) ;
	}
}

/**
* 상세검색 보여주는 부분관련
*/
//해당 layer에 diaplay를 위치값에 따라 나타내고 없애준다.
function displayLayer(_target, _elm, _x, _y)
{
	target = document.getElementById(_target);
	var frm = document.searchfrm;
	if(!_x)
		_x = 0;
	if(!_y)
		_y = 0;
		
	if(target)
	{
		if(target.style.visibility == "visible")
		{
			target.style.visibility = "hidden";
			if(frm.dbsrh[0].disabled == false) frm.dbsrh[0].checked = false;
		}
		else
		{
			x = parseInt(getElementX(_elm));
			y = parseInt(getElementY(_elm));
			target.style.position = "absolute";
			target.style.left = (x + _x) + "px";
			target.style.top = (y + _y) + _elm.offsetHeight + "px";
			target.style.zIndex = '1000';
			target.style.display = 'block';
			target.style.visibility = "visible";
			_elm.focus();
		}
	}
}

//object의 x position을 가져온다.//
function getElementX(_elm)
{
	xPos = _elm.offsetLeft;
	tempEl = _elm.offsetParent;
	while (tempEl != null)
	{
		xPos += (tempEl.offsetLeft);
		tempEl = tempEl.offsetParent;
	}
	return xPos;
}
//object의 Y position을 가져온다.
function getElementY(_elm)
{
	yPos = _elm.offsetTop;
	tempEl = _elm.offsetParent;
	while (tempEl != null)
	{
		yPos += (tempEl.offsetTop);
		tempEl = tempEl.offsetParent;
	}
	return yPos;
}

/**
* 계약서 상세선택항목 활성화
*/
function actsrh(_elm){		
	var frm = document.searchfrm;
	if(frm.dbsrh[0].checked == true) {
		displayLayer('detailSearch', _elm, -90, -375);
		frm.zsrh[0].checked = true;
		frm.zsrh[1].checked = true;
		frm.zsrh[0].disabled = false;
		frm.zsrh[1].disabled = false;
		frm.finfo.checked = false;
		frm.finfo.disabled = false;
		
		actsrhinfo();
		
	} else {
		displayLayer('detailSearch', _elm, -90, -375);
		frm.zsrh[0].checked = false;
		frm.zsrh[1].checked = false;
		frm.zsrh[0].disabled = true;
		frm.zsrh[1].disabled = true;
		frm.finfo.checked = false;
		frm.finfo.disabled = true;
		
		actsrhinfo();
	}
}

/**
* 계약서 정보 세부선택항목 활성화
*/
function actsrhinfo(){		
	var frm = document.searchfrm;
	if(frm.finfo.checked == true) {
		frm.region_gbn.disabled = false;
		frm.biz_clsfcn.disabled = false;
		frm.depth_clsfcn.disabled = false;
		frm.cnclsnpurps_bigclsfcn.disabled = false;
		frm.cnclsnpurps_midclsfcn.disabled = false;
		frm.payment_gbn.disabled = false;
		frm.cntrt_cnclsn_yn.disabled = false;
		frm.cntrt_status.disabled = false;
		frm.srch_start_dt.disabled = false;
		frm.srch_end_dt.disabled = false;			
	} else {
		frm.region_gbn.disabled = true;
		frm.biz_clsfcn.disabled = true;
		frm.depth_clsfcn.disabled = true;
		frm.cnclsnpurps_bigclsfcn.disabled = true;
		frm.cnclsnpurps_midclsfcn.disabled = true;
		frm.payment_gbn.disabled = true;
		frm.cntrt_cnclsn_yn.disabled = true;
		frm.cntrt_status.disabled = true;
		frm.srch_start_dt.disabled = true;
		frm.srch_end_dt.disabled = true;
	}
	frm.region_gbn.options[0].selected = true;
	frm.biz_clsfcn.options[0].selected = true;
	frm.depth_clsfcn.options[0].selected = true;
	frm.cnclsnpurps_bigclsfcn.options[0].selected = true;
	frm.cnclsnpurps_midclsfcn.options[0].selected = true;
	frm.payment_gbn.options[0].selected = true;
	frm.cntrt_cnclsn_yn.options[0].selected = true;
	frm.cntrt_status.options[0].selected = true;
	frm.srch_start_dt.value= "";
	frm.srch_end_dt.value= "";
}

/**
* 카테고리 영역활성화
*/
function actdetailSearch(){		
	var frm = document.searchfrm;
	if(frm.dbsrh[0].disabled == true) {
		frm.dbsrh[0].checked = false;
		frm.dbsrh[1].checked = false;
		frm.dbsrh[2].checked = false;
		frm.dbsrh[3].checked = false;
		frm.dbsrh[4].checked = false;
		frm.dbsrh[5].checked = false;
		frm.dbsrh[0].disabled = false;
		frm.dbsrh[1].disabled = false;
		frm.dbsrh[2].disabled = false;
		frm.dbsrh[3].disabled = false;
		frm.dbsrh[4].disabled = false;
		frm.dbsrh[5].disabled = false;
	} else {
		frm.dbsrh[0].checked = true;
		frm.dbsrh[1].checked = true;
		frm.dbsrh[2].checked = true;
		frm.dbsrh[3].checked = true;
		frm.dbsrh[4].checked = true;
		frm.dbsrh[5].checked = true;
		frm.dbsrh[0].disabled = true;
		frm.dbsrh[1].disabled = true;
		frm.dbsrh[2].disabled = true;
		frm.dbsrh[3].disabled = true;
		frm.dbsrh[4].disabled = true;
		frm.dbsrh[5].disabled = true;
		if(document.getElementById('detailSearch').style.visibility == "visible") {
			displayLayer('detailSearch', '', -90, -375);
		}
	}
}

//통합검색관련 스크립트 끝


//연계관련 공지띄우기
function openPop(){
	var noticeCookie = getCookie("noticePopupClm");
	//if(noticeCookie != "no"){
	PopUpWindowOpen2('/las/openPopVD.do', '340', '390', false, 'PopUpRelationNoticeClm');
	//}
}

function divShow(obj) {
	obj.style.display = 'block';
}
function divHidden(obj) {
	obj.style.display = 'none';
}

//공무집행대응 변호사 팝업
function openContactUsPop(){
	PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsClm');		
}
//업무분야별 전문 변호사 팝업
function openExpertPop(){
	PopUpWindowOpen2('/las/openPopMSC.do', '840', '600', false, 'PopUpExpertClm');
}
</script>
</head>
<body>

<form name="frm" id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<!-- targetMenuId -->
	<input type="hidden" name="targetMenuId">
	<input type="hidden" name="target_menu_id">
	
	<!-- selected_menu_id -->
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_nm">
	<input type="hidden" name="selected_menu_detail_id">
	<!--상세내역에 띄워줄 URL 정보.-->
	<input type="hidden" name="set_init_url">
	
	<input type="hidden" name="menuLogEnable" value="Y">
	<input type="hidden" name="chgLangflag">
	
	<input type="hidden" name="curPage">		
	
	<!-- <input type="hidden" name="seqno"> -->
	<input type="hidden" name="menu_id" value="" />
	
	<input type="hidden" name="flag" value=""/>
</form>

<div id="topWrap">

	<!-- header -->
	<div id="header">
		<div id="logo"> 
			<a href="javascript:Menu.main('frm','C');" class="h1_logo"><IMG SRC="<%=IMAGE%>/common/<%=titleImage%>.gif"  BORDER="0" title="<spring:message code="clm.page.field.logo.title" />"  alt="<spring:message code="clm.page.field.logo.title" />" ></a><!-- 계약관리 시스템 -->
			<span style='margin-left:7px'><img src="<%=IMAGE%>/common/pncacc.gif"  border="0" alt="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION"  title="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION" /></span>
			<!-- utility -->
			<ul class="utility">
				<li><a href="#" class="user_request"><img src="<%=IMAGE%>/icon/ico_user.gif" alt="user" title="user"/></a> 
				<%if(localeCode.equals("ko")){ %>
					<a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "")%></a> </li>
				<%}else{ %>
					<a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm_en"), "")%></a> </li>	
				<%} %>	
				<li><a href="javascript:Menu.main('frm','C');"><img src="<%=IMAGE%>/icon/icon_home.gif" alt="Home" title="Home"/></a> </li>
				<li><a href="javascript:Menu.goSiteMap('frm','C');"><img src="<%=IMAGE%>/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a></li>   
				<%--<li><a href="<%=itvocUrl%>" target="_blank">IT VOC</a></li>--%>
				<!-- <li class="bg_none"><a href="javascript:Menu.logout('frm','C');">Logout</a></li> -->
				<%-- <li class="confidential"><img src="<%=IMAGE%>/icon/ico_confidential2.gif" alt="confidential"/></li>  --%>
				<li style='margin-top:-2px;'>
				<%if(localeCode.equals("ko")){ %>
					<a href=# onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(<%=IMAGE%>/btn/arr_dot_bg.gif) no-repeat right 1px;'>
					<img id="lang_ko" src="<%=IMAGE%>/btn/btn_on_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href=# onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_en','','<%=IMAGE%>/btn/btn_on_l_eng.gif',1)"><img id="lang_en" src="<%=IMAGE%>/btn/btn_off_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
			
				<%}else{ %>
					<a href=# onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(<%=IMAGE%>/btn/arr_dot_bg.gif) no-repeat right 1px;' onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_ko','','<%=IMAGE%>/btn/btn_on_l_kor.gif',1)">
					<img id="lang_ko" src="<%=IMAGE%>/btn/btn_off_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href=# onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');"><img id="lang_en" src="<%=IMAGE%>/btn/btn_on_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
				<%} %>	
				</li>
				
				
				<li style='margin-top:-4px; margin-left:5px;'  onMouseOver='divShow(contactus)' onMouseOut='divHidden(contactus)'>
                	<a href="javascript:openContactUsPop();" style='background:none;'><img src="<%=IMAGE%>/new2011/contactus_btn.gif" alt="<spring:message code="clm.page.field.menu.execLawyer" />" title="<spring:message code="clm.page.field.menu.execLawyer" />" /></a><!--공무집행  대응 변호사-->
                </li>
			</ul>
			<!-- //utility -->
		</div>
		<div id='contactus' style='display:none;position:absolute; top:10px; right:137px; z-index:1'>
			<img src="<%=IMAGE%>/new2011/contactus_tip.png" alt='<spring:message code="clm.page.field.menu.execOfficial" />' /><!--공정거래위원회 조사 등 공무집행에 대응할 사업부별 담당 변호사입니다.-->
		</div>
		<hr/>

		<!-- gnb -->
        <div class="gnb">
			<ul>
<%				String multi_menu_nm = ""; String multi_submenu_nm = "";
				for(int idx=0;idx < menuList.size();idx++ ){	
				    ListOrderedMap menu = null;
				    menu = (ListOrderedMap)menuList.get(idx);
				    if(localeCode.equals("en")){ multi_menu_nm = (String) menu.get("menu_nm_eng");}
				    else{ multi_menu_nm = (String) menu.get("menu_nm"); }
				    
				    if(((Integer)menu.get("menu_level")).intValue()==1){
%>
				<li id="menu_<%=menuMax%>" onMouseOver="javascript:subMenuSet(<%=menuMax%>)">
					<a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=menu.get("menu_id")%>', '_self');">
					<%=multi_menu_nm%>
					</a>
					<ul id="gnb_2depth_0<%=menuMax%>" class="gnb_2depth_0<%=menuMax%> gnb_2depth">		
					
<%
						int subMenuIdx = 1;

						for(int idx2=0;idx2 < menuList.size(); idx2++){
							ListOrderedMap subMenu = null;
							subMenu = (ListOrderedMap)menuList.get(idx2);
							if(localeCode.equals("en")){ multi_submenu_nm = (String) subMenu.get("menu_nm_eng");}
   						    else{ multi_submenu_nm = (String) subMenu.get("menu_nm"); }
   						    
							if(Integer.parseInt((String)subMenu.get("menu_level"))==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
								
								//계약서담당자 변경 view여부
								if("20110803091537677_0000000066".equals(subMenu.get("menu_id")) ){
									if(chgPersonflag){
								
								if(idx2 == menuList.size()){
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>" class="last-menu"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
<%									
								}else{
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
<%
								}
								subMenuIdx++;
									}
								}else{
			
								if(idx2 == menuList.size()){
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>" class="last-menu"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
<%									
								}else{
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
<%
								}
								subMenuIdx++;																		
								
								}														
							}			
						}
%>														
					</ul>								
				</li>
<%				    	 
						menuMax++;	 
				    }
				}
%>			
			</ul>
			 <span class="collapse"><img src="<%=IMAGE%>/icon/ico_collapse.png" alt="collapse" title="collapse"  class="png24 cp" onclick="menu_Toggle(this, 'clm', 'logo', '');" /></span>
		</div>
        <!-- //gnb -->

		<!-- lnb -->
		<!-- //lnb -->

	</div>
	<!-- //header -->

	<!-- container -->
	<div id="container">

		<!-- content -->
		<div id="main_content">

			<!-- main img -->
			<div class="main_img_box">
				<div class="main_img1"><img src="<%=IMAGE%>/common/main_img2.jpg" alt="<spring:message code='clm.page.msg.main.clms' htmlEscape='true' />" title="<spring:message code='clm.page.msg.main.clms' htmlEscape='true' />" /></div>
			</div>
			<!-- //main img -->

			<div class="twocolum_box">
				<div class="twocolum fL">
					<div class="bbs">

						<!-- work -->
						<div id="work">
							<ul>
								<li><img src="<%=IMAGE%>/common/work_tab_on.gif" /></li>
<%
	if(!personalFlag){
%>								
								<li><a href="#" onClick="work.style.display='none';approval.style.display='block';"><img src="<%=IMAGE%>/common/approval_tab_off.gif"/></a></li>
<%
	}
%>					
								<%
									int cnt = 0;
									cnt = Integer.parseInt((String)clmStatusCount.get("C02501")) + Integer.parseInt((String)clmStatusCount.get("C02502")) + Integer.parseInt((String)clmStatusCount.get("C02503")) + Integer.parseInt((String)clmStatusCount.get("C02504")) + Integer.parseInt((String)clmStatusCount.get("C02505"));
								%>
								<li class="more2">
									<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091536533_0000000045', '_self');">Total : <strong><%=StringUtil.commaIn(String.valueOf(cnt))%></strong></a>
								</li>
							</ul>
							<div class="work">
								<ul class="step-list">
									<li class="step-list-bg01">
										<strong>
											<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091536879_0000000048', '_self');" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('step01','','<%=IMAGE%>/new2011/txt_main_step01_on.gif',1)">
												<img src="<%=IMAGE%>/new2011/txt_main_step01_off.gif" name="step01" border="0" id="step01" />
											</a>
										</strong>
										<div>
											<em><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091536879_0000000048', '_self');"><%=clmStatusCount.get("C02501")%> <spring:message code="clm.page.msg.common.case" /></a></em>
										</div>
									</li>
									<li class="step-list-bg02">
										<strong>
											<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537108_0000000050', '_self');" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('step02','','<%=IMAGE%>/new2011/txt_main_step02_on.gif',1)">
												<img src="<%=IMAGE%>/new2011/txt_main_step02_off.gif" name="step02" border="0" id="step02" />
											</a>
										</strong>
										<span class="left_bg"></span>
										<div>
											<em><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537108_0000000050', '_self');"><%=clmStatusCount.get("C02502")%> <spring:message code="clm.page.msg.common.case" /></a></em>
										</div>
									</li>
									<li class="step-list-bg03">
										<strong>
											<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537216_0000000051', '_self');" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('step03','','<%=IMAGE%>/new2011/txt_main_step03_on.gif',1)">
												<img src="<%=IMAGE%>/new2011/txt_main_step03_off.gif" name="step03" border="0" id="step03" />
											</a>
										</strong>
										<span class="left_bg"></span>
										<div>
											<em><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537216_0000000051', '_self');"><%=clmStatusCount.get("C02503")%> <spring:message code="clm.page.msg.common.case" /></a></em>
										</div>
									</li>
									<li class="step-list-bg04">
										<strong>
											<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537331_0000000052', '_self');" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('step04','','<%=IMAGE%>/new2011/txt_main_step04_on.gif',1)">
												<img src="<%=IMAGE%>/new2011/txt_main_step04_off.gif" name="step04" border="0" id="step04" />
											</a>
										</strong>
										<span class="left_bg"></span>
										<div>
											<em><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537331_0000000052', '_self');"><%=clmStatusCount.get("C02504")%> <spring:message code="clm.page.msg.common.case" /></a></em>
										</div>
									</li>
									<li class="step-list-bg05">
										<strong>
											<a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537445_0000000053', '_self');" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('step05','','<%=IMAGE%>/new2011/txt_main_step05_on.gif',1)">
												<img src="<%=IMAGE%>/new2011/txt_main_step05_off.gif" name="step05" border="0" id="step05" />
											</a>
										</strong>
										<span class="left_bg"></span>
										<div>
											<em><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091537445_0000000053', '_self');"><%=clmStatusCount.get("C02505")%> <spring:message code="clm.page.msg.common.case" /></a></em>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<!-- //work -->						
<%
	if(!personalFlag){
%>
						<!-- approval -->
						<div id="approval" style="display:none">
							<ul>
								<li><a href="#" onClick="work.style.display='block';approval.style.display='none';"><img src="<%=IMAGE%>/common/work_tab_off.gif" /></a></li>
								<li><img src="<%=IMAGE%>/common/approval_tab_on.gif" /></li>
								<li class="more"><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091536650_0000000046', '_self');" title="more"><IMG SRC="<%=IMAGE%>/btn/btn_more.gif"></a></li>
							</ul>
							<table>
								<colgroup>
								<col/>
								<col width="76px;"/>
								</colgroup>
<%
if(clmsApprovalList.size() > 0){

	for(int idx=0;idx<clmsApprovalList.size();idx++){
		ListOrderedMap approvalList = null;
		approvalList = (ListOrderedMap)clmsApprovalList.get(idx);
%>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
									<th class="overflow">
										<a href="javascript:Menu.detail('frm','_self','20110802182454521_0000000036','20110803091536650_0000000046','/clm/manage/myApproval.do?method=detailMyApproval&cnsdreq_id=<%=approvalList.get("cnsdreq_id")%>');">
										<%=approvalList.get("req_title")%></a>			
<%
		if("NEW".equals(approvalList.get("new_yn"))){
%>
										<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
<%			
		}
%>	
									</th>
									<td class="date"><%=approvalList.get("prcs_depth_nm")%></td>
								</tr>				
<%		
	}
}else{
%>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
									<th class="overflow">
										<spring:message code="secfw.msg.succ.noResult" />		
									</th>
									<td class="date"></td>
								</tr>											
<%	
}
%>									
							</table>
						</div>
						<!-- //approval -->
<%
	}
%>
					</div>
				</div>
				<div class="twocolum fL">
					<div class="left-bbs">
						<!-- notice1 -->
						<div id="allNotice">
							<ul>
								<li><img src="<%=IMAGE%>/common/notice01_tab_on.gif" /></li>
								<li><a href="#" onClick="allNotice.style.display='none';notice.style.display='block';"><img src="<%=IMAGE%>/common/notice02_tab_off.gif"/></a></li>
								<li class="more"><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803092210347_0000000062', '_self');" title="more"><IMG SRC="<%=IMAGE%>/btn/btn_more.gif"></a></li>
							</ul>
							<table>
								<colgroup>
								<col/>
								<col width="76px;"/>
								</colgroup>
<%if(clmBoardList != null && clmBoardList.size() > 0){
	for(int idx=0;idx<clmBoardList.size();idx++){
		ListOrderedMap boardList = null;
		boardList = (ListOrderedMap)clmBoardList.get(idx);
%>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
									<th class="overflow">
										<a href="#" onClick="javascript:getBoardDetail('<%=boardList.get("seqno")%>');">
										<%if(localeCode.equals("ko")){ %>
						          		<%=(String)boardList.get("title")%>
						          		<%} else { %>
						          		<%=(String)boardList.get("title_en")%>
						          		<%} %>
										</a>
<%
		if("NEW".equals(boardList.get("new_yn"))){
%>
										<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
<%			
		}
%>
										
									</th>
									<td class="date"><%=DateUtil.formatDate((String)boardList.get("regdt"),"-")%></td>
								</tr>
<%		
	}
}
%>									
							</table>
						</div>
						<!-- //notice1 -->
						<!-- notice2 -->
						<div id="notice" style="display:none">
							<ul>
								<li><a href="#" onClick="allNotice.style.display='block';notice.style.display='none';"><img src="<%=IMAGE%>/common/notice01_tab_off.gif" /></a></li>
								<li><img src="<%=IMAGE%>/common/notice02_tab_on.gif" /></li>
								<li class="more"><a href="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803091536762_0000000047', '_self');" title="more"><IMG SRC="<%=IMAGE%>/btn/btn_more.gif"></a></li>
							</ul>
							<table>
								<colgroup>
								<col/>
								<col width="76px;"/>
								</colgroup>
<%
	for(int idx=0;idx<clmMyNoticeList.size();idx++){
		ListOrderedMap myNoticeList = null;
		myNoticeList = (ListOrderedMap)clmMyNoticeList.get(idx);
%>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
									<th class="overflow">
										<a href="#" onClick="javascript:getMyNoticeDetail('<%=myNoticeList.get("mis_id")%>','<%=myNoticeList.get("module_id")%>');"><%=myNoticeList.get("subject")%></a>
<%
		if("NEW".equals(myNoticeList.get("new_yn"))){
%>
										<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
<%			
		}
%>
										
									</th>
									<td class="date"><%=myNoticeList.get("create_date")%></td>
								</tr>
<%		
	}
%>	
							</table>
						</div>
						
						<!-- //notice2 -->
					</div>
				</div>
			</div>
			<!-- searchbox -->
			<!-- 검색엔진작업영역 -->
			<!-- **************************** Form Setting **************************** -->
			<form:form name="searchfrm" id='searchfrm' method="post" autocomplete="off">
				<input type="hidden" name="method"   value="" /> 
				<input type="hidden" id="curPage" name="curPage" value=""/>
				<input type="hidden" id="srch_index_db" name="srch_index_db" value=""/>
				<input type="hidden" id="queryText" name="queryText" value=""/>
				<input type="hidden" id="queryField" name="queryField" value=""/>
				<input type="hidden" id="fieldText" name="fieldText" value="">
				<input type="hidden" id="srchMinDate" name="srchMinDate" value="">
				<input type="hidden" id="srchMaxDate" name="srchMaxDate" value="">
				<input type="hidden" id="flag" name="flag" value=""/>
			<!-- // **************************** Form Setting **************************** -->
			<div class="search-area">
				<div class="searchbox">
					<div class="searchbox_in">
						<div class="search_content">
							<div class="txt01">
								<img src="<%=IMAGE%>/common/txt01.gif" alt="<spring:message code="clm.page.field.srch.search" />" />
								<label for="check01"><spring:message code="clm.page.field.srch.main.contract" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" onclick="actsrh(this)" value="clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_master_orglastransfer,clm_master_divisiontransfer" checked disabled/>
								<label for="check02"><spring:message code="clm.page.field.srch.main.about" /> </label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_about" checked disabled/>
								<label for="check03"><spring:message code="clm.page.field.srch.main.library" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" value="clm_library" checked disabled/>
								<label for="check04"><spring:message code="clm.page.field.srch.main.rule" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" value="clm_rule_regulation,clm_rule_decision" checked disabled/>
<%--								
                                <label for="check05"><spring:message code="clm.page.field.srch.main.share" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" value="clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer" checked disabled/>
--%>                                
                                <label for="check05"><spring:message code="clm.page.field.srch.main.share" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" value="clm_share_contracttype,clm_share_rule,clm_share_terms" checked disabled/>
                                <label for="check06"><spring:message code="clm.page.field.srch.main.counsel" /> </label> <input type="checkbox" name="dbsrh" id="dbsrh" value="clm_counsel_notice,clm_counsel_faq,clm_counsel_qna" checked disabled/>
							</div>
							<div class="search">
								<span class="search-input">
									<input type="text" class="txt" id="query" name="query" maxlength=100 onkeypress="if(event.keyCode==13) {pageAction();return false;}" value=""/>
								</span>
								<span class="search-btn">
									<a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search02.gif" alt="<spring:message code="clm.page.field.srch.search" />" /></a>
									<a href="javascript:;" onclick="actdetailSearch();" class="detail_search"><img src="<%=IMAGE%>/btn/btn_detail_search.gif" alt="<spring:message code="clm.page.field.srch.detailSearch" />" /></a>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="detailSearch" class="option_layer" style="height:257px; width:350px;">
				<ul>
					<li>
						<strong><spring:message code="clm.page.field.srch.contractField" /></strong>
						<label for="option01"><spring:message code="clm.page.field.srch.title" /></label> <input type="checkbox" name="zsrh" id="zsrh" value="DRETITLE" checked />
						<label for="option02"><spring:message code="clm.page.field.srch.attach" /></label> <input type="checkbox" name="zsrh" id="zsrh" value="DREFILECONTENT" checked />
						<label for="option03"><spring:message code="clm.page.field.srch.info" /></label> <input type="checkbox" name="finfo" id="finfo" onclick="actsrhinfo()" value="" />
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.region_gbn" /></strong>
						<select class="all" style="width:199px" name="region_gbn" id="region_gbn" disabled>
						<option value = "" selected>-- ALL --</option>
						<option value = "C01801" ><spring:message code="clm.page.field.srch.region_gbn_value1" /></option>
						<option value = "C01802" ><spring:message code="clm.page.field.srch.region_gbn_value2" /></option>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.biz_clsfcn" /></strong>
						<select class="all" style="width:199px" name="biz_clsfcn" id="biz_clsfcn" disabled>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.depth_clsfcn" /></strong>
						<select class="all" style="width:199px" name="depth_clsfcn" id="depth_clsfcn" disabled>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.cnclsnpurps_bigclsfcn" /></strong>
						<select class="all" style="width:199px" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" disabled>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.cnclsnpurps_midclsfcn" /></strong>
						<select class="all" style="width:199px" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" disabled>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.payment_gbn" /></strong>
						<select class="all" style="width:199px" name="payment_gbn" id="payment_gbn" disabled>
						<option value = "" selected>-- ALL --</option>
						<option value = "C02001" ><spring:message code="clm.page.field.srch.payment_gbn_value1" /></option>
						<option value = "C02002" ><spring:message code="clm.page.field.srch.payment_gbn_value2" /></option>
						<option value = "C02003" ><spring:message code="clm.page.field.srch.payment_gbn_value3" /></option>
						<option value = "C02004" ><spring:message code="clm.page.field.srch.payment_gbn_value4" /></option>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn" /></strong>
						<select class="all" style="width:199px" name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" disabled>
						<option value = "" selected>-- ALL --</option>
						<option value = "Y" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value1" /></option>
						<option value = "N" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value2" /></option>
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.cntrt_status" /></strong>
						<select class="all" style="width:199px" name="cntrt_status" id="cntrt_status" disabled>
<%--						
						<option value = "" selected>-- ALL --</option>
						<option value = "C02401" ><spring:message code="clm.page.field.srch.cntrt_status_value1" /></option>
						<option value = "C02402" ><spring:message code="clm.page.field.srch.cntrt_status_value2" /></option>
						<option value = "C02403" ><spring:message code="clm.page.field.srch.cntrt_status_value3" /></option>
						<option value = "C02404" ><spring:message code="clm.page.field.srch.cntrt_status_value4" /></option>
						<option value = "C02405" ><spring:message code="clm.page.field.srch.cntrt_status_value5" /></option>
						<option value = "C02406" ><spring:message code="clm.page.field.srch.cntrt_status_value6" /></option>
--%>
						<option value = "" selected>-- ALL --</option>
						<option value = "C02501" ><spring:message code="clm.page.field.srch.cntrt_status_value1" /></option>
						<option value = "C02502" ><spring:message code="clm.page.field.srch.cntrt_status_value2" /></option>
						<option value = "C02503" ><spring:message code="clm.page.field.srch.cntrt_status_value3" /></option>
						<option value = "C02504" ><spring:message code="clm.page.field.srch.cntrt_status_value4" /></option>
						<option value = "C02505" ><spring:message code="clm.page.field.srch.cntrt_status_value5" /></option>						
						</select>
					</li>
					<li>
						<strong><spring:message code="clm.page.field.srch.dredate" /></strong>
						<td>
						<input type="text" name="srch_start_dt" id="srch_start_dt" value="" disabled class="text_calendar" style="width:70px" readonly />
						~
						<input type="text" name="srch_end_dt" id="srch_end_dt" value="" disabled class="text_calendar" style="width:70px" readonly />
						</td>
					</li>
				</ul>
				<a href="javascript:;" onclick="displayLayer('detailSearch', this, -90, -375);" class="btn_close"><img src="<%=IMAGE%>/new2011/btn_close2.gif" alt="<spring:message code="clm.page.field.srch.close" />" /></a>
			</div>
			</form:form>
			<!-- //searchbox -->
			<!-- //검색엔진작업영역 -->
			
			<div class="banner_area">
			
				<table style='margin-top:20px;width:100%'>
					<colgroup>
						<col width="185px" />
						<col width="185px" />
						<col width="*" />
					</colgroup>
					<tr>
					<!--  !@# 서버URL일괄변경 -->
						<td><a href="http://slas.sec.samsung.net/login.do" target="_blank"><img src="<%=IMAGE%>/common/bananer_las.jpg"  alt="법무시스템" title="법무시스템" /></a></td>
						<td><a href="http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660" target="_blank"><img src="<%=IMAGE%>/common/bananer_VOC.jpg"  alt="IT-VOC" title="IT-VOC"/></a></td>						
						<td width="*">
							<%
				if(viewQnA){
%>
				<!-- banner -->
<%--				
				<div class="banner">
					<div class="banner_in" style='text-align:left'>
						<div onClick="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20110803092535871_0000000071', '_self');" style='position:relative; width:152px; height:60px; text-align:left; left:50%; margin-left:-103px; padding:16px 0 0 53px; margin-top:14px;cursor:pointer;font-size:10px; background:url("<%=IMAGE%>/common/banner_faq3.gif") no-repeat'>
							<div style='font-family:Malgun Gothic; font-size:11px; letter-spacing:-1px; color:#536D45'><%=bannerMsg1%></div>
<%
					if(banner2BoldFlag){
%>
							<div style='font-family:Malgun Gothic; font-size:11px; letter-spacing:-1px; color:#536D45'><%=bannerMsg2%></div>
<%						
					}else{
%>
							<div style='font-family:tahoma;'><%=bannerMsg2%></div>
<%
					}
%>
<%
					if(banner3BoldFlag){
%>
							<div style='font-family:Malgun Gothic; font-size:11px; letter-spacing:-1px; color:#536D45'><%=bannerMsg3%></div>
<%						
					}else{
%>
							<div style='font-family:tahoma;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;'><%=bannerMsg3%></div>
<%
					}
%>						
						</div> 
					</div>
				</div>
 --%>
				<div class="banner"  style='margin:0; padding:0;'>
					<div class="banner_in" style='text-align:left;overflow:hidden;'>
      					<div style='position:relative; height:60px;  text-align:left; padding-top:12px; margin-top:px;cursor:pointer;font-size:12px; background:url("<%=IMAGE%>/common/banner_faq4.gif") no-repeat 0px 4px'>
       					    	<div style='font-family:Dotum; letter-spacing:-1px; font-size:12px; color:#4B7D30; font-weight:bold; margin:0 0 4px 53px'><spring:message code="clm.page.msg.main.contManagerOri" /></div>
       						<div style='font-family:Dotum; letter-spacing:-1px;  font-size:11px;font-weight:bold; margin:0 0 4px 51px'><%=bannerMsg1%></div>
<%
					if(banner2BoldFlag){
%>       						
       						<div style='font-family:Dotum; letter-spacing:-1px; font-size:11px; font-weight:bold; margin:0 0 4px 51px'><%=bannerMsg2%></div>
<%						
					}else{
%>  
    						<div style='font-family:Dotum; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;font-weight:bold; font-size:11px; margin:0 0 4px 51px'><%=bannerMsg2%></div>
<%
					}
%>							

<%
					if(banner3BoldFlag){
%>
							<div style='font-family:Dotum; letter-spacing:-1px; font-size:11px; font-weight:bold; margin:0 0 4px 51px'><%=bannerMsg3%></div>
<%						
					}else{}
%>
							<!--  div style='font-family:tahoma; overflow:hidden;text-overflow:ellipsis;white-space:nowrap; margin:0 0 0 53px'--><!--  /div-->

      					</div> 
     				</div>
    			</div> 
				
				<!-- //banner -->
<%
				}else{
%>
				<!-- banner -->
				<div class="banner">
					<div class="banner_in">
						<p><a href="<%=cpmsLoginUrl%>" target="_blank"><img src="<%=IMAGE%>/common/banner_cpms.jpg" alt="Compliance Manager" /></a></p>
					</div>
				</div>
				<!-- //banner -->
<%
				}
%>
							
				
						</td>
					</tr>
				</table>
				
				
				
	

			</div>
			
			<div class="sp"></div>
		</div>
		<!-- //content -->
		<!-- footer -->
		<div id="footer">
			<address> Copyright ⓒ SAMSUNG. All rights reserved. </address>
		</div>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>


<script language="javascript">
	openPop();
	
	var cpFlag = false;
	//사업부관리자 승인 건수 알럿띄우기
	<%
	List roleList = (List)session.getAttribute("secfw.session.role");

	if(roleList != null && roleList.size() > 0) {
		for(int ri = 0; ri < roleList.size(); ri++) {
			HashMap roleHm = (HashMap)roleList.get(ri);
			if("RD01".equals((String)roleHm.get("role_cd"))) {
	%>
				cpFlag = true;
	<%
				break;
			}
		}
	}
	%>
	if(cpFlag){
		alertAdmin();
	}

	function alertAdmin(){
		
		var options = {
			url: "<c:url value='/common/clmsCom.do?method=getApprovalCntMain' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				//setTimeout(alertAdmin, 300000); //5분마다 발생
				var cntrt_cnt_1 		= responseText.cntrt_cnt_1;
				var cntrt_cnt_2 		= responseText.cntrt_cnt_2;
				var cntrt_cnt_3 		= responseText.cntrt_cnt_3;
				var cntrt_cnt_4 		= responseText.cntrt_cnt_4;
				
				if(cntrt_cnt_1 > 0 || cntrt_cnt_2 > 0 || cntrt_cnt_4 > 0){
					//alert("원본접수 "+cntrt_cnt_1+"건,\n자동연장 승인 "+cntrt_cnt_2+"건,\n체결후등록 승인 "+cntrt_cnt_3+"건,\n표준계약서 승인 "+cntrt_cnt_4+"건이 있습니다.");
					//alert("원본접수 "+cntrt_cnt_1+"건,\n자동연장 승인 "+cntrt_cnt_2+"건,\n표준계약서 승인 "+cntrt_cnt_4+"건이 있습니다.");
					var valert = "<spring:message code='common.page.field.AlertApprovalPopup.allmsg'  htmlEscape='false'/>";
					var vchg_alert = valert;
					vchg_alert = vchg_alert.replace("{0}", cntrt_cnt_1);
					vchg_alert = vchg_alert.replace("{1}", cntrt_cnt_2);
					vchg_alert = vchg_alert.replace("{2}", cntrt_cnt_4);
					alert(vchg_alert);
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	}
</SCRIPT>

<!-- 세션을 유지하기 위한 iframe하나를 숨겨돈다. iframe안에서 페이지를 5분마다 refresh 한다-->
<iframe name='iframeTemp' src="<c:url value='/clm/pageReload.do' />" frameborder='0' style='width:0px;height:0px'></iframe>


</body>
</html>
<%!
//My Approval view 여부 판단(RD01이 아닌 사용자는 안보이게 한다.)
public boolean personalFlag(HttpSession session) throws Exception{
	/*
	RD01 : 사업부계약관리자
	*/	
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	
	ClmsDataUtil.debug("##roleList : " + roleList);
	
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    
    if(userRoleList != null && userRoleList.size()>0) {
    	if(!userRoleList.contains("RD01")) {
    		retV = true;
		}
	}
    return retV;
}

//계약서담당자변경 view 여부 판단
public boolean chgPersonflag(HttpSession session) throws Exception{
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String managerYN = (String)session.getAttribute("secfw.session.manager_yn");
	
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    String result = "";
    if(userRoleList != null && userRoleList.size()>0) {
    	
		if(userRoleList.contains("RA00") || userRoleList.contains("RD01")){
			result = "A";
		}else if(userRoleList.contains("RD02")){
			result = "B";
		}
	}
    
    //RA00,RD01 보여야함
    //RD02이면서 조직장인 경우 보여야함
    if("A".equals(result) || ("B".equals(result) && "Y".equals(managerYN))){
    	retV = true;
    }
    
    return retV;
}
%>
