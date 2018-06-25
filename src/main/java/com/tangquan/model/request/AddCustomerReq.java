package com.tangquan.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:17
 */
@ApiModel(value = "AddCustomerReq",description = "添加前台用户")
public class AddCustomerReq {

    @Id
    @ApiModelProperty(value = "id")
    private Integer id;

    @NotEmpty(message = "INVALID_USERNAME")
    @ApiModelProperty(value = "登录名",required = true)
    private String username;

    @NotNull(message = "INVALID_STATUS")
    @ApiModelProperty(value = "性别：1 - 男  0 - 女",required = true)
    private String gender;

    @NotNull(message = "INVALID_GROUP")
    @ApiModelProperty(value = "出生年份",required = true)
    private String birth_year;

    @NotNull(message = "INVALID_GROUP")
    @ApiModelProperty(value = "职业",required = true)
    private String vocation;

    @NotNull(message = "INVALID_GROUP")
    @ApiModelProperty(value = "所在区域",required = true)
    private String district;

//    @NotNull(message = "INVALID_GROUP")
//    @ApiModelProperty(value = "昵称-微信获取",required = true)
//    private String name;
//
//    @NotNull(message = "INVALID_GROUP")
//    @ApiModelProperty(value = "积分",required = true)
//    private String score;
//
//    @NotNull(message = "INVALID_GROUP")
//    @ApiModelProperty(value = "头像-微信获取",required = true)
//    private String avatar;
//
//    @NotNull(message = "INVALID_GROUP")
//    @ApiModelProperty(value = "微信用户名",required = true)
//    private String weichat_name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "create_time", nullable = false)
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "最近一次登录时间",required = true)
    @Column(name = "update_time", nullable = false)
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
