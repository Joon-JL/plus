<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>::: 삼성전자 계약관리시스템 :::</title>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript">
function goRequest(){
	document.location="/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/las/test/contractmanage_d.jsp";
	
}
</script>
</head>
<body>
<div>
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home">계약관리 > 계약검토 ><strong> 목록</strong></div>
		<!-- //location -->
		<!-- content -->	
		<div id="content">
			<!-- title -->
			<div class="title">
				<h3>목록</h3>
			</div>
			<!-- //title -->
			<!--90% 안에서 작업하기 search-->
			<div class="search_box">
				<div class="search_box_inner">
					<div class="searchin">
						<ul>
							<li>
								<dl>
									<dt style="width:14%;">의뢰일</dt>
									<dd style="width:31%;">
										<input type="text" value="2011-04-20"  class="text_calendar02"/ ><img src="<%=IMAGE%>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /> ~ 
										<input type="text" value="2011-12-15"  class="text_calendar02"/><img src="<%=IMAGE%>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" />
									</dd>
									<dt style="width:14%;">의뢰자</dt>
									<dd style="width:31%;">
										<input type="text" style="width:164px" class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt style="width:14%;">계약유형</dt>
									<dd style="width:31%;">
										<input type="text" name="text_field11" id="" value="" style="width:173px" class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
									</dd>
									<dt style="width:14%;">계약대상</dt>
									<dd style="width:31%;">
										<select class="all" style="width:185px">
											<option>선택</option>
										</select>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt style="width:14%;">Step</dt>
									<dd style="width:31%;">
										<select class="all" style="width:194px">
											<option>선택</option>
										</select>
									</dd>
									<dt style="width:14%;">상태</dt>
									<dd style="width:31%;">
										<select class="all" style="width:185px">
											<option>선택</option>
										</select>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt style="width:14%;">검토의뢰 제목</dt>
									<dd style="width:76%;">
										<input type="text" value="" style="width:544px"/>
									</dd>
								</dl>
							</li>
						</ul>
						<div class="btn_search_01"><a href="#"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="조회"/></a></div>
					</div>
				</div>
			</div>
			<!--//90% 안에서 작업하기 search-->
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_b"><span class="save"></span><a href="#">조회</a></span>
				</div>
			</div>
			<!-- //button -->
			<!-- list -->
			<table class="list_basic">
			  <colgroup>
					<col width="30%" />
					<col width="10%" />
					<col width="12%" />
					<col width="12%" />
					<col width="12%" />
					<col width="12%" />
					<col width="12%" />
				</colgroup>
			  <thead>
			    <tr>
			      <th>검토의뢰 제목</th>
			      <th>계약건수</th>
			      <th>의뢰자</th>
				  <th>담당자</th>
			      <th>등록일</th>
			      <th>Step</th>
			      <th>상태</th>
		        </tr>
		      </thead>
			  <tbody>
			    <tr>
			      <td class="tL"><a href="javascript:goRequest();" class="txtover">대만 CHIMEI, LCD용 Connector사용에 따른 License 계약 검토 요청 검토 요청</a></td>
			      <td>3</td>
			      <td>김의뢰</td>
				  <td>김담당</td>
			      <td>2011-03-24</td>
			      <td>검토의뢰</td>
			      <td class="blue_cor">의뢰</td>
		        </tr>
			    <tr>
			      <td class="tL"><a href="javascript:goRequest();" class="txtover">칩스앤미디어의 MFD(Multi Format Decoder) IP 사용 계약 검토 요청 검토 요청 검토 요청 검토 요청 검토 요청</a></td>
			      <td>1</td>
			      <td>김의뢰</td>
				  <td>김담당</td>
			      <td>2011-03-24</td>
			      <td>검토의뢰</td>
			      <td class="yellow_cor">Drop</td>
		        </tr>
			    <tr>
			      <td class="tL"><a href="javascript:goRequest();" class="txtover">캐나다 Videotron Set-top Box 공급 계약 검토 요청</a></td>
			      <td>3</td>
			      <td>김의뢰</td>
				  <td>김담당</td>
			      <td>2011-03-24</td>
			      <td>검토의뢰</td>
			      <td class="bora_cor">결재완료</td>
		        </tr>
			    <tr>
			      <td class="tL"><a href="javascript:goRequest();" class="txtover">거래기본계약 해지시의 절차 및 준수사항</a></td>
			      <td>4</td>
			      <td>김의뢰</td>
				  <td>김담당</td>
			      <td>2011-03-24</td>
			      <td>검토의뢰</td>
			      <td class="green_cor">결재중</td>
		        </tr>
				<tr class="end">
			      <td class="tL"><a href="javascript:goRequest();" class="txtover">거래기본계약 해지시의 절차 및 준수사항 준수사항</a></td>
			      <td>4</td>
			      <td>김의뢰</td>
				  <td>김담당</td>
			      <td>2011-03-24</td>
			      <td>검토의뢰</td>
			      <td class="red_cor">기타</td>
		        </tr>
		      </tbody>
			  </table>
			<!-- //list -->
		   <!-- pagination -->
			<div class="t_titBtn">
				<div class="total_num">
					Total: 100건
				</div>
				<div class="pagination">
					<a href="#" class="img"><img src="<%=IMAGE%>/icon/page_first.gif" alt="처음"/></a>
					<a href="#" class="img"><img src="<%=IMAGE%>/icon/page_prev.gif" alt="이전"/></a> <a href="#">1</a> <strong>2</strong>
					<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#">7</a> <a href="#">8</a> <a href="#">9</a>
					<a href="#">10</a> <a href="#" class="img"><img src="<%=IMAGE%>/icon/page_next.gif" alt="다음"/></a>
					<a href="#" class="img"><img src="<%=IMAGE%>/icon/page_last.gif" alt="마지막"/></a>
				</div>
			</div>
			<!-- //pagination -->
		</div>
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
	</div>
	<!-- //container -->
</div>
</body>
</html>