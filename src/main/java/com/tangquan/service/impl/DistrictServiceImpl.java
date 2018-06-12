package com.tangquan.service.impl;

import com.tangquan.model.District;
import com.tangquan.repository.DistrictRepository;
import com.tangquan.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistrict() {
        List<District> allDistrict = districtRepository.findAll();
        return allDistrict;
    }

}
