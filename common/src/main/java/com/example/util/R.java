package com.example.util;

import cn.hutool.core.util.StrUtil;
import com.example.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import static com.example.enums.ResultEnum.SUCCESS;

/**
 * ClassName R
 *
 * @author qml
 * Date 2022/2/24 9:54
 * Version 1.0
 **/
@Data
@ApiModel("返回信息")
public class R<T> implements Serializable {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("数据")
    private T data;

    @ApiModelProperty("提示信息")
    private String msg;

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public R() {
    }

    public static <T> R<T> success() {
        return restResult(null, SUCCESS);
    }

    public static <T> R<T> success(T data) {
        return restResult(data, SUCCESS);
    }

    private static final <T> R<T> restResult(final T data, final ResultEnum resultEnum) {
        return restResult(data, resultEnum, null);
    }

    private static final <T> R<T> restResult(final T data, final ResultEnum resultEnum, final String msg) {
        final R<T> apiResult = new R<>();
        apiResult.setCode(resultEnum.getValue());
        apiResult.setData(data);
        apiResult.setMsg(StrUtil.blankToDefault(msg, resultEnum.getDesc()));
        return apiResult;
    }

}