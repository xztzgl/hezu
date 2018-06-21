package com.tangquan.repository;

import com.tangquan.model.FavoriteProductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface FavoriteListRepository extends JpaRepository<FavoriteProductView,Integer> {


    @Query(value = "select a From FavoriteProductView a where a.customer_id = :customer_id ")
    Page<FavoriteProductView> findByCustomer_id(@Param("customer_id") String customer_id, Pageable pageRequest);

}
