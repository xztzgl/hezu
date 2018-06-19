package com.tangquan.service.impl;

import com.tangquan.model.AddHouse;
import com.tangquan.model.Evaluate;
import com.tangquan.model.House;
import com.tangquan.model.Order;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.model.request.OrderReq;
import com.tangquan.repository.AddHouseRepository;
import com.tangquan.repository.AddOrderRepository;
import com.tangquan.repository.HouseRepository;
import com.tangquan.service.HouseService;
import com.tangquan.service.OrderService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
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
@Service("houseService")
public class HouseServiceImpl implements HouseService {

    @Autowired


    HouseRepository houseRepository;

    @Override
    public Page<House> getAllHouse(HouseListReq houseListReq) {
        Page<House> houseList = houseRepository.findAll(new PageRequest(Integer.valueOf(houseListReq.getPage()), Integer.valueOf(houseListReq.getLimit())));

        return houseList;
    }

    @Autowired
    AddHouseRepository addHouseRepository;
    @Override
    public Map add(AddHouse addHouse) {
        Map res = new HashMap();
        AddHouse newHouse = new AddHouse();
        BeanUtils.copyProperties(addHouse, newHouse);
        addHouse.setId(0);
        addHouse.setPublish_id("21101");
        addHouse.setCreate_time(new java.util.Date());
        addHouseRepository.save(addHouse);

        res.put("value", addHouse.getId());
        res.put("success", true);
        return res;
    }

    @Override
    public Map update(AddHouse addHouse) {
        Map res = new HashMap();
        AddHouse newHouse = new AddHouse();
        BeanUtils.copyProperties(addHouse, newHouse);
        addHouse.setPublish_id("21101");
        addHouse.setCreate_time(new java.util.Date());
        addHouseRepository.save(addHouse);

        res.put("value", addHouse.getId());
        res.put("success", true);
        return res;
    }

    @Override
    public Map delete(Integer id) {
        Map res = new HashMap();
        boolean exists = addHouseRepository.exists(id);
        if (!exists) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        addHouseRepository.delete(id);
        House house = houseRepository.findOneById(id);
        if (house != null) {
            addHouseRepository.delete(house.getId());
        }

        res.put("success", true);
        return res;

    }

    @Autowired
    AddOrderRepository addOrderRepository;
    @Autowired
    OrderService orderService;
    @Override
    public Map order(OrderReq orderReq) {
        Order order = new Order();
        order.setCustomer_id(orderReq.getCustomer_id());
        order.setProduct_id(orderReq.getProduct_id());
        return orderService.add(order);

    }


    @Override
    public Map evaluate(Evaluate evaluate) {
        Map res = new HashMap();
        Order order = addOrderRepository.findOneById(evaluate.getId());
        if (order != null) {
            Order newOrder = new Order();
            BeanUtils.copyProperties(order, newOrder);
            newOrder.setId(Integer.valueOf(evaluate.getId()));
            newOrder.setSign_time(evaluate.getSign_time());
            newOrder.setEvaluation(evaluate.getEvaluation());
            newOrder.setDescription(evaluate.getDescription());

            newOrder.setStatus_id("21002");
            newOrder.setFinish_time(new java.util.Date());

            addOrderRepository.save(newOrder);

            res.put("value", newOrder.getId());
            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return res;

    }

}
