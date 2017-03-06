package com.github.cruiser.enums;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属模
 * 块。 例如：无效用户可以定义为 [10010001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 *
 * @author yingjun
 */
public enum ResultEnum {

    // 数据库想操作异常
    DB_INSERT_RESULT_ERROR("DB990001", "db insert error"),
    DB_UPDATE_RESULT_ERROR("DB990002", "db update error"),
    DB_SELECTONE_IS_NULL("DB990003", "db select return null"),

    // 系统异常
    INTERNAL_SERVER_ERROR("SERV0004", "系统错误"),
    HTTP_IO_ERROR("SERV0005", "http连接失败"),
    HTTP_RESPONSE_ERROR("SERV0006", "http返回失败"),

    // 用户相关异常
    TOKEN_IS_ILLICIT("CLI00007", "Token验证非法"),
    SESSION_IS_OUT_TIME("CLI00008", "会话超时"),
    INVALID_USER("CLI00009", "无效用户"),

    // 网络异常
    // Weixin 异常 TODO

    //数据异常
    RESOURCE_NOT_EXIST("CLI00010", "资源不存在"),
    PARAM_ERROR("CLI00011", "参数错误");

    private String resultCode;
    private String resultMsg;

    ResultEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public static ResultEnum getMsgByCode(String resultCode) {
        for (ResultEnum state : values()) {
            if (resultCode.equals(state.getResultCode())) {
                return state;
            }
        }
        return null;
    }

}
