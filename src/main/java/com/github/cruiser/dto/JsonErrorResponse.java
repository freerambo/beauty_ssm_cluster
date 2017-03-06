package com.github.cruiser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 
 * @author yingjun
 *
 * ajax 请求的返回类型封装JSON结果
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonErrorResponse<T> implements Serializable {


    private static final long serialVersionUID = -4185151304730685014L;

    private String exceptionCode;

    private T exceptionBody;

    public JsonErrorResponse(String code, T msg) {
        this.exceptionCode = code;
        this.exceptionBody = msg;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public T getExceptionBody() {
        return exceptionBody;
    }

    public void setExceptionBody(T exceptionBody) {
        this.exceptionBody = exceptionBody;
    }

    @Override
    public String toString() {
        return "BaseResult [exceptionCode: " + exceptionCode
                +", exceptionBody: " + exceptionBody + "]";
    }

}
