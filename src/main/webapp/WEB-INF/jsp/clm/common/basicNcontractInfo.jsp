<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
//Sungwoo replaced parameter cross-site script 2014-09-17 
String cntrt_id = request.getParameter("cntrt_id");
String cnsdreq_id = request.getParameter("cnsdreq_id");
String isTitleNeeded = request.getParameter("isTitleNeeded");

%>
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>
<style>
	.conclusionHiddenSection1{display:none;}
</style>
<script>
$(function(){
	var cntrt_id = encodeURI('<%=request.getParameter("cntrt_id")%>');
	var cnsdreq_id = encodeURI('<%=request.getParameter("cnsdreq_id")%>');
	var isTitleNeeded = encodeURI('<%=request.getParameter("isTitleNeeded")%>');
	
	if(isTitleNeeded == "Y"){
		$("#divCntrtInfoTitle").show();	
	}
	
	$.ajax({
		url:"/clm/manage/conclusion.do?method=getBasicAndContractInfo",
		data:{cntrt_id:cntrt_id , cnsdreq_id:cnsdreq_id, menu_id:"20130319154642301_0000000355"}
	}).done(function(data, textStatus, xhr){
		
		// Contract information
		bindContractData(data.contractInfo[0]);
		// Attachments
		bindAttachments(data.contractInfo);
		// Related Contract information
		bindRelatedContract(data.relatedContracts);
		// Conclustion information
		bindConclusionInformation(data.contractInfo);
		
		$("#btnCounterpartyPopup").click(function(){
			customerPop(data.list[0].cntrt_oppnt_cd, data.list[0].cntrt_oppnt_cd);
		});	
	}).always(function(){
		$("#divContractInfoModule").unblock();
	});
});

function bindContractData(cntrtInfo){
	// Contract Information
	$("#spReqTitle").text(cntrtInfo.req_title);
	$("#spContractId").text(cntrtInfo.cntrt_no);
	$("#spContTitle").text(cntrtInfo.cntrt_nm);
	$("#spStepStatus").text(cntrtInfo.prcs_depth_desc+"/"+cntrtInfo.depth_status_desc);
	$("#spRequestor").text(cntrtInfo.reqman_nm+"/"+cntrtInfo.reqman_jikgup_nm+"/"+cntrtInfo.req_dep_nm);
	$("#spCounterparty").text(cntrtInfo.customer_nm);
	$("#spIncharge").text(cntrtInfo.respman_nm+"/"+cntrtInfo.respman_jikgup_nm+"/"+cntrtInfo.respman_dept_nm);
	$("#spCompRegNo").text(cntrtInfo.company_reg_no);
	$("#spReviewer").text("[O]"+cntrtInfo.rev_o_nm+" / "+cntrtInfo.rev_o_jikgup+" / "+cntrtInfo.rev_o_dept);
	if(cntrtInfo.rev_s_nm){
		$("#spReviewer").append("<br>").append("[S]").append(cntrtInfo.rev_s_nm+" / "+cntrtInfo.rev_s_jikgup+" / "+cntrtInfo.rev_s_dept);
	}
	$("#spIsMandatoryCounterparty").text((cntrtInfo.mandatory == "Y" ? "Yes" : "No"));
	$("#spContAmount").text(cntrtInfo.cntrt_amt);
	$("#spCurrency").text(cntrtInfo.currency_unit);
	$("#spVendorCustomer").text((cntrtInfo.vendor_type == "V" ? "Vendor" : "Customer"));
	$("#spContrctPeriod").text(cntrtInfo.cntrtperiod_startday+" ~ "+cntrtInfo.cntrtperiod_endday);
	$("#spAutoExtension").text((cntrtInfo.auto_rnew_yn == "Y" ? "Yes" : "No"));
	$("#spPaymentCollection").text(cntrtInfo.payment_gbn_nm);
	$("#spContType").text(cntrtInfo.biz_clsfcn_nm+"/"+cntrtInfo.depth_clsfcn_nm+"/"+cntrtInfo.cnclsnpurps_bigclsfcn_nm+"/"+cntrtInfo.cnclsnpurps_midclsfcn_nm);
	$("#spContMatter").text(cntrtInfo.cntrt_trgt_nm);
	$("#spContMatterDetail").text(cntrtInfo.cntrt_trgt_det);
	$("#spOtherComment").text(cntrtInfo.pshdbkgrnd_purps);
	$("#spSpecialReq").text(cntrtInfo.spcl_cond);
	$("#spGovLaw").text(cntrtInfo.loac);
	$("#spGovLawDetail").text(cntrtInfo.loac_etc);
	$("#spDispute").text(cntrtInfo.dspt_resolt_mthd);
	$("#spDisputeDetail").text(cntrtInfo.dspt_resolt_mthd_det);
	
	// GERP information
	$("#sGerpCode").text(cntrtInfo.gerp_cd);
	$("#sDivName").text(cntrtInfo.gerp_div_nm);
	$("#sCostType").text(cntrtInfo.cost_type_nm);
	
	// Pre Approval Information
	if(cntrtInfo.bfhdcstn_apbtday){
		$("#spApprovalDate").text(cntrtInfo.bfhdcstn_apbtday);
		$("#spApprovalMethod").text(cntrtInfo.bfhdcstn_apbt_mthd_nm);
		$("#spCreatedBy").text(cntrtInfo.preapproval_creator_nm+"/"+cntrtInfo.preapproval_creator_jikgup_nm+"/"+cntrtInfo.preapproval_creator_dept_nm);
		$("#spApprover").text(cntrtInfo.preapproval_approver_nm+"/"+cntrtInfo.preapproval_approver_jikgup_nm+"/"+cntrtInfo.preapproval_approver_dept_nm);
	}
}

//Attachments
function bindAttachments(list){
	var fileList = [];
	var br = fileName = "", fileIcon = "<img src='/images/clm/en/icon/ico_save_w.gif'/>&nbsp;";
	$(list).each(function(idx, item){
		if(item.rev_c_filename && $.inArray("c"+item.rev_c_fileid, fileList) < 0){
			fileList.push("c"+item.rev_c_fileid);
			
			fileName = item.rev_c_filename+"("+getFileSizeFromBytes(item.rev_c_filesize)+")";
			$("#divContractFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.rev_c_fileid+"\")'>"+fileName+"</a>");
		}
		if(item.rev_a_filename && $.inArray("a"+item.rev_a_fileid, fileList) < 0){
			fileList.push("a"+item.rev_a_fileid);
			
			fileName = item.rev_a_filename+"("+getFileSizeFromBytes(item.rev_a_filesize)+")";
			$("#divAppendixFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.rev_a_fileid+"\")'>"+fileName+"</a>");
		}
		if(item.rev_o_filename && $.inArray("o"+item.rev_o_fileid, fileList) < 0){
			fileList.push("o"+item.rev_o_fileid);
			
			fileName = item.rev_o_filename+"("+getFileSizeFromBytes(item.rev_o_filesize)+")";
			$("#divOtherFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.rev_o_fileid+"\")'>"+fileName+"</a>");
		}
		if(item.rev_u_filename && $.inArray("u"+item.rev_o_fileid, fileList) < 0){
			fileList.push("u"+item.rev_u_fileid);
			
			fileName = item.rev_u_filename+"("+getFileSizeFromBytes(item.rev_u_filesize)+")";
			$("#divOutlookFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.rev_u_fileid+"\")'>"+fileName+"</a>");
		}
		// Pre Approval attachment		
		if(item.preapproval_filename && $.inArray("p"+item.preapproval_fileid, fileList) < 0){
			fileList.push("p"+item.preapproval_fileid);
			
			fileName = item.preapproval_filename+"("+getFileSizeFromBytes(item.preapproval_filesize)+")";
			$("#divApprovalAttachment").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.preapproval_fileid+"\")'>"+fileName+"</a>");
		}
		// Copy of Contract
		if(item.copy_filename){
			fileList.push("z"+item.copy_fileid);
			
			fileName = item.copy_filename+"("+getFileSizeFromBytes(item.copy_filesize)+")";
			$("#divCopyAttachments").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.copy_fileid+"\")'>"+fileName+"</a>");
		}
		br = "<br>";
	});
	fileList = null;
}

function bindRelatedContract(cntrts){
	if(cntrts.length > 0){
		var row_content = "";
		$(cntrts).each(function(idx, item){
			row_content = "<tr>";
			row_content += "<td class='tC'>"+item.rel_type_nm+"</td>";
			row_content += "<td><a href=\"javascript:goDetail('"+item.cnsdreq_id+"');\">"+item.relation_cntrt_nm+"</a></td>";
			row_content += "<td class='tC'>"+item.rel_defn+"</td>";
			row_content += "<td class='tC'>"+item.expl+"</td>";
			row_content += "</tr>";
			$("#tbRelatedContract > tbody").append(row_content);
		});
	} else {
		$("#tbRelatedContract > tbody").append("<tr><td colspan='4' align='center'>No Related Contract</td></tr>");
	}
}

function bindConclusionInformation(list){
	var cntrtInfo = list[0];
	$("#spConclusion").text((cntrtInfo.cntrt_cnclsn_yn == "Y" ? "Yes" : "No"));
	$("#spExpectedConclusionDate").text(cntrtInfo.cnclsn_plndday);
	$("#spActualConclusionDate").text(cntrtInfo.cntrt_cnclsnday);
	if(cntrtInfo.expected_signatory_nm){
		$("#spExpectedSignatory").text(cntrtInfo.expected_signatory_nm+"/"+cntrtInfo.expected_signatory_jikgup+"/"+cntrtInfo.expected_signatory_dept);
	}
	$("#spSignMethod").text(cntrtInfo.seal_mthd_nm);
	if(cntrtInfo.seal_mthd == "C02102"){
		$(".conclusionHiddenSection1").show();
		
		$("#spSubSignatory").text(cntrtInfo.signman_nm+"/"+cntrtInfo.signman_jikgup+"/"+cntrtInfo.sign_dept_nm);
		$("#spSubSignDate").text(cntrtInfo.signday);
		if(cntrtInfo.oppnt_signman_nm){
			$("#spCounterpartySingnatory").text(cntrtInfo.oppnt_signman_nm+"/"+cntrtInfo.oppnt_signman_jikgup+"/"+cntrtInfo.oppnt_signman_dept);
			$("#spCounterpartySignDate").text(cntrtInfo.oppnt_signday);
		}
	}
	
	// CC
	$("#spIncharge").text(cntrtInfo.reqman_nm+"/"+cntrtInfo.reqman_jikgup_nm+"/"+cntrtInfo.req_dep_nm);
	if (cntrtInfo.reqman_id == "<c:out value='${command.session_user_id}' />" || "<c:out value='${command.top_role}' />" == "RA01"){
		$("#btnAddCC").show();
	}
	var br = "", cclist = "", cc_id_list = [];
	$(list).each(function(idx, item){
		if(idx == 1){ br = "<br/>"; }
		if(item.cced_id && $.inArray(item.cced_id, cc_id_list) < 0){
			cc_id_list.push(item.cced_id);
			cclist += br + item.cced_nm+"/"+item.cced_jikgup_nm+"/"+item.cced_dept_nm;
		}			
	});
	if(cclist){ 
		$("#spCC").html(cclist); 
	}
	cc_id_list = null;
	
	$("#spCopyRegister").text(cntrtInfo.copy_register_nm+"/"+cntrtInfo.copy_register_jikgup_nm+"/"+cntrtInfo.copy_register_dept_nm);
	$("#spCopyRegisterDate").text(cntrtInfo.cpy_regday);
	$("#spCopyProvider").text(cntrtInfo.origin_provider_nm+"/"+cntrtInfo.origin_provider_jikgup_nm+"/"+cntrtInfo.origin_provider_dept_nm);
	$("#spSubmittedDate").text(cntrtInfo.org_acptday);
	$("#spLegalAdmin").text(cntrtInfo.legal_admin_nm+"/"+cntrtInfo.legal_admin_jikgup_nm+"/"+cntrtInfo.legal_admin_dept_nm);
	$("#spStorageLocation").text(cntrtInfo.org_strg_pos);
	$("#spNote").text(cntrtInfo.org_acpt_dlay_cause);
}

function downloadFile(fileID){
	$("#hidFile_id").val(fileID);
	// 가장 가까운 form을 가져와 submit 한다. 다른 코드에 영향을 미치지 않도록, submit 이후에는 원래 지정되어 있던 action을 다시 지정한다.
	var dest = "/clms/common/clmsfile.do?method=doClmsDownload";
	var closestForm = document.forms[document.forms.length-1];
	var originDest = $("<a></a>").attr("href", closestForm.action);
	originDest = originDest.pathname+originDest.search;
	closestForm.action = dest;
	closestForm.target = "_self";
	closestForm.submit();
	
	closestForm.action = originDest;
}
function getFileSizeFromBytes(bytesize){
	var size = myRound(eval(Math.round(bytesize / 1024 * 100) * .01), 2);
	var unit = 'KB';
	if (size > 1000) {
		size = myRound(Math.round(bytesize *.001 * 100) * .01, 2);
		unit = 'MB';
	}
	return size.toString()+unit;
}
function myRound(num, pos) {
	var posV = Math.pow(10, (pos ? pos : 2));
	return Math.round(num*posV)/posV;
}

// Pop up window in order to add CC
function openChooseClient(){
    var frm = document.frm;
    
    var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
    var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
    var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
    var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
    var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
    
    var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
    
    frm.chose_client.value = items;
            
    PopUpWindowOpen('', "530", "480",true,'PopUpWindow');
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpWindow";
    frm.submit();
 }

// Callback function which CC popup sets back selected person to copy.
function setListClientInfo(returnValue) {
    var arrReturn = returnValue.split("!@#$");
    var innerHtml ="";	
    
    $('#spCC').html("");
    
    if(arrReturn[0]=="") {
    	return ;
    }
    
    for(var i=0; i < arrReturn.length;i++) {
    	var arrInfo = arrReturn[i].split("|");
    	if(i > 0){
			innerHtml += "<br/>";
    	}
   		innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+ arrInfo[0] +"' />";
   		innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+ arrInfo[1] +"' />";
   		innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+ arrInfo[2] +"' />";		        	
   		innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+ arrInfo[3] +"' />";
   		innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+ arrInfo[4] +"' />";
   		innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
    		
    	$('#spCC').html(innerHtml);
    }
    
    // 관련자 리스트 수정 여부 저장
    $("#client_modify_div").val("Y");
    
    // 여기 부터 AJAX 로 실시간 DB 저장 처리   메소드 명 modifyRefCCAJAX
    var options = {   
				url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {}
	};		
	$("#frm").ajaxSubmit(options);	
}
</script>

<input type="hidden" id="client_modify_div" name="client_modify_div"/>
<input type="hidden" id="hidFile_id" name="file_id">
<input type="hidden" name="customer_cd" id="customer_cd"  />
<input type="hidden" name="dodun" id="dodun" />
<input type="hidden" id="hidGerpResult" />
<div class="title_basic" id="divCntrtInfoTitle" style="display:none"><h4><spring:message code="clm.page.msg.manage.contInfo" /></h4></div>
<div id="divContractInfoModule">
	<!-- Contract Information section -->
	<table class="table-style01">
	    <colgroup>
	        <col width="16%" />
	        <col width="16%" />
	        <col width="18%" />
	        <col width="16%" />
	        <col width="18%" />
	        <col width="16%" />
	    </colgroup>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.reqName" /></th><!-- Request title -->
	        <td colspan="3"><span id="spReqTitle"></span></td>
	        <th><spring:message code="clm.page.msg.manage.contId" /></th><!-- Contract ID -->
	        <td><span id="spContractId"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code='clm.page.field.contract.basic.name' /></th><!-- Contract title -->
	        <td colspan="3"><span id="spContTitle"></span></td>
	        <th><spring:message code="clm.page.field.manageCommon.step"/>/<spring:message code="clm.page.field.manageCommon.status" /></th><!-- Step/Status -->
	        <td><span id="spStepStatus"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.reqPerson" /></th><!-- Reviewer -->
	        <td colspan="3"><span id="spRequestor"></span></td>
	        <th><spring:message code='clm.page.msg.common.otherParty' /></th><!-- Counterparty -->
	        <td>
	            <a id="btnCounterpartyPopup" href="javascript:return false;"><span id="spCounterparty"></span></a>
	        </td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.chrgPerson" /></th><!-- In charge -->
	        <td colspan="3"><span id="spIncharge"></span></td>
	        <th><spring:message code='clm.page.field.customer.registerNo' /></th><!-- Company registration -->
	        <td><span id="spCompRegNo"></span></td>
	    </tr>
	    <tr>    
	        <th><spring:message code="clm.page.msg.manage.reviewer" /></th><!-- Reviewer -->
	        <td colspan="3"><span id="spReviewer"></span></td>
	        <th><spring:message code='clm.page.field.customer.IsContractRequired2' /></th><!-- Is Mandatory Counterparty -->
	        <td><span id="spIsMandatoryCounterparty"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.contAmt" /></th><!-- Contract amount -->
	        <td><span id="spContAmount"></span></td>
	        <th><spring:message code='clm.page.field.contract.detail.money' /></th><!-- Currency unit -->
	        <td><span id="spCurrency"></span></td>
	        <th><spring:message code='clm.page.field.customer.vendorType' /></th><!-- Vendor/Customer -->
	        <td><span id="spVendorCustomer"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.contPeriod" /></th><!-- Contract period -->
	        <td><span id="spContrctPeriod"></span></td>
	        <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th> <!-- Automatic extension -->
	        <td><span id="spAutoExtension"></span></td>
	        <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th><!-- Payment/Collection -->
	        <td><span id="spPaymentCollection"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code='clm.page.field.contract.basic.type' /></th><!-- Contract Type -->
	        <td colspan="5"><span id="spContType"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.field.contract.basic.thing" /></th><!--  Contract Matter -->
	        <td><span id="spContMatter"></td>
	        <th><spring:message code="clm.page.field.contract.basic.detail" /></th><!-- If other Please specify details -->
	        <td colspan="3"><span id="spContMatterDetail"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code='clm.page.field.contract.detail.object' /></th><!-- Other comments -->
	        <td colspan="5"><span id="spOtherComment"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.etcMain" /></th><!-- Special request -->
	        <td colspan="5"><span id="spSpecialReq"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.field.contract.detail.loac"/></th><!-- Governing Law -->
	        <td><span id="spGovLaw"></span></td>
	        <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th><!-- Details of Govering Law -->
	        <td colspan="3"><span id="spGovLawDetail"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th><!-- Dispute Resolution Method -->
	        <td><span id="spDispute"></span></td>
	        <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th><!-- Details of Dispute Resolution Method -->
	        <td colspan="3"><span id="spDisputeDetail"></span></td>
	    </tr>
	    <!-- Attachments -->
	    <tr class="slide-target02 slide-area">
		      <th class='rightline' id="spanRow"><spring:message code="clm.page.field.contract.basic.filename" /></th>
		      <td class="tL"><span class="blueD"><spring:message code="clm.page.field.contract.basic.filename1" /></span></td>
		      <td colspan="4"><div id="divContractFile"></div></td>
		</tr>
		<tr id="fileAppendics" style="display:none;">
		     <td class='tL'><span class="blueD"><spring:message code='clm.page.field.srch.attach' /></span></td>
		     <td colspan="4"><div id="divAppendixFile"></div></td>
		</tr>
		<tr id="fileOther" style="display:none;">
		     <td class='tL'><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td>
		     <td colspan="4"><div id="divOtherFile"></div></td>
		</tr>
		<tr id="fileOutlook" style="display:none;">
		      <td class='tL'><span class="blueD"><spring:message code='clm.page.msg.common.outlook' /></span></td>
		      <td colspan="4"><div id="divOutlookFile"></div></td>
		</tr>
	</table>
	
	<!-- GERP Information section -->
	<div class="title_basic3"><spring:message code="clm.page.field.contract.gerp.gerpInformation"/></div>
	<table id="tb_grp_info" class="table-style01">
	   <colgroup>
	   		<col width="18%"/>
	   		<col width="16%"/>
	   		<col width="18%"/>
	   		<col width="16%"/>
	   		<col width="16%"/>
	   		<col width="16%"/>
	   </colgroup>
	   <tbody>
	    	<tr>
	         	<th><spring:message code="clm.page.field.customer.gerpCode"/></th>
	         	<td><span id="sGerpCode"></span></td>
	         	<th><spring:message code="clm.page.field.contract.gerp.headDivisionCode"/></th>
	         	<td><span id="sDivName"></span></td>
	         	<th><spring:message code="clm.page.field.contract.gerp.costType"/></th>
	         	<td><span id="sCostType"></span></td>
	     	</tr>
	    </tbody>
	</table>
	
	<!-- Pre-Approval section -->
	<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
	<table class="table-style01">
	    <colgroup>
	        <col width="12%" />
	        <col width="38%" />
	        <col width="12%" />
	        <col width="38%" />
	    </colgroup>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
	        <td class="tL"><span id="spApprovalDate"></span></td>
	        <th><spring:message code="clm.page.msg.manage.apprType" /></th>
	        <td><span id="spApprovalMethod"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.proposer" /></th>
	        <td><span id="spCreatedBy"></span></td>
	        <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
	        <td><span id="spApprover"></span></td>
	    </tr>
	    <tr>
	        <th><spring:message code="clm.page.msg.manage.attachData" /></th>
	        <td colspan="3"><div id="divApprovalAttachment"></div></td>
	    </tr>
	</table>
	
	<!-- Related Contract section -->
	<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
	<div id="div_rel_contract">
	    <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="tbRelatedContract">
	        <colgroup>
	            <col width="15%" />
	            <col width="50%" />
	            <col width="10%" />
	            <col width="25%" />
	            <col/>                  
	        </colgroup>
	        <tbody id="relatedContractBody">
	        	<tr>
	            	<th><spring:message code="clm.page.msg.manage.relation" /></th>
	            	<th><spring:message code="clm.page.msg.manage.relCont" /></th>
	            	<th><spring:message code="clm.page.msg.manage.define" /></th>
	            	<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
	        	</tr>
	        </tbody>      
	    </table>
	</div>
	
	<!-- Conclusion Information section -->
	<div class="title_basic"><h4><spring:message code="clm.page.msg.manage.conclInf" /></h4></div>
	<div class="title_basic3"><h3><spring:message code="clm.page.msg.manage.bscInf" /></h3></div>
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="18%" />
			<col width="16%" />
			<col width="18%" />
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.conclYn" /></th>
			<td><span id="spConclusion"></span></td>
			<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
		  	<td><span id="spExpectedConclusionDate"></span></td>
		  	<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
			<td><span id="spActualConclusionDate"></span></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.signRes" /></th>
			<td colspan="3"><span id="spExpectedSignatory"></span></td>
			<th><spring:message code="clm.page.msg.manage.signType" /></th>
			<td><span id="spSignMethod"></span></td>
		</tr>	
		<tr class="conclusionHiddenSection1">
			<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
			<td colspan="3"><span id="spSubSignatory"></span></td>
			<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
			<td><span id="spSubSignDate"></span></td>
		</tr>
		<tr class="conclusionHiddenSection1">
			<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
			<td colspan="3"><span id="spCounterpartySingnatory"></span></td>
			<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
			<td><span id="spCounterpartySignDate"></span></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
			<td colspan="5"><span id="spIncharge"></span></td>
		</tr>
		<tr>
			<th class="borTz02">
				<spring:message code="clm.page.msg.manage.relPerson" />
				<!-- Add CC button -->
				<span class="btn_all_b" onclick='javascript:openChooseClient();' style="display:none; position: absolute; margin-left: 19px;" id="btnAddCC">
		        	<span class="add"></span>
		        	<a><spring:message code='clm.page.msg.manage.add' /></a>
		        </span>
			</th>
			<td colspan="5">
				<span id="spCC"></span>
			</td>
		</tr>
		<!-- Contract Copy Information -->
		<tr>
			<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
			<td colspan="3"><span id="spCopyRegister"></span></td>
			<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
			<td><span id="spCopyRegisterDate"></span></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
			<td class="last-td" colspan="5"><div id="divCopyAttachments"></div></td>
		</tr>
		<!-- Origin Contract Information -->
		<tr>
			<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
			<td colspan="3"><span id="spCopyProvider"></span></td>
			<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
			<td><span id="spSubmittedDate"></span></td>
		</tr>
		<tr>
			<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
			<td colspan="3"><span id="spLegalAdmin"></span></td>
			<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
			<td><span id="spStorageLocation"></span></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.common.memo" /></th>
			<td colspan="5"><span id="spNote"></span></td>
		</tr>
	</table>
</div>