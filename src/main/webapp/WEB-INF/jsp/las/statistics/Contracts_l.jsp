<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- This fragment is returned by the Controller when isAjax is true -->
<div style="margin-bottom: 15px; font-size: 14px; color: #475569;">
  <strong>Search Results:</strong> ${fn:length(resultList)} records found.
</div>

<table style="width: 100%; border-collapse: collapse; background: white; border: 1px solid #e2e8f0;">
  <thead>
  <tr style="background-color: #f8fafc;">
    <th style="padding: 12px; text-align: left; border-bottom: 2px solid #e2e8f0; font-size: 13px; color: #475569; font-weight: 600;">Contract ID</th>
    <th style="padding: 12px; text-align: left; border-bottom: 2px solid #e2e8f0; font-size: 13px; color: #475569; font-weight: 600;">Requester (In Charge)</th>
    <th style="padding: 12px; text-align: left; border-bottom: 2px solid #e2e8f0; font-size: 13px; color: #475569; font-weight: 600;">Request Title</th>
    <th style="padding: 12px; text-align: left; border-bottom: 2px solid #e2e8f0; font-size: 13px; color: #475569; font-weight: 600;">Request Date</th>
  </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${not empty resultList}">
      <c:forEach var="contract" items="${resultList}">
        <tr style="border-bottom: 1px solid #f1f5f9; transition: background-color 0.2s;">
          <td style="padding: 12px; font-size: 14px; color: #1e293b;">
            <a href="javascript:void(0);"
               style="color: #2563eb; text-decoration: underline; cursor: pointer;"
               onclick="detailAction('${contract.CNSDREQ_ID}', '${contract.PRCS_DEPTH}', '${contract.DEPTH_STATUS}')">
              <c:out value="${contract['Contract ID']}" />
            </a>
          </td>
          <td style="padding: 12px; font-size: 14px; color: #1e293b;">
            <c:out value="${contract['Requester (In Charge)']}" />
          </td>
          <td style="padding: 12px; font-size: 14px; color: #1e293b;">
            <c:out value="${contract['Request Title']}" />
          </td>
          <td style="padding: 12px; font-size: 14px; color: #1e293b;">
            <c:out value="${contract['Request Date']}" />
          </td>
        </tr>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <tr>
        <td colspan="4" style="text-align: center; padding: 60px; color: #94a3b8; font-style: italic; background-color: #ffffff;">
          No data found matching your current search criteria.
        </td>
      </tr>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<!-- Hidden form for navigation -->
<form name="frm" id="frm" method="post" action="">
  <input type="hidden" name="method" value="" />
  <input type="hidden" name="menu_id" value="20130319154642301_0000000355" />
  <input type="hidden" name="cnsdreq_id" value="" />
</form>

<script type="text/javascript">
  /**
   * Move to detail/management page
   */
  function detailAction(id, prcs, depth_status){
    // Use getElementById for reliable selection in AJAX fragments
    var frm = document.getElementById("frm");
    if (!frm) {
      console.error("Form 'frm' not found in the DOM.");
      return;
    }

    var myAction = "";
    var myMethod = "";

    if(prcs == "C02501"){   // Review / Final Reply
      myAction = "/clm/manage/consideration.do";
      myMethod = "detailConsiderationNew";
    } else if(prcs == "C02502"){  // Execution (Consultation)
      myAction = "/clm/manage/consultation.do";
      myMethod = "detailConsultationNew";
    } else if(prcs == "C02503"){ // Registration
      myAction = "/clm/manage/conclusion.do";
      myMethod = "detailConclusionNew";
    } else if(prcs == "C02504"){ // Implementation (Execution)
      myAction = "/clm/manage/execution.do";
      myMethod = "listContract";
    } else if(prcs == "C02505"){ // Termination (Completion)
      myAction = "/clm/manage/completion.do";
      myMethod = "listContract";
    }

    if (myAction !== "") {
      frm.action = myAction;
      frm.method.value = myMethod;
      frm.cnsdreq_id.value = id;
      frm.target = "_blank";
      frm.submit();
    }
  }
</script>