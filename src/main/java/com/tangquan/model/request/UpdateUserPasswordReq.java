package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/14
 * Time: 下午4:07
 */
@ApiModel("修改用户密码")
public class UpdateUserPasswordReq {

    @NotNull(message = "UID_EMPTY")
    @ApiModelProperty("uid")
    private Integer uid;

    @NotEmpty(message = "OLD_PWD_EMPTY")
    @ApiModelProperty("旧密码")
    private String oldPassword;

    @NotEmpty(message = "NEW_PWD_EMPTY")
    @ApiModelProperty("新密码")
    private String newPassword;

    @NotEmpty(message = "CONFIRM_NEW_PWD_EMPTY")
    @ApiModelProperty("确认密码")
    private String confirmPassword;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
