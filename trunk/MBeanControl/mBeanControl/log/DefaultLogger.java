package mBeanControl.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLogger {
	private static String notFoundAttributeMessage ="Attribute not found with name: ";
	private static String notFoundObject ="Object not found with name: ";
	public static void NotFoundObjectAttributeLog(String ObjectName,@SuppressWarnings("rawtypes") Class classWithError,String stackTrace){
		Logger logger = LoggerFactory.getLogger(classWithError);
		logger.info(notFoundAttributeMessage + ObjectName);
		logger.debug(stackTrace);
	}
	public static void NotFoundObjectLog(String ObjectName,@SuppressWarnings("rawtypes") Class classWithError,String stackTrace){
		Logger logger = LoggerFactory.getLogger(classWithError);
		logger.info(notFoundObject + ObjectName);
		logger.debug(stackTrace);
	}
	
}
