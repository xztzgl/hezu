package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/21
 * Time: 下午5:56
 */
@ApiModel(value = "UserAuthOrderReq",description = "登录")
public class UserAuthOrderReq {

    @NotNull(message = "UID_EMPTY")
    @ApiModelProperty(value = "用户ID",required = true)
    private Integer uid;

    @NotEmpty(message = "ADDRESS_EMPTY")
    @ApiModelProperty(value = "用户访问地址",required = true)
    private String address;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
