package com.tangquan.controller;

import com.tangquan.model.AddPerson;
import com.tangquan.model.request.DetailReq;
import com.tangquan.model.request.PersonListReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CustomerService;
import com.tangquan.service.PersonService;
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
@Api(description = "微信端-找合租人", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-person/")
public class WechatPersonController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "合租人列表")
    @PostMapping("/list")
    public ApiResponse<Map> list(@Validated @RequestBody PersonListReq personListReq) {
        return ApiResponse.ok(personService.getAllPersonBySearch(personListReq));
    }

    @ApiOperation(value = "合租人详情")
    @PostMapping("/get")
    public ApiResponse<Map> get(@Validated @RequestBody DetailReq detailReq) {
        return ApiResponse.ok(personService.findOneById(detailReq));
    }

    @Autowired
    PersonService personService;

    @ApiOperation(value = "新增合租人")
    @PostMapping("/add")
    public ApiResponse<Map> add(@Validated @RequestBody AddPerson addPerson) {
        return ApiResponse.ok(personService.add(addPerson));
    }

    @ApiOperation(value = "修改合租人")
    @PostMapping("/update")
    public ApiResponse<Map> update(@Validated @RequestBody AddPerson addPerson) {
        return ApiResponse.ok(personService.update(addPerson));
    }

    @ApiOperation(value = "删除合租人")
    @PostMapping("/delete/{id}")
    public ApiResponse<Map> delete(@ApiParam(value = "合租者ID", required = true) @PathVariable Integer id) {
        return ApiResponse.ok(personService.delete(id));
    }


}
