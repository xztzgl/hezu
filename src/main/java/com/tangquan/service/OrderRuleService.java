package com.tangquan.service;

import com.tangquan.model.request.OrderRuleReq;
import com.tangquan.model.request.UpdateOrderRuleReq;
import com.tangquan.model.response.AllOrderRuleResponse;
import com.tangquan.model.response.AuthGroupAccessReponse;
import com.tangquan.model.response.OrderRuleResponse;

import java.util.List;

/**
 * 菜单规则服务
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:48
 */
public interface OrderRuleService {

    /**
     * 添加菜单
     * @param orderRuleReq
     */
    Integer add(OrderRuleReq orderRuleReq);

    /**
     * 获取所有菜单列表,含层级结构
     * @return
     */
    List<AllOrderRuleResponse> getAllOrderRule(Integer groupId);

    /**
     * 删除菜单
     * @param id
     */
    Integer delete(Integer id);

    /**
     * 获取所有菜单列表,无层级结构
     * @return
     */
    List<AuthGroupAccessReponse> getAllOrderRules(Integer groupId);

    /**
     * 编辑菜单
     * @param updateOrderRuleReq
     */
    Integer update(UpdateOrderRuleReq updateOrderRuleReq);

    /**
     * 获取单个菜单
     * @param id
     * @return
     */
    OrderRuleResponse get(Integer id);
}
