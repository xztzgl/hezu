package com.tangquan.service.impl;

import com.tangquan.model.Notice;
import com.tangquan.model.request.NoticeListReq;
import com.tangquan.repository.NoticeRepository;
import com.tangquan.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired


    NoticeRepository noticeRepository;

    @Override
    public Page<Notice> getAllNotice(NoticeListReq noticeListReq) {
        Page<Notice> noticeList = noticeRepository.findAll(new PageRequest(Integer.valueOf(noticeListReq.getPage()), Integer.valueOf(noticeListReq.getLimit())));

        return noticeList;
    }

}
