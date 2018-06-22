package com.tangquan.controller;

import com.tangquan.model.Evaluate;
import com.tangquan.model.OrderProductView;
import com.tangquan.model.request.OrderListReq;
import com.tangquan.model.request.OrderReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.HouseService;
import com.tangquan.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "微信端-订单", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-order/")
public class WechatOrderController {


    @Autowired
    OrderService orderService;

    @ApiOperation(value = "我的订单列表")
    @PostMapping("/list")
    public ApiResponse<Page<OrderProductView>> list(@Validated @RequestBody OrderListReq orderListReq) {
        return ApiResponse.ok(orderService.getAllOrderBySearch(orderListReq));

    }

    @Autowired
    HouseService houseService;

    @ApiOperation(value = "预约")
    @PostMapping("/order")
    public ApiResponse<Map> order(@Validated @RequestBody OrderReq orderReq) {
        return ApiResponse.ok(houseService.order(orderReq));
    }

    @ApiOperation(value = "评价")
    @PostMapping("/evaluate")
    public ApiResponse<Map> evaluate(@Validated @RequestBody Evaluate evaluate) {
        return ApiResponse.ok(houseService.evaluate(evaluate));
    }




}
