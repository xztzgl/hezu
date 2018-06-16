package com.tangquan.service;


import com.tangquan.model.Notice;
import com.tangquan.model.request.NoticeListReq;
import org.springframework.data.domain.Page;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface NoticeService {

    /**
     * 获取所有用户列表
     * @return
     */
    Page<Notice> getAllNotice(NoticeListReq noticeListReq);

}
