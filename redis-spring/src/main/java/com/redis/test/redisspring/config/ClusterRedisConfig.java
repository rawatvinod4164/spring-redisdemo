package com.redis.test.redisspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ClusterRedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.cluster.max-redirects}")
    private int maxRedirects;

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public String getNodes() {
        return nodes;
    }
    public List<String> getNodesList(){
        return Arrays.asList(this.nodes.split(","));
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}

