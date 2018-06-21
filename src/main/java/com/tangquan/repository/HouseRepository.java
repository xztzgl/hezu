package com.tangquan.repository;

import com.tangquan.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息列表
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface HouseRepository extends JpaRepository<House,Integer> {

    House findOneById(Integer id);


//    @Query("select a from House a where a.district_id=?1 and a.housetype_id=1")
//    Page<House> findAllBySearch(PageRequest pageRequest);

}
