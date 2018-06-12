package com.tangquan.repository;

import com.tangquan.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 管理员操作
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 上午10:37
 */
public interface AdminUserRepository extends JpaRepository<AdminUser,Integer> {

    AdminUser findOneByLoginName(String loginName);

    AdminUser findOneByLoginNameAndPassword(String loginName, String password);

    AdminUser findOneByLoginNameAndStatus(String loginName,Integer status);
}
