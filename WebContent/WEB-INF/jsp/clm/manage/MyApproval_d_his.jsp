<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : MyApproval_d_his.jsp
 * 프로그램명 : 이력정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.05
 */
--%>
<%
	ArrayList con	    = (ArrayList)request.getAttribute("con");
%>

<c:if test="${contractLom.depth_status=='C02636'}">
<div class="title_basic"><h5 class="ntitle05"><spring:message code="clm.page.msg.manage.apprHistory" /><img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-cnclsnInfo-view100');"/></h5></div>
<div id="contract-cnclsnInfo-view100">
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-conHisView">
	<colgroup>
		<col width="60%" />
		<col width="15%" />
		<col width="25%" />
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="clm.page.msg.manage.agreeBrkd" /></th>
		<th><spring:message code="clm.page.msg.manage.date" /></th>
		<th><spring:message code="clm.page.msg.manage.name" /></th>		
	</tr>
	</thead>
	<tbody>
<%
if(con.size() > 0){
	for(int idx=0;idx < con.size();idx++){

		ListOrderedMap lom = (ListOrderedMap)con.get(idx);		
%>		
		<tr>
			<td class="tL">
				<span class="mR5">
				<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" />
				</span>
				<%if("Y".equals((String)lom.get("agree_yn"))){
				%>
				[<spring:message code="clm.page.msg.common.agreement" />]
				<%  }else if("N".equals((String)lom.get("agree_yn"))){%>
				[<spring:message code="clm.page.msg.manage.notAgree" />]
				<%  } %>
			</td>
			<td class="tC"><%=lom.get("reg_dt") %></td>
			<td><%=lom.get("agreeman_nm") %>/<%=lom.get("agreeman_jikgup_nm") %>/<%=lom.get("agreeman_dept_nm") %></td>
		</tr>
		<!-- 테이블안에 테이블 -->
		<tr class="Nocol" id="tr_show" style="display:none">
			<td colspan="3">
				<table class="table-style_sub02">
				<colgroup>
					<col class="tal_w04" />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tr class="Nocol">
						<th><spring:message code="clm.page.msg.manage.agreeRsn" /></th>
						<td colspan="2">
							<%=lom.get("agree_cause") %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
<%
	}
}else{
%>
		<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="3" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
<%
}
%>
	</tbody>
</table>
</div>
</c:if>
