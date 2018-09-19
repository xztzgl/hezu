package com.tangquan.service.impl;

import com.tangquan.service.AuthGroupService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import com.tangquan.model.OrderRule;
import com.tangquan.repository.OrderRuleRepository;
import com.tangquan.model.request.OrderRuleReq;
import com.tangquan.model.request.UpdateOrderRuleReq;
import com.tangquan.model.response.AllOrderRuleResponse;
import com.tangquan.model.response.AuthGroupAccessReponse;
import com.tangquan.model.response.OrderRuleResponse;
import com.tangquan.service.OrderRuleService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("orderRuleService")
public class OrderRuleServiceImpl implements OrderRuleService {

    @Autowired
    OrderRuleRepository orderRuleRepository;

    @Autowired
    AuthGroupService authGroupService;

    @Override
    public Integer add(OrderRuleReq orderRuleReq) {
        Integer res = 0;
        OrderRule orderRule = new OrderRule();
        try {
            BeanUtils.copyProperties(orderRule,orderRuleReq);
            OrderRule order = orderRuleRepository.findByTitle(orderRule.getTitle());
            if(order != null) {
                throw new ApiException(GatewayError.BAD_ARGUMENT,"菜单" + orderRule.getTitle() + "已存在。");
            }
            orderRuleRepository.save(orderRule);
            res = orderRule.getId();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<AllOrderRuleResponse> getAllOrderRule(Integer groupId) {
        List<String> list = authGroupService.getGroupRules(groupId);
        List<AllOrderRuleResponse> orderRuleResponseList = allOrderRuleList(0,list);
        return orderRuleResponseList;
    }

    @Override
    public Integer delete(Integer id) {
        OrderRule orderRule = orderRuleRepository.findOne(id);
        Optional<OrderRule> orderRuleOpt = Optional.ofNullable(orderRule);
        if(!orderRuleOpt.isPresent()) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        List<OrderRule> list = orderRuleRepository.findOneByPidOrderBySortAsc(id);
        if(list != null && list.size() > 0) {
            throw new ApiException(GatewayError.EXEC_FAILED,"该节点下有子节点,请先删除子节点。");
        }
        orderRuleRepository.delete(id);
        return id;
    }

    @Override
    public List<AuthGroupAccessReponse> getAllOrderRules(Integer groupId) {
        List<String> list = authGroupService.getGroupRules(groupId);
        List<AuthGroupAccessReponse> authGroupAccessReponseList = newArrayList();
        List<AllOrderRuleResponse> orderList = allOrderRuleList(0,list);
        bulid(orderList,authGroupAccessReponseList);
        return authGroupAccessReponseList;
    }

    @Override
    public Integer update(UpdateOrderRuleReq updateOrderRuleReq) {
        Integer res = 0;
        OrderRule orderRule = new OrderRule();
        OrderRule order = orderRuleRepository.findOne(updateOrderRuleReq.getId());
        if(order == null) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        try {
            BeanUtils.copyProperties(orderRule,updateOrderRuleReq);
            orderRuleRepository.save(orderRule);
            res = orderRule.getId();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public OrderRuleResponse get(Integer id) {
        OrderRule orderRule = orderRuleRepository.findOne(id);
        OrderRuleResponse orderRuleResponse = new OrderRuleResponse();
        try {
            BeanUtils.copyProperties(orderRuleResponse,orderRule);
            OrderRule pOrderRule = orderRuleRepository.findOne(orderRule.getPid());
            if(pOrderRule != null) {
                orderRuleResponse.setPTitle(pOrderRule.getTitle());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return orderRuleResponse;
    }


    /**
     * 获取所有菜单,递归计算
     * @param pid 父节点ID
     * @return
     */
    private List<AllOrderRuleResponse> allOrderRuleList(int pid,List<String> ruleList) {
        List<AllOrderRuleResponse> allOrderRuleResponseArrayList = newArrayList();
        List<OrderRule> orderList = orderRuleRepository.findOneByPidOrderBySortAsc(pid);
        if(orderList != null && orderList.size() > 0) {
            for(OrderRule orderRule : orderList) {
                AllOrderRuleResponse orderRuleResponse = new AllOrderRuleResponse();
                try {
                    BeanUtils.copyProperties(orderRuleResponse,orderRule);
                    String id = String.valueOf(orderRuleResponse.getId());
                    if(CollectionUtils.isEmpty(ruleList)){
                        orderRuleResponse.setChecked(false);
                    }else{
                        if(ruleList.contains(id)){
                            orderRuleResponse.setChecked(true);
                        }else{
                            orderRuleResponse.setChecked(false);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orderRuleResponse.setChildren(allOrderRuleList(orderRule.getId(),ruleList));
                allOrderRuleResponseArrayList.add(orderRuleResponse);
            }
        }
        return allOrderRuleResponseArrayList;
    }

    /**
     * 递归计算获取所有菜单规则
     * @param allOrderRuleList
     * @param authGroupAccessReponseList
     */
    private void bulid(List<AllOrderRuleResponse> allOrderRuleList,List<AuthGroupAccessReponse> authGroupAccessReponseList) {
        for(AllOrderRuleResponse orderRuleResponse : allOrderRuleList) {
            AuthGroupAccessReponse authGroupAccessReponse = new AuthGroupAccessReponse();
            authGroupAccessReponse.setTitle(orderRuleResponse.getTitle());
            authGroupAccessReponse.setPid(orderRuleResponse.getPid());
            authGroupAccessReponse.setId(orderRuleResponse.getId());
            authGroupAccessReponseList.add(authGroupAccessReponse);
            if(orderRuleResponse.getChildren() != null && orderRuleResponse.getChildren().size() > 0) {
                bulid(orderRuleResponse.getChildren(),authGroupAccessReponseList);
            }
        }
    }


}
