package com.sds.secframework.common.aspect;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import anyframe.common.exception.BaseException;

/** 
 * This ExceptionTransfer class contains messageSource
 * and transfer method for catching and transferring
 * exception through aspect service
 */
public class ExceptionTransfer implements ApplicationContextAware {

    /**
     * messageSource is used to call getMessage method.
     */
    private MessageSource messageSource;

    /**
     * catch and transfer exception through aspect
     * service ( setting :
     * [src/main/resources/spring/common/context-aspect.xml] )
     * @param thisJoinPoint
     *        aspect service JoinPoint
     * @param exception
     *        aspect service exception
     */
    public void transfer(JoinPoint thisJoinPoint, Exception exception) throws BaseException {
    	    	
        String pkgName = thisJoinPoint.getTarget().getClass().getName().toLowerCase();
        
        int lastIndex = pkgName.lastIndexOf(".");
        String className = pkgName.substring(lastIndex + 1);
        String opName = (thisJoinPoint.getSignature().getName()).toLowerCase();

        Log logger = LogFactory.getLog(thisJoinPoint.getTarget().getClass());

        if (exception instanceof BaseException) {
        	BaseException secframeworkEx = (BaseException) exception;
            logger.error(secframeworkEx.getMessage(), secframeworkEx);
            throw secframeworkEx;
        }

        logger.error(messageSource.getMessage("error." + className + "."
            + opName, new String[] {}, Locale.getDefault()), exception);
        throw new BaseException(messageSource.getMessage("error."
            + className + "." + opName, new String[] {}, Locale
            .getDefault()), exception);
        
    }

    /**
     * This method is used to set messageSource bean
     * @param applicationContext
     *        <code>ApplicationContext</code> object.
     * @throws BeansException
     *         if bean is not found.
     * @see org.springframework.context.ApplicationContextAware
     *      #setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.messageSource = (MessageSource) applicationContext.getBean("messageSource");
    }

}
