<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

	String langCd = (String)session.getAttribute("secfw.session.language_flag"); // 언어코드(ko|en)
	String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");        // 시스템코드(LAS)
	String IMAGE = "/images/"+ sysCd.toLowerCase() + "/" + langCd; // 이미지 경로설정
	String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/" + langCd;  // CSS 경로 설정
%>

<div class="title_basic">
	<h4><spring:message code="las.page.field.hqrequest.page09" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tr_down_check_list_5');" style='cursor:pointer;'/></h4>
</div>			
				
<div id="tr_down_check_list_5">	

<table id="tab_contents_sub_conts3_5" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">	
	<colgroup>	
		<col width="5%" />
		<col width="20%" />
		<col width="*" />
	</colgroup>
    <tr>
        <th class='tC'>No</th>
        <th class='tC'><spring:message code='las.page.field.hqrequest.page12' /></th>
        <th class='tC'><spring:message code='las.page.field.hqrequest.page13' /></th>
       </tr>
       
       <c:choose>
			<c:when test="${!empty chekItemList2}">
					<c:forEach var="list_in" items="${chekItemList2}" varStatus="x">
						 <tr>
						 	<td class='tC'><c:out value='${x.index + 1}'  /></td>
						 	<td><c:out value='${list_in.CD_NM}' /></td>
						 	<td><c:out value='${list_in.REMARK}'  escapeXml="" /></td>
						 </tr>		
					</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
				</tr>
			</c:otherwise>
	</c:choose>						


</table>

</div>