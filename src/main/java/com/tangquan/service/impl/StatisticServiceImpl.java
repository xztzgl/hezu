package com.tangquan.service.impl;

import com.tangquan.model.Customer;
import com.tangquan.model.OrderProductView;
import com.tangquan.model.request.StatisticReq;
import com.tangquan.repository.CustomerRepository;
import com.tangquan.repository.OrderListRepository;
import com.tangquan.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {


    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Map getUser() {
        Map res = new HashMap();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);


        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);

        String end = format.format(calendar.getTime());

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);

        String start = format.format(calendar.getTime());

        // 新注册
        String sqlNewCustomer = "select a from Customer a where a.create_time = a.update_time";

        Query queryNewCustomer = em.createQuery(sqlNewCustomer);// 这里做个更正


        List<Customer> customerListNewCustomer = queryNewCustomer.getResultList();

        // 昨日访问
        String sqlYestodayCustomer = "from Customer where update_time >= '" + start +"' and update_time <= '"+end+"'";

        Query queryYestodayCustomer = em.createQuery(sqlYestodayCustomer);// 这里做个更正


        List<Customer> customerListYestodayCustomer = queryYestodayCustomer.getResultList();


        res.put("newTotalCustomer", customerListNewCustomer.size());
        res.put("yestodayTotalCustomer", customerListYestodayCustomer.size());
        res.put("totalCustomer", customerRepository.count());


        return res;
    }

    @Autowired
    OrderListRepository orderListRepository;
    @Override
    public Map appointmentYesterday() {
        Map res = new HashMap();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);


        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);

        String end = format.format(calendar.getTime());

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);

        String start = format.format(calendar.getTime());

        // 新增预约
        String sqlNew = "from OrderProductView where create_time >= '" + start +"' and create_time <= '"+end+"'";

        Query queryNew = em.createQuery(sqlNew);


        List<Customer> NewOrder = queryNew.getResultList();

        // 昨日完成
        String sqlYestoday = "from OrderProductView where finish_time >= '" + start +"' and finish_time <= '"+end+"'";

        Query queryYestoday = em.createQuery(sqlYestoday);


        List<Customer> YestodayFinishOrder = queryYestoday.getResultList();

        // 累计金额
        List<OrderProductView> TotalOrder = orderListRepository.findAll();

        Integer total = 0;

        int size = TotalOrder.size();

        for(int i=0;i<size;i++){

            Object o= TotalOrder.get(i);
            total += ((OrderProductView) o).getHouse_rental();

        }


        res.put("newTotalOrder", NewOrder.size());
        res.put("finishedTotalOrder", YestodayFinishOrder.size());
        res.put("totalRental", total);


        return res;
    }


    @Override
    public List appointmentStatistic(StatisticReq statisticReq) {


        String start = statisticReq.getStartTime();

        String end = statisticReq.getEndTime();

        // 新增预约
        String sql = "from OrderProductView where create_time >= '" + start +"' and create_time <= '"+end+"'";

        Query query = em.createQuery(sql);


        List<OrderProductView> TotalOrder = query.getResultList();


        return TotalOrder;
    }

    @PersistenceContext
    private EntityManager em; //注入EntityManager


}
