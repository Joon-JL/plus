<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	  uri="http://java.sun.com/jstl/core"%>
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
 * 설	  명 : 기존 History 페이지를 공통으로 통합처리
 * 작  성  자 : 신성우
 * 작  성  일 : 2014.09.12
 */
--%>
<%
	ArrayList review	= (ArrayList)request.getAttribute("review");
	ArrayList approve 	= (ArrayList)request.getAttribute("approve");
	ArrayList info 		= (ArrayList)request.getAttribute("info");
	
	ArrayList resultAttachArrList = (ArrayList)request.getAttribute("resultAttachList");
	ListOrderedMap tempLom = new ListOrderedMap();
	ArrayList listInfoAttach	 		 = new ArrayList();		//사전품의
	ArrayList listHisConsultCntrtAttach	 = new ArrayList();		//의뢰계약서
	ArrayList listHisConsultAppendAttach = new ArrayList();		//의뢰별첨
	ArrayList listHisConsultOtherAttach	 = new ArrayList();		//의뢰기타
	ArrayList listHisConsultOutLookAttach= new ArrayList();		//Outlook Sungwoo added 2014-09-01
	ArrayList listHisReplyCntrtAttach	 = new ArrayList();		//회신계약서
	ArrayList listHisReplyAppendAttach	 = new ArrayList();		//회신별첨
	ArrayList listHisReplyOtherAttach	 = new ArrayList();		//회신기타 Sungwoo added 2014-09-01
	ArrayList listHisApproveAttach		 = new ArrayList();		//승인
	ArrayList listHisSignAttach			 = new ArrayList();		//체결

	if(resultAttachArrList != null && resultAttachArrList.size() > 0) {
		for(int j=0; j < resultAttachArrList.size(); j++) {
			tempLom = (ListOrderedMap)resultAttachArrList.get(j);
			if("info".equals((String)tempLom.get("filetype"))) {
				listInfoAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultCntrt".equals((String)tempLom.get("filetype"))) {		//Contract Request
				listHisConsultCntrtAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultOther".equals((String)tempLom.get("filetype"))) {
				listHisConsultOtherAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultAppend".equals((String)tempLom.get("filetype"))) {
				listHisConsultAppendAttach.add(resultAttachArrList.get(j));
			} else if("hisConsultOutlook".equals((String)tempLom.get("filetype"))) {
				listHisConsultOutLookAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyCntrt".equals((String)tempLom.get("filetype"))) {		//Contract Reply
				listHisReplyCntrtAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyAppend".equals((String)tempLom.get("filetype"))) {
				listHisReplyAppendAttach.add(resultAttachArrList.get(j));
			} else if("hisReplyOthers".equals((String)tempLom.get("filetype"))) {
				listHisReplyOtherAttach.add(resultAttachArrList.get(j));
			} else if("hisApprove".equals((String)tempLom.get("filetype"))) {		//Copy of Concluded Contract 
				listHisApproveAttach.add(resultAttachArrList.get(j));
			} else if("hisSign".equals((String)tempLom.get("filetype"))) {
				listHisSignAttach.add(resultAttachArrList.get(j));
			}
		}
	}
%>
	<!-- 이력정보 -->
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
		<!-- 테이블안에 테이블 -->
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
						<td colspan="2"><%=StringUtil.convertEnterToBR((String)lom.get("cont")) %></td>
					</tr>
					<!--  2012.03.20 검토본변경여부, 변경내역 및 사유 추가 added by hanjihoon -->			   
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
						<td colspan="2"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("plndbn_chge_cont"))) %></td>
					</tr>
				<%
					}
				}
				%>
				<tr class="slide-target02 slide-area">
					<th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
				</tr>
				<%
				if(listHisConsultCntrtAttach != null && listHisConsultCntrtAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td>
						<%
							for(int i=0; i<listHisConsultCntrtAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisConsultCntrtAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%		
								}
							}
							%>
						</td>
					</tr>
				<%
				}

				if(listHisConsultAppendAttach != null && listHisConsultAppendAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
						<td>
						<%		
							for(int i=0; i<listHisConsultAppendAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisConsultAppendAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				} 

				if(listHisConsultOtherAttach != null && listHisConsultOtherAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td>
							<%		
							for(int i=0; i<listHisConsultOtherAttach.size();i++){
								tempLom = (ListOrderedMap)listHisConsultOtherAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}
				
				if(listHisConsultOutLookAttach != null && listHisConsultOutLookAttach.size() > 0) {
				%>
	 				<!-- Outlook attachment Sungwoo replaced 2014-09-01.-->
	 				<tr class="slide-target02 slide-area">
	 					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
	 					<td>
	 						<%		
							for(int i=0; i<listHisConsultOutLookAttach.size();i++){
								tempLom = (ListOrderedMap)listHisConsultOutLookAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}
			}else if("REPLY__".equals(lom.get("cr_flag"))){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.reviewOpinion" /></th>
						<td colspan="2"><%=(String)lom.get("cont")%></td>
					</tr>
				<%
				if(!"".equals(((String)lom.get("cnsd_apbt")).trim())){// 그룹장 의견 
				%>
				<c:if test="${command.top_role ne 'ETC' }">
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.grpMasterOp" /></th>
						<td colspan="2"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("cnsd_apbt"))) %></td>
					</tr>
				</c:if>
				<%
				}
				%>
				<tr class="slide-target02 slide-area">
					<th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
				</tr>
				<%
				if(listHisReplyCntrtAttach != null && listHisReplyCntrtAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td><div style='clear:both;'></div>
							<%
							for(int i=0; i<listHisReplyCntrtAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisReplyCntrtAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
							%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
							<%
								}
							}
							%>
						</td>
					</tr>
				<%
				}
				
				if(listHisReplyAppendAttach != null && listHisReplyAppendAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
						<td>
							<%
							for(int i=0; i<listHisReplyAppendAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisReplyAppendAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
							%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
							<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}

				if(listHisReplyOtherAttach != null && listHisReplyOtherAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td>
						<%
						for(int i=0; i<listHisReplyOtherAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisReplyOtherAttach.get(i);
							if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
						%>
							<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
						<%
							}
						}
						%>
						</td>
					</tr>
				<%
				}
				}else if("RESHOLD".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th>
						<td><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("cont")))%></td>
					</tr>
				<%
				}else if("ZADMIN_REPLY".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){//ZADMIN_REPLY
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="las.page.field.contractManager.pstpnRs"/><!-- 보류사유 --></th>
						<td>
							<%=(String)lom.get("cont") %>
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

			<!-- Approval History -->
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
					<!-- 테이블안에 테이블 -->
					<tr class="Nocol" id="tr_show01" >
						<td colspan="4">
							<table class="table-style_sub02">
								<colgroup>
									<col width="13%" />
									<col width="87%" /> <!-- width size 변경 신성우 2014-04-24 -->
								</colgroup>
								<tr class="Nocol">
									<th>
										<spring:message code="clm.page.msg.manage.proposer" />
									</th>
									<td>
										<%=lom.get("bfhdcstn_mtnman_nm") %>
									</td>
								</tr>
								<%
								if(listInfoAttach != null && listInfoAttach.size() > 0) {
								%>
								<tr class="Nocol">
									<th><spring:message code="clm.page.msg.common.attachment" /></th>
									<td>
										<%
										for(int i=0; i<listInfoAttach.size(); i++){
											tempLom = (ListOrderedMap)listInfoAttach.get(i);
										%>
											<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
										<%
										}
										%>
									</td>
								</tr>
								<%
								}
								%>
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
				boolean bFirstLine  = false; //현재 Mis_id의 첫번째 라인여부
				String sMemMis_id   = "" ;  //현재 Mis_id
				int iMemAllMember   = 0 ;   //현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
				int iMemRow		 = 0 ;   //현재 iMemAllMember의 Row위치
				String sNextMis_id  = "" ;  //다음  Mis_id
				int iFileRowIndex   = 0 ;   //파일 index  
				String sApprovalName= "";   //승인자명 
				String sStatus	  = "";
				String sStatusCd		= "";
				ListOrderedMap LastApprover = null;
				//Sungwoo replaced 2014-09-16 script 호출형태에서 변경처리
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
						//다음Mis_id와  현재Mis_id가 동일하지 않으면 다른 결재권임.
						if(! sNextMis_id.equals(sMemMis_id)){
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
					<!-- 테이블안에 테이블 -->
					<tr class="Nocol" id="tr_show01">
						<td colspan="4">
							<table class="table-style_sub02">
								<colgroup>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가에 따른 width 재조정 -->
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
									<!-- Sungwoo 2014-11-19 approval opinion 추가 -->
									<td><%=lom.get("opinion")%></td>
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
									<!-- Sungwoo 2014-11-19 approval opinion 추가 -->
									<td><%=lom.get("opinion")%></td>
								</tr>
			<%
					}
					 
					//해당Mis_id의 최종 Row이면
					if(iMemRow == iMemAllMember) {
								if(listHisApproveAttach != null && listHisApproveAttach.size() > 0) {
			%>
								<tr class="slide-target02 slide-area">
									<th><span class="tal_lineL"><spring:message code="clm.page.msg.common.attachment" /></span></th>
									<!-- Sungwoo 2014-05-14 Approve 상태 변경으로 인한 colspan 변경 -->
									<td colspan="3">
										<%
										for(int i=0; i<listHisApproveAttach.size(); i++){
											tempLom = (ListOrderedMap)listHisApproveAttach.get(i);
										%>
											<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
										<%
										}
										%>
									</td>
								</tr>
								<%
								}
								%>
							</table>
						</td>
					</tr>
			<%	
					}
				}
			}
			%>
			</table>
			<!-- //Approval History -->

<script language="javascript">
$(document).ready(function(){
	$("[id^='tr_show']").hide();
	$("[id^='tr_show01']").hide();
});
 	var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
 	var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';

	//검토이력
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
	//체결이력
	$('img[alt$=show02]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show02').attr("style", "display:");
		
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show02').attr("style", "display:none");
	});

</script>