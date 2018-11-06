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
    ADD_SUB_ERR(20008, "新增子数据错误"),
    RESER_DATA_REPEATED(20009, "不能多次预约"),
    FIRST_INPUT_PRELOAD(20010, "首填预报吨位"),

    CUSTOMER_TASK_MODIFY_FORBIDDEN(30001, "已存在客户任务流向，禁止修改"),
    CUSTOMER_TASK_DEL_FORBIDDEN(30002, "已存在客户任务流向，禁止删除"),

    CUSTOMER_TASK_FLOW_MODIFY_FORBIDDEN(30003, "已存在任务调度，禁止修改"),
    CUSTOMER_TASK_FLOW_DEL_FORBIDDEN(30004, "已存在任务调度，禁止删除"),

    DISPATCH_MODIFY_FORBIDDEN(30005, "已存在航次信息，禁止修改"),
    DISPATCH_DEL_FORBIDDEN(30006, "已存在航次信息，禁止删除"),

    WECHAT_NOT_BIND(40000, "未绑定"),
    WECHAT_CODE_EMPTY(40001, "未指定code"),
    WECHAT_APP_TYPE_INVALID(40002, "指定的appType无效"),
    WECHAT_GET_OPENID_ERR(40003, "获取openId失败"),
    WECHAT_ACCESS_TOKEN_EMPTY(40004, "无access token"),
    WECHAT_GET_UNIONID_ERR(40005, "获取openId失败"),
    WECHAT_BIND_USER_NOT_EXIST(40006, "未获取到绑定的用户信息"),
    WECHAT_ALREADY_BIND(40007, "已被绑定"),
    WECHAT_BIND_ERR(40008, "绑定操作失败"),
    WECHAT_UNBIND_ERR(40009, "解绑操作失败"),
    WECHAT_SYS_USER_BIND_BY_OTHER_ERR(40010, "当前系统账号已被其他微信账号绑定"),

    WEIXIN_AUTHORITY_FORBIDDEN(70000,"用户禁止授权"),

    AUTHORITY_SETTING_ERR(89999,"设置角色权限错误"),

	AUTHTICATION_NOT_EXIST(90000,"账号不存在"),
	AUTHTICATION_PASSWD_ERROR(90001,"密码错误"),
	AUTHTICATION_TOKEN_EXPIRE(90002,"Token已过期"),
	AUTHTICATION_TOKEN_ERROR(90003,"非法Token"),
	AUTHTICATION_FAIL(99999,"认证失败")
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
