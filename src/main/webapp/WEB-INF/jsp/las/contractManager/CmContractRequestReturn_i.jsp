<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/WEB-INF/jsp/common/common2.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>::: 삼성전자 계약관리시스템 :::</title>
<link href="<%=CSS %>/layout_ko.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS %>/menu_ko.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS %>/unused.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS %>/projectCommon.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="../js/common.js" type="text/javascript"></script>

<LINK href="<%=CSS %>/add_design.css" type="text/css" rel="stylesheet">
<link href="<%=CSS %>/new_style.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="../js/jquery-1.6.2.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/new_style.js" type="text/javascript"></script>

</head>
<%/*계약관리-검토의뢰-검토회신*/ %>
<body>
<div id="wrap">
		<!-- location -->
		<div class="location"><IMG SRC="../images/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home">계약관리 > 계약검토 ><strong> 검토회신</strong></div>
		<!-- //location -->
	<!-- container -->
	<div id="container">
			<!-- title -->
			<div class="title">
				<h3>계약검토</h3>
			</div>
			<!-- //title -->
			<!-- content -->
			<div id="content">
				<!-- title -->
				<div class="newstyle-area">
					<div class="title_basic">
						<h4>검토의뢰 정보</h4>
							<!-- button -->
							<div class="btn_wrap fR">
								<span class="btn_all_w"><span class="tsave"></span><a href="#">임시저장</a></span>
								<span class="btn_all_w"><span class="mail"></span><a href="#">발신</a></span>
								<span class="btn_all_w"><span class="list"></span><a href="#">목록</a></span>
							</div>
							<!-- //button -->
					</div>
					<!-- //title -->
					<table cellspacing="0" cellpadding="0" class="table-style01">
						<tr>
							<th width="100">검토의뢰제목</th>
							<td class="last-td" colspan="7">대만 CHIMEI, LCD용 Connector사용에 따른 License 계약 검토 요청</td>
						</tr>
						<tr>
							<th>의뢰자</th>
							<td>김의뢰
								<a href="#none"><img src="../images/btn/btn_wrap_fr_reference.gif" /></a></td>
							<th width="70">부서</th>
						  <td>무선사업부</td>
							<th width="70">의뢰일</th>
					    <td>2011.06.25</td>
							<th width="70">회신요청일</th>
							<td class="last-td">2011.06.28</td>
						</tr>
						<tr>
							<th><span>회신요청<br />내용</span></th>
							<td class="last-td" colspan="7">L社향 제품에 적용될 LCD용 Connector 기술 특허를 사용하기 위한 Patent License Agreement의 검토를 요청드리오니, 빠른 검토 회신 주시면 감사하겠습니다</td>
						</tr>
					</table>
				<!-- title -->
				<div class="title_basic">
					<h4>계약기본 정보</h4>
				</div>
				<!-- //title -->
				<table cellspacing="0" cellpadding="0" class="table-style01 table-top">
					<tr>
						<th width="100">계약명</th>
						<td class="last-td" colspan="5">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약</td>
					</tr>
					<tr>
						<th>계약상대방</th>
						<td><input type="text" value="CHIMEI" style="width:142px" class="text_search"/><img src="../images/icon/ico_search.gif" onclick="javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);" class="cp" alt="search" /></td>
						<th width="80">고객유형</th>
					  <td>법인</td>
						<th width="80">대표자명</th>
						<td class="last-td">홍길동</td>
					</tr>
					<tr>
						<th width="80">계약유형</th>
						<td colspan="3">
							<input type="text" value="본계약" class="text_full" style="width:142px" />
							<input type="text" value="신규계약" class="text_full" style="width:108px" />
							<input type="text" value="라이선스 계약" class="text_full" style="width:108px" />
							<img src="../images/icon/ico_search_g.gif" />
						</td>
						<th>계약대상</th>
						<td class="last-td"><select class="all" style="width:96px">
                        <option>특허</option></select></td>
					</tr>
					<tr>
						<th>계약대상 상세</th>
						<td class="last-td" colspan="5"><span class="last-td">
							  <textarea name="textarea2" class="all2">LCD용 Connector 기술</textarea></span></td>
					</tr>
				  </table>
				<table cellspacing="0" cellpadding="0" class="table-style01 table-bottom">
					<tr>
						<th width="100" rowspan="2">첨부 파일</th>
						<td width="40"><span class="th-color">계약서</span></td>
						<td class="last-td"><img src="../images/icon/ico_word.gif" /> <a href="#none">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약.doc</a></td>
					</tr>
					<tr>
						<td><span class="th-color">기타</span></td>
						<td class="last-td"><img src="../images/icon/ico_excel.gif" /> <a href="#none">라이선스 상세조건.xls</a></td>
					</tr>
				</table>
				<!-- tab -->
				<div class="tab_box" style="margin-top:20px;">
					 <ul class="tab_basic">
						<li><a href="#">계약상세내역</a></li>
						<li><a href="#">사전검토정보</a></li>
						<li class="on"><strong>회신</strong></li>
					 </ul>
				</div>
				<!-- //tab -->
					<table cellspacing="0" cellpadding="0" class="table-style01" style="margin-top:0">
						<tr>
							<th width="110">법무담당자</th>
							<td><select class="all" style="width:200px"><option>김법무</option></select></td></td>
							<th width="100">부서</th>
							<td>해외법무그룹(본사)</td>
							<th width="100">공개여부</th>
							<td class="last-td">
								<input name="" type="radio" disabled="disabled" class="radio" value="" checked/><label for="" >Yes</label>
								<input type="radio" id="" name="" value=""/><label for="" >No</label></td>
						</tr>
						<tr>
							<th>법무팀 의견</th>
						  <td class="last-td" colspan="5"><img src="../images/common/editor2.gif" /></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td class="last-td" colspan="5"><input type="checkbox" value=""/>
							<img src="../images/icon/ico_word.gif" />
							<a href="#none">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약.doc</a>
							<img src="../images/icon/ico_close_s.gif" />
							<span class="btn_option"><span class="add"></span><a href="#">파일찾기</a></span></td>
						</tr>
					</table>
				<br />
				<br />
				<br />
				</div>
			</div>
			<!-- //content -->
		<!-- footer -->
		<br /><br /><br />
		<div id="footer">
			<address>
			ⓒ SAMSUNG
			</address>
		</div>
		<!-- //footer -->
	</div>
	<!-- //container -->
</div>
</body>
</html>