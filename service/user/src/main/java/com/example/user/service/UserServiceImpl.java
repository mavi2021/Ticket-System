package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dto.req.UserRegisterReqDTO;
import com.example.user.dto.req.UserUpdateReqDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import com.example.user.mapper.UserMapper;
import com.example.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.Service;


/**
 * @create 2023/7/10 10:04
 */
@org.springframework.stereotype.Service("userService")
@Service(version = "1.0.0",protocol = "dubbo")
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

//    @Reference(version = "1.0.0", check = false)
//    private OrderService orderService;

    @Override
    public UserQueryRespDTO queryUserByUserId(Long userId) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getId, userId);
        User user = baseMapper.selectOne(userLambdaQueryWrapper);
        return BeanUtil.copyProperties(user, UserQueryRespDTO.class);
    }

    @Override
    public UserQueryRespDTO queryUserByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUsername, username);
        User user = baseMapper.selectOne(userLambdaQueryWrapper);
        return BeanUtil.copyProperties(user, UserQueryRespDTO.class);
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = Wrappers.lambdaUpdate(User.class).eq(User::getUsername, requestParam.getUsername());
        User user = BeanUtil.copyProperties(requestParam, User.class);
        baseMapper.update(user, userLambdaUpdateWrapper);
    }

    public void add(UserRegisterReqDTO userRegisterReqDTO){
        User user = BeanUtil.copyProperties(userRegisterReqDTO, User.class);
        baseMapper.insert(user);
    }

    @Override
    public void deleteByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUsername, username);
        baseMapper.delete(userLambdaQueryWrapper);
    }


}
