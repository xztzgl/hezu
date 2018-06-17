package com.tangquan.service.impl;

import com.tangquan.model.Person;
import com.tangquan.model.request.PersonListReq;
import com.tangquan.repository.PersonRepository;
import com.tangquan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
