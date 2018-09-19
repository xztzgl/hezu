package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "CustomerListReq",description = "用户列表")
public class CustomerListReq {

//    @NotEmpty(message = "ORDER_RULE_NAME_EMPTY")
    @ApiModelProperty(value = "用户名")
    private String search;

    @ApiModelProperty(value = "每页条数")
    private String limit;

    @ApiModelProperty(value = "第几页")
    private String page;

//    @NotEmpty(message = "ORDER_RULE_TITLE_EMPTY")
//    @ApiModelProperty(value = "菜单标题",required = true)
//    private String title;
//
//    @ApiModelProperty(value = "状态 1:启用(默认) 0:禁用",required = true)
//    private Integer status = 1;
//
//    @NotNull(message = "ORDER_RULE_PID_EMPTY")
//    @ApiModelProperty(value = "父级ID",required = true)
//    private Integer pid;
//
//    @ApiModelProperty(value = "图标")
//    private String icon;
//
//    @ApiModelProperty(value = "排序,用于左侧菜单排序(默认都为1)",required = true)
//    private Integer sort = 1;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
