package com.redis.test.redisspring.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
public class PublishModel implements Serializable {
    private String name;
    private int id;

    public PublishModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PublishModel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
