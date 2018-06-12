package com.tangquan.controller;

import com.tangquan.model.request.OrderRuleReq;
import com.tangquan.model.request.UpdateOrderRuleReq;
import com.tangquan.model.response.AllOrderRuleResponse;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.model.response.OrderRuleResponse;
import com.tangquan.service.OrderRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:50
 */
@RestController
@Api(description = "菜单管理API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/order-manage/")
public class OrderRuleController {

    @Autowired
    OrderRuleService orderRuleService;

    @ApiOperation(value = "添加菜单")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@Validated @RequestBody OrderRuleReq orderRuleReq) {
        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
    }

    @ApiOperation(value = "获取所有菜单列表")
    @GetMapping("/get-all-orders/{groupId}")
    public ApiResponse<List<AllOrderRuleResponse>> getAllOrders(@ApiParam(value = "所属权限组ID",required = true) @PathVariable Integer groupId) {
        return ApiResponse.ok(orderRuleService.getAllOrderRule(groupId));
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/del/{id}")
    public ApiResponse<Integer> delOrder(@ApiParam(value = "菜单ID",required = true) @PathVariable Integer id) {
        return ApiResponse.ok(orderRuleService.delete(id));
    }

    @ApiOperation(value = "编辑菜单")
    @PutMapping("/update")
    public ApiResponse<Integer> update(@Validated @RequestBody UpdateOrderRuleReq updateOrderRuleReq) {
        return ApiResponse.ok(orderRuleService.update(updateOrderRuleReq));
    }

    @ApiOperation(value = "获取菜单")
    @GetMapping("/get/{id}")
    public ApiResponse<OrderRuleResponse> get(@ApiParam(value = "菜单ID",required = true) @PathVariable Integer id) {
        return ApiResponse.ok(orderRuleService.get(id));
    }
}
