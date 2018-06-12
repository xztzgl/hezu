package com.tangquan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午2:50
 */
@ApiModel(value = "CodeMap",description = "数据字典")
@Entity
@Table(name="hezu_codemap")
public class CodeMap {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @ApiModelProperty(value = "数据字典Code")
//    private Integer code;

//    @NotEmpty(message = "ORDER_RULE_CODE_EMPTY")
    @Id
    @ApiModelProperty(value = "数据字典Code",required = true)
//    @Column(name = "code", nullable = false)
    private Integer code;

//    @NotEmpty(message = "ORDER_RULE_VALUE_EMPTY")
    @ApiModelProperty(value = "数据字典对应的值",required = true)
    @Column(name = "value", nullable = false)
    private String value;

//    @NotNull(message = "ORDER_RULE_PID_EMPTY")
    @ApiModelProperty(value = "父级ID",required = true)
    @Column(name = "pid", nullable = false)
    private Integer pid;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
