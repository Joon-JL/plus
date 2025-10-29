<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sec.clm.draft.dto.CustomerForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : Customer_p.jsp
 * 프로그램명 : 거래상대방 팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 * 수  정  자 : 
 * 수  정  일 : 
 * 수정  내용 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List selectList = (List)request.getAttribute("selectList");
	List customerList = (List)request.getAttribute("customerList");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true);
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customer.do' />";
		frm.method.value = "listCustomer";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customer.do' />";
		frm.method.value = "listCustomer";
		frm.curPage.value = "1";
		frm.submit();
	}

    //확인버튼 클릭.
	function confirm(){
		var frm = document.frm;
		var userInfo = new Object();

		if(frm.customer_gb.value == "M"){//여러개 선택이 가능할 경우.
			if(typeof frm.customer_cds.value != "undefined"){//리스트가 1개일 경우
				
				userInfo.customer_cd   = frm.customer_cds.value;
				userInfo.gubn_cd       = frm.gubn_cds.value;
				userInfo.account_cd    = frm.account_cds.value;
				userInfo.customer_nm1  = frm.customer_nm1s.value;
				userInfo.customer_nm2  = frm.customer_nm2s.value;
				userInfo.iv_nm1        = frm.iv_nm1s.value;
				userInfo.iv_nm2        = frm.iv_nm2s.value;
				userInfo.street        = frm.streets.value;
				userInfo.city          = frm.citys.value;
				userInfo.district      = frm.districts.value;
				userInfo.iv_street     = frm.iv_streets.value;
				userInfo.iv_city       = frm.iv_citys.value;
				userInfo.iv_district   = frm.iv_districts.value;
				userInfo.nation        = frm.nations.value;
				userInfo.region        = frm.regions.value;
				userInfo.postalcode    = frm.postalcodes.value;
				userInfo.telephone1    = frm.telephone1s.value;
				userInfo.rep_nm        = frm.rep_nms.value;
				userInfo.email         = frm.emails.value;
				userInfo.fax           = frm.faxs.value;
				userInfo.url           = frm.urls.value;
				userInfo.industry_type = frm.industry_types.value;
				userInfo.business_type = frm.business_types.value;
				userInfo.tax_nm1       = frm.tax_nm1s.value;
				userInfo.tax_nm2       = frm.tax_nm2s.value;
				userInfo.tax_nm3       = frm.tax_nm3s.value;
				userInfo.tax_nm4       = frm.tax_nm4s.value;
				userInfo.index         = 0;
				
			    opener.returnCustomer(userInfo);
				window.close();
				
			}else{//리스트가 여러 개 일 경우
				for(var i = 0 ; i < frm.customer_cds.length ; i++){
					
					userInfo.customer_cd   = frm.customer_cds[i].value;
					userInfo.gubn_cd       = frm.gubn_cds[i].value;
					userInfo.account_cd    = frm.account_cds[i].value;
					userInfo.customer_nm1  = frm.customer_nm1s[i].value;
					userInfo.customer_nm2  = frm.customer_nm2s[i].value;
					userInfo.iv_nm1        = frm.iv_nm1s[i].value;
					userInfo.iv_nm2        = frm.iv_nm2s[i].value;
					userInfo.street        = frm.streets[i].value;
					userInfo.city          = frm.citys[i].value;
					userInfo.district      = frm.districts[i].value;
					userInfo.iv_street     = frm.iv_streets[i].value;
					userInfo.iv_city       = frm.iv_citys[i].value;
					userInfo.iv_district   = frm.iv_districts[i].value;
					userInfo.nation        = frm.nations[i].value;
					userInfo.region        = frm.regions[i].value;
					userInfo.postalcode    = frm.postalcodes[i].value;
					userInfo.telephone1    = frm.telephone1s[i].value;
					userInfo.rep_nm        = frm.rep_nms[i].value;
					userInfo.email         = frm.emails[i].value;
					userInfo.fax           = frm.faxs[i].value;
					userInfo.url           = frm.urls[i].value;
					userInfo.industry_type = frm.industry_types[i].value;
					userInfo.business_type = frm.business_types[i].value;
					userInfo.tax_nm1       = frm.tax_nm1s[i].value;
					userInfo.tax_nm2       = frm.tax_nm2s[i].value;
					userInfo.tax_nm3       = frm.tax_nm3s[i].value;
					userInfo.tax_nm4       = frm.tax_nm4s[i].value;
					userInfo.index         = i;
					
				    opener.returnCustomer(userInfo);
					window.close();
				}
			}
		}else{//하나만 선택할 경우.
			var checkItemchk = 0;
		    var index = 0;
		    var customerCnt = <%=customerList.size()%>;
		    if(customerCnt != 0){
				for(var i = 0 ; i < frm.check_item.length ; i++) {
					if (frm.check_item[i].checked == true){
						checkItemchk++;
						index = i;
					}
				}
				if(checkItemchk == 1){
					userInfo.customer_cd   = frm.customer_cd[index].value;
					userInfo.gubn_cd       = frm.gubn_cd[index].value;
					userInfo.account_cd    = frm.account_cd[index].value;
					userInfo.customer_nm1  = frm.customer_nm1[index].value;
					userInfo.customer_nm2  = frm.customer_nm2[index].value;
					userInfo.iv_nm1        = frm.iv_nm1[index].value;
					userInfo.iv_nm2        = frm.iv_nm2[index].value;
					userInfo.street        = frm.street[index].value;
					userInfo.city          = frm.city[index].value;
					userInfo.district      = frm.district[index].value;
					userInfo.iv_street     = frm.iv_street[index].value;
					userInfo.iv_city       = frm.iv_city[index].value;
					userInfo.iv_district   = frm.iv_district[index].value;
					userInfo.nation        = frm.nation[index].value;
					userInfo.region        = frm.region[index].value;
					userInfo.postalcode    = frm.postalcode[index].value;
					userInfo.telephone1    = frm.telephone1[index].value;
					userInfo.rep_nm        = frm.rep_nm[index].value;
					userInfo.email         = frm.email[index].value;
					userInfo.fax           = frm.fax[index].value;
					userInfo.url           = frm.url[index].value;
					userInfo.industry_type = frm.industry_type[index].value;
					userInfo.business_type = frm.business_type[index].value;
					userInfo.tax_nm1       = frm.tax_nm1[index].value;
					userInfo.tax_nm2       = frm.tax_nm2[index].value;
					userInfo.tax_nm3       = frm.tax_nm3[index].value;
					userInfo.tax_nm4       = frm.tax_nm4[index].value;
					userInfo.index         = 0;
	
					opener.returnCustomer(userInfo);
					window.close();
				}else{
					if(checkItemchk > 1){
						alert("<spring:message code='clm.msg.alert.customer.checkItemChk'/>");
						return;
					}
					if(customerCnt != 0){
						if(frm.customer_cd.value == ""){
							alert("<spring:message code='clm.msg.alert.customer.checkItemChk'/>");
							return;
						}else{
							if(frm.check_item.checked == true){
								userInfo.customer_cd   = frm.customer_cd.value;
								userInfo.gubn_cd       = frm.gubn_cd.value;
								userInfo.account_cd    = frm.account_cd.value;
								userInfo.customer_nm1  = frm.customer_nm1.value;
								userInfo.customer_nm2  = frm.customer_nm2.value;
								userInfo.iv_nm1        = frm.iv_nm1.value;
								userInfo.iv_nm2        = frm.iv_nm2.value;
								userInfo.street        = frm.street.value;
								userInfo.city          = frm.city.value;
								userInfo.district      = frm.district.value;
								userInfo.iv_street     = frm.iv_street.value;
								userInfo.iv_city       = frm.iv_city.value;
								userInfo.iv_district   = frm.iv_district.value;
								userInfo.nation        = frm.nation.value;
								userInfo.region        = frm.region.value;
								userInfo.postalcode    = frm.postalcode.value;
								userInfo.telephone1    = frm.telephone1.value;
								userInfo.rep_nm        = frm.rep_nm.value;
								userInfo.email         = frm.email.value;
								userInfo.fax           = frm.fax.value;
								userInfo.url           = frm.url.value;
								userInfo.industry_type = frm.industry_type.value;
								userInfo.business_type = frm.business_type.value;
								userInfo.tax_nm1       = frm.tax_nm1.value;
								userInfo.tax_nm2       = frm.tax_nm2.value;
								userInfo.tax_nm3       = frm.tax_nm3.value;
								userInfo.tax_nm4       = frm.tax_nm4.value;
								userInfo.index         = 0;
		
								opener.returnCustomer(userInfo);
								window.close();
							}else{
								userInfo.customer_cd   = "";
								userInfo.gubn_cd       = "";
								userInfo.account_cd    = "";
								userInfo.customer_nm1  = "";
								userInfo.customer_nm2  = "";
								userInfo.iv_nm1        = "";
								userInfo.iv_nm2        = "";
								userInfo.street        = "";
								userInfo.city          = "";
								userInfo.district      = "";
								userInfo.iv_street     = "";
								userInfo.iv_city       = "";
								userInfo.iv_district   = "";
								userInfo.nation        = "";
								userInfo.region        = "";
								userInfo.postalcode    = "";
								userInfo.telephone1    = "";
								userInfo.rep_nm        = "";
								userInfo.email         = "";
								userInfo.fax           = "";
								userInfo.url           = "";
								userInfo.industry_type = "";
								userInfo.business_type = "";
								userInfo.tax_nm1       = "";
								userInfo.tax_nm2       = "";
								userInfo.tax_nm3       = "";
								userInfo.tax_nm4       = "";
								userInfo.index         = 0;
		
								opener.returnCustomer(userInfo);
								window.close();
							}
						}
					}else{
						
					}
				}
			
		    }else{
		    	userInfo.customer_cd   = "";
				userInfo.gubn_cd       = "";
				userInfo.account_cd    = "";
				userInfo.customer_nm1  = "";
				userInfo.customer_nm2  = "";
				userInfo.iv_nm1        = "";
				userInfo.iv_nm2        = "";
				userInfo.street        = "";
				userInfo.city          = "";
				userInfo.district      = "";
				userInfo.iv_street     = "";
				userInfo.iv_city       = "";
				userInfo.iv_district   = "";
				userInfo.nation        = "";
				userInfo.region        = "";
				userInfo.postalcode    = "";
				userInfo.telephone1    = "";
				userInfo.rep_nm        = "";
				userInfo.email         = "";
				userInfo.fax           = "";
				userInfo.url           = "";
				userInfo.industry_type = "";
				userInfo.business_type = "";
				userInfo.tax_nm1       = "";
				userInfo.tax_nm2       = "";
				userInfo.tax_nm3       = "";
				userInfo.tax_nm4       = "";
				userInfo.index         = 0;

				opener.returnCustomer(userInfo);
				window.close();
		    }
		}
	}
    
    //리스트 row 클릭
    function rowSelect(obj){
		var userInfo = new Object();
		var i = obj.rowIndex-1;

		userInfo.customer_cd   = frm.customer_cd[i].value;
		userInfo.gubn_cd       = frm.gubn_cd[i].value;
		userInfo.account_cd    = frm.account_cd[i].value;
		userInfo.customer_nm1  = frm.customer_nm1[i].value;
		userInfo.customer_nm2  = frm.customer_nm2[i].value;
		userInfo.iv_nm1        = frm.iv_nm1[i].value;
		userInfo.iv_nm2        = frm.iv_nm2[i].value;
		userInfo.street        = frm.street[i].value;
		userInfo.city          = frm.city[i].value;
		userInfo.district      = frm.district[i].value;
		userInfo.iv_street     = frm.iv_street[i].value;
		userInfo.iv_city       = frm.iv_city[i].value;
		userInfo.iv_district   = frm.iv_district[i].value;
		userInfo.nation        = frm.nation[i].value;
		userInfo.region        = frm.region[i].value;
		userInfo.postalcode    = frm.postalcode[i].value;
		userInfo.telephone1    = frm.telephone1[i].value;
		userInfo.rep_nm        = frm.rep_nm[i].value;
		userInfo.email         = frm.email[i].value;
		userInfo.fax           = frm.fax[i].value;
		userInfo.url           = frm.url[i].value;
		userInfo.industry_type = frm.industry_type[i].value;
		userInfo.business_type = frm.business_type[i].value;
		userInfo.tax_nm1       = frm.tax_nm1[i].value;
		userInfo.tax_nm2       = frm.tax_nm2[i].value;
		userInfo.tax_nm3       = frm.tax_nm3[i].value;
		userInfo.tax_nm4       = frm.tax_nm4[i].value;
		userInfo.index         = i;
		
	    opener.returnCustomer(userInfo);
		window.close();
    }
    
    //리스트 추가 시.
	function addInfo(obj, i, customerCd){
    	var frm = document.frm;
    	var addHtml = "";
    	
    	if(typeof frm.customer_cds == "undefined"){
    	}else{
    		if(frm.customer_cds.length > 1){//중복 체크
    			for(var j = 0 ; j < frm.customer_cds.length ; j++){
    				if(customerCd == frm.customer_cds[j].value){
    					alert("<spring:message code='clm.msg.alert.customer.customerCdChk'/>");
    					return;
    				}
    			}
    		}
    	}

    	addHtml = "<tr>";
    	addHtml = addHtml + "<td><input type='hidden' id='customer_nm1s' name='customer_nm1s' value='"+frm.customer_nm1[i].value+"'/>"+frm.customer_nm1[i].value+"</td>";
    	addHtml = addHtml + "<td><input type='hidden' id='rep_nms' name='rep_nms' value='"+frm.rep_nm[i].value+"' />"+frm.rep_nm[i].value+"</td>";
    	addHtml = addHtml + "<td><input type='hidden' id='industry_types' name='industry_types' value='"+frm.industry_type[i].value+"' />"+frm.industry_type[i].value+"</td>";
    	addHtml = addHtml + "<td><input type='hidden' id='streets' name='streets' value='"+frm.street[i].value+"' />"+frm.street[i].value+"</td>";
    	addHtml = addHtml + "<td><input type='hidden' id='telephone1s' name='telephone1s' value='"+frm.telephone1[i].value+"' />"+frm.telephone1[i].value+"</td>";

    	addHtml = addHtml + "<input type='hidden' id='customer_cds' name='customer_cds' value='"+frm.customer_cd[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='gubn_cds' name='gubn_cds' value='"+frm.gubn_cd[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='account_cds' name='account_cds' value='"+frm.account_cd[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='customer_nm2s' name='customer_nm2s' value='"+frm.customer_nm2[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='iv_nm1s' name='iv_nm1s' value='"+frm.iv_nm1[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='iv_nm2s' name='iv_nm2s' value='"+frm.iv_nm2[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='citys' name='citys' value='"+frm.city[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='districts' name='districts' value='"+frm.district[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='iv_streets' name='iv_streets' value='"+frm.iv_street[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='iv_citys' name='iv_citys' value='"+frm.iv_city[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='iv_districts' name='iv_districts' value='"+frm.iv_district[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='nations' name='nations' value='"+frm.nation[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='regions' name='regions' value='"+frm.region[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='postalcodes' name='postalcodes' value='"+frm.postalcode[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='emails' name='emails' value='"+frm.email[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='faxs' name='faxs' value='"+frm.fax[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='urls' name='urls' value='"+frm.url[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='business_types' name='business_types' value='"+frm.business_type[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='tax_nm1s' name='tax_nm1s' value='"+frm.tax_nm1[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='tax_nm2s' name='tax_nm2s' value='"+frm.tax_nm2[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='tax_nm3s' name='tax_nm3s' value='"+frm.tax_nm3[i].value+"' />";
    	addHtml = addHtml + "<input type='hidden' id='tax_nm4s' name='tax_nm4s' value='"+frm.tax_nm4[i].value+"' />";
    	addHtml = addHtml + "</tr>";
    	
    	$('#selectList').append(addHtml);
    }

</script>
</head>
<body class="pop">

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<!-- key form-->
<input type="hidden" name="customer_gb" id="customer_gb" value="<c:out value='${command.customer_gb}'/>" />
<!-- 첨부파일정보 -->

<!-- // **************************** Form Setting **************************** -->
<!-- header -->
<h1><spring:message code="clm.page.msg.common.otherParty" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<!-- Popup Detail -->
	<!-- Popup Size 600*600 -->
	<div class="h_450">
	  <div class="pop_content">
		<!-- title -->
		<div class="title_sub">
			<h5><spring:message code="clm.page.title.customer.listTitle" /></h5>
		</div>
		<!-- //title -->
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
									<col width="10%"/>
									<col width="90%"/>
								</colgroup>
								<tr>
									<th><spring:message code="clm.page.field.customer.customer_nm1" /></th>
									<td> 
									    <input type="text" id="srch_customer" name="srch_customer" value="<c:out value='${command.srch_customer}' />" onKeyPress="if(event.keyCode==13) {pageAction();return false;}"  />
									</td>
								</tr>
							</table>
						</td>
						<td class="vb tC">
							<a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--//search-->
		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
			<colgroup>
			    <c:if test="${command.customer_gb eq 'M'}">
 	                <col width="7%"/>
				    <col width="33%"/>
				    <col width="10%"/>
				    <col width="15%"/>
				    <col width="23%"/>
				    <col width="12%"/>
 	            </c:if>
 	            <c:if test="${command.customer_gb eq 'O'}">
 	                <col width="4%"/>
				    <col width="32%"/>
				    <col width="13%"/>
				    <col width="13%"/>
				    <col width="25%"/>
				    <col width="13%"/>
 	            </c:if>
			    
		  	</colgroup>
		  	<thead>
				<tr>
				    <th></th>
				    <th><spring:message code="clm.page.field.customer.customer_nm1" /></th>
				    <th><spring:message code="clm.page.field.customer.rep_nm" /></th>
				    <th><spring:message code="clm.page.field.customer.industry_type" /></th>
				    <th><spring:message code="clm.page.field.customer.street" /></th>
				    <th><spring:message code="clm.page.field.customer.tax_nm1" /></th>
				</tr>
			</thead>
		 	<tbody>
		 	    <c:choose>
				 	<c:when test="${pageUtil.totalRow > 0}">
				 	    <c:forEach var="list" items="${customerList}" varStatus="listCnt">
				 	        <tr onmouseout="this.className='';this.style.cursor='pointer';" onmouseover="this.className='on';this.style.cursor='pointer';">
				 	            <c:if test="${command.customer_gb eq 'M'}">
				 	                <td><span class="btn_all_w"><a href="javascript:addInfo(this, <c:out value="${listCnt.index}"/>, <c:out value='${list.customer_cd}'/>);"><spring:message code='clm.page.button.select' /></a></span></td>
				 	            </c:if>
				 	            <c:if test="${command.customer_gb eq 'O'}">
				 	                <td class="tC"><input type="radio" id="check_item" name="check_item" value="<c:out value='${list.customer_cd}'/>" /></td>
				 	            </c:if>
				 	            <td class="tL" title="<c:out value='${list.customer_nm1 }'/>" onclick="javascript:rowSelect(this.parentElement);"><div style='width:250px;overflow:hidden;text-overflow:ellipsis'><nobr><c:out value="${list.customer_nm1}" /></nobr></div></td>
				 	            <td class="tC" title="<c:out value='${list.rep_nm }'/>" onclick="javascript:rowSelect(this.parentElement);"><div style='width:90px;overflow:hidden;text-overflow:ellipsis'><nobr><c:out value="${list.rep_nm}" /></nobr></div></td>
				 	            <td class="tC" title="<c:out value='${list.industry_type }'/>" onclick="javascript:rowSelect(this.parentElement);"><div style='width:90px;overflow:hidden;text-overflow:ellipsis'><nobr><c:out value="${list.industry_type}" /></nobr></div></td>
				 	            <td class="tL" title="<c:out value='${list.street }'/>" onclick="javascript:rowSelect(this.parentElement);"><div style='width:180px;overflow:hidden;text-overflow:ellipsis'><nobr><c:out value="${list.street}" /></nobr></div></td>
				 	            <td class="tC" title="<c:out value='${list.telephone1 }'/>" onclick="javascript:rowSelect(this.parentElement);"><div style='width:90px;overflow:hidden;text-overflow:ellipsis'><nobr><c:out value="${list.telephone1}" /></nobr></div></td>
				 	            <input type="hidden" id="customer_cd" name="customer_cd" value="<c:out value='${list.customer_cd}'/>" />
								<input type="hidden" id="gubn_cd" name="gubn_cd" value="<c:out value='${list.gubn_cd}'/>" />
								<input type="hidden" id="account_cd" name="account_cd" value="<c:out value='${list.account_cd}'/>" />
								<input type="hidden" id="customer_nm1" name="customer_nm1" value="<c:out value='${list.customer_nm1}'/>" />
								<input type="hidden" id="customer_nm2" name="customer_nm2" value="<c:out value='${list.customer_nm2}'/>" />
								<input type="hidden" id="iv_nm1" name="iv_nm1" value="<c:out value='${list.iv_nm1}'/>" />
								<input type="hidden" id="iv_nm2" name="iv_nm2" value="<c:out value='${list.iv_nm2}'/>" />
								<input type="hidden" id="street" name="street" value="<c:out value='${list.street}'/>" />
								<input type="hidden" id="city" name="city" value="<c:out value='${list.city}'/>" />
								<input type="hidden" id="district" name="district" value="<c:out value='${list.district}'/>" />
								<input type="hidden" id="iv_street" name="iv_street" value="<c:out value='${list.iv_street}'/>" />
								<input type="hidden" id="iv_city" name="iv_city" value="<c:out value='${list.iv_city}'/>" />
								<input type="hidden" id="iv_district" name="iv_district" value="<c:out value='${list.iv_district}'/>" />
								<input type="hidden" id="nation" name="nation" value="<c:out value='${list.nation}'/>" />
								<input type="hidden" id="region" name="region" value="<c:out value='${list.region}'/>" />
								<input type="hidden" id="postalcode" name="postalcode" value="<c:out value='${list.postalcode}'/>" />
								<input type="hidden" id="telephone1" name="telephone1" value="<c:out value='${list.telephone1}'/>" />
								<input type="hidden" id="rep_nm" name="rep_nm" value="<c:out value='${list.rep_nm}'/>" />
								<input type="hidden" id="email" name="email" value="<c:out value='${list.email}'/>" />
								<input type="hidden" id="fax" name="fax" value="<c:out value='${list.fax}'/>" />
								<input type="hidden" id="url" name="url" value="<c:out value='${list.url}'/>" />
								<input type="hidden" id="industry_type" name="industry_type" value="<c:out value='${list.industry_type}'/>" />
								<input type="hidden" id="business_type" name="business_type" value="<c:out value='${list.business_type}'/>" />
								<input type="hidden" id="tax_nm1" name="tax_nm1" value="<c:out value='${list.tax_nm1}'/>" />
								<input type="hidden" id="tax_nm2" name="tax_nm2" value="<c:out value='${list.tax_nm2}'/>" />
								<input type="hidden" id="tax_nm3" name="tax_nm3" value="<c:out value='${list.tax_nm3}'/>" />
								<input type="hidden" id="tax_nm4" name="tax_nm4" value="<c:out value='${list.tax_nm4}'/>" />
				 	        </tr>
				 	     </c:forEach>
				 	</c:when>
				 	<c:otherwise>
				 	    <tr>
							<td colspan="7" class="td_ac" align="center"><!--<spring:message
												code="secfw.msg.succ.noResult" /> 12/05/02 박보나 과장 요구 수정-->
										<spring:message code="clm.page.msg.draft.announce018" /> <spring:message code="clm.page.msg.draft.announce019" />		 
							</td>
					    </tr>
				 	</c:otherwise>
			 	</c:choose>
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
		
		<c:if test="${command.customer_gb eq 'M'}">
			<!-- 선택된 값 start -->		
			<table id="selectList" border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
				    <col width="10%"/>
				    <col width="10%"/>
				    <col width="10%"/>
				    <col width="40%"/>
				    <col width="20%"/>
			  	</colgroup>
			  	<thead>
					<tr>
					    <th><spring:message code="clm.page.field.customer.customer_nm1" /></th>
					    <th><spring:message code="clm.page.field.customer.rep_nm" /></th>
					    <th><spring:message code="clm.page.field.customer.industry_type" /></th>
					    <th><spring:message code="clm.page.field.customer.street" /></th>
					    <th><spring:message code="clm.page.field.customer.tax_nm1" /></th>
					</tr>
				</thead>
			 	<tbody>
					<%	if(selectList != null && selectList.size()>0){
						    for(int idx=0;idx < selectList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)selectList.get(idx);
					%>
					 	        <tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';" onClick="">
					 	            <td><%=lom.get("customer_nm1")%></td>
					 	            <td><%=lom.get("rep_nm")%></td>
					 	            <td><%=lom.get("industry_type")%></td>
					 	            <td class="tL"><%=lom.get("street")%></td>
					 	            <td><%=lom.get("telephone1")%></td>
					 	            <input type="hidden" id="customer_cds" name="customer_cds" value="<%=lom.get("customer_cd")%>" />
									<input type="hidden" id="gubn_cds" name="gubn_cds" value="<%=lom.get("gubn_cd")%>" />
									<input type="hidden" id="account_cds" name="account_cds" value="<%=lom.get("account_cd")%>" />
									<input type="hidden" id="customer_nm1s" name="customer_nm1s" value="<%=lom.get("customer_nm1")%>" />
									<input type="hidden" id="customer_nm2s" name="customer_nm2s" value="<%=lom.get("customer_nm2")%>" />
									<input type="hidden" id="iv_nm1s" name="iv_nm1s" value="<%=lom.get("iv_nm1")%>" />
									<input type="hidden" id="iv_nm2s" name="iv_nm2s" value="<%=lom.get("iv_nm2")%>" />
									<input type="hidden" id="streets" name="streets" value="<%=lom.get("street")%>" />
									<input type="hidden" id="citys" name="citys" value="<%=lom.get("city")%>" />
									<input type="hidden" id="districts" name="districts" value="<%=lom.get("district")%>" />
									<input type="hidden" id="iv_streets" name="iv_streets" value="<%=lom.get("iv_street")%>" />
									<input type="hidden" id="iv_citys" name="iv_citys" value="<%=lom.get("iv_city")%>" />
									<input type="hidden" id="iv_districts" name="iv_districts" value="<%=lom.get("iv_district")%>" />
									<input type="hidden" id="nations" name="nations" value="<%=lom.get("nation")%>" />
									<input type="hidden" id="regions" name="regions" value="<%=lom.get("region")%>" />
									<input type="hidden" id="postalcodes" name="postalcodes" value="<%=lom.get("postalcode")%>" />
									<input type="hidden" id="telephone1s" name="telephone1s" value="<%=lom.get("telephone1")%>" />
									<input type="hidden" id="rep_nms" name="rep_nms" value="<%=lom.get("rep_nm")%>" />
									<input type="hidden" id="emails" name="emails" value="<%=lom.get("email")%>" />
									<input type="hidden" id="faxs" name="faxs" value="<%=lom.get("fax")%>" />
									<input type="hidden" id="urls" name="urls" value="<%=lom.get("url")%>" />
									<input type="hidden" id="industry_types" name="industry_types" value="<%=lom.get("industry_type")%>" />
									<input type="hidden" id="business_types" name="business_types" value="<%=lom.get("business_type")%>" />
									<input type="hidden" id="tax_nm1s" name="tax_nm1s" value="<%=lom.get("tax_nm1")%>" />
									<input type="hidden" id="tax_nm2s" name="tax_nm2s" value="<%=lom.get("tax_nm2")%>" />
									<input type="hidden" id="tax_nm3s" name="tax_nm3s" value="<%=lom.get("tax_nm3")%>" />
									<input type="hidden" id="tax_nm4s" name="tax_nm4s" value="<%=lom.get("tax_nm4")%>" />
					 	        </tr>
					<%		}
						}
					%>
			    </tbody>
			</table>
			<!-- 선택된 값 end -->
		</c:if>
		
		<!--footer -->
	    <div class="pop_footer">
	        <!-- button -->
	        <div class="btn_wrap fR">
	            <span class="btn_all_w"><span class="save"></span><a href="javascript:confirm()"><spring:message code='clm.page.button.confirm' /></a></span>
	        </div>
             <!-- //button -->
         </div>
         <!-- //footer -->
         <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	  </div>
	</div>
	<!-- //Popup Detail -->
</div>
<!-- //body -->
</form:form>
</body>
</html>