package com.clear.entity;

import com.clear.serialize.JSONSerializer;
import com.clear.serialize.Serializer;
import com.clear.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.clear.entity.Command.LOGIN_REQUEST;
import static com.clear.entity.Command.LOGIN_RESPONSE;

/**
 * ClassName PacketCodeC
 * 参考链接：  https://blog.csdn.net/wmq880204/article/details/114361947
 *
 * @author qml
 * Date 2022-5-6 18:00
 * Version 1.0
 **/

public class PacketCodeC {
    //自定义一个魔术
    private static final int MAGIC_NUMBER = 0x12345678;

    //创建一个静态实例供外部调用
    private static final PacketCodeC INSTANCE = new PacketCodeC();

    //创建两个map，一个存储数据类型（如是登录数据还是普通消息），第二个是存储序列化类型
    private final Map<Byte, Class<? extends Packet>> packageTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    public PacketCodeC() {
        packageTypeMap = new HashMap<>();
        packageTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packageTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, new JSONSerializer());
    }

    //编码
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        //创建ByteBuf对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        //序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //实际编码过程，将通讯协议几个部分，一一编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    //解码
    public Packet decode(ByteBuf byteBuf) {
        //跳过魔数，int是4个字节
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        //获取数据类型
        Class<? extends Packet> requestType = getRequestType(command);

        //获取序列化类型
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    //获取序列化类型
    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    //获取数据类型
    private Class<? extends Packet> getRequestType(byte command) {
        Class<? extends Packet> aClass = packageTypeMap.get(command);
        return aClass;
    }
}