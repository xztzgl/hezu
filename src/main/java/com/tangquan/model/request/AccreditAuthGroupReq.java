package com.tangquan.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: yangtiansi
 * Date: 2018/4/23
 * Time: 下午8:06
 */
@ApiModel(value = "AccreditAuthGroupReq",description = "授权权限组")
public class AccreditAuthGroupReq {

    @NotNull(message = "ID_EMPTY")
    @ApiModelProperty(value = "ID",required = true)
    private Integer id;

    @ApiModelProperty(value = "权限规则ID \",\"号分割",required = true)
    private String rules;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
