package com.example.user.factory;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.enums.LoginStrategyEnum;
import com.example.user.strategy.entity.BaseLoginStrategy;
import com.example.user.strategy.login.EmailLoginStrategy;
import com.example.user.strategy.login.PhoneLoginStrategy;
import com.example.user.strategy.login.UsernameLoginStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @create 2023/10/14 20:19
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class LoginStrategyFactory {
//    @Getter
    private final UsernameLoginStrategy usernameLoginStrategy;

//    @Getter
    private final EmailLoginStrategy  emailLoginStrategy;

//    @Getter
    private final PhoneLoginStrategy phoneLoginStrategy;

    public BaseLoginStrategy getLoginStrategy(Integer loginType){
        if(loginType.equals(LoginStrategyEnum.USERNAME.getType())) {
            return usernameLoginStrategy;
        }else if(loginType.equals(LoginStrategyEnum.PHONE.getType())) {
            return phoneLoginStrategy;
        }else {
            return emailLoginStrategy;
        }
    }

//        Object o = null;
//        try {
//            Field field = getField(loginStrategyName);
//            o = field.get(this);
//        }catch (NoSuchFieldException | IllegalAccessException e){
//            log.error("找不到对应的登录策略，请检查该策略是否存在！！！");
//        }
//        return o;
}

//    public Object getLoginStrategyByType2(Integer type){
////        String loginStrategyName = LoginStrategyEnum.findNameByType(type);
////        Object o = null;
////        try {
////            Field field = getField(loginStrategyName);
////            o = field.get(this);
////        }catch (NoSuchFieldException | IllegalAccessException e){
////            log.error("找不到对应的登录策略，请检查该策略是否存在！！！");
////        }
////        return o;
//        return null;
//    }

