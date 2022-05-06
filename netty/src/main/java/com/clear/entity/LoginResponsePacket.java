package com.clear.entity;

import lombok.Data;

import java.io.Serializable;

import static com.clear.entity.Command.LOGIN_RESPONSE;

/**
 * ClassName LoginResponsePacket
 *
 * @author qml
 * Date 2022-5-6 17:19
 * Version 1.0
 **/
@Data
public class LoginResponsePacket extends Packet implements Serializable {
    //是否登录成功
    private Boolean success;
    //如果失败，返回失败的信息
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}