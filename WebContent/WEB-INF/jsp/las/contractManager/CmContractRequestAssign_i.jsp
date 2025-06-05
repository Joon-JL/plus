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
<%/*계약관리-검토의뢰-배정*/ %>
<body>
<div id="wrap">
	<!-- location -->
		<div class="location"><IMG SRC="../images/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home">계약관리 > 계약검토 ><strong> 배정</strong></div>
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
						<h4>계약검토</h4>
							<!-- button -->
							<div class="btn_wrap fR">
								<span class="btn_all_w"><span class="tsave"></span><a href="#">임시저장</a></span>
								<span class="btn_all_w"><span class="check"></span><a href="#">검토품의</a></span>
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
						<td width="40">계약서</td>
						<td class="last-td"><img src="../images/icon/ico_word.gif" /> <a href="#none">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약.doc</a></td>
					</tr>
					<tr>
						<td>기타</td>
						<td class="last-td"><img src="../images/icon/ico_excel.gif" /> <a href="#none">라이선스 상세조건.xls</a></td>
					</tr>
				</table>
				<!-- tab -->
				<div class="tab_box" style="margin-top:20px;">
					 <ul class="tab_basic">
						<li class="on"><strong>계약상세내역</strong></li>
						<li><a href="#">사전검토정보</a></li>
					 </ul>
				</div>
				<!-- //tab -->
					<table cellspacing="0" cellpadding="0" class="table-style01" style="margin-top:0">
						<tr>
							<th width="100">계약금액</th>
							<td><input type="text" value="1,000,000" class="text_full align-right" /></td>
							<th width="100">계약단가</th>
							<td><input type="text" value="0" class="text_full align-right" /></td>
							<th width="80">통화(단위)</th>
							<td class="last-td"><select class="all" style="width:96px">
                        <option>USD</option></select></td>
						</tr>
						<tr>
							<th>계약기간</th>
							<td>
								<input type="text" name="" id="" value="2011-07-25"  class="text_calendar"/ ><img src="../images/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /> ~ <input type="text" name="" id="" value="2011-07-25"  class="text_calendar"/ ><img src="../images/icon/ico_calendar.gif" onclick="" class="cp" alt="search" />
							<th>비밀유지기간</th>
							<td class="last-td" colspan="3"><input type="text" value="정보 공개 후 5년" class="text_full" /></td>
						</tr>
						<tr>
							<th>추진목적 및 배경</th>
							<td class="last-td" colspan="5"><img src="../images/common/editor2.gif" /></td>
						</tr>
						<tr>
							<th>기대효과</th>
							<td class="last-td" colspan="5"><textarea name="" class="all2">본 기술도입에 따른 자체 개발기간 단축으로 인해 제품의 조기 개발완료가 가능할 것으로 보이며 향후 5년간 매출 $5,000M 기여 예상됨</textarea></td>
						</tr>
						<tr>
							<th>지불/수금 구분</th>
							<td class="last-td" colspan="5"><select class="all" style="width:200px">
                        <option>지불</option></select></td>
						</tr>
						<tr>
							<th>지불/수금 조건</th>
							<td class="last-td" colspan="5"><textarea name="textarea"  class="all2">1차: $500,000 (계약체결일), 2차: $500,000 (Exhibit A에 명기된 Deliverables 입수 후 당사 승인일)※ 상기 조건 충족후 업체가 발행한 Invoice 수령일로부터 30일 내 지급</textarea></td>
						</tr>
						<tr>
							<th><span>프로젝트명 /<br />적용대상 제품</span></th>
							<td valign="top">Orion2</td>
							<th width="100">라이선스 범위<br />및 조건</th>
							<td class="last-td" colspan="3"><textarea name=""  class="all2">당사가 개발, 외주개발, 생산, 외주 생산하는 전제품에 적용하여 제품을 판매할 수 있는 비독점적, 전세계적...</textarea></td>
						</tr>
						<tr>
							<th rowspan="2">법무담당자</th>
							<td rowspan="2" valign="top"><input type="text" value="법무담당자 선택" style="width:142px" class="text_search"/><img src="../images/icon/ico_search.gif" onclick="javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);" class="cp" alt="search" /></td>
							<th>선택</td>
						  <td class="last-td" colspan="2"><input type="checkbox" id="" value="" checked="checked" />
							회신전 결재확인 
							<input type="checkbox" id="" value="" disabled="disabled" />
							부담당자
							<a href="#none"><img src="../images/icon/ico_search_g.gif" /></a>					
							</td>
							<td class="last-td align-right"><a href="#none"><img src="../images/btn/btn_appoint.gif" /></a></td>
						</tr>
						<tr>
							<th>이관선택</th>
							<td class="last-td" colspan="2"><select class="all" style="width:200px"><option>국내 → 해외</option></select>
							</td>
							<td class="last-td align-right"><a href="#none"><img src="../images/btn/btn_transfer.gif" /></a>
							<a href="#none"><img src="../images/btn/btn_return.gif" /></a></td>
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