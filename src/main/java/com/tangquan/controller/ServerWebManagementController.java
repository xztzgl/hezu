package com.tangquan.controller;

import com.tangquan.model.Customer;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "后台-管理API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/server-web-management/")
public class ServerWebManagementController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "用户信息管理菜单")
    @GetMapping("/user")
    public ApiResponse<Page<Customer>> user(String username) {
        return ApiResponse.ok(customerService.getCustomerByMobile(username));
    }

//    @ApiOperation(value = "房屋信息管理菜单")
//    @PostMapping("/house")
//    public ApiResponse<Integer> house(@Validated @RequestBody OrderRuleReq orderRuleReq) {
//        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
//    }
//
//    @ApiOperation(value = "找人信息管理菜单")
//    @PostMapping("/partner")
//    public ApiResponse<Integer> partner(@Validated @RequestBody OrderRuleReq orderRuleReq) {
//        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
//    }
//
//    @ApiOperation(value = "通知信息管理菜单")
//    @PostMapping("/notice")
//    public ApiResponse<Integer> notice(@Validated @RequestBody OrderRuleReq orderRuleReq) {
//        return ApiResponse.ok(orderRuleService.add(orderRuleReq));
//    }

}
