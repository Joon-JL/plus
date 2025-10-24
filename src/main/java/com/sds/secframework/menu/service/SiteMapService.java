package com.sds.secframework.menu.service;
import java.util.HashMap;
import net.sf.json.JSONArray;
import com.sds.secframework.common.util.ExcelLoader;
import com.sds.secframework.menu.dto.SiteMapVO;

/**
 * 페이지관리 Interface
 *
 */
public interface SiteMapService {


	
	public HashMap SerchAuth(SiteMapVO vo) throws Exception;
	
}
