package com.tangquan.controller;

import com.tangquan.model.AuthGroup;
import com.tangquan.model.request.AccreditAuthGroupReq;
import com.tangquan.model.request.AuthGroupReq;
import com.tangquan.model.request.UpdateAuthGroupReq;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.model.response.AuthGroupAccessReponse;
import com.tangquan.service.AuthGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午4:17
 */
@RestController
@Api(description = "权限组管理API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/auth-group/")
public class AuthGroupController {

    @Autowired
    AuthGroupService authGroupService;

    @ApiOperation(value = "添加权限组")
    @PostMapping("/add")
    public ApiResponse<Integer> add(@Validated @RequestBody AuthGroupReq authGroupReq) {
        return ApiResponse.ok(authGroupService.add(authGroupReq));
    }

    @ApiOperation(value = "删除权限组")
    @DeleteMapping("/del/{id}")
    public ApiResponse<Integer> del(@ApiParam(value = "权限组ID",required = true) @PathVariable Integer id) {
        return ApiResponse.ok(authGroupService.del(id));
    }

    @ApiOperation(value = "编辑权限组")
    @PutMapping("/update")
    public ApiResponse<Integer> update(@Validated @RequestBody UpdateAuthGroupReq updateAuthGroupReq) {
        return ApiResponse.ok(authGroupService.update(updateAuthGroupReq));
    }

    @ApiOperation(value = "授权权限组")
    @PutMapping("/accredit")
    public ApiResponse<Integer> accredit(@Validated @RequestBody AccreditAuthGroupReq accreditAuthGroupReq) {
        return ApiResponse.ok(authGroupService.accredit(accreditAuthGroupReq));
    }

    @ApiOperation(value = "获取权限组列表")
    @GetMapping("/find-all")
    public ApiResponse<List<AuthGroup>> findAll() {
        return ApiResponse.ok(authGroupService.findAll());
    }

    @ApiOperation(value = "根据权限组获取授权列表")
    @GetMapping("/access-auth-group/{groupId}")
    public ApiResponse<List<AuthGroupAccessReponse>> accessAuthGroup(
            @ApiParam(value = "权限组ID",required = true) @PathVariable Integer groupId) {
        return ApiResponse.ok(authGroupService.getGroupCheckRules(groupId));
    }
}
