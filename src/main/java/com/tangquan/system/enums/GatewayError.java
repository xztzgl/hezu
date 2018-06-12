package com.tangquan.system.enums;

import com.tangquan.service.Error;

/**
 * Author: wangfeng
 * Date: 17/11/30
 * Time: 下午4:13
 */
public enum GatewayError implements Error {
    BAD_ARGUMENT("10001", "错误的参数: %s"),
    INTERNAL_ERROR("10002", "系统内部错误"),
    BAD_METHOD("10003", "错误的请求方式"),
    NOT_FOUND("10004", "数据不存在"),
    EXEC_FAILED("10005", "执行失败: %s"),
    PWD_IS_NOT_SAME("10006", "密码不一致"),
    USER_NOT_EXIST("10007", "用户不存在"),
    PWD_IS_NOT_RIGHT("10008", "密码不正确"),
    SUPER_ADMIN("10009","不得操作超级管理员用户"),
    SUPER_ADMIN_GROUP("10010","不得操做超级管理员权限组"),
    USER_MUST_LOGIN("10011","请先登陆后操作"),
    AUTH_GROUP_NOT_EXIST("10012","权限组不存在"),
    TOKEN_ERROR("10013","Token非法"),
    TOKEN_EXPIRE("10014","Token已过期");

    private final String errorCode;
    private final String errorMessage;

    GatewayError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
