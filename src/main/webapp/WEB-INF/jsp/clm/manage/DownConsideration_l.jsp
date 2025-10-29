<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sec.clm.manage.dto.ManageForm" %>
<%@ page import="java.util.HashMap" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%-- 
/**
 * 파  일  명 : DownConsideration_l.jsp
 * 프로그램명 : 의뢰현황 다운로드 페이지
 * 설      명 : 
 * 작  성  자 : 김형준
 * 작  성  일 : 2012.03.13
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ManageForm command = (ManageForm)request.getAttribute("command");
	
	//허가된 사람 이외에는 사업부 선택을 못하게 막는다.
	boolean blngtCdViewFlag  = blngtCdViewFlag(session);
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		initCal("srch_start_reqday");   //첫번재 의뢰일 설정
	    initCal("srch_end_reqday");     //두번재 의뢰일 설정
<%
	if(blngtCdViewFlag){ //모두 view
%>
		getCodeSelectByUpCd("frm", "srch_resp_dept", '/common/clmsCom.do?method=getUnitOrgnzComboHTML&up_orgnz_cd=B00000004&combo_abbr=F&combo_type=S&locale=H&combo_selected=');
<%		
	}else{ // disable 시킨다.
%>
		getCodeSelectByUpCd("frm", "srch_resp_dept_tmp", '/common/clmsCom.do?method=getUnitOrgnzComboHTML&up_orgnz_cd=B00000004&combo_abbr=F&combo_type=S&locale=H&combo_selected='+'<c:out value='${command.session_blngt_orgnz}'/>');
		document.frm.srch_resp_dept_tmp.disabled = true;
		document.frm.srch_resp_dept.value = '<c:out value='${command.session_blngt_orgnz}'/>';
<%		
	}
%>	    
	});
	
	
	//엑셀다운
	function excelDownLoad(){
		var frm = document.frm;
		
		if(frm.srch_start_reqday.value == ''){
			alert("<spring:message code="clm.page.msg.manage.announce128" />");
			return;
		}
		if(frm.srch_end_reqday.value == ''){
			alert("<spring:message code="clm.page.msg.manage.announce129" />");
			return;
		}
		
		//날짜계산
		var sd = frm.srch_start_reqday.value; //start Day
	    var ed = frm.srch_end_reqday.value; //end Day
		sd = sd.split("-");
		ed = ed.split("-");
		var st = new Date(sd[0],sd[1]-1,sd[2]).getTime();
		var et = new Date(ed[0],ed[1]-1,ed[2]).getTime();
		var fromto = (et-st)/(1000*60*60*24)+1;

		if(fromto > 60){
			alert("<spring:message code="clm.page.msg.manage.announce180" />");
			return;			
		}
		
<%
		if(blngtCdViewFlag){ //모두 view
%>		
			if(frm.srch_resp_dept.value == ''){
				alert("<spring:message code="clm.page.msg.manage.announce159" />");
				return;
			}
<%		
		}
%>
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
	    frm.method.value = "considerationExcel";
	    frm.submit();
	}
</script>
</head>

<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />

<%	
	if(!blngtCdViewFlag){
%>
	<input type="hidden" name="srch_resp_dept" id="srch_resp_dept" value=""/>
<%		
	}	
%>

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title"><h3><spring:message code="clm.page.msg.manage.reqNow" /> Download</h3></div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			
			<!-- search-->		    
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="25%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="35%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="srch" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" style="width:100%"/>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" style="width:80px" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
										<td>
											<input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar02 srch"/>
											~
	  										<input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02 srch"/>
										</td>
									</tr>
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration.orgnz" /></th>
										<td colspan="5">
											<%
												if(blngtCdViewFlag){ //모두 view
											%>
											<select name="srch_resp_dept" id="srch_resp_dept"  style="width:250px" />
											</select>											
											<%
													
												}else{
											%>
											<select name="srch_resp_dept_tmp" id="srch_resp_dept_tmp"  style="width:250px" />
											</select>											
											<%		
												}
											%>											
										</td>
									</tr>
									
								</table>
							</td>
							<td class="vb tC">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn"><span class="excel_down"></span><a href="javascript:excelDownLoad()"> <spring:message code='las.page.button.excelDownload' /></a></span>
				</div>
		  	</div>
			
	
			<!-- list -->
			<!-- //list -->
		</div>
		<!-- //content -->
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>
<%!
//백도어 view 여부
public boolean blngtCdViewFlag(HttpSession session) throws Exception{
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String userId = (String)session.getAttribute("secfw.session.user_id"); //UserId

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
    	if(userRoleList.contains("RA00")){
			result = "A";
		}
	}
    
  //시스템 관리자이거나 아래 사람들만 spot 이 보임
    if("A".equals(result) || (
    		"R020218102320C101152".equals(userId) || //심우규(SDS)
    		"D080228100555C602146".equals(userId) || //한지연(SDS)
    		"D050120225841C101032".equals(userId) || //서유강
    		"R020218102326C100366".equals(userId) || //임현승
    		"M030702144020C108208".equals(userId) || //이기원
    		"M050707002047C104463".equals(userId) || //박수진
    		"D091230221722C100767".equals(userId) || //박태준
    		"D060705193616C103463".equals(userId) || //이효은
    		"R020218102340C104121".equals(userId) || //최윤식
    		"R020218102336C109809".equals(userId) //김일환
    )){
    	retV = true;
    }
    
    return retV;
}
 %>