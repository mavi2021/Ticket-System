package com.example.user.controller;

import com.example.common.Result;
import com.example.user.service.UserService;
import com.example.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @create 2023/7/10 16:21
 */
@RestController
@RequestMapping("/user")
@Api("用户功能接口")
@RefreshScope
public class UserController {

//    @Autowired(required = false)
    @Reference(version = "1.0.0",check = false)
    private UserService userService;

//    @PostMapping("/add")
//    @ApiOperation("添加用户")
//    public Result<Object> add(@Valid @RequestBody User user){
//        return userService.add(user);
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    @ApiOperation("删除用户")
//    public Result<Object> deleteById(@Valid @PathVariable("id") Long id){
//        return userService.deleteById(id);
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新用户信息")
//    public Result<Object> update(@Valid @RequestBody User user){
//        return userService.update(user);
//    }
//
//    @GetMapping("/getUserById/{id}")
//    @ApiOperation("查询用户信息")
//    public Result<Object> getUserById(@Valid @PathVariable("id") Long id){
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/getAllByPage/{pageNum}/{pageSize}")
//    @ApiOperation("获取分页列表")
//    public Result<Object> getAllByPage(@Valid @PathVariable Long pageNum,@PathVariable Long pageSize){
//        return userService.getAllByPage(pageNum,pageSize);
//    }
}