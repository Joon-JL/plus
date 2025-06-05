<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Consideration_master.jsp
 * 프로그램명 : 검토의뢰   작성
 * 설      명 : 
 * 작  성  자 : chahyunjin
 * 작  성  일 : 2011.08
 */
--%>
<% 
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");	
	ArrayList listRc = (ArrayList)request.getAttribute("listRc");
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
%>	
<!-- page name: Consideration_inner_d.jsp -->
<script type="text/javascript">
<!--
	$(document).ready(function(){
		// 화면로드시 금액 100,000 형태로 변환
		var frm = document.frm;
		var amt = frm.cntrt_amt.value;
		//alert("amt = " +amt);
		//alert("cAmt = " +Comma(amt));
		frm.cntrt_amt.value = Comma(amt);
		
		//첨부파일 설정 
		initPage();
		
		//연관계약 표시 하기
		listRelationContract();	
	    
	    if(<%=StringUtil.isNull((String)lomRq.get("cntrt_untprc_expl"))%> == false) {	
	    	frm.target          = "fileList3";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120211";
		    frm.ref_key.value = $('#master_cntrt_id').val();
		    frm.view_gbn.value = "download";
		    frm.fileInfoName.value = "fileInfos3";
		    frm.fileFrameName.value = "fileList3";
		  //frm.submit();
		    getClmsFilePageCheck('frm');
	    }
		
	    // 2012.03.28 활성화 계약건의 상태에 따라 DROP버튼 Display added by hanjihoon
	    if($('#session_user_id').val() == $('#reqman_id').val() && ($('#depth_status').val() == "C02606" || $('#depth_status').val() == "C02608")){
	    	$("#id_dropContract").show();
	    }else{
	    	$("#id_dropContract").hide();
	    }
	});
	
//-->
</script>		
		
		<!--계약기본정보 -->
		<table id="table_contractBasicInfo" cellspacing="0" cellpadding="0" class="table-style03">
		    <input type="hidden" name="master_cnslt_no" id="master_cnslt_no"  value="<c:out value='${lomRq.cnslt_no}'/>@0" />
			<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
			<input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>">
			<input type="hidden" name="session_user_id" id="session_user_id" value="<c:out value='${command.session_user_id}'/>">
			<input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${lomRq.reqman_id}'/>">
			<input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${lomRq.depth_status}'/>">
			   <colgroup>
				<col width="4%" />
				<col width="8%" />            
                <col width="8%" />                
				<col width="22%" />
				<col width="13%" />
				<col width="16%" />
				<col width="13%" />
				<col width="16%" />
				</colgroup>
				<tr>
					<th colspan="2"><spring:message code="clm.page.msg.manage.contName" /></th>
					<td colspan="6"><span class="fL"><c:out value="${lomRq.cntrt_nm}" /></span></td>
				</tr>
				<tr>
					<th colspan="2"><spring:message code="clm.page.msg.common.otherParty" /></th>
					<!--<a href="JAVA-SCRIPT:customerPop(customerCd,dodun');">XXXXX</a> -->
					<td colspan="2"><a href="javascript:customerPop('<c:out value="${lomRq.cntrt_oppnt_cd}" />','<c:out value="${lomRq.cntrt_oppnt_cd}" />');"><c:out value="${lomRq.cntrt_oppnt_nm}" /></a></td>
					<th><spring:message code="clm.page.msg.manage.announce212" /></th>
					<td><c:out value="${lomRq.cntrt_oppnt_rprsntman}" /></td>
					<th><spring:message code="clm.page.msg.manage.enterpriseType" /></th>
					<td><c:out value="${lomRq.cntrt_oppnt_type_nm}" /></td>
				</tr>
				<tr>				
					<th colspan="2"><spring:message code="clm.page.field.contract.basic.oppntnm" /></th>
					<td colspan="2"><c:out value="${lomRq.cntrt_oppnt_respman}" /></td>
					<th><spring:message code="clm.page.msg.manage.chrgPhone" /></th>
					<td><c:out value="${lomRq.cntrt_oppnt_telno}" /></td>
					<th><spring:message code="clm.page.msg.manage.chrgEmail" /></th>
					<td><c:out value="${lomRq.cntrt_oppnt_email}" /></td>
				</tr>	
				<%	String strColspan="6";
				if("Y".equals(lomRq.get("plndbn_req_yn")) && "C02608".equals(lomRq.get("depth_status"))){ //최종본의뢰여부
					strColspan="4";
				    }
				%>
				<tr class="slide-target02 slide-area">
					<th colspan="2"><spring:message code="clm.page.msg.manage.contType" /></th>					
					<td colspan="<%=strColspan%>">					
						<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
						<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
						<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
						<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" />
					</td>
					<%	if("Y".equals(lomRq.get("plndbn_req_yn")) && "C02608".equals(lomRq.get("depth_status"))){ //최종본의뢰여부%>					
					<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
					<td><c:out value="${lomRq.auto_rnew_yn}" /></td>				
					<% }%>					
				</tr>
				<tr class="slide-target02 slide-area">
					<th colspan="2"><spring:message code="clm.page.msg.manage.contItm" /></th>
					<td colspan="2">
						<c:out value="${lomRq.cntrt_trgt_nm}" />	
					</td>
					<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
					<td colspan="3"><c:out value="${lomRq.cntrt_trgt_det}" /></td>
				</tr>
				<!-- 2012.02.23 파일첨부 도움말 추가 added by hanjihoon -->
				<tr class="slide-target02 slide-area">
				<th colspan="2" rowspan="3"><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.common.attachment" /></th>
					<td class="blueD"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br></c:if><spring:message code="clm.page.msg.manage.contract" /></span></td>
					<td  colspan="5">
					<c:choose>
						<c:when test="${command.plndbn_req_yn == 'Y'}">
							<span><spring:message code="clm.page.msg.manage.announce113" /></span>
						</c:when>
						<c:otherwise>
							<span><spring:message code="clm.page.msg.manage.announce004" /></span>
						</c:otherwise>
					</c:choose>
					<iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr> 
				<tr class="slide-target02 slide-area">
					<td class="tal_lineL"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br></c:if><spring:message code="clm.page.msg.manage.attachment" /></span></td>
					<td colspan="5">
					<c:choose>
						<c:when test="${command.plndbn_req_yn == 'Y'}">
							<span><spring:message code="clm.page.msg.manage.announce050" /><br><spring:message code="clm.page.msg.manage.announce168" /></span>
						</c:when>
						<c:otherwise>
							<span><spring:message code="clm.page.msg.manage.announce051" /></span>
						</c:otherwise>
					</c:choose>
					<!-- Sungwoo 2014-07-21 scrolling changed -->
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>				
					</td>
				</tr>
				<tr class="slide-target02 slide-area">
					<td class="tal_lineL">
						<c:choose>
							<c:when test="${command.plndbn_req_yn == 'Y'}">
								<span class="blueD"><spring:message code="clm.page.msg.manage.chgMrkd" /><br><spring:message code="clm.page.msg.manage.contract" />,<br><spring:message code="clm.page.msg.manage.attach" /></span>
							</c:when>
							<c:otherwise>
								<span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span>
							</c:otherwise>
						</c:choose>
					</td>
					<td colspan="6">
					<c:choose>
						<c:when test="${command.plndbn_req_yn == 'Y'}">
							<span><spring:message code="clm.page.msg.manage.announce183" /></span>
						</c:when>
						<c:otherwise>
							<span><spring:message code="clm.page.msg.manage.announce046" /></span>
						</c:otherwise>
					</c:choose>
					<iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" width="100%" height="60px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>				
					</td>
				</tr>
<!-- 				<tr class="slide-target02 slide-area"> -->
<%-- 					<th colspan="2"><spring:message code="clm.page.msg.manage.stdCont" /></th>					 --%>
<%-- 					<td colspan="<%=strColspan%>">					 --%>
<%-- 						<iframe src="<c:url value='/clm/blank.do' />" id="fileListStd" name="fileListStd" frameborder="0" width="100%" height="5px" leftmargin="0" topmargin="0" scrolling="auto"></iframe> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				
				<%if("C02406".equals(lomRq.get("cntrt_status"))){ %>			
				<tr id="tr_dropContractOpnn" class="slide-target02 slide-area">
					<th colspan="2"><spring:message code="clm.page.msg.manage.rsnForDrop" /></th><!-- out  -->
					<td id="td_dropContractOpnn" colspan="5"><c:out escapeXml="false" value="${lomRq.cntrt_chge_demnd_cause}" />								
					</td>
				</tr>			
				<%	} %>
			</table>
		<!-- //계약기본정보 -->
		<!--bottom table -->
		<div class="tal_subBox" id="tr_down02" style="display:none;"> 		
			<!-- tab -->
			<div class="tab_box">
				 <ul class="tab_basic">
					<li id="tab_contents_sub_css1" class="on"><a href="javascript:subTitleTabMove('1')"><spring:message code="clm.page.msg.manage.contDetail" /></a></li>
					<li id="tab_contents_sub_css2"><a href="javascript:subTitleTabMove('2')"><spring:message code="clm.page.msg.manage.preRevInf" /></a></li>
					<li id="tab_contents_sub_css4"><a href="javascript:subTitleTabMove('4')"><spring:message code="clm.page.msg.manage.relContInf" /></a></li>					
					<% if("C04207".equals(lomRq.get("prgrs_status"))){		//최종회신 완료 %>
					<!-- <li id="tab_contents_sub_css3"><a href="javascript:subTitleTabMove('3')">회신</a></li>//-->
					<% }%>
				 </ul>
			</div>
			<!-- //tab -->
			<!-- 계약상세 -->
			<div id="tab_contents_sub_conts1" >
			<!-- table001-->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="12%" />
					<col width="30%" />
					<col width="14%" />
					<col width="32%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
					<td colspan="4">
						<c:out value="${lomRq.cntrtperiod_startday}" /> ~ <c:out value="${lomRq.cntrtperiod_endday}" />
					</td>
					<!-- 10.28 삭제 요청으로 주석 처리 됨
					<th><spring:message code="clm.page.msg.manage.secretPeriod" /></th>
					<td><c:out value="${lomRq.secret_keepperiod}" /></td>
					 -->
				</tr>
				<c:if test="${lomRq.payment_gbn_cd == 'C02004'}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
					<td colspan="4"><c:out value="${lomRq.payment_gbn_nm}" /><input type="hidden" id="cntrt_amt" style="border:0px" value="<c:out value='${lomRq.cntrt_amt}' />"/></td>					
				</tr>
				</c:if>
				<c:if test="${lomRq.payment_gbn_cd != 'C02004'}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
					
					<td colspan="2">
					    <c:out value="${lomRq.payment_gbn_nm}" />
					</td>
					<th><spring:message code="clm.page.field.contract.detail.price" /></th>
					<td colspan="2">
					    <input type="text" id="cntrt_amt" name="cntrt_amt" style="border:1px" width="100%" value="<c:out value='${lomRq.cntrt_amt}' />" readonly="readonly"/>
					</td>
				</tr>
				</c:if>				
				<c:if test="${lomRq.payment_gbn_cd != 'C02004'}">
			            <tr>
							<c:choose>
							<c:when test="${lomRq.cntrt_untprc_expl != ''}">
							<th><spring:message code="clm.page.msg.manage.currency" /></th>
							<td colspan="2">
								<c:out value="${lomRq.crrncy_unit}" />
							</td>
							<th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
							<td><spring:message code="clm.page.msg.manage.conclSingleAmt" />					    
							</td>
							</c:when>
							<c:otherwise>
							<th><spring:message code="clm.page.msg.manage.currency" /></th>
							<td colspan="4">
								<c:out value="${lomRq.crrncy_unit}" />
							</td>
							</c:otherwise>
							</c:choose>							
						</tr>
			   </c:if>
			   <c:if test='${lomRq.cntrt_untprc_expl != "" }'>
						<tr>
							<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
							<td colspan="4">
								
								<c:out value="${lomRq.cntrt_untprc_expl}" escapeXml="false"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="tal_lineL">
								<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>							
							</td>
						</tr>
			   </c:if>
			    <c:if test="${!empty lomRq.pshdbkgrnd_purps}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.bg" /></th>
					<td colspan="4" >					
							<c:out escapeXml="false" value="${lomRq.pshdbkgrnd_purps}" />														
				    </td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.antcptnefct}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.hope" /></th>
					<td colspan="4"><c:out escapeXml="false" value="${lomRq.antcptnefct}" /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.payment_cond}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.sendRcvCond" /></th>
					<td colspan="4"><c:out escapeXml="false" value="${lomRq.payment_cond}" /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.etc_main_cont || !empty lomRq.if_sys_cd}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.etcMain" /></th><!-- ETC_MAIN_CONT -->
					<td colspan="4">
						<c:out escapeXml="false" value="${lomRq.etc_main_cont}" />
						<c:if test="${!empty lomRq.if_sys_cd}"> [<c:out value="${lomRq.if_sys_cd}" escapeXml="false" />]</c:if>
					</td>
				</tr>
				</c:if>
				<!-- 특화속성 정보 표시  -->
				<%
					for(int j =0; j<listTs.size(); j++){
						Map lom = (Map)listTs.get(j);
						if(!"".equals(StringUtil.bvl(lom.get("attr_value"), ""))){
				%>
				<tr>
				<th><span><%=(String)lom.get("attr_nm") %></span></th>
					<td valign="top" colspan="6"><%=(String)lom.get("attr_value") %></td>
				</tr>			
				<%		}
					}
				%>
				
				<!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->				
				<c:if test='${lomRq.cnsd_status == "C04305" && lomRq.plndbn_req_yn == "Y"}' >
				<c:if test="${!empty lomRq.oblgt_lmt}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.limitResp" /></th>
					<td colspan="4"><c:out escapeXml="false" value="${lomRq.oblgt_lmt}" /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.spcl_cond}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
					<td colspan="4"><c:out escapeXml="false" value="${lomRq.spcl_cond}" /></td>
				</tr>
				</c:if>
				<tr>
					<th><spring:message code="clm.page.msg.manage.properLaw" /></th>
					<td colspan="2"><c:out value="${lomRq.loac_nm}" /></td>
					<th><spring:message code="clm.page.msg.manage.properLawDtl" /></th>
					<td><c:out escapeXml="false" value="${lomRq.loac_etc}" /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.method" /></th>
					<td colspan="2"><c:out value="${lomRq.dspt_resolt_mthd_nm}" /></td>
					<th><spring:message code="clm.page.msg.manage.methodDetail" /></th>
					<td><c:out escapeXml="false" value="${lomRq.dspt_resolt_mthd_det}" /></td>
				</tr>
				</c:if>
				<!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->
			</table>
			<!-- //table001 -->			
			</div>			
			<!-- //계약상세 -->			
			<!-- 사전검토정보 -->
			<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:none;">
					<colgroup>
						<col width="12%" />
						<col width="30%" />
						<col width="13%" />
						<col width="16%" />
						<col width="13%" />
						<col width="16%" />  
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.preApprProp" /></th>
						<td colspan="3">
						<c:if test="${lomRq.bfhdcstn_mtnman_nm != ''}">
						    <c:out value="${lomRq.bfhdcstn_mtnman_nm}" />/<c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/>/<c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/>
						</c:if>
						</td>
						<th><spring:message code="clm.page.msg.manage.apprType" /></th>
						<td><c:out value="${lomRq.bfhdcstn_apbt_mthd_nm}" /></td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td colspan="5">						    
						    <c:out value='${lomRq.bfhdcstn_mtnman_email}'/></td>
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
										<td>
										    <c:out value='${lomRq.bfhdcstn_apbtman_nm}'/></td>
										<td>
										    <c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/></td>
										<td>
										    <c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/></td>
										<td>
										    <c:out value='${lomRq.bfhdcstn_apbtday}'/></td>
									</tr>
								</tbody>
							</table>
							<!-- //테이블 안에 테이블 -->
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.attachData" /></th>
						<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>								
						</td>
					</tr>
				</table>			
			<!-- //사전검토정보 -->
			<!-- 연관계약정보 -->				
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="tab_contents_sub_conts4" style="display:none">
				<colgroup>
					<col width="12%" />
					<col width="50%" />
					<col width="10%" />
					<col/>					
				</colgroup>
				<tr id="trRelationContract">
					<th>RelationType</th>
					<th><spring:message code="clm.page.msg.manage.relCont" /></th>
					<th><spring:message code="clm.page.msg.manage.define" /></th>
					<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
				</tr>				
			</table>			
			<!-- //연관계약정보 표시-->				
			<!-- 회신-->
							
			<!-- //회신 -->	
		</div>
	