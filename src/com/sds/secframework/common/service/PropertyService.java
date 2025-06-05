package com.sds.secframework.common.service;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class PropertyService {

	private MessageSource propertySource = null;

	public void setPropertySource(MessageSource propertySource) {
		this.propertySource = propertySource;
	}
	
	public String getProperty(String code, Object[] args, Locale locale) {
		return propertySource.getMessage(code, args, locale);
	}
	
	public String getProperty(String code, Object[] args, String defaultValue, Locale locale) {
		return propertySource.getMessage(code, args, defaultValue, locale);
	}
	
	/**
	 * Parameter  Default Locale 
	 * @param code
	 * @return
	 */
	public String getProperty(String code) {
		try {
			propertySource.getMessage(code, null, Locale.getDefault());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertySource.getMessage(code, null, Locale.getDefault());
	}

	/**
	 * Default Locale 
	 * @param code
	 * @param args
	 * @return
	 */
	public String getProperty(String code, Object[] args) {
		return propertySource.getMessage(code, args, Locale.getDefault());
	}
}
