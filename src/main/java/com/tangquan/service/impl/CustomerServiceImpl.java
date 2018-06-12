package com.tangquan.service.impl;

import com.tangquan.model.Customer;
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
    public Page<Customer> getCustomerByMobile(String username) {
        Page<Customer> customerList = customerRepository.findAll(new PageRequest(0, 10));
//        Page<Customer> customerList = new ArrayList<Customer>();
//        if (Objects.nonNull(username)) {
//            Customer customer= customerRepository.findOneByUsername(username);
//            customerList.add(customer);
//            return customerList;
//        } else {
//            customerList = (List<Customer>) customerRepository.findAll(new PageRequest(1, 20));
//        }

        customerList.add()

        return customerList;
    }

}
