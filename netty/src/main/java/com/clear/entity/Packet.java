package com.clear.entity;

import lombok.Data;

/**
 * ClassName Packet
 *
 * @author qml
 * Date 2022-5-6 17:10
 * Version 1.0
 **/
@Data
public abstract class Packet {
    //协议版本
    private Byte version=1;

    //获取数据类型
    public abstract Byte getCommand();
}