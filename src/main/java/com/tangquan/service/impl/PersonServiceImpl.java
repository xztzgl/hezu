package com.tangquan.service.impl;

import com.tangquan.model.AddPerson;
import com.tangquan.model.House;
import com.tangquan.model.Person;
import com.tangquan.model.request.HouseListReq;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public Map getAllPersonBySearch(PersonListReq personListReq) {

        //构建Query，注意用的是HQL语法
        String sql = "select t from Person t where t.id <> 0";

        Integer page = 0;
        Integer limit = 10;

        if(personListReq.getPage() != null && personListReq.getPage() != ""){//判断keyword是否为空
            page = Integer.valueOf(personListReq.getPage());
        }
        if(personListReq.getLimit() != null && personListReq.getLimit() != ""){//判断keyword是否为空
            limit = Integer.valueOf(personListReq.getLimit());
        }
        if(personListReq.getDistrict_id() != null && personListReq.getDistrict_id() != ""){//判断keyword是否为空
            sql += " and t.district_id = :district_id"; //根据下标
        }
        if(personListReq.getHousetype_id() != null && personListReq.getHousetype_id() != ""){//判断keyword是否为空
            sql += " and t.housetype_id = :housetype_id"; //根据下标
        }
        if(personListReq.getRental_id() != null && personListReq.getRental_id() != ""){//判断keyword是否为空
            sql += " and t.rental >= :rental_min"; //根据下标
            sql += " and t.rental < :rental_max"; //根据下标
        }

        Query query = em.createQuery(sql);// 这里做个更正

        query.setFirstResult(page * limit);
        query.setMaxResults(limit);


        if(personListReq.getDistrict_id() != null && personListReq.getDistrict_id() != ""){//判断keyword是否为空
            query.setParameter("district_id", Integer.valueOf(personListReq.getDistrict_id())); //根据下标
        }
        if(personListReq.getHousetype_id() != null && personListReq.getHousetype_id() != ""){//判断keyword是否为空
            query.setParameter("housetype_id", Integer.valueOf(personListReq.getHousetype_id())); //根据下标
        }
        if(personListReq.getRental_id() != null && personListReq.getRental_id() != ""){//判断keyword是否为空
            String rental_id = personListReq.getRental_id();
            // 与codeMap字典表对应
            switch (rental_id) {
                // 0-1500
                case "20401":
                    query.setParameter("rental_min", 0);
                    query.setParameter("rental_max", 1500);
                    break;

                // 1500-3000
                case "20402":
                    query.setParameter("rental_min", 1500);
                    query.setParameter("rental_max", 3000);
                    break;

                // 3000-4500
                case "20403":
                    query.setParameter("rental_min", 3000);
                    query.setParameter("rental_max", 4500);
                    break;

                // 4500-
                case "20404":
                    query.setParameter("rental_min", 4500);
                    query.setParameter("rental_max", 100000);
                    break;

                default:
                    query.setParameter("rental_min", 0); //根据下标
                    query.setParameter("rental_max", 0); //根据下标
                    break;
            }

        }


        //查询
        List<House> houseList = query.getResultList();
        Map pageList = new HashMap();
        pageList.put("content", houseList);
        pageList.put("page", page);
        pageList.put("limit", limit);

        return pageList;
    }

    @PersistenceContext
    private EntityManager em; //注入EntityManager

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
