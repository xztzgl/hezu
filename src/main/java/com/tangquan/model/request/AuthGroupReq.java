package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/21
 * Time: 上午10:18
 */
@ApiModel(value = "AuthGroupReq",description = "权限组")
public class AuthGroupReq {

    @NotEmpty(message = "INVALID_AUTH_GROUP_TITLE")
    @ApiModelProperty(value = "权限组名称",required = true)
    private String title;

    @NotNull(message = "INVALID_AUTH_GROUP_STATUS")
    @ApiModelProperty(value = "权限组状态",required = true)
    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
