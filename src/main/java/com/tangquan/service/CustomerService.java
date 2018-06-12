package com.tangquan.service;


import com.tangquan.model.Customer;
import org.springframework.data.domain.Page;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface CustomerService {

    /**
     * 获取所有数据字典列表
     * @return
     */
    Page<Customer> getCustomerByMobile(String username);

}
