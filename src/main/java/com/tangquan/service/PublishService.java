package com.tangquan.service;


import com.tangquan.model.request.PublishListReq;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface PublishService {


    /**
     * 根据条件获取发布列表
     * @return
     */
    Map getAllPublishBySearch(PublishListReq publishListReq);


}
