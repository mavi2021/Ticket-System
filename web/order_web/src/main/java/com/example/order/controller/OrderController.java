//package com.example.order.controller;
//
//import com.example.order.entity.Order;
//import com.example.order.service.OrderService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.dubbo.config.annotation.Reference;
//import org.springframework.web.bind.annotation.*;
//import com.example.common.Result;
//
//import javax.validation.Valid;
//
///**
// * @create 2023/7/11 15:27
// */
//@RestController
//@RequestMapping("/order")
//@Api("订单功能接口")
//public class OrderController {
////    @Autowired(required = false)
//    @Reference(version = "1.0.0",check = false)
////    @Resource
//    private OrderService orderService;
//
//    @PostMapping("/add")
//    @ApiOperation("添加订单")
//    public Result<Object> add(@Valid @RequestBody Order order){
//        return orderService.add(order);
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    @ApiOperation("删除订单")
//    public Result<Object> deleteById(@Valid @PathVariable("id") Long id){
//        return orderService.deleteById(id);
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新订单")
//    public Result<Object> update(@Valid @RequestBody Order order){
//        return orderService.update(order);
//    }
//
//    @GetMapping("/getOrderById/{id}")
//    @ApiOperation("查询订单")
//    public Result<Object> getOrderById(@Valid @PathVariable("id") Long id){
//        return orderService.getOrderById(id);
//    }
//
//    @GetMapping("/getOrderByUserId/{id}")
//    @ApiOperation("根据用户id查询名下所有订单")
//    public Result<Object> getOrdersByUserId(@Valid @PathVariable("id") Long id){
//        return orderService.getOrdersByUserId(id);
//    }
//
//    @DeleteMapping("/deleteOrdersByUserId/{id}")
//    @ApiOperation("根据用户id查询名下所有订单")
//    public Result<Object> deleteOrdersByUserId(@Valid @PathVariable("id") Long id){
//        return orderService.deleteOrdersByUserId(id);
//    }
//
//    @GetMapping("/getAllByPage/{pageNum}/{pageSize}")
//    @ApiOperation("获取分页列表")
//    public Result<Object> getAllByPage(@Valid @PathVariable Long pageNum,@PathVariable Long pageSize){
//        return orderService.getAllByPage(pageNum,pageSize);
//    }
//}
