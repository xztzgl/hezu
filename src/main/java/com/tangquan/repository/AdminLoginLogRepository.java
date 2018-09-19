package com.tangquan.repository;

import com.tangquan.model.AdminLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员登录日志
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 上午10:39
 */
@Repository
public interface AdminLoginLogRepository extends JpaRepository<AdminLoginLog,Integer> {

    AdminLoginLog findOneByUid(Integer uid);
}
