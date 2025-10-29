<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%--
/**
 * 파  일  명 : LawStaConsultGrpmgr_l.jsp
 * 프로그램명 : 표준계약서 리스트 (그룹장/담당자 페이지)
 * 설      명 : 표준계약서의 리스트만을 보여주게 됩니다.
 * 작  성  자 : 
 * 작  성  일 : 2013.04
 */
--%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	session.removeAttribute("main_approach");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		// 2014-02-10 Kevin. TO-DO 리스트의 값 로드가 중복으로 발생한다. 페이지 로딩 시간이 오래 걸리는 상황에서는 응답시간 지연이 발생하여 주석처리 함. 데이터 Refresh는 메뉴 클릭으로 대신한다.
		/* window.parent.frames['leftFrame'].refreshCount(); */ 
		
	    getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_sys_cd=LAS&combo_grp_cd=N1_I&combo_type=S&useman_mng_itm1=S&combo_del_yn=N&combo_selected=<c:out value='${command.srch_prgrs_status}'/>');
	    getCodeSelectByUpCd("frm", "srch_reg_dept", '/common/clmsCom.do?method=getUnitOrgnzComboHTML&up_orgnz_cd=B00000004&combo_abbr=A&combo_type=S&locale='+'<c:out value='${isForeign}'/>'+'&combo_selected='+'<c:out value='${command.srch_reg_dept}'/>');
	    getCodeSelectByUpCd("frm", "srch_elcomp", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=USERCOMP&combo_type=T&combo_del_yn=N&sel_grd=<c:out value='${command.sSel_grd}'/>&combo_selected='+'<c:out value='${command.srch_elcomp}'/>');
	    initCal("srch_start_ymd");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_ymd","20110803163201974_0000000117");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
        
        //if(frm.srch_reception.value == "Y")
		//	frm.srch_reception.checked = true;
		//else
		//	frm.srch_reception.checked = false;
	    
        <%//if(ClmsBoardUtil.searchRole(request, "RA00")){ %>
        //$('[name=srch_reception]').hide();
        //$('#receipt').hide();
        <%//}%>
	});
	
	$(function(){
		
		$("#srch_respman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				search();
			}
		});
		$("#srch_title").keydown(function(event) {
			if(event.keyCode == "13") {
				search();
			}
		});
		$("#srch_cont").keydown(function(event) {
			if(event.keyCode == "13") {
				search();
			}
		});
		 
	});
	
	function search(){
		goPage(1) ;
	}
	
	
	function init(){
    	alertMessage('<c:out value='${command.return_message}'/>');
    }
	
	function goPage(page) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
	
		var startDt = f.srch_start_ymd.value ;
		var endDt = f.srch_end_ymd.value ;
	
		if(startDt>endDt){
			viewHiddenProgress(false) ;
			alert("<spring:message code='las.page.field.lawconsulting.setFromDateEarly'/>") ;
			f.srch_start_ymd.focus() ;
			return ;
		}
		
		//if(f.srch_reception.checked){
		//	f.srch_reception.value = 'Y';
		//}
		//else{
		//	f.srch_reception.value = 'N';
		//}
		
		f.cnslt_no.value = "";
		f.hstry_no.value = 0;
		f.curPage.value = page ;
		f.method.value = "listStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.method.value = "forwardDetailStaLawConsult" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	/*자문유형(상담종류) 팝업*/
	function popup(){
		var f = document.frm ;
		f.cnslt_no.value = "";
		f.hstry_no.value = 0;
		f.method.value = "forwardPopupLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		PopUpWindowOpen('', 700, 380, true);
		frm.target = "PopUpWindow";
		f.submit() ;
	}
	
	/* 팝업 창에서 체크한 상담종류에 대한 값을 받음 */
	function returnType(type, type_name){
		
		frm.srch_consult_type.value = type;
		frm.srch_consult_type_name.value = type_name;
		frm.srch_consult_type_name.setAttribute('width',frm.srch_consult_type_name.value.length);
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

	    PopUpWindowOpen('', 800, 450, true);
	    frm.target = "PopUpWindow";

	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
	    //frm.srch_user_cntnt_type.value = "userId"; //마이싱글아이디로 검색(tb_com_user의 user_id 아님, tb_com_user의 single_id 임)
	    //frm.srch_user_cntnt_type.value = "epid"; //EPID로 검색(tb_com_user의 user_id임)

	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.submit();

	    frm.target = "";
	}
	
	function setUserInfos(obj) {
		var f = document.frm;
	    var userInfo = obj;
	    
	    f.srch_reg_nm.value = userInfo.cn;
	    f.srch_reg_id.value = userInfo.epid;
	}
	    
</script>

</head>

<body onload="init()" autocomplete="off">



<div id="wrap">

	<!-- container -->
	<div id="container">
		<!-- Location -->
        <div class="location">
            <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
  		  <!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.lawconsulting.stdConReview"/></h3>
		</div>
		<!-- //title -->
		<!-- content -->	
		<div id="content">
		<div id="content_in">
		<form:form name="frm" id="frm" autocomplete="off">
		<input type="hidden" name="method" value=""/>
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
		<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
		<input type="hidden" name="cnslt_no" />
		<input type="hidden" name="hstry_no" />
		<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
		<input type="hidden" id="isStdCont" name="isStdCont" value="Y"/>
		<!-- input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/ -->
		
			<div class="newstyle-area">
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
									<col width="23%"/>
									<col width="10%"/>
									<col width="23%"/>
									<col width="10%"/>
									<col width="24%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawconsult.reg_dt"/><!--의뢰일--></th>
									<td>
										 <input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02" style='width:74px'/> ~
    									 <input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02" style='width:73px'/></td>
									<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><span class="astro"></span></th>
							        <td>
							        	<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text" style='width:200px'/>
							    	</td>
									<th><spring:message code="las.page.field.lawconsult.prgrs_status" /><!--진행상황--></th>
									<td>
									  <select name="srch_prgrs_status" id="srch_prgrs_status"  style='width:204px'>
								      </select>
									</td>
								</tr>
								<tr>
									<!-- 
								  <th><spring:message code="las.page.field.lawconsult.regdept" /></th>
									<td><span style="width:35%;">
									  <select name="srch_reg_dept" id="srch_reg_dept"  style='width:204px'>									 
								      </select>
									</span></td>
									 -->
									 <th><spring:message code="las.page.field.lawconsult.title" /></th>
									<td><input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text" style='width:200px' /></td>
									<th><spring:message code="las.page.field.contractManager.requBy" /></th>
									<td>
								    <input id="srch_reg_nm" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}' escapeXml='false'/>" type="text"  class="text_search" style='width:184px'/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" /></td>
									<!-- 표준계약서만 보여준다. 
									<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
									<td><span style="width:35%;">
								    <input id="srch_consult_type_name" name="srch_consult_type_name" value="<c:out value='${command.srch_consult_type_name}' escapeXml='false'/>" type="text" class="text_search"  readonly="readonly"/><img src="<%//=IMAGE%>/icon/ico_search.gif" onclick="javascript:popup()" class="cp" alt="search" /> </span></td>
								    -->
								    <input id="srch_consult_type_name" name="srch_consult_type_name" value="A20" type="hidden" class="text_search"/>
								    <th><spring:message code="clm.page.msg.common.content" /></th>
									<td><input id="srch_cont" name="srch_cont" value="<c:out value='${command.srch_cont}' escapeXml='false'/>" type="text" class="text" style='width:200px'/>  </td>
								</tr>
								<tr>
								<th><spring:message code="las.page.field.lawconsult.solo_yn" /></th>
				            	<td>
									<select name="srch_solo_yn" id="srch_solo_yn" style="width:268px" />
										<option value=""><spring:message code="las.page.field.contractManager.selectL"/></option><!-- -- 선택 -- -->
										<option value="1" <c:if test="${command.srch_solo_yn eq '1'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.dmst"/></option><!-- 공동 -->
										<option value="2" <c:if test="${command.srch_solo_yn eq '2'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.frgn"/></option><!-- 공동 -->
										<option value="3" <c:if test="${command.srch_solo_yn eq '3'}">selected</c:if>><spring:message code="las.page.field.lawconsult.alone"/></option><!-- 단독 -->
									</select>
								</td>
								<c:if test="${elUserYn == 'Y'}">
              						<th><spring:message code="clm.page.msg.common.selCompNm" /></th>
              						<td>
               							<select class="srch" name="srch_elcomp" id="srch_elcomp" style="width:205px"> </select>
              						</td>
          			 			</c:if>
								</tr>
								<!-- 
								<tr>
								
								<th id="receipt"><spring:message code="las.page.field.lawconsult.receipt" /></th>
								<td id="receipt">
									<input type="checkbox" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/>
								</td>
								
								</tr> -->
							</table>
						</td>
						<td class="vb tC"><a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>"/></a></td>
						</tr>
					</table>
				</div>
			</div>
					
			<!-- list -->
			<table class="list_basic mt20">
			 <c:choose>
              	<c:when test="${elUserYn == 'Y'}">
        			 <colgroup>
			           <col width="15%" />
			            <col width="5%" />
			            <col width="*%"/>
			            <col width="9%" />
			            <col width="9%" />
			            <col width="20%" />
			            <col width="9%" />
			            <col width="8%" />
			         </colgroup>      		
              	</c:when>
              	<c:otherwise>
              	    <colgroup>
			            <col width="5%" />
			            <col width="5%" />
			            <col width="*%"/>
			            <col width="12%" />
			            <col width="12%" />
			            <col width="12%" />
			            <col width="8%" />
			            <col width="10%" />
			          </colgroup>
              	</c:otherwise>
              </c:choose>
			  <thead>
			    <tr>
			      <c:choose>
	              	<c:when test="${elUserYn == 'Y'}">
	              		<th><spring:message code="secfw.page.field.user.comp_nm" /></th>
	              	</c:when>
	              	<c:otherwise>
	              		<th><spring:message code="las.page.field.lawconsult.no" /></th>
	              	</c:otherwise>
	              </c:choose>
	              <th><spring:message code="las.page.field.lawconsult.solo_yn" /></th>
			      <th><spring:message code="las.page.field.lawconsult.title" /></th>
			      <th><spring:message code="las.page.field.mainproject.operdiv_respman_dept_nm"/></th>
			      <th><spring:message code="las.page.field.contractManager.requBy" /></th>
			      <th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
				  <th><spring:message code="las.page.field.lawconsult.status" /></th>	
				</tr>
		      </thead>
			  <tbody>
				<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">				
						<c:forEach var="list" items="${command.lawconsult_list}">
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
							<c:choose>
				              	<c:when test="${elUserYn == 'Y'}">
				              		<td class='tC'><c:out value='${list.comp_nm}'/></td>
				              	</c:when>
				              	<c:otherwise>
				              		<td class='tC'><c:out value='${list.rm}'/></td>
				              	</c:otherwise> 
				            </c:choose>
							<td class='tC'>
				            	<c:choose>
					            	<c:when test="${list.solo_yn == '1'}">
					            		<spring:message code="las.page.field.lawconsult.cavity.dmst"/>
					            	</c:when>
					            	<c:when test="${list.solo_yn == '2'}">
					            		<spring:message code="las.page.field.lawconsult.cavity.frgn"/>
					            	</c:when>
					            	<c:when test="${list.solo_yn == '3'}">
					            		<spring:message code="las.page.field.lawconsult.alone"/>
					            	</c:when>
					            	<c:otherwise>
					            		
					            	</c:otherwise>
				            	</c:choose>
				            </td>
						    <td class="tL"><a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')"><c:out value='${list.title}' escapeXml="false"/></a></td>
						    <td class="tL"><c:out value='${list.reg_dept_nm}'/></td>
							<td class="tL"><c:out value='${list.reg_nm}'/></td>
							<td class="tL"><c:out value='${list.respman_nm}'/></td>
							<td class="tC"><c:out value='${list.reg_dt_date}'/></td>
							<td class="tC"><c:out value='${list.prgrs_status_name100}'/></td>
						</tr>
						</c:forEach>
			   		 </c:when>
			   		 <c:otherwise>
			     	     <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			      	      <td colspan="8" class="tC"><spring:message code="las.page.msg.noResult" /></td>
			      	    </tr>
			   		 </c:otherwise>
				</c:choose>
		      </tbody>
		  </table>
			<!-- //list -->
			<!-- pagination -->
			<div class="t_titBtn">
				<div class="total_num">
					Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>	
				</div>
			</div>
			<!-- //pagination -->
			
			</form:form>
		</div>
		</div>
		</div>
		<!-- //content -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<!-- //container -->
 <!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
</body>
</html>
