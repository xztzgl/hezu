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
@ApiModel(value = "Notice",description = "消息")
@Entity
@Table(name="hezu_message")
public class AddNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "客户对应id",required = true)
    @Column(name = "customer_id", nullable = false)
    private Integer customer_id;

    @ApiModelProperty(value = "发送方式",required = true)
    @Column(name = "method", nullable = false)
    private Integer method;

    @ApiModelProperty(value = "发送类别对应id",required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @ApiModelProperty(value = "关联产品id",required = true)
    @Column(name = "product_id", nullable = false)
    private Integer product_id;

    @ApiModelProperty(value = "内容",required = true)
    @Column(name = "text", nullable = false)
    private String text;

    @ApiModelProperty(value = "关联产品类别",required = true)
    @Column(name = "product_type", nullable = false)
    private Integer product_type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "create_time", nullable = false)
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
