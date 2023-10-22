package com.example.user.toolkit;

import cn.hutool.jwt.JWTUtil;
import com.example.user.constant.UserConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @create 2023/10/14 17:32
 */
public final class TokenUtil {

    private static final String KEY =  "ssjjsai";

    public static String generateJWT(Long id, String username, String realname){
        Map<String, Object> tokenMap = new HashMap<String, Object>(){
            {
                put(UserConstant.USER_ID_KEY, id);
                put(UserConstant.USER_NAME_KEY, username);
                put(UserConstant.REAL_NAME_KEY, realname);
            }
        };
        return JWTUtil.createToken(tokenMap, KEY.getBytes());
    }
}
