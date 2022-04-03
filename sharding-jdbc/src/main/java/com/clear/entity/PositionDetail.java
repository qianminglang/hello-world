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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionDetail that = (PositionDetail) o;

        if (id != that.id) return false;
        if (pid != that.pid) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (pid ^ (pid >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
