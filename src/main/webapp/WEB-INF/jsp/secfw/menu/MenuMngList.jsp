<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : MenuMngList.jsp
 * 프 로 그 램 명 : 메뉴관리
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
<title><spring:message code="secfw.page.title.menu.MenuMngList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<link href="<c:url value='/script/secfw/jquery/style/dynatree/ui.dynatree.css' />" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.dynatree.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--
var title_menu_nm      = '<spring:message code="secfw.page.field.menu.menu_nm" />';
var title_menu_nm_eng  = '<spring:message code="secfw.page.field.menu.menu_nm_eng" />';
var title_menu_order   = '<spring:message code="secfw.page.field.menu.menu_order" />';
var title_menu_url     = '<spring:message code="secfw.page.field.menu.menu_url" />';
var title_use_yn       = '<spring:message code="secfw.page.field.menu.use_yn" />' + ' (Y/N)';
var title_display_yn   = '<spring:message code="secfw.page.field.menu.display_yn" />' + ' (Y/N)';
var title_menu_type    = '<spring:message code="secfw.page.field.menu.menu_type" />' + ' (Menu:M/Popup:P)';
var title_authcheck_yn = '<spring:message code="secfw.page.field.menu.authcheck_yn" />' + ' (Y/N)';
var title_popup_nm     = '<spring:message code="secfw.page.field.menu.popup_nm" />';
var title_popup_width  = '<spring:message code="secfw.page.field.menu.popup_width" />';
var title_popup_height = '<spring:message code="secfw.page.field.menu.popup_height" />';
var title_comments     = '<spring:message code="secfw.page.field.menu.comments" />';
var title_menu_nm_cha  = '<spring:message code="secfw.page.field.menu.menu_nm_cha" />';
var title_menu_nm_jpn  = '<spring:message code="secfw.page.field.menu.menu_nm_jpn" />';
var title_menu_nm_fra  = '<spring:message code="secfw.page.field.menu.menu_nm_fra" />';
var title_menu_nm_deu  = '<spring:message code="secfw.page.field.menu.menu_nm_deu" />';
var title_menu_nm_ita  = '<spring:message code="secfw.page.field.menu.menu_nm_ita" />';
var title_menu_nm_esp  = '<spring:message code="secfw.page.field.menu.menu_nm_esp" />';
var title_menu_img     = '<spring:message code="secfw.page.field.menu.menu_img" />';
var title_menu_img_eng = '<spring:message code="secfw.page.field.menu.menu_img_eng" />';
var title_menu_img_cha = '<spring:message code="secfw.page.field.menu.menu_img_cha" />';
var title_menu_img_jpn = '<spring:message code="secfw.page.field.menu.menu_img_jpn" />';

    $(function() {

        $("#allCheck")
        .click(function() {
            if(($(this).attr("checked"))) {
                $("input:checkbox[name='chk_id']")
                    .each(function(){
                        $(this).attr("checked", true);
                    });
            } else {
                $("input:checkbox[name='chk_id']")
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

        listMenuTreeAjax();
    });

    /**
    * 메뉴Tree 조회
    */
    function listMenuTreeAjax() {

        var options = {
            url: "<c:url value='/secfw/menuMng.do?method=listMenuTree'/>",
            type: "post",
            dataType: "json",
            async:false,
            success: redrawTree
        }
        $("#frm").ajaxSubmit(options);
    }

    function redrawTree(responseText, statusText) {

        $("#menuTree").dynatree({
            checkbox: false,
            selectMode: 1,
            rootVisible: true,
            children: responseText,
            onSelect: function(select, dtnode) {
                //listMenuTableAjax(dtnode.data.key);
            },
            onClick: function(dtnode, event) {
                if( dtnode.getEventTargetType(event) == "title" && dtnode.data.isFolder == true)
                   dtnode.toggleExpand();

                listMenuTableAjax(dtnode.data.key);
            },
            onKeydown: function(dtnode, event) {
                if( event.which == 32 ) {
                    dtnode.toggleSelect();
                    return false;
                }
            }
        });

        if(responseText != null && responseText.length != 0) {
            var tree = $("#menuTree").dynatree("getTree");
            var rootNode = tree.getRoot();

            rootNode.data.title="Menu";
            rootNode.data.hideCheckbox=true;
            rootNode.render(true);

        }
    }

    /**
    * 메뉴Table 조회
    */
    function listMenuTableAjax(data) {

        var frm = document.frm;
        frm.select_menu_id.value = data;

        var totalCnt = 0;

        var options = {
            url: "<c:url value='/secfw/menuMng.do?method=listMenuTable'/>",
            type: "post",
            dataType: "json",
            async:false,
            success: function(responseText, statusText) {
                $('#menuTable tr:not(#menuTableHeader)').remove();
                if(responseText != null && responseText.length != 0) {
                    $.each(responseText, function(entryIndex, entry) {
                        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                                 +  "<td class=\'tC\'><input type=\'checkbox\' id=\'" + entry['menu_id'] + "\' name=\'chk_id\' class=\'checkbox\' value=\'" + entry['menu_id'] + "\'></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nms\' id=\'menu_nms\' class=\'text\' value=\'" + entry['menu_nm'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm + "\' required=\'*\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_engs\' id=\'menu_nm_engs\' class=\'text\' value=\'" + entry['menu_nm_eng'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_eng + "\' required=\'*\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_chas\' id=\'menu_nm_chas\' class=\'text\' value=\'" + entry['menu_nm_cha'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_cha + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_jpns\' id=\'menu_nm_jpns\' class=\'text\' value=\'" + entry['menu_nm_jpn'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_jpn + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_fras\' id=\'menu_nm_fras\' class=\'text\' value=\'" + entry['menu_nm_fra'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_fra + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_deus\' id=\'menu_nm_deus\' class=\'text\' value=\'" + entry['menu_nm_deu'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_deu + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_itas\' id=\'menu_nm_itas\' class=\'text\' value=\'" + entry['menu_nm_ita'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_ita + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_esps\' id=\'menu_nm_esps\' class=\'text\' value=\'" + entry['menu_nm_esp'] + "\' size=\'20\' fieldTitle=\'" + title_menu_nm_esp + "\' maxLength=\'100\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_orders\' id=\'menu_orders\' class=\'text\' value=\'" + entry['menu_order'] + "\' size=\'2\' fieldTitle=\'" + title_menu_order + "\' required=\'*\' maxLength=\'3\' num=\'i\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_urls\' id=\'menu_urls\' class=\'text\' value=\'" + entry['menu_url'] + "\' size=\'25\' fieldTitle=\'" + title_menu_url + "\' maxLength=\'150\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'display_yns\' id=\'display_yns\' class=\'text\' value=\'" + entry['display_yn'] + "\' size=\'1\' fieldTitle=\'" + title_display_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_types\' id=\'menu_types\' class=\'text\' value=\'" + entry['menu_type'] + "\' size=\'1\' fieldTitle=\'" + title_menu_type + "\' maxLength=\'1\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'authcheck_yns\' id=\'authcheck_yns\' class=\'text\' value=\'" + entry['authcheck_yn'] + "\' size=\'1\' fieldTitle=\'" + title_authcheck_yn + "\' maxLength=\'1\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_imgs\' id=\'menu_imgs\' class=\'text\' value=\'" + entry['menu_img'] + "\' size=\'10\' fieldTitle=\'" + title_menu_img + "\' maxLength=\'300\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_engs\' id=\'menu_img_engs\' class=\'text\' value=\'" + entry['menu_img_eng'] + "\' size=\'10\' fieldTitle=\'" + title_menu_img_eng + "\'  maxLength=\'300\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_chas\' id=\'menu_img_chas\' class=\'text\' value=\'" + entry['menu_img_cha'] + "\' size=\'10\' fieldTitle=\'" + title_menu_img_cha + "\'  maxLength=\'300\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_jpns\' id=\'menu_img_jpns\' class=\'text\' value=\'" + entry['menu_img_jpn'] + "\' size=\'10\' fieldTitle=\'" + title_menu_img_jpn + "\'  maxLength=\'300\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'popup_nms\' id=\'popup_nms\' class=\'text\' value=\'" + entry['popup_nm'] + "\' size=\'10\' fieldTitle=\'" + title_popup_nm + "\' maxLength=\'30\'/></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'popup_widths\' id=\'popup_widths\' class=\'text\' value=\'" + entry['popup_width'] + "\' size=\'4\' fieldTitle=\'" + title_popup_width + "\' maxLength=\'4\' num=\'i\' /></td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'popup_heights\' id=\'popup_heights\' class=\'text\' value=\'" + entry['popup_height'] + "\' size=\'4\' fieldTitle=\'" + title_popup_height + "\' maxLength=\'4\' num=\'i\' /></td>"
                                 +  "<td class=\'tC\'>" + entry['menu_id'] + "</td>"
                                 +  "<td class=\'tC\'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'" + entry['comments'] + "\' size=\'20\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
                                 +  "<input type=\'hidden\' name=\'menu_ids\' value=\'" + entry['menu_id'] + "\' />"
                                 + "</tr>";
                        $('#menuTable').append(html);
                        totalCnt = entry['total_cnt'];
                    });
                } else {
                    var html = "<tr id=\'resultNotFoundRow\'><td colspan=11 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
                    $('#menuTable').append(html);
                }

                $('#totalCnt').text(totalCnt);
            }
        }
        $("#frm").ajaxSubmit(options);

        redrawTree();
    }

    /**
    * 메뉴추가
    */
    function newMenu() {

        var pNode = $("#menuTree").dynatree("getActiveNode");
        if(pNode == null) {
            alert("<spring:message code='secfw.page.field.menu.selLeftUpMenu'/>");
            return;
        }

        $('#resultNotFoundRow').remove();
        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
             +  "<td class=\'tC\'><input type=\'checkbox\' id=\'\' name=\'chk_id\' class=\'checkbox\' value=\'\'></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nms\' id=\'menu_nms\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm + "\' required=\'*\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_engs\' id=\'menu_nm_engs\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_eng + "\' required=\'*\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_chas\' id=\'menu_nm_chas\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_cha + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_jpns\' id=\'menu_nm_jpns\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_jpn + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_fras\' id=\'menu_nm_fras\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_fra + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_deus\' id=\'menu_nm_deus\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_deu + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_itas\' id=\'menu_nm_itas\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_ita + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_nm_esps\' id=\'menu_nm_esps\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_menu_nm_esp + "\' maxLength=\'100\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_orders\' id=\'menu_orders\' class=\'text\' value=\'\' size=\'2\' fieldTitle=\'" + title_menu_order + "\' required=\'*\' maxLength=\'3\' num=\'i\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_urls\' id=\'menu_urls\' class=\'text\' value=\'\' size=\'25\' fieldTitle=\'" + title_menu_url + "\' maxLength=\'150\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'display_yns\' id=\'display_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_display_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_types\' id=\'menu_types\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_menu_type + "\' maxLength=\'1\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'authcheck_yns\' id=\'authcheck_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_authcheck_yn + "\' maxLength=\'1\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_imgs\' id=\'menu_imgs\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_menu_img + "\' maxLength=\'300\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_engs\' id=\'menu_img_engs\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_menu_img_eng + "\' maxLength=\'300\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_chas\' id=\'menu_img_chas\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_menu_img_cha + "\' maxLength=\'300\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'menu_img_jpns\' id=\'menu_img_jpns\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_menu_img_jpn + "\' maxLength=\'300\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'popup_nms\' id=\'popup_nms\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_popup_nm + "\' maxLength=\'30\'/></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'popup_widths\' id=\'popup_widths\' class=\'text\' value=\'\' size=\'4\' fieldTitle=\'" + title_popup_width + "\' maxLength=\'4\' num=\'i\' /></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'popup_heights\' id=\'popup_heights\' class=\'text\' value=\'\' size=\'4\' fieldTitle=\'" + title_popup_height + "\' maxLength=\'4\' num=\'i\' /></td>"
             +  "<td class=\'tC\'></td>"
             +  "<td class=\'tC\'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
             +  "<input type=\'hidden\' name=\'menu_ids\' value=\'\' />"
             + "</tr>";
        $('#menuTable').append(html);
        $('#menuTable tr:last td :checkbox[name=chk_id]').focus();

    }

    /**
    * 메뉴 등록
    */
    function insertMenu() {

        var pNode = $("#menuTree").dynatree("getActiveNode");
        if(pNode == null || $('#menuTable tr:not(#menuTable,#menuTableHeader)').is("tr") != true) {
            alert("<spring:message code='secfw.page.field.menu.afterAddMenu'/>");
            return;
        }

        if(validateForm(document.frm) != false) {

            var pNode = $("#menuTree").dynatree("getActiveNode");

            var options = {
                url: "<c:url value='/secfw/menuMng.do?method=insertMenu'/>",
                type: "post",
                dataType: "json",
                async: false,
                success: function(responseText, statusText) {
                    if(responseText != null && responseText.length != 0) {
                        pNode.removeChildren();
                        pNode.addChild(responseText);
                    }
                }
            }
            $("#frm").ajaxSubmit(options);

            listMenuTreeAjax();
            listMenuTableAjax(pNode.data.key);
        }
    }

    /**
    * 메뉴 삭제
    */
    function deleteMenu() {

        //삭제 메뉴가 없을경우
        if($('#menuTable input:checked:not(#allCheck)').is("input") != true) {
            alert("<spring:message code='secfw.page.field.menu.noMenuForDel'/>");
            return;
        }

        var pNode = $("#menuTree").dynatree("getActiveNode");
        var options = {
            url: "<c:url value='/secfw/menuMng.do?method=deleteMenu'/>",
            type: "post",
            dataType: "json",
            async: false,
            success: function() {

                $.each($('#menuTable input:checked:not(#allCheck)'),
                    function(entryIndex, entry) {
                        if(entry.id != null && entry.id != '') {
                            var node = $("#menuTree").dynatree("getTree").getNodeByKey(entry.id);
                            node.remove();
                        }
                    });
                $('#menuTable input:checked:not(#allCheck)').parent().parent().remove();
            }
        }
        $("#frm").ajaxSubmit(options);

        listMenuTreeAjax();
        listMenuTableAjax(pNode.data.key);
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
      <h3><spring:message code="secfw.page.field.menu.regMenu"/></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
       
                
          
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="method" value="">
          <input type="hidden" name="select_menu_id" value="">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          
          <div class="newstyle-area">
          
          
          
          	
                	
                 
            <table class="fix" style='width:100%'>
              <colgroup>
                <col width="300px"/>
                <col width="*"/>
              </colgroup>
              <tr>
                <td class="vt">
                  <!-- Treeview -->
                  <div id="menuTree" class="pop_treeview" style="height:451px;"></div>
                  <!-- //Treeview -->
                </td>
                <td class="vt" >
                    <div class="total_num">Total : <span id="totalCnt"></span></div>
		            <div class='btnwrap mt_m20'>
		            	<span class="btn_all_w" onclick="javascript:newMenu();"><span class="add"></span><a><spring:message code="secfw.page.button.add" /></a></span>
		               	<span class="btn_all_w" onclick="javascript:insertMenu();"><span class="edit"></span><a><spring:message code="secfw.page.button.new" /></a></span>
		               	<span class="btn_all_w" onclick="javascript:deleteMenu();"><span class="delete"></span><a><spring:message code="secfw.page.button.delete" /></a></span>
		            </div>
                 
                  <div style="height:453px;width:100%; overflow-x:scroll;" >
                    <table id="menuTable" class="list_basic" style="width:1320px;">
                      <colgroup>
                        <col width="30px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="150px" align="center"/>
                        <col width="40px" align="center"/>
                        <col width="180px" align="center"/>
                        <col width="40px" align="center"/>
                        <col width="40px" align="center"/>
                        <col width="40px" align="center"/>
                        <col width="80px" align="center"/>
                        <col width="80px" align="center"/>
                        <col width="80px" align="center"/>
                        <col width="80px" align="center"/>
                        <col width="80px" align="center"/>
                        <col width="100px" align="center"/>
                        <col width="60px" align="center"/>
                        <col width="60px" align="center"/>
                        <col width="180px" align="center"/>
                        <col width="150px" align="center"/>
                      </colgroup>
                      <tr id="menuTableHeader">
                        <th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox"></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm" /><!-- 메뉴명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_eng" /><!-- 영문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_cha" /><!-- 중문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_jpn" /><!-- 일문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_fra" /><!-- 일문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_deu" /><!-- 일문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_ita" /><!-- 일문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_nm_esp" /><!-- 일문명 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_order" /><!-- 메뉴순서 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_url" /><!-- URL --></th>
                        <th><spring:message code="secfw.page.field.menu.use_yn" /><!-- 사용여부 --></th>
                        <th><spring:message code="secfw.page.field.menu.display_yn" /><!-- 표시여부 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_type" /><!-- 메뉴타입 --></th>
                        <th><spring:message code="secfw.page.field.menu.authcheck_yn" /><!-- 권한체크 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_img" /><!-- 메뉴이미지 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_img_eng" /><!-- 영문이미지 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_img_cha" /><!-- 중문이미지 --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_img_jpn" /><!-- 일문이미지 --></th>
                        <th><spring:message code="secfw.page.field.menu.popup_nm" /><!-- 팝업명 --></th>
                        <th><spring:message code="secfw.page.field.menu.popup_width" /><!-- 팝업가로 PX --></th>
                        <th><spring:message code="secfw.page.field.menu.popup_height" /><!-- 팝업세로 PX --></th>
                        <th><spring:message code="secfw.page.field.menu.menu_id" /><!-- 메뉴ID --></th>
                        <th><spring:message code="secfw.page.field.menu.comments" /><!-- 설명 --></th>
                      </tr>
                    </table>
                  </div>
                </td>
              </tr>
            </table>
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