<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : MappingCompAuthMenu.jsp
 * 프 로 그 램 명 : 메뉴-권한 설정
 * 작    성    자 : 전종효
 * 작    성    일 : 2013.03
 * 설          명 : 사별 메뉴-권한 설정 화면으로 권한관리 메뉴의 메뉴-권한 설정은 'C60'을 위한것이고,
 *                  현재 화면은 삼성전자 자회사(12개사)에 대한 메뉴-권한을 설정하는 것이다.
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
<title><spring:message code="secfw.page.title.auth.CompAuthList" /></title>
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
    $(function() {
        var frm = document.frm;

        $('#img_srch_auth').click(function(event) {
            if (frm.srch_comp_cd.value != "") {
                frm.comp_cd.value = frm.srch_comp_cd.value;
                listAuthAjax();
            } else {
                alert('<spring:message code="secfw.msg.alert.select.comp_nm" />');
            }
        });

        $(document).keydown(function(event) {
            if (event.keyCode == "13") {
                var target = event.target;

                if (target.id == "srch_auth_cntnt") {
                    if (frm.srch_comp_cd.value != "") {
                        frm.comp_cd.value = frm.srch_comp_cd.value;
                        listAuthAjax();
                    } else {
                        alert('<spring:message code="secfw.msg.alert.select.comp_nm" />');
                    }
                }

                return false;
            }
        });

        //access control 설정
        //$('#chkAuthTR').hide();
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
    * 메뉴리스트 조회
    */
    function listMenuAuthAjax(compCd, data, authNm) {

        var frm = document.frm;
        frm.auth_cd.value = data;
        frm.comp_cd.value = compCd;

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=listCompMenuAuth'/>",
            type: "post",
            dataType: "json",
            beforeSend: function() {
                $('#menuTree').hide();
                $('#progressTree').show();
            },
            complete: function() {
                $('#progressTree').hide();
                $('#menuTree').show();
            },
            success: function(responseText, statusText) {
                $("#menuTree").dynatree({
                    checkbox: true,
                    selectMode: 2,
                    rootVisible: true,
                    children: responseText,
                    onSelect: function(select, dtnode) {
                    },
                    onClick: function(dtnode, event) {

                        $("input[name=auth_menu_id]").val(dtnode.data.key);

                        if( dtnode.getEventTargetType(event) == "title" && dtnode.data.isFolder == false) {
                           //dtnode.toggleSelect();

                            var getAccessControl = {
                                    url: "<c:url value='/secfw/authMapping.do?method=getAccessControl2'/>",
                                    type: "post",
                                    dataType: "json",
                                    beforeSubmit: function(formData, form){
                                        return true;
                                    },
                                    success: function(responseText, statusText) {
                                        setAccessControl(responseText['accessControl']);
                                    }
                                }
                                $("#frm").ajaxSubmit(getAccessControl);

                        }
                    }
                });

                if(responseText != null && responseText.length != 0) {
                    var tree = $("#menuTree").dynatree("getTree");
                    var rootNode = tree.getRoot();

                    rootNode.data.title="Menu";
                    rootNode.data.hideCheckbox=true;

                    rootNode.removeChildren();
                    rootNode.addChild(responseText);
                }

            }
        }
        $("#frm").ajaxSubmit(options);
    }

    /**
    * 권한-메뉴 등록
    */
    function insertMenuAuth() {

        $('#frm input[name=mapping_menu_id]').remove();

        var tree = $("#menuTree").dynatree("getTree");
        $.each(tree.getSelectedNodes(),function(entryIndex, entry){
            var node = entry;
            var menuId = node.data.key;

            var html = "<input type=\'hidden\' name=\'mapping_menu_id\' value=\'" + menuId + "\' />"
            $('#frm').append(html);
        });

        $('#selectAuthMenu option')
        .each(function(){
            $(this).attr("selected", true);
        });

        var options = {
            url: "<c:url value='/secfw/authMapping.do?method=insertCompMenuAuth'/>",
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
    * 권한리스트 콜백함수
    */
    function listAuth(responseText, statusText) {
        var frm = document.frm;

        $('#tableAuthList tr').remove();
        if(responseText != null && responseText.length != 0) {
            $.each(responseText, function(entryIndex, entry) {
                var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                         +  "<td class='end'><a href=\"javascript:listMenuAuthAjax(\'" + frm.srch_comp_cd.value + "\',\'" + entry['auth_cd'] + "\', \'" + entry['auth_nm'] + "\');\">" + entry['auth_nm'] + "</a></td>"
                         + "</tr>";
                $('#tableAuthList').append(html);
            });
        } else {
            var html = "<tr><td align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableAuthList').append(html);
        }
        viewHiddenProgress(false);
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
      <h3><spring:message code="secfw.page.title.auth.AuthMenuMapping" /></h3>
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
          <input type="hidden" name="comp_cd" value="">
          <input type="hidden" name="auth_menu_id" value="">
         
            <table class="all fix">
              <colgroup>
                <col width="59%" />
                <col width="2%"/>
                <col width="39%" />
              </colgroup>
              <tr>
                <td class="vt">
                
                
                  <!-- search-->
		            <div class="search_box">
						<div class="search_box_inner">
							<table class="search_tb">
								<colgroup>
								<col/>
								<col width="78px"/>
								</colgroup>
								<tr>
									<td>
										<table class="search_form">
					                        <colgroup>
					                          <col width="15%"/>
					                          <col width="35%"/>
					                          <col width="15%"/>
					                          <col width="35%"/>
					                        </colgroup>
					                        <tr>
					                          <th><spring:message code="secfw.page.field.user.comp_nm" /><!-- 회사명 --></th>
					                          <td><select id="srch_comp_cd" name="srch_comp_cd">
					                               <c:out value="${combo.compGbn}" escapeXml="false"/>
					                               <option value='C10' >삼성전자</option>
					                               </select></td>
					                          <th><spring:message code="secfw.page.field.auth.auth_nm" /><!-- 권한명 --></th>
					                          <td><input type="text" name="srch_auth_cntnt" id="srch_auth_cntnt" class="text w_70" value="<c:out value='${command.srch_auth_cntnt}'/>" /></td>
					                        </tr>
                      					</table>
                     				</td>
								 <td class="tC"><a href="#" id="img_srch_auth"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search"/></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->	
                 
                  <!-- List -->
                  <div class='tableWrap mt20'>
					<div class='tableone'>
				        <table class="list_basic">
		                    <colgroup>
		                      <col width="100%" align="left"/>
		                      <col />
		                    </colgroup>
		                    <tr>
		                      <th class='end'><spring:message code="secfw.page.field.auth.auth_nm" /></th>
		                      <th class="th_scroll"></th>
		                    </tr>
		                </table>
		            </div>
		          </div>
		          
                   <div class='tabletwo' style='height:372px;'>
				  	<table class="list_scr" id="tableAuthList" >
                    	<colgroup>
                        	<col align="left"/>
                      	</colgroup>
                    </table>
                  </div>
                  <!-- //List -->
                  
                </td>
                <td></td>
                <td class="vt">
                
                  <!-- MenuList -->
                  <div id="menuTree" class="pop_treeview_authMenu" style="height:454px; width:99%"></div>
                  <div id="progressTree" class="pop_treeview_authMenu" style="display:none;height:454px;width:99%">
                    <table width="99%" height="99%" border="0">
                      <tr>
                        <td align=center class='box'><img src="<c:url value='/images/secfw/common/process_circle.gif' />"><br>&nbsp;<br><spring:message code="secfw.page.field.auth.isSearching"/></td>
                      </tr>
                    </table>
                  </div>
                  <!-- //MenuList -->
                
                </td>
              </tr>
            </table>
            
            <!-- button -->
			<div class="mb3 mt10 tR">
				<span class="btn_all_w" onclick='javascript:insertMenuAuth();'><span class="save"></span><a href="#"><spring:message code="secfw.page.button.insert" /></a></span>
			</div>
			<!-- //button -->
			
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