<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.Date" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%--
/**
 * 파  일  명 : MainLawInfoList_i.jsp
 * 프로그램명 : 주요 / 법무 사례 등록
 * 설      명 : 
 * 작  성  자 :  
 * 작  성  일 : 2011.08
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">
        
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;

		//저장
		if(flag == "insert"){
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		    frm.method.value = "insertMainLawInfo";
		    
		    //나모웹에디터 관련
// 		    frm.body_mime.value = frm.wec.MIMEValue;
			
		    
		    // Validation check
		    if(frm.title.value == ""){
				alert("<spring:message code='las.msg.alert.titleIsReq'/>");	
			}else if("<c:out value='${command.info_gbn}' />"=="C00405" && (frm.trgt_nation.value=="" || frm.law_gbn.value == "")){
				alert("<spring:message code='las.msg.alert.isReqfieldAndCountry'/>");
			}else if("<c:out value='${command.info_gbn}' />"=="C00406" && (frm.dmstfrgn_gbn.value=="" || frm.law_gbn.value == "")){
				alert("<spring:message code='las.msg.alert.isReqClassfication'/>");
		    }else{
		    	if(confirm("<spring:message code='las.msg.ask.insert'/>")){

	                fileList.UploadFile(function(uploadCount){
	                    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	                    if(uploadCount == -1){
	                        initPage(); //첨부파일창 Reload
	                        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	                        return;
	                    }
		    			
		    			//첨부파일이 필수인 경우: 사용자 선택사항
		    			//if(uploadCount==0){
		    			//	alert("<spring:message code='secfw.msg.error.fileNon' />");
		    			//	return;
		    			//}

						viewHiddenProgress(true) ;
				    	frm.submit();	
	                });
		    	}
					    	  
		    }
			
		} else if(flag == "cancel"){	
			
			if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){

				frm.target = "_self";
				frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
			    frm.method.value = "listMainLawInfo";

				viewHiddenProgress(true) ;
				frm.submit();
			}
		}
	}
	
	function selectOptions(select){
		var frm = document.frm;
		
		var gbnValue;
		gbnValue = select.options[select.selectedIndex].value;
		
		if (gbnValue=="F"){
			frm.trgt_nation.style.visibility = "visible";
		} else {
			frm.trgt_nation.style.visibility = "hidden";
		}
	}
	
	$(document).ready(function(){
		//alert("TEST");
		//분류 세팅
	    getCodeSelectByUpCd("frm", "law_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd="+"<c:out value='${command.info_gbn}'/>"+"&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.law_gbn}'/>");
		getCodeSelectByUpCd("frm", "trgt_nation", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=L2&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.trgt_nation}'/>");
		getCodeSelectByUpCd("frm", "dmstfrgn_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C002&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.dmstfrgn_gbn}'/>");
		
		//첨부파일창 load
		initPage();
			
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
	});
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}


//-->
</script>

</head>
<body>

<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		
		<!-- title -->
		<div class="title">
			<c:choose>
				<c:when test="${command.info_gbn=='C00405'}">
			   		<h3><spring:message code="las.page.title.lawinformation.mayorLegTrends" /></h3>
				</c:when>
				<c:when test="${command.info_gbn=='C00406'}">
					<h3><spring:message code="las.page.title.lawinformation.mayorLagCases" /></h3>
				</c:when>
			</c:choose>
		</div>
		<!-- //title -->
			
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
			<input type="hidden" name="srch_combo" value="<c:out value='${command.srch_combo}'/>" />
			<input type="hidden" name="srch_name" value="<c:out value='${command.srch_name}'/>" />
			<input type="hidden" name="srch_mainlawexam" value="<c:out value='${command.srch_mainlawexam}'/>" />
			<input type="hidden" name="srch_dmstfrgn_gbn" value="<c:out value='${command.srch_dmstfrgn_gbn}'/>" />
			<input type="hidden" name="srch_nation" value="<c:out value='${command.srch_nation}'/>" />
			<!-- key form-->
			<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />
			<!-- 나모 웹 에디터 관련 -->
<!-- 			<input type="hidden" name="body_mime" id="body_mime" value="" /> -->
<!-- 			<input type="hidden" name="cont" id="cont" value="" /> -->
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F004" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 	value="upload" />
			<!-- // **************************** Form Setting **************************** -->
			
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code='las.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code='las.page.button.cancel' /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
			<c:choose>
				<c:when test="${command.info_gbn=='C00405' }">
					<col width="12%" />
					<col width="21%" />
					<col width="12%" />
					<col width="21%" />
					<col width="12%" />
					<col width="22%" />
				</c:when>
				<c:otherwise>
					<col width="15%" />
					<col width="85%" />
				</c:otherwise>
			</c:choose>
				</colgroup>
				<tbody>
					<c:choose>
						<c:when test="${command.info_gbn=='C00405' }">
							<tr>
								<th><spring:message code="las.page.field.board.notice.title" /></th>
					            <td colspan="3">
					            	<input type="text" name="title" id="title" class="text_full" value="" maxLength="64" alt="<spring:message code="las.page.field.contract.library.title" />" required="required"/>
					            </td>
					            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
					            <td><c:out value="${command.session_user_nm}"/></td>
				        	</tr>
				        	<tr>
					            <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>
					            <td><%=DateUtil.formatDate(DateUtil.today(), "-") %></td>
					            <th><spring:message code="las.page.field.lawinformation.classfy" /></th>
					            <td width='150'>
					            	<select name="law_gbn" id="law_gbn" alt="<spring:message code="las.page.field.lawinformation.classfy" />" required>
									</select>    
					            </td>
					          	<th><spring:message code='las.page.field.lawinformation.country' /></th> 
							    <td width='150'>
							    	<select name="trgt_nation" id="trgt_nation" alt="<spring:message code='las.page.field.lawinformation.country' />" required>
									</select>      
								</td>  
				        	</tr>			
						</c:when>
						<c:when test="${command.info_gbn=='C00406' }">
							<tr>
								<th><spring:message code="las.page.field.lawinformation.law_gbn"/></th>
					            <td colspan="5">
					            	<select name="law_gbn" id="law_gbn" alt="<spring:message code="las.page.field.lawinformation.law_gbn" />" style="width:180px" required>
									</select>
					            	<select name=dmstfrgn_gbn id="dmstfrgn_gbn" style="width:80px" required onChange="javascript:selectOptions(this)">
									</select>    
								    <select name="trgt_nation" id="trgt_nation" style="visibility:hidden" alt="<spring:message code='las.page.field.lawinformation.country' />">
									</select>
								</td>  
							</tr>
							<tr>
								<th><spring:message code="las.page.field.board.notice.title" /></th>
					            <td colspan="5">
					            	<input type="text" name="title" id="title" class="text_full" value="" maxLength="128" alt="<spring:message code="las.page.field.contract.library.title" />" required/>
					            </td>
				        	</tr>
				        	<tr>
					            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
					            <td colspan="5">
					            	<c:out value="${command.session_user_nm}"/>
					            </td>
				        	</tr>			
						</c:when>
					</c:choose>
					<tr>
			            <th><spring:message code="clm.page.msg.common.content"/></th>
						<td colspan="5">
							<span id="cont">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						    <textarea id="cont" name="cont" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'cont')" class="text_area_full"></textarea>
							<%//@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
			            <th><spring:message code="las.page.field.board.notice.attach_file"/> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
						<td colspan="5">
							<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
						</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!--  Function Button  -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code='las.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code='las.page.button.cancel' /></a></span>
			</div>
			<!-- //  Function Button  -->
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
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
</SCRIPT>
</html>
