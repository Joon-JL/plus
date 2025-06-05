<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : StdContract_l.jsp
 * 프로그램명 : 표준계약서 리스트
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	session.removeAttribute("main_approach");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_sys_cd=LAS&combo_grp_cd=N1_I&combo_type=S&useman_mng_itm1=S&combo_del_yn=N&combo_selected=<c:out value='${command.srch_prgrs_status}'/>');
	    //getCodeSelectByUpCd("frm", "srch_reg_dept", '/common/clmsCom.do?method=getUnitOrgnzComboHTML&up_orgnz_cd=B00000004&combo_abbr=A&combo_type=S&locale='+'<c:out value='${isForeign}'/>'+'&combo_selected='+'<c:out value='${command.srch_reg_dept}'/>');
	   
	    initCal("srch_start_ymd");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_ymd","20110803163201974_0000000117");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
        
 
	});
	
	function search(){
		goPage(1) ;
	}
	
	function goPage(page) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
	
		var startDt = f.srch_start_ymd.value ;
		var endDt = f.srch_end_ymd.value ;
	
		if(startDt>endDt){
			viewHiddenProgress(false) ;
			alert("<spring:message code="clm.page.msg.common.announce004" />") ;
			f.srch_start_ymd.focus() ;
			return ;
		}
		if(f.srch_reg_nm.value==''){f.srch_reg_id.value='';}

		f.cnslt_no.value = "";
		f.hstry_no.value = 0;
		f.curPage.value = page ;
		f.method.value = "listStdContract" ;
		f.action = "<c:url value='/clm/draft/stdContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	//상세 (팝업으로 처리 할 것)
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.method.value = "forwardDetailStdContract" ;
		f.action = "<c:url value='/clm/draft/stdContract.do' />" ;
	    frm.target = "_self";
		
		f.submit() ;
		
	}
	
	/**
     * ESB 임직원 조회
	 */
	function searchEmployee() {
	    var frm = document.frm;
	    frm.srch_user_cntnt.value = frm.srch_reg_nm.value;
	    var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	        frm.srch_reg_nm.focus();
	        return;
	    }

	    PopUpTargetWindowOpen('', 800, 450, true,"PopUpWindowSt");
	    frm.target = "PopUpWindowSt";

	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
	    //frm.srch_user_cntnt_type.value = "userId"; //마이싱글아이디로 검색(tb_com_user의 user_id 아님, tb_com_user의 single_id 임)
	    //frm.srch_user_cntnt_type.value = "epid"; //EPID로 검색(tb_com_user의 user_id임)

	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.submit();

	}
	
	/**
	* 임직원 조회정보 결과 setting
	*/
	function setUserInfos(obj) {
	    var userInfo = obj;
	 	if(userInfo.epid=="") {
	    	return;
	    }
	    
	 	frm.srch_reg_nm.value = userInfo.cn;
	 	frm.srch_reg_id.value = userInfo.epid;
	 	
	}
	
	
	/**   
	*표준계약서 선택 시
	*/
	function StdSelect(arg1,arg2){
		var frm = document.frm;	
		frm.cnslt_no.value		= arg1;				// 자문번호
		frm.hstry_no.value		= arg2;				// 이력번호
	}
	
	/**   
	*표준계약서 확인버튼클릭시
	*/
	function confirm(){
		var frm = document.frm;	
		if(frm.cnslt_no.value == "") {
			alert("<spring:message code="clm.page.msg.draft.announce023" />");
			return; 	
		}
		opener.returnStdContract(frm.hstry_no.value, frm.cnslt_no.value);
		window.close();
		
	}
	
	/**   
	*취소 버튼클릭시
	*/
	function can(){
		window.close();
	}
</script>

</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="viewDlgAlert('<c:out value='${command.return_message}'/>')" autocomplete="off" class="pop">

	<!-- header -->
	<h1><spring:message code="clm.page.msg.draft.stdContList" /></h1>
	<!-- //header -->

	<div class="pop_area">
	<div class="pop_content"  style='height:375px;'>
		
		
		
		<form:form name="frm" id="frm" autocomplete="off">
			<input type="hidden" name="method" value=""/>
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
			<input type="hidden" name="cnslt_no" />
			<input type="hidden" name="hstry_no" />
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
		
		
				<!--90% 안에서 작업하기 search-->
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
									<col width="30%"/>
									<col width="10%"/>
									<col width="20%"/>
									<col width="10%"/>
									<col width="20%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawconsult.reg_dt"/><!--의뢰일--></th>
									<td>
										 <input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02"/> ~
    									 <input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02"/></td>
									<th><spring:message code="las.page.field.lawconsult.respman_nm" /><span class="astro"></span></th>
							    	<td>
							    		<span style="width:35%;">
							      			<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text_full"/>
							      		</span>
							      	</td>
									<th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
									<td>
										<span style="width:35%;">
								    		<input id="srch_reg_nm" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}' escapeXml='false'/>" type="text"  class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" /> 
								    	</span>
								    </td>
								</tr>
								<tr>
									<!-- 소속회사 검색 없음
									<th><spring:message code="clm.page.msg.draft.announce101" /></th>
								  	 
								  	<td>
								  		<span style="width:35%;">
								  		    <input type="hidden" id="srch_reg_dept" name="srch_reg_dept" />
								  		    <input id="srch_reg_dept_nm" name="srch_reg_dept_nm" value="<c:out value='${command.srch_reg_dept_nm}' escapeXml='false'/>" type="text" readonly="readonly" class="text_full" style="width:80%"/>
								      	</span>
								    </td>
								    -->
								    <th><spring:message code="las.page.field.lawconsult.title" /></th>
								    <td><input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text_full" /></td>
								    <th><spring:message code="clm.page.msg.common.content" /></th>
								    <td><input id="srch_cont" name="srch_cont" value="<c:out value='${command.srch_cont}' escapeXml='false'/>" type="text" class="text_full"/>  </td>
								</tr>
							</table>
						</td>
						<td class="vb tC"><a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a></td>
						</tr>
					</table>
				</div>
			</div>
					
			<!-- list -->
			<table class="list_basic mt20">
			 <colgroup>
			   <col width="8%" />
			   <col width="43%"/>
			   <col width="15%" />
			   <col width="13%" />
			   <col width="13%" />
			   <col width="8%" />
		     </colgroup>
			  <thead>
			    <tr>
			      <th><spring:message code="clm.page.msg.common.select" /></th>
			      <th><spring:message code="las.page.field.lawconsult.title" /></th>
			      <th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
			      <th><spring:message code="clm.page.field.manageCommon.reqDeptNm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
				</tr>
		      </thead>
			  <tbody>
				<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">				
						<c:forEach var="list" items="${command.lawconsult_list}">
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
							<td class="tC"><input name="sel_no" type="radio" class="radio" onclick="javascript:StdSelect('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>');" /></td>
						    <td class="tL"><a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')"><c:out value='${list.title}' escapeXml="false"/></a></td>
							<td><c:out value='${list.reg_nm}'/></td>
							<td><c:out value='${list.reg_dept_nm}'/></td>
							<td><c:out value='${list.respman_nm}'/></td>
							<td><c:out value='${list.reg_dt_date}'/></td>
						</tr>
						</c:forEach>
			   		 </c:when>
			   		 <c:otherwise>
			     	     <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			      	      <td colspan="6"><spring:message code="las.page.msg.noResult" /></td>
			      	    </tr>
			   		 </c:otherwise>
				</c:choose>
		      </tbody>
		  </table>
			<!-- //list -->
			
			
			<div class="total_num">
				Total: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="las.page.field.lawconsult.totalrow" />
			</div>
			
			<div style='color:#604732; border:1px solid #ccc2a9;padding:3px 5px 5px 5px; font:normal 11px Nanum Gothic, Malgun Gothic, Dotum;letter-spacing:-0.02em; margin:10px 0 20px 0; background-color:#fcfcf1'>
                <img src="<%=IMAGE%>/icon/ico_alert01.gif" />  <span style='color:#b94010'><spring:message code="clm.page.msg.draft.announce100" /></span><br/>
                <span style='margin-left:18px;'></span><spring:message code="clm.page.msg.draft.announce017" />
            </div>
			
			
			
			
		
		
		</form:form>
		<!-- //content -->
	</div></div>
	<!-- //container -->


<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w"><span class="confirm"></span><a href="javascript:confirm()"><spring:message code='clm.page.button.confirm' /></a></span>
		<span class="btn_all_w"><span class="save"></span><a href="javascript:can()"><spring:message code="clm.page.msg.common.cancel" /></a></span>
				
	</div>
</div>
<!-- //footer -->




<!-- //container -->

		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>
