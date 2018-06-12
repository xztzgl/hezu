package com.tangquan.controller;

import com.tangquan.model.request.OrderRuleReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.OrderRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "后台-统计API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/server-web-statistic/")
public class ServerWebStatisticController {

    @Autowired
    OrderRuleService orderRuleService;

    @ApiOperation(value = "用户分析菜单")
    @PostMapping("/user")
    public ApiResponse<Integer> user(@Validated @RequestBody OrderRuleReq orderRuleReq) {
        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
    }

    @ApiOperation(value = "订单分析菜单")
    @PostMapping("/appointment")
    public ApiResponse<Integer> appointment(@Validated @RequestBody OrderRuleReq orderRuleReq) {
        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
    }

}
