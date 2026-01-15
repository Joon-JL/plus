<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Contract Management Statistics</title>
    <script src="https://code.jquery.com/jquery-1.7.2.min.js"></script>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 25px; background-color: #f8fafc; color: #1e293b; }
        .search-panel { background: white; padding: 25px; border-radius: 12px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); border: 1px solid #e2e8f0; margin-bottom: 25px; }
        .form-row { display: flex; flex-wrap: wrap; gap: 20px; align-items: flex-end; }
        .input-group { display: flex; flex-direction: column; gap: 6px; }
        .input-group label { font-size: 12px; font-weight: 600; color: #64748b; }
        .input-group input { padding: 8px 12px; border: 1px solid #cbd5e1; border-radius: 6px; font-size: 14px; }

        .button-group { margin-top: 20px; display: flex; gap: 10px; }
        .btn { padding: 10px 20px; border-radius: 6px; border: none; font-weight: 600; cursor: pointer; transition: all 0.2s; }
        .btn-primary { background-color: #2563eb; color: white; }
        .btn-primary:hover { background-color: #1d4ed8; }
        .btn-success { background-color: #16a34a; color: white; }
        .btn-success:hover { background-color: #15803d; }

        select, input[type="text"], input[type="date"] { padding: 6px 10px; border: 1px solid #ccc; border-radius: 4px; height: 35px; box-sizing: border-box; font-size: 14px; }

        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; border: 1px solid #e2e8f0; }
        th { background-color: #f1f5f9; padding: 12px; text-align: left; font-size: 13px; color: #475569; border-bottom: 2px solid #e2e8f0; }
        td { padding: 12px; border-bottom: 1px solid #f1f5f9; font-size: 14px; }
        tr:hover { background-color: #f8fafc; }
        .no-data { text-align: center; padding: 40px; color: #94a3b8; }
    </style>
</head>
<body>
<!-- Diagnostic Panel: Using Scriptlets to verify object existence if JSTL fails -->
<%
    Object listObj = request.getAttribute("resultList");
    int listSize = 0;
    if (listObj instanceof List) {
        listSize = ((List) listObj).size();
    }
%>
<p>Contract Report for Joon</p>
<div class="search-panel">
    <form id="contractsForm" name="contractsForm" action="/las/statistics/statistics.do?method=listContracts" method="post">
        <!-- Bound to vo.excelDownload in StatisticsController -->
        <input type="hidden" id="excelDownload" name="excelDownload" value="false">
        <input type="hidden" name="menu_id"   value="20260110022106201_0000000465" />

        <div class="form-row">
            <div class="input-group">
                <label>Company Code</label>
                <input type="text" id="srch_comp_cd" name="srch_comp_cd" value="${command.srch_comp_cd}" placeholder="e.g. SESK">
            </div>

            <div class="input-group">
                <label>From Date</label>
                <input type="date" name="fromDate" value="${command.fromDate}">
            </div>

            <div class="input-group">
                <label>To Date</label>
                <input type="date" name="toDate" value="${command.toDate}">
            </div>
            <div class="input-group">
                <label>Query Type</label>
                <select id="queryCode" name="queryCode">
                    <option value="basic" <c:if test="${command.queryCode == 'basic'}">selected</c:if>>Basic</option>
                    <option value="sesk" <c:if test="${command.queryCode == 'sesk'}">selected</c:if>>SESK</option>
                    <option value="l1" <c:if test="${command.queryCode == 'l1'}">selected</c:if>>Layout1</option>
                    <option value="l2" <c:if test="${command.queryCode == 'l2'}">selected</c:if>>Layout2</option>
                </select>
            </div>
        </div>

        <div class="button-group">
            <button type="button" class="btn btn-primary" onclick="doAction('view')">
                Search Results
            </button>
            <button type="button" class="btn btn-success" onclick="doAction('excel')">
                Download Full Excel
            </button>
        </div>
    </form>
</div>

<table>
    <thead>
    <tr>
<%--        <th>Company Code</th>--%>
        <th>Contract ID</th>
<%--        <th>Contract Start Date</th>--%>
<%--        <th>Contract End Date</th>--%>
<%--        <th>Contract Status</th>--%>
        <th>Requester (In Charge)</th>
<%--        <th>Requesting dept.</th>--%>
        <th>Request Title</th>
<%--        <th>Contract Name</th>--%>
        <th>Request Date</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <%-- Checking both JSTL and the scriptlet variable for redundancy --%>
        <c:when test="${not empty resultList || (pageContext.request.getAttribute('resultList') != null)}">
            <c:forEach var="contract" items="${resultList}">
                <tr>
<%--                    <td><c:out value="${contract['Company Code']}" /></td>--%>
                    <td><c:out value="${contract['Contract ID']}" /></td>
<%--                    <td><c:out value="${contract['Contract Start Date']}" /></td>--%>
<%--                    <td><c:out value="${contract['Contract End Date']}" /></td>--%>
<%--                    <td><c:out value="${contract['Contract Status']}" /></td>--%>
                    <td><c:out value="${contract['Requester (In Charge)']}" /></td>
<%--                    <td><c:out value="${contract['Requesting dept.']}" /></td>--%>
                    <td><c:out value="${contract['Request Title']}" /></td>
<%--                    <td><c:out value="${contract['Contract Name']}" /></td>--%>
                    <td><c:out value="${contract['Request Date']}" /></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4" class="no-data">
                    No data found for the selected criteria.
                    <br/><small style="color: #cbd5e1;">(List size from Scriptlet: <%= listSize %>)</small>
                    <br/><small style="color: #cbd5e1;">(List size from JSTL: ${fn:length(resultList)})</small>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>


    </tbody>
</table>

<script type="text/javascript">

    $(document).ready(function() {
        /**
         * Change event for queryCode select box.
         * Automatically sets Company Code to 'all' for Layout1 and Layout2.
         */
        $('#queryCode').on('change', function() {
            console.log('changed the value');
            var selectedType = $(this).val();
            if (selectedType === 'l1' || selectedType === 'l2') {
                $('#srch_comp_cd').val('all');
            } else {
                $('#srch_comp_cd').val('SESK');
            }
        });
    });

    /**
     * Logic to handle form submission
     * @param {string} mode - 'view' for standard list, 'excel' for CSV export
     */
    function doAction(mode) {
        var form = document.contractsForm;
        var downloadFlag = document.getElementById('excelDownload');

        if (mode === 'excel') {
            downloadFlag.value = "true";
            form.submit();
        } else {
            downloadFlag.value = "false";
            form.submit();
        }
    }

    function changeCompCd() {

    }
</script>
</body>
</html>