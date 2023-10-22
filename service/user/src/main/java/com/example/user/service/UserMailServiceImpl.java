package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserMailAddDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import com.example.user.entity.UserMail;
import com.example.user.mapper.UserMailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @create 2023/10/14 17:02
 */
@Service
public class UserMailServiceImpl extends ServiceImpl<UserMailMapper, UserMail> implements UserMailService{

    @Override
    public String selectUserByLoginMail(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserMail> userMailLambdaQueryWrapper = Wrappers.lambdaQuery(UserMail.class).eq(UserMail::getMail, requestParam.getLoginName());
        UserMail userMail = baseMapper.selectOne(userMailLambdaQueryWrapper);
        return userMail.getUsername();
    }

    @Override
    public void add(UserMailAddDTO userMailAddDTO) {
        UserMail userMail = BeanUtil.copyProperties(userMailAddDTO, UserMail.class);
        baseMapper.insert(userMail);
    }

    @Override
    public void deleteByUsername(String username) {
        LambdaQueryWrapper<UserMail> userMailLambdaQueryWrapper = Wrappers.lambdaQuery(UserMail.class).eq(UserMail::getUsername, username);
        baseMapper.delete(userMailLambdaQueryWrapper);
    }


}
