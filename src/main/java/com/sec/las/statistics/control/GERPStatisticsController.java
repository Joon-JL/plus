package com.sec.las.statistics.control;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.ManageForm;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.las.statistics.service.GERPStatisticsService;
import com.sec.las.statistics.dto.GERPStatisticsVO;
import com.sec.las.statistics.dto.StatisticsForm;
import com.sec.las.statistics.dto.StatisticsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

public class GERPStatisticsController extends CommonController {

	private GERPStatisticsService gerpStatisticsService = null;
	public void setGERPStatisticsService(GERPStatisticsService gerpStatisticsService){
		this.gerpStatisticsService = gerpStatisticsService;
	}
	
	/**
	 * 2014-05-02 Kevin added.
	 * In relation to GERP interaction, this method returns a statistics page for contract conclusion rate.
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ModelAndView GERPContractConclusionRate(HttpServletRequest req, HttpServletResponse res) throws Exception{
		try
		{
			String forwardUrl = "/WEB-INF/jsp/las/statistics/Statistics_MandatoryContractRate.jsp";
			
			StatisticsForm form = new StatisticsForm();
			StatisticsVO vo = new StatisticsVO();
			bind(req, form);
			bind(req, vo);
			COMUtil.getUserAuditInfo(req, form);
			COMUtil.getUserAuditInfo(req, vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardUrl);
			return mav;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("Error");
		}
		catch (Throwable t) 
		{
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 2014-04-30 Kevin implemented
	 * This method returns statistics data for GERP contract rate.
	 * The data has been accumulated for the statistics in daily basis
	 *  
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void ListGERPContractRateByYear(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		JSONObject result = new JSONObject();
		res.setContentType("application/json, charset=utf-8");
		
		StatisticsForm form = new StatisticsForm();
		StatisticsVO vo = new StatisticsVO();
		bind(req, form);
		bind(req, vo);
		COMUtil.getUserAuditInfo(req, form);
		COMUtil.getUserAuditInfo(req, vo);
		
		String targetYear = req.getParameter("targetYear");
		String targetType =req.getParameter("targetType");
		GERPStatisticsVO gerpVO = new GERPStatisticsVO();
		gerpVO.setType("1");
		gerpVO.setTargetYear(targetYear);
		gerpVO.setVendorType(targetType);
		
		List list = this.gerpStatisticsService.GetGERPContractRateStatisticsData(gerpVO);
		List<Map> resultList = new ArrayList<Map>();
		
		for(int i = 0; i < list.size(); i++){
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			Map items = new HashMap();
			items.put("compcd", (String)map.get("COMP_CD"));
			items.put("customer", (Double)map.get("CUSTOMER_RATE"));
			items.put("vendor", (Double)map.get("VENDOR_RATE"));
			items.put("customerfigure", (Integer)map.get("CUSTOMER_FIGURE"));
			items.put("vendorfigure", (Integer)map.get("VENDOR_FIGURE"));
			items.put("customerdenominator", (Integer)map.get("CUSTOMER_DENOMINATOR"));
			items.put("vendordenominator", (Integer)map.get("VENDOR_DENOMINATOR"));
			items.put("updatedate", (String)map.get("UPDATE_DATE"));
			
			resultList.add(items);
		}
		result.put("list", resultList);
		
		PrintWriter writer = res.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	
	/**
	 * 2014-04-30 Kevin implemented.
	 * This method returns data for noncontracted GERP counterparty, mainly Customer at the moment. But, in future Vender will be included.
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void ListGERPNoncontractedCounterparty(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		JSONObject result = new JSONObject();
		res.setContentType("application/json, charset=utf-8");
		
		String compCd = req.getParameter("targetComp");
		String targetYear = req.getParameter("targetYear");
		String targetType	= req.getParameter("targetType");
		
		StatisticsForm form = new StatisticsForm();
		StatisticsVO vo = new StatisticsVO();
		bind(req, form);
		bind(req, vo);
		COMUtil.getUserAuditInfo(req, form);
		COMUtil.getUserAuditInfo(req, vo);
		
		GERPStatisticsVO gerpVO = new GERPStatisticsVO();
		gerpVO.setType("2");
		gerpVO.setTargetYear(targetYear);
		gerpVO.setCompCd(compCd);
		gerpVO.setVendorType(targetType);
		
		List list = this.gerpStatisticsService.GetGERPContractRateStatisticsData(gerpVO);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		
		HashMap hm = new HashMap();
		for(int i = 0; i < list.size(); i++){
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			if(i == 0){
				hm.put("total", (Integer)map.get("TOTAL_CNT"));
				hm.put("optional", (Integer)map.get("OPTIONAL_CNT"));
				hm.put("mandatory", (Integer)map.get("MANDATORY_CNT"));
				hm.put("cnt", (Integer)map.get("CONTRACT_CNT"));
				hm.put("rate", (Double)map.get("RATE"));
			}
			
			Map<String, String> items = new HashMap<String, String>();
			items.put("gerpcd", (String)map.get("GERP_CD"));
			items.put("name", (String)map.get("CUSTOMER_NM1"));
			items.put("mandatory", (String)map.get("MANDATORY"));
			items.put("cnt", ((Integer)map.get("CNT")).toString());
			
			resultList.add(items);
		}
		
		result.put("stat", hm);
		result.put("list", resultList);
		
		PrintWriter writer = res.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	
	/**
	 * 2014-04-30 Kevin implemented.
	 * This method returns data to illustrate how contract rate has been changed in monthly basis.
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void ListGERPContractRateInMontlyBasis(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		JSONObject result = new JSONObject();
		res.setContentType("application/json, charset=utf-8");
		
		/*HttpSession session = req.getSession(false);*/
		/*String compCd = (String)session.getAttribute("secfw.session.comp_cd");*/
		String compCd = req.getParameter("targetComp");
		String targetYear = req.getParameter("targetYear");
		String targetType	= req.getParameter("targetType");
		
		StatisticsForm form = new StatisticsForm();
		StatisticsVO vo = new StatisticsVO();
		bind(req, form);
		bind(req, vo);
		COMUtil.getUserAuditInfo(req, form);
		COMUtil.getUserAuditInfo(req, vo);
		
		GERPStatisticsVO gerpVO = new GERPStatisticsVO();
		gerpVO.setType("3");
		gerpVO.setTargetYear(targetYear);
		gerpVO.setCompCd(compCd);
		gerpVO.setVendorType(targetType);
		
		List list = this.gerpStatisticsService.GetGERPContractRateStatisticsData(gerpVO);
		List<Map> resultList = new ArrayList<Map>();
		
		for(int i = 0; i < list.size(); i++){
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			Map items = new HashMap();
			items.put("compcd", (String)map.get("COMP_CD"));
			items.put("cnumber", (Double)map.get("CUSTOMER_FIGURE"));
			items.put("vnumber", (Double)map.get("VENDOR_FIGURE"));
			items.put("yearmonth", (String)map.get("YEAR_MONTH"));
			items.put("updatedate", (String)map.get("UPDATE_DATE"));
			
			resultList.add(items);
		}
		result.put("list", resultList);
		
		PrintWriter writer = res.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	
	/**
	* 2014-05-06 Kevin added.
	* Download excel of non-contracted customer list.
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void NoncontractedCounterpartyExceldownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			
			String compCd = req.getParameter("targetCompCd");
			String targetYear = req.getParameter("targetYear");
			String targetType	= req.getParameter("targetType");
			
			StatisticsForm form = new StatisticsForm();
			StatisticsVO vo = new StatisticsVO();
			bind(req, form);
			bind(req, vo);
			COMUtil.getUserAuditInfo(req, form);
			COMUtil.getUserAuditInfo(req, vo);
			
			GERPStatisticsVO gerpVO = new GERPStatisticsVO();
			gerpVO.setType("4");
			gerpVO.setTargetYear(targetYear);
			gerpVO.setCompCd(compCd);
			gerpVO.setVendorType(targetType);
			
			List resultList = this.gerpStatisticsService.GetGERPContractRateStatisticsData(gerpVO);
			
			final String fileNm = "Non-contracted_customer_list.xls";
			String[]	sheetNmAry = {"Customer"};
			String[]	subTitleInfo	= new String[]{"Subsidiary Name", "Code", "Name", "Mandatory", "Contract Count"};
			String[]	columnInfo = new String[]{"COMP_CD", "GERP_CD", "CUSTOMER_NM1", "MANDATORY", "CONTRACT_CNT"};
			short[]	columnAlign	 = new short[]{ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT, ExcelBuilder.ALIGN_LEFT};
			
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
							
			excel.setBold(true);
			excel.setFontColor(ExcelBuilder.COLOR_BLACK);
			excel.setFontName("Arial");
			excel.setFontSize((short)11);
			excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
			excel.setBorder(true);
			
			excel.addTitleRow(0, subTitleInfo);
			excel.setDefaultStyle();
			
			excel.setAlign(columnAlign);
			excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
			excel.setBorder(new boolean[]{true});
			
			excel.addRows(0, columnInfo, resultList);
			
			excel.setDefaultStyle();
			
			excel.download(fileNm, res);
			
		}catch (Exception e) {
			e.printStackTrace();
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
