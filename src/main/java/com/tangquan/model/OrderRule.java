package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "OrderRule",description = "菜单规则")
@Entity
@Table(name="tq_auth_rule")
public class OrderRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "菜单ID")
    private Integer id;

    @NotEmpty(message = "ORDER_RULE_NAME_EMPTY")
    @ApiModelProperty(value = "规则名称",required = true)
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "ORDER_RULE_TITLE_EMPTY")
    @ApiModelProperty(value = "菜单标题",required = true)
    @Column(name = "title", nullable = false)
    private String title;

    @ApiModelProperty(value = "状态 1:启用(默认) 0:禁用",required = true)
    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @NotNull(message = "ORDER_RULE_PID_EMPTY")
    @ApiModelProperty(value = "父级ID",required = true)
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Column(name = "sort", nullable = false)
    @ApiModelProperty(value = "排序,用于左侧菜单排序(默认都为1)",required = true)
    private Integer sort = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
