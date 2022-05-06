package com.clear.entity;

/**
 * ClassName Command
 *
 * @author qml
 * Date 2022-5-6 17:13
 * Version 1.0
 **/

public interface Command {
    //定义请求指令和响应指令1和2，其他指令类型
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
}