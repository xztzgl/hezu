package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "PersonListReq",description = "找人列表")
public class PersonListReq {

//    @NotEmpty(message = "ORDER_RULE_NAME_EMPTY")
//    @ApiModelProperty(value = "用户名")
//    private String search;

    @ApiModelProperty(value = "每页条数")
    private String limit;

    @ApiModelProperty(value = "第几页")
    private String page;

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
