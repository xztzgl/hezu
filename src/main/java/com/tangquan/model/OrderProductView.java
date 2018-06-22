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
@ApiModel(value = "OrderProductView",description = "订单列表试图")
@Entity
@Table(name="hezu_order_product_view")
public class OrderProductView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "房源信息id",required = true)
    @Column(name = "product_id", nullable = false)
    private String product_id;

    @ApiModelProperty(value = "预约状态对应的id",required = true)
    @Column(name = "status_id", nullable = false)
    private String status_id;

    @ApiModelProperty(value = "客户对应的id",required = true)
    @Column(name = "customer_id", nullable = false)
    private String customer_id;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "create_time", nullable = false)
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "评价即完成",required = true)
    @Column(name = "finish_time", nullable = false)
    private Date finish_time;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "签约时间",required = true)
    @Column(name = "sign_time", nullable = false)
    private Date sign_time;

    @ApiModelProperty(value = "评价",required = true)
    @Column(name = "evaluation", nullable = false)
    private String evaluation;

    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "description", nullable = false)
    private String description;


    @ApiModelProperty(value = "上传图片对应的id，用“,”分隔",required = true)
    @Column(name = "house_image_id", nullable = false)
    private String house_image_id;


    @ApiModelProperty(value = "发布房源信息的标题",required = true)
    @Column(name = "house_title", nullable = false)
    private String house_title;

    @ApiModelProperty(value = "所在区域的id",required = true)
    @Column(name = "house_district_id", nullable = false)
    private Integer house_district_id;

    @ApiModelProperty(value = "小区名称",required = true)
    @Column(name = "house_name", nullable = false)
    private String house_name;

    @ApiModelProperty(value = "合租类型对应的id",required = true)
    @Column(name = "house_renttype_id", nullable = false)
    private Integer house_renttype_id;

    @ApiModelProperty(value = "楼层-第几层",required = true)
    @Column(name = "house_floor_layer", nullable = false)
    private Integer house_floor_layer;

    @ApiModelProperty(value = "楼层-总层数",required = true)
    @Column(name = "house_floor_total", nullable = false)
    private Integer house_floor_total;

    @ApiModelProperty(value = "建筑面积",required = true)
    @Column(name = "house_built_area", nullable = false)
    private Integer house_built_area;

    @ApiModelProperty(value = "租金",required = true)
    @Column(name = "house_rental", nullable = false)
    private Integer house_rental;

    @ApiModelProperty(value = "配套设施对应的id",required = true)
    @Column(name = "house_infrastructure_id", nullable = false)
    private String house_infrastructure_id;

    @ApiModelProperty(value = "户型对应id",required = true)
    @Column(name = "house_housetype_id", nullable = false)
    private Integer house_housetype_id;

    @ApiModelProperty(value = "性别限制 对应性别id",required = true)
    @Column(name = "house_gender_id", nullable = false)
    private Integer house_gender_id;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "入住时间",required = true)
    @Column(name = "house_checkin_time", nullable = false)
    private Date house_checkin_time;

    @ApiModelProperty(value = "朝向对应id",required = true)
    @Column(name = "house_orientation_id", nullable = false)
    private String house_orientation_id;

    @ApiModelProperty(value = "装修对应的id",required = true)
    @Column(name = "house_decoration_id", nullable = false)
    private Integer house_decoration_id;

    @ApiModelProperty(value = "支付方式对应id",required = true)
    @Column(name = "house_payment_id", nullable = false)
    private Integer house_payment_id;

    @ApiModelProperty(value = "信息可见的时间对应的id",required = true)
    @Column(name = "house_seentime_id", nullable = false)
    private Integer house_seentime_id;

    @ApiModelProperty(value = "描述",required = true)
    @Column(name = "house_description", nullable = false)
    private String house_description;

    @ApiModelProperty(value = "发布者对应的id",required = true)
    @Column(name = "house_creator_id", nullable = false)
    private Integer house_creator_id;

    @ApiModelProperty(value = "发布状态对应的id",required = true)
    @Column(name = "house_publish_id", nullable = false)
    private Integer house_publish_id;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "GMT + 8")
    @ApiModelProperty(value = "创建时间",required = true)
    @Column(name = "house_create_time", nullable = false)
    private Date house_create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public Date getSign_time() {
        return sign_time;
    }

    public void setSign_time(Date sign_time) {
        this.sign_time = sign_time;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHouse_image_id() {
        return house_image_id;
    }

    public void setHouse_image_id(String house_image_id) {
        this.house_image_id = house_image_id;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

    public Integer getHouse_district_id() {
        return house_district_id;
    }

    public void setHouse_district_id(Integer house_district_id) {
        this.house_district_id = house_district_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public Integer getHouse_renttype_id() {
        return house_renttype_id;
    }

    public void setHouse_renttype_id(Integer house_renttype_id) {
        this.house_renttype_id = house_renttype_id;
    }

    public Integer getHouse_floor_layer() {
        return house_floor_layer;
    }

    public void setHouse_floor_layer(Integer house_floor_layer) {
        this.house_floor_layer = house_floor_layer;
    }

    public Integer getHouse_floor_total() {
        return house_floor_total;
    }

    public void setHouse_floor_total(Integer house_floor_total) {
        this.house_floor_total = house_floor_total;
    }

    public Integer getHouse_built_area() {
        return house_built_area;
    }

    public void setHouse_built_area(Integer house_built_area) {
        this.house_built_area = house_built_area;
    }

    public Integer getHouse_rental() {
        return house_rental;
    }

    public void setHouse_rental(Integer house_rental) {
        this.house_rental = house_rental;
    }

    public String getHouse_infrastructure_id() {
        return house_infrastructure_id;
    }

    public void setHouse_infrastructure_id(String house_infrastructure_id) {
        this.house_infrastructure_id = house_infrastructure_id;
    }

    public Integer getHouse_housetype_id() {
        return house_housetype_id;
    }

    public void setHouse_housetype_id(Integer house_housetype_id) {
        this.house_housetype_id = house_housetype_id;
    }

    public Integer getHouse_gender_id() {
        return house_gender_id;
    }

    public void setHouse_gender_id(Integer house_gender_id) {
        this.house_gender_id = house_gender_id;
    }

    public Date getHouse_checkin_time() {
        return house_checkin_time;
    }

    public void setHouse_checkin_time(Date house_checkin_time) {
        this.house_checkin_time = house_checkin_time;
    }

    public String getHouse_orientation_id() {
        return house_orientation_id;
    }

    public void setHouse_orientation_id(String house_orientation_id) {
        this.house_orientation_id = house_orientation_id;
    }

    public Integer getHouse_decoration_id() {
        return house_decoration_id;
    }

    public void setHouse_decoration_id(Integer house_decoration_id) {
        this.house_decoration_id = house_decoration_id;
    }

    public Integer getHouse_payment_id() {
        return house_payment_id;
    }

    public void setHouse_payment_id(Integer house_payment_id) {
        this.house_payment_id = house_payment_id;
    }

    public Integer getHouse_seentime_id() {
        return house_seentime_id;
    }

    public void setHouse_seentime_id(Integer house_seentime_id) {
        this.house_seentime_id = house_seentime_id;
    }

    public String getHouse_description() {
        return house_description;
    }

    public void setHouse_description(String house_description) {
        this.house_description = house_description;
    }

    public Integer getHouse_creator_id() {
        return house_creator_id;
    }

    public void setHouse_creator_id(Integer house_creator_id) {
        this.house_creator_id = house_creator_id;
    }

    public Integer getHouse_publish_id() {
        return house_publish_id;
    }

    public void setHouse_publish_id(Integer house_publish_id) {
        this.house_publish_id = house_publish_id;
    }

    public Date getHouse_create_time() {
        return house_create_time;
    }

    public void setHouse_create_time(Date house_create_time) {
        this.house_create_time = house_create_time;
    }
}
