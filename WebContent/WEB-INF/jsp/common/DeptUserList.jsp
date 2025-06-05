<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.clmscom.dto.CLMSCommonForm" %>
<%@ page import="model.outldap.samsung.net.Organization" %>
<%--
/**
 * 파     일     명 : DeptUserList.jsp
 * 프 로 그  램  명 : 부서 선택 (팝업)
 * 설            명 : 부서트리에서 선택된 부서를 멀티로 선택
 * 작     성     자 : 김형준
 * 작     성     일 : 2011.09.16
 * 수     정     자 : 이준석
 * 수     정     일 : 2013.05.03
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />

<title></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript" src="/script/secfw/jquery/jquery.js"></script>
<script type="text/javascript" src="/script/secfw/jquery/ui.core.js"></script>
<script type="text/javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript" src="/script/secfw/common/CommonProgress.js"></script>
<script language='javascript' src='/script/clms/event.js'></script>
<script type="text/javascript" src="/script/clms/clms_common.js"></script>

<%
Organization resultList[] = (Organization[])request.getAttribute("resultList");
ArrayList userDeptList = (ArrayList)request.getAttribute("userDeptList");
CLMSCommonForm command = ((CLMSCommonForm)request.getAttribute("command") == null ? new CLMSCommonForm() : (CLMSCommonForm)request.getAttribute("command"));
String returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
%>
<script type="text/javascript">
<!--
	var dupSubmit = true;
	var choiceDept = "";
	var searchDept = "";


	function pageAction(actionFlag) {

		if(actionFlag == "search") {
			var frm = document.searchForm;
			if(comTrim(replace(frm.dept_nm.value, "*", "")) != "" && calculate_msglen(frm.dept_nm.value) >= 4) {
				if(frm.dept_nm.value.indexOf('*') >= 0) {
					var widthLen = 150;
					var heightLen = 250;
					var leftPos = (window.screen.availWidth - widthLen) / 2;
					var topPos = (window.screen.availHeight - heightLen) / 2;
					var searchDeptWin = window.open('', "searchDeptWin", "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=" + leftPos + ",top=" + topPos + ",width=" + widthLen + ",height=" + heightLen)
					searchDeptWin.focus();
					
					frm.target = "searchDeptWin";

					frm.action = "<c:url value='/common/clmsCom.do' />";
					frm.method.value = "searchDeptEsb";

					frm.submit();
				} else {
					frm.target = "_self";
					frm.action = "<c:url value='/common/clmsCom.do' />";
					frm.method.value = "listDeptTreeEsb";
					frm.up_dept_cd.value = "TOP";
		
					frm.submit();
				}
			} else {
				alert('<spring:message code='secfw.page.msg.deptSrch.announce001' />');
				return;
			}
		}
	}
	
	function calculate_msglen(message) {
		 var resultSize = 0;
		    if (message == null) return 0;
		    for(var i=0; i<message.length; i++){
		        var c = escape(message.charAt(i));
		        if(c.length == 1) resultSize ++;
		        else if(c.indexOf("%u") != -1) resultSize += 2;
		        else if(c.indexOf("%") != -1) resultSize += c.length/3;
		    }
		    return resultSize;
		}
	
	function setDeptInfo(retDept) {
		var frm = document.searchForm;

		frm.dept_nm.value = retDept;
		frm.target = "_self";
		frm.action = "<c:url value='/common/clmsCom.do' />";
		frm.method.value = "listDeptTreeEsb";
		frm.up_dept_cd.value = "TOP";

		frm.submit();
	}

	function showHide(iconPos) {
		
		if(dupSubmit) {
			if(document.all["plusIcon_" + iconPos].style.display == 'none') {
				document.all["plusIcon_" + iconPos].style.display = '';
				document.all["minusIcon_" + iconPos].style.display = 'none';
				document.all["DSP_" + iconPos].style.display = 'none';
			} else {
				document.all["plusIcon_" + iconPos].style.display = 'none';
				document.all["minusIcon_" + iconPos].style.display = '';
				if(document.all["DSP_" + iconPos].innerHTML == "") {
					subDept(iconPos);
				} else {
					document.all["DSP_" + iconPos].style.display = '';
				}
			}
		}
	}

	function subDept(iconPos) {
		document.searchForm.up_dept_cd.value = iconPos;
		var options = {
				url: "/common/clmsCom.do?method=listDeptTreeAjaxEsb",
				type: "post",
				dataType: "json",
				beforeSubmit: function(formData, form) {
					dupSubmit = false;
					return true;
				},
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						var changeFlag = false;
						var changeDept = "";
                    	var html = "<ul class='List02' style='margin-left:12px;'>";
						$.each(responseText, function(entryIndex, entry) {
							html += "<li>";
							if(entry['down_dept_yn'] == "T") {
								html += "<img src='<%=IMAGE%>/btn/arr_list_add.gif' id='plusIcon_" + entry['dept_cd'] + "' onclick='JavaScript:showHide(\"" + entry['dept_cd'] + "\");' style='cursor:hand'>";
								html += "<img src='<%=IMAGE%>/btn/arr_list_minus.gif' id='minusIcon_" + entry['dept_cd'] + "' onclick='JavaScript:showHide(\"" + entry['dept_cd'] + "\");' style='cursor:hand;display:none'>";
								html += "<span id='SP_" + entry['dept_cd'] + "'><a href='JavaScript:setOpenerDeptInfo(\"" + entry['dept_cd'] + "\",\"" + entry['dept_nm'] + "\");'>&nbsp;" + entry['dept_nm'] + "</a></span>";
								html += "<div id='DIV_" + entry['dept_cd'] + "' style='display:none' title='aa'></div>";
							}else{
								html += "<img src='<%=IMAGE%>/btn/arr_list_minus.gif' id='minusIcon_" + entry['dept_cd'] + "' >";
								html += "<span id='SP_" + entry['dept_cd'] + "'><a href='JavaScript:setOpenerDeptInfo(\"" + entry['dept_cd'] + "\",\"" + entry['dept_nm'] + "\");'>&nbsp;" + entry['dept_nm'] + "</a></span>";
							}
							if(searchDept != "") {
								if(entry['dept_nm'] == searchDept) {
									changeFlag = true;
									changeDept = entry['dept_cd'];
									searchDept = "";
								}
							}
							html += "</li>";
							html += "<p id=DSP_" + entry['dept_cd'] + "></p>";
						});
						html += "</ul>";
		
					$('#DSP_' + iconPos).html(html);
				
						document.all["DIV_" + iconPos].style.display = 'block';

						if(changeFlag) {
							$('#SP_' + changeDept).addClass("pick");
							choiceDept = changeDept;
							changeFlag = false;
						}

					}
					dupSubmit = true;
					findSelfDept();

				}
			};

		if(dupSubmit) {
			$("#searchForm").ajaxSubmit(options);
		}
		
	}
	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}

	var findSelfLen = 0;
	var findSelfPos = 0;
<%
	int upDeptLength = 0;
	String currentDept = "";
	out.print("var findSelArr = [");
	if(userDeptList != null && userDeptList.size() > 1) {
		ListOrderedMap curLom = (ListOrderedMap)userDeptList.get(0);
		currentDept = (String)curLom.get("dept_cd");
		for(int idx = userDeptList.size()-1; idx > 0; idx--) {
			ListOrderedMap lom = (ListOrderedMap)userDeptList.get(idx);
			
			String deptCd = (String)lom.get("dept_cd");
			if(deptCd != null && !deptCd.equals("TOP")) {
				out.print("\"" + deptCd + "\",");
				upDeptLength++;
			}
		}
	}
	out.println("\"\"];");
	out.println("findSelfLen = " + upDeptLength + ";");
%>
	searchDept = "<%=StringUtil.bvl(command.getDept_nm(),"")%>";

	function findSelfDept() {
		if(findSelfPos < findSelfLen) {
			showHide(findSelArr[findSelfPos]);
			findSelfPos++;
		}
	}

	function searchEmp() {
		var frm = document.searchForm;

		frm.target = "_self";
		frm.action = "<c:url value='/common/clmsCom.do' />";
		frm.method.value = "listEmployee";
		frm.srch_user_cntnt_type.value = "";
		frm.srch_user_cntnt.value = "";

		frm.submit();
	}
	
	function setOpenerDeptInfo(retDeptCd, retDeptNm){
		opener.setDeptInfo(retDeptCd, retDeptNm);
		
		tmp = document.searchForm.page_gbn.value;
		
		if(tmp == 'one'){
			window.close();
		}else{
			if(!confirm("<spring:message code='secfw.msg.ask.deptAlert3' />")) {
				window.close();
			}	
		}
	}
//-->
</script>
</head>
<body class="pop" onLoad='showMessage("<%=returnMessage %>");'>
<!-- header -->
<h1><spring:message code="common.page.field.DeptUserList.serDept"/></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Size 840*600 -->
<div class="pop_content">
    <form name="searchForm" id="searchForm" method="post" onsubmit="return false;" autocomplete="off">
      <input type="hidden" name="curPage" id="curPage" value="" />
      <input type="hidden" name="method" id="method" />
      <input type="hidden" name="user_id" id="user_id" />
      <input type="hidden" name="dept_cd" id="dept_cd" />
      <input type="hidden" name="up_dept_cd" id="up_dept_cd" />
      <input type="hidden" name="srch_user_cntnt_type" id="srch_user_cntnt_type" />
      <input type="hidden" name="srch_user_cntnt" id="srch_user_cntnt" />
      <input type="hidden" name="page_gbn" id="page_gbn" value="<%=StringUtil.bvl(command.getPage_gbn(),"")%>"/>
     
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
                  <col width="90%"/>
                </colgroup>
                <tr>
                  <th><spring:message code='secfw.page.field.user.dept' /></th>
                  <td>
                    <input type='text' id="dept_nm" name="dept_nm" value="<%=StringUtil.bvl(command.getDept_nm(),"") %>" style="width:218px;" class='text' onKeyPress="if(event.keyCode==13) {pageAction('search');return false;}">
                  </td>
                </tr>
              </table>
            </td>
            <td class="tC"><a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif"/></a></td>
            </tr>
            <tr>
            	<td><div class="ico_alert01 mL15" ><spring:message code='secfw.msg.ask.deptAlert2' /></div></td>
            </tr>
          </table>
          
        </div>
            </div>
            </form>
        <!-- Organization List -->
      <div class="menuList">
        <ul class="List01">           
<%
  if(resultList != null && resultList.length > 0) {
    for(int idx = 0; idx < resultList.length; idx++) {
      //ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
      Organization org = resultList[idx];
      
      String deptCd = resultList[idx].getDepartmentnumber();		//부서코드
      String deptNm = "";		//부서명
      if("en".equals(langCd)) {
    	  if(resultList[idx].getEpendepartment() == null) deptNm = resultList[idx].getOu();
    	  else  deptNm =  "UNKNOWN".equals(resultList[idx].getEpendepartment().toUpperCase()) ? resultList[idx].getOu() : resultList[idx].getEpendepartment();
      } else {
    	  deptNm = resultList[idx].getOu(); 
      }
      int deptLevel = Integer.parseInt(resultList[idx].getEpdeptlevel());
      int deptOrder = Integer.parseInt(resultList[idx].getEpdeptorder());
      String downDeptYN = StringUtil.bvl((String)resultList[idx].getEplowdept(), "");
      String upDeptCd = resultList[idx].getEphighdeptnumber();
      
      out.print("<li>");
      
      if(downDeptYN.equals("T")) {
        out.print("<img src='" + IMAGE+ "/btn/arr_list_add.gif' id='plusIcon_" + deptCd + "' onClick='JavaScript:showHide(\"" + deptCd + "\");' style='cursor:hand'>");
        out.print("<img src='" + IMAGE+ "/btn/arr_list_minus.gif' id='minusIcon_" + deptCd + "' onClick='JavaScript:showHide(\"" + deptCd + "\");' style='cursor:hand;display:none'>");
        out.print("<span id='SP_" + deptCd + "'><a href='JavaScript:setOpenerDeptInfo(\"" + deptCd + "\", \"" + deptNm + "\");'>&nbsp;" + deptNm + "</a></span>");
        out.print("<div id='DIV_" + deptCd + "' style='display:none' title='ttt'></div>");
      } else {
        out.print("<img src='" + IMAGE+ "/btn/arr_list_minus.gif' alt='down' id='minusIcon_" + deptCd + "'/>");
        out.print("<span id='SP_" + deptCd + "'><a href='JavaScript:setOpenerDeptInfo(\"" + deptCd + "\", \"" + deptNm + "\");'>&nbsp;" + deptNm + "</a></span>");  
      }
      out.println("</li>");
      out.print("<p id=DSP_" + deptCd + "></p>");
    }
    //out.println("</table>");
  }
%>      
        </ul>
      </div>
    <!-- //Organization List -->
    </div>
</div>
<!--footer -->
<div class="pop_footer">
  <div class="btn_wrap fR">
    <span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a href="#"><spring:message code="common.page.field.DeptUserList.close"/></a></span>
  </div>
</div>
<!-- //footer -->
<script>findSelfDept();</script>
</body>
</html>