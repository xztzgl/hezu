package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "District",description = "地区数据")
@Entity
@Table(name="hezu_codemap_district")
public class District {

    @Id
    @ApiModelProperty(value = "地区Code",required = true)
    private Integer disp_local_id;

    @ApiModelProperty(value = "地区Code对应的值",required = true)
    @Column(name = "local_name", nullable = false)
    private String local_name;

    @ApiModelProperty(value = "父级ID",required = true)
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @ApiModelProperty(value = "排序",required = true)
    @Column(name = "order", nullable = false)
    private Integer order;

    public Integer getDisp_local_id() {
        return disp_local_id;
    }

    public void setDisp_local_id(Integer disp_local_id) {
        this.disp_local_id = disp_local_id;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
