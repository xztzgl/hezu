package com.tangquan.service.impl;

import com.tangquan.model.*;
import com.tangquan.model.request.DetailReq;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.model.request.OrderReq;
import com.tangquan.repository.*;
import com.tangquan.service.HouseService;
import com.tangquan.service.OrderService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public Map getAllHouseBySearch(HouseListReq houseListReq) {

        //构建Query，注意用的是HQL语法
        String sql = "select t from House t where t.id <> 0";

        Integer page = 0;
        Integer limit = 10;

        if(houseListReq.getPage() != null && houseListReq.getPage() != ""){//判断keyword是否为空
            page = Integer.valueOf(houseListReq.getPage());
        }
        if(houseListReq.getLimit() != null && houseListReq.getLimit() != ""){//判断keyword是否为空
            limit = Integer.valueOf(houseListReq.getLimit());
        }
        if(houseListReq.getDistrict_id() != null && houseListReq.getDistrict_id() != ""){//判断keyword是否为空
            sql += " and t.district_id = :district_id"; //根据下标
        }
        if(houseListReq.getHousetype_id() != null && houseListReq.getHousetype_id() != ""){//判断keyword是否为空
            sql += " and t.housetype_id = :housetype_id"; //根据下标
        }
        if(houseListReq.getRental_id() != null && houseListReq.getRental_id() != ""){//判断keyword是否为空
            sql += " and t.rental >= :rental_min"; //根据下标
            sql += " and t.rental < :rental_max"; //根据下标
        }

        Query query = em.createQuery(sql);// 这里做个更正

        query.setFirstResult(page * limit);
        query.setMaxResults(limit);


        if(houseListReq.getDistrict_id() != null && houseListReq.getDistrict_id() != ""){//判断keyword是否为空
            query.setParameter("district_id", Integer.valueOf(houseListReq.getDistrict_id())); //根据下标
        }
        if(houseListReq.getHousetype_id() != null && houseListReq.getHousetype_id() != ""){//判断keyword是否为空
            query.setParameter("housetype_id", Integer.valueOf(houseListReq.getHousetype_id())); //根据下标
        }
        if(houseListReq.getRental_id() != null && houseListReq.getRental_id() != ""){//判断keyword是否为空
            String rental_id = houseListReq.getRental_id();
            // 与codeMap字典表对应
            switch (rental_id) {
                // 0-1500
                case "20401":
                    query.setParameter("rental_min", 0);
                    query.setParameter("rental_max", 1500);
                    break;

                // 1500-3000
                case "20402":
                    query.setParameter("rental_min", 1500);
                    query.setParameter("rental_max", 3000);
                    break;

                // 3000-4500
                case "20403":
                    query.setParameter("rental_min", 3000);
                    query.setParameter("rental_max", 4500);
                    break;

                // 4500-
                case "20404":
                    query.setParameter("rental_min", 4500);
                    query.setParameter("rental_max", 100000);
                    break;

                default:
                    query.setParameter("rental_min", 0); //根据下标
                    query.setParameter("rental_max", 0); //根据下标
                    break;
            }

        }


        //查询
        List<House> houseList = query.getResultList();
        Map pageList = new HashMap();
        pageList.put("content", houseList);
        pageList.put("page", page);
        pageList.put("limit", limit);

        return pageList;
    }

    @PersistenceContext
    private EntityManager em; //注入EntityManager


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

    @Autowired
    FavoriteListRepository favoriteListRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Map findOneById(DetailReq detailReq) {
        Map res = new HashMap();
        House house = houseRepository.findOneById(Integer.valueOf(detailReq.getProduct_id()));
        if (house != null) {
            res.put("data", house);

            Favorite favorite = favoriteListRepository.findOneHouseByProductId(detailReq.getProduct_id(), detailReq.getCustomer_id());
            if (favorite != null) {
                res.put("favorited", true);
            } else {
                res.put("favorited", false);
            }


            // 自己创建的可以删除，不能预约
            if (house.getCreator_id().equals(Integer.valueOf(detailReq.getCustomer_id()))) {
                res.put("canOrder", false);
                res.put("canDelete", true);

            } else {
                res.put("canOrder", true);
                res.put("canDelete", false);
            }

            Customer customer = customerRepository.findOneById(house.getCreator_id());
            if (house.getSeentime_id().equals(20901)) {
                if (customer != null) {
                    res.put("customer_mobile", customer.getUsername());
                }
            } else {

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);


                if (calendar.getTime().getTime() > house.getCreate_time().getTime() ) {
                    res.put("customer_mobile", customer.getUsername());
                }
            }

        } else {
            res.put("data", new HashMap());
        }


        res.put("success", true);
        return res;

    }

}
