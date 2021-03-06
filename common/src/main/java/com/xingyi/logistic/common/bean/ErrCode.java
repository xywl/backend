package com.xingyi.logistic.common.bean;

/**
 * Created by Jadic on 2018/1/2.
 */
public enum ErrCode {

    PARAM_MISS(10000, "参数为空"),
    PARAM_INVALID(10001, "参数不合法"),

    DATA_REPEATED(20001, "数据重复"),
    DATA_NOT_EXIST(20002, "数据不存在"),
    ID_INVALID(20003, "ID不合法"),
    ADD_ERR(20004, "添加错误"),
    MODIFY_ERR(20005, "修改错误"),
    DEL_ERR(20006, "删除错误"),
    GET_ERR(20007, "查询错误"),
    ;
    private int code;
    private String msg;

    ErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
