<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .management-container {
        padding: 20px;
        background: #fff;
        border-radius: 8px;
    }
    .mgmt-header {
        font-size: 14px;
        font-weight: 600;
        color: #475569;
        margin-bottom: 15px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .management-table { width: 100%; border-collapse: collapse; }
    .management-table th { background: #f1f5f9; color: #475569; padding: 12px; text-align: left; font-size: 12px; border-bottom: 2px solid #e2e8f0; }
    .management-table td { padding: 15px 12px; border-bottom: 1px solid #f1f5f9; font-size: 14px; vertical-align: middle; }

    .status-indicator { display: flex; flex-direction: column; gap: 4px; }
    .depth1-tag { font-weight: 700; color: #1e293b; font-size: 13px; }
    .depth2-tag { color: #64748b; font-size: 12px; display: flex; align-items: center; gap: 4px; }

    .person-info { display: flex; flex-direction: column; }
    .person-name { font-weight: 500; color: #334155; }
    .person-id { font-size: 11px; color: #94a3b8; }

    .action-panel {
        display: flex;
        flex-direction: column;
        gap: 8px;
    }
    .status-select {
        padding: 8px;
        border: 1px solid #cbd5e1;
        border-radius: 6px;
        font-size: 13px;
        background-color: #fff;
        outline: none;
    }
    .status-select:focus {
        border-color: #2563eb;
        ring: 2px solid #dbeafe;
    }
    .btn-update-status {
        background-color: #10b981;
        color: white;
        padding: 8px 16px;
        border-radius: 6px;
        border: none;
        font-size: 12px;
        font-weight: 600;
        cursor: pointer;
        transition: background 0.2s;
    }
    .btn-update-status:hover { background-color: #059669; }
</style>

<div class="management-container">
    <c:choose>
        <c:when test="${not empty resultList}">


            <table class="management-table">
                <thead>
                    <tr>
                        <th> Title</th>
                        <th style="width: 180px;">ITEM 1</th>
                        <th style="width: 180px;">ITEM 2</th>
                        <th style="width: 180px;">ITEM 3</th>
                        <th style="width: 180px;">ITEM 4</th>
                        <th style="width: 180px;">SRT</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${resultList}">
                        <tr>
                            <td>
                                <c:out value="${list.ITM0}" />
                            </td>
                            <td>
                                <c:out value="${list.ITM1}" />
                            </td>
                            <td>
                                <c:out value="${list.ITM2}" />
                            </td>
                            <td>
                                <c:out value="${list.ITM3}" />
                            </td>
                            <td>
                                <c:out value="${list.ITM4}" />
                            </td>
                            <td>
                                <c:out value="${list.SRT}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div style="padding: 40px; text-align: center;">
                <p style="color: #94a3b8; font-style: italic;">No results found for the provided Contract ID.</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript">
    /**
     * Helper to grab the selected value and pass it to the main update function
     */
    function triggerUpdate(cntrtNo) {
        var selectedStatus = $('#targetStatus_' + cntrtNo).val();

        if (!selectedStatus) {
            alert("Please select a target status first.");
            return;
        }

        // Calls the updateContractStatus function defined in processor.jsp/Contracts.jsp
        if (typeof updateContractStatus === "function") {
            updateContractStatus(cntrtNo, selectedStatus);
        } else {
            console.error("updateContractStatus function not found in parent page.");
        }
    }
</script>