package com.tangquan.service.impl;

import com.tangquan.model.CodeMap;
import com.tangquan.repository.CodeMapRepository;
import com.tangquan.service.CodeMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("codeMapService")
public class CodeMapServiceImpl implements CodeMapService {

    @Autowired
    CodeMapRepository codeMapRepository;

    @Override
    public List<CodeMap> getAllCodeMap() {
        List<CodeMap> allCodeMap = (List<CodeMap>) codeMapRepository.findAll();
        return allCodeMap;
    }

}
