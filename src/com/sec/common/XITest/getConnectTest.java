package com.sec.common.XITest;

import java.text.SimpleDateFormat;
import java.util.Properties;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.IRepository;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.PoolManager;

public class getConnectTest {
	

	static final String SID = "GJP";
	static private IRepository repository;
	static private String rfcId = "ZHBF_B_GET_EXCH_RATE";
	static JCO.Client client;
	static JCO.Function function;
	static JCO.ParameterList importParams;
	static JCO.ParameterList exportParams;
	static JCO.ParameterList tableParams;
	protected static Properties ifid_info			= null;
	public static void main(String args[]) {

		try {
			// EMGERPManager emg = new EMGERPManager();

			String alias = "";
			// //품질
			// //메타데이타를 가져오는 코드 - Logon Group 사용
			// JCO.addClientPool(alias, 10, "100", "CPIC_SYS1539",
			// "aa2459bb","EN", "165.213.246.237","GFQ","RFC_GROUP");
			//
			// //XI 접근
			// Properties p = new Properties();
			// p.put("jco.client.type", "E");
			// p.put("jco.client.gwhost", "gxqci01.sec.samsung.net"); //XI 품질 서버
			// DNS명
			// p.put("jco.client.gwserv", "sapgw00");
			// p.put("jco.client.tpname","S1539F");
			// p.put("jco.client.codepage", "4103");
			// JCO.Client client = JCO.createClient(p);

			// //운영
			// //메타데이타를 가져오는 코드 - Logon Group 사용
			// JCO.addClientPool(alias, 10, "100", "CPIC_SYS1539",
			// "aa2459bb","EN", "gerpfi.sec.samsung.net","GFP","RFC_GROUP");
			//
			// //XI 접근
			// Properties p = new Properties();
			// p.put("jco.client.type", "E");
			// p.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 운영서버
			// DNS명
			// p.put("jco.client.gwserv", "sapgw63");
			// p.put("jco.client.tpname","S1539F");
			// p.put("jco.client.codepage", "4103");
			// JCO.Client client = JCO.createClient(p);

			// GXQ
			// 메타데이타를 가져오는 코드 - Logon Group 사용
			// JCO.addClientPool(alias, 10, "100", "CPIC_SYS1539",
			// "aa2459bb","EN", "gerpfi.sec.samsung.net","GFP","RFC_GROUP");

			//3) 운영시스템
			//   (GJP-GXP-IS운영)

			    //메타데이타를 가져오는 코드 - Logon Group  사용
			   JCO.addClientPool(alias, 10, "100", "CPIC_SYS1539", "aa2459bb","EN","gerphub.sec.samsung.net", "GJP","RFC_GROUP");

			   //XI 접근
			    Properties p = new Properties();
			    p.put("jco.client.type", "E");
			    p.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 운영서버 DNS명
			    p.put("jco.client.gwserv", "sapgw63");
			    p.put("jco.client.tpname","S1539F");
			    p.put("jco.client.codepage", "4102");
			    JCO.Client client = JCO.createClient(p);

			client.connect();
			System.out.println(" client.isAlive:" + client.isAlive());
			
			// Create a new repository
			repository = JCO.createRepository("EMRepository", SID);
			
			String rfcId = "ZHBF_B_GET_EXCH_RATE";
			IFunctionTemplate ftemplate = repository.getFunctionTemplate(rfcId);
			function = ftemplate.getFunction();
			importParams = function.getImportParameterList();
			exportParams = function.getExportParameterList();
			tableParams = function.getTableParameterList();


			client.execute(function);
			

			client.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	public String getCurTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss z");
		java.util.Date currentTime = new java.util.Date();
		String str = formatter.format(currentTime);
		return str;
	}

	protected static Properties getLoginProperties() {

		/*
		 * ######## 통합테스트 서버 UFQ ######## #jco.client.client = 100
		 * #jco.client.user = CPIC_SYS0076 #jco.client.passwd = a2164b
		 * #jco.client.ashost = 10.47.12.87 #jco.client.sysnr = 00
		 * #jco.client.lang = EN
		 */
		// JCO.addClientPool(alias, 10, "100", "CPIC_SYS1539",
		// "aa2459bb","EN","165.213.246.201","GJQ","RFC_GROUP");

		Properties p = new Properties();
		p.put("jco.client.client", "100");
		p.put("jco.client.user", "CPIC_SYS1539");
		p.put("jco.client.passwd", "aa2459bb");
		p.put("jco.client.ashost", "165.213.246.201");
		p.put("jco.client.sysnr", "00");
		p.put("jco.client.lang", "EN");

		return p;
	}
	public static Properties getXIMapInfo()
	{
		if (ifid_info == null)
		{
			ifid_info	= new Properties();
			try
			{
				
//				   //XI 접근
//			    Properties p = new Properties();
//			    p.put("jco.client.type", "E");
//			    p.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 운영서버 DNS명
//			    p.put("jco.client.gwserv", "sapgw63");
//			    p.put("jco.client.tpname","S1539F");
//			    p.put("jco.client.codepage", "4103");
//			    JCO.Client client = JCO.createClient(p);
				
			    ifid_info.put("jco.client.type", "E");
			    ifid_info.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 품질 서버 DNS명
			    ifid_info.put("jco.client.gwserv", "sapgw63");
			    ifid_info.put("jco.client.tpname","S1539F");
			    ifid_info.put("jco.client.codepage", "4102");
				
//			    ifid_info.put("jco.client.type", propertyService.getProperty("jco.client.type"));
//			    ifid_info.put("jco.client.gwhost", propertyService.getProperty("jco.client.gwhost")); //XI 품질 서버 DNS명
//			    ifid_info.put("jco.client.gHwserv", propertyService.getProperty("jco.client.gwserv"));
//			    ifid_info.put("jco.client.tpname", propertyService.getProperty("jco.client.tpname"));
//			    ifid_info.put("jco.client.codepage", propertyService.getProperty("jco.client.codepage"));				
				
			}
			catch (Exception ex)
			{
				System.out.println(ex);
				//System.exit(1);
			}
		}
		System.out.println("ifid_info ====== " +ifid_info);
		return ifid_info;
	}

}
