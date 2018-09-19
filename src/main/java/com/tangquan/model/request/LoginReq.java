package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author: wangfeng
 * Date: 17/12/21
 * Time: 下午5:46
 */
@ApiModel(value = "LoginReq",description = "登录")
public class LoginReq {

    @NotEmpty(message = "INVALID_USERNAME")
    @ApiModelProperty(value = "登录名",required = true)
    private String loginName;

    @NotEmpty(message = "INVALID_PWD")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
