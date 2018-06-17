package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "NoticeReq",description = "通知信息")
public class NoticeReq {


    @ApiModelProperty(value = "客户对应id",required = true)
    private Integer customer_id;


    @ApiModelProperty(value = "发送方式",required = true)
    private Integer method;


    @ApiModelProperty(value = "发送类别对应id",required = true)
    private Integer type;


    @ApiModelProperty(value = "关联产品id",required = true)
    private Integer product_id;


    @ApiModelProperty(value = "内容",required = true)
    private String text;


    @ApiModelProperty(value = "关联产品类别",required = true)
    private Integer product_type;


    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }
}
