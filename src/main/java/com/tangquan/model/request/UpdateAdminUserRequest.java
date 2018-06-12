package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 上午11:27
 */
@ApiModel("管理员用户请求")
public class UpdateAdminUserRequest {

    @NotNull(message = "ID_EMPTY")
    @ApiModelProperty(value = "ID",required = true)
    private Integer id;

    @ApiModelProperty(value = "登录名",required = true)
    private String loginName;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty("密码,选填")
    private String password;

    @ApiModelProperty("确认密码,选填")
    private String confirmPassword;

    @ApiModelProperty(value = "状态 0:禁用 1:启用",required = true)
    private Integer status;

    @ApiModelProperty(value = "所属权限组",required = true)
    private Integer groupId;

    @ApiModelProperty(value = "机构名称",required = true)
    private String bankId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
