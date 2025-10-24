<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<% 
    // 메뉴네비게이션 스트링
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>

<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript">
    function init(){
		
        alertMessage('<c:out value='${command.return_message}'/>') ;
    }

    function send(){
        var f = document.frm;
        var confirmMessage = "";
        
        if(f.isGrpmgr.value == 'Y'){
            confirmMessage = "<spring:message code='clm.field.signmng.alertmsg22' />" ;
        }
        else{
            confirmMessage = "<spring:message code='secfw.msg.ask.hold' />" ;
        }

        if(validateForm(document.frm)) {
            if(confirm(confirmMessage)){

                if(f.check_prgrs_status.value == 'referGrpmgr'){
                    f.method.value = "modifyStatusLawConsult" ;
                }else{
                    f.method.value = "insertLawConsult" ;
                }
				
                viewHiddenProgress(true) ;
                f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
                f.target = "_self" ;
                f.submit() ;
            }
        }
    }

    function goList() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.method.value = "listLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onLoad="init()" autocomplete="off">

<div id="wrap">
  <!-- container -->
  <div id="container">
    <!-- Location -->
    <div class="location">
      <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
    <!-- //Location -->
    <div class="title">
      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /></h3> */ --%>
      <c:choose>
        <c:when test="${command.isGrpmgr == 'Y'}">
          <h3><spring:message code="las.page.field.lawconsulting.lawReqAdvRjtIns" /></h3>
        </c:when>
        <c:otherwise>
          <h3><spring:message code="las.page.field.lawconsulting.lawReqAdvHoldIns" /></h3>
        </c:otherwise>
      </c:choose>
    </div>
    <!-- content -->
    <div id="content">
    <div id="content_in">
    <form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" value="<c:out value='${command.cnslt_no}'/>"/>
	<input type="hidden" name="hstry_no" value="<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="consult_type" value="<c:out value='${command.consult_type}'/>"/>
	<input type="hidden" name="prgrs_status" value=""/>
	<input type="hidden" name="check_prgrs_status" value="<c:out value='${command.check_prgrs_status}'/>"/>
	<input type="hidden" name="isGrpmgr" value="<c:out value='${command.isGrpmgr}'/>"/>
	<input type="hidden" name="if_key_no" value="<c:out value='${lom.if_key_no}'/>"/>
	<input type="hidden" name="if_flag" value="<c:out value='${lom.if_flag}'/>"/>
	<input type="hidden" name="reg_dt" value="<c:out value='${lom.reg_dt}'/>"/>
	<input type="hidden" name="req_reply_dt" value="<c:out value='${lom.req_reply_dt}'/>"/>
	<!-- 재의뢰시 내용(contents) 유지  -->
	<input type="hidden" name="body_mime" value="<c:out value='${lom.cont}'/>"/>
	
	<!-- 검색 정보 -->
	<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"/>
	<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>"/>
	<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
	<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>"/>
	<input type="hidden" name="srch_cont" value="<c:out value='${command.srch_cont}'/>"/>
	<input type="hidden" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}'/>"/>
	<input type="hidden" name="srch_reg_dept" value="<c:out value='${command.srch_reg_dept}'/>"/>
	<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
	<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>"/>
	<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
	<input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/>
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	
	<input type="hidden" id="isStdCont" name="isStdCont" value="N"/>
	
      <!-- title -->
      <div class="t_titBtn">
        <div class="btn_wrap">
          <span class="btn_all_w"><span class="check"></span><a href="javascript:send()"><spring:message code="las.page.button.lawconsult.send" /></a></span>
          <span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
        </div>
      </div>
      <!-- //title -->
      <!-- view -->
      <table border="0" cellspacing="0" cellpadding="0" class="table-style01">
        <colgroup>
          <col width="15%"/>
          <col width="85%"/>
        </colgroup>
        <tbody>
          <tr>
            <th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
            <td><c:out value='${lom.prgrs_status_name}'/></td>
          </tr>
		  <c:choose>
            <c:when test="${command.isGrpmgr == 'Y'}">
              <tr class="end">
                <th><spring:message code="las.page.field.lawconsult.rejctcause" /></th>
                <td>
                <span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen800" /><br>
                <textarea name="rejct_cause" id="rejct_cause" rows="16" onkeyup="frmChkLenLang(this,800,'curByteExpl_body_mine','<%=langCd%>')" class="text_area all" alt="<spring:message code="las.page.field.lawconsulting.rejectRs"/>"></textarea></td>
              </tr>
            </c:when>
            <c:otherwise>
              <tr class="end">
                <th><spring:message code="las.page.field.lawconsult.holdcause" /></th>
                <td>
                <span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen800" /><br>
                <textarea name="hold_cause" id="hold_cause" rows="16" onkeyup="frmChkLenLang(this,800,'curByteExpl_body_mine','<%=langCd%>')" class="text_area all" alt="<spring:message code="las.page.field.lawconsulting.pstpnRs"/>"></textarea></td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
      <!-- //view -->
    </div>
    </div>
    <!-- //content -->
    
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  </div>
  <!-- //container -->
</div>
<!-- footer  -->
    <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
    <!-- // footer -->
</form:form>
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>