package com.tangquan.controller;

import com.tangquan.model.Notice;
import com.tangquan.model.request.NoticeListReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.NoticeService;
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
@Api(description = "微信端-消息", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wechat-notice/")
public class WechatNoticeController {


    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "我的消息列表")
    @PostMapping("/list")
    public ApiResponse<Page<Notice>> list(@Validated @RequestBody NoticeListReq noticeListReq) {
        return ApiResponse.ok(noticeService.getAllNoticeBySearch(noticeListReq));
    }




}
