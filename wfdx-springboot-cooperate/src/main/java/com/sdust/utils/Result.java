package com.sdust.utils;
/**
 *
 * 枚举类 用来将项目中会出现的成功和失败的所有情况一一列举出来
 * 并且给出指定的 公共方法来返回JsonResponse数据
 * */
public enum Result {
    SUCCESS(200,"成功"),
    ERROR(5001,"系统错误"),
    EMAIL_ERROR(5002,"验证码错误");
    public int code;
    public String msg;

    private Result(int code,String msg){
       this.code = code;
       this.msg = msg;
    }
    //封装公共的成功的方法（不带参数）
    public static<T> JsonResponse<T> success(){
        return new JsonResponse<>(Result.SUCCESS.code,Result.SUCCESS.msg, null);
    }
    //封装公共的成功的方法（带参数）
    public static<T> JsonResponse<T> success(T data){
        return new JsonResponse<>(Result.SUCCESS.code,Result.SUCCESS.msg, data);
    }

    public static<T> JsonResponse<T> error(T data){
        return new JsonResponse<>(Result.ERROR.code, Result.ERROR.msg, data);
    }
    public static<T> JsonResponse<T> error(int code,String msg,T data){
        return new JsonResponse<>(code,msg,data);
    }
}
