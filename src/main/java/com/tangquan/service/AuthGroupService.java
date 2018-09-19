package com.tangquan.service;

import com.tangquan.model.AuthGroup;
import com.tangquan.model.request.AccreditAuthGroupReq;
import com.tangquan.model.request.AuthGroupReq;
import com.tangquan.model.request.UpdateAuthGroupReq;
import com.tangquan.model.response.AuthGroupAccessReponse;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午3:47
 */
public interface AuthGroupService {

    /**
     * 添加权限组
     * @param authGroupReq
     * @return
     */
    Integer add(AuthGroupReq authGroupReq);

    /**
     * 删除权限组
     * @param id
     * @return
     */
    Integer del(Integer id);

    /**
     * 获取权限列表
     * @return
     */
    List<AuthGroup> findAll();

    /**
     * 更新权限组
     * @param updateAuthGroupReq
     */
    Integer update(UpdateAuthGroupReq updateAuthGroupReq);

    /**
     * 授权权限组
     * @param accreditAuthGroupReq
     */
    Integer accredit(AccreditAuthGroupReq accreditAuthGroupReq);
    /**
     * 根据权限组ID获取授权列表
     * @param groupId
     * @return
     */
    List<AuthGroupAccessReponse> getGroupCheckRules(Integer groupId);

    /**
     * 根据权限组ID获取菜单地址
     * @param groupId
     * @return
     */
    List<String> getRulesNameByGroup(Integer groupId);

    List<String> getGroupRules(Integer groupId);
}
