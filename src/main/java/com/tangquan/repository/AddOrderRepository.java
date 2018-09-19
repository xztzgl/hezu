package com.tangquan.repository;

import com.tangquan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息列表
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface AddOrderRepository extends JpaRepository<Order,Integer> {

    Order findOneById(Integer id);
}
