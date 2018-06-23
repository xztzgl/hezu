package com.tangquan.controller;

import com.tangquan.model.request.StatisticReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    StatisticService statisticService;

    @ApiOperation(value = "用户分析菜单")
    @GetMapping("/user")
    public ApiResponse<Map> user() {
        return ApiResponse.ok(statisticService.getUser());
    }

    @ApiOperation(value = "订单分析菜单")
    @GetMapping("/appointment/yesterday")
    public ApiResponse<Map> appointmentYesterday() {
        return ApiResponse.ok(statisticService.appointmentYesterday());
    }

    @ApiOperation(value = "订单分析菜单图表分析")
    @PostMapping("/appointment/statistic")
    public ApiResponse<List> appointmentStatistic(@Validated @RequestBody StatisticReq statisticReq) {
        return ApiResponse.ok(statisticService.appointmentStatistic(statisticReq));
    }

//    @ApiOperation(value = "订单分析菜单")
//    @PostMapping("/appointment")
//    public ApiResponse<Integer> appointment(@Validated @RequestBody OrderRuleReq orderRuleReq) {
//        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
//    }

}
