<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contract Management & Status Change</title>
    <script src="https://code.jquery.com/jquery-1.7.2.min.js"></script>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 30px; background-color: #f8fafc; color: #1e293b; }
        .search-area { background: white; padding: 25px; border-radius: 12px; border: 1px solid #e2e8f0; margin-bottom: 25px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        .input-item { display: inline-block; margin-right: 15px; vertical-align: bottom; }
        .input-item label { display: block; font-size: 12px; font-weight: 600; color: #64748b; margin-bottom: 6px; }
        .input-item input { padding: 8px 12px; border: 1px solid #cbd5e1; border-radius: 6px; height: 38px; font-size: 14px; width: 300px; }
        .btn { padding: 0 20px; border-radius: 6px; border: none; font-weight: 600; cursor: pointer; height: 38px; transition: all 0.2s; }
        .btn-search { background-color: #2563eb; color: white; }
        .btn-search:hover { background-color: #1d4ed8; }
        #table-wrapper { background: white; border: 1px solid #e2e8f0; min-height: 150px; padding: 15px; border-radius: 8px; box-shadow: 0 1px 2px rgba(0,0,0,0.05); }
        .loading-msg { padding: 50px; text-align: center; color: #94a3b8; font-style: italic; }
    </style>
</head>
<body>

<div class="search-area">
    <form id="searchForm" name="searchForm" onsubmit="return false;">
        <input type="hidden" name="isAjax" value="true">

        <div class="input-item">
            <label>Search by Contract ID</label> SESK250102_0001
            <input type="text" name="srch_cntrt_no" id="srch_cntrt_no" placeholder="e.g. SESK250101_0001">
        </div>

        <button type="button" class="btn btn-search" onclick="ajaxSearch()">Find Contract</button>
    </form>
</div>

<div id="table-wrapper">
    <div class="loading-msg">Enter a Contract ID to retrieve current status and management options.</div>
</div>

<script type="text/javascript">
    /**
     * Retrieves contract details without page refresh.
     */
    function ajaxSearch() {
        var id = $('#srch_cntrt_no').val();
        if(!id) { alert("Please enter a Contract ID."); return; }

        $('#table-wrapper').html('<div class="loading-msg">Accessing secure records...</div>');

        $.ajax({
            type: "POST",
            url: "/clm/admin/contract.do?method=listContracts",
            data: $('#searchForm').serialize(),
            success: function(html) {
                $('#table-wrapper').html(html);
            },
            error: function() {
                alert("Search failed. Verify your session or server status.");
            }
        });
    }

    /**
     * Triggers the background status update.
     */
    function updateContractStatus(cntrtNo, targetStatus) {
        if(!confirm("Change status of " + cntrtNo + " to " + targetStatus + "?")) return;

        $.ajax({
            type: "POST",
            url: "/clm/admin/contract.do?method=changeStatusAjax",
            data: {
                srch_cntrt_no: cntrtNo,
                newStatus: targetStatus
            },
            success: function(response) {
                if (response.trim() === "1") {
                    alert("Success: Status updated to " + status);
                    // Reload to reflect changes
                    window.location.reload();
                } else {
                    alert("Procedure Message: " + response);
                }
            },
            error: function() {
                alert("Communication error during status update.");
            }
        });
    }
</script>

</body>
</html>