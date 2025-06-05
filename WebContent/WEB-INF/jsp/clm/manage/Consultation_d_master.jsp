<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
/*
화면별로 특화정보 <table> 구성이 틀려서 페이지 플래그로 구분하기위해
 PG001 : 계약체결
 PG002 : 계약등록
*/
HttpSession ss_special_page_flag = request.getSession(false);
ss_special_page_flag.setAttribute("special_page_flag","PG001");

ListOrderedMap contractLom 	 	=  (ListOrderedMap)request.getAttribute("contractLom");
%>
<script language="javascript">
	$(document).ready(function(){
		//지불구분
		getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C020&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.payment_gbn}'/>');

		//사전승인 승인 방법
		getCodeSelectByUpCd("frm", "bfhdcstn_apbt_mthd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C028&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.bfhdcstn_apbt_mthd}'/>');
		
		paymentGbnChange();
	});	
	
	function paymentGbnChange(){  		
  		//payment_gbn 지수불 구분이 해당사항 없음이면 계약 금액 은 입력 불가 처리
  		//계약금액,통화단위 ,계약단가 비활성화
  		if($('#payment_gbn > option:selected').val() == "C02004" ){
  			
  			//cntrt_amt 계약금액			
			$("#cntrt_amt").val("");
			$("input[name=cntrt_amt]").attr('disabled', true);
			$("input[name=cntrt_untprc]").attr('checked', false);
			$("#crrncy_unit").attr('disabled', true);
			$("#crrncy_unit").html("<option value=><spring:message code='clm.page.msg.manage.notBe' /></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			document.getElementById("trCntrtUntprc").style.display="none";
			$("#cntrt_untprc_expl").val("");
		}else{
			
			$("input[name=cntrt_amt]").attr('disabled', false);
			getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.crrncy_unit}'/>');
			$("#input[name=cntrt_untprc]").attr('disabled', false);
			
			$("#crrncy_unit").attr('disabled', false);
			$("input[name=cntrt_untprc]").attr('disabled', false);
			
			if(frm.page_gbn.value == 'modify'){
				if($("#cntrt_untprc_expl").val()){
	  				document.getElementById("trCntrtUntprc").style.display="block";
	  				$("[name=cntrt_untprc]").attr('checked', true);
	  			}
			}
		}
  	}
	
  	//단가내역 요약 첨부파일 초기화
  	function initCntrtUntprcExpl(){
  		frm.target          = "fileList3";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120211";
	     frm.ref_key.value = "";
	     frm.view_gbn.value = "upload";
	     frm.fileInfoName.value = "fileInfos3";
	     frm.fileFrameName.value = "fileList3";
	     frm.submit();
  	}	
  	
	 /**
	 * 계약단가 활성화시 
	 */
	 function clickCntrtUntprc(){
		 var frm = document.frm;
		 if($("[name=cntrt_untprc]").is(":checked")){ //단가로 체결
			 document.getElementById("trCntrtUntprc").style.display="block";
		 }else{ //계약으로 체결
			 frm.cntrt_untprc_expl.value = "";
		 	 document.getElementById("trCntrtUntprc").style.display="none";
		 }		 
	 }

</script>

<!-- toptable-->
	<div id="master-table" style="display:none;">
	<table border="0" cellspacing="0" cellpadding="0" class="table-style03">
		<colgroup>
			<col width="16%" />
			<col width="11%" />
			<col width="19%" />
			<col width="14%" />
			<col width="12%" />
			<col width="12%" />
			<col width="17%" />
		</colgroup>
		<tr>
			<th><spring:message code='clm.page.field.contract.basic.name' /></th>
			<td colspan="6"><c:out value="${contractLom.cntrt_nm}" />
				<input type="hidden" name="cntrt_nm" id="cntrt_nm" value="<c:out value='${contractLom.cntrt_nm}'/>" />
			</td>
		</tr>
		<tr>
			<th><spring:message code='clm.page.msg.common.otherParty' /></th>
			<td colspan="2">
				<input type="hidden" name="customer_cd" id="customer_cd" value="" />
				<input type="hidden" name="dodun" id="dodun" value="" />
				
				<a href="javascript:customerPop('<c:out value="${contractLom.cntrt_oppnt_cd}" />','<c:out value="${contractLom.cntrt_oppnt_cd}" />');">
					<c:out value="${contractLom.cntrt_oppnt_nm}" />
				</a>
			</td>			
			<th><spring:message code='clm.page.field.customer.registerNo' /></th>
			<td><c:out value="${contractLom.cntrt_oppnt_rprsntman}" /></td>
			<th><spring:message code='clm.page.field.customer.contractRequired' /></th>
			<td><c:out value="${contractLom.cntrt_oppnt_type_nm}" /></td>
		</tr>
 		<!-- 계약 상대방 담당자 start -->
		<c:choose>
			<c:when test="${command.page_gbn=='modify'}">
				<c:choose>
					<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
						<c:choose>
							<c:when test="${command.session_user_id == command.reqman_id}">
								<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
								<td colspan="2"><input type="text" class="text_full" name="cntrt_oppnt_respman" id="cntrt_oppnt_respman" value="<c:out value='${contractLom.cntrt_oppnt_respman}' />"/></td>
								<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
								<td><input type="text" class="text_full" name="cntrt_oppnt_telno" id="cntrt_oppnt_telno" value="<c:out value='${contractLom.cntrt_oppnt_telno}' />"/></td>
								<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
								<td><input type="text" class="text_full" name="cntrt_oppnt_email" id="cntrt_oppnt_email" value="<c:out value='${contractLom.cntrt_oppnt_email}' />"/></td>
							</c:when>
							<c:otherwise>
								<c:if test='${contractLom.cntrt_oppnt_respman != "" }'>
									<tr>
										<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
										<td colspan="2"><c:out value="${contractLom.cntrt_oppnt_respman}" /></td>
										<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
										<td><c:out value="${contractLom.cntrt_oppnt_telno}" /></td>
										<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
										<td><c:out value="${contractLom.cntrt_oppnt_email}" /></td>
									</tr>
								</c:if>
							</c:otherwise>
						</c:choose>	
					</c:when>
					<c:otherwise>
						<c:if test='${contractLom.cntrt_oppnt_respman != "" }'>
							<tr>
								<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
								<td colspan="2"><c:out value="${contractLom.cntrt_oppnt_respman}" /></td>
								<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
								<td><c:out value="${contractLom.cntrt_oppnt_telno}" /></td>
								<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
								<td><c:out value="${contractLom.cntrt_oppnt_email}" /></td>
							</tr>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:if test='${contractLom.cntrt_oppnt_respman != "" }'>
					<tr>
						<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
						<td colspan="2"><c:out value="${contractLom.cntrt_oppnt_respman}" /></td>
						<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
						<td><c:out value="${contractLom.cntrt_oppnt_telno}" /></td>
						<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
						<td><c:out value="${contractLom.cntrt_oppnt_email}" /></td>
					</tr>
				</c:if>
			</c:otherwise>
		</c:choose>
		<!-- 계약 상대방 담당자 end --> 
		<tr>
			<th><spring:message code='clm.page.field.contract.basic.type' /></th>
			<td colspan="6">
			<c:out value='${contractLom.biz_clsfcn_nm}'/> / <c:out value='${contractLom.depth_clsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_bigclsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_midclsfcn_nm}'/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.field.contract.basic.thing" /></th>
			<td colspan="2"><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
			<th><spring:message code="clm.page.field.contract.basic.detail" /></th>
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<td colspan="3"><input type="text" class="text_full" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${contractLom.cntrt_trgt_det}' />"/></td>
								</c:when>
								<c:otherwise>
									<td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" /></td>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" /></td>
						</c:otherwise>
					</c:choose>
				</c:when>
			 	<c:otherwise>
			 		<td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" /></td>
			 	</c:otherwise>
			 </c:choose>			
			
		</tr>
		<!-- 추진목적 및 배경 -->
		<c:choose>
			<c:when test="${command.page_gbn=='modify'}">
				<c:choose>
					<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
						<c:choose>
							<c:when test="${command.session_user_id == command.reqman_id}">
								<tr>
									<th><spring:message code='clm.page.field.contract.detail.object' /></th>
									<td colspan="6">
									<input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="" />
									<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${contractLom.pshdbkgrnd_purps}'/>" />
									<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%>
									</td>
									
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th><spring:message code='clm.page.field.contract.detail.object' /></th>
									<td colspan="6"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/>
								</tr>
							</c:otherwise>
						</c:choose>		
					</c:when>
					<c:otherwise>
						<tr>
							<th><spring:message code='clm.page.field.contract.detail.object' /></th>
							<td colspan="6"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr>
					<th><spring:message code='clm.page.field.contract.detail.object' /></th>
					<td colspan="6"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/>
				</tr>
			</c:otherwise>
		</c:choose>		
					
		<tr>
			<th><spring:message code='clm.page.field.contract.detail.effect' /></th>
			<td colspan="6">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<textarea name="antcptnefct" id="antcptnefct" cols="10" rows="7" class="text_area_full" onkeyup="f_chk_byte(this,600)" maxLength="1000"><c:out value="${contractLom.antcptnefct}" /></textarea>
								</c:when>
								<c:otherwise>
									<!--<c:out value="${contractLom.antcptnefct}" escapeXml="false"/>-->
									<%=StringUtil.convertEnterToBR((String)contractLom.get("antcptnefct")) %>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<!--<c:out value="${contractLom.antcptnefct}" escapeXml="false"/>-->
							<%=StringUtil.convertEnterToBR((String)contractLom.get("antcptnefct")) %>
						</c:otherwise>	
					</c:choose>
				</c:when>
				<c:otherwise>
					<!--<c:out value="${contractLom.antcptnefct}" escapeXml="false"/>-->
					<%=StringUtil.convertEnterToBR((String)contractLom.get("antcptnefct")) %>
				</c:otherwise>
			</c:choose>		
			</td>	
		</tr>
		<tr>
			<th rowspan="3"><spring:message code="clm.page.field.contract.basic.filename" /></th>
			<td><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename1' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" width="100%" height="60px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
			</td>
		</tr>
		<tr>
			<td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.manage.attachment_br' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" width="100%" height="60px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
		<tr>
			<td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="66px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>				
			</td>
		</tr>	
	</table>
		<!-- //top table -->

		<!-- bottom table -->
	<div class="tal_subBox01" id="contract-master">
		<!-- tab02 -->
		<div class="t_titBtn">
			<ul class="tab_basic05" id="tab_contents_sub">
				<li class="on"><a href="javascript:subTitleTabMove('1')"><spring:message code="clm.page.tab.title.contractdetail"/></a></li>
				<li><a href="javascript:subTitleTabMove('2')"><spring:message code="clm.page.tab.title.contractprecsnd"/></a></li>
				<li id="contract-relationInfoTab"><a href="javascript:subTitleTabMove('4')"><spring:message code="clm.page.field.contract.detail.relation"/></a></li>
				<!-- <li class="on"><a href="javascript:subTitleTabMove('3')"><spring:message code="clm.page.tab.title.consultationapproval"/></a></li>-->				
			</ul>
		</div>
		<!-- //tab -->
		<table id="tab_contents_sub_conts1" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:none">
			<colgroup>
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="20%" />
			</colgroup>
			<tr>
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<th><spring:message code='clm.page.field.contract.detail.period' /></th>
									<td colspan="2">
										<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
										<c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if>  
										<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
									<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
									<td colspan="3"><c:out value="${contractLom.secret_keepperiod}" /></td>
								</c:when>
								<c:otherwise>
									<th><spring:message code='clm.page.field.contract.detail.period' /></th>
									<td colspan="2"><c:out value="${contractLom.cntrtperiod_startday}" />
									<c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if><c:out value="${contractLom.cntrtperiod_endday}" />	
									<input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
									<input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>			
									<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
									<td colspan="3"><c:out value="${contractLom.secret_keepperiod}" /></td>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<th><spring:message code='clm.page.field.contract.detail.period' /></th>
							<td colspan="2"><c:out value="${contractLom.cntrtperiod_startday}" />
							~			    <c:out value="${contractLom.cntrtperiod_endday}" />	
							<input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
							<input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>			
							<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
							<td colspan="3"><c:out value="${contractLom.secret_keepperiod}" /></td>
						</c:otherwise>	
					</c:choose>
				</c:when>
				<c:otherwise>
					<th><spring:message code='clm.page.field.contract.detail.period' /></th>
					<td colspan="2"><c:out value="${contractLom.cntrtperiod_startday}" />
					~			    <c:out value="${contractLom.cntrtperiod_endday}" />	
					<input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
					<input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>			
					<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
					<td colspan="3"><c:out value="${contractLom.secret_keepperiod}" /></td>
				</c:otherwise>
			</c:choose>					
			</tr>
			
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
								<tr>
									<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
									<td colspan="2">
										<select name="payment_gbn" id="payment_gbn" required class="all" style="width:150px" onChange="paymentGbnChange();" >						
										</select>
									</td>
									
									<th><spring:message code='clm.page.field.contract.detail.price' /></th>
									<td colspan="3">
										<input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}'/>" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full"  />
									</td>
								</tr>
								<tr>
									<th><spring:message code='clm.page.field.contract.detail.money' /></th>
									<td colspan="2">
										<select name="crrncy_unit" id="crrncy_unit">						
										</select>
									</td>
									<th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
									<td colspan="3">
										<input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onClick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
									</td>
								</tr>
								<tr id="trCntrtUntprc" style="display:none">
									<th><spring:message code="clm.page.msg.manage.conclSingleAmt" /></th>
									<td colspan="5">
										<textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${contractLom.cntrt_untprc_expl}'/></textarea>
										<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
									</td>
								</tr>								
								</c:when>
								<c:otherwise>
								<input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
								<tr>
									<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
									<c:choose>
										<c:when test="${contractLom.payment_gbn=='C02004'}">
										<td colspan="5"><c:out value="${contractLom.payment_gbn_nm}" /></td>
										</c:when>
										<c:otherwise>
										<td><c:out value="${contractLom.payment_gbn_nm}" /></td>
										<th><spring:message code='clm.page.field.contract.detail.price' /></th>
										<td><c:out value="${contractLom.cntrt_amt}" /></td>
										<th><spring:message code='clm.page.field.contract.detail.money' /></th>
										<td><c:out value="${contractLom.crrncy_unit}" /></td>
										</c:otherwise>
									</c:choose>
								</tr>	
								<c:if test='${contractLom.cntrt_untprc_expl != "" }'>
								<tr>
									<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
									<td colspan="5">
										<c:out value="${contractLom.cntrt_untprc_expl}" />
									</td>
								</tr>
								<tr>
									<td colspan="5" class="tal_lineL">
										<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>							
									</td>
								</tr>
								</c:if>								
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
						<input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
						<tr>
							<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
							<c:choose>
								<c:when test="${contractLom.payment_gbn=='C02004'}">
								<td colspan="5"><c:out value="${contractLom.payment_gbn_nm}" /></td>
								</c:when>
								<c:otherwise>
								<td><c:out value="${contractLom.payment_gbn_nm}" /></td>
								<th><spring:message code='clm.page.field.contract.detail.price' /></th>
								<td><c:out value="${contractLom.cntrt_amt}" /></td>
								<th><spring:message code='clm.page.field.contract.detail.money' /></th>
								<td><c:out value="${contractLom.crrncy_unit}" /></td>
								</c:otherwise>
							</c:choose>
						</tr>	
						<c:if test='${contractLom.cntrt_untprc_expl != "" }'>
						<tr>
							<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
							<td colspan="5">
								<c:out value="${contractLom.cntrt_untprc_expl}" />
							</td>
						</tr>
						<tr>
							<td colspan="5" class="tal_lineL">
								<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>							
							</td>
						</tr>
						</c:if>						
						</c:otherwise>	
					</c:choose>
				</c:when>
				<c:otherwise>
				<input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
				<c:choose>
					<c:when test="${contractLom.payment_gbn=='C02004'}">
						<tr>
							<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
							<td colspan="5"><c:out value="${contractLom.payment_gbn_nm}" /></td>
						</tr>					
					</c:when>
					<c:otherwise>
						<tr>
							<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
							<td colspan="2"><c:out value="${contractLom.payment_gbn_nm}" /></td>
							<th><spring:message code='clm.page.field.contract.detail.price' /></th>
							<td colspan="3"><c:out value="${contractLom.cntrt_amt}" /></td>
						</tr>
						<tr>
							<th><spring:message code='clm.page.field.contract.detail.money' /></th>
							<td colspan="5"><c:out value="${contractLom.crrncy_unit}" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
				
				<c:if test='${contractLom.cntrt_untprc_expl != "" }'> <!-- 단가내역 설명 -->
				<tr>
					<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th> <!-- 단가내역 설명 -->
					<td colspan="5">
						<c:out value="${contractLom.cntrt_untprc_expl}" />
					</td>
				</tr>
				<tr>
					<td colspan="5" class="tal_lineL">
						<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>							
					</td>
				</tr>
				</c:if>				
				</c:otherwise>
			</c:choose>					
			
			<tr>
				<th><spring:message code='clm.page.field.contract.detail.payment' /></th> <!-- 지불/수금 조건 -->
				<td colspan="5">
				<c:choose>
					<c:when test="${command.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
								<c:choose>
									<c:when test="${command.session_user_id == command.reqman_id}">
										<textarea name="payment_cond" id="payment_cond" cols="10" rows="3" onkeyup="f_chk_byte(this,600)" class="text_area_full"><c:out value="${contractLom.payment_cond}" /></textarea>
									</c:when>
									<c:otherwise>
										<!--<c:out value='${contractLom.payment_cond}' escapeXml="false"/>-->
										<%=StringUtil.convertEnterToBR((String)contractLom.get("payment_cond")) %>
									</c:otherwise>
								</c:choose>		
							</c:when>
							<c:otherwise>
								<!--<c:out value='${contractLom.payment_cond}' escapeXml="false"/>-->
								<%=StringUtil.convertEnterToBR((String)contractLom.get("payment_cond")) %>
							</c:otherwise>
						</c:choose>	
					</c:when>
					<c:otherwise>
						<!--<c:out value='${contractLom.payment_cond}' escapeXml="false"/>-->
						<%=StringUtil.convertEnterToBR((String)contractLom.get("payment_cond")) %>
					</c:otherwise>
				</c:choose>
					
				</td>
			</tr>
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<tr>
										<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
										<td colspan="5">
											<textarea name="etc_main_cont" id="etc_main_cont" cols="10" rows="3" onkeyup="f_chk_byte(this,600)" class="text_area_full"><c:out value='${contractLom.etc_main_cont}'/></textarea>
										</td>								
									</tr>
								</c:when>
								<c:otherwise>
									<%
										if(!"".equals(StringUtil.bvl((String)contractLom.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl((String)contractLom.get("if_sys_cd"), ""))){
									%>
									<tr>
										<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
										<td colspan="5">
											<!--<c:out value='${contractLom.etc_main_cont}' escapeXml="false"/>-->
											<%=StringUtil.convertEnterToBR((String)contractLom.get("etc_main_cont")) %>
											<c:if test="${!empty contractLom.if_sys_cd}"> [<c:out value="${contractLom.if_sys_cd}" escapeXml="false" />]</c:if>
										</td>								
									</tr>
									<%
										}
									%>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<%
								if(!"".equals(StringUtil.bvl((String)contractLom.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl((String)contractLom.get("if_sys_cd"), ""))){
							%>
							<tr>
								<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
								<td colspan="5">
									<!--<c:out value='${contractLom.etc_main_cont}' escapeXml="false"/>-->
									<%=StringUtil.convertEnterToBR((String)contractLom.get("etc_main_cont")) %>
									<c:if test="${!empty contractLom.if_sys_cd}"> [<c:out value="${contractLom.if_sys_cd}" escapeXml="false" />]</c:if>
								</td>								
							</tr>
							<%
								}
							%>
						</c:otherwise>
					</c:choose>	
				</c:when>
				<c:otherwise>
					<%
						if(!"".equals(StringUtil.bvl((String)contractLom.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl((String)contractLom.get("if_sys_cd"), ""))){
					%>
					<tr>
						<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
						<td colspan="5">
							<!--<c:out value='${contractLom.etc_main_cont}' escapeXml="false"/>-->
							<%=StringUtil.convertEnterToBR((String)contractLom.get("etc_main_cont")) %>
							<c:if test="${!empty contractLom.if_sys_cd}"> [<c:out value="${contractLom.if_sys_cd}" escapeXml="false" />]</c:if>
						</td>								
					</tr>
					<%
						}
					%>
				</c:otherwise>
			</c:choose>
					 
		</table>
		<!-- 특화정보시작 -->	
		<div id="consultation-specialinfo-list"></div>
		<!-- 특화정보끝 -->
		<table id="tab_contents_sub_conts1_sub" cellspacing="0" cellpadding="0" class="table-style01 borz01" style="margin-top:0; border:0; width:100%;">
			<colgroup>
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="20%" />
			</colgroup>
			<tr>
				<%-- <th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:0px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th> --%>
				<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
				<td colspan="6">
					<c:choose>
						<c:when test="${contractLom.auto_rnew_yn=='Y'}">
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" checked disabled="disabled">Yes</input>
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" disabled="disabled">No</input>
						</c:when>
						<c:otherwise>					
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" disabled="disabled">Yes</input>
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" checked disabled="disabled">No</input>	
						</c:otherwise>
					</c:choose>
				</td>
				
			</tr>
			<c:if test='${!empty contractLom.oblgt_lmt}'>
			<tr>
				<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:0px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
			  	<td colspan="6">
			  		<!-- <input type="text" name="oblgt_lmt" id="oblgt_lmt" value="<c:out value='${contractLom.oblgt_lmt}'/>" class="text_full" style="width:150px"/>-->
			  		<c:out value='${contractLom.oblgt_lmt}'/>
		      	</td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.spcl_cond}'>
			<tr>
				<th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
				<td colspan="6">
					<c:out value='${contractLom.spcl_cond}' escapeXml="false"/> 		
				</td>
			</tr>
			</c:if>
			<tr>
				<th><spring:message code="clm.page.field.contract.detail.loac"/></th>
				<td colspan="2"><c:out value='${contractLom.loac}'/></td>
				<th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
				<td colspan="2"><c:out value='${contractLom.loac_etc}'/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
				<td colspan="2"><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
				<th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
				<td colspan="2"><c:out value='${contractLom.dspt_resolt_mthd_det}'/></td>
			</tr>
		</table>
		
		<!-- 사전검토정보 Start -->
		<div id="consultation-before-info">
			<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="30%" />
					<col width="13%" />
					<col width="16%" />
					<col width="13%" />
					<col width="16%" />  
				</colgroup>
<c:choose>
	<c:when test="${command.page_gbn=='modify'}"> <%-- 단계가 수정단계이고 --%>
		<c:choose>
			<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}"> <%--진행단계가 회신,작성중,보류일 경우에만 수정가능--%>
				<c:choose>
					<c:when test="${command.session_user_id == command.reqman_id}"> <%--등록자와 현재 접속자가 같을 경우만 수정 가능--%>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApprProp" /></th>
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${contractLom.bfhdcstn_mtnman_id}'/>" />
						<input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('mtn');return false;}" /><a href="javascript:searchEmployee('mtn');"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a>
					</td>
					<th><spring:message code="clm.page.msg.manage.level" /></th>
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>" />
					    <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/></sapn></td>
					<th><spring:message code="clm.page.msg.manage.deptName" /></th>
					<td>
	                          <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>" />
	                          <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/></span></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_email" value="<c:out value='${contractLom.bfhdcstn_mtnman_email}'/>" />
					    
					    <span id="bfhdcstn_mtn_email_span"><c:out value='${contractLom.bfhdcstn_mtnman_email}'/></span></td></td>
					<th><spring:message code="clm.page.msg.manage.apprType" /></th>
					<td colspan="3">
						<select name="bfhdcstn_apbt_mthd" id="bfhdcstn_apbt_mthd">
							<option><spring:message code="clm.page.msg.common.select" /></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApproval" /><br /><spring:message code="clm.page.msg.manage.apprPerInf" /></th>
					<td colspan="5">
					<span>※ <spring:message code="clm.page.msg.manage.announce109" /></span>
						<!-- 테이블 안에 테이블 -->
						<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub">
							<colgroup>
								<col width="115" />
								<col width="115" />
								<col width="115" />
								<col width="115" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="clm.page.msg.manage.name" /></th>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<th><spring:message code="clm.page.msg.common.department" /></th>
									<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
									    <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${contractLom.bfhdcstn_apbtman_id}'/>" />
									    <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_nm}'/>" style="width:60px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('apbt');return false;}"/><a href="javascript:searchEmployee('apbt');"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a>
									</td>
									<td>
									    <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/>" />
									    <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></span>
									</td>
									<td>
									    <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>" />
									    <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></span>
									</td>
									<td>
										<input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${contractLom.bfhdcstn_apbtday}'/>" />
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->						
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="5">
						<spring:message code="clm.page.msg.manage.announce097" />
						<!-- 2014-07-03 Sungwoo replacement height size -->
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
					</c:when>
					<c:otherwise>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApprProp" /></th>
					<td colspan="3">
					<c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
						<c:out value="${contractLom.bfhdcstn_mtnman_nm}" />/<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>/<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
					</c:if>
					</td>
					<th><spring:message code="clm.page.msg.manage.apprType" /></th>
					<td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td colspan="5"><c:out value='${contractLom.bfhdcstn_mtnman_email}'/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApproval" /><br /><spring:message code="clm.page.msg.manage.name" /></th>
					<td colspan="5">
						<!-- 테이블 안에 테이블 -->
						<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub">
							<colgroup>
								<col width="115" />
								<col width="115" />
								<col width="115" />
								<col width="115" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="clm.page.msg.manage.name" /></th>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<th><spring:message code="clm.page.msg.common.department" /></th>
									<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="5">
						<!-- 2014-07-03 Sungwoo replacement height size -->
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
					</c:otherwise>
				</c:choose>		
			</c:when>
			<c:otherwise>				
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApprProp" /></th>
					<td colspan="3">
					<c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
						<c:out value="${contractLom.bfhdcstn_mtnman_nm}" />/<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>/<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
					</c:if>
					</td>
					<th><spring:message code="clm.page.msg.manage.apprType" /></th>
					<td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td colspan="5">						    
						<c:out value='${contractLom.bfhdcstn_mtnman_email}'/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApproval" /><br /><spring:message code="clm.page.msg.manage.apprPerInf" /></th>
					<td colspan="5">
						<!-- 테이블 안에 테이블 -->
						<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub">
							<colgroup>
								<col width="115" />
								<col width="115" />
								<col width="115" />
								<col width="115" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="clm.page.msg.manage.name" /></th>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<th><spring:message code="clm.page.msg.common.department" /></th>
									<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="5">
						<!-- 2014-07-03 Sungwoo replacement height size -->
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>								
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApprProp" /></th>
					<td colspan="3">
					<c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
						<c:out value="${contractLom.bfhdcstn_mtnman_nm}" />/<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>/<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
					</c:if>
					</td>
					<th><spring:message code="clm.page.msg.manage.apprType" /></th>
					<td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td colspan="5">						    
						<c:out value='${contractLom.bfhdcstn_mtnman_email}'/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preApproval" /><br /><spring:message code="clm.page.msg.manage.apprPerInf" /></th>
					<td colspan="5">
						<!-- 테이블 안에 테이블 -->
						<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub">
							<colgroup>
								<col width="115" />
								<col width="115" />
								<col width="115" />
								<col width="115" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="clm.page.msg.manage.name" /></th>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<th><spring:message code="clm.page.msg.common.department" /></th>
									<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></td>
									<td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="5">
						<!-- 2014-07-03 Sungwoo replacement height size -->
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>								
					</td>
				</tr>
	</c:otherwise>
</c:choose>
			</table>


		</div>
		<!-- 사전검토정보 End -->
		
		<!-- 연관계약정보Start -->
		<div id="consultation-relationcontract-list"></div>
		<!-- 연관계약정보End -->
	</div>	
	</div>
	<!-- 체결품의정보시작 -->
		<div class="title_basic">
			<h5 class="ntitle05"><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts3');"/></h5>
		</div>
		<table id="tab_contents_sub_conts3" cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th>
			<td colspan="2">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<c:choose>
										<c:when test="${contractLom.seal_mthd=='C02101'}">
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.signCorp" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.sign" /></input>
										</c:when>
										<c:when test="${contractLom.seal_mthd=='C02103'}">
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" checked onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.signCorp" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.sign" /></input>
										</c:when>
										<c:otherwise>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.signCorp" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.msg.manage.sign" /></input>	
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${contractLom.seal_mthd=='C02101'}">
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
										</c:when>
										<c:when test="${contractLom.seal_mthd=='C02103'}">
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" checked disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
										</c:when>
										<c:otherwise>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>	
											<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>			
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${contractLom.seal_mthd=='C02101'}">
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
								</c:when>
								<c:when test="${contractLom.seal_mthd=='C02103'}">
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" checked disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
								</c:when>
								<c:otherwise>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>	
									<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<br/>※ <spring:message code="clm.page.msg.manage.announce066" />
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${contractLom.seal_mthd=='C02101'}">
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
						</c:when>
						<c:when test="${contractLom.seal_mthd=='C02103'}">
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" checked disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
						</c:when>
						<c:otherwise>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.msg.manage.userSign" /></input>
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.msg.manage.signCorp" /></input>	
							<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked disabled="disabled"><spring:message code="clm.page.msg.manage.sign" /></input>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>	
			</td>
			<%-- 	신성우 주석처리 2014-04-01
			<th><spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/></th>
			<td colspan="2">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="30" onClick="javascript:getBeforeDay(30);"><spring:message code="clm.page.msg.manage.30dayBef" /></input>
									&nbsp;<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="60" onClick="javascript:getBeforeDay(60);"><spring:message code="clm.page.msg.manage.60dayBef" /></input>
									&nbsp;<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="0" onClick="javascript:$('#exprt_notiday').val('');"><spring:message code="clm.page.msg.manage.sellevel" /></input>
									&nbsp;&nbsp;&nbsp;<input type="text" name="exprt_notiday" id="exprt_notiday" value="<c:out value='${contractLom.exprt_notiday}'/>"  class="text_calendar02" required alt="<spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/>" />
								</c:when>
								<c:otherwise>
									<c:out value='${contractLom.exprt_notiday}'/>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
							<c:out value='${contractLom.exprt_notiday}'/>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:out value='${contractLom.exprt_notiday}'/>
				</c:otherwise>
			</c:choose>		
			</td> --%>
		</tr>
		<c:choose>
			<c:when test="${contractLom.seal_mthd=='C02101' || contractLom.seal_mthd=='C02103'}">
			<tr id="seal-tr" style="display:block">
				<th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th>
			  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
							  		<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${contractLom.seal_ffmtman_id}'/>" /> 
							  		<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${contractLom.seal_ffmtman_nm}'/>" />
							  		<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${contractLom.seal_ffmt_dept_cd}'/>"/>
								  	<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${contractLom.seal_ffmt_dept_nm}'/>"/>
								  	<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>"/>
								  	
								  	<c:choose>
									  	<c:when test="${contractLom.seal_mthd=='C02101'}">
									  		<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
									  	</c:when>
		  								<c:otherwise>
		  									<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px;border-right:#7f9db9 1px solid" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}" disabled/><img style="display:none;" id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
		  								</c:otherwise>
	  								</c:choose>
								  								  		
							  		<span id="ffmtman">&nbsp;&nbsp;<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></span>
							  	</c:when>
							  	<c:otherwise>
							  		<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
							  	</c:otherwise>
							  </c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
			  	</c:otherwise>
			  </c:choose>		
		  	</td>
		 </tr>
		 <tr id="seal-tr1" style="display:block">
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
			  						<input type="text" name="cnclsn_plndday" id="cnclsn_plndday" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" required/>
			  					</c:when>
			  					<c:otherwise>
			  						<c:out value='${contractLom.cnclsn_plndday}'/>
			  					</c:otherwise>
			  				</c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.cnclsn_plndday}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.cnclsn_plndday}'/>
			  	</c:otherwise>
			</c:choose>  			
		  	</td>
		 </tr>
		 <tr id="sign-tr" style="display:none">
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th><!-- 서명예정자 -->
			<td colspan="5">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
							  		<input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
							  		<input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
							  		<input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
							  		<input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
							  		<input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
							  		<span id="plndman">&nbsp;&nbsp;<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
							  	</c:when>
							  	<c:otherwise>
							  		<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
							  	</c:otherwise>
							  </c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>	
			  	<c:otherwise>
			  		<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
			  	</c:otherwise>
			  </c:choose>	
			</td>
		</tr>
		<tr id="sign-tr1" style="display:none">
			<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
			  						<input type="text" name="cnclsn_plndday1" id="cnclsn_plndday1" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" />
			  					</c:when>
			  					<c:otherwise>
			  						<c:out value='${contractLom.cnclsn_plndday}'/>
			  					</c:otherwise>
			  				</c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.cnclsn_plndday}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.cnclsn_plndday}'/>
			  	</c:otherwise>
			</c:choose>  			
		  	</td>
	  	</tr>
	  	</c:when>
	  	<c:otherwise>
	  	<tr id="seal-tr" style="display:none">
			<th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
							  		<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${contractLom.seal_ffmtman_id}'/>" /> 
							  		<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${contractLom.seal_ffmtman_nm}'/>" />
							  		<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${contractLom.seal_ffmt_dept_cd}'/>"/>
								  	<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${contractLom.seal_ffmt_dept_nm}'/>"/>
								  	<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>"/>
							  		<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search"/>
							  		<span id="ffmtman">&nbsp;&nbsp;<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></span>
							  	</c:when>
							  	<c:otherwise>
							  		<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
							  	</c:otherwise>
							 </c:choose>	
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/>
			  	</c:otherwise>
			  </c:choose>		
		  	</td>
		  </tr>
		  <tr id="seal-tr1" style="display:none">
		  		<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
			  						<input type="text" name="cnclsn_plndday" id="cnclsn_plndday" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" />
			  					</c:when>
			  					<c:otherwise>
			  						<c:out value='${contractLom.cnclsn_plndday}'/>
			  					</c:otherwise>
			  				</c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.cnclsn_plndday}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.cnclsn_plndday}'/>
			  	</c:otherwise>
			  </c:choose>			
		  	</td>
		 </tr>
		 <tr id="sign-tr" style="display:block">
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th><!-- 서명자 -->
			<td colspan="5">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
							  		<input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
							  		<input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
							  		<input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
							  		<input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
							  		<input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
							  		<span id="plndman">&nbsp;&nbsp;<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
							  	</c:when>
							  	<c:otherwise>
							  		<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
							  	</c:otherwise>
							  </c:choose>		
				  		</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
			  	</c:otherwise>
			  </c:choose>			
			</td>
		</tr>
		<tr id="sign-tr1" style="display:block">
			<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
			  						<input type="text" name="cnclsn_plndday1" id="cnclsn_plndday1" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" required alt="<spring:message code='clm.page.field.contract.consultation.detail.conclusionday'/>"/>
			  					</c:when>
			  					<c:otherwise>
			  						<c:out value='${contractLom.cnclsn_plndday}'/>
			  					</c:otherwise>
			  				</c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.cnclsn_plndday}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.cnclsn_plndday}'/>
			  	</c:otherwise>
			  </c:choose>			
		  	</td>
	  	</tr>
	  	</c:otherwise>
	  	</c:choose>
		<tr>
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
		  	<td colspan="5">
		  	<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
			  		<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
			  						<input type="hidden" name="cntrt_respman_id" id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
			  						<input type="hidden" name="cntrt_respman_nm" id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
							  		<input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>"/>
							  		<input type="hidden" name="cntrt_resp_dept" id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>"/>
							  		<input type="hidden" name="cntrt_resp_dept_nm" id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>"/>
							  		<input type="text" name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value=""  style="width:110px" class="text_search"  alt="<spring:message code='clm.page.field.contract.consultation.detail.contractrespmannm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('contract');" class="cp" alt="search"/>
							  		<span id="respman">&nbsp;&nbsp;<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></span>
							  		<br><br><span><spring:message code="clm.msg.alert.contract.consultation.respmannotice"/></span>
							  	</c:when>
							  	<c:otherwise>
							  		<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/>
							  	</c:otherwise>
						  	</c:choose>		
			  			</c:when>
			  			<c:otherwise>
			  				<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:when>
			  	<c:otherwise>
			  		<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/>
			  	</c:otherwise>
			</c:choose>			
		  	</td>
		</tr>
		<c:if test="${command.user_role=='RD01'}">
			<tr>
			  	<th><spring:message code="clm.page.field.contract.consultation.detail.agreeday"/></th>
			  	<td colspan="2">
			  		<c:out value='${contractLom.agreeday}'/>
			  	</td>
			  	<th><spring:message code="clm.page.field.contract.consultation.detail.agreeyn"/></th>
			  	<td colspan="2"><c:out value='${contractLom.agree_yn}'/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.agreecause"/></th>
				<td colspan="5">
					<c:out value='${contractLom.agree_cause}'/>
				</td>
			</tr>
		</c:if>	
	</table>