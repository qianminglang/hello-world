package com.clear.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "position_detail", schema = "lagou1", catalog = "")
public class PositionDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "pid")
    private long pid;
    @Basic
    @Column(name = "description")
    private String description;


}
