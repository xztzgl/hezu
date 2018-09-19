package com.tangquan.service;


import com.tangquan.model.Customer;
import com.tangquan.model.request.AddCustomerReq;
import com.tangquan.model.request.CustomerListReq;
import com.tangquan.model.request.CustomerLoginReq;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface CustomerService {

    /**
     * 获取单条用户列表
     * @return
     */
    Page<Customer> getCustomerByUsername(String username);

    /**
     * 获取所有用户列表
     * @return
     */
    Page<Customer> getAllCustomer(CustomerListReq customerListReq);

    Map addCustomer(AddCustomerReq addCustomerReq);
    Map getCode(String username);
    Map login(CustomerLoginReq customerLoginReq);

}
