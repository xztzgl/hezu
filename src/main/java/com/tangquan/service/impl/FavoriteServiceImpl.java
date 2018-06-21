package com.tangquan.service.impl;

import com.tangquan.model.Favorite;
import com.tangquan.model.FavoriteProductView;
import com.tangquan.model.request.FavoriteListReq;
import com.tangquan.repository.AddFavoriteRepository;
import com.tangquan.repository.FavoriteListRepository;
import com.tangquan.service.FavoriteService;
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
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {

//    @Autowired
//
//
//    HouseRepository houseRepository;
//
//    @Override
//    public Page<House> getAllHouse(HouseListReq houseListReq) {
//        Page<House> houseList = houseRepository.findAll(new PageRequest(Integer.valueOf(houseListReq.getPage()), Integer.valueOf(houseListReq.getLimit())));
//
//        return houseList;
//    }

    @Autowired
    FavoriteListRepository favoriteListRepository;

    @Override
    public Page<FavoriteProductView> getAllFavoriteBySearch(FavoriteListReq favoriteListReq) {
        Page<FavoriteProductView> favoriteList = favoriteListRepository.findByCustomer_id(favoriteListReq.getCustomer_id(), new PageRequest(Integer.valueOf(favoriteListReq.getPage()), Integer.valueOf(favoriteListReq.getLimit())));

        return favoriteList;
    }


    @Override
    public Map add(Favorite favorite) {
        Map res = new HashMap();

        addFavoriteRepository.save(favorite);

        res.put("success", true);
        return res;
    }

    @Autowired
    AddFavoriteRepository addFavoriteRepository;

    @Override
    public Map delete(Favorite favorite) {
        Map res = new HashMap();

        Favorite resulte = addFavoriteRepository.findOneByCondition(favorite.getCustomer_id(), favorite.getProduct_id(), favorite.getProduct_type());
        if (resulte != null) {

            addFavoriteRepository.delete(favorite);

            res.put("success", true);
        } else {
            res.put("success", false);
        }

        return res;
    }

//    @Override
//    public Map update(AddHouse addHouse) {
//        Map res = new HashMap();
//        AddHouse newHouse = new AddHouse();
//        BeanUtils.copyProperties(addHouse, newHouse);
//        addHouse.setPublish_id("21101");
//        addHouse.setCreate_time(new java.util.Date());
//        addHouseRepository.save(addHouse);
//
//        res.put("value", addHouse.getId());
//        res.put("success", true);
//        return res;
//    }
//
//    @Override
//    public Map delete(Integer id) {
//        Map res = new HashMap();
//        boolean exists = addHouseRepository.exists(id);
//        if (!exists) {
//            throw new ApiException(GatewayError.NOT_FOUND);
//        }
//        addHouseRepository.delete(id);
//        House house = houseRepository.findOneById(id);
//        if (house != null) {
//            addHouseRepository.delete(house.getId());
//        }
//
//        res.put("success", true);
//        return res;
//
//    }
//
//    @Autowired
//    AddOrderRepository addOrderRepository;
//    @Override
//    public Map order(Integer id) {
//        Map res = new HashMap();
//        boolean exists = addHouseRepository.exists(id);
//        if (!exists) {
//            throw new ApiException(GatewayError.NOT_FOUND);
//        }
//
//
//
//        res.put("success", true);
//        return res;
//
//    }

}
