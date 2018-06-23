package com.tangquan.controller;

import com.tangquan.model.*;
import com.tangquan.model.request.*;
import com.tangquan.model.response.ApiResponse;
import com.tangquan.repository.AddHouseRepository;
import com.tangquan.repository.AddPersonRepository;
import com.tangquan.service.CustomerService;
import com.tangquan.service.HouseService;
import com.tangquan.service.NoticeService;
import com.tangquan.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: Djoz
 * Date: 18/06/06
 * Time: 下午5:50
 */
@RestController
@Api(description = "后台-管理API", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/server-web-management/")
public class ServerWebManagementController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "用户信息管理菜单")
    @PostMapping("/user")
    public ApiResponse<Page<Customer>> user(@Validated @RequestBody CustomerListReq customerListReq) {
        if (Objects.nonNull(customerListReq.getSearch()) && customerListReq.getSearch() != "") {
            return ApiResponse.ok(customerService.getCustomerByUsername(customerListReq.getSearch()));
        } else {
            return ApiResponse.ok(customerService.getAllCustomer(customerListReq));
        }

    }


    @Autowired
    HouseService houseService;
    @Autowired
    AddHouseRepository addHouseRepository;
    @Autowired
    AddPersonRepository addPersonRepository;

    @ApiOperation(value = "房屋信息管理菜单")
    @PostMapping("/house")
    public ApiResponse<Page<House>> house(@Validated @RequestBody HouseListReq houseListReq) {
        return ApiResponse.ok(houseService.getAllHouse(houseListReq));
    }

    @Transactional
    @ApiOperation(value = "房屋信息确认")
    @PutMapping("/house/confirm/{id}")
    public ApiResponse<Map> houseConfirm(@ApiParam(value = "房屋ID", required = true) @PathVariable Integer id) {
        Map res = new HashMap();
        addHouseRepository.updatePublish("21104", id);

        AddHouse house = addHouseRepository.findOneById(id);
        String publish_id = house.getPublish_id();
        if (publish_id.equals("21104")) {
            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return ApiResponse.ok(res);
    }

    @Transactional
    @ApiOperation(value = "合租者信息确认")
    @PutMapping("/person/confirm/{id}")
    public ApiResponse<Map> personConfirm(@ApiParam(value = "合租者ID", required = true) @PathVariable Integer id) {
        Map res = new HashMap();
        addPersonRepository.updatePublish("21104", id);

        AddPerson person = addPersonRepository.findOneById(id);
        String publish_id = person.getPublish_id();
        if (publish_id.equals("21104")) {
            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return ApiResponse.ok(res);
    }

    @Transactional
    @ApiOperation(value = "房屋信息删除")
    @DeleteMapping("/house/delete/{id}")
    public ApiResponse<Map> houseDelete(@ApiParam(value = "房屋ID", required = true) @PathVariable Integer id) {
        Map res = new HashMap();
        AddHouse willDelete = addHouseRepository.findOneById(id);

        addHouseRepository.delete(id);

        AddHouse house = addHouseRepository.findOneById(id);
        if (house == null) {
            NoticeReq noticeReq = new NoticeReq();
            noticeReq.setCustomer_id(Integer.valueOf(willDelete.getCreator_id()));
            noticeReq.setMethod(21201);
            noticeReq.setProduct_id(willDelete.getId());
            noticeReq.setProduct_type(1);
            noticeReq.setText("您发布的信息因违规已被管理员删除，由此给您带来不便，请您谅解！");

            noticeService.add(noticeReq, 21302);
            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return ApiResponse.ok(res);
    }

    @Transactional
    @ApiOperation(value = "合租者信息删除")
    @DeleteMapping("/person/delete/{id}")
    public ApiResponse<Map> personDelete(@ApiParam(value = "合租者ID", required = true) @PathVariable Integer id) {
        Map res = new HashMap();
        AddPerson willDelete = addPersonRepository.findOneById(id);
        addPersonRepository.delete(id);

        AddPerson person = addPersonRepository.findOneById(id);

        if (person == null) {
            NoticeReq noticeReq = new NoticeReq();
            noticeReq.setCustomer_id(Integer.valueOf(willDelete.getCreator_id()));
            noticeReq.setMethod(21201);
            noticeReq.setProduct_id(willDelete.getId());
            noticeReq.setProduct_type(2);
            noticeReq.setText("您发布的信息因违规已被管理员删除，由此给您带来不便，请您谅解！");

            noticeService.add(noticeReq, 21302);
            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return ApiResponse.ok(res);
    }

    @Autowired
    PersonService personService;

    @ApiOperation(value = "找人信息管理菜单")
    @PostMapping("/partner")
    public ApiResponse<Page<Person>> partner(@Validated @RequestBody PersonListReq personListReq) {
        return ApiResponse.ok(personService.getAllPerson(personListReq));
    }


    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "通知信息管理菜单")
    @PostMapping("/notice")
    public ApiResponse<Page<Notice>> notice(@Validated @RequestBody NoticeListReq noticeListReq) {
        return ApiResponse.ok(noticeService.getAllNotice(noticeListReq));
    }

    @ApiOperation(value = "添加通知信息")
    @PostMapping("/notice/add")
    public ApiResponse<Map> add(@Validated @RequestBody NoticeReq noticeReq) {
        return ApiResponse.ok(noticeService.add(noticeReq, 21301));
    }

}
