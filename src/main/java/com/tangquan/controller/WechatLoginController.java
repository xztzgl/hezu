package com.tangquan.controller;

import com.tangquan.model.request.AddCustomerReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "微信端-登录", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-login/")
public class WechatLoginController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "获取验证码")
    @GetMapping("/getCode/{username}")
    public ApiResponse<Map> user(@ApiParam(value = "用户名", required = true) @PathVariable String username) {
        return ApiResponse.ok(customerService.getCode(username));

    }


//    @Autowired
//    HouseService houseService;
//
//    @ApiOperation(value = "登录")
//    @PostMapping("/login")
//    public ApiResponse<Page<House>> house(@Validated @RequestBody HouseListReq houseListReq) {
//        return ApiResponse.ok(houseService.getAllHouse(houseListReq));
//    }


    @ApiOperation(value = "注册")
    @PostMapping("/sign")
    public ApiResponse<Map> partner(@Validated @RequestBody AddCustomerReq addCustomerReq) {
        return ApiResponse.ok(customerService.addCustomer(addCustomerReq));
    }


}
