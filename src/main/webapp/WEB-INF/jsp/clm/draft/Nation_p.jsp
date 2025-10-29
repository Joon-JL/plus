<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파  일  명 : Nation_p.jsp
 * 프로그램명 : 국가검색 팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

    //class control
    function nationOnLoad(val){
    	var frm = document.frm;
    	
    	$('#tab_'+val).addClass("on");
    	
    	for(var i = 1; i <= 26 ; i++){
    		if(i != val){
    			$('#tab_'+i).removeClass("on");
    		}
    	}
    }

    //tab 눌렀을 시
    function tabClick(val, gu){
    	nationOnLoad(val);//class control
    	
    	frm.target = "_self";
    	frm.action = "<c:url value='/clm/draft/nation.do' />";
    	frm.method.value = "listNation";
    	frm.gu.value = gu;
    	frm.tabVal.value = val;
    	frm.submit();
    }
    
    //tr 눌렀을 시
    function rtnVal(cd, cdNm){
    	var frm = document.frm;
		var nationInfo = new Object();
		
		nationInfo.nation   = cd;
		nationInfo.nationNm = cdNm;
		if(frm.isModify.value == 'Y'){
			if(frm.isSearch.value == 'Y'){
				opener.setNation(nationInfo);
			}
			else{
				opener.setNation2(nationInfo);	
			}
		}
		else{
			opener.setNation(nationInfo);
		}
		
		window.close();
    }
    
</script>
</head>
<body class="pop" onLoad="javascript:nationOnLoad('<c:out value='${command.tabVal}'/>');">
<!-- header -->
<h1><spring:message code="clm.page.title.nation.listTitle" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<!-- content -->
	<div class="pop_content"  style='height:'>
	
	<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<!-- key form-->
<input type="hidden" name="gu" id="gu" value="" />
<input type="hidden" name="tabVal" id="tabVal" value="<c:out value='${command.tabVal}'/>" />
<!-- 첨부파일정보 -->

<input type="hidden" name="isSearch" id="isSearch" value="<c:out value='${command.isSearch}'/>"  />
<input type="hidden" name="isModify" id="isModify" value="<c:out value='${command.isModify}'/>"  />

<!-- // **************************** Form Setting **************************** -->
		<!-- tab -->
	  	<div class="tab_box">
			<ul class="tab_basic">
		      <li id="tab_1" class="on"><a href="javascript:tabClick('1','A');">A</a></li>
		      <li id="tab_2"><a href="javascript:tabClick('2','B');">B</a></li>
			  <li id="tab_3"><a href="javascript:tabClick('3','C');">C</a></li>
			  <li id="tab_4"><a href="javascript:tabClick('4','D');">D</a></li>
			  <li id="tab_5"><a href="javascript:tabClick('5','E');">E</a></li>
			  <li id="tab_6"><a href="javascript:tabClick('6','F');">F</a></li>
			  <li id="tab_7"><a href="javascript:tabClick('7','G');">G</a></li>
			  <li id="tab_8"><a href="javascript:tabClick('8','H');">H</a></li>
			  <li id="tab_9"><a href="javascript:tabClick('9','I');">I</a></li>
			  <li id="tab_10"><a href="javascript:tabClick('10','J');">J</a></li>
			  <li id="tab_11"><a href="javascript:tabClick('11','K');">K</a></li>
			  <li id="tab_12"><a href="javascript:tabClick('12','L');">L</a></li>
			  <li id="tab_13"><a href="javascript:tabClick('13','M');">M</a></li>
			  <li id="tab_14"><a href="javascript:tabClick('14','N');">N</a></li>
			  <li id="tab_15"><a href="javascript:tabClick('15','O');">O</a></li>
			  <li id="tab_16"><a href="javascript:tabClick('16','P');">P</a></li>
			  <li id="tab_17"><a href="javascript:tabClick('17','Q');">Q</a></li>
			  <li id="tab_18"><a href="javascript:tabClick('18','R');">R</a></li>
			  <li id="tab_19"><a href="javascript:tabClick('19','S');">S</a></li>
			  <li id="tab_20"><a href="javascript:tabClick('20','T');">T</a></li>
			  <li id="tab_21"><a href="javascript:tabClick('21','U');">U</a></li>
			  <li id="tab_22"><a href="javascript:tabClick('22','V');">V</a></li>
			  <li id="tab_23"><a href="javascript:tabClick('23','W');">W</a></li>
			  <li id="tab_24"><a href="javascript:tabClick('24','X');">X</a></li>
			  <li id="tab_25"><a href="javascript:tabClick('25','Y');">Y</a></li>
			  <li id="tab_26"><a href="javascript:tabClick('26','Z');">Z</a></li>
	        </ul>
		</div>
		<!-- //tab -->
		
		<!-- List -->
        <div class='tableWrap'>
			<div class='tableone'>
		        <table class="list_basic">
				<colgroup>
				    <col width="100%"/>
			  	</colgroup>
			  	<thead>
					<tr>
					    <th><spring:message code="clm.page.field.nation.nation"/></th>
					</tr>
				</thead>
				</table>
			</div>
		</div>
		<style>
			.h_120 {height:255px;}
			*html .h_120 {height:255px;}
		</style>
				
		<div class='tabletwo h_120'>
			<table class="list_scr">
		 	<tbody>
		 	    <c:choose>
		 	        <c:when test="${command.cnt eq 'Y'}">
			 	    <c:forEach var="list" items="${nationList}">
				 	    <tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';" onClick="javascript:rtnVal('<c:out value='${list.cd}'/>','<c:out value='${list.cd_nm}'/>');">
				 	        <td class="tL"><c:out value="${list.cd_nm}"/></td>
				 	    </tr>
			 	    </c:forEach>
			 	    </c:when>
			 	    <c:otherwise>
			 	        <tr>
					        <td class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					    </tr>
			 	    </c:otherwise>
		 	    </c:choose>
		    </tbody>
		</table>
		</div>
		<!-- //list -->
		</form:form>
		
	  </div>
	</div>
	
	<!--footer -->
	<div class="pop_footer">
		<div class="btn_wrap fR" >
			<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="secfw.page.field.approval.cancel" /></a></span>
		</div>
	</div>
	<!-- //footer -->


<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

</body>
</html>