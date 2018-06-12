package com.tangquan.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tangquan.service.Error;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonSerialize(using = ApiResponseSerializer.class)
@ApiModel("通用响应")
public class ApiResponse<T> {

    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERROR = "error";
    public static final String MESSAGE_SUCCESS = "success";
    @ApiModelProperty("接口状态:ok成功;error:失败")
    private String status;
    @ApiModelProperty("错误码")
    private String errorCode;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("接口返回值")
    private T data;

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(STATUS_OK, "0", MESSAGE_SUCCESS, null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(STATUS_OK, "0", MESSAGE_SUCCESS, data);
    }

    public static <T> ApiResponse<T> error(Error error) {
        return new ApiResponse<>(STATUS_ERROR, error.getErrorCode(), error.getErrorMessage(), null);
    }

    public static <T> ApiResponse<T> error(String errorCode, String message) {
        return new ApiResponse<>(STATUS_ERROR, errorCode, message, null);
    }

    public ApiResponse(String status, String errorCode, String message, T data) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static String getStatusOk() {
        return STATUS_OK;
    }

    public static String getStatusError() {
        return STATUS_ERROR;
    }

    public static String getMessageSuccess() {
        return MESSAGE_SUCCESS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
