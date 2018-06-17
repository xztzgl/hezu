package com.tangquan.controller;

import com.tangquan.model.Customer;
import com.tangquan.model.House;
import com.tangquan.model.Notice;
import com.tangquan.model.Person;
import com.tangquan.model.request.*;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CustomerService;
import com.tangquan.service.HouseService;
import com.tangquan.service.NoticeService;
import com.tangquan.service.PersonService;
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
import java.util.Objects;

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
    @PostMapping("/user")
    public ApiResponse<Page<Customer>> user(@Validated @RequestBody CustomerListReq customerListReq) {
        if (Objects.nonNull(customerListReq.getSearch()) && customerListReq.getSearch() != "") {
            return ApiResponse.ok(customerService.getCustomerByUsername(customerListReq.getSearch()));
        } else {
            return ApiResponse.ok(customerService.getAllCustomer(customerListReq));
        }

    }


    @Autowired
    HouseService houseService;

    @ApiOperation(value = "房屋信息管理菜单")
    @PostMapping("/house")
    public ApiResponse<Page<House>> house(@Validated @RequestBody HouseListReq houseListReq) {
        return ApiResponse.ok(houseService.getAllHouse(houseListReq));
    }

    @Autowired
    PersonService personService;

    @ApiOperation(value = "找人信息管理菜单")
    @PostMapping("/partner")
    public ApiResponse<Page<Person>> partner(@Validated @RequestBody PersonListReq personListReq) {
        return ApiResponse.ok(personService.getAllPerson(personListReq));
    }


    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "通知信息管理菜单")
    @PostMapping("/notice")
    public ApiResponse<Page<Notice>> notice(@Validated @RequestBody NoticeListReq noticeListReq) {
        return ApiResponse.ok(noticeService.getAllNotice(noticeListReq));
    }

    @ApiOperation(value = "添加通知信息")
    @PostMapping("/notice/add")
    public ApiResponse<Map> add(@Validated @RequestBody NoticeReq noticeReq) {
        return ApiResponse.ok(noticeService.add(noticeReq));
    }

}
