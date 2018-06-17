package com.tangquan.service.impl;

import com.tangquan.model.House;
import com.tangquan.model.request.HouseListReq;
import com.tangquan.repository.HouseRepository;
import com.tangquan.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {

    @Autowired


    HouseRepository houseRepository;

    @Override
    public Page<House> getAllHouse(HouseListReq houseListReq) {
        Page<House> houseList = houseRepository.findAll(new PageRequest(Integer.valueOf(houseListReq.getPage()), Integer.valueOf(houseListReq.getLimit())));

        return houseList;
    }

}
