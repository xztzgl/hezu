package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午4:33
 */
@Entity
@Table(name="tq_admin_user")
@ApiModel(value = "AdminUser",description = "管理员")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @NotEmpty(message = "INVALID_LOGIN_NAME")
    @ApiModelProperty(value = "登录名",required = true)
    @Column(name = "login_name", nullable = false)
    private String loginName;

    @NotEmpty(message = "INVALID_USERNAME")
    @ApiModelProperty(value = "管理员姓名",required = true)
    @Column(name = "user_name", nullable = false)
    private String username;

    @NotEmpty(message = "INVALID_PWD")
    @ApiModelProperty(value = "管理员密码",required = true)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "INVALID_STATUS")
    @ApiModelProperty(value = "状态 1:启用 0:禁用",required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
