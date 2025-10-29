package com.sds.secframework.common.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

public class MessageService {

	private MessageSource messages = null;
	public MessageSource getMessageSource() {
		return messages;
	}

	/**
	 * Locale 선언 및 세팅
	 */
	private LocaleResolver localeResolver;
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	public void setMessageSource(MessageSource messages) {
		this.messages = messages;
	}
	
	public String getMessage(String code, Object[] args, Locale locale) {
		return messages.getMessage(code, args, locale);
	}
	
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messages.getMessage(code, args, defaultMessage, locale);
	}
	
	/**
	 * Parameter Default Locale .
	 * @param code
	 * @return
	 */
	public String getMessage(String code) {
		return messages.getMessage(code, null, Locale.getDefault());
	}

	/**
	 * Default Locale 
	 * @param code
	 * @param args
	 * @return
	 */
	public String getMessage(String code, Object[] args) {
		return messages.getMessage(code, args, Locale.getDefault());
	}
}
