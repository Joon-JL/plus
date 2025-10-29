<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : Consideration_inner_i.jsp
 * 프로그램명 : 검토 의뢰내용 수정 팝업 화면
 * 설	  명 : 
 * 작  성  자 : 제이남
 * 작  성  일 : 2013.04
			
 */
--%>
<% 
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");	
	ArrayList listCs = (ArrayList)request.getAttribute("listCs");
	
	ArrayList listRc = (ArrayList)request.getAttribute("listRc");	
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
	
	StringBuffer resultCt = new StringBuffer(1024);
	StringBuffer resultSb = new StringBuffer(1024);
	
	String plndbnReqYn 	= (String)lomRq.get("plndbn_req_yn");
	String respUserDiv 	= "Y"; //(String)lomRq.get("resp_user_div");  모든 권한 사용
	
	Integer attrSeqno;		
	String crtnDeth;	
	String attrNm;
	String attrCont;
	String thAttrNm;
	String mndtryYn;
	String dpMndtry;
	
	if (listTs != null && listTs.size() > 0) {
		for (int i = 0; i < listTs.size(); i++) {
			ListOrderedMap lom = (ListOrderedMap)listTs.get(i);			
			
			attrSeqno	= (Integer)lom.get("attr_seqno");		
			crtnDeth	= (String)lom.get("crtn_deth");	
			attrNm	 	= (String)lom.get("attr_nm");
			attrCont	= (String)lom.get("attr_cont");
			thAttrNm	= attrNm.replaceAll("/", "/<br>");
			mndtryYn	= (String)lom.get("mndtry_yn");	//tn_clm_type_spclinfo 필수여부
			dpMndtry 	= "";		
			
			//필수여부 
			if(respUserDiv.equals("Y") && mndtryYn.equals("Y")){
				dpMndtry = "required alt='"+attrNm+"'";					
			}else{
				dpMndtry = "alt='"+attrNm+"'";
			}  
			
			if(attrSeqno == 5){									// 라이선스범위및조건
				if(respUserDiv.equals("Y")){					// 담당자의 입력 여부
					resultCt.append("<tr> \n");
					if(mndtryYn.equals("Y")){
						resultCt.append("<th><span>* "+attrNm+"</span></th> \n");					
					}else{
						resultCt.append("<th><span>"+attrNm+"</span></th> \n");
					}
					resultCt.append("<td valign='top' colspan='1'> \n");
					resultCt.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
					resultCt.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
					resultCt.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' maxLength='4000'>" + attrCont + "</textarea> \n");
					resultCt.append("</tr> \n");					
				}else if(plndbnReqYn.equals("Y")){				// 예정본여부
					resultCt.append("<tr> \n");
					resultCt.append("<th><span>"+attrNm+"</span></th> \n");
					resultCt.append("<td valign='top' colspan='1'> \n");
					resultCt.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre></td> \n");
					resultCt.append("</tr> \n");
				}
			}else{
				resultCt.append("<tr> \n");
				resultCt.append("<th><span>"+attrNm+"</span></th> \n");
				resultCt.append("<td valign='top' colspan='1'> \n");
				resultCt.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre></td> \n");
				resultCt.append("</tr> \n");
			}
		}
	}
	

	
	if (listCs != null && listCs.size() > 0) {
		for (int i = 0; i < listCs.size(); i++) {
			ListOrderedMap lom = (ListOrderedMap)listCs.get(i);
			
			attrSeqno	= (Integer)lom.get("attr_seqno");		
			crtnDeth	= (String)lom.get("crtn_deth");	
			attrNm	 	= (String)lom.get("attr_nm");
			attrCont	= (String)lom.get("attr_cont");
			thAttrNm	= attrNm.replaceAll("/", "/<br>");
			mndtryYn	= (String)lom.get("mndtry_yn");	//tn_clm_type_spclinfo 필수여부
			dpMndtry 	= "";
			
			//필수여부
			if(respUserDiv.equals("Y") && mndtryYn.equals("Y")){
				dpMndtry = "required alt='"+attrNm+"'";					
			}else{
				dpMndtry = "alt='"+attrNm+"'";
			}
			
			if(respUserDiv.equals("Y")){
				resultSb.append("<tr> \n");
				//필수여부
				if(mndtryYn.equals("Y")){
					resultSb.append("<th><span>* "+thAttrNm+"</span></th> \n");					
				}else{
					resultSb.append("<th><span>"+thAttrNm+"</span></th> \n");
				}
				resultSb.append("<td valign='top'> \n");
				
				resultSb.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
				resultSb.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
				
				if(attrSeqno == 2){				// 독자 개발 가능 여부
					resultSb.append("<select name='arr_attr_cont_idp' id='arr_attr_cont_idp' style='width:100px;' onChange='javascript:selectIndependent();'> \n");
					resultSb.append("	<option value='1' " + (attrCont.equals("") || attrCont.equals("1")? "selected" : "") + "><spring:message code='las.page.field.contractManager.possible'/></option> \n");
					resultSb.append("	<option value='2' " + (attrCont.equals("2")? "selected" : "") + "><spring:message code='las.page.field.contractManager.impossible'/></option> \n");
					resultSb.append("	<option value='3' " + (attrCont.equals("3")? "selected" : "") + "><spring:message code='las.page.field.contractManager.psbCndi'/></option> \n");
					resultSb.append("	<option value='4' " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "selected" : "") + "><spring:message code='las.page.field.contractManager.others'/></option> \n");
					resultSb.append("</select> \n");
					resultSb.append("<div id='div_arr_attr_cont_idp_val' class='mt4' style='display: " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "block;" : "none;") + "'> \n");
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont_idp_val' cols='30' rows='3' class='text_area_full' style='display: " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "block;" : "none;") + "' maxLength='4000'>" + attrCont + "</textarea> \n");
					resultSb.append("</div> \n");
				}else if(attrSeqno == 3){		// Warranty/유지보수조건
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else if(attrSeqno == 4){		// 개발결과물귀속/소유권
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else if(attrSeqno == 7){		// 소유권
					resultSb.append("<select name='arr_attr_cont_owner'  id='arr_attr_cont_owner' style='width:100px;' onChange='javascript:selectOwnership();' > \n");
					resultSb.append("	<option value='1' " + (attrCont.equals("") || attrCont.equals("1")? "selected" : "") + "><spring:message code='las.page.field.contractManager.theCompany'/></option> \n");
					resultSb.append("	<option value='2' " + (attrCont.equals("2")? "selected" : "") + "><spring:message code='las.page.field.contractManager.enterprise'/></option> \n");
					resultSb.append("	<option value='3' " + (attrCont.equals("3")? "selected" : "") + "><spring:message code='las.page.field.contractManager.joint'/></option> \n");
					resultSb.append("	<option value='4' " + (attrCont.equals("4")? "selected" : "") + ">N.A</option> \n");
					resultSb.append("</select> \n");
					resultSb.append("<input type='hidden' name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont_owner_val' value='1'/> \n");
				}else if(attrSeqno == 8){		// Indeminification 조건 
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else{
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' maxLength='4000'>" + attrCont + "</textarea> \n");
				}
				
				resultSb.append("</td></tr> \n");
			//}else if(attrCont.equals("")){
			}else{
				if(attrCont != "" ){
					resultSb.append("<tr> \n");
					resultSb.append("<th><span>"+thAttrNm+"</span></th> \n");
					resultSb.append("<td valign='top'> \n");
					
					if(attrSeqno == 2){				// 독자 개발 가능 여부
						resultSb.append(attrCont.equals("1")? "<spring:message code='las.page.field.contractManager.possible'/>" : "");
						resultSb.append(attrCont.equals("2")? "<spring:message code='las.page.field.contractManager.impossible'/>" : "");
						resultSb.append(attrCont.equals("3")? "<spring:message code='las.page.field.contractManager.psbCndi'/>" : "");
						resultSb.append(!(attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre>" : "");
					}else if(attrSeqno == 7){		// 소유권
						resultSb.append(attrCont.equals("1")? "<spring:message code='las.page.field.contractManager.theCompany'/>" : "");
						resultSb.append(attrCont.equals("2")? "<spring:message code='las.page.field.contractManager.enterprise'/>" : "");
						resultSb.append(attrCont.equals("3")? "<spring:message code='las.page.field.contractManager.joint'/>" : "");
						resultSb.append(attrCont.equals("3")? "N.A" : "");
					}else{		// 유지보수조건 및 기타
						resultSb.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre>");
					}
				}
				
				resultSb.append("</td></tr> \n");
			}
		}
	}
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta sci="Consideration_inner_i.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.page.field.contractManager.requUpdate"/></title>

<LINK href="<%=CSS%>/las.css"	type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css"	type="text/css" rel="stylesheet">
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="/script/secfw/common/CommonProgress.js"></script>																																					
<script language="javascript" src="/util/fsw/fsw.js"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script>
<!--

var chk = 0;
var chk_flag = false;
var toggle_flag = false;

	//금액에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
	function olnyNum(obj){
		var str = obj.value;
		str = new String(str);
		var Re = /[^0-9.]/g;
		str = str.replace(Re,'');
		
		// 금액 100,000 형태로 변환 추가 (2011/10/15)
		obj.value = Comma(str);
	}
	
	$(document).ready(function(){
		
		//  단가로 계약 내용 초기화
		if($("#cntrt_untprc_chk").val()==''){
			$('#trCntrtUntprc').hide();
		} else {
			$('#trCntrtUntprc').show();
			toggle_flag = true;
		}
		
		var frm = document.frm;
		var amt = frm.cntrt_amt.value;
		frm.cntrt_amt.value = Comma(amt);
		
		//######################################
		initCal("cntrtperiod_startday");	//계약기간
		initCal("cntrtperiod_endday");		//계약기간
		initCal("bfhdcstn_apbtday");		//처리일
		
		//지불구분
		getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C020&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.payment_gbn}'/>');				
		//2011. 10. 15 mod by 김현구. 기본값 세팅 - KRW 
		getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.crrncy_unit}'/>');	
		//거래상재방 업체 Type - cntrt_oppnt_type	
		getCodeSelectByUpCd("frm", "cntrt_oppnt_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C056&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_oppnt_type}'/>');
		//계약대상  - cntrt_trgt		
		getCodeSelectByUpCd("frm", "cntrt_trgt", '/common/clmsCom.do?method=getComboHTML&combo_sys_cd=LAS&combo_gbn=CONTRACTTYPE&combo_up_cd=<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_trgt}'/>');		
		//사전승인 승인 방법
		getCodeSelectByUpCd("frm", "bfhdcstn_apbt_mthd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C028&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.bfhdcstn_apbt_mthd}'/>');
		//연관계약 관계유형		
		getCodeSelectByUpCd("frm", "rel_type", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_select=");
		
		// 2012.04.13 준거법 초기값 설정 added by hanjihoon
		var input_loac = "<c:out value='${lomRq.loac}'/>";
		
		if(input_loac == "" || input_loac == null){
			input_loac = "C02210";	// 한국
		}
		
		initPage();
		
		//######################################		
		if("<c:out value='${lomRq.crrncy_unit}'/>" == ""){
			$("#cntrt_amt").val("");
			$("input[name=cntrt_amt]").attr('disabled', true);
			$("input[name=cntrt_untprc]").attr('checked', false);
			$("#crrncy_unit").attr('disabled', true);
			$("#crrncy_unit").html("<option value=><spring:message code='las.page.field.contractManager.notInv'/></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			$("#cntrt_untprc_expl").val("");
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
		}else{
			
			if($("#cntrt_untprc_expl").val()){
				$("[name=cntrt_untprc]").attr('checked', true);
			}
		}
		
		tit_Toggle(this, 'tr_down02'); // 김재원 추가 2011.10.15
		
		//}
		
		// TNC 데이타 이거나, 30대거래선 대상 계약 유형일 경우 활성화 
		tncOpenYn();
	});
	
	
	function tncOpenYn(){
    	
    	var frm = document.frm;
    	
    	if( frm.biz_clsfcn.value == 'T0103' &&  frm.depth_clsfcn.value == 'T0201' && ( frm.cnclsnpurps_midclsfcn.value == "T030705" ||  frm.cnclsnpurps_midclsfcn.value == "T030706") ){
    		if("<c:out value='${lomRq.sys_nm}'/>" == "TNC" && (frm.cntrt_trgt.value == "T030705006" || frm.cntrt_trgt.value == "T030705003" || frm.cntrt_trgt.value == "T030705002") ){
    			tncfunc();
    		}else{
    			tncfunc_false();
    		}
    	} else {
    		tncfunc();
    	}
    }
	
    function tncfunc(){
    
    	//alert("1");
    	
    	$("#cntrt_top30_cus").attr('disabled', true);
		//$("#cntrt_top30_cus").val("C05606");
		$("#cntrt_src_cont_drft").attr('disabled', true);
		//$("#cntrt_src_cont_drft").val("SOC10");
    }
    
    
    function tncfunc_false(){
    	
    	//alert("2");
    	
    	$("#cntrt_top30_cus").attr('disabled', false);
		//$("#cntrt_top30_cus").val("C05606");
		$("#cntrt_src_cont_drft").attr('disabled', false);
		//$("#cntrt_src_cont_drft").val("SOC10");
    }
	
	/**
	  * 단가로 체결 체크박스 클릭 시 상세 정보 표시 TOGGLE
	  */
	function toggleCheckBox(){
		if(toggle_flag==true){
			$('#trCntrtUntprc').hide();
			toggle_flag = false;
			$('#cntrt_untprc_expl').val("");
		}else{
			$('#trCntrtUntprc').show();
			toggle_flag = true;
		}
	}	
	
	function selectIndependent(){
		if($("#arr_attr_cont_idp").val() == "4"){
			$("#div_arr_attr_cont_idp_val").show();
			$("#arr_attr_cont_idp_val").val("");
			$("#arr_attr_cont_idp_val").attr("style","display:");
		}else{
			$("#div_arr_attr_cont_idp_val").hide();
			$("#arr_attr_cont_idp_val").attr("style", "display:none");	
			$("#arr_attr_cont_idp_val").val($("#arr_attr_cont_idp").val());			
		}
	}
	
	function selectOwnership(){
		$("#arr_attr_cont_owner_val").val($("#arr_attr_cont_owner").val());
	}
	
	function chgLoac(val){
		if(val == "C02211"){		//기타(자유기술)
			$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");
			$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");
			$("#loac_etc").attr("required","");
		}else{						//그외
			$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");
			$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");
			$("#loac_etc").removeAttr("required");
		}
	}
	
	function initPage(){	
			var frm = document.frm;
			
			$('input[id^=fileInfos]').val("");
			var view = "";
			var file_key = "";	
			
			// 2012.06.08 최종본이면 첨부파일 수정 불가능 modified by hanjihoon
			if("Y" == $('#plndbn_req_yn').val()){
				view="modify";
			}else{
				view="modify";
			}
			
			file_key = frm.cntrt_id.value+"@"+frm.cnsdreq_id.value;

			//계약관련 #1 계약서 - 계약서
			 frm.target		  = "fileList1";
			 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			 frm.method.value	= "forwardClmsFilePage";
			 frm.file_bigclsfcn.value = "F012";
			 frm.file_midclsfcn.value = "F01202";
			 frm.file_smlclsfcn.value = "F0120201";
			 frm.ref_key.value = file_key;
			 frm.view_gbn.value = view;
			 frm.multiYn.value = "N";
			 frm.fileInfoName.value = "fileInfos1";
			 frm.fileFrameName.value = "fileList1";
			 getClmsFilePageCheck('frm');
			
			//fileListEtc-첨부파일 첨부/별첨
			 frm.target		  = "fileListEtc";
			 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			 frm.method.value	= "forwardClmsFilePage";
			 frm.file_bigclsfcn.value = "F012";
			 frm.file_midclsfcn.value = "F01202";
			 frm.file_smlclsfcn.value = "F0120208";
			 frm.ref_key.value = file_key;
			 frm.view_gbn.value = view;
			 frm.multiYn.value = "Y";
			 frm.fileInfoName.value = "fileInfosEtc";
			 frm.fileFrameName.value = "fileListEtc";
			 getClmsFilePageCheck('frm');
			 
			//계약관련 #2 계약서- 기타
			 frm.target		  = "fileList2";
			 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			 frm.method.value	= "forwardClmsFilePage";
			 frm.file_bigclsfcn.value = "F012";
			 frm.file_midclsfcn.value = "F01202";
			 frm.file_smlclsfcn.value = "F0120205";
			 frm.ref_key.value = file_key;
			 frm.view_gbn.value = view;
			 frm.multiYn.value = "Y";
			 frm.fileInfoName.value = "fileInfos2";
			 frm.fileFrameName.value = "fileList2";
			 getClmsFilePageCheck('frm');
			 
			//계약관련 #3 단가내역 
			 frm.target		  = "fileList3";
			 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			 frm.method.value	= "forwardClmsFilePage";
			 frm.file_bigclsfcn.value = "F012";
			 frm.file_midclsfcn.value = "F01202";
			 frm.file_smlclsfcn.value = "F0120211";
			 frm.ref_key.value = frm.cntrt_id.value;
			 frm.view_gbn.value = view;
			 frm.fileInfoName.value = "fileInfos3";
			 frm.fileFrameName.value = "fileList3";
			 getClmsFilePageCheck('frm');
			 
			//계약관련 #4 사전검토정보 
			 frm.target		  = "fileList4";
			 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			 frm.method.value	= "forwardClmsFilePage";
			 frm.file_bigclsfcn.value = "F012";
			 frm.file_midclsfcn.value = "F01201";
			 frm.file_smlclsfcn.value = "F0120101";
			 frm.ref_key.value = frm.cntrt_id.value ;
			 frm.view_gbn.value = view;
			 frm.multiYn.value = "Y";
			 frm.fileInfoName.value = "fileInfos4";
			 frm.fileFrameName.value = "fileList4";
			 getClmsFilePageCheck('frm');
		 
	 		//OutLook 첨부파일 2014.03.20 추가
			frm.target		  = "fileListOut";
			frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			frm.method.value	= "forwardClmsFilePage";
			frm.file_bigclsfcn.value = "O001";
			frm.file_midclsfcn.value = "O0011";
			frm.file_smlclsfcn.value = "O00111";
			frm.ref_key.value = file_key;
			frm.view_gbn.value = "download";
			frm.fileInfoName.value = "fileListOut";
			frm.fileFrameName.value = "fileListOut";
			getClmsFilePageCheck('frm');
		 
		 	// 2014-04-17 Kevin Added.
			// GERP readonly iframe load. 
			frm.target		  = "iframeGERP";
			frm.action		  = "<c:url value='/clm/manage/consideration.do' />";
			frm.gerpPageType.value = "I";		// Input form
			frm.method.value	= "forwardGERPDetail";
			frm.submit();
	}	
	
	
	function forwardConsideration(arg){
		var frm = document.frm;	
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		frm.method.value = "listManageConsideration";			
		
		frm.submit();		
	}
	
	// 2014-04-17 Kevin. iframeGERP에 있는 gerp 정보를  hidden 필드에 바인딩 한다.
	function getGERPInfo(){
		var doc = window.frames["iframeGERP"].document;
		
		var selectedCostType = $(doc).find("#sCostType").val();
		var seelctedContractType = $(doc).find("#sContractType").val();
		var selectedDivCode = $(doc).find("#sGERPDivList").val();
		$("#gerpCostType").val((selectedCostType == null ? "" : selectedCostType));
		$("#gerpContractType").val((seelctedContractType == null ? "" : seelctedContractType));
		$("#gerpDivCode").val((selectedDivCode == null ? "" : selectedDivCode));
	}
	
	/**
	*  계약 관리 수정 
	*/
	function modifyContract(arg){
		var frm = document.frm;		
		frm.submit_status.value = arg;
		frm.target = "_self";		
		
			if(!frm.cntrt_trgt.value){
				alert("<spring:message code='las.page.field.contractManager.mustDoTarget'/>");return;//계약 대상은 필수 입력 항목 입니다.
			}
			
 			//계약기간 Chk
 			var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
 			var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
 			var cue = frm.cntrt_untprc_expl.value.replace(/^\s+/,""); // lpad 하는 것입니다.
 			
 			if(eval(str_from) > eval(str_to)){
 				alert("<spring:message code='las.page.field.contractManager.wrongPeriod'/>");
 				return;
 			}

			// 단가로체결일경우 단가내역요약 필수
			if($("#cntrt_untprc").attr("checked")){
				if(frm.cntrt_untprc_expl.value==""){
					alert("<spring:message code='las.page.field.contractManager.mustDoUcost'/>") ;
					return ;
				}
			}
 			
 			if($("[name=cntrt_untprc]").is(":checked")){
 				if(!$("#cntrt_untprc_expl").val()){			
				//cntrt_untprc_expl
				alert("<spring:message code='las.page.field.contractManager.inputCostInfo'/>");//계약 단가가 체크됨 단가내역 요약을 입력 하여 주시기 바랍니다. 
				return;
 				} 				
 				if(cue.length == 0){
 					alert("<spring:message code='las.page.field.contractManager.noSpace'/>");
 					return;
 				}
 			} 

 			<% 
 			//Sungwoo added condtion 2014-09-18 ECMS, Old SELMS의 경우 pre-approval 필수 제외처리
 			if(lomRq.get("depth_status").equals("C02606") || lomRq.get("depth_status").equals("C02609")){
 			%>
			//사전승인발의자
	 			if(!frm.bfhdcstn_mtnman_id.value){
	 				alert("<spring:message code='las.page.field.contractManager.mustDoPreappMover'/>");
	 				return;
	 			}
				//사전승인승인자
	 			if(!frm.bfhdcstn_apbtman_id.value){
	 				alert("<spring:message code='las.page.field.contractManager.mustDoPreappBy'/>");
	 				return;
	 			}
				//사전승인승인일
	 			if(!frm.bfhdcstn_apbtday.value){
	 				alert("<spring:message code='las.page.field.contractManager.mustDoPreappDay'/>");
	 				return;
	 			}
				//사전승인승인방식
	 			if(!frm.bfhdcstn_apbt_mthd.value){
	 				alert("<spring:message code='las.page.field.contractManager.mustDoPreappType'/>");
	 				return;
	 			}	
			<%
 			}
			%>
		
		viewHiddenProgress(true);
		
		// 2014-04-17 Kevin added.
		getGERPInfo();
		
		var options = {
			url: "<c:url value='/clm/review/consideration.do?method=modifyContractMaster' />",
			type: "post",
			async: false,
			dataType: "json",			
			success: function(responseText, statusText) {  //undefined
			//계약서 필수 에서 빼기 최종 의뢰 ,,,,,,재의뢰시 에 .....최종본
			viewHiddenProgress(false);
			
				if(responseText.returnVal == undefined){					
					alert("<spring:message code='las.page.field.contractManager.askAdminError'/>");					
				}else{				
					alert(responseText.returnMsg);//필수 입력 항목 안내문		
					self.close();
					opener.detailConsideration();
				}				
			}
		}
			//계약관련#0
		 fileList1.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				viewHiddenProgress(true);
				return; 					
			}
			if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() && arg != "save"){
				alert("<spring:message code="clm.page.msg.manage.contract" /> <spring:message code='secfw.msg.error.fileNon' />");
				viewHiddenProgress(false);
				  return;
			}
			//계약서 첨부/별첨
			fileListEtc.UploadFile(function(uploadCount){
				if(uploadCount == -1){
					initPage();
					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					viewHiddenProgress(false);
					return; 					
				}
				 //계약관련#1
				 fileList2.UploadFile(function(uploadCount){
					if(uploadCount == -1){
						initPage();
						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						viewHiddenProgress(true);
						return; 					
					}
					
				 	fileList3.UploadFile(function(uploadCount){
						if(uploadCount == -1){
							//initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							viewHiddenProgress(false);							
							return; 					
						}
						
						//계약관련#3
	 				 fileList4.UploadFile(function(uploadCount){
	 					if(uploadCount == -1){
	 						//initPage();
	 						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 						viewHiddenProgress(false);
	 						return; 					
	 					}	
	 					
 						$("#frm").ajaxSubmit(options);
				 		});//end fileList4
				 	});//end fileList3 				
			 	});//end fileList2
			});//end fileListEtc		
		 });//end fileList1 	
	}
			
			
	//계약기본 정보 수정 
	function forwardContractMaster(){
		 var frm = document.frm;		
		 frm.target = "_self";
		 frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
		 frm.method.value = "forwardContractMaster";
		 frm.submit();	
	}
	
	//글자수 제한
	function f_chk_byte(aro_name,ari_max) {	
		var ls_str	 = aro_name.value;
		var li_str_len = ls_str.length;
		var li_max	  = ari_max;
		var i			= 0;
		var li_byte	 = 0;
		var li_len	  = 0;
		var ls_one_char = "";
		var ls_str2	 = "";
	
		for(i=0; i< li_str_len; i++) {
			ls_one_char = ls_str.charAt(i);
			if (escape(ls_one_char).length > 4) 
				li_byte += 2;
			else 
				li_byte++;
			
			if (li_byte <= li_max) li_len = i + 1;
		}
		
		if(li_byte > li_max) {
			alert("<spring:message code='las.page.field.contractManager.overByte'/>");
			ls_str2 = ls_str.substr(0, li_len);
			aro_name.value = ls_str2;
		}
		aro_name.focus();	
	}

	/**
	* 결재자 정보 / 승인자 정보 가져올대 그룹장 이하이면 그룹장 이상을 지정 하시기 바랍니다. 
	* 직급 이하 유무 확인 
	*/
	function listTbComCd(arg){
		var frm = document.frm;
		var options = {
			url: "/common/clmsCom.do?method=listTbComCd&grp_cd=C061&cd="+arg,
			type: "post",
			dataType: "json",
			success: function(responseText,returnMessage) {
				if(responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
						//entry['DB컬럼명'] : TB_COM_CD 에서 조회된 "DB컬럼" 값
						if(entry['useman_mng_itm1'] =="" || entry['useman_mng_itm1'] == "N"){						
						alert("<spring:message code='las.page.field.contractManager.assignScheif'/>");						
						}
					});
				}
			}
		}
		$("#frm").ajaxSubmit(options);
	}

	function paymentGbnChange(){		
		//payment_gbn 지수불 구분이 해당사항 없음이면 계약 금액 은 입력 불가 처리
		//계약금액,통화단위 ,계약단가 비활성화
		if($('#payment_gbn > option:selected').val() == "C02004" ){
			//cntrt_amt 계약금액			
			$("#cntrt_amt").val("");
			$("input[name=cntrt_amt]").attr('disabled', true);
			$("input[name=cntrt_untprc]").attr('checked', false);
			$("#crrncy_unit").attr('disabled', true);
			$("#crrncy_unit").html("<option value=><spring:message code='las.page.field.contractManager.notInv'/></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			$("#cntrt_untprc_expl").val("");
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
		}else{			
			$("input[name=cntrt_amt]").attr('disabled', false);
			getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=KRW');
			$("input[name=cntrt_untprc]").attr('disabled', false);
			$("#crrncy_unit").attr('disabled', false);
		}
	}
	
	function paymentGbnChange2(){
		$("input[name=cntrt_untprc]").attr('checked', false);
	}
	
	//단가내역 요약 첨부파일 초기화
	function initCntrtUntprcExpl(){
		frm.target		  = "fileList3";
		 frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
		 frm.method.value	= "forwardClmsFilePage";
		 frm.file_bigclsfcn.value = "F012";
		 frm.file_midclsfcn.value = "F01202";
		 frm.file_smlclsfcn.value = "F0120211";
		 frm.ref_key.value = "";
		 frm.view_gbn.value = "modify";
		 frm.fileInfoName.value = "fileInfos3";
		 frm.fileFrameName.value = "fileList3";
		 frm.submit();
	}
	
	 /**
	  *계약 건 데이타  저장	- master tb insert or update submit
	  */	  
	  function insertContractMaster(){

		var frm = document.frm;
		
			var options = {
				url: "<c:url value='/clm/manage/consideration.do?method=insertContractMaster' />",
				type: "post",
				async: false,
				dataType: "json",			
				success: function(responseText, statusText) {  //undefined
					if(responseText.returnCd == undefined){
						alert("<spring:message code='las.page.field.contractManager.askAdminError'/>");
					}else{						
						if("update" != responseText.exeStatus){
							var html = "<input type=\"hidden\" value=\""+responseText.returnCntrtId+"\" name=\"arr_cntrt_id\" id=\"arr_cntrt_id\" />";
							$("#cnsdreq_id").before(html);
							
							if(!$('#cnsdreq_id').val()){
								$('#cnsdreq_id').val(responseText.returnCnsdReqId);
							}
						}
						addTabCnt();
						var tabCnt = getTabCnt();
						
						var tabTitle = document.getElementById("tab_title"); 
						var tabTitleLi = document.createElement("li"); 
						tabTitle.appendChild(tabTitleLi);	 
						tabTitleLi.id="tab_li"+tabCnt+"";
						tabTitleLi.innerHTML = "<a href=\"javascript:titleTabClick('"+tabCnt+"','');\"><spring:message code='las.page.field.contractManager.contract'/>"+tabCnt+"</a>";
						
						for(i=1; i<=tabCnt; i++){			
							if(tabCnt==i){
								document.getElementById("tab_li"+i+"").className = "on";
							}else{
								document.getElementById("tab_li"+i+"").className = "";
							}		  
						}
						forwardInsertContractMaster();

						tit_Toggle(this, 'tr_down02');				
						
						if("T0103" == frm.biz_clsfcn.value){//본계약인 경우만 특화속성 생성
						listSpecialAttr(frm.cnclsnpurps_bigclsfcn.value,frm.cnclsnpurps_midclsfcn.value);
						}
						
					}
				}
			}
			//계약관련#1
			 fileList1.UploadFile(function(uploadCount){
				if(uploadCount == -1){
					initPage();
					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					return; 					
				}
				if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() ){
	 					alert("<spring:message code='las.page.field.contractManager.contForm'/> <spring:message code='secfw.msg.error.fileNon' />");
						return;
	 			}
				//계약서 첨부/별첨
 				fileListEtc.UploadFile(function(uploadCount){
 					if(uploadCount == -1){
 						initPage();
 						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
 						return; 					
 					}
					 //계약관련#2
					 fileList2.UploadFile(function(uploadCount){
						if(uploadCount == -1){
							initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return; 					
						}
						//계약관련#3
					 	fileList3.UploadFile(function(uploadCount){
							if(uploadCount == -1){
								//initPage();
								alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
								return; 					
							}							
							//계약관련#4
		 				 fileList4.UploadFile(function(uploadCount){
		 					if(uploadCount == -1){
		 						//initPage();
		 						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
		 						return; 					
		 					}
		 					viewHiddenProgress(true);
		 					$("#frm").ajaxSubmit(options);
		 										 
					 		});//end fileList4
					 	});//end fileList3					
				 	});//end fileList2
 				});//end fileListEtc 		
 			 });//end fileList1
	  }
	 /**
	 * 파일얼로드 저장
	 */
	 function saveFileUpload(){
		 var frm = document.frm;
		 
		 frm.target = "_self";
		 frm.action = "<c:url value='/clm/manage/consideration.do' />";
		 frm.method.value = "saveFileUpload";
		 	//#1
			fileList1.UploadFile(function(uploadCount){
				if(uploadCount == -1){
					initPage();
					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					return; 					
				}				
			 });					 
		 }
	 
	  /**
	  * 연관계약정보 RoW ADD
	  */
	  function addRelCntrt(){
		  var html ="<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">  "							
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.name' /></span></td>  "	
			  +	"<td colspan=\"5\">  "	
			  +	"<input type=\"hidden\" name=\"arr_parent_cntrt_id\" id=\"parent_cntrt_id\" value=\"12345678\" /><spring:message code='las.page.field.contractManager.annotation.ContId'/>"	
			  +	"<input type=\"hidden\" name=\"arr_parent_cntrt_nm\" id=\"parent_cntrt_nm\" value=\"\" style=\"width:400px\" class=\"text_search\"/><img src=\"<%=IMAGE%>/icon/ico_search.gif\' onclick=\"javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);\" class=\"cp\" alt=\"search\" />  "	
			  +	"</td>  "	
			  +	"</tr>  "	
			  +	"<tr>  "							
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.kind' /></span></td>  "	
			  +	"<td colspan=\"5\">  "	
			  +	"<select name=\"arr_rel_type\" id=\"rel_type"+($('#tab_contents_sub_conts1 tr').length+1)+"\" class=\"all\" style=\"width:100px\">  "							
			  +	"</select>  "	
			  +	"<select name=\"arr_rel_type_etc\" id=\"rel_type_etc"+($('#tab_contents_sub_conts1 tr').length+1)+"\" class=\"all\" style=\"width:100px\">  "							
			  +	"</select>  "	
			  +	"<input type=\"text\" name=\"arr_expl\" id=\"expl\" value=\"<spring:message code='las.page.field.contractManager.lnkCntEpl'/>  >>>>>>> <<<<<<<<  \" style=\"width:600px\" class=\"text_search\"/>  "	
			  +	"<a href=\"javascript:minusRelCntrt();\"><img src=\"<%=IMAGE%>/icon/ico_minus.gif\" /></a>  "	
			  +	"</td>  "	
			  +	"</tr>  "	;
		  //trRelationContract
		  $("#trRelationContract").after(html);	
		  
		  getCodeSelectByUpCd("frm", "rel_type"+($('#tab_contents_sub_conts1 tr').length-1), '/common/clmsCom.do?combo_sys_cd=LAS&method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_selected=');
		  
	  }	
	  
	  /**
	  * 연관계약 타입 선택
	  */
	  function reltypeChange(){
		  var type = $("#rel_type").val();  
		  if("C03201" == type){
				$("#rel_defn").html("<option value=NDA>NDA</option><option value=MOU/LOI>MOU/LOI</option>");
			}else if("C03202" == type){
				$("#rel_defn").html("<option value=Master>Master</option><option value=Sub>Sub</option>");
			}else if("C03203" == type){
				$("#rel_defn").html("<option value=<spring:message code='las.page.field.contractManager.befChng'/>><spring:message code='las.page.field.contractManager.befChng'/></option><option value=<spring:message code='las.page.field.contractManager.bfClose'/>><spring:message code='las.page.field.contractManager.bfClose'/></option>");
			}else{
				$("#rel_defn").html("<option value=><spring:message code='las.page.field.contractManager.noSelect'/></option>");
			}
	  }
	  
	  /**
	  * 연관계약 정보 actionRelContract
	  */
	  function actionRelationContract(arg,id){
		  
		  var frm = document.frm;
		  frm.submit_status.value=arg;
		  
		  if("delete" == arg){
			  frm.parent_cntrt_id.value = id;
		  }else{
			  if(!$("#parent_cntrt_id").val()){
				  alert("<spring:message code='las.page.field.contractManager.selectLnkConfInf'/>");
				  $("#rel_type").focus();
				  return;
			  }else if(!$("#rel_type").val()){
				  alert("<spring:message code='las.page.field.contractManager.selectRel'/>");
				  $("#rel_type").focus();
				  return;
			  }
		  }
		  
		  var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=actionRelationContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				
				if(responseText.returnVal == 1){
					alert("<spring:message code='las.page.field.contractManager.procEnd'/>");
					
					//입력폼 초기화 
					$("#rel_type").val("");
					$("#parent_cntrt_id").val("");
					$("#parent_cntrt_name").val("");
					$("#rel_defn").html("<option><spring:message code='las.page.field.contractManager.select2'/></option>");
					$("#expl").val("");
					listRelationContract();
				}else if(responseText.returnVal == 2){
					alert("<spring:message code='las.page.field.contractManager.isOverlap'/>");
				}else{
					alert("<spring:message code='las.page.field.contractManager.askAdminError'/>");
				}
			}
		}
		$("#frm").ajaxSubmit(options);
	  }	 
	  
	  /**
	  * 연관계약 정보 reload
	  */
	  function listRelationContract(){
		  var frm = document.frm;
		  frm.submit_status.value="edit";
		  var options = {
				url: "<c:url value='/clm/manage/consideration.do?method=listRelationContract' />",
				type: "post",
				async: false,
				dataType: "html",
				success: function (data) { 
					if(data.indexOf('<html>') == -1){  
						$("#trRelationContract").nextAll().remove();
						$("#trRelationContract").after(data);
					}
				}
			};
			$("#frm").ajaxSubmit(options);
	  }
	  
	/**
	* 관리페이지 이동
	*/
	function forwardModifyConsideration(){
		var frm = document.frm;
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "/clm/manage/consideration.do";
		frm.status.value = "forwardModify";
		frm.method.value = "forwardModifyConsideration";		
		frm.submit();
	}
	
		/**
		*계약상세 탭 클릭 
		*/
		function subTitleTabMove(num){		 	
		 if(num==1){							
			 document.getElementById("tab_contents_sub_conts1").style.display = "block"; 
			 document.getElementById("tab_contents_sub_conts2").style.display = "none";
			 document.getElementById("tab_contents_sub_css1").className = "on";
			 document.getElementById("tab_contents_sub_css2").className = "";			  
			 document.getElementById("table_mt_rc").style.display = "none";
			 document.getElementById("li_mt_rc").className = "";
			}else if(num==2){	
			  document.getElementById("tab_contents_sub_conts1").style.display = "none";
			 document.getElementById("tab_contents_sub_conts2").style.display = "block";
			 document.getElementById("tab_contents_sub_css1").className = "";
			 document.getElementById("tab_contents_sub_css2").className = "on";
			 document.getElementById("table_mt_rc").style.display = "none";
			 document.getElementById("li_mt_rc").className = "";
			}else if(num==4){
			document.getElementById("tab_contents_sub_conts1").style.display = "none";
				 document.getElementById("tab_contents_sub_conts2").style.display = "none";
				 document.getElementById("tab_contents_sub_css1").className = "";
				 document.getElementById("tab_contents_sub_css2").className = "";
			 		
			$("#table_mt_rc").show();
			$("#li_mt_rc").addClass("on");				
			}else{
			  document.getElementById("tab_contents_sub_conts1").style.display = "none";
			 document.getElementById("tab_contents_sub_conts2").style.display = "none";
			 document.getElementById("tab_contents_sub_css1").className = "";
			 document.getElementById("tab_contents_sub_css2").className = "";
			 document.getElementById("table_mt_rc").style.display = "none";
			 document.getElementById("li_mt_rc").className = "";
			}	
		}
		
		/**
		* 연관계약 계약 목록 팝업
		*/
		function popupListContract(arg){
			var frm = document.frm;
			arg=$("#rel_type").val();
			var parent_cntrt_id = "";
			var parent_cntrt_nm = "";
			var rel_type1 = frm.rel_type.value;
			
			//C03204
			var result = new retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1);
			
			if(frm.cntrt_nm.value != ""){
			 
				var v_except_cntrt_id = frm.cntrt_id.value;
				PopUpWindowOpen2("/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel&except_cntrt_id="+v_except_cntrt_id , 1000, 600, false, "PopUpRelInfo");
			 
			} else {
			 alert("<spring:message code='las.page.field.contractManager.contFistInput'/>");
			}
		}
		
		/**
		* 연관계약 팝업 리턴 처리
		*/	  
		function setContract(id,name,biz,sArg){
			var fm = document.frm;			
			
			//리턴값 변수에 저장
			var cntrt_id = id;
			var cntrt_name = name;
			var cntrt_biz_clsfcn = biz;
			var srch_arg = sArg;
			
			$("#parent_cntrt_id").val(cntrt_id);
			$("#rel_type").val(srch_arg);
			$("#parent_cntrt_name").val(cntrt_name);
		}
		
	  /**
	  * Obj init
	  */
	  function retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1){
		  this.parent_cntrt_id = parent_cntrt_id;
		  this.parent_cntrt_nm = parent_cntrt_nm;
		  this.rel_type1 = rel_type1;
	  }
	  
	  /**
	  *계약유형선택에서 사용할 오브젝트 초기화
	  */
	  function retObj(biz_clsfcn, biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm,cnclsnpurps_midclsfcn,cnclsnpurps_midclsfcn_nm,mod_status){ 
		 this.biz_clsfcn_nm	 = biz_clsfcn_nm;
		 this.depth_clsfcn_nm	= depth_clsfcn_nm
		 this.cnclsnpurps_bigclsfcn_nm 	= cnclsnpurps_bigclsfcn_nm;
		 this.cnclsnpurps_midclsfcn_nm 	= cnclsnpurps_midclsfcn_nm;
		  this.biz_clsfcn		 = $("#biz_clsfcn").val() ;		  
		  this.depth_clsfcn		= $("#depth_clsfcn").val();	 	 	
		  this.cnclsnpurps_bigclsfcn 	= $("#cnclsnpurps_bigclsfcn").val();	 	  
	 	  this.cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val();
	 	  this.mod_status = "popup";
	  }		  
	  /**
	  *계약유형선택팝업 오픈
	  */
	  function openChooseContractType() {	  
		  
		  var frm = document.frm;	
		
	 	  var biz_clsfcn		 = $("#biz_clsfcn").val();
		  var biz_clsfcn_nm	  = "";
	 	  var depth_clsfcn		= $("#depth_clsfcn").val();
	 	  var depth_clsfcn_nm	= ""; 	
	 	
	 	  var cnclsnpurps_bigclsfcn 	= $("#cnclsnpurps_bigclsfcn").val();
	 	  var cnclsnpurps_bigclsfcn_nm	= "";
	 	  var cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val();
	 	  var cnclsnpurps_midclsfcn_nm 		= "";
	 	  var mod_status = "";
	
	 	  var result = new retObj(biz_clsfcn, biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm, cnclsnpurps_midclsfcn, cnclsnpurps_midclsfcn_nm,mod_status);
	 	  PopUpWindowOpen("/clm/manage/chooseContractType.do?method=forwardChooseContractTypePopup", 1000, 630, false);
		  
		  if(result.biz_clsfcn != "" && result.depth_clsfcn != "" &&  result.cnclsnpurps_bigclsfcn != "" &&result.cnclsnpurps_midclsfcn != "") {		  
		  
			  frm.biz_clsfcn.value 				= result.biz_clsfcn;					// 비즈니스 분류
			  frm.biz_clsfcn_nm.value 			= result.biz_clsfcn_nm;					// 비즈니스 분류	 
			  frm.depth_clsfcn.value 			= result.depth_clsfcn;					  //	단계분류
			  frm.depth_clsfcn_nm.value 		= result.depth_clsfcn_nm;					  //	단계분류
			  frm.cnclsnpurps_bigclsfcn.value 	= result.cnclsnpurps_bigclsfcn;	//	체결목적대분류
			  frm.cnclsnpurps_bigclsfcn_nm.value= result.cnclsnpurps_bigclsfcn_nm;	//	체결목적대분류	
			  frm.cnclsnpurps_midclsfcn.value 	= result.cnclsnpurps_midclsfcn;	//	체결목적중분류 */	  
			  frm.cnclsnpurps_midclsfcn_nm.value= result.cnclsnpurps_midclsfcn_nm;	//	체결목적중분류 */			 
			  frm.en_biz_clsfcn_nm.value 			= result.en_biz_clsfcn_nm;					// 비즈니스 분류
			  frm.en_depth_clsfcn_nm.value 			= result.en_depth_clsfcn_nm;					  //	단계분류
			  frm.en_cnclsnpurps_bigclsfcn_nm.value = result.en_cnclsnpurps_bigclsfcn_nm;	//	체결목적대분류  
			  frm.en_cnclsnpurps_midclsfcn_nm.value = result.en_cnclsnpurps_midclsfcn_nm;	//	체결목적중분류 */
			  
			  changeSubCd("cntrt_trgt", "CONTRACTTYPE", result.cnclsnpurps_midclsfcn);		  
			  
			  if("T0103" == result.biz_clsfcn){//본계약인 경우만 특화속성 생성
			  listSpecialAttr(result.cnclsnpurps_bigclsfcn,result.cnclsnpurps_midclsfcn);
			  }
			  //계약명 return
			  returnCntrtNm();
			  
		  }	
		}
	  /*
	  * 특화 속성 정보 가져오기 
	  */
	  function listSpecialAttr(bigClsFcn,midClsFcn){
		  var options = {
				url: "<c:url value='/clm/manage/consideration.do?method=listSpecialAttr&bigclsfcn="+bigClsFcn+"&midclsfcn="+midClsFcn+"' />",
				type: "post",
				async: false,
				dataType: "html",
				success: function (data) {
					$("#trSpecialAttr").next().remove();
					$("#trSpecialAttr").after(data);					
				}
			};
		  $("#frm").ajaxSubmit(options);	 
	  }
	 	 
	 /*
	 *계약대상
	 */
	 function changeSubCd(selObjId, gbn, upCd) {
		 if(upCd == "") {
			 upCd = "XXX";
		 } 
		 getCodeSelectByUpCd("frm", selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
	 }
		 
	 /**
	 *모달팝업
	 */
	 function PopUpWindowModalOpenCnsd(surl, popupwidth, popupheight, bScroll, obj){
		  
		 if( popupwidth  > window.screen.width )
			 popupwidth = window.screen.width;
		 if( popupheight > window.screen.height )
			 popupheight = window.screen.height; 
	
		 if( isNaN(parseInt(popupwidth)) ){
			 Top  = (window.screen.availHeight - 600) / 2;
			 Left = (window.screen.availWidth  - 800) / 2;
		 } else {
			 Top  = (window.screen.availHeight - popupheight)  / 2;
			 Left = (window.screen.availWidth  - popupwidth) / 2;
		 }
	
		 if (Top < 0) Top = 0;
		 if (Left < 0) Left = 0;
	
	 	var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
		 window.showModalDialog(surl, obj, sFeatures);
	  }
	 
	 	 
	 /**
	 * 사전승인발의자  사전승인 승인자 정보	 /clm/manage/chooseClient.do	forwardChooseClientPopup
	 */
	 function searchEmployee(srcFrm, gb){
		 var frm = document.frm;
		 var srchValue = comTrim(srcFrm.value); //입력받은 값 
		 
		 if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
	 	 alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	 	 srcFrm.focus();
			 return;
		 }
		 
		 //사전승인발의자인지 사전승인승인자정보에서 호출했는지 구분해주기 위해.
		 frm.searchEmployeeGb.value = gb;
		 
		 PopUpWindowOpen('', 800, 450, false);
		 
		 frm.target = "PopUpWindow";
		 frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
		 frm.srch_user_cntnt.value = srchValue;
		 frm.action = "<c:url value='/secfw/esbOrg.do' />";
		 frm.method.value = "listEsbEmployee";
		 frm.submit();
		 
	 }
	 
	 /**
	 * 임직원 조회정보 결과 setting
	 */
	 function setUserInfos(obj) {
		 var userInfo = obj; 
		 var frm = document.frm;
		 
		 //사전승인발의자
		 if(frm.searchEmployeeGb.value == "MTN"){
			 frm.bfhdcstn_mtnman_id.value 			= userInfo.epid;		//사전승인발의자 id
			 frm.bfhdcstn_mtnman_nm.value 			= userInfo.cn;; 		//발의자 이름
			 frm.bfhdcstn_mtnman_jikgup_nm.value 	= userInfo.title;		//직급
			 frm.bfhdcstn_mtn_dept_nm.value			= userInfo.department;	//	//이메일 SPAN
			 frm.bfhdcstn_mtnman_email.value		= userInfo.mail;		//이메일
			 
			 $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);
			 $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);
			 $('#bfhdcstn_mtn_email_span').html(userInfo.mail);
			 $('#bfhdcstn_mtnman_email').val(userInfo.mail);

			 
		 } else if(frm.searchEmployeeGb.value == "APPR") {		//검토의뢰시 결재자 ID
		//eptitlenumber 직급코드 
		 
		 listTbComCd(userInfo.eptitlenumber);
		 
		 frm.approvalman_id.value = userInfo.epid;
			 frm.approvalman_nm.value = userInfo.cn;
			 frm.approvalman_search_nm.value = userInfo.cn;
			 frm.approvalman_dept_nm.value = userInfo.department;
			 $('#approvalman_dept_nm').html(userInfo.department);
			 frm.approvalman_jikgup_nm.value=userInfo.title;
			 $('#approvalman_jikgup_nm').html(userInfo.title);			 
			 
			 
		 } else{//사전승인승인자정		 
		 frm.bfhdcstn_apbtman_id.value		  = userInfo.epid;		//사전승인승인자정id
		 frm.bfhdcstn_apbtman_nm.value		  = userInfo.cn;; 		//발의자 이름
		 frm.bfhdcstn_apbtman_jikgup_nm.value	= userInfo.title;		//직급
		 frm.bfhdcstn_apbt_dept_nm.value		= userInfo.department;	//부서
		 
		 $('#bfhdcstn_apbtman_jikgup_nm_span').html(userInfo.title);
			 $('#bfhdcstn_apbt_dept_nm_span').html(userInfo.department);
		 }
 	 }

	
	 /************************************************************************
	 * 의뢰인관리 팝업
	 */
	 function openChooseClient(){
		var frm = document.frm;
		//alert($("input[name=arr_trgtman_id]").length);
		
		var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
		var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
		var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
		var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
		var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
		
		var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
		frm.chose_client.value = items;
				
		PopUpWindowOpen('', "530", "470", false);
		frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
		frm.method.value="forwardChooseClientPopup";
		frm.target = "PopUpWindow";
		frm.submit();
	 }
	 /**
	 *
	 */
	 function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll) {
		 if( popupwidth  > window.screen.width )
				popupwidth = window.screen.width;
		 if( popupheight > window.screen.height )	
				popupheight = window.screen.height;
		 if( isNaN(parseInt(popupwidth)) ){
			 Top  = (window.screen.availHeight - 600) / 2;
			 Left = (window.screen.availWidth  - 800) / 2;
		 } else {
			 Top  = (window.screen.availHeight - popupheight)  / 2;
			 Left = (window.screen.availWidth  - popupwidth) / 2;
		 }
		 if (Top < 0) Top = 0;
		 if (Left < 0) Left = 0;
		 popupwidth = parseInt(popupwidth) + 10 ;
		 popupheight = parseInt(popupheight) + 29 ;	 
		 
		 
		 //팝업창  주소표시줄 검색 제공 자 없애기 위해서 수정 
		 var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars="+(bScroll ? "yes" : "no")+", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		 PopUpWindow = window.open(surl, "PopUpWindow" , Feture);
		 PopUpWindow.focus();
	 }
	 /**
	 *의뢰자 리턴 객체 받기 심주완수정-2011.10.15
	 */
	 function setListClientInfo(returnValue) {
		var arrReturn = returnValue.split("!@#$");
		var innerHtml ="";	
		
		$('#id_trgtman_nm').html("");
		
		for(var i=0; i < arrReturn.length;i++) {
		var arrInfo = arrReturn[i].split("|");
		if((i != 0 && i != 1) && (i % 2 == 0)){
			innerHtml += "<br/>";
		}
		if(i != 0 && (i % 2 != 0)){
			innerHtml += ",";
		}
			innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+ arrInfo[0] +"' />";
			innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+ arrInfo[1] +"' />";
			innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+ arrInfo[2] +"' />";				
			innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+ arrInfo[3] +"' />";
			innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+ arrInfo[4] +"' />";
			
			innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
			
		$('#id_trgtman_nm').html(innerHtml);
		
		}
		// 관련자 리스트 수정 여부 저장
		frm.client_modify_div.value = "Y";
	 }	
	
	/************************************************************************
	*
	*/
	function returnCntrtNm(){
		var tmpNmAbbr ="";
		//alert($('#orgnz_nm_abbr').val()  + ">>" + $('#req_dept_nm').val());
		if(!$('#orgnz_nm_abbr').val()){			
			tmpNmAbbr = $('#req_dept_nm').val();
		}else{
			tmpNmAbbr = $('#orgnz_nm_abbr').val();
		}
		var tmpCntrtOppntNm ="";
		
		if("H" == $('#dmstfrgn_gbn').val()){
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '16'); //한국일경우 8자리까지 김재원 값 16으로 변경
		}else{
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '12'); ///해외일경우 12자리까지
		}
		var tmpNm = $('#cntrt_nm').val();
		var arr = tmpNm.split("_");
		//의ㅗ리자 이외 사람이 변경시 소속부서는 의죄자걸로 나머지는 변경된 정보로 계약명 설정 
		var cntrtNm = arr[0] +"_"+ tmpCntrtOppntNm + "_" + $('#biz_clsfcn_nm').val() + "_" + $('#depth_clsfcn_nm').val() + "_" + $('#cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#cnclsnpurps_midclsfcn_nm').val();
		var en_cntrtNm = arr[0] + "_" + tmpCntrtOppntNm + "_" + $('#en_biz_clsfcn_nm').val() + "_" + $('#en_depth_clsfcn_nm').val() + "_" + $('#en_cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#en_cnclsnpurps_midclsfcn_nm').val();
		
		$('#cntrt_nm').val(cntrtNm);
		$('#en_cntrt_nm').val(en_cntrtNm);
		$('#div_cntrt_nm').html("");
		$('#div_cntrt_nm').html(cntrtNm);
	}
	
	//다이렉트결재콜...수정하지마세요
	function openApproval() {
		var frm = document.frm;
		frm.submit_status.value = "req";
		var options = {
				url:"<c:url value='/clm/manage/consideration.do?method=insertConsideration' />",
				type:"post",
				dataType:"json",
				success: function(returnJson,returnMessage){						
					if(returnJson.retApproval=="Y") {
						viewDlgAlert("<spring:message code='las.page.field.contractManager.succApproval'/>");
						setApprovalYN("Y");
					} else {
						//결재 실패시 
						if(returnJson.ret){
							alert("<spring:message code='las.page.field.contractManager.errorInApproval'/>");
							setApprovalYN("N");	
						}
					}						
				}			
			};
			$('#frm').ajaxSubmit(options);
	}
	
	//결재상신리턴결과
	function setApprovalYN(yn){
		var frm = document.frm;
		if(yn=="Y") {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
		frm.target="_self";
		frm.method.value="modifyConsiderationStatus";
		frm.submit();
		} else {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
		frm.target="_self";
		frm.method.value="listManageConsideration";
		frm.submit();
		}
	}	
		 //미리보기팝업
		 function considerationPreview() {
			 alert("<spring:message code='las.page.field.contractManager.tmpSaveApply'/>");
			 var frm = document.frm;
				
			 PopUpWindowOpen('', "1024", "768", false);
				
			 frm.action 		= "<c:url value='/clm/manage/consideration.do' />";
			 frm.method.value	= "forwardPreviewPop";
			 frm.target 		= "PopUpWindow";
			 frm.submit(); 
		 }
	//#############################################
		 /************************************************************************
			 * 거래상대방 팝업 띄우기
			 */ 
			 function openNegoCustomer(){
				 var frm = document.frm;
				 var customer = frm.cntrt_oppnt_nm.value;
				 
				PopUpWindowOpen('', 900, 600, false);
		 		frm.target = "PopUpWindow";
		 		frm.action = "/clm/draft/customerTest.do";
		 		frm.method.value = "listCustomerTest"; 		
		 		frm.customer_gb.value	= "O";
		 		frm.submit();
			 }
			/**
			* 거래상대방 객체 리턴
			*/
			function returnCustomer(obj){
				chk++;	
				
				var dmstfrgn_gbn ="F";
				var cntrt_oppnt_region_gbn = "C01802";
				$('#cntrt_oppnt_cd').val(obj.customer_cd);
				$('#cntrt_oppnt_nm').val(obj.customer_nm1); //한국어 사용자가 등록할 경우
				$('#dmstfrgn_gbn').val(dmstfrgn_gbn);	//국내 해외 구분 
				$("#region_gbn").val(cntrt_oppnt_region_gbn);
				$('#cntrt_oppnt_rprsntman').val(obj.tax_no);		
				
				var cntrtOppntKind = "";
				var cntrtOppntCeo  = "";		
				
				document.getElementById("cntrtOppntChk").value = obj.index;	
				
				// 2014-04-17 Kevin added. To put gerp information on the GERP Information section.		
				window.frames["iframeGERP"].fnSetCounterpartyNameNCode(obj.customer_nm1, obj.gerp_cd, obj.contract_required, obj.vendorType);
				
				//계약명 return
				returnCntrtNm();
			}
			
			function chgLoac(val){
				if(val == "C02211"){		//기타(자유기술)
					$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");
					$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");
					$("#loac_etc").attr("required","");
				}else{						//그외
					$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");
					$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");
					$("#loac_etc").removeAttr("required");
				}
			}
			//#############################################################
			
			
			// TOP 30 팝업
	function openTop30Customer(){
		   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
	}
	
	function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
		 	if( popupwidth  > window.screen.width )
		 		popupwidth = window.screen.width;
		 	if( popupheight > window.screen.height )
		 		popupheight = window.screen.height; 
		 		
		 	if( isNaN(parseInt(popupwidth)) ){
		 		Top  = (window.screen.availHeight - 600) / 2;
		 		Left = (window.screen.availWidth  - 800) / 2;
		 	} else {
		 		Top  = (window.screen.availHeight - popupheight)  / 2;
		 		Left = (window.screen.availWidth  - popupwidth) / 2;
		 	}

		 	if (Top < 0) Top = 0;
		 	if (Left < 0) Left = 0;
		 	
		 	popupwidth = parseInt(popupwidth) + 10 ;
		 	popupheight = parseInt(popupheight) + 29 ;		 	
		 	
		 	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		 	PopUpWindow = window.open(surl, popName , Feture);

		 	PopUpWindow.focus();
		 	
		 }
			
</script>
</head>
<body class="pop">
	<!-- header -->
	<h1><spring:message code="las.page.field.contractManager.requUpdate"/></h1><!-- 의뢰내용수정 -->
	<!-- //header -->
	
	<div class="pop_area">
		<div class="pop_content"  style='height:647px;'>
		
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="">
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="status" id="status" value="<c:out value='${lomRq.status}'/>" />
		<input type="hidden" name="submit_status" id="submit_status" />
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>
		<input type="hidden" name="client_modify_div" id="client_modify_div" />
		<!-- 나모 웹 에디터 관련 -->
		<!--  //입력일 때-->				
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos1"  id="fileInfos1" value="1" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos2" id="fileInfos2" value="2" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfosEtc" id="fileInfosEtc"  value="3" /> <!-- 저장될 파일 정보 -->		
		<input type="hidden" name="fileInfos3" id="fileInfos3"  value="4" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4" id="fileInfos4"  value="5" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos5" id="fileInfos5"  value="6" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfoName"	value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"	value="" /> <!-- 첨부파일 화면 iFrame 명 -->
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"	 value="" /> <!-- 키값 -->				
		<input type="hidden" name="view_gbn"	value="modify" /> <!-- 첨부파일 화면 -->
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		<!-- 관련자 데이타 설정  -->
		<input type="hidden" name="chose_client" id="chose_client" />
		<!-- 기존 데이터가 있을 경우에 데이터 삭제해주기 위해서. -->
		<input type="hidden" name="cntrtOppntChk" id="cntrtOppntChk" value="" />
		<!-- 계약단가요약에 데이터가 있는지 체크하기 위해 -->
		<input type="hidden" name="cntrt_untprc_chk" id="cntrt_untprc_chk" value="<c:out value='${lomRq.cntrt_untprc_expl}'/>" />
		<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn" value="<c:out value='${lomRq.dmstfrgn_gbn}'/>">		
		<!-- 임직원조회 팝업 시 리턴값 사전승인발의자인지 사전승인승인자정보 인지 구분하기 위해 -->
		<input type="hidden" name="searchEmployeeGb" id="searchEmployeeGb" value="" />
		<input type="hidden" name="cnsdreq_id" id ="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />
		<input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>">		
		<input type="hidden" name="customer_gb" value="" />	
		<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
		<input type="hidden" name="tmp_cnsd_level" id ="tmp_cnsd_level" value="<c:out value='${lomRq.cnsd_level}'/>" />
		<input type="hidden" name="tmp_prgrs_status" id="tmp_prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>">		
		<!-- 2014-04-17 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
		<input type="hidden" name="gerpPageType" id="gerpPageType" />
		<!-- 2014-04-17 Kevin added.GERP 정보 hidden fields. -->
		<input type="hidden" name="gerpCostType" id="gerpCostType" />
		<input type="hidden" id="gerpContractType" name="gerpContractType" />
		<input type="hidden" id="gerpDivCode" name="gerpDivCode" />
		
		<!-- title -->
		<div class="title_basic mt0">
			<h4><spring:message code="las.page.field.contractManager.reqInfo"/></h4>
		</div>
		<!-- //title -->				
		<!-- button -->
		<div class="btn_wrap mt_m20">					
			 <span class="btn_all_w" onclick="modifyContract();"><span class="save"></span><a><spring:message code="las.page.button.save"/></a></span>
		</div>
		<!-- //button -->
			
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic">
				<colgroup>				
					<col width="12%" />
					<col width="88%" /> <!-- width size 변경 신성우 2014-04-24 -->
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractManager.reqTitle"/></th><!-- 검토의뢰제목 -->
					<td><input type="text" name="req_title" name="id" alt="<spring:message code="las.page.field.contractManager.reqTitle"/>" required value="<c:out value="${lomRq.req_title}" escapeXml='false' />" class="text_full" maxlength="75" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractManager.invPerson"/></th><!-- 관련자 -->
					<td>
						<span id="id_trgtman_nm">
						<% for(int j=0;j<listCa.size();j++){%>
						<% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
						<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
						<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
						<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("demnd_seqno") %>" />
						<input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("trgtman_id") %>" />
						<input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("trgtman_nm") %>" />
						<input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("trgtman_jikgup_nm") %>" />
						<input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("trgtman_dept_nm") %>" />
						<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>					
						<% }%>
						</span>
					
						<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span> <a><spring:message code="las.page.field.contractManager.add"/></a></span>
					
				</tr>
				<tr>
					<th class="borTz02"><spring:message code="las.page.field.contractManager.reqContent"/></th><!-- 요청내용 -->
					<td>
					<span id="cnsd_demnd_cont1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
					<textarea name="cnsd_demnd_cont" id="cnsd_demnd_cont" alt="<spring:message code="las.page.field.contractManager.requContent"/>" cols="30" rows="7" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'cnsd_demnd_cont1','<%=langCd%>')" class="text_area_full"><c:out value='${lomRq.cnsd_demnd_cont}' escapeXml='false'/></textarea>
					</td>
				</tr>		
				<c:if test="${lomRq.plndbn_req_yn == 'Y'}">					
				<!-- 최종본의뢰시 표시 -->				
				<tr>
					<th class="borTz02"><spring:message code="las.page.field.contractManager.isChanged"/> </th><!-- 검토본변경여부 (lastbn_chge_yn) -->
					<td>
						<select name="lastbn_chge_yn" id="lastbn_chge_yn">
							<option value="Y" <% if("Y".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>YES</option>
							<option value="N" <% if("N".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>NO</option>
						 </select><spring:message code="las.page.field.contractManager.checkArticle"/>
					</td>
				</tr>
				<tr>
					<th class="borTz02"><spring:message code="las.page.field.contractManager.chngRs"/></th><!-- 변경내역 및 사유 -->
					<td><textarea name="plndbn_chge_cont" id="plndbn_chge_cont" alt="<spring:message code="las.page.field.contractManager.chngRs"/>" cols="30" rows="4" class="text_area_full"><c:out value="${lomRq.plndbn_chge_cont}" escapeXml='false'/></textarea></td>
				</tr>
				<!-- 최종본의뢰시 표시 ** -->
				</c:if>
			</table>
		
		<div class="title_basic">
			<h4><spring:message code="las.page.field.contractManager.contInfo"/><img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');"/></h4>
		</div>
		<!-- //title -->
				
		<!-- 계약기본 정보 -->
		<table cellspacing="0" cellpadding="0" class="list_basic">
		  <colgroup>
				<col width="4%" />
				<col width="8%" />			
				<col width="18%" />				
				<col width="12%" />
				<col width="13%" />
				<col width="16%" />
				<col width="13%" />
				<col width="16%" />	
			</colgroup>
			<tr>
				<th colspan="2"><spring:message code="las.page.field.contractManager.contNm"/></th><!-- 계약명 -->
				<td colspan="6">
				<input type="hidden" name="cntrt_nm" id="cntrt_nm" alt="<spring:message code="las.page.field.contractManager.contNm"/>"  value="<c:out value='${lomRq.cntrt_nm}'/>" />
				<input type="hidden" name="en_cntrt_nm" id="en_cntrt_nm" htmlEscape="true" />
				<span id="div_cntrt_nm">
				<% if(lomRq.get("cntrt_nm") != null){%>
					<%=lomRq.get("cntrt_nm") %>
				<% }else{%>
					<spring:message code="las.page.field.contractManager.autoGen"/>
				<% }%>				 
				</span>
				</td>
			</tr>			
			<tr>
				<th colspan="2"><spring:message code="clm.page.msg.common.otherParty"/></th><!-- 계약상대방 -->
				<div style='display:none'>
					<input type="hidden" name="cntrt_oppnt_cd" id="cntrt_oppnt_cd" required alt="<spring:message code="las.page.field.contractManager.contPtnCd"/>" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>">
					<input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${lomRq.region_gbn}'/>"><!-- javascript:customerPop('O');">< -->					
				</div>
				<td colspan="2">
					<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" style="width:200px" required alt="<spring:message code="las.page.field.contractManager.contPtnNm"/>" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" readonly="readonly" class="text_search"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="openNegoCustomer('O');" class="cp" alt="search" />
				</td>
				<!-- Sungwoo 임시 hidden 분기 처리 2014-06-19 Response number field 처리가 잘못되어있음 -->
				<% if(!lomRq.get("PRCS_DEPTH").equals("C02507")){%>
				<th><spring:message code="clm.page.field.customer.registerNo" /></th><!-- Registration No  -->
				<td><input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" required alt="<spring:message code="las.page.field.contractManager.rpNm"/>" value="<c:out value='${lomRq.cntrt_oppnt_rprsntman}' escapeXml='false'/>" maxlength="60" class="text_full" style="width:80%"></td>				
				<% }else{%>
					<th></th><td><input type="hidden" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" required alt="<spring:message code="las.page.field.contractManager.rpNm"/>" value="<c:out value='${lomRq.cntrt_oppnt_rprsntman}' escapeXml='false'/>" maxlength="60" class="text_full" style="width:80%"></td>
				<% }%>				 
				<th>
					<!-- 2014-04-17 Kevin commented. -->
					<%-- <spring:message code="clm.page.field.customer.contractRequired" /><!-- Contract Required  --> --%>
				</th>
				<td>
					<!-- 2014-04-17 Kevin commented. -->
					<!-- <select name="cntrt_oppnt_type" id="cntrt_oppnt_type"></select> -->
				</td>				
			</tr>
			<tr class="slide-target02 slide-area">
				<th colspan="2"><spring:message code="las.page.field.contractManager.contType"/></th><!-- 계약유형 -->
				<td colspan="6">
					<input type="hidden" name="biz_clsfcn" id="biz_clsfcn" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
					<input type="hidden" name="depth_clsfcn" id="depth_clsfcn" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
					<input type="hidden" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
					<input type="hidden" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />				
					
					<input type="hidden" name="en_biz_clsfcn_nm" id="en_biz_clsfcn_nm" value="<c:out value='${lomRq.biz_clsfcn_nm_en}'/>" />
					<input type="hidden" name="en_depth_clsfcn_nm" id="en_depth_clsfcn_nm" value="<c:out value='${lomRq.depth_clsfcn_nm_en}'/>" />
					<input type="hidden" name="en_cnclsnpurps_midclsfcn_nm" id="en_cnclsnpurps_midclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn_nm_en}'/>"  />
					<input type="hidden" name="en_cnclsnpurps_bigclsfcn_nm" id="en_cnclsnpurps_bigclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn_nm_en}'/>" />		 
						
					<input readOnly type="text" name="biz_clsfcn_nm" id="biz_clsfcn_nm" required alt="<spring:message code="las.page.field.contractManager.contType"/>" value="<c:out value='${lomRq.biz_clsfcn_nm}'/>" class="text_full" style="width:30%" />
					<input readOnly type="text" name="depth_clsfcn_nm" id="depth_clsfcn_nm" required alt="<spring:message code="las.page.field.contractManager.contType"/>" value="<c:out value='${lomRq.depth_clsfcn_nm}'/>" class="text_full" style="width:20%" />
					<input readOnly type="text" name="cnclsnpurps_bigclsfcn_nm" id="cnclsnpurps_bigclsfcn_nm" required alt="<spring:message code="las.page.field.contractManager.contType"/>" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn_nm}'/>" class="text_full" style="width:20%" />
					<input readOnly type="text" name="cnclsnpurps_midclsfcn_nm" id="cnclsnpurps_midclsfcn_nm" required alt="<spring:message code="las.page.field.contractManager.contType"/>" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn_nm}'/>" class="text_full" style="width:20%" />
					<a href="javascript:openChooseContractType();"><img src="<%=IMAGE %>/icon/ico_search_g.gif" /></a>
				</td>
			</tr>
			<tr id="trCntrtInfo">
                    <th colspan="2"><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td colspan="2">
                        <select name="cntrt_top30_cus" id="cntrt_top30_cus" class="all" style="width:95%">
                           <c:out value="${lomRq.cntrt_top30_cus }" escapeXml="false"/>
                        </select>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
                    <td>
                        <select name="cntrt_rel_prd" id="cntrt_rel_prd" class="all" style="width:95%">
                           <c:out value="${lomRq.relPrdCombo}" escapeXml="false"/>
                        </select>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
                    <td>
                        <select name="cntrt_src_cont_drft" id="cntrt_src_cont_drft" class="all" style="width:95%">
                           <c:out value="${lomRq.srcConDraft }" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
			<tr class="slide-target02 slide-area">
				<th colspan="2"><spring:message code="las.page.field.contractManager.contTarget"/></th><!-- 계약대상 -->
				<td colspan="2">
					<select name="cntrt_trgt" id="cntrt_trgt" class="all" style="width:95%"></select>
				</td>
				<th><spring:message code="las.page.field.contractManager.contTargetD"/></th>
				<td colspan="3"><input type="text" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${lomRq.cntrt_trgt_det}' escapeXml='false'/>" maxlength="300" class="text_full" /><!-- 500 --></td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th colspan="2" rowspan="4"><spring:message code="las.page.field.contractManager.attchFile"/><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th><!-- 첨부파일 -->
				<td class="blueD"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}">[<spring:message code="las.page.field.lawconsideration.attach"/>]<br></c:if><spring:message code="las.page.field.contractManager.contForm"/></span></td>
				<td  colspan="5">
				<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
				<iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0"	scrolling="auto" class='addfile_iframe' allowTransparency="true" style="width:100%;" ></iframe>					
				</td>
			</tr> 
			<tr class="slide-target02 slide-area">
				<td class="tal_lineL"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}">[<spring:message code="las.page.field.contractManager.finalVer"/>]<br></c:if><spring:message code="las.page.field.contractManager.attach"/></span></td>
				<td colspan="5">
				<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
				<!-- Sungwoo 2014-07-21 scrolling changed -->
				<iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<td class="tal_lineL"><span class="blueD">
					<c:choose>
						<c:when test="${command.plndbn_req_yn == 'Y'}">
							<span class="blueD">[<spring:message code="las.page.field.contractManager.chngChck"/>]<br><spring:message code="las.page.field.contractManager.contForm"/>,<br><spring:message code="las.page.field.contractManager.etcAttach"/></span>
						</c:when>
						<c:otherwise>
							<span class="blueD"><spring:message code="las.page.field.contractManager.others"/></span>
						</c:otherwise>
					</c:choose>
					</span>
				</td>
				<td colspan="5">
				<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>			
				<iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" scrolling="auto" class='addfile_iframe' allowTransparency="true"></iframe>						
				</td>
			</tr>			
			<tr class="slide-target02 slide-area">
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
					<td colspan="5">
						<iframe src="<c:url value='/clm/blank.do' />" id="fileListOut" name="fileListOut" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
			</tr>
			<%if("C02406".equals(lomRq.get("cntrt_status"))){ %>			
			<tr id="dropOPnnArea" class="slide-target02 slide-area">
				<th colspan="2">Drop</th>
				<td colspan="6"><%=lomRq.get("cntrt_chge_demnd_cause") %>								
				</td>
			</tr>			
			<%	} %>
		</table>
		<!-- //계약기본 정보 -->
		<!--bottom table -->
		<div id="tr_down02" style="display:none;">		
			
			<!-- 계약상세 -->
			<div class="title_basic3"><spring:message code="las.page.field.contractManager.basicInfo"/></div>
			<!-- 계약상세내역-->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="12%" />
					<col width="30%" />
					<col width="14%" />
					<col width="32%" />
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractManager.contPeriod"/></th><!-- 계약기간 -->
					<td colspan="4">
						<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" required alt="<spring:message code="las.page.field.contractManager.contFromDate"/>" value="<c:out value='${lomRq.cntrtperiod_startday}'/>" class="text_calendar02"/> ~ 
						<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" required alt="<spring:message code="las.page.field.contractManager.contToDate"/>" value="<c:out value='${lomRq.cntrtperiod_endday}'/>" class="text_calendar02" />
					</td>		 
				<tr>
					<th><spring:message code="las.page.field.contractManager.chkPayCol"/></th><!-- 지불/수금 구분 -->
					<td colspan="2">
						<select name="payment_gbn" id="payment_gbn" class="all" style="width:150px" onChange="paymentGbnChange();">						
						</select>
					</td>	
					<th><spring:message code="las.page.field.contractManager.contAmt"/></th>
					<td><input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt}' />" onclick="javascript:paymentGbnChange2();" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="19" /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractManager.currcUnt2"/></th><!-- 통화(단위) -->
					<td colspan="2">
						<select name="crrncy_unit" id="crrncy_unit">						
						</select>
					</td>
					<th><spring:message code="las.page.field.contractManager.contCost"/></th><!-- 단가로 체결 -->
					<td>
						<input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y"  onclick="toggleCheckBox();"/> <label for="" ><spring:message code="las.page.field.contractManager.signUcost"/></label>
					</td>
				</tr>
				<tr id="trCntrtUntprc" >
					<th><spring:message code="las.page.field.contractManager.sumUcost"/></th><!-- 단가내역 요약 -->
					<td colspan="4">
						<textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${lomRq.cntrt_untprc_expl}' escapeXml='false'/></textarea>
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractManager.addPnB"/></th><!-- 추진목적 및 배경 -->
					<td colspan="4">
						<span id="pshdbkgrnd_purps1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						<textarea name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" alt="<spring:message code="las.page.field.contractManager.addPnB"/>" cols="30" rows="7" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'pshdbkgrnd_purps1','<%=langCd%>')" class="text_area_full"><c:out value='${lomRq.pshdbkgrnd_purps}' escapeXml='false'/></textarea>
					</td>
				</tr>
				
				<tr id="trSpecialAttr">
					<th><spring:message code="las.page.field.contractManager.etcPoint"/></th><!-- 기타주요사항 -->
					<td colspan="4"><textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="las.page.field.contractManager.etcPoint"/>" cols="30" rows="4" onkeyup="f_chk_byte(this,300)" class="text_area_full" maxLength="800"><c:out value='${lomRq.etc_main_cont}' escapeXml='false'/></textarea></td>
				</tr>			
				
				</table>
		</div>		
		
		<!-- 2014-04-17 Kevin Added. GERP Information Input form -->
		<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:none; width:100%; height:120px; margin:10px 0 5px 0;" scrolling="no" frameborder="0"></iframe>
			
			<!-- //계약상세 -->	
			<div class="title_basic3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.before_cnsd_info"/></div><!-- 사전계약정보 -->	
			<!-- 사전검토정보-->
			<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="30%" />
					<col width="13%" />
					<col width="16%" />
					<col width="13%" />
					<col width="16%" />  
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractManager.preAppMover"/></th>
						<input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${lomRq.bfhdcstn_mtnman_id}'/>" />
					<td>
						<input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN');" class="cp" alt="search" />
					</td>
					<th><spring:message code="las.page.field.contractManager.rank"/></th>
					<td>
						<input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/>" />
						<span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/></sapn></td>
					<th><spring:message code="las.page.field.contractManager.deptNm"/></th>
					<td>
							  <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/>" />
							  <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/></span></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractManager.appWay"/></th>
					<td colspan="5">
						<select name="bfhdcstn_apbt_mthd" id="bfhdcstn_apbt_mthd">
							<option><spring:message code="las.page.field.contractManager.select"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractManager.preApproval"/><br /><spring:message code="las.page.field.contractManager.appByInfo"/></th>
					<td colspan="5">
					<span><spring:message code="las.page.field.contractManager.onlyFinal"/></span>
						<!-- 테이블 안에 테이블 -->
						<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub02">
							<colgroup>
								<col width="25%" />
								<col width="25%" />
								<col width="25%" />
								<col width="25%" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="las.page.field.contractManager.name"/></th>
									<th><spring:message code="las.page.field.contractManager.rank"/></th>
									<th><spring:message code="las.page.field.contractManager.dept"/></th>
									<th><spring:message code="las.page.field.contractManager.appDay"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${lomRq.bfhdcstn_apbtman_id}'/>" />
									<td class='tC'>
										<input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_nm}'/>" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search"  onclick="javascript:searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT');"  /></td>
									<td class='tC'>
										<input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/>" />
										<span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/></span></td>
									<td class='tC'>
										<input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/>" />
										<span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/></span></td>
									<td class='tC'>
										<input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${lomRq.bfhdcstn_apbtday}'/>" /></td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->						
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractManager.attchData"/><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="5">
						<spring:message code="las.page.field.contractManager.mustDoAddfile"/>					
					<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
					<!-- Sungwoo replacement height size 2014-07-03-->
					<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
			</table>
			<!-- 사전검토정보 -->
			<div class="title_basic3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_cntrt_info" /><!-- 연관계약정보 --> </div>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="20%" />
					<col width="40%" />
					<col width="15%" />
					<col/>
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_type" /></th>
					<th><spring:message code="las.page.field.contractManager.lnkCont"/></th>
					<th><spring:message code="las.page.field.contractManager.defDetail"/></th>
					<th><spring:message code="las.page.field.contractManager.relationD"/></th>					
				</tr>				
				<tr id="trRelationContract">
					 <td><select name="rel_type" id="rel_type" onChange="reltypeChange();"></select></td>
					 <input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id">
					 <td>
					 	<input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
					 <td><select name="rel_defn" id="rel_defn"></select></td>
					 <td style="vertical-align:middle" >
					 	<input type="text" name="expl" id="expl" class="text_full" style="width:60%"> <img src="<%=IMAGE %>/btn/btn_regist.gif" onclick="javascript:actionRelationContract('insert','','');"  class='cp' />
					  </td>
				</tr>
				<c:out value="${contRc}" escapeXml="false"/>				
			</table>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
			<tr>
				<td>
					<spring:message code="las.page.field.contractManager.makeRel"/><br>
					&nbsp;<spring:message code="las.page.field.contractManager.ndaMou"/><br>
					&nbsp;<spring:message code="las.page.field.contractManager.masterSub"/><br>
					&nbsp;<spring:message code="las.page.field.contractManager.chngCncl"/><br>
					&nbsp;<spring:message code="las.page.field.contractManager.others4"/><br>
				</td>
				</tr>
			</table>	
			<!-- //연관계약정보 -->
			<!-- 검토의견 (회신)-->
			
			<!-- //회신 -->
		</div>
		
		<!-- //tab_content -->		
		<!-- //content -->	
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</form:form>
	</div>
</div>

<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		 <span class="btn_all_w" onclick="modifyContract();"><span class="save"></span><a><spring:message code="las.page.button.save"/></a></span>
		 <span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a href="#"><spring:message code='las.page.button.cancel' /></a></span>
	</div>
</div>
<!-- //footer -->

</body>
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</html>