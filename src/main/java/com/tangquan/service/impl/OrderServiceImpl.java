package com.tangquan.service.impl;

import com.tangquan.model.House;
import com.tangquan.model.Order;
import com.tangquan.model.OrderProductView;
import com.tangquan.model.request.NoticeReq;
import com.tangquan.model.request.OrderListReq;
import com.tangquan.repository.AddOrderRepository;
import com.tangquan.repository.HouseRepository;
import com.tangquan.repository.OrderListRepository;
import com.tangquan.service.NoticeService;
import com.tangquan.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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
        Page<OrderProductView> favoriteList = orderListRepository.findByCustomer_idAndStatus_id(orderListReq.getCustomer_id(), orderListReq.getStatus_id(), new PageRequest(Integer.valueOf(orderListReq.getPage()), Integer.valueOf(orderListReq.getLimit())));

        return favoriteList;
    }

    @Autowired
    AddOrderRepository addOrderRepository;
    @Autowired
    NoticeService noticeService;
    @Autowired
    HouseRepository houseRepository;
    @Override
    public Map add(Order order) {
        Map res = new HashMap();
        Order newOrder = new Order();
        BeanUtils.copyProperties(order, newOrder);
        order.setId(0);
        order.setStatus_id("21001");
        Date createTime = new java.util.Date();
        order.setCreate_time(createTime);
        addOrderRepository.save(order);

        NoticeReq noticeReq = new NoticeReq();
        noticeReq.setCustomer_id(Integer.valueOf(order.getCustomer_id()));
        noticeReq.setMethod(21201);
        noticeReq.setProduct_id(Integer.valueOf(order.getProduct_id()));
        noticeReq.setProduct_type(1);
        noticeReq.setText("您预约了一个房屋，别忘了和房主联系哦");

        noticeService.add(noticeReq, 21302);

        House house = houseRepository.findOneById(Integer.valueOf(order.getProduct_id()));

        res.put("success", true);

        if (house.getSeentime_id().equals(20901)) {
            res.put("customer_mobile", house.getUsername());
        } else {

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);


            if (calendar.getTime().getTime() > createTime.getTime() ) {
                res.put("customer_mobile", house.getUsername());
            } else {
                res.put("customer_mobile", "");
            }
        }


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
