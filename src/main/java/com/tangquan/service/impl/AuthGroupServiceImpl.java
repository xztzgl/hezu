package com.tangquan.service.impl;

import com.tangquan.model.AuthGroup;
import com.tangquan.model.OrderRule;
import com.tangquan.model.request.AccreditAuthGroupReq;
import com.tangquan.model.request.AuthGroupReq;
import com.tangquan.model.request.UpdateAuthGroupReq;
import com.tangquan.model.response.AllOrderRuleResponse;
import com.tangquan.model.response.AuthGroupAccessReponse;
import com.tangquan.repository.AuthGroupRepository;
import com.tangquan.repository.OrderRuleRepository;
import com.tangquan.service.AuthGroupService;
import com.tangquan.service.OrderRuleService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午3:47
 */
@Service
public class AuthGroupServiceImpl implements AuthGroupService {

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    OrderRuleRepository orderRuleRepository;

    @Autowired
    OrderRuleService orderRuleService;

    @Override
    public Integer add(AuthGroupReq authGroupReq) {
        Integer res = 0;
        AuthGroup authGroup = new AuthGroup();
        authGroup.setGroupType((short) 2);//普通管理员
        authGroup.setRules(null);
        try {
            BeanUtils.copyProperties(authGroup, authGroupReq);
            AuthGroup auth = authGroupRepository.findOneByTitle(authGroup.getTitle());
            if (auth != null) {
                throw new ApiException(GatewayError.BAD_ARGUMENT, authGroup.getTitle() + "名称已存在。");
            }
            AuthGroup newAuthGroup = authGroupRepository.save(authGroup);
            res = newAuthGroup.getId();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Integer del(Integer id) {
        AuthGroup authGroup = authGroupRepository.findOne(id);
        if (authGroup == null) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        if (authGroup.getGroupType() == AuthGroup.SUPER_ADMIN) {
            throw new ApiException(GatewayError.SUPER_ADMIN_GROUP);
        }

        authGroupRepository.delete(id);
        return id;
    }

    @Override
    public List<AuthGroup> findAll() {
        return authGroupRepository.findAll();
    }

    @Override
    public Integer update(UpdateAuthGroupReq req) {
        AuthGroup group = authGroupRepository.findOne(req.getId());
        if (group == null) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        if (group.getGroupType() == AuthGroup.SUPER_ADMIN) {
            throw new ApiException(GatewayError.SUPER_ADMIN_GROUP);
        }
        authGroupRepository.updateAuthGroup(req.getTitle(), req.getStatus(), req.getId());
        return req.getId();
    }

    @Override
    public Integer accredit(AccreditAuthGroupReq req) {
        AuthGroup group = authGroupRepository.findOne(req.getId());
        if (group == null) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        if (group.getGroupType() == AuthGroup.SUPER_ADMIN) {
            throw new ApiException(GatewayError.SUPER_ADMIN_GROUP);
        }
        authGroupRepository.accreditAuthGroup(req.getRules(), req.getId());
        return req.getId();
    }

    @Override
    public List<AuthGroupAccessReponse> getGroupCheckRules(Integer groupId){
        List<String> list = getGroupRules(groupId);
        List<AuthGroupAccessReponse> authGroupAccessReponseList = getGroupRules(0,list);
        return authGroupAccessReponseList;
    }

    public List<AuthGroupAccessReponse> getGroupRules(Integer pid,List<String> ruleList) {
        List<AuthGroupAccessReponse> allOrderRuleResponseArrayList = CollectionHelper.newArrayList();
        List<OrderRule> orderList = orderRuleRepository.findOneByPidOrderBySortAsc(pid);
        if(orderList != null && orderList.size() > 0) {
            for(OrderRule orderRule : orderList) {
                AuthGroupAccessReponse authGroupAccessReponse = new AuthGroupAccessReponse();
                try {
                    BeanUtils.copyProperties(authGroupAccessReponse,orderRule);
                    String id = String.valueOf(authGroupAccessReponse.getId());
                    if(CollectionUtils.isEmpty(ruleList)){
                        authGroupAccessReponse.setChecked(false);
                    }else{
                        if(ruleList.contains(id)){
                            authGroupAccessReponse.setChecked(true);
                        }else{
                            authGroupAccessReponse.setChecked(false);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                authGroupAccessReponse.setChildren(getGroupRules(orderRule.getId(),ruleList));
                allOrderRuleResponseArrayList.add(authGroupAccessReponse);
            }
        }
        return allOrderRuleResponseArrayList;
    }

    @Override
    public List<String> getGroupRules(Integer groupId){
        //获取该权限组的授权的目录
        AuthGroup authGroup = authGroupRepository.findOne(groupId);
        Optional<AuthGroup> authGroupOpt = Optional.ofNullable(authGroup);
        authGroupOpt.orElseThrow(() -> new ApiException(GatewayError.NOT_FOUND));
        String rules = authGroupOpt.get().getRules();
        if (StringUtils.isNotEmpty(rules)) {
            String[] str = rules.split(",");
            List<String> list = Arrays.asList(str);
            return list;
        }
        return null;
    }

    @Override
    public List<String> getRulesNameByGroup(Integer groupId) {
        List<String> list = newArrayList();
        AuthGroup authGroup = authGroupRepository.findOne(groupId);
        if (authGroup != null && !authGroup.getRules().isEmpty()) {
            for (String id : authGroup.getRules().split(",")) {
                list.add(orderRuleRepository.findOne(Integer.valueOf(id)).getName());
            }
        }
        return list;
    }


}
