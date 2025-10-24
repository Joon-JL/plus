<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.las.board.dto.BbsForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Bbs_d.jsp
 * 프로그램명 : Q&A / 자유게시판 / 자료실 / 법무시스템매뉴얼  - 상세
 * 설      명 : 	단순 CRUD 특성만 갖는 게시판성 메뉴
 *				Q&A 			- C01201 
 *				자유게시판 		- C01214 
 *				자료실			- C01215
 *				법무시스템매뉴얼 	- C01216
 *				VOC				- C01217
 * 작  성  자 : 황민우(수정) 
 * 작  성  일 : 2013.05
 */
--%>
<%
	BbsForm command = (BbsForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	 function setResize(h) {
	  document.getElementById("wec").style.height = h + 40 + 'px';
	 }

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "modify"){
			
			frm.insert_kbn.value = "2";	
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
					frm.method.value = "forwardModifySysQnA";
				</c:when>
				<c:otherwise>
					frm.method.value = "forwardModifyOpenBbs";
				</c:otherwise>
			</c:choose>

			viewHiddenProgress(true) ;
			frm.submit();

		} else if(flag == "reply"){	
			frm.insert_kbn.value = "1";	
			frm.title.value = "<c:out value='${lom.title}' />";
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
					frm.method.value = "forwardInsertReplySysQnA";
				</c:when>
				<c:otherwise>
					frm.method.value = "forwardInsertReplyOpenBbs";
				</c:otherwise>
			</c:choose>

			viewHiddenProgress(true) ;
			frm.submit();

			
		} else if(flag == "delete"){	
			
			if(confirm("<spring:message code='las.msg.ask.delete'/>")){
				var frm = document.frm;
				
				var options = {
                    url: "<c:url value='/las/board/bbs.do?method=delCheck' />",
                    type: "post",
                    success: function(responseText,returnMessage) {
                    	if(responseText>0){
                    		alert("<spring:message code='las.msg.alert.replyIsExisted'/>");
                    		return;
                    	}else{
                    		frm.target = "_self";
            				frm.action = "<c:url value='/las/board/bbs.do' />";
            				<c:choose>
            					<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
            						frm.method.value = "deleteSysQnA";
            					</c:when>
            					<c:when test="${command.bbs_knd=='C01202' || command.bbs_knd=='C01203'}">
            					frm.method.value = "deleteOpenBbs";
            					</c:when>
            					<c:otherwise>
            						frm.method.value = "deletePds";
            					</c:otherwise>
            				</c:choose>

            				viewHiddenProgress(true) ;
            			    frm.submit();
                    	}
                    }
	            };
	            $("#frm").ajaxSubmit(options);   
				
			}
		
		} else if(flag == "list"){	

			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
					frm.method.value = "listSysQnA";
				</c:when>
				<c:otherwise>
					frm.method.value = "listPds";
				</c:otherwise>
			</c:choose>

			viewHiddenProgress(true) ;
			frm.submit();
			
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(grp_no,grp_seqno, postup_srt, postup_depth, total_seqno){
		
		var frm = document.frm;
		
		frm.grp_no.value		= grp_no;
	    frm.grp_seqno.value		= grp_seqno;
	    frm.postup_srt.value	= postup_srt;
	    frm.postup_depth.value	= postup_depth;
	    frm.total_seqno.value	= total_seqno;
	
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/bbs.do' />";
		<c:choose>
			<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
				frm.method.value = "detailSysQnA";
			</c:when>
			<c:otherwise>
				frm.method.value = "detailOpenBbs";
			</c:otherwise>
		</c:choose>

		viewHiddenProgress(true) ;
		frm.submit();		
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

	$(document).ready(function(){
		//첨부파일창 load
		initPage();
		initTextarea();
	});
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}	
	
	function initTextarea(){
		
		if("<c:out value='${command.bbs_knd}'/>" == "C01216"){
			// set textarea for crosseditor contents
	        setTimeout(function(){
		        
	            // contents
	            document.getElementById('cont_show').contentWindow.document.body.innerHTML = document.getElementById('cont_value').value;
	            document.getElementById('cont_show').height = document.getElementById('cont_show').contentWindow.document.body.scrollHeight+25+'px';
	            document.getElementById('cont_show').width = document.getElementById('cont_show').contentWindow.document.body.scrollWidth+10+'px';
	        }, 1000*0.5);
			
		}else{
			// do nothing
		}
		
	}
	
	
//-->

</script>

</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		
		<!-- Title -->
		<div class="title">
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'}">
				<h3><spring:message code="las.page.title.board.SysQnA" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01214'}">
				<h3><spring:message code="las.page.title.board.freeBoard" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01215'}">
				<h3><spring:message code="las.page.title.board.pds_5" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01216'}">
				<h3><spring:message code="las.page.field.board.lasMenual" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01217'}">
				<h3><spring:message code="las.page.title.board.voc" /></h3>
				</c:when>
			</c:choose>
			</div>
			<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<!-- search form -->
			<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
			<input type="hidden" name="srch_type"      value="<c:out value='${command.srch_type}'/>" />
			<input type="hidden" name="srch_word"       value="<c:out value='${command.srch_word}'/>" />
			<!-- key form -->
			<input type="hidden" name="bbs_knd" value="<c:out value='${command.bbs_knd}'/>" />
			<input type="hidden" name="insert_kbn" value="<c:out value='${command.insert_kbn}'/>" />
			<input type="hidden" name="grp_no" value="<c:out value='${command.grp_no}'/>" />
			<input type="hidden" name="grp_seqno" value="<c:out value='${command.grp_seqno}'/>" />
			<input type="hidden" name="postup_srt" value="<c:out value='${command.postup_srt}'/>" />
			<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />
			<input type="hidden" name="total_seqno" value="<c:out value='${command.total_seqno}'/>" />
			<input type="hidden" name="title" value="<c:out value='${command.title}'/>" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F007" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.bbs_knd}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="<c:out value='${command.total_seqno}'/>" />
			<input type="hidden" name="view_gbn" 	value="download" />
			
			<input type="hidden" name="cont" id="cont" 	value="<c:out value='${lom.cont}'/>" />
			<!-- //**************************** Form Setting **************************** -->
			
			<div class="btnwrap">
				<%if(command.isAuth_reply()){ %> 	
				<span class="btn_all_w"><span class="reply"></span><a href="javascript:pageAction('reply')"><spring:message code="las.page.button.reply2" /></a></span>
				<%} %>
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify')"><spring:message code="las.page.button.modify" /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete')"><spring:message code="las.page.button.delete" /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list')"><spring:message code="las.page.button.list" /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>					
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tbody>
		        	<tr>
			            <th><spring:message code="las.page.field.board.notice.title" /></th>
			        	<td colspan="3">
			        	    <c:out value='${lom.title}' escapeXml="false"/>
			        	</td>
		        	</tr>
		        	<tr>
			            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
			        	<td>
			        		<c:out value='${lom.reg_nm}' default="" />/<c:out value="${lom.jikgup_nm }" />/<c:out value="${lom.dept_nm }" />
			        	</td>
			            <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>
			        	<td>
			        		<c:out value='${lom.reg_dt}' default="" />
			        	</td>
		        	</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
							<c:choose>
								<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
									<%=StringUtil.convertEnterToBR((String)lom.get("cont"))%>
								</c:when>
								<c:when test="${command.bbs_knd=='C01216'}">
									<textarea id="cont_value" name="cont_value" style="display: none;"><c:out value='${lom.cont}'/></textarea>
					    			<iframe id=cont_show name="cont_show" src="<c:url value='/clm/blank.do' />" frameborder="0"  style="width:100%"></iframe>
								</c:when>
								<c:otherwise>
									<%-- <c:out value='${lom.cont}' escapeXml="false"/> -- 신성우 변경처리 2014-04-03 --%>
									<%=StringUtil.convertEnterToBR((String)lom.get("cont"))%>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
			    		<th><spring:message code="las.page.field.board.notice.attach_file" /></th>
						<td colspan="3">
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
							<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" ></iframe>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->		
			<div class="t_titBtn">
				<div class="btn_wrap">
<%-- 					<span class="btn_all_w"><span class="confirm"></span><a href="javascript:pageAction('list')"><spring:message code="secfw.page.button.accept" /></a></span> --%>
				</div>
			</div>
			<!-- //Function Button  -->
			
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="7%" />
					<col width="58%" /> <!-- width size 변경 신성우 2014-04-24 -->
					<col width="20%" />
					<col width="15%" />
				</colgroup>
				<tr>
				  	<th><spring:message code="las.page.field.board.notice.no" /></th><!-- 번호 -->
				  	<th><spring:message code="las.page.field.board.notice.title" /></th><!-- 제목 -->
				  	<th><spring:message code="las.page.field.board.notice.reg_nm" /></th><!-- 작성자 -->
				  	<th><spring:message code="las.page.field.board.notice.reg_dt" /></th><!-- 작성일 -->
<%-- 				  	<th><spring:message code="las.page.field.contract.library.rdcnt" /></th><!-- 조회수 --> --%>
				</tr>
				<c:choose>
					<c:when test="${command.totalCnt > 0}">
						<c:forEach var="list" items="${al}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td class="tC"><c:out value='${list.rn}'/></td>
								<td class="tL overflow" title='<c:out value='${list.title}' escapeXml="false"/>'>
						           	<a href='javascript:detailView("<c:out value='${list.grp_no}'/>","<c:out value='${list.grp_seqno}'/>","<c:out value='${list.postup_srt}'/>","<c:out value='${list.postup_depth}'/>","<c:out value='${list.total_seqno}'/>");'>
										<c:if test="${list.new_yn=='Y'}"><span class="iconBox icon020"></span></c:if>
										<c:if test="${list.postup_depth > 0}">
										<c:forEach var="x" begin="0" end="${list.postup_depth}" step="1" >
					        				&nbsp;           				
					        			</c:forEach>
						          			<IMG SRC="<%=IMAGE%>/icon/icon_reply.gif">
										</c:if>
										<c:if test="${list.grp_seqno==command.grp_seqno}">
											<strong><c:out value='${list.title}' escapeXml="false"/></strong>
										</c:if>
										<c:if test="${list.grp_seqno!=command.grp_seqno}">
											<c:out value='${list.title}' escapeXml="false"/>
										</c:if>
						    		</a>
				            </td>
				            <td class="tC"><c:out value='${list.reg_nm}'/></td>
				            <td class="tC"><c:out value='${list.reg_dt}'/></td>
<%-- 				            <td class="tC"><c:out value='${list.rdcnt}'/></td> --%>
						</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="4" align="center"><spring:message code="las.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
				</c:choose>
	       	</table>
			<!-- //List -->
			<div class="btnwrap mt20">
				<%if(command.isAuth_reply()){ %> 	
				<span class="btn_all_w"><span class="reply"></span><a href="javascript:pageAction('reply')"><spring:message code="las.page.button.reply2" /></a></span>
				<%} %>
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify')"><spring:message code="las.page.button.modify" /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete')"><spring:message code="las.page.button.delete" /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list')"><spring:message code="las.page.button.list" /></a></span>
			</div>
				
			</form:form>
		</div>
		</div>
		<!-- //content -->			
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()"> 
 document.wec.Value = document.frm.cont.value;
 document.wec.EditMode = 0;
 document.wec.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
 document.wec.SetDefaultFontSize("9"); 
 
 setResize(document.wec.PrintToImage(600, ""));
</SCRIPT>
</html>