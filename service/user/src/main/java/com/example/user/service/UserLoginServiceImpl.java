package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserMailAddDTO;
import com.example.user.dto.req.UserPhoneAddDTO;
import com.example.user.dto.req.UserRegisterReqDTO;
import com.example.user.dto.resp.UserLoginRespDTO;
import com.example.user.dto.resp.UserRegisterRespDTO;
import com.example.user.entity.User;
import com.example.user.executor.LoginStrategyExecutor;
import com.example.user.mapper.UserMapper;
import com.example.user.toolkit.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserService userService;

    @Resource
    private UserMailService userMailService;

    @Resource
    private UserPhoneService userPhoneService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginStrategyExecutor loginStrategyExecutor;

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        String username = loginStrategyExecutor.getUsername(requestParam);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username)
                .eq(User::getPassword, requestParam.getPassword());
        User user = userMapper.selectOne(userLambdaQueryWrapper);
//        if(BeanUtil.isEmpty(user)){
//            throw new Exception("账号不存在或密码错误");
//        }
        String accessToken = TokenUtil.generateJWT(user.getId(), user.getUsername(), user.getRealName());
        return UserLoginRespDTO.builder()
                .userId(user.getId())
                .username(username)
                .realName(user.getRealName())
                .accessToken(accessToken)
                .build();
    }

    @Override
    public UserLoginRespDTO checkLogin(String accessToken) {
        return null;
    }

    @Override
    public void logout(String accessToken) {

    }

    @Override
    public Boolean hasUsername(String username) {
        return null;
    }

    @Override
    @Transactional
    public UserRegisterRespDTO register(UserRegisterReqDTO requestParam) {
        userService.add(requestParam);
        if(BeanUtil.isNotEmpty(requestParam.getMail())){
            UserMailAddDTO userMailAddDTO = BeanUtil.copyProperties(requestParam, UserMailAddDTO.class);
            userMailService.add(userMailAddDTO);
        }
        if(BeanUtil.isNotEmpty(requestParam.getPhone())){
            UserPhoneAddDTO userPhoneAddDTO = BeanUtil.copyProperties(requestParam, UserPhoneAddDTO.class);
            userPhoneService.add(userPhoneAddDTO);
        }
        return BeanUtil.copyProperties(requestParam, UserRegisterRespDTO.class);
    }
}
