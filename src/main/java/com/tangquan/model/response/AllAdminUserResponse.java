package com.tangquan.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午6:00
 */
@ApiModel("管理员列表")
public class AllAdminUserResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "登录名",required = true)
    private String loginName;

    @ApiModelProperty(value = "管理员姓名",required = true)
    private String username;

    @ApiModelProperty(value = "用户组",required = true)
    private String groupName;

    @ApiModelProperty(value = "用户组ID")
    private Integer groupId;

    @ApiModelProperty(value = "用户组类别",required = true)
    private short groupType;

    @ApiModelProperty(value = "状态 1:启用 0:禁用",required = true)
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIP;

    @ApiModelProperty(value = "所属银行ID")
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public short getGroupType() {
        return groupType;
    }

    public void setGroupType(short groupType) {
        this.groupType = groupType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
