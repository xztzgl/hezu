package com.tangquan.repository;

import com.tangquan.model.CodeMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 菜单规则
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface CodeMapRepository extends CrudRepository<CodeMap,Integer> {

//    CodeMap findAll();

//    /**
//     * 根据ID获取有效菜单
//     * @param id
//     * @return
//     */
//    @Query("select a from OrderRule a where a.id=?1 and a.status=1")
//    CodeMap findValidOneById(Integer id);

}
