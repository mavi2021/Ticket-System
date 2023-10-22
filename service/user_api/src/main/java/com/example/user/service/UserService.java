package com.example.user.service;

import com.example.user.dto.req.UserAddReqDto;
import com.example.user.dto.req.UserRegisterReqDTO;
import com.example.user.dto.req.UserUpdateReqDTO;
import com.example.user.dto.resp.UserQueryRespDTO;
import jakarta.validation.constraints.NotEmpty;

/**
 * @create 2023/7/10 10:04
 */
public interface UserService {

    /**
     * 根据用户 ID 查询用户信息
     *
     * @param userId 用户 ID
     * @return 用户详细信息
     */
    UserQueryRespDTO queryUserByUserId(@NotEmpty Long userId);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    UserQueryRespDTO queryUserByUsername(@NotEmpty String username);

    /**
     * 根据用户 ID 修改用户信息
     *
     * @param requestParam 用户信息入参
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 新增用户
     *
     * @param userRegisterReqDTO 用户信息入参
     */
    void add(UserRegisterReqDTO userRegisterReqDTO);


    /**
     * 新增用户
     *
     * @param username 用户名入参
     */
    void deleteByUsername(String username);

}
