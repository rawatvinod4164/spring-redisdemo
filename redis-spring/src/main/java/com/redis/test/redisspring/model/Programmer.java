package com.redis.test.redisspring.model;


import java.io.Serializable;
import java.util.Objects;

public class Programmer implements Serializable {
    private int id;
    private String compnay;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompnay() {
        return compnay;
    }

    public void setCompnay(String compnay) {
        this.compnay = compnay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id &&
                Objects.equals(compnay, that.compnay) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, compnay, name);
    }
}
