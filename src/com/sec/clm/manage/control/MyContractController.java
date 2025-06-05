package com.sec.clm.manage.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.service.MyContractService;
/**
 * 계약관리>My Approval>체결품의 
 * @author HP
 *
 */
public class MyContractController extends CommonController {
	
	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	private MyContractService myContractService;
	
	public void setMyContractService(MyContractService myContractService) {
		this.myContractService = myContractService;
	}
	
	/**
	 * MyContract 목록 조회
	 * 공통목록 : 신남원 : 2011.10.04
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listMyContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			
			String pageMode = StringUtil.bvl(request.getParameter("status_mode"),"");
			
			if("rel".equals(pageMode)){
				request.setAttribute("status_mode", "rel");
			} else if("mail".equals(pageMode)){
				request.setAttribute("status_mode", "mail"); //Admin>계약담당자 메일발송 메뉴 페이지용(2012.07.09)
			} else if("samplecontract".equals(pageMode)){
				request.setAttribute("status_mode", "samplecontract"); //SampleContract 조회	
			} else if("StatisticsListManage".equals(pageMode)) {
				request.setAttribute("status_mode", "StatisticsListManage");
			} else if("myContractCCed".equals(pageMode)){
				request.setAttribute("status_mode", "myContractCCed");
			} else {
				request.setAttribute("status_mode", "myContract");
			}
			
			mav = manageControl.listManage(request, response);

			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public ModelAndView detailTypePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			request.setAttribute("status_mode", "myContract");
			
			mav = manageControl.detailTypePopup(request, response);
			
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public ModelAndView forwardTypePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			
			request.setAttribute("status_mode", "myContract");
			mav = manageControl.forwardTypePopup(request, response);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	
}