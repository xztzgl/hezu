package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:59
 */
@ApiModel(value = "AuthGroup",description = "权限组")
@Entity
@Table(name="tq_auth_group")
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @NotEmpty(message = "INVALID_AUTH_GROUP_TITLE")
    @ApiModelProperty(value = "权限组名称",required = true)
    @Column(name = "title",nullable = false)
    private String title;

    @NotNull(message = "INVALID_AUTH_GROUP_STATUS")
    @ApiModelProperty(value = "权限组状态",required = true)
    @Column(name = "status",nullable = false)
    private Integer status;

    @ApiModelProperty(value = "权限组类型  1:超级管理员 2:普通管理员",required = true)
    @Column(name = "group_type",nullable = false)
    private short groupType;

    @ApiModelProperty(value = "权限规则ID \",\"号分割",required = true)
    @Column(name = "rules")
    private String rules;

    public static final int SUPER_ADMIN = 1;//超级管理员
    public static final int COMMON_ADMIN = 2;//超级管理员

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public short getGroupType() {
        return groupType;
    }

    public void setGroupType(short groupType) {
        this.groupType = groupType;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public static int getSuperAdmin() {
        return SUPER_ADMIN;
    }

    public static int getCommonAdmin() {
        return COMMON_ADMIN;
    }
}
