<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>

<%--
/**
 * 파  일  명 : ConsiderationHQ_d.jsp
 * 프로그램명 : HQ 검토 의뢰내역 - 검토의뢰
 * 작  성  일 : 201.04.01
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	//String menuNavi = (String) request.getAttribute("secfw.menuNavi");

	boolean authYN_HQ01 = false;	
	boolean authYN_HQ02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("HQ01")){
		authYN_HQ01 = true;
	}
	
	if(tmpSessionRoleList.contains("HQ02")){
		authYN_HQ02 = true;
	}

	// 특화정보 표시를 위한 서블릿 스크립트 START 20140515 박K
	 ArrayList listDc = (ArrayList) request.getAttribute("listDc");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");

	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap) request.getAttribute("lomMn");
	ListOrderedMap lomDcd = (ListOrderedMap) request.getAttribute("lomDcd");

	String userId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
	String userNm = StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "");
	
	String modYn = (String)request.getAttribute("modYn");
	ListOrderedMap lomT;
	
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");
	ArrayList listCs = (ArrayList)request.getAttribute("listCs");
	
	StringBuffer resultCt = new StringBuffer(1024);
	StringBuffer resultSb = new StringBuffer(1024);
	
	String plndbnReqYn 	= (String)lomRq.get("plndbn_req_yn");
	String respUserDiv 	= (String)lomRq.get("resp_user_div");
	String autoApbtYn 	= (String)lomRq.get("auto_apbt_yn");
	
	Integer attrSeqno;		
	String crtnDeth;	
	String attrNm;
	String attrCont;
	String mndtryYn;
	String thAttrNm;
	String dpMndtry;
	
	if(listTs != null && listTs.size() > 0) {
		for (int i = 0; i < listTs.size(); i++) {
			lomT = (ListOrderedMap)listTs.get(i);
			
			attrSeqno	= ((BigDecimal)lomT.get("attr_seqno")).intValue();		
			crtnDeth    = (String)lomT.get("crtn_deth");	
			attrNm	   	= (String)lomT.get("attr_nm");
			attrCont	= StringUtil.convertHtmlTochars((String)lomT.get("attr_cont"));
			mndtryYn    = (String)lomT.get("mndtry_yn");			// 필수여부
			thAttrNm	= mndtryYn.equals("Y")? "* " + attrNm.replaceAll("/", "/<br>") : attrNm.replaceAll("/", "/<br>");
			dpMndtry 	= "";
			
			//필수여부 
			if(respUserDiv.equals("Y") && mndtryYn.equals("Y")){
				dpMndtry = "required alt='"+attrNm+"'";					
			}else{
				dpMndtry = "alt='"+attrNm+"'";
			}
			
			if(plndbnReqYn.equals("Y") && attrSeqno == 5){		// 라이선스범위및조건
				// 2012.01.31 계약 보류건은 VIEW 전환 modified by hanjihoon
				if(!"C02603".equals(lomRq.get("depth_status")) && respUserDiv.equals("Y") && "RA02".equals(command.getTop_role()) && "Y".equals((String)request.getAttribute("modYn"))){	// 담당자의 입력 여부
					resultCt.append("<tr> \n");
					resultCt.append("<th><span>"+thAttrNm+"</span></th> \n");
					resultCt.append("<td valign='top' colspan='1'> \n");
					resultCt.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
					resultCt.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
					resultCt.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + attrCont + "</textarea> \n");
					resultCt.append("</tr> \n");					
				}else if(!attrCont.equals("")){
					resultCt.append("<tr> \n");
					resultCt.append("<th><span>"+thAttrNm+"</span></th> \n");
					resultCt.append("<td valign='top' colspan='1'> \n");
					//resultCt.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre></td> \n");
					resultCt.append("<pre>" + attrCont + "</pre></td> \n");
					resultCt.append("</tr> \n");
				}
			}else if(attrSeqno != 5 && !attrCont.equals("")){
				resultCt.append("<tr> \n");
				resultCt.append("<th><span>"+thAttrNm+"</span></th> \n");
				resultCt.append("<td valign='top' colspan='1'> \n");
				//resultCt.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre></td> \n");
				resultCt.append("<pre>" + attrCont + "</pre></td> \n");
				resultCt.append("</tr> \n");
			}
		}
	}
	
	if(plndbnReqYn.equals("Y") && listCs != null && listCs.size() > 0) {
		for (int i = 0; i < listCs.size(); i++) {
			lomT = (ListOrderedMap)listCs.get(i);
			
			attrSeqno	= ((BigDecimal)lomT.get("attr_seqno")).intValue();	
			crtnDeth    = (String)lomT.get("crtn_deth");	
			attrNm	   	= (String)lomT.get("attr_nm");
			attrCont	= StringUtil.convertHtmlTochars((String)lomT.get("attr_cont"));
			mndtryYn    = (String)lomT.get("mndtry_yn");			// 필수여부
			thAttrNm	= mndtryYn.equals("Y")? "* " + attrNm.replaceAll("/", "/<br>") : attrNm.replaceAll("/", "/<br>");
			dpMndtry 	= "";
			
			if(respUserDiv.equals("Y") && mndtryYn.equals("Y") && attrSeqno != 2 && attrSeqno != 7){
				dpMndtry = "required alt='"+attrNm+"'";					
			}else{
				dpMndtry = "alt='"+attrNm+"'";
			}
			
			if(!"C02603".equals(lomRq.get("depth_status")) && respUserDiv.equals("Y") && command.getTop_role().equals("RA02") && "Y".equals((String)request.getAttribute("modYn"))){
				resultSb.append("<tr> \n");
				resultSb.append("<th><span>"+thAttrNm+"</span></th> \n");
				resultSb.append("<td valign='top'> \n");
				
				resultSb.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
				resultSb.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
				
				if(attrSeqno == 2){				// 독자 개발 가능 여부
					if(attrCont.equals("")){
						attrCont = "1";
					}
					
					resultSb.append("<select name='arr_attr_cont_idp' id='arr_attr_cont_idp' style='width:100px;' onChange='javascript:selectIndependent();'> \n");
					resultSb.append("	<option value='1' " + (attrCont.equals("") || attrCont.equals("1")? "selected" : "") + "><spring:message code='las.page.field.contractManager.possible'/></option> \n");//가능
					resultSb.append("	<option value='2' " + (attrCont.equals("2")? "selected" : "") + "><spring:message code='las.page.field.contractManager.impossible'/></option> \n");//불가능
					resultSb.append("	<option value='3' " + (attrCont.equals("3")? "selected" : "") + "><spring:message code='las.page.field.contractManager.psbCndi'/></option> \n");//조건부 가능
					resultSb.append("	<option value='4' " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "selected" : "") + "><spring:message code='las.page.field.contractManager.others'/></option> \n");//기타
					resultSb.append("</select> \n");
					resultSb.append("<div id='div_arr_attr_cont_idp_val' class='mt4' style='display: " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "block;" : "none;") + "'> \n");
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont_idp_val' cols='30' rows='3' class='text_area_full' style='display: " + (!(attrCont.equals("") || attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "block;" : "none;") + "' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + (attrCont.equals("4")? "" : attrCont) + "</textarea> \n");
					resultSb.append("</div> \n");
				}else if(attrSeqno == 3){		// Warranty/유지보수조건
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else if(attrSeqno == 4){		// 개발결과물귀속/소유권
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else if(attrSeqno == 7){		// 소유권
					if(attrCont.equals("")){
						attrCont = "1";
					}
					
					resultSb.append("<select name='arr_attr_cont_owner'  id='arr_attr_cont_owner' style='width:100px;' onChange='javascript:selectOwnership();' > \n");
					resultSb.append("	<option value='1' " + (attrCont.equals("") || attrCont.equals("1")? "selected" : "") + "><spring:message code='las.page.field.contractManager.theCompany'/></option> \n");//당사
					resultSb.append("	<option value='2' " + (attrCont.equals("2")? "selected" : "") + "><spring:message code='las.page.field.contractManager.enterprise'/></option> \n");//업체
					resultSb.append("	<option value='3' " + (attrCont.equals("3")? "selected" : "") + "><spring:message code='las.page.field.contractManager.joint'/></option> \n");//공동
					resultSb.append("	<option value='4' " + (attrCont.equals("4")? "selected" : "") + ">N.A</option> \n");
					resultSb.append("</select> \n");
					resultSb.append("<input type='hidden' name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont_owner_val' value='1'/> \n");
				}else if(attrSeqno == 8){		// Indeminification 조건 
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + attrCont + "</textarea> \n");
				}else{
					resultSb.append("<textarea name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' cols='30' rows='3' class='text_area_full' onkeyup='f_chk_byte(this,4000)' maxLength='4000'>" + attrCont + "</textarea> \n");
				}
				
				resultSb.append("</td></tr> \n");
			}else{
				if(!attrCont.equals("")){
					resultSb.append("<tr> \n");
					resultSb.append("<th><span>"+thAttrNm+"</span></th> \n");
					resultSb.append("<td valign='top'> \n");
					
					if(attrSeqno == 2){				// 독자 개발 가능 여부
						resultSb.append(attrCont.equals("1")? "<spring:message code='las.page.field.contractManager.possible'/>" : "");//가능
						resultSb.append(attrCont.equals("2")? "<spring:message code='las.page.field.contractManager.impossible'/>" : "");//불가능
						resultSb.append(attrCont.equals("3")? "<spring:message code='las.page.field.contractManager.psbCndi'/>" : "");//조건부 가능
						resultSb.append(!(attrCont.equals("1") || attrCont.equals("2") || attrCont.equals("3"))? "<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre>" : "");
					}else if(attrSeqno == 7){		// 소유권
						resultSb.append(attrCont.equals("1")? "<spring:message code='las.page.field.contractManager.theCompany'/>" : "");//당사
						resultSb.append(attrCont.equals("2")? "<spring:message code='las.page.field.contractManager.enterprise'/>" : "");//업체
						resultSb.append(attrCont.equals("3")? "<spring:message code='las.page.field.contractManager.joint'/>" : "");//공동
						resultSb.append(attrCont.equals("3")? "N.A" : "");
					}else{		// 유지보수조건 및 기타
						//resultSb.append("<pre>" + StringUtil.convertEnterToBR(attrCont) + "</pre>");
						resultSb.append("<pre>" + attrCont + "</pre>");
					}
				}
				
				resultSb.append("</td></tr> \n");
			}
		}
	}		
	
	//history
	ArrayList review	= (ArrayList)request.getAttribute("review");
	ArrayList approve 	= (ArrayList)request.getAttribute("approve");
	//ArrayList sign 		= (ArrayList)request.getAttribute("sign");
	ArrayList info 		= (ArrayList)request.getAttribute("info");
	//ArrayList hold 		= (ArrayList)request.getAttribute("hold");
	List cnsdList 		= (List)request.getAttribute("cnsdList");
	
	String notApprYn	= "N";
	
	if(lomRq.get("depth_status").equals("C02606") && lomRq.get("prgrs_status").equals("C04205") && lomDcd.get("cnsd_status").equals("C04303")){
		if(command.getTop_role().equals("RA01") || command.getTop_role().equals("RB01") ){
			notApprYn 	= "Y";
		}
			
	}
	request.setAttribute("not_appr_yn", notApprYn);	
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="javascript">

var NamoAttachSaveDir = "<spring:message code='secfw.namo.attach.dir'/>"; // 프로퍼티에서 저장 경로를 가져온다.
var NamoAttachUrl = "<spring:message code='secfw.namo.http.url'/>"; // 프로퍼티에서 저장 경로를 가져온다.

var chk_flag = false;

	$(document).ready(function(){

		// alert("TOP_ROLE ? : " +  "<%=command.getTop_role()%>");	
	
		//  특화정보 초기화
		$('#tab_contents_sub_conts4').hide();
		$('#tab_contents_sub_conts5').hide();
		$('#tab_contents_sub_conts6').hide();	
	
		var blngt_orgnz 			= $("#blngt_orgnz").val();								//소속조직
		var mn_cnsd_dept			= $("#mn_cnsd_dept").val();								//검토의뢰_정_검토_부서
		var vc_cnsd_dept			= $("#vc_cnsd_dept").val();								//검토의뢰_부_검토_부서
		var mn_respman_apnt_yn		= $("#mn_respman_apnt_yn").val();						//검토의뢰_정_담당자_지정_여부
		var vc_respman_apnt_yn		= $("#vc_respman_apnt_yn").val();						//검토의뢰_부_담당자_지정_여부
		var depth_status			= "<c:out value='${lomRq.depth_status}' />";			//단계_상태
		var cnsd_status				= "<c:out value='${lomDcd.cnsd_status}' />";			//검토_상태
		var prgrs_status			= $("#prgrs_status").val();								//진행상태
		
		//결재없이회신버튼 제어를 위하여 추가(2012.08.14)
		var plndbn_req_yn			= $('#plndbn_req_yn').val();							//최종본의뢰여부
		var auto_apbt_yn			= "<%=autoApbtYn%>";									//전결처리여부
		var top_role				= $("#top_role").val();
		var page_div				= $("#page_div").val();									//RESP이면 그룹장이 담당자 검토 실행

		//Ownership 확인
		$("#table_div").val('A');

		$('#list_respman_id_hq li').bind('click', function(){
			$('#list_respman_id_hq li').removeAttr("style", "");
			$('#list_respman_id_hq li').removeClass('selected');
			
			$(this).attr("style", "background: #dddddd;");
			$(this).addClass("selected");		
		});
		
		// 배정 팝업용일 가능성이 있음.
		getCodeSelectByUpCd("frm", "list_user_id", "/common/clmsCom.do?method=getLasPersonComboHTML&combo_type=&combo_selected=&srch_dept=law&cnsdreq_id=" + "<c:out value='${lomRq.cnsdreq_id}'/>");
		getCodeSelectByUpCd("frm", "sub_list_user_id", "/common/clmsCom.do?method=getLasPersonComboHTML&combo_type=&combo_selected=&srch_dept=ip&cnsdreq_id=" + "<c:out value='${lomRq.cnsdreq_id}'/>");
				
		initPage();
		
		// 준거법
		// getCodeSelectByUpCd("frm", "loac", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C022&combo_type=S&combo_del_yn=N&combo_selected=" + "<c:out value='${lomRq.loac}'/>");
		// 분쟁_해결_방법
		// getCodeSelectByUpCd("frm", "dspt_resolt_mthd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C023&combo_type=S&combo_del_yn=N&combo_selected=");
		
		// 검토가 최종본인 경우 특화정보를 표시 한다. 20140515 DAR K
		if("<c:out value='${lomRq.plndbn_req_yn}' />" == "Y"){
			$('#tab_contents_sub_conts4').show();
			$('#tab_contents_sub_conts5').show();
			$('#tab_contents_sub_conts6').show();			
		}
				
		tit_Toggle(this,'tr_down01');
		tit_Toggle(this,'tr_down02');
		
		// viewHiddenProgress(false);
		
		// 이력부분 + - 버튼 접기 / 펴기 이벤트 바인드
	    var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
 		var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
	    // 검토이력
 		$('img[alt$=show]').toggle(function(){
			$(this).removeAttr().attr("src",expandIcon);
			$(this).parent().parent().parent().next('#tr_show').attr("style", "display:");		
		}, function(){
			$(this).removeAttr().attr("src",collapseIcon);
			$(this).parent().parent().parent().next('#tr_show').attr("style", "display:none");
		});
		//승인이력
		$('img[alt$=show01]').toggle(function(){
			$(this).removeAttr().attr("src",expandIcon);
			$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:");
			
		}, function(){
			$(this).removeAttr().attr("src",collapseIcon);
			$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:none");
		});
		// 본사 검토 이력
	    $('img[alt$=show_hq]').toggle(function(){
			$(this).removeAttr().attr("src",expandIcon);
			$(this).parent().parent().parent().next('#tr_show_hq').attr("style", "display:");		
		}, function(){
			$(this).removeAttr().attr("src",collapseIcon);
			$(this).parent().parent().parent().next('#tr_show_hq').attr("style", "display:none");
		});
	    
		<c:if test="${lomHq.HQ_REL_PRO == 'RP030'}">
		
			<c:if test="${lomHq.CE_RESP_YN == '2'}">	
				frm.hq_ce_cnsd_opnn.value = "CE Reviewer was not assigned";
			</c:if>
				
			<c:if test="${lomHq.IM_RESP_YN == '2'}">	
				frm.hq_im_cnsd_opnn.value = "IM Reviewer was not assigned";
			</c:if>			

		</c:if>

	});

	
	// 화면출력이 완료된 시점에서 크로스에디터로 작성된 내용을 초기화 처리 한다.  
/* 	$(window).load(function(){	
		namoView();
	}); */
	
	/**
	* 크로스에디터 내용 display
	*/
	function namoView(){

		//1. 검토요청내용
	    document.getElementById('if_cnsd_demnd_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_demnd_cont_textarea').value;
	    document.getElementById('if_cnsd_demnd_cont').height = document.getElementById('if_cnsd_demnd_cont').contentWindow.document.body.scrollHeight+30+'px';

	 	//2. 추진목적 및 배경
	 	document.getElementById('if_cnsd_bg_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_bg_cont_textarea').value;
	 	document.getElementById('if_cnsd_bg_cont').height = document.getElementById('if_cnsd_bg_cont').contentWindow.document.body.scrollHeight+30+'px';
	 	
		//3. 검토의견 - 추후 사용가능성이 있음 
	 	document.getElementById('if_cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_opnn_textarea').value;
	 	document.getElementById('if_cnsd_opnn').height = document.getElementById('if_cnsd_opnn').contentWindow.document.body.scrollHeight+30+'px';
	}
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){

		var frm = document.frm;		
		$("#cntrt_srch_yn").val("N");		
		frm.stat_flag.value = arg;
		
		if(arg == "LIST"){ //목록으로 가기 
		
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/review/considerationHQ.do' />";
			frm.method.value = "listConsiderationHQ";		
			frm.submit();			
		} 		
	}	
	
	/**
	* 임시저장, 의견전달, 발신 
	*/
	function returnConsideration(arg){
		
		var frm = document.frm;
		var temp_msg = "";
		
		frm.stat_flag.value = arg;
		
		$("#cntrt_srch_yn").val("N");
		$('#attach_file_div').val("N");
		$("#stat_flag").val(arg);
		
		if(arg=="SAVE"){  //  임시저장
			
			temp_msg = "<spring:message code='las.msg.succ.save' />"; // Saved.			
			
		} else if(arg=="SEND"){		// 회신
			
			temp_msg = "<spring:message code='las.page.button.resolved' />"; //  Replied
		
		} else{ 	
			return;	
		}	
			
		var options = {
				url: "<c:url value='/clm/review/considerationHQ.do?method=returnConsideration' />",
				type: "post",
				dataType: "json",			
				success: function(responseText, statusText) {
					viewHiddenProgress(false);
					
					if(responseText.returnVal == undefined || responseText.returnVal == 2){
						if(responseText.returnMsg != ""){
							viewDlgAlert(responseText.returnMsg);
		    			}else{
		    				viewDlgAlert("<spring:message code='las.page.field.considerationImpl.returnConsideration01' />");
		    			}
						window.setTimeout(function() {forwardConsideration("LIST");},3000);
			    		$('#attach_file_div').val("Y");
						viewHiddenProgress(false);
					}else{
						viewDlgAlert(temp_msg);
		    			window.setTimeout(function() {forwardConsideration("LIST");},3000);
			    		$('#attach_file_div').val("Y");
						viewHiddenProgress(false);
					}										
				}
			};
		
			frmSubmit(options,arg);			
	}	
	
	/**
	* 첨부파일 저장 후 FORM 전송 
	*/
	function frmSubmit(options,arg){
		var frm = document.frm;
		var display_div = $('input[name="cnsdreq_opnn"]:checked').val();
		var msg;
		var msgVal = ""; 
		
	    var file_key_hq_send = frm.cntrt_id.value +"@"+ frm.hq_cnsdreq_id.value + "_" + frm.next_level.value;
	    frm.ref_key.value = file_key_hq_send;
						
		// frm.body_mime_rq.value =CrossEditor1.GetBodyValue(); // 크로스에디터 적용시 필요한 처리 
		
		<c:choose>			
			<c:when test="${lomHq.HQ_REL_PRO == 'RP010'}">			
					
				if(frm.hq_ce_cnsd_opnn.value == ""){
					msgVal = "HQ(CE) Legal Opinion "; 
					alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
					return;
				} 
		
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP020'}">			
			
				if(frm.hq_im_cnsd_opnn.value == ""){
					msgVal = "HQ(IM) Legal Opinion "; 	
					alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
					return;
				} 
	
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP030'}">	
			
			
				if(arg == "SAVE"){		// 임시저장
				
					if(frm.hq_ce_cnsd_opnn.value == "" && frm.hq_ce_cnsd_opnn.value == ""){
						msgVal = "HQ(CE) Legal Opinion OR HQ(IM) Legal Opinion "; 	
						alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
						return;
					} 		
					
				} else if(arg == "SEND"){		// 회신
					
					if(frm.hq_ce_cnsd_opnn.value == ""){
						msgVal = "HQ(CE) Legal Opinion "; 	
						alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
						return;
					} 		
					
	 				if(frm.hq_im_cnsd_opnn.value == ""){
						msgVal = "HQ(IM) Legal Opinion "; 	
						alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
						return;
					} 
				
				}				
	
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP040'}">			
			
				if(frm.hq_not_cnsd_opnn.value == ""){
					msgVal = "HQ Legal Opinion "; 	
					alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
					return;
				} 		
	
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>	
		
		if(arg == "SAVE"){		// 임시저장
			if(!confirm("<spring:message code='secfw.msg.ask.tempSave' />")){ //임시저장하시겠습니까?
				return;
			}
		} else if(arg == "SEND"){		// 회신
			if(!confirm("<spring:message code='las.msg.ask.approvalOpnn' />")){ // 검토 회신 하시겠습니까?
				return;
			}
		}
		
		viewHiddenProgress(true);		
		
		<c:choose>			
			<c:when test="${lomHq.HQ_REL_PRO == 'RP010'}">			
		
				hq_ce_cnsd_opnn_file.UploadFile(function(uploadCount){
					//viewHiddenProgress(true);
					$("#frm").ajaxSubmit(options);
				});		
		
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP020'}">			
			
				hq_im_cnsd_opnn_file.UploadFile(function(uploadCount){
					viewHiddenProgress(true);		
					$("#frm").ajaxSubmit(options);
				});		
	
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP030'}">	
			
				hq_ce_cnsd_opnn_file.UploadFile(function(uploadCount){
					hq_im_cnsd_opnn_file.UploadFile(function(uploadCount){
						viewHiddenProgress(true);		
						$("#frm").ajaxSubmit(options);
					});
				});		
	
			</c:when>
		
			<c:when test="${lomHq.HQ_REL_PRO == 'RP040'}">			
			
				hq_not_cnsd_opnn_file.UploadFile(function(uploadCount){
					viewHiddenProgress(true);		
					$("#frm").ajaxSubmit(options);
				});
	
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>			
	
	}
	
	/**
	* 첨부파일 초기화 로딩 처리
	*/
	function initPage(){
		var frm = document.frm;
		$('input[id^=fileInfosRe]').val("");
		var	file_key = frm.cntrt_id.value +"@"+ frm.cnsdreq_id.value;
		var	unprc_file_key = frm.cntrt_id.value;
		
	   	// 의뢰 - 계약서
	    frm.target          = "fileListContract";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120201";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "N";
	    frm.fileInfoName.value = "fileInfosContract";
	    frm.fileFrameName.value = "fileListContract";  
    	$("#img_step_q").hide();	    
    	$("#ico_maxsize1").hide();	 
    	$("#ico_maxsize2").hide();	 
    	$("#ico_maxsize3").hide();	 
    	$("#ico_maxsize4").hide();
    	$("#fileListContract").addClass("addfile_iframe_d");	 	
    	$("#fileListEtc").addClass("addfile_iframe_d");	
    	$("#fileListGita").addClass("addfile_iframe_d");	
    //	$("#fileListOL").addClass("addfile_iframe_d");	 
		frm.view_gbn.value = "download";
	  	getClmsFilePageCheck('frm');
	    
	 	// 의뢰 - 첨부/별첨
	    frm.target          = "fileListEtc";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120208";
	    frm.ref_key.value = file_key;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfosEtc";
	    frm.fileFrameName.value = "fileListEtc";
   		frm.view_gbn.value = "download";
	  	getClmsFilePageCheck('frm');

	 	// 의뢰 - 기타
	    frm.target          = "fileListGita";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage"; 
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120205";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfosGita";
	    frm.fileFrameName.value = "fileListGita";
   		frm.view_gbn.value = "download";
	  	getClmsFilePageCheck('frm');
	    
	 	// 의뢰 -  OUTLOOK
/* 	    frm.target          = "fileListOL";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage"; 
	    frm.file_bigclsfcn.value = "O001";
	    frm.file_midclsfcn.value = "O0011";
	    frm.file_smlclsfcn.value = "O00111";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfoOL";
	    frm.fileFrameName.value = "fileListOL";
    	frm.view_gbn.value = "download";
		
    	getClmsFilePageCheck('frm');	  */
	    
	 	// 의뢰 - 단가
	    
	 	// 의뢰 - 사전검토 파일
	    frm.target          = "fileListBf";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01201";
	    frm.file_smlclsfcn.value = "F0120101";
		frm.ref_key.value = frm.cntrt_id.value;
		frm.view_gbn.value = "download";
	    frm.fileInfoName.value = "fileInfosBf";
	    frm.fileFrameName.value = "fileListBf";
	    getClmsFilePageCheck('frm');
	    

	    // inner_d_view 추가
	    if(<%=StringUtil.isNull(StringUtil.convertHtmlTochars((String)lomRq.get("cntrt_untprc_expl")))%> == false) {
		  	  //계약관련 #3 단가
		  	    frm.target          = "fileListUnit";
		  	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		  	    frm.method.value    = "forwardClmsFilePage";
		  	    frm.file_bigclsfcn.value = "F012";
		  	    frm.file_midclsfcn.value = "F01202";
		  	    frm.file_smlclsfcn.value = "F0120211";
		  		frm.ref_key.value = unprc_file_key;  //단가의 경우 계약키로 변경 
		  		frm.view_gbn.value = "download";
		  	    frm.fileInfoName.value = "fileInfosUnit";
		  	    frm.fileFrameName.value = "fileListUnit";
		  	    getClmsFilePageCheck('frm');
		}
	    
		// 최종본이 아닐경우 첨부파일을 Display
		if("<c:out value='${lomRq.plndbn_req_yn}' />" == "N"){
			$('tr[name="tr_attached_file"]').show();
		}
		
		if("Y" == $('#plndbn_req_yn').val()){
			$("#tab_contents_sub_conts4").show();
			$("#tab_contents_sub_conts5").show();
			$("#tab_contents_sub_conts6").show();
		}		
		
		// document.getElementById('tr_down03').style.display = "block";

		// inner_d 추가
 		if($("#top_role").val() == "HQ01"){ // ADMIN인 경우 
				 
				$("#td_loac").html("<c:out value='${lomRq.loac_nm}' escapeXml='false'/>");
				// $("#td_dspt_resolt_mthd").html("<c:out value='${lomRq.dspt_resolt_mthd_nm}' escapeXml='false'/>");  // 분쟁해결 방법
				
				//연관계약 표시 편집 
				$("#trRelationContract").remove();
				$("#trRelationContractMsg").hide();			
				$('span[id=id_relCImg]').remove();  //삭제 이미지 없애기
				
				// 2012.02.14 부서검토_검토상태가 작성중일 경우 검토의견과 첨부파일을 VIEW모드로 전환 modified by hanjihoon
/* 				if($("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && "Y" == $("#mn_respman_apnt_yn").val() && ($("#mn_cnsd_status").val() == "C04302" || $("#mn_cnsd_status").val() == "C04303" || $("#mn_cnsd_status").val() == "C04305" || $("#vc_cnsd_status").val() == "C04302" || $("#vc_cnsd_status").val() == "C04303" || $("#vc_cnsd_status").val() == "C04305") ){
					$("#tr_down03").attr("style","display:");
				} */
		}	
	

	    // 법인 검토 의견 첨부 파일 부분 START 
		// 회신_계약서
/* 	    frm.target          = "fileListReContract";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120202";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "N";
	    frm.fileInfoName.value = "fileInfosReContract";
	    frm.fileFrameName.value = "fileListReContract";
		frm.view_gbn.value  = "download";
		getClmsFilePageCheck('frm');
		
		// 회신_첨부/별첨
	    frm.target          = "fileListReEtc";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120209";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfosReEtc";
	    frm.fileFrameName.value = "fileListReEtc";
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm');
		
		// 회신_기타
	    frm.target          = "fileListReGita";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120206";
	    frm.ref_key.value = file_key;
	    frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfosReGita";
	    frm.fileFrameName.value = "fileListReGita";
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm'); */
	    // 법인 검토 의견 첨부 파일 부분 END 

	    // 병행 검토 요청 첨부 파일 부분 START 
	   	//CNTRT_ID + '@' + HQ_CNSDREQ_ID+CNSDREQ_LEVEL
	    var file_key_hq_req = frm.cntrt_id.value +"@"+ frm.hq_cnsdreq_id.value + "_" + frm.cnsd_level_req.value;
	   	
 	    frm.target          = "file_hq_cnsd_demnd_cont";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "H010";
	    frm.file_midclsfcn.value = "H01001";
	    frm.file_smlclsfcn.value = "H0100101";
		frm.ref_key.value = file_key_hq_req;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfosHQReq";
	    frm.fileFrameName.value = "file_hq_cnsd_demnd_cont";
    	frm.view_gbn.value  = "download";
    	getClmsFilePageCheck('frm');

	    var file_key_hq = frm.cntrt_id.value +"@"+ frm.hq_cnsdreq_id.value + "_" + frm.cnsd_level.value;
	    
	    <c:if test="${( lomHq.hq_cnsd_status eq 'C16203' ) || ( lomHq.hq_cnsd_status eq 'C16204' ) || ( lomHq.hq_cnsd_status eq 'C16205' ) || ( lomHq.hq_cnsd_status eq 'C16206' ) || ( lomHq.hq_cnsd_status eq 'C16207' )  }">
		
		<c:choose>
			<c:when test="${lomHq.HQ_REL_PRO == 'RP010'}">			
		
		 	    frm.target          = "hq_ce_cnsd_opnn_file";
			    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
			    frm.method.value    = "forwardClmsFilePage";
			    frm.file_bigclsfcn.value = "H020";
			    frm.file_midclsfcn.value = "H02001";
			    frm.file_smlclsfcn.value = "H0200101";
				frm.ref_key.value = file_key_hq;
				frm.multiYn.value = "Y";
			    frm.fileInfoName.value = "fileInfosCE";
			    frm.fileFrameName.value = "hq_ce_cnsd_opnn_file";

				<c:if test="${lomHq.EDIT_VIEW eq 'DOWNLOAD'  }">
			    	frm.view_gbn.value  = "download";
			    	getClmsFilePageCheck('frm');
				</c:if>

				<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
					frm.view_gbn.value = "modify";
					frm.submit();
				</c:if>
		
			</c:when>
			
			<c:when test="${lomHq.HQ_REL_PRO == 'RP020'}">			
			
		 	    frm.target          = "hq_im_cnsd_opnn_file";
			    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
			    frm.method.value    = "forwardClmsFilePage";
			    frm.file_bigclsfcn.value = "H020";
			    frm.file_midclsfcn.value = "H02001";
			    frm.file_smlclsfcn.value = "H0200102";
				frm.ref_key.value = file_key_hq;
				frm.multiYn.value = "Y";
			    frm.fileInfoName.value = "fileInfosIM";
			    frm.fileFrameName.value = "hq_im_cnsd_opnn_file";
	
				<c:if test="${lomHq.EDIT_VIEW eq 'DOWNLOAD' }">
			    	frm.view_gbn.value  = "download";
			    	getClmsFilePageCheck('frm');
				</c:if>
	
				<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
					frm.view_gbn.value = "modify";
					frm.submit();
				</c:if>
	
			</c:when>
			
			<c:when test="${lomHq.HQ_REL_PRO == 'RP030'}">	
			
		 	    frm.target          = "hq_ce_cnsd_opnn_file";
			    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
			    frm.method.value    = "forwardClmsFilePage";
			    frm.file_bigclsfcn.value = "H020";
			    frm.file_midclsfcn.value = "H02001";
			    frm.file_smlclsfcn.value = "H0200101";
				frm.ref_key.value = file_key_hq;
				frm.multiYn.value = "Y";
			    frm.fileInfoName.value = "fileInfosCE";
			    frm.fileFrameName.value = "hq_ce_cnsd_opnn_file";
	
				<c:if test="${lomHq.EDIT_VIEW eq 'DOWNLOAD' }">
			    	frm.view_gbn.value  = "download";
			    	getClmsFilePageCheck('frm');
				</c:if>
	
				<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
					frm.view_gbn.value = "modify";
					frm.submit();
				</c:if>
			
		 	    frm.target          = "hq_im_cnsd_opnn_file";
			    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
			    frm.method.value    = "forwardClmsFilePage";
			    frm.file_bigclsfcn.value = "H020";
			    frm.file_midclsfcn.value = "H02001";
			    frm.file_smlclsfcn.value = "H0200102";
				frm.ref_key.value = file_key_hq;
				frm.multiYn.value = "Y";
			    frm.fileInfoName.value = "fileInfosIM";
			    frm.fileFrameName.value = "hq_im_cnsd_opnn_file";
	
				<c:if test="${lomHq.EDIT_VIEW eq 'DOWNLOAD' }">
			    	frm.view_gbn.value  = "download";
			    	getClmsFilePageCheck('frm');
				</c:if>
	
				<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
					frm.view_gbn.value = "modify";
					frm.submit();
				</c:if>

			</c:when>
			
			<c:when test="${lomHq.HQ_REL_PRO == 'RP040'}">			
			
		 	    frm.target          = "hq_not_cnsd_opnn_file";
			    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
			    frm.method.value    = "forwardClmsFilePage";
			    frm.file_bigclsfcn.value = "H020";
			    frm.file_midclsfcn.value = "H02001";
			    frm.file_smlclsfcn.value = "H0200103";
				frm.ref_key.value = file_key_hq;
				frm.multiYn.value = "Y";
			    frm.fileInfoName.value = "fileInfosNOT";
			    frm.fileFrameName.value = "hq_not_cnsd_opnn_file";
	
				<c:if test="${lomHq.EDIT_VIEW eq 'DOWNLOAD' }">
			    	frm.view_gbn.value  = "download";
			    	getClmsFilePageCheck('frm');
				</c:if>
	
				<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
					frm.view_gbn.value = "modify";
					frm.submit();
				</c:if>

			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
		</c:if>
	    // 병행 검토 요청 첨부 파일 부분 END	
	    	
	}
	
	/**
	* 인쇄 팝업
	*/
	function retrieveDetail(){
		var frm = document.frm;

		var options = {
			url: "<c:url value='/clm/manage/completion.do?method=detailForPop' />",
			type : "post",
			async : false,
			dataType : "html",
			success : function(data){
				frm.contents.value = data;
				openDetailPop();
			}
		}
		$("#frm").ajaxSubmit(options);
	}
	
	/**
	* 인쇄 팝업 OPEN 시 상세 내용 전송
	*/
	function openDetailPop(){
		var frm = document.frm;
		
		PopUpWindowOpen('', "1024", "768", false);		
		frm.action	= "<c:url value='/clm/manage/completion.do' />";
		frm.method.value = "forwardDetailPop";
		frm.target   = "PopUpWindow";
		frm.submit();
	}
	
	/**
	* 글자수 제한[한글 3byte]
	*/
  	function f_chk_byte(aro_name,ari_max) {   
        var ls_str     = aro_name.value;
        var li_str_len = ls_str.length;
        var li_max      = ari_max;
        var i           = 0;
        var li_byte     = 0;
        var li_len      = 0;
        var ls_one_char = "";
        var ls_str2     = "";
    
        for(i=0; i< li_str_len; i++) {
            ls_one_char = ls_str.charAt(i);
            if (escape(ls_one_char).length > 4) 
                li_byte += 3;
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
	
	function implJson(method){
		
		var frm = document.frm;
		//viewHiddenProgress(true);
		
		// alert("implJson method: " + method);
		
		var options = {
				url: "<c:url value='/clm/review/considerationHQ.do?method=" + method + "' />",
				type: "post",
				dataType: "json",
	    		success: function(responseText, statusText){
	    			viewHiddenProgress(false);
	    			
	    			if(responseText != null && responseText.length != 0) {
	    				viewDlgAlert(responseText.returnMessage);
		    			
		    			// 2012.04.20 미배정 리스트로 리턴 modified by hanjihoon
		    			if($("#cnsd_resp_div").val() == "N" && method == "confirmRespmanHQ"){
							
							$("#curPage").val('1');
							$("#srch_prgrs_status_hq").val('C16202');
							
							window.setTimeout(function() {forwardConsideration('LIST');},3000);		//계약검토 목록으로 리턴
		    			}
		    			
		    			// 그룹장의 검토작성 실행 여부 초기화
		    			$("#cnsd_resp_div").val('N');
	    			}
	    		}
		};
		
		// 회신 처리 
		if(method == "SEND"){
				// frm.body_mime_rq.value =CrossEditor1.GetBodyValue(); // 크로스에디터 적용	
				
/*   				첨부 필수 파일 체크 로직  
 
					viewHiddenProgress(true);
					fileListContract.UploadFile(function(uploadCount){
						// 최종본일 경우 계약서파일 필수로 변경
						if(uploadCount == 0 && (arg == "DISPATCH" || arg == "RESP" || arg=="NOAPPROVAL") ){
							viewHiddenProgress(false);
							alert("<spring:message code='clm.page.field.approval.makeApprovalContractInfo58'/> <spring:message code='secfw.msg.error.fileNon' />");//첨부파일은 필수입니다.
	    					return;
	    				}
						fileListEtc.UploadFile(function(uploadCount){
							fileListGita.UploadFile(function(uploadCount){
								fileListReContract.UploadFile(function(uploadCount){
		  							fileListReEtc.UploadFile(function(uploadCount){
		  								fileListReGita.UploadFile(function(uploadCount){			  								
			  	  							$("#frm").ajaxSubmit(options);
		  								});
		  							});
								});
							});
						});
					});
			*/
					viewHiddenProgress(true);				
					file_hq_cnsd_demnd_cont.UploadFile(function(uploadCount){
						$("#frm").ajaxSubmit(options);
					});
		} else {
			viewHiddenProgress(true);
	    	$("#frm").ajaxSubmit(options);
		}			
	}

	
	/**
	*  HQ Reviewer  의 리스트 박스 세팅 처리 (팝업창에서 호출 됨)
	*/
	function returnResp(respId, respNm, apbtMemo,ce_resp_yn,im_resp_yn, hq_apbt_memo  ) {
		$('#list_respman_id_hq li').remove();
		$('#list_respman_id_hq input').remove();
		
		$("#ce_resp_yn").val(ce_resp_yn);		
		$("#im_resp_yn").val(im_resp_yn);
		$("#hq_apbt_memo").val(hq_apbt_memo);
		
	    for(var i=0; i<respId.length; i++){
	    	$('#list_respman_id_hq').append("<li id='" + respId[i] + "'>" + respNm[i] + "</li><input type='hidden' name='list_respmanHQ_ids' id='" + 'id_' + respId[i] + "' value='" + respId[i] + "'></input>");
	    }
	    
		// 스타일 및 클래스 지정
		$('#list_respman_id_hq li').bind('click', function(){
			$('#list_respman_id_hq li').removeAttr("style", "");
			$('#list_respman_id_hq li').removeClass('selected');
			
			$(this).attr("style", "background: #dddddd;");
			$(this).addClass("selected");		
		});
		
		$('#'+respId[i]).click();
		
		$("#apbt_memo").val(apbtMemo);
	}

	/**
	* 담당자 지정
	*/
	function confirmRespmanHQ(mainManId, param1, param2) {		
		var method = "confirmRespmanHQ";
		if(param1 != "Y"){
			param1 = "N";
		}
	
		$("#main_man_id").val(mainManId);		
		implJson(method);
	}
	
	/**
	* 담당자 의견 반려 처리
	*/
	function rejctOpnn() {
		if(($("#top_role").val() == "RA01" || $("#top_role").val() == "RM01") && $("#rejct_opnn").val() == ""){
			alert("<spring:message code='las.page.msg.contractmanager.consideration_info.input_rejct_reason' />");
			return;
		}
		
		var msg = "<spring:message code='las.msg.ask.reject' />";//반려하시겠습니까?
		var method = "rejctOpnn";
		
		if(confirm(msg) && validateTempSave(frm)){
			implJson(method);
		}
	}
	
	/**
	* 배정 팝업
	*/
	function popRespmanHQ(){
		var frm = document.frm;
		
		PopUpWindowOpen('', 800, 570, true);
		frm.method.value = "popRespmanHQ";
		frm.action = "<c:url value='/clm/review/considerationHQ.do' />",
		frm.target = "PopUpWindow";
		
		frm.submit();
	}


	//검토이력 나모 내용을 보여준다.
	function attachNamoView(contId){
		
		document.getElementById(contId).contentWindow.document.body.innerHTML = document.getElementById(contId+'_textarea').value;	
		document.getElementById(contId).height = document.getElementById(contId).contentWindow.document.body.scrollHeight +20+'px';	 		  // 화면에서 iframe의 높이 값을 지정함.
	}
	
	/**
	* +버튼 클릭시 첨부파일을 보여준다. (HQ 이력)
	*/
	function attachFileHQ(obj, hq_cnsdreq_id , idx, contId, hq_rel_pro, cont_type) {
		
		// alert("obj: " + obj + "/ hq_cnsdreq_id : " + hq_cnsdreq_id + " / idx: " + idx + " / contId : " + contId  + " / HQ_REL_PRO:" + hq_rel_pro)

		var attachLoading = new Array() ;
		
		// 펼쳐질때만 첨부파일을 가져온다.
		if(obj.src.indexOf("minus")>0) {
			return ;
		}
		
		var frm = document.frm;
		var fileFieldObj = null ;
		
		if(attachLoading[idx]== "Y") {
			return ;
		}		
			
		fileFieldObj = $('input[name=fileInfos_'+ idx +']') ;
		
		$fileInfos = $(fileFieldObj).attr("name");
        $fileList = $(fileFieldObj).prev().attr("name");
        $cntrt_cnsdreq_id = $(fileFieldObj).next().val();    
         
        frm.target          = $fileList;
        frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";
        frm.fileInfoName.value = $fileInfos;
        frm.fileFrameName.value = $fileList;
        frm.view_gbn.value = "download";    
		frm.ref_key.value = frm.cntrt_id.value +"@"+ idx;
        
   		// var file_key_hq = frm.cntrt_id.value +"@"+ frm.hq_cnsdreq_id.value + "_" + frm.cnsd_level.value;
   		
		if("ADMIN_REPLY" == cont_type){
   			
			return ;
   			
   		} else if("REQ" == cont_type){
   			
		    frm.file_bigclsfcn.value = "H010";
  			frm.file_midclsfcn.value = "H01001";
  			frm.file_smlclsfcn.value = "H0100101";
  		    getClmsFilePageCheck('frm');
   			
   		} else {
   	   		
   			if('RP010' == hq_rel_pro){
   	   			
   			    frm.file_bigclsfcn.value = "H020";
   			    frm.file_midclsfcn.value = "H02001";
   			    frm.file_smlclsfcn.value = "H0200101";
   		    	getClmsFilePageCheck('frm');
   	   			
   	   		} else if('RP020' == hq_rel_pro){
   	   			
   			    frm.file_bigclsfcn.value = "H020";
   			    frm.file_midclsfcn.value = "H02001";
   			    frm.file_smlclsfcn.value = "H0200102";
   		    	getClmsFilePageCheck('frm');
   	   			
   	   		} else if('RP030' == hq_rel_pro){
   	   			
   			    frm.file_bigclsfcn.value = "H020";
   			    frm.file_midclsfcn.value = "H02001";
   			    frm.file_smlclsfcn.value = "H0200101";
   		    	getClmsFilePageCheck('frm');
   		    	
   		        frm.target          = $fileList + "_2";  		        
   		        frm.fileFrameName.value = $fileList + "_2";

   			    frm.file_bigclsfcn.value = "H020";
   			    frm.file_midclsfcn.value = "H02001";
   			    frm.file_smlclsfcn.value = "H0200102";
   		    	getClmsFilePageCheck('frm');
   	   			
   	   		} else if('RP040' == hq_rel_pro){
   	   			
   			    frm.file_bigclsfcn.value = "H020";
   			    frm.file_midclsfcn.value = "H02001";
   			    frm.file_smlclsfcn.value = "H0200103";
   		    	getClmsFilePageCheck('frm');
   	   			
   	   		}	   			
   		}
		
		attachLoading[idx] = "Y" ;		
	}
	
	
	//=================================
	// history +,- 버튼 클릭
	//=================================
	function attachFile(obj, flag, cnsdreq_id, idx, contId) {

		var attachLoading = new Array() ;
		
		// 펼쳐질때만 첨부파일을 가져온다.
		if(obj.src.indexOf("minus")>0) {
			return ;
		}

		//검토이력 나모 내용을 보여준다.
		if(contId != ""){
			if(flag!="CONSULT") {
				setTimeout("attachNamoView('"+contId+"')", 1000*0.5);
			}
		}
		
		var frm = document.frm;
		var fileFieldObj = null ;
		
		if(attachLoading[flag + idx]== "Y") {
			return ;
		}
		
		//alert(obj+","+flag+","+cnsdreq_id+","+idx);
		if(flag=="CONSULT") {
			
			// 의뢰 - 계약서
			fileFieldObj = $('input[name=fileInfos_cntrt'+ idx +']') ;
			
			$fileInfos_cntrt = $(fileFieldObj).attr("name");
	        $fileList_cntrt = $(fileFieldObj).prev().attr("name");
	        $cntrt_cnsdreq_id = $(fileFieldObj).next().val();
	        
	        frm.target          = $fileList_cntrt;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_cntrt;
            frm.fileFrameName.value = $fileList_cntrt;
            frm.view_gbn.value = "download";
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120201";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$cntrt_cnsdreq_id;
            getClmsFilePageCheck('frm');
            
            // 의뢰 - 첨부/별첨
            fileFieldObj = $('input[name=fileInfos_append'+ idx +']') ;
            
            $fileInfos_append = $(fileFieldObj).attr("name");
            $fileList_append = $(fileFieldObj).prev().attr("name");
            $append_cnsdreq_id = $(fileFieldObj).next().val();
            
            frm.target          = $fileList_append;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_append;
            frm.fileFrameName.value = $fileList_append;
            frm.view_gbn.value = "download";
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120208";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$append_cnsdreq_id;
            getClmsFilePageCheck('frm');	        
            
            // 의뢰 - 기타
            fileFieldObj = $('input[name=fileInfos_other'+ idx +']') ;
            
            $fileInfos_other = $(fileFieldObj).attr("name");
            $fileList_other = $(fileFieldObj).prev().attr("name");
            $other_cnsdreq_id = $(fileFieldObj).next().val();
            
            frm.target          = $fileList_other;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_other;
            frm.fileFrameName.value = $fileList_other;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120205";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$other_cnsdreq_id;
            getClmsFilePageCheck('frm');
            
            // 의뢰 - outlook
/*             fileFieldObj = $('input[name=fileInfos_ol'+ idx +']') ;
            
            $fileInfos_ol = $(fileFieldObj).attr("name");
            $fileList_ol = $(fileFieldObj).prev().attr("name");
            $ol_cnsdreq_id = $(fileFieldObj).next().val();
            
            frm.target          = $fileList_ol;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_ol;
            frm.fileFrameName.value = $fileList_ol;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "O001";
            frm.file_midclsfcn.value = "O00101";
            frm.file_smlclsfcn.value = "O00111";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$other_cnsdreq_id;
            getClmsFilePageCheck('frm'); */
		    
		} else if(flag=="REPLY__" || flag=="REPLYREJECT__") {
            //회신 - 정_계약서
            fileFieldObj = $('input[name=reply_fileInfos_cntrt'+ idx +']') ;
            
            $reply_fileInfos_cntrt = $(fileFieldObj).attr("name");
            $reply_fileList_cntrt = $(fileFieldObj).prev().attr("name");
            $reply_cntrt_cnsdreq_id = $(fileFieldObj).next().val();
             
            frm.target          = $reply_fileList_cntrt;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $reply_fileInfos_cntrt;
            frm.fileFrameName.value = $reply_fileList_cntrt;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120202";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$reply_cntrt_cnsdreq_id;
            getClmsFilePageCheck('frm');
            
            // 회신 - 정_첨부/별첨
            fileFieldObj = $('input[name=reply_fileInfos_append'+ idx +']') ;
            $reply_fileInfos_append = $(fileFieldObj).attr("name");
            $reply_fileList_append = $(fileFieldObj).prev().attr("name");
            $reply_append_cnsdreq_id = $(fileFieldObj).next().val();
             
            frm.target          = $reply_fileList_append;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $reply_fileInfos_append;
            frm.fileFrameName.value = $reply_fileList_append;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120209";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$reply_append_cnsdreq_id;
            getClmsFilePageCheck('frm');
            
            // 회신 - 정_기타
           	fileFieldObj = $('input[name=reply_fileInfos_other'+ idx +']') ;
           	
           	$reply_fileInfos_other = $(fileFieldObj).attr("name");
            $reply_fileList_other = $(fileFieldObj).prev().attr("name");
            $reply_other_cnsdreq_id = $(fileFieldObj).next().val();
            
            frm.target          = $reply_fileList_other;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $reply_fileInfos_other;
            frm.fileFrameName.value = $reply_fileList_other;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120206";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$reply_other_cnsdreq_id;
            getClmsFilePageCheck('frm');
           
/*              // 회신 - 부_계약서
            fileFieldObj = $('input[name=dept_reply_fileInfos_cntrt'+ idx +']') ;
           	
           	$dept_reply_fileInfos_cntrt = $(fileFieldObj).attr("name");
            $dept_reply_fileList_cntrt = $(fileFieldObj).prev().attr("name");
            $dept_reply_cntrt_cnsdreq_id = $(fileFieldObj).next().val();
            $dept_reply_cntrt_cnsd_dept = $(fileFieldObj).next().next().val();           
            
            frm.target          = $dept_reply_fileList_cntrt;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $dept_reply_fileInfos_cntrt;
            frm.fileFrameName.value = $dept_reply_fileList_cntrt;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120203";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$dept_reply_cntrt_cnsdreq_id+"@"+$dept_reply_cntrt_cnsd_dept;
            getClmsFilePageCheck('frm');
        
            // 회신 - 부_첨부/별첨
            fileFieldObj = $('input[name=dept_reply_fileInfos_append'+ idx +']') ;
            
            $dept_reply_fileInfos_append = $(fileFieldObj).attr("name");
            $dept_reply_fileList_append = $(fileFieldObj).prev().attr("name");
            $dept_reply_append_cnsdreq_id = $(fileFieldObj).next().val();
            $dept_reply_append_cnsd_dept = $(fileFieldObj).next().next().val();
            
            frm.target          = $dept_reply_fileList_append;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $dept_reply_fileInfos_append;
            frm.fileFrameName.value = $dept_reply_fileList_append;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120210";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$dept_reply_append_cnsdreq_id+"@"+$dept_reply_append_cnsd_dept;
            getClmsFilePageCheck('frm');
            
            // 회신 - 부_기타
            fileFieldObj = $('input[name=dept_reply_fileInfos_other'+ idx +']') ;
            
            $dept_reply_fileInfos_other = $(fileFieldObj).attr("name");
            $dept_reply_fileList_other = $(fileFieldObj).prev().attr("name");
            $dept_reply_other_cnsdreq_id = $(fileFieldObj).next().val();
            $dept_reply_other_cnsd_dept = $(fileFieldObj).next().next().val();
            
            frm.target          = $dept_reply_fileList_other;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $dept_reply_fileInfos_other;
            frm.fileFrameName.value = $dept_reply_fileList_other;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120207";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$dept_reply_other_cnsdreq_id+"@"+$dept_reply_other_cnsd_dept;
            getClmsFilePageCheck('frm');  */
            
        }
	    //승인-파일
		else if (flag=="APPROVE"){ 
        	fileFieldObj = $('input[name=approve_fileInfos'+ idx +']') ;
         
            $approve_fileInfos = $(fileFieldObj).attr("name");
            $approve_fileList = $(fileFieldObj).prev().attr("name");
            $approve_cnsdreq_id = $(fileFieldObj).next().val();
            
            if($approve_fileInfos==null) {
            	return ;
            }
            frm.target          = $approve_fileList;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $approve_fileInfos;
            frm.fileFrameName.value = $approve_fileList;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01203";
            frm.file_smlclsfcn.value = "";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>";
            getClmsFilePageCheck('frm');
		}
		//사전검토정보-파일
		else if(flag=="INFO"){
			fileFieldObj = $('input[name=info_fileInfos]') ;
         
            $info_fileInfos = $(fileFieldObj).attr("name");
            $info_fileList = $(fileFieldObj).prev().attr("name");
            
            if($info_fileInfos==null) {
            	return ;
            }
            
            frm.target          = $info_fileList;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $info_fileInfos;
            frm.fileFrameName.value = $info_fileList;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01201";
            frm.file_smlclsfcn.value = "F0120101";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>";
            getClmsFilePageCheck('frm');
        }
		
		attachLoading[flag + idx] = "Y" ;
		
	}
	
	//innder_d 에서 추가
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
			$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");//* 준거법상세
			$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />");//* 준거법상세
			$("#loac_etc").attr("required","");
		}else{						//그외
			$("#th_loac_etc").html("<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");//준거법상세
			$("#loac_etc").attr("alt","<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />");//준거법상세
			$("#loac_etc").removeAttr("required");
		}
	}
		
	/**
	* Admin Return Popup
	*/
	function popRTN(mode){	
		
		var frm = document.frm;

		frm.rtnMode.value = mode;		
		PopUpWindowOpen('', 600, 170, true);
		frm.method.value = "popRTN";
		frm.action = "<c:url value='/clm/review/considerationHQ.do' />",
		frm.target = "PopUpWindow";
		
		frm.submit();
	}
	
	/**
	*  Admin Return Popup 저장시 버튼 감추기 
	*/
	function btnAction(flag){
		if(flag=='f'){
			$('#btn_up15').hide();
			$('#btn_down15').hide();
		} else {
			$('#btn_up15').show();
			$('#btn_down15').show();
		}
	}
		
	/*
	 * 참조자 관리 팝업
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
                
        PopUpWindowOpen('', "530", "480", false);
        frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
        frm.method.value="forwardChooseClientPopup";
        frm.target = "PopUpWindow";
        frm.submit();
     }
	
	/**
	 * 참조자 검색팝업에서  리턴 객체 받아서 AJAX 저장 처리  
	 */
	 function setListClientInfo(returnValue) {
         
        var arrReturn = returnValue.split("!@#$");
        var innerHtml ="";	
        
        $('#id_trgtman_nm').html("");
        
        if(arrReturn[0]=="") {
        	return ;
        }
        
        for(var i=0; i < arrReturn.length;i++) {
        	var arrInfo = arrReturn[i].split("|");
        	//$('#reqman_nm').append("<option value=" + arrInfo[2]+">" + arrInfo[2]+ "</option>");
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
        
        // 여기 부터 AJAX 로 실시간 DB 저장 처리   메소드 명 modifyRefCCAJAX
        var options = {   
					url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val == '1'){
									// alert(msg_comp); // 처리하였습니다.
								}
							});						
						}						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);
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
	
	/**
	 * POPUP OPEN (공통으로 사용되고 있음)
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
	     //var Feture = "fullscreen=no,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
		 PopUpWindow = window.open(surl, "PopUpWindow" , Feture);
		 PopUpWindow.focus();
	 }
	
		// TOP 30 팝업
		function openTop30Customer(){			 
			
			   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
		}
		
		/************************************************
		 * function : 연관계약 상세 페이지로 이동
		 * 	: 각화면에서 연관계약를 팝업으로 띄운다.
		 * variable 
		 * 	- strobj  : CNSDREQ_ID : 의뢰번호 
		 * example	: 
		*************************************************/
		function goReDetail(cntrt_id){

			PopUpWindowOpen('/clm/manage/completion.do?method=listContract&menu_id=20130319155624549_0000000374&conListGu=Z1000&cntrt_id='+cntrt_id, 1000, 600, true,"detailPopUp");
		}

</script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		
		<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdreq" /> HQ</h3><!-- 계약검토 -->
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
			<!-- content_in -->	
			<div id="content_in">				
				
					<form:form name="frm" id='frm' method="post" autocomplete="off">
					<input type="hidden" name="method" 			id="method" 		value="" />
					<input type="hidden" name="gbn_last" 		id="gbn_last" 		value="N" />
					<input type="hidden" name="menu_id" 		id="menu_id" 		value="<c:out value='${command.menu_id}'/>" />
					<input type="hidden" name="curPage" 		id="curPage" 		value="<c:out value='${command.curPage}'/>" />
					<input type="hidden" name="status" 			id="status" 		value="" />
					<input type="hidden" name="tab_cnt" 		id="tab_cnt" 		value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
					<input type="hidden" name="cnsdreq_id" 		id="cnsdreq_id" 	value="<c:out value='${lomRq.cnsdreq_id}'/>" />		 
					<input type="hidden" name="req_dept" 		id="req_dept" 		value="<c:out value='${lomRq.req_dept}'/>" />
					<input type="hidden" name="req_operdiv" 	id="req_operdiv" 	value="<c:out value='${lomRq.req_operdiv}'/>" />
					<input type="hidden" name="cntrt_id" 		id="cntrt_id"  		value="<c:out value='${lomRq.cntrt_id}'/>" />		
					<input type="hidden" name="prev_cnsdreq_id" id="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
					<input type="hidden" name="stat_flag" 		id="stat_flag" 		value="<c:out value='${command.stat_flag}'/>" />
					<input type="hidden" name="page_flag" 		id="page_flag" 		value="<c:out value='${command.page_flag}'/>" />
					<input type="hidden" name="dmstfrgn" 		id="dmstfrgn" 		value="<c:out value='${command.dmstfrgn}'/>" />
					<input type="hidden" name="req_title" 		id="req_title" 		value="<c:out value='${lomRq.req_title}'/>" />
					<input type="hidden" name="cntrt_oppnt_cd" 	id="cntrt_oppnt_cd" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>" />
					<input type="hidden" name="cntrt_oppnt_nm" 	id="cntrt_oppnt_nm" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" />
					<input type="hidden" name="submit_status" 	id="submit_status" />	
					<input type="hidden" name="page_div" 		id="page_div" 		value="<c:out value='${command.page_div}'/>" />
					
					<!-- 첨부파일정보 -->
					<input type="hidden" name="fileInfosContract" 		id="fileInfosContract"    	value="" /> <!-- 의뢰 - 계약서 파일 -->
					<input type="hidden" name="fileInfosEtc" 			id="fileInfosEtc"    		value="" /> <!-- 의뢰 - 첨부/별첨 파일 -->
					<input type="hidden" name="fileInfosGita" 			id="fileInfosGita"    		value="" /> <!-- 의뢰 - 기타 파일 -->
					<input type="hidden" name="fileInfoOL" 			id="fileInfoOL"    		value="" /> <!-- 의뢰 - 아웃룩 파일 -->
					
					<input type="hidden" name="fileInfosReContract" 	id="fileInfosReContract"    value="" /> <!-- 회신 - 계약서 파일 -->	
					<input type="hidden" name="fileInfosReEtc" 			id="fileInfosReEtc"    		value="" /> <!-- 회신 - 첨부/별첨 파일 -->
					<input type="hidden" name="fileInfosReGita" 		id="fileInfosReGita"    	value="" /> <!-- 회신 - 기타 파일 -->
					<input type="hidden" name="fileInfosReVcContract" 	id="fileInfosReVcContract"	value="" /> <!-- 회신 - 계약서 파일 -->	
					<input type="hidden" name="fileInfosReVcEtc" 		id="fileInfosReVcEtc"    	value="" /> <!-- 회신 - 첨부/별첨 파일 -->
					<input type="hidden" name="fileInfosReVcGita" 		id="fileInfosReVcGita"    	value="" /> <!-- 회신 - 기타 파일 -->
					
					<input type="hidden" name="fileInfos" 		id="fileInfos"    	value="" /> <!-- -->
					<input type="hidden" name="fileInfoName"	  id="fileInfoName"	 value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
					<input type="hidden" name="fileFrameName"  id="fileFrameName"   value="" /> <!-- 첨부파일 화면 iFrame 명 -->
						
					<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
					<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
					<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
					<input type="hidden" name="ref_key"     	value="<c:out value='${lomHq.hq_cnsdreq_id}'/>" /> <!-- 키값 -->	
					<input type="hidden" name="view_gbn"    	value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->		
										
					
					<input type="hidden" name="fileInfosHQReq" 		id="fileInfosHQReq"    	value="" /> <!-- HQREQ 용 첨부-->
					<input type="hidden" name="fileInfosCE" 		id="fileInfosCE"    	value="" /> <!-- CE 용 첨부-->
					<input type="hidden" name="fileInfosIM" 		id="fileInfosIM"    	value="" /> <!-- IM 용 첨부 -->
					<input type="hidden" name="fileInfosNOT" 		id="fileInfosNOT"    	value="" /> <!-- NOT 용 첨부 -->
					
					<input type="hidden" name="blngt_orgnz" 	id="blngt_orgnz" 	value="<c:out value='${command.blngt_orgnz}'/>" />	<!-- 소속조직 -->
					<input type="hidden" name="top_role" 		id="top_role" 		value="<c:out value='${command.top_role}'/>" />		<!-- ROLL -->	
					<input type="hidden" name="cntrt_srch_yn" 	id="cntrt_srch_yn" 	value="<c:out value='${command.cntrt_srch_yn}'/>" /><!-- 팝업검색여부 -->
					<input type="hidden" name="file_yn" 		id="file_yn" 		value="" />
					<input type="hidden" name="multiYn" 		value="" /><!-- 멀티 -->
					<input type="hidden" name="plndbn_req_yn" 	id="plndbn_req_yn" 	value="<c:out value="${lomRq.plndbn_req_yn}" />" /><!-- 최종본 의뢰 여부  -->
					<input type="hidden" name="attach_file_div" id="attach_file_div" value="Y" />
					<input type="hidden" name="save_cntrt_id" 	id="save_cntrt_id" 	value="<c:out value='${lomRq.cntrt_id}'/>" />
					<input type="hidden" name="cnsd_resp_div" 	id="cnsd_resp_div" 	value="N" /><!-- 그룹장의 검토작성 실행 여부  -->
					<input type="hidden" name="main_man_id" 	id="main_man_id" 	value="" />	<!-- 정_검토담당자  -->
					<!-- 첨부파일정보 END -->
					
					<!-- 검색조건 유지 START -->
					<input type="hidden" name="srch_type_cd" 		value="<c:out value='${command.srch_type_cd}' escapeXml='false'/>" />
					<input type="hidden" name="srch_req_title" 		value="<c:out value='${command.srch_req_title}' escapeXml='false'/>" />
					<input type="hidden" name="srch_orgnz" 			value="<c:out value='${command.srch_orgnz}' escapeXml='false'/>" />
					<input type="hidden" name="srch_start_dt" 		value="<c:out value='${command.srch_start_dt}' escapeXml='false'/>" />
					<input type="hidden" name="srch_end_dt" 		value="<c:out value='${command.srch_end_dt}' escapeXml='false'/>" />
					<input type="hidden" name="srch_reqman_nm" 		value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />
					<input type="hidden" name="srch_prgrs_status" 	value="<c:out value='${command.srch_prgrs_status}' escapeXml='false'/>" />
					<input type="hidden" name="srch_owner_dept" 	value="<c:out value='${command.srch_owner_dept}' escapeXml='false'/>" />
					<input type="hidden" name="srch_law_status" 	id="srch_law_status" value="<c:out value='${command.srch_law_status}' escapeXml='false'/>" />
					<input type="hidden" name="srch_ip_status" 		id="srch_ip_status" value="<c:out value='${command.srch_ip_status}' escapeXml='false'/>" />
					<input type="hidden" name="srch_respman_nm" 	value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />
					<input type="hidden" name="srch_biz_depth" 		value="<c:out value='${command.srch_biz_depth}' escapeXml='false'/>" />
					<input type="hidden" name="srch_cnclsnpurps" 	value="<c:out value='${command.srch_cnclsnpurps}' escapeXml='false'/>" />
					<input type="hidden" name="srch_cntrt_oppnt_nm" value="<c:out value='${command.srch_cntrt_oppnt_nm}' escapeXml='false'/>" />
					<input type="hidden" name="srch_cnsd_cont" 		value="<c:out value='${command.srch_cnsd_cont}' escapeXml='false'/>" />
					<input type="hidden" name="srch_if_sys_cd" 		value="<c:out value='${command.srch_if_sys_cd}' escapeXml='false'/>" />
					<input type="hidden" name="srch_hq_reqman_nm"  id="srch_hq_reqman_nm"  value="<c:out value='${command.srch_hq_reqman_nm}' escapeXml='false'/>" />	<!-- HQ 검토자  -->
					<input type="hidden" name="srch_prgrs_status_hq"  id="srch_prgrs_status_hq"  value="<c:out value='${command.srch_prgrs_status_hq}' escapeXml='false'/>" />	<!-- HQ 검토상태 -->
					<input type="hidden" name="contents" value=""/>
					<!-- 검색조건 유지 END -->
					
					<input type="hidden" name="cnclsnpurps_bigclsfcn" 	id="cnclsnpurps_bigclsfcn" 	value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적_대분류 -->
					<input type="hidden" name="prgrs_status" 			id="prgrs_status" 			value="<c:out value='${lomRq.prgrs_status}'/>" />			<!-- 검토의뢰_진행상태 -->
					<input type="hidden" name="mn_cnsd_dept" 			id="mn_cnsd_dept" 			value="<c:out value='${lomRq.mn_cnsd_dept}'/>" />			<!-- 검토의뢰_정_검토_부서 -->
					<input type="hidden" name="mn_respman_apnt_yn" 		id="mn_respman_apnt_yn" 	value="<c:out value='${lomRq.mn_respman_apnt_yn}'/>" />		<!-- 검토의뢰_정_담당자_지정_여부 -->
					<input type="hidden" name="vc_cnsd_dept" 			id="vc_cnsd_dept" 			value="<c:out value='${lomRq.vc_cnsd_dept}'/>" />			<!-- 검토의뢰_부_검토_부서 -->
					<input type="hidden" name="vc_respman_apnt_yn" 		id="vc_respman_apnt_yn" 	value="<c:out value='${lomRq.vc_respman_apnt_yn}'/>" />		<!-- 검토의뢰_부_담당자_지정_여부 -->
					<input type="hidden" name="table_div" 				id="table_div" 				value="<c:out value='${lomDcd.cnsd_status}' />" />													<!-- 테이블 기준 -->
					<input type="hidden" name="respman_apnt_yn" 		id="respman_apnt_yn" 		value="<c:out value='${command.respman_apnt_yn}' />" />												<!-- 담당자 지정 여부 -->
					<input type="hidden" name="vc_cnsd_demnd_cont" 		id="vc_cnsd_demnd_cont" 	value="" />													<!-- 검토요청내용 -->
					<input type="hidden" name="dmstfrgn_gbn"			id="dmstfrgn_gbn"			value="" />													<!-- 국내/해외 구분 -->
					<input type="hidden" name="cnsd_status" 			id="cnsd_status" 			value="<c:out value='${lomDcd.cnsd_status}' />" />
					<input type="hidden" name="mn_cnsd_status" 			id="mn_cnsd_status" 		value="<c:out value='${lomMn.cnsd_status}' />" />
					<input type="hidden" name="btn_div1"				id="btn_div1"				value="N" />												<!-- 임시저장 버튼 구분값 -->
					<input type="hidden" name="btn_div2"				id="btn_div2"				value="N" />												<!-- 의견전달 버튼 구분값 -->
					<input type="hidden" name="btn_div3"				id="btn_div3"				value="N" />												<!-- 발신 버튼 구분값 -->
					<input type="hidden" name="btn_div4"				id="btn_div4"				value="N" />												<!-- 회신 버튼 구분값 -->
					<input type="hidden" name="apbt_memo" 				id="apbt_memo" 				value="" />													<!-- 그룹장 메세지  -->
					<input type="hidden" name="auto_apbt_yn" 			id="auto_apbt_yn" 			value="Y" />		
					<input type="hidden" name="solo_yn" 				id="solo_yn" 				value="1" />	
					<input type="hidden" name="not_appr_yn" 			id="not_appr_yn" 			value="" />	
					<input type="hidden" name="con_depth_status" id="con_depth_status" value="<%=lomRq.get("depth_status") %>" />
					<input type="hidden" name="respUserDiv" id="respUserDiv" value="<%=lomRq.get("resp_user_div") %>" />
					<input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>" />
					
					
					<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />	
					
					<input type="hidden" name="hq_cnsdreq_id" id="hq_cnsdreq_id"  value="<c:out value='${lomHq.hq_cnsdreq_id}'/>" />	
					<input type="hidden" name="cnsd_level" id="cnsd_level"  value="<c:out value='${lomHq.cnsd_level}'/>" />	
					<input type="hidden" name="next_level" id="next_level"  value="<c:out value='${lomHq.next_level}'/>" />	
					<input type="hidden" name="cnsd_level_req" id="cnsd_level_req"  value="<c:out value='${lomHq.cnsd_level_req}'/>" />						
					<input type="hidden" name="hq_rel_pro" id="hq_rel_pro"  value="<c:out value='${lomHq.hq_rel_pro}'/>" />				
					
					<input type="hidden" id="rtnMode" name="rtnMode" value="'" />
					
					<!-- 관련자 데이타 설정  -->					
					<input type="hidden" name="client_modify_div" id="client_modify_div" />
					<input type="hidden" name="chose_client" id="chose_client" />
					
					<input type="hidden" name="ce_resp_yn" id="ce_resp_yn"  value="<c:out value='${lomHq.ce_resp_yn}'/>"  >		
					<input type="hidden" name="im_resp_yn" id="im_resp_yn"  value="<c:out value='${lomHq.im_resp_yn}'/>"  >	
					<input type="hidden" name="hq_apbt_memo" id="hq_apbt_memo"  value="<c:out value='${lomHq.hq_apbt_memo}'/>"  >	
					
					<c:forEach var="cntrtMt" items="${listDc}">
						<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
					</c:forEach>			
					
<%--  					 lomRq.depth_status : <c:out value='${lomRq.depth_status}'/>  / lomRq.prgrs_status :<c:out value='${lomRq.prgrs_status}'/>  / respYn: <c:out value='${respYn}' />  
					<div align="right">
					<span style="color:red ; font-size: 13pt ; align:right;" >TOPROLE  : <c:out value='${topRole}'/>  
					/ hasH0102 : <c:out value='${hasH0102}'/>  
					/ HQ_CNSD_STATUS : <c:out value='${lomHq.hq_cnsd_status}' /> 
					/ HQ_REL_PRO : <c:out value='${lomHq.hq_rel_pro}' />
					/ lomHq.EDIT_VIEW : <c:out value='${lomHq.EDIT_VIEW}' />
					/ respYnHQ : <c:out value='${respYnHQ}' />
					</span> 
					</div>
					<br>    --%>
				
					<!-- title -->
					<div class="title_basic mt0"><!-- 검토의뢰 정보 -->
						<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');" style='cursor:pointer'/></h4>
					</div>
					<!-- //title -->
					<!--View -->
					<!-- 수정/검토의뢰/최종본작성/목록 -->		
					
					<!-- button -->
					<div class="btnwrap mt_22">
					
						<c:if test="${topRole eq 'HQ01'}" >
						
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16202'}" ><!-- 미 배정 상태 일때 -->		
								<span id="btn_admin_reply"  class="btn_all_w" onclick="popRTN('ADMIN_REPLY');"><span class="mail"></span><a><spring:message code="las.filed.considetration.adminreply"/></a></span><!-- Admin Reply -->		
							</c:if>
							
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16204'}" ><!-- HQ HEAD 승인 대기 상태 일때 -->		
								<span id="btn_reject"  class="btn_all_w" onclick="popRTN('APPR');"><span class="mail"></span><a><spring:message code="las.page.button.approval"/></a></span><!-- 승인 -->
								<span id="btn_reject"  class="btn_all_w" onclick="popRTN('REJECT');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.reviewReject"/></a></span><!-- 반려 -->
							</c:if>
							
							<c:if test="${hasH0102 eq 'Y' && respYnHQ eq 'Y' }" ><!-- HQ01 권한과 HQ02 권한 둘다 가지고 있을 때 그리고 이 의뢰에 대해서 배정을 받았을 때.-->								
								<c:if test="${lomHq.hq_cnsd_status  eq 'C16203' || lomHq.hq_cnsd_status  eq 'C16206' }" ><!-- 검토중(배정완료) 상태 일때 -->		
									<span id="btn_save" class="btn_all_w" style="display:" onclick="returnConsideration('SAVE');" ><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span><!-- 임시저장 -->
									<span id="btn_send" class="btn_all_w" style="display:" onclick="returnConsideration('SEND');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.send"/></a></span><!-- 회신 -->	
								</c:if>				
							</c:if>	
						
						</c:if>
						
						<c:if test="${topRole eq 'HQ02' && respYnHQ eq 'Y' }" >		
							
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16203' || lomHq.hq_cnsd_status  eq 'C16206'}" ><!-- 검토중(배정완료) 상태 혹은 반려 상태 일때 -->		
																<span id="btn_send" class="btn_all_w" style="display:" onclick="returnConsideration('SEND');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.send"/></a></span><!-- 회신 -->	
								<span id="btn_save" class="btn_all_w" style="display:" onclick="returnConsideration('SAVE');" ><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span><!-- 임시저장 -->
							</c:if>
						
						</c:if>
						
						<span id="btn_print" class="btn_all_w"  onclick="retrieveDetail();" ><span class="print"></span><a><spring:message code="las.page.field.contractManager.print"/></a></span><!-- 인쇄 -->
						<span id="btn_list" class="btn_all_w"  onclick="forwardConsideration('LIST');" ><span class="list"></span><a><spring:message code="las.page.field.contractManager.list"/></a></span><!-- 목록 -->

					</div>
					<!-- //button -->
				
				<!-- toptable -->
				<table cellspacing="0" cellpadding="0" class="table-style01">
					<colgroup>
						<col width="15%" />
						<col width="27%" />
						<col width="13%" />
						<col width="16%" />
						<col width="13%" />
						<col width="16%" />
					</colgroup>
					<tr>
						<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /><!-- 의뢰명 --></th>
					  	<td colspan="5">
					  		<c:out value='${lomRq.req_title}' escapeXml='false'/>					  	
					  	</td>
					</tr>
					<tr class="lineAdd">
						<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th><!-- 의뢰자 -->
						<td><c:out value="${lomRq.reqman_nm}" escapeXml='false'/> / <c:out value="${lomRq.reqman_jikgup_nm}" escapeXml='false'/> / <c:out value="${lomRq.req_dept_nm}" escapeXml='false'/> <c:if test="${!empty lomRq.reqman_tel}"> / <c:out value="${lomRq.reqman_tel}" escapeXml='false'/></c:if></td>
						<th><spring:message code="las.page.field.contractmanager.consideration_d.req_dt" /></th><!-- 의뢰일시 -->
						<td><c:out value="${lomRq.req_dt}" /></td>
						<th><spring:message code="las.page.field.contractmanager.consideration_d.re_demndday" /></th><!-- 회신요청일 -->
						<td><c:out value="${lomRq.re_demndday}" /></td>
					</tr>
				</table>
				<div id="tr_down01" class="border-top-no" style="display:none;">
					<table id="tb_down01" cellspacing="0" cellpadding="0" class="table-style01">
						<colgroup>
							<col width="15%" />
							<col width="10%" />
							<col width='17%' />
							<col width="13%" />
							<col width='16%' />
							<col width="13%" />
							<col width='16%' />
						</colgroup>
						<tr class="space">
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					 	<c:if test="${!empty lomRm}"> 
							<tr class="lineAdd">
								<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.relation_man" /><!-- CC --></th>
							  	<td colspan="6">						  				  	
								  	<span id="id_trgtman_nm">						  	
									  	<c:forEach var="list" items="${lomRm}" varStatus="status">
											<c:out value='${list.relation_man}' escapeXml='false'/> 
												<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<c:out value='${status.count}' />" />
												<input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<c:out value='${list.TRGTMAN_ID}' />" />
												<input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<c:out value='${list.TRGTMAN_NM}' />" />
												<input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<c:out value='${list.TRGTMAN_JIKGUP_NM}' />" />
												<input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<c:out value='${list.TRGTMAN_DEPT_NM}' />" />
												<c:if test="${status.count > 0 && !status.last}">
													,&nbsp;&nbsp;
												</c:if>
												<c:if test="${status.count mod 2==0}">
													</br>
												</c:if>
										</c:forEach>							
									</span>				
							  	</td>
							</tr>
						</c:if>
						<tr class="lineAdd">
							<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /><!-- 검토요청내용 --></th>
							<td colspan="6"><c:out value="${lomRq.cnsd_demnd_cont}" escapeXml="false" />
							<%-- 	<textarea id="if_cnsd_demnd_cont_textarea" name="cnsd_demnd_cont_textarea" style="display: none;"><c:out value="${lomRq.cnsd_demnd_cont}" escapeXml="false" /></textarea>
								<iframe id="if_cnsd_demnd_cont" name="if_cnsd_demnd_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe> --%>
							</td>
						</tr>
						<c:if test="${!empty lomRq.vc_cnsd_demnd_cont_dp}">
							<tr class="lineAdd">
								<th><spring:message code="las.page.field.contractManager.lnkDept"/><br><spring:message code="las.page.field.contractManager.reqReason"/><!-- 검토요청 사유 --></th>
								<td colspan="4">
									<c:out value='${lomRq.vc_cnsd_demnd_cont_dp}' escapeXml="false" />
								</td>
								<th><spring:message code="las.page.field.contractManager.lnkDept"/><br><spring:message code="las.page.field.contractManager.reqDate"/><!-- 검토요청 일시 --></th>
								<td>
									<c:out value='${lomRq.req_cnsd_demnd_dt}' />
								</td>
							</tr>
						</c:if>
						
						<tr class="slide-target02 slide-area">
							<th rowspan="4"><spring:message code="las.page.field.contractManager.reqAttached"/><!-- 검토요청 첨부파일 -->
							<img src="/images/las/ko/common/step_q.gif"  id="img_step_q" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>"  />
							</th>
							<c:choose>
								<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
									<td class="blueD"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract_f" /><!-- [최종본]<br>계약서 --></span></td>
								</c:when>
								<c:otherwise>
									<td class="blueD"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /><!-- 계약서 --></span></td>
								</c:otherwise>
							</c:choose>
							<td colspan="6">
								<div id="ico_maxsize1" class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListContract" name="fileListContract"  frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true" style="height:39px"></iframe>
							</td>
						</tr> 
						<tr class="slide-target02 slide-area">
							<c:choose>
								<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
									<td class="tal_lineL"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach_f" /><!-- [최종본]<br>첨부/별첨 --></span></td>
								</c:when>
								<c:otherwise>
									<td class="tal_lineL"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /><!-- 첨부/별첨 --></span></td>
								</c:otherwise>
							</c:choose>
							<td colspan="6">
								<div id="ico_maxsize2" class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc"  frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
							</td>
						</tr>
						<tr class="slide-target02 slide-area">
							<td class="tal_lineL"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.etc" /><!-- 기타 --></span></td>

							<td colspan="6">
								<div id="ico_maxsize3"  class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListGita" name="fileListGita"  frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
							</td>
						</tr>
<%-- 						<tr class="slide-target02 slide-area">
							<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
							<td colspan="6">
								<div id="ico_maxsize4"  class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListOL" name="fileListOL"  frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
							</td>
						</tr> --%>
					</table>
				</div>
				
				<!-- //법무 시스템 - 계약검토 END -->
				
				<!-- Contract Information START-->
			    <div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /><!-- 계약 정보 --> <img id="btn_down_cntrt" src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');" style='cursor;pointer'/></h4>
				</div>
				<!-- //title -->

				<div id="tr_down02" style="display:none;">
					<div class="title_basic3"><spring:message code="las.page.field.contractManager.basicInfo"/></div>
					<table cellspacing="0" cellpadding="0" class="table-style01">
						<colgroup>
							<col width="15%" />
							<col width="9%" />
							<col width="18%" />
							<col width="13%" />
							<col width="16%" />
							<col width="13%" />
							<col width="16%" />
						</colgroup>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /><!-- 의뢰명 --></th>
							<td colspan="6"><span class="fL"> 
							<c:out value="${lomRq.req_title}" escapeXml='false'/></span></td>
						</tr>
						<tr>
							<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_nm" /><!-- 계약명 --></th>
							<td colspan="4"><span class="fL">
							<input type="hidden" name="cntrt_nm" id="cntrt_nm" value="<c:out value="${lomRq.cntrt_nm}" escapeXml='false'/>" /> 
							<c:out value="${lomRq.cntrt_nm}" escapeXml='false'/></span></td>
							<th><spring:message code="las.page.field.contractManager.contractId"/><!-- 계약ID --></th>
							<td><span class="fL"><c:out value="${lomRq.cntrt_no}" /></span></td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration.reqman_nm" /><!-- 의뢰자 --></th>
							<td colspan="2"><c:out value="${lomRq.reqman_nm}" escapeXml='false'/> / <c:out value="${lomRq.reqman_jikgup_nm}" escapeXml='false'/> / <c:out value="${lomRq.req_dept_nm}" escapeXml='false'/></td>
							<th><spring:message code="las.page.field.contractManager.personInCharge"/><!-- 담당자 --></th>
							<td><c:out value="${lomRq.cntrt_respman_nm}" escapeXml='false'/> / <c:out value="${lomRq.cntrt_respman_jikgup_nm}" escapeXml='false'/> / <c:out value="${lomRq.cntrt_resp_dept_nm}" escapeXml='false'/></td>
							<th><spring:message code="las.page.field.contractManager.reviewer"/><!-- 검토자 --></th>
							<td><c:out value="${lomRq.cnsdman_info}" /></td>
						</tr>
						<c:if test="${!empty lomRq.cntrt_oppnt_nm || !empty lomRq.cntrt_oppnt_rprsntman || !empty lomRq.cntrt_oppnt_type_nm}">
						<tr>
							<th><spring:message code="clm.page.msg.common.otherParty" /><!-- 계약상대방 --></th>
							<td colspan="2"><a href="javascript:customerPop('<c:out value="${lomRq.cntrt_oppnt_cd}" />','<c:out value="${lomRq.cntrt_oppnt_cd}" />');"><c:out value="${lomRq.cntrt_oppnt_nm}" /></a></td>

							
							<th><spring:message code="clm.page.field.customer.registerNo" /><!-- Registration No  --></th>
							<td><c:out value="${lomRq.cntrt_oppnt_rprsntman}" escapeXml='false'/></td>
		                    <th><spring:message code="clm.page.field.customer.gerpCode" /></th>
                   			<td><c:out value="${lomRq.gerp_cd}" /></td>
							
						</tr>
						</c:if>
						<c:if test="${!empty lomRq.cntrt_oppnt_respman || !empty lomRq.cntrt_oppnt_telno || !empty lomRq.cntrt_oppnt_email}">
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_oppnt_respman" /><!-- 상대방 담당자 --></th>
							<td colspan="2"><c:out value="${lomRq.cntrt_oppnt_respman}" escapeXml='false'/></td>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.tel" /><!-- 담당자 전화번호 --></th>
							<td><c:out value="${lomRq.cntrt_oppnt_telno}" escapeXml='false'/></td>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.email" /><!-- 담당자 E-mail --></th>
							<td><c:out value="${lomRq.cntrt_oppnt_email}" escapeXml='false'/></td>
						</tr>
						</c:if>
						<tr class="slide-target02 slide-area">			
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /><!-- 계약유형 --></th>				
								
							<td colspan="6">
								<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
								<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
								<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
								<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" />
							</td>				
						</tr>
						<tr class="slide-target02 slide-area">
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_nm" /><!-- 계약대상 --></th>
							<td colspan="2">
								<c:out value="${lomRq.cntrt_trgt_nm}" escapeXml='false'/>	
							</td>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_det" /><!-- 계약대상 상세 --></th>
							<td colspan='3'><c:out value="${lomRq.cntrt_trgt_det}" escapeXml='false'/></td>
						</tr>
						
						<!-- 계약 유형이 T030705 이고 T030706 일 경우에만 화면에 표시 됨. <c:out value='${lomRq.cnsl_mid_cd3}'/>-->
                        
                            <tr>
		                    <th><spring:message code="clm.page.msg.manage.top30Cus" /> <img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
		                    <td colspan="2"><c:out value="${lomRq.top_30_cus}" /></td>
		                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
		                    <td><c:out value="${lomRq.related_products}" escapeXml="false"/></td>
		                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
				            <td><c:out value="${lomRq.cont_draft}" /></td>
		                </tr>
                        
						
						
							<tr>
								<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th><!-- 계약기간  -->
								<td colspan="4">
									<%=DateUtil.formatDate((String)lomRq.get("cntrtperiod_startday"),"-")%> <c:if test="${!empty lomRq.cntrtperiod_startday}">~</c:if> <%=DateUtil.formatDate((String)lomRq.get("cntrtperiod_endday"),"-")%>
								</td>
								<th style="border-top:0px;"><spring:message code="las.page.field.contractManager.chkPayCol"/><!-- 지불/수금 구분 --></th>
								<td><c:out value="${lomRq.payment_gbn_nm}" escapeXml='false'/><input type="hidden" id="cntrt_amt" style="border:0px" value="<c:out value='${lomRq.cntrt_amt_chg}' />"/></td>
							</tr>	
				            <tr>
								<c:choose>
								<c:when test="${lomRq.cntrt_untprc_expl != ''}">
								<th><spring:message code="las.page.field.contractManager.contAmt"/><!-- 계약금액 --></th>
								<td colspan="2">
									<input type="text" id="cntrt_amt" style="border:0px" width="100%" value="<c:out value='${lomRq.cntrt_amt_chg}' />" readonly="readonly"/>
								</td>
								<th><spring:message code="las.page.field.contractManager.contCost"/><!-- 계약단가 --></th>
								<td><spring:message code="las.page.field.contractManager.signUcost"/><!-- 단가로 체결 --></td>
								<th><spring:message code="las.page.field.contractManager.currcUnt"/><!-- 통화단위 --></th>
								<td><c:out value="${lomRq.crrncy_unit}" /></td>
								</c:when>
								<c:otherwise>
								<th><spring:message code="las.page.field.contractManager.contAmt"/><!-- 계약금액 --></th>
								<td colspan="4"><input type="text" id="cntrt_amt" style="border:0px" width="100%" value="<c:out value='${lomRq.cntrt_amt_chg}' escapeXml='false'/>" readonly="readonly"/></td>
								<th><spring:message code="las.page.field.contractManager.currcUnt"/><!-- 통화단위 --></th>
								<td><c:out value="${lomRq.crrncy_unit}" escapeXml='false'/></td>
								</c:otherwise>
								</c:choose>							
							</tr>
							<c:if test='${lomRq.cntrt_untprc_expl != "" }'>
								<tr>
									<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /><!-- 단가내역 요약 --></th>
									<td colspan="6">
										<c:out value="${lomRq.cntrt_untprc_expl}" escapeXml="false"/>                                                    
									</td>
								</tr>
								<tr>
									<td colspan="6" class="tal_lineL">
										<iframe src="<c:url value='/clm/blank.do' />" id="fileListUnit" name="fileListUnit"  frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>					
									</td>
								</tr>
							</c:if>
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.pshdbkgrnd_purps" /></th><!-- 추진목적 및 배경 -->
								<td colspan="6"><c:out value="${lomRq.pshdbkgrnd_purps}" escapeXml="false" />				
							<%--     <textarea id="if_cnsd_bg_cont_textarea" name="cnsd_bg_cont_textarea" style="display: none;"><c:out value="${lomRq.pshdbkgrnd_purps}" escapeXml="false" /></textarea>
					    		<iframe id="if_cnsd_bg_cont" name="cnsd_bg_cont" src="<c:url value='/clm/blank.do' />" frameborder="0" scrolling="auto" style="width:100%"></iframe>   --%>
								</td>
							</tr>
							<c:if test="${!empty lomRq.antcptnefct_dp}">
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.antcptnefct" /></th><!-- 기대효과 -->
								<td colspan="6">
									<c:out value="${lomRq.antcptnefct_dp}" escapeXml="false"/>
									</td>
							</tr>
							</c:if>
							<c:if test="${!empty lomRq.payment_cond_dp}">
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.payment_cond" /></th><!-- 지불/수금 조건 -->
								<td colspan="6">
									<c:out value="${lomRq.payment_cond_dp}" escapeXml="false"/>
								</td>
							</tr>
							</c:if>
							<c:if test="${!empty lomRq.etc_main_cont_dp || !empty lomRq.if_sys_cd}">
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.etc_main_cont" /></th><!-- 기타주요사항 -->
								<td colspan="6">
									<c:out value="${lomRq.etc_main_cont_dp}" escapeXml='false'/>
									<c:if test="${!empty lomRq.if_sys_cd}"> [<c:out value="${lomRq.if_sys_cd}" />]</c:if>
								</td>
							</tr>
							</c:if>
							<c:if test="${lomRq.plndbn_req_yn eq 'Y'}"><!-- 최종본 일 경우에 비밀유지기간 표시 -->
							<tr>
								<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th><!-- 자동연장여부 -->
								<td colspan="6"><input type="radio" name="auto_rnew_yn" disabled='disabled' value="Y" <%if("Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %> />Yes
									<input type="radio" name="auto_rnew_yn" disabled='disabled' value="N" <%if(!"Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %>/>No
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.secretPeriod" /></th><!-- 비밀유지기간 -->
								<td id="td_secret_keepperiod" colspan="6">
									<c:out value="${lomRq.secret_keepperiod_dp}" escapeXml="false"/>
								</td>						
							</tr>
							</c:if>
						</table>
					<!-- Contract Information END-->
			
					<!--특화속성 정보 표시 START-->
					
					<div class="border-top-no">
						<!-- 특화속성 정보 표시[계약]  -->
						<table id="tab_contents_sub_conts4" cellspacing="0" cellpadding="0" border="0" class="table-style01" >
							<colgroup>
								<col width="15%" />
								<col />
							</colgroup>
							<%=resultCt.toString()%>
						</table>
					</div>
					<div class="border-top-no">
						<!-- 특화속성 정보 표시[법무]  -->
						<table id="tab_contents_sub_conts5" cellspacing="0" cellpadding="0" border="0" class="table-style01" >
							<colgroup>
								<col width="15%" />
								<col />
							</colgroup>
							<%=resultSb.toString()%>
						</table>
					</div>
					<div class="border-top-no">
						<table id="tab_contents_sub_conts6" cellspacing="0" cellpadding="0" border="0" class="table-style01" >
							<colgroup>
								<col width="15%" />
								<col width="27%"/>
								<col width="13%" />
								<col width="45%" />
							</colgroup>
							<% if("Y".equals(lomRq.get("plndbn_req_yn"))){ 	//최종본회신 %>
				
									<tr id="tr_view_oblgt_lmt" style="border-top:0px;">
										<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
										<td colspan="3">
											<c:out value='${lomRq.oblgt_lmt_dp}' escapeXml='false'/>
										</td>
									</tr>
<%-- 									<c:if test="${!empty lomRq.spcl_cond_dp}">
									<tr>
										<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.spcl_cond" /></th><!-- 기타 특약사항 -->
										<td colspan="3" id="tr_spcl_cond">
											<c:out value='${lomRq.spcl_cond_dp}' escapeXml='false'/>
										</td>
									</tr>
									</c:if> --%>
									<c:if test="${!empty lomRq.loac_nm}">
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_rq" /></th><!-- * 준거법 -->
										<td id="td_loac">
											<c:out value='${lomRq.loac_nm}' escapeXml='false'/>
										</td>
										<c:choose>
											<c:when test="${lomRq.loac=='C02211'}"><!-- 기타(자유기술) -->
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc_rq" /></th><!-- * 준거법 상세 -->
											</c:when>
											<c:otherwise>
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc" /></th><!-- 준거법 -->
											</c:otherwise>
										</c:choose>
										<td id="td_loac_etc">
											<c:out value='${lomRq.loac_etc_dp}' escapeXml="false"/>
										</td>
									</tr>
									</c:if>
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq" /></th><!-- * 분쟁해결방법-->				
										<td id="td_dspt_resolt_mthd_det" colspan="3">
											<c:out value='${lomRq.dspt_resolt_mthd_det_dp}' escapeXml="false"/>
										</td>
									</tr>											
							
						<% }%>
						</table>
			    	</div>
			    	
			    	<!--특화속성 정보 표시 END-->
					
					<!-- 사전승인정보 START-->					
					<div class="title_basic3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.before_cnsd_info" /><!-- 사전승인정보 --> </div>
					<div id="tr_down02_02">
						<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01">
							<colgroup>
								<col width="15%" />
								<col width="27%" />
								<col width="13%" />
								<col width="45%" />
							</colgroup>
							<tr>
			                    <th><spring:message code="las.page.field.contractManager.appDd"/><!-- 승인일자 --></th>
			                    <td><c:out value='${lomRq.chg_bfhdcstn_apbtday}' escapeXml='false'/></td>
			                    <th><spring:message code="las.page.field.contractManager.appWay"/><!-- 승인방식 --></th>
			                    <td><c:out value="${lomRq.bfhdcstn_apbt_mthd_nm}" escapeXml='false'/></td>
			                </tr>
			                <tr>
			                    <th><spring:message code="las.page.field.contractManager.mover"/><!-- 발의자 --></th>
			                    <td>
			                        <c:if test="${lomRq.bfhdcstn_mtnman_nm != ''}">
			                            <c:out value="${lomRq.bfhdcstn_mtnman_nm}" escapeXml='false'/>/ <c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}' escapeXml='false'/>/ <c:out value='${lomRq.bfhdcstn_mtn_dept_nm}' escapeXml='false'/>
			                        </c:if>
			                    </td>
			                    <th><spring:message code="las.page.field.contractManager.appBy"/><!-- 승인자 --></th>
			                    <td>
			                        <c:if test="${lomRq.bfhdcstn_apbtman_nm != ''}">
			                            <c:out value='${lomRq.bfhdcstn_apbtman_nm}' escapeXml='false'/>/ <c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}' escapeXml='false'/>/ <c:out value='${lomRq.bfhdcstn_apbt_dept_nm}' escapeXml='false'/>
			                        </c:if>
			                    </td>
			                </tr>
			                <tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_data" /><!-- 첨부자료 --></th>
								<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListBf" name="fileListBf"  frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
								</td>
							</tr>
						</table>
					</div>
					<!-- 사전승인정보 END -->
					
					<!-- T&C 연계 정보 START -->					
					<jsp:include page="/clm/manage/completion.do">
				      <jsp:param name="method" value="getTncLink" />
				      <jsp:param name="cntrt_id4" value='<%=lomRq.get("cntrt_id") %>'/>
				    </jsp:include>					
					<!-- T&C 연계 정보 END -->
					
					<!-- 연관계약정보 START-->					
					<div class="title_basic3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_cntrt_info" /><!-- 연관계약정보 --> </div>
						<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
							<colgroup>
								<col width="15%" />
								<col width="40%" />
								<col width="16%" />
								<col/>
							</colgroup>
							<tr>
								<th class='tC'><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_type" /><!-- 관계 --></th>
								<th class='tC'><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_cntrt" /><!-- 연관계약 --></th>
								<th class='tC'><spring:message code="las.page.field.contractmanager.consideration_inner_d.detail_define" /><!-- 세부정의 --></th>
								<th class='tC'><spring:message code="las.page.field.contractmanager.consideration_inner_d.relation_detail" /><!-- 관계상세 --></th>					
							</tr>		
							<c:choose>
								<c:when test ="${!empty relationList}">
										<c:forEach var="list" items="${relationList}" varStatus="i">
											<tr id="trRelationContractCont">
												<td><c:out value='${list.rel_type_nm}' escapeXml='false'/></td>
												<td><a href="javascript:goReDetail('<c:out value='${list.parent_cntrt_id}' />');"><c:out value='${list.relation_cntrt_nm}' escapeXml='false'/></a></td>
												<td><c:out value='${list.rel_defn}' escapeXml='false'/></td>
												<td><c:out value='${list.expl}' escapeXml='false'/></td>
											</tr>		
										</c:forEach>									
									</c:when>
									<c:otherwise>
										<tr id="trRelationContractCont"><td colspan="4" class='tC' align="center"><spring:message code="las.msg.succ.noResult" /></td></tr>
								</c:otherwise>
							</c:choose>
						</table>
	
				</div>				
				<!-- //연관계약정보 END -->
				

				<!--검토의견  VIEW START -->
<%-- 				<div id="tr_down03">
					<div class="title_basic">
						<h4><% if("Y".equals(lomRq.get("plndbn_req_yn"))){%><spring:message code="las.page.field.contractManager.fvRvCmt"/><% }else{%><spring:message code="las.page.field.contractManager.reviewComment"/><% }%> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_cnsd');" style='cursor:pointer;'/></h4>
					</div>
					<%
					String strMn = "<spring:message code='las.page.field.contractManager.genArticle'/>";//일반조항
					String strVc = "<spring:message code='las.page.field.contractManager.lnkDept'/>";//유관부서
					String strMnd = "<spring:message code='las.page.field.contractManager.lgPic'/>";//법무담당자
					String strVcd = "<spring:message code='las.page.field.contractManager.lnkDeptPic'/>";//유관부서 담당자			
					%>


					<!-- inner_d_view 검토의견 -->
					<div id="tr_down_cnsd">
						
						<table id="tab_contents_sub_conts3_1" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
							<colgroup>
								<col width="15%" />
								<col width="27%" />
								<col width="13%" />
								<col />
							</colgroup>
							<tr id="tr_space3_1" class="space">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<!-- 검토기본정보 -->				
								<div id="div_cnsd3_1" class="title_basic3" ><spring:message code="las.page.field.contractManager.reviewInfo"/><!-- 검토기본정보 --></div>
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cnsdman_info" /></th><!-- 검토 담당자 -->
										<td colspan="3"><c:out value='${lomRq.cnsdman_info}' escapeXml='false'/></td>
									</tr>

								<c:if test="${!empty lomRq.apbt_memo_dp}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.gcMsg"/><!-- 그룹장 메세지 --><br/><spring:message code="las.page.field.contractManager.toReviewer"/><!-- (그룹장 → 검토자) --></th>
										<td colspan="3">
											<c:out value='${lomRq.apbt_memo_dp}' escapeXml="false"/>
										</td>						
									</tr>
								</c:if>
								<c:if test="${!empty lomMn.main_matr_cont_dp}">
									<tr id="tr_main_matr_cont">
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /></th><!-- TN_CLM_CONTRACT_CNSD	주요_사안_내용 MAIN_MATR_CONT    부서 검토 테이블로 바꿀꺼임 -->
										<td colspan="3">
											<c:out value="${lomMn.main_matr_cont_dp}" escapeXml="false" />
										</td>						
									</tr>
								</c:if>

						</table>
						
						
						<table id="tab_contents_sub_conts3_3" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
							<colgroup>
								<col width="15%" />
								<col width="9%" />
								<col width="18%" />
								<col width="13%" />
								<col />
							</colgroup>
							<tr id="tr_space3_3" class="space">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<div id="div_cnsd3_3" class="title_basic3"><spring:message code="las.page.field.contractManager.feedbackComment"/><!-- 검토회신의견 --></div>

								<tr>
								<%	if("Y".equals(lomRq.get("plndbn_req_yn"))){ //최종본의뢰여부 %>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> TEST(최종본) </th><!-- * 최종검토의견 -->
								<%	}else{%>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> TEST </th><!-- * 종합검토의견 -->
								<%	}%>
									<td colspan="4">
										<textarea id="if_cnsd_opnn_textarea" name="cnsd_opnn_textarea" style="display: none;"><c:out value="${lomRq.cnsd_opnn}" escapeXml="false" /></textarea>
										<iframe id="if_cnsd_opnn" name="if_cnsd_opnn" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
									</td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
									<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></td><!-- 계약서 -->
									<td colspan="3">
									 <iframe src="<c:url value='/clm/blank.do' />" id="fileListReContract" name="fileListReContract"  frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
									</td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<td class="tal_lineL"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /><!-- 첨부/별첨 --></td>
									<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReEtc" name="fileListReEtc" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe></td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<td class="tal_lineL"><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td>
									<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReGita" name="fileListReGita"frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe></td>
								</tr>
							
						</table>
						
					</div> --%>
				<!--검토의견  VIEW END -->
				
				<!--병행검토 요청  VIEW START -->				
				<div id="tr_down03">
					<div class="title_basic">
						<h4>Subsidiary Request Information<img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_hq_cnsd');" style='cursor:pointer;'/></h4>
					</div>

					<div id="tr_down_hq_cnsd">
						
						<table id="tab_contents_sub_conts3_3" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
							<colgroup>
								<col width="15%" />
								<col width="9%" />
								<col width="18%" />
								<col width="13%" />
								<col />
							</colgroup>
							<tr id="tr_space3_3" class="space">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<th>Requester</th><!-- 병행 검토 요청자 -->
								<td colspan="4"><c:out value='${lomHq.REQMAN_NM}' escapeXml='false'/></td>
							</tr>
							<tr>
								<th>Request Details</th><!-- * 병행검토 요청 의견 -->
								<td colspan="4"><c:out value="${lomHq.HQ_CNSD_DEMND_CONT_TD}" escapeXml="false" />	</td>
							</tr>
							<tr name="tr_attached_file" style="display:;">
								<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
								<td colspan="4">
								 <iframe src="<c:url value='/clm/blank.do' />" id="file_hq_cnsd_demnd_cont" name="file_hq_cnsd_demnd_cont"  frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
								</td>
							</tr>
							
						</table>
						
					</div>
				</div>	
				<!--병행검토 요청  VIEW END -->

				<!--병행 검토의견  INSERT START -->				
				<c:if test="${( lomHq.hq_cnsd_status eq 'C16203' ) || ( lomHq.hq_cnsd_status eq 'C16204' ) || ( lomHq.hq_cnsd_status eq 'C16205' ) || ( lomHq.hq_cnsd_status eq 'C16206' ) || ( lomHq.hq_cnsd_status eq 'C16207' )  }">
				<div id="tr_down04">
					<div class="title_basic">
						<h4>HQ Legal Opinion<img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_hq_resp');" style='cursor:pointer;'/></h4>
					</div>
					<div id="tr_down_hq_resp">
						
							<table id="tab_contents_sub_conts3_3" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
								<colgroup>
									<col width="15%" />
									<col width="9%" />
									<col width="18%" />
									<col width="13%" />
									<col />
								</colgroup>
								<tr id="tr_space3_3" class="space">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
																
								<c:choose>
									<c:when test="${lomHq.HQ_REL_PRO == 'RP010'}">										
										
										<tr>
											<th>HQ(CE) Legal Opinion  <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><span class='astro'>*</span></c:if></th><!-- * HQ(IM) Legal Opinion -->
											
											<c:choose>
												<c:when test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
													<td colspan="4">
														<span id="hq_ce_cnsd_opnn">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
		 												<textarea name="hq_ce_cnsd_opnn" id="hq_ce_cnsd_opnn" cols="40" rows="8" maxLength="4000"  onkeyup="frmChkLenLang(this,4000,'hq_ce_cnsd_opnn','<%=langCd%>')" class="text_area_full" alt="HQ(CE) Legal Opinion "><c:out value='${lomHq.hq_ce_cnsd_opnn}'  escapeXml="false" /></textarea> 			
													</td>												
												</c:when>
												<c:otherwise>												
													<td colspan="4"><c:out value='${lomHq.hq_ce_cnsd_opnn_td}'  escapeXml="false" /></td>											
												</c:otherwise>
											</c:choose>									
										
										</tr>
										<tr name="tr_attached_file" style="display:;">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
												<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
												<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
												</c:if>
											</th>
											<td colspan="4">
											 <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><div class='ico_maxsize fR'>Max Size : <span>50MB</span></div></c:if>
											 <iframe src="<c:url value='/clm/blank.do' />" id="hq_ce_cnsd_opnn_file" name="hq_ce_cnsd_opnn_file" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
											</td>
										</tr>
										
									</c:when>
									<c:when test="${lomHq.HQ_REL_PRO == 'RP020'}">
										
										<tr>
											<th>HQ(IM) Legal Opinion  <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><span class='astro'>*</span></c:if></th><!-- * HQ(IM) Legal Opinion -->
											
											<c:choose>
												<c:when test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
													<td colspan="4">
														<span id="hq_im_cnsd_opnn">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
		 												<textarea name="hq_im_cnsd_opnn" id="hq_im_cnsd_opnn" cols="40" rows="8" maxLength="4000"  onkeyup="frmChkLenLang(this,4000,'hq_im_cnsd_opnn','<%=langCd%>')" class="text_area_full" alt="HQ(IM) Legal Opinion " ><c:out value='${lomHq.hq_im_cnsd_opnn}'  escapeXml="false" /></textarea> 			
													</td>
												</c:when>
												<c:otherwise>												
													<td colspan="4"><c:out value='${lomHq.hq_im_cnsd_opnn_td}'  escapeXml="false" /></td>											
												</c:otherwise>
											</c:choose>										
											
										</tr>
										<tr name="tr_attached_file" style="display:;">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
												<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
												<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
												</c:if>			</th>
											<td colspan="4">
											 <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><div class='ico_maxsize fR'>Max Size : <span>50MB</span></div></c:if>
											 <iframe src="<c:url value='/clm/blank.do' />" id="hq_im_cnsd_opnn_file" name="hq_im_cnsd_opnn_file" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
											</td>
										</tr>
										
									</c:when>
									<c:when test="${lomHq.HQ_REL_PRO == 'RP030'}">
										
										<tr>
											<th>HQ(CE) Legal Opinion  <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><span class='astro'>*</span></c:if></th><!-- * HQ(IM) Legal Opinion -->
											<c:choose>
												<c:when test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
													<td colspan="4">
														<span id="hq_ce_cnsd_opnn">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
		 												<textarea name="hq_ce_cnsd_opnn" id="hq_ce_cnsd_opnn" cols="40" rows="8" maxLength="4000"  onkeyup="frmChkLenLang(this,4000,'hq_ce_cnsd_opnn','<%=langCd%>')" class="text_area_full" alt="HQ(CE) Legal Opinion "><c:out value='${lomHq.hq_ce_cnsd_opnn}'  escapeXml="false" /></textarea> 			
													</td>												
												</c:when>
												<c:otherwise>												
													<td colspan="4"><c:out value='${lomHq.hq_ce_cnsd_opnn_td}'  escapeXml="false" /></td>												
												</c:otherwise>
											</c:choose>	
										</tr>
										
										<tr name="tr_attached_file" style="display:;">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
												<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
												<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
												</c:if>	</th>
											<td colspan="4">
											 <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><div class='ico_maxsize fR'>Max Size : <span>50MB</span></div></c:if>
											 <iframe src="<c:url value='/clm/blank.do' />" id="hq_ce_cnsd_opnn_file" name="hq_ce_cnsd_opnn_file" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
											</td>
										</tr>
										
										<tr>
											<th>HQ(IM) Legal Opinion  <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><span class='astro'>*</span></c:if></th><!-- * HQ(IM) Legal Opinion -->
											<c:choose>
												<c:when test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
													<td colspan="4">
														<span id="hq_im_cnsd_opnn">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
		 												<textarea name="hq_im_cnsd_opnn" id="hq_im_cnsd_opnn" cols="40" rows="8" maxLength="4000"  onkeyup="frmChkLenLang(this,4000,'hq_im_cnsd_opnn','<%=langCd%>')" class="text_area_full" alt="HQ(IM) Legal Opinion " ><c:out value='${lomHq.hq_im_cnsd_opnn}'  escapeXml="false" /></textarea> 			
													</td>
												</c:when>
												<c:otherwise>												
													<td colspan="4"><c:out value='${lomHq.hq_im_cnsd_opnn_td}'  escapeXml="false" /></td>											
												</c:otherwise>
											</c:choose>	
										</tr>
										
										<tr name="tr_attached_file" style="display:;">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
												<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
												<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
												</c:if>
											</th>
											<td colspan="4">
											 <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><div class='ico_maxsize fR'>Max Size : <span>50MB</span></div></c:if>
											 <iframe src="<c:url value='/clm/blank.do' />" id="hq_im_cnsd_opnn_file" name="hq_im_cnsd_opnn_file" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
											</td>
										</tr>										
										
									</c:when>

									<c:otherwise>
									
										<tr>
											<th>HQ Legal Opinion  <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><span class='astro'>*</span></c:if></th><!-- * HQ(IM) Legal Opinion -->
											<c:choose>
												<c:when test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
													<td colspan="4">
														<span id="hq_not_cnsd_opnn">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
		 												<textarea name="hq_not_cnsd_opnn" id="hq_not_cnsd_opnn" cols="40" rows="8" maxLength="4000"  onkeyup="frmChkLenLang(this,4000,'hq_not_cnsd_opnn','<%=langCd%>')" class="text_area_full" alt="HQ(IM) Legal Opinion "><c:out value='${lomHq.hq_not_cnsd_opnn}'  escapeXml="false" /></textarea> 			
													</td>													
												</c:when>
												<c:otherwise>												
													<td colspan="4"><c:out value='${lomHq.hq_not_cnsd_opnn_td}'  escapeXml="false" /></td>											
												</c:otherwise>
											</c:choose>			
										
										</tr>
										<tr name="tr_attached_file" style="display:;">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
												<c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }">
												<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
												</c:if>
											</th>
											<td colspan="4">
											 <c:if test="${lomHq.EDIT_VIEW eq 'MODIFY' && respYnHQ eq 'Y'  }"><div class='ico_maxsize fR'>Max Size : <span>50MB</span></div></c:if>
											 <iframe src="<c:url value='/clm/blank.do' />" id="hq_not_cnsd_opnn_file" name="hq_not_cnsd_opnn_file" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
											</td>
										</tr>
									
									</c:otherwise>
								</c:choose>
								

							</table>

					</div>				
				</div>
				</c:if>
				<!--병행 검토의견  INSERT END -->
				<!--병행 검토의견  INSERT END -->
				<!--병행 검토의견  INSERT END -->
				
				<!--본사 병행 검토 대상 계약 체크 START -->
				<!--본사 병행 검토 대상 계약 체크 START -->
				
				<div class="title_basic">
					<h4><spring:message code="las.page.field.hqrequest.page06" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_check');" style='cursor:pointer;'/></h4>
				</div>
				
					<div id="tr_down_check">
						<table id="tr_down_check" cellspacing="0" cellpadding="0" border="0" class="table-style01" >
							<colgroup>
								<col width="5%" />
								<col width="*" />
								<col width="10%" />
								<col />
							</colgroup>
								<tr>
									 <th class='tC'>No</th>
								    <th class='tC'><spring:message code="las.page.field.hqrequest.page08" /><!-- 번호 --></th>
							        <th class='tC'>YES/NO</th>							       
								</tr>	
							    <c:if test="${!empty hqchekItemList }">
							        <c:forEach var="hqchekItemList" items="${hqchekItemList}" varStatus="i">
							            <tr>
							            	<td  class='tC'><c:out value="${i.count}"/></td>
							                <td><c:out value="${hqchekItemList.cd_nm }" escapeXml="false"/></td>
							                <td  class='tC'>
							                <c:choose>
							                    <c:when test="${ 'Y' eq hqchekItemList.check_yn }">YES</c:when>
							                    <c:when test="${ 'N' eq hqchekItemList.check_yn }">NO</c:when>
							                    <c:otherwise>&nbsp;</c:otherwise>
							                </c:choose>
							                </td>
						                </tr>
							        </c:forEach>
							    </c:if>

						</table>

					</div>
				<!--본사 병행 검토 대상 계약 체크 END -->
				<!--본사 병행 검토 대상 계약 체크 END -->
				
				<!--15개체크항목   START-->
				<!--15개체크항목   START-->
				
				<c:if test="${'Y' eq lomRq.plndbn_req_yn}">
						<div class="title_basic">
						<h4><spring:message code="las.page.field.hqrequest.page09" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tr_down_check_list');" style='cursor:pointer;'/></h4>
						</div>
						
						<div id="tr_down_check_list">
						
							<table id="tab_contents_sub_conts3_3" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">	
								<colgroup>	
									<col width="5%" />
									<col width="20%" />
									<col width="*" />
								</colgroup>
							    <tr>
							        <th class='tC'>No</th>
							        <th class='tC'><spring:message code='las.page.field.hqrequest.page12' /></th>
							        <th class='tC'><spring:message code='las.page.field.hqrequest.page13' /></th>
						        </tr>						        
						        	<c:choose>
										<c:when test="${!empty chekItemList}">
												<c:forEach var="list_in" items="${chekItemList}" varStatus="x">
													 <tr>
													 	<td class='tC'><c:out value='${x.index + 1}'  /></td>
													 	<td><c:out value='${list_in.CD_NM}' /></td>
													 	<td><c:out value='${list_in.REMARK}'  escapeXml="false" /></td>
													 </tr>		
												</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="3" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
											</tr>
										</c:otherwise>
								</c:choose>						
							
							</table>														
						</div>
				</c:if>				
				
				<!-- 15개체크항목  END-->
				<!-- 15개체크항목  END-->
				
				<!-- 15개체크항목  JSP INCLUDE START -->
				<!-- 15개체크항목  JSP INCLUDE START -->
				
<%-- 			    <jsp:include page="/clm/review/considerationHQ.do">
			    	<jsp:param name="method" value="getCheckList" />
                  	<jsp:param name="cntrt_id3" value='<%=lomRq.get("cntrt_id")%>' />
                </jsp:include> --%>
                
                <!-- 15개체크항목  JSP INCLUDE END-->
				<!-- 15개체크항목  JSP INCLUDE END-->
				
				<!-- 이력정보 START -->
				<!-- div id="contractHis-list"></div-->
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_his.review_history" /><!-- 검토이력 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-reviewHisView');" style='cursor;pointer'/></h4>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-reviewHisView">
					<colgroup>
						<col width="*" />
						<col width="10%" />
						<col width="25%" />
					</colgroup>
					<thead>
					<tr>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.detail_req" /><!-- 의뢰내역 --></th>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.day" /><!-- 일자 --></th>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.reqman_respman" /><!-- 의뢰인/검토자 --></th>
					</tr>
					</thead>
					<tbody>
				<%
				if(review.size() > 0){
					String contId = "";
					for(int idx=0;idx < review.size();idx++){
				
						ListOrderedMap lom = (ListOrderedMap)review.get(idx);		
						contId = (String)lom.get("cr_flag")+"_"+((BigDecimal)lom.get("rm")).intValue();	
				%>		
						<tr>
							<td class="tL">
								<span class="">
								<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" onclick="attachFile(this, '<%=lom.get("cr_flag") %>', '<%=lom.get("cnsdreq_id") %>', '<%=idx%>', '<%=contId%>')"/>
								</span>
								<%=lom.get("cr_flan_nm") %>
								<%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
							</td>
							<td class="tC"><%=lom.get("reg_dt") %></td>
							<td><%=lom.get("man_nm") %><%=lom.get("jikgup_nm") %><%=lom.get("dept_nm") %></td>
						</tr>
						<!-- 테이블안에 테이블 -->
						<tr class="Nocol" id="tr_show" style="display:none;">
							<td class="nopadding" colspan="3" style='padding:6px 1px 6px 0'>
								<table class="table-style_sub02">
								<colgroup>									
									<col width="13%" />
									<col width="10%" />
									<col width="*%" />
									<col width="10%" />
									<col width="25%" />
								</colgroup>
								
					<%	
						if("CONSULT".equals(lom.get("cr_flag"))){//검토요청
							
					%>					
									<tr class="Nocol">										
										<th ><spring:message code="las.page.field.contractmanager.consideration_his.detail_cnsdreq" /><!-- 검토요청내용 --></th>
										<td colspan="4"><%=lom.get("cont") %>
											<%-- <textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=lom.get("cont") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe> --%>
										</td>
									</tr>
					<!--  2012.03.20 검토본변경여부, 변경내역 및 사유 추가 added by hanjihoon -->				
									<tr class="Nocol">
										
										<th rowspan="4" class='rightline'><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
										<td class="tL"><spring:message code="las.page.field.contractmanager.consideration_his.contract" /><!-- 계약서 --></td>
										<td colspan="3">
											<iframe src="<c:url value='/clm/blank.do' />" id="fileList_cntrt<%=idx %>" name="fileList_cntrt<%=idx %>" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"  style='height:20px;'></iframe>
											<input type="hidden" name="fileInfos_cntrt<%=idx %>"  value="" />
											<input type="hidden" name="cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class="tL"><spring:message code="las.page.field.contractmanager.consideration_his.attach" /><!-- 첨부/별첨 --></td>
										<td colspan="3">
											<iframe src="<c:url value='/clm/blank.do' />" id="fileList_append<%=idx %>" name="fileList_append<%=idx %>"frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
											<input type="hidden" name="fileInfos_append<%=idx %>"  value="" />
											<input type="hidden" name="append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
				 					<tr class="Nocol">
				 						
										<td class="tL"><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td>
										<td colspan="3">
											<iframe src="<c:url value='/clm/blank.do' />" id="fileList_other<%=idx %>" name="fileList_other<%=idx %>" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
											<input type="hidden" name="fileInfos_other<%=idx %>"  value="" />
											<input type="hidden" name="other_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
<%-- 									<tr class="Nocol">
				 						
										<td class="tC">outlook</td>
										<td colspan="3">
											<iframe src="<c:url value='/clm/blank.do' />" id="fileList_ol<%=idx %>" name="fileList_ol<%=idx %>" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true"></iframe>
											<input type="hidden" name="fileInfos_ol<%=idx %>"  value="" />
											<input type="hidden" name="ol_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr> --%>

					<%	
						}else if("REJECT_".equals(lom.get("cr_flag"))){//반려
									if(cnsdList.size() > 0){
										for(int cnt=0;cnt < cnsdList.size();cnt++){
											ListOrderedMap lomCnsd = (ListOrderedMap)cnsdList.get(cnt);
											if(lom.get("cnsdreq_id").equals(lomCnsd.get("cnsdreq_id")) && ((BigDecimal)lomCnsd.get("seqno")).intValue() > 0){
												lomCnsd.put("rejct_opnn", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomCnsd.get("rejct_opnn"),"")));
									%>
									<tr class="Nocol">
										
										<th>[<%=lomCnsd.get("seqno")%><spring:message code="las.page.field.contractManager.seqRctDate"/><!-- 차] 반려일자 --></th>
										<td colspan="4">
											<%=lomCnsd.get("rejct_dt") %>		
										</td>
									</tr>
									<tr class="Nocol">
										
										<th>[<%=lomCnsd.get("seqno")%><spring:message code="las.page.field.contractManager.seqRvCmt"/><!-- 차] 검토의견 --></th>
										<td colspan="4">
											<textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=(String)StringUtil.nvl((String)lomCnsd.get("cnsd_opnn"), "") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>										
										</td>
									</tr>
									<tr class="Nocol">
										
										<th>[<%=lomCnsd.get("seqno")%><spring:message code="las.page.field.contractManager.seqRctCmt"/><!-- 차] 반려의견 --></th>
										<td colspan="4">
											<%=lomCnsd.get("rejct_opnn") %>		
										</td>
									</tr>
									<%
											}				
										}
							
									}
									%>
					<%
						}else if("REPLY__".equals(lom.get("cr_flag")) || "REPLYREJECT__".equals(lom.get("cr_flag"))){ //회신						
					%>
													
									<% if(null != lom.get("vc_cnsd_opnn") && "" != lom.get("vc_cnsd_opnn")){%>
									<tr class="Nocol" name="tr_replay_off<%=idx %>" style="display:;">
										
										<th id="th_cnsd_sub_d<%=idx %>" class='gray'><spring:message code="las.page.field.contractManager.lnkDeptCmt"/><!-- 유관부서의견 --><img src="<%=IMAGE%>/btn/btn_ss2_down.gif" alt="down" onclick="tr_toggle(this,'tr_replay_on'+'<%=idx %>','tr_replay_off'+'<%=idx %>');" valign='absmiddle' style='cursor:pointer;' title="show" /></th>
										<td class='gray tC'><spring:message code="las.page.field.contractManager.writer"/><!-- 작성자 --></td>
										<td class='gray'>
											<%=lom.get("vc_cnsdman_nm") %> / <%=lom.get("vc_cnsd_jikgup_nm") %> / <%=lom.get("vc_cnsd_dept_nm") %>
										</td>
										<td class='gray tC'><spring:message code="las.page.field.contractManager.reportDay"/><!-- 작성일시 --></td>
										<td class='gray'>
											<%=lom.get("vc_cnsd_dt") %>
										</td>
									</tr>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
										
										<th id="th_cnsd_sub_u<%=idx %>" rowspan="2"><spring:message code="las.page.field.contractManager.lnkDeptCmt"/><!-- 유관부서의견 --><img src="<%=IMAGE%>/btn/btn_ss2_up.gif" alt="up" onclick="tr_toggle(this,'tr_replay_off'+'<%=idx %>','tr_replay_on'+'<%=idx %>');" valign='absmiddle' style='cursor:pointer;' title="hide" /></th>
										<td class='tC'><spring:message code="las.page.field.contractManager.writer"/><!-- 작성자 --></td>
										<td>
											<%=lom.get("vc_cnsdman_nm") %> / <%=lom.get("vc_cnsd_jikgup_nm") %> / <%=lom.get("vc_cnsd_dept_nm") %>
										</td>
										<td class='tC'><spring:message code="las.page.field.contractManager.reportDay"/><!-- 작성일시 --></td>
										<td>
											<%=lom.get("vc_cnsd_dt") %>
										</td>
									</tr>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.content"/><!-- 작성일시 --></td>
										<td colspan="3">
											<%=lom.get("vc_cnsd_opnn") %>
										</td>
									</tr>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
										
										<th rowspan="3" class='rightline'><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.contract" /><!-- 계약서 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="dept_reply_fileList_cntrt<%=idx %>" name="dept_reply_fileList_cntrt<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no"  ></iframe>
											<input type="hidden" name="dept_reply_fileInfos_cntrt<%=idx %>"  value="" />
											<input type="hidden" name="dept_reply_cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="dept_reply_cntrt_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">										
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.attach" /><!-- 첨부/별첨 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="dept_reply_fileList_append<%=idx %>" name="dept_reply_fileList_append<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="dept_reply_fileInfos_append<%=idx %>"  value="" />
											<input type="hidden" name="dept_reply_append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="dept_reply_append_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="dept_reply_fileList_other<%=idx %>" name="dept_reply_fileList_other<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="dept_reply_fileInfos_other<%=idx %>"  value="" />
											<input type="hidden" name="dept_reply_other_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="dept_reply_other_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<% if(null != lom.get("vc_apbt_dt") && "" != lom.get("vc_apbt_dt")){%>
									<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
									
										<% if(null != lom.get("vc_apbt_opnn") && "" != lom.get("vc_apbt_opnn")){%>
										<th rowspan="2"><spring:message code="las.page.field.contractManager.lnkDeptAppcmt"/><!-- 유관부서승인의견 --></th>
										<% }else{ %>
										<th><spring:message code="las.page.field.contractManager.lnkDeptAppcmt"/><!-- 유관부서승인의견 --></th>
										<% } %>
										<td class='tC'><spring:message code="las.page.field.contractManager.appBy"/><!-- 승인자 --></td>
										<td>
											<%=lom.get("vc_apbtman_nm") %> / <%=lom.get("vc_apbt_jikgup_nm") %> / <%=lom.get("vc_apbt_dept_nm") %>
										</td>
										<td><spring:message code="las.page.field.contractManager.appDate"/><!-- 승인일시 --></td>
										<td>
											<%=lom.get("vc_apbt_dt") %>
										</td>
									</tr>
										<% if(null != lom.get("vc_apbt_opnn") && "" != lom.get("vc_apbt_opnn")){%>
										<tr class="Nocol" name="tr_replay_on<%=idx %>" style="display:none;">
											
											<td class='tC'><spring:message code="las.page.field.contractManager.content"/><!-- 내용 --></td>
											<td colspan="3">
												<%=lom.get("vc_apbt_opnn") %>
											</td>
										</tr>
										<% }%>
									<% }%>
									<% }%>
									<tr class="Nocol">
										
										<th rowspan="2" class='rightline'><spring:message code="las.page.field.contractManager.feedbackComment"/><!-- 검토회신의견 --></th>
										<td class='tC'><spring:message code="las.page.field.contractManager.writer"/><!-- 작성자 --></td>
										<td colspan="2">
										<% if("".equals((String)lom.get("mn_cnsd_jikgup_nm"))){ %>
											<%=lom.get("mn_cnsdman_nm") %>
										<% }else{ %>
											<%=lom.get("mn_cnsdman_nm") %> / <%=lom.get("mn_cnsd_jikgup_nm") %> / <%=lom.get("mn_cnsd_dept_nm") %>
										<% } %>
										</td>
										<td class='tC'><spring:message code="las.page.field.contractManager.reportDay"/><!-- 작성일시 --></td>
										<td>
											<%=lom.get("mn_cnsd_dt") %>
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.content"/><!-- 내용 --></td>
										<td colspan="4">
											<textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=lom.get("mn_cnsd_opnn") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>										
										</td>
									</tr>
									<tr class="Nocol">
										
										<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /></th><!-- 첨부파일 -->
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.contract" /><!-- 계약서 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_cntrt<%=idx %>" name="reply_fileList_cntrt<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_cntrt<%=idx %>"  value="" />
											<input type="hidden" name="reply_cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="reply_cntrt_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.attach" /></td><!-- 첨부/별첨 -->
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_append<%=idx %>" name="reply_fileList_append<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_append<%=idx %>"  value="" />
											<input type="hidden" name="reply_append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="reply_append_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td><!-- 첨부파일 -->
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_other<%=idx %>" name="reply_fileList_other<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_other<%=idx %>"  value="" />
											<input type="hidden" name="reply_other_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
											<input type="hidden" name="reply_ohter_cnsd_dept<%=idx %>"  value="<%=lom.get("cnsd_dept") %>" />
										</td>
									</tr>
									<% if(null != lom.get("mn_apbt_dt") && "" != lom.get("mn_apbt_dt")){%>
									<tr class="Nocol">
										
										<% if(null != lom.get("mn_apbt_opnn") && "" != lom.get("mn_apbt_opnn")){%>
										<th rowspan="2"><spring:message code="las.page.field.contractManager.approvalComment"/><!-- 검토회신승인의견 --></th>
										<% }else{ %>
										<th><spring:message code="las.page.field.contractManager.approvalComment"/><!-- 검토회신승인의견 --></th>
										<% } %>
										<td class='tC'><spring:message code="las.page.field.contractManager.appBy"/><!-- 승인자 --></td>
										<td colspan="2">
											<%=lom.get("mn_apbtman_nm") %> 
											<% if(lom.get("mn_apbt_jikgup_nm") != ""){%>
											/ <%=lom.get("mn_apbt_jikgup_nm") %> / <%=lom.get("mn_apbt_dept_nm") %>
											<%} %>
										</td>
										<td class='tC'><spring:message code="las.page.field.contractManager.appDate"/><!-- 승인일시 --></td>
										<td>
											<%=lom.get("mn_apbt_dt") %>
										</td>
									</tr>
									<% if(null != lom.get("mn_apbt_opnn") && "" != lom.get("mn_apbt_opnn")){%>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.content"/><!-- 내용 --></td>
										<td colspan="4">
											<%=lom.get("mn_apbt_opnn") %>
										</td>
									</tr>
									<% }%>
									<% }%>
									
	
									
					<%
							
						}else if("RESHOLD".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){//보류
					%>
									<tr class="Nocol">
										
										<th><spring:message code="las.page.field.contractManager.pstpnRs"/><!-- 보류사유 --></th>
										<td colspan="4">
											<%=(String)lom.get("cont") %>
											<textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=lom.get("mn_apbt_opnn") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>										
										</td>
									</tr>
						<% if(null != lom.get("cnsd_dept_opnn") && "" != lom.get("cnsd_dept_opnn")){%>
									<tr class="Nocol">
										
										<th rowspan="3"><spring:message code="las.page.field.contractManager.reviewComment"/><!-- 검토의견 --></th>
										<td class='tC'><spring:message code="las.page.field.contractManager.writer"/><!-- 작성자 --></td>
										<td colspan="4">
											<%=lom.get("cnsdman_nm") %> / <%=lom.get("cnsd_jikgup_nm") %> / <%=lom.get("cnsd_dept_nm") %>
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.reportDay"/><!-- 작성자 --></td>
										<td colspan="4">
											<%=lom.get("cnsd_dt") %>
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.content"/><!-- 내용 --></td>
										<td colspan="4">
											<textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=lom.get("cnsd_dept_opnn") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>										
										</td>
									</tr>
									<tr class="Nocol">
										
										<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.contract" /></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_cntrt<%=idx %>" name="reply_fileList_cntrt<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_cntrt<%=idx %>"  value="" />
											<input type="hidden" name="reply_cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractmanager.consideration_his.attach" /><!-- 첨부/별첨 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_append<%=idx %>" name="reply_fileList_append<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_append<%=idx %>"  value="" />
											<input type="hidden" name="reply_append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
									<tr class="Nocol">
										
										<td class='tC'><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td>
										<td colspan="4">
											<iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_ohter<%=idx %>" name="reply_fileList_other<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="reply_fileInfos_other<%=idx %>"  value="" />
											<input type="hidden" name="reply_other_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
										</td>
									</tr>
						<% }%>
								
					<%
						}else if("ZADMIN_REPLY".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){//ZADMIN_REPLY
					%>
									<tr class="Nocol">
										
										<th><spring:message code="las.page.field.contractManager.pstpnRs"/><!-- 보류사유 --></th>
										<td colspan="4">
											<%=(String)lom.get("cont") %>
											<textarea id="<%=contId %>_textarea" name="<%=contId %>_textarea" style="display: none;"><%=lom.get("mn_apbt_opnn") %></textarea>
											<iframe id="<%=contId %>" name="<%=contId %>" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>										
										</td>
									</tr>
					
								<% }%>						
					
								</table>
							</td>
						</tr>
				<%
					}
				}else{
					
				%>
						<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="3" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
						</tr>
				<%
				}
				%>
					</tbody>
				</table>
				
				<!-- 검토이력정보 END -->
				

				<!-- HQ 이력정보 START -->
				
				<div class="title_basic">
					<h4>HQ  <spring:message code="las.page.field.contractmanager.consideration_his.review_history" /><!-- 검토이력 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'hq_reviewHisView');" style='cursor;pointer'/></h4>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="hq_reviewHisView">
					<colgroup>
						<col width="*" />
						<col width="10%" />
						<col width="25%" />
					</colgroup>
					<thead>
					<tr>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.detail_req" /><!-- 의뢰내역 --></th>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.day" /><!-- 일자 --></th>
						<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.reqman_respman" /><!-- 의뢰인/검토자 --></th>
					</tr>
					</thead>
					<tbody>
					
					<c:forEach var="list" items="${hqHistory}">
					
						<tr>
							<td class="tL">
								<c:out value='${list.cr_flan_nm}'/> 
								<c:out value='${list.title}'/> 
							</td>
							<td class="tC"><c:out value='${list.reg_dt}'/></td>
							<td>	<c:out value='${list.man_nm}'/>	</td>					
						</tr>
						
						<c:if test="${!empty list.HQ_LIST }" >
							<c:forEach var="list_in" items="${list.HQ_LIST}" varStatus="x">			
							
								<c:choose>
									<c:when test="${'REQ' eq list_in.cont_type }">	
										<c:set var="cont_lavel1"  value="HQ Review Request"/>
									</c:when>
									<c:when test="${'ADMIN_REPLY' eq list_in.cont_type }">	
										<c:set var="cont_lavel1"  value="HQ Header REPLY"/>
									</c:when>
									<c:otherwise>
									
										<c:choose>
											<c:when test="${'RP010' eq list_in.hq_rel_pro }">
												<c:set var="cont_lavel1"  value="HQ(CE) Legal Opnion"/>
												<c:set var="cont_lavel2"  value="HQ(IM) Legal Opnion"/>	
											</c:when>
											<c:when test="${'RP020' eq list_in.hq_rel_pro }">
												<c:set var="cont_lavel1"  value="HQ(IM) Legal Opnion"/>	
											</c:when>
											<c:when test="${'RP030' eq list_in.hq_rel_pro }">
												<c:set var="cont_lavel1"  value="HQ(CE) Legal Opnion"/>
												<c:set var="cont_lavel2"  value="HQ(IM) Legal Opnion"/>	
											</c:when>
											<c:otherwise>
												<c:set var="cont_lavel1"  value="HQ Legal Opnion"/>						
											</c:otherwise>
										</c:choose>
									
									</c:otherwise>
								</c:choose>				
							
								<tr class="Nocol">										
									<td class="tL">
										<span class="">
											<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show_hq" onclick='attachFileHQ(this, "<c:out value="${list_in.hq_cnsdreq_id}"  />" , "<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value='${list_in.cnsd_level}'  />" , "<c:out value='${list_in.hq_cnsdreq_id}'  />_<c:out value='${list_in.cnsd_level}'  />" ,  "<c:out value='${list_in.hq_rel_pro}'  />"  ,  "<c:out value='${list_in.cont_type}'  />" )'/>
										</span>
										<c:out value='${list_in.cr_flan_nm}'/> 
										<c:out value='${list_in.title}'/> 
									</td>
									<td class="tC"><c:out value='${list_in.reg_dt}'/></td>
									<td><c:out value='${list_in.man_nm}'/></td>
								</tr>
								
								<!-- 테이블안에 테이블 START-->						
								<tr class="Nocol" id="tr_show_hq" style="display:none">
									
	 								<td class="nopadding" colspan="3" style='padding:6px 1px 6px 0'>
										<table class="table-style_sub02">
											<colgroup>
												<col width="16%" />
												<col width="16%" />
												<col width="16%" />
												<col width="*" />
											</colgroup>
											<tr class="Nocol">										
													<th ><c:out value="${cont_lavel1}" /></th>
													<td colspan="3"><c:out value="${list_in.CONT_TD}" escapeXml="false"  /></td>
											</tr>
											<c:if test="${ list_in.cont_type ne 'ADMIN_REPLY'  }" >
												<tr class="Nocol">
													<th><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
													<td colspan="3">
														<iframe src="<c:url value='/clm/blank.do' />" id="fileList_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />" name="fileList_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true" ></iframe>
														<input type="hidden" name="fileInfos_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />"  value="" />
													</td>
												</tr>
											</c:if>
											<c:if test="${ (list_in.hq_rel_pro eq 'RP030' ) 
																&& ( 'REQ' ne list_in.cont_type )
																	&& ( 'ADMIN_REPLY' ne list_in.cont_type)  }" >
											
												<tr class="Nocol">										
													<th ><c:out value="${cont_lavel2}" /></th>
													<td colspan="3"><c:out value="${list_in.CONT2_TD}" escapeXml="false"  /></td>
												</tr>
												
												<tr class="Nocol">
													<th><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /><!-- 첨부파일 --></th>
													<td colspan="3">
														<iframe src="<c:url value='/clm/blank.do' />" id="fileList_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />_2" name="fileList_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />_2" frameborder="0" class='addfile_iframe_d' scrolling="no" allowTransparency="true" ></iframe>
														<input type="hidden" name="fileInfos_<c:out value="${list_in.hq_cnsdreq_id}"  />_<c:out value="${list_in.cnsd_level}"  />_2"  value="" />
													</td>
												</tr>											
											
											</c:if>
										</table>
									</td> 
								</tr>
								<!-- 테이블안에 테이블 =END-->								
							</c:forEach>
						</c:if>											
					</c:forEach>					
					</tbody>
				</table>
					
				<!-- HQ 이력정보 END -->
				<!-- HQ 이력정보 END -->
				<!-- HQ 이력정보 END -->				
				
				<!-- 승인 이력정보 START -->
				<!-- 승인 이력정보 START -->
				<!-- 승인 이력정보 START -->				
				
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_his.approve_history" /><!-- 승인이력 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-approveHisView');" style='cursor;pointer'/></h4>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-approveHisView">
					<colgroup>
						<col width="67%" />
						<col width="8%" />
						<col width="8%" />
						<col width="17%" />
					</colgroup>
					<thead>
						<tr>
							<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.report_name" /><!-- 품의명 --></th>
							<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.status" /><!-- 상태 --></th>
							<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.day" /><!-- 일자 --></th>
							<th class="tC"><spring:message code="las.page.field.contractmanager.consideration_his.approve_man" /><!-- 승인자 --></th>
						</tr>
					</thead>
					<tbody>
				<%
				if(info.size() > 0){
					
					for(int idx=0;idx < info.size();idx++){
				
						ListOrderedMap lom = (ListOrderedMap)info.get(idx);
						
				%>		
						<tr>
							<td class="tL">
								<span class="">
								<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" onclick="attachFile(this, 'INFO', '<%=lom.get("cntrt_id") %>', '<%=idx%>' ,'')"/>
								</span>
								<spring:message code="las.page.field.contractmanager.consideration_his.before_report_info" /><!-- 사전승인정보 -->
							</td>
							
							<td class="tC"><%=StringUtil.bvl(lom.get("bfhdcstn_apbt_mthd_nm"), "").trim()%></td>
							<td class="tC"><%=StringUtil.bvl(lom.get("bfhdcstn_apbtday"), "").trim() %></td>
							<td><%=StringUtil.bvl(lom.get("bfhdcstn_apbtman_nm"), "").trim() %> / <%=StringUtil.bvl(lom.get("bfhdcstn_apbtman_jikgup_nm"), "").trim() %> / <%=StringUtil.bvl(lom.get("bfhdcstn_apbt_dept_nm"), "").trim()%></td>
						</tr>
						<!-- 테이블안에 테이블 -->
						<tr class="Nocol" id="tr_show01" style="display:none">
							
							<td class="nopadding" colspan="4" style='padding:6px 1px 6px 0'>
								<table class="table-style_sub02">
									<colgroup>
										
										<col width="15%" />
										<col width="85%" />
									</colgroup>
									<tr class="Nocol">
										
										<th>
											 <spring:message code="las.page.field.contractmanager.consideration_his.suggest_man" /><!-- 발의자 -->
										</th>
										<td colspan="3">
											<%=StringUtil.bvl(lom.get("bfhdcstn_mtnman_nm"), "").trim() %>/ <%=StringUtil.bvl(lom.get("bfhdcstn_mtnman_jikgup_nm"), "").trim()%> / <%=StringUtil.bvl(lom.get("bfhdcstn_mtn_dept_nm"), "").trim()%>
										</td>
									</tr>
									<tr class="Nocol">
										
										<th><spring:message code="las.page.field.contractmanager.consideration_his.attached_file" /></th><!-- 첨부파일 -->
										<td colspan="3">
											<iframe src="<c:url value='/clm/blank.do' />" id="info_fileList" name="info_fileList" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="info_fileInfos"  value="" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
				<%
					} 
				}else {
				%>
						<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
						</tr>
				<%
				}
				%>	
				<%
				if(approve.size() > 0){				
					
					boolean bFirstLine	= false; //현재 Mis_id의 첫번째 라인여부
					String sMemMis_id  	= "" ; 	//현재 Mis_id
					int iMemAllMember  	= 0 ; 	//현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
					int iMemRow		   	= 0 ;	//현재 iMemAllMember의 Row위치
					String sNextMis_id  = "" ; 	//다음  Mis_id
					int iFileRowIndex	= 0 ;	//파일 index
					int iFile_cnt		= 0; 	//파일갯수  
					String sApprovalName= "";	//승인자명 
					String sStatus		= "";
					String sActivity	= "";
					for(int idx=0;idx < approve.size();idx++){
						
						ListOrderedMap lom = (ListOrderedMap)approve.get(idx);
						sNextMis_id = (String)lom.get("mis_id");
						
						if(idx == 0){
							sMemMis_id  = (String)lom.get("mis_id");
							bFirstLine	= true;
							//iMemAllMember= ((BigDecimal)lom.get("cntrt_id_cnt")).intValue();	//신성우 변경처리 전달받는 값 자체가 integer 상태.. 처리방식이 잘못되어있음 2014-03-06
							iMemAllMember= Integer.parseInt(lom.get("cntrt_id_cnt").toString());
							iMemRow = 1;
						}else{
							//다음Mis_id와  현재Mis_id가 동일하지 않으면 다른 결재권임.
							if(! sNextMis_id.equals(sMemMis_id)){
								sMemMis_id = sNextMis_id;
								bFirstLine	= true;
								//iMemAllMember= ((BigDecimal)lom.get("cntrt_id_cnt")).intValue();	//신성우 변경처리 전달받는 값 자체가 integer 상태.. 처리방식이 잘못되어있음 2014-03-06
								iMemAllMember= Integer.parseInt(lom.get("cntrt_id_cnt").toString());
								iMemRow = 0;
								
								iFileRowIndex ++;
							}
							
							iMemRow ++;
						}
						
						if(bFirstLine) {
							bFirstLine = false;
							
				%>
						
						<tr>
							<td class="tL">
								<span class="">
								<img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" onclick="attachFile(this, 'APPROVE', '<%=lom.get("cnsdreq_id") %>', '<%=iFileRowIndex%>','')"/>
								</span>
								<%=lom.get("title") %>
							</td>
							<td class="tC"><%=lom.get("status") %></td>
							<td class="tC"><%=lom.get("create_date") %></td>
							<td><span id='sp_approval<%=iFileRowIndex %>'></span></td>
						</tr>
						<!-- 테이블안에 테이블 -->
						<tr class="Nocol" id="tr_show01" style="display:none">
							<td class="nopadding" colspan="4" style="padding-top: 6px; padding-right: 1px; padding-bottom: 6px; padding-left: 0px;">
								<table class="table-style_sub02">
									<colgroup>
										<col width="15%" />
										<col width="85%" />
									</colgroup>
									<tr class="Nocol">
										<th rowspan='<%=lom.get("cntrt_id_cnt")%>'>
											 <spring:message code="las.page.field.contractManager.appEmp"/><!-- 결재자 -->
										</th>
										<td>
											<strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
										</td>
									</tr>
									
				<%
						}else if(! bFirstLine){
							sApprovalName = "";
							sActivity = (String)lom.get("activity");
							if(sActivity != null && sActivity.equals("1") )
								sApprovalName = (String)lom.get("user_name") + "/" + (String)lom.get("duty") + "/" + (String)lom.get("dept_name");
				%>					
									<script>document.getElementById('sp_approval<%=iFileRowIndex %>').innerHTML = '<%=sApprovalName%>';</script>
									<tr class="Nocol">
										<td>
											<strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
										</td>
									</tr>
				<% 
						} 
						//해당Mis_id의 최종 Row이면	
						if(iMemRow == iMemAllMember) {
							iFile_cnt= Integer.parseInt(lom.get("file_cnt").toString());
							//첨부 파일이 있으면	
							if(iFile_cnt > 0) {
				%>
									<tr class="Nocol">
										<th><spring:message code="las.page.field.contractManager.attchFile"/><%=lom.get("file_cnt") %></th>
										<td>
											<iframe src="<c:url value='/clm/blank.do' />" id="approve_fileList<%=iFileRowIndex %>" name="approve_fileList<%=iFileRowIndex %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="no" ></iframe>
											<input type="hidden" name="approve_fileInfos<%=iFileRowIndex %>"  value="" /> <!-- 이력기타파일정보 -->
											<input type="hidden" name="approve_cnsdreq_id<%=iFileRowIndex %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
										</td>
									</tr>
								<%} %>	
								</table>
							</td>
						</tr>
				<%			 
						}
					}
				}
				%>
					</tbody>
				</table>				
								
				<!-- 승인 이력정보 END -->
				<!-- 승인 이력정보 END -->
				<!-- 승인 이력정보 END -->
							
	
				<!-- subsidiary 배정 START-->
				<!-- subsidiary 배정 START-->

					<div class='title_basic'>
						<h4><spring:message code="las.page.field.statistics.lap.subsidiary"/> <spring:message code="las.page.field.contractManager.reviewEmp"/><!-- subsidiary 검토담당자 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01_resp');" style='cursor;pointer'/></h4>
					</div>
								
					<table id='tr_down01_resp' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
						<colgroup>
							<col width='15%' />
							<col width='85%' />
						</colgroup>							
						<tr>
							<th>
								<spring:message code="las.page.field.contractManager.reviewEmp"/><!-- 검토담당자 -->&nbsp;
							</th>		
							<td>	
								<ul id='list_respman_id' name='list_respman_id' class='las_men' style='width:100%;margin-left:0px;margin-top:2px;'>
								<c:forEach var='list' items='${lomResp}'>
									<li id="<c:out value='${list.list_respman_id}'/>"><c:out value='${list.list_respman_nm}' escapeXml='false'/></li><input type="hidden" name="list_respman_ids" id="id_<c:out value='${list.list_respman_id}'/>" value="<c:out value='${list.list_respman_id}'/>"></input>
								</c:forEach>
								</ul>
							</td>
						</tr>			
					</table>
					
				<!-- subsidiary 배정 END-->
				<!-- subsidiary 배정 END-->
					
				<!-- HQ 배정 START-->
				<!-- HQ 배정 START-->

					<div class='title_basic'>
						<h4>HQ <spring:message code="las.page.field.contractManager.reviewEmp"/><!-- HQ 검토담당자 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01_resp_hq');" style='cursor;pointer'/></h4>
					</div>
								
					<table id='tr_down01_resp_hq' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
						<colgroup>
							<col width='15%' />
							<col width='85%' />
						</colgroup>							
						<tr>
							<th>HQ <spring:message code="las.page.field.contractManager.reviewEmp"/><!-- 검토담당자 -->&nbsp;
								<c:if test="${command.top_role == 'HQ01' }">
									<c:if test="${ lomHq.hq_cnsd_status eq 'C16202' || lomHq.hq_cnsd_status eq 'C16203' }">
										<span class="btn_all_b" onclick="popRespmanHQ();"><span class="check"></span><a href='#'><spring:message code="las.page.button.select"/><!-- 배정팝업버튼 --></a></span>
									</c:if>
								</c:if>
							</th>		
							<td>	
								<ul id='list_respman_id_hq' name='list_respman_id_hq' class='las_men' style='width:100%;margin-left:0px;margin-top:2px;'>
								<c:forEach var='listhq' items='${lomRespHQ}'>
									<li id="<c:out value='${listhq.list_respman_id}'/>"><c:out value='${listhq.list_respman_nm}' escapeXml='false'/></li><input type="hidden" name="list_respman_ids" id="id_<c:out value='${listhq.list_respman_id}'/>" value="<c:out value='${listhq.list_respman_id}'/>"></input>
								</c:forEach>
								</ul>
							</td>
						</tr>			
					</table>
					
					<!-- HQ 배정 END-->
					<!-- HQ 배정 END-->
					
					<!-- HQ Header 검토승인 VIEW START-->
					<!-- HQ Header 검토승인 VIEW START-->
					
					<!-- 	HQ Header 승인 의견이나 반려사유가 있는 경우에만 표시 -->
					<c:if test="${!empty lomHq.HQ_APBT_OPNN || !empty lomHq.HQ_REJ_OPNN }">
					
					<div class='title_basic'>
						<h4> HQ <spring:message code="las.page.field.contractManager.gcComment"/><!-- HQ Header 의견 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01_resp_hq');" style='cursor;pointer'/></h4>
					</div>
						<table id='tr_down01_manager' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
							<colgroup>
								<col width='15%' />
								<col />
							</colgroup>

							<c:if test="${!empty lomHq.HQ_APBT_OPNN}">
								<tr>
									<th>HQ Header Opinion</th>
									<td><c:out value='${lomHq.HQ_APBT_OPNN_TD}' escapeXml="false"/></td>
								</tr>
							</c:if>

							<c:if test="${!empty lomHq.HQ_REJ_OPNN}">
								<tr>
									<th>HQ Header reject reason<!-- 반려 사유 --></th>
									<td><c:out value='${lomHq.HQ_REJ_OPNN_TD}' escapeXml="false"/></td>
								</tr>
							</c:if>								
						</table>
					</c:if>	
					
					<!-- HQ Header 검토승인 VIEW END -->
					<!-- HQ Header 검토승인 VIEW END -->				
					
					
					<!-- 	HQ 배정의견이  있는 경우에만 표시  START -->
					<c:if test="${!empty lomHq.hq_apbt_memo }">
					
					<div class='title_basic'>
						<h4> Message to Reviewer <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01_resp_hq');" style='cursor;pointer'/></h4>
					</div>
						<table id='tr_down01_manager' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
							<colgroup>
								<col width='15%' />
								<col />
							</colgroup>
								<tr>
									<th>Message to Reviewer </th>
									<td><c:out value='${lomHq.hq_apbt_memo_td}' escapeXml="false"/></td>
								</tr>							
						</table>
					</c:if>	
					<!-- 	HQ 배정의견이  있는 경우에만 표시  END -->
					
					<!-- 하단 BUTTON START -->
					<!-- 하단 BUTTON START -->
					<!-- 하단 BUTTON START -->
										
					<div class="btn_wrap_c">
					
						<c:if test="${topRole eq 'HQ01'}" >
						
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16202'}" ><!-- 미 배정 상태 일때 -->		
								<span id="btn_admin_reply_dn"  class="btn_all_w" onclick="popRTN('ADMIN_REPLY');"><span class="mail"></span><a><spring:message code="las.filed.considetration.adminreply"/></a></span><!-- Admin Reply -->		
							</c:if>
							
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16204'}" ><!-- HQ HEAD 승인 대기 상태 일때 -->		
								<span id="btn_reject_dn"  class="btn_all_w" onclick="popRTN('APPR');"><span class="mail"></span><a><spring:message code="las.page.button.approval"/></a></span><!-- 승인 -->
								<span id="btn_reject_dn"  class="btn_all_w" onclick="popRTN('REJECT');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.reviewReject"/></a></span><!-- 반려 -->
							</c:if>
							
							<c:if test="${hasH0102 eq 'Y' && respYnHQ eq 'Y' }" ><!-- HQ01 권한과 HQ02 권한 둘다 가지고 있을 때 그리고 이 의뢰에 대해서 배정을 받았을 때.-->								
								<c:if test="${lomHq.hq_cnsd_status  eq 'C16203' || lomHq.hq_cnsd_status  eq 'C16206' }" ><!-- 검토중(배정완료) 상태 일때 -->		
									<span id="btn_save_dn" class="btn_all_w" style="display:" onclick="returnConsideration('SAVE');" ><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span><!-- 임시저장 -->
									<span id="btn_send_dn" class="btn_all_w" style="display:" onclick="returnConsideration('SEND');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.send"/></a></span><!-- 회신 -->	
								</c:if>				
							</c:if>	
						
						</c:if>
						
						<c:if test="${topRole eq 'HQ02' && respYnHQ eq 'Y' }" >		
							
							<c:if test="${lomHq.hq_cnsd_status  eq 'C16203' || lomHq.hq_cnsd_status  eq 'C16206'}" ><!-- 검토중(배정완료) 상태 혹은 반려 상태 일때 -->		
								<span id="btn_send_dn" class="btn_all_w" style="display:" onclick="returnConsideration('SEND');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.send"/></a></span><!-- 회신 -->	
								<span id="btn_save_dn" class="btn_all_w" style="display:" onclick="returnConsideration('SAVE');" ><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span><!-- 임시저장 -->
							</c:if>
						
						</c:if>
						
						<span id="btn_print_dn" class="btn_all_w"  onclick="retrieveDetail();" ><span class="print"></span><a><spring:message code="las.page.field.contractManager.print"/></a></span><!-- 인쇄 -->
						<span id="btn_list_dn" class="btn_all_w"  onclick="forwardConsideration('LIST');" ><span class="list"></span><a><spring:message code="las.page.field.contractManager.list"/></a></span><!-- 목록 -->

					</div>	

					<!-- 하단 BUTTON END -->
					<!-- 하단 BUTTON END -->
					<!-- 하단 BUTTON END -->	

			
			</form:form>				
				
			</div>
			<!-- //content_in -->	
		</div>
		<!-- //content -->			

	</div>
	<!-- //container -->
</div>

<script type="text/javascript">
/* 	function OnInitCompleted(e){			
		if(e.editorTarget == CrossEditor1){					
			CrossEditor1.SetValue(document.getElementById("body_mime_rq").value); // 컨텐츠 내용 에디터 삽입	
		}	
	}	 */ 			
</script>

<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>		
</body>
</html>