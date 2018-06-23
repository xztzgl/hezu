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
@ApiModel(value = "AddPerson",description = "合租者信息")
@Entity
@Table(name="hezu_product_person")
public class AddPerson {

    @Id
    @ApiModelProperty(value = "id")
    private Integer id;


    @ApiModelProperty(value = "所在区域的id",required = true)
    @Column(name = "district_id", nullable = false)
    private String district_id;


    @ApiModelProperty(value = "合租类型对应的id",required = true)
    @Column(name = "renttype_id", nullable = false)
    private String renttype_id;

    @ApiModelProperty(value = "性别限制 对应性别id",required = true)
    @Column(name = "gender_id", nullable = false)
    private String gender_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "入住时间",required = true)
    @Column(name = "checkin_time", nullable = false)
    private Date checkin_time;


    @ApiModelProperty(value = "租金",required = true)
    @Column(name = "rental", nullable = false)
    private String rental;


    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "description", nullable = false)
    private String description;

    @ApiModelProperty(value = "发布者对应的id",required = true)
    @Column(name = "creator_id", nullable = false)
    private String creator_id;

    @ApiModelProperty(value = "户型对应id",required = true)
    @Column(name = "housetype_id", nullable = false)
    private Integer housetype_id;

    @ApiModelProperty(value = "发布状态对应的id",required = true)
    @Column(name = "publish_id", nullable = false)
    private String publish_id;

    @ApiModelProperty(value = "信息可见的时间对应的id",required = true)
    @Column(name = "seentime_id", nullable = false)
    private String seentime_id;

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

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getRenttype_id() {
        return renttype_id;
    }

    public void setRenttype_id(String renttype_id) {
        this.renttype_id = renttype_id;
    }

    public String getGender_id() {
        return gender_id;
    }

    public void setGender_id(String gender_id) {
        this.gender_id = gender_id;
    }

    public Date getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(Date checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(String publish_id) {
        this.publish_id = publish_id;
    }

    public String getSeentime_id() {
        return seentime_id;
    }

    public void setSeentime_id(String seentime_id) {
        this.seentime_id = seentime_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getHousetype_id() {
        return housetype_id;
    }

    public void setHousetype_id(Integer housetype_id) {
        this.housetype_id = housetype_id;
    }
}
