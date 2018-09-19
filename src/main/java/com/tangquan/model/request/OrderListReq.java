package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "OrderListReq",description = "订单列表")
public class OrderListReq {


    @ApiModelProperty(value = "每页条数")
    private String limit;

    @ApiModelProperty(value = "第几页")
    private String page;

//    以下为wechat端使用
    @ApiModelProperty(value = "客户对应的id",required = true)
    private String customer_id;

    @ApiModelProperty(value = "订单状态对应的id",required = true)
    private String status_id;


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

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }
}
