package com.tangquan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "Order",description = "预约")
@Entity
@Table(name="hezu_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "房源信息id",required = true)
    @Column(name = "product_id", nullable = false)
    private String product_id;

    @ApiModelProperty(value = "预约状态对应的id",required = true)
    @Column(name = "status_id", nullable = false)
    private String status_id;

    @ApiModelProperty(value = "客户对应的id",required = true)
    @Column(name = "customer_id", nullable = false)
    private String customer_id;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "create_time", nullable = false)
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "评价即完成",required = true)
    @Column(name = "finish_time", nullable = false)
    private Date finish_time;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "签约时间",required = true)
    @Column(name = "sign_time", nullable = false)
    private Date sign_time;

    @ApiModelProperty(value = "评价",required = true)
    @Column(name = "evaluation", nullable = false)
    private String evaluation;

    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "description", nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public Date getSign_time() {
        return sign_time;
    }

    public void setSign_time(Date sign_time) {
        this.sign_time = sign_time;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
