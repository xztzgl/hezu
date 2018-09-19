package com.tangquan.repository;

import com.tangquan.model.AddHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 消息列表
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface AddHouseRepository extends JpaRepository<AddHouse,Integer> {

    AddHouse findOneById(Integer id);

    @Modifying
    @Query("update AddHouse t set t.publish_id = ?1 where t.id = ?2")
    int updatePublish(String publish_id,Integer id);
}
