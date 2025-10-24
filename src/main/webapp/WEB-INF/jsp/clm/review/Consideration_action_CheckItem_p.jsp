<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Consideration_action_CheckItem_p.jsp
 * 프로그램명 : 최종본 검토 시 필수항목 체크 후 사유 입력 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.05.12
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta sci="Consideration_action_CheckItem_p.jsp" />
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>
<script language="javascript">

 var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.

 // 필수 항목 체크 시 N을 선택하게 될 경우 입력 각 항목에 맞는 사유를 적을 수 있는 창을 활성화 시킨다.
 function changeText(itemCd,itemNo){
	 
	 var frm = document.frm;
	 
	 var ch_Click = $("input[name=checkItemYN_"+itemNo+"]:checked").val();
	 var check_value_yn;
	 var check_value;
	 
	 
	 // No를 선택하게 되면 사유를 입력하는 textarea를 활성화 시켜 준다.
	 // Yes를 다시 선택하게 되면, 사유 입력한 내용은 지워지고, 입력할수 있는 textarea를 닫아 버린다.
	 
	 if("Y" == ch_Click){
		 
		 $("#tr_"+itemNo).hide();
		 document.getElementsByName("tA_"+itemNo)[0].value = "";
		 
	 } else {
		 $("#tr_"+itemNo).show(); 
	 }
	 
 }
 
 // 선택한 내용을 가지고 저장을 하게 된다.
 function save(){
	 
	 var frm = document.frm;
	 var ch_Click;
	 var check_value_yn;
	 var check_value;
	 var txt_area_value = "";
	 var txt_key = "";
	 var sum_txt = "";
	 var con_text = "";
	 
	 for(var z=1; z <= <c:out value='${insertResultListSize }'/>;z++){
		 
		// Yes, No인지 판단
		ch_Click = $("input[name=checkItemYN_"+z+"]:checked").val();
		
		if("N" == ch_Click){
			
			txt_key += document.getElementsByName("tA_key_"+z)[0].value+"^";
			txt_area_value += document.getElementsByName("tA_"+z)[0].value+"^";
			
			con_text = txt_area_value.replace("^", "");
			
			if("" == con_text){
				alert("<spring:message code="las.page.field.hqrequest.page" />");
				return;
			}

		} else {
			
		}
		
		if(undefined == ch_Click){
			alert("<spring:message code="las.page.field.hqrequest.page01" /> "+ z +" <spring:message code="las.page.field.hqrequest.page02" />");
			return;
		}
		
	 }
	 
	 sum_txt = txt_key+"||"+txt_area_value;
	 
	 frm.sum_text.value = sum_txt;
	 
	 var options = {   
				url: "<c:url value='/clm/review/consideration.do?method=insertCheckReson' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						var html = "";
						$.each(responseText, function(entryIndex, entry) {
							
							var return_val = entry['return_val'];
							
							if(return_val == '1'){
								alert(msg_comp); // 처리하였습니다.							
								
								//$(opener.location).attr("href", "javascript:forwardConsideration('SAVE')"); //  부모창의 내용을 입시저장 된다.
								
								opener.checkListAppend(); // 상세 화면을 변경 시켜 버린다.
								
								self.close();  // 입력창을 닫아 버린다
							} else {
								alert("<spring:message code="clm.page.msg.manage.announce157" />");
							}									

						});						
					}	 
					
					// viewHiddenProgress(false) ;	
				}
		};		
		$("#frm").ajaxSubmit(options);
	 
 }
 
 function all_check(){
	 
	 var frm = document.frm;
	 
	 for(var z=1; z <= <c:out value='${insertResultListSize }'/>;z++){
		 
		 $("#tr_"+z).hide();
		 document.getElementsByName("tA_"+z)[0].value = "";
		 
		// 모두 체크
		$("input[name=checkItemYN_"+z+"]:radio[value='Y']").attr("checked",true);
		 
	 }
	 
 }
 

</script>
</head>
<body class="pop" >

<!-- header -->
<h1><spring:message code="las.page.field.hqrequest.page05"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content" style='height:560px'>
		<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
				<!-- search form -->
				<input type="hidden" name="method"   value="" />
				<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
				<input type="hidden" name="sum_text" value="" />
				<input type="hidden" name="cntrt_id" value="<c:out value='${command.cntrt_id }'/>" />
				<input type="hidden" name="checkGbn" value="modify" />
				
			<!-- // **************************** Form Setting **************************** -->
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
				<colgroup>
				    <col width="10%"/>
					<col width="*"/>
					<col width="30%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.rdcnt"/></th>
						<th><spring:message code="clm.common.checkPageListTitle"/></th>
						<th><spring:message code="clm.page.msg.manage.confirmYn"/><div class="btn_wrap_c" style="margin-top:3px"><span class="btn_all_w" onclick="javascript:all_check();"><a><spring:message code="las.page.field.hqrequest.page03" /></a></span></div></th>
					</tr>
				</thead>
			 	<tbody>
			 	<c:choose>
				 	<c:when test="${cnt > 0 }">
				 	    <c:forEach var="chlist" items="${insertResultList}" varStatus="i">
				 	        <c:choose>
				 	            <c:when test="${'X' == chlist.rea_item_cd }">
				 	                <tr class="end">
							          <td class='tC'><c:out value='${chlist.no }'/> </td>
							          <td><c:out value='${chlist.cd_nm }'/> <img src="/images/las/ko/common/step_q.gif"  id="img_step_q" alt="info" title="<c:out value='${chlist.useman_mng_itm1 }'/>"  /></td>
							          <td align="center"> 
							               <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="Y" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" checked="checked"/> <spring:message code="las.page.field.lawconsult.yes" />
							               <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="N" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" /> <spring:message code="las.page.field.lawconsult.no2" />
							          </td>
							        </tr>
							        <tr style="display:none;" id="tr_<c:out value='${chlist.no }'/>">
							          <td colspan="3">
							            <spring:message code="las.page.field.hqrequest.page04" />
							            <textarea rows="3" cols="" class="text_area_full" name="tA_<c:out value='${chlist.no }'/>" id="<c:out value='${chlist.no }'/>" ></textarea>
							            <input type="hidden" name="tA_key_<c:out value='${chlist.no }'/>" value="<c:out value='${chlist.item_cd }'/>" />
							          </td>
							        </tr>
				 	            </c:when>
				 	            <c:when test="${'X' != chlist.rea_item_cd }">
				 	                <tr class="end">
							          <td class='tC'><c:out value='${chlist.no }'/> </td>
							          <td><c:out value='${chlist.cd_nm }'/> <img src="/images/las/ko/common/step_q.gif"  id="img_step_q" alt="info" title="<c:out value='${chlist.useman_mng_itm1 }'/>"  /></td>
							          <td align="center"> 
							               <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="Y" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" /> <spring:message code="las.page.field.lawconsult.yes" />
							               <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="N" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" checked="checked"/> <spring:message code="las.page.field.lawconsult.no2" />
							          </td>
							        </tr>
							        <tr style="display:" id="tr_<c:out value='${chlist.no }'/>">
							          <td colspan="3">
							            <spring:message code="las.page.field.hqrequest.page04" />
							            <textarea rows="3" cols="" class="text_area_full" name="tA_<c:out value='${chlist.no }'/>" id="<c:out value='${chlist.no }'/>" ><c:out value="${chlist.remark }" escapeXml="false"/></textarea>
							            <input type="hidden" name="tA_key_<c:out value='${chlist.no }'/>" value="<c:out value='${chlist.item_cd }'/>" />
							          </td>
							        </tr>
				 	            </c:when>
				 	            <c:otherwise>
				 	                
				 	            </c:otherwise>
				 	        </c:choose>
				        </c:forEach>
				    </c:when>
				    <c:otherwise>
				        <c:forEach var="chlist" items="${insertResultList}" varStatus="i">
				 	                <tr class="end">
							          <td class='tC'><c:out value='${chlist.no }'/> </td>
							          <td><c:out value='${chlist.cd_nm }'/> <img src="/images/las/ko/common/step_q.gif"  id="img_step_q" alt="info" title="<c:out value='${chlist.useman_mng_itm1 }'/>" /></td>
							          <td align="center">
							              <c:choose>
							                  <c:when test="${'Y' eq chlist.check_list_yn }">
							                      <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="Y" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" checked="checked" /> <spring:message code="las.page.field.lawconsult.yes" />
							                      <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="N" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" /> <spring:message code="las.page.field.lawconsult.no2" />
							                  </c:when>
							                  <c:otherwise>
							                      <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="Y" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" /> <spring:message code="las.page.field.lawconsult.yes" />
							                      <input name="checkItemYN_<c:out value='${chlist.no }'/>" id="checkItemYN_<c:out value='${chlist.item_cd }'/>" type="radio" value="N" onclick="javascript:changeText('<c:out value='${chlist.item_cd }'/>','<c:out value='${chlist.no }'/>')" /> <spring:message code="las.page.field.lawconsult.no2" />
							                  </c:otherwise>
							              </c:choose>
							          </td>
							        </tr>
							        <tr style="display:none;" id="tr_<c:out value='${chlist.no }'/>">
							          <td colspan="3">
							            <spring:message code="las.page.field.hqrequest.page04" />
							            <textarea rows="3" cols="" class="text_area_full" name="tA_<c:out value='${chlist.no }'/>" id="<c:out value='${chlist.no }'/>" ></textarea>
							            <input type="hidden" name="tA_key_<c:out value='${chlist.no }'/>" value="<c:out value='${chlist.item_cd }'/>" />
							          </td>
							        </tr>
				 	           
				        </c:forEach>
				    </c:otherwise>
			    </c:choose>
			    
					
							
			  </tbody>
			</table>
			<!-- //list -->
		</form:form>
</div>
</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	     <span class="btn_all_w" onclick="javascript:save();"><span class="save"></span><a><spring:message code="clm.page.msg.common.save" /></a></span>
	     <span class="btn_all_w" onclick="javascript:self.close();"><span class="cancel"></span><a><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>