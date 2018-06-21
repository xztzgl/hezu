package com.tangquan.service;


import com.tangquan.model.AddHouse;
import com.tangquan.model.Evaluate;
import com.tangquan.model.House;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.model.request.OrderReq;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface HouseService {

    /**
     * 获取所有用户列表
     * @return
     */
    Page<House> getAllHouse(HouseListReq houseListReq);

    /**
     * 根据条件获取所有用户列表
     * @return
     */
    Map getAllHouseBySearch(HouseListReq houseListReq);

    /**
     * 添加
     * @param addHouse
     */
    Map add(AddHouse addHouse);

    /**
     * 修改
     * @param addHouse
     */
    Map update(AddHouse addHouse);

    /**删除
     * @param id
     */
    Map delete(Integer id);

    /**预约
     * @param orderReq
     */
    Map order(OrderReq orderReq);

    /**评价
     * @param evaluate
     */
    Map evaluate(Evaluate evaluate);
}
