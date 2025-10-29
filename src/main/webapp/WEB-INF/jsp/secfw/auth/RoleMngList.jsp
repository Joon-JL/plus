<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파    일    명 : RoleMngList.jsp
 * 프 로 그 램 명 : 역할 등록
 * 작    성    자 : 금현서
 * 작    성    일 : 2009.11
 * 설          명 :
 */
--%>
<%
    // 메뉴네비게이션 스트링
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--
var title_role_cd      = '<spring:message code="secfw.page.field.role.role_cd" />';
var title_role_nm      = '<spring:message code="secfw.page.field.role.role_nm" />';
var title_comments     = '<spring:message code="secfw.page.field.role.comments" />';
var title_use_yn       = '<spring:message code="secfw.page.field.role.use_yn" />' + ' (Y/N)';

    $(function() {

        $("#allCheck")
        .click(function() {
            if(($(this).attr("checked"))) {
                $("input:checkbox[name='chkValues']")
                    .each(function(){
                        $(this).attr("checked", true);
                    });
            } else {
                $("input:checkbox[name='chkValues']")
                .each(function(){
                    $(this).attr("checked", false);
                });
            }
        });

        $(document).keydown(function(event){

            if(event.keyCode == "13") {
                var target = event.target;
                if(target.id == "srch_cntnt") {
                    listRole();
                } else {
                    return false;
                }
            }

        });

        listRole();
    });

    /**
    * 역활 조회
    */
    function listRole(displayYn) {

        var totalCnt = 0;

        var options = {
            url: "<c:url value='/secfw/roleMng.do?method=listRole' />",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                if("N" != displayYn)
                    viewHiddenProgress(true);
                return true;
            },
            success: function(responseText,returnMessage) {
                $('#roleTable tr').remove();
                if(responseText != null && responseText.length != 0) {
                    $.each(responseText, function(entryIndex, entry) {
                        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                                 +  "<td class=\'tC\'><input type=\'checkbox\' id=\'\' name=\'chkValues\' class=\'checkbox\' value=\'" + entry['role_cd'] + "\'></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'role_cds\' id=\'role_cds\' class=\'text\' value=\'" + entry['role_cd'] + "\'  fieldTitle=\'" + title_role_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'role_nms\' id=\'role_nms\' class=\'text\' value=\'" + entry['role_nm'] + "\'  fieldTitle=\'" + title_role_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text_full\' value=\'" + entry['comments'] + "\'  fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
                                 + "</tr>";
                        $('#roleTable').append(html);
                        totalCnt = entry['total_cnt'];
                    });
                } else {
                    var html = "<tr id=\'resultNotFoundRow\'><td colspan=5 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
                    $('#roleTable').append(html);
                }

                $('#roleTableCnt').text(totalCnt);
                if("N" != displayYn)
                    viewHiddenProgress(false);
            }
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 역활 추가
    */
    function newRole() {

        $('#resultNotFoundRow').remove();
        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
             +  "<td class=\'tC\'><input type=\'checkbox\' id=\'\' name=\'chkValues\' class=\'checkbox\' value=\'\'></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'role_cds\' id=\'role_cds\' class=\'text\' value=\'\'  fieldTitle=\'" + title_role_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'role_nms\' id=\'role_nms\' class=\'text\' value=\'\'  fieldTitle=\'" + title_role_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text_full\' value=\'\'  fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
             + "</tr>";
        $('#roleTable').append(html);
        $('#roleTable tr:last td :checkbox[name=chkValues]').focus();

    }

    /**
    * 역활 등록
    */
    function insertRole() {

        //등록할려는 역활가 없을경우
        if($('#roleTable tr:not(#resultNotFoundRow)').is("tr") != true) {
            alert("<spring:message code='secfw.page.field.auth.noRole'/>");
            return;
        }

        if(validateForm(document.frm) != false) {

            var options = {
                url: "<c:url value='/secfw/roleMng.do?method=insertRole' />",
                type: "post",
                dataType: "json",
                beforeSubmit: function(formData, form){
                    viewHiddenProgress(true);
                    return true;
                },
                success: function(responseText, statusText) {
                    if(responseText.returnMessage =="SUCC") {
                        viewHiddenProgress(false);
                        alert('<spring:message code="secfw.msg.succ.insert" />');
                        listRole('N');
                    }
                }
            }
            $("#frm").ajaxSubmit(options);

        }
    }

    /**
    * 역활 삭제
    */
    function deleteRole() {

        var chkValues = document.getElementsByName("chkValues");
        var j=0;
        for(i=0;i<chkValues.length;i++){
            if(chkValues[i].checked == true){
                j++;
            }
        }

        if(j < 1){
            alert("<spring:message code='secfw.msg.ask.noSelect' />");
        } else {
            var options = {
                    url: "<c:url value='/secfw/roleMng.do?method=deleteRole' />",
                    type: "post",
                    dataType: "json",
                    beforeSubmit: function(formData, form){
                        viewHiddenProgress(true);
                        return true;
                    },
                    success: function() {
                        viewHiddenProgress(false);
                        alert('<spring:message code="secfw.msg.succ.delete" />');
                        listRole('N');
                    }
                }
                $("#frm").ajaxSubmit(options);
        }
    }
//-->
</script>
</head>
<body>
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"> <c:out value='${menuNavi}' escapeXml="false"/></div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.title.role.RoleList" /></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <input type="hidden" name="method" value=""><!-- 페이지 공통 -->
          <input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>"><!-- 이중등록 방지 -->
          
           		<!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col width="*"/>
								<col width="78px"/>
							</colgroup>
							<tr>
								<td>
									
									<table class="search_form">
					                  <colgroup>
					                    <col width='10%' />
					                    <col width='30%' />
					                    <col width='10%' />
					                    <col width='*%' />
					                  </colgroup>
					                  <tr>
					                    <th><spring:message code="secfw.page.field.role.role_nm" /><!-- 역활명 --> </th>
					                    <td><input type="text" id="srch_cntnt" name="srch_cntnt" class="text" value="<c:out value='${command.srch_cntnt}'/>" /></td>
					                    <th><spring:message code="secfw.page.field.common.use_yn" /><!-- 사용여부 --></th>
					                    <td>
					                      <select name="srch_use_yn" id="use_yn" class="select">
					                        <option value=""  <c:if test="${command.srch_use_yn eq ''}">selected</c:if>><spring:message code="secfw.page.field.common.total" /></option>
					                        <option value="Y" <c:if test="${command.srch_use_yn eq 'Y'}">selected</c:if>><spring:message code="secfw.page.field.common.use" /></option>
					                        <option value="N" <c:if test="${command.srch_use_yn eq 'N'}">selected</c:if>><spring:message code="secfw.page.field.common.not_use" /></option>
					                      </select>
					                    </td>
					                  </tr>
					                </table>
					                
					            </td>
								<td class='tC'><a href="javascript:listRole('Y');"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->	
				
				
	            <!-- Button -->
	            <div class="btnwrap">
	            	<span class="btn_all_w" onclick="javascript:newRole();"><span class="add"></span><a href="#"><spring:message code="secfw.page.button.add" /></a></span>
	            	<span class="btn_all_w" onclick="javascript:insertRole();"><span class="edit"></span><a href="#"><spring:message code="secfw.page.button.new" /></a></span>
	            	<span class="btn_all_w" onclick="javascript:deleteRole();"><span class="delete"></span><a href="#"><spring:message code="secfw.page.button.delete" /></a></span>
	            </div>
	            <!-- //Button -->
           
	            <!-- List -->
	            <div class='tableWrap'>
					<div class='tableone'>
						<table class="list_basic">
			              <colgroup>
			                <col width="10%">
			                <col width="20%"/>
			                <col width="20%"/>
			                <col width="10%"/>
			                <col width="40%"/>
			              </colgroup>
			              <tr>
			                <th><input type="checkbox" name="allCheck" id="allCheck" class="checkbox"></th>
			                <th><spring:message code="secfw.page.field.role.role_cd" /><!-- 역활코드 --></th>
			                <th><spring:message code="secfw.page.field.role.role_nm" /><!-- 역활명 --></th>
			                <th><spring:message code="secfw.page.field.common.use_yn" /><!-- 사용여부 --></th>
			                <th class="end"><spring:message code="secfw.page.field.common.comments" /><!-- 설명 --></th>
			              </tr>
			            </table>
			        </div>
			    </div>
			    <style>
					.h_120 {max-height:280px;}
					*html .h_120 {height:280px;}
				</style>
				<div class='tabletwo h_120'>
				<table class="list_scr" id="roleTable">
	           		 <colgroup>
		                <col width="10%">
		                <col width="20%"/>
		                <col width="20%"/>
		                <col width="10%"/>
		                <col width="40%"/>
		              </colgroup>
	              </table>
	            </div>
	            <!-- //List -->
            
	            <!-- Total -->
	            <div class="total_num">Total : <span id="roleTableCnt"></span></div>
	            <!-- //Total -->
           
            
        </form:form>
        <!-- //**************************** Form Setting **************************** -->
        
        
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>