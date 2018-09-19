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

    //    以下为wechat端使用
    @ApiModelProperty(value = "按片区")
    private String district_id;

    @ApiModelProperty(value = "按户型")
    private String housetype_id;

    @ApiModelProperty(value = "按价格")
    private String rental_id;

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

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getHousetype_id() {
        return housetype_id;
    }

    public void setHousetype_id(String housetype_id) {
        this.housetype_id = housetype_id;
    }

    public String getRental_id() {
        return rental_id;
    }

    public void setRental_id(String rental_id) {
        this.rental_id = rental_id;
    }
}
