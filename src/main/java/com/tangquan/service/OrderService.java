package com.tangquan.service;


import com.tangquan.model.Order;
import com.tangquan.model.OrderProductView;
import com.tangquan.model.request.OrderListReq;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface OrderService {

//    /**
//     * 获取所有用户列表
//     * @return
//     */
//    Page<Order> getAllHouse(HouseListReq houseListReq);

    /**
     * 根据条件获取所有用户列表
     * @return
     */
    Page<OrderProductView> getAllOrderBySearch(OrderListReq orderListReq);
    /**
     * 添加
     * @param order
     */
    Map add(Order order);

//    /**
//     * 修改
//     * @param addHouse
//     */
//    Map update(AddHouse addHouse);
//
//    /**删除
//     * @param id
//     */
//    Map delete(Integer id);
//
//    /**预约
//     * @param id
//     */
//    Map order(Integer id);
}
