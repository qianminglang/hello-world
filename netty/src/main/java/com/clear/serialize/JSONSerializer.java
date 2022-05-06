package com.clear.serialize;

import com.alibaba.fastjson.JSON;

/**
 * ClassName JSONSerializer
 *
 * @author qml
 * Date 2022-5-6 17:56
 * Version 1.0
 **/

public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}