package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "Favorite",description = "收藏")
@Entity
@Table(name="hezu_customer_favorite")
public class Favorite {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;


    @ApiModelProperty(value = "商品类型id  1-房源house、2-人person",required = true)
    @Column(name = "product_type", nullable = false)
    private String product_type;

    @Id
    @ApiModelProperty(value = "商品对应的id",required = true)
    @Column(name = "product_id", nullable = false)
    private String product_id;

    @ApiModelProperty(value = "客户对应的id",required = true)
    @Column(name = "customer_id", nullable = false)
    private String customer_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
