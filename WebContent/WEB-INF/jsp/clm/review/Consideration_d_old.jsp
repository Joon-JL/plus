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
 * 파  일  명 : Consideration_d.jsp
 * 프로그램명 : 의뢰내역 - 검토의뢰 - 계약검토[일반의뢰]
 * 설      명 : 
 * 작  성  자 : 제이남
 * 작  성  일 : 2013.04.01
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	boolean authYN_RA02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("RA02")){
		authYN_RA02 = true;
	}

	ArrayList listDc = (ArrayList) request.getAttribute("listDc");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap) request.getAttribute("lomMn");
	ListOrderedMap lomDcd = (ListOrderedMap) request.getAttribute("lomDcd");

	String userId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
	String userNm = StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "");
	
	String modYn = (String)request.getAttribute("modYn");
	ListOrderedMap lomT;
	StringBuffer resultCt = new StringBuffer(1024);
	StringBuffer resultSb = new StringBuffer(1024);
	
	String plndbnReqYn 	= (String)lomRq.get("plndbn_req_yn");
	String respUserDiv 	= (String)lomRq.get("resp_user_div");
	String autoApbtYn 	= (String)lomRq.get("auto_apbt_yn");
	
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
<meta sci="Consideration_d_old.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
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
			
		//  특화정보 초기화
		$('#tab_contents_sub_conts4').hide();
		$('#tab_contents_sub_conts5').hide();
		$('#tab_contents_sub_conts6').hide();	
	
		var blngt_orgnz 			= $("#blngt_orgnz").val();								//소속조직
		var mn_cnsd_dept			= $("#mn_cnsd_dept").val();								//검토의뢰_정_검토_부서
		var vc_cnsd_dept			= $("#vc_cnsd_dept").val();								//검토의뢰_부_검토_부서
		var depth_status			= "<c:out value='${lomRq.depth_status}' />";			//단계_상태
		var cnsd_status				= "<c:out value='${lomDcd.cnsd_status}' />";			//검토_상태
		var prgrs_status			= $("#prgrs_status").val();								//진행상태
		//결재없이회신버튼 제어를 위하여 추가(2012.08.14)
		var plndbn_req_yn			= $('#plndbn_req_yn').val();							//최종본의뢰여부
		var auto_apbt_yn			= "<%=autoApbtYn%>";									//전결처리여부
		var top_role				= $("#top_role").val();
		var page_div				= $("#page_div").val();									//RESP이면 그룹장이 담당자 검토 실행

		//Ownership 확인
		if(mn_cnsd_dept == "A00000001" || mn_cnsd_dept == "A00000002"){
			if(blngt_orgnz == "A00000001" || blngt_orgnz == "A00000002"){
				$("#table_div").val('A');
			}
		}else if(mn_cnsd_dept == 'A00000003'){
			if(blngt_orgnz == "A00000001" || blngt_orgnz == "A00000002"){
				$("#table_div").val('B');
			}else if(blngt_orgnz == 'A00000003'){
				$("#table_div").val('A');
			}
		}

		//history
		contractHisList(); //2014-09-02 Sungwoo Replaced Contract History. 
		
		//1. 1차 의뢰 배정전 [검토요청 상태] 
		if(depth_status == 'C02606' && prgrs_status == 'C04203' && cnsd_status == ''){
			
			<% if(authYN_RA02){ %>
				$("#btn_up6").show();				
			<%}%> 
		
			if(top_role == 'RA01' || top_role == 'RB01'){
				$("#btn_up_modifyContract").show();			//의뢰내용 수정
				$("#btn_down_modifyContract").show();	
			}else if(top_role == 'RA02'){
				
			}
		//2. 의뢰 배정 후 [담당자 지정,검토의견-임시저장]
		}else if(depth_status == 'C02606' && prgrs_status == 'C04204' && (cnsd_status == '' || cnsd_status == 'C04302')){
			if(top_role == 'RA01' ){
				$("#btn_up_modifyContract").show();			//의뢰내용 수정
				$("#btn_down_modifyContract").show();		
				$("#btn_up6").show();						//검토작성
				$("#btn_down6").show();	
			//전자검토자에게 검토작성을 할 경우 검토유형 지정 문제로 인해 검토작성버튼 안보이도록
			}else if(top_role == 'RB01'){
				$("#btn_up_modifyContract").show();			//의뢰내용 수정
				$("#btn_down_modifyContract").show();	
			}else if(top_role == 'RA02' && "<c:out value='${respYn}' />" == "Y" ){
				$("#btn_up1").show();						//임시저장
				$("#btn_down1").show();		
				$("#btn_div1").val("Y");
				$("#btn_up3").show();						//발신
				$("#btn_down3").show();		
				//전결처리  시 결재없이 회신 버튼은 제외
				if(auto_apbt_yn != 'Y' && page_div != "RESP"){
					$("#btn_up12").show();					//결재없이 회신
					$("#btn_down12").show();
				}
				if(plndbn_req_yn == 'N'){
					$("#btn_up_lastConsideration").show();	//최종본 검토
					$("#btn_down_lastConsideration").show();
				}
				
				$("#btn_up_modifyContract").show();			//의뢰내용 수정
				$("#btn_down_modifyContract").show();	
			}
		//3. 발신 후 [검토의견 작성완료(미결)]
		}else if(depth_status == 'C02606' && prgrs_status == 'C04205' && cnsd_status == 'C04303'){

			if(top_role == 'RA01' || top_role == 'RB01'){
				$("#btn_up4").show();						//검토회신
				$("#btn_down4").show();		
				$("#btn_up5").show();						//미결취소
				$("#btn_down5").show();		
				$("#btn_up11").show();						//검토반려
				$("#btn_down11").show();		
				$("#btn_up_modifyContract").show();			//의뢰내용 수정
				$("#btn_down_modifyContract").show();	
				$("#btn_up1").show();						//임시저장
				$("#btn_down1").show();		
				$("#btn_div1").val("Y");
				
				$("#not_appr_yn").val("Y");
				
			}else if(top_role == 'RA02'  && "<c:out value='${respYn}' />" == "Y" ){
				$("#btn_up5").show();						//미결취소
				$("#btn_down5").show();	
			}
		}
		
		if($("#page_div").val() == "RESP"){					// 담당자 지정
			$("#btn_up7").show();
			$("#btn_down7").show();
		}

		$("#btn_up10").show();					// 인쇄
		$("#btn_down10").show();
		$("#btn_up8").show();					// 목록
		$("#btn_down8").show();

		// 회신된 인터페이스 검토의뢰건일 경우 인터페이스 처리 버튼 활성화
		if($("#top_role").val() == "RA00" && "<c:out value='${lomRq.sys_nm}'/>" != "" && prgrs_status >= "C04205"){
			$("#btn_up13").show();				// 인터페이스 처리
			$("#btn_down13").show();		
		}

		if((blngt_orgnz == mn_cnsd_dept || blngt_orgnz == vc_cnsd_dept) && (prgrs_status == "C04202" || prgrs_status == "C04203" || prgrs_status == "C04204" || prgrs_status == "C04206") && cnsd_status < 'C04303' && depth_status != 'C02607' && depth_status != 'C02603'){
			// 그룹장 권한 버튼[그룹장이 담당자를 지정할 수 있는 화면에서 화면전환이 가능]
			if($("#page_div").val() == "APPR"){				// 검토 회신
				$("#btn_up6").show();
				$("#btn_down6").show();
			}
		}

		$('#list_respman_id li').bind('click', function(){
			$('#list_respman_id li').removeAttr("style", "");
			$('#list_respman_id li').removeClass('selected');
			
			$(this).attr("style", "background: #dddddd;");
			$(this).addClass("selected");		
		});
		
		getCodeSelectByUpCd("frm", "list_user_id", "/common/clmsCom.do?method=getLasPersonComboHTML&combo_type=&combo_selected=&srch_dept=law&cnsdreq_id=" + "<c:out value='${lomRq.cnsdreq_id}'/>");
		getCodeSelectByUpCd("frm", "sub_list_user_id", "/common/clmsCom.do?method=getLasPersonComboHTML&combo_type=&combo_selected=&srch_dept=ip&cnsdreq_id=" + "<c:out value='${lomRq.cnsdreq_id}'/>");
		
		initPage();
		// 준거법
		getCodeSelectByUpCd("frm", "loac", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C022&combo_type=S&combo_del_yn=N&combo_selected=" + "<c:out value='${lomRq.loac}'/>");
		// 분쟁_해결_방법
		getCodeSelectByUpCd("frm", "dspt_resolt_mthd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C023&combo_type=S&combo_del_yn=N&combo_selected=");
			
		
		if("<c:out value='${lomRq.plndbn_req_yn}' />" == "Y"){
			$('#tab_contents_sub_conts4').show();
			$('#tab_contents_sub_conts5').show();
			$('#tab_contents_sub_conts6').show();			
		}
		
		if($('#tab_contents_sub_conts3_1 tr').length==1){
			$('#tr_space3_1').hide();
			$('#div_cnsd3_1').hide();
		}
		
		if($('#tab_contents_sub_conts3_3 tr').length==1){
			$('#tr_space3_3').hide();
			$('#div_cnsd3_3').hide();
		}
		
		if($('#tab_contents_sub_conts3_1 tr').length==1 && $('#tab_contents_sub_conts3_3 tr').length==1){
			$('#tr_down03').hide();
		}
		
		tit_Toggle(this,'tr_down01');
		tit_Toggle(this,'tr_down02');
		
		viewHiddenProgress(false);
		
		// 결재 없이 회신 버튼 감추기 2013-09-30 구주 총괄 요구 사항
		$("#btn_up12").hide();
		$("#btn_down12").hide();

	});
	
	$(window).load(function(){	
		namoView();
	});
	
	function namoView(){
		
		//나모 내용 display

		//1. 검토요청내용
	    document.getElementById('if_cnsd_demnd_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_demnd_cont_textarea').value;

	 	//2. 추진목적 및 배경
	 	document.getElementById('if_cnsd_bg_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_bg_cont_textarea').value;
	 	
	 	//3. 검토의견
	 	if("<c:out value='${modYn}'/>" == "N"){	 		
	 		if("C04303" == "<c:out value='${lomMn.cnsd_status}'/>"){	 			
	 		 	//검토의견
	 		 	document.getElementById('if_cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_opnn_textarea').value;
	 		}
	 		 		
	 		if("C04305" == "<c:out value='${lomMn.cnsd_status}'/>"){
	 		 	//검토의견
	 		 	document.getElementById('if_cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_opnn_textarea').value;
	 		}
	 	}
	}

	/**
	  * 연관계약 타입 선택
	  */
	  function reltypeChange(){
	  	var type = $("#rel_type").val();  
	  	if("C03201" == type){
			$("#rel_defn").html("<option value=NDA><spring:message code='las.page.field.contractmanager.consideration_d.nda' /></option><option value=MOU/LOI><spring:message code='las.page.field.contractmanager.consideration_d.mou_loi' /></option>");
		}else if("C03202" == type){
			$("#rel_defn").html("<option value=Master><spring:message code='las.page.field.contractmanager.consideration_d.master' /></option><option value=Sub><spring:message code='las.page.field.contractmanager.consideration_d.sub' /></option>");
		}else if("C03203" == type){
			$("#rel_defn").html("<option value=변경전><spring:message code='las.page.field.contractmanager.consideration_d.before_change' /></option><option value=해지전><spring:message code='las.page.field.contractmanager.consideration_d.before_cancel' /></option>");
		}else{
			$("#rel_defn").html("<option value=><spring:message code='las.page.field.contractmanager.consideration_d.select_none' /></option>");
		}
	  }
	
	/**
	  * 연관계약 정보 reload
	  */
	  function listRelationContract(){
		  var frm = document.frm;
		  frm.submit_status.value="delete";
		  var options = {
 				url: "<c:url value='/clm/review/consideration.do?method=listRelationContract' />",
 				type: "post",
 				async: false,
 				dataType: "html",
 				success: function (data) {
 					$("#trRelationContract").nextAll().remove();
 					$("#trRelationContract").after(data);
 				}
 			};
	  		$("#frm").ajaxSubmit(options);
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
				  alert("<spring:message code='las.page.msg.contractmanager.consideration_d.select_relation_contract' />");//연관계약 정보을 선택 하여 주시기 바랍니다.
				  $("#rel_type").focus();
				  return;
			  }else if(!$("#rel_type").val()){
				  alert("<spring:message code='las.page.field.contractManager.selectRel'/>");//관계를 선택 하여 주시기 바랍니다.
				  $("#rel_type").focus();
				  return;
			  }
		  }
		  
		  var options = {
			url: "<c:url value='/clm/review/consideration.do?method=actionRelationContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				
				if(responseText.returnVal == 1){
					
					viewHiddenProgress(false); // 진행 상태창이 닫혀지지 않아서 추가함. 김재원 1월 7일 
					
	    			// 2012.04.26 Alert 수정 modified by hanjihoon
	    			viewDlgAlert("<spring:message code='las.msg.succ.save' />");

					//입력폼 초기화 
					$("#rel_type").val("");
					$("#parent_cntrt_id").val("");
					$("#parent_cntrt_name").val("");
					$("#rel_defn").html("<option><spring:message code='las.page.field.contractmanager.consideration_d.option_none' /></option>");//--선택--
					$("#expl").val("");
					window.setTimeout(function() {listRelationContract();},3000);
				}else if(responseText.returnVal == 2){
					alert("<spring:message code='las.page.msg.contractmanager.consideration_d.duplicate_contract' />");//중복된 계약건이 존재 합니다.
				}else{
					alert("<spring:message code='las.page.msg.contractmanager.consideration_d.fail_process' />");//정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	  }
	 
	/**
	* 연관계약 계약 목록 팝업
	*/
	function popupListContract(arg){
		var frm = document.frm;
		
		if(frm.cntrt_nm.value != ""){		
			var v_except_cntrt_id = frm.cntrt_id.value;
		 	PopUpWindowOpen2("/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel&except_cntrt_id="+v_except_cntrt_id , 1000, 600, false, "PopUpRelInfo");
		}else{
		 	alert("<spring:message code='las.page.msg.contractmanager.consideration_d.input_contract_name' />");//계약 기본 정보의 계약명을 선 입력 하세요.
		}
	}
	
	/**
	* 관련계약 팝업 선택 조회
	*/ 
	function returnCntrDetail(cnsdreq_id){
		var frm = document.frm;
		
		$("#cntrt_srch_yn").val("N");
		
		frm.cnsdreq_id.value = cnsdreq_id;
		frm.method.value = "detailConsideration_old";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
		frm.target = "_self";
		
		frm.submit();
	}
	
	/**
	* 계약기본 정보 리턴
	*/
	function detailContractMaster(){
		var options = {
			url: "<c:url value='/clm/review/consideration.do?method=detailContractMaster' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#tab_contents").html("");
				$("#tab_contents").html(data);
				
				viewHiddenProgress(false);
			}
		};
		viewHiddenProgress(true);
		$("#frm").ajaxSubmit(options);
	}
	
	/**
	* 선택 되지 않은 계약건의 필수 조건 체크[ajax]
	*/
	function requiredValidation(arg){
		var options = {
			url: "<c:url value='/clm/review/consideration.do?method=requiredValidation' />",
			type: "post",
			dataType: "json",
			success: function(returnJson,returnMessage) {
				if(returnJson.result == "Y") {
					if(arg == "RESP"){										// 회신
						var display_div = $('input[name="cnsdreq_opnn"]:checked').val();
						
						if($("#top_role").val() == "RA01"){
							if(display_div=="Y"){
								approvalOpnn();
							}else if(display_div=="N"){
								if($("#rejct_opnn").val() == ""){
									alert("<spring:message code='las.page.msg.contractmanager.consideration_info.input_rejct_reason' />");//반려사유를 입력하십시요.
									return;
								}else{
									rejctOpnn();
								}
							}else{
								alert("<spring:message code='las.page.msg.contractmanager.consideration_d.select_cnsd_approve' />");//검토승인을 선택하십시요.
							}				
						}else{
							approvalOpnn();
						}
						return;
					}else{													// 의견전달, 발신
						returnConsideration(arg);
					}
				}else{
					alert(returnJson.message);
					result = false;
				}
			}
	    };
		$("#frm").ajaxSubmit(options);
	}
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		$("#cntrt_srch_yn").val("N");
		
		frm.stat_flag.value = arg;
		
		if(($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01") && arg == "RESP"){	// 의견전달, 회신시 승인의견에 그룹장의견을 대입
			$('input:radio[name=cnsdreq_opnn]:input[value=Y]').attr("checked", true);
			$("#apbt_opnn").val($("#manager_opnn").val());
			$("#rejct_opnn").val("");
		}
		if(arg == "SAVE"  || arg =="DELIVERY" || arg =="DISPATCH" || arg == "NOAPPROVAL"){		// 임시저장, 의견전달, 발신, 결재없이 발신			
			
			if("<c:out value='${command.top_role}' />" == "RA01"){
				var cnsd_opnn = CrossEditor.GetBodyValue();
				frm.cnsd_opnn.value = cnsd_opnn; //  종합검토의견W
			} else {
				var cnsd_opnn = CrossEditor1.GetBodyValue();
				frm.cnsd_opnn.value = cnsd_opnn; //  종합검토의견W
			} 
		
			returnConsideration(arg);
			return;
		}else if(arg == "RESP"){										// 회신
			var cnsd_opnn = CrossEditor.GetBodyValue();
			frm.cnsd_opnn.value = cnsd_opnn; //  종합검토의견
			approvalOpnn();
			return;
		}else if(($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01") && arg == "REJECT"){
			$('input:radio[name=cnsdreq_opnn]:input[value=N]').attr("checked", true);
			$("#apbt_opnn").val("");
			$("#rejct_opnn").val($("#manager_opnn").val());
			
			rejctOpnn();
			
			return;
		}else if(arg == "PROCESSCANCEL"){
			if(!confirm("<spring:message code='las.msg.ask.approvalCancel' />")){//미결취소하시겠습니까?
				return;
			}
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/review/consideration.do' />";
			frm.method.value = "processCancel";		
			frm.submit();
		}else if(arg == "LIST"){									//목록
			
			var prgrs_status			= $("#prgrs_status").val();								//진행상태
			var depth_status			= "<c:out value='${lomRq.depth_status}' />";			//단계_상태
			var cnsd_status				= "<c:out value='${lomDcd.cnsd_status}' />";			//검토_상태
			
			// Mycontract에서 사별이관 자료가 넘어올 경우 myCntract로 이동 시키기 위해서 분기 함. !@#.김재원.20130520
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/myContract.do' />";
			frm.method.value = "listMyContract";
			frm.submit();
		}else if(arg == "CNSDREQ"){//유관부서검토요청
			PopUpWindowModalOpenCnsd("/clm/review/consideration.do?method=listConsideration_under_d&menu_id=20111021144301108_0000000245&cnsdreq_id="+$("#cnsdreq_id").val(), 600, 230, false);
		}else if(arg == "CNSDRESP"){		// 담당자 검토
			if($("#id_" + "<%=userId%>").val() != "<%=userId%>"){			// 그룹장을 담당자로 지정
				if($('#list_respman_id input').length == 3){
					alert("<spring:message code='las.msg.alert.consideration.respmanSelect4' />");//로그인 사용자를 포함한 담당자는 최대 3명까지 지정하실수 있습니다.
					return;
				}else if(!confirm("<spring:message code='las.msg.ask.assignResp' />")){//로그인 사용자가 담당자목록에 없습니다.\\n담당자로 지정하시겠습니까?
					return;
				}
				
			    $('#list_respman_id').append("<li id='" + "<%=userId%>" + "'>" + "<%=userNm%>" + "</li><input type='hidden' name='list_respman_ids' id='" + 'id_' + "<%=userId%>" + "' value='" + "<%=userId%>" + "'></input>");
			    
			    $("#cnsd_resp_div").val("Y");			// 그룹장의 검토작성 실행 여부
			    $("#main_man_id").val("<%=userId%>"); 	// 그룹장을 정검토자로 지정
			    
				var options = {
						url: "<c:url value='/clm/review/consideration.do?method=confirmRespman' />",
						type: "post",
						dataType: "json",
			    		success: function(responseText, statusText){
			    			viewHiddenProgress(false);
			    			
			    			if(responseText != null && responseText.length != 0) {
			    				viewDlgAlert(responseText.returnMessage);
				    			// 그룹장의 검토작성 실행 여부 초기화
				    			$("#cnsd_resp_div").val('N');
			    				
								$("#page_div").val("RESP");
								frm.target = "_self";		 
								frm.action = "<c:url value='/clm/review/consideration.do' />";
								frm.method.value = "detailConsideration_old";
								frm.submit();
			    			}
			    		}
				}
				
				viewHiddenProgress(true);
		    	$("#frm").ajaxSubmit(options);
			}else{
				$("#page_div").val("RESP");
				frm.target = "_self";		 
				frm.action = "<c:url value='/clm/review/consideration.do' />";
				frm.method.value = "detailConsideration_old";
				frm.submit();
			}
		}else if(arg == "CNSDAPPR"){		// 그룹장 검토
			
			$("#page_div").val("APPR");
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/review/consideration.do' />";
			frm.method.value = "detailConsideration_old";
			frm.submit();
		}
	}	

	/**
	* 의뢰 상세 내역
	*/
	function detailConsideration(){
		var frm = document.frm;
		
		frm.method.value = "detailConsideration_old";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
		frm.target = "_self";		
		frm.submit();
	}
	
	/**
	* 모달 팝업
	*/
	function PopUpWindowModalOpenCnsd(surl, popupwidth, popupheight, bScroll){
		
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
		
		var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:no;resizable:no;";
		var objReturnValue = window.showModalDialog(surl, window, sFeatures);
		
		if(objReturnValue != undefined){
			if(objReturnValue.applyRespYn == "Y"){
				titleTabClick("applyResp", $("#save_cntrt_id").val());
				$("#vc_cnsd_demnd_cont").val(objReturnValue.vcCnsdDemndCont);
				$("#dmstfrgn_gbn").val(o.dmstfrgnGbn); //국내/해외 구분
				implJson("applyResp");						// 유관 부서 검토 요청
			}
		}
	}		  
	
	//계약기본 정보 수정 
	function forwardContractMaster(){
		 var frm = document.frm;		 
		
		 PopUpWindowOpenWithName('', 1000, 700, 'modifyContract',false);		 
		 frm.action = "<c:url value='/clm/review/consideration.do' />";
		 frm.method.value = "forwardContractMaster";
		 frm.target = "modifyContract";		
		 frm.submit();	
	}
	
	function setModifyContractMaster(){
		viewDlgAlert("<spring:message code='las.page.field.contractManager.processCmplt'/>");//계약 내용이 정상적으로 처리 되었습니다.
		detailContractMaster();
	}
	
	/**
	* 임시저장, 의견전달, 발신 
	*/
	function returnConsideration(arg){
		
		var frm = document.frm;
		var temp_msg = "";

		if("Y" == frm.respUserDiv.value){
			
			if($("#arr_attr_seqno").val() == 2){
				// 특화속성 값 설정
				
				if($("#arr_attr_cont_idp").val() == "4" && $("#arr_attr_cont_idp_val").val() == ""){
					$("#arr_attr_cont_idp_val").val("4");
				}
			}
		
		}

		if(arg=="SAVE"){
			$('#attach_file_div').val("N");
			$("#stat_flag").val(arg);
			temp_msg = "<spring:message code='las.msg.succ.save' />"; // Saved.
		}else if(arg=="DELIVERY"){		// 의견전달
			temp_msg = "<spring:message code='las.page.button.resolved' />"; //  Replied
		}else if(arg=="DISPATCH" || arg == "NOAPPROVAL"){
			temp_msg = "<spring:message code='las.page.button.resolved' />"; //  Replied
		}else{
			return;
		}	
		var options = {
			url: "<c:url value='/clm/review/consideration.do?method=returnConsideration' />",
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
	
	function frmSubmit(options,arg){
		var frm = document.frm;
		var display_div = $('input[name="cnsdreq_opnn"]:checked').val();
		var msg;
		
		//그룹장권한
		<%if( ("RA01".equals(command.getTop_role()) || "RB01".equals(command.getTop_role())) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){%>
			frm.body_mime_rq.value =CrossEditor.GetBodyValue(); // 2013-10-01 박병주 크로스에디터 적용		
			
			if(arg =="DELIVERY" || arg =="DISPATCH" || arg == "RESP" || arg == "NOAPPROVAL"){
				if(arg=="DELIVERY"){		// 의견전달
					if(!confirm("<spring:message code='secfw.msg.ask.delivery' />")){//의견전달하시겠습니까?
						return;
					}
				}else if(arg=="DISPATCH"){	// 발신
					if(!confirm("<spring:message code='secfw.msg.ask.send' />")){//발신하시겠습니까?
						return;
					}
					if($("#page_div").val() == "RESP"){
						$("#stat_flag").val("DISPATCH");
					}
				}else if(arg=="NOAPPROVAL"){	// 결재없이 회신
					if(!confirm("<spring:message code='secfw.msg.ask.noApproval' />")){//\\"결재없이 회신\\"을 선택하면 법무그룹장의 승인을 거치지 않고\\n의뢰인에게 검토 내용이 바로 회신됩니다.\\n이에 대한 책임은 검토자에게 있습니다.\\n법무그룹장의 승인을 받으시려면 \\"결재없이 회신\\"이 아니라\\n\\"발신\\" 버튼을 누르시기 바랍니다.\\n\\n계속 진행하시겠습니까?
						return;
					}
					$("#stat_flag").val("DISPATCH");
					arg = "DISPATCH";
				}
			}else if(!(arg =="LIST" || arg == "PROCESSCANCEL")){
				if(!validateTempSave(frm)){
					return;
				}
			}else if(arg == "SAVE"){		// 임시저장
				if(!confirm("<spring:message code='secfw.msg.ask.tempSave' />")){//임시저장하시겠습니까?
					return;
				}
			}
			if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){				

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
		  								// viewHiddenProgress(true);
		  	  							$("#frm").ajaxSubmit(options);
	  								});
	  							});
							});
						});
					});
				});
			}else{
				fileListReContract.UploadFile(function(uploadCount){
					fileListReEtc.UploadFile(function(uploadCount){
						fileListReGita.UploadFile(function(uploadCount){
							viewHiddenProgress(true);
							$("#frm").ajaxSubmit(options);
						});
					});
				});
			}
			
		<%}else if( ("RA01".equals(command.getTop_role()) || "RB01".equals(command.getTop_role())) && lomRq.get("vc_cnsd_dept").equals(command.getBlngt_orgnz())){%>
			
			if(arg =="DELIVERY" || arg =="DISPATCH" || arg == "RESP" || arg == "NOAPPROVAL"){
				if($("#top_role").val() == "RA02" && !validateForm(frm)){
					return;
				}
				
				if(arg=="DELIVERY"){		// 의견전달
					if(display_div == "Y"){			// 부_부서 검토의견 승인
						if(!confirm("<spring:message code='secfw.msg.ask.delivery' />")){//의견전달하시겠습니까?
							return;
						}
					}else if(display_div == "N"){	// 부_부서 검토의견 반려
						if($("#rejct_opnn").val() == ""){
							alert("<spring:message code='las.page.msg.contractmanager.consideration_info.input_rejct_reason' />");//반려사유를 입력하십시요.
							return;
						}
					}else{
						alert("<spring:message code='las.page.msg.contractmanager.consideration_d.select_cnsd_approve' />");//검토승인을 선택하십시요
						return;
					}
				}else if(arg=="DISPATCH"){	// 발신
					if(!confirm("<spring:message code='secfw.msg.ask.send' />")){//발신하시겠습니까?
						return;
					}
					if($("#page_div").val() == "RESP"){
						$("#stat_flag").val("DISPATCH");
					}
				}else if(arg == "NOAPPROVAL"){//결재없이 회신일 경우 추가(auto_apbt_yn 를 'Y'로 세팅하고 발신으로 변경하여 넘김)
					if(!confirm("<spring:message code='secfw.msg.ask.noApproval' />")){//\\"결재없이 회신\\"을 선택하면 법무그룹장의 승인을 거치지 않고\\n의뢰인에게 검토 내용이 바로 회신됩니다.\\n이에 대한 책임은 검토자에게 있습니다.\\n법무그룹장의 승인을 받으시려면 \\"결재없이 회신\\"이 아니라\\n\\"발신\\" 버튼을 누르시기 바랍니다.\\n\\n계속 진행하시겠습니까?
						return;
					}
					$("#stat_flag").val("DISPATCH");
					arg = "DISPATCH";
				}
			}else if(!(arg =="LIST" || arg == "PROCESSCANCEL")){
				if($("#top_role").val() == "RA02" && !validateTempSave(frm)){
					return;
				}
			}else if(arg == "SAVE"){		// 임시저장
				if(!confirm("<spring:message code='secfw.msg.ask.tempSave' />")){//임시저장하시겠습니까?
					return;
				}
			}
			viewHiddenProgress(true);
			$("#frm").ajaxSubmit(options);
		
		<%}else if( "RA02".equals(command.getTop_role()) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())) { //로그인자가 정검토부서 이고  담당자인경우 InserTmode%>
			if( "Y" == $('#plndbn_req_yn').val() && ( "SAVE" == arg || "DISPATCH" == arg || "TABSAVE" == arg ) ){ //정검토 부서 이며 담당자 최종본이면서 임시저장 발신시 자동갱신여 체크해야 함								
				if ( typeof($('input:radio[name=auto_rnew_yn]:checked').val()) == "undefined" ){
	            	alert("<spring:message code='las.page.field.contractManager.chkAutoExtn'/>");	//자동 연장 여부를 선택하세요.
	            	chk_flag = false;	            	
	            	return;
				}
			}
			frm.body_mime_rq.value =CrossEditor1.GetBodyValue(); // 2013-10-01 박병주 크로스에디터 적용		
			
			if(arg =="DELIVERY" || arg =="DISPATCH" || arg == "RESP" || arg == "NOAPPROVAL"){
				
				if($("#top_role").val() == "RA02" ) {
					
					//필수체크 추가(문서모드 IE9)
					var msgVal = ""; 
					if("<c:out value='${lomRq.plndbn_req_yn}'/>" == "Y"){ //최종본
						if(frm.loac.value == ""){
							msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_rq' />";//준거법
							alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
							return;
						}
						if(frm.loac.value == "C02211" && frm.loac_etc.value == ""){//기타(자유기술)
							msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />";//준거법상세
							alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
							return;
						}			
						msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />"; //최종검토의견
					}else{
						msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />"; //종합검토의견
					}
					
					//검토의견
					if(stripHTMLtag(frm.body_mime_rq.value) == ""){
						alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
						return;
					}
				}
				
				if(arg=="DELIVERY" ){		// 의견전달
					msg = "<spring:message code='secfw.msg.ask.delivery'/>";//의견전달하시겠습니까?			
					if(!confirm(msg)){
						return;
					}
				}else if(arg=="DISPATCH"){	// 발신
					msg = "<spring:message code='secfw.msg.ask.send' />";//발신하시겠습니까?
					if(!confirm(msg)){
						return;
					}
					if($("#page_div").val() == "RESP"){
						$('#auto_apbt_yn').val("Y");
						$("#stat_flag").val("DISPATCH");
					}
				}else if(arg == "NOAPPROVAL"){//결재없이 회신일 경우 추가(auto_apbt_yn 를 'Y'로 세팅하고 발신으로 변경하여 넘김)
					msg = "<spring:message code='secfw.msg.ask.noApproval' />";//\\"결재없이 회신\\"을 선택하면 법무그룹장의 승인을 거치지 않고\\n의뢰인에게 검토 내용이 바로 회신됩니다.\\n이에 대한 책임은 검토자에게 있습니다.\\n법무그룹장의 승인을 받으시려면 \\"결재없이 회신\\"이 아니라\\n\\"발신\\" 버튼을 누르시기 바랍니다.\\n\\n계속 진행하시겠습니까?
					$("#stat_flag").val("DISPATCH");
					arg = "DISPATCH";
					
					if(!confirm(msg)){
						return;
					}
				}
			}else if(!(arg =="LIST" || arg == "PROCESSCANCEL")){
				if(!validateTempSave(frm)){
					return;
				}
			}else if(arg == "SAVE"){		// 임시저장
				if(!confirm("<spring:message code='secfw.msg.ask.tempSave' />")){//임시저장하시겠습니까?
					return;
				}
			}
			if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){				
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
		  								// viewHiddenProgress(true);
		  	  							$("#frm").ajaxSubmit(options);
	  								});
	  							});
							});
						});
					});
				});
			}else{				
				viewHiddenProgress(true);
				fileListReContract.UploadFile(function(uploadCount){
					fileListReEtc.UploadFile(function(uploadCount){
						fileListReGita.UploadFile(function(uploadCount){
							// viewHiddenProgress(true);
							$("#frm").ajaxSubmit(options);
						});
					});
				});
			}
		 	
		<%}else if ("RA02".equals(command.getTop_role())&& lomRq.get("vc_cnsd_dept").equals(command.getBlngt_orgnz())) { //로그인자가 부검토부서 이고  담당자인경우 InserTmode%>
			
			if(arg =="DELIVERY" || arg =="DISPATCH" || arg == "RESP" || arg == "NOAPPROVAL"){
				
				if(arg=="DELIVERY"){		// 의견전달
					msg = "<spring:message code='secfw.msg.ask.delivery' />";
					if(!confirm(msg)){
						return;
					}
				}else if(arg=="DISPATCH"){	// 발신
					msg = "<spring:message code='secfw.msg.ask.send' />";//발신하시겠습니까?
					if(!confirm(msg)){
						return;
					}
				}else if(arg == "NOAPPROVAL"){//결재없이 회신일 경우 추가(auto_apbt_yn 를 'Y'로 세팅하고 발신으로 변경하여 넘김)
					msg = "<spring:message code='secfw.msg.ask.noApproval' />";//\\"결재없이 회신\\"을 선택하면 법무그룹장의 승인을 거치지 않고\\n의뢰인에게 검토 내용이 바로 회신됩니다.\\n이에 대한 책임은 검토자에게 있습니다.\\n법무그룹장의 승인을 받으시려면 \\"결재없이 회신\\"이 아니라\\n\\"발신\\" 버튼을 누르시기 바랍니다.\\n\\n계속 진행하시겠습니까?
					$("#stat_flag").val("DISPATCH");
					arg = "DISPATCH";
					
					if(!confirm(msg)){
						return;
					}
				}
			}else if(!(arg =="LIST" || arg == "PROCESSCANCEL")){
				if(!validateTempSave(frm)){
					return;
				}
			}else if(arg == "SAVE"){		// 임시저장
				if(!confirm("<spring:message code='secfw.msg.ask.tempSave' />")){//임시저장하시겠습니까?
					return;
				}
			}
			viewHiddenProgress(true);
  			fileListReVcContract.UploadFile(function(uploadCount){
	  	  		fileListReVcEtc.UploadFile(function(uploadCount){
	  	  			fileListReVcGita.UploadFile(function(uploadCount){
		  	  			//viewHiddenProgress(true);
		  				$("#frm").ajaxSubmit(options);
	  	  			});
				});	  	  	  			
		 	});
		<%}%>
	}
	
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
	    if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){
	    	$("#img_step_q").show();	 
	    	$("#ico_maxsize1").show();	 
	    	$("#ico_maxsize2").show();	 
	    	$("#ico_maxsize3").show();	 
	    	$("#ico_maxsize4").show();
	    	$("#fileListContract").addClass("addfile_iframe");	 	    
	    	$("#fileListEtc").addClass("addfile_iframe");	 
	    	$("#fileListGita").addClass("addfile_iframe");	 
		   	$("#fileListOL").addClass("addfile_iframe");	 
    		frm.view_gbn.value = "modify";
			frm.submit();
	    }else{
	    	$("#img_step_q").hide();	    
	    	$("#ico_maxsize1").hide();	 
	    	$("#ico_maxsize2").hide();	 
	    	$("#ico_maxsize3").hide();	 
	    	$("#ico_maxsize4").hide();
	    	$("#fileListContract").addClass("addfile_iframe_d");	 	
	    	$("#fileListEtc").addClass("addfile_iframe_d");	
	    	$("#fileListGita").addClass("addfile_iframe_d");	
	    	$("#fileListOL").addClass("addfile_iframe_d");	 
    		frm.view_gbn.value = "download";
    		frm.submit();
	    }
	    
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
	    if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){
    		frm.view_gbn.value = "modify";
			frm.submit();
	    }else{
    		frm.view_gbn.value = "download";
    		frm.submit();
	    }
	    
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
	    if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){
    		frm.view_gbn.value = "modify";
			frm.submit();
	    }else{
    		frm.view_gbn.value = "download";
    		frm.submit();
	    }
	    
	 	// 의뢰 -  OUTLOOK
	    frm.target          = "fileListOL";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage"; 
	    frm.file_bigclsfcn.value = "O001";
	    frm.file_midclsfcn.value = "O00101";
	    frm.file_smlclsfcn.value = "O00111";
		frm.ref_key.value = file_key;
		frm.multiYn.value = "Y";
	    frm.fileInfoName.value = "fileInfoOL";
	    frm.fileFrameName.value = "fileListOL";
   		frm.view_gbn.value = "download";
   		frm.submit();
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
	    frm.submit();
	    
	    //===========================
	    // inner_d_view 추가
	    //===========================		
	    
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
		  	  	frm.submit();
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
		
		// 담당자메모 Display
		if($("#respman_apnt_yn").val() == "Y"){
			$("#tr_cnsd_memo").show();
		}
		
		document.getElementById('tr_down03').style.display = "block";
		
		// 2012.03.28 활성화 계약건의 상태에 따라 수정 버튼 Display added by hanjihoon
		// 시스템 관리자 ,법무관리자,범무담당자,시스템 운영자만 수정 권한 있음
		
		if($("#con_depth_status").val() != "C02603" && ($("#top_role").val() =='RA00' || $("#top_role").val() == 'RA01' || $("#top_role").val() == 'RA02' || $("#top_role").val() == 'RM00')){
			$("#btn_up_modifyContract").show();
			$("#btn_down_modifyContract").show();
		}else{
			$("#btn_up_modifyContract").hide();
			$("#btn_down_modifyContract").hide();
		}
		
		//========================
		// inner_d 추가
		//========================
		if("<c:out value='${modYn}'/>" == "Y"){
			//연관계약 관계유형		
			getCodeSelectByUpCd("frm", "rel_type", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_select=");
			
			// 검토담당자, 사안개요 Display
			if( $("#mn_cnsd_dept").val() == $("#blngt_orgnz").val()  && ("A00000001" == $("#blngt_orgnz").val() || "A00000002" == $("#blngt_orgnz").val()) ){
				$("#tr_cnsdman_info").show();
				//$("#tr_main_matr_cont").show();
			}
			// 2012.02.03 법무팀이 정검토부서일 경우에만 사안개요를 작성 modified by hanjihoon
			if( $("#mn_cnsd_dept").val() == $("#blngt_orgnz").val() && ("A00000001" == $("#blngt_orgnz").val() || "A00000002" == $("#blngt_orgnz").val()) ){
				$("#tr_main_matr_cont").show();
			}
			
			// 자동갱신여부 Edit 가능여부 판단
			// 2012.01.31 계약 보류건은 VIEW 전환 modified by hanjihoon
			if($("#con_depth_status").val() != "C02603" && ($("#top_role").val() == "RA02" && "<c:out value='${lomRq.resp_user_div}' />" == "Y")){
				$("input:radio[name=auto_rnew_yn]").attr("disabled","");
			}
			// 2012.01.31 계약 보류건은 VIEW 전환 added by hanjihoon
			/* 버튼은 325 line 에서 제어
			if($("#con_depth_status").val() == "C02603"){		// 계약 DROP건일경우, 임시저장, 의견전달, 발신, 회신, 검토반려  버튼을 Hidden
	 			$("#btn_up1").hide();
	 			$("#btn_down1").hide();
	 			$("#btn_up2").hide();
	 			$("#btn_down2").hide();
	 			$("#btn_up3").hide();
	 			$("#btn_down3").hide();
	 			$("#btn_up4").hide();
	 			$("#btn_down4").hide();
			 	$("#btn_up11").hide();
				$("#btn_down11").hide();
			}else{ 
				if($("#btn_div1").val() == "Y"){				// 임시저장
	 				$("#btn_up1").show();
	 				$("#btn_down1").show();
				}
				if($("#btn_div2").val() == "Y"){				// 의견전달
	 				$("#btn_up2").show();
	 				$("#btn_down2").show();
	 	 			if($("#top_role").val() == "RA01"){							// 반려
	 	 			 	$("#btn_up11").show();
	 	 				$("#btn_down11").show();
	 	 			}
				}
				if($("#btn_div3").val() == "Y"){				// 발신
	 				$("#btn_up3").show();
	 				$("#btn_down3").show();
		 			
	 				// 결재없이 회신 
	 				var autoApbt = "<%=autoApbtYn%>";
	 				if(autoApbt != "Y"){
		 				$("#btn_up12").show();
						$("#btn_down12").show();
					}
				}
				if($("#btn_div4").val() == "Y"){				// 회신
	 				$("#btn_up4").show();
	 				$("#btn_down4").show();
	 	 			if($("#top_role").val() == "RA01"){							// 반려
	 	 			 	$("#btn_up11").show();
	 	 				$("#btn_down11").show();
	 	 			}
				}
			}
			*/
			
			
			if($("#top_role").val() == "RA02"){			 
				if(($("#blngt_orgnz").val() == $("#mn_cnsd_dept").val())){
					// -2012.01.09 이관확인중 진행상태일 경우 임시저장 허용 modified by hanjihoon
					if("Y" == $("#mn_respman_apnt_yn").val() && ("C04202" == $("#prgrs_status").val() || "C04203" == $("#prgrs_status").val() || "C04204" == $("#prgrs_status").val() || "C04205" == $("#prgrs_status").val() || "C04206" == $("#prgrs_status").val() || "C04207" == $("#prgrs_status").val())){ //정검토부서 이면서 지정여부 가 Y 일때 검토의견 활성화				
						$("#tr_down03").attr("style","display:");
					}
					if("Y" != $('#plndbn_req_yn').val()){ //담당자 이면서 최종본 일경우만 비밀유지기간 입력가능
						$("#secret_keepperiod").attr("readOnly", true);
					}
					if("C02603" == $("#con_depth_status").val() || ("C04303" == $("#mn_cnsd_status").val()  || "C04305" == $("#mn_cnsd_status").val())){	//미결 또는 검토 완료인 경우 
						$("#secret_keepperiod").attr("readOnly", true);
						$("#td_loac").html("<c:out value='${lomRq.loac_nm}' escapeXml='false'/>");
						$("#loac_etc").attr("readOnly", true);
						$("#dspt_resolt_mthd_det").attr("readOnly", true);
						$("#oblgt_lmt").attr("readOnly", true);
						$("#spcl_cond").attr("readOnly", true);
						
						//연관계약 표시 편집					
						$("#trRelationContract").remove();
						$("#trRelationContractMsg").hide();
					}
				}else{		//부 검토부서 부 담당자
					$("#secret_keepperiod").attr("readOnly", true);
					$("#td_loac").html("<c:out value='${lomRq.loac_nm}' escapeXml='false'/>");
					$("#loac_etc").attr("readOnly", true);
					$("#dspt_resolt_mthd_det").attr("readOnly", true);
					$("#oblgt_lmt").attr("readOnly", true);
					$("#spcl_cond").attr("readOnly", true);
					
					//연관계약 표시 편집 
					$("#trRelationContract").remove();
					$("#trRelationContractMsg").hide();
					//4203 의뢰
					// -2012.01.09 이관확인중 진행상태일 경우 임시저장 허용 modified by hanjihoon
					if("Y" == $("#vc_respman_apnt_yn").val() && ("C04202" == $("#prgrs_status").val() || "C04203" == $("#prgrs_status").val() || "C04204" == $("#prgrs_status").val() || "C04205" == $("#prgrs_status").val() || "C04206" == $("#prgrs_status").val() || "C04207" == $("#prgrs_status").val()) ){ //부검토부서 이면서 부담당자 지정 여부가 Y 일때 검토의견 활성화				
						$("#tr_down03").attr("style","display:");
					}
					
					//로그인한 사람이 배정자 이면 EDIT 화면  아니면 VIEW 화면  
				}
				
			}else if($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01" ){//그룹장인 경우 
				 
				$("#td_loac").html("<c:out value='${lomRq.loac_nm}' escapeXml='false'/>");
				
				//연관계약 표시 편집 
				$("#trRelationContract").remove();
				$("#trRelationContractMsg").hide();			
				$('span[id=id_relCImg]').remove();  //삭제 이미지 없애기
				
				// 2012.02.14 부서검토_검토상태가 작성중일 경우 검토의견과 첨부파일을 VIEW모드로 전환 modified by hanjihoon
				if($("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && "Y" == $("#mn_respman_apnt_yn").val() && ($("#mn_cnsd_status").val() == "C04302" || $("#mn_cnsd_status").val() == "C04303" || $("#mn_cnsd_status").val() == "C04305" || $("#vc_cnsd_status").val() == "C04302" || $("#vc_cnsd_status").val() == "C04303" || $("#vc_cnsd_status").val() == "C04305") ){
					$("#tr_down03").attr("style","display:");
				}
			}
		}		
		
	    // 담당 그룹장/변호사/전자검토자
		if( (  ($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01") && (($("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#mn_cnsd_status").val() == "C04303") )) 
				|| ( $("#top_role").val() == "RA02" && "<c:out value='${respYn}' />" == "Y" && $("#respman_apnt_yn").val() == "Y" && ($("#cnsd_status").val() == "" || $("#cnsd_status").val() == "C04302")) ){	
	    <% if( "RA01".equals(command.getTop_role()) || "RB01".equals(command.getTop_role())){ // 그룹장인경우 VieWmode %>
		    <%if(lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz()) && ("C04305".equals(lomMn.get("cnsd_status")) || "C04303".equals(lomMn.get("cnsd_status")))){%>
		    		// 회신 - 정_계약서
				    frm.target          = "fileListReContract";
				    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
				    frm.method.value    = "forwardClmsFilePage";
				    frm.file_bigclsfcn.value = "F012";
				    frm.file_midclsfcn.value = "F01202";
				    frm.file_smlclsfcn.value = "F0120202";
					frm.ref_key.value = file_key;
					frm.multiYn.value = "N";
				    frm.fileInfoName.value = "fileInfosReContract";
				    frm.fileFrameName.value = "fileListReContract";
			    <% if(!"C04305".equals(lomRq.get("cnsd_status")) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){%>
					frm.view_gbn.value = "modify";
					frm.submit();
				<%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
				<%}%>
				    
					// 회신 - 정_첨부/별첨
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
				<% if(!"C04305".equals(lomRq.get("cnsd_status")) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){%>
					frm.view_gbn.value = "modify";
					frm.submit();
				<%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
				<%}%>
			
					// 회신 - 정_기타
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
				<% if(!"C04305".equals(lomRq.get("cnsd_status")) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){%>
					frm.view_gbn.value = "modify";
					frm.submit();
				<%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
				<%}%>
			<%}%>
				
	    <%}else if ( "RA02".equals(command.getTop_role()) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())) { //로그인자가 정검토부서 이고  담당자인경우 InserTmode%>
			// 2012.03.12 정부서가 아니면 정부서의 검토의견, 계약서 첨부파일을 정부서의 회신 이후 조회 가능 added by hanjihoon
			<%if (lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz()) || "C04305".equals(lomRq.get("cnsd_status"))) {%>
					// 회신 - 정_계약서
				    frm.target          = "fileListReContract";
				    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
				    frm.method.value    = "forwardClmsFilePage";
				    frm.file_bigclsfcn.value = "F012";
				    frm.file_midclsfcn.value = "F01202";
				    frm.file_smlclsfcn.value = "F0120202";
					frm.ref_key.value = file_key;
					frm.multiYn.value = "N";
				    frm.fileInfoName.value = "fileInfosReContract";
				    frm.fileFrameName.value = "fileListReContract";
			    <%if (!lomMn.isEmpty() && ("C04303".equals(lomMn.get("cnsd_status"))	|| "C04305".equals(lomMn.get("cnsd_status")))) {%>
			    	frm.view_gbn.value  = "download";
			    	frm.submit();
			    <%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
			    <%}%>
				    
			 		// 회신 - 정_첨부/별첨
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
			    <%if (!lomMn.isEmpty() && ("C04303".equals(lomMn.get("cnsd_status")) || "C04305".equals(lomMn.get("cnsd_status")))) {%>
				    frm.view_gbn.value = "download";
				    frm.submit();
			    <%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
			    <%}%>
			    
					// 회신 - 정_기타
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
				<%if (!lomMn.isEmpty() && ("C04303".equals(lomMn.get("cnsd_status")) || "C04305".equals(lomMn.get("cnsd_status")))) {%>
				    frm.view_gbn.value = "download";
				    frm.submit();
			    <%}else{%>
			    	if($("#con_depth_status").val() == "C02603"){
			    		frm.view_gbn.value = "download";
			    		frm.submit();
			    	}else{
			    		frm.view_gbn.value = "modify";
						frm.submit();
			    	}
			    <%}%>
		    <%}%>
		<%}%>
		}
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
		
		var options = {
				url: "<c:url value='/clm/review/consideration.do?method=" + method + "' />",
				type: "post",
				dataType: "json",
	    		success: function(responseText, statusText){
	    			viewHiddenProgress(false);
	    			
	    			if(responseText != null && responseText.length != 0) {
	    				viewDlgAlert(responseText.returnMessage);
		    			
		    			// 2012.04.20 미배정 리스트로 리턴 modified by hanjihoon
		    			if($("#cnsd_resp_div").val() == "N" && method == "confirmRespman"){
		    				
							if($("#blngt_orgnz").val() == "A00000001" || $("#blngt_orgnz").val() == "A00000002"){
								if($("#srch_law_status").val() != "C04224"){
									$("#curPage").val('1');
									$("#srch_law_status").val('C04224');
								}
							}else if($("#blngt_orgnz").val() == "A00000003"){
								if($("#srch_ip_status").val() != "C04224"){
									$("#curPage").val('1');
									$("#srch_ip_status").val('C04224');
								}
							}
							window.setTimeout(function() {forwardConsideration('LIST');},3000);		//계약검토 목록으로 리턴
		    			}else if(method == "transLawDept" || method == "approvalOpnn" || method == "rejctOpnn" || method == "applyResp"){
		    				window.setTimeout(function() {forwardConsideration('LIST');},3000);		//계약검토 목록으로 리턴
		    			}else{
		    				window.setTimeout(function() {goDetail('LIST');},3000);		//계약검토 목록으로 리턴
		    			}
		    			
		    			// 그룹장의 검토작성 실행 여부 초기화
		    			$("#cnsd_resp_div").val('N');
	    			}
	    		}
		};
		
		if(method == "approvalOpnn"){
			
			if("RA01" == $("#top_role").val() || "RB01" == $("#top_role").val()){				// 그룹장 
				frm.body_mime_rq.value =CrossEditor.GetBodyValue(); // 2013-10-01 박병주 크로스에디터 적용	
				
				if($("#top_role").val() == "RA02" && !validateForm(frm)){
					return;
				}
				//필수체크 추가(문서모드 IE9)
				if(stripHTMLtag(frm.body_mime_rq.value) == "" || stripHTMLtag(frm.body_mime_rq.value) == "&nbsp;"){
					
					var msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />"; //종합검토의견
					
					if("<c:out value='${lomRq.plndbn_req_yn}'/>" == "Y"){ //최종본
						msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />"; //최종검토의견
					}
					
					alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
					return;
				}
				if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){				
					
					viewHiddenProgress(true);
					fileListContract.UploadFile(function(uploadCount){
						// 최종본일 경우 계약서파일 필수로 변경
						if(uploadCount == 0 && (arg == "DISPATCH" || arg == "RESP" || arg=="NOAPPROVAL") ){
							viewHiddenProgress(false);
							alert("<spring:message code='clm.page.field.approval.makeApprovalContractInfo58'/> <spring:message code='secfw.msg.error.fileNon'/>");//첨부파일은 필수입니다.
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
				}else{
					viewHiddenProgress(true);				
					fileListReContract.UploadFile(function(uploadCount){
						fileListReEtc.UploadFile(function(uploadCount){
							fileListReGita.UploadFile(function(uploadCount){								
								$("#frm").ajaxSubmit(options);
							});
						});
					});
				}
			}else if("RA02" == $("#top_role").val()){		// 정검토부서 담장자 회신
				frm.body_mime_rq.value =CrossEditor1.GetBodyValue(); // 2013-10-01 박병주 크로스에디터 적용	
				
				if($("#top_role").val() == "RA02" && !validateForm(frm)){
					return;
				}
				if($('#plndbn_req_yn').val() == "Y" && $("#blngt_orgnz").val() == $("#mn_cnsd_dept").val() && $("#respman_apnt_yn").val() == "Y" && $("#btn_div1").val() == "Y"){				
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
				}else{
					viewHiddenProgress(true);				
					fileListReContract.UploadFile(function(uploadCount){
						fileListReEtc.UploadFile(function(uploadCount){
							fileListReGita.UploadFile(function(uploadCount){
								$("#frm").ajaxSubmit(options);
							});
						});
					});
				}
			}else{
				viewHiddenProgress(true);
				$("#frm").ajaxSubmit(options);
			}
		}else{
			viewHiddenProgress(true);
	    	$("#frm").ajaxSubmit(options);
		}
	}
	
	/**
	*검토의뢰건 상세 보기 RETURN
	*/
	function goDetail(){
		var frm = document.frm;	
		frm.method.value = "detailConsideration_old";
		frm.action = "<c:url value='/clm/review/consideration.do' />";	
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();		
	}
	
	/**
	* 법무담당자 추가
	*/
	function addRow() {
		var nvalue = $("#list_user_id option:selected").val();
		var ntext = $("#list_user_id option:selected").text();
		var IsUserExist = true;
		// 유효성체크
		if(nvalue==""){
			return;
		}
		// id 정보 비교
		$('#list_respman_id li').each(function(){
			if(nvalue==$(this).attr("id")) IsUserExist = false;	
		});
		// 유효성체크	
		if(IsUserExist){
			// 담당자 3명이상 유무 확인
			if($('#list_respman_id input').length > 2){
				alert("<spring:message code='las.msg.alert.consideration.respmanSelect2' />");//담당자는 최대 3명까지 지정하실수 있습니다.
				return;	
			}
			$('#list_respman_id').append("<li id='" + nvalue + "'>" + ntext + "</li><input type='hidden' name='list_respman_ids' id='" + 'id_' + nvalue + "' value='" + nvalue + "'></input>");
		} else {
			alert("<spring:message code='las.msg.alert.consideration.respmanSelect1' />");	//입력하신 법무담당자는 이미 추가목록에 있습니다.
			return;	
		}
		// 스타일 및 클래스 지정
		$('#list_respman_id li').bind('click', function(){
			$('#list_respman_id li').removeAttr("style", "");
			$('#list_respman_id li').removeClass('selected');
			
			$(this).attr("style", "background: #dddddd;");
			$(this).addClass("selected");		
		});
		
		$('#'+nvalue).click();
	}
	
	/**
	* 법무담당자 제외
	*/
	function removeRow() {
		var nvalue;
		if($('#list_respman_id .selected').val() == null){
			alert("<spring:message code='las.msg.alert.consideration.respmanSelect3' />");//선택된 법무담당자가 없습니다.
		}else{
			nvalue = $('#list_respman_id .selected').attr('id');
			$('#list_respman_id .selected').remove();
			$('#id_' + nvalue).remove();
			
			nvalue = $('#list_respman_id li').length - 1;
				
			if($('#list_respman_id input').length != -1){
				$('#list_respman_id li:eq(' + nvalue + ')').click();
			}
		}
	}
	
	/**
	* 담당자리스트 팝업 - 확인
	*/
	function returnResp(respId, respNm, apbtMemo) {
		$('#list_respman_id li').remove();
		$('#list_respman_id input').remove();
		
	    for(var i=0; i<respId.length; i++){
	    	$('#list_respman_id').append("<li id='" + respId[i] + "'>" + respNm[i] + "</li><input type='hidden' name='list_respman_ids' id='" + 'id_' + respId[i] + "' value='" + respId[i] + "'></input>");
	    }
	    
		// 스타일 및 클래스 지정
		$('#list_respman_id li').bind('click', function(){
			$('#list_respman_id li').removeAttr("style", "");
			$('#list_respman_id li').removeClass('selected');
			
			$(this).attr("style", "background: #dddddd;");
			$(this).addClass("selected");		
		});
		
		$('#'+respId[i]).click();
		
		$("#apbt_memo").val(apbtMemo);
	}

	/**
	* 담당자 지정
	*/
	function confirmRespman(mainManId, param1, param2) {		
		var method = "confirmRespman";
		if(param1 != "Y"){
			param1 = "N";
		}
	
		$("#main_man_id").val(mainManId);
		implJson(method);
	}
	/**
	* 그룹장 승인, 반려 Display
	*/
	function apbtCnsdChng() {
		var display_div = $('input[name="cnsdreq_opnn"]:checked').val();
		
		if(display_div=="Y"){
			$('#rejct_opnn').val("");
			$('#div_rejct_opnn').hide();
			$('#div_apbt_opnn').show();
		}else if(display_div=="N"){
			$('#apbt_opnn').val("");
			$('#div_apbt_opnn').hide();
			$('#div_rejct_opnn').show();
		}
	}
	
	/**
	* 이관_승인의견 Display
	*/
	function approveTransChng() {
		$('#div_res_trnsf').show();
		$('#trnsf_hndl_cause').val("");
	}
	
	/**
	* 이관승인, 이관거절
	*/
	function approveTrans(div) {
		var msg = "";
		var method = "";
		var display_div = $('input[name="approve_trans"]:checked').val();
		
		if(display_div=="Y"){
			msg = "<spring:message code='las.msg.ask.approve' />";//승인하시겠습니까?
			method = "approveTrans";
		}else if(display_div=="N"){
			msg = "<spring:message code='las.msg.ask.reject' />";//반려하시겠습니까?
			method = "disapproveTrans";
		}else{
			alert("<spring:message code='las.page.msg.contractmanager.consideration_info.select_approve' />");//승인를 선택하십시요.
			return;
		}
		
		if($("#trnsf_demnd_cause").val() == ""){
			alert("<spring:message code='las.page.msg.contractmanager.consideration_info.input_approve_oppnt' />");//승인의견을 입력하십시요.
			return;
		}
		
		if(confirm(msg)){
			implJson(method);
		}
	}	
	
	/**
	* 담당자 의견 승인 처리
	*/
	function approvalOpnn() {
		var frm = document.frm;
		//alert("approvalOpnn");
		var msg;
		if($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01"){	
			msg = "<spring:message code='las.msg.ask.approve' />";//승인하시겠습니까?
		}else{
			msg = "<spring:message code='las.msg.ask.approvalOpnn' />";//검토회신하시겠습니까?
		}
		
		var method = "approvalOpnn";
		if(confirm(msg) && validateTempSave(frm)){
			if($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01"){
				document.getElementById('tr_down02').style.display = "block";
				document.getElementById('tr_down03').style.display = "block";
			}
			implJson(method);
		}
	}
	
	/**
	* 담당자 의견 반려 처리
	*/
	function rejctOpnn() {
		if(($("#top_role").val() == "RA01" || $("#top_role").val() == "RB01") && $("#rejct_opnn").val() == ""){
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
	* 담당자 팝업
	*/
	function popRespman(){
		var frm = document.frm;
		
		PopUpWindowOpen('', 800, 570, true);
		frm.method.value = "popRespman";
		frm.action = "<c:url value='/clm/review/consideration.do' />",
		frm.target = "PopUpWindow";
		
		frm.submit();
	}
	
	/**
	*최종본 회신 버튼클릭시 
	*/
	function goLastConsideration(gbn_last){
		/*
		var cnfMsg = "[최종본 의뢰로 변경하여 검토 하기] \n첨부의뢰된 계약서가 최종본이 아닌 경우\n사용하실 수 없는 기능입니다. \n\n";
		cnfMsg += "* 일반의뢰로 접수되었지만 계약서가 최종본과 동일한 경우에\n불필요한 중복의뢰를 피하기 위하여\n최종본으로 검토 할 수 있는 기능입니다.\n\n";
		cnfMsg += "* 기존 첨부파일은 삭제가 됩니다.\n\n";
		cnfMsg += "최종본으로 전환하여 검토하시겠습니까?\n선택하시면 일반 의뢰 검토로 되돌릴 수 없습니다.";
		*/
		var cnfMsg = '<spring:message code="las.page.field.contractManager.checkFinalReview"/>';//\"확인\"을 누르면 최종본 검토 화면으로 전환됩니다.\n화면이 전환되면 현재 페이지에서 작성한 내용과 첨부파일이\n모두 삭제됩니다.\n또한 최종본 검토로 전환된 후에는 일반 검토로 되돌릴 수 없습니다.\n\n최종본 검토로 전환하시겠습니까?
				

		if(!confirm(cnfMsg)) return;
		
		var frm = document.frm;	
		frm.gbn_last.value = gbn_last;					// 최종본 회신구분
        
		frm.method.value = "detailConsideration_old";
		frm.action = "<c:url value='/clm/review/consideration.do' />";	
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();		
	}	
	
	/**
	* 인터페이스 처리(회신파일 업로드)
	* 2012.08.28 인터페이스 회신정보 누락 검토의뢰건을 처리 added by hanjihoon 
	*/
	function insertInterfaceInfo() {
		var msg = "<spring:message code='las.page.field.contractManager.askResendOms'/>";//인터페이스 회신정보 누락 검토의뢰건을 다시 처리하시겠습니까?
		var method = "insertInterfaceInfo";
		
		if(confirm(msg)){
			implJson(method);
		}
	}
	//검토이력 나모 내용을 보여준다.
	function attachNamoView(contId){
		document.getElementById(contId).contentWindow.document.body.innerHTML = document.getElementById(contId+'_textarea').value;	
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
	*최종본 회신시 분쟁해결 방법 셀렉트 추가 
	*/
	function addDspt(){

		var orign_text  = $("#dspt_resolt_mthd_det").val();
		var add_text = $("#dspt_resolt_mthd option:selected").text();
		
		if($("#dspt_resolt_mthd option:selected").val()==''){
			alert("<spring:message code='clm.page.msg.common.announce006' />");
			return;
		} 
	
		if(orign_text!='')
			add_text = ' , ' + add_text;
		
		orign_text = orign_text + add_text;
		
		$("#dspt_resolt_mthd_det").val(orign_text);
		
	}		
	
	//innder_d 에서 추가 끝
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
			<h3><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdreq" /></h3><!-- 계약검토 -->
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
					
					<input type="hidden" name="fileInfoName"	value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
					<input type="hidden" name="fileFrameName"   value="" /> <!-- 첨부파일 화면 iFrame 명 -->
						
					<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
					<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
					<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
					<input type="hidden" name="ref_key"     	value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
					<input type="hidden" name="view_gbn"    	value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->		
					
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
					<input type="hidden" name="contents" value=""/>
					
					<!-- Sungwoo. 2014-05-20 검색조건 추가 -->
					<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
					<input type="hidden" name="cnclsnpurps_bigclsfcn" 	id="cnclsnpurps_bigclsfcn" 	value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적_대분류 -->
					<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
					<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
					<input type="hidden" name="prgrs_status" 			id="prgrs_status" 			value="<c:out value='${lomRq.prgrs_status}'/>" />			<!-- 검토의뢰_진행상태 -->
					<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
					<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />			<!-- 의뢰 종료일 -->
					<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
					<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
					<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
					<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
					<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
					<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
					<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
					
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
					<input type="hidden" name="solo_yn" 				id="solo_yn" 				value="3" />	
					<input type="hidden" name="not_appr_yn" 			id="not_appr_yn" 			value="" />	
					<input type="hidden" name="con_depth_status" id="con_depth_status" value="<%=lomRq.get("depth_status") %>" />
					<input type="hidden" name="respUserDiv" id="respUserDiv" value="<%=lomRq.get("resp_user_div") %>" />
					<input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>" />
					<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />	
								
					<!-- Sungwoo added search parameter 2014-06-12 -->
					<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
					<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
					<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
					
					<c:forEach var="cntrtMt" items="${listDc}">
						<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
					</c:forEach>					
				
					<!-- title -->
					<div class="title_basic mt0"><!-- 검토의뢰 정보 -->
						<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');" style='cursor:pointer'/></h4>
					</div>
					<!-- //title -->
					<!--View -->
					<!-- 수정/검토의뢰/최종본작성/목록 -->			
					<!-- button -->
					<div class="btnwrap mt_22">
						<span id="btn_up2" class="btn_all_w" style="display:none" onclick="forwardConsideration('DELIVERY');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.thrwCmt"/></a></span><!-- 의견전달 -->
					    <span id="btn_up3" class="btn_all_w" style="display:none" onclick="forwardConsideration('DISPATCH');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.return"/></a></span><!-- 발신 -->
					    <span id="btn_up4" class="btn_all_w" style="display:none" onclick="forwardConsideration('RESP');" ><span class="mail"></span><a><c:if test="${command.top_role == 'RA01' || command.page_div == 'RESP'}"><spring:message code="las.page.field.contractManager.review"/><!--검토--> </c:if><spring:message code="las.page.field.contractManager.return"/><!-- 회신 --></a></span>
					    <span id="btn_up12" class="btn_all_w" style="display:none" onclick="forwardConsideration('NOAPPROVAL');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.fbNoApp"/><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.seqNoApp'/><spring:message code='las.page.field.contractManager.canGiveback'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
					    <span id="btn_up5" class="btn_all_w" style="display:none" onclick="forwardConsideration('PROCESSCANCEL');" ><span class="delete"></span><a><spring:message code="las.page.field.contractManager.cnclUndec"/></a></span><!-- 미결취소 -->
					    <span id="btn_up11" class="btn_all_w" style="display:none" onclick="forwardConsideration('REJECT');" ><span class="mail"></span><a><spring:message code="las.page.field.contractManager.reviewReject"/></a></span><!-- 검토반려 -->
					    <span id="btn_up_lastConsideration" class="btn_all_w" style="display:none" onclick="goLastConsideration('Y');" ><span class="edit"></span><a><spring:message code="las.page.field.contractManager.fvRv"/><!-- 최종본검토 --><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.evenFinal'/><spring:message code='las.page.field.contractManager.chckFinal'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
						<span id="btn_up_modifyContract" class="btn_all_w" style="display:none;" onclick="forwardContractMaster();" ><span class="modify"></span><a><spring:message code="las.page.field.contractManager.requUpdate"/><!-- 의뢰내용 수정 --><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.canUpate'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
						<% if(authYN_RA02){ %><span id="btn_up6" class="btn_all_w" style="display:none" onclick="forwardConsideration('CNSDRESP');" ><span class="reset"></span><a><c:choose><c:when test="${(lomRq.mn_cnsd_dept == command.blngt_orgnz && lomMn.cnsd_status == 'C04302')}"><spring:message code="las.page.field.contractManager.reviewUpdate"/><!--검토수정--></c:when><c:otherwise><spring:message code="las.page.field.contractManager.createReview"/><!--검토작성--></c:otherwise></c:choose></a></span><%}%>
						<span id="btn_up7" class="btn_all_w" style="display:none" onclick="forwardConsideration('CNSDAPPR');" ><span class="check2"></span><a><spring:message code="las.page.field.contractManager.pickPic"/></a></span><!-- 담당자 지정 -->
						<span id="btn_up1" class="btn_all_w" style="display:none" onclick="forwardConsideration('SAVE');" ><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span><!-- 임시저장 -->
						<span id="btn_up10" class="btn_all_w" style="display:none" onclick="openPrint();" ><span class="print"></span><a><spring:message code="las.page.field.contractManager.print"/></a></span><!-- 인쇄 -->
						<span id="btn_up8" class="btn_all_w" style="display:none" onclick="forwardConsideration('LIST');" ><span class="list"></span><a><spring:message code="las.page.field.contractManager.list"/></a></span><!-- 목록 -->
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
					  	<td colspan="5"><c:out value='${lomRq.req_title}' escapeXml='false'/></td>
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
							<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.relation_man" /><!-- 관련자 --></th>
						  	<td colspan="6">
						  	<c:forEach var="list" items="${lomRm}" varStatus="status">
								<c:out value='${list.relation_man}' escapeXml='false'/> <!-- 계약_마스터_계약_ID -->
									<c:if test="${status.count > 0 && !status.last}">
										,&nbsp;&nbsp;
									</c:if>
									<c:if test="${status.count mod 2==0}">
										<br/>
									</c:if>
							</c:forEach>
						  	</td>
						</tr>
						</c:if>
						<tr class="lineAdd">
							<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /><!-- 검토요청내용 --></th>
							<td colspan="6">
								<textarea id="if_cnsd_demnd_cont_textarea" name="cnsd_demnd_cont_textarea" style="display: none;"><c:out value="${lomRq.cnsd_demnd_cont}" escapeXml="" /></textarea>
								<iframe id="if_cnsd_demnd_cont" name="if_cnsd_demnd_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
							</td>
						</tr>
						<c:if test="${lomRq.plndbn_req_yn eq 'Y' && !empty lomRq.lastbn_chge_yn_nm}">
							<tr class="lineAdd">
								<th><spring:message code="las.page.field.contractmanager.consideration_d.lastbn_chge_yn_nm" /><!-- 검토본변경여부 --></th>
								<td colspan="6">
									<c:out value='${lomRq.lastbn_chge_yn_nm}' escapeXml='false'/>
								</td>
							</tr>
							</c:if>
							<c:if test="${lomRq.plndbn_req_yn eq 'Y' && !empty lomRq.plndbn_chge_cont_dp}">
							<tr class="lineAdd">
								<th><spring:message code="las.page.field.contractmanager.consideration_d.plndbn_chge_cont" /><!-- 변경내역 및 사유 --></th>
								<td colspan="6">
									<c:out value="${lomRq.plndbn_chge_cont_dp}" escapeXml="false" />
								</td>
							</tr>
						</c:if>
						
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
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListContract" name="fileListContract"  frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
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
								<!-- Sungwoo 2014-07-21 scrolling changed -->
		                		<iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
						<tr class="slide-target02 slide-area">
							<td class="tal_lineL"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.etc" /><!-- 기타 --></span></td>
							<td colspan="6">
								<div id="ico_maxsize3"  class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
		                    	<!-- Sungwoo 2014-07-21 scrolling changed -->
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListGita" name="fileListGita"  frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
						<tr class="slide-target02 slide-area">
							<td class="tal_lineL"><span class="blueD">OUTLOOK</span></td>
							<td colspan="6">
								<div id="ico_maxsize4"  class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
		                    	<!-- Sungwoo 2014-07-21 scrolling changed -->
								<iframe src="<c:url value='/clm/blank.do' />" id="fileListOL" name="fileListOL"  frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
					</table>
				</div>
				
				<!-- //법무 시스템 - 계약검토 -->
				
				<!-- title -->
			    <div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /><!-- 계약 정보 --> <img id="btn_down_cntrt" src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');" style='cursor;pointer'/></h4>
				</div>
				<!-- //title -->

				<!-- inner_d_view 시작 -->
				<!--middle table2 -->
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
							<% if(!lomRq.get("PRCS_DEPTH").equals("C02507")){%>									
							<td><c:out value="${lomRq.cntrt_oppnt_rprsntman}" escapeXml='false'/></td>
							<% }else{%>
							<td></td>
							<% } %>
							<th><spring:message code="clm.page.field.customer.contractRequired" /><!-- Contract Required  --></th>
							<td><c:out value="${lomRq.cntrt_oppnt_type_nm}" escapeXml='false'/></td>
							
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
										<iframe src="<c:url value='/clm/blank.do' />" id="fileListUnit" name="fileListUnit"  frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>					
									</td>
								</tr>
							</c:if>
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.pshdbkgrnd_purps" /></th><!-- 추진목적 및 배경 -->
								<td colspan="6">				
							    <textarea id="if_cnsd_bg_cont_textarea" name="cnsd_bg_cont_textarea" style="display: none;"><c:out value="${lomRq.pshdbkgrnd_purps}" escapeXml="" /></textarea>
					    		<iframe id="if_cnsd_bg_cont" name="cnsd_bg_cont" src="<c:url value='/clm/blank.do' />" frameborder="0" scrolling="auto" style="width:100%"></iframe>  
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
							<c:if test="${lomRq.plndbn_req_yn eq 'Y'}">
							<tr>
								<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th><!-- 자동연장여부 -->
								<td colspan="6"><input type="radio" name="auto_rnew_yn" disabled='disabled' value="Y" <%if("Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %> />Yes
									<input type="radio" name="auto_rnew_yn" disabled='disabled' value="N" <%if(!"Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %>/>No
								</td>
							</tr>
							<tr>
							<c:choose>
							<c:when test="${command.top_role eq 'RA02' && lomRq.resp_user_div eq 'Y' && modYn eq 'Y'}">
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.secret_keepperiod_rq" /></th><!-- * 비밀유지기간 -->
								<td id="td_secret_keepperiod"  colspan="6">
									<input type="text" id="secret_keepperiod" name="secret_keepperiod" class="text_full" onkeyup="f_chk_byte(this,64)" maxLength="64" value="<c:out value='${lomRq.secret_keepperiod}' escapeXml='false'/>" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.secret_keepperiod_rq' />">
								</td>
							</c:when>
							<c:otherwise>
								<th><spring:message code="clm.page.msg.manage.secretPeriod" /></th><!-- 비밀유지기간 -->
								<td id="td_secret_keepperiod" colspan="6">
									<c:out value="${lomRq.secret_keepperiod_dp}" escapeXml="false"/>
								</td>
							</c:otherwise>
							</c:choose>
							</tr>
							</c:if>
						</table>
			
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
							<% if( ("RA01".equals(command.getTop_role()) ||"RB01".equals(command.getTop_role())) || "N".equals(modYn)){ // 그룹장인경우 VieWmode %>
							<tr id="tr_view_oblgt_lmt" style="border-top:0px;">
								<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
								<td colspan="3">
									<c:out value='${lomRq.oblgt_lmt_dp}' escapeXml='false'/>
								</td>
							</tr>
							<c:if test="${!empty lomRq.spcl_cond_dp}">
							<tr>
								<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.spcl_cond" /></th><!-- 기타 특약사항 -->
								<td colspan="3" id="tr_spcl_cond">
									<c:out value='${lomRq.spcl_cond_dp}' escapeXml='false'/>
								</td>
							</tr>
							</c:if>
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
							<% }else{%>
							<tr style="border-top:0px;">
								<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
								<td colspan="3" id="tr_oblgt_lmt">
									<textarea name="oblgt_lmt" id="oblgt_lmt" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength="1000" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.oblgt_lmt' />"><%=(String)StringUtil.nvl((String)lomRq.get("oblgt_lmt"), "") %></textarea>
								</td>
							</tr>
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.spcl_cond" /></th><!-- 기타 특약사항 -->
								<td colspan="3" id="tr_spcl_cond">
									<textarea name="spcl_cond" id="spcl_cond" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength="1000" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.spcl_cond' />"><%=(String)StringUtil.nvl((String)lomRq.get("spcl_cond"), "") %></textarea>
								</td>
							</tr>
							<tr>
								<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_rq" /> <span class='astro'>*</span></th><!-- * 준거법 -->
								<td id="td_loac">
									<select name="loac" id="loac" style="width:110px;" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_rq' />" onChange="javascript:chgLoac(this.value);">						  
									</select>
								</td>
								<c:choose>
									<c:when test="${lomRq.loac=='C02211'}">
										<th id="th_loac_etc"><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc_rq" /> <span class='astro'>*</span></th><!-- * 준거법상세 -->
										<td id="td_loac_etc"><input type="text" name="loac_etc" id="loac_etc" class="text_full" onkeyup="f_chk_byte(this,500)" maxLength='500' value="<%=(String)StringUtil.nvl((String)lomRq.get("loac_etc"), "") %>" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc_rq' />" /></td>	
									</c:when>
									<c:otherwise>
										<th id="th_loac_etc"><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc" /></th><!-- 준거법상세 -->
										<td id="td_loac_etc"><input type="text" name="loac_etc" id="loac_etc" class="text_full" onkeyup="f_chk_byte(this,500)" maxLength='500' value="<%=(String)StringUtil.nvl((String)lomRq.get("loac_etc"), "") %>" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />" /></td>
									</c:otherwise>
								</c:choose>	
							</tr>							
							<tr>
								<th rowspan="2"  class='rightline'><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq" /></th><!-- * 분쟁해결방법 -->
								<td id="td_dspt_resolt_mthd"  colspan="3">
									<select name="dspt_resolt_mthd" id="dspt_resolt_mthd" style="width:110px;" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq' />" >
									</select> <span class="btn_all_b" onclick="javascript:addDspt();"><span class="add"></span> <a><spring:message code="las.page.field.contractManager.add"/></a></span> 
								</td>
								<!-- <th><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_det" /></th>분쟁_해결_방법_상세 -->					
							 	
							</tr>
							<tr>
								<td id="td_dspt_resolt_mthd_det" colspan="3">
									<textarea name="dspt_resolt_mthd_det" id="dspt_resolt_mthd_det" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength='1000' alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_det' />"><%=(String)StringUtil.nvl((String)lomRq.get("dspt_resolt_mthd_det"), "") %></textarea>
								</td>
							</tr>
							<% }%>	
						<% }%>
						</table>
					</div>
					
					<!-- 사전승인정보 -->
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
								<td colspan="3">
			                        <!-- Sungwoo replacement height size 2014-07-03-->
									<iframe src="<c:url value='/clm/blank.do' />" id="fileListBf" name="fileListBf" frameborder="0" class='addfile_iframe_d'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
								</td>
							</tr>
						</table>
					</div>
					<!-- 사전승인정보 -->
					<!-- 연관계약정보 -->
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
							<c:when test="${modYn eq 'N' }">
								<c:choose>
								<c:when test ="${!empty relationList}">
									<c:forEach var="list" items="${relationList}" varStatus="i">
										<tr id="trRelationContractCont">
											<td><c:out value='${list.rel_type_nm}' escapeXml='false'/></td>
											<td><c:out value='${list.relation_cntrt_nm}' escapeXml='false'/></td>
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
							</c:when>
							<c:otherwise>
								<tr id="trRelationContract">
									 <td><select name="rel_type" id="rel_type" onChange="reltypeChange();"></select></td>
									 <td><input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
									 		<input type="text" name="parent_cntrt_name" id="parent_cntrt_name" style="width:92%" class="text_search" readonly="readonly" /><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
									 <td><select name="rel_defn" id="rel_defn" style="width:95%"></select></td>
									 <td><input type="text" name="expl" id="expl" class="text_full" style="width:74%" onkeyup="f_chk_byte(this,600)" maxLength="600" />
									 <img src="<%=IMAGE %>/btn/btn_regist.gif" onclick="javascript:actionRelationContract('insert','','');" class='cp' />
									 </td>
								</tr>
								<c:out value="${contRc}" escapeXml="false"/>
							</table>
							<table id="trRelationContractMsg" cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
								<tr>
									<td>
										<spring:message code="las.page.field.contractManager.makeRel"/><br/><!-- ※ 현계약과 선행, 주/종, 변경/해지, 기타의 Relation type을 가지는 모든 연관계약을 지정하여 주시기 바랍니다. -->
										&nbsp;<spring:message code="las.page.field.contractManager.precede"/><br/><!-- 1)선행관계   : '연관계약'이 '현계약'의 선행 계약 -->
										&nbsp;<spring:message code="las.page.field.contractManager.masterSub"/><br/><!-- 2) 주종 관계 : '연관계약'이 '현계약'의 'Master/Sub' 계약  -->
										&nbsp;<spring:message code="las.page.field.contractManager.chngCncl"/><br/><!-- 3) 변경/해지 : '연관계약'이 '현계약'의 '변경전/해지전' 계약 -->
										&nbsp;<spring:message code="las.page.field.contractManager.others4"/><!-- 4) 기타 -->
									</td>
								</tr>
							</table>
							</c:otherwise>
							</c:choose>
				</div>
				<!-- //연관계약정보 -->
				
				<!--bottom table 검토의견 -->
				<div id="tr_down03">
					<div class="title_basic">
						<h4><% if("Y".equals(lomRq.get("plndbn_req_yn"))){%><spring:message code="las.page.field.contractManager.fvRvCmt"/><% }else{%><spring:message code="las.page.field.contractManager.reviewComment"/><% }%> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_cnsd');" style='cursor:pointer;'/></h4>
					</div>
					<%
					String strMn = "<spring:message code='las.page.field.contractManager.genArticle'/>";//일반조항
					String strVc = "<spring:message code='las.page.field.contractManager.lnkDept'/>";//유관부서
					String strMnd = "<spring:message code='las.page.field.contractManager.lgPic'/>";//법무담당자
					String strVcd = "<spring:message code='las.page.field.contractManager.lnkDeptPic'/>";//유관부서 담당자			
					%>
					<c:choose>
					<c:when test="${modYn eq 'N' }">
					<!-- inner_d_view 검토의견 -->
					<div id="tr_down_cnsd">
						<c:if test="${!empty lomRq.cnsdman_info || !empty lomRq.apbt_memo_dp || !((command.blngt_orgnz == 'A00000001' || lomMn.cnsd_dept == command.blngt_orgnz || lomMn.cnsd_status == 'C04305') && !empty lomMn.main_matr_cont)}">
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
								<!-- 2012.03.12 사안개요는 조회조건은 정부서, 로그인 세션 부서가 법무팀, 정부서가 회신인 상태일 경우 조회 가능 added by hanjihoon -->

									<tr id="tr_main_matr_cont">
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /></th><!-- TN_CLM_CONTRACT_CNSD	주요_사안_내용 MAIN_MATR_CONT    부서 검토 테이블로 바꿀꺼임 -->
										<td colspan="3">
											<c:out value="${lomMn.main_matr_cont_dp}" escapeXml="false" />
<%-- 										<textarea id="if_main_matr_cont_textarea" name="main_matr_cont_textarea" style="display: none;"><c:out value="${lomMn.main_matr_cont}" escapeXml="" /></textarea>
										<textarea id="main_matr_cont" name="main_matr_cont" ><c:out value="${lomMn.main_matr_cont}" escapeXml="" /></textarea>
										<iframe id="if_main_matr_cont" name="if_main_matr_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe> --%>
										</td>						
									</tr>
						</table>
						</c:if>
						<c:if test="${lomMn.cnsd_dept == command.blngt_orgnz || lomMn.cnsd_status == 'C04305'}">
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
							<!-- 2012.03.12 정부서가 아니면 정부서의 계약서 첨부파일을 정부서의 회신 이후 조회 가능 added by hanjihoon -->
								<tr>
								<%	if("Y".equals(lomRq.get("plndbn_req_yn"))){ //최종본의뢰여부 %>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> </th><!-- * 최종검토의견 -->
								<%	}else{%>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> </th><!-- * 종합검토의견 -->
								<%	}%>
									<td colspan="4">
										<textarea id="if_cnsd_opnn_textarea" name="cnsd_opnn_textarea" style="display: none;"><c:out value="${lomRq.cnsd_opnn}" escapeXml="" /></textarea>
										<iframe id="if_cnsd_opnn" name="if_cnsd_opnn" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
									</td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
									<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></td><!-- 계약서 -->
									<td colspan="3">
									 <iframe src="<c:url value='/clm/blank.do' />" id="fileListReContract" name="fileListReContract"  frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
									</td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<td class="tal_lineL"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /><!-- 첨부/별첨 --></td>
									<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReEtc" name="fileListReEtc" frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe></td>
								</tr>
								<tr name="tr_attached_file" style="display:none;">
									<td class="tal_lineL"><spring:message code="las.page.field.contractManager.others"/><!-- 기타 --></td>
									<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReGita" name="fileListReGita"frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe></td>
								</tr>
						</table>
						</c:if>
					</div>
					<!--// inner_d_view 검토의견 -->
					</c:when>
					<c:otherwise>
					<!-- inner_d 검토의견 -->
					<div id="tr_down_cnsd">
						<c:choose>
						<c:when test="${command.top_role == 'RA01' or command.top_role == 'RB01' }"><!-- 그룹장 -->
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
								<div id="div_cnsd3_1" class="title_basic3"><spring:message code="las.page.field.contractManager.reviewInfo"/><!-- 검토기본정보 --></div>
								<c:if test="${!empty lomRq.cnsdman_info}">
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cnsdman_info" /></th><!-- 검토 담당자 -->
										<td colspan="3"><c:out value='${lomRq.cnsdman_info}' escapeXml='false'/></td>
									</tr>
								</c:if>
								<c:if test="${!empty lomRq.apbt_memo_dp}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.gcMsg"/><br/><spring:message code="las.page.field.contractManager.toReviewer"/><!-- (그룹장 → 검토자) --></th>
										<td colspan="3">
											<c:out value='${lomRq.apbt_memo_dp}' escapeXml="false"/>
										</td>						
									</tr>
								</c:if>
								<c:choose>
								<c:when test="${lomRq.mn_cnsd_dept == command.blngt_orgnz && !empty lomMn.main_matr_cont}">
									<tr id="tr_main_matr_cont" style="display:none">
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /></th><!-- 사안개요 -->
										<td colspan="3"><c:out value='${lomMn.main_matr_cont_dp}' escapeXml="false"/></td>						
									</tr>
								</c:when>
								</c:choose>
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
								<c:if test="${lomRq.mn_cnsd_dept == command.blngt_orgnz}">
									<c:choose>
									<c:when test="${lomRq.mn_cnsd_dept == command.blngt_orgnz && lomMn.cnsd_status == 'C04303'}">
										<tr>
											<c:choose>
											<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 최종검토의견 -->
												<td colspan="4">
												<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" />
												<input type="hidden" name="body_mime_cnsd_opnn" id="body_mime_cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />" />
												<input type="hidden" name="body_mime_rq" id="body_mime_rq" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />" />
											</c:when>
											<c:otherwise>
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 종합검토의견 -->
												<td colspan="4">
												<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" />
												<input type="hidden" name="body_mime_cnsd_opnn" id="body_mime_cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />" />
												<input type="hidden" name="body_mime_rq" id="body_mime_rq" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />" />
											</c:otherwise>
											</c:choose>
											<script type="text/javascript">										
											
												// var contentValue = document.getElementById("body_mime_rq").value; 
												var CrossEditor = new NamoSE('namoeditor');
												CrossEditor.params.Width = "100%";
											    // CrossEditor.params.Css = "/style.css";
											    CrossEditor.params.UserLang = "enu";
											    CrossEditor.params.FullScreen = false;
											    CrossEditor.ShowTab(false);
											    CrossEditor.UserToolbar = true;
											    CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";
											            					
											    CrossEditor.EditorStart();										

											</script>
											</td>
										</tr>
									</c:when>
									<c:when test="${lomRq.prgrs_status != 'C04203' }">
										<tr>
											<c:choose>
											<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> </th><!-- * 최종검토의견 -->
											</c:when>
											<c:otherwise>
												<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> </th><!-- * 종합검토의견 -->
											</c:otherwise>
											</c:choose>
											<td colspan="4">
												<c:out value='${lomRq.cnsd_opnn}' escapeXml="false"/>
											</td>
										</tr>
									</c:when>
									</c:choose>
									<c:if test="${(lomRq.mn_cnsd_dept == command.blngt_orgnz && lomMn.cnsd_status == 'C04303') || lomMn.cnsd_status == 'C04305'}">
									<tr name="tr_attached_file" style="display:none;">
										<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
										<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></td><!-- 계약서 -->
										<td colspan="3">
										 <iframe src="<c:url value='/clm/blank.do' />" id="fileListReContract" name="fileListReContract" frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe></iframe>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td class="tal_lineL"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /></td>
										<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReEtc" name="fileListReEtc"frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe></td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td class="tal_lineL"><spring:message code="las.page.field.contractManager.others"/></td>
										<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileListReGita" name="fileListReGita"frameborder="0" width='100%' class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe></td>
									</tr>
									</c:if>
								</c:if>
							</table>
						</c:when>
						<c:when test="${command.top_role == 'RA02' && lomRq.mn_cnsd_dept == command.blngt_orgnz}"><!-- 정_검토부서 담당자 -->
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
								<div id="div_cnsd3_1" class="title_basic3"><spring:message code="las.page.field.contractManager.reviewInfo"/><!-- 검토기본정보 --></div>
								<c:if test="${!empty lomRq.cnsdman_info}">
									<tr>
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cnsdman_info" /></th>
										<td colspan="3"><c:out value='${lomRq.cnsdman_info}' escapeXml='false'/></td>
									</tr>
								</c:if>
								<c:if test="${!empty lomRq.apbt_memo_dp}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.gcMsg"/><br/><spring:message code="las.page.field.contractManager.toReviewer"/></th>
										<td colspan="3">
											<c:out value='${lomRq.apbt_memo_dp}' escapeXml="false"/>
										</td>						
									</tr>
								</c:if>
								
									<tr id="tr_main_matr_cont" style="display:none">
										<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /><!-- 사안개요 --></th>
										<td colspan="3">
											 <span id="main_matr_cont1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
											<textarea name='main_matr_cont' id='main_matr_cont' cols='40' rows='5' onkeyup="frmChkLenLang(this,2000,'main_matr_cont1','<%=langCd%>')" maxLength='2000' class='text_area_full' alt="<spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont"/>"><c:out value="${lomMn.main_matr_cont}" escapeXml="false" /></textarea>								
										</td>
									</tr>
									
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
								<c:choose>
								<c:when test="${lomMn.cnsd_status eq 'C04303' || lomMn.cnsd_status eq 'C04305'}">
									<tr>
										<c:choose>
										<c:when test="${lomRq.plndbn_req_yn eq 'Y'}">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 최종검토의견 -->
										</c:when>
										<c:otherwise>
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 종합검토의견 -->
										</c:otherwise>
										</c:choose>
										<td colspan="4">
											<c:out value='${lomRq.cnsd_opnn}' escapeXml="false"/>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
											<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
										</th>
										<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></td><!-- 계약서 -->
										<td colspan="3">
											<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
											<iframe src="<c:url value='/clm/blank.do' />" id="fileListReContract" name="fileListReContract" frameborder="0" width='100%' class='addfile_iframe'  scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /></td>
										<td colspan="3">
											<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
											<iframe src="<c:url value='/clm/blank.do' />" id="fileListReEtc" name="fileListReEtc" frameborder="0" width='100%' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td><spring:message code="las.page.field.contractManager.others"/></td>
										<td colspan="3">
											<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
											<iframe src="<c:url value='/clm/blank.do' />" id="fileListReGita" name="fileListReGita" frameborder="0" width='100%' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<c:choose>
										<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 최종검토의견 -->
											<td colspan="4">
											<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" />
											<input type="hidden" name="body_mime_cnsd_opnn" id="body_mime_cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />" />
											<input type="hidden" name="body_mime_rq" id="body_mime_rq" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.final_cnsd_opnn_rq' />" />
										</c:when>
										<c:otherwise>
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq" /> <span class='astro'>*</span></th><!-- * 종합검토의견 -->
											<td colspan="4">
											<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" />
											<input type="hidden" name="body_mime_cnsd_opnn" id="body_mime_cnsd_opnn" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />" />
											<input type="hidden" name="body_mime_rq" id="body_mime_rq" value="<c:out value='${lomRq.cnsd_opnn}' />" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn_rq' />" />
										</c:otherwise>
										</c:choose>
										<%-- <%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%> --%>
											<script type="text/javascript">	
											
											
											var CrossEditor1 = new NamoSE('namoeditor1');
											var CrossEditorHeight1 = "400";	
											CrossEditor1.params.Height = CrossEditorHeight1;	
											CrossEditor1.params.Width = "100%";
										    CrossEditor1.params.Css = "/style.css";
										    CrossEditor1.params.UserLang = "enu";
										    CrossEditor1.params.FullScreen = false;
										    CrossEditor1.ShowTab(false);
										    CrossEditor1.UserToolbar = true;
										    CrossEditor1.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";
										            					
										    CrossEditor1.EditorStart();											

											</script>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" />
											<img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" />
										</th>
										<td><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></td><!-- 계약서 -->
										<td colspan="3">
										 <div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
										 <iframe src="<c:url value='/clm/blank.do' />" id="fileListReContract" name="fileListReContract" frameborder="0" width='100%' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td class="tal_lineL"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attach" /></td>
										<td colspan="3">
										<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
										<iframe src="<c:url value='/clm/blank.do' />" id="fileListReEtc" name="fileListReEtc" frameborder="0" width='100%' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
									<tr name="tr_attached_file" style="display:none;">
										<td class="tal_lineL"><spring:message code="las.page.field.contractManager.others"/></td>
										<td colspan="3">
										<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
										<iframe src="<c:url value='/clm/blank.do' />" id="fileListReGita" name="fileListReGita" frameborder="0" width='100%' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
										</td>
									</tr>
								</c:otherwise>
								</c:choose>
							</table>
						</c:when>
						</c:choose>
					</div>
					<!--// inner_d 검토의견 -->
					</c:otherwise>
					</c:choose>
				</div>
				<!-- inner_d_view 끝 -->
				
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			
				<!-- 검토담당자 & 승인 -->		
					<!-- 배정 -->
					<div class='title_basic'>
						<h4><spring:message code="las.page.field.contractManager.reviewEmp"/><!-- 검토담당자 --> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01_resp');" style='cursor;pointer'/></h4>
					</div>
					
					<table id='tr_down01_resp' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
						<colgroup>
							<col width='15%' />
							<col width='85%' />
						</colgroup>							
									<tr>
										<th>
											<spring:message code="las.page.field.contractManager.reviewEmp"/><!-- 검토담당자 -->&nbsp;
											<c:if test="${command.top_role == 'RA01' && (lomRq.depth_status != 'C02607' && lomRq.depth_status != 'C02603') && (lomRq.prgrs_status == 'C04202' || lomRq.prgrs_status == 'C04203' || lomRq.prgrs_status == 'C04204' || lomRq.prgrs_status == 'C04206')}">
												<span class="btn_all_b" onclick="popRespman();"><span class="check"></span><a href='#'><spring:message code="las.page.button.select"/><!-- 선택 --></a></span>
											</c:if>
										</th>		
										<td>	
											<ul id='list_respman_id' name='list_respman_id' class='las_men' style='width:100%;margin-left:0px;margin-top:2px;'>
											<c:forEach var='list' items='${lomResp}'>
												<li id="<c:out value='${list.list_respman_id}'/>"><c:out value='${list.list_respman_nm}' escapeXml='false'/></li><input type="hidden" name="list_respman_ids" id="id_<c:out value='${list.list_respman_id}'/>" value="<c:out value='${list.list_respman_id}'/>"></input>
											</c:forEach>
											</ul>
										</td>
									</tr>									
						</tr>
					</table>
					
					<!-- 검토승인 -->
					<!-- 	법무팀	: 미결상태 이후 단계일경우와 반려의견이 있는경우 -->
					<!-- 	IP팀	: 체결예정본 회신이고 IP센터가 ownership을 가지고 있는 경우만 내부결제 가능 -->
					<c:if test="${ ( (command.top_role eq 'RA01' || command.top_role eq 'RB01') || (command.top_role eq 'RA02' && lomDcd.cnsd_status != 'C04303')) && ((lomDcd.cnsd_status eq 'C04302' && !empty lomDcd.rejct_opnn) || (lomDcd.cnsd_status >= 'C04303' && (command.blngt_orgnz == 'A00000001' && command.blngt_orgnz == lomRq.mn_cnsd_dept )) ) }">
						<br /><br />
						<table id='tr_down01_manager' cellspacing='0' cellpadding='0' class='table-style01' border='0'>
							<colgroup>
								<col width='15%' />
								<col />
							</colgroup>
							<c:choose>
								<c:when test="${(command.top_role eq 'RA01' || command.top_role eq 'RB01') && lomDcd.cnsd_status eq 'C04303'}">
									<tr style='display:none'>
										<th><spring:message code="las.page.field.contractManager.ifApp"/><!-- 승인여부 --></th>
										<td>
											<input name='cnsdreq_opnn' type='radio' class='radio' value='Y' onclick='javascript:apbtCnsdChng();' /><spring:message code="las.page.field.contractManager.approval2"/>&nbsp;&nbsp;
											<input name='cnsdreq_opnn' type='radio' class='radio' value='N' onclick='javascript:apbtCnsdChng();' /><spring:message code="las.page.field.contractManager.reject"/><!-- 반려 -->&nbsp;&nbsp;
										</td>
									</tr>
									<tr id='div_apbt_opnn' style='display:none'>
										<th><spring:message code="las.page.field.contractManager.appCmt"/><!-- 승인의견 --></th>
										<td><textarea name='apbt_opnn' id='apbt_opnn' cols='40' rows='3' onkeyup='f_chk_byte(this,1000)' maxLength='1000' class='text_area_full' alt="<spring:message code="las.page.field.contractManager.appCmt"/>"></textarea></td>
									</tr>
									<tr id='div_rejct_opnn' style='display:none'>
										<th><spring:message code="las.page.field.contractManager.rejectCmt"/><!-- 반려의견 --></th>
										<td><textarea name='rejct_opnn' id='rejct_opnn' cols='40' rows='3' onkeyup='f_chk_byte(this,1000)' maxLength='1000' class='text_area_full' alt="<spring:message code="las.page.field.contractManager.rejectCmt"/>"></textarea></td>
									</tr>
									<tr>
										<th><spring:message code="las.page.field.contractManager.gcComment"/><!-- 그룹장 의견 --><BR>
										(<spring:message code="las.page.field.contractManager.gcCommentUnder"/>)</th>
										<td><textarea name='manager_opnn' id='manager_opnn' cols='40' rows='3' onkeyup='f_chk_byte(this,1000)' maxLength='1000' class='text_area_full' alt="<spring:message code="las.page.field.contractManager.gcComment"/>"></textarea></td>
									</tr>
								</c:when>
								<c:when test="${!empty lomDcd.apbt_dt}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.ifApp"/><!-- 승인여부 --></th>
										<td>
											<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' checked='checked' value='Y' /><spring:message code="las.page.field.contractManager.approval2"/>&nbsp;&nbsp;
											<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' value='N' /><spring:message code="las.page.field.contractManager.reject"/>&nbsp;&nbsp;
										</td>
									</tr>
									<c:if test="${!empty lomDcd.apbt_opnn}">
										<tr>
											<th><spring:message code="las.page.field.contractManager.gcComment"/><!-- 그룹장 의견 --></th>
											<td><c:out value='${lomDcd.apbt_opnn_dp}' escapeXml="false"/></td>
										</tr>
									</c:if>
								</c:when>
								<c:when test="${(command.top_role eq 'RA01' || command.top_role eq 'RB01') && !empty lomDcd.rejct_opnn}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.ifApp"/><!-- 승인여부 --></th>
										<td>
											<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' value='Y' /><spring:message code="las.page.field.contractManager.approval2"/>&nbsp;&nbsp;
											<input name='cnsdreq_opnn' type='radio' class='radio' disabled='disabled' checked='checked' value='N' /><spring:message code="las.page.field.contractManager.reject"/>&nbsp;&nbsp;
										</td>
									</tr>
									<c:if test="${!empty lomDcd.rejct_opnn}">
										<tr>
											<th><spring:message code="las.page.field.contractManager.gcComment"/><!-- 그룹장 의견 --></th>
											<td><c:out value='${lomDcd.rejct_opnn}' escapeXml="false"/></td>
										</tr>
									</c:if>
								</c:when>
								<c:when test="${command.top_role eq 'RA02' && !empty lomDcd.rejct_opnn}">
									<tr>
										<th><spring:message code="las.page.field.contractManager.ifApp"/><!-- 승인여부 --></th>
										<td>
											<input name='cnsdreq_opnn_sub' type='radio' class='radio' disabled='disabled' value='Y' /><spring:message code="las.page.field.contractManager.approval2"/>&nbsp;&nbsp;
											<input name='cnsdreq_opnn_sub' type='radio' class='radio' disabled='disabled' checked='checked' value='N' /><spring:message code="las.page.field.contractManager.reject"/>&nbsp;&nbsp;
										</td>
									</tr>
									<c:if test="${!empty lomDcd.rejct_opnn}">
										<tr>
											<th><spring:message code="las.page.field.contractManager.gcComment"/><!-- 그룹장 의견 --></th>
											<td><c:out value='${lomDcd.rejct_opnn}' escapeXml="false"/></td>
										</tr>
									</c:if>
								</c:when>
							</c:choose>
						</table>
					</c:if>
			
			
			<!-- button -->
			<div class="btn_wrap_c">
				<BR>
				<!-- span id="btn_down13" class="btn_all_w" style="display:none"><span class="edit"></span><a href="javascript:insertInterfaceInfo();"><spring:message code="las.page.field.contractManager.resendOms"/></a></span--><!-- 인터페이스 회신 누락건 재전송 -->
				<span id="btn_down2" class="btn_all_w" style="display:none" onclick="forwardConsideration('DELIVERY');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.thrwCmt"/></a></span>
				<span id="btn_down3" class="btn_all_w" style="display:none" onclick="forwardConsideration('DISPATCH');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.return"/></a></span>
				<span id="btn_down4" class="btn_all_w" style="display:none" onclick="forwardConsideration('RESP');"><span class="mail"></span><a><c:if test="${command.top_role == 'RA01' || command.page_div == 'RESP'}"><spring:message code="las.page.field.contractManager.review"/> </c:if><spring:message code="las.page.field.contractManager.return"/></a></span>
				<span id="btn_down12" class="btn_all_w" style="display:none" onclick="forwardConsideration('NOAPPROVAL');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.fbNoApp"/><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.seqNoApp'/><br/><spring:message code='las.page.field.contractManager.canGiveback'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
				<span id="btn_down5" class="btn_all_w" style="display:none" onclick="forwardConsideration('PROCESSCANCEL');"><span class="delete"></span><a><spring:message code="las.page.field.contractManager.cnclUndec"/></a></span>
				<span id="btn_down11" class="btn_all_w" style="display:none" onclick="forwardConsideration('REJECT');"><span class="mail"></span><a><spring:message code="las.page.field.contractManager.reviewReject"/></a></span>
				<span id="btn_down_lastConsideration" class="btn_all_w" style="display:none" onclick="goLastConsideration('Y');"><span class="edit"></span><a><spring:message code="las.page.field.contractManager.fvRv"/><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.evenFinal'/><br/><spring:message code='las.page.field.contractManager.chckFinal'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
				<span id="btn_down_modifyContract" class="btn_all_w" style="display:none" onclick="forwardContractMaster();"><span class="edit"></span><a><spring:message code="las.page.field.contractManager.requUpdate"/><span class='b_info' onMouseover="fixedtooltip('<spring:message code='las.page.field.contractManager.canUpate'/>', this, event, '300px')" onMouseout="delayhidetip()"></span></a></span>
				<% if(authYN_RA02){ %><span id="btn_down6" class="btn_all_w" style="display:none" onclick="forwardConsideration('CNSDRESP');"><span class="reset"></span><a><c:choose><c:when test="${(lomRq.mn_cnsd_dept == command.blngt_orgnz && lomMn.cnsd_status == 'C04302') || lomRq.vc_cnsd_dept == command.blngt_orgnz }"><spring:message code="las.page.field.contractManager.reviewUpdate"/></c:when><c:otherwise><spring:message code="las.page.field.contractManager.createReview"/></c:otherwise></c:choose></a></span><%}%>
				<span id="btn_down7" class="btn_all_w" style="display:none" onclick="forwardConsideration('CNSDAPPR');"><span class="check2"></span><a><spring:message code="las.page.field.contractManager.pickPic"/></a></span>
				<!-- span id="btn_down9" class="btn_all_w" style="display:none"><span class="mail"></span><a href="javascript:forwardConsideration('CNSDREQ');"><spring:message code="las.page.field.contractManager.lnkDeptReq"/></a></span--><!-- 유관부서검토요청 -->
				<span id="btn_down1" class="btn_all_w" style="display:none" onclick="forwardConsideration('SAVE');"><span class="tsave"></span><a><spring:message code="las.page.field.contractManager.tmpSave"/></a></span>
				<span id="btn_down10" class="btn_all_w" style="display:none" onclick="openPrint();"><span class="print"></span><a><spring:message code="las.page.field.contractManager.print"/></a></span>
				<span id="btn_down8" class="btn_all_w" style="display:none" onclick="forwardConsideration('LIST');"><span class="list"></span><a><spring:message code="las.page.field.contractManager.list"/></a></span>
			</div>
			<!-- //button -->
			
			</form:form>
				
				
			</div>
			<!-- //content_in -->	
		</div>
		<!-- //content -->	
		

	</div>
	<!-- //container -->
</div>
<%
	if("Y".equals(notApprYn) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){
%>
		<script type="text/javascript">
		 	function OnInitCompleted(e){			 	
		 		if(e.editorTarget == CrossEditor){
					e.editorTarget.SetValue(document.getElementById("body_mime_rq").value); // 컨텐츠 내용 에디터 삽입			
				}
		 	}
	 	</script>	
<%
	}else if ( "RA02".equals(command.getTop_role()) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())) { //로그인자가 정검토부서 이고  담당자인경우 InserTmode
%>
		<script type="text/javascript">
			function OnInitCompleted(e){			
				if(e.editorTarget == CrossEditor1){					
					CrossEditor1.SetValue(document.getElementById("body_mime_rq").value); // 컨텐츠 내용 에디터 삽입	
				}	
			}	 			
		</script>
<%
	}else if ("RA02".equals(command.getTop_role()) && lomRq.get("vc_cnsd_dept").equals(command.getBlngt_orgnz())) { //로그인자가 부검토부서 이고  담당자인경우 InserTmode
%>
		<script type="text/javascript">
		function OnInitCompleted(e){						
			if(e.editorTarget == CrossEditor1){
				CrossEditor1.SetValue(document.getElementById("body_mime_rq").value); // 컨텐츠 내용 에디터 삽입	
			}	
		}	 			
		</script>
<% 
	}else if(!"C04305".equals(lomRq.get("cnsd_status")) && "Y".equals(notApprYn)){		//종합검토의견 이 최종 완료가 아니면 변경 모드 로 ..

%>
		<script type="text/javascript">
	 	function OnInitCompleted(e){	
	 		if(e.editorTarget == CrossEditor)	{
				e.editorTarget.SetValue(document.getElementById("body_mime_rq").value); // 컨텐츠 내용 에디터 삽입			
			}
	 	}
	 	</script>	
<% }%>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>		
</body>
</html>