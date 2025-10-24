package com.sds.secframework.util.control;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.dto.ZipForm;
import com.sds.secframework.util.service.ZipService;

public class ZipController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private ZipService zipService;
	public void setZipService(ZipService zipService) {
		this.zipService = zipService;
	}
	/**
	 * 우편번호 검색
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listZipcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ZipForm form = new ZipForm();
			bind(request, form);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			String dongnm = form.getDongnm();
			
			HashMap hm = new HashMap();
			hm.put("dongnm", dongnm);

			getLogger().debug("============dongnm: "+ dongnm);			
			
			List listZipcode = zipService.listZipcode(hm);
			
			ModelAndView mav = new ModelAndView(this.getSuccess_list());
			mav.addObject("listZipcode", listZipcode);
			
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
	}
}
