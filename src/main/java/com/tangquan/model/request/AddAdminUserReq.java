package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:17
 */
@ApiModel(value = "AddAdminUserReq",description = "添加管理员")
public class AddAdminUserReq {

    @NotEmpty(message = "INVALID_LOGIN_NAME")
    @ApiModelProperty(value = "登录名",required = true)
    private String loginName;

    @NotEmpty(message = "INVALID_USERNAME")
    @ApiModelProperty(value = "管理员姓名",required = true)
    private String username;

    @NotEmpty(message = "INVALID_PWD")
    @ApiModelProperty(value = "管理员密码",required = true)
    private String password;

    @NotEmpty(message = "CONFIRM_NEW_PWD_EMPTY")
    @ApiModelProperty(value="确认密码",required = true)
    private String confirmPassword;

    @NotNull(message = "INVALID_STATUS")
    @ApiModelProperty(value = "状态 1:启用 0:禁用",required = true)
    private Integer status;

    @NotNull(message = "INVALID_GROUP")
    @ApiModelProperty(value = "所属权限组",required = true)
    private Integer groupId;

    @NotEmpty(message = "INVALID_ORGS_NO")
    @ApiModelProperty(value = "银行机构编码(多级,\"|\"分隔)")
//    private String bankId;

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

//    public String getBankId() {
//        return bankId;
//    }

//    public void setBankId(String bankId) {
//        this.bankId = bankId;
//    }
}
