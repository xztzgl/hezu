package com.tangquan.repository;

import com.tangquan.model.AuthGroupAccess;
import org.springframework.data.repository.CrudRepository;

/**
 * 管理与权限关系操作
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午2:46
 */
public interface AuthGroupAccessRepository extends CrudRepository<AuthGroupAccess,Integer> {

}
