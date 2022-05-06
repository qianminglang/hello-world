package com.clear.serialize;

import com.alibaba.fastjson.serializer.JSONSerializer;

/**
 * ClassName Serializer
 * 序列化接口
 *
 * @author qml
 * Date 2022-5-6 17:23
 * Version 1.0
 **/

public interface Serializer {
    JSONSerializer DEFAULT = new JSONSerializer();

    //序列化算法
    byte getSerializerAlogrithm();

    //java对象二进制转换
    byte[] serialize(Object object);

    //二进制转换成java对象
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}