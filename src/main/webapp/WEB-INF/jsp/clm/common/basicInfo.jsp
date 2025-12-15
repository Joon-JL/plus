<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
//Sungwoo replaced parameter cross-site script 2014-09-17 
String cnsdreq_id = request.getParameter("cnsdreq_id");
String is_contractreview = request.getParameter("is_contactreview");
%>
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>
<script>
$(function(){
	var cnsdreq_id = '<%= cnsdreq_id %>';
	var isContractReview = '<%= is_contractreview %>';
	
	$.ajax({
		url:"/clm/manage/consideration.do?method=getDataForBasicInformationSection",
		data:{cnsdreq_id:cnsdreq_id, menu_id:"20130319154642301_0000000355"}
	}).done(function(data, textStatus, xhr){
		if(isContractReview === "Y"){
			contractReviewViewPoint(data);
		} else {
			myContractViewPoint(data);
		}
	}).always(function(){
		$("#tbBasicInfo").unblock();
	});
});

function contractReviewViewPoint(data){
	var cnt = 1, br="";
	var fileName = "", fileIcon = "<img src='/images/clm/en/icon/ico_save_w.gif'/>&nbsp;";
	var fileList = [];
	var demnd_seq = 1;
	$(data.list).each(function(idx, item){
		if(idx === 0){
			var isReviewerOfThisContract = (item.rev_o_id == "<c:out value='${command.session_user_id}' />" || item.rev_s_id == "<c:out value='${command.session_user_id}' />");
			if("<c:out value='${command.top_role}' />" == "RA01" || isReviewerOfThisContract){
				var imgSrc = (item.mark_num == "3" ? "/images/las/en/icon/icon_b_red.gif" : "/images/las/en/icon/icon_b_white.gif");
				var cntrt_id = "<c:out value='${lomRq.CNTRT_ID}'/>";
				var imgId = "icon_"+cntrt_id;
				$("#spReqTitle").append("<img id="+imgId+" src='"+imgSrc+"' class='img_align' alt=\"<c:out value='${lomRq.mark_num}'/>\" style='cursor:pointer' />&nbsp;");
				$("#"+imgId).click(function(){
					setMarkUpAJAX(cntrt_id);
				});
			}
			$("#spReqTitle").append(item.req_title);
			$("#spRequestor").text(item.req_nm+" / "+item.req_jikgup+" / "+item.req_dept);
			$("#spReqDate").text(item.req_dt);
			$("#spStepStatus").text(item.step+" / "+item.status);
			$("#spInCharge").text(item.ic_nm+" / "+item.ic_jikgup+" / "+item.ic_dept);
			if(item.rev_o_nm){
				$("#spReviewer").text("[O]"+item.rev_o_nm+" / "+item.rev_o_jikgup+" / "+item.rev_o_dept);
			}
			if(item.rev_s_nm){
				br = (item.rev_o_nm ? "<br>" : "");
				$("#spReviewer").append(br).append("[S]").append(item.rev_s_nm+" / "+item.rev_s_jikgup+" / "+item.rev_s_dept);
				br = "";
			}
			if(item.reply_dt){
				$("#spReplyDate").text(item.reply_dt);
			}
			// Show Add button in CC filed.
			if(isReviewerOfThisContract){
				$("#btnAddCC").show();
			}
			if(item.cnsd_level == 1){
				$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.purposeDetails"/>');
				$("#divContent").html(item.purpose);
			} else{
				// re-request일 경우.
				$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.reRequestComment"/>');
				$("#divContent").html(item.req_opinion);
			}
			
			// Show tr which include content td.
			$("#trContent").show();
			
			if(item.req_a_filename){++cnt; $("#fileAppendics").show();}
			if(item.req_o_filename){++cnt; $("#fileOther").show();}
			if(item.req_u_filename){++cnt; $("#fileOutlook").show();}
			
			if(cnt > 0){
				$("#spanRow").attr("rowspan", cnt); 
			}
		}
		
		if(item.req_c_filename && $.inArray("c"+item.req_c_fileid, fileList) < 0){
			fileList.push("c"+item.req_c_fileid);
			
			fileName = item.req_c_filename+"("+getFileSizeFromBytes(item.req_c_filesize)+")";
			$("#divContractFile").append(br).append(fileIcon).append("<a href=javascript:downloadFile(\""+item.req_c_fileid+"\");>"+fileName+"</a>");
		}
		if(item.req_a_filename && $.inArray("a"+item.req_a_fileid, fileList) < 0){
			fileList.push("a"+item.req_a_fileid);
			
			fileName = item.req_a_filename+"("+getFileSizeFromBytes(item.req_a_filesize)+")";
			$("#divAppendixFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.req_a_fileid+"\")'>"+fileName+"</a>");
		}
		if(item.req_o_filename && $.inArray("o"+item.req_o_fileid, fileList) < 0){
			fileList.push("o"+item.req_o_fileid);
			
			fileName = item.req_o_filename+"("+getFileSizeFromBytes(item.req_o_filesize)+")";
			$("#divOtherFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.req_o_fileid+"\")'>"+fileName+"</a>");
		}
		br = "<br>";
		//Sungwoo replaced for the avoid duplication CCed list 2014-11-07
		if(item.cc_nm && item.demnd_seqno >= demnd_seq){
			// $("#spCCedList").append(item.cc_nm+" / "+item.cc_jikgup+" / "+item.cc_dept+"<br/>======");

            $("#spCCedList").append(item.cc_nm + " / " + item.cc_jikgup + " / " + item.cc_dept //+"<br/>");
                + "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+demnd_seq+"'  /><input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+item.cc_nm+"' /><input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+item.cc_nm+"' /><input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+item.cc_jikgup+"' /><input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+item.cc_dept+"' /><br/>"); //

            demnd_seq++;
		}
	});
	fileList = null;
}

function myContractViewPoint(data){
	var cnt = 1, br="";
	var fileName = "", fileIcon = "<img src='/images/clm/en/icon/ico_save_w.gif'/>&nbsp;";
	var fileList = [];
	var demnd_seq = 1;
	$(data.list).each(function(idx, item){
		if(idx === 0){
			$("#spReqTitle").text(item.req_title);
			$("#spRequestor").text(item.req_nm+" / "+item.req_jikgup+" / "+item.req_dept);
			$("#spReqDate").text(item.req_dt);
			$("#spStepStatus").text(item.step+" / "+item.status);
			$("#spInCharge").text(item.ic_nm+" / "+item.ic_jikgup+" / "+item.ic_dept);
			if(item.rev_o_nm){
				$("#spReviewer").text("[O]"+item.rev_o_nm+" / "+item.rev_o_jikgup+" / "+item.rev_o_dept);
			}
			if(item.rev_s_nm){
				br = (item.rev_o_nm ? "<br>" : "");
				$("#spReviewer").append(br).append("[S]").append(item.rev_s_nm+" / "+item.rev_s_jikgup+" / "+item.rev_s_dept);
				br = "";
			}
			if(item.reply_dt){
				$("#spReplyDate").text(item.reply_dt);
			}
			// Show Add button in CC filed.
			if(item.reqman_id === "<c:out value='${command.session_user_id}' />" || "<c:out value='${command.top_role}' />" === "RA01"){
				$("#btnAddCC").show();
			}
			switch(item.depth_status){
				case "C02601":	// review/draft saved
				case "C02606":	// Review in progress
					if(item.cnsd_level == 1){
						$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.purposeDetails"/>');
						$("#divContent").html(item.purpose);
					} else if(item.is_final === "Y"){
						$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.finalVer" /><br/><spring:message code="clm.page.msg.manage.reviewReqCont" />' );
						$("#divContent").html(item.req_opinion);
					} else{
						// re-request일 경우.
						$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.reRequestComment"/>');
						$("#divContent").html(item.req_opinion);
					}
					break;
				case "C02607":	// Holding
					$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.heldReason"/>');
					$("#divContent").html(item.hold_cause);
					break;
				case "C02608":	// Replied
					if(item.is_final === "Y"){
						$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.finalVer" /><br/>');
					} 
					$("#spContentTitle").append('<spring:message code="clm.page.msg.manage.reviewOpinion" />');
					$("#divContent").html(item.reply);
					break;
				case "C02609":	// Admin Replied
					$("#spContentTitle").html('<spring:message code="las.page.field.contractManager.feedbackComment"/>');
					$("#divContent").html(item.admin_opinion);
					
					// Admin replied 일 경우 Reviewer에 [Legal Admin]으로 시작하는 legal admin을 표시하고, reply date에서 admin opinion을 입력한 시간을 표시한다.
					$("#spReviewer").append("[Legal Admin]").append(item.admin_nm+" / "+item.admin_jikgup+" / "+item.admin_dept);
					$("#spReplyDate").text(item.admin_return_dt);
					break;
				default:
					$("#spContentTitle").html('<spring:message code="clm.page.msg.manage.finalVer" /><br/><spring:message code="clm.page.msg.manage.reviewOpinion" />');
					$("#divContent").html(item.reply);
			}
			// Show tr which include content td.
			$("#trContent").show();
			
			if(!item.reply){
				if(item.req_a_filename){++cnt; $("#fileAppendics").show();}
				if(item.req_o_filename){++cnt; $("#fileOther").show();}
				if(item.req_u_filename){++cnt; $("#fileOutlook").show();}
			} else {
				if(item.rev_a_filename){++cnt; $("#fileAppendics").show();}
				if(item.rev_o_filename){++cnt; $("#fileOther").show();}
				if(item.req_u_filename){++cnt; $("#fileOutlook").show();}
			}
			if(cnt > 0){
				$("#spanRow").attr("rowspan", cnt); 
			}
		}
		
		if(!item.reply){
			if(item.req_c_filename && $.inArray("c"+item.req_c_fileid, fileList) < 0){
				fileList.push("c"+item.req_c_fileid);
				
				fileName = item.req_c_filename+"("+getFileSizeFromBytes(item.req_c_filesize)+")";
				$("#divContractFile").append(br).append(fileIcon).append("<a href=javascript:downloadFile(\""+item.req_c_fileid+"\");>"+fileName+"</a>");
			}
			if(item.req_a_filename && $.inArray("a"+item.req_a_fileid, fileList) < 0){
				fileList.push("a"+item.req_a_fileid);
				
				fileName = item.req_a_filename+"("+getFileSizeFromBytes(item.req_a_filesize)+")";
				$("#divAppendixFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.req_a_fileid+"\")'>"+fileName+"</a>");
			}
			if(item.req_o_filename && $.inArray("o"+item.req_o_fileid, fileList) < 0){
				fileList.push("o"+item.req_o_fileid);
				
				fileName = item.req_o_filename+"("+getFileSizeFromBytes(item.req_o_filesize)+")";
				$("#divOtherFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.req_o_fileid+"\")'>"+fileName+"</a>");
			}
		} else {
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
		}
		if(item.rev_u_filename){
			fileName = item.rev_u_filename+"("+getFileSizeFromBytes(item.rev_u_filesize)+")";
			$("#divOutlookFile").append(br).append(fileIcon).append("<a href='javascript:downloadFile(\""+item.rev_u_fileid+"\")'>"+fileName+"</a>");
		}
		br = "<br>";
		
		//Sungwoo replaced for the avoid duplication CCed list 2014-11-07
		if(item.cc_nm && item.demnd_seqno >= demnd_seq) {
            $("#spCCedList").append(item.cc_nm + " / " + item.cc_jikgup + " / " + item.cc_dept //+"<br/>");
                 + "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+demnd_seq+"'  /><input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+item.cc_nm+"' /><input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+item.cc_nm+"' /><input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+item.cc_jikgup+"' /><input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+item.cc_dept+"' /><br/>"); //
            demnd_seq++;
        }
    });
    fileList = null;
}


/*
 * Sungwoo commented 2014-09-03. 전체 페이지 사용을 위해 공통페이지로 이동처리 
 */
/*
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
*/
function getFileSizeFromBytes(bytesize){
	var size = myRound(eval(Math.round(bytesize / 1024 * 100) * .01), 2);
	var unit = 'KB';
	if (size > 1000) {
		size = myRound(Math.round(size *.001 * 100) * .01, 2);
		unit = 'MB';
	}
	return size.toString()+unit;
}
function myRound(num, pos) {
	var posV = Math.pow(10, (pos ? pos : 2));
	return Math.round(num*posV)/posV;
}

// set high priority request
function setMarkUpAJAX(cntrt_id) {
	
	var frm = document.frm;
	var mark_src = "";
	var flag= "";		
	
	frm.mark_cntrt_id.value = cntrt_id;
	
	if($('#icon_' + cntrt_id).attr('alt')=='3'){
		mark_src = "/images/las/en/icon/icon_b_white.gif";
		$("#mark_num").val("1");
		flag = "unchk";			
	} else {
		mark_src = "/images/las/en/icon/icon_b_red.gif";
		$("#mark_num").val("3");			
	}
		
	var options = {   
		url: "<c:url value='/clm/review/consideration.do?method=setMarkUpAJAX' />",
		type: "post",
		dataType: "json",
		success: function(responseText, statusText) {
			if(responseText.returnCnt != 0) {
				var html = "";
				$.each(responseText, function(entryIndex, entry) {
					var return_val = entry['return_val'];
					if(return_val == '1'){
						if(flag=='unchk'){
							$('#icon_' + cntrt_id).attr('alt','1');
						} else {
							$('#icon_' + cntrt_id).attr('alt','3');
						}
						$('#icon_' + cntrt_id).attr('src',mark_src);
					}									
				});						
			}	 
		}
	};		
	$("#frm").ajaxSubmit(options);	
}

// CC button click event, open a pop-up for user search.
function openChooseClient(){
    // var frm = document.frm;
    var frm = document.getElementById('frm');
    // var frm = document.getElementById('miniFrm');

    if (!frm) {
        console.error("Error: Form with ID 'frm' not found.");
        // Consider returning here or showing a user message instead of an alert
        return;
    }

    var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
    var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
    var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
    var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
    var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
    
    var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
    document.getElementById('chose_client').value = items;
    // frm.chose_client.value = items;
    // frm.elements['chose_client'].value = items;
    // console.log("1 : " +  document.getElementById('chose_client').value);
    // console.log("2 : " +  document.getElementById('chose_client').value);

    // alert(frm.chose_client.value );
            
    PopUpWindowOpen('', "530", "480",true,'PopUpWindow');
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpWindow";
    frm.submit();
 }
 
 // To receive user search result who will be added as CC.
function setListClientInfo(returnValue) {
    var arrReturn = returnValue.split("!@#$");
    var innerHtml ="";
    var isListEmpty = (arrReturn.length === 1 && arrReturn[0] === ""); // True if result is just "" or if no separator was found

    console.log('arrReturn[0] : ' + arrReturn[0]);//
    // if(arrReturn[0]=="") { return ; }
    
    $('#spCCedList').html("");

    var trgtmanIds = []; // Array to collect IDs

    if (!isListEmpty) {
        for(var i=0; i < arrReturn.length;i++) {
            var arrInfo = arrReturn[i].split("|");

            if (arrInfo.length >= 5) {
                // Add the ID to our array for later handling
                trgtmanIds.push(arrInfo[1]);

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
            }
        }
        $('#spCCedList').html(innerHtml);
    }


    // 관련자 리스트 수정 여부 저장
    document.getElementById('client_modify_div').value = "Y";
    // $("#client_modify_div").val("Y");
    
    // 여기 부터 AJAX 로 실시간 DB 저장 처리   메소드 명 modifyRefCCAJAX
    var options = {   
		url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
		type: "post",
		dataType: "json"
	};		
	
    $("#frm").ajaxSubmit(options);	
 }
</script>
<!-- Sungwoo commented 2014-09-03 동적 생성처리로 변경 -->
<!-- <input type="hidden" id="hidFile_id" name="file_id"> -->

<input type="hidden" id="mark_cntrt_id" name="mark_cntrt_id">
<input type="hidden" id="mark_num" name="mark_num" >
<input type="hidden" id="client_modify_div" name="client_modify_div">
<input type="hidden" name="chose_client" id="chose_client" />
<table cellspacing="0" cellpadding="0" class="table-style01" id="tbBasicInfo">
	<colgroup>              
	    <col width="15%" />
	    <col width="10%" />
	    <col width="40%" />
	    <col width="15%" />
	    <col width="20%" />
	</colgroup>
    <tr>
        <th><spring:message code="clm.page.field.manageRequest.reqTitle"/></th>
        <td colspan="6">[basicinfo=]<span id="spReqTitle"></span></td>
    </tr>
    <tr class="lineAdd">
        <th><spring:message code="clm.page.field.manageRequest.demndmanNm"/></th>
        <td colspan="2"><span id="spRequestor"></span></td>
        <th><spring:message code="clm.page.field.manageRequest.demndDt"/></th>
        <td><span id="spReqDate"></span></td>
    </tr>
    <tr class="lineAdd">
        <th><spring:message code="clm.page.msg.manage.chrgPerson"/></th>
        <td colspan="2"><span id="spInCharge"></span></td>
        <th><spring:message code="clm.page.field.manageCommon.step"/>/<spring:message code="clm.page.field.manageCommon.status" /></th>
        <td><span id="spStepStatus"></span></td>
    </tr>
    <tr class="lineAdd">
        <th><spring:message code="clm.page.msg.manage.reviewer"/></th>
        <td colspan="2"><span id="spReviewer"></span></td>
        <th><spring:message code="clm.page.msg.manage.replyDate" /></th>
        <td><span id="spReplyDate"></span></td>
    </tr>
    <tr class="lineAdd">
        <th>
        	<spring:message code="clm.page.field.myapproval.etcinfo"/>
        	<!-- Add button -->
	        <span class="btn_all_b" onclick='javascript:openChooseClient();' style="display:none; position: absolute; margin-left: 19px;" id="btnAddCC">
	        	<span class="add"></span>
	        	<a><spring:message code='clm.page.msg.manage.add' /></a>
	        </span>
        </th>
        <td colspan="6">
	        <span id="spCCedList"></span>
        </td>
    </tr>
    <tr id="trContent" style="display:none;">
        <th class="borTz02"><span id="spContentTitle"></span></th>
        <td colspan="6">
			<div id="divContent" style="width:100%"></div>
		</td>
    </tr>
	<tr class="slide-target02 slide-area">
	      <th class='rightline' id="spanRow"><spring:message code="clm.page.field.contract.basic.filename" /></th>
	      <td class="tL"><span class="blueD"><spring:message code="clm.page.field.contract.basic.filename1" /></span></td>
	      <td colspan="3"><div id="divContractFile"></div></td>
	</tr>
	<tr id="fileAppendics" style="display:none;">
	     <td class='tL'><span class="blueD"><spring:message code='clm.page.field.srch.attach' /></span></td>
	     <td colspan="3"><div id="divAppendixFile"></div></td>
	</tr>
	<tr id="fileOther" style="display:none;">
	     <td class='tL'><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td>
	     <td colspan="3"><div id="divOtherFile"></div></td>
	</tr>
	<tr id="fileOutlook" style="display:none;">
	      <td class='tL'><span class="blueD"><spring:message code='clm.page.msg.common.outlook' /></span></td>
	      <td colspan="3"><div id="divOutlookFile"></div></td>
	</tr>
</table>
