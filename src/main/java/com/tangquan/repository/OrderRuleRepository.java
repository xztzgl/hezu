package com.tangquan.repository;

import com.tangquan.model.OrderRule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单规则
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface OrderRuleRepository extends CrudRepository<OrderRule,Integer> {

    OrderRule findByTitle(String title);

    /**
     * 根据ID获取有效菜单
     * @param id
     * @return
     */
    @Query("select a from OrderRule a where a.id=?1 and a.status=1")
    OrderRule findValidOneById(Integer id);

    /**
     * 根据pid获取菜单规则
     * @param pid
     * @return
     */
    List<OrderRule> findOneByPidOrderBySortAsc(Integer pid);
}
