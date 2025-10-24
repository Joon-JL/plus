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

<script language="javascript" src="<c:url value='/script/las/common.js' />" type="text/javascript"></script>

<LINK href="<%=CSS %>/add_design.css" type="text/css" rel="stylesheet">
<link href="<%=CSS %>/new_style.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/las/jquery-1.6.2.min.js' />" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/las/new_style.js' />" type="text/javascript"></script>

</head>
<%
  /*계약관리-검토의뢰-목록*/ 
  /*법무시스템 > 계약검토 > 국내 (해외)*/ 
%>
<body>
<div id="wrap">
	<!-- header -->	
	<!-- //header -->
	<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home">계약관리 > 계약검토 ><strong> 목록</strong></div>
	<!-- //location -->
	<!-- container -->
	<div id="container">
		<!-- title -->
			<div class="title">
				<h3>목록</h3>
			</div>
			<!-- //title -->
			<!-- content -->
			<div id="content">
				<!-- search-->
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
										<col width="23%"/>
										<col width="10%"/>
										<col width="23%"/>
										<col width="10%"/>
										<col width="23%"/>
									</colgroup>
									<tr>
										<th>의뢰일</th>
										<td>
											<input type="text" value="2011-04-20"  class="text_calendar"/ ><img src="<%=IMAGE %>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /> ~
											 <input type="text" value="2011-12-15"  class="text_calendar"/><img src="<%=IMAGE %>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /></td>
										<th>의뢰자<span class="astro"></span></th>
										<td>
											<input type="text" style="width:160px" class="text_search"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th>계약유형<span class="astro"></span></th>
										<td>
											<input type="text" name="text_field11" id="" value="" style="width:160px" class="text_search"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
									</tr>
									<tr>
										<th>계약대상</th>
										<td>
											<input type="text" name="text_field11" style="width:160px" class="text_search"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th>Step</th>
										<td>
											<select class="all" style="width:182px">
											<option>계약검토</option></select>
										</td>
										<th>상태</th>
										<td>
											<select class="all" style="width:182px">
											<option>선택</option></select>
										<td></td>
									</tr>
									<tr>
									<th>검토의뢰 제목</th>
									<td colspan="3"><input type="text" value="" style="width:466px" class="text_full"/></td>
									<td> </td>
									<td> </td>
									</tr>
								</table>
							</td>
							<td class="vb tC"><a href="#"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="조회"/></a></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="btn-box" style="padding-top:5px;">
					<span class="right-set">
						<span class="btn_all_w"><span class="reset"></span><a href="#">Drop처리</a></span>
					</span>
				</div>
				<!-- list -->
				<table class="list_basic list_basic_new" style="width:100%;">
					<colgroup>
						<col width="49px"/>
						<col width="297px" />
						<col width="74px"/>
						<col width="76px"/>
						<col width="108px"/>
						<col width="95px"/>
						<col width="96px"/>
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" id="contract" value="y"/></th>
							<th>검토의뢰 제목</th>
							<th>계약건수</th>
							<th>의뢰자</th>
							<th>등록일</th>
							<th>Step</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">대만 CHIMEI, LCD용 Connector사용에 따른 License 계약 검토 요청</a></td>
							<td class="tC">3</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
						  <td class="tC">담당자 배정전</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">일본 Chisso, liquid crystal 구매 계약 검토 요청</a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">중국 Foxconn, Desktop ODM 공급 계약 검토 요청</a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">Xerox, Captiva product specific agreement(PSA) OEM 계약 검토 </a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">독일 TomTec사 소프트웨어 구매 및 용역개발 협의를 위한 NDA 검토 요청</a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">중국 APEX 초음파 Probe 위탁개발 NDA 검토 요청</a></td>
							<td class="tC">2</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">터키 Vestel과의 LCD 모듈 공정 용역 서비스계약 검토 요청</a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
						  <td class="tC">검토중</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">미국 Verizon사향 Netbook 공급 계약 검토 요청</a></td>
							<td class="tC">1</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토완료</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">캐나다 Videotron Set-top Box 공급 계약 검토 요청</a></td>
							<td class="tC">3</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
						  <td class="tC">검토중</td>
						</tr>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='on'">
							<td class="tC"><input type="checkbox" id="contract" value="y"/></td>
							<td class="overflow"><a href="#">대만 CHIMEI, LCD용 Connector사용에 따른 License 계약 검토 요청</a></td>
							<td class="tC">4</td>
							<td class="tC">김의뢰</td>
							<td class="tC">2011-03-24</td>
							<td class="tC">계약검토</td>
							<td class="tC">검토중</td>
						</tr>
					</tbody>
				</table>
				<!-- //list -->
				<div class="total_num">
					Total: 10건
				</div>
				<!-- pagination -->
				<div class="pagination">
					<a href="#" class="img"><img src="<%=IMAGE %>/icon/page_first.gif" alt="처음"/></a>
					<a href="#" class="img"><img src="<%=IMAGE %>/icon/page_prev.gif" alt="이전"/></a> <a href="#">1</a> <strong>2</strong>
					<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#">7</a> <a href="#">8</a> <a href="#">9</a>
					<a href="#">10</a> <a href="#" class="img"><img src="<%=IMAGE %>/icon/page_next.gif" alt="다음"/></a>
					<a href="#" class="img"><img src="<%=IMAGE %>/icon/page_last.gif" alt="마지막"/></a>
				</div>
				<!-- //pagination -->
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