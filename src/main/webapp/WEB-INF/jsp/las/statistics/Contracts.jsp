<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contract Statistics</title>
    <!-- jQuery is required for the AJAX call to work -->
    <script src="https://code.jquery.com/jquery-1.7.2.min.js"></script>
    <style>
        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 20px; background-color: #f8fafc; color: #1e293b; }
        .search-area { background: white; padding: 25px; border-radius: 12px; border: 1px solid #e2e8f0; margin-bottom: 25px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        .input-item { display: inline-block; margin-right: 15px; vertical-align: bottom; }
        .input-item label { display: block; font-size: 12px; font-weight: 600; color: #64748b; margin-bottom: 6px; }
        .input-item input, .input-item select { padding: 8px 12px; border: 1px solid #cbd5e1; border-radius: 6px; height: 38px; font-size: 14px; }
        .btn { padding: 0 20px; border-radius: 6px; border: none; font-weight: 600; cursor: pointer; height: 38px; transition: all 0.2s; }
        .btn-search { background-color: #2563eb; color: white; }
        .btn-search:hover { background-color: #1d4ed8; }
        .btn-excel { background-color: #16a34a; color: white; margin-left: 5px; }
        .btn-excel:hover { background-color: #15803d; }
        #table-wrapper { background: white; border: 1px solid #e2e8f0; min-height: 150px; padding: 15px; border-radius: 8px; box-shadow: 0 1px 2px rgba(0,0,0,0.05); }
        .loading-msg { padding: 50px; text-align: center; color: #94a3b8; font-style: italic; }
    </style>
</head>
<body>

<div class="search-area">
    <form id="searchForm" name="searchForm">
        <!-- These hidden fields communicate the mode to StatisticsController.java -->
        <input type="hidden" id="excelDownload" name="excelDownload" value="false">
        <input type="hidden" id="menu_id" name="menu_id" value="20260110022106201_0000000465">
        <input type="hidden" id="isAjax" name="isAjax" value="true">

        <div class="input-item">
            <label>Company Code</label>
            <input type="text" name="srch_comp_cd" id="srch_comp_cd" value="${command.srch_comp_cd}" placeholder="e.g. SESK">
        </div>

        <div class="input-item">
            <label>Query Type</label>
            <select name="queryCode" id="queryCode">
                <option value="basic" ${command.queryCode == 'basic' ? 'selected' : ''}>Basic Layout</option>
                <option value="sesk" ${command.queryCode == 'sesk' ? 'selected' : ''}>SESK Style</option>
                <option value="l1" ${command.queryCode == 'l1' ? 'selected' : ''}>Layout 1</option>
                <option value="l2" ${command.queryCode == 'l2' ? 'selected' : ''}>Layout 2</option>
            </select>
        </div>

        <div class="input-item">
            <label>From Date</label>
            <input type="date" name="fromDate" value="${command.fromDate}">
        </div>

        <div class="input-item">
            <label>To Date</label>
            <input type="date" name="toDate" value="${command.toDate}">
        </div>

        <button type="button" class="btn btn-search" onclick="ajaxSearch()">Search Results</button>
        <button type="button" class="btn btn-excel" onclick="executeExcelDownload()">Excel Export</button>
    </form>
</div>

<!-- The content of processor_list.jsp will be dynamically loaded into this div -->
<div id="table-wrapper">
    <div class="loading-msg">Please select criteria and click Search to retrieve data.</div>
</div>

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
     * Executes an AJAX POST to the controller.
     * The controller detects isAjax=true and returns only the table fragment.
     */
    function ajaxSearch() {
        console.log('Test...');
        $('#table-wrapper').html('<div class="loading-msg">Fetching records from server...</div>');

        var params = $('#searchForm').serialize();

        $.ajax({
            type: "POST",
            url: "/las/statistics/statistics.do?method=listContracts", // Ensure this matches your Spring mapping
            data: params,
            success: function(htmlFragment) {
                // Injects the HTML returned from processor_list.jsp
                $('#table-wrapper').html(htmlFragment);
            },
            error: function(xhr) {
                alert("Search failed. Please check your connection or session.");
                $('#table-wrapper').html('<div class="loading-msg">Error loading data.</div>');
            }
        });
    }

    /**
     * Standard form submission for Excel.
     * AJAX cannot trigger a browser 'Save As' dialog, so we use a regular submit.
     */
    function executeExcelDownload() {
        var form = document.searchForm;
        $('#excelDownload').val("true");
        $('#isAjax').val("false"); // We want the full response stream, not a fragment

        form.action = "/las/statistics/statistics.do?method=listContracts";
        form.method = "POST";
        form.submit();

        // Restore flags for the next UI interaction (Search)
        $('#excelDownload').val("false");
        $('#isAjax').val("true");
    }
</script>

</body>
</html>