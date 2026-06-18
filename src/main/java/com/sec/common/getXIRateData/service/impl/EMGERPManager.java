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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EMGERPManager {
	private static IRepository repository;
	static final String SID	= "GJP";
    protected static PropertyService propertyService;

	protected static final Properties login_properties = new Properties();
	protected static final Properties ifid_info	= new Properties();

    static {
        try {
            login_properties.put("jco.client.client", "100");
            login_properties.put("jco.client.user", "CPIC_SYS1539");
            login_properties.put("jco.client.passwd", "aa2459bb");
            login_properties.put("jco.client.ashost","gerphub.sec.samsung.net");
            login_properties.put("jco.client.sysnr", "10");
            login_properties.put("jco.client.lang", "EN");

            ifid_info.put("jco.client.type", "E");
            ifid_info.put("jco.client.gwhost", "gerpxi.sec.samsung.net"); //XI 품질 서버 DNS명
            ifid_info.put("jco.client.gwserv", "sapgw63");
            ifid_info.put("jco.client.codepage", "4102");
            ifid_info.put("jco.client.tpname", "S1539F");
        } catch (Exception e) {
//            getLogger().info("EMGERPManager : " + e);
        }
    }

	public EMGERPManager(PropertyService propertyService)
	{
        EMGERPManager.propertyService = propertyService;

        if (repository == null) {
            synchronized (EMGERPManager.class) {
                if (repository==null ) {
                    init();
                }
            }
        }
	}

	private void init() {
		try {
			JCO.addClientPool(SID,						// Alias for this pool
								  10,						// Max. number of connections
								  login_properties);	// properties
			// Create a new repository
			repository = JCO.createRepository("EMRepository", SID);
		} catch (JCO.Exception ex) {
            getLogger().info("EMGERPManager init: " + ex);
		}
	}

	public IRepository get_Repository()
	{
		return repository;
	}

/*******************************************************************************
    XI Interface를 위해 RFC ID 와 I/F ID Mapping 정보 Read
********************************************************************************/
	public static Properties getXIMapInfo() {
		return ifid_info;
	}

    protected Log getLogger() {
        return LogFactory.getLog(this.getClass());
    }
}
