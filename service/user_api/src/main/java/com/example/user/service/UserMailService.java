package com.example.user.service;

import com.example.user.dto.req.UserLoginReqDTO;
import com.example.user.dto.req.UserMailAddDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import com.example.user.entity.User;

/**
 * @create 2023/10/14 16:59
 */
public interface UserMailService {

    String selectUserByLoginMail(UserLoginReqDTO requestParam);

    void add(UserMailAddDTO userMailAddDTO);

    void deleteByUsername(String username);
}
