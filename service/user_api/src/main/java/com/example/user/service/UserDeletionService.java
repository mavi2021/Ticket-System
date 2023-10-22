package com.example.user.service;

import com.example.user.dto.req.UserDeletionReqDTO;
import com.example.user.dto.resp.UserDeletionRespDTO;
import com.example.user.entity.UserDeletion;

/**
 * @create 2023/10/14 15:36
 */
public interface UserDeletionService {
    /**
     * 根据证件类型和证件号查询注销次数
     *
     * @param idType 证件类型
     * @param idCard 证件号
     * @return 注销次数
     */
    Integer queryUserDeletionNum(Integer idType, String idCard);

    /**
     * 注销用户
     *
     * @param requestParam 注销用户入参
     */
    void deletionUser(UserDeletionReqDTO requestParam);

    /**
     * 查询用户注销信息
     *
     * @param requestParam 注销用户入参
     */
    public UserDeletionRespDTO selectUserDeletion(UserDeletionReqDTO requestParam);

}
