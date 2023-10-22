package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dto.req.UserDeletionReqDTO;
import com.example.user.dto.resp.UserDeletionRespDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import com.example.user.entity.UserDeletion;
import com.example.user.mapper.UserDeletionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @create 2023/10/14 15:39
 */
@Service
public class UserDeletionServiceImpl extends ServiceImpl<UserDeletionMapper, UserDeletion> implements UserDeletionService{

    @Autowired
    private UserService userService;
    @Autowired
    private UserMailService userMailService;
    @Autowired
    private UserPhoneService userPhoneService;

    @Override
    public Integer queryUserDeletionNum(Integer idType, String idCard) {
        LambdaQueryWrapper<UserDeletion> userLambdaQueryWrapper = Wrappers.lambdaQuery(UserDeletion.class).eq(UserDeletion::getIdType, idType)
                .eq(UserDeletion::getIdCard, idCard).select();
        return Math.toIntExact(baseMapper.selectCount(userLambdaQueryWrapper));
    }

    @Override
    @Transactional
    public void deletionUser(UserDeletionReqDTO requestParam) {
        UserDeletionRespDTO userDeletionRespDTO = selectUserDeletion(requestParam);
        UserDeletion userDeletion = BeanUtil.copyProperties(userDeletionRespDTO, UserDeletion.class);
        baseMapper.insert(userDeletion);
        userService.deleteByUsername(requestParam.getUsername());
        userMailService.deleteByUsername(requestParam.getUsername());
        userPhoneService.deleteByUsername(requestParam.getUsername());
    }

    @Override
    public UserDeletionRespDTO selectUserDeletion(UserDeletionReqDTO requestParam){
        UserQueryRespDTO userQueryRespDTO = userService.queryUserByUsername(requestParam.getUsername());
        return BeanUtil.copyProperties(userQueryRespDTO, UserDeletionRespDTO.class);
    }


}
