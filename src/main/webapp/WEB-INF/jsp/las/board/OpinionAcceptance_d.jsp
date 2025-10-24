<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.board.dto.OpinionAcceptanceForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : OpinionAcceptance_d.jsp
 * 프로그램명 : 법무공지, 법원송달문서공지, 시스템FAQ - 상세
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2011.08.18
 */
 --%>
<%
	OpinionAcceptanceForm command = (OpinionAcceptanceForm)request.getAttribute("command");
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	$(document).ready(function() {		
		// alert("<c:out value='${command.session_user_id}' />");
				
		initPage(); //첨부파일창 load
		
		// 답변 목록 접기 / 펴기 이벤트 바인드
	    var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
 		var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
	    $('img[alt$=show]').toggle(function(){
			$(this).removeAttr().attr("src",expandIcon);
			$(this).parent().parent().parent().next('#tr_show').attr("style", "display:");		
		}, function(){
			$(this).removeAttr().attr("src",collapseIcon);
			$(this).parent().parent().parent().next('#tr_show').attr("style", "display:none");
		});
	    
	});

	/**
	* 첨부 파일 초기화
	*/
	function initPage() {
		frm.target = "fileList";
		frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardClmsFilePage";
		frm.submit();
	}
	
	/**
	* 목록 으로 가기
	*/
	function goList() {
		var frm = document.frm;		
		frm.method.value = "listOpinionAcceptance";
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	/**
	* 발송 처리
	*/
	function Send() {
		var frm = document.frm;

		if (confirm("<spring:message code='las.msg.field.opnion.alert001' />")) {  // 타법인 참여 대상자에게 의견참여 inform을 발송 하시겠습니까? 발송 후에는 inform 내용에 대한 수정이 불가 합니다.
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
			frm.method.value = "sendRequestReplyOpinion";
			viewHiddenProgress(true);
			frm.submit();
		}
	}

	/**
	* 답변 처리
	*/
	function goReply() {
		var frm = document.frm;
		frm.insert_kbn.value = "REPLY";
		frm.title.value = "<c:out value='${lom.title}' />";
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.method.value = "forwardInsertReplyOpinionAcceptance";
		viewHiddenProgress(true);
		frm.submit();
	}

	/**
	* 수정 처리
	*/
	function goModify() {
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.method.value = "forwardModifyOpinionAcceptance";		
		viewHiddenProgress(true);
		frm.submit();
	}
	
	/**
	* 답변 수정 처리
	*/
	function goModifyReply(re_seqno) {
		var frm = document.frm;
		frm.re_seqno.value  = re_seqno;			
		frm.method.value = "forwardModifyOpinionAcceptanceReply";
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();		
	}	

	/**
	* 삭제처리
	*/
	function goRemove(){
		var frm = document.frm;
		if (confirm("<spring:message code='secfw.msg.ask.delete' />")) {
			frm.method.value = "deleteOpinionAcceptance";		
			frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
			frm.target = "_self";
			viewHiddenProgress(true);
			frm.submit();
		}
	}	
	
	/**
	* 답변 삭제처리
	*/
	function goRemoveReply(re_seqno) {
		var frm = document.frm;
		if (confirm("<spring:message code='secfw.msg.ask.delete' />")) {			
			frm.re_seqno.value  = re_seqno;			
			frm.method.value = "deleteOpinionAcceptanceReply";
			frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
			frm.target = "_self";
			viewHiddenProgress(true);
			frm.submit();
		}
	}	
	
	/**
	* +버튼 클릭시 텍스트 내용을 보여준다.
	*/
	function attachNamoView(contId){		
		document.getElementById(contId).contentWindow.document.body.innerHTML = document.getElementById(contId+'_textarea').value;	
	}
	
	/**
	* +버튼 클릭시 첨부파일을 보여준다.
	*/
	function attachFile(obj, re_seqno, idx, contId) {

		var attachLoading = new Array() ;
		
		// 펼쳐질때만 첨부파일을 가져온다.
		if(obj.src.indexOf("minus")>0) {
			return ;
		}

		//검토이력 나모 내용을 보여준다.
 		if(contId != ""){
			setTimeout("attachNamoView('"+contId+"')", 1000*0.5);
		} 
		
		var frm = document.frm;
		var fileFieldObj = null ;
		
		if(attachLoading[idx]== "Y") {
			return ;
		}		
			
		fileFieldObj = $('input[name=fileInfos_'+ idx +']') ;
		
		$fileInfos = $(fileFieldObj).attr("name");
        $fileList = $(fileFieldObj).prev().attr("name");
        $cntrt_cnsdreq_id = $(fileFieldObj).next().val();
        
        frm.target          = $fileList;
        frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";
        frm.fileInfoName.value = $fileInfos;
        frm.fileFrameName.value = $fileList;
        frm.view_gbn.value = "download";

        frm.file_bigclsfcn.value = "F007";
        frm.file_midclsfcn.value = "REPLY";
        frm.file_smlclsfcn.value = "";
        frm.ref_key.value = re_seqno;
        getClmsFilePageCheck('frm');
		
		attachLoading[idx] = "Y" ;		
	}

	/**
	* 메세지 처리
	*/
	function showMessage(msg) {
		if (msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
			
<div id="wrap">
<div id="container">

<!-- Location -->
<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
<!-- //Location --> 

<!-- Title -->
<div class="title">
		<h3>Request for Comments on other Subsidiary</h3>
</div>
<!-- //Title -->
<div id="content">
<div id="content_in"> 
		
<form:form name="frm" id="frm" method="post" autocomplete="off">	
<input type="hidden" name="method" 		value="" />
<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />

			<!-- key form -->
			<input type="hidden" name="insert_kbn" value="<c:out value='${command.insert_kbn}'/>" />
			<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="re_seqno" value="" />
			<input type="hidden" name="title" value="<c:out value='${lom.title}'/>" />

			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F007" />
			<input type="hidden" name="file_midclsfcn" 	value="ACC" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 	value="download" />
			
			<input type="hidden" name="cont" id="cont" 	value="<c:out value='${lom.cont}'/>" />
			
			<!-- 답변 첨부파일정보 -->
			<input type="hidden" name="fileInfos" id="fileInfos" 	value="" />
			<input type="hidden" name="fileInfoName"	value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
			<input type="hidden" name="fileFrameName"   value="" /> <!-- 첨부파일 화면 iFrame 명 -->
			
			
			<div class="btnwrap">
					<c:if test="${lom.reg_id eq command.session_user_id}">						
						
						<c:if test="${lom.STATUS eq 'SAVED'}">
							<span class="btn_all_w" onclick="javascript:Send()" ><span class="mail"></span><a><spring:message code="secfw.page.button.send" /><!-- Send --></a></span>
						</c:if>
						
						<c:if test="${lom.STATUS ne 'SENT'}">
							<span class="btn_all_w" onclick="javascript:goModify()" ><span class="modify"></span><a><spring:message code="secfw.page.button.modify" /><!-- Modify --></a></span>
							<span class="btn_all_w" onclick="javascript:goRemove()" ><span class="delete"></span><a><spring:message code="secfw.page.button.delete" /><!-- Delete --></a></span>
						</c:if>											
						
					</c:if>
					
					<c:if test="${lom.STATUS eq 'SENT'}">	
						<span class="btn_all_w" onclick="javascript:goReply()" ><span class="reply"></span><a><spring:message code="secfw.page.button.reply" /><!-- Reply --></a></span>
					</c:if>
					
					<span class="btn_all_w" onclick="javascript:goList()" ><span class="list"></span><a><spring:message code="secfw.page.button.list" /><!-- List --></a></span>
				</div>
			
				<!--list detail--->
				<table class="list_basic">
						<colgroup>
						<col width="15%" />
						<col width="85%" />
						</colgroup>
						<tbody>
						<tr>
								<th><spring:message code="secfw.page.field.common.title" /><!--  Title--></th>
								<td><c:out value='${lom.title}' escapeXml="false" /></td>
						</tr>
						<tr>
								<th><spring:message code="secfw.page.field.bbs.writer" /><!--  Writer--></th>
								<td>
									<c:out value='${lom.reg_nm}' default="" /> / <c:out value="${lom.REG_JIGYUP_NM}"  /> / <c:out value="${lom.REG_DEPT_NM}"  />
								</td>
						</tr>
						<tr>
								<th><spring:message code="secfw.page.field.log.period" /><!--  Date--></th>
								<td>
									<c:out value='${lom.reg_dt}' default="" />
								</td>
						<tr>
								<th><spring:message code="clm.page.field.admin.subject.detail" /><!--  Deatils--></th>
								<td>
									<c:out value='${lom.cont}' escapeXml="false" />
								</td>
						</tr>
						<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
								<!-- Sungwoo Replaced scroll option 2014/08/04 -->
								<td><iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto"  allowTransparency="true"  ></iframe></td>
						</tr>
						</tbody>
				</table>
				<!-----// list detail----->
				
				<!---userlist--->
				<table class="list_basic" style='border-top:0'>
						<colgroup>
						<col width="15%" />
						<col width="85%" />
						</colgroup>												
						<tr>
						<th class="rightline"><spring:message code="las.msg.field.opnion.filed002" /><!--  Participant--> [<c:out value="${rowspan}" />] </th>
						<td>
							<c:forEach var="userList" items="${userList}">	
							
								<c:choose>
									<c:when test="${'Y' eq userList.REPLY_YN}">
										<!-- <img id="prev_mon"  src="/images/las/en/icon/icon_answer_y.gif" class='img_align'/> -->
										[O]
									</c:when>
									<c:otherwise>
										<!-- <img id="prev_mon"  src="/images/las/en/icon/icon_answer_n.gif" class='img_align'/> -->
										[X]
									</c:otherwise>
								</c:choose>
								<c:out value="${userList.COMP_NM}" /> / <c:out value="${userList.USER_NM}" /> / <c:out value="${userList.JIKGUP_NM}"  /> / <c:out value="${userList.DEPT_NM}" /> <BR>
							</c:forEach>
						</td>
						</tr>						
				</table> 
					
				<!-- reply list -->
				<table class="list_basic_new mt20">
					<colgroup>
						<col width="60%" /> <!-- width size 변경 신성우 2014-04-24 -->
						<col width="30%" />
						<col width="10%" />
					</colgroup>
					<tr>
						<th><spring:message code="secfw.page.field.common.title" /><!--  Title--></th>
						<th><spring:message code="secfw.page.field.bbs.writer" /><!--  Writer--></th>
						<th><spring:message code="secfw.page.field.log.period" /><!--  Date--></th>
					</tr>
						<c:choose>
							<c:when test="${!empty underList}">
								<c:forEach var="list" items="${underList}" varStatus="x">
									<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
										<td class="tL overflow" title="<c:out value='${list.title}' escapeXml="false"/>" >
											<span class="">
												<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" onclick='attachFile(this, "<c:out value="${list.re_seqno}"  />" , "<c:out value="${x.index}"  />" , "<c:out value="${x.index}"  />")'/>
											</span>					
											&nbsp;&nbsp;<c:out value='${list.title}' escapeXml="false"/></td>
							            <td class="overflow" title="<c:out value='${list.reg_nm}' default="" /> / <c:out value="${list.REG_JIGYUP_NM}"  /> / <c:out value="${list.REG_DEPT_NM}"  />" >
							            	<c:out value='${list.reg_nm}' default="" /> / <c:out value="${list.REG_JIGYUP_NM}"  /> / <c:out value="${list.REG_DEPT_NM}"  />
							            </td>
							            <td class="tC"><c:out value='${list.reg_dt}'/></td>
									</tr>
									<tr class="Nocol" id="tr_show" style="display:none;">
										<td class="nopadding" colspan="3" style='padding:6px 1px 6px 0'>																					
											<table class="table-style_sub02">
												<colgroup>											
													<col width="15%" />
													<col width="85%" />
												</colgroup>
												<tr class="Nocol">										
													<th ><spring:message code="clm.page.field.admin.subject.detail" /><!--  Deatils--></th>
													<td>
														<textarea id="<c:out value="${x.index}"  />_textarea"  name="<c:out value="${x.index}"  />_textarea"  style="display: none;"><c:out value="${list.CONT}" escapeXml="false"  /></textarea>
														<iframe id="<c:out value="${x.index}"  />" name="<c:out value="${x.index}"  />" src="<c:url value='/clm/blank.do' />"  frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
													</td>
												</tr>
												
												<tr class="Nocol">
													<th><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
													<td>
														<!-- Sungwoo Replaced scroll option 2014/08/04 -->
														<iframe src="<c:url value='/clm/blank.do' />" id="fileList_<c:out value="${x.index}"  />" name="fileList_<c:out value="${x.index}"  />" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true" ></iframe>
														<input type="hidden" name="fileInfos_<c:out value="${x.index}"  />"  value="" />
														<input type="hidden" name="cntrt_cnsdreq_id<c:out value="${x.index}"  />"  value="<c:out value="${list.re_seqno}"  />" />
													</td>
												</tr>
												
												<!-- 수정 권한이 있는 경우-->
												<c:if test="${list.reg_id eq command.session_user_id}">
													<tr class="Nocol">										
														<td class="tR" colspan="2">
															<span class="btn_all_b" onclick="javascript:goModifyReply('<c:out value="${list.re_seqno}"  />')" ><span class="modify"></span><a>Modify</a></span>
															<span class="btn_all_b" onclick="javascript:goRemoveReply('<c:out value="${list.re_seqno}"  />')" ><span class="delete"></span><a>Delete</a></span>
														</td>
													</tr>
												</c:if>
												<!-- // 수정 권한이 있는 경우 -->
												
												</table>
										</td>
									</tr>
									
								</c:forEach> 
				    	</c:when>
				    	<c:otherwise>
						<tr>
							<td colspan="3" align="center"><spring:message code="las.msg.succ.noResult" /></td>
						</tr>
				    	</c:otherwise>
					</c:choose>						
				</table>
				<!-- //list -->

				<div class="btnwrap mt10">
					<c:if test="${lom.reg_id eq command.session_user_id}">						
						
						<c:if test="${lom.STATUS eq 'SAVED'}">
							<span class="btn_all_w" onclick="javascript:Send()" ><span class="mail"></span><a><spring:message code="secfw.page.button.send" /><!-- Send --></a></span>
						</c:if>
						
						<c:if test="${lom.STATUS ne 'SENT'}">
							<span class="btn_all_w" onclick="javascript:goModify()" ><span class="modify"></span><a><spring:message code="secfw.page.button.modify" /><!-- Modify --></a></span>
							<span class="btn_all_w" onclick="javascript:goRemove()" ><span class="delete"></span><a><spring:message code="secfw.page.button.delete" /><!-- Delete --></a></span>
						</c:if>					
						

					</c:if>
					
					<c:if test="${lom.STATUS eq 'SENT'}">	
						<span class="btn_all_w" onclick="javascript:goReply()" ><span class="reply"></span><a><spring:message code="secfw.page.button.reply" /><!-- Reply --></a></span>
					</c:if>
					
					<span class="btn_all_w" onclick="javascript:goList()" ><span class="list"></span><a><spring:message code="secfw.page.button.list" /><!-- List --></a></span>
				</div>
						
				</form:form>
			
			</div>
		</div>
		<!-- //content -->			
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</div>
<!-- footer  --> 
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script> 
<!-- // footer --> 
</body>
</html>