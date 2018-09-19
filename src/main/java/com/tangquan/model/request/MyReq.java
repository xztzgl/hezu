package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "MyReq",description = "我的首页")
public class MyReq {


//    @ApiModelProperty(value = "每页条数")
//    private String limit;
//
//    @ApiModelProperty(value = "第几页")
//    private String page;

//    以下为wechat端使用
    @ApiModelProperty(value = "客户对应的id",required = true)
    private String customer_id;


    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
