<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- key hidden Form -->
<!-- //key hidden Form -->
<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01" id="delay-table">
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
	<tbody id="delayTbody">
	<c:choose>
		<c:when test="${command.page_gbn=='modify'}">
			<c:choose>
				<c:when test="${command.prgrs_status=='C04217' and command.depth_status=='C02641'}">
					<c:choose>
						<c:when test="${command.session_user_id == command.cntrt_respman_id}">
							<c:choose>
								<c:when test="${delaySize==0}">
									<tr>
										<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code='clm.page.field.contract.conclusion.detail.delaycause' /></th>
										<td colspan="5" class="tal_lineL">
											<input type="hidden" name="work_flag" id="work_flag" value="I"/>
											<input type="hidden" name="dlay_seqno" id="dlay_seqno" value="-1"/>
								      		<input type="hidden" name="chgebfr_cnclsn_plndday" id="chgebfr_cnclsn_plndday" value=""/>
											<textarea name="dlay_cause" id="dlay_cause" cols="10" rows="3" class="text_area_full" alt="<spring:message code="clm.page.msg.manage.rsnForNotConcl" htmlEscape="true" />"></textarea>
										</td>
										<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;">
											<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/>
										</th>
										<td class="tal_lineL">
											<input type="text" name="chgeaft_cnclsn_plndday" id="chgeaft_cnclsn_plndday" value="<c:out value='${list.chgeaft_cnclsn_plndday}'/>"  class="text_calendar02" alt="<spring:message code="clm.page.msg.manage.conclResDate" htmlEscape="true" />"/>
										</td>
									</tr>
								</c:when>
								<c:otherwise>	
									<c:forEach var="list" items="${delayList}">
										<tr>
											<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code='clm.page.field.contract.conclusion.detail.delaycause' /></th>
										   	<td colspan="5" class="tal_lineL">
										   		<input type="hidden" name="work_flag" id="work_flag" value="U"/>
									   	   		<input type="hidden" name="dlay_seqno" id="dlay_seqno" value="<c:out value='${list.dlay_seqno}'/>"/>
								      			<input type="hidden" name="chgebfr_cnclsn_plndday" id="chgebfr_cnclsn_plndday" value="<c:out value='${list.chgebfr_cnclsn_plndday}'/>"/>
												<textarea name="dlay_cause" id="dlay_cause" cols="10" rows="3" class="text_area_full" alt="<spring:message code="clm.page.msg.manage.rsnForNotConcl" htmlEscape="true" />"><c:out value="${list.dlay_cause}" escapeXml="false"/></textarea>
											</td>
											<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;">
												<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/>
											</th>
											<td class="tal_lineL">
												<input type="text" name="chgeaft_cnclsn_plndday" id="chgeaft_cnclsn_plndday" value="<c:out value='${list.chgeaft_cnclsn_plndday}'/>"  class="text_calendar02" alt="<spring:message code="clm.page.msg.manage.conclResDate" htmlEscape="true" />"/>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:forEach var="list" items="${delayList}">
							<tr>
								<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code='clm.page.field.contract.conclusion.detail.delaycause' /></th>
							   	<td colspan="5" class="tal_lineL"><c:out value="${list.dlay_cause}" escapeXml="false"/></td>
								<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;">
									<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/>
								</th>
								<td class="tal_lineL"><c:out value='${list.chgeaft_cnclsn_plndday}'/></td>
							</tr>
							</c:forEach>
						</c:otherwise>
						</c:choose>	
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${delayList}">
						<tr>
							<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code='clm.page.field.contract.conclusion.detail.delaycause' /></th>
						   	<td colspan="5" class="tal_lineL"><c:out value="${list.dlay_cause}" escapeXml="false"/></td>
							<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;">
								<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/>
							</th>
							<td class="tal_lineL"><c:out value='${list.chgeaft_cnclsn_plndday}'/></td>
						</tr>
					</c:forEach>
				</c:otherwise>	
			</c:choose>		
		</c:when>
		<c:otherwise>
			<c:forEach var="list" items="${delayList}">
						<tr>
							<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><spring:message code='clm.page.field.contract.conclusion.detail.delaycause' /></th>
						   	<td colspan="5" class="tal_lineL"><c:out value="${list.dlay_cause}" escapeXml="false"/></td>
							<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;">
								<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/>
							</th>
							<td class="tal_lineL"><c:out value='${list.chgeaft_cnclsn_plndday}'/></td>
						</tr>
					</c:forEach>
		</c:otherwise>
	</c:choose>			
	</tbody>	
</table>
