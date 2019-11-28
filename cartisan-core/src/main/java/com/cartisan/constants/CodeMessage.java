package com.cartisan.constants;

import lombok.Getter;
import lombok.ToString;

/**
 * @author colin
 */
@Getter
@ToString
public class CodeMessage {
    /**
     * 模仿 Http status 约定：
     * 2 开头：表示成功
     *  通用成功 code：20000
     * 4 开头：表示请求参数错误，多用于参数校验
     * 5 开头：表示服务端处理错误，多为不符合业务规则
     *
     * code 一共 5 位
     *  第一位：表示成功或失败
     *  第二三位：表示具体业务编号
     *      00~09：通用业务
     *  第四五位：表示具体操作
     *      00~09：通用操作相关
     *      10~29：添加操作相关
     *      30~49：更新操作相关
     *      50~69：删除操作相关
     *      70~99：其它
     *
     * 不同业务间的 code 允许重复，同一业务内 code 不允许重复
     * 00 往往代表普适或不具体
     */
    private Integer code;
    private String message;

    protected CodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessage fillArgs(String... args) {
        Integer code = this.code;
        String message = String.format(this.message, (Object[]) args);

        return new CodeMessage(code, message);
    }
}
