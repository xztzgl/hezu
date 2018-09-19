package com.tangquan.service.impl;

import com.tangquan.framework.JWTHelper;
import com.tangquan.model.*;
import com.tangquan.model.request.AddAdminUserReq;
import com.tangquan.model.request.UpdateAdminUserRequest;
import com.tangquan.model.request.UpdateUserPasswordReq;
import com.tangquan.model.response.AdminUserResponse;
import com.tangquan.model.response.AllAdminUserResponse;
import com.tangquan.repository.*;
import com.tangquan.service.AdminUserService;
import com.tangquan.service.AuthGroupService;
import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import com.tangquan.system.utils.CommonUtils;
import com.tangquan.system.utils.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 上午10:42
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    AdminUserBankRepository adminUserBankRepository;

    @Autowired
    AuthGroupAccessRepository authGroupAccessRepository;

    @Autowired
    AdminLoginLogRepository adminLoginLogRepository;

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    OrderRuleRepository orderRuleRepository;

    @Autowired
    AuthGroupService authGroupService;

    @Autowired
    JWTHelper jwtHelper;

    @Override
    @Transactional
    public Integer addAdminUser(AddAdminUserReq adminUserReq) {
        Integer res = 0;
        AdminUser adminUser = new AdminUser();
        try {
            BeanUtils.copyProperties(adminUser, adminUserReq);

            AdminUser user = adminUserRepository.findOneByLoginName(adminUser.getLoginName());
            if (user != null) {
                throw new ApiException(GatewayError.BAD_ARGUMENT, adminUser.getLoginName() + "用户已存在。");
            }
            if (!adminUserReq.getPassword().equals(adminUserReq.getConfirmPassword())) {
                throw new ApiException(GatewayError.PWD_IS_NOT_SAME);
            }

            AuthGroup authGroup = authGroupRepository.findOne(adminUserReq.getGroupId());
            if (authGroup == null) {
                throw new ApiException(GatewayError.AUTH_GROUP_NOT_EXIST);
            }

            adminUser.setCreateTime(new Date());
            adminUser.setPassword(CommonUtils.SHA256(adminUserReq.getPassword()));
            AdminUser newAdminUser = adminUserRepository.save(adminUser);
            if (newAdminUser != null) {

                AuthGroupAccess authGroupAccess = new AuthGroupAccess();
                authGroupAccess.setUid(newAdminUser.getId());
                authGroupAccess.setGroupId(adminUserReq.getGroupId());
                authGroupAccessRepository.save(authGroupAccess);

                AdminUserBank adminUserBank = new AdminUserBank();

//                adminUserBank.setBankId(adminUserReq.getBankId());

                adminUserBank.setUid(newAdminUser.getId());
                adminUserBankRepository.save(adminUserBank);
                res = adminUserBank.getUid();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public AdminUserResponse getAdminUser(Integer id) {
        AdminUserResponse updateAdminUserResponse = new AdminUserResponse();
        AdminUser adminUser = adminUserRepository.findOne(id);
        try {
            BeanUtils.copyProperties(updateAdminUserResponse, adminUser);
            AuthGroupAccess authGroupAccess = authGroupAccessRepository.findOne(id);
            if (authGroupAccess != null) {
                updateAdminUserResponse.setGroupId(authGroupAccess.getGroupId());
            }
            AdminUserBank adminUserBank = adminUserBankRepository.findOne(id);
            if (adminUserBank != null) {
                updateAdminUserResponse.setBankId(adminUserBank.getBankId());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return updateAdminUserResponse;
    }


    @Override
    @Transactional
    public Integer delAdminUser(Integer id) {
        boolean exists = adminUserRepository.exists(id);
        if (!exists) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }
        boolean isSuper = isSuperAdmin(id);
        if (isSuper) {
            throw new ApiException(GatewayError.SUPER_ADMIN);
        }
        adminUserRepository.delete(id);
        authGroupAccessRepository.delete(id);
        adminUserBankRepository.delete(id);
        AdminLoginLog adminLoginLog = adminLoginLogRepository.findOneByUid(id);
        if (adminLoginLog != null) {
            adminLoginLogRepository.delete(adminLoginLog.getId());
        }
        return id;
    }

    @Override
    @Transactional
    public List<AllAdminUserResponse> getAllAdminUser() {
        List<AdminUser> adminUserList = adminUserRepository.findAll();
        List<AllAdminUserResponse> res = newArrayList();
        if (adminUserList.size() > 0) {
            adminUserList.forEach(x -> {
                AllAdminUserResponse allAdminUserResponse = new AllAdminUserResponse();
                try {
                    BeanUtils.copyProperties(allAdminUserResponse, x);
                    AdminUserBank adminUserBank = adminUserBankRepository.findOne(x.getId());
                    if (adminUserBank != null) {
                        allAdminUserResponse.setBankId(adminUserBank.getBankId());
                    }
                    AuthGroupAccess authGroupAccess = authGroupAccessRepository.findOne(x.getId());
                    if (authGroupAccess != null) {
                        AuthGroup authGroup = authGroupRepository.findOne(authGroupAccess.getGroupId());
                        if (authGroup != null) {
                            allAdminUserResponse.setGroupName(authGroup.getTitle());
                            allAdminUserResponse.setGroupType(authGroup.getGroupType());
                            allAdminUserResponse.setGroupId(authGroup.getId());
                        }
                    }
                    AdminLoginLog adminLoginLog = adminLoginLogRepository.findOneByUid(x.getId());
                    if (adminLoginLog != null) {
                        allAdminUserResponse.setLastLoginIP(adminLoginLog.getLastLoginIp());
                        allAdminUserResponse.setLastLoginTime(DateUtils.date2Str(adminLoginLog.getLastLoginTime()));
                    }
                    res.add(allAdminUserResponse);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }
        return res;
    }

    @Override
    @Transactional
    public Integer updateAdminUser(UpdateAdminUserRequest updateAdminUserRequest) {
        AdminUser oldAdminUser = adminUserRepository.findOne(updateAdminUserRequest.getId());
        if (oldAdminUser == null) {
            throw new ApiException(GatewayError.NOT_FOUND);
        }

        boolean isSuper = isSuperAdmin(updateAdminUserRequest.getId());
        if (isSuper) {
            throw new ApiException(GatewayError.SUPER_ADMIN);
        }

        AuthGroup authGroup = authGroupRepository.findOne(updateAdminUserRequest.getGroupId());
        if (authGroup == null) {
            throw new ApiException(GatewayError.AUTH_GROUP_NOT_EXIST);
        }

        oldAdminUser.setLoginName(updateAdminUserRequest.getLoginName());
        oldAdminUser.setUsername(updateAdminUserRequest.getUsername());
        oldAdminUser.setStatus(updateAdminUserRequest.getStatus());
        oldAdminUser.setId(updateAdminUserRequest.getId());
        if (!StringUtils.isEmpty(updateAdminUserRequest.getPassword())) {
            if (StringUtils.isEmpty(updateAdminUserRequest.getConfirmPassword())) {
                throw new ApiException(GatewayError.BAD_ARGUMENT, "确认密码不能为空");
            }
            if (!updateAdminUserRequest.getPassword().equals(updateAdminUserRequest.getConfirmPassword())) {
                throw new ApiException(GatewayError.PWD_IS_NOT_SAME);
            }
            oldAdminUser.setPassword(CommonUtils.SHA256(updateAdminUserRequest.getPassword()));
        }
        adminUserRepository.save(oldAdminUser);

        AuthGroupAccess authGroupAccess = new AuthGroupAccess();
        authGroupAccess.setUid(updateAdminUserRequest.getId());
        authGroupAccess.setGroupId(updateAdminUserRequest.getGroupId());
        authGroupAccessRepository.save(authGroupAccess);

        AdminUserBank adminUserBank = new AdminUserBank();
        adminUserBank.setBankId(updateAdminUserRequest.getBankId());
//        adminUserBank.setBankIds(updateAdminUserRequest.getBankIds());
//        adminUserBank.setBankId(updateAdminUserRequest.getBankIds().split("\\|")
//                [updateAdminUserRequest.getBankIds().split("\\|").length-1]);
        adminUserBank.setUid(updateAdminUserRequest.getId());
        adminUserBankRepository.save(adminUserBank);
        return adminUserBank.getUid();
    }

    @Override
    public AdminUserResponse login(String loginName, String pwd, String lastLoginIp) {
        AdminUser adminUser = adminUserRepository.findOneByLoginNameAndStatus(loginName,1);
        if (adminUser == null) {
            throw new ApiException(GatewayError.USER_NOT_EXIST);
        }
        AdminUser user = adminUserRepository.findOneByLoginNameAndPassword(loginName, CommonUtils.SHA256(pwd));
        if (user == null) {
            throw new ApiException(GatewayError.PWD_IS_NOT_RIGHT);
        }
        AdminUserResponse adminUserResponse = new AdminUserResponse();
        try {
            BeanUtils.copyProperties(adminUserResponse, user);
            //获取银行信息
            AdminUserBank adminUserBank = adminUserBankRepository.findOne(user.getId());
            if (adminUserBank != null) {
                BeanUtils.copyProperties(adminUserResponse, adminUserBank);
            }
            //生成token
            String jwtStr = jwtHelper.createJWT(null, adminUserResponse.getLoginName(), adminUserResponse.getBankId());
            adminUserResponse.setToken(jwtStr);
            //获取用户所有菜单权限
            AuthGroupAccess authGroupAccess = authGroupAccessRepository.findOne(adminUser.getId());
            if (authGroupAccess != null) {
                AuthGroup authGroup = authGroupRepository.findOne(authGroupAccess.getGroupId());
                adminUserResponse.setGroupId(authGroupAccess.getGroupId());
                if (authGroup != null && !authGroup.getRules().isEmpty()) {
                    String[] str = authGroup.getRules().split(",");
                    List<OrderRule> orderRuleList = newArrayList();
                    for (String id : str) {
                        OrderRule authRule = orderRuleRepository.findValidOneById(Integer.valueOf(id));
                        if (authRule != null) {
                            orderRuleList.add(authRule);
                        }
                    }
                    adminUserResponse.setOrderRuleList(orderRuleList);
                }
            }
            //更新登录日志
            loginLog(user.getId(), lastLoginIp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return adminUserResponse;
    }

    @Override
    public Integer updateUserPassword(UpdateUserPasswordReq updateUserPasswordReq) {
        AdminUser adminUser = adminUserRepository.findOne(updateUserPasswordReq.getUid());
        Optional<AdminUser> adminUserOpt = Optional.ofNullable(adminUser);
        if (!adminUserOpt.isPresent()) {
            throw new ApiException(GatewayError.USER_NOT_EXIST);
        }
        boolean pwdRight = CommonUtils.SHA256(updateUserPasswordReq.getOldPassword()).equals(adminUser.getPassword());
        if (!pwdRight) {
            throw new ApiException(GatewayError.PWD_IS_NOT_RIGHT);
        }
        if (!updateUserPasswordReq.getNewPassword().equals(updateUserPasswordReq.getConfirmPassword())) {
            throw new ApiException(GatewayError.BAD_ARGUMENT, "密码不一致");
        }
        adminUser.setPassword(CommonUtils.SHA256(updateUserPasswordReq.getNewPassword()));
        adminUserRepository.save(adminUser);
        return adminUser.getId();
    }

    @Override
    public boolean userAuthOrder(Integer uid, String address) {
        boolean res = false;
        AdminUser adminUser = adminUserRepository.findOne(uid);
        if (adminUser == null) {
            throw new ApiException(GatewayError.USER_NOT_EXIST);
        }
        AuthGroupAccess authGroupAccess = authGroupAccessRepository.findOne(uid);
        if (authGroupAccess != null) {
            List<String> addressList = authGroupService
                    .getRulesNameByGroup(authGroupAccess.getGroupId());
            if (addressList != null && addressList.size() > 0) {
                for (String str : addressList) {
                    if (address.indexOf(str) == 0) {
                        res = true;
                        break;
                    }
                }
            }
        }
        return res;
    }


    /**
     * 更新登录日志
     *
     * @param uid         用户ID
     * @param lastLoginIp
     */
    private void loginLog(Integer uid, String lastLoginIp) {
        AdminLoginLog adminLoginLog = adminLoginLogRepository.findOneByUid(uid);
        if (adminLoginLog != null) {
            adminLoginLog.setLastLoginIp(lastLoginIp);
            adminLoginLog.setUid(uid);
            adminLoginLog.setLastLoginTime(new Date());
            adminLoginLogRepository.save(adminLoginLog);
        } else {
            AdminLoginLog loginLog = new AdminLoginLog();
            loginLog.setUid(uid);
            loginLog.setLastLoginIp(lastLoginIp);
            loginLog.setLastLoginTime(new Date());
            adminLoginLogRepository.save(loginLog);
        }
    }

    /**
     * 判断当前用户是否是超级管理员
     *
     * @param uid
     * @return
     */
    private boolean isSuperAdmin(Integer uid) {
        boolean res = false;
        AdminUser adminUser = adminUserRepository.findOne(uid);
        if (adminUser != null) {
            AuthGroupAccess authGroupAccess = authGroupAccessRepository.findOne(adminUser.getId());
            if (authGroupAccess != null) {
                AuthGroup authGroup = authGroupRepository.findOne(authGroupAccess.getGroupId());
                if (authGroup.getGroupType() == AuthGroup.SUPER_ADMIN) {
                    res = true;
                }
            }
        }
        return res;
    }

}
