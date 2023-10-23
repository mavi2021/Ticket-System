package com.example.user.strategy.login;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.service.UserMailService;
import com.example.user.strategy.entity.BaseLoginStrategy;
import org.springframework.stereotype.Component;

/**
 * @create 2023/10/14 19:27
 */
@Component
public class EmailLoginStrategy extends BaseLoginStrategy{

    private final UserMailService userMailService;

    public EmailLoginStrategy(UserMailService userMailServiceImpl){
        this.userMailService = userMailServiceImpl;
    }

    @Override
    public String getUserName(UserLoginReqDTO requestParam) {
        return userMailService.selectUserByLoginMail(requestParam);
    }
}