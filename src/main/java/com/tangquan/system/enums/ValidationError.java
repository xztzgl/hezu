package com.tangquan.system.enums;

import com.tangquan.system.exception.ApiException;
import com.tangquan.service.Error;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ValidationError implements Error {
  INVALID_NAME("20001", "无效的名称"),
  INVALID_AUTH_GROUP_TITLE("20002","权限组名称不能为空"),
  INVALID_AUTH_GROUP_STATUS("20003","权限组状态不能为空"),
  INVALID_LOGIN_NAME("20004","登录名不能为空"),
  INVALID_USERNAME("20005","管理员用户名不能为空"),
  INVALID_PWD("20006","管理员密码不能为空"),
  INVALID_STATUS("20008","状态不能为空"),
  INVALID_CREATE_TIME("20009","创建时间不能为空"),
  INVALID_GROUP("20010","所属权限组不能为空"),
  ORDER_RULE_NAME_EMPTY("20011","规则名称不能为空"),
  ORDER_RULE_TITLE_EMPTY("20012","菜单标题不能为空"),
  ORDER_RULE_PID_EMPTY("20013","父级ID不能为空"),
  ORDER_RULE_ICON_EMPTY("20014","图标不能为空"),
  UID_EMPTY("20015","用户ID不能为空"),
  OLD_PWD_EMPTY("20016","旧密码不能为空"),
  NEW_PWD_EMPTY("20017","新密码不能为空"),
  CONFIRM_NEW_PWD_EMPTY("20018","确认密码不能为空"),
  AUTH_GROUP_TYPE_EMPTY("20019","权限组类型不能为空"),
  ID_EMPTY("20020","ID不能为空"),
  ADDRESS_EMPTY("20021","访问地址不能为空");

  private final String code;
  private final String value;
  private static final Map<String, ValidationError> NAME_KEYED_MAP = new HashMap<>();

  static {
    try {
      for (Field field : ValidationError.class.getFields()) {
        if (field.getType() == ValidationError.class) {
          NAME_KEYED_MAP.put(field.getName(), (ValidationError) field.get(ValidationError.class));
        }
      }
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  ValidationError(String code, String value) {
    this.code = code;
    this.value = value;
  }

  public String getCode() {
    return code;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String getErrorCode() {
    return getCode();
  }

  @Override
  public String getErrorMessage() {
    return getValue();
  }

  public static ValidationError find(String name) {
    return Optional.ofNullable(NAME_KEYED_MAP.get(name))
            .orElseThrow(() -> new ApiException(GatewayError.INTERNAL_ERROR));
  }
}
