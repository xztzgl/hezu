package com.tangquan.model.response;

import com.tangquan.model.OrderRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午3:50
 */
@ApiModel("返回用户信息")
public class AdminUserResponse {

    @ApiModelProperty(value = "用户ID",required = true)
    private Integer id;

    @ApiModelProperty(value = "登录名",required = true)
    private String loginName;

    @ApiModelProperty(value = "管理员姓名",required = true)
    private String username;

    @ApiModelProperty(value = "状态 1:启用 0:禁用",required = true)
    private Integer status;

    @ApiModelProperty(value = "所属银行ID")
    private String bankId;

    @ApiModelProperty(value = "所属权限组")
    private Integer groupId;

//    @ApiModelProperty(value = "所属银行ID(多级)")
//    private String bankIds;

    @ApiModelProperty(value = "用户token",required = true)
    private String token;

    @ApiModelProperty(value = "菜单列表")
    private List<OrderRule> orderRuleList;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

//    public String getBankIds() {
//        return bankIds;
//    }
//
//    public void setBankIds(String bankIds) {
//        this.bankIds = bankIds;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<OrderRule> getOrderRuleList() {
        return orderRuleList;
    }

    public void setOrderRuleList(List<OrderRule> orderRuleList) {
        this.orderRuleList = orderRuleList;
    }
}

