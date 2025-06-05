<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ManageForm" %>


<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.WebUtil"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="com.sec.clm.manage.dto.ConclusionVO" %>
<%--
/**
 * 파  일  명 : LinkContractList_p.jsp
 * 프로그램명 : 연관계약정보 팝업 목록
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.10.20
 
 
 */
--%>
<%
	//PageUtil 객체를 읽어들임
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");

	ConclusionVO conclusionVOByIF = null;	
	String cntrtIdIf = "";
	String key_id = StringUtil.bvl((String)request.getAttribute("key_id"),"");
	String key_id_pre ="";
	
	if(key_id.length()>7){
		key_id_pre = StringUtil.bvl((String)request.getAttribute("key_id"),"").substring(0,7);		
	}else{
		key_id_pre=key_id;
	}
	
	String key_nm = StringUtil.bvl((String)request.getAttribute("key_nm"),"");

	if(request.getAttribute("conclusionVOByIF")!=null){
		conclusionVOByIF = (ConclusionVO)request.getAttribute("conclusionVOByIF");
		if(conclusionVOByIF!=null){//IRP건으로 KEY_ID로 조회해서 계약이 있는지여부 
			cntrtIdIf = StringUtil.bvl(conclusionVOByIF.getCntrt_id(),"");	
		}else{
			conclusionVOByIF = new ConclusionVO();
		}
	}
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<LINK href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">
                                      
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script type="text/javascript">

	$(document).ready(function(){
 	
	 	initCal("srch_start_cnlsnday");   //첫번재 체결일 설정
	    initCal("srch_end_cnlsnday");     //두번재 체결일 설정
	
	});

	// 조회버튼 클릭
	function doAction(){
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
		frm.method.value = "listContractIRPPop";
	    frm.curPage.value = "1";
	    frm.submit();
        
	}	
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
		frm.method.value = "listContractIRPPop";
		frm.curPage.value = pgNum;
		frm.submit();
	}
	
	function countIF(idx,sysNm,cntrtStatus) { 	
				
		if((sysNm=="CMS"||sysNm=="")){
			
			if(cntrtStatus=="C02402"||cntrtStatus=="C02403"||cntrtStatus=="C02404"){
			
				idx=idx-1;
				
                if (frm.cntrt_idx.length == undefined) {//채크박스가한개만 보일때
                	if(frm.cntrt_idx.checked==true){
		            	frm.cntrt_idx.checked=false;	
		            	frm.checked_sys_nm.value="";//이전연계시스템
		            }else{
		            	frm.cntrt_idx.checked=true;
		            	frm.checked_sys_nm.value=sysNm;//이전연계시스템
		            }
                }else{
    		        for ( var i = 0; i < frm.cntrt_idx.length; i++) {
    		        	if (frm.cntrt_idx[i].checked) {
    		        		frm.cntrt_idx[i].checked=false;
    		            	frm.checked_sys_nm.value="";//이전연계시스템
    		        	}
    		            if(i==idx){
    		            	frm.cntrt_idx[i].checked=true;
    		            	frm.checked_sys_nm.value=sysNm;//이전연계시스템
    		            }
    		        }                	
                }
                
			}else{
				alert("<spring:message code="clm.page.msg.manage.announce057" />");
			}
		}else{
			alert(sysNm+" <spring:message code="clm.page.msg.manage.announce117" />");
		}
	}

	//인터페이스작업[IRP로 계약정보 보내기] 유효성채크
    function callContractIF() {     
        var frm = document.frm ;    
        var check_value = 0;
        var checkedIdxValue ="";
        
        if (frm.cntrt_idx == undefined) {
            alert("<spring:message code="clm.page.msg.common.announce005" />");
            return;
        }
        
        if (frm.cntrt_idx.length == undefined) {
        	if(frm.cntrt_idx.checked==true){
        		check_value += 1;		            	
            }
        }else{           
            for ( var i = 0; i < frm.cntrt_idx.length; i++) {
            	if (frm.cntrt_idx[i].checked) check_value += 1;
            }        	
        }
            
        
        if (check_value == 0) {
            alert("<spring:message code="clm.page.msg.common.announce006" />");
        } else {
        	
            if (frm.cntrt_idx.length == undefined) {
            	if(frm.cntrt_idx.checked==true){
            		checkedIdxValue=frm.cntrt_idx.value;		            	
                }
            }else{
                for ( var i = 0; i < frm.cntrt_idx.length; i++) {
                	if (frm.cntrt_idx[i].checked) {
                		checkedIdxValue=frm.cntrt_idx[i].value;
                	};
                }            	
            }
            
            frm.cntrt_id.value=checkedIdxValue;
			if("<%=cntrtIdIf%>"!="" && "<%=cntrtIdIf%>"==checkedIdxValue){
				//재전송
            	if(confirm("<spring:message code="clm.page.msg.manage.same" />(<%=key_id%>)<spring:message code="clm.page.msg.manage.announce014" />")){
                    sendIRP();            		
            	}
            }else if("<%=cntrtIdIf%>"!=""){
            	if(confirm("<spring:message code="clm.page.msg.manage.same" />(<%=key_id%>)<spring:message code="clm.page.msg.manage.announce015" />")){
                    sendIRP();            		
            	}            	
            }else{
            	sendIRP();//신규전송
            }
        }       
    }	
    
  	//인터페이스작업[IRP로 계약정보 보내기] ajax로 리턴
    function sendIRP() {
    	
    	var options = {
    		url: "<c:url value='/clm/manage/conclusion.do?method=sendIRPContractIF'/>",
    		type: "post",
    		async: false,
    		dataType: "json",
    		success: function (data) {
    						
    			if(data != null && data.length != 0) {
    								
    				$.each(data, function(entryIndex, entry) {
    					
    					alert(entry['RESULT_MSG']);	
    					
    					
    				});
    				
    			}
    			
    			window.close();
    			
    		}
    	};
    	if(confirm("<spring:message code="clm.page.msg.manage.announce006" />")){
    		$("#frm").ajaxSubmit(options);		
    	}
    }    
</script>
</head>
<body class="pop">
<form:form name="frm" id='frm' method="post" autocomplete="off">
	<input type="hidden" name="method" value="" />
    <input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
    <input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />    
    <input type="hidden" name="cntrt_id" id="cntrt_id" value="">
    <input type="hidden" name="pre_cntrt_id" id="pre_cntrt_id" value="<c:out value='${conclusionVOByIF.cntrt_id}'/>">
    <input type="hidden" name="key_id" id="key_id" value="<c:out value='${key_id}'/>">
    <input type="hidden" name="key_nm" id="key_nm" value="<c:out value='${key_nm}'/>">
    <input type="hidden" name="checked_sys_nm" id="checked_sys_nm" value="">

<!-- header -->
<h1><spring:message code="clm.page.title.manageContractPop.listTitle" />(IRP)</h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600">
		<div class="pop_content mt10">
		<!-- container -->	
			<!--90% 안에서 작업하기 popup search-->
			<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="180px"/>
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="20%"/>										
										<col colspan="2"/>
									</colgroup>
									<tr>

										<th><spring:message code="clm.page.msg.manage.workCode" />: </th>
										<td><%=key_id_pre%></td>
										<th><spring:message code="clm.page.msg.manage.contName" />: </th>
										<td colspan="5"><c:out value='${conclusionVOByIF.cntrt_nm}'/></td>
																		
									</tr>
									<tr>

										<th><spring:message code="clm.page.msg.manage.countParty" />: </th>
										<td><c:out value='${conclusionVOByIF.cntrt_oppnt_nm}'/></td>
										<th><spring:message code="clm.page.msg.manage.contAmt" />: </th>
										<td><c:out value='${conclusionVOByIF.cntrt_amt}'/></td>
										<th><spring:message code="clm.page.msg.manage.curr" />: </th>
										<td><c:out value='${conclusionVOByIF.crrncy_unit}'/></td>
										<th><spring:message code="clm.page.msg.manage.dateOfCont" />: </th>
										<td><c:out value='${conclusionVOByIF.cnclsn_plndday}'/></td>
																		
									</tr>									
								</table>
							</td>				
							<td class="vb tC">	
							</td>													
							<td class="vb tC">
							</td>
						</tr>
					</table>
				</div>
          	</div>        	
			<!--search-->
			<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="250px"/>
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col />
										<col width="10%"/>
										<col />
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
										<td><input type="text" name="srch_review_title" style="width:150px" class="text_full" value="<c:out value='${command.srch_review_title}'/>"/></td>
										<th><spring:message code="clm.page.msg.manage.contConclYn" /></th>
										<td>
											<select name="srch_state" id="srch_state">
												<option value="0" default <c:if test="${command.srch_state eq '0'}">selected</c:if>>--<spring:message code="clm.page.msg.common.all" />--</option>
												<option value="1" <c:if test="${command.srch_state eq '1'}">selected</c:if>><spring:message code="clm.page.msg.manage.contYetToBe" /></option>
												<option value="2" <c:if test="${command.srch_state eq '2'}">selected</c:if>><spring:message code="clm.page.msg.manage.contConcl" /></option>
											</select>										
										</td>
										<th><spring:message code="clm.page.msg.manage.dateOfCont" /></th>
										<td>
					                        <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" readonly required alt="<spring:message code="clm.page.msg.manage.contPerStrtDt" htmlEscape="true" />" value="<c:out value='${command.srch_start_cnlsnday}'/>" class="text_calendar02"/> ~ 
					                        <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" readonly required alt="<spring:message code="clm.page.msg.manage.contPerEndDt" htmlEscape="true" />" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar02" />
                    					</td>
									</tr>								
								</table>
							</td>				
							<td class="vb tC">	
							<c:if test="true">
								<span class="btn_all_w"><a href="javascript:callContractIF();"><spring:message code="clm.page.msg.manage.irpCont" /></a></span>
							</c:if>		
							<a href="javascript:doAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>	
						</tr>
					</table>
				</div>
          	</div>        	
			<!--search-->			
			
			<!--//90% 안에서 작업하기 popup search-->
					<!-- table01 -->
					<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
					  <colgroup>
						<col width="4%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="14%"/>						
						<col width="8%"/>
						<col width="10%"/>
						<col width="6%"/>
						<col width="10%"/>
						<col width="8%"/>
					  </colgroup>
					  <thead>
						<tr>
						  <th></th>
						  <th><spring:message code="clm.page.field.manageCommon.cntrtNm" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.cntrtType" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.respmanNm" /></th>				  
						  <th><spring:message code="clm.page.msg.manage.contAmt" /></th>	
						  <th><spring:message code="clm.page.msg.manage.unit" /></th>				  
						  <th><spring:message code="clm.page.msg.manage.contConclDt" /></th>		  
						  <th>I/F</th>
						  <th><spring:message code="clm.page.field.manageCommon.step" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.status" /></th>
						</tr>
					  </thead>
					  <tbody>
					  <%
						if(pageUtil.getTotalRow() > 0) {
						
						int listIdx = 0;
						
							for(int idx=0; idx<resultList.size(); idx++) {
								listIdx++;
								ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);	%>
						<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:countIF(<%=listIdx %>,'<%=lom.get("sys_nm") %>','<%=lom.get("cntrt_status") %>');">
						  <td>
						  <input type="checkbox" name="cntrt_idx" id="cntrt_idx" value="<%=lom.get("cntrt_id") %>" 
						  <%if(cntrtIdIf.equals((String)lom.get("cntrt_id"))) {%>checked<%} %> 
						  <%if((!"IRP".equals((String)lom.get("sys_nm"))&&!"CMS".equals((String)lom.get("sys_nm"))&&!"".equals((String)lom.get("sys_nm")))||"C02401".equals((String)lom.get("cntrt_status"))) {%>disabled<%} %>
						  />
						  </td>
						  <td class="tL txtover" title="<spring:message code="clm.page.msg.manage.reqName" htmlEscape="true" />: <%=lom.get("req_title") %>"><nobr><%if(cntrtIdIf.equals((String)lom.get("cntrt_id"))) {%><font color="red"><%} %><%=lom.get("cntrt_nm") %><%if(cntrtIdIf.equals((String)lom.get("cntrt_id"))) {%></font><%} %></nobr></td>
						  <td><%=lom.get("biz_clsfcn_nm") %></td>
						  <td><%=lom.get("cntrt_respman_nm") %></td>
						  <td style="text-align:right"><%=lom.get("cntrt_amt") %></td>
						  <td><%=lom.get("crrncy_unit") %></td>
						  <td><%=DateUtil.formatDate((String)(lom.get("cnclsn_plndday")), "-") %></td>
						  <td><%=lom.get("sys_nm") %></td>
						  <td><%=lom.get("prcs_depth_nm") %></td>
						  <td><%=lom.get("depth_status_nm") %></td>
						  </tr>
						<% 	}
						}else{
						%>
							<tr class="end">
					  		<td colspan="10"><spring:message code="secfw.msg.succ.noResult" /></td>
							</tr>
						<%
						}
						%>
						  </tbody>
					</table>
					<!--// table01 -->

					<div class="total_num">
						Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />

					</div>
					<!-- pagination -->
					<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>
					</div>
					<!-- //pagination -->
				<!-- //content -->
		</div>
	</div>
</div>
<input type="text" name="srch_review_title2" style="width:0px; height: 0px;" />
<!-- //body -->
<!--footer -->
<div class="pop_footer">

</div>
<!-- //footer -->
</form:form>
</body>

</html>
