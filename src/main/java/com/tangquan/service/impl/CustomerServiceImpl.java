package com.tangquan.service.impl;

import com.tangquan.model.Customer;
import com.tangquan.model.request.AddCustomerReq;
import com.tangquan.model.request.CustomerListReq;
import com.tangquan.repository.CustomerRepository;
import com.tangquan.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> getCustomerByUsername(String username) {

//        List customerList = new ArrayList<Customer>();
//
//        Customer customer= customerRepository.findOneByUsername(username, new PageRequest(0, 10));
//        customerList.add(customer);

        Page<Customer> customerList = customerRepository.findByUsernameLike(username, new PageRequest(0, 10));
        return customerList;
    }

    @Override
    public Page<Customer> getAllCustomer(CustomerListReq customerListReq) {
        Page<Customer> customerList = customerRepository.findAll(new PageRequest(Integer.valueOf(customerListReq.getPage()), Integer.valueOf(customerListReq.getLimit())));

        return customerList;
    }


    // 注册
    @Override
    @Transactional
    public Map addCustomer(AddCustomerReq addCustomerReq) {
        Map res = new HashMap();
        Customer addCustomer = new Customer();
        try {
            BeanUtils.copyProperties(addCustomer, addCustomerReq);

            Customer user = customerRepository.findOneByUsername(addCustomer.getUsername());
            if (user == null) {
                addCustomer.setId(0);
            } else {
                addCustomer.setId(user.getId());
            }

            addCustomer.setPassword("123");
            addCustomer.setCreate_time(new java.util.Date());
            addCustomer.setUpdate_time(new java.util.Date());
//            adminUser.setPassword(CommonUtils.SHA256(adminUserReq.getPassword()));
            customerRepository.save(addCustomer);
            res.put("success", true);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return res;
    }

    // 获取验证码
    @Override
    @Transactional
    public Map getCode(String username) {
        Map res = new HashMap();

        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码

        Customer addCustomer = new Customer();

        Customer user = customerRepository.findOneByUsername(username);



        if (user == null) {

            addCustomer.setId(0);
            addCustomer.setUsername(username);
            addCustomer.setPassword(verifyCode);
            customerRepository.save(addCustomer);

        } else {
            user.setPassword(verifyCode);
            customerRepository.save(user);
        }


        res.put("verifyCode", verifyCode);
        res.put("success", true);

        return res;
    }

}
