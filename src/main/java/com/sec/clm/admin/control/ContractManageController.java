package com.sec.clm.admin.control;

import com.sds.secframework.common.control.CommonController;
import com.sec.clm.admin.dto.CntrBasicAttrMngVO;
import com.sec.clm.admin.dto.ContractManageVO;
import com.sec.clm.admin.service.ContractManageService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ContractManageController extends CommonController {

    private ContractManageService contractManageService;

    public void setContractManageService(ContractManageService contractManageService) {
        this.contractManageService = contractManageService;
    }


    public ModelAndView listContracts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContractManageVO vo  = new ContractManageVO();

        System.out.println("== listContracts..");

        // 1. Bind form parameters (srch_cntrt_no, etc.) to the VO
        bind(request, vo);

        // 2. Identify if this is a background AJAX request
        boolean isAjax = "true".equals(request.getParameter("isAjax"));

        List resultList = new ArrayList();

        // 3. Execute search only if a Contract ID is provided
        if (vo.getSrch_cntrt_no() != null && !"".equals(vo.getSrch_cntrt_no().trim())) {
            resultList = contractManageService.listContracts(vo);
        }

        // 4. Select the view:
        // processor_list.jsp is the fragment for AJAX updates.
        // processor.jsp is the main container.
        String viewName = isAjax ? "/WEB-INF/jsp/clm/admin/ContractManage_d.jsp"
                : "/WEB-INF/jsp/clm/admin/ContractManage_s.jsp";

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("resultList", resultList);
        mav.addObject("command", vo);

        return mav;
    }

    /**
     * Action method to update contract status via AJAX.
     * Returns the update count as a plain response.
     */
    public void changeStatusAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContractManageVO vo  = new ContractManageVO();
        bind(request, vo);

        try {

            // Validation for the selected code block logic
            if (vo.getSrch_cntrt_no() != null &&
                    vo.getSrch_cntrt_no().length() > 10 &&
                    vo.getSrch_cntrt_no().indexOf('_') != -1) {

                // The service calls a stored procedure to handle the 2-depth status update
                int updateCount = contractManageService.changeCntrtStatus(vo);

                // Since SP uses NOCOUNT ON, we return "1" on successful transaction commit
                response.getWriter().write("1");

                // Write response directly for AJAX success handler
                response.setContentType("text/plain");
                response.getWriter().write(String.valueOf(updateCount));
            } else {
                response.getWriter().write("INVALID_ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Return error details to the UI success handler for troubleshooting
            response.getWriter().write("ERROR: " + e.getMessage());
        }
    }
}
