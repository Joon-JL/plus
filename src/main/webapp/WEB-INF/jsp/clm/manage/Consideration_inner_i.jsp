<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="java.util.ArrayList" %>
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
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");	
	ArrayList listRc = (ArrayList)request.getAttribute("listRc");
%>
<script type="text/javascript">
<!--
	// 금액에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
	function olnyNum(obj){
		var str = obj.value;
		str = new String(str);
		var Re = /[^0-9]/g;
		str = str.replace(Re,'');
		
		// 금액 100,000 형태로 변환 추가 (2011/10/15)
		obj.value = Comma(str);
	}
	

	
	$(document).ready(function(){
		// 수정화면인 경우 화면로드시 금액 100,000 형태로 변환
		var frm = document.frm;
		var amt = frm.cntrt_amt.value;
		frm.cntrt_amt.value = Comma(amt);
		
		
		
		initCal("cntrtperiod_startday");    //계약기간
  		initCal("cntrtperiod_endday");		//계약기간
  		initCal("bfhdcstn_apbtday");		//처리일
  		
  		//연관계약 표시 하기
		listRelationContract();

  		//지불구분
  		getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C020&combo_type=&combo_del_yn=N&combo_selected=<c:out value='${lomRq.payment_gbn}'/>');				
  		//화페단위
  		//getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.crrncy_unit}'/>');
  		//2011. 10. 15 mod by 김현구. 기본값 세팅 - KRW 
  		getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.crrncy_unit}'/>');	
  		//거래상재방 업체 Type - cntrt_oppnt_type	
  		getCodeSelectByUpCd("frm", "cntrt_oppnt_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C056&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_oppnt_type}'/>');
  		//계약대상  - cntrt_trgt		
  		getCodeSelectByUpCd("frm", "cntrt_trgt", '/common/clmsCom.do?method=getComboHTML&combo_sys_cd=CLM&combo_gbn=CONTRACTTYPE&combo_up_cd=<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_trgt}'/>');		
  		//사전품의 승인 방법
  		getCodeSelectByUpCd("frm", "bfhdcstn_apbt_mthd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C028&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.bfhdcstn_apbt_mthd}'/>');
  		//연관계약 관계유형		
  		getCodeSelectByUpCd("frm", "rel_type", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_select=");
  		
  		initPage();	
  		
  		
  		if("<c:out value='${lomRq.crrncy_unit}'/>" == ""){
  			$("#cntrt_amt").val("");
			$("input[name=cntrt_amt]").attr('disabled', true);
			$("input[name=cntrt_untprc]").attr('checked', false);
			$("#crrncy_unit").attr('disabled', true);
			$("#crrncy_unit").html("<option value=><spring:message code='clm.page.msg.manage.notBe' /></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			document.getElementById("trCntrtUntprc").style.display="none";
			$("#cntrt_untprc_expl").val("");
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
  		}else{
  			
  			if($("#cntrt_untprc_expl").val()){
  				document.getElementById("trCntrtUntprc").style.display="block";
  				$("[name=cntrt_untprc]").attr('checked', true);
  			}
  			
  		}
  		
  		frmChkLen(frm.antcptnefct,1000,'curByteExpl');
	});
	
    // 계약기간 입력 당시 체크 하기
    function chkDate(){
    	var frm = document.frm;
    	var sCntrtDay = frm.cntrtperiod_startday.value;   //계약기간 시작
    	var eCntrtDay = frm.cntrtperiod_endday.value;     //계약기간 종료
    	
    	
    	if(sCntrtDay != "" && eCntrtDay != ""){
	    	if(sCntrtDay < eCntrtDay){
	    		alert("<spring:message code="clm.page.msg.manage.announce042" />");
	    	}
    	}
    }
	

//-->
</script>

        <input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>">
        <input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>">
        <!-- 계약기본 정보 -->
		<table cellspacing="0" cellpadding="0" class="table-style03">
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
				<td colspan="6"><input type="hidden" name="cntrt_nm" id="cntrt_nm" alt="<spring:message code="clm.page.msg.manage.contName" htmlEscape="true" />"  value="<c:out value='${lomRq.cntrt_nm}'/>" />
				<span id="div_cntrt_nm">
				<!-- 2012.02.22 계약을 추가할때 계약명 초기화 modified by hanjihoon -->
				<% if(lomRq.get("biz_clsfcn") != null){%>
					<%=lomRq.get("cntrt_nm") %>
				<% }else{%>
					<spring:message code="clm.page.msg.manage.announce045" />
				<% }%>				 
				</span>
				</td>
			</tr>			
			<tr>
				<th colspan="2"><spring:message code="clm.page.msg.common.otherParty" /></th><!-- REGION_GBN -->
				<td colspan="2">
					<input type="hidden" name="cntrt_oppnt_cd" id="cntrt_oppnt_cd" required alt="<spring:message code="clm.page.msg.manage.othPartyCode" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>">
					<input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${lomRq.region_gbn}'/>"><!-- javascript:customerPop('O');">< -->					
					<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" required alt="<spring:message code="clm.page.msg.manage.othPartyName" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" readonly="readonly" class="text_full" style="width:80%">				
					<%	if("1".equals(lomRq.get("total_cnt"))){ %>
					<img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="javascript:openNegoCustomer('O');" />
					<%}%></td>
				<th><spring:message code="clm.page.msg.manage.announce212" /></th>
				<td><input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" required alt="<spring:message code="clm.page.msg.manage.announce212" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_rprsntman}'/>" maxlength="60" class="text_full" style="width:80%">
				</td>
				<th><spring:message code="clm.page.msg.manage.enterpriseType" /></th>
				<td><select name="cntrt_oppnt_type" id="cntrt_oppnt_type"></select>
				</td>				
			</tr>
			<tr>
				<th colspan="2"><spring:message code="clm.page.field.contract.basic.oppntnm" /></th>
				<td colspan="2"><input type="text" name="cntrt_oppnt_respman" id="cntrt_oppnt_respman" value="<c:out value='${lomRq.cntrt_oppnt_respman}'/>" maxlength="60" class="text_full" style="width:80%"></td>
				<th><spring:message code="clm.page.msg.manage.chrgPhone" /></th>
				<td><input type="text" name="cntrt_oppnt_telno" id="cntrt_oppnt_telno" value="<c:out value='${lomRq.cntrt_oppnt_telno}'/>" maxlength="60" class="text_full" style="width:80%"></td>
				<th><spring:message code="clm.page.msg.manage.chrgEmail" /></th>
				<td><input email type="text" name="cntrt_oppnt_email" id="cntrt_oppnt_email"  value="<c:out value='${lomRq.cntrt_oppnt_email}'/>" maxlength="60" class="text_full" style="width:80%"></td>				
			</tr>
			<tr class="slide-target02 slide-area">
				<th colspan="2"><spring:message code="clm.page.msg.manage.contType" /></th>
				<td colspan="6">
					<input type="hidden" name="biz_clsfcn" id="biz_clsfcn" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
					<input type="hidden" name="depth_clsfcn" id="depth_clsfcn" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
					<input type="hidden" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
					<input type="hidden" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />				
					
					<input readOnly type="text" name="biz_clsfcn_nm" id="biz_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.biz_clsfcn_nm}'/>" class="text_full" style="width:30%" />
					<input readOnly type="text" name="depth_clsfcn_nm" id="depth_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.depth_clsfcn_nm}'/>" class="text_full" style="width:20%" />
					<input readOnly type="text" name="cnclsnpurps_bigclsfcn_nm" id="cnclsnpurps_bigclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn_nm}'/>" class="text_full" style="width:20%" />
					<input readOnly type="text" name="cnclsnpurps_midclsfcn_nm" id="cnclsnpurps_midclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn_nm}'/>" class="text_full" style="width:20%" />
					<a href="javascript:openChooseContractType();"><img src="<%=IMAGE %>/icon/ico_search_g.gif" /></a>
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th colspan="2"><spring:message code="clm.page.msg.manage.contItm" /></th>
				<td colspan="2">
					<select name="cntrt_trgt" id="cntrt_trgt" class="all" style="width:95%"></select>
				</td>
				<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
				<td colspan="3"><input type="text" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${lomRq.cntrt_trgt_det}'/>" maxlength="300" class="text_full" /><!-- 500 --></td>
			</tr>
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
				<input type="hidden" name="std_hstry_no" id="std_hstry_no" required alt="<spring:message code="clm.page.msg.manage.counselNo" htmlEscape="true" />" value="">
				<input type="hidden" name="std_cnslt_no" id="std_cnslt_no" value="">					
<!-- 			<tr> -->
<%-- 			<th colspan="2"><spring:message code="clm.page.msg.manage.stdCont" /></th> --%>
<!-- 				<td colspan="6"> -->
<%-- 					<input type="hidden" name="std_hstry_no" id="std_hstry_no" required alt="<spring:message code="clm.page.msg.manage.counselNo" htmlEscape="true" />" value=""> --%>
<!-- 					<input type="hidden" name="std_cnslt_no" id="std_cnslt_no" value="">					 -->
<%-- 					<input type="text" name="std_title" id="std_title" alt="<spring:message code="clm.page.msg.manage.stdContName" htmlEscape="true" />" value="" readonly="readonly" class="text_full" style="width:60%">				 --%>
<%-- 					<a href="javascript:openStdContract();"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a> --%>
<!-- 				</td> -->
<!-- 			</tr>			 -->
			<%if("C02406".equals(lomRq.get("cntrt_status"))){ %>			
			<tr id="dropOPnnArea" class="slide-target02 slide-area">
				<th colspan="2">Drop</th>
				<td colspan="6"><%=lomRq.get("cntrt_chge_demnd_cause") %>								
				</td>
			</tr>			
			<%	} %>
		</table>
		<!-- //계약기본 정보 -->
		<!--bottom table -->
		<div class="tal_subBox" id="tr_down02" style="display:none;">		
			<!-- tab -->
			<div class="tab_box">
				 <ul class="tab_basic">
					<li id="tab_contents_sub_css1" class="on"><a href="javascript:subTitleTabMove('1')"><spring:message code="clm.page.msg.manage.contDetail" /></a></li>
					<li id="tab_contents_sub_css2" ><a href="javascript:subTitleTabMove('2')"><spring:message code="clm.page.msg.manage.preRevInf" /></a></li>
					<li id="li_mt_rc" ><a href="javascript:subTitleTabMove('4')"><spring:message code="clm.page.msg.manage.relContInf" /></a></li>
					<!-- 의뢰 작성시 검토의견 란 표시 하지 않음 <li id="tab_contents_sub_css3" ><a href="javascript:subTitleTabMove('3')">검토의견</a></li>//-->
				 </ul>
			</div>
			<!-- //tab -->
			<!-- 계약상세 -->
			<div id="tab_contents_sub_conts1">
			<!-- 계약상세내역-->
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
						<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" required alt="<spring:message code="clm.page.msg.manage.contPerStrtDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_startday}'/>" class="text_calendar02"/> ~ 
						<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" required alt="<spring:message code="clm.page.msg.manage.contPerEndDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_endday}'/>" class="text_calendar02" />
					</td>		 
				<tr>
					<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
					<td colspan="2">
						<select name="payment_gbn" id="payment_gbn" class="all" style="width:150px" onChange="paymentGbnChange();">						
						</select>
					</td>	
					<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
					<td><input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt}' />" onclick="javascript:paymentGbnChange2();" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="19" /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.currency" /></th>
					<td colspan="2">
						<select name="crrncy_unit" id="crrncy_unit">						
						</select>
					</td>
					<th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
					<td>
						<input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onClick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
					</td>
				</tr>
				<tr id="trCntrtUntprc" style="display:none">
					<th><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
					<td colspan="4">
						<textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${lomRq.cntrt_untprc_expl}'/></textarea>
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.bg" /></th>
					<td colspan="4">										 
					<input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="<c:out value='${lomRq.pshdbkgrnd_purps}'/>" />
					<input type="hidden" name="body_mime1" id="body_mime1" value="<c:out value='${lomRq.pshdbkgrnd_purps}'/>" />
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%>								
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.hope" /></th>
					<td colspan="4">
						<span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.common.max1000byte" /><br>  
						<textarea name="antcptnefct" id="antcptnefct" alt="<spring:message code="clm.page.msg.manage.hope" htmlEscape="true" />" cols="30" rows="7" maxLength="1000" onkeyup="frmChkLenLang(this,1000,'curByteExpl','<%=langCd%>')" class="text_area_full"><c:out value='${lomRq.antcptnefct}'/></textarea>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.sendRcvCond" /></th>
					<td colspan="4"><textarea name="payment_cond" id="payment_cond" alt="<spring:message code="clm.page.msg.manage.sendRcvCond" htmlEscape="true" />" cols="10" rows="4" onkeyup="f_chk_byte(this,200)" class="text_area_full" maxLength="400"><c:out value='${lomRq.payment_cond}'/></textarea></td>
				</tr>
				
				<tr id="trSpecialAttr">
					<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
					<td colspan="4"><textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="f_chk_byte(this,300)" class="text_area_full" maxLength="800"><c:out value='${lomRq.etc_main_cont}'/></textarea></td>
				</tr>
				<!-- 특화속성 정보 표시  -->
				<%if(listTs != null && listTs.size() > 0){ 				%>					
				<%	for(int j =0; j<listTs.size(); j++){
						ListOrderedMap lom = (ListOrderedMap)listTs.get(j);
						
						String dpMndtry = "";
						if((String)lom.get("mndtry_yn") == "Y"){
							dpMndtry = "required alt='"+(String)lom.get("attr_nm")+"'";
						}
						if(j%2 == 0){									%>								
							<tr>
							<th><span><%=(String)lom.get("attr_nm") %></span></th>
				<%			if(j == listTs.size()-1){					%>
								<td valign="top" colspan="6">
				<%			}else{										%>
								<td valign="top" colspan="2">
				<%			}											%>
								<input type="hidden" name="arr_attr_seqno" id="arr_attr_seqno" value="<%=(Integer)lom.get("attr_seqno") %>">
								<input type="hidden" name="arr_attr_cd" id="arr_attr_cd" value="<%=(String)lom.get("attr_cd") %>">
								<input type="text" name="arr_attr_cont" <%=dpMndtry %> id="arr_attr_cont" value="<%=StringUtil.bvl((String)lom.get("attr_value"), "")%>" maxlength="240" class="text_full" style="margin-top:4px;" /></td>
				<%		}else if(j%2 == 1){								%>
							<th><span><%=(String)lom.get("attr_nm") %></span></th>
								<td colspan="3">
								<input type="hidden" name="arr_attr_seqno" id="arr_attr_seqno" value="<%=(Integer)lom.get("attr_seqno") %>">
								<input type="hidden" name="arr_attr_cd" id="arr_attr_cd" value="<%=(String)lom.get("attr_cd") %>">
								<input type="text" name="arr_attr_cont" <%=dpMndtry %> id="arr_attr_cont" value="<%=StringUtil.bvl((String)lom.get("attr_value"), "")%>" maxlength="240" class="text_full" style="margin-top:4px;" /></td>	
							</td>								
				<%		}												%>							
				<%	}													%>
				<%}														%>
			</table>	
			<!-- 계약상세내역 -->	
			</div>	
			<!-- //계약상세 -->		
			<!-- 사전검토정보-->
			<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:none">
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
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${lomRq.bfhdcstn_mtnman_id}'/>" />
						<input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN');return false;}" /><a href="javascript:searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN');"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a>
					</td>
					<th><spring:message code="clm.page.msg.manage.level" /></th>
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/>" />
					    <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/></sapn>
					</td>
					<th><spring:message code="clm.page.msg.manage.deptName" /></th>
					<td>
	                          <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/>" />
	                          <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/></span></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td>
					    <input type="hidden" name="bfhdcstn_mtnman_email" value="<c:out value='${lomRq.bfhdcstn_mtnman_email}'/>" />
					    
					    <span id="bfhdcstn_mtn_email_span"><c:out value='${lomRq.bfhdcstn_mtnman_email}'/></span></td></td>
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
									    <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${lomRq.bfhdcstn_apbtman_id}'/>" />
									    <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_nm}'/>" style="width:60px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT');return false;}"/><a href="javascript:searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT');"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a></td>
									<td>
									    <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/>" />
									    <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/></span></td>
									<td>
									    <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/>" />
									    <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/></span></td>
									<td>
									    <input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${lomRq.bfhdcstn_apbtday}'/>" /></td>
								</tr>
							</tbody>
						</table>
						<!-- //테이블 안에 테이블 -->						</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="5">
						<spring:message code="clm.page.msg.manage.announce097" />					
					<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>								
					</td>
				</tr>
			</table>
			<!-- 사전검토정보 -->
			<div id="table_mt_rc" style="display:none">
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="50%" />
					<col width="10%" />
					<col/>
				</colgroup>
				<tr>
					<th>RelationType</th>
					<th><spring:message code="clm.page.msg.manage.relCont" /></th>
					<th><spring:message code="clm.page.msg.manage.define" /></th>
					<th><spring:message code="clm.page.msg.manage.relDetail" /></th>					
				</tr>				
				<tr id="trRelationContract">
					 <td><select name="rel_type" id="rel_type" onChange="reltypeChange();"></select></td>
					 <td><input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id">
					 		<input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
					 <td><select name="rel_defn" id="rel_defn"></select></td>
					 <td><input type="text" name="expl" id="expl" class="text_full" style="width:60%">
					 <a href="javascript:actionRelationContract('insert','','');"><img src="<%=IMAGE %>/btn/btn_apply.gif"></a></td>
				</tr>				
			</table>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
			<tr>
				<td>
					※ <spring:message code="clm.page.msg.manage.announce205" /> <br>
					&nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br>
					&nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br>
					&nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br>
					&nbsp;4) <spring:message code="clm.page.msg.common.etc" />
  
				</td>
				</tr>
			</table>
			</div>			
			<!-- //연관계약정보 -->
			<!-- 검토의견 (회신)-->
			
			<!-- //회신 -->
		</div>		

				