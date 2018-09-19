package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "NoticeListReq",description = "消息列表")
public class NoticeListReq {

    @ApiModelProperty(value = "每页条数")
    private String limit;

    @ApiModelProperty(value = "第几页")
    private String page;

    //    以下为wechat端使用
    @ApiModelProperty(value = "客户对应的id",required = true)
    private String customer_id;

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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
