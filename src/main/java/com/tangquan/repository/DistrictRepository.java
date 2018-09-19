package com.tangquan.repository;

import com.tangquan.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 菜单规则
 * Author: wangfeng
 * Date: 17/12/13
 * Time: 下午5:47
 */
@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {


}
