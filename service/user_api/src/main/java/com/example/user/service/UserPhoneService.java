package com.example.user.service;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserPhoneAddDTO;
import com.example.user.entity.UserPhone;

/**
 * @create 2023/10/14 22:00
 */
public interface UserPhoneService {

    String selectUserByLoginPhone(UserLoginReqDTO requestParam);

    void add(UserPhoneAddDTO userPhoneAddDTO);

    void deleteByUsername(String username);
}
