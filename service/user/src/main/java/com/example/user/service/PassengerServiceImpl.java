package com.example.user.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.common.exception.ServiceException;
import com.example.user.dto.req.PassengerRemoveReqDTO;
import com.example.user.dto.req.PassengerReqDTO;
import com.example.user.dto.resp.PassengerActualRespDTO;
import com.example.user.dto.resp.PassengerRespDTO;
import com.example.user.entity.Passenger;
import com.example.user.enums.VerifyStatusEnum;
import com.example.user.mapper.PassengerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @create 2023/9/20 21:07
 */
@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service("passengerService")
@Service(version = "1.0.0",protocol = "dubbo")
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService{

    private final PlatformTransactionManager transactionManager;

//    private final DistributedCache distributedCache;

    @Transactional
    @Override
    public List<PassengerRespDTO> listPassengerQueryByUsername(String username) {
        LambdaQueryWrapper<Passenger> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Passenger::getUsername,username);
        List<Passenger> actualUserPassengerListStr = baseMapper.selectList(queryWrapper);
        return actualUserPassengerListStr.stream().map(actualUserPassenger->{
            PassengerRespDTO passengerRespDTO = new PassengerRespDTO();
            CopyOptions options = CopyOptions.create()
                    .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
                    .setIgnoreError(true);     // 忽略复制过程中出现的错误
            BeanUtil.copyProperties(actualUserPassenger,passengerRespDTO,options);
            return passengerRespDTO;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<PassengerActualRespDTO> listPassengerQueryByIds(String username, List<Long> ids) {
        LambdaQueryWrapper<Passenger> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Passenger::getUsername,username);
        List<Passenger> actualUserPassengerListStr = baseMapper.selectList(queryWrapper);
        System.out.println(actualUserPassengerListStr);
        return actualUserPassengerListStr.stream()
                .filter(passenger-> ids.contains(passenger.getId()))
                .map(actualUserPassenger->{
//                     new PassengerActualRespDTO();
//                    CopyOptions options = CopyOptions.create()
//                            .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
//                            .setIgnoreError(true);     // 忽略复制过程中出现的错误
                    return BeanUtil.copyProperties(actualUserPassenger, PassengerActualRespDTO.class);
                }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void savePassenger(PassengerReqDTO requestParam) {
        String username = "zhangsan";
        try{
            Passenger passenger = Passenger.builder().build();
            CopyOptions options = CopyOptions.create()
                    .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
                    .setIgnoreError(true);     // 忽略复制过程中出现的错误
            BeanUtil.copyProperties(requestParam,passenger,options);
            passenger.setCreateDate(new Date());
            passenger.setVerifyStatus(VerifyStatusEnum.REVIEWED.getCode());
            int inserted = baseMapper.insert(passenger);
            if (!SqlHelper.retBool(inserted)) {
                throw new ServiceException(String.format("[%s] 新增乘车人失败", username));
            }
        }catch (Exception ex){
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), JSON.toJSONString(requestParam));
            } else {
                log.error("[{}] 新增乘车人失败，请求参数：{}", username, JSON.toJSONString(requestParam), ex);
            }
        }
    }

    @Transactional
    @Override
    public void updatePassenger(PassengerReqDTO requestParam) {
        String username = "zhangsan";
        try {
            Passenger passenger = Passenger.builder().build();
            CopyOptions options = CopyOptions.create()
                    .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
                    .setIgnoreError(true);     // 忽略复制过程中出现的错误
            BeanUtil.copyProperties(requestParam,passenger,options);
            LambdaUpdateWrapper<Passenger> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Passenger::getId,requestParam.getId())
                        .eq(Passenger::getUsername,username);
            int updated = baseMapper.update(passenger, updateWrapper);
            if(!SqlHelper.retBool(updated)){
                throw new ServiceException(String.format("[%s] 修改乘车人失败", username));
            }
        }catch (Exception ex){
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), com.alibaba.fastjson2.JSON.toJSONString(requestParam));
            } else {
                log.error("[{}] 修改乘车人失败，请求参数：{}", username, com.alibaba.fastjson2.JSON.toJSONString(requestParam), ex);
            }
            throw ex;
        }
    }

    @Transactional
    @Override
    public void removePassenger(PassengerRemoveReqDTO requestParam) {
        String username = "zhangsan";
        try{
            LambdaUpdateWrapper<Passenger> deleteWrapper = new LambdaUpdateWrapper<>();
            deleteWrapper.eq(Passenger::getId,requestParam.getId())
                    .eq(Passenger::getUsername,username);
            int deleted = baseMapper.delete(deleteWrapper);
            if (!SqlHelper.retBool(deleted)){
                throw new ServiceException(String.format("[%s] 删除乘车人失败", username));
            }
        }catch (Exception ex){
            if (ex instanceof ServiceException) {
                log.error("{}，请求参数：{}", ex.getMessage(), com.alibaba.fastjson2.JSON.toJSONString(requestParam));
            } else {
                log.error("[{}] 删除乘车人失败，请求参数：{}", username, com.alibaba.fastjson2.JSON.toJSONString(requestParam), ex);
            }
            throw ex;
        }
    }
}
