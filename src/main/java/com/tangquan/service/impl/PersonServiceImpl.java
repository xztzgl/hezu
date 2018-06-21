package com.tangquan.service.impl;

import com.tangquan.model.AddPerson;
import com.tangquan.model.Person;
import com.tangquan.model.request.PersonListReq;
import com.tangquan.repository.AddPersonRepository;
import com.tangquan.repository.PersonRepository;
import com.tangquan.service.PersonService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:49
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired


    PersonRepository personRepository;

    @Override
    public Page<Person> getAllPerson(PersonListReq personListReq) {
        Page<Person> personList = personRepository.findAll(new PageRequest(Integer.valueOf(personListReq.getPage()), Integer.valueOf(personListReq.getLimit())));

        return personList;
    }

    @Autowired
    AddPersonRepository addPersonRepository;
    @Override
    public Map add(AddPerson addPerson) {
        Map res = new HashMap();
        AddPerson newHouse = new AddPerson();
        BeanUtils.copyProperties(addPerson, newHouse);
        addPerson.setId(0);
        addPerson.setPublish_id("21101");
        addPerson.setCreate_time(new java.util.Date());
        addPersonRepository.save(addPerson);

        res.put("value", addPerson.getId());
        res.put("success", true);
        return res;
    }

    @Override
    public Map update(AddPerson addPerson) {
        Map res = new HashMap();
        AddPerson newHouse = new AddPerson();
        BeanUtils.copyProperties(addPerson, newHouse);
        addPerson.setPublish_id("21101");
        addPerson.setCreate_time(new java.util.Date());
        addPersonRepository.save(addPerson);

        res.put("value", addPerson.getId());
        res.put("success", true);
        return res;
    }

    @Override
    public Map delete(Integer id) {
        Map res = new HashMap();
        boolean exists = addPersonRepository.exists(id);
        if (!exists) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        addPersonRepository.delete(id);
        Person house = personRepository.findOneById(id);
        if (house != null) {
            addPersonRepository.delete(house.getId());
        }

        res.put("success", true);
        return res;

    }

}
