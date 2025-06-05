package com.sds.secframework.viewResolver;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author tiger380
 * @version 1.0 2010/11/26
 */
public class CustomUrlBasedViewResolver extends UrlBasedViewResolver implements Ordered {
	/**
	 * Override method
	 */
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View)getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		
		if (viewObj instanceof JstlView) {
			JstlView jstlView = (JstlView)viewObj;
			
			/**
			 * freeMarker resolver 로 넘기기위해 ftl 이면 null 을 넘긴다
			 * null 로 넘겨야쥐 다음 우선순위의 viewResolver 를 호출한다.
			 * 아니면 Exception 을 발생한다.
			 */
			if (jstlView.getBeanName().indexOf(".ftl") != -1) {
				return null;
			}
		}
		
		return viewObj;
	}
}
