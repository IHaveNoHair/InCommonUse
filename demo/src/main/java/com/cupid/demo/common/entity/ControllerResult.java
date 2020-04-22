package com.cupid.demo.common.entity;

import lombok.Data;

/**
 * controller层返回值统一处理类
 * @author cupid
 * @date 2020-04-17
 */
@Data
public class ControllerResult<T> {
    /**
     * 返回类型编码
     * int类型
     */
    private int code;

    /**
     * 返回值信息
     * String类型
     */
    private String msg;

    /**
     * 返回值详情
     * 根据泛型确定
     */
    private T data;

    /**
     * 成功的时候调用
     * @param data 返回数据
     * @param <T> 泛型
     * @return 返回值
     */
    public static<T> ControllerResult<T> success( T data){
        return new ControllerResult<T>(data);
    }

    /**
     * 失败的时候调用
     * @param codeMsg 返回数据
     * @param <T> 泛型
     * @return 返回值
     */
    public static<T> ControllerResult<T> error( CodeMsg codeMsg){
        return new ControllerResult<T>(codeMsg);
    }

    /**
     * 成功时的构造函数
     * @param data 返回数据
     */
    private ControllerResult(T data){
        this.code = 1;
        this.msg = "SUCCESS";
        this.data = data;
    }

    /**
     * 成功的构造函数
     * @param code 返回类型编码
     * @param msg 返回信息
     */
    private ControllerResult(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ControllerResult() {
    }

    private ControllerResult(CodeMsg codeMsg){
        if (codeMsg != null){
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public ControllerResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
}
