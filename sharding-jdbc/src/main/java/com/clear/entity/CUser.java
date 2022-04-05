package com.clear.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "c_user")
public class CUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Column(name = "name")
    private String name;
    //逻辑列名
    @Column(name = "pwd")
    private String pwd;
}
