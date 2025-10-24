<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- 
/**
 * 파  일  명 : Consideration_old_info.jsp
 * 프로그램명 : 검토의뢰 상세정보 리턴
 * 설      명 : (구)법무시스템 검토의뢰 상세정보 조회용 페이지
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>

<input type="hidden" name="cnclsnpurps_bigclsfcn" 	id="cnclsnpurps_bigclsfcn" 	value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적_대분류 -->
<input type="hidden" name="prgrs_status" 			id="prgrs_status" 			value="<c:out value='${lomRq.prgrs_status}'/>" />			<!-- 검토의뢰_진행상태 -->
<input type="hidden" name="mn_cnsd_dept" 			id="mn_cnsd_dept" 			value="<c:out value='${lomRq.mn_cnsd_dept}'/>" />			<!-- 검토의뢰_정_검토_부서 -->
<input type="hidden" name="mn_respman_apnt_yn" 		id="mn_respman_apnt_yn" 	value="<c:out value='${lomRq.mn_respman_apnt_yn}'/>" />		<!-- 검토의뢰_정_담당자_지정_여부 -->
<input type="hidden" name="vc_cnsd_dept" 			id="vc_cnsd_dept" 			value="<c:out value='${lomRq.vc_cnsd_dept}'/>" />			<!-- 검토의뢰_부_검토_부서 -->
<input type="hidden" name="vc_respman_apnt_yn" 		id="vc_respman_apnt_yn" 	value="<c:out value='${lomRq.vc_respman_apnt_yn}'/>" />		<!-- 검토의뢰_부_담당자_지정_여부 -->
<input type="hidden" name="table_div" 				id="table_div" 				value="" />													<!-- 테이블 기준 -->
<input type="hidden" name="respman_apnt_yn" 		id="respman_apnt_yn" 		value="" />													<!-- 담당자 지정 여부 -->
<input type="hidden" name="vc_cnsd_demnd_cont" 		id="vc_cnsd_demnd_cont" 	value="" />													<!-- 검토요청내용 -->

<script language="javascript">
 
function makeInnerHtml(){
	/*
	 - top_role 	: 	RA00 시스템관리자
	 			  		RA01 법무관리자
	 			  		RA02 법무담당자
	 - blngt_orgnz 	:	A00000001 국내법무팀
	 					A00000002 해외법무팀
	 					A00000003 IP센터
	 - cnclsnpurps_bigclsfcn 	: 	T0301 개발
	 								T0303 라이센스
	 								T0302 특허
	 								그외는 기타
	*/
	   
	var cnsdreq_id 				= "<c:out value='${lomRq.cnsdreq_id}'/>";				//검토의뢰_ID
	var cnclsnpurps_bigclsfcn 	= $("#cnclsnpurps_bigclsfcn").val();					//체결목적_대분류
	var blngt_orgnz 			= $("#blngt_orgnz").val();								//소속조직
	var top_role 				= $("#top_role").val();									//권한직위
	var prgrs_status			= $("#prgrs_status").val();								//진행상태
	var prgrs_status_nm			= "<c:out value='${lomRq.prgrs_status_nm}' />";			//진행상태명
	var mn_cnsd_dept			= $("#mn_cnsd_dept").val();								//검토의뢰_정_검토_부서
	var mn_respman_apnt_yn		= $("#mn_respman_apnt_yn").val();						//검토의뢰_정_담당자_지정_여부
	var vc_cnsd_dept			= $("#vc_cnsd_dept").val();								//검토의뢰_부_검토_부서
	var vc_respman_apnt_yn		= $("#vc_respman_apnt_yn").val();						//검토의뢰_부_담당자_지정_여부
	var req_demnd_dt			= "<c:out value='${lomRq.req_demnd_dt}' />";			//검토의뢰_이관_요청_일시
	var res_hndl_dt				= "<c:out value='${lomRq.res_hndl_dt}' />";				//검토의뢰_이관_처리_일시
	var req_demndman_nm			= "<c:out value='${lomRq.req_demndman_nm}' />";			//검토의뢰_이관_요청자_명
	var res_hndlman_nm			= "<c:out value='${lomRq.res_hndlman_nm}' />";			//검토의뢰_이관_승인자_명
	var trans_status			= "<c:out value='${lomRq.trans_status}' />";			//검토의뢰_이관_상태
	var vc_cnsd_demnd_cont		= "<c:out value='${lomRq.vc_cnsd_demnd_cont}' />";		//검토요청_사유
	var req_cnsd_demnd_dt		= "<c:out value='${lomRq.req_cnsd_demnd_dt}' />";		//검토요청_일시
	var respman_apnt_yn			= "Y";													//검토의뢰_담당자 _지정_여부
	var plndbn_req_yn			= "<c:out value='${lomRq.plndbn_req_yn}' />";			//체결예정본여부
	var pub_yn					= "<c:out value='${lomRq.pub_yn}' />";					//공개여부
	var cnsd_dept_div   		= "";													//Ownership
	
	// A.계약별_검토
	var cnsd_status_a			= "<c:out value='${lomCd.cnsd_status}' />";				//검토_상태
	var cnsd_status_nm_a		= "<c:out value='${lomCd.cnsd_status_nm}' />";			//검토_상태_명
	var apbtman_nm_a			= "<c:out value='${lomCd.apbtman_nm}' />";				//승인자명
	var apbt_dt_a				= "<c:out value='${lomCd.apbt_dt}' />";					//승인일시
	var apbt_opnn_a				= "<c:out value='${lomCd.apbt_opnn}' escapeXml='false' />";				//승인의견
	var rejct_opnn_a			= "<c:out value='${lomCd.rejct_opnn}' escapeXml='false' />";				//부서검토_반려_내용
	
	// B.계약별_부서검토
	var cnsd_status_b			= "<c:out value='${lomDcd.cnsd_status}' />";			//검토_상태
	var cnsd_status_nm_b		= "<c:out value='${lomDcd.cnsd_status_nm}' />";			//검토_상태_명
	var apbtman_nm_b			= "<c:out value='${lomDcd.apbtman_nm}' />";				//승인자명
	var apbt_dt_b				= "<c:out value='${lomDcd.apbt_dt}' />";				//일시
	var apbt_opnn_b				= "<c:out value='${lomDcd.apbt_opnn}' escapeXml='false' />";				//승인의견
	var rejct_opnn_b			= "<c:out value='${lomDcd.rejct_opnn}' escapeXml='false' />";				//부서검토_반려_내용
	
	var cnsd_status				= "";													//검토_상태
	var cnsd_status_nm			= "";													//검토_상태_명
	var apbtman_nm				= "";													//승인자명
	var apbt_dt					= "";													//승인일시
	var apbt_opnn				= "";													//승인의견
	var rejct_opnn				= "";													//부서검토_반려_내용
	var resp_div				= "";													//담당자 구분
	var innerHtmlUp 			= "";
	var innerHtmlDown 			= "";
	var innerHtmlDownApbt 		= "";
	var resp_cnt				= 0;													//소속부서 담당자 수
	var resp_sub_cnt			= 0;													//상대 협업부서 담당자 수
	
	if(blngt_orgnz == mn_cnsd_dept){									//소속조직으로 담당자 지정여부 판단
		respman_apnt_yn = mn_respman_apnt_yn;
		$("#respman_apnt_yn").val(mn_respman_apnt_yn);
	}else if(blngt_orgnz == vc_cnsd_dept){
		respman_apnt_yn = vc_respman_apnt_yn;
		$("#respman_apnt_yn").val(vc_respman_apnt_yn);
	}else{
		//alert("로그인 사용자의 소속조직과 해당 검토의뢰 담당부서가 맞지 않습니다.");
		//return;
	}
	
	//Ownership 확인
	if(mn_cnsd_dept == "A00000001" || mn_cnsd_dept == "A00000002"){
		if(blngt_orgnz == 'A00000001' || blngt_orgnz == 'A00000002'){
			cnsd_status = cnsd_status_a;
			cnsd_status_nm = cnsd_status_nm_a;
			apbtman_nm = apbtman_nm_a;
			apbt_dt = apbt_dt_a;
			apbt_opnn = apbt_opnn_a;
			rejct_opnn = rejct_opnn_a;
			$("#table_div").val('A');
		}
		cnsd_dept_div = "law";
	}else if(mn_cnsd_dept == 'A00000003'){
		if(blngt_orgnz == 'A00000001' || blngt_orgnz == 'A00000002'){
			cnsd_status = cnsd_status_b;
			cnsd_status_nm = cnsd_status_nm_b;
			apbtman_nm = apbtman_nm_b;
			apbt_dt = apbt_dt_b;
			apbt_opnn = apbt_opnn_b;
			rejct_opnn = rejct_opnn_b;
			$("#table_div").val('B');
		}else if(blngt_orgnz == 'A00000003'){
			cnsd_status = cnsd_status_a;
			cnsd_status_nm = cnsd_status_nm_a;
			apbtman_nm = apbtman_nm_a;
			apbt_dt = apbt_dt_a;
			apbt_opnn = apbt_opnn_a;
			rejct_opnn = rejct_opnn_a;
			$("#table_div").val('A');
		}
		cnsd_dept_div = "ip";
	}
	
	//소속부서 담당자 수 count
	<c:forEach var="list" items="${lomResp}">
		resp_cnt += 1;
	</c:forEach>
	
	//협업부서 담당자 수 count
	<c:forEach var="list" items="${lomRespSub}">
		resp_sub_cnt += 1;
	</c:forEach>
	
				  innerHtmlUp = "<table id='tr_down01' cellspacing='0' cellpadding='0' class='table-style01' border='0'>"
							  + "	<colgroup>"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "	</colgroup>";
							  
	//*검토회신
	if(((apbt_opnn != null && apbt_opnn != "") || (rejct_opnn != null && rejct_opnn != "")) && ((blngt_orgnz == "A00000001" || blngt_orgnz == "A00000002"))) {
			innerHtmlDownApbt +="<tr><td>&nbsp;</td></tr>"
			  				  + "<table cellspacing='0' cellpadding='0' class='table-style01' border='0'>"
							  + "	<colgroup>"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "	</colgroup>";
		if(apbt_opnn != null && apbt_opnn != ""){
			innerHtmlDownApbt +="	<tr>"
				  			  + "		<th><spring:message code='las.page.field.contractManager.approveReview'/></th>"
							  + "		<td colspan='3'>"			
				 			  + "			<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' checked='checked' value='Y' /><spring:message code='las.page.field.contractManager.approval2'/>&nbsp;&nbsp;"
					  		  + "			<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' value='N' /><spring:message code='las.page.field.contractManager.reject'/>&nbsp;&nbsp;"
				  		  	  + "		</td>"
				  		  	  + "   </tr>"
				  			  + "	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.appCmt'/></th>"
							  + "		<td colspan='3'><pre>" + apbt_opnn + "</pre></td>"
							  + "   </tr>";

		}else if(rejct_opnn != null && rejct_opnn != ""){
			innerHtmlDownApbt +="	<tr>"
			  			  	  + "		<th><spring:message code='las.page.field.contractManager.approveReview'/></th>"
						  	  + "		<td colspan='3'>"
				 			  + "			<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' value='Y' /><spring:message code='las.page.field.contractManager.approval2'/>&nbsp;&nbsp;"
		  		  			  + "			<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' checked='checked' value='N' /><spring:message code='las.page.field.contractManager.reject'/>&nbsp;&nbsp;"
				  		  	  + "		</td>"
				  		  	  + "   </tr>"
				  			  + "	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.rejectCmt'/></th>"
							  + "		<td colspan='3'><pre>" + rejct_opnn + "</pre></td>"
							  + "   </tr>";
		}
		innerHtmlDownApbt +="</table>";
	}
	
	//*배정
	  			innerHtmlDown +="<div class='title_basic'>"
			  				  + "<h4><spring:message code='las.page.field.contractManager.reviewEmp'/></h4>"
			  				  + "</div>"
			  				  + "<table id='tr_down01_resp' cellspacing='0' cellpadding='0' class='table-style01' border='0'>"
							  + "	<colgroup>"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "		<col width='15%' />"
							  + "		<col width='35%' />"
							  + "	</colgroup>"
					 		  + "	<tr>"
					 		  + "		<th><spring:message code='las.page.field.contractManager.legal'/></th>";
	if(resp_cnt != "0"){
				innerHtmlDown +="		<td valign=bottom>"
							  + "			<ul id='list_respman_id' name='list_respman_id' class='las_men' style='border:0px;width:100%;margin-left:0px;margin-top:2px;'>"
							  + "			<c:forEach var='list' items='${lomResp}'>"
							  + "			<li id='" + "<c:out value='${list.list_respman_id}'/>" + "'>" + "<c:out value='${list.list_respman_nm}'/>" + "</li><input type='hidden' name='list_respman_ids' id='" + "id_" + "<c:out value='${list.list_respman_id}'/>" + "' value='" + "<c:out value='${list.list_respman_id}'/>" + "'></input>"
							  + "			</c:forEach>"
							  + "			</ul>"
				     		  + "		</td>";
	}else{
				innerHtmlDown +="		<td><spring:message code='las.page.field.contractManager.noPersonInChrg'/></td>";
	}
				innerHtmlDown +="		<th>IP</th>";
	if(resp_sub_cnt != "0"){
				innerHtmlDown +="		<td valign=bottom>"
						  	  + "			<ul id='list_respman_id_sub' name='list_respman_id_sub' class='las_men' style='width:100%;margin-left:0px;margin-top:2px;'>"
							  + "			<c:forEach var='list' items='${lomRespSub}'>"
							  + "			<li id='" + "<c:out value='${list.list_respman_id}'/>" + "'>" + "<c:out value='${list.list_respman_nm}'/>" + "</li><input type='hidden' name='list_respman_ids' id='" + "id_" + "<c:out value='${list.list_respman_id}'/>" + "' value='" + "<c:out value='${list.list_respman_id}'/>" + "'></input>"
							  + "			</c:forEach>"
							  + "			</ul>"
							  + "		</td>";
	}else{
				innerHtmlDown +="		<td><spring:message code='las.page.field.contractManager.noPersonInChrg'/></td>";
	}
				innerHtmlDown +="	</tr>"
							  + "</table>";


				  innerHtmlUp +="	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.mainDept'/></th>"
							  + "		<td colspan='3'>";
	if(cnsd_dept_div == "law"){
				  innerHtmlUp +="			<input name='' type='radio' disabled='disabled' class='radio' value='' checked='checked' /><label for='' ><spring:message code='las.page.field.contractManager.lgTeam'/></label>"
							  + "			<input name='' type='radio' disabled='disabled' class='radio' value='' /><label for='' ><spring:message code='las.page.field.contractManager.ipCnt'/></label>";
	}else if(cnsd_dept_div == "ip"){
				  innerHtmlUp +="			<input name='' type='radio' disabled='disabled' class='radio' value='' /><label for='' ><spring:message code='las.page.field.contractManager.lgTeam'/></label>"
		 		 			  + "			<input name='' type='radio' disabled='disabled' class='radio' value='' checked='checked' /><label for='' ><spring:message code='las.page.field.contractManager.ipCnt'/></label>";
	}
	  			  innerHtmlUp +="		</td>";
	/*
			  	  innerHtmlUp +="		<th>공개여부</th>"
						  + "		<td>";
	if(pub_yn == "Y"){
			  	  innerHtmlUp +="			<input name='' type='radio' disabled='disabled' class='radio' value='' checked='checked' /><label for='' >Yes</label>"
						  + "			<input name='' type='radio' disabled='disabled' class='radio' value='' /><label for='' >No</label>";
	}else if(pub_yn == "N"){
			 	  innerHtmlUp +="			<input name='' type='radio' disabled='disabled' class='radio' value='' /><label for='' >Yes</label>"
			  			  + "			<input name='' type='radio' disabled='disabled' class='radio' value='' checked='checked' /><label for='' >No</label>";
	}
				  innerHtmlUp +=" 		</td>";
	
	*/
		  		  innerHtmlUp +="	</tr>";

	if(blngt_orgnz == "A00000001" || blngt_orgnz == "A00000002") {
			/* IP센터는 체결예정본 회신이고 IP센터가 ownership을 가지고 있는 경우만 내부결제 가능 */
				  innerHtmlUp +="	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.reviewStat'/></th>"
							  + "		<td colspan='3'>" + cnsd_status_nm;
							  + "		</td>"
			  	  			  + " 	</tr>";
		if(rejct_opnn != null && rejct_opnn != ""){
				  innerHtmlUp +="	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.groupChief'/><br><spring:message code='las.page.field.contractManager.rejectRs'/></th>"
							  + "		<td colspan='3'><pre>" + rejct_opnn + "</pre></td>"
							  + "   </tr>";
		}
	
		if(apbt_dt != null && apbt_dt != ""){
				  innerHtmlUp +="	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.lastChkP'/></th>"
							  + "		<td>" + apbtman_nm + "</td>"
							  + "		<th><spring:message code='las.page.field.contractManager.lastChkDate'/></th>"
							  + "		<td>" + apbt_dt + "</td>"
							  + "	</tr>"
							  + "	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.gcConfirm'/></th>"
							  + "		<td colspan='3'><pre>" + apbt_opnn + "</pre></td>"
							  + "   </tr>";
		}
	}
	
	if(prgrs_status == 'C04212'){
			  	  innerHtmlUp +="	<tr>"
							  + "		<th><spring:message code='las.page.field.contractManager.pstpnRs'/></th>"
							  + "		<td colspan='3'><pre>" + "<c:out value='${lomRq.cnsd_hold_cause}' />" + "</pre></td>"
							  + " 	</tr>";
	}
				  innerHtmlUp +="</table>";

	document.getElementById("detailInfoHtmlUp").innerHTML = "";
	document.getElementById("detailInfoHtmlUp").innerHTML = innerHtmlUp;
		
	document.getElementById("detailInfoHtmlDown").innerHTML = "";
		
	if(innerHtmlDownApbt == ""){
		document.getElementById("detailInfoHtmlDown").innerHTML = innerHtmlDown;
	}else{
		document.getElementById("detailInfoHtmlDown").innerHTML = innerHtmlDownApbt;
	}
		
	$('#list_respman_id li').bind('click', function(){
		$('#list_respman_id li').removeAttr("style", "");
		$('#list_respman_id li').removeClass('selected');
		
		$(this).attr("style", "background: #dddddd;");
		$(this).addClass("selected");		
	});
}

</script>