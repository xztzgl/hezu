package com.tangquan.service.impl;

import com.tangquan.model.Customer;
import com.tangquan.model.request.CustomerListReq;
import com.tangquan.repository.CustomerRepository;
import com.tangquan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
