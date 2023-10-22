package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.service.UserPhoneService;
import com.example.user.strategy.AbstractLoginStrategy;
import com.example.user.strategy.entity.BaseLoginStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 19:26
 */
@Component
public class PhoneLoginStrategy extends BaseLoginStrategy{

    @Resource
    private UserPhoneService userPhoneService;

    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return userPhoneService.selectUserByLoginPhone(requestParam);
    }
}
