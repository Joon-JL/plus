package com.sds.secframework.user.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.sso.EpTrayUtil;
import com.sds.secframework.common.sso.Utils;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.log.dto.LogVO;
import com.sds.secframework.log.service.LogService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserForm;
import com.sds.secframework.user.dto.UserVO;
import com.sds.secframework.user.service.UserService;
import com.sec.common.util.ClmsDataUtil;

public class LinkController extends CommonController {

	/**
	 * 타 시스템 Link 시  SSO Check 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView linkSsoCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String CntrtGbn = StringUtil.bvl((String)request.getParameter("cntrt_gbn"), "");
		String SysNm = StringUtil.bvl((String)request.getParameter("sys_nm"), "");
		String KeyId = StringUtil.bvl((String)request.getParameter("key_id"), "");
		String returnURL = StringUtil.bvl((String)request.getParameter("returnURL"), "");
		//연계시스템구분 추가 2012/05/14
		String infsysnm = StringUtil.bvl((String)request.getParameter("infsysnm"), "");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/WEB-INF/jsp/secfw/user/LinkSSOCheckFw.jsp");
		mav.addObject("cntrt_gbn", CntrtGbn);
		mav.addObject("sys_nm", SysNm);
		mav.addObject("key_id", KeyId);
		mav.addObject("returnURL", returnURL);
		mav.addObject("infsysnm", infsysnm);		
		return mav;		
		
	}
}
