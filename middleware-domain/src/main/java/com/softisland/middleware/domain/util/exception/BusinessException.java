package com.softisland.middleware.domain.util.exception;

/**
 * Created by fengrongze on 2016/8/31.
 *
 */
public class BusinessException extends RuntimeException {


    /**
     * 构造函数
     * @param message 错误消息
     */
    public BusinessException(String message)
    {
        super(message);
    }


    /**
     *
     * @param cause
     */
    public BusinessException(Throwable cause)
    {
        super(cause);
    }


    /**
     *
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause){
        super(message,cause);
    }
}
