package com.tangquan.system.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Author: wangfeng
 * Date: 17/12/12
 * Time: 下午3:28
 */
public class CommonUtils {

    /**
     * 加密
     * @param password
     * @return
     */
    public static String SHA256(String password) {
        return Hashing.md5()
                .hashString(password+"1dFlxLhiuLqnUZe9kA", StandardCharsets.UTF_8)
                .toString();
    }

}
