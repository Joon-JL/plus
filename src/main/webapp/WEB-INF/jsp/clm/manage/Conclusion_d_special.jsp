<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- key hidden Form -->
<!-- //key hidden Form -->
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01" width="100%">
		<colgroup>
		<c:choose>
			<c:when test="${td_cnt==1}">
	  		<col width="14%" />
			<col width="34%" />
			<col width="14%" />
			<col width="38%" />
			</c:when>
			<c:when test="${td_cnt==2}">
			<col width="14%" />
			<col width="34%" />
			<col width="14%" />
			<col width="38%" />
			</c:when>
			<c:when test="${td_cnt==3}">
			<col width="14%" />
			<col width="34%" />
			<col width="14%" />
			<col width="14%" />
			<col width="12%" />
			<col width="12%" />
			</c:when>
		</c:choose>	
	  	</colgroup>
	  	<tr>
	  	<c:forEach var="list" items="${specialList}" varStatus="i">
	  		<c:choose>
	  			<c:when test = "${i.count-1 == td_cnt }">
	  	</tr>
	  	<tr>	
	  			</c:when>
	  		</c:choose>	
	  		
	  		<th style="line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border-top:0px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;"><c:out value="${list.attr_nm}"/></th>
	  		<td class="tal_lineL" <c:out value="${list.colspan_string}"/>>
	  			<input type="hidden" name="attr_seqno_arr" value="<c:out value="${list.attr_seqno}"/>" />
	  			<input type="hidden" name="attr_cd_arr" value="<c:out value="${list.attr_cd}"/>" />
	  			<input type="hidden" name="attr_worktype_arr" value="<c:out value="${list.attr_worktype}"/>" />
	  			<input type="text" name="attr_cont_arr" value="<c:out value="${list.attr_value}"/>" class="text_full" />
	  		</td>
	  </c:forEach>
	</tr>
	</table>
	  