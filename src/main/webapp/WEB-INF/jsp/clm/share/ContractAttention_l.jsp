<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sec.common.util.ClmsDataUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.share.dto.ContractAttentionForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : ContractAttention_l.jsp
 * 프로그램명 : 계약체결시 유의사항 목록
 * 설      명 : 
 * 작  성  자 : 유영섭
 * 작  성  일 : 2011.09.16
 */
--%>  
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ContractAttentionForm command = (ContractAttentionForm)request.getAttribute("command");

%> 

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>



<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">


<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
		
	
$(document).ready(function(){

    getCodeSelectByUpCd("frm", "srch_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_bigclsfcn()%>");
    

    getCodeSelectByUpCd("frm", "srch_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<%=command.getSrch_bigclsfcn()%>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_midclsfcn()%>");
    <%
    if (!"".equals(StringUtil.bvl(command.getSrch_bigclsfcn(), ""))) {  //search 후에 자동셋팅 위해(대분류가 값이 있을때 자동으로 중분류 리스트 조회하여 셋팅)
%>
	var bigcate = '<%=command.getSrch_bigclsfcn()%>';  
	if(bigcate == "T01" || bigcate == "T02"){
		frm.srch_smlclsfcn.style.display = 'none';
	}
	
<%
    }
%>


	getCodeSelectByUpCd("frm", "srch_smlclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<%=command.getSrch_midclsfcn()%>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_smlclsfcn()%>");


    

});

function changeSubCd(selObjId, gbn, upCd) {
	
	if(upCd == "T01" || upCd == "T02" || upCd == ''){
    	frm.srch_smlclsfcn.style.display = 'none';
    	frm.srch_smlclsfcn.value = "";
    }else if(upCd == "T03" || upCd == ""){	
        frm.srch_smlclsfcn.style.display = '';
	}

	if(upCd == "") {
        upCd = "XXX";
    }

    getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
}



	
	/** 
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />"; 
		frm.method.value = "listContractAttention";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		viewHiddenProgress(true) ;
		
		var frm = document.frm;   
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />";  

		if(flag == "search"){
		    frm.method.value = "listContractAttention";		    
			frm.curPage.value = "1";
			frm.submit();
	    }else if(flag == "new"){	
			frm.target = "_self";
			frm.action = "<c:url value='/clm/share/ContractAttention.do' />";  
		    frm.method.value = "forwardContractAttentionInsert";
		    frm.mtbat_no.value=0;

			viewHiddenProgress(true) ;
			frm.submit();
		}
		
		
		
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(mtbat_no){
		viewHiddenProgress(true) ;
		  
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />";  
		frm.method.value = "detailContractAttention";
		frm.mtbat_no.value = mtbat_no;
		frm.submit();		
	}
	
</script>
</head>

<body onLoad=''>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1;'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 

</script>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value=" " />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
	<!-- Detail Page Param -->
	<input type="hidden" name="mtbat_no" value="<c:out value='${command.mtbat_no}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"> 
	
	<!-- key Form -->

<!-- //
**************************** Form Setting **************************** 
-->
 <div id="wrap"> 
	
	
	<!-- container -->
	<div id="container">
		<!-- Location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.contractattention.listTitle" /></h3>
		<!-- //title -->
		</div>
		
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
										<col width="8%"/>
										<col width="92%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.contractattention.exam" /></th>
										<td>
										<select name="srch_bigclsfcn" id="srch_bigclsfcn" style="width:163px;" onChange='javascript:changeSubCd("srch_midclsfcn", "CONTRACTTYPE", this.value);'>
										</select>
										<select name="srch_midclsfcn" id="srch_midclsfcn" style="width:163px;" onChange='javascript:changeSubCd("srch_smlclsfcn", "CONTRACTTYPE", this.value);'>
						      
										</select>	
										<select name="srch_smlclsfcn" id="srch_smlclsfcn" style="width:163px;" >
										
						           		</select>		
										</td>
									</tr>
								</table>
							</td>
							<td class="tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>			
			<!--//search-->
	
		<%if(command.isAuth_insert()){ %>
			<!-- button -->
			<div class="btn_wrap_t">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
			</div>
			<!-- //button -->
		<%} %>
			
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="50px"/>
					<col width="290px" />
					<col width="210px"/>
					<col width="90px"/>
					<col width="90px"/>
					<col width="90px"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.no" /></th>
						<th><spring:message code="clm.page.field.contractattention.exam" /></th>
						<th><spring:message code="clm.page.field.contractattention.title" /></th>
						<th><spring:message code="clm.page.field.contractattention.regNm" /></th>
						<th><spring:message code="clm.page.field.contractattention.regdt" /></th>
						<th><spring:message code="clm.page.field.contractattention.rdcnt" /></th>
						
					</tr>
				</thead>
			 	<tbody>
				<%   if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			    <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView(<%=lom.get("mtbat_no")%>)"> 
			          <td><%=lom.get("mtbat_no")%></td>
			          <td class="tL txtover"><%=lom.get("type_gbn_nm")%></td>
			          <td class="tL txtover"><%=lom.get("title")%></a></td>		          
			          <td class="tC"><%=lom.get("reg_nm")%></td>
			          <td class="tC"><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
			          <td class="tC"><%=lom.get("rdcnt")%></td>
			   </tr>
				<%
						    }
					}else{		  
				%>
			
					<tr>
					  <td colspan="5" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%> 
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="total_num">
				<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
			</div>
			
			<!-- pagination  -->
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
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
