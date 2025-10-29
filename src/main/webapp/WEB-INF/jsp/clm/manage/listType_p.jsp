<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%--
/**
 * 파  일  명 : listType_p.jsp
 * 프로그램명 : 상세 검색 팝업안에서 나오는 유형 코드 목록 
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%
	ArrayList resultList = (ArrayList) request.getAttribute("resultList");

    int iResult = 0; 
    iResult = resultList.size();
    
%>

<div class="heigh_scroll">

	<table class="list_basic" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="98%" />
			<col />
		</colgroup>
		<thead>
		</thead>
		<tbody>
<%
	int beforeLev = 0;
	int lev3Cnt = 0;
    for(int i = 0; i < resultList.size(); i++){
    	
    	ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
	   	if(((BigDecimal)lom.get("level")).intValue() == 1){
	   		if(beforeLev == 2 || beforeLev == 3){
	   			lev3Cnt = 0;
%>
			</span>
			</td>
		</tr>	
<%
	   		}
%>			    
		 <tr>
			<th class="tL" style='padding-left:5px'>
			    <input name="srch_lsrch_Ltype_cd" type="checkbox" value="<%= lom.get("TYPE_CD") %>" /> <%= lom.get("CD_NM") %>
		    </th>
		</tr>
<%
			beforeLev = ((BigDecimal)lom.get("level")).intValue();
    	} else if(((BigDecimal)lom.get("level")).intValue() == 2){
    		if(beforeLev == 3){
    			lev3Cnt = 0;
%>
			</span>
			</td>
		</tr>	
<%
	   		}
%>			    
		<tr>
			<th class="sub01 tL" style="padding-left:24px;background:#fff">
			    <input name="srch_lsrch_Mtype_cd" type="checkbox" value="<%= lom.get("TYPE_CD") %>" class="pL10" /> <%= lom.get("CD_NM") %>
		    </th>
		</tr>
<%
			beforeLev = ((BigDecimal)lom.get("level")).intValue();
    	} else if(((BigDecimal)lom.get("level")).intValue() == 3){
    		beforeLev = ((BigDecimal)lom.get("level")).intValue();
    		if(lev3Cnt == 0){
%>			    
		<tr>
			<td class="tL" style="padding-left:40px;white-space:normal;">
			<span style="display:block;">
<%
    		}
%>
			    <input name="srch_lsrch_Stype_cd" type="checkbox" value="<%= lom.get("TYPE_CD") %>" class="pL10" /> <%= lom.get("CD_NM") %>
<%		
			lev3Cnt++;
    		beforeLev = ((BigDecimal)lom.get("level")).intValue();
		}
	   	
	   	if(i == resultList.size() - 1){
%>
			</span>
			</td>
		</tr>
<%
	   	}
    }
%>
		</tbody>
	</table>
</div>