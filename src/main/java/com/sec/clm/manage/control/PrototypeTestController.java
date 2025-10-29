/**
 * Project Name : 이행정보
 * File name	: ExecutionController.java
 * Description	: 이행정보  Controller
 * Author		: 신승완
 * Date			: 2011. 09. 05
 * Copyright	:
 */
package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.hsqldb.lib.Iterator;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.PrototypeTestForm;
import com.sec.clm.manage.dto.PrototypeTestVO;
import com.sec.clm.manage.service.ExecutionService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.service.ComUtilService;

/**
 * Description	: 이행정보  Controller
 * Author 		: 신승완
 * Date			: 2011. 09. 05
 */
public class PrototypeTestController extends CommonController {
	
	/**
	 * ExecutionService 서비스
	 */
	private ExecutionService executionService;
	
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;
	
	
	/**
	 * ExecutionService 서비스 세팅
	 * @param executionService
	 * @return void
	 * @throws
	 */
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	/**
	 * ComUtil 서비스 세팅
	 * @param comUtilService
	 * @return void
	 * @throws
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * 계약정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailPrototypeTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			PrototypeTestForm form       = null;
			PrototypeTestVO   vo         = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			//forward 방법1
			String pageLv = request.getParameter("pageLv");
			if("01".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest01.jsp";
			}else if("02".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest02.jsp";
			}else if("03".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest03.jsp";
			}else if("04".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest04.jsp";
			}else if("05".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest05.jsp";
			}else if("06".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest06.jsp";
			}else if("07".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest07.jsp";
			}else if("08".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest08.jsp";
			}else if("09".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest09.jsp";
			}else if("10".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest10.jsp";
			}else if("11".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest11.jsp";
			}else if("12".equals(pageLv)){
				forwardURL = "/WEB-INF/jsp/clm/test/PrototypeTest12.jsp";
			}
			//forward 방법2
			String pageURL = request.getParameter("pageURL");
			if(!"".equals(pageURL) || pageURL!=null){
				forwardURL=pageURL;
			}
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new PrototypeTestForm();
			vo = new PrototypeTestVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("Command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("detailPrototypeTest() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("detailPrototypeTest() Throwable !!");
		}
	}
	
}