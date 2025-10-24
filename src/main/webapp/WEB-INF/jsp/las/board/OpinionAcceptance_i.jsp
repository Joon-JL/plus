<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : OpinionAcceptance_i.jsp
 * 프로그램명 : 타법인 의견수렴 등록 / 수정 / 답변 / 답변 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2013.11
 */
--%>
<%
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>OpnionAcceptance</title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js' />"></script>

<script language="javascript">

$(document).ready(function(){
	
	// alert("<c:out value='${command.insert_kbn}' />");
	
	initPage();
});
	
/**
* 첨부파일 초기화
*/
function initPage(){
	frm.target = "fileList";
    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value = "forwardClmsFilePage";
	frm.submit();
}
	
/**
* 저장
*/
function save() {
	var frm = document.frm ;
	
	if(frm.title.value == "") {
		var msgVal = "<spring:message code='secfw.page.field.common.title' />";		
		alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
		return;
	}	
	
	if(frm.cont.value == "") {		
		var msgVal = "<spring:message code='clm.page.field.admin.subject.detail' />";		
		alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
		return;
	}	
	
	if (confirm("<spring:message code='secfw.msg.ask.save' />")) {

			fileList
	        .UploadFile(function(uploadCount) {
	            //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	            if (uploadCount == -1) {
	                initPage(); //첨부파일창 Reload
	                alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	                return;
	            }
	
	            //  의견 수렴등록 / 수정 일 경우에만 활성화
				<c:if test="${command.insert_kbn=='INSERT' || command.insert_kbn=='MODIFY'}">
	
	            $('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});
	            
	            var rightDocLen = frm.receivers.options.length;
	            
	            var tot = "";
	        	
	    		var tempEpid = "" ;
	    		var tempSingleId = "" ;
	    		var tempEmail = "";
	    		var tempName = "";
	    		var tempDeptNm = "";
	    		var tempJikgupNm = "";
	    		var tempCompNm = "";
	/*     		var tempTelNo = "";
	    		var tempMobile =""; */
	
	    		var tempValue_array = new Array();
	    		var tempValue_array2 = new Array();
	    		var tempValue_array3 = new Array();
	    		var tempValue_array4 = new Array();
	    		var tempValue_array5 = new Array();
	    		var tempValue_array6 = new Array();
	    		var tempValue_array7 = new Array();
	/*     		var tempValue_array8 = new Array();
	    		var tempValue_array9 = new Array(); */
	            
	    		var receiverCnt = 0;
	    		
	    		for ( i = 0; i < rightDocLen; i++)
	    		{	
	    			tot = (frm.receivers.options[i].value).split(":");
	    			
	    			tempEpid  = tot[0];
	    			tempSingleId  = tot[1];
	    			tempEmail = tot[2];
					tempName =  tot[3];
					tempDeptNm = tot[4];
					tempJikgupNm = tot[5];
					tempCompNm = tot[6];
	/* 				tempTelNo = tot[7];
					tempMobile = tot[8];			 */	
					
			    	tempValue_array[i] = tempEpid;
			    	tempValue_array2[i] = tempSingleId;
			    	tempValue_array3[i] = tempEmail;
			    	tempValue_array4[i] = tempName;
			    	tempValue_array5[i] = tempDeptNm;
			    	tempValue_array6[i] = tempJikgupNm;
			    	tempValue_array7[i] = tempCompNm;
	/* 		    	tempValue_array8[i] = tempTelNo;
			    	tempValue_array9[i] = tempMobile; */
	    			
	    		}
	    		
	    		frm.userEpid.value = tempValue_array;
	        	frm.userSingleId.value = tempValue_array2;
	        	frm.userEmail.value = tempValue_array3;
	        	frm.userName.value = tempValue_array4;
	        	frm.userDeptNm.value = tempValue_array5;
	        	frm.userJikupNm.value = tempValue_array6;
	        	frm.userCompNm.value = tempValue_array7;
	/*         	frm.userTelNo.value = tempValue_array8;
	        	frm.userMobile.value = tempValue_array9; */
	    		
	        	/***** Participant List End *****/
	        	</c:if>
	        	
	        	<c:choose>
	        		<c:when test="${command.insert_kbn=='INSERT'}">
	        			frm.method.value = "insertOpinionAcceptance";
					</c:when>
	        		<c:when test="${command.insert_kbn=='MODIFY'}">
		    			frm.method.value = "modifyOpinionAcceptance";
					</c:when>
					<c:when test="${command.insert_kbn=='REPLY'}">
						frm.method.value = "insertReplyOpinionAcceptance";
					</c:when>
					<c:when test="${command.insert_kbn=='REPLY_MODIFY'}">
						frm.method.value = "modifyReplyOpinionAcceptance";
					</c:when>
				</c:choose>            
				
				frm.action = "<c:url value='/las/board/opinionAcceptance.do' />" ;
				frm.target = "_self";			
				
			//	alert(tempValue_array);
			//	alert(tempValue_array6);
			//	return;
			
				// alert("frm.method.value::" + frm.method.value);
				
				viewHiddenProgress(true);
				frm.submit() ;             
	        });
		}
	
	}

	/**
	* 창닫기 -
	*/
	function cancel() {
		
		var f = document.frm ;		
		
		//  INSERT 시 목록 화면 그외 상세 화면으로 이동
		<c:choose>
	    	<c:when test="${command.insert_kbn=='INSERT'}">
	    		frm.method.value = "listOpinionAcceptance" ;
			</c:when>
	    	<c:when test="${command.insert_kbn=='MODIFY' || command.insert_kbn=='REPLY'  || command.insert_kbn=='REPLY_MODIFY' }">
	    		frm.method.value = "detailOpinionAcceptance" ;
			</c:when>
		</c:choose>
		
		viewHiddenProgress(true) ;		
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />" ;
		frm.target = "_self" ;
		frm.submit() ; 
	}

	/**
	* 임직원 조회 function
	*/	
	function searchEmployee()
	{
		var frm = document.frm;
		
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";
		frm.multiChk.value = "N";
		frm.doSearch.value = "N";
		PopUpWindowOpen('', 800, 450, true, "false");
		frm.submit();
	}	

	/**
	* 임직원정보 Setting
	*/	
	function setUserInfos(userList) {
			
		var epid     = userList.epid;
		var singleId = userList.userid;
		var email   = userList.mail;
		var name     = userList.cn;
		var deptNm   = userList.department;
		var jikgupNm = userList.title;
		var compNm   = userList.o;
		var telNo    = userList.telephonenumber;
		var mobile   = userList.mobile;

		var flag = true;
		
		//기존 추가된 사용자 인지 체크
		$('#receivers option')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(singleId) >= 0) {
				flag = false;
			}
		});
	
		if(flag){
			var html = "<option value='" + epid + ":" + singleId + ":" + email + ":" + name + ":" + deptNm + ":" + jikgupNm + ":" + compNm + ":" + telNo + ":" + mobile + "'>"
	         + singleId + " / " + name + " / " + deptNm + "(" + jikgupNm + ")" + " / " + compNm 
	         //+ " / " + telNo + " / " + mobile
	         + "</option>";
	
			$('#receivers').append(html);
			$("#member_cnt").html(countOption());
		}		
	}		

	/**
	* 삭제 버튼 클릭 시 참가자 리스트에서 선택 참가자 삭제
	*/
	function delOption() {
		
		var sel_cnt = 0;
		var msg_sel = "<spring:message code='secfw.page.field.user.selectUser'/>"; // 사용자를 선택해 주십시오
		var msg_del = "<spring:message code='secfw.msg.ask.cpAlert12'/>"; // 선택한 건을 삭제하시겠습니까?
		
        $("#receivers option:selected").each(function (index){
        	sel_cnt++;
		});          

		if(sel_cnt==0){
			alert(msg_sel);
			return;
		} else {
			if(confirm(msg_del)){
				$('#receivers option:selected').remove();
			}	
		}
		
		$("#member_cnt").html(countOption());
	}
	
	/**
	* 참가자 수 option 카운드
	*/
	function countOption() {
		
		var sel_cnt = 0;
		
        $("#receivers option").each(function (index){
        	sel_cnt++;
		}); 
        
        return sel_cnt;        
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- 이중등록 방지 -->
<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><img src="<%=IMAGE %>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
		<h3>Request for Comments on other Subsidiary</h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<form:form name="frm" id="frm" method="post" autocomplete="off">	
		<input type="hidden" name="method" 		value="" />
		<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />
		
		<input type="hidden" name="insert_kbn" value="<c:out value='${command.insert_kbn}'/>" />
		<input type="hidden" name="seqno"      value="<c:out value='${command.seqno}'/>" />
		<input type="hidden" name="re_seqno"      value="<c:out value='${command.re_seqno}'/>" />
		
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos" 	value="" />
		<input type="hidden" name="file_bigclsfcn" 	value="F007" />		
		
		<c:choose>
        	<c:when test="${command.insert_kbn=='INSERT' || command.insert_kbn=='MODIFY'}">
        		<input type="hidden" name="file_midclsfcn" 	value="ACC" />
			</c:when>
			<c:when test="${command.insert_kbn=='REPLY' || command.insert_kbn=='REPLY_MODIFY' }">
				<input type="hidden" name="file_midclsfcn" 	value="REPLY" />
			</c:when>
		</c:choose>     		
		
		<input type="hidden" name="file_smlclsfcn" 	value="" />
		
		<c:choose>
        	<c:when test="${command.insert_kbn=='INSERT' || command.insert_kbn=='MODIFY'}">
        		<input type="hidden" name="ref_key" 	value="<c:out value='${lom.seqno}'/>"/>
			</c:when>
			<c:when test="${command.insert_kbn=='REPLY' || command.insert_kbn=='REPLY_MODIFY' }">
				<input type="hidden" name="ref_key" 	value="<c:out value='${lom.re_seqno}'/>"/>
			</c:when>
		</c:choose> 
		
		<c:choose>
        	<c:when test="${command.insert_kbn=='INSERT' || command.insert_kbn=='REPLY'}">
        		<input type="hidden" name="view_gbn" 	value="upload" />
			</c:when>
			<c:when test="${command.insert_kbn=='MODIFY' || command.insert_kbn=='REPLY_MODIFY' }">
				<input type="hidden" name="view_gbn" 	value="modify" />
			</c:when>
		</c:choose>    		
		<!-- // 첨부파일정보 -->		
		
		<!-- Participant 정보 -->
			<input type="hidden" name="userEpid" />
			<input type="hidden" name="userSingleId" />
			<input type="hidden" name="userEmail" />
			<input type="hidden" name="userName" />
			<input type="hidden" name="userDeptNm" />
			<input type="hidden" name="userJikupNm" />
			<input type="hidden" name="userCompNm" />
			<input type="hidden" name="userTelNo" />
			<input type="hidden" name="userMobile" />
			
			<!-- 사용자검색 -->
			<input type="hidden" name="receiver_nm" value="" />
			<input type="hidden" name="multiChk" value="" />
			<input type="hidden" name="srch_user_cntnt" value="" />
			<input type="hidden" name="srch_user_cntnt_type" value="user_nm" />
			<input type="hidden" name="doSearch" value="" />
				
				<div class="t_titBtn">
				<div class="btn_wrap">
				
					<c:choose>
		        		<c:when test="${command.insert_kbn=='INSERT'}">
		        			<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='secfw.page.field.approval.draft' /> <spring:message code='las.page.button.save' /></a></span>
						</c:when>
		        		<c:when test="${command.insert_kbn=='MODIFY'}">
			    			<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='secfw.page.field.approval.draft' /> <spring:message code='las.page.button.save' /></a></span>
						</c:when>
						<c:when test="${command.insert_kbn=='REPLY'}">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
						</c:when>
						<c:when test="${command.insert_kbn=='REPLY_MODIFY'}">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
						</c:when>
					</c:choose> 

					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span>
				</div>
			</div>
				<!--list detail--->
				<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
						<colgroup>
						<col width="15%" />
						<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
						<col width="10%" />
						<col width="40%" />
						</colgroup>
						<tbody>
						<tr>
								<th><spring:message code="secfw.page.field.common.title" /><!--  Title--></th>
								<td colspan='3'><input type="text" name="title" id="title" class='text w_99' maxlength="100" value="<c:out value='${lom.title}'/>" /></td>
						</tr>
						<tr>
								<th><spring:message code="secfw.page.field.bbs.writer" /><!--  Writer--></th>
								<td><c:out value='${command.session_user_nm}'/> / <c:out value='${command.session_dept_nm}'/> / <c:out value='${command.session_jikgup_nm}'/></td>
								<th><spring:message code="secfw.page.field.log.period" /><!--  Date--></th>
								<td><%=DateUtil.formatDate(DateUtil.today(), "-") %></td>
						</tr>
						<tr>
								<th><spring:message code="clm.page.field.admin.subject.detail" /><!--  Deatils--></th>
								<td colspan='3'>
								<span id="cont1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
								<textarea class="text_area w_99 h_120" name="cont" id="cont" rows="10" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'cont1','<%=langCd%>')" ><c:out value='${lom.cont}'/></textarea></td>
						</tr>
						<tr>
								<th><spring:message code="las.page.field.bbs.file" /><!--  Attachments--> <img src="<%=IMAGE%>/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
								<td colspan="3">
		            		<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
		            		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList"  frameborder="0" class='addfile_iframe' scrolling="auto" ></iframe>
		            		</td>
						</tr>
						
						<!-- 의견 수렴등록 일 경우에만 활성화  -->
						<c:if test="${command.insert_kbn=='INSERT' || command.insert_kbn=='MODIFY'}">
							<tr>
								<th rowspan="2" class="rightline" ><spring:message code="las.msg.field.opnion.filed002" /><!--  Participant--> [<span id="member_cnt"><c:out value="${rowspan}" /></span>] </th>
								<td colspan='3' style="border-bottom:1px solid #ffffff ;"><!-- search -->
								
								<select id="receivers" name="receivers" size="6" multiple="multiple" class='text' style='width:100%; height:200px;' >
										<c:if test="${!empty member_list}">
											<c:forEach var="list" items="${member_list}" varStatus="x">
												<option value=" <c:out value='${list.USER_ID}'/>:<c:out value='${list.SINGLE_ID}'/>:<c:out value='${list.EMAIL}'/>:<c:out value='${list.USER_NM}'/>:<c:out value='${list.DEPT_NM}'/>:<c:out value='${list.JIKGUP_NM}'/>:<c:out value='${list.COMP_NM}'/>">
													<c:out value='${list.COMP_NM}'/> / <c:out value='${list.SINGLE_ID}'/>	/ <c:out value='${list.USER_NM}'/> / <c:out value='${list.DEPT_NM}'/>(<c:out value='${list.JIKGUP_NM}'/>) 
								          		</option>
								         	</c:forEach>
								         </c:if>
								</select>
								</td>
							</tr>
							<tr>
								<td colspan='3' class="tR" >
									<span class="btn_all_b" onclick="javascript:searchEmployee();" ><span class="iconBox icon011"></span><a><spring:message code="secfw.page.field.user.selectL" /><!-- Select --></a></span>
									<span class="btn_all_b" onclick="javascript:delOption()" ><span class="delete"></span><a><spring:message code="secfw.page.button.delete" /><!-- Delete --></a></span>
								</td>
							</tr>
						</c:if>
						<!-- // 의견 수렴등록 일 경우에만 활성화  -->

						</tbody>
				</table>
				
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					
					<c:choose>
		        		<c:when test="${command.insert_kbn=='INSERT'}">
		        			<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='secfw.page.field.approval.draft' /> <spring:message code='las.page.button.save' /></a></span>
						</c:when>
		        		<c:when test="${command.insert_kbn=='MODIFY'}">
			    			<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='secfw.page.field.approval.draft' /> <spring:message code='las.page.button.save' /></a></span>
						</c:when>
						<c:when test="${command.insert_kbn=='REPLY'}">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
						</c:when>
						<c:when test="${command.insert_kbn=='REPLY_MODIFY'}">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
						</c:when>
					</c:choose> 
							
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span>
				</div>
			</div>
			<!-- // Function Button  -->

			</form:form>			
			</div>
			<!-- //content_in -->
				
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>