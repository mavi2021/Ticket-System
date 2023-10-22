package com.example.user.controller;

import com.example.common.Result;
import com.example.user.dto.req.PassengerRemoveReqDTO;
import com.example.user.dto.req.PassengerReqDTO;
import com.example.user.dto.resp.PassengerActualRespDTO;
import com.example.user.dto.resp.PassengerRespDTO;
import com.example.user.service.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @create 2023/9/20 22:17
 */
@RestController
@Api("乘客功能接口")
@RefreshScope
public class PassengerController {

    @Reference(version = "1.0.0", check = false)
    private PassengerService passengerService;

    @PostMapping("/api/user-service/passenger/save")
    @ApiOperation("新增乘车人")
    public Result<Void> add(@Valid @RequestBody PassengerReqDTO requestParam){
        passengerService.savePassenger(requestParam);
        return Result.success();
    }

//    @DeleteMapping("/api/user-service/passenger/remove")
    @PostMapping("/api/user-service/passenger/remove")
    @ApiOperation("移除乘车人")
    public Result<Void> deleteById(@Valid @RequestBody PassengerRemoveReqDTO requestParam){
        passengerService.removePassenger(requestParam);
        return Result.success();
    }

//    @PutMapping("/api/user-service/passenger/update")
    @PostMapping("/api/user-service/passenger/update")
    @ApiOperation("修改乘车人")
    public Result<Void> update(@Valid @RequestBody PassengerReqDTO requestParam){
        passengerService.updatePassenger(requestParam);
        return Result.success();
    }

    @GetMapping("/api/user-service/passenger/query")
    @ApiOperation("根据用户名查询乘车人列表")
    public Result<List<PassengerRespDTO>> getUserById(@Valid @RequestParam("username") String username){
        List<PassengerRespDTO> passengerRespDTOS = passengerService.listPassengerQueryByUsername(username);
        return Result.success(passengerRespDTOS);
    }

    @GetMapping("/api/user-service/inner/passenger/actual/query/ids")
    @ApiOperation("根据乘车人ID集合查询乘车人列表")
    public Result<List<PassengerActualRespDTO>> getAllByPage(@Valid @RequestParam("username") String username, @RequestParam("ids") List<Long> ids){
        List<PassengerActualRespDTO> passengerActualRespDTOS = passengerService.listPassengerQueryByIds(username, ids);
        return Result.success(passengerActualRespDTOS);
    }
}
