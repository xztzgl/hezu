package com.tangquan.service.impl;

import com.tangquan.model.PublishHouse;
import com.tangquan.model.PublishPerson;
import com.tangquan.model.request.PublishListReq;
import com.tangquan.repository.HouseRepository;
import com.tangquan.repository.PersonRepository;
import com.tangquan.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("publishService")
public class PublishServiceImpl implements PublishService {
    @Autowired
    HouseRepository houseRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Map getAllPublishBySearch(PublishListReq publishListReq) {

        //构建Query，注意用的是HQL语法
        String sql1 = "select t from PublishHouse t where t.creator_id = :customer_id";
        Query query1 = em.createQuery(sql1);
        query1.setParameter("customer_id", Integer.valueOf(publishListReq.getCustomer_id()));

        String sql2 = "select t from PublishPerson t where t.creator_id = :customer_id";
        Query query2 = em.createQuery(sql2);
        query2.setParameter("customer_id", Integer.valueOf(publishListReq.getCustomer_id()));


        //查询
        List<PublishHouse> houseList = query1.getResultList();
        List<PublishHouse> personList = query2.getResultList();



        houseList.addAll(personList);

//        Collections.sort(houseList, (o1, o2) -> {
//            int i = (int)o1.getCreate_time().getTime() - (int)o2.getCreate_time().getTime();
//            return i;
//        });




        Integer page = 0;
        Integer limit = 10;

        if(publishListReq.getPage() != null && publishListReq.getPage() != ""){//判断keyword是否为空
            page = Integer.valueOf(publishListReq.getPage());
        }
        if(publishListReq.getLimit() != null && publishListReq.getLimit() != ""){//判断keyword是否为空
            limit = Integer.valueOf(publishListReq.getLimit());
        }


        int start = 0;
        int end = houseList.size();
        if (page * limit < end) {
            start = page * limit;
        } else {
            start = end;
        }
        if (end > page * limit + limit) {
            end = page * limit + limit;
        }

        List<PublishHouse> pageResult = houseList.subList(start, end);


        Map pageList = new HashMap();
        pageList.put("content", pageResult);
        pageList.put("page", page);
        pageList.put("limit", limit);

        return pageList;
    }

    @PersistenceContext
    private EntityManager em; //注入EntityManager


}
