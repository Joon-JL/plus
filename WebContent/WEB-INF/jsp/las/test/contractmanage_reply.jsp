<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>::: 삼성전자 계약관리시스템 :::</title>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
<script language="javascript">
function goScheduledReply() {
	document.location="/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/las/test/contractmanage_scheduledreply.jsp";
}

function goList() {
	document.location="/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/las/test/contractmanage_l.jsp";
}

</script>
</head>
<body>

<!-- container -->
<div id="container">
<!-- location -->
<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"> 계약관리 > 검토검토 > <strong>검토회신</strong></div>
<!-- //location -->
	<!-- content -->
	<div id="content">
		<!-- title -->	
		<div class="title">
			<h3 class="h3_mz">계약검토</h3>
		</div>		
		<!-- //title -->
		<div class="t_titBtn">
			<!-- title -->
			<div class="title_basic">
				<h4>검토의뢰정보  <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/></h4>
			</div>
			<!-- //title -->
			<!-- button -->
			<div class="btn_wrap_t03">
				<span class="btn_all_w"><span class="tsave"></span><a href="#">임시저장</a></span>
				<span class="btn_all_w"><span class="mail"></span><a href="javascript:goScheduledReply();">발신</a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();">목록</a></span>
			</div>
			<!-- //button -->
		</div>
		
		<!-- toptable -->
		<table cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="22%" />
				<col width="14%" />
				<col width="18%" />
				<col width="14%" />
				<col width="18%" />
			</colgroup>
			<tr>
				<th>검토의뢰제목</th>
				 <td colspan="5">계약서 검토 요청의 건계약서 검토 요청의 건계약서 검토 요청의 건</td>
			</tr>
			<tr>
				<th>의뢰자</th>
				<td>
					김의뢰 <a href="#none"><img src="<%=IMAGE%>/btn/btn_wrap_fr_reference.gif" alt="참조" /></a>
				</td>
				<th>부서</th>
				<td>무선사업부</td>
				<th>의뢰일</th>
				<td>2011.06.25</td>
			</tr>
			<tr>
				<th>회신요청일</th>
				<td colspan="5">2011.06.25</td>
			</tr>
		</table>
		<!-- //top table -->
		<!-- table011 -->
		<table cellspacing="0" cellpadding="0" class="table-style01 borz01" id="tr_down01" style="display:block;">
			<colgroup>
				<col width="15%" />
				<col width="22%" />
				<col width="14%" />
				<col width="18%" />
				<col width="14%" />
				<col width="18%" />
			</colgroup>
			<tr id="tr_down01">
				<th class="borTz02"><span>검토요청 내용</span></th>
				<td colspan="5">검토 요청 내용 입니다. 검토 요청 내용 입니다.  검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. 검토 요청 내용 입니다. </td>
			</tr>
			<tr>
				<th>법무담당자</th>
				<td>
					<select class="all" style="width:74px">
					<option>김법무</option>
					</select>
				</td>
				<th>부서</th>
				<td>무선사업부(본사)</td>
				<th>공개여부</th>
				<td>
					<input name="" type="radio" disabled="disabled" class="radio" value="" checked/> <label for="" >Yes</label>
					<input name="" type="radio" disabled="disabled" class="radio" value="" checked/> <label for="" >No</label>
				</td>
			</tr>
			<tr class="end">
				<th>첨부파일</th>
				<td colspan="5">
					<div>
						<img src="<%=IMAGE%>/icon/ico_excel.gif" /> 
						<a href="#none">라이선스 상세조건.xls</a>
						<a href="#" class="add_del">삭제</a>
					</div>
					<div>
						<img src="<%=IMAGE%>/icon/ico_excel.gif" /> 
						<a href="#none">라이선스 상세조건.xls</a>
						<a href="#" class="add_del">삭제</a>
					</div>
				</td>
			</tr>
		</table>
		<!-- //table011 -->
		<!-- title -->
	  <div class="title_basic">
			<h4>계약기본 정보 <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');"/></h4>
		</div>
		<!-- //title -->
		<!-- tab01 -->
		<ul class="tab_basic04">
			<li class="on"><input type="checkbox" value=""/> <label for="" ><a href="#">계약1</a></label></li>
			<li><input type="checkbox" value=""/> <label for="" ><a href="#">계약2</a></label></li>
			<li><input type="checkbox" value=""/> <label for="" ><a href="#">계약3</a></label></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<!-- middle table -->
		<table cellspacing="0" cellpadding="0" class="table-style03">
		   <colgroup>
				<col width="15%" />
				<col width="9%" />
				<col width="21%" />
				<col width="14%" />
				<col width="12%" />
				<col width="12%" />
				<col width="17%" />
			</colgroup>

			<tr>
				<th>계약명</th>
				<td colspan="6"><input type="text" class="text_full" value="무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약" /></td>
			</tr>
			<tr>
				<th>계약상대방</th>
				<td colspan="2"><input type="text" value="CHIMEI" style="width:142px" class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);" class="cp" alt="search" /></td>
				<th>고객유형</th>
				<td>법인</td>
				<th>대표자명</th>
				<td>홍길동</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th>계약유형</th>
				<td colspan="4">
					<input type="text" value="본계약"  class="text_full" style="width:142px" />
					<input type="text" value="신규계약"  class="text_full" style="width:108px" />
					<input type="text" value="라이선스계약"  class="text_full" style="width:108px" />
					<a href="#none" ><img src="<%=IMAGE%>/icon/ico_search_g.gif" /></a>
				</td>
				<th>자동연장여부</th>
				<td>
					<input name="" type="radio" disabled="disabled" class="radio" value="" checked/> <label for="" >Yes</label>
					<input name="" type="radio" disabled="disabled" class="radio" value="" checked/> <label for="" >No</label>
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th>계약대상</th>
				<td colspan="2">
					<select class="all" style="width:96px">
					<option>특허</option></select>
				</td>
				<th>계약대상 상세</th>
				<td colspan="3"><input type="text" value="LCD용 Connector 기술" class="text_full" /></td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th rowspan="2">첨부 파일</th>
				<td class="blueD"><span class="blueD">계약서</span></td>
				<td colspan="5">
					<img src="<%=IMAGE%>/icon/ico_word.gif" /> 
					<a href="#none">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약.doc</a>
					<a href="#" class="add_del">삭제</a>
					<span class="btn_option pad_h_5"><span class="add"></span><a href="#">파일찾기</a></span>
				</td>
			</tr> 
			<tr class="slide-target02 slide-area">
				<td class="tal_lineL"><span class="blueD">기타</span></td>
				<td colspan="5">
					<img src="<%=IMAGE%>/icon/ico_excel.gif" /> 
					<a href="#none">라이선스 상세조건.xls</a>
					<a href="#" class="add_del">삭제</a>
					<span class="btn_option"><span class="add"></span><a href="#">파일찾기</a></span>
				</td>
			</tr>
		</table>
		<!--//middle table -->

		<!--bottom table -->
		<div class="tal_subBox" id="tr_down02" style="display:block;">
			<!-- tab -->
			<div class="tab_box">
				 <ul class="tab_basic">
					<li><a href="#">계약상세내역</a></li>
					<li><a href="#">사전검토정보</a></li>
					<li class="on"><a href="#">회신</a></li>
				 </ul>
			</div>
			<!-- //tab -->

			<!-- table001-->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="14%" />
					<col width="86%" />
				</colgroup>
				<tr>
					<th>사안개요</th>
					<td>
						<img src="<%=IMAGE%>/common/editor2.gif" />
					</td>
				</tr>
				<tr>
					<th>Ownership</th>
					<td colspan="4">
						<input name="" type="radio" disabled="disabled" class="radio" value="" checked/> <label for="" >법무팀</label>
						<input name="" type="radio" disabled="disabled" class="radio" value="" /> <label for="" >IP센터</label>
					</td>
				</tr>
				<tr>
					<th>일반조항<br>검토의견</th>
					<td colspan="4">
						<img src="..<%=IMAGE%>/common/editor2.gif" />
					</td>
				</tr>
				<tr>
					<th>IP조항<br>검토의견</th>
					<td colspan="4">
						<img src="..<%=IMAGE%>/common/editor2.gif" />
					</td>
				</tr>
				<tr>
					<th>종합검토 의견</th>
					<td colspan="4">
						<img src="..<%=IMAGE%>/common/editor2.gif" />
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="4">
						<div>
							<input type="checkbox" value=""/>
							<img src="..<%=IMAGE%>/icon/ico_excel.gif" /> 
							<a href="#none">라이선스 상세조건.xls</a>
							<a href="#" class="add_del">삭제</a>
							<span class="btn_option"><span class="add"></span><a href="#">파일찾기</a></span>
						</div>
					</td>
				</tr>
			</table>
			<!-- //table001 -->
		</div>
		<!-- //content -->
	</div>
	<!-- //bottom table -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</div>
<!-- //container -->
</body>
</html>