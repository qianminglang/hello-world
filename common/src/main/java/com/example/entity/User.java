package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName User
 *
 * @author qml
 * Date 2022/2/25 10:36
 * Version 1.0
 **/
@Data
public class User implements Serializable {
    private String userName;
    private String age;
}