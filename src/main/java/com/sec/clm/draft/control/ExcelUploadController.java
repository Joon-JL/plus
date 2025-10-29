package com.sec.clm.draft.control;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.CustomerNewForm;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.ExcelForm;
import com.sec.clm.draft.dto.ExcelVO;
import com.sec.clm.draft.service.ExcelUploadService;

public class ExcelUploadController extends CommonController {

	/**
	 * Excel upload 서비스
	 */
	private ExcelUploadService excelUploadService;

	public void setExcelUploadService(ExcelUploadService excelUploadService) {
		this.excelUploadService = excelUploadService;
	}
	
	
//	private LawfirmCstCalService costCalculateService;
//	
//	public void setLawfirmCstCalService(LawfirmCstCalService costCalculateService) {
//		this.costCalculateService = costCalculateService;
//	}

	/**
	 * Counselink Upload 목록
	 * 
	 * @param request
	 *            , response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listCustomerUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/Customer_u.jsp";

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("menu_id", request.getParameter("menu_id"));
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	
	
	/**
	 * 선택한 엑셀 파일을 서버에 업로드 후 bulk insert
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveExcelFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		try {
			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";

			/*********************************************************
			 * 엑셀 관련 처리
			 **********************************************************/
			//String file_path = request.getParameter("file_path");
			//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("/las/upload/201309/20130911_44474239147545894.xls"));
			
			ExcelVO vo = new ExcelVO();
			
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, vo);
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile excelFile = multipartRequest.getFile("filename");	    
	        
	        InputStream is = excelFile.getInputStream();
	        
	        Workbook wb = WorkbookFactory.create(is);
	        
	        Sheet sheet = wb.getSheetAt(0);
			
	        
	        
//	        Workbook wb = WorkbookFactory.create(excelFile.getInputStream());
	        
	        Calendar calendar = Calendar.getInstance();
            java.util.Date date = calendar.getTime();
            
            // 파일 정보
            String fileId = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
            String tableNm = "XLS_"+fileId;
	        
	        String orgFileNm = excelFile.getOriginalFilename();
	        String runIp = propertyService.getProperty("secfw.server.batchIP");
	        String workSheetNm = sheet.getSheetName();
	        String fileInfo = "";
	        String serverFileNm = "";
	        String serverDir = "";
	        String saveDir = "";
	        String returnMessage = "";
	   
	        if (!"".equals(orgFileNm)) {
				
				// 파일의 확장자가 있을 경우 확인
				if (orgFileNm.lastIndexOf(".") != -1) {
					
					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
					 fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
				}
				
				serverFileNm = fileId + "." + fileInfo;
				
				serverDir = propertyService.getProperty("clms.uploadify.serverDir");
				
				String subDir = DateUtil.getDate(new Date()).substring(0,6);
				saveDir = serverDir +'/'+subDir;
				
				
				// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
				if (!new File(saveDir).exists()) {
					new File(saveDir).mkdirs();
				}
				
				File uploadedFile = new File(saveDir, serverFileNm);
				// 파일을 서버의 저장경로로 저장한다.	
				excelFile.transferTo(uploadedFile);
				
				
				// 임시 테이블 생성
//				vo.setTableNm("XLS_20140107174633");
//				vo.setFileNm("C:/Temp/201401/20140107174633.xls");
				vo.setTableNm(tableNm);
				vo.setFileNm(serverDir+"/"+subDir+"/"+serverFileNm);
				vo.setRunIp(runIp);
				vo.setWorkSheetNm(workSheetNm);
				
				List Tempupload = excelUploadService.saveTempTable(vo);
				
				if(Tempupload!=null && Tempupload.size()>0) {
					ListOrderedMap lom = (ListOrderedMap)Tempupload.get(0);
					
					if(lom.get("o_msg").equals("Y")){
						
						List upload = excelUploadService.insertExcelToTable(vo);
						
						if(upload!=null && upload.size()>0) {
							ListOrderedMap lom2 = (ListOrderedMap)upload.get(0);
						
							if(lom2.get("o_msg").equals("Y")){
								List ins = excelUploadService.excelInsertResult(vo);
								
								if(ins!=null && ins.size()>0) {
									ListOrderedMap lom3 = (ListOrderedMap)ins.get(0);
									
									returnMessage = messageSource.getMessage("clm.page.msg.manage.complete", null, new RequestContext(request).getLocale())+" : "+lom3.get("TOTAL")
											+" , "+messageSource.getMessage("clm.page.msg.manage.noAgree", null, new RequestContext(request).getLocale())+" : "+lom3.get("EX_CNT");
									
								}
							}else{
								returnMessage = messageSource.getMessage("clm.page.msg.manage.announce157",  null, new RequestContext(request).getLocale());
							}
						}else {
							returnMessage = messageSource.getMessage("clm.page.msg.manage.announce157",  null, new RequestContext(request).getLocale());
						}
					}else {
						returnMessage = messageSource.getMessage("clm.page.msg.manage.announce157",  null, new RequestContext(request).getLocale());
					}
				}else {
					returnMessage = messageSource.getMessage("clm.page.msg.manage.announce157",  null, new RequestContext(request).getLocale());
				}
				
				
	        }
			
			mav.addObject("menu_id", request.getParameter("menu_id"));
			mav.addObject("returnMessage", returnMessage);
			mav.setViewName(forwardURL);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			String returnMessage = messageSource.getMessage("las.page.lawfirm.costcalculate.fieldName41", null, new RequestContext(request).getLocale());
			request.setAttribute("returnMessage", returnMessage);
			//mav.addObject("returnMessage", returnMessage);
			mav = listCustomerUpload(request, response);
			return mav;
		}
	}	

	/**
	 * 선택한 엑셀 파일을 로드해서 화면에 출력
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addExcelContent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		try {
			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";

			/*********************************************************
			 * 엑셀 관련 처리
			 **********************************************************/
			//String file_path = request.getParameter("file_path");
			//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("/las/upload/201309/20130911_44474239147545894.xls"));
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile excelFile = multipartRequest.getFile("filename");	        
	        
	        InputStream is = excelFile.getInputStream();
	        
	        Workbook wb = WorkbookFactory.create(is);
	        		
//	        Workbook wb = WorkbookFactory.create(excelFile.getInputStream());
	        
//	        Workbook wb = WorkbookFactory.create(is);
        
			//워크북 생성               
			//HSSFWorkbook workbook = new HSSFWorkbook(is);

			//시트 이름과 시트번호를 추출
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			ArrayList calList = new ArrayList();
			
			for (int r = 2; r <= rows; r++) {
				ListOrderedMap lom = new ListOrderedMap();
				//에러메시지
				StringBuffer err_msg = new StringBuffer();
				
				// 시트에 대한 행을 하나씩 추출
				Row row   = sheet.getRow(r);
				if (row != null) { 
					int cells = row.getPhysicalNumberOfCells();

					for (short c = 0; c <= cells; c++) {
						// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
						
						//소수점을 받아오기 위해 통화단위를 먼저 받아온다
//						Cell currencyCell = row.getCell(16); 
//						String currency = StringUtil.nvl(currencyCell.getStringCellValue(),"USD");
//						int point = costCalculateService.getPoint(currency);
						
						Cell cell  = row.getCell(c);
						if (cell != null) { 
							String value = null;
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_FORMULA :
								value = cell.getCellFormula();
								break;
							case Cell.CELL_TYPE_NUMERIC :
								//날짜로 들어올 시
								if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									value = formatter.format(cell.getDateCellValue());
								}
								//숫자로 들어올 시
								else{
									String df = "########################.########"; 
//									if(c>10 && c<16){ // c = 11,12,13,14,15 (비용). 비용일 시 통화단위에 따른 소수점 세팅
//										df = "#######################0";
//										
//										if(point > 0){
//											df += ".";
//										}
//										for(int i = 0 ; i < point ; i++){
//											df += "0";
//										}
//									}
									DecimalFormat dft = new DecimalFormat(df);
									value = String.valueOf(dft.format(cell.getNumericCellValue()));
								}
								
								break;
							case Cell.CELL_TYPE_STRING :
								value = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_BLANK :
								value = null;
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								value = String.valueOf(cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_ERROR :
								value = String.valueOf(cell.getErrorCellValue());
								break;
							default :
							}

							value = StringUtil.nvl(value, "");
							value = value.trim();
							
							//각 cell 데이터의 유효성 처리 및 map에 저장
							// 1,3,4,7,8,11,12,13,14,15,16
							
							String cell_nm = "";
							
							switch(c){
							case 0 : //Lawfirm Office
								cell_nm = "gerp_cd";
								if(value.length() < 1){
									err_msg.append("0:missed");
								}
								lom.put(cell_nm, value);
								break;
							
							case 1 : //Invoice Id(lawfirm)
								cell_nm = "customer_nm1";
								if(value.length() < 1){
									err_msg.append("1:missed");
								}
								lom.put(cell_nm, value);
								break;
							
							case 2 : //Invoice Date
								cell_nm="nation";
								if(value.length() < 1){
									err_msg.append("2:missed");
								}
								lom.put(cell_nm, value);
								break;
																
							case 3 : //Matter ID (counselLink)
								cell_nm="postalcode";								
								lom.put(cell_nm, value);
								break;
							
							case 4 : //Matter Title
								cell_nm="street";
								if(value.length() < 1){
									err_msg.append("4:missed");
								}
								lom.put(cell_nm, value);
								break;
							
							case 5 : //Amount							
								cell_nm="stapr";
								lom.put(cell_nm, value);
								break;
							
							case 6 : //Invoice Billed Amt (Fees)(LVL)							
								cell_nm="cityn";
								lom.put(cell_nm, value);
								break;
							
							case 7 : //Invoice Billed Amt (Expenses)(LVL)																
								cell_nm="telephone1";
								lom.put(cell_nm, value);
								break;
							
							case 8 : //Invoice Lawfirm Discounts and Premiums(LVL)								
								
								cell_nm="email";
								lom.put(cell_nm, value);
								break;
							
							case 9 : //Invoice LawFirm Taxes(LVL)
								//$ 제거								
								cell_nm="customer_cd";
								if(value.length() < 1){
									err_msg.append("9:missed");
								}
								lom.put(cell_nm, value);
								break;
							
							case 10 : //Local Currency
								
								cell_nm="rep_nm";
								lom.put(cell_nm, value);
								break;
							}
							
						} 
					}
					//null인 field 값 세팅
					//validation 에러 체크 필드 초기화
					if(err_msg.length() == 0){
						lom.put("is_err", "N");
					}
					else{
						lom.put("is_err", "Y");
					}
					lom.put("err_msg", err_msg);
					
					//널값세팅
					setNullValue(lom);

					HttpSession session = request.getSession(false);
					lom.put("ins_id", StringUtil.nvl(session.getAttribute("secfw.session.user_id"), ""));
					
					//환율
//					HashMap hm5 = new HashMap();
//					hm5.put("c_dt", lom.get("invoice_date").toString().replaceAll("/", "").replaceAll("-", ""));
//					hm5.put("toCurrency", "USD");
//					hm5.put("currency", lom.get("currency").toString());
//					hm5.put("amt", lom.get("cal_amt_cur").toString());
					
					//환율 계산
//					HashMap resultMap = costCalculateService.exchageRate(hm5);
//					lom.put("cal_amt_krw", resultMap.get("amt_krw").toString());
//					lom.put("cal_amt_usd", resultMap.get("ex_amt").toString());
					
					calList.add(lom);
				}
			}
			
			//유효성체크
//			excelUploadService.validateContent(calList);
			
			//MAV에 객체 추가
			mav.addObject("menu_id", request.getParameter("menu_id"));
			mav.addObject("calList", calList);
			mav.addObject("vendorType", "V");
			mav.setViewName(forwardURL);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			String returnMessage = messageSource.getMessage("las.page.lawfirm.costcalculate.fieldName41", null, new RequestContext(request).getLocale());
			request.setAttribute("returnMessage", returnMessage);
			//mav.addObject("returnMessage", returnMessage);
			mav = listCustomerUpload(request, response);
			return mav;
		}
	}	
	
	/**
	 * 선택한 엑셀 파일을 로드해서 화면에 출력
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addExcelContentC(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		try {
			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";

			/*********************************************************
			 * 엑셀 관련 처리
			 **********************************************************/
			//String file_path = request.getParameter("file_path");
			//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("/las/upload/201309/20130911_44474239147545894.xls"));
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile excelFile = multipartRequest.getFile("filename");	        
	        
//	        InputStream is = excelFile.getInputStream();
	        
//	        File inputFile = new File(excelFile.getInputStream().read());
	        
	        Workbook wb = WorkbookFactory.create(excelFile.getInputStream());
	        
//	        Workbook wb = WorkbookFactory.create(is);
        
			//워크북 생성               
			//HSSFWorkbook workbook = new HSSFWorkbook(is);

			//시트 이름과 시트번호를 추출
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			ArrayList calList = new ArrayList();
			
			for (int r = 2; r <= rows; r++) {
				ListOrderedMap lom = new ListOrderedMap();
				//에러메시지
				StringBuffer err_msg = new StringBuffer();
				
				// 시트에 대한 행을 하나씩 추출
				Row row   = sheet.getRow(r);
				if (row != null) { 
					int cells = row.getPhysicalNumberOfCells();

					for (short c = 0; c <= cells; c++) {
						// 행에대한 셀을 하나씩 추출하여 셀 타입에 따라 처리
						
						//소수점을 받아오기 위해 통화단위를 먼저 받아온다
//						Cell currencyCell = row.getCell(16); 
//						String currency = StringUtil.nvl(currencyCell.getStringCellValue(),"USD");
//						int point = costCalculateService.getPoint(currency);
						
						Cell cell  = row.getCell(c);
						if (cell != null) { 
							String value = null;
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_FORMULA :
								value = cell.getCellFormula();
								break;
							case Cell.CELL_TYPE_NUMERIC :
								//날짜로 들어올 시
								if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									value = formatter.format(cell.getDateCellValue());
								}
								//숫자로 들어올 시
								else{
									String df = "########################.########"; 
//									if(c>10 && c<16){ // c = 11,12,13,14,15 (비용). 비용일 시 통화단위에 따른 소수점 세팅
//										df = "#######################0";
//										
//										if(point > 0){
//											df += ".";
//										}
//										for(int i = 0 ; i < point ; i++){
//											df += "0";
//										}
//									}
									DecimalFormat dft = new DecimalFormat(df);
									value = String.valueOf(dft.format(cell.getNumericCellValue()));
								}
								
								break;
							case Cell.CELL_TYPE_STRING :
								value = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_BLANK :
								value = null;
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								value = String.valueOf(cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_ERROR :
								value = String.valueOf(cell.getErrorCellValue());
								break;
							default :
							}

							value = StringUtil.nvl(value, "");
							value = value.trim();
							
							//각 cell 데이터의 유효성 처리 및 map에 저장
							// 1,3,4,7,8,11,12,13,14,15,16
							
							String cell_nm = "";
							
							switch(c){
							case 0 : //Lawfirm Office
								cell_nm = "gerp_cd";
//								if(value.length() > 100){
//									err_msg.append("1:over length");
//								}
								lom.put(cell_nm, value);
								break;
							
							case 1 : //Invoice Id(lawfirm)
								cell_nm = "customer_nm1";
								lom.put(cell_nm, value);
								break;
							
							case 2 : //Invoice Date
								cell_nm="nation";
								lom.put(cell_nm, value);
								break;
																
							case 3 : //Matter ID (counselLink)
								cell_nm="postalcode";								
								lom.put(cell_nm, value);
								break;
							
							case 4 : //Matter Title
								cell_nm="street";
								lom.put(cell_nm, value);
								break;
							
							case 5 : //Amount							
								cell_nm="stapr";
								lom.put(cell_nm, value);
								break;
							
							case 6 : //Invoice Billed Amt (Fees)(LVL)							
								cell_nm="cityn";
								lom.put(cell_nm, value);
								break;
							
							case 7 : //Invoice Billed Amt (Expenses)(LVL)																
								cell_nm="telephone1";
								lom.put(cell_nm, value);
								break;
							
							case 8 : //Invoice Lawfirm Discounts and Premiums(LVL)								
								
								cell_nm="email";
								lom.put(cell_nm, value);
								break;
							
							case 9 : //Invoice LawFirm Taxes(LVL)
								//$ 제거								
								cell_nm="customer_cd";
								lom.put(cell_nm, value);
								break;
							
							case 10 : //Local Currency
								
								cell_nm="rep_nm";
								lom.put(cell_nm, value);
								break;
							}
							
						} 
					}
					//null인 field 값 세팅
					//validation 에러 체크 필드 초기화
					if(err_msg.length() == 0){
						lom.put("is_err", "N");
					}
					else{
						lom.put("is_err", "Y");
					}
					lom.put("err_msg", err_msg);
					
					//널값세팅
					setNullValue(lom);

					HttpSession session = request.getSession(false);
					lom.put("ins_id", StringUtil.nvl(session.getAttribute("secfw.session.user_id"), ""));
					
					//환율
//					HashMap hm5 = new HashMap();
//					hm5.put("c_dt", lom.get("invoice_date").toString().replaceAll("/", "").replaceAll("-", ""));
//					hm5.put("toCurrency", "USD");
//					hm5.put("currency", lom.get("currency").toString());
//					hm5.put("amt", lom.get("cal_amt_cur").toString());
					
					//환율 계산
//					HashMap resultMap = costCalculateService.exchageRate(hm5);
//					lom.put("cal_amt_krw", resultMap.get("amt_krw").toString());
//					lom.put("cal_amt_usd", resultMap.get("ex_amt").toString());
					
					calList.add(lom);
				}
			}
			
			//유효성체크
//			excelUploadService.validateContent(calList);
			
			//MAV에 객체 추가
			mav.addObject("menu_id", request.getParameter("menu_id"));
			mav.addObject("calList", calList);
			mav.addObject("vendorType", "V");
			mav.setViewName(forwardURL);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			String returnMessage = messageSource.getMessage("las.page.lawfirm.costcalculate.fieldName41", null, new RequestContext(request).getLocale());
			request.setAttribute("returnMessage", returnMessage);
			//mav.addObject("returnMessage", returnMessage);
			mav = listCustomerUpload(request, response);
			return mav;
		}
	}	

	private void setNullValue(ListOrderedMap lom) throws Exception {
		//lom.put("lfm_nm", StringUtil.nvl((String)lom.get("lfm_nm"), ""));
		//lom.put("invoice_no", StringUtil.nvl((String)lom.get("invoice_no"), ""));
		//lom.put("invoice_date", StringUtil.nvl((String)lom.get("invoice_date"), ""));
		//lom.put("case_id", StringUtil.nvl((String)lom.get("case_id"), ""));
		//lom.put("case_title", StringUtil.nvl((String)lom.get("case_title"), ""));
//		lom.put("cal_amt_krw", StringUtil.nvl((String)lom.get("cal_amt_krw"), "0.00"));
//		lom.put("cal_amt_usd", StringUtil.nvl((String)lom.get("cal_amt_usd"), "0.00"));
//		lom.put("cal_amt_cur", StringUtil.nvl((String)lom.get("cal_amt_cur"), "0.00"));
//		lom.put("cal_amt_fee", StringUtil.nvl((String)lom.get("cal_amt_fee"), "0.00"));
//		lom.put("cal_amt_exp", StringUtil.nvl((String)lom.get("cal_amt_exp"), "0.00"));
//		lom.put("cal_amt_tax", StringUtil.nvl((String)lom.get("cal_amt_tax"), "0.00"));
//		lom.put("cal_amt_dis", StringUtil.nvl((String)lom.get("cal_amt_dis"), "0.00"));
//		lom.put("currency", StringUtil.nvl((String)lom.get("currency"), "USD"));
	}
	
	/**
	 * 엑셀 컨텐츠 저장
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveExcelContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";
			String returnMessage = "";
			int result = 0;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Service
			 **********************************************************/
		
			//calList받아옴
			List calList = (List)session.getAttribute("calList");
			List resultList = new ArrayList();
			String[] delYn = request.getParameterValues("delYn");
			String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			ListOrderedMap lom = null;
			
			for(int i = 0 ; i < calList.size() ; i++){
				lom = (ListOrderedMap) calList.get(i);
				lom.put("session_comp_cd", compCd);
				lom.put("delYn", delYn[i]);
				lom.put("vendor_type", "V");
				resultList.add(lom);
			}
			
			//서비스 호출
			excelUploadService.saveExcelContent(resultList);
			
			returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
					
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			
			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("menu_id", request.getParameter("menu_id"));
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			String returnMessage = messageSource.getMessage("las.page.lawfirm.costcalculate.fieldName41", null, new RequestContext(request).getLocale());
			request.setAttribute("returnMessage", returnMessage);
			//mav.addObject("returnMessage", returnMessage);
			mav = listCustomerUpload(request, response);
			return mav;
		}
	}
	
	/**
	 * 엑셀 컨텐츠 저장
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveExcelContentC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";
			String returnMessage = "";
			int result = 0;
			/*********************************************************
			 * Service
			 **********************************************************/
		
			//calList받아옴
			List calList = (List)request.getSession().getAttribute("calList");
			List resultList = new ArrayList();
			String[] delYn = request.getParameterValues("delYn");
			ListOrderedMap lom = null;
			
			for(int i = 0 ; i < calList.size() ; i++){
				lom = (ListOrderedMap) calList.get(i);
				lom.put("delYn", delYn[i]);
				resultList.add(lom);
			}
			
			//서비스 호출
			excelUploadService.saveExcelContent(resultList);
			
			returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
					
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			
			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("menu_id", request.getParameter("menu_id"));
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			String returnMessage = messageSource.getMessage("las.page.lawfirm.costcalculate.fieldName41", null, new RequestContext(request).getLocale());
			request.setAttribute("returnMessage", returnMessage);
			//mav.addObject("returnMessage", returnMessage);
			mav = listCustomerUpload(request, response);
			return mav;
		}
	}
	
	/**
	* 엑셀 업로드 팝업 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardExcelUpload(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	/**
	* 엑셀 파일 업로드
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView excelUpload(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelUpload.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	/**
	* 엑셀 에러 팝업 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardExcelErrorReport(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
//			String forwardURL = "/WEB-INF/jsp/clm/draft/excelErrorReport_l.jsp";
			
			
			ModelAndView mav = new ModelAndView();
			mav = listExcelErrorReport(request, response);
//			mav.setViewName(forwardURL);
			
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* 엑셀 에러 list 조회
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listExcelErrorReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelErrorReport_l.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ExcelForm form = new ExcelForm();
			ExcelVO vo = new ExcelVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setSrch_word3(StringUtil.bvl(vo.getSrch_word3(),""));
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			List resultList = null;
			
			resultList = excelUploadService.listErrorReport(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(lom.get("TOTAL_CNT") == null ? "0" : lom.get("TOTAL_CNT"))));
				//pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			
			return mav;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	/**
	* 엑셀 에러 팝업 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listExcelErrorDetail(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/excelErrorReport_d.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ExcelForm form = new ExcelForm();
			ExcelVO vo = new ExcelVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			vo.setSrch_word(StringUtil.bvl(vo.getSrch_word(),""));
			vo.setSrch_word2(StringUtil.bvl(vo.getSrch_word2(),""));
			
			List resultList = null;
			
			resultList = excelUploadService.listErrorDetail(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(lom.get("TOTAL_CNT") == null ? "0" : lom.get("TOTAL_CNT"))));
				//pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	

}
