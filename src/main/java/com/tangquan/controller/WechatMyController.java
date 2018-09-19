package com.tangquan.controller;

import com.tangquan.model.Customer;
import com.tangquan.model.request.MyReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.repository.CustomerRepository;
import com.tangquan.repository.OrderListRepository;
import com.tangquan.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderListRepository orderListRepository;

    @ApiOperation(value = "我的首页")
    @PostMapping("/customer")
    public ApiResponse<Map> customer(@Validated @RequestBody MyReq myReq) {

        Map res = new HashMap();
        Map data = new HashMap();

        Customer customer =  customerRepository.findOneById(Integer.valueOf(myReq.getCustomer_id()));

        List order = orderListRepository.findByCustomer_id(myReq.getCustomer_id());

        data.put("username", customer.getUsername());
        data.put("score", customer.getScore());
        data.put("avatar", customer.getAvatar());
        data.put("totalOrder", order.size());



        res.put("data", data);
        res.put("success", true);
        return ApiResponse.ok(res);
    }

//    @Autowired
//    CustomerService customerService;

//    @ApiOperation(value = "我的列表")
//    @PostMapping("/list")
//    public ApiResponse<Page<Customer>> list(@Validated @RequestBody CustomerListReq customerListReq) {
//        if (Objects.nonNull(customerListReq.getSearch()) && customerListReq.getSearch() != "") {
//            return ApiResponse.ok(customerService.getCustomerByUsername(customerListReq.getSearch()));
//        } else {
//            return ApiResponse.ok(customerService.getAllCustomer(customerListReq));
//        }
//
//    }






}
