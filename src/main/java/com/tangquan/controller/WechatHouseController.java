package com.tangquan.controller;

import com.tangquan.model.AddHouse;
import com.tangquan.model.Evaluate;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.model.request.OrderReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.HouseService;
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
@Api(description = "微信端-房屋", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-house/")
public class WechatHouseController {

    @Autowired
    HouseService houseService;

    @ApiOperation(value = "房屋列表")
    @PostMapping("/list")
    public ApiResponse<Map> list(@Validated @RequestBody HouseListReq houseListReq) {
        return ApiResponse.ok(houseService.getAllHouseBySearch(houseListReq));
    }



//    @ApiOperation(value = "房屋详情")
//    @PostMapping("/get")
//    public ApiResponse<Page<House>> get(@Validated @RequestBody HouseListReq houseListReq) {
//        return ApiResponse.ok(houseService.getAllHouse(houseListReq));
//    }


    @ApiOperation(value = "新增房屋")
    @PostMapping("/add")
    public ApiResponse<Map> add(@Validated @RequestBody AddHouse addHouse) {
        return ApiResponse.ok(houseService.add(addHouse));
    }

    @ApiOperation(value = "修改房屋")
    @PostMapping("/update")
    public ApiResponse<Map> update(@Validated @RequestBody AddHouse addHouse) {
        return ApiResponse.ok(houseService.update(addHouse));
    }

    @ApiOperation(value = "删除房屋")
    @PostMapping("/delete/{id}")
    public ApiResponse<Map> delete(@ApiParam(value = "房屋ID", required = true) @PathVariable Integer id) {
        return ApiResponse.ok(houseService.delete(id));
    }

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
