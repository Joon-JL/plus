package com.sec.common.XITest;
/**
 * Project Name : 법무시스템 이식
 * File name	: EMGERPManager.java
 * Description	: XI 접속정보를 가져온다.
 * Author		: 박병주
 * Date		: 2011. 11
 * Copyright	:  2011 by SAMSUNG. All rights reserved.  
 */
import java.util.Properties;

import com.sap.mw.jco.IRepository;
import com.sap.mw.jco.JCO;
// import com.sds.secframework.common.service.PropertyService;

public class EMGERPManager {
	String ais = this.getClass().getResource("").getPath(); 
	static private   IRepository  repository;
	static final     String SID				= "GJP";
	protected static Properties login_properties		= null;
	protected static Properties ifid_info			= null;
	private final    boolean verbose			= false;
	
//	protected static PropertyService propertyService;
//	
//	/**
//	 * set propertyService
//	 */
//	public void setPropertyService(PropertyService propertyService) {
//		this.propertyService = propertyService;
//	}
//	
	protected static Properties getMetaProperties(){
		
//		# 운영시스템 접속
//		######## 메타데이타    ########
//		jco.client.client = "100"
//		jco.client.user = "CPIC_SYS1539"
//		jco.client.passwd = "aa2459bb"
//		jco.client.ashost = "gerphub.sec.samsung.net"
//		jco.client.sysnr = "10"
//		jco.client.lang = "EN"
//
//		######## GJP   ########
//		jco.client.type		= E
//		jco.client.gwhost	= gerpxi.sec.samsung.net
//		jco.client.gwserv	= sapgw63
//		jco.client.codepage	= 4103
//		#jco.client.codepage	= 4102
//		jco.client.tpname	= S1539F
	    Properties p = new Properties();
	    p.put("jco.client.client", "100");
	    p.put("jco.client.user", "CPIC_SYS1539");
	    p.put("jco.client.passwd", "aa2459bb");
	    p.put("jco.client.ashost","gerphub.sec.samsung.net");
	    p.put("jco.client.sysnr", "10");
	    p.put("jco.client.lang", "EN");	

		
		
	    return p;
	}

	public EMGERPManager()
	{
		System.out.println("==== JCO connection Start ====");

		if (repository==null )
			init();
	}

	private void init()
	{
		System.out.println("==== JCO Init ====");		
		
		try
		{
			boolean flag = true;

			if (flag)
			{
				// Add a connection pool to the specified system
				JCO.addClientPool(SID,						// Alias for this pool
								  10,						// Max. number of connections
								  getLoginProperties());	// properties  
			}
			// Create a new repository
			repository = JCO.createRepository("EMRepository", SID);
		
		System.out.println("==== JCO Init Finish ====" + repository);
		}

		catch (JCO.Exception ex)
		{
			System.out.println("Caught an exception: \n" + ex);
		}
	}


	public static Properties getLoginProperties()
	{
		if (login_properties == null)
		{

			login_properties	= new Properties();

			try
			{
				login_properties = getMetaProperties();
			}
			catch (Exception ex)
			{
				System.out.println(ex);
			}
		}
		System.out.println("ERP_properties ====== " +login_properties);
		return login_properties;
	}


	public IRepository get_Repository()
	{
		return repository;
	}
/*******************************************************************************
    XI Interface를 위해 RFC ID 와 I/F ID Mapping 정보 Read
********************************************************************************/
	public static Properties getXIMapInfo()
	{
		if (ifid_info == null)
		{
			ifid_info	= new Properties();
			try
			{

//				# 운영시스템 접속
//				######## 메타데이타    ########
//				jco.client.client = "100"
//				jco.client.user = "CPIC_SYS1539"
//				jco.client.passwd = "aa2459bb"
//				jco.client.ashost = "gerphub.sec.samsung.net"
//				jco.client.sysnr = "10"
//				jco.client.lang = "EN"
		//
//				######## GJP   ########
//				jco.client.type		= E
//				jco.client.gwhost	= gerpxi.sec.samsung.net
//				jco.client.gwserv	= sapgw63
//				jco.client.codepage	= 4103
//				#jco.client.codepage	= 4102
//				jco.client.tpname	= S1539F

			    ifid_info.put("jco.client.type", "E");
			    ifid_info.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 품질 서버 DNS명
			    ifid_info.put("jco.client.gwserv", "sapgw63");
			    ifid_info.put("jco.client.codepage", "4103");//4103은 윈도우로컬환경, 4102은 유닉스환경
			    ifid_info.put("jco.client.tpname", "S1539F");
		
				
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
