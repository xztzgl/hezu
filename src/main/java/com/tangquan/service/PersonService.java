package com.tangquan.service;


import com.tangquan.model.AddPerson;
import com.tangquan.model.Person;
import com.tangquan.model.request.PersonListReq;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 管理员服务
 * Author: wangfeng
 * Date: 17/12/1
 * Time: 下午5:33
 */
public interface PersonService {

    /**
     * 获取所有用户列表
     * @return
     */
    Page<Person> getAllPerson(PersonListReq personListReq);

    /**
     * 根据条件获取所有用户列表
     * @return
     */
    Map getAllPersonBySearch(PersonListReq personListReq);

    /**
     * 添加
     * @param addPerson
     */
    Map add(AddPerson addPerson);

    /**
     * 修改
     * @param addPerson
     */
    Map update(AddPerson addPerson);

    /**删除
     * @param id
     */
    Map delete(Integer id);

}
