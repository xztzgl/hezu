package com.tangquan.service;


import com.tangquan.model.request.AddAdminUserReq;
import com.tangquan.model.request.UpdateAdminUserRequest;
import com.tangquan.model.request.UpdateUserPasswordReq;
import com.tangquan.model.response.AdminUserResponse;
import com.tangquan.model.response.AllAdminUserResponse;

import java.util.List;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface AdminUserService {

    /**
     * 添加管理员
     * @param adminUserReq
     */
    Integer addAdminUser(AddAdminUserReq adminUserReq);

    /**
     * 获取管理员用户
     * @param id
     * @return
     */
    AdminUserResponse getAdminUser(Integer id);

    /**
     * 删除管理员用户
     * @param id
     * @return
     */
    Integer delAdminUser(Integer id);

    /**
     * 获取管理员列表
     * @return
     */
    List<AllAdminUserResponse> getAllAdminUser();

    /**
     * 编辑管理员
     * @param updateAdminUserRequest
     */
    Integer updateAdminUser(UpdateAdminUserRequest updateAdminUserRequest);

    /**
     * 登录
     * @param loginName
     * @param pwd
     * @param lastLoginIp
     * @return
     */
    AdminUserResponse login(String loginName, String pwd, String lastLoginIp);

    /**
     * 修改密码
     * @param updateUserPasswordReq
     */
    Integer updateUserPassword(UpdateUserPasswordReq updateUserPasswordReq);

    /**
     * 判断用户是否有权限访问某路径
     * @param uid
     * @param address
     * @return
     */
    boolean userAuthOrder(Integer uid,String address);



}
