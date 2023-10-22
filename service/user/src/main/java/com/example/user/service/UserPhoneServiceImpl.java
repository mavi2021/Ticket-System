package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserPhoneAddDTO;
import com.example.user.entity.UserPhone;
import com.example.user.mapper.UserPhoneMapper;
import org.springframework.stereotype.Service;

/**
 * @create 2023/10/14 22:01
 */
@Service
public class UserPhoneServiceImpl extends ServiceImpl<UserPhoneMapper, UserPhone> implements UserPhoneService{

    @Override
    public String selectUserByLoginPhone(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserPhone> userPhoneLambdaQueryWrapper = Wrappers.lambdaQuery(UserPhone.class)
                .eq(UserPhone::getPhone, requestParam.getLoginName());
        UserPhone userPhone = baseMapper.selectOne(userPhoneLambdaQueryWrapper);
        return userPhone.getUsername();
    }

    @Override
    public void add(UserPhoneAddDTO userPhoneAddDTO) {
        UserPhone userPhone = BeanUtil.copyProperties(userPhoneAddDTO, UserPhone.class);
        baseMapper.insert(userPhone);
    }

    @Override
    public void deleteByUsername(String username) {
        LambdaQueryWrapper<UserPhone> userPhoneLambdaQueryWrapper = Wrappers.lambdaQuery(UserPhone.class).eq(UserPhone::getUsername, username);
        baseMapper.delete(userPhoneLambdaQueryWrapper);
    }


}
