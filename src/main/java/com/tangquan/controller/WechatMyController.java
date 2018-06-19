package com.tangquan.controller;

import com.tangquan.model.Customer;
import com.tangquan.model.House;
import com.tangquan.model.request.CustomerListReq;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CustomerService;
import com.tangquan.service.HouseService;
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

import java.util.Objects;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "微信端-我的", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-my/")
public class WechatMyController {


    @Autowired
    HouseService houseService;

    @ApiOperation(value = "我的首页")
    @PostMapping("/customer")
    public ApiResponse<Page<House>> customer(@Validated @RequestBody HouseListReq houseListReq) {
        return ApiResponse.ok(houseService.getAllHouse(houseListReq));
    }

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "我的列表")
    @PostMapping("/list")
    public ApiResponse<Page<Customer>> list(@Validated @RequestBody CustomerListReq customerListReq) {
        if (Objects.nonNull(customerListReq.getSearch()) && customerListReq.getSearch() != "") {
            return ApiResponse.ok(customerService.getCustomerByUsername(customerListReq.getSearch()));
        } else {
            return ApiResponse.ok(customerService.getAllCustomer(customerListReq));
        }

    }




}
