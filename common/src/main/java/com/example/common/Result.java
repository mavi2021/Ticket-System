package com.example.common;

import lombok.Data;

@Data
public class Result<T> {

    private Result(){}

    public Result(Integer code, String message, T data, Long count) {
        this.status = code;
        this.msg = message;
        this.obj = data;
        this.count = count;
    }

    private Integer status;   //返回码,200成功
    private String msg; //返回描述
    private T obj;         //返回数据
    private Long count;     //分页查询的总记录数

    public static Result<Void> success(){
        return new Result(200,"success",null,null);
    }

    public static Result<Object> success(String message){
        return new Result(200,message,null,null);
    }

    public static <T> Result<T> success(Object data){
        return new Result(200,null,data,null);
    }

    public static Result<Object> success(Object data, Long count){
        return new Result(200,"success",data,count);
    }

    public static Result<Object> success(String message,Object data, Long count){
        return new Result(200,message,data,count);
    }

    public static Result<Object> success(String message,Object data){
        return new Result(200,"success",data,null);
    }

    public static Result<Object> fail(){
        return new Result(400,"fail",null,null);
    }

    public static Result<Object> fail(String message){
        return new Result(400,message,null,null);
    }

    public static Result<Object> error(){
        return new Result(500,"fail",null,null);
    }

    public static Result<Object> error(String message){
        return new Result(500,message,null,null);
    }

}
