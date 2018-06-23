package com.tangquan.service;


import com.tangquan.model.request.StatisticReq;

import java.util.List;
import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface StatisticService {

    /**
     * 获取所有用户列表
     * @return
     */
    Map getUser();
    /**
     * 订单分析昨日核心
     * @return
     */
    Map appointmentYesterday();
    /**
     * 订单分析获取图表数据
     * @return
     */
    List appointmentStatistic(StatisticReq statisticReq);


}
