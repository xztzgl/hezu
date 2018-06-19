package com.tangquan.controller;

import com.tangquan.model.Person;
import com.tangquan.model.request.PersonListReq;
import com.tangquan.model.response.ApiResponse;
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

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "微信端-收藏", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-favorite/")
public class WechatFavoriteController {


    @Autowired
    PersonService personService;

    @ApiOperation(value = "新增收藏")
    @PostMapping("/add")
    public ApiResponse<Page<Person>> add(@Validated @RequestBody PersonListReq personListReq) {
        return ApiResponse.ok(personService.getAllPerson(personListReq));
    }



}
