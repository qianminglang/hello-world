package com.clear.entity;

import lombok.Data;

import static com.clear.entity.Command.LOGIN_REQUEST;

/**
 * ClassName LoginRequestPacket
 *
 * @author qml
 * Date 2022-5-6 17:15
 * Version 1.0
 **/
@Data
public class LoginRequestPacket extends Packet {
    //定义用户信息
    private Integer userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}