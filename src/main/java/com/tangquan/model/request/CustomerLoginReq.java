package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:17
 */
@ApiModel(value = "CustomerLoginReq",description = "前台用户登录")
public class CustomerLoginReq {

    @ApiModelProperty(value = "登录名",required = true)
    private String username;

    @ApiModelProperty(value = "验证码",required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
