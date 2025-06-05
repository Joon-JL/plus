package com.sds.secframework.common.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

/**
 * This LoggingAspect class contains beforeLogging
 * method for logging through aspect service
 */
public class LoggingAspect {

    /**
     * catch and logging through aspect service (
     * setting :
     * [src/main/resources/spring/common/context-aspect.xml] )
     * @param thisJoinPoint
     *        aspect service JoinPoint
     */
    public void beforeLogging(JoinPoint thisJoinPoint) {
    	
        Class clazz       = thisJoinPoint.getTarget().getClass();
        String className  = (thisJoinPoint.getTarget().getClass().getName()).toLowerCase();
        String methodName = thisJoinPoint.getSignature().getName();

        StringBuffer buf = new StringBuffer();
        buf.append("\n** Secframework Logging Aspect : executed " + methodName
            + "() in " + className + " Class.");
        Object[] arguments = thisJoinPoint.getArgs();
        if (arguments.length > 0) {
            buf.append("\n*************" + arguments[0].getClass().getName()
                + "*************\n");
            buf.append(arguments[0].toString());
            buf.append("\n*******************************************\n");
        } else
            buf.append("\nNo arguments\n");

        Log logger = LogFactory.getLog(clazz);
        if (logger.isDebugEnabled())
            logger.debug(buf.toString());
    }
}
