//////////////////////////////////////////////////////////////////
////
//1. Program Name	: ZHBF_B_GET_EXCH_RATE				//
//2. Descripttion	:											//
//3. Solman Key	: 60495										//
//4. I/F ID		: FI00333									//
//5. Last Update	: 2008. 05. 08. (zeus)						//
////
//////////////////////////////////////////////////////////////////
package com.sec.common.XITest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.IRepository;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Table;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.common.getXIRateData.dto.GetXIRateDataVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/config/spring/secframework/context-aspect.xml",
		"classpath:/config/spring/secframework/context-datasource.xml",
		"classpath:/config/spring/secframework/context-idgen.xml",
		"classpath:/config/spring/secframework/context-msg-properties.xml",
		"classpath:/config/spring/secframework/context-query.xml",
		"classpath:/config/spring/secframework/context-secframework.xml",
		"classpath:/config/spring/secframework/context-transaction.xml",
		"classpath:/config/spring/clm/context-clm.xml",
		"classpath:/config/spring/common/context-common.xml"})
@TransactionConfiguration(transactionManager="secfw.txManager",defaultRollback=false)
@Transactional
public class ZHBF_B_GET_EXCH_RATE  extends CommonServiceImpl {
	
//	@Autowired
//	private CommonDAO commonDAO;
//	public void setCommonDAO(CommonDAO commonDAO) {
//		this.commonDAO = commonDAO;
//	}
//	
	private JCO.Client client;
	private JCO.Function function;
	private JCO.ParameterList importParams;
	private JCO.ParameterList exportParams;
	private JCO.ParameterList tableParams;

	public ZHBF_B_GET_EXCH_RATE() {
	}

	/* connect */
	public void connect(String rfcId) {
		// Return된 데이터가 한글인 경우, 간혹 깨지는 현상 발생하여 Encoding Type Print
		// System.out.println("String encoding = " +
		// System.getProperty("file.encoding"));
		// Encoding Type에 문제가 없는 경우 EMXIMapping.properties File에서
		// jco.client.codepage = 4103 -> jco.client.codepage = 4102 로 변경을 해본다.

		EMGERPManager sap = new EMGERPManager();
		IRepository repository = sap.get_Repository();
		
		IFunctionTemplate ftemplate = repository.getFunctionTemplate(rfcId);
		function = ftemplate.getFunction();
		importParams = function.getImportParameterList();
		exportParams = function.getExportParameterList();
		tableParams = function.getTableParameterList();

	}

	/* Execute */
	public void execute() {
		EMGERPManager xi = new EMGERPManager();
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

	public void ZHBF_B_GET_EXCH_RATE() {
		// 연계 레이아웃에 따라 개발(정의)된 G-ERP의 Function ID
		String rfcId = "ZHBF_B_GET_EXCH_RATE";

		try {
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
	    	
			DecimalFormat df = new  DecimalFormat("##0.00");
			double x = 0.00;
		    
		    for (int i = 0; i < table.getNumRows(); i++) {
		    	
		    	GetXIRateDataVO tempVO = new GetXIRateDataVO();
	    	
		    	table.setRow(i);		    	

				String fld_nm = "";
				String fld_val = "";
		

				BigDecimal temp_num = new BigDecimal(x);
				
				for (JCO.FieldIterator e = table.fields(); e
						.hasMoreElements();) {
					JCO.Field field = e.nextField();
					fld_nm = (field.getName()).trim();
					fld_val = field.getString();
					
					tempVO.setC_dt(c_DT);
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

				}
				
				gxidVo[i] = tempVO;
				tempVO = null; 
				temp_num = null;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			disconnect();
		}
	}
	
	public void ZHBF_B_GET_EXCH_RATE(String c_dt) {

		String rfcId = "ZHBF_B_GET_EXCH_RATE";

		try {
			connect(rfcId);
			
		    JCO.ParameterList importListTable = function.getImportParameterList();
		    
		    String param1 = new String(c_dt);
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
		    System.out.println(f.getName());
		    System.out.println("value :" + exportListOutput.getValue("RC"));
		   
		    Table table = tbl.getTable(1);
		    
		    GetXIRateDataVO[] gxidVo = new GetXIRateDataVO[table.getNumRows()];
	    	String CO_CD = "C10";

			DecimalFormat df = new  DecimalFormat("###0.00");
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
					
					tempVO.setC_dt(c_dt);
					tempVO.setCo_cd(CO_CD);
					
					if("FCURR".equals(fld_nm))
						tempVO.setCurrency(fld_val);
					
					if("UKURS".equals(fld_nm)){
						if(fld_val.indexOf(".") == 0){
							fld_val = "0" + fld_val;
						}
						temp_num = new BigDecimal(fld_val);
						fld_val = df.format(temp_num.doubleValue()); 
						fld_val.replaceAll(",","");
						tempVO.setArrate(fld_val);					
					}
				}
				
				gxidVo[i] = tempVO;
				tempVO = null; 
				temp_num = null;
			}
		    
		    
		    // DB 에 삽입
		  //  insertData(gxidVo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			disconnect();
		}
	}
	
	public void ZHBF_B_GET_EXCH_RATE_test() {
		ZHBF_B_GET_EXCH_RATE em = new ZHBF_B_GET_EXCH_RATE();
		ArrayList el = em.dateList(2010);
		for(int i = 0;i < el.size();i++){
			// System.out.println("EL:::" + (String)el.get(i));
			em.ZHBF_B_GET_EXCH_RATE((String)el.get(i));			
		}	
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
			}
			
			for(int i = 1; i < day + 1; i ++){
				String thisday = "";
				thisday = getAllYearCurTime(year,month,i);
				day_list.add(thisday);
				// System.out.println(thisday);
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
					if(commonDAO == null) {
					}
				
					commonDAO.insert("com.sec.common.getXIRateData.insert", gxivo[i]);

				}
			}
		}	
	}

	public String getCurTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMdd");
		java.util.Date currentTime = new java.util.Date();
		String str = formatter.format(currentTime);
		return str;
	}
	
	public String getAllYearCurTime(int year,int month, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMdd");

		java.util.Date currentTime = new java.util.Date(year - 1900, month - 1, day);
		String str = formatter.format(currentTime);
		return str;
	}

	//@Test
	public void insertAnualCurrency(){
		ZHBF_B_GET_EXCH_RATE em = new ZHBF_B_GET_EXCH_RATE();
		//em.ZHBF_B_GET_EXCH_RATE();
		ArrayList el = em.dateList(2010);
		for(int i = 0;i < el.size();i++){
			// System.out.println("EL:::" + (String)el.get(i));
			em.ZHBF_B_GET_EXCH_RATE((String)el.get(i));			
		}	
	}
	
	
	// public static void main(String[] argv) {
	@Test
	public void main() {
		ZHBF_B_GET_EXCH_RATE em = new ZHBF_B_GET_EXCH_RATE();
		em.ZHBF_B_GET_EXCH_RATE("20120130");
		//ArrayList el = em.dateList(2010);
		//for(int i = 0;i < el.size();i++){
			// System.out.println("EL:::" + (String)el.get(i));
		//	em.ZHBF_B_GET_EXCH_RATE((String)el.get(i));			
		//}	
		
		// em.ZHBF_B_GET_EXCH_RATE_test();
	}

}

