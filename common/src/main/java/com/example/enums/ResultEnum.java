package com.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName ResultEnum
 *
 * @author qml
 * Date 2022/2/24 10:20
 * Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum implements Enumerator{
    SUCCESS(0, "操作成功"),

    FAIL(1, "操作失败"),

    FORBIDDEN(403, "权限不足"),

    BAD_REQUEST(400, "错误的请求"),

    UNAUTHORIZED(401, "Unauthorized");



    private final Integer value;

    private final String desc;
}