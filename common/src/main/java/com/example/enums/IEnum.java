package com.example.enums;

import java.io.Serializable;

/**
 * ClassName IEnum
 *
 * @author qml
 * Date 2022/2/24 10:28
 * Version 1.0
 **/

public interface IEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();
}
