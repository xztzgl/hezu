package com.tangquan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "Person",description = "找人信息")
@Entity
@Table(name="hezu_product_person_customer_view")
public class Person {

    @Id
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "账户名-手机号",required = true)
    @Column(name = "username", nullable = false)
    private String username;

    @ApiModelProperty(value = "所在区域的id",required = true)
    @Column(name = "district_id", nullable = false)
    private Integer district_id;

    @ApiModelProperty(value = "合租类型对应的id",required = true)
    @Column(name = "renttype_id", nullable = false)
    private Integer renttype_id;

    @ApiModelProperty(value = "户型对应id",required = true)
    @Column(name = "housetype_id", nullable = false)
    private Integer housetype_id;

    @ApiModelProperty(value = "性别限制 对应性别id",required = true)
    @Column(name = "gender_id", nullable = false)
    private Integer gender_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "入住时间",required = true)
    @Column(name = "checkin_time", nullable = false)
    private Date checkin_time;

    @ApiModelProperty(value = "租金",required = true)
    @Column(name = "rental", nullable = false)
    private Integer rental;

    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "description", nullable = false)
    private String description;

    @ApiModelProperty(value = "发布者对应的id",required = true)
    @Column(name = "creator_id", nullable = false)
    private Integer creator_id;

    @ApiModelProperty(value = "发布状态对应的id",required = true)
    @Column(name = "publish_id", nullable = false)
    private Integer publish_id;

    @ApiModelProperty(value = "信息可见的时间对应的id",required = true)
    @Column(name = "seentime_id", nullable = false)
    private Integer seentime_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "create_time", nullable = false)
    private Date create_time;

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

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getRenttype_id() {
        return renttype_id;
    }

    public void setRenttype_id(Integer renttype_id) {
        this.renttype_id = renttype_id;
    }

    public Integer getGender_id() {
        return gender_id;
    }

    public void setGender_id(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public Date getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(Date checkin_time) {
        this.checkin_time = checkin_time;
    }

    public Integer getRental() {
        return rental;
    }

    public void setRental(Integer rental) {
        this.rental = rental;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public Integer getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(Integer publish_id) {
        this.publish_id = publish_id;
    }

    public Integer getSeentime_id() {
        return seentime_id;
    }

    public void setSeentime_id(Integer seentime_id) {
        this.seentime_id = seentime_id;
    }

    public Integer getHousetype_id() {
        return housetype_id;
    }

    public void setHousetype_id(Integer housetype_id) {
        this.housetype_id = housetype_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
