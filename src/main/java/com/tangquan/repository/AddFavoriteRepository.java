package com.tangquan.repository;

import com.tangquan.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 消息列表
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface AddFavoriteRepository extends JpaRepository<Favorite,Integer> {

    Favorite findOneById(Integer id);


    @Query("select a From Favorite a where a.customer_id = :customer_id and a.product_id = :product_id and a.product_type = :product_type")
    Favorite findOneByCondition(@Param("customer_id") String customer_id, @Param("product_id") String product_id, @Param("product_type") String product_type);
}
