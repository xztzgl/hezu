package com.tangquan.controller;

import com.tangquan.model.request.PublishListReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.PublishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(description = "微信端-发布", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-publish/")
public class WechatPublishController {


    @Autowired
    PublishService publishService;

    @ApiOperation(value = "我的发布列表")
    @PostMapping("/list")
    public ApiResponse<Map> list(@Validated @RequestBody PublishListReq publishListReq) {
        return ApiResponse.ok(publishService.getAllPublishBySearch(publishListReq));
    }




}
