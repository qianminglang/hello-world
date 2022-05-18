package com.clear.queue;

public class KouZhao {
    private String Type;
    private Integer id;

    public KouZhao(String type, Integer id) {
        Type = type;
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
