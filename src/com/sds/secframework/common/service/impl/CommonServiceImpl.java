package com.sds.secframework.common.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.transaction.PlatformTransactionManager;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.CommonService;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.file.service.AttachFileService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmsfile.service.ClmsFileService;

public class CommonServiceImpl implements CommonService, MessageSourceAware {
	
	protected static final String MODIFY = "modify";
	protected static final String SEARCH = "search";
	protected static final String DELETE = "delete";
	protected static final String INSERT = "insert";
	
	protected CommonDAO commonDAO;
	protected PropertyService propertyService;
	protected MessageSource messageSource;
	protected AttachFileService attachFileService;
	protected ClmsFileService clmsFileService;
	protected ComUtilService comUtilService;
	protected PlatformTransactionManager transactionManager;
	
	/**
	 * comUtilService 선언 및 세팅
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * FileService 선언 및 세팅
	 */
	
	public void setAttachFileService(AttachFileService attachFileService) {
		this.attachFileService = attachFileService;
	}

	public void setClmsFileService(ClmsFileService clmsFileService) {
		this.clmsFileService = clmsFileService;
	}
	
	/**
	 * set commonDAO 
	 */
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	/**
	 * set propertyService
	 */
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	/**
	 * set messageSourceAccessor
	 */
	 public void setMessageSource(MessageSource messageSource) {
	        this.messageSource = messageSource;
	 }
	 
	 protected MessageSource getMessageSource() {
		 return messageSource;
	 }

	/**
	 * User Transaction에 대한 선언 및 세팅
	 * @param transactionManager
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	/**
	 * Log 
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/
	protected Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
}
