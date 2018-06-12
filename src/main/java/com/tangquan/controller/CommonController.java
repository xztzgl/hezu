package com.tangquan.controller;

//import com.tangquan.model.request.OrderRuleReq;

import com.tangquan.model.CodeMap;
import com.tangquan.model.District;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.CodeMapService;
import com.tangquan.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import org.springframework.validation.annotation.Validated;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "CommonAPI", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/common/")
public class CommonController {

    @Autowired
    CodeMapService CodeMapService;


    @ApiOperation(value = "数据字典列表")
    @GetMapping("/code-map")
    public ApiResponse<List<CodeMap>> codeMap() {
        return ApiResponse.ok(CodeMapService.getAllCodeMap());
    }


    @Autowired
    DistrictService DistrictService;

    @ApiOperation(value = "片区列表")
    @GetMapping("/district")
    public ApiResponse<List<District>> district() {
        return ApiResponse.ok(DistrictService.getAllDistrict());
    }

}
