package com.tangquan.repository;

import com.tangquan.model.AdminUserBank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 管理员和银行对应关系操作
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:33
 */
public interface AdminUserBankRepository extends JpaRepository<AdminUserBank,Integer> {
}
