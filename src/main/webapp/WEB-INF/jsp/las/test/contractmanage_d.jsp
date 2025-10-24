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
function goList() {
	document.location="/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/las/test/contractmanage_l.jsp";
}

function goReply() {
	document.location="/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/las/test/contractmanage_reply.jsp";
}
</script>
</head>
<body>

<!-- container -->
<div id="container">
<!-- location -->
<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"> 계약관리 > 계약검토 > <strong>배정</strong></div>
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
				<span class="btn_all_w"><span class="check"></span><a href="javascript:goReply();">검토회신</a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();">목록</a></span>
			</div>
			<!-- //button -->
		</div>
		
		<!-- toptable -->
		<table cellspacing="0" cellpadding="0" class="table-style01" border="0">
			<colgroup>
					<col width="15%" />
					<col width="40%" />
					<col width="13%" />
					<col width="32%" />
				</colgroup>
			<tr>
				<th>검토의뢰제목</th>
			  <td colspan="3">계약서 검토 요청의 건</td>
			</tr>
			<tr>
				<th>의뢰자</th>
				<td>
					<select class="all" style="width:74px">
					<option>김의뢰</option>
					</select>
					<a href="#none"><img src="<%=IMAGE%>/btn/btn_wrap_fr_reference.gif" alt="참조" /></a>
				</td>
				<th>부서</th>
				<td>무선사업부</td>
			</tr>
		</table>
		<!-- //top table -->
		<!-- table01 -->
		<table cellspacing="0" cellpadding="0" class="table-style01 borz01" id="tr_down01" style="display:block;">
			<colgroup>
					<col width="15%" />
					<col width="40%" />
					<col width="13%" />
					<col width="32%" />
				</colgroup>
			<tr>
				<th class="borTz02">의뢰일</th>
				<td>2011.06.25</td>
				<th class="borTz02">회신요청일</th>
				<td><input type="text" value="2011-04-20" class="text_calendar"/><img src="<%=IMAGE%>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /></td>
			</tr>
			<tr>
				<th>검토요청 내용</th>
				<td colspan="3">
					검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요
					검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요
					검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요! 검토 요청 내용입니다. 참조하세요
				</td>
			</tr>
			<tr>
				<th><span>첨부파일</span></th>
				<td colspan="3">
					<img src="<%=IMAGE%>/icon/ico_excel.gif" /> 
					<a href="#none">라이선스 상세조건.xls</a>
					<a href="#" class="add_del">삭제</a>
					<span class="btn_option"><span class="add"></span><a href="#">파일찾기</a></span>
				</td>
			</tr>
			<tr>
				<th>이관요청사유</th>
				<td><textarea name="textarea" id="textarea" cols="40" rows="3" class="text_area_full"></textarea></td>
				<th>이관요청</th>
				<td>
					<div>
						<span class="bul_txt">이관요청</span>
						<strong>(2011-08-20 13:50)</strong>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer02.gif" alt="이관요청" /></a>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer02.jpg" alt="이관불필요" /></a>
					</div>
				</td>
			</tr>
			<tr>
				<th>법무팀 승인</th>
				<td>
					<div>
						<span class="bul_txt">이관승인</span>
						<strong>(2011-08-20 13:50)</strong>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer03.gif" alt="이관승인" /></a>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer04.gif" alt="이관거절" /></a>
					</div>
				</td>
				<th>법무팀 승인자</th>
				<td>
					이법무 / 해외법무팀
				</td>
			</tr>
			<tr>
				<th>검토요청사유</th>
				<td><textarea name="textarea" id="textarea" cols="40" rows="3" class="text_area_full"></textarea></td>
				<th>검토요청</th>
				<td>
					<div>
						<span class="bul_txt">검토요청</span>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer05.gif" alt="검토요청" /></a>
					</div>
				</td>
			</tr>
			<tr class="end">
				<th>법무담당자</th>
				<td>
					<select class="sel_choo">
					<option>법무담당자 선택</option>
					</select>
					<ul class="las_men">
						<li>이법무1</li>
						<li>이법무1</li>
						<li>이법무1</li>
						<li>이법무1</li>
						<li>이법무1</li>
					</ul>
					<a href="#none"><img src="<%=IMAGE%>/btn/btn_expect.gif" alt="제외" /></a>
				</td>
				<th class="tal_lineL">선택</th>
				<td>
					<div>
						<select class="checkSel">
						<option>국내 -> 해외</option>
						</select>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_transfer.gif" alt="이관" /></a>
						<a href="#none"><img src="<%=IMAGE%>/btn/btn_return.gif" alt="반려" /></a>
						
					</div>
				</td>
			</tr>
		</table>
		<!-- //table01 -->
		<!-- title -->
	  <div class="title_basic">
			<h4>계약기본 정보  <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');"/></h4>
		</div>
		<!-- //title -->
		<!-- tab01 -->
		<ul class="tab_basic02">
			<li class="on"><a href="#">계약1</a></li>
			<li><a href="#">계약2</a></li>
			<li><a href="#">계약3</a></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<!-- middle table -->
		<table cellspacing="0" cellpadding="0" border="0" class="table-style03">
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
				<td colspan="6"><span class="fL">무선사업부_CHIMEI_본계약_신규_Patent_라이선스계약</span><span class="tal_print fR"><a href="#">프린터하기</a></span></td>
			</tr>
			<tr>
				<th>계약상대상</th>
				<td colspan="2"><input type="text" value="CHIMEI" style="width:142px" class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);" class="cp" alt="search" /></td>
				<th>고객유형</th>
				<td>법인</td>
				<th>대표자명</th>
				<td>홍길동</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th>계약유형</th>
				<td colspan="6">
					<input type="text" value="본계약" class="text_full" style="width:142px" />
					<input type="text" value="신규계약" class="text_full" style="width:108px" />
					<input type="text" value="라이선스계약" class="text_full" style="width:108px" />
					<a href="#none" ><img src="<%=IMAGE%>/icon/ico_search_g.gif" /></a>
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
					<li class="on"><a href="#">계약상세내역</a></li>
					<li><a href="#">사전검토정보</a></li>
				 </ul>
			</div>
			<!-- //tab -->
			<!-- table001-->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="14%" />
					<col width="10%" />
					<col width="30%" />
					<col width="14%" />
					<col width="32%" />
				</colgroup>
				<tr>
					<th>계약금액</th>
					<td colspan="2"><input type="text" value="1,000,000" width="200px" class="tR"/></td>
					<th>통화(단위)</th>
					<td>
						<select>
						<option>USD</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>계약단가</th>
					<td colspan="2">
						<div><input type="checkbox" value=""/><label for="" > 단가로 체결</label></div>
						<div class="pL3">
							<img src="<%=IMAGE%>/icon/ico_excel.gif" /> 
							<a href="#none">라이선스 상세조건.xls</a>
							<a href="#" class="add_del">삭제</a>
						</div>
					</td>
					<th>단가내역</th>
					<td>
							<textarea name="textarea" id="textarea" cols="30" rows="3" class="text_area_full"></textarea>
					</td>
				</tr>
				<tr>
					<th>계약기간</th>
					<td colspan="2">
						<input type="text" name="" id="" value="2011-07-25"  class="text_calendar"/ ><img src="<%=IMAGE%>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" /> ~ <input type="text" name="" id="" value="2011-07-25"  class="text_calendar"/ ><img src="<%=IMAGE%>/icon/ico_calendar.gif" onclick="" class="cp" alt="search" />
					</td>
					<th>비밀유지기간</th>
					<td ><input type="text" value="정보 공개 후 5년" style="width:300px;"/></td>
				</tr>
				<tr>
					<th>추진목적 <br />및 배경</th>
					<td colspan="4"><img src="<%=IMAGE%>/common/editor2.gif" /></td>
				</tr>
				<tr>
					<th>기대효과</th>
					<td colspan="4"><textarea name="textarea" id="textarea" cols="30" rows="3" class="text_area_full">본 기술도입에 따른 자체 개발기간 단축으로 인해 제품의 조기 개발완료가 가능할 것으로 보이며 향후 5년간 매출 $5,000M 기여 예상됨</textarea></td>
				</tr>
				<tr>
					<th>지불/수불<br />구분</th>
					<td colspan="4"><select class="all" style="width:200px">
				<option>지불</option></select></td>
				</tr>
				<tr>
					<th>지불/수불<br />조건</th>
					<td colspan="4"><textarea name="textarea" id="textarea" cols="10" rows="3" class="text_area_full">1차: $500,000 (계약체결일), 2차: $500,000 (Exhibit A에 명기된 Deliverables 입수 후 당사 승인일)※ 상기 조건 충족후 업체가 발행한 Invoice 수령일로부터 30일 내 지급</textarea></td>
				</tr>
				<tr>
					<th><span style="width:100px;">프로젝트명 /<br />적용대상 제품</span></th>
					<td valign="top" colspan="2"><input type="text" value="Orion2" width="200px" /></td>
					<th><span>라이선스 범위<br />및 조건</span></th>
					<td><textarea name="textarea" id="textarea" cols="39" rows="3" class="text_area_full">당사가 개발, 외주개발, 생산, 외주 생산하는 전제품에 적용하여 제품을 판매할 수 있는 비독점적, 전세계적...</textarea></td>
				</tr>
			</table>
			<!-- //table001 -->
			<!--table002-->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
				<colgroup>
					<col width="14%" />
					<col width="10%" />
					<col/>
				</colgroup>
				<tr>
					<th rowspan="2" class="borTz02">연관계약정보</th>
					<td><span class="th-color">계약명</span></td>
					<td><input type="text" value="무선사업부_CHIMEI_NDA_신규_Patent_라이선스계약
					" style="width:400px" class="text_search"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);" class="cp" alt="search" />
					</td>
				</tr>
				<tr>
					<td class="tal_lineL">
						<span class="th-color">연관유형</span>
					</td>
					<td class="tal_lineL">
						<select name="select2" class="all" style="width:100px">
							<option>Biz 단계</option>
						</select>
						<select name="select2" class="all" style="width:100px">
							<option>NDA</option>
						</select>
						<input type="text" class="text_full" style="width:250px" />
						<img src="<%=IMAGE%>/icon/ico_plus.gif" />
					</td>
				</tr>
			</table>
			<!-- //table002-->
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