package com.tangquan.system.handler;

import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.enums.ValidationError;
import com.tangquan.system.exception.ApiException;
import com.tangquan.model.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Description:错误异常捕获
 * Author: wangfeng
 * Date: 17/11/29
 * Time: 上午11:00
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<Void> handler(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            FieldError fe = manve.getBindingResult().getFieldError();
            if (fe != null) {
                ValidationError ve = ValidationError.find(fe.getDefaultMessage());
                return ApiResponse.error(ve.getErrorCode(), ve.getErrorMessage());
            } else {
                ObjectError ge = manve.getBindingResult().getGlobalError();
                logger.warn("Programming error:{} cannot convert err code:{} ",fe.getDefaultMessage(), ex);
                return ApiResponse.error(GatewayError.BAD_ARGUMENT.getErrorCode(),
                        "Object " + ge.getObjectName() + " is invalid.");
            }
        }else if (ex instanceof ApiException) {
            ApiException ae = (ApiException) ex;
            logger.warn("Handler error message:{}",ex);
            return ApiResponse.error(ae);
        }else if(ex instanceof HttpRequestMethodNotSupportedException) {
            logger.warn("Request method not supported{}", ex);
            return ApiResponse.error(GatewayError.BAD_METHOD);
        }else {
            logger.error("Handler not expected exception{}", ex);
            return ApiResponse.error(GatewayError.INTERNAL_ERROR);
        }
    }
}
