package com.tangquan.controller;

import com.tangquan.framework.JWTHelper;
import com.tangquan.model.request.*;
import com.tangquan.model.response.AdminUserResponse;
import com.tangquan.model.response.AllAdminUserResponse;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 上午10:44
 */
@RestController
@Api(description = "用户管理API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/admin-user/")
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @ApiOperation(value = "添加管理员")
    @PostMapping("/add")
    public ApiResponse<Integer> addAdminUser(@Validated @RequestBody AddAdminUserReq adminUserReq) {

        return ApiResponse.ok(adminUserService.addAdminUser(adminUserReq));
    }

    @ApiOperation(value = "获取管理员列表")
    @GetMapping("/find-all")
    public ApiResponse<List<AllAdminUserResponse>> findAll() {
        return ApiResponse.ok(adminUserService.getAllAdminUser());
    }

    @ApiOperation(value = "删除管理员")
    @DeleteMapping("/del/{id}")
    public ApiResponse<Integer> del(@ApiParam(value = "管理员ID", required = true) @PathVariable Integer id) {
        return ApiResponse.ok(adminUserService.delAdminUser(id));
    }

    @ApiOperation(value = "获取管理员")
    @GetMapping("/get/{id}")
    public ApiResponse<AdminUserResponse> getUser(
            @ApiParam(value = "管理员ID", required = true) @PathVariable Integer id) {
        return ApiResponse.ok(adminUserService.getAdminUser(id));
    }

    @ApiOperation(value = "编辑管理员")
    @PutMapping("/update")
    public ApiResponse<Integer> updateUser(@Validated @RequestBody UpdateAdminUserRequest updateAdminUserRequest) {
        return ApiResponse.ok(adminUserService.updateAdminUser(updateAdminUserRequest));
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ApiResponse<AdminUserResponse> login(@Validated @RequestBody LoginReq loginReq,
                                                HttpServletRequest request, HttpServletResponse response) {
        AdminUserResponse adminUserResponse =
                adminUserService.login(loginReq.getLoginName(), loginReq.getPassword(), request.getRemoteAddr());
        response.addHeader(HttpHeaders.AUTHORIZATION, JWTHelper.AUTH_TYPE + adminUserResponse.getToken());
        return ApiResponse
                .ok(adminUserResponse);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/update-password")
    public ApiResponse<Integer> updateUserPassword(
            @Validated @RequestBody UpdateUserPasswordReq updateUserPasswordReq) {
        return ApiResponse.ok(adminUserService.updateUserPassword(updateUserPasswordReq));
    }

    @ApiOperation(value = "判断用户是否有权限访问")
    @PutMapping("/user-auth-order")
    public ApiResponse<Boolean> userAuthOrder(@Validated @RequestBody UserAuthOrderReq userAuthOrderReq) {
        return ApiResponse.ok(adminUserService.userAuthOrder(userAuthOrderReq.getUid(), userAuthOrderReq.getAddress()));
    }

}
