package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:14
 */
@Entity
@Table(name="tq_admin_user_bank")
@ApiModel(value = "AdminUserBank",description = "管理员和银行对应关系")
public class AdminUserBank {

    @Id
    @ApiModelProperty(value = "用户ID")
    @Column(name = "uid",nullable=false)
    private Integer uid;

//    @ApiModelProperty(value = "银行机构编码(多级,\"|\"分隔)")
//    @Column(name = "bank_ids",nullable=false)
//    private String bankIds;

    @ApiModelProperty(value = "银行机构编码")
    @Column(name = "bank_id",nullable=false)
    private String bankId;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getBankId() {
        return bankId;
    }

//    public void setBankId(String bankId) {
//        this.bankId = bankId;
//    }
    public void setBankId(String bankId) {
        this.bankId = "B0002K232010001";
    }
}
