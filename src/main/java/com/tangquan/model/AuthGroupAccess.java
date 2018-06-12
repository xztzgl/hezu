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
 * Time: 下午2:41
 */
@Entity
@Table(name="tq_auth_group_access")
@ApiModel(value = "AuthGroupAccess",description = "管理与权限关系表")
public class AuthGroupAccess {

    @Id
    @ApiModelProperty(value = "用户ID",required = true)
    @Column(name = "uid", nullable = false)
    private Integer uid;

    @ApiModelProperty(value = "权限组ID",required = true)
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
