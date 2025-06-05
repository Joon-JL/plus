package com.sec.common.getXIRateData.service.impl;
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
import com.sds.secframework.common.service.PropertyService;

public class EMGERPManager {
	String ais = this.getClass().getResource("").getPath(); 
	static private   IRepository  repository;
	static final     String SID				= "GJP";
	protected static Properties login_properties		= null;
	protected static Properties ifid_info			= null;
	private final    boolean verbose			= false;
	protected static PropertyService propertyService;	
	
	protected static Properties getMetaProperties(){

	    Properties p = new Properties();
//	    p.put("jco.client.client", propertyService.getProperty("jco.client.client"));
//	    p.put("jco.client.user", propertyService.getProperty("jco.client.user"));
//	    p.put("jco.client.passwd", propertyService.getProperty("jco.client.passwd"));
//	    p.put("jco.client.ashost", propertyService.getProperty("jco.client.ashost"));
//	    p.put("jco.client.sysnr", propertyService.getProperty("jco.client.sysnr"));
//	    p.put("jco.client.lang", propertyService.getProperty("jco.client.lang"));
	    
	    p.put("jco.client.client", "100");
	    p.put("jco.client.user", "CPIC_SYS1539");
	    p.put("jco.client.passwd", "aa2459bb");
	    p.put("jco.client.ashost","gerphub.sec.samsung.net");
	    p.put("jco.client.sysnr", "10");
	    p.put("jco.client.lang", "EN");	
		
	    return p;
	}

	public EMGERPManager(PropertyService propertyService)
	{
		this.propertyService = propertyService;

		if (repository==null )
			init();
	}

	private void init()
	{
		
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
		
		}

		catch (JCO.Exception ex)
		{
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
			}
		}
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
												
//			    ifid_info.put("jco.client.type", propertyService.getProperty("jco.client.type"));
//			    ifid_info.put("jco.client.gwhost", propertyService.getProperty("jco.client.gwhost")); 
//			    ifid_info.put("jco.client.gwserv", propertyService.getProperty("jco.client.gwserv"));
//			    ifid_info.put("jco.client.tpname", propertyService.getProperty("jco.client.tpname"));
//			    ifid_info.put("jco.client.codepage", propertyService.getProperty("jco.client.codepage"));

			    ifid_info.put("jco.client.type", "E");
			    ifid_info.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 품질 서버 DNS명
			    ifid_info.put("jco.client.gwserv", "sapgw63");
			    ifid_info.put("jco.client.codepage", "4102");
			    ifid_info.put("jco.client.tpname", "S1539F");
				
			}
			catch (Exception ex)
			{
				//System.exit(1);
			}
		}
		return ifid_info;
	}
}
