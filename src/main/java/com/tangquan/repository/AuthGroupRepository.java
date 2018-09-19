package com.tangquan.repository;

import com.tangquan.model.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 授权
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午3:44
 */
@Repository
@Transactional
public interface AuthGroupRepository extends JpaRepository<AuthGroup,Integer> {

    AuthGroup findOneByTitle(String title);

    @Modifying
    @Query("update AuthGroup t set t.title = ?1,t.status = ?2 where t.id = ?3")
    int updateAuthGroup(String title,Integer status,Integer id);

    @Modifying
    @Query("update AuthGroup t set t.rules = ?1 where t.id = ?2")
    int accreditAuthGroup(String rules,Integer id);
}
