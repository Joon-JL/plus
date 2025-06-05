<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : MappingRoleUser.jsp
 * 프 로 그 램 명 : 역활-사용자 설정
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
<script language="javascript">
<!--

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

        /**
        * 이벤트처리
        */
        $('#img_srch_role').click(function(event){
            listRoleAjax();
        });
        $('#img_srch_user').click(function(event){
            listUserAjax();
        });
        $('#btn_addTotal').click(function(event){
            $("#allCheck").attr("checked",true);
            $("#allCheck").trigger("click");

            setRoleUser();
        });

        $('#btn_addSelect').click(function(event){
            setRoleUser();
            $('#selectRoleUser').focus();
        });

        $(document).keydown(function(event){
            if(event.keyCode == "13") {
                var target = event.target;
                if(target.id == "srch_role_cntnt") {
                    listRoleAjax();
                } else if (target.id == "srch_user_cntnt") {
                    listUserAjax();
                } else {
                    return false;
                }
            }
        });

    });

    /**
    * 롤리스트 조회
    */
    function listRoleAjax() {
        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listRole'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: listRole
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 사용자리스트 조회
    */
    function listUserAjax() {
        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listUser'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: listUser
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 역활-사용자 매핑리스트 조회
    */
    function showRoleUser(roleCd, roleNm) {

        var frm = document.frm;
        frm.role_cd.value=roleCd;
        frm.role_nm.value=roleNm;

        //$('#roleTitle strong').text("ROLE : " + roleNm);

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listRoleUser'/>",
            type: "post",
            dataType: "json",
            beforeSend: function() {
                viewHiddenProgress(true);
                $('#selectRoleUser option').remove();
                var html = "<option id=\'processList\'><spring:message code='secfw.page.field.auth.Searching'/></option>";
                $('#selectRoleUser').append(html);
            },
            success: function(responseText, statusText){

                var roleNm = document.frm.role_nm.value;
                var totalCnt = 0;

                $('#selectRoleUser option').remove();
                $.each(responseText, function(entryIndex, entry) {
                    var html = "<option value=\'" + entry['user_id'] + "\'>"
                             + "[" + roleNm + "]"
                             + entry['user_nm'] + "/"
                             + entry['jikgup_nm'] + "/"
                             + entry['dept_nm'] + "/"
                             + entry['comp_nm']
                             + "</option>";
                    $('#selectRoleUser').append(html);
                    totalCnt += 1;
                });

                if(totalCnt == 0) {
                    var html = "<option id=\'noRowListRoleUser\'>[" + roleNm + "] <spring:message code='secfw.page.field.auth.noUserFound'/>" +"</option>";
                    $('#selectRoleUser').append(html);
                }
                $('#selectRoleUser').focus();
                viewHiddenProgress(false);
            }
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 역활-사용자 등록
    */
    function insertRoleUser() {

        $('#selectRoleUser option')
        .each(function(){
            $(this).attr("selected", true);
        });

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=insertRoleUser'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: function(responseText, statusText) {
                viewHiddenProgress(false);
                alert(responseText.returnMessage);
            }
        };
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 역활리스트 콜백함수
    */
    function listRole(responseText, statusText) {
        $('#tableRoleList tr').remove();
        if(responseText != null && responseText.length != 0) {
            $.each(responseText, function(entryIndex, entry) {
                var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                         +  "<td><a href=\"javascript:showRoleUser(\'" + entry['role_cd'] + "\',\'" + entry['role_nm'] + "\');\">" + entry['role_nm'] + "</a></td>"
                         + "</tr>";
                $('#tableRoleList').append(html);
            });
        } else {
            var html = "<tr><td align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableRoleList').append(html);
        }
        viewHiddenProgress(false);
    }

    /**
    * 사용자리스트 콜백함수
    */
    function listUser(responseText, statusText) {
        $('#tableUserList tr:not(#userListHeader)').remove();
        if(responseText != null && responseText.length != 0) {
            $.each(responseText, function(entryIndex, entry) {
                var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                         +  "<td class='tC'><input type=\'checkbox\' id=\'" + entry['user_id'] + "\' name=\'chkValues\' class=\'checkbox\' value=\'" + entry['user_id'] + "\'></td>"
                         +  "<td id=\'" + entry['user_id'] + "Text" + "\'>"
                            + entry['user_nm'] + "/"
                            + entry['jikgup_nm'] + "/"
                            + entry['dept_nm'] + "/"
                            + entry['comp_nm']
                         +  "</td>"
                         + "</tr>";
                $('#tableUserList').append(html);
            });
        } else {
            var html = "<tr><td colspan=2 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableUserList').append(html);
        }
        viewHiddenProgress(false);
    }

    /**
    * 역활-사용자 설정
    */
    function setRoleUser() {

        var roleNm = document.frm.role_nm.value;

        $('#noRowListRoleUser').remove();
        $("#tableUserList :checkbox:checked:not(#allCheck)")
        .each(function(entry, entryIndex){
            //checkBox ID
            var elementId = $(this).attr('id');
            //사용자 ID
            var inputId = $(this).val();

            //사용자 정보
            var inputText = "[" + roleNm + "]" + $("#" + elementId + "Text").text();

            if(!isExist(inputId, inputText)) { //해당데이타가 없을 경우
                var html = "<option value=\'" + inputId + "\'>" + inputText + "</option>";
                $('#selectRoleUser').append(html);
            }
        });
    }

    /**
    * 역활-사용자 삭제
    */
    function deleteRoleUser() {
        $('#selectRoleUser option:selected').remove();
    }

    /**
    * 등록된 사용자 인지 체크
    */
    function isExist(inputId, inputText) {
        if(inputId == "") {
            alert(inputText + "<spring:message code='secfw.page.field.auth.noUserInfo'/>");
            return true;
        } else {
            for (i = 0 ; i < document.frm.selectRoleUser.length ; i ++)
            {
                    if(document.frm.selectRoleUser.options[i].value.indexOf(inputId) >= 0) {
                        return true;
                    }
            }
            return false;
        }
    }
//-->
</script>
</head>
<body>
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.title.auth.RoleUserMapping" /></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <input type="hidden" name="role_cd" value="">
          <input type="hidden" name="role_nm" value="">
          
          	<style>
				.h_120 {height:120px;}
				*html .h_120 {height:120px;}
		    </style>
		    
            <table class="all fix">
              <colgroup>
                <col width="48%" />
                <col width="4%"/>
                <col width="48%" /> <!-- width size 변경 신성우 2014-04-24 -->
              </colgroup>
              <tr>
                <td class="vt">
                
	                <!-- search-->
		            <div class="search_box">
						<div class="search_box_inner">
							<table class="search_tb">
								<tr>
									<td>
										
										<table class="search_form">
				                        	<colgroup>
					                          <col width="15%"/>
					                          <col width="*%"/>
					                        </colgroup>
					                        <tr>
					                          <th><spring:message code="secfw.page.field.role.role_nm" /><!-- 역활명 --></th>
					                          <td>
					                          	<input type="text" name="srch_role_cntnt" id="srch_role_cntnt" class="text" value="<c:out value='${command.srch_role_cntnt}'/>" />
					                          	<a id="img_srch_role"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a>
					                          </td>
					                        </tr>
					                      </table>
					                      
					                  </td>
								</tr>
							</table>
						</div>
		            </div>     
					<!-- search-->	
				
                  <span class='ico_alert01 fR' style='margin-top:6px'>Please choose a click.</span>
                  <!-- List -->
                  <div class='tableWrap'>
					<div class='tableone'>
						<table class="list_basic">
		                    <colgroup>
		                      <col width="100%"/>
		                    </colgroup>
		                    <tr>
		                      <th class='end'><spring:message code="secfw.page.field.role.role_nm" /></th>
		                    </tr>
                 		 </table>
                  	</div>
                  </div>
                  
                  <div class='tabletwo h_120'>
				  <table class="list_scr" id="tableRoleList">
	                  <colgroup>
			         	 <col width="100%"/>
			          </colgroup>
                  </table>
                  </div>
                  <!-- //List -->
                  
                </td>
                
                <td>&nbsp;</td>
                
                <td class="vt">
	                <!-- search-->
		            <div class="search_box">
						<div class="search_box_inner">
							<table class="search_tb">
								<tr>
									<td>
										
										<table class="search_form">
											<colgroup>
											  <col width="15%"/>	
					                          <col width="*%"/>
					                        </colgroup>
					                        <tr>
					                          <th><spring:message code="secfw.page.field.bbs.classification" /></th>
					                          <td>
					                            <select name="srch_user_cntnt_type" id="srch_user_cntnt_type" class="select">
					                              <option value="userType" <c:if test="${command.srch_user_cntnt_type eq 'userType'}">selected</c:if>><spring:message code="secfw.page.field.user.user_nm" /></option>
					                              <option value="compType" <c:if test="${command.srch_user_cntnt_type eq 'compType'}">selected</c:if>><spring:message code="secfw.page.field.common.comNm" /></option>
					                            </select>
					                          	<input type="text" id="srch_user_cntnt" name="srch_user_cntnt" class="text"/>
						                        <a id="img_srch_user"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a>
						                      </td>
					                        </tr>
					                      </table>
	                   				</td>
								</tr>
							</table>
						</div>
		            </div>     
					<!-- search-->	
				
				  <!-- Button -->
                  <div class="btnwrap">
                  	<span class="btn_all_b" id="btn_addTotal"><span class="all"></span><a><spring:message code="secfw.page.button.addAll" /></a></span>
                  	<span class="btn_all_b" id="btn_addSelect"><span class="add"></span><a><spring:message code="secfw.page.button.addSelect" /></a></span>
                  </div>
                  <!--//Button -->
                  
                  
                  <!-- List -->
                  <div class='tableWrap'>
				  <div class='tableone'>
				  <table class="list_basic">
                    <colgroup>
                      <col width="10%"/>
                      <col width="90%"/>
                    </colgroup>
                    <tr id="userListHeader">
                      <th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox"></th>
                      <th class='end'><spring:message code="secfw.page.field.user.user_nm" /><!-- 성명 --></th>
                    </tr>
                  </table>
                  </div>
                  </div>
                  
                  <div class='tabletwo h_120'>
				  <table class="list_scr" id="tableUserList">
                 	<colgroup>
                      <col width="10%"/>
                      <col width="90%"/>
                    </colgroup>
                  </table>
                  </div>
                  <!-- //List -->
                  
                  
                  
                  
                  
                </td>
              </tr>
            </table>
            
            <div class='hr_line2 mt20'></div>
            
            <div class='mt20 tR'>
            	<span class="btn_all_b" onclick="javascript:deleteRoleUser();"><span class="delete"></span><a><spring:message code="secfw.page.button.delete" /></a></span>
            	<select id="selectRoleUser" name="mapping_user_id" multiple="MULTIPLE" style='width:100%; height:100px; border:1px solid #ccc;margin-top:3px'></select>
            </div>
            
            
            <!-- Button -->
            <div class='btnwrap mt10'>
            	<span class="btn_all_w" onclick="javascript:insertRoleUser();"><span class="save"></span><a><spring:message code="secfw.page.button.insert" /></a></span>
            </div>
            <!-- //Button -->
          
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