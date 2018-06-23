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
@ApiModel(value = "AddHouse",description = "房屋信息")
@Entity
@Table(name="hezu_product_house")
public class AddHouse {

    @Id
    @ApiModelProperty(value = "id")
    private Integer id;


    @ApiModelProperty(value = "上传图片对应的id，用“,”分隔",required = true)
    @Column(name = "image_id", nullable = false)
    private String image_id;

    @ApiModelProperty(value = "发布房源信息的标题",required = true)
    @Column(name = "title", nullable = false)
    private String title;

    @ApiModelProperty(value = "所在区域的id",required = true)
    @Column(name = "district_id", nullable = false)
    private String district_id;

    @ApiModelProperty(value = "小区名称",required = true)
    @Column(name = "house_name", nullable = false)
    private String house_name;

    @ApiModelProperty(value = "合租类型对应的id",required = true)
    @Column(name = "renttype_id", nullable = false)
    private String renttype_id;

    @ApiModelProperty(value = "楼层-第几层",required = true)
    @Column(name = "floor_layer", nullable = false)
    private String floor_layer;

    @ApiModelProperty(value = "楼层-总层数",required = true)
    @Column(name = "floor_total", nullable = false)
    private String floor_total;

    @ApiModelProperty(value = "建筑面积",required = true)
    @Column(name = "built_area", nullable = false)
    private String built_area;

    @ApiModelProperty(value = "租金",required = true)
    @Column(name = "rental", nullable = false)
    private String rental;

    @ApiModelProperty(value = "配套设施对应的id",required = true)
    @Column(name = "infrastructure_id", nullable = false)
    private String infrastructure_id;

    @ApiModelProperty(value = "户型对应id",required = true)
    @Column(name = "housetype_id", nullable = false)
    private String housetype_id;

    @ApiModelProperty(value = "性别限制 对应性别id",required = true)
    @Column(name = "gender_id", nullable = false)
    private String gender_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "入住时间",required = true)
    @Column(name = "checkin_time", nullable = false)
    private Date checkin_time;

    @ApiModelProperty(value = "朝向对应id",required = true)
    @Column(name = "orientation_id", nullable = false)
    private String orientation_id;

    @ApiModelProperty(value = "装修对应的id",required = true)
    @Column(name = "decoration_id", nullable = false)
    private String decoration_id;

    @ApiModelProperty(value = "支付方式对应id",required = true)
    @Column(name = "payment_id", nullable = false)
    private String payment_id;

    @ApiModelProperty(value = "信息可见的时间对应的id",required = true)
    @Column(name = "seentime_id", nullable = false)
    private String seentime_id;

    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "description", nullable = false)
    private String description;

    @ApiModelProperty(value = "发布者对应的id",required = true)
    @Column(name = "creator_id", nullable = false)
    private String creator_id;

    @ApiModelProperty(value = "发布状态对应的id",required = true)
    @Column(name = "publish_id", nullable = false)
    private String publish_id;

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

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getRenttype_id() {
        return renttype_id;
    }

    public void setRenttype_id(String renttype_id) {
        this.renttype_id = renttype_id;
    }

    public String getFloor_layer() {
        return floor_layer;
    }

    public void setFloor_layer(String floor_layer) {
        this.floor_layer = floor_layer;
    }

    public String getFloor_total() {
        return floor_total;
    }

    public void setFloor_total(String floor_total) {
        this.floor_total = floor_total;
    }

    public String getBuilt_area() {
        return built_area;
    }

    public void setBuilt_area(String built_area) {
        this.built_area = built_area;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getInfrastructure_id() {
        return infrastructure_id;
    }

    public void setInfrastructure_id(String infrastructure_id) {
        this.infrastructure_id = infrastructure_id;
    }

    public String getHousetype_id() {
        return housetype_id;
    }

    public void setHousetype_id(String housetype_id) {
        this.housetype_id = housetype_id;
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

    public String getOrientation_id() {
        return orientation_id;
    }

    public void setOrientation_id(String orientation_id) {
        this.orientation_id = orientation_id;
    }

    public String getDecoration_id() {
        return decoration_id;
    }

    public void setDecoration_id(String decoration_id) {
        this.decoration_id = decoration_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getSeentime_id() {
        return seentime_id;
    }

    public void setSeentime_id(String seentime_id) {
        this.seentime_id = seentime_id;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
