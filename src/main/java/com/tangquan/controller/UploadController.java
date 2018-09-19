package com.tangquan.controller;

//import com.tangquan.model.request.OrderRuleReq;

import com.tangquan.model.response.ApiResponse;
import com.tangquan.service.UploadMultiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.validation.annotation.Validated;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "UploadAPI", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.MULTIPART_FORM_DATA_VALUE)
@RequestMapping("/upload/")
public class UploadController {

    @Autowired
    UploadMultiService UploadMultiService;
    @ApiOperation(value = "上传")
    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String path = UploadMultiService.upload(file, "3", "hezu", request);

        return ApiResponse.ok(path);

    }

}
