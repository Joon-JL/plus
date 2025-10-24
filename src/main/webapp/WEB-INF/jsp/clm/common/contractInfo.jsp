<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
</script>

<table class="table-style01">
    <colgroup>
        <col width="18%" />
        <col width="16%" />
        <col width="18%" />
        <col width="16%" />
        <col width="16%" />
        <col width="16%" />
    </colgroup>
    <tr>
        <th><spring:message code='clm.page.field.contract.basic.name' /></th>
        <td colspan="3"><span id="spCntrtNm"></span></td>
        <th><spring:message code="clm.page.msg.manage.contId" /></th>
        <td><span id="spCntrtID"></span></td>
    </tr>
    <tr>
        <th><spring:message code='clm.page.msg.common.otherParty' /></th>
        <td>
            <input type="hidden" name="customer_cd" id="customer_cd" value="" /><input type="hidden" name="dodun" id="dodun" value="" />
            <a href="javascript:customerPop('<c:out value="${contractLom.cntrt_oppnt_cd}" />','<c:out value="${contractLom.cntrt_oppnt_cd}" />');">
                <c:out value="${contractLom.cntrt_oppnt_nm}" escapeXml="false"/>
            </a>
        </td>
        <th><spring:message code='clm.page.field.customer.registerNo' /></th>
        <td><c:out value="${contractLom.cntrt_oppnt_rprsntman}" escapeXml="false"/></td>
        <th><spring:message code="clm.page.msg.manage.contId" /></th>
        <td><span id="spIsMandatory"></span></td>
    </tr>
    <tr>
        <th><spring:message code='clm.page.field.contract.basic.type' /></th>
        <td colspan="3">
        <c:out value='${contractLom.biz_clsfcn_nm}'/> / <c:out value='${contractLom.depth_clsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_bigclsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_midclsfcn_nm}'/>
        </td>
        <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
        <td>
            <c:if test="${contractLom.auto_rnew_yn=='Y'}">Yes</c:if>
            <c:if test="${contractLom.auto_rnew_yn!='Y'}">No</c:if>
        </td>
    </tr>
    <tr>
        <th><spring:message code="clm.page.field.contract.basic.thing" /></th>
        <td><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
        <th><spring:message code="clm.page.field.contract.basic.detail" /></th>
        <td colspan="3">
        <c:choose>
            <c:when test="${lomReq.page_mode=='M'}">
                <input type="text" class="text_full" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${contractLom.cntrt_trgt_det}' escapeXml="false"/>"/></td>
            </c:when>
            <c:otherwise>
                <c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/>
            </c:otherwise>
        </c:choose>
        </td>
    </tr>
    <tr>
        <c:choose>
            <c:when test="${lomReq.page_mode=='M'}">
                <th><spring:message code='clm.page.field.contract.detail.period' /></th>
                <td colspan="3">
                    <input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
                    <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if> 
                    <input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
                <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
                <td>
                    <select name="payment_gbn" id="payment_gbn" required class="all" style="width:150px" onChange="paymentGbnChange();" >
                        <c:out value="${combo.paymentGbn}" escapeXml="false"/>
                    </select>
                </td>
            </c:when>
            <c:otherwise>
                <th><spring:message code='clm.page.field.contract.detail.period' /></th>
                <td colspan="3"><c:out value="${contractLom.cntrtperiod_startday}" />
                <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if>
                <c:out value="${contractLom.cntrtperiod_endday}" /> 
                <input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
                <input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>
                </td>            
                <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
                <td>
                    <c:out value="${contractLom.payment_gbn_nm}" />
                    <input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
                </td>
            </c:otherwise>
        </c:choose>                 
    </tr>
    <tr>
        <c:choose>
            <c:when test="${lomReq.page_mode=='M'}">
                <th><spring:message code='clm.page.field.contract.detail.price' /></th>
                <td colspan="3">
                    <input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}'/>" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full"  />
                    <input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onclick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
                </td>
                <th><spring:message code='clm.page.field.contract.detail.money' /></th>
                <td>
                    <select name="crrncy_unit" id="crrncy_unit">
                        <c:out value="${combo.crrncyUnit }" escapeXml="false"/>
                    </select>
                </td>
            </c:when>
            <c:otherwise>
                <th><spring:message code='clm.page.field.contract.detail.price' /></th>
                <td colspan="3"><c:out value="${contractLom.cntrt_amt}" /><c:if test="${contractLom.cntrt_untprc_expl!=''}"> &nbsp;&nbsp;&nbsp;<spring:message code="clm.page.msg.manage.unitPrice" /></c:if></td>
                <th><spring:message code='clm.page.field.contract.detail.money' /></th>
                <td><c:out value="${contractLom.crrncy_unit}" /></td>
            </c:otherwise>
        </c:choose>                
    </tr>
    <c:choose>
        <c:when test="${lomReq.page_mode=='M'}">
            <tr id="trCntrtUntprc" style="display:none">
               <th><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
               <td colspan="5">
                   <textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${contractLom.cntrt_untprc_expl}'/></textarea>
                   <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>
               </td>
           </tr>
        </c:when>
        <c:otherwise>
            <c:if test='${contractLom.cntrt_untprc_expl != "" }'>
                <tr>
                    <th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
                    <td colspan="5">
                        <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lomReq.get("cntrt_untprc_expl")))%>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" class="tal_lineL">
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>                           
                    </td>
                </tr>
            </c:if> 
        </c:otherwise>
    </c:choose>
    <!-- 추진목적 및 배경 -->
    <c:choose>
        <c:when test="${lomReq.page_mode=='M'}">
            <tr>
                <th><spring:message code='clm.page.field.contract.detail.object' /></th>
                <td colspan="5">
                <input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="" />
                <input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${contractLom.pshdbkgrnd_purps}'/>" />
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <th><spring:message code='clm.page.field.contract.detail.object' /></th>
                <td colspan="5"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/>
            </tr>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${lomReq.page_mode=='M'}">
            <tr>
                <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                <td colspan="5">
                    <textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="f_chk_byte(this,300)" class="text_area_full" maxLength="800"><c:out value='${contractLom.etc_main_cont}'/></textarea>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                <td colspan="5">
                    <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)contractLom.get("etc_main_cont")))%>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
    
    <!-- 특화 정보 -->
    <c:forEach var="list" items="${considerationSpecialList}">
        <c:if test="${!empty list.attr_value}">
        <tr>    
            <th><c:out value="${list.attr_nm}"/></th>
            <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
        </tr>
        </c:if> 
    </c:forEach>
    <c:forEach var="list" items="${consultationSpecialList}">
        <c:if test="${!empty list.attr_value}">
        <tr>
            <th><c:out value="${list.attr_nm}"/></th>
            <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
        </tr>
        </c:if>
    </c:forEach>
    <!-- 특화 정보 끝 -->
    
    <!-- 배상책임한도 -->
    <c:if test='${!empty contractLom.oblgt_lmt}'>
    <tr>
        <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
        <td colspan="5">
            <c:out value='${contractLom.oblgt_lmt}' escapeXml="false"/>
        </td>
    </tr>
    </c:if>
    
    <!-- 기타 특약사항 -->
    <c:if test='${!empty contractLom.spcl_cond}'>
    <tr>
        <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
        <td colspan="5"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
    </tr>
    </c:if>
    <!-- 비밀유지기간 -->
    <tr>
        <th><spring:message code="clm.page.field.contract.detail.secret" /></th>
        <td colspan="5"><c:out value='${contractLom.secret_keepperiod}' escapeXml="false"/></td>
    </tr>
    <!-- 준거법 / 준거법상세 -->
    <tr>
        <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
        <td><c:out value='${contractLom.loac}'/></td>
        <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
        <td colspan="3"><c:out value='${contractLom.loac_etc}' escapeXml="false"/></td>
    </tr>
    <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
    <tr>
        <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
        <td><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
        <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
        <td colspan="3"><c:out value='${contractLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
    </tr>

</table>