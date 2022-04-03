package com.clear.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "b_order")
@Data
public class BOrder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "is_del")
    private boolean isDel;
    @Basic
    @Column(name = "company_id")
    private int companyId;
    @Basic
    @Column(name = "position_id")
    private long positionId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "publish_user_id")
    private int publishUserId;
    @Basic
    @Column(name = "resume_type")
    private int resumeType;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "create_time")
    private Date createTime;
    @Basic
    @Column(name = "operate_time")
    private Date operateTime;
    @Basic
    @Column(name = "work_year")
    private String workYear;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "position_name")
    private String positionName;
    @Basic
    @Column(name = "resume_id")
    private Integer resumeId;

}
