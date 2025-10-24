/**
 * Project Name : 법무시스템 이식
 * File name	: GetXIRateDataServiceImpl.java
 * Description	: XI 시스템에서 환율정보를 가져온다.
 * Author		: 박병주
 * Date		: 2011. 11
 * Copyright	:  2011 by SAMSUNG. All rights reserved.  
 */
package com.sec.common.getXIRateData.service.impl;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.IRepository;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Table;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.getXIRateData.dto.GetXIRateDataVO;
import com.sec.common.getXIRateData.service.GetXIRateDataService;

public class GetXIRateDataServiceImpl extends CommonServiceImpl implements GetXIRateDataService {
	private JCO.Client client;
	private JCO.Function function;
	private JCO.ParameterList importParams;
	private JCO.ParameterList exportParams;
	private JCO.ParameterList tableParams;
	
	protected static PropertyService propertyService;
	
	/**
	 * set propertyService
	 */
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	private ComUtilService comUtilService;	
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/* connect */
	public void connect(String rfcId) {
		// Return된 데이터가 한글인 경우, 간혹 깨지는 현상 발생하여 Encoding Type Print
		// System.getProperty("file.encoding"));
		// Encoding Type에 문제가 없는 경우 EMXIMapping.properties File에서
		// jco.client.codepage = 4103 -> jco.client.codepage = 4102 로 변경을 해본다.

		EMGERPManager sap = new EMGERPManager(propertyService);
		IRepository repository = sap.get_Repository();
		
		IFunctionTemplate ftemplate = repository.getFunctionTemplate(rfcId);
		function = ftemplate.getFunction();
		importParams = function.getImportParameterList();
		exportParams = function.getExportParameterList();
		tableParams = function.getTableParameterList();
	}

	/* Execute */
	public void execute() {
		EMGERPManager xi = new EMGERPManager(propertyService);
		Properties if_param = new Properties();
		if_param = xi.getXIMapInfo();

		JCO.Client client = JCO.createClient(if_param);
		client.execute(function);
	}

	/* Disconnect */
	public void disconnect() {
		JCO.releaseClient(client);
		if (client != null)
			JCO.releaseClient(client);
	}
	/**
	 * XI 연계 커넥트/ 데이타 취득 / DB INSERT
	 * 
	 * @param 
	 * @return void
	 */
	public void insertRateData() {
		// 연계 레이아웃에 따라 개발(정의)된 G-ERP의 Function ID
		String rfcId = "ZHBF_B_GET_EXCH_RATE";

		try {
			//if(comUtilService.isCronServerAndPort()) {				
			if(comUtilService.isCronServer()) {				
			
				connect(rfcId);
				
			    JCO.ParameterList importListTable = function.getImportParameterList();
			    
			    String param1 = new String(getCurTime().trim());
			    String param2 = new String("KRW");
			    
			    String param1_utf = new String(param1.getBytes(), "UTF-8");
			    String param2_utf = new String(param2.getBytes(), "UTF-8");
			    			    
			    importParams.setValue(param1_utf,"DATE");
			    importParams.setValue(param2_utf,"TCURR");
			    
				execute();
				
				//결과
			    JCO.ParameterList exportListOutput = function.getExportParameterList();
			    
			    ParameterList tbl = function.getTableParameterList();
		    
			    JCO.Field f = exportListOutput.getField(0);
			   
			    Table table = tbl.getTable(1);
			    
			    GetXIRateDataVO[] gxidVo = new GetXIRateDataVO[table.getNumRows()];
		    	String CO_CD = "C10";
		    	String c_DT = getCurTime();
		    	
				// DecimalFormat df = new  DecimalFormat("#,##0.00");
		    	DecimalFormat df = new  DecimalFormat("##0.00");
		    	double x = 0.00;
			    
			    for (int i = 0; i < table.getNumRows(); i++) {
			    	
			    	GetXIRateDataVO tempVO = new GetXIRateDataVO();
			    	BigDecimal temp_num = new BigDecimal(x);
		    	
			    	table.setRow(i);		    	
	
					String fld_nm = "";
					String fld_val = "";
	
					for (JCO.FieldIterator e = table.fields(); e
							.hasMoreElements();) {
						JCO.Field field = e.nextField();
						fld_nm = (field.getName()).trim();
						fld_val = field.getString();
											
						tempVO.setC_dt(c_DT);
						
						if("KURST".equals(fld_nm) && "M".equals(fld_val))
							tempVO.setCo_cd(CO_CD);
						
						if("FCURR".equals(fld_nm))
							tempVO.setCurrency(fld_val);
						
						if("UKURS".equals(fld_nm)){
							if(fld_val.indexOf(".") == 0){
								fld_val = "0" + fld_val;
							}
							temp_num = new BigDecimal(fld_val);
							fld_val = df.format(temp_num.doubleValue()); 
							tempVO.setArrate(fld_val);	
			
						}
						// Return된 Structure의 결과 값을 Print
					}
					
					gxidVo[i] = tempVO;
					tempVO = null;				
					temp_num = null;
				}
			    
			    insertData(gxidVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	/**
	 * XI 연계 커넥트/ 데이타 취득 / DB INSERT
	 * 
	 * @param 
	 * @return void
	 */
	public void insertRateData(String cdt) {
		// 연계 레이아웃에 따라 개발(정의)된 G-ERP의 Function ID
		String rfcId = "ZHBF_B_GET_EXCH_RATE";

		try {
			
			
			
				connect(rfcId);
				
			    JCO.ParameterList importListTable = function.getImportParameterList();
			    
			    String param1 = cdt;
			    String param2 = new String("KRW");
			    
			    String param1_utf = new String(param1.getBytes(), "UTF-8");
			    String param2_utf = new String(param2.getBytes(), "UTF-8");
			    			    
			    importParams.setValue(param1_utf,"DATE");
			    importParams.setValue(param2_utf,"TCURR");
			    
				execute();
				
				//결과
			    JCO.ParameterList exportListOutput = function.getExportParameterList();
			    
			    ParameterList tbl = function.getTableParameterList();
		    
			    JCO.Field f = exportListOutput.getField(0);
			   
			    Table table = tbl.getTable(1);
			    
			    GetXIRateDataVO[] gxidVo = new GetXIRateDataVO[table.getNumRows()];
		    	String CO_CD = "C10";
		    	String c_DT = cdt;
		    	
				// DecimalFormat df = new  DecimalFormat("#,##0.00");
		    	DecimalFormat df = new  DecimalFormat("##0.00");
		    	double x = 0.00;
			    
			    for (int i = 0; i < table.getNumRows(); i++) {
			    	
			    	GetXIRateDataVO tempVO = new GetXIRateDataVO();
			    	BigDecimal temp_num = new BigDecimal(x);
		    	
			    	table.setRow(i);		    	
	
					String fld_nm = "";
					String fld_val = "";
	
					for (JCO.FieldIterator e = table.fields(); e
							.hasMoreElements();) {
						JCO.Field field = e.nextField();
						fld_nm = (field.getName()).trim();
						fld_val = field.getString();
											
						tempVO.setC_dt(c_DT);
						
						if("KURST".equals(fld_nm) && "M".equals(fld_val))
							tempVO.setCo_cd(CO_CD);
						
						if("FCURR".equals(fld_nm))
							tempVO.setCurrency(fld_val);
						
						if("UKURS".equals(fld_nm)){
							if(fld_val.indexOf(".") == 0){
								fld_val = "0" + fld_val;
							}
							temp_num = new BigDecimal(fld_val);
							fld_val = df.format(temp_num.doubleValue()); 
							tempVO.setArrate(fld_val);	
			
						}
						// Return된 Structure의 결과 값을 Print
					}
					
					gxidVo[i] = tempVO;
					tempVO = null;				
					temp_num = null;
				}
			    
			    insertData(gxidVo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	/**
	 * XI 연계 커넥트/ 데이타 취득 / DB INSERT
	 * 
	 * @param 
	 * @return void
	 */
	public void insertRateData_test() {
		
		try{
		
		ArrayList el = dateList(2011);
		for(int i = 0;i < el.size();i++){
			insertRateData((String)el.get(i));			
		}	
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	/**
	 * 현재 날짜 포맷
	 * 
	 * @param 
	 * @return String
	 */
	public String getCurTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMdd");
		java.util.Date currentTime = new java.util.Date();
		String str = formatter.format(currentTime);
		return str;
	}
	
	public ArrayList dateList(int year) {

		ArrayList day_list = new ArrayList();
		
		int day = 0;
		
		for(int month = 1 ; month < 13; month++){
			
			if(month == 1){
				day = 31;
			} else if(month == 2){
				day = 28;
			} else if(month == 3){
				day = 31;
			} else if(month == 4){
				day = 30;
			} else if(month == 5){
				day = 31;
			} else if(month == 6){
				day = 30;
			} else if(month == 7){
				day = 31;
			} else if(month == 8){
				day = 31;
			} else if(month == 9){
				day = 30;
			} else if(month == 10){
				day = 31;
			} else if(month == 11){
				day = 30;
			} else if(month == 12){
				day = 31;
				//day = 21;
			}
			
			for(int i = 1; i < day + 1; i ++){
				String thisday = "";
				thisday = getAllYearCurTime(year,month,i);
				day_list.add(thisday);
			}			
		}			
			
		return day_list;

	}
	
	
	/**
	 * 환율 삽입
	 * 
	 * @param GetXIRateDataVO[]
	 * @return void
	 * @throws Exception
	 */
	public void insertData(GetXIRateDataVO[] gxivo)throws Exception {
		
		if(gxivo!=null){
			for(int i=0; i < gxivo.length; i++) {
				if("C10".equals(gxivo[i].getCo_cd())){
					commonDAO.insert("com.sec.common.getXIRateData.insert", gxivo[i]);
				}
			}
		}	
	}
	
	public String getAllYearCurTime(int year,int month, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMdd");

		java.util.Date currentTime = new java.util.Date(year - 1900, month - 1, day);
		String str = formatter.format(currentTime);
		return str;
	}
	
	public synchronized boolean getStatusBatch() throws Exception {
		
		if(comUtilService.isCronServer()) { // 운영 서버에서만 데몬 실행
			String ins1pidFile = "/las/logs/las1/las1.pid";
			
			File ins1File = new File(ins1pidFile);
		    
		    if(ins1File.exists()) {
		    	FileInputStream fis = new FileInputStream(ins1File);
		    	BufferedInputStream bis = new BufferedInputStream(fis);
		    	DataInputStream dis = new DataInputStream(bis);
		     
		    	String ins1Pid = "";
	
		    	if(dis.available() != 0) {
		    		ins1Pid = dis.readLine();
		    	}
		    	
		    	RuntimeMXBean rmb = ManagementFactory.getRuntimeMXBean();
		        String processId = rmb.getName();
		        
		        if(processId.length() > ins1Pid.length()) {
		         processId = processId.substring(0, ins1Pid.length());
		        }
		        
		        // 첫번째 인스턴스에서만 실행
		        if(processId.equals(ins1Pid)) {
		        	return true;
		        }else{
		        	return false;
		        }
		        
		    }else{
	        	return false;
	        }
		}else{
        	return false;
        }
	}
}
