<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : LawFirmManage_l.jsp
 * 프로그램명 : 로펌관리 목록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.08
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
	
		// 사건구분1
		getCodeSelectByUpCd2("frm", "srch_kbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn1}'/>");
		
		// 사건구분1이 null 이 아닌 경우 
		var isSrch_kbn1 = "<c:out value='${command.srch_kbn1}'/>";
		
		if(isSrch_kbn1!=''){
			// 사건구분2 로드
			StReload_partin("<c:out value='${command.srch_kbn1}'/>");
		}
	
	});
	
	/**
	*  사건구분2  reload
	*/
	function StReload_partin(index){
		$(partin2).html("<select name='srch_kbn2' id='srch_kbn2' alt='<spring:message code='las.page.field.lawservice.event2'/>' style='width:92px;' ></select>");
		getCodeSelectByUpCd2("frm", "srch_kbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+index+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn2}'/>");
	} 

	/**
	* 국가선택 POPUP function
	*/
	function NationPop(mode)
	{
    	var frm = document.frm;

		PopUpWindowOpen('', "1000", "860", true);
		frm.action = "/secfw/main.do";
		frm.method.value="forwardPage";
		frm.target = "PopUpWindow";
		frm.forward_url.value="/WEB-INF/jsp/las/lawservice/NationList_p.jsp";
		frm.submit();
	}

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		frm.method.value = "listLawfirmManage";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;		

		if(flag == "search"){			
		    //유효성 체크
		    if(validateForm(frm)){		    
			    viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
			    frm.method.value = "listLawfirmManage";
				frm.curPage.value = "1";		
				frm.submit();			
		    }
		}else if(flag == "new"){
			
			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		    frm.method.value = "forwardLawfirmManageInsert";
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(lawfirm_id){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.lawfirm_id.value		= lawfirm_id;

		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		frm.method.value = "detailLawfirmManage";
		
		frm.submit();		
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

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
<input type="hidden" name="screen_type"  value="list" />

<!-- search form -->

<!-- key Form -->
<input type="hidden" name="lawfirm_id"  value="" />
<input type="hidden" name="nation"  value="" />
<input type="hidden" name="srch_nation"  value="" />
<input type="hidden" name="forward_url"  value="" />

<div id="wrap">    
    <div id="container">
            <!-- Location -->
            <div class="location">
                <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
            </div>
            <!-- //Location -->
             <!-- Title -->
            <div class="title">
		<h3><spring:message code="las.page.field.lawservice.lawfirmmng"/></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
	
		<div id="content">
		<!--search-->        
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
									<col width="15%"/>
									<col width="40%"/>
									<col width="15%"/>
									<col width="30%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawservice.event"/></th>
									<td colspan=3" >
										<select id='srch_event_no' name='srch_event_no' style='width:250px;'>
											<option value=''><spring:message code="las.page.field.lawservice.select"/></option>	
											<c:forEach var="list" items="${eventList}">
											<option value="<c:out value='${list.event_no}'/>" <c:if test='${list.event_no == command.srch_event_no}'> selected</c:if>><c:out value='${list.event_nm}'/></option>			
											</c:forEach>
										</select>
										<input id="srch_event_nm" name="srch_event_nm" value="<c:out value='${command.srch_event_nm}'/>" type="text" style="width:150px;" alt="<spring:message code="las.page.field.lawservice.eventnm"/>" maxlength="100" />
									</td>
							  </tr>
							  <tr>
									<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
									<td colspan=3" >
										<select id='srch_lawfirm_id' name='srch_lawfirm_id' style='width:250px;'>
											<option value=''><spring:message code="las.page.field.lawservice.select"/></option>	
											<c:forEach var="list" items="${lawfirmList}">
											<option value="<c:out value='${list.lawfirm_id}'/>" <c:if test='${list.lawfirm_id == command.srch_lawfirm_id}'> selected </c:if>><c:out value='${list.lawfirm_nm}'/></option>			
											</c:forEach>
										</select>
										<input id="srch_lawfirm_nm" name="srch_lawfirm_nm" value="<c:out value='${command.srch_lawfirm_nm}'/>" type="text" style="width:150px;" alt="<spring:message code="las.page.field.lawservice.lawfirmnm"/>" maxlength="100"/>
									</td>
							  </tr>
							   <tr>
									<th><spring:message code="las.page.field.lawservice.gbn1"/></th>
									<td>
										<select id="srch_kbn1" name="srch_kbn1" style="width:154px;" onChange="StReload_partin(this.value)">
										</select>
										<span id="partin2"></span>
									</td>
									<th><spring:message code="las.page.field.lawservice.nation"/></th>
									<td>
										<input id="srch_nation_nm"  name="srch_nation_nm" value="<c:out value='${command.srch_nation_nm}'/>" type="text" style="width:150px;" onclick="javascript:NationPop()" readoly />
										&nbsp;<a href="javascript:NationPop('srch');"><spring:message code="las.page.field.lawservice.selectnation"/></a>
									</td>
							  </tr>
							  <tr>
									<th><spring:message code="las.page.field.lawservice.maniftree"/></th>
									<td colspan=3" >
										<input id="srch_mainftre_estmt" name="srch_mainftre_estmt" value="<c:out value='${command.srch_mainftre_estmt}'/>" type="text" style="width:403px;" alt="<spring:message code="las.page.field.lawservice.maniftree"/>" maxlength="2000"/>
									</td>
							  </tr>
							</table>
						</td>
						<td class="vb tC"><a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.lawservice.read"/>"/></a></td>
						</tr>
					</table>
				</div>
	  </div>
			<!--//search-->
	
			
		<div class="t_titBtn">
			<div class="btn_wrap_t">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="las.page.button.lawservice.regist"/></a></span>
			</div>
		</div>

		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
		  <colgroup>
		  <col width="7%" />
		  <col width="58%" />
		  <col width="20%" />
		  <col width="15%" />
		  </colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order"/></th>
			  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
			  <th><spring:message code="las.page.field.lawservice.nation"/></th>
			  <th><spring:message code="las.page.field.lawservice.contractday"/></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">
							<c:forEach var="list" items="${lom}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td><c:out value='${list.rn}'/></td>
								<td class="tL">
									<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
						            	<nobr><a href='javascript:detailView("<c:out value='${list.lawfirm_id}'/>");'>
						          		<c:out value='${list.lawfirm_nm}'/></a></nobr>
						          	</div>
					            </td>
					            <td class="tC"><c:out value='${list.nation}'/></td>
					            <td class="tC"><c:out value='${list.reg_dt}'/></td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="4" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
		<!-- //list -->
		<!-- pagination -->
		<div class="pagination">
			<%=pageUtil.getPageNavi(pageUtil, "") %>
		</div>
		<!-- //pagination -->
	</div>
	<!-- //content -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
<!-- //wrap -->
</form:form>
</body>
</html>