package com.tangquan.controller;

import com.tangquan.model.Favorite;
import com.tangquan.model.FavoriteProductView;
import com.tangquan.model.request.FavoriteListReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.FavoriteService;
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
    FavoriteService favoriteService;

    @ApiOperation(value = "房屋列表")
    @PostMapping("/list")
    public ApiResponse<Page<FavoriteProductView>> list(@Validated @RequestBody FavoriteListReq favoriteListReq) {
        return ApiResponse.ok(favoriteService.getAllFavoriteBySearch(favoriteListReq));
    }

    @ApiOperation(value = "新增收藏")
    @PostMapping("/add")
    public ApiResponse<Map> add(@Validated @RequestBody Favorite favorite) {
        return ApiResponse.ok(favoriteService.add(favorite));
    }

    @ApiOperation(value = "删除收藏")
    @PostMapping("/delete")
    public ApiResponse<Map> delete(@Validated @RequestBody Favorite favorite) {
        return ApiResponse.ok(favoriteService.delete(favorite));
    }



}
