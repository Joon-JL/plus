<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파    일    명 : PageMngList.jsp
 * 프 로 그 램 명 : 페이지관리
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
<title><spring:message code="secfw.page.title.menu.PageMngList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--
var title_page_nm      = '<spring:message code="secfw.page.field.page.page_nm" />';
var title_page_url     = '<spring:message code="secfw.page.field.page.page_url" />';
var title_use_yn       = '<spring:message code="secfw.page.field.page.use_yn" />' + ' (Y/N)';
var title_authcheck_yn = '<spring:message code="secfw.page.field.page.authcheck_yn" />' + ' (Y/N)';
var title_developer_nm = '<spring:message code="secfw.page.field.page.developer_nm" />';
var title_comments     = '<spring:message code="secfw.page.field.page.comments" />';

    $(function() {

        $("#allCheckPage")
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
                var target = event.target;
                if(target.id == "srch_page_nm") {
                    listPageTable();
                } else {
                    return false;
                }
            }

        });

        listPageTable();
    });

    /**
    * 페이지목록 조회
    */
    function listPageTable() {

        var totalCnt = 0;

        var options = {
            url: "<c:url value='/secfw/pageMng.do?method=listPageTable'/>",
            type: "post",
            dataType: "json",
            success: function(responseText,returnMessage) {
                $('#pageTable tr:not(#pageTableHeader)').remove();
                if(responseText != null && responseText.length != 0) {
                    $.each(responseText, function(entryIndex, entry) {
                        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
                                 +  "<td><input type=\'checkbox\' id=\'\' name=\'chk_id\' class=\'checkbox\' value=\'" + entry['page_id'] + "\'></td>"
                                 +  "<td class=\'overflow\'><nobr>" + entry['page_id'] + "</nobr></td>"
                                 +  "<td><input type=\'text\' name=\'page_nms\' id=\'page_nms\' class=\'text\' value=\'" + entry['page_nm'] + "\' size=\'18\' fieldTitle=\'" + title_page_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
                                 +  "<td><input type=\'text\' name=\'page_urls\' id=\'page_urls\' class=\'text\' value=\'" + entry['page_url'] + "\' size=\'22\' fieldTitle=\'" + title_page_url + "\' required=\'*\' maxLength=\'100\' /></td>"
                                 +  "<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
                                 +  "<td><input type=\'text\' name=\'authcheck_yns\' id=\'authcheck_yns\' class=\'text\' value=\'" + entry['authcheck_yn'] + "\' size=\'1\' fieldTitle=\'" + title_authcheck_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
                                 +  "<td><input type=\'text\' name=\'developer_nms\' id=\'developer_nms\' class=\'text\' value=\'" + entry['developer_nm'] + "\' size=\'7\' fieldTitle=\'" + title_developer_nm + "\' maxLength=\'30\'/></td>"
                                 +  "<td class='end'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'" + entry['comments'] + "\' style=\'width:100%\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
                                 +  "<input type=\'hidden\' name=\'page_ids\' value=\'" + entry['page_id'] + "\' />"
                                 + "</tr>";
                        $('#pageTable').append(html);
                        totalCnt = entry['total_cnt'];
                    });
                } else {
                    var html = "<tr id=\'resultNotFoundRowPage\'><td colspan=8 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
                    $('#pageTable').append(html);
                }

                $('#pageTotalCnt').text(totalCnt);
            }
        }
        $("#frmPage").ajaxSubmit(options);
    }

    /**
    * 페이지 추가
    */
    function newPage() {

        $('#resultNotFoundRowPage').remove();
        var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
             +  "<td><input type=\'checkbox\' id=\'\' name=\'chk_id\' class=\'checkbox\' value=\'\'></td>"
             +  "<td></td>"
             +  "<td><input type=\'text\' name=\'page_nms\' id=\'page_nms\' class=\'text\' value=\'\' size=\'18\' fieldTitle=\'" + title_page_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
             +  "<td><input type=\'text\' name=\'page_urls\' id=\'page_urls\' class=\'text\' value=\'\' size=\'22\' fieldTitle=\'" + title_page_url + "\' required=\'*\' maxLength=\'100\' /></td>"
             +  "<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
             +  "<td><input type=\'text\' name=\'authcheck_yns\' id=\'authcheck_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_authcheck_yn + "\' required=\'*\' maxLength=\'1\'/></td>"
             +  "<td><input type=\'text\' name=\'developer_nms\' id=\'developer_nms\' class=\'text\' value=\'\' size=\'7\' fieldTitle=\'" + title_developer_nm + "\' maxLength=\'30\'/></td>"
             +  "<td  class='end'><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'\' style=\'width:100%\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\' /></td>"
             +  "<input type=\'hidden\' name=\'page_ids\' value=\'\' />"
             + "</tr>";
        $('#pageTable').append(html);
        $('#pageTable tr:last td :checkbox[name=chk_id]').focus();

    }

    /**
    * 페이지 등록
    */
    function insertPage() {

        //등록할려는 페이지가 없을경우
        if($('#pageTable tr:not(#resultNotFoundRowPage)').is("tr") != true) {
            alert("<spring:message code='secfw.page.field.menu.noPage'/>");
            return;
        }

        if(validateForm(document.frmPage) != false) {

            var options = {
                url: "<c:url value='/secfw/pageMng.do?method=insertPage'/>",
                type: "post",
                dataType: "json",
                async: false,
                success: function(responseText, statusText) {
                    if(responseText.returnMessage =="SUCC") {
                        alert('<spring:message code="secfw.msg.succ.insert" />');
                        listPageTable();
                    }
                }
            }
            $("#frmPage").ajaxSubmit(options);
        }
    }

    /**
    * 페이지 삭제
    */
    function deletePage() {

        var options = {
            url: "<c:url value='/secfw/pageMng.do?method=deletePage'/>",
            type: "post",
            dataType: "json",
            success: function() {
                listPageTable();
                //$('#pageTable input:checked:not(#allCheckPage)').parent().parent().remove();
            }
        }
        $("#frmPage").ajaxSubmit(options);
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
      <h3><spring:message code="secfw.page.field.menu.regPage"/></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
        <!-- **************************** Form Setting **************************** -->
        <form:form id="frmPage" name="frmPage" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          
            
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
					                    <col width='15%' />
					                    <col width='85%' />
					                  </colgroup>
					                  <tr>
					                    <th><spring:message code="secfw.page.field.page.page_nm" /><!-- 페이지명 --></th>
					                    <td><input type="text" id="srch_page_nm" name="srch_page_nm" class="text" value="<c:out value='${command.srch_page_nm}'/>" /></td>
					                  </tr>
					                </table>
					                
					             </td>
								<td class='tC'><a href="javascript:listPageTable();"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- // search-->	
           
	           
	            <div class="btnwrap tR">
	   				<!-- <a href="javascript:comLayerPopCenter('layerExcelUpload');" class="bt_fn ico_excel_up"><span name="btnExcelUpload"><spring:message code="secfw.page.button.excelUpload" /></span></a> -->
	                <span class="btn_all_w" onclick="javascript:newPage();"><span class="add"></span><a href="#"><spring:message code="secfw.page.button.add" /></a></span>
					<span class="btn_all_w" onclick="javascript:insertPage();"><span class="edit"></span><a href="#"><spring:message code="secfw.page.button.new" /></a></span>
					<span class="btn_all_w" onclick="javascript:deletePage();"><span class="delete"></span><a href="#"><spring:message code="secfw.page.button.delete" /></a></span>
	            </div>
            
	            <!-- List -->
	            <div class='tableWrap'>
				<div class='tableone'>
				<table class="list_basic">
	              <colgroup>
	                <col width="5%" />
	                <col width="20%" />
	                <col width="20%" />
	                <col width="20%" />
	                <col width="5%" />
	                <col width="5%" />
	                <col width="7%" />
	                <col width="18%" />
	              </colgroup>
	              <tr id="pageTableHeader">
	                <th><input type="checkbox" id="allCheckPage" name="allCheckPage" class="checkbox"></th>
	                <th><spring:message code="secfw.page.field.page.page_id" /></th>
	                <th><spring:message code="secfw.page.field.page.page_nm" /></th>
	                <th><spring:message code="secfw.page.field.page.page_url" /></th>
	                <th><spring:message code="secfw.page.field.page.use_yn" /></th>
	                <th><spring:message code="secfw.page.field.page.authcheck_yn" /></th>
	                <th><spring:message code="secfw.page.field.page.developer_nm" /></th>
	                <th><spring:message code="secfw.page.field.page.comments" /></th>
	                
	              </tr>
	            </table>
	            </div>
	            </div>
	            
				<style>
					.h_120 {height:120px;}
					*html .h_120 {height:120px;}
				</style>
				<div class='tabletwo h_120'>
				<table class="list_scr" id="pageTable" >
					<col width="5%" class='tC'/>
	                <col width="20%" class='tC'/>
	                <col width="20%" class='tC'/>
	                <col width="20%" class='tC'/>
	                <col width="5%" class='tC'/>
	                <col width="5%" class='tC'/>
	                <col width="7%" class='tC'/>
	                <col width="18%" class='tC'/>
	              </colgroup>
	              </table>
	            </div>
	           
	            <!-- Total -->
	            <div class="total_num">Total : <span id="pageTotalCnt"></span></div>
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