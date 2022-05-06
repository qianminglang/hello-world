package com.clear.serialize;

/**
 * ClassName SerializerAlgorithm
 *
 * @author qml
 * Date 2022-5-6 17:37
 * Version 1.0
 **/

public interface SerializerAlgorithm {
    //json序列化标识，如果你有其他的序列化方式可以在这里注明标识，类似上面的登录指令
    byte JSON = 1;
}