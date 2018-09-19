package com.tangquan.service;


import com.tangquan.model.Favorite;
import com.tangquan.model.FavoriteProductView;
import com.tangquan.model.request.FavoriteListReq;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface FavoriteService {

    /**
     * 根据条件获取所有用户列表
     * @return
     */
    Page<FavoriteProductView> getAllFavoriteBySearch(FavoriteListReq favoriteListReq);

    /**
     * 添加
     * @param favorite
     */
    Map add(Favorite favorite);

//    /**
//     * 修改
//     * @param addHouse
//     */
//    Map update(AddHouse addHouse);
//
    /**删除
     * @param favorite
     */
    Map delete(Favorite favorite);
//
//    /**预约
//     * @param id
//     */
//    Map order(Integer id);
}
