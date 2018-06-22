package com.tangquan.service.impl;

import com.tangquan.model.Order;
import com.tangquan.model.OrderProductView;
import com.tangquan.model.request.OrderListReq;
import com.tangquan.repository.AddOrderRepository;
import com.tangquan.repository.OrderListRepository;
import com.tangquan.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

//    @Autowired
//
//
//    HouseRepository houseRepository;
//
//    @Override
//    public Page<House> getAllHouse(HouseListReq houseListReq) {
//        Page<House> houseList = houseRepository.findAll(new PageRequest(Integer.valueOf(houseListReq.getPage()), Integer.valueOf(houseListReq.getLimit())));
//
//        return houseList;
//    }

    @Autowired
    OrderListRepository orderListRepository;
    @Override
    public Page<OrderProductView> getAllOrderBySearch(OrderListReq orderListReq) {
        Page<OrderProductView> favoriteList = orderListRepository.findByCustomer_id(orderListReq.getCustomer_id(), orderListReq.getStatus_id(), new PageRequest(Integer.valueOf(orderListReq.getPage()), Integer.valueOf(orderListReq.getLimit())));

        return favoriteList;
    }

    @Autowired
    AddOrderRepository addOrderRepository;
    @Override
    public Map add(Order order) {
        Map res = new HashMap();
        Order newOrder = new Order();
        BeanUtils.copyProperties(order, newOrder);
        order.setId(0);
        order.setStatus_id("21001");
        order.setCreate_time(new java.util.Date());
        addOrderRepository.save(order);

        res.put("value", order.getId());
        res.put("success", true);
        return res;
    }

//    @Override
//    public Map update(AddHouse addHouse) {
//        Map res = new HashMap();
//        AddHouse newHouse = new AddHouse();
//        BeanUtils.copyProperties(addHouse, newHouse);
//        addHouse.setPublish_id("21101");
//        addHouse.setCreate_time(new java.util.Date());
//        addHouseRepository.save(addHouse);
//
//        res.put("value", addHouse.getId());
//        res.put("success", true);
//        return res;
//    }
//
//    @Override
//    public Map delete(Integer id) {
//        Map res = new HashMap();
//        boolean exists = addHouseRepository.exists(id);
//        if (!exists) {
//            throw new ApiException(GatewayError.NOT_FOUND);
//        }
//        addHouseRepository.delete(id);
//        House house = houseRepository.findOneById(id);
//        if (house != null) {
//            addHouseRepository.delete(house.getId());
//        }
//
//        res.put("success", true);
//        return res;
//
//    }
//
//    @Autowired
//    AddOrderRepository addOrderRepository;
//    @Override
//    public Map order(Integer id) {
//        Map res = new HashMap();
//        boolean exists = addHouseRepository.exists(id);
//        if (!exists) {
//            throw new ApiException(GatewayError.NOT_FOUND);
//        }
//
//
//
//        res.put("success", true);
//        return res;
//
//    }

}
