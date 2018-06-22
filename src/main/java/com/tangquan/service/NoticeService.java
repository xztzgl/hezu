package com.tangquan.service;


import com.tangquan.model.Notice;
import com.tangquan.model.request.NoticeListReq;
import com.tangquan.model.request.NoticeReq;
import org.springframework.data.domain.Page;

import java.util.Map;

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

    /**
     * 根据条件获取所有用户列表
     * @return
     */
    Page<Notice> getAllNoticeBySearch(NoticeListReq noticeListReq);

    /**
     * 添加菜单
     * @param noticeReq
     */
    Map add(NoticeReq noticeReq);

}
