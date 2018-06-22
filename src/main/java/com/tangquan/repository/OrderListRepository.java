package com.tangquan.repository;

import com.tangquan.model.OrderProductView;
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
public interface OrderListRepository extends JpaRepository<OrderProductView,Integer> {


    @Query(value = "select a From OrderProductView a where a.customer_id = :customer_id and a.status_id = :status_id ")
    Page<OrderProductView> findByCustomer_id(@Param("customer_id") String customer_id, @Param("status_id") String status_id, Pageable pageRequest);

    @Query(value = "select a From Favorite a where a.product_id = :product_id and a.customer_id = :customer_id ")
    OrderProductView findOneOrderByProductId(@Param("product_id") String product_id, @Param("customer_id") String customer_id);

}
