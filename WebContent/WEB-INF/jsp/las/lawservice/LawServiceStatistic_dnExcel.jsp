<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
String fileName = "<spring:message code='las.page.field.lawservice.formXls'/>";

try {
	fileName = new String(fileName.getBytes("EUC-KR"), "8859_1");
} catch(Exception e) {
	
}

response.setHeader("Content-Disposition", "attachment; filename="+fileName);
response.setHeader("Content-Description", "JSP Generated Data");
response.setHeader("Content-Transfer-Encoding", "binary;"); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form>
	<c:if test="${command.srch_type == 'event' || command.srch_type == 'lawfirm'}">
	<table border="1" cellspacing="0" cellpadding="0" class="list_basic tr_nobg" style="margin-bottom:30px;" >
			<colgroup>
				<col width="150px" />
				<col width="150px" />
				<col width="150px" />
				<col width="40px" />
				<c:forEach begin="1" end="26" varStatus="status" step="1" >
					<col />
				</c:forEach>
			</colgroup>
		  	<thead>
				<tr>
					<c:if test="${command.srch_type == 'event'}">
						<th rowspan="2" ><spring:message code="las.page.field.lawservice.event"/></th>
						<th rowspan="2" >LAWFIRM</th>
					</c:if>
					<c:if test="${command.srch_type == 'lawfirm'}">
						<th rowspan="2" >LAWFIRM</th>
						<th rowspan="2" ><spring:message code="las.page.field.lawservice.event"/></th>
					</c:if>
					<th rowspan="2"><spring:message code="las.page.field.lawservice.suitTarget"/></th>
					<th rowspan="2"><spring:message code="las.page.field.lawservice.bnkNote"/></th>
						<c:forEach begin="1" end="12" varStatus="status" step="1" >
							<th colspan="2" rowspan="1" style="width:140px;"  ><c:out value="${status.count}"/><spring:message code="las.page.field.lawservice.mm"/></th>
				  		</c:forEach>
				  	<th colspan="2" rowspan="1" style="width:140px;" >총계</th>
				</tr>
				<tr>
					<c:forEach begin="1" end="13" varStatus="status" step="1" >
						<c:if test="${command.srch_money_type == ''}"><th class="tal_lineL tal_bg_cor01" style="width:70px;" ><spring:message code="las.page.field.lawservice.frCurrn"/></th><th class="tal_bg_cor01" style="width:70px;" ><spring:message code="las.page.field.lawservice.won"/></th></c:if>
						<c:if test="${command.srch_money_type == 'FWD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" ><spring:message code="las.page.field.lawservice.frCurrn"/></th></c:if>
						<c:if test="${command.srch_money_type == 'KWD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" ><spring:message code="las.page.field.lawservice.won"/></th></c:if>
						<c:if test="${command.srch_money_type == 'USD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" >USD</th></c:if>						
				  	</c:forEach>
				</tr>
		  	</thead>
		  <tbody>
		  	<c:choose>
				<c:when test="${command.forward_from == 'pop'}">
		  		 <tr>
		  			<c:if test="${command.srch_money_type == ''}">
		  			<td colspan="4"><spring:message code="las.page.field.lawservice.summary"/></td>
			  			<c:forEach var="list" items="${lom_sum_all}"  varStatus="status" >
							<td class="tR"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>	
			  		<c:if test="${command.srch_money_type == 'FWD'}">
			  		<td colspan="4"><spring:message code="las.page.field.lawservice.summary"/></td>
			  			<c:forEach var="list" items="${lom_sum_fore}"  varStatus="status" >
							<td class="tR" colspan="2"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>
			  		<c:if test="${command.srch_money_type == 'KWD'}">
			  		<td colspan="4"><spring:message code="las.page.field.lawservice.summary"/></td>
			  			<c:forEach var="list" items="${lom_sum_exp}"  varStatus="status" >
							<td class="tR" colspan="2"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>		
		  		</tr>
		  		<c:forEach var="list" items="${resultSumList}">
		  		<tr>		  		
		  			<c:if test="${command.srch_money_type == ''}">
		  				<td colspan="3"><spring:message code="las.page.field.lawservice.bnSum"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" ><c:out value='${list.for01}'/></td>
						<td class="tR" ><c:out value='${list.exp01}'/></td>
						<td class="tR" ><c:out value='${list.for02}'/></td>
						<td class="tR" ><c:out value='${list.exp02}'/></td>
						<td class="tR" ><c:out value='${list.for03}'/></td>
						<td class="tR" ><c:out value='${list.exp03}'/></td>
						<td class="tR" ><c:out value='${list.for04}'/></td>
						<td class="tR" ><c:out value='${list.exp04}'/></td>
						<td class="tR" ><c:out value='${list.for05}'/></td>
						<td class="tR" ><c:out value='${list.exp05}'/></td>
						<td class="tR" ><c:out value='${list.for06}'/></td>
						<td class="tR" ><c:out value='${list.exp06}'/></td>
						<td class="tR" ><c:out value='${list.for07}'/></td>
						<td class="tR" ><c:out value='${list.exp07}'/></td>
						<td class="tR" ><c:out value='${list.for08}'/></td>
						<td class="tR" ><c:out value='${list.exp08}'/></td>
						<td class="tR" ><c:out value='${list.for09}'/></td>
						<td class="tR" ><c:out value='${list.exp09}'/></td>
						<td class="tR" ><c:out value='${list.for10}'/></td>
						<td class="tR" ><c:out value='${list.exp10}'/></td>
						<td class="tR" ><c:out value='${list.for11}'/></td>
						<td class="tR" ><c:out value='${list.exp11}'/></td>
						<td class="tR" ><c:out value='${list.for12}'/></td>
						<td class="tR" ><c:out value='${list.exp12}'/></td>
						<td class="tR" ><c:out value='${list.forsum}'/></td>
						<td class="tR" ><c:out value='${list.expsum}'/></td>
					</c:if>
				    <c:if test="${command.srch_money_type == 'FWD'}">
				    	<td colspan="3"><spring:message code="las.page.field.lawservice.bnSum"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>						
						<td class="tR" colspan="2" ><c:out value='${list.for01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.forsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'KWD'}">
						<td colspan="3"><spring:message code="las.page.field.lawservice.bnSum"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>
		  				<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'USD'}">
						<td colspan="4"> <spring:message code="las.page.field.lawservice.summary"/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
		  			
		  		
		  		</tr>
		  		</c:forEach>		  
				<c:forEach var="list" items="${resultList}">
				<tr>
					<c:if test="${command.srch_type == 'event'}">
					    <td><c:out value='${list.event_nm}'/></td>
					    <td><c:out value='${list.lawfirm_nm}'/></td>
					    <td><c:out value='${list.lawsuit_trgt_cd}'/></td>

				    </c:if>
				    <c:if test="${command.srch_type == 'lawfirm'}">
					    <td><c:out value='${list.lawfirm_nm}'/></td>
					    <td><c:out value='${list.event_nm}'/></td>
					    <td><c:out value='${list.lawsuit_trgt_cd}'/></td>

				    </c:if>
				    <c:if test="${command.srch_money_type == ''}">
				    	<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" ><c:out value='${list.for01}'/></td>
						<td class="tR" ><c:out value='${list.exp01}'/></td>
						<td class="tR" ><c:out value='${list.for02}'/></td>
						<td class="tR" ><c:out value='${list.exp02}'/></td>
						<td class="tR" ><c:out value='${list.for03}'/></td>
						<td class="tR" ><c:out value='${list.exp03}'/></td>
						<td class="tR" ><c:out value='${list.for04}'/></td>
						<td class="tR" ><c:out value='${list.exp04}'/></td>
						<td class="tR" ><c:out value='${list.for05}'/></td>
						<td class="tR" ><c:out value='${list.exp05}'/></td>
						<td class="tR" ><c:out value='${list.for06}'/></td>
						<td class="tR" ><c:out value='${list.exp06}'/></td>
						<td class="tR" ><c:out value='${list.for07}'/></td>
						<td class="tR" ><c:out value='${list.exp07}'/></td>
						<td class="tR" ><c:out value='${list.for08}'/></td>
						<td class="tR" ><c:out value='${list.exp08}'/></td>
						<td class="tR" ><c:out value='${list.for09}'/></td>
						<td class="tR" ><c:out value='${list.exp09}'/></td>
						<td class="tR" ><c:out value='${list.for10}'/></td>
						<td class="tR" ><c:out value='${list.exp10}'/></td>
						<td class="tR" ><c:out value='${list.for11}'/></td>
						<td class="tR" ><c:out value='${list.exp11}'/></td>
						<td class="tR" ><c:out value='${list.for12}'/></td>
						<td class="tR" ><c:out value='${list.exp12}'/></td>
						<td class="tR" ><c:out value='${list.forsum}'/></td>
						<td class="tR" ><c:out value='${list.expsum}'/></td>						
					</c:if>
				    <c:if test="${command.srch_money_type == 'FWD'}">
				    	<td><c:out value='${list.crrncy_unit}'/></td>						
						<td class="tR" colspan="2" ><c:out value='${list.for01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.forsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'KWD'}">
						<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'USD'}">
						<td>USD</td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>

				</tr>
				</c:forEach>
			
				</c:when>
				    <c:otherwise>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="30" align="center"><spring:message code="las.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>		
	</c:if>
	
	<c:if test="${command.srch_type == 'DOJ'}">
	<table id="tb_list_DOJ" border="1" cellspacing="0" cellpadding="0" class="list_basic tr_nobg">
		  <colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="7%" />
				<col width="6%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="7%" />
			</colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.eventNm"/></th>
			  <th><spring:message code="las.page.field.lawservice.lfNm"/></th>
			  <th><spring:message code="las.page.field.lawservice.recpDate"/></th>
			  <th><spring:message code="las.page.field.lawservice.bnkNote"/></th>
			  <th><spring:message code="las.page.field.lawservice.notPayAmt"/></th>
			  <th><spring:message code="las.page.field.lawservice.ReqAmt"/></th>
			  <th>FEES</th>
			  <th>EXP.</th>
			  <th><spring:message code="las.page.field.lawservice.rmtcDol"/></th>
			  <th><spring:message code="las.page.field.lawservice.rmtcWon"/></th>
			  <th><spring:message code="las.page.field.lawservice.rmtcDate"/></th>
			</tr>
		  </thead>
		  <tbody>		  
		  	  <c:if test="${!empty resultList_DOJSum_all}">
			  	<tr>
			          <td class="tC" colspan="4" ><spring:message code="las.page.field.lawservice.totalSum"/></td>
			          <c:forEach var="list" items="${resultList_DOJSum_all}" varStatus="st">
				          <td class="tC"><c:out value='${list}'/></td>
			          </c:forEach>
			          <td class="tC">&nbsp;</td>					            					            
			  	</tr>
			  </c:if>
			  
			  <c:if test="${!empty resultList_DOJSum}">
			  	<c:forEach var="list" items="${resultList_DOJSum}" varStatus="st">
			  	<tr>
			          <td class="tC" colspan="3" ><spring:message code="las.page.field.lawservice.bnTotal"/></td>
			          <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
			          <td class="tC"><c:out value='${list.pre_for_amount}'/></td>
			          <td class="tC"><c:out value='${list.totamt}'/></td>
			          <td class="tC"><c:out value='${list.srvc_amt}'/></td>
			          <td class="tC"><c:out value='${list.addtnl_amt}'/></td>
			          <td class="tC"><c:out value='${list.exp_amount_exp}'/></td>
			          <td class="tC"><c:out value='${list.exp_amount}'/></td>
			          <td class="tC">&nbsp;</td>					            					            
			  	</tr>
			  	</c:forEach>
			  </c:if>
			  <c:if test="${!empty resultList_DOJ}">
			  	<c:forEach var="list" items="${resultList_DOJ}">
			  	<tr>
			          <td class="tC"><c:out value='${list.event_nm}'/></td>
			          <td class="tC"><c:out value='${list.lawfirm_nm}'/></td>
			          <td class="tC"><c:out value='${list.acptday}'/></td>
			          <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
			          <td class="tC"><c:out value='${list.pre_for_amount}'/></td>
			          <td class="tC"><c:out value='${list.totamt}'/></td>
			          <td class="tC"><c:out value='${list.srvc_amt}'/></td>
			          <td class="tC"><c:out value='${list.addtnl_amt}'/></td>
			          <td class="tC"><c:out value='${list.exp_amount_exp}'/></td>
			          <td class="tC"><c:out value='${list.tot_remit_amt}'/></td>
			          <td class="tC"><c:out value='${list.remitday}'/></td>					            					            
			  	</tr>
			  	</c:forEach>
			  </c:if>
			  <c:if test="${empty resultList_DOJ && empty resultList_DOJSum}">
			  	<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			  		<td colspan="11" align="center"><spring:message code="las.msg.succ.noResult" /></td>
			  	</tr>
			  </c:if>			  
		  </tbody>
	 </table>
	 </c:if>
</form>
</body>
</html>
