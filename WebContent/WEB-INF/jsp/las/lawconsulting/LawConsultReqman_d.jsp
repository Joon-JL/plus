<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>

<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
    // 메뉴네비게이션 스트링
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	// 자문 권한 관련자
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript">
    $(document).ready(function(){
        initPage();
        $('#cnsd_opnn').html($('#cnsd_opnn_hidden').val());
        
       
    });

    function initPage(){

        //임시저장, 의뢰, 재의뢰 시
        if(frm.prgrs_status.value == 'S01' || frm.prgrs_status.value == 'S02' || frm.prgrs_status.value == 'S04'){
        frm.target          = "fileList";
        frm.action = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";
        frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();
        }
        //회신 시
        if(frm.prgrs_status.value == 'S03'){
        frm.target          = "fileList";
        frm.file_midclsfcn.value = "F00301";
        frm.action = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";
        frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();

        if(frm.extnl_cnsltyn.value == 'Y'){
            frm.target          = "fileList3";
            frm.file_midclsfcn.value = "F00302";
            frm.action = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = "fileInfos3";
            frm.fileFrameName.value = "fileList3";
            getClmsFilePageCheck('frm');
            //frm.submit();
            }
        }
    }

    function init(){

        alertMessage('<c:out value='${command.return_message}'/>') ;
    }


    function remove(){
        if(confirm("<spring:message code="secfw.msg.ask.delete" />")) {
            viewHiddenProgress(true) ;
            var f = document.frm ;
            f.method.value = "deleteLawConsult" ;
            f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
            f.target = "_self" ;
            f.submit() ;
        }
    }

    function goModifyForm() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.method.value = "forwardModifyLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goHoldForm() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.check_prgrs_status.value = 'hold';
        f.method.value = "forwardHoldLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function send(){
        var f = document.frm;
        var confirmMessage = "<spring:message code='secfw.msg.ask.send' />";
        if(confirm(confirmMessage)){
        	viewHiddenProgress(true) ;
            //hstry_no 가 0 보다 크면 재의뢰
            if(f.hstry_no.value > 0)
                f.check_prgrs_status.value = "resend";
            //hstry_no = 0 이면 의뢰
            else
                f.check_prgrs_status.value = "send";

            f.method.value = "modifyStatusLawConsult" ;
            f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
            f.target = "_self" ;
            f.submit();
        }
    }

    function goList() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.method.value = "listLawConsultRequest" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goDetail(cnslt_no, hstry_no) {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.cnslt_no.value = cnslt_no;
        f.hstry_no.value = hstry_no;
        f.method.value = "forwardDetailLawConsultReqman" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goInsertForm() {
        
    	if("<c:out value='${lom.vacation_yn}'/>" == "Y")
    		alert("<spring:message code='las.msg.alert.reRequest.onVaction'/>");
    	
    	viewHiddenProgress(true) ;
        var f = document.frm ;
        f.check_prgrs_status.value = "resend";
        f.method.value = "forwardInsertLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onLoad="init();initPage()" autocomplete="off">
<div id="wrap">
	<!-- container -->
  	<div id="container">
    <!-- Location -->
    <div class="location">
    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
    <!-- //Location -->
    <!-- title -->
    <div class="title">
    	<%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /><!-- 자문리스트 --></h3> */ --%>
      	<h3><spring:message code="las.page.field.lawconsulting.lawReqAdv" /></h3>
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
		<input type="hidden" name="cnslt_no" value="<c:out value='${command.cnslt_no}'/>"/>
		<input type="hidden" name="hstry_no" value="<c:out value='${command.hstry_no}'/>"/>
		<input type="hidden" name="consult_type" value="<c:out value='${lom.consult_type}'/>"/>
		<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
		<input type="hidden" name="check_prgrs_status" value=""/>
		<input type="hidden" name="extnl_cnsltyn" value="<c:out value='${lom.extnl_cnsltyn}'/>"/>
		<!-- 검색 정보 -->
		<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"/>
		<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>"/>
		<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
		<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>"/>
		<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>"/>
		<input type="hidden" name="contents" value=""/>

				
		<!-- 페이지 포워딩 구분 -->
		<input type="hidden" name="fwd_gbn" value="req"/>
		
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos"   value="" />
		<input type="hidden" name="fileInfos2"  value="" />
		<input type="hidden" name="fileInfos3"  value="" />
		<input type="hidden" name="file_bigclsfcn"  value="F003" />
		<input type="hidden" name="file_midclsfcn"  value="F00301" />
		<input type="hidden" name="file_smlclsfcn"  value="" />
		<input type="hidden" name="ref_key"     value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
		<input type="hidden" name="view_gbn"    value="download" />
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
		    
      	<!-- upper button -->
      	<div class="t_titBtn">
        	<div class="btn_wrap">
        	
          		<c:if test="${lom.prgrs_status == 'S01'}">
          			<!-- 참조인은 수정권한을 배제해야함 -->
          			<c:if test = "${ccYn !='Y'}">
			            <span class="btn_all_w"><span class="edit"></span><a href="javascript:send()"><spring:message code="las.page.field.lawconsulting.reqAdv"/></a></span>
			            <span class="btn_all_w"><span class="modify"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			            <span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.lawconsult.delete" /></a></span>
					</c:if>
          		</c:if>
          		
          		<c:if test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
            		<c:if test="${command.hstry_no == maxHstryNo}">
            		<c:if test = "${ccYn !='Y'}">
              			<span class="btn_all_w"><span class="modify"></span><a href="javascript:goHoldForm()"><spring:message code="las.page.button.lawconsult.hold" /></a></span>
            		</c:if>
            		</c:if>
          		</c:if>
          		
          		<c:if test="${lom.prgrs_status == 'S03' || lom.prgrs_status == 'S05'}">
            		<c:if test="${command.hstry_no == maxHstryNo}">
            		<c:if test = "${ccYn !='Y'}">
              			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.resend" /></a></span>
            		</c:if>
            		</c:if>
          		</c:if>
          
          		<!-- 프린트 / 목록이동  -->
          		<span class="btn_all_w"><span class="print"></span><a href="javascript:goPrint()"><spring:message code="las.page.button.lawconsult.print" /></a></span>
          		<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
        	</div>
      	</div>
      	<!-- //upper button -->
      	<!-- view -->
		
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
		<colgroup>
          	<col width="15%"/>
         	<col width="35%" />
          	<col width="15%" />
          	<col width="35%"/>
		</colgroup>
		<tbody>
        <c:choose>
          	<c:when test="${lom.prgrs_status == 'S05'}">
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
	              	<td colspan="3"><c:out value='${lom.prgrs_status_name}'/></td>
	            </tr>
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
	              	<td colspan="3"><c:out value='${lom.hold_cause}' escapeXml="false"/></td>
	            </tr>
          	</c:when>
          	<c:when test="${lom.prgrs_status == 'S07'}">
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
	              	<td colspan="3"><c:out value='${lom.prgrs_status_name}'/></td>
	            </tr>
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsulting.rejectBy"/></th>
	              	<td colspan="3"><c:out value='${lom.mod_nm}' escapeXml="false"/></td>
	            </tr>
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
	              	<td colspan="3"><c:out value='${lom.rejct_cause}' escapeXml="false"/></td>
	            </tr>
			</c:when>
			<c:otherwise>
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.title" /></th>
	              	<td colspan="3"><c:out value='${lom.title}' escapeXml='false'/></td>
	            </tr>
	            <tr>
	              	<th><spring:message code="las.page.field.speakconsult.reqman_nm" /></th>
	              	<td><c:out value='${lom.reg_nm}'/>/<c:out value='${lom.regman_jikgup_nm}'/></td><!-- 의뢰자 / 등록자 -->
	              	<th><spring:message code="las.page.field.lawconsult.department" /></th>
	              	<td><c:out value='${lom.reg_dept_nm}'/></td> 
	            </tr>
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
	              	<td><c:out value='${lom.respman_nm}'/></td> <!-- 검토자 -->
	              	<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
	              	<td><c:out value='${lom.prgrs_status_name}'/></td><!-- 진행상태 -->
	            </tr>
	            
	            <tr>
	              	<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
	              	<td><c:out value='${lom.reg_dt}'/></td>
	              	<!--!@#2013.11.22 : 회신요청일 추가 (Requested date for reply) -->
	              	<th><spring:message code="clm.page.field.contract.request.returndt"/></th> <!-- 회신요청일 -->
	          	  	<td><c:out value='${lom.req_reply_dt}'/></td>
	            </tr>
	            
	          	<tr><!-- 자문유형 -->
	              	<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
	              	<td colspan=3><c:out value='${lom.consult_type_name}'/></td>
				</tr>
				
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
					<td colspan="3">
<% 
						if(listCa !=null){
							for(int j=0;j<listCa.size();j++){	
								ListOrderedMap lom = (ListOrderedMap)listCa.get(j);
%>
								<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("respman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("respman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("respman_jikgup") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("resp_dept") %>" />
								- <%=lom.get("respman_nm") %>/<%=lom.get("respman_jikgup") %>/<%=lom.get("resp_dept")%><BR/>					
<% 
							}
						}
%>
					</td>
				</tr>
				<c:choose>
					<c:when test="${lom.prgrs_status == 'S03'}">
                		<tr >
		                	<th>
		                    	<c:if test="${lom.dmstfrgn_gbn == 'H'}">
		                      		<spring:message code="las.page.field.lawconsult.lasopnn" />
			                    </c:if>
			                    <c:if test="${lom.dmstfrgn_gbn == 'F'}">
			                      	<spring:message code="las.page.field.lawconsulting.lgAbGrCmt"/>
			                    </c:if>
			                    <c:if test="${lom.dmstfrgn_gbn == 'IP'}">
			                      	<spring:message code="las.page.field.lawconsulting.ipCntOpi"/>
			                    </c:if>
		                	</th>
		                	<input type="hidden" id="cnsd_opnn_hidden" value="<c:out value='${lom.cnsd_opnn}' />"/>
							<td colspan="3" ><div id="cnsd_opnn" style="height:200px;overflow-y:scroll;"></div></td>	
							    
                		</tr>
               			<c:if test="${lom.grpmgr_re_yn == 'Y'}">
		               		<tr>
		                 		<th><spring:message code="las.page.field.lawconsult.apbt_opnn" /></th>
		                 		<td colspan="3"><c:out value='${lom.apbt_opnn}' escapeXml="false"/></td>
		               		</tr>
               			</c:if>
                		<tr>
                  			<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
                  			<td colspan="3">
	                  			<!-- Sungwoo Replaced scroll option 2014/08/04 -->
                  				<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
                  			</td>
						</tr>
					</c:when>
				<c:otherwise>
                	<tr>
                  		<th><spring:message code="las.page.field.lawconsult.cont" /></th>
                  		<td colspan="3"><c:out value='${lom.cont}' escapeXml="false"/></td>
                	</tr>
                	<tr>
                  		<th><spring:message code="las.page.field.lawconsult.attachfile" /></th>
                  		<td colspan="3">
	                  		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
                  			<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
                  		</td>
                	</tr>
				</c:otherwise>
			</c:choose>
            
            <c:if test="${lom.prgrs_status == 'S03'}">
			<c:if test="${lom.extnl_cnsltyn == 'Y'}">
				<tr>
                  	<th><spring:message code="las.page.field.lawconsult.extnlconsult" /></th>
                  	<td colspan="3">
                    	<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
                      		<colgroup>
								<col width="50%" />
								<col width="25%" />
								<col width="25%" />
                      		</colgroup>
                      		<thead>
	                        	<tr>
			                        <th><spring:message code="las.page.field.lawconsult.comp_nm" /></th>
			                        <th><spring:message code="las.page.field.lawconsult.cnslt_amt" /></th>
			                        <th><spring:message code="las.page.field.lawconsult.crrncy_unit" /></th>
	                        	</tr>
                      		</thead>
	                      	<tbody>
		                        <c:forEach var="list" items="${extnlcompList}" varStatus="cnt">
		                          	<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			                            <td class="tL">
			                              	<c:out value='${list.comp_nm}' escapeXml="false"/></a>
			                            </td>
			                            <!-- <td><c:out value='${list.cnslt_amt}'/></td> -->
			                            <td><fmt:formatNumber value="${list.cnslt_amt}" pattern="#,###"/></td>
			                            <td><c:out value='${list.crrncy_unit}'/></td>
		                          	</tr>
		                        </c:forEach>
							</tbody>
					</table>
				</td>
			</tr>
            <tr>
            	<th><spring:message code="las.page.field.lawconsult.extnl_attachfile" /></th>
                <td colspan="3">
	                <!-- Sungwoo Replaced scroll option 2014/08/04 -->
                	<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
                </td>
			</tr>
		</c:if>
        </c:if>
	</c:otherwise>
	</c:choose>
    </tbody>
    </table>
    <!-- //view -->
    <div class="title_basic">
        <h4><spring:message code="las.page.field.lawconsult.history" /><!-- 의뢰회신 List --></h4>
    </div>
    <!-- list -->
      <table border="0" cellspacing="0" cellpadding="0" class="list_basic">
        <colgroup>
          <col width="50%">
          <col width="21%" />
          <col width="21%" />
          <col width="8%" />
        </colgroup>
        <thead>
          <tr>
            <th><spring:message code="las.page.field.lawconsult.title" /></th>
            <th><spring:message code="las.page.field.speakconsult.reqman_nm" />/<spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
            <th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
            <th><spring:message code="las.page.field.lawconsult.dt" /></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="list" items="${historyList}" varStatus="cnt">
            <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
              <td class="tL">
                <c:choose>
                  <c:when test="${list.cnslt_pos == 0 }">
                  </c:when>
                  <c:otherwise>
                    <c:forEach begin="1" end="${list.cnslt_pos}">
                      &nbsp;
                    </c:forEach>
                    <img src="<%=IMAGE%>/icon/icon_reply.gif" alt="" />
                  </c:otherwise>
                </c:choose>
                <a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')">
                <c:if test="${command.hstry_no == list.hstry_no}"><b></c:if>
                  [<c:out value='${list.prgrs_status_name}'/>] <c:out value='${list.title}' escapeXml="false"/></a>
                <c:if test="${command.hstry_no == list.hstry_no}"></b></c:if>
              </td>
              <td class="tC"><c:out value='${list.reg_nm}'/></td>
              <td class="tC"><c:out value='${list.respman_nm}'/></td>
              <td class="tC"><c:out value='${list.reg_dt_date}'/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <!-- //list -->
      
      </form:form>
    </div>
    </div>
    <!-- //content -->
    
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  </div>
  <!-- //container -->
</div>
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>

<!-- footer  -->
    <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
    <!-- // footer -->
</body>
</html>