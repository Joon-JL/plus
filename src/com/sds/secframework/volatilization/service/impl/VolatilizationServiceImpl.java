package com.sds.secframework.volatilization.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.volatilization.service.VolatilizationService;

public class VolatilizationServiceImpl extends Thread implements VolatilizationService {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private VolatilizationService volatilizationService;
	public void setVolatilizationService(VolatilizationService volatilizationService) {
		this.volatilizationService = volatilizationService;
	}
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	/**
	 * User Transaction에 대한 선언 및 세팅
	 * @param transactionManager
	 */
	private PlatformTransactionManager transactionManager;
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	// 목록조회
	public String listTestDupConn() throws Exception {
		String result = "";
		HashMap hm = new HashMap();
		List al =  commonDAO.list("secfw.volatilization.listTestDupConn", hm);		
		if(al!=null && al.size()>0) {			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(0);
				
				result = (String)lom.get("id");
			}
		}		
		return result;	
	}

	// 신규등록
	public int insertTestDupConn() throws Exception {
		int result = 0;

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = null;
		
		try {

			status = transactionManager.getTransaction(def);

			HashMap hm = new HashMap();
			hm.put("id", "A");
			result = commonDAO.insert("secfw.volatilization.insertTestDupConn", hm);
			
			transactionManager.commit(status);
			status = transactionManager.getTransaction(def);
			sleep(5000);

			hm.put("id", "B");
			result = commonDAO.insert("secfw.volatilization.insertTestDupConn", hm);

			transactionManager.commit(status);
		} catch(Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw e;
		} 
		return result;
	}
	
}
