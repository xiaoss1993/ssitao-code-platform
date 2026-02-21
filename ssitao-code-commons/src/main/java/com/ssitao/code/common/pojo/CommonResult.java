package com.ssitao.code.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误提示
     */
    private String msg;

    /**
     * 将传入的 result 对象转换成 CommonResult
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setData(data);
        result.setMsg("success");
        return result;
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
