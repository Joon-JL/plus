<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : MappingCompReviewer.jsp
 * 프 로 그 램 명 : 전자검토자 관리
 * 작    성    자 : 이준석
 * 작    성    일 : 2013.04
 * 설          명 :
 */
--%>
<%
    // 메뉴네비게이션 스트링
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.auth.CompAuthList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--
    $(function() {
        $("#allCheck").click(function() {
            if (($(this).attr("checked"))) {
            $("input:checkbox[name='chkValues']").each(function() {
                $(this).attr("checked", true);
            });
            } else {
            $("input:checkbox[name='chkValues']").each(function() {
                $(this).attr("checked", false);
            });
            }
        });

        /**
         * 이벤트처리
         */
        $('#img_srch_comp').click(function(event) {
            listMngCompAjax();
        });
        $('#img_srch_user').click(function(event) {
            listUserAjax();
        });
        $('#btn_addTotal').click(function(event) {
            $("#allCheck").attr("checked", true);
            $("#allCheck").trigger("click");

            setRoleUser();
        });

        $('#btn_addSelect').click(function(event) {
            setRoleUser();
            $('#selectMngComp').focus();
        });

        $(document).keydown(function(event) {
            if (event.keyCode == "13") {
            var target = event.target;
            if (target.id == "srch_role_cntnt") {
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
     * 사용자리스트 조회
     */
    function listUserAjax() {
        var options = {
            url : "<c:url value='/secfw/authMapping.do?method=listUserByRole'/>",
            type : "post",
            dataType : "json",
            beforeSubmit : function(formData, form) {
            viewHiddenProgress(true);
            return true;
            },
            success : listUser
        };
        $("#frm").ajaxSubmit(options);
    }

    /**
     * 사용자리스트 조회
     */
    function listMngCompAjax() {
        var options = {
            url : "<c:url value='/secfw/authMapping.do?method=listMngComp'/>",
            type : "post",
            dataType : "json",
            beforeSubmit : function(formData, form) {
            viewHiddenProgress(true);
            return true;
            },
            success : listMngComp
        };
        $("#frm").ajaxSubmit(options);
    }

    /**
     * 역활-사용자 등록
     */
    function insertMngComp() {
    	if(document.frm.mng_user_id.value == '') return;
        $('#selectMngComp option').remove();

        $("input:checkbox[name='chkValues']").each(
            function() {
                if ($(this).attr("checked") == true) {
                var html = "<option value=\'" + $(this).attr("id")
                    + "\'>" + $(this).attr("id") + "</option>";
                $('#selectMngComp').append(html);
                }
            });

        //alert('#'+document.frm.mng_user_id.value);

        $('#selectMngComp option').each(function() {
            $(this).attr("selected", true);
        });

        var options = {
            url : "<c:url value='/secfw/authMapping.do?method=insertMngComp'/>",
            type : "post",
            dataType : "json",
            beforeSubmit : function(formData, form) {
            viewHiddenProgress(true);
            return true;
            },
            success : function(responseText, statusText) {
            viewHiddenProgress(false);
            alert(responseText.returnMessage);
            listUserAjax();
            
            }
        };
        $("#frm").ajaxSubmit(options);
    }

    /**
     * 사용자리스트 콜백함수
     */
    function listUser(responseText, statusText) {
        $('#tableUserList tr:not(#userListHeader)').remove();
        if (responseText != null && responseText.length != 0) {
            $.each(
                    responseText,
                    function(entryIndex, entry) {
                    <% if("en".equals(langCd)) { %>
                    var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\" onClick=\"selectUser(\'"
                        + entry['user_id']
                        + "\', \'" + entry['sec_reviewer_type'] + "\', this)\" id=\'" + entry['user_id'] + "Text" + "\'>"
                        + "<td>"
                        + entry['user_real_nm_eng']
                        + "/"
                        + entry['jikgup_nm_eng']
                        + "/"
                        + entry['dept_nm_eng']
                        + "/"
                        + entry['comp_nm_eng'] + "</td>"
                        + "<td id=\'" + entry['user_id'] + "Text\'>" + entry['user_nm_eng'] + "</td>"
                        + "</tr>";
                    <% } else { %>
                    var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\" onClick=\"selectUser(\'"
                        + entry['user_id']
                        + "\', \'" + entry['sec_reviewer_type'] + "\', this)\" id=\'" + entry['user_id'] + "Text" + "\'>"
                        + "<td>"
                        + entry['user_real_nm']
                        + "/"
                        + entry['jikgup_nm']
                        + "/"
                        + entry['dept_nm']
                        + "/"
                        + entry['comp_nm'] + "</td>"
                        + "<td id=\'" + entry['user_id'] + "Text\'>" + entry['user_nm'] + "</td>"
                        + "</tr>";
                    <% } %>
                    $('#tableUserList').append(html);
                    });
        } else {
            var html = "<tr><td colspan=2 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableUserList').append(html);
        }
        
        viewHiddenProgress(false);
    }

    function selectUser(user_id, sec_reviewer_type, element) {
    	  //element.childNodes[0].style.fontWeight = 'bold';
        document.frm.mng_user_id.value = user_id;
        listMngCompAjax();
        $('input:radio[value=' + sec_reviewer_type + ']').attr("checked","checked");
        $('tr').css('background-color','white');
        element.style.backgroundColor = '#F6F6F6';
       
        
    }

    /**
     * 회사리스트 콜백함수
     */
    function listMngComp(responseText, statusText) {
        $('#tableCompList tr:not(#compListHeader)').remove();
        if (responseText != null && responseText.length != 0) {
            $
                .each(
                    responseText,
                    function(entryIndex, entry) {
                    <% if("en".equals(langCd)) { %>
                    var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                        + "<td><input type=\'checkbox\' id=\'" + entry['cd'] + "\' name=\'chkValues\' class=\'checkbox\' " + entry['checked'] + "></td>"
                        + "<td id=\'" + entry['cd'] + "Text" + "\'>"
                        + entry['cd_nm_eng'] + "</td>" + "</tr>";
                    <% } else { %>
                    var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                        + "<td><input type=\'checkbox\' id=\'" + entry['cd'] + "\' name=\'chkValues\' class=\'checkbox\' " + entry['checked'] + "></td>"
                        + "<td id=\'" + entry['cd'] + "Text" + "\'>"
                        + entry['cd_nm'] + "</td>" + "</tr>";
                    <% } %>
                    $('#tableCompList').append(html);
                    });
        } else {
            var html = "<tr><td colspan=2 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
            $('#tableCompList').append(html);
        }
        viewHiddenProgress(false);
    }

    /**
     * 역활-사용자 설정
     */
    function setRoleUser() {

    var roleNm = document.frm.role_nm.value;

    $('#noRowListRoleUser').remove();
    $("#tableUserList :checkbox:checked:not(#allCheck)").each(
        function(entry, entryIndex) {
            //checkBox ID
            var elementId = $(this).attr('id');
            //사용자 ID
            var inputId = $(this).val();

            //사용자 정보
            var inputText = "[" + roleNm + "]"
                + $("#" + elementId + "Text").text();

            if (!isExist(inputId, inputText)) { //해당데이타가 없을 경우
            var html = "<option value=\'" + inputId + "\'>"
                + inputText + "</option>";
            $('#selectMngComp').append(html);
            }
        });
    }

    /**
     * 등록된 사용자 인지 체크
     */
    function isExist(inputId, inputText) {
        if (inputId == "") {
            alert(inputText
                + "<spring:message code='secfw.page.field.auth.noUserInfo'/>");
            return true;
        } else {
            for (i = 0; i < document.frm.selectMngComp.length; i++) {
                if (document.frm.selectMngComp.options[i].value
                    .indexOf(inputId) >= 0) {
                    return true;
                }
            }
            return false;
        }
    }
//-->
</script>
</head>
<body onLoad="listUserAjax();">
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.title.compReviewer.title" /></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
      
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <input type="hidden" name="role_cd" value="RB01">
          <input type="hidden" name="select_grp_cd" value="COMP">
          <input type="hidden" name="role_nm" value="">
          <input type="hidden" name="mng_user_id" value="">
          
          
            <select id="selectMngComp" name="mapping_comp_cd" size="6" multiple="multiple" class="auth_select_multi" style="display: none" />
           
            <table class="all fix">
              <colgroup>
                <col width="48%" />
                <col width="4%" />
                <col width="48%" />
              </colgroup>
              <tr>
                <td class="vt" rowspan="2">
                
                  <!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col width="*px"/>
								<col width="78px"/>
							</colgroup>
							<tr>
								<td>
									<table class="search_form">
										<colgroup>
				                          <col width="20%"/>
				                          <col width="80%" />
				                        </colgroup>
				                        <tr>
				                          <th style='padding-top:4px;padding-bottom:0px;'>
				                            <select name="srch_user_cntnt_type" id="srch_user_cntnt_type">
				                              <option value="userType" <c:if test="${command.srch_user_cntnt_type eq 'userType'}">selected</c:if>><spring:message code="secfw.page.field.user.user_nm" /></option>
				                              <option value="compType" <c:if test="${command.srch_user_cntnt_type eq 'compType'}">selected</c:if>><spring:message code="secfw.page.field.common.comNm" /></option>
				                            </select>
				                          </th>
				                          <td>
				                            <input type="text" id="srch_user_cntnt" name="srch_user_cntnt" class="text w_70" />
				                          </td>
				                        </tr>
				                     </table>
				                     
				                   </td>
								   <td class="tC"><a href="#" id="img_srch_user"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search"/></a></td>
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
		                      <col width="50%" />
		                      <col width="50%" />
		                    </colgroup>
		                    <tr id="userListHeader">
		                      <th><spring:message code="secfw.page.field.user.user_nm" /><!-- 성명 --></th>
		                      <th><spring:message code="secfw.page.field.secReviewer.secReviewer" /><!-- 전자검토자 --></th>
		                    </tr>
		                  </table>
		              </div>
		          </div>
		          
                  <div class='tabletwo' style='height:264px'>
                  	<table class="list_scr" id="tableUserList">
                    	<colgroup>
	                      <col width="50%" />
	                      <col width="50%" />
	                    </colgroup>
                    </table>
                  </div>
                  <!-- //List -->
                  
                </td>
                
                <td rowspan="2"></td>
                
                <td class="vt">
                
                
                  <!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col width="*px"/>
								<col width="78px"/>
							</colgroup>
							<tr>
								<td>
									
									<table class="search_form">
				                        <colgroup>
				                          <col width='20%' />
				                          <col width="80%" />
				                        </colgroup>
				                        <tr>
				                          <th><spring:message code="secfw.page.field.common.comNm" /><!-- 회사명 --></th>
				                          <td><input type="text" name="mng_comp_cd" id="mng_comp_cd" class="text w_70" value="<c:out value='${command.srch_role_cntnt}'/>" /></td>
				                        </tr>
				                      </table>
                  				</td>
								<td class='tC'><a href="#" id="img_srch_comp"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
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
		                     <col width="10%" align="center" />
                        	 <col width="90%" />
		                    </colgroup>
		                    <tr id="compListHeader">
		                      <th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox"></th>
		                      <th class='end'><spring:message code="secfw.page.field.common.comNm" /></th>
		                    </tr>
		                  </table>
		              </div>
		          </div>
		          
                  <div class='tabletwo' style='height:264px'>
				  <table class="list_scr" id="tableCompList">
                      <colgroup>
                        <col width="10%" align="center" />
                        <col width="90%" />
                      </colgroup>
                    </table>
                  </div>
                  <!-- // List -->
                </td>
                
              </tr>
              <tr>
                <td><spring:message code="secfw.page.field.secReviewer.secReviewer" /><!-- 전자검토자 -->
                <input type="radio" name="sec_reviewer_type" value="D"><spring:message code="secfw.page.field.secReviewer.domestic" /><!-- (국내) --></input>
                <input type="radio" name="sec_reviewer_type" value="O"><spring:message code="secfw.page.field.secReviewer.overseas" /><!-- (해외) --></input>
                </td>
              </tr>
            </table>
            
            <!-- button -->
			<div class="mb3 mt10 tR">
				<span class="btn_all_w" onclick='javascript:insertMngComp();'><span class="save"></span><a href="#"><spring:message code="secfw.page.button.insert" /></a></span>
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