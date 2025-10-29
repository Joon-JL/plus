<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
/*
화면별로 특화정보 <table> 구성이 틀려서 페이지 플래그로 구분하기위해
 PG001 : 계약체결
 PG002 : 계약등록
*/
HttpSession ss_special_page_flag = request.getSession(false);
ss_special_page_flag.setAttribute("special_page_flag","PG002");
%>

	<input type="hidden" name="prcs_depth" id="prcs_depth" value="<c:out value='${contractLom.prcs_depth}'/>"   /> 	<!-- 프로세스단계 파일 정보 -->
	<input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${contractLom.depth_status}'/>" /> 	<!-- 단계상태 -->
	<input type="hidden" name="cntrt_status" id="cntrt_status"  value="<c:out value='${contractLom.cntrt_status}'/>" /> 	<!-- 계약상태-->
	<input type="hidden" name="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>"/>
	<input type="hidden" name="chose_client" value="<c:out value='${reqAuthFormInfo}'/>" />
    <input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
	<!-- toptable-->
	<table border="0"cellspacing="0" cellpadding="0" class="table-style03">
		<colgroup>
			<col width="15%" />
			<col width="11%" />
			<col width="19%" />
			<col width="14%" />
			<col width="12%" />
			<col width="12%" />
			<col width="17%" />
		</colgroup>
		<tr>
			<th><spring:message code='clm.page.field.contract.basic.name' /></th>
			<td colspan="6"><c:out value="${contractLom.cntrt_nm}" /></td>
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
			<td class="last-td"><c:out value="${contractLom.cntrt_oppnt_rprsntman}" /></td>
			<th><spring:message code='clm.page.field.customer.contractRequired' /></th>
			<td><c:out value="${contractLom.cntrt_oppnt_type_nm}" /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
			<td colspan="2">
				<c:out value="${contractLom.cntrt_oppnt_respman}" />
			</td>			
			<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
			<td>
				<c:out value="${contractLom.cntrt_oppnt_telno}" />
			</td>
			<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
			<td>
				<c:out value="${contractLom.cntrt_oppnt_email}" />
			</td>
		</tr>
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
			<td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" /></td>
		</tr>
		<tr>
			<th><spring:message code='clm.page.field.contract.detail.object' /></th>
			<td colspan="6"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/></td>
		</tr>
		<tr>
			<th><spring:message code='clm.page.field.contract.detail.effect' /></th>
			<td colspan="6"><c:out value="${contractLom.antcptnefct}" escapeXml="false"/></td>
		</tr>
		<tr>
			<th rowspan="3"><spring:message code="clm.page.field.contract.basic.filename" /></th>
			<td><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename1' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
		<tr>
			<td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.manage.attachment_br' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr> 
		<tr>
			<td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td>
			<td colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
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
				<li><a href="javascript:subTitleTabMove('4')"><spring:message code="clm.page.field.contract.detail.relation"/></a></li>
			</ul>
		</div>
		<!-- //tab -->
		<table id="tab_contents_sub_conts1" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:none">
			<colgroup>
				<col width="14%" />
				<col width="10%" />
				<col width="14%" />
				<col width="16%" />
				<col width="14%" />
				<col width="32%" />
			</colgroup>
			<tr>
				<th><spring:message code='clm.page.field.contract.detail.period' /></th>
				<td colspan="2"><c:out value="${contractLom.cntrtperiod_startday}" />
				~			    <c:out value="${contractLom.cntrtperiod_endday}" />	
				<input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
				<input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>
				</td>			
				<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
				<td colspan="3"><c:out value="${contractLom.secret_keepperiod}" /></td>
			</tr>
			<tr>
				<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
				<td colspan="2"><c:out value="${contractLom.payment_gbn_nm}" /></td>
				<th><spring:message code='clm.page.field.contract.detail.price' /></th>
				<td colspan="3"><c:out value="${contractLom.cntrt_amt}" /></td>
			</tr>
			<c:if test="${!empty contractLom.crrncy_unit}">
			<tr>
				<th><spring:message code='clm.page.field.contract.detail.money' /></th>
				<td colspan="5"><c:out value="${contractLom.crrncy_unit}" /></td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.cntrt_untprc_expl}'>
			<tr>
				<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
				<td colspan="5">
					<c:out value="${contractLom.cntrt_untprc_expl}" />
				</td>
			</tr>
			<tr>
				<td colspan="5" class="tal_lineL">
					<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>							
				</td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.payment_cond}'>
			<tr>
				<th><spring:message code='clm.page.field.contract.detail.payment' /></th>
				<td class="tal_lineL" colspan="5"><c:out value="${contractLom.payment_cond}" /></td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.etc_main_cont|| !empty contractMstLom.if_sys_cd}'>
			<tr>
				<th><spring:message code='las.page.field.contractmanager.consideration_inner_d.etc_main_cont' /></th>
				<td class="tal_lineL" colspan="5">
					<c:out value="${contractLom.etc_main_cont}" />
					<c:if test="${!empty contractLom.if_sys_cd}"> [<c:out value="${contractLom.if_sys_cd}" escapeXml="false" />]</c:if>
				</td>
			</tr>
			</c:if>
		</table>
		<!-- 특화정보시작 -->	
		<div id="conclusion-specialinfo-list">
		</div>
		<!-- 특화정보끝 -->
		<table id="tab_contents_sub_conts1_sub" cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01" width="100%">
			<colgroup>
				<col width="14%" />
				<col width="10%" />
				<col width="14%" />
				<col width="16%" />
				<col width="14%" />
				<col width="32%" />
			</colgroup>
			<c:if test='${!empty contractLom.auto_rnew_yn}'>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th> <!-- 자동갱신여부 -->
				<td colspan="6">
					<c:choose>
						<c:when test="${contractLom.auto_rnew_yn=='Y'}">
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" checked disabled="disabled" >Yes</input>
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" disabled="disabled">No</input>
						</c:when>
						<c:otherwise>					
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" disabled="disabled">Yes</input>
					<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" checked disabled="disabled">No</input>	
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.oblgt_lmt}'>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th> <!-- 책임한도(LOL) -->
			  	<td colspan="6" class="tal_lineL">
			  		<!-- <input type="text" name="oblgt_lmt" id="oblgt_lmt" value="<c:out value='${contractLom.oblgt_lmt}'/>" class="text_full" style="width:150px"/>-->
			  		<c:out value='${contractLom.oblgt_lmt}'/>
		      	</td>
			</tr>
			</c:if>
			<c:if test='${!empty contractLom.spcl_cond}'>
			<tr>
				<th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
				<td colspan="6"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
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
		
		<!-- 연관계약정보Start -->
		<div id="conclusion-relationcontract-list">
		</div>
		<!-- 연관계약정보End -->
	</div>	
	<!-- 이행정보영역Start -->
	<div class="title_basic">
		<h5 class="ntitle05"><spring:message code="clm.page.tab.title.contractexec"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'conclusion-execinfo-list');"/></h5>
	</div>
	<!-- 이행정보영역Start -->
	<div id="conclusion-execinfo-list">
	</div>
	<!-- 이행정보영역end -->
	<!-- 체결본등록정보시작 -->
	<div class="title_basic">
		<h5 class="ntitle05"><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'conclusion_div');"/></h5>
	</div>
	<!-- 미체결사유히스토리 관리 안함  우선 주석처리 2011.11.17-->
	<!-- <div class="btn_wrap_t02" id="exec-delay-btn">
				<span class="btn"><span class="notreason"></span><a href="javascript:openDelayCause();"><spring:message code="clm.page.field.delaycause.delaycause"/></a></span>
	</div>-->
	<div id="conclusion_div">
	<table id="tab_contents_sub_conts4" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:">
			<colgroup>
				<col width="15%" />
				<col width="13%" />
				<col width="11%" />
				<col width="11%" />
				<col width="11%" />
				<col width="12%" />
				<col width="14%" />
				<col />
			</colgroup>
			<tr>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionyn"/></th><!-- 체결여부 -->
				<td>
					<input name="cntrt_cnclsn_yn_val" type="hidden" value="<c:out value='${contractLom.cntrt_cnclsn_yn}'/>"/>
					<c:choose>
					<c:when test="${contractLom.cntrt_cnclsn_yn == 'Y'}">
						<c:choose>
							<c:when test="${contractCommand.page_gbn == 'modify'}">
								<c:choose>
									<c:when test="${contractLom.depth_status == 'C02641'}"> <!-- 체결 미확인 -->
										<c:choose>
											<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}">
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" checked="checked" disabled="disabled"/>Yes
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" disabled="disabled"/>No
											</c:when>
											<c:otherwise>		
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" checked="checked"/>Yes
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" />No
											</c:otherwise>
										</c:choose>	
									</c:when>
									<c:otherwise>
											<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" checked="checked" disabled="disabled"/>Yes
											<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" disabled="disabled"/>No
									</c:otherwise>
								</c:choose>
							</c:when>	
							<c:otherwise>
								<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" checked="checked" disabled="disabled"/>Yes
								<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" disabled="disabled"/>No
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">	
								<c:choose>				
									<c:when test="${contractLom.depth_status=='C02641'}"> <!-- 체결 미확인 -->
										<c:choose>
											<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}">
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" disabled="disabled"/>Yes
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" checked="checked" disabled="disabled"/>No
											</c:when>
											<c:otherwise>
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" />Yes
												<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" checked="checked"/>No
											</c:otherwise>
										</c:choose>	
									</c:when>
									<c:otherwise>
										<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" disabled="disabled"/>Yes
										<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" checked="checked" disabled="disabled"/>No
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="Y" disabled="disabled"/>Yes
								<input name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" type="radio" class="radio" value="N" checked="checked" disabled="disabled"/>No
							</c:otherwise>
						</c:choose>		
					</c:otherwise>
					</c:choose>	
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/></th> <!-- 체결예정일 -->
				<td><c:out value='${contractLom.cnclsn_plndday}'/></td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday"/></th> <!-- 계약체결일 -->
				<td colspan="3">
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}">
										<c:out value='${contractLom.cntrt_cnclsnday}'/>
									</c:when>
									<c:otherwise>		
										<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="<c:out value='${contractLom.cntrt_cnclsnday}'/>"  class="text_calendar02" required alt="<spring:message code="clm.page.field.contract.conclusion.detail.conclusionday"/>" />
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.cntrt_cnclsnday}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.cntrt_cnclsnday}'/>
					</c:otherwise>
				</c:choose>
				※ <spring:message code="clm.page.msg.manage.signCompl" />	
				</td>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th> <!-- 직인/서명 구분 -->
				<td colspan="3">
				<c:choose>
					<c:when test="${contractLom.seal_mthd=='C02101'}"> <!-- 인감날인 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02101" checked="checked" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input> <!-- 직인 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal2"/></input> <!-- 법인인감 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input> <!-- 서명 -->
						<input type="hidden" name="seal_mthd" value="C02101"/>
					</c:when>
					<c:when test="${contractLom.seal_mthd=='C02103'}"> <!-- 서명 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input> <!-- 직인 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02103" checked="checked" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal2"/></input> <!-- 법인인감 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02102" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input> <!-- 서명 -->
						<input type="hidden" name="seal_mthd" value="C02103"/>
					</c:when>
					<c:otherwise>
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02101" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input> <!-- 직인 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02103" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.seal2"/></input> <!-- 법인인감 -->
						<input type="radio" name="seal_mthd1" id="seal_mthd1" class="radio" value="C02102" checked="checked" disabled="disabled"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input> <!-- 서명 -->
						<input type="hidden" name="seal_mthd" value="C02102"/>
					</c:otherwise>
				</c:choose>
				</td>
				<c:choose>
					<c:when test="${contractLom.seal_mthd=='C02101' || contractLom.seal_mthd=='C02103'}"> <!-- 인감날인 -->
						<th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th> <!-- 날인담당자 -->
						<c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
								<td colspan="3">
								<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${contractLom.seal_ffmtman_id}'/>" /> 
						  		<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${contractLom.seal_ffmtman_nm}'/>" />
						  		<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${contractLom.seal_ffmt_dept_cd}'/>"/>
							  	<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${contractLom.seal_ffmt_dept_nm}'/>"/>
								<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>"/>
								<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
								<span id="ffmtman">&nbsp;&nbsp;<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></span>
								</td>
							</c:when>
							<c:otherwise>
								<td colspan="3"><c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></td>	
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th> <!-- 날인담당자 -->
						<td colspan="3"><c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></td>
					</c:otherwise>
				</c:choose>		
			</tr>
			<c:if test="${contractLom.seal_mthd=='C02102'}"> <!-- 서명일 떄 -->
			<tr>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/></th> <!-- 서명자(당사) -->
				<td colspan="5">
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="signman_id" id="signman_id" value="<c:out value='${contractLom.signman_id}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
										<input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="<c:out value='${contractLom.signman_jikgup_nm}'/>" />
										<input type="hidden" name="signman_nm" id="signman_nm" value="<c:out value='${contractLom.signman_nm}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
										<input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="<c:out value='${contractLom.sign_dept_nm}'/>" />
										<input type="text" name="signman_search_nm" id="signman_search_nm" value="" style="width:102px" class="text_search" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
										<span id="signman">&nbsp;&nbsp;<c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/></span>
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/>
					</c:otherwise>
				</c:choose>				
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.signday"/></th> <!-- 서명일(당사) -->
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.signday}'/>
									</c:when>
									<c:otherwise>
										<input type="text" name="signday" id="signday" value="<c:out value='${contractLom.signday}'/>"  class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signday"/>"/>
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.signday}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.signday}'/>
					</c:otherwise>
				</c:choose>		
				</td>
			</tr>
<%-- 			</c:if> --%>
			<tr>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/></th> <!-- 서명자(상대) -->
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.oppnt_signman_nm}'/>
									</c:when>
									<c:otherwise>			
										<input type="text" name="oppnt_signman_nm" id="oppnt_signman_nm" value="<c:out value='${contractLom.oppnt_signman_nm}'/>"  required maxlength="100" class="text_full" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/>"/>
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.oppnt_signman_nm}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.oppnt_signman_nm}'/>
					</c:otherwise>
				</c:choose>			
				</td>	
				<th><spring:message code="clm.page.field.choosesealperson.jikgupnm"/></th> <!-- 직급 -->
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.oppnt_signman_jikgup}'/>
									</c:when>
									<c:otherwise>		
										<input type="text" name="oppnt_signman_jikgup" id="oppnt_signman_jikgup" value="<c:out value='${contractLom.oppnt_signman_jikgup}'/>" required class="text_full" maxlength="50" alt="<spring:message code="clm.page.field.choosesealperson.jikgupnm"/>" />
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.oppnt_signman_jikgup}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.oppnt_signman_jikgup}'/>
					</c:otherwise>
				</c:choose>	
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/></th> <!-- 부서명 -->
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.oppnt_signman_dept}'/>
									</c:when>
									<c:otherwise>		
										<input type="text" name="oppnt_signman_dept" id="oppnt_signman_dept" value="<c:out value='${contractLom.oppnt_signman_dept}'/>" required class="text_full" maxlength="50" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/>" />
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.oppnt_signman_dept}'/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.oppnt_signman_dept}'/>
					</c:otherwise>
				</c:choose>		
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/></th> <!-- 서명일(상대) -->
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.oppnt_signday}'/>
									</c:when>
									<c:otherwise>	
										<input type="text" name="oppnt_signday" id="oppnt_signday" value="<c:out value='${contractLom.oppnt_signday}'/>" required class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/>"/>
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.oppnt_signday}'/>
							</c:otherwise>	
						</c:choose>	
					</c:when>
					<c:otherwise>
						<c:out value='${contractLom.oppnt_signday}'/>
					</c:otherwise>
				</c:choose>	
				</td>
			</tr>
			</c:if>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
				<td colspan="5"><c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></td>
				<%-- 신성우 주석처리 2014-04-01
				<th><spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/></th>
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when test="${contractLom.depth_status=='C02641'}"><!-- 미등록상태일때 -->
								<c:choose>
									<c:when test="${contractLom.cntrt_respman_id != contractCommand.session_user_id}"><!-- 미등록상태일때 -->
										<c:out value='${contractLom.exprt_notiday}'/>
									</c:when>
									<c:otherwise>
									<input type="text" name="exprt_notiday" id="exprt_notiday" value="<c:out value='${contractLom.exprt_notiday}'/>" class="text_calendar02" required alt="<spring:message code='clm.page.field.contract.consultation.detail.expirenotifyday'/>"/>
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
			<tr>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregnm"/></th><!-- 사본등록자 -->
				<!-- <td colspan="5"><c:out value='${contractCommand.session_user_nm}'/>/<c:out value='${contractCommand.session_jikgup_nm}'/>/<c:out value='${contractCommand.session_dept_nm}'/></td>//-->
				<!-- 사본등록자가 로그인 세션정보로 되어있어 DB 정보로 변경함 chahyunjin 2012.02.14 -->
				<td colspan="5"><c:out value='${contractLom.cpy_regman_nm}'/>/<c:out value='${contractLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractLom.cpy_reg_dept_nm}'/></td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregday"/></th>
				<td><c:out value='${contractLom.cpy_regday}'/><input type="hidden" name="cpy_reqday" value="<c:out value='${contractLom.cpy_regday}'/>"/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.coclusioncopy"/></th>
				<td colspan="7">※ <spring:message code="clm.page.msg.manage.announce007" />
					<iframe src="<c:url value='/clm/blank.do' />" id="fileContractCopy" name="fileContractCopy" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
				</td>
			</tr>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${contractLom.depth_status=='C02641'}">
							<c:choose>
								<c:when test="${contractCommand.session_user_id == contractLom.cntrt_respman_id}">
									<tr>
										<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th>
										<td colspan="6" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
										<td><span class="btn_all_b" onclick='javascript:openChooseClient();'><span class="add"></span><a><spring:message code='clm.page.msg.manage.add' /></a></span></td>
									</tr>	
								</c:when>
								<c:otherwise>
									<c:if test='${reqAuthInfo!=""}'>
										<tr>
											<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th>
											<td colspan="7" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
										</tr>	
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test='${reqAuthInfo!=""}'>
								<tr>
									<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th>
									<td colspan="7" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
								</tr>	
							</c:if>	
						</c:otherwise>
					</c:choose>
					
				</c:when>
				<c:otherwise>
					<c:if test='${reqAuthInfo!=""}'>
						<tr>
							<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th>
							<td colspan="7" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
						</tr>	
					</c:if>	
				</c:otherwise>
			</c:choose>	
			</tr>			
		</table>
		<div id="conclusion-delayinfo-list">
		</div>
	</div>