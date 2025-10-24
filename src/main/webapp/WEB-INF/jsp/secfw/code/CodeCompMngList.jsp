<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.code.dto.CodeVO" %>
<%--
/**
 * 파    일    명 : CodeCompMngList.jsp
 * 프 로 그 램 명 : 사용회사관리
 * 작    성    자 : 전종효
 * 작    성    일 : 2013.04
 * 설          명 : 사용회사관리 화면으로 기존의 코드관리 중 사용회사만 따로 관리하기 위해 분리함
 */
--%>
<%
    CodeVO cVO = (CodeVO)request.getAttribute("vo");

    // 메뉴 네비게이션 스트링
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");
    String menu_id  = cVO != null ? cVO.getMenu_id() : "";
    menu_id = "20130403182818690_0000000441";
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.code.CompCodeList" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--
var title_grp_cd             = '<spring:message code="secfw.page.field.code.grp_cd" />';
var title_grp_cd_nm          = '<spring:message code="secfw.page.field.code.grp_cd_nm" />';
var title_grp_cd_abbr_nm     = '<spring:message code="secfw.page.field.code.grp_cd_abbr_nm" />';
var title_grp_cd_nm_eng      = '<spring:message code="secfw.page.field.code.grp_cd_nm_eng" />';
var title_grp_cd_abbr_nm_eng = '<spring:message code="secfw.page.field.code.grp_cd_abbr_nm_eng" />';
var title_cd                 = '<spring:message code="secfw.page.field.code.cd" />';
var title_cd_nm              = '<spring:message code="secfw.page.field.code.cd_nm" />';
var title_cd_abbr_nm         = '<spring:message code="secfw.page.field.code.cd_abbr_nm" />';
var title_cd_nm_eng          = '<spring:message code="secfw.page.field.code.cd_nm_eng" />';
var title_cd_abbr_nm_eng     = '<spring:message code="secfw.page.field.code.cd_abbr_nm_eng" />';
var title_comments           = '<spring:message code="secfw.page.field.code.comments" />';
var title_use_yn             = '<spring:message code="secfw.page.field.code.use_yn" />' + ' (Y/N)';
var title_cd_order           = '<spring:message code="secfw.page.field.code.cd_order" />';
var title_useman_mng_itm1    = '<spring:message code="secfw.page.field.code.useman_mng_itm1" />';
var title_useman_mng_itm2    = '<spring:message code="secfw.page.field.code.useman_mng_itm2" />';
var title_useman_mng_itm3    = '<spring:message code="secfw.page.field.code.useman_mng_itm3" />';
var title_useman_mng_itm4    = '<spring:message code="secfw.page.field.code.useman_mng_itm4" />';
var title_useman_mng_itm5    = '<spring:message code="secfw.page.field.code.useman_mng_itm5" />';

    $(function() {
        $("#allCheckCode")
        .click(function() {
            if(($(this).attr("checked"))) {
                $("input:checkbox[name='chk_cds']")
                    .each(function(){
                        $(this).attr("checked", true);
                    });
            } else {
                $("input:checkbox[name='chk_cds']")
                .each(function(){
                    $(this).attr("checked", false);
                });
            }
        });

        $(document).keydown(function(event){
            if(event.keyCode == "13") {
                return false;
            }
        });
    });

    /**
    * 공통코드 조회
    */
    function listCode(data) {
        var frm = document.frmCode;
        frm.select_grp_cd.value = data;

        var totalCnt = 0;

        $('#codeTable tr:not(#codeTableHeader)').remove();

        var options = {
            url: "<c:url value='/secfw/code.do?method=listCode'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                if("N" != data)
                    viewHiddenProgress(true);
                return true;
            },
            success: function(responseText, statusText) {
                $('#codeTable tr:not(#codeTableHeader)').remove();
                if(responseText != null && responseText.length != 0) {
                    $.each(responseText, function(entryIndex, entry) {
                        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                                 +  "<td class='tC'><input type=\'checkbox\' id=\'" + entry['cd'] + "\' name=\'chk_cds\' class=\'checkbox\' value=\'" + entry['cd'] + "\'></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cds\' id=\'cds\' class=\'text\' value=\'" + entry['cd'] + "\' size=\'10\' fieldTitle=\'" + title_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cd_nms\' id=\'cd_nms\' class=\'text\' value=\'" + entry['cd_nm'] + "\' size=\'20\' fieldTitle=\'" + title_cd_nm + "\' required=\'*\' maxLength=\'60\' /></td>"
                                 +  "<td class='tC'>" + entry['grp_cd'] + "</td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cd_abbr_nms\' id=\'cd_abbr_nms\' class=\'text\' value=\'" + entry['cd_abbr_nm'] + "\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm + "\' maxLength=\'60\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cd_nm_engs\' id=\'cd_nm_engs\' class=\'text\' value=\'" + entry['cd_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_cd_nm_eng + "\' maxLength=\'60\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cd_abbr_nm_engs\' id=\'cd_abbr_nm_engs\' class=\'text\' value=\'" + entry['cd_abbr_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm_eng + "\' maxLength=\'60\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'cd_orders\' id=\'cd_orders\' class=\'text\' value=\'" + entry['cd_order'] + "\' size=\'3\' fieldTitle=\'" + title_cd_order + "\' required=\'*\' maxLength=\'3\' char=\'n\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'" + entry['comments'] + "\' size=\'10\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm1s\' id=\'useman_mng_itm1s\' class=\'text\' value=\'" + entry['useman_mng_itm1'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm1 + "\' maxLength=\'30\'/></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm2s\' id=\'useman_mng_itm2s\' class=\'text\' value=\'" + entry['useman_mng_itm2'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm2 + "\' maxLength=\'30\'/></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm3s\' id=\'useman_mng_itm3s\' class=\'text\' value=\'" + entry['useman_mng_itm3'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm3 + "\' maxLength=\'30\'/></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm4s\' id=\'useman_mng_itm4s\' class=\'text\' value=\'" + entry['useman_mng_itm4'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm4 + "\' maxLength=\'30\'/></td>"
                                 +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm5s\' id=\'useman_mng_itm5s\' class=\'text\' value=\'" + entry['useman_mng_itm5'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm5 + "\' maxLength=\'30\'/></td>"
                                 + "</tr>";
                        $('#codeTable').append(html);
                        totalCnt = entry['total_cnt'];
                    });
                } else {
                    var html = "<tr id=\'resultNotFoundRowCode\'><td colspan=15 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
                    $('#codeTable').append(html);
                }

                $('#codeTotalCnt').text(totalCnt);
                if("N" != data) {
                    viewHiddenProgress(false);
                }
            }
        }

        $("#frmCode").ajaxSubmit(options);
    }

    /**
    * 공통코드 추가
    */
    function newCode() {

        var frm = document.frmCode;
        var grpCd = frm.select_grp_cd.value;

        $('#resultNotFoundRowCode').remove();
        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
             +  "<td class='tC'><input type=\'checkbox\' id=\'\' name=\'chk_cds\' class=\'checkbox\' value=\'\'></td>"
             +  "<td class='tC'><input type=\'text\' name=\'cds\' id=\'cds\' class=\'text w_96\' value=\'\'  fieldTitle=\'" + title_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'cd_nms\' id=\'cd_nms\' class=\'text w_96\' value=\'\'  fieldTitle=\'" + title_cd_nm + "\' required=\'*\' maxLength=\'60\' /></td>"
             +  "<td class='tC'>" + grpCd + "</td>"
             +  "<td class='tC'><input type=\'text\' name=\'cd_abbr_nms\' id=\'cd_abbr_nms\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm + "\' maxLength=\'60\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'cd_nm_engs\' id=\'cd_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_nm_eng + "\' maxLength=\'60\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'cd_abbr_nm_engs\' id=\'cd_abbr_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm_eng + "\' maxLength=\'60\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'cd_orders\' id=\'cd_orders\' class=\'text\' value=\'\' size=\'3\' fieldTitle=\'" + title_cd_order + "\' required=\'*\' maxLength=\'3\' char=\'n\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
             +  "<td class='tC'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
             +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm1s\' id=\'useman_mng_itm1s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm1 + "\' maxLength=\'30\'/></td>"
             +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm2s\' id=\'useman_mng_itm2s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm2 + "\' maxLength=\'30\'/></td>"
             +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm3s\' id=\'useman_mng_itm3s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm3 + "\' maxLength=\'30\'/></td>"
             +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm4s\' id=\'useman_mng_itm4s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm4 + "\' maxLength=\'30\'/></td>"
             +  "<td class='tC'><input type=\'text\' name=\'useman_mng_itm5s\' id=\'useman_mng_itm5s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm5 + "\' maxLength=\'30\'/></td>"
             + "</tr>";
        $('#codeTable').append(html);
        $('#codeTable tr:last td :checkbox[name=chk_cds]').focus();
    }

    /**
    * 공통코드 등록
    */
    function insertCode() {

        var frm = document.frmCode;
        var selectGrpCd = frm.select_grp_cd.value;

        if(selectGrpCd == null || selectGrpCd == '') {
            alert("<spring:message code='secfw.page.field.code.regGroupCd'/>");
            return;
        }

        // 등록할려는 코드가 없을경우
        if($('#codeTable tr:not(#codeTableHeader,#resultNotFoundRowCode)').is("tr") != true) {
            alert("<spring:message code='secfw.page.field.code.noCd'/>");
            return;
        }

        if(validateForm(document.frmCode) != false) {

            var options = {
                url: "<c:url value='/secfw/code.do?method=insertCode'/>",
                type: "post",
                dataType: "json",
                beforeSubmit: function(formData, form){
                    viewHiddenProgress(true);
                    return true;
                },
                success: function(responseText, statusText) {
                    viewHiddenProgress(false);
                    alert(responseText.returnMessage);

                    if(responseText.returnMessage.indexOf('Duplication!') == -1)
                        listCode(document.frmCode.select_grp_cd.value);
                }
            }

            $("#frmCode").ajaxSubmit(options);
        }
    }

    /**
    * 공통코드 삭제
    */
    function deleteCode() {

        var options = {
            url: "<c:url value='/secfw/code.do?method=deleteCode'/>",
            type: "post",
            dataType: "json",
            beforeSubmit: function(formData, form){
                viewHiddenProgress(true);
                return true;
            },
            success: function() {
                viewHiddenProgress(false);
                $('#codeTable input:checked:not(#allCheckCode)').parent().parent().remove();
            }
        }

        $("#frmCode").ajaxSubmit(options);
    }
//-->
</script>
</head>
<body onload="listCode('COMP')">
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.title.code.CompAuthMgmt"/><!-- 사용회사관리 --></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
      
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frmCode" name="frmCode" method="post">
          <input type="hidden" name="select_grp_cd" value="COMP">
          <input type="hidden" name="menu_id" value="<%=menu_id%>" />
          <div class="newstyle-area">
            
            <div class="total_num">Total : <span id="codeTotalCnt"></span></div>
           
            <div class="btnwrap mt_m20">
            	<span class="btn_all_w" onclick='javascript:newCode();'><span class="add"></span><a href="#"><spring:message code="secfw.page.field.code.add"/></a></span>
            	<span class="btn_all_w" onclick='javascript:insertCode();'><span class="edit"></span><a href="#"><spring:message code="secfw.page.field.code.registration"/></a></span>
            	<span class="btn_all_w" onclick='javascript:deleteCode();'><span class="delete"></span><a href="#"><spring:message code="secfw.page.field.code.delete"/></a></span>
           </div>
            
            <!-- Code list -->
            <div class="dscrollxy" style="height:90%; width:*">
              <table id="codeTable" class="list_basic">
                <colgroup>
                  <col width="30px" align="center"/>
                  <col width="70px" align="center"/>
                  <col width="130px" align="center"/>
                  <col width="110px" align="center"/>
                  <col width="100px" align="center"/>
                  <col width="100px" align="center"/>
                  <col width="100px" align="center"/>
                  <col width="40px" align="center"/>
                  <col width="40px" align="center"/>
                  <col width="80px" align="center"/>
                  <col width="80px" align="center"/>
                  <col width="80px" align="center"/>
                  <col width="80px" align="center"/>
                  <col width="80px" align="center"/>
                  <col width="80px" align="center"/>
                </colgroup>
                <tr id="codeTableHeader">
                  <th><input type="checkbox" id="allCheckCode" name="allCheckCode" class="checkbox"></th>
                  <th><spring:message code="secfw.page.field.code.cd"/></th>
                  <th><spring:message code="secfw.page.field.code.cdNm"/></th>
                  <th><spring:message code="secfw.page.field.code.groupCd"/></th>
                  <th><spring:message code="secfw.page.field.code.cdSumNm"/></th>
                  <th><spring:message code="secfw.page.field.code.cdEngNm"/></th>
                  <th><spring:message code="secfw.page.field.code.cdSumEngNm"/></th>
                  <th><spring:message code="secfw.page.field.code.order"/></th>
                  <th><spring:message code="secfw.page.field.code.use"/></th>
                  <th><spring:message code="secfw.page.field.code.explain"/></th>
                  <th><spring:message code="secfw.page.field.code.others1"/></th>
                  <th><spring:message code="secfw.page.field.code.others2"/></th>
                  <th><spring:message code="secfw.page.field.code.others3"/></th>
                  <th><spring:message code="secfw.page.field.code.others4"/></th>
                  <th><spring:message code="secfw.page.field.code.others5"/></th>
                </tr>
                
              </table>
            </div>
            <!-- //Code list -->
          </div>
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