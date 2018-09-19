package com.tangquan.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 上午10:27
 */
@Table(name="tq_admin_login_log")
@Entity
public class AdminLoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID",required = true)
    @Column(name = "uid", nullable = false)
    private Integer uid;

    @ApiModelProperty(value = "最后登录时间",required = true)
    @Column(name = "last_login_time", nullable = false)
    private Date lastLoginTime;

    @ApiModelProperty(value = "最后登录IP",required = true)
    @Column(name = "last_login_ip", nullable = false)
    private String lastLoginIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}
