package com.sds.secframework.common.dao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;
import anyframe.core.query.QueryServiceException;

import com.sds.secframework.common.dto.CommonVO;

/**
 * Secframework 에서 사용할 AbstractDAO Class
 *
 */
public class CommonDAO extends AbstractDAO {
	
	private String dbType;
	
	
	/**
	 * prefix / postfix 를 사용하지 않고 queryId 를 그대로 사용하기위해 재 정의
	 */
	public CommonDAO() {
		super();
		
		setFindByPkPostfix("");
		setFindListPostfix("");
		setFindPrefix("");
		setCreateId("");
		setRemoveId("");
		setUpdateId("");	
	}
	
	/**
	 * prefix / postfix 를 사용하지 않고 queryId 를 그대로 사용하기위해 재 정의
	 */
	public CommonDAO(String dbType) {
		this();
		this.dbType = dbType;
	}
	
	public IQueryService getQueryService() {
		return super.getQueryService();
	}

	private String getDbType() {
		return this.dbType;
	}
	
	// 조회
	public List list(String tableName) throws QueryServiceException {
		HashMap hm = new HashMap();
		hm.put("dbType", getDbType());
		
		List result = (List)findList(tableName, hm);
		return result;
	}
	
	public List list(String tableName, Map targetMap) throws QueryServiceException {
		targetMap.put("dbType", getDbType());
		List result = (List)super.findList(tableName, targetMap);
		return result;
	}
	
	public List list(String tableName, Object targetObj) throws QueryServiceException {
		
		CommonVO targetVO = (CommonVO)targetObj;
		targetVO.setDbType(getDbType());
		
		List result = (List)super.findList(tableName, targetVO);
		return result;
	}
	
	public Object findByPk(String tableName, Map targetMap) throws QueryServiceException {
		return super.findByPk(tableName, targetMap);
	}
	
	// 입력
	public int insert(String tableName, Map targetMap) throws QueryServiceException {
		objlog(targetMap,true);
		int result = super.create(tableName, targetMap);
		return result;
	}
	
	//2014-05-22 Kevin commented. Too many logs generated and bunch of logs on console are not helpful.
	public void objlog(Object paramObj, boolean print){
		/*if(print){
			try{
				Map methodMap = com.sec.common.util.ClmsDataUtil.getGetterMethod(paramObj.getClass());
				Iterator iterator = methodMap.keySet().iterator();
				
				while (iterator.hasNext())                                                                              
				{                                                                                                       
					String key = (String)iterator.next();                                                                      
					Method method = (Method)methodMap.get(key);                                                         
					                                                                                                    
					System.out.println("[" + key + "] : [" + method.invoke(paramObj, null) + "]");                                                                                                  
				}
			}                                                                                                   
			catch (Exception ignored)                                                                           
			{                                                                                                   
			}
		}*/
	}
	
	public int insert(String tableName, Object targetObj) throws QueryServiceException {
		objlog(targetObj,true);
		int result = super.create(tableName, targetObj);
		return result;
	}

	public int insert(String tableName) throws QueryServiceException {

		int result = super.create(tableName, new HashMap());
		return result;
	}
	// 수정
	public int modify(String tableName, Map targetMap) throws QueryServiceException {
		objlog(targetMap,true);
		int result = super.update(tableName, targetMap);
		return result;
	}
	
	public int modify(String tableName, Object targetObj) throws QueryServiceException {
		objlog(targetObj,true);
		int result = super.update(tableName, targetObj);
		return result;
	}

	public int modify(String tableName) throws QueryServiceException {
		
		int result = super.update(tableName, new HashMap());
		return result;
	}
	// 삭제
	public int delete(String tableName, Map targetMap) throws QueryServiceException {
		
		int result = super.remove(tableName, targetMap);
		return result;
	}
	
	public int delete(String tableName, Object targetObj) throws QueryServiceException {
		
		int result = super.remove(tableName, targetObj);
		return result;
	}

	public int delete(String tableName) throws QueryServiceException {
		
		int result = super.remove(tableName, new HashMap());
		return result;
	}
	// 쿼리직접사용
	public List listBySQL(String sql, String[] type, Object[] value) throws QueryServiceException {
		IQueryService queryService = getQueryService();
		
		return (List)queryService.findBySQL(sql, type, value);
	}

	public int insertBySQL(String sql, String[] type, Object[] value) throws QueryServiceException {
		IQueryService queryService = getQueryService();		
		return queryService.createBySQL(sql, type, value);
	}

	public int modifyBySQL(String sql, String[] type, Object[] value) throws QueryServiceException {
		IQueryService queryService = getQueryService();		
		return queryService.updateBySQL(sql, type, value);
	}

	public int deleteBySQL(String sql, String[] type, Object[] value) throws QueryServiceException {
		IQueryService queryService = getQueryService();		
		return queryService.removeBySQL(sql, type, value);
	}

	// Batch update
	public int[] batchUpdate(String sql, List value) throws QueryServiceException {
		
		int[] result = null;
		
		try {
		IQueryService queryService = getQueryService();		
		result = queryService.batchUpdate(sql, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Batch update
	public int[] batchUpdateBySQL(String sql, String[] type, List value) throws QueryServiceException {
		
		int[] result = null;
		
		try {
		IQueryService queryService = getQueryService();		
		result = queryService.batchUpdateBySQL(sql, type, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	//CallableStatement
	public Map execute(String queryId, Map value) throws QueryServiceException {
		
		Map result = null;
		
		try {
			IQueryService queryService = getQueryService();		
			result = queryService.execute(queryId, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;		
	}

}
