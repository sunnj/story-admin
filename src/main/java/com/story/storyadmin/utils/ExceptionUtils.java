package com.story.storyadmin.utils;

/**
 * 异常处理工具类
 * 
 */
public final class ExceptionUtils {

	/**
	 * 错误堆栈记录长度
	 */
	private static final int EXCEPTION_STACK_LEN = 8000;
	
	/**
	 * 将异常堆栈信息转为字符串
	 * 
	 * @param e
	 * @return
	 */
    public static String getStackMsg(Exception e) {    
        StringBuffer sb = new StringBuffer();    
        StackTraceElement[] stackArray = e.getStackTrace();    
        for (int i = 0; i < stackArray.length; i++) {    
            StackTraceElement element = stackArray[i];    
            sb.append(element.toString() + "\n");    
        }    
        return extractStack(sb.toString());    
    }    
    
    /**
     * 将异常堆栈信息转为字符串
     * 
     * @param e
     * @return
     */
    public static String getStackMsg(Throwable e) {    
        StringBuffer sb = new StringBuffer();    
        StackTraceElement[] stackArray = e.getStackTrace();    
        for (int i = 0; i < stackArray.length; i++) {    
            StackTraceElement element = stackArray[i];    
            sb.append(element.toString() + "\n");    
        }    
        return extractStack(sb.toString());    
    } 
    
    private static String extractStack(String stack) {
    	int len = stack.length() < EXCEPTION_STACK_LEN ? stack.length() : EXCEPTION_STACK_LEN;
    	return stack.substring(0, len);
    }
	
}
