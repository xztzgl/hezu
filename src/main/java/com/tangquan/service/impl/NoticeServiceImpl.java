package com.tangquan.service.impl;

import com.tangquan.model.AddNotice;
import com.tangquan.model.Notice;
import com.tangquan.model.request.NoticeListReq;
import com.tangquan.model.request.NoticeReq;
import com.tangquan.repository.AddHouseRepository;
import com.tangquan.repository.AddNoticeRepository;
import com.tangquan.repository.AddPersonRepository;
import com.tangquan.repository.NoticeRepository;
import com.tangquan.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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


    @Override
    public Page<Notice> getAllNoticeBySearch(NoticeListReq noticeListReq) {
        Page<Notice> favoriteList = noticeRepository.findByCustomer_id(noticeListReq.getCustomer_id(), new PageRequest(Integer.valueOf(noticeListReq.getPage()), Integer.valueOf(noticeListReq.getLimit())));

        return favoriteList;
    }

    @Autowired

    AddNoticeRepository addNoticeRepository;

    @Autowired
    AddHouseRepository addHouseRepository;

    @Autowired
    AddPersonRepository addPersonRepository;

    @Transactional
    @Override
    public Map add(NoticeReq noticeReq) {
        Map res = new HashMap();
        AddNotice addNotice = new AddNotice();
        BeanUtils.copyProperties(noticeReq, addNotice);
        addNotice.setType(21301);
        addNotice.setCreate_time(new java.util.Date());
        addNoticeRepository.save(addNotice);

        if (noticeReq.getProduct_type().equals(1)) {
            addHouseRepository.updatePublish("21102", noticeReq.getProduct_id());
        }
        if (noticeReq.getProduct_type().equals(2)) {
            addPersonRepository.updatePublish("21102", noticeReq.getProduct_id());
        }

        res.put("value", addNotice.getId());
        res.put("success", true);
        return res;
    }

}
