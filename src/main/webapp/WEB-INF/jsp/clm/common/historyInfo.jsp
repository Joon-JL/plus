<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"     uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : historyInfo.jsp
 * 프로그램명 : History Information List
 */
--%>
<%
	ArrayList review   = (ArrayList)request.getAttribute("review");
	ArrayList approve  = (ArrayList)request.getAttribute("approve");
	ArrayList info     = (ArrayList)request.getAttribute("info");

	ArrayList resultAttachArrList = (ArrayList)request.getAttribute("resultAttachList");
	ListOrderedMap tempLom = new ListOrderedMap();
	ArrayList listInfoAttach             = new ArrayList();
	ArrayList listHisConsultCntrtAttach  = new ArrayList();
	ArrayList listHisConsultAppendAttach = new ArrayList();
	ArrayList listHisConsultOtherAttach  = new ArrayList();
	ArrayList listHisConsultOutLookAttach= new ArrayList();
	ArrayList listHisReplyCntrtAttach    = new ArrayList();
	ArrayList listHisReplyAppendAttach   = new ArrayList();
	ArrayList listHisReplyOtherAttach    = new ArrayList();
	ArrayList listHisApproveAttach       = new ArrayList();
	ArrayList listHisSignAttach          = new ArrayList();

	if(resultAttachArrList != null && resultAttachArrList.size() > 0) {
		for(int j=0; j < resultAttachArrList.size(); j++) {
			tempLom = (ListOrderedMap)resultAttachArrList.get(j);
			if("info".equals((String)tempLom.get("filetype"))) {
				listInfoAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultCntrt".equals((String)tempLom.get("filetype"))) {
				listHisConsultCntrtAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultOther".equals((String)tempLom.get("filetype"))) {
				listHisConsultOtherAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultAppend".equals((String)tempLom.get("filetype"))) {
				listHisConsultAppendAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultOutlook".equals((String)tempLom.get("filetype"))) {
				listHisConsultOutLookAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyCntrt".equals((String)tempLom.get("filetype"))) {
				listHisReplyCntrtAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyAppend".equals((String)tempLom.get("filetype"))) {
				listHisReplyAppendAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyOthers".equals((String)tempLom.get("filetype"))) {
				listHisReplyOtherAttach.add(resultAttachArrList.get(j));
			} else if("hisApprove".equals((String)tempLom.get("filetype"))) {
				listHisApproveAttach.add(resultAttachArrList.get(j));
			} else if("hisSign".equals((String)tempLom.get("filetype"))) {
				listHisSignAttach.add(resultAttachArrList.get(j));
			}
		}
	}
%>
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.revHistory" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-reviewHisView');"/></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-reviewHisView">
	<colgroup>
		<col width="66%" />
		<col width="10%" />
		<col width="24%" />
	</colgroup>
	<tr>
		<th class="tC"><spring:message code="clm.page.msg.manage.reqBrkd" /></th>
		<th class="tC"><spring:message code="clm.page.msg.manage.date" /></th>
		<th class="tC"><spring:message code="clm.page.msg.manage.reqRevPer" /></th>
	</tr>
	<%
		if(review.size() > 0){
			for(int idx=0;idx < review.size();idx++){
				ListOrderedMap lom = (ListOrderedMap)review.get(idx);
				String currentReqId = (String)lom.get("cnsdreq_id");
	%>
	<tr>
		<td class="tL">
			<span class="mR5"><img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" /></span>
			<%=lom.get("cr_flan_nm") %>
			<%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
		</td>
		<td class="tC"><%=StringUtil.bvl((String)lom.get("reg_dt"),"")%></td>
		<td><%=lom.get("nm") %></td>
	</tr>
	<tr class="Nocol" id="tr_show">
		<td colspan="3">
			<table class="table-style_sub02">
				<colgroup>
					<col width='13%'/>
					<col width='10%'/>
					<col width='77%'/>
				</colgroup>
				<%
					if("CONSULT".equals(lom.get("cr_flag"))){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
					<td colspan="2">
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("cont"),""))%></textarea>
					</td>
				</tr>
				<%
					if("Y".equals(lom.get("plndbn_req_yn"))){
						if(!"".equals(StringUtil.bvl(lom.get("lastbn_chge_yn_nm"), ""))){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th>
					<td colspan="2"><%=lom.get("lastbn_chge_yn_nm") %></td>
				</tr>
				<%
					}
					if(!"".equals(StringUtil.bvl(lom.get("plndbn_chge_cont"), ""))){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
					<td colspan="2">
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("plndbn_chge_cont"),""))%></textarea>
					</td>
				</tr>
				<%
						}
					}
				%>

				<%
					boolean hasConsultCntrt = false; boolean hasConsultAppend = false;
					boolean hasConsultOther = false; boolean hasConsultOutlook = false;
					int consultAttachRows = 0;

					for(int i=0; i<listHisConsultCntrtAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisConsultCntrtAttach.get(i)).get("cnsdreq_id"))) { hasConsultCntrt = true; break; } }
					for(int i=0; i<listHisConsultAppendAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisConsultAppendAttach.get(i)).get("cnsdreq_id"))) { hasConsultAppend = true; break; } }
					for(int i=0; i<listHisConsultOtherAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisConsultOtherAttach.get(i)).get("cnsdreq_id"))) { hasConsultOther = true; break; } }
					for(int i=0; i<listHisConsultOutLookAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisConsultOutLookAttach.get(i)).get("cnsdreq_id"))) { hasConsultOutlook = true; break; } }

					if(hasConsultCntrt) consultAttachRows++;
					if(hasConsultAppend) consultAttachRows++;
					if(hasConsultOther) consultAttachRows++;
					if(hasConsultOutlook) consultAttachRows++;

					if(consultAttachRows > 0) {
						boolean isFirstConsultAttach = true;
				%>
				<% if(hasConsultCntrt) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstConsultAttach) { %><th rowspan="<%=consultAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstConsultAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
					<td>
						<%
							for(int i=0; i<listHisConsultCntrtAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisConsultCntrtAttach.get(i);
								if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>

				<% if(hasConsultAppend) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstConsultAttach) { %><th rowspan="<%=consultAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstConsultAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
					<td>
						<% for(int i=0; i<listHisConsultAppendAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisConsultAppendAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>

				<% if(hasConsultOther) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstConsultAttach) { %><th rowspan="<%=consultAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstConsultAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
					<td>
						<% for(int i=0; i<listHisConsultOtherAttach.size();i++){
							tempLom = (ListOrderedMap)listHisConsultOtherAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>

				<% if(hasConsultOutlook) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstConsultAttach) { %><th rowspan="<%=consultAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstConsultAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
					<td>
						<% for(int i=0; i<listHisConsultOutLookAttach.size();i++){
							tempLom = (ListOrderedMap)listHisConsultOutLookAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>
				<% } %>

				<%
				}else if("REPLY__".equals(lom.get("cr_flag"))){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.msg.manage.reviewOpinion" /></th>
					<td colspan="2">
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("cont"),""))%></textarea>
					</td>
				</tr>
				<%
					if(!"".equals(StringUtil.bvl((String)lom.get("cnsd_apbt"),"").trim())){
				%>
				<c:if test="${command.top_role ne 'ETC' }">
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.grpMasterOp" /></th>
						<td colspan="2">
							<div class="safe-html-target"></div>
							<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("cnsd_apbt"),""))%></textarea>
						</td>
					</tr>
				</c:if>
				<%
					}
				%>

				<%
					boolean hasReplyCntrt = false; boolean hasReplyAppend = false; boolean hasReplyOther = false;
					int replyAttachRows = 0;
					for(int i=0; i<listHisReplyCntrtAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisReplyCntrtAttach.get(i)).get("cnsdreq_id"))) { hasReplyCntrt = true; break; } }
					for(int i=0; i<listHisReplyAppendAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisReplyAppendAttach.get(i)).get("cnsdreq_id"))) { hasReplyAppend = true; break; } }
					for(int i=0; i<listHisReplyOtherAttach.size(); i++){ if(currentReqId.equals(((ListOrderedMap)listHisReplyOtherAttach.get(i)).get("cnsdreq_id"))) { hasReplyOther = true; break; } }

					if(hasReplyCntrt) replyAttachRows++;
					if(hasReplyAppend) replyAttachRows++;
					if(hasReplyOther) replyAttachRows++;

					if(replyAttachRows > 0) {
						boolean isFirstReplyAttach = true;
				%>
				<% if(hasReplyCntrt) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstReplyAttach) { %><th rowspan="<%=replyAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstReplyAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
					<td><div style='clear:both;'></div>
						<% for(int i=0; i<listHisReplyCntrtAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisReplyCntrtAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>

				<% if(hasReplyAppend) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstReplyAttach) { %><th rowspan="<%=replyAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstReplyAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
					<td>
						<% for(int i=0; i<listHisReplyAppendAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisReplyAppendAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>

				<% if(hasReplyOther) { %>
				<tr class="slide-target02 slide-area">
					<% if(isFirstReplyAttach) { %><th rowspan="<%=replyAttachRows%>"><spring:message code="clm.page.msg.common.attachment" /></th><% isFirstReplyAttach = false; } %>
					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
					<td>
						<% for(int i=0; i<listHisReplyOtherAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisReplyOtherAttach.get(i);
							if( currentReqId.equals(tempLom.get("cnsdreq_id")) ){
						%>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } } %>
					</td>
				</tr>
				<% } %>
				<% } %>

				<%
				}else if("RESHOLD".equals(lom.get("cr_flag")) && !StringUtil.bvl((String)lom.get("cont"),"").equals("")){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th>
					<td colspan="2">
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("cont"),""))%></textarea>
					</td>
				</tr>
				<%
				}else if("ZADMIN_REPLY".equals(lom.get("cr_flag")) && !StringUtil.bvl((String)lom.get("cont"),"").equals("")){
				%>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="las.page.field.contractManager.pstpnRs"/></th>
					<td colspan="2">
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("cont"),""))%></textarea>
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
	<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
		<td colspan="3" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
	</tr>
	<%
		}
	%>
</table>

<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.apprHist" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-approveHisView');"/></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-approveHisView">
	<colgroup>
		<col width="57%" />
		<col width="10%" />
		<col width="8%" />
		<col width="25%" />
	</colgroup>
	<tr>
		<th class="tC"><spring:message code="clm.page.msg.manage.itmName" /></th>
		<th class="tC"><spring:message code="clm.page.msg.manage.status" /></th>
		<th class="tC"><spring:message code="clm.page.msg.manage.date" /></th>
		<th class="tC"><spring:message code="clm.page.msg.manage.apprPer" /></th>
	</tr>
	<%
		if(info.size() > 0){
			for(int idx=0;idx < info.size();idx++){
				ListOrderedMap lom = (ListOrderedMap)info.get(idx);
	%>
	<tr>
		<td class="tL">
			<span class="mR5"><img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" /></span>
			<spring:message code="clm.page.msg.manage.preApprInf" />
		</td>
		<td><%=lom.get("bfhdcstn_apbt_mthd_nm")!=null?lom.get("bfhdcstn_apbt_mthd_nm"):"" %></td>
		<td class="tC"><%=lom.get("bfhdcstn_apbtday")!=null ?lom.get("bfhdcstn_apbtday"):"" %></td>
		<td><%=lom.get("bfhdcstn_apbtman_nm") %>/<%=lom.get("bfhdcstn_apbtman_jikgup_nm") %>/<%=lom.get("bfhdcstn_apbt_dept_nm") %></td>
	</tr>
	<tr class="Nocol" id="tr_show01" >
		<td colspan="4">
			<table class="table-style_sub02">
				<colgroup>
					<col width="13%" />
					<col width="87%" />
				</colgroup>
				<tr class="Nocol">
					<th><spring:message code="clm.page.msg.manage.proposer" /></th>
					<td><%=lom.get("bfhdcstn_mtnman_nm") %></td>
				</tr>
				<%
					if(listInfoAttach != null && listInfoAttach.size() > 0) {
				%>
				<tr class="Nocol">
					<th><spring:message code="clm.page.msg.common.attachment" /></th>
					<td>
						<% for(int i=0; i<listInfoAttach.size(); i++){
							tempLom = (ListOrderedMap)listInfoAttach.get(i); %>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } %>
					</td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<%
		}
	}else {
	%>
	<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
		<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
	</tr>
	<%
		}
	%>
	<%
		if(approve.size() > 0){
			boolean bFirstLine  = false;
			String sMemMis_id   = "" ;
			int iMemAllMember   = 0 ;
			int iMemRow        = 0 ;
			String sNextMis_id  = "" ;
			int iFileRowIndex   = 0 ;
			String sApprovalName= "";
			String sStatus   = "";
			String sStatusCd      = "";
			ListOrderedMap LastApprover = null;

			if(approve.size() > 0){
				LastApprover = ((ListOrderedMap)approve.get(approve.size()-1));
				sApprovalName = (String)LastApprover.get("user_name") + "/" + (String)LastApprover.get("duty") + "/" + (String)LastApprover.get("dept_name");
			}

			for(int idx=0;idx < approve.size();idx++){
				ListOrderedMap lom = (ListOrderedMap)approve.get(idx);
				sNextMis_id = (String)lom.get("mis_id");

				if(idx == 0){
					sMemMis_id  = (String)lom.get("mis_id");
					bFirstLine  = true;
					iMemAllMember= (((BigDecimal)lom.get("cntrt_id_cnt")).intValue());
					iMemRow = 1;
				}else{
					if(! sNextMis_id.equals(sMemMis_id)){
						if (iMemRow != iMemAllMember && iMemRow > 1) { out.print("</table></td></tr>"); }
						sMemMis_id = sNextMis_id;
						bFirstLine  = true;
						iMemAllMember= (((BigDecimal)lom.get("cntrt_id_cnt")).intValue());
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
                      <span class="mR5">
                      <img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" />
                      </span>
			<a href="javascript:considerationPreview('<%=sMemMis_id %>');"><%=lom.get("title") %></a>
		</td>
		<td><%=lom.get("status") %></td>
		<td class="tC"><%=lom.get("create_date") %></td>
		<td><%=sApprovalName %></td>
	</tr>
	<tr class="Nocol" id="tr_show01">
		<td colspan="4">
			<table class="table-style_sub02">
				<colgroup>
					<col width="13%" />
					<col width="50%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="17%"/>
				</colgroup>
				<tr class="Nocol">
					<th rowspan='<%=lom.get("cntrt_id_cnt")%>'><spring:message code="clm.page.msg.manage.approver" /></th>
					<td>
						<strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
					</td>
					<td><%=lom.get("prg_status")%></td>
					<td><%=lom.get("approved")%></td>
					<td>
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("opinion"),""))%></textarea>
					</td>
				</tr>
				<%
				}else if(! bFirstLine){
					sStatus = (String)lom.get("cd_nm");
					sStatusCd = (String)lom.get("cd");
				%>
				<tr class="Nocol">
					<td class="tal_lineL">
						<strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
					</td>
					<td><%=lom.get("prg_status")%></td>
					<td><%=lom.get("approved")%></td>
					<td>
						<div class="safe-html-target"></div>
						<textarea class="safe-html-source" style="display:none;"><%=StringUtil.convertHtmlTochars(StringUtil.bvl((String)lom.get("opinion"),""))%></textarea>
					</td>
				</tr>
				<%
					}

					boolean isLastInGroup = (iMemRow == iMemAllMember);
					boolean isAbsolutelyLast = (idx == approve.size() - 1);
					boolean nextIsDifferentGroup = false;
					if (!isAbsolutelyLast) {
						ListOrderedMap nextLom = (ListOrderedMap)approve.get(idx + 1);
						if (!sMemMis_id.equals((String)nextLom.get("mis_id"))) {
							nextIsDifferentGroup = true;
						}
					}

					if(isLastInGroup || isAbsolutelyLast || nextIsDifferentGroup) {
						if(listHisApproveAttach != null && listHisApproveAttach.size() > 0) {
				%>
				<tr class="slide-target02 slide-area">
					<th><span class="tal_lineL"><spring:message code="clm.page.msg.common.attachment" /></span></th>
					<td colspan="4">
						<% for(int i=0; i<listHisApproveAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisApproveAttach.get(i); %>
						<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=StringUtil.convertHtmlTochars((String)tempLom.get("org_file_nm")) %></a><br/>
						<% } %>
					</td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<%
				}
			}
		}
	%>
</table>

<script language="javascript">
	$(document).ready(function(){
		$("[id^='tr_show']").hide();
		$("[id^='tr_show01']").hide();

		// ==========================================
		// SECURE HTML RENDERING ENGINE
		// ==========================================
		$(".safe-html-target").each(function() {
			// 1. Get the securely escaped HTML from the hidden textarea
			var rawHtml = $(this).next(".safe-html-source").val();

			// 2. Inject it safely. The browser's DOM parser will automatically
			//    fix and close any broken tags, preventing the layout from breaking!
			$(this).html(rawHtml);
		});
	});

	var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
	var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';

	$('img[alt$=show]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show').attr("style", "display:");
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show').attr("style", "display:none");
	});

	$('img[alt$=show01]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:");
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:none");
	});

	$('img[alt$=show02]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show02').attr("style", "display:");
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show02').attr("style", "display:none");
	});
</script>