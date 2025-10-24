<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : MappingAuthPage.jsp
 * 프 로 그 램 명 : 권한-페이지 설정
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
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
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
        $('#img_srch_auth').click(function(event){
            listAuthAjax();
        });
        $('#img_srch_page').click(function(event){
            listPageAjax();
        });
        $('#btn_addTotal').click(function(event){
            $("#allCheck").attr("checked",true);
            $("#allCheck").trigger("click");

            setPageAuth();
        });
        $('#btn_addSelect').click(function(event){
            setPageAuth();
            $('#selectPageAuth').focus();
        });

        $(document).keydown(function(event){
            if(event.keyCode == "13") {
                var target = event.target;
                if(target.id == "srch_auth_cntnt") {
                    listAuthAjax();
                } else if (target.id == "srch_page_cntnt") {
                    listPageAjax();
                } else {
                    return false;
                }
            }
        });

    });

    /**
    * 권한리스트 조회
    */
    function listAuthAjax() {
        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listAuth'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: listAuth
        }
        $("#frm").ajaxSubmit(options);
        return false;
    }

    /**
    * 권한리스트 콜백함수
    */
    function listAuth(responseText, statusText) {
        $('#tableAuthList tr').remove();
        if(responseText != null && responseText.length != 0) {
            $.each(responseText, function(entryIndex, entry) {
                var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                         +  "<td><a href=\"javascript:showPageAuth(\'" + entry['auth_cd'] + "\',\'" + entry['auth_nm'] + "\');\">" + entry['auth_nm'] + "</a></td>"
                         + "</tr>";
                $('#tableAuthList').append(html);
            });
        } else {
            var html = "<tr><td align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableAuthList').append(html);
        }
        viewHiddenProgress(false);
    }

    /**
    * 페이지 리스트 조회
    */
    function listPageAjax() {
        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listPage'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: function(responseText, statusText) {
                $('#tablePageList tr:not(#pageListHeader)').remove();
                if(responseText != null && responseText.length != 0) {
                    $.each(responseText, function(entryIndex, entry) {
                        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                                 +  "<td><input type=\'checkbox\' id=\'" + entry['page_id'] + "\' name=\'chkValues\' class=\'checkbox\' value=\'" + entry['page_id'] + "\'></td>"
                                 +  "<td id=\'" + entry['page_id'] + "Text" + "\'>"
                                    + entry['page_nm']
                                 +  "</td>"
                                 + "</tr>";
                        $('#tablePageList').append(html);
                    });
                } else {
                    var html = "<tr><td colspan=2 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
                    $('#tablePageList').append(html);
                }
                viewHiddenProgress(false);
            }
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 권한-페이지 매핑리스트 조회
    */
    function showPageAuth(authCd, authNm) {

        var frm = document.frm;
        frm.auth_cd.value=authCd;
        frm.auth_nm.value=authNm;

        $("input:checkbox")
        .each(function(){
            $(this).attr("checked", false);
        });

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listPageAuth'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            beforeSend: function() {
                $('#selectPageAuth option').remove();
                var html = "<option id=\'processList\'><spring:message code='secfw.page.field.auth.Searching'/></option>";
                $('#selectPageAuth').append(html);
            },
            success: function(responseText, statusText) {
                var authNm = document.frm.auth_nm.value;
                var totalCnt = 0;

                $('#selectPageAuth option').remove();
                $.each(responseText, function(entryIndex, entry) {
                    var html = "<option value=\'" + entry['page_id'] + "\'>"
                             + "[" + authNm + "]"
                             + entry['page_nm']
                             + "</option>";
                    $('#selectPageAuth').append(html);
                    totalCnt += 1;
                });

                if(totalCnt == 0) {
                    var nhtml = "<option id=\'noRowListPageAuth\' value=\'noRowListPageAuth\'>[" + authNm + "] <spring:message code='secfw.page.field.auth.noPageSetup'/>" +"</option>";
                    $('#selectPageAuth').append(nhtml);
                }
                $('#selectPageAuth').focus();
                viewHiddenProgress(false);
            }
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 권한-페이지 등록
    */
    function insertPageAuth() {

        $('#selectPageAuth option')
        .each(function(){
            $(this).attr("selected", true);
        });

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=insertPageAuth'/>",
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
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 권한-페이지 설정
    */
    function setPageAuth() {

        var authNm = document.frm.auth_nm.value;

        $('#noRowListPageAuth').remove();
        $("#tablePageList :checkbox:checked:not(#allCheck)")
        .each(function(entry, entryIndex){
            //checkBox ID
            var elementId = $(this).attr('id');
            //페이지아이디
            var inputId = $(this).val();

            //사용자 정보
            var inputText = "[" + authNm + "]" + $("#" + elementId + "Text").text();

            if(!isExist(inputId, inputText)) { //해당데이타가 없을 경우
                var html = "<option value=\'" + inputId + "\'>" + inputText + "</option>";
                $('#selectPageAuth').append(html);
            }
        });
    }

    /**
    * 권한-페이지 삭제
    */
    function deletePageAuth() {
        $('#selectPageAuth option:selected').remove();
    }

    /**
    * 등록된 페이지인지 체크
    */
    function isExist(inputId, inputText) {
        if(inputId == "") {
            alert(inputText + "<spring:message code='secfw.page.field.auth.noPageInfo'/>");
            return true;
        } else {
            for (i = 0 ; i < document.frm.selectPageAuth.length ; i ++)
            {
                    if(document.frm.selectPageAuth.options[i].value.indexOf(inputId) >= 0) {
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
      <h3><spring:message code="secfw.page.title.auth.AuthPageMapping" /></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <input type="hidden" name="auth_cd" value="">
          <input type="hidden" name="auth_nm" value="">
          <div class="newstyle-area">
            <table class="all fix">
              <colgroup>
                <col width="48%" />
                <col width="4%" align="center"/>
                <col width="48%" />
              </colgroup>
              <tr>
                <td class="vt">
                  <!-- Search -->
                  <div class="search_box">
                    <div class="search_box_in">
                      <table class="search_in">
                        <colgroup>
                          <col/>
                          <col width="60%"/>
                          <col/>
                        </colgroup>
                        <tr>
                          <td><spring:message code="secfw.page.field.auth.auth_nm" /><!-- 권한명 --> :</td>
                          <td><input type="text" name="srch_auth_cntnt" id="srch_auth_cntnt" class="text all" value="<c:out value='${command.srch_auth_cntnt}'/>" /></td>
                          <td><a href="#" class="bt_search"><span id="img_srch_auth"><img src="<c:url value='/images/secfw/ico/ico_search.gif' />"/><spring:message code="secfw.page.button.search" /></span></a></td>
                        </tr>
                      </table>
                    </div>
                  </div>
                  <!-- //Search -->
                  <!-- List -->
                  <table class="basic_list mz fix">
                    <colgroup>
                      <col width="100%" align="left"/>
                      <col />
                    </colgroup>
                    <tr>
                      <th><spring:message code="secfw.page.field.auth.auth_nm" /></th>
                      <th class="th_scroll"></th>
                    </tr>
                  </table>
                  <div class="dscrolly" style="height:200px">
                    <table id="tableAuthList" class="basic_list tb_non fix">
                      <colgroup>
                        <col align="left"/>
                      </colgroup>
                    </table>
                  </div>
                  <!-- //List -->
                </td>
                <td></td>
                <td class="vt">
                  <!-- Search -->
                  <div class="search_box">
                    <div class="search_box_in">
                      <table class="search_in">
                        <colgroup>
                          <col/>
                          <col width="50%"/>
                          <col/>
                        </colgroup>
                        <tr>
                          <td>
                            <select name="srch_page_cntnt_type" id="srch_page_cntnt_type" class="select">
                              <option value="page" <c:if test="${command.srch_page_cntnt_type eq 'page'}">selected</c:if>><spring:message code="secfw.page.field.page.page_nm" /></option>
                              <option value="dept" <c:if test="${command.srch_page_cntnt_type eq 'dept'}">selected</c:if>><spring:message code="secfw.page.field.dept.dept_nm" /></option>
                            </select>
                          </td>
                          <td><input type="text" id="srch_page_cntnt" name="srch_page_cntnt" class="text all"/></td>
                          <td><a href="#" class="bt_search"><span id="img_srch_page"><img src="<c:url value='/images/secfw/ico/ico_search.gif' />"/><spring:message code="secfw.page.button.search" /></span></a></td>
                        </tr>
                      </table>
                    </div>
                  </div>
                  <!-- //Search -->
                  <!-- List -->
                  <table class="basic_list mz fix">
                    <colgroup>
                      <col width="30px" align="center"/>
                      <col align="left"/>
                      <col />
                    </colgroup>
                    <tr id="pageListHeader">
                      <th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox"></th>
                      <th><spring:message code="secfw.page.field.page.page_nm" /><!-- 성명 --></th>
                      <th class="th_scroll"></th>
                    </tr>
                  </table>
                  <div class="dscrolly" style="height:199px">
                    <table id="tablePageList" class="basic_list tb_non fix">
                      <colgroup>
                        <col width="30px" align="center"/>
                        <col align="left"/>
                      </colgroup>
                    </table>
                  </div>
                  <!-- //List -->
                  <!-- Button -->
                  <div class="bt_content_top">
                    <a href="#" class="bt_fn ico_add"><span id="btn_addTotal"><spring:message code="secfw.page.button.addAll" /></span></a>
                    <a href="#" class="bt_fn ico_verification"><span id="btn_addSelect"><spring:message code="secfw.page.button.addSelect" /></span></a>
                  </div>
                  <!--//Button -->
                </td>
              </tr>
              <tr>
                <td colspan="3">
                  <!-- div class="bt_top"><span class="title" id="authTitle"><strong></strong></span></div-->
                  <!-- List -->
                  <table class="all">
                    <colgroup>
                      <col width="*"/>
                      <col width="70px" align="right"/>
                    </colgroup>
                    <tr>
                      <td>
                        <select id="selectPageAuth" name="mapping_page_id" size="6" multiple="multiple" class="auth_select_multi" style='width:100%'></select>
                      </td>
                      <td valign="top"><a href="javascript:deletePageAuth();" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/ico_delete.gif' />"><spring:message code="secfw.page.button.delete" /></span></a></td>
                    </tr>
                  </table>
                  <!-- //List -->
                  <div class="ct_mz"></div>
                </td>
              </tr>
            </table>
            <!-- Button -->
            <div class="bt_right"><a href="javascript:insertPageAuth();" class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_confirm.gif' />"><spring:message code="secfw.page.button.insert" /></span></a></div>
            <!-- //Button -->
          </div>
        </form:form>
        <!-- //**************************** Form Setting **************************** -->
      </div>
      <!-- //content_in -->
    </div>
    <!-- //content -->
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  </div>
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->
</body>
</html>
