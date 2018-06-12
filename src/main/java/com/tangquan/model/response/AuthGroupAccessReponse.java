package com.tangquan.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/14
 * Time: 下午4:38
 */
@ApiModel("权限组授权列表")
public class AuthGroupAccessReponse {

//    @ApiModelProperty(value = "id",required = true)
//    private Integer id;
//
//    @ApiModelProperty(value = "父节点ID",required = true)
//    private Integer pid;
//
//    @ApiModelProperty(value = "菜单名称",required = true)
//    private String title;
//
//    @ApiModelProperty(value = "是否选中",required = true)
//    private Boolean checked;

    @ApiModelProperty(value = "菜单ID")
    private Integer id;

    @ApiModelProperty(value = "是否选中",required = true)
    private Boolean checked;

    @ApiModelProperty(value = "规则名称",required = true)
    private String name;

    @ApiModelProperty(value = "菜单标题",required = true)
    private String title;

    @ApiModelProperty(value = "状态 1:启用(默认) 0:禁用",required = true)
    private Integer status;

    @ApiModelProperty(value = "父级ID",required = true)
    private Integer pid;

    @ApiModelProperty(value = "图标",required = true)
    private String icon;

    @ApiModelProperty(value = "排序,用于左侧菜单排序(默认都为1)",required = true)
    private Integer sort;

    @ApiModelProperty(value = "子菜单",required = true)
    private List<AuthGroupAccessReponse> children;

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

    public List<AuthGroupAccessReponse> getChildren() {
        return children;
    }

    public void setChildren(List<AuthGroupAccessReponse> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
