package com.cupid.demo.common.entity;

import lombok.Data;

/**
 * controller层返回值统一处理类
 * @author cupid
 * @date 2020-04-17
 */
@Data
public class CodeMsg {
    /**
     * 返回类型编码
     */
    private int code;
    /**
     * 返回类型信息
     */
    private String msg;

    //通用编码
    /**
     * 成功
     */
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    /**
     * 服务端异常
     */
    public static CodeMsg SERVER_ERROR  = new CodeMsg(500100,"服务端异常");
    /**
     * 参数校验异常
     */
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常 : %s");

    /**
     * 可变餐构造
     * @param args 参数
     * @return 返回CodeMsg对象
     */
    public CodeMsg fillArgs(Object... args){
        int code = this.code;
        String message = String.format(this.msg,args);
        return new CodeMsg(code,message);
    }

    /**
     * 无参构造
     */
    public CodeMsg() {
    }

    /**、
     * 全参构造
     * @param code 编码
     * @param msg 内容
     */
    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
//    public static CodeMsg getSUCCESS() {
//        return SUCCESS;
//    }
//
//    public static void setSUCCESS(CodeMsg SUCCESS) {
//        CodeMsg.SUCCESS = SUCCESS;
//    }
//
//    public static CodeMsg getServerError() {
//        return SERVER_ERROR;
//    }
//
//    public static void setServerError(CodeMsg serverError) {
//        SERVER_ERROR = serverError;
//    }
//
//    public static CodeMsg getBindError() {
//        return BIND_ERROR;
//    }
//
//    public static void setBindError(CodeMsg bindError) {
//        BIND_ERROR = bindError;
//    }
}
