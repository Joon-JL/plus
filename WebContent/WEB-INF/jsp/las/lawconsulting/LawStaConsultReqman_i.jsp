<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>
<%--
/**
 * 파  일  명 : LawStaConsultReqman_i.jsp
 * 프로그램명 : 표준계약서 의뢰 
 * 설      명 : 표준계약서 의뢰만을 위한 페이지 입니다.
 * 작  성  자 : 
 * 작  성  일 : 2013.04
 */
--%>

<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">

	function initPage(){
		
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.multiYn.value = "N";
		frm.preAllowFileList.value = "DOC,DOCX,";
		frm.submit();
		
		// 표준계약서 제목에 세팅하기
		
		//if(frm.check_prgrs_status.value != 'resend' && frm.hstry_no.value == 0){
		//	frm.title.value = "[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>]" + frm.title.value;
		//}
		
		//alert(document.frm.body_mime.value);
		//alert('<c:out value="${command.check_prgrs_status}"/>');
		
		//var reTest2 = /\[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>\]/g;
		//var title = frm.title.value;

		//if(reTest2.test(title) == false){
		//	frm.title.value = "[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>]" + frm.title.value;
		//}
	}
	
	/*자문유형(상담종류) 팝업*/
	function popup(){
		var f = document.frm ;
		f.method.value = "forwardPopupLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		PopUpWindowOpen('', 700, 380, true);
		frm.target = "PopUpWindow";
		f.submit() ;
	}

	function cancel() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function tempSave(){
		var f = document.frm;
		f.check_prgrs_status.value = "tempSaveReqman";
		insert();
	}
	
	function send(){
		var f = document.frm;
		if(f.check_prgrs_status.value != 'resend' && f.hstry_no.value == 0){
			f.check_prgrs_status.value = "send";
		}
		insert();
	}

	function insert() {
		var f = document.frm;
		if (validateForm(document.frm)) {
			if (f.hstry_no.value == 0) {
				if (f.check_prgrs_status.value == "tempSaveReqman") {
					var confirmMessage = "<spring:message code='secfw.msg.ask.tempSave' />";
				} else {
					var confirmMessage = f.cnslt_no.value == "" ? "<spring:message code='secfw.msg.ask.insert' />"
							: "<spring:message code='secfw.msg.ask.update' />";
				}
			} else {
				var confirmMessage = f.check_prgrs_status.value == "tempSaveReqman" ? "<spring:message code='secfw.msg.ask.tempSave' />"
						: "<spring:message code='secfw.msg.ask.insert' />";
			}
			if (frm.consult_type.value == "") {
				alert("<spring:message code='las.page.field.lawconsulting.selectConsultType'/>");
				return;
			}
			
			/*
			var title = frm.title.value;
			var type = frm.consult_type.value;
			
			var reTest = new RegExp("A20");
			var reTest2 = /\[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>\]/g;
			var title = frm.title.value;
			var split_title = title.split("[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>]");

			//선택한 자문유형 중 표준계약서가 있으면 제목앞에 [표준계약서]를 표기한다
			if(type.match(reTest)){
				if(reTest2.test(title) == false){
					frm.title.value = "[<spring:message code='las.page.field.lawconsulting.stndFormReq'/>]" + frm.title.value;
				}
			}
			//표준계약서가 없을 시
			else{
				//제목에 [표준계약서] 라는 단어가 들어있다면 제거한다
				if(reTest2.test(title)){
					frm.title.value = split_title[1];
				}
			}
			*/

			fileList
					.UploadFile(function(uploadCount) {
						//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
						if (uploadCount == -1) {
							initPage(); //첨부파일창 Reload
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return;
						}
						
						if(uploadCount == 0){
			            	 alert("<spring:message code='las.page.field.lawconsulting.formNeedAttchFile'/>");
			            	 return;
			             }
						
						if (confirm(confirmMessage)) {
							//if(f.hstry_no.value == 0){
							if ('<c:out value="${command.prgrs_status}"/>' == 'S05'
									|| '<c:out value="${command.prgrs_status}"/>' == 'S03') {
								f.method.value = "insertStaLawConsult";
							} else
								f.method.value = f.cnslt_no.value == "" ? "insertStaLawConsult"
										: "modifyStaLawConsult";
							//}
							//else {
							//	 f.method.value = "insertLawConsult";
							//}
							f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />";
							f.target = "_self";
							
							viewHiddenProgress(true);
							f.submit();
						}

					});

		}
	}
</script>
</head>

<body onload="initPage();" autocomplete="off">


<div id ="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
        <div class="location">
            <img src="../../images/las/ko/icon/ico_home.gif" border="0" alt="Home"><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
    
    <div class="title">
			<h3><spring:message code="las.page.field.lawconsulting.stndFormReq"/></h3>
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
	<input type="hidden" name="consult_type" value="A20"/>
	<input type="hidden" name="check_prgrs_status" value="<c:out value='${command.check_prgrs_status}'/>"/>
	<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	
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
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="file_bigclsfcn" 	value="F003" />
	<input type="hidden" name="file_midclsfcn" 	value="F00301" />
	<input type="hidden" name="file_smlclsfcn" 	value="" />
	<input type="hidden" name="ref_key" 	value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="multiYn"         value="" /> 		<!-- 멀티여부 -->
	<input type="hidden" name="preAllowFileList"         value="" />
	
	<input type="hidden" id="isStdCont" name="isStdCont" value="Y"/>	
	
	<!-- 작성완료 후 페이지 포워딩 구분을 위한 페이지 구분정보 -->
	<c:choose>
		<c:when test="${command.fwd_gbn != null}">
			<input type="hidden" name="fwd_gbn" value="<c:out value='${command.fwd_gbn}'/>" />
		</c:when>
		<c:otherwise>
			<input type="hidden" name="fwd_gbn" value="req"/>
		</c:otherwise>
	</c:choose>
		
	<c:choose>
		<c:when test="${command.cnslt_no == '' || command.check_prgrs_status == 'resend'}">
			<input type="hidden" name="view_gbn" 	value="upload" />
			
		</c:when>
		<c:otherwise>
			<input type="hidden" name="view_gbn" 	value="modify" />
			
		</c:otherwise>
	</c:choose>
	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
	
	
	<!-- title -->
	<!-- upper button -->
      <div class="t_titBtn">
        <div class="btn_wrap">
          <c:choose>
            <c:when test="${command.top_role != 'etc'}">
              <c:choose>
           	  <c:when test="${otherModify == 'Y'}">
           	  	<span class="btn_all_w"><span class="modify"></span><a href="javascript:send();"><spring:message code="las.page.button.modify"/></a></span>
              </c:when>
	          <c:otherwise>
	          	<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.field.lawconsulting.stndFormReq"/></a></span>
	                  <span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
	          </c:otherwise>
	          </c:choose>
            </c:when>
            <c:otherwise>
              <c:choose>
                <c:when test="${command.cnslt_no == '' || command.prgrs_status == 'S02' || command.prgrs_status == 'S05' || command.prgrs_status == 'S03'}">
                  <span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.field.lawconsulting.stndFormReq"/></a></span>
                  <span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
                </c:when>
                <c:otherwise>
                  <c:choose>
                    <c:when test="${command.prgrs_status == 'S03'}"><span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span></c:when>
                    <c:otherwise><span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span></c:otherwise>
                  </c:choose>
                </c:otherwise>
              </c:choose>
            </c:otherwise>
          </c:choose>
          <span class="btn_all_w"><span class="list"></span><a href="javascript:cancel();"><spring:message code="las.page.button.lawconsult.list" /></a></span>
        </div>
      </div>
      <!-- //upper button -->
	<!-- //title -->
 
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%"/>
		        <col width="35%" />
		        <col width="15%" />
		        <col width="35%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><font color="FF0000">*</font> <spring:message code="las.page.field.lawconsult.title" /></th>
					<td colspan="3">
						<input id="title" name="title" type="text" value="<c:out value='${command.title}' escapeXml='false'/>" class="text_full" alt="<spring:message code="las.page.field.lawconsulting.title"/>" required="*" maxLength="128"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
					<td><c:out value='${command.reg_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.department" /></th>
					<td><c:out value='${command.reg_dept_nm}'/></td>
				</tr>
				<!-- 표준계약서만 선택이 가능하기 때문에 주석 처리 함.
				<tr>
					<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
					<td colspan="3">
						<input id="consult_type_name" name="consult_type_name" type="text" value="<c:out value='${command.consult_type_name}' escapeXml='false'/>" class="text_search" readonly="readonly" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:popup();}"/><img src="<%//=IMAGE%>/icon/ico_search.gif" onclick="javascript:popup();" class="cp" alt="search"/>
					</td>
				</tr>
				 -->
				<tr>
					<th><spring:message code="clm.page.msg.common.content" /></th>
					<td colspan="3">
						<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						<c:choose>
							<c:when test="${command.cont!=null}"> 
									<textarea name="body_mime" id="body_mime" rows="10" cols="15" onkeyup="frmChkLen(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"><c:out value='${command.cont}'/></textarea>
							</c:when>
							<c:otherwise>
									<textarea name="body_mime" id="body_mime" rows="10" cols="15" onkeyup="frmChkLen(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"></textarea>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.attachfile" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX&#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="3">
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		
		<br/>
	<div align="center">
		 <c:choose>
        <c:when test="${command.top_role != 'etc'}">
           <c:choose>
           	  <c:when test="${otherModify == 'Y'}">
           	  	<span class="btn_all_w"><span class="modify"></span><a href="javascript:send();"><spring:message code="las.page.button.modify"/></a></span>
              </c:when>
	          <c:otherwise>
	          	<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.field.lawconsulting.stndFormReq"/></a></span>
	                  <span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
	          </c:otherwise>
	          </c:choose>
        </c:when>
        <c:otherwise>
          <c:choose>
            <c:when test="${command.cnslt_no == '' || command.prgrs_status == 'S02' || command.prgrs_status == 'S05' || command.prgrs_status == 'S03'}">
              <span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.field.lawconsulting.stndFormReq"/></a></span>
              <span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
            </c:when>
            <c:otherwise>
              <c:choose>
                <c:when test="${command.prgrs_status == 'S03'}"><span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span></c:when>
                <c:otherwise><span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span></c:otherwise>
              </c:choose>
            </c:otherwise>
          </c:choose>
        </c:otherwise>
      </c:choose>
      <span class="btn_all_w"><span class="list"></span><a href="javascript:cancel();"><spring:message code="las.page.button.lawconsult.list" /></a></span>
    </div>
		<!-- //view -->
		</form:form>
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

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>

</html>
