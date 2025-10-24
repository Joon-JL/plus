<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    List menuList       = (List) request.getAttribute("menuList");     // 메뉴 리스트
    List lasBoardList   = (List) request.getAttribute("lasBoardList"); // 공지사항 리스트
    List latestNoticeSeqno  = (List) request.getAttribute("latestNoticeSeqno"); // 팝업공지대상 시퀀스넘버 리스트

    String topMenuLevel = (String) request.getAttribute("topMenuLevel");
    int menuSize        = Integer.parseInt((String) request.getAttribute("menuSize"));
    int menuIdCnt       = 0;
    int menuMax         = 1;

    String localeCode   = (String)session.getAttribute("secfw.session.language_flag");
    String blngt_orgnz  = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	String division_gbn = StringUtil.bvl((String)session.getAttribute("secfw.server.division_gbn"), ""); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.

    String mainImgNm   = "/attach/main/" + langCd + "/" + (String)request.getAttribute("mainImgNm");
    String mainBgImgNm = IMAGE + "/main/" + (String)request.getAttribute("mainBgImgNm");
    
    
    ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	ArrayList userRoleList = new ArrayList();
	
	String role_nm = "";
	String role_nm_sum = "";
	String role_cd = "";
	String split_role_nm = "";

	if(roleList != null && roleList.size()>0){
		for(int idx=0; idx<roleList.size();idx++) {
			HashMap roleListMap = (HashMap)roleList.get(idx);
			
			role_cd = (String)roleListMap.get("role_cd");
			
			if(idx == 0){
				
				if("RA01".equals(role_cd) || "RD01".equals(role_cd) || "RM00".equals(role_cd)){
					
					role_nm = "Leqal Admin";
					
				} else {
					role_nm = (String)roleListMap.get("role_nm");
				}
				
			} else if(idx > 0){
				
				if("RA01".equals(role_cd) || "RD01".equals(role_cd) || "RM00".equals(role_cd)){
					
					if(idx > 1){
						role_nm = "";
					} else {
						role_nm = ", Leqal Admin";
					}
					
					
				} else {
					role_nm =", "+ (String)roleListMap.get("role_nm");
				}
				
			} else {
				
				if("RA01".equals(role_cd) || "RD01".equals(role_cd) || "RM00".equals(role_cd)){
					if("Leqal Admin".equals(role_nm)){
						role_nm = "";
					}
					
				} else {
					role_nm =", "+ (String)roleListMap.get("role_nm");
				}
				
			}
			
			role_nm_sum += role_nm;
			
		}
	}

%>

<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
<!-- 2014-09-03 Kevin added -->
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>

<!-- 통합검색관련 추가끝 -->
<script>

$(function(){
	// 2014-09-03 Kevin added.
	// 화면 오픈시 나타났던 alert 박스 대신 growl 레이어를 통해 수집해야 할 hard copy 갯수를 알려준다. 사용자 경험 개선.
	var V_NotServed = '<c:out value="${cntNotServed}"/>';
	if(V_NotServed != '0'){
        $.blockUI({ 
            message: $("div.growlUI"), 
            fadeIn: 1500, 
            fadeOut: 700, 
            timeout: 5000, 
            showOverlay: false, 
            centerY: false, 
            css: { 
                width: '350px',
                top: '',
                bottom: '10px', 
                left: '', 
                right: '10px', 
                border: 'none', 
                padding: '5px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: .6, 
                color: '#fff' 
            } 
        }); 
	}
	
	// 시작기간과 끝기간의 캘린더를 생성한다.
	initCal("srch_start_dt");	
	initCal("srch_end_dt");
	// 순차적으로 상세검색용 selectbox를 작성한다.
	getCodeSelectByUpCd("searchfrm", "biz_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getBiz_clsfcn&flag=L');
	getCodeSelectByUpCd("searchfrm", "depth_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getDepth_clsfcn&flag=L');
	getCodeSelectByUpCd("searchfrm", "cnclsnpurps_bigclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_bigclsfcn&flag=L');
	getCodeSelectByUpCd("searchfrm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=L');
	
	// cnclsnpurps_bigclsfcn 하위 selectbox를 동적으로 가져온다.
	$("#cnclsnpurps_bigclsfcn").change(function() {
		var upCd = $("#cnclsnpurps_bigclsfcn").val();
		getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=C&upCd=' + upCd);
	});
	
	
	if (!$.browser.opera) {
        $('select.lang_select').each(function() {
            var title = $(this).attr('title');
            if ($('option:selected', this).val() != '')
                title = $('option:selected',this).text();

            $(this)
                .css({'z-index':10,'opacity':0,'-khtml-appearance':'none'})
                .after('<span class="lang_select">' + title + '</span>')
                .change(function() {
                    val = $('option:selected',this).text();
                    $(this).next().text(val);
            })
        });
    };
    var NowDate = new Date();
	var nYear = NowDate.getFullYear();		
	var nMonth = NowDate.getMonth();	
	cal(nYear,nMonth);
	
});


/**
 * 공지사항 상세
 */
function getBoardDetail(seqno) { 
    var frm = document.frm;

    PopUpTargetWindowOpen('', 640, 480, false,"PopUpWindowMain");
    frm.target = "PopUpWindowMain";

    frm.action = '/las/board/notice.do?seqno='+seqno;
    frm.method.value  = 'detailNoticeByMain';
    frm.menu_id.value = '20130319160701794_0000000390';// 공지사항
    frm.submit();
}


/**
 * 팝업공지 상세
 */
function getNoticePopDetail(seqno, position) {
    var frm = document.frm;

    PopUpWindowOpen3('', 640, 480, false, seqno, position);
    frm.target = seqno;

    frm.action = '/las/board/notice.do?seqno='+seqno+'&path_gbn=Main';
    frm.method.value  = 'detailNoticeByMain';
    frm.menu_id.value = '20130319160701794_0000000390';// 공지사항
    frm.submit();
}


/**
 * 주요입법동향 상세
 */
function getLawInfoDetail(seqno) {
    var frm = document.frm;

    PopUpWindowOpen('', 640, 480, false);
    frm.target = "PopUpWindow";

    frm.action = '/las/lawinformation/mainLawInfo.do?seqno='+seqno;
    frm.method.value  = 'detailMainLawInfoByMain';
    frm.menu_id.value = '20130319160137907_0000000381';
    frm.submit();
}

/**
 * 일정관리 등록화면 팝업
 */
function popUpWeekSchedule() {

<%
    if (ClmsBoardUtil.searchRole(request, "RA02") || ClmsBoardUtil.searchRole(request, "RA03")) {
%>
        var frm = document.frm;
        var eventCookie = getCookie("weekSchedule");

        if (eventCookie != "no") {
            PopUpWindowModalOpen('/las/board/weekSchedule.do?method=forwardInsertWeekScheduleByMain&menu_id=20130321152454125_0000000431', 800, 620);
        }
<%
    }
%>

}

// 모달 팝업
function PopUpWindowModalOpen(surl, popupwidth, popupheight, bScroll) {
    if (popupwidth > window.screen.width)
        popupwidth = window.screen.width;
    if (popupheight > window.screen.height)
        popupheight = window.screen.height;

    if (isNaN(parseInt(popupwidth))) {
        Top  = (window.screen.availHeight - 600) / 2;
        Left = (window.screen.availWidth  - 800) / 2;
    } else {
        Top  = (window.screen.availHeight - popupheight) / 2;
        Left = (window.screen.availWidth  - popupwidth) / 2;
    }

    if (Top < 0) Top   = 0;
    if (Left < 0) Left = 0;

    var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
    window.showModalDialog(surl, "PopUpWindow", sFeatures);
}


/**
* 버튼 동작 부분
*/ 
function pageAction(){
	
	var frm = document.searchfrm;
	frm.target = "_self";
	frm.action = "<c:url value='/clm/search/search.do' />";
	frm.flag.value = "L"; //Url통합관련
	
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
	    frm.method.value = "passSearch";
		frm.curPage.value = "1";
		//frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_rule_regulation,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
		//카테고리 체크에 따라 검색할 색인DB를 세팅한다.
		for(var i = 0; i < frm.dbsrh.length; i++)
		{
			if(frm.dbsrh[i].checked == true)
			{
				if(frm.srch_index_db.value == "") {
					frm.srch_index_db.value = frm.dbsrh[i].value;
				} else {
					frm.srch_index_db.value = frm.srch_index_db.value + "," + frm.dbsrh[i].value;
				}
			}	
		}
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
	    frm.method.value = "passSearch";		    
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
		
		if(frm.dbsrh[0].checked != true & frm.dbsrh[1].checked != true & frm.dbsrh[5].checked != true) {
			if(checkdbnum == 1) frm.method.value = "listDetailSearch";
		}
		
		if(frm.srch_index_db.value == "") {
			frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_rule_regulation,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
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
					fieldText = fieldText + " AND MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_PRCS_DEPTH";
				} else {
					fieldText = "MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_PRCS_DEPTH";
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
		viewHiddenProgress(true);
	}
}

/**
* 계약서 상세선택항목 활성화
*/
function actsrh(_elm){		
	var frm = document.searchfrm;
	if(frm.dbsrh[0].checked == true) {
		$('#detailSearch').show();
		frm.zsrh[0].checked = true;
		frm.zsrh[1].checked = true;
		frm.zsrh[0].disabled = false;
		frm.zsrh[1].disabled = false;
		frm.finfo.checked = false;
		frm.finfo.disabled = false;
		
		actsrhinfo();
		
	} else {
		$('#detailSearch').hide();
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
	// 2014-09-03 Kevin code refactoring.
	var isChecked = frm.finfo.checked; 
	frm.biz_clsfcn.disabled = !isChecked;
	frm.depth_clsfcn.disabled = !isChecked;
	frm.cnclsnpurps_bigclsfcn.disabled = !isChecked;
	frm.cnclsnpurps_midclsfcn.disabled = !isChecked;
	frm.payment_gbn.disabled = !isChecked;
	frm.cntrt_cnclsn_yn.disabled = !isChecked;
	frm.cntrt_status.disabled = !isChecked;
	frm.srch_start_dt.disabled = !isChecked;
	frm.srch_end_dt.disabled = !isChecked;
	
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
	// 2014-09-03 Kevin code refactoring.
	var isDisabled = frm.dbsrh[0].disabled;
	for(var i = 0; i < 6; i++){
		frm.dbsrh[i].checked = frm.dbsrh[i].disabled = !isDisabled; 
	}
	
	var searchTarget = $('#searchTarget');
	if (searchTarget.is(":visible")) {
		searchTarget.hide();
	} else {
		$('#detailSearch').hide();
		searchTarget.show();
	}
}

// 오픈공지팝업 - 2012-03-14 사업부 오픈공지 팝업은 다 막고 연계테스트 위한 팝업을 띄운다.
// 영상디스플레이 - 계약관리용 연계공지팝업으로 변환하여 사용,
// 무선사업부 - 법무시스템용 연계공지 팝업으로 변환하여 사용
function openPop() {
    var temp = '<%= blngt_orgnz %>';
    //Device Solutions부문 팝업공지 띄움(Device Solutions부문,메모리사업부,System LSI사업부,LED사업부,반도체연구소,Test&Package센터,Infra기술센터,생산기술연구소
    //2012/11/9 ~ 2012/11/13까지 띄움
    if (temp == "B00000014" || temp == "B00000003" || temp == "B00000015" || temp == "B00000040" || temp == "B00000033" || temp == "B00000034" || temp == "B00000035" || temp == "B00000036") {
        var date1 = "2012/11/09"; // 팝업 시작날짜
        var date2 = "2012/11/11"; // 팝업 종료날짜

        var now = new Date(); // 현재날짜
        var begin = new Date(date1);
        var end   = new Date(date2);

        // 조건에 맞으면 팝업을 띄움...
        if (now >= begin && now <= end) {
            var noticeCookie = getCookie("noticePopupDS");
            if (noticeCookie != "no") {
                PopUpWindowOpen3('/las/openPopDS.do', '330', '375', false, 'PopUpOpenNoticeDS',0);
            }
        }
    }

    // 팝업공지가 존재하면 해당 공지사항의 Seqno를 가져와 팝업을 띄움
    if(<%=latestNoticeSeqno.size()%> > 0){
        var noticeCookieLas = getCookie("noticePopupLasMain");
        var position=0;
<%
        int i=0;

        for(i=0; i<latestNoticeSeqno.size();i++){
            ListOrderedMap lom = (ListOrderedMap)latestNoticeSeqno.get(i);      %>
            if(noticeCookieLas != "no"){
                getNoticePopDetail(<%=lom.get("seqno").toString()%>,position);
                position = position+100;
            }<%
        }
%>
    }

}

function PopUpWindowOpen3(surl, popupwidth, popupheight, bScroll, popName, position) {
    if (popupwidth > window.screen.width)
        popupwidth = window.screen.width;
    if (popupheight > window.screen.height)
        popupheight = window.screen.height;

    if (isNaN(parseInt(popupwidth))) {
        Top  = (window.screen.availHeight - 600) / 2;
        Left = (window.screen.availWidth  - 800) / 2;
    } else {
        Top  = (window.screen.availHeight - popupheight) / 2;
        Left = (window.screen.availWidth  - popupwidth) / 2;
    }

    // position : 팝업창 여러개 호출시, 위치 차등
    Left = Left + position;
    Top  = Top  + position;

    if (Top < 0)   Top = 0;
    if (Left < 0) Left = 0;

    popupwidth = parseInt(popupwidth) + 10 ;
    popupheight = parseInt(popupheight) + 29 ;

    var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
    PopUpWindow = window.open(surl, popName , Feture);

    PopUpWindow.focus();
}

function divShow(obj) {
    obj.style.display = 'block';
}

function divHidden(obj) {
    obj.style.display = 'none';
}

// 공무집행 대응 변호사 팝업
function openContactUsPop() {
    PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsLas');
}

// 업무분야별 전문 변호사 팝업
function openExpertPop() {
    PopUpWindowOpen2('/las/openPopMSC.do', '840', '600', false, 'PopUpExpertClm');
}

//it-voc 이동
function openItvoc(){
	
	var msg = "Contact Division contract managers for the tasks below.\r\n";
	msg += "(Refer to the contract manager info on the main page.)\r\n";
	msg += "\r\n";
	msg += "- Change Requester/Associate\r\n";
	msg += "- Modify the Counterparty Info\r\n";	
	msg += "- Register or Delete Related People (After the Submission of the Original Contract)\r\n";
	msg += "- Modify a Copy of the Concluded Contract (After the Submission of the Original Contract)\r\n";
	msg += "- Modify Contract Info (After the Submission of the Original Contract)\r\n";
	
	if(confirm(msg)){
		if(<%=langCd.equals("ko")%>){
			window.open("http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660");	
		}else{
			window.open("http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660");
		}
	}
}
</script>
<!-- select 디자인 -->
<script type="text/javascript">
    
    /**
	 * 오늘날짜 구하기
	 */
	function getToday_YYYYMMDD(mode) {
		
		var NowDate = new Date();
		yyyy = NowDate.getFullYear();
		mm = Number(NowDate.getMonth()) + 1;
		dd =  NowDate.getDate();
		
		str_yyyy = yyyy;
		str_mm = "";
		str_dd = "";
		
		var str_yyyymmdd;		
		
		if ( mm < 10) {
			str_mm = "0" + mm;
		} else {
			str_mm = String(mm);
		}
		
	  	if (dd < 10) {
	  		str_dd = "0"+ dd;
		} else {
			str_dd = String(dd);
		}
		
		if(mode=="yyyymmdd"){			  
		  	str_yyyymmdd = str_yyyy + str_mm + str_dd;		
		} else if(mode=="yyyy.mm.dd"){
			str_yyyymmdd = str_yyyy + "." + mm +"."  + dd;			
		} else if(mode=="yyyy/mm/dd"){
			str_yyyymmdd = str_yyyy + "/" + mm +"/"  + dd;			
		} else if(mode=="yyyy-mm-dd"){
			str_yyyymmdd = str_yyyy + "-" + str_mm +"-"  + str_dd;			
		}

		return str_yyyymmdd;
	}
    
	
	var v_OldYear=0;
    var v_OldMonth=0;
    /**
	 * 월 변경
	 */
	function chgCal(flag){
    	
		viewHiddenProgress(true) ;	
    	
    	var v_TempYear = v_OldYear;
        var v_TempMonth = v_OldMonth;
        
    	if(flag=='PREV'){
    		v_TempMonth = v_TempMonth-1;
    		
    		if( v_TempMonth == -1) {
    			v_TempYear = v_TempYear -1 ;
    			v_TempMonth = 11;   			
    		}
    		
    	}else{
    		v_TempMonth = v_TempMonth+1;
    		if( v_TempMonth == 12) {
    			v_TempYear = v_TempYear +1 ;
    			v_TempMonth = 0;
    		}

    	}    	
   	
    	cal(v_TempYear, v_TempMonth);
    }
    /**
	 * 달력 호출
	 */
	function cal(iYear, iMonth) {
    	v_OldYear = iYear;
        v_OldMonth = iMonth;

    	var v_Prev_Of_OldMonth = iMonth-1;
        
    	if(iMonth==0){
    		v_Prev_Of_OldMonth = 11;
    	}
		document.getElementById('cal_title').innerHTML = iYear + '.' + (iMonth+1);
        
		var str = "";
		var frm = document.frm;
		var str_today_yyyymmdd = getToday_YYYYMMDD("yyyymmdd");

		var yoil = new Array(	
				"<spring:message code='las.page.field.cal.mon'/>",	//월 
				"<spring:message code='las.page.field.cal.tue'/>", 	//화
				"<spring:message code='las.page.field.cal.wed'/>", 	//수
				"<spring:message code='las.page.field.cal.thr'/>", 	//목
				"<spring:message code='las.page.field.cal.fri'/>", 	//금
				"<spring:message code='las.page.field.cal.sat'/>", //토
				"<spring:message code='las.page.field.cal.sun'/>"	//일
				);	
		
		var endDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		
		//윤달계산								
		if(iYear % 400 == 0 || (iYear % 4 == 0 && iYear % 100 !=0)){endDay[1] = 29;}
		
		var c = 0;		
		
		eDate = new Date();
		eDate.setFullYear(iYear);
		eDate.setDate(1);
		eDate.setMonth(iMonth);
		
		$('#s_year').html('');
		$('#s_month').html('');
		
		$('#s_year').html(eDate.getFullYear());
		$('#s_month').html(eDate.getMonth() + 1);

		var fDay = eDate.getDay();
		var lDay = endDay[eDate.getMonth()];
		
		if(fDay==0){
			fDay = 6;
		} else {
			fDay = fDay - 1;
		}

		str += "<table class='form_cal_mini'>";
		
		str += "<tr>";
		str += "<th><spring:message code='las.page.field.cal.mon'/></th>";  // 월
		str += "<th><spring:message code='las.page.field.cal.tue'/></th>";  // 화
		str += "<th><spring:message code='las.page.field.cal.wed'/></th>";  // 수
		str += "<th><spring:message code='las.page.field.cal.thr'/></th>";  // 목
		str += "<th><spring:message code='las.page.field.cal.fri'/></th>";  // 금
		str += "<th class='sat'><spring:message code='las.page.field.cal.sat'/></th>"; // 토
		str += "<th class='sun'><spring:message code='las.page.field.cal.sun'/></th>"; // 일
		str += "</tr>";

		str += "</tr><tr>";

		var lPrevDay = endDay[v_Prev_Of_OldMonth];
		// 전달 공백 채우기
		for ( var i = 0; i < fDay; i++) {
			str += "<td class='no_momth' >"+(lPrevDay-(fDay-i-1))+"</td>";
			c++;
		}
		
		var str_yyyymm = "";
		
		for ( var i = 1; i <= lDay; i++) {
			
			str_month = "";
			str_day = "";
			
			c++;
			str_year = eDate.getFullYear();
			month = eDate.getMonth()+1;

 		  	if ( month < 10) {
			    str_month = "0" + month;
			} else {
				str_month = String(month);
			}
			
 		  	if ( i < 10) {
			    str_day = "0"+ i;
			} else {
				 str_day = String(i);
			}
 		  	
 		  	str_yyyymm = str_year + str_month;
 		  	var str_yyyymmdd = str_year + str_month + str_day;
 		 	var str_yyyymmdd2 = str_year +"-"+ str_month  +"-"+ str_day;
 		  	
			if (c == 7) {
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('tab_day', '" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day_sun'>" + i +  "</div></td>";
				
				if(i!=lDay)
					str += "</tr><tr>";
				
				c = 0;
			} else if (c==6){
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('tab_day', '" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day_sat'>" + i + "</div></td>";

			} else {
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('tab_day', '" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day'>" + i +  "</div></td>";
			}					
		}
		
		var lNextDay = 1;
		// 다음달 공백 채우기
		if (c != 0){ 			
			for (i = c; i < yoil.length; i++) {
				str += "<td class='no_momth' >"+(lNextDay++)+"</td>";
			}		
		}

		str += "</tr>";
		str += "</table>";		
		
		$('#srch_yyyymm').val(str_yyyymm);		
		
		setInitInfoAJAX();
	
		document.getElementById('cal').innerHTML = str;
 		
 		$('#' + str_today_yyyymmdd).removeClass();
 		$('#' + str_today_yyyymmdd).addClass("today");
	
	}
    
	/**
	* 해당월의 개인 일정 유무 데이타를 습득해서 하이라이트 처리 한다.
	*/
	function setInitInfoAJAX() {
		
		$("#tab_mode").val("tab_month");		
		$("#initInfo").html('');	
		
		var frm = document.frm;
		var str_today = getToday_YYYYMMDD("yyyymmdd");

		var options = {   
				url: "<c:url value='/las/cal.do?method=lasCalendarAJAX' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						var html = "";
						$.each(responseText, function(entryIndex, entry) {
							
							var cnt = Number(entry['PLN_SUM'])  + Number(entry['EXE_SUM']) + Number(entry['CONC_SUM']);
							
							if(cnt > 0) {
								var xxxx=  entry['START_DT'];			
								if(xxxx!=str_today){
									$('#' + xxxx).addClass("hasday");
								}								
							}
						});						
					}	 
					viewHiddenProgress(false) ;	
				}
		};		
		$("#frm").ajaxSubmit(options);	
	}	
    
	/**
	 *  SELMS+ CAL POP
	 */
	function goDayTap(flag,day) {
	    var frm = document.frm;  

		$("#tab_mode").val(flag);
		$("#srch_yyyymm").val("");		

		frm.menu_id.value = '20130319160137907_0000000381'; 				
		frm.action = "/las/cal.do";
		
		if (flag == "tab_month") { 		// 월별
			frm.method.value = "lasCalendar";	
		} else if (flag == "tab_day") { // 일별
	 		frm.method.value = "lasCalendarDay";
	 		$("#srch_yyyymmdd2").val(day);	 		
		} else if (flag == "tab_week") { // 주별			
	 		frm.method.value = "lasCalendarWeek";		 		
		}		
		
		frm.target = "PopUpCal";		
		PopUpWindowOpen3('/las/blank.do', '940', '520', true, 'PopUpCal');

		frm.submit();		
	}
	
</script>
<!-- //select 디자인 -->
</head>
<body>
<form name="frm" id="frm" method="post" autocomplete="off">
  	<!-- method -->
  	<input type="hidden" id="method" name="method">
  	<!-- targetMenuId -->
  	<input type="hidden" name="targetMenuId">
  	<input type="hidden" name="target_menu_id">
  	<!-- selected_menu_id -->
  	<input type="hidden" name="selected_menu_id">
  	<input type="hidden" name="selected_menu_nm">
  	<input type="hidden" name="selected_menu_detail_id">
  	<!--상세내역에 띄워줄 URL 정보 -->
  	<input type="hidden" name="set_init_url">
  	<input type="hidden" name="menuLogEnable" value="Y">
  	<input type="hidden" name="chgLangflag">
  	<input type="hidden" name="curPage">
  	<input type="hidden" name="menu_id" value="" />
  	<input type="hidden" name="flag" value=""/>
   	<input type="hidden" id="tab_mode"  name="tab_mode" value=""/>
   	
   	<!-- 달력 키 필드-->
	<input type="hidden" id="srch_yyyymm" name="srch_yyyymm" value="">	
	<input type="hidden" id="srch_yyyymmdd" name="srch_yyyymmdd" value="">
	<input type="hidden" id="srch_yyyymmdd2" name="srch_yyyymmdd2" value="">
</form>
<!-- topMenu -->
<div id="topWrap" >
  <div id="header">
    <div id='util'>
      <div class='mainC1'>
        
          <a href="javascript:Menu.main('frm','L');" class="h1_logo"><IMG SRC="<%=IMAGE%>/common/sys_logo<%= "C60".equals(compCd) || "C10".equals(compCd) ? "2" : "_" + compCd %>.gif"  BORDER="0" alt="<spring:message code="las.page.field.logo.title" />" ></a>
          
          <!-- Privileged Logo : 프랑스 법인에서는 보이지 않게 수정 : 2013.12.06 -->
          <%
          	if(!StringUtil.bvl((String)session.getAttribute("secfw.session.locale"), "en").startsWith("fr")){
		  %>          
          		<span class='pncacc'><img src="<%=IMAGE%>/common/pncacc.gif"  border="0" alt="Privileged and Confidential Attorney-Client Communication" title="Privileged and Confidential Attorney-Client Communication"/></span>
          <%} %>
          <ul class="utility">
            <li>
                <a class="user" title="<%=role_nm_sum%>"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm_en"), "")%></a>
            </li>
            <li>
            	<a href="javascript:Menu.main('frm','L')"><img src="<%=IMAGE%>/icon/icon_home.gif" alt="Home" title="Home"/></a><a href="javascript:Menu.goSiteMap('frm','L');"><img src="<%=IMAGE%>/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a>
            </li>
            <li class="lang_sel">
              <select class="lang_select" name="chgLangflagSel" onChange="javascript:frm.chgLangflag.value=this.value;Menu.main('frm','L');">
                <option value="en" <% if (localeCode.equals("en")) { %>selected<%}%> >English</option>
                <option value="fr" <% if (localeCode.equals("fr")) { %>selected<%}%> >Français</option>
			<!-- 	<option value="de" <% if (localeCode.equals("de")) { %>selected<%}%> >Deutsch</option> -->
              </select>
            </li>
          </ul>
      </div>
    </div>
    <div id='gnbWrap'>
      <div class="gnb mainC2">
        <ul>
<%
    String multi_menu_nm    = "";
    String multi_submenu_nm = "";

    for (int idx=0;idx < menuList.size();idx++ ) {
        ListOrderedMap menu = null;
        menu = (ListOrderedMap)menuList.get(idx);

        if (localeCode.equals("en")) {
            multi_menu_nm = (String) menu.get("menu_nm_eng");
        } else if (localeCode.equals("fr")) {
            multi_menu_nm = (String) menu.get("menu_nm_fra");
        } else if (localeCode.equals("de")) {
            multi_menu_nm = (String) menu.get("menu_nm_deu");
        } else if (localeCode.equals("it")) {
            multi_menu_nm = (String) menu.get("menu_nm_ita");
        } else if (localeCode.equals("es")) {
            multi_menu_nm = (String) menu.get("menu_nm_esp");
        } else {
            multi_menu_nm = (String) menu.get("menu_nm");
        }

        if (Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1) {
%>
                <li  id="menu_<%=menuMax%>" onMouseOver="javascript:subMenuSet(<%=menuMax%>)">
                  <%
                if("TCMS".equals((String) menu.get("menu_nm_eng"))){
                %>
                	<a href="javascript:Menu.gotoTnc();"><%=multi_menu_nm%></a>
                <%}else{ %>
                	<a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=menu.get("menu_id")%>', '_self');"><%=multi_menu_nm%></a>
                <%} %>
                  <ul id="gnb_2depth_0<%=menuMax%>" class="gnb_2depth_0<%=menuMax%> gnb_2depth">
<%
            int subMenuIdx = 1;

            for (int idx2=0; idx2 < menuList.size(); idx2++) {
                ListOrderedMap subMenu = null;
                subMenu = (ListOrderedMap)menuList.get(idx2);

                if (localeCode.equals("en")) {
                    multi_submenu_nm = (String) subMenu.get("menu_nm_eng");
                } else if (localeCode.equals("fr")) {
                    multi_submenu_nm = (String) subMenu.get("menu_nm_fra");
                } else if (localeCode.equals("de")) {
                    multi_submenu_nm = (String) subMenu.get("menu_nm_deu");
                } else if (localeCode.equals("it")) {
                    multi_submenu_nm = (String) subMenu.get("menu_nm_ita");
                } else if (localeCode.equals("es")) {
                    multi_submenu_nm = (String) subMenu.get("menu_nm_esp");
                } else {
                    multi_submenu_nm = (String) subMenu.get("menu_nm");
                }

                if (Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level"))) == 2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))) {
                    if (idx2 == menuList.size()) {
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
            }// end inner for
%>
                  </ul>

                </li>
                 <li class="line"></li>
<%
            menuMax++;
        }
    }// end outer for
%>
        </ul>
        <span class="util_hidden_m"><img src="<%=IMAGE%>/icon/ico_collapse.png" alt="collapse"  title="collapse" onclick="menu_Toggle(this, 'las', 'util', '');" /></span>
      </div>
    </div>
  </div>
</div>
<!-- //topMenu -->
<!-- container -->
<div id="container">
<!---컨텐츠랩 시작--->
<div id='contentWrap'>

		<!---왼쪽컨텐츠 시작----->
		<div id='conleft'>
				<div class="ml12"><img src='<%=IMAGE%>/main/1_new_copy.png'></div>
				
				<!-- 검색엔진작업영역 -->
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
				
				<!-----검색영역----->
				<div class="pt15">
						<div class="sta_wrap">
								<fieldset>
										<div class="srch_box">
												<div class="srch_box_sub">
														<input type="text" title="<spring:message code="clm.page.field.srch.search" />" accesskey="s" class="srch_txt" id="query" name="query" mxlength=100 onkeypress="if(event.keyCode==13) {pageAction();return false;}" value=""/>
														<a href="javascript:pageAction();"><img src="<%=IMAGE%>/main/btn_search.png" alt="<spring:message code="clm.page.field.srch.search" />" /></a>
												</div>
												<a href="javascript:;" onclick="actdetailSearch();" class="detail_search"><img src="<%=IMAGE%>/main/btn_search_detail.png" alt="<spring:message code="clm.page.field.srch.detailSearch" />" /></a>
										</div>
								</fieldset>
						</div>
					
							<!-----// 검색영역----->
							
						<!----상세검색 전체시작----->
						<div class="search_detail" id="searchTarget" style="display: none;">
								<div class="box">
										<h1 class="h1"><img src="<%=IMAGE%>/main/icon_search.png"><spring:message code="clm.page.field.srch.detailSearch" /> <span class="close_s" onclick="javascript:actdetailSearch();" title="close"></span></h1>
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" onclick="actsrh(this)" value="clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion" checked disabled/> <spring:message code="clm.page.field.srch.main.contract" /></label>
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_advice_review,clm_advice_request,clm_advice_ntts" checked disabled/> <spring:message code="clm.page.field.srch.main.advice" /></label>	
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_about" checked disabled/> <spring:message code="clm.page.field.srch.main.about" /></label>	
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_rule_regulation" checked disabled/> <spring:message code="clm.page.field.srch.main.rule" /></label>
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_share_terms" checked disabled/> <spring:message code="clm.page.field.srch.main.share" /></label>
										<label style=""><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_counsel_notice,clm_counsel_faq,clm_counsel_qna" checked disabled/> <spring:message code="clm.page.field.srch.main.counsel" /></label>
								</div>
								<!----> 
							<!--계약상세검색-->
								<div class="search_box" id="detailSearch" style="display: none;">
									<div class="search_box_inner">
										<h1 class="search_box_h1"> <spring:message code="clm.page.field.srch.main.contract" /></h1>
											<table class="search_tb">
												<tr> 
													<td>
														<table class="search_form">
															<colgroup>
																<c:choose>
																	<c:when test="${langCd=='en'}">
																		<col width="150px"/>
																		<col width="250px"/>
																	</c:when>
																	<c:when test="${langCd=='fr'}">
																		<col width="170px"/>
																		<col width="235px"/>
																	</c:when>
																	<c:when test="${langCd=='de'}">
																		<col width="170px"/>
																		<col width="235px"/>
																	</c:when>
																	<c:otherwise>
																		<col width="150px"/>
																		<col width="250px"/>
																	</c:otherwise>
																</c:choose>
															</colgroup>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.contractField" /> </th>
																	<td>
																		<input type="checkbox" name="zsrh" id="zsrh" value="DRETITLE:IF_REQ_TITLE" checked /><label for="option01"><spring:message code="clm.page.field.srch.title" /></label> 
																		<input type="checkbox" name="zsrh" id="zsrh" value="DREFILECONTENT:IF_ORG_FILE_NM" checked /><label for="option02"><spring:message code="clm.page.field.srch.attach" /></label> 
																		<input type="checkbox" name="finfo" id="finfo" onclick="actsrhinfo()" value="" /><label for="option03"><spring:message code="clm.page.field.srch.info" /></label> 
																	</td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.dredate" /> </th>
																	<td><input type="text" name="srch_start_dt" id="srch_start_dt" value="" class="text_search" style="width:72px" readonly /> ~ <input type="text" name="srch_end_dt" id="srch_end_dt" value="" disabled class="text_search" style="width:73px" readonly /></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span>  <spring:message code="clm.page.field.srch.biz_clsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="biz_clsfcn" id="biz_clsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.depth_clsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="depth_clsfcn" id="depth_clsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.cnclsnpurps_bigclsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.cnclsnpurps_midclsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.payment_gbn" /> </th>
																	<td><select class="all" style="width:199px" name="payment_gbn" id="payment_gbn" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "C02001" ><spring:message code="clm.page.field.srch.payment_gbn_value1" /></option>
																			<option value = "C02002" ><spring:message code="clm.page.field.srch.payment_gbn_value2" /></option>
																			<option value = "C02003" ><spring:message code="clm.page.field.srch.payment_gbn_value3" /></option>
																			<option value = "C02004" ><spring:message code="clm.page.field.srch.payment_gbn_value4" /></option>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span>  <spring:message code="clm.page.field.srch.cntrt_cnclsn_yn" /> </th>
																	<td><select class="all" style="width:199px" name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "Y" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value1" /></option>
																			<option value = "N" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value2" /></option>
																			</select></td>
															</tr>
															<tr>
																	<th><span class="iconBox icon019"></span> <spring:message code="clm.page.field.srch.cntrt_status" /> </th>
																	<td><select class="all" style="width:199px" name="cntrt_status" id="cntrt_status" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "C02501" ><spring:message code="clm.page.field.srch.cntrt_status_value1" /></option>
																			<option value = "C02502" ><spring:message code="clm.page.field.srch.cntrt_status_value2" /></option>
																			<option value = "C02503" ><spring:message code="clm.page.field.srch.cntrt_status_value3" /></option>
																			<option value = "C02504" ><spring:message code="clm.page.field.srch.cntrt_status_value4" /></option>
																			<option value = "C02505" ><spring:message code="clm.page.field.srch.cntrt_status_value5" /></option>
																			</select>
																	</td>
															</tr>
																	
														</table>
													</td>
												</tr>
											</table>
											
										</div>
								</div>
								<!--//search--> 
						</div>
						<!----// 상세검색-----> 
				</div>
				</form:form>
				<!-----// 검색영역----->
				
				<div class="trmt"><img src='<%=IMAGE%>/main/1_main_bg_left.png'></div>
		</div>
		
		<!---오른쪽컨텐츠랩 시작--->
		<div id='contrightWrap'> 
				
				<!----오른쪽컨텐츠 시작--->
				<div id='conright'> 
						
						<!---오른쪽컨텐츠 배경박스 시작----->
						<div class='conboxR'>
								<div class='bx_top'><span class='right'></span><span class='left'></span></div>
								<div class='bx_mid' style='height:540px;'>
										<div class='conWrap'> 
												<!----내가할일 시작--->
												<div class='conbox01'>
														<h1><spring:message code="las.page.field.main.todoList"/></h1>
														<div class='work01'>
																<div id='tab01'>
																		<ul class="tab_basic">
																		<c:choose>
														                    <c:when test="${topRole == 'HQ01' || topRole == 'HQ02'}">
														                    <li class="on"><strong><spring:message code="las.page.title.main.tab01"/></strong></li>
														                    </c:when>
														                    <c:otherwise>
														                    <li class="on"><strong><spring:message code="las.page.title.main.tab01"/></strong></li>
																			<li onClick="divShow(tab02),divHidden(tab01),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab02"/></a></li>
																			<li onClick="divShow(tab03),divHidden(tab01),divHidden(tab02),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab06"/></a></li>
																			<li onClick="divShow(tab04),divHidden(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab04"/></a></li>
														                    </c:otherwise>
														              	</c:choose>      
																		</ul>
																		
																		<div class='sum'></div>
																		<table class='tblData'>
															              <colgroup>
																			<col width="27%" />
															                <col width="31%" />
															                <col width="18%" />
															                <col width="24%" />
															              </colgroup>
															            <!-- 검토자의 검토유형 기본세팅 -->
															            <c:set var="solo_yn" value=""/>
															            <c:choose>
															            <c:when test="${sessionScope['secfw.session.user_nm'] eq '검토자(국내)'}">
															            <c:set var="solo_yn" value="1"/>
															            </c:when>
															            <c:when test="${sessionScope['secfw.session.user_nm'] eq '검토자(해외)'}">
															            <c:set var="solo_yn" value="2"/>
															            </c:when>
															            </c:choose>
															            <!--// 검토자의 검토유형 기본세팅 -->
															            <c:choose>
															                <c:when test="${topRole == 'RA01' || topRole == 'RB01'}">
															                <tr>
															                    <th><spring:message code="las.page.field.main.unchosenLong"/></th>
															                    <th><spring:message code="las.page.field.main.inCheck"/></th>
															                    <th><spring:message code="las.page.field.main.tmpSave"/></th>
															                    <th><spring:message code="las.page.field.main.reassign"/></th>
															                </tr>
															                <tr>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04224&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${cntUnassign}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04204&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${cntConsider}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04225&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${cntTempsave}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04206&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${cntResign}"/> </a></td>
															                </tr>
															                </c:when>
															                <c:when test="${topRole == 'RA02'}">
															                <tr>
															                    <th><spring:message code="las.page.field.main.unchosenLong"/></th>
															                    <th><spring:message code="las.page.field.main.inCheck"/></th>
															                    <th><spring:message code="las.page.field.main.tmpSave"/></th>
															                    <th><spring:message code="las.page.field.main.reassign"/></th>
															                </tr>
															                <tr>
															                    <td>-</td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04204&srch_closed_yn=N');"><c:out value="${cntConsider}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04225&srch_closed_yn=N');"><c:out value="${cntTempsave}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04206&srch_closed_yn=N');"><c:out value="${cntResign}"/> </a></td>
															                </tr>
															                </c:when>
															                
															                <c:when test="${topRole == 'HQ01' || topRole == 'HQ02'}">
															                <tr>
															                    <th><spring:message code="las.page.field.main.unchosenLong"/></th><!-- Not Assigned -->
															                    <th><spring:message code="las.page.field.main.inCheck"/></th><!-- Review In Progress -->
															                    <th><spring:message code="las.page.field.main.undecidedLong"/></th><!-- Not Approved -->
															                </tr>
															                <tr>
															                	<c:set var="p_srch_hq_reqman_nm" value="" />
															                	<c:if test="${topRole == 'HQ01'}">
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16202');"><c:out value="${cntUnassign}"/> </a></td>
															                    </c:if>
															                    <c:if test="${topRole == 'HQ02'}">
															                    <c:set var="p_srch_hq_reqman_nm" value="&srch_hq_reqman_nm=${command.session_user_nm}" />
															                    <td>-</td>
															                    </c:if>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16203<c:out value="${p_srch_hq_reqman_nm}"/>');"><c:out value="${cntReviewinProgress}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16204<c:out value="${p_srch_hq_reqman_nm}"/>');"><c:out value="${cntNotapproved}"/> </a></td>
															                </tr>
															                </c:when>
															                
															                <c:otherwise>
															                <tr>
															                    <th><spring:message code="las.page.title.main.review"/></th>
															                    <th><spring:message code="las.page.title.main.consultation"/></th>
															                    <th><spring:message code="las.page.title.main.conclusion"/></th>
															                    <th><spring:message code="las.page.title.main.completion"/></th>
															                </tr>
															                <tr>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02501&closed_yn=N');"><c:out value="${cntCont01}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02502&closed_yn=N');"><c:out value="${cntCont02}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02503&closed_yn=N');"><c:out value="${cntCont03}"/> </a></td>
															                    <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02505&closed_yn=N');"><c:out value="${cntCont05}"/> </a></td>
															                </tr>
															                </c:otherwise>
															            </c:choose>
															            </table>
																</div>
																<div id='tab02' style='display:none'>
														            <ul class="tab_basic">
														              <li onClick="divShow(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab01"/></a></li>
														              <li class="on"><strong><spring:message code="las.page.title.main.tab02"/></strong></li>
														              <li onClick="divShow(tab03),divHidden(tab01),divHidden(tab02),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab06"/></a></li>
														              <li onClick="divShow(tab04),divHidden(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab04"/></a></li>
														            </ul>
														            <div class='sum'></div>
														            <table class='tblData'>
														              <colgroup>
														<% if (localeCode.equals("ko")) { %>
														                <col width="20%" />
														                <col width="20%" />
														                <col width="20%" />
														                <col width="20%" />
														<% } else { %>
														                <col width="27%" />
														                <col width="31%" />
														                <col width="18%" />
														                <col width="24%" />
														<% } %>
														              </colgroup>
														              <tr>
														                <th><spring:message code="las.page.field.main.unchosenLong"/></th>
														                <th><spring:message code="las.page.field.main.inCheck"/></th>
														                <th><spring:message code="las.page.field.main.tmpSave"/></th>
														                <th><spring:message code="las.page.field.main.reassign"/></th>
														              </tr>
														              <tr>
														                <c:choose>
														                    <c:when test="${topRole == 'RA01' || topRole == 'RB01'}">
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V01&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${lawCntNoassign}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${lawCntReview}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${lawCntTemp}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03&srch_solo_yn=<c:out value="${solo_yn }"/>&srch_closed_yn=N');"><c:out value="${lawCntResign}"/> </a></td>
														                    </c:when>
														                    <c:when test="${topRole == 'RA02' }">
														                        <td>-</td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02');"><c:out value="${lawCntReview}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09');"><c:out value="${lawCntTemp}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03');"><c:out value="${lawCntResign}"/> </a></td>
														                    </c:when>
														                    <c:otherwise>
														                        <td>-</td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500453_0000000370', '/las/lawconsulting/lawConsult.do?method=listLawConsultRequest&isForeign=H&srch_prgrs_status=S02');"><c:out value="${lawCntReg}"/> </a></td>
														                        <td>-</td>
														                        <td>-</td>
														                    </c:otherwise>
														                </c:choose>
														              </tr>
														            </table>
														        </div>
																
																<div id='tab03' style='display:none'>
														            <ul class="tab_basic">
														              <li onClick="divShow(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab01"/></a></li>
														              <li onClick="divShow(tab02),divHidden(tab01),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab02"/></a></li>
														              <li class="on"><strong><spring:message code="las.page.title.main.tab06"/></strong></li>
														              <li onClick="divShow(tab04),divHidden(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab04"/></a></li>
														            </ul>
														            <div class='sum'></div>
														            <table class='tblData'>
														              <tr>
														                <th><spring:message code="las.page.title.main.tab06"/></th>
														              </tr>
														              <tr>
														              	<td><c:out value="${countAutoExtension}"/> </td>
														              </tr>
														            </table>
														          </div>
														          <div id='tab04' style='display:none'>
														            <ul class="tab_basic">
														              <li onClick="divShow(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab01"/></a></li>
														              <li onClick="divShow(tab02),divHidden(tab01),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab02"/></a></li>
														              <li onClick="divShow(tab03),divHidden(tab01),divHidden(tab02),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab06"/></a></li>
														              <li class="on"><strong><spring:message code="las.page.title.main.tab04"/></strong></li>
														            </ul>
														            <div class='sum'></div>
														            <table class='tblData'>
														              <colgroup>
														                <col width="32%" />
														                <col width="36%" />
														                <col width="32%" />
														              </colgroup>
														              <tr>
														                <th><spring:message code="las.page.title.main.orgRcv"/></th>
														                <th><spring:message code="las.page.title.main.autoExp"/></th>
														                <th><spring:message code="las.page.title.main.aftconclReg"/></th>
														              </tr>
														              <tr>
														                <c:choose>
														                    <c:when test="${isCntMng == 'Y'}">
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319155330501_0000000362', '20130319155330501_0000000362', '/clm/manage/myApproval.do?method=listMyApproval&list_mode=cnsdreq&isOrgMgr=N');"><c:out value="${cntContMngOrgRcv}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319155330749_0000000363', '20130319155330749_0000000363', '/clm/manage/completion.do?method=listAutoRenewApproval');"><c:out value="${cntContMngAutoExp}"/> </a></td>
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130319155330773_0000000364', '20130319155330773_0000000364', '/clm/manage/registration.do?method=listRegistrationApproval');"><c:out value="${cntContMngAfterConReg}"/> </a></td>
														                    </c:when>
														                    <c:otherwise>
														                        <td>-</td>
														                        <td>-</td>
														                        <td>-</td>
														                    </c:otherwise>
														                </c:choose>
														              </tr>
														            </table>
														          </div>
														          <div id='tab05' style='display:none'>
														            <ul class="tab_basic">
														              <li onClick="divShow(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab01"/></a></li>
														              <li onClick="divShow(tab02),divHidden(tab01),divHidden(tab03),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab02"/></a></li>
														              <li onClick="divShow(tab03),divHidden(tab01),divHidden(tab02),divHidden(tab04),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab06"/></a></li>
														              <li onClick="divShow(tab04),divHidden(tab01),divHidden(tab02),divHidden(tab03),divHidden(tab05)"><a><spring:message code="las.page.title.main.tab04"/></a></li>
														              <li class="on"><strong><spring:message code="las.page.title.main.tab05"/></strong></li>
														            </ul>
														            <table class='tblData'>
														              <tr>
														                <th><spring:message code="las.page.title.main.tab05"/></th>
														              </tr>
														              <tr>
														                <c:choose>
														                    <c:when test="${isCntSealorSign == 'Y'}">
														                        <td><a href="javascript:Menu.detail3('frm', '_top', '20130418141001246_0000000444', '20130418141339964_0000000446', '/clm/sign/signManage.do?method=listSignMng&forwardFrom=MAIN');"><c:out value="${cntSealSign}"/> </a></td>
														                    </c:when>
														                    <c:otherwise>
														                        <td>-</td>
														                    </c:otherwise>
														                  </c:choose>
														
														              </tr>
														
														            </table>
														        </div>
														        <!-- '-'표시는 해당사항 없음을 의미합니다. -->
																<div class='alert'><span><spring:message code="las.page.title.main.countAlert"/></span></div>
														</div>
														<!----------------구분선 시작------------------->
														<div class='line'></div>
														<!----------------구분선 마침------------------->
												</div>
												
												<!----// 내가할일 마침---> 
												<!----공지사항 시작--->
												<div class='conbox02'>
											        <h1><spring:message code="las.page.title.board.Notice"/></h1>
											        <button class='more' title='more' alt='more' onclick="javascript:Menu.go('frm', '/secfw/main.do', 'forwardMainFrame', '20130319160701794_0000000390', '_self');"></button>
											        <table class="list_basic_notice">
														<colgroup>
														<col style="width:80%">
														<col style="width:20%">
														</colgroup>
														
											<%
											    for (int idx=0; idx<lasBoardList.size(); idx++) {
											        ListOrderedMap boardList = null;
											        boardList = (ListOrderedMap)lasBoardList.get(idx);
											%>
											          	<tr>
											          	    <td>
											          	        <span class="iconBox icon019"></span>
														          <%if ("NEW".equals(boardList.get("new_yn"))) { %><span class="iconBox icon020"></span><% } %>
														          <a onClick="javascript:getBoardDetail('<%=boardList.get("seqno")%>');" title="<%=(String)boardList.get("title") %>">
														              <%if (localeCode.equals("ko")){
														                    out.print(StringUtil.convertHtmlTochars((String)boardList.get("title")));
														                }else{
														                    out.print(StringUtil.convertHtmlTochars((String)boardList.get("title_en")));
														                }%>
														          </a>
														      </td>
											          	      <td class='date'><%=(String)boardList.get("reg_dt")%></td>
											          	 </tr>
											<%
											    }// end for
											%>
											        	
											        </table>	
											        <!----------------구분선 시작------------------->
													<div class='line'></div>
													<!----------------구분선 마침------------------->
											    </div>
											    <!----// 공지사항---> 
												
												<!----서비스데스크 시작--->
												<div class='conbox03'>
											        <!---배너시작---->
													<div class='voc'>
															<ul>
																	<li style='padding-top:6px;'><span class="voc_listT" onclick="javascript:openItvoc();">Register IT-VOC</span></li>
																	
															</ul>
													</div>
													<!---// 배너마침----> 
											    </div>
												<!----// 서비스데스크 마침---> 
												
												<div class="cal_area"> 
                                                        
                                                <!--------전체날짜 시작 -------->
                                                <div class="date_cal_mini">
                                                        <a href="javascript:chgCal('PREV')"><img src="<%=IMAGE%>/cal/month_prev.gif" width="18" height="17"></a>                                                                    
                                                            <span id='cal_title'></span> 
                                                        <a href="javascript:chgCal('NEXT')"><img src="<%=IMAGE%>/cal/month_next.gif" width="18" height="17"> </a> 
                                                </div>
                                                <div class="cal_divider"></div>
                                                <!--------- //전체날짜 마침------->
                                                <div id='cal'>
                                                        <table class="form_cal_mini">
                                                                <tr>
                                                                        <th class="sun">Sun</th>
                                                                        <th>Mon</th>
                                                                        <th>Tue</th>
                                                                        <th>Wed</th>
                                                                        <th>Thu</th>
                                                                        <th>Fri</th>
                                                                        <th class="sat">Sat</th>
                                                                </tr>
                                                        </table>
                                                </div>
                                        </div>
                                        <!-----// 캘린더 마침------>
                                                
										</div>
								</div>
								<div class='bx_btm'><span class='right'></span><span class='left'></span></div>
						</div>
						<!---//오른쪽컨텐츠배경박스 마침-----> 
				</div>
				<!----// 오른쪽컨텐츠 마침---> 
		</div>
		<!---// 컨텐츠랩 마침---> 
		
</div>
<!---// 전체컨테이너 마침---> 
<!----푸터시작---->
<div id="footer">
		<address>
		<div class='fL copy'>ⓒ SAMSUNG</div>
		<div class='fR privacy' style="display: none;"> <spring:message code="las.main.field.individual.policy"/><!-- Privacy Policy --></div>
		</address>
</div>
<!----// 푸터마침---->		
 
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />

<!-- 2014-09-03 Kevin added -->
<!-- 레이어드 notification 창 -->
<style>
div.growlUI { background: url(../../../../images/secfw/common/confirm2.png) no-repeat 10px 10px }
div.growlUI h2 {
    color: white; padding: 5px 5px 5px 75px; text-align: left
}
</style>
<div class="growlUI" style="display: none;">
    <h2><spring:message code="las.main.notification.notserved" /></h2>
    <%-- <h4><spring:message code="las.main.notification.notserved.number" arguments="<c:out value='${cntNotServed}'/>" /></h4> --%>
    <h2>: <c:out value='${cntNotServed}'/> case(s)</h2>
</div>
<script>
    openPop();
</script>

<!-- 세션을 유지하기 위한 iframe하나를 숨겨돈다. iframe안에서 페이지를 5분마다 refresh 한다 -->
<iframe name='iframeTemp' src="<c:url value='/las/pageReload.do' />" frameborder='0' style='width:0px;height:0px'></iframe>
</body>
</html>
