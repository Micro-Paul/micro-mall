package org.micro.exception;

/**
 * @author micro-paul
 * @date 2022年02月10日 17:45
 */
public interface BaseErrorInfoInterface {

    /** 错误码*/
    int getErrorCode();

    /** 错误描述*/
    String getErrorMsg();
}
