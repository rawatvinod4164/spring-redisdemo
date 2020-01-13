package com.redis.test.redisspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.test.redisspring.dao.ProgrammerDao;
import com.redis.test.redisspring.dao.ProgrammerDaoClusterRedisTemplate;
import com.redis.test.redisspring.model.Programmer;
import com.redis.test.redisspring.services.ProgrammerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgrammerController {
    @Autowired
    ProgrammerServiceImpl programmerService;
    @Autowired
    ProgrammerServiceImpl programmerServiceCluster;
    @Autowired
    ProgrammerDaoClusterRedisTemplate programmerDaoClusterRedisTemplate;
    @Autowired
    ProgrammerDao programmerDao;

    @RequestMapping(method = RequestMethod.POST, value = "/programmer-string")
    public void add(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        programmerDao.setProgrammerAsString(String.valueOf(programmer.getId()), mapper.writeValueAsString(programmer));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/programmer-string/{id}")
    public String readString(@PathVariable String id){
        return programmerDao.getProgrammer(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/programmer-string/cluster")
    public void addToCluster(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        programmerServiceCluster.setProgrammerAsString(String.valueOf(programmer.getId()), mapper.writeValueAsString(programmer));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/programmer-string/cluster/{id}")
    public String readStringFromCluster(@PathVariable String id){
        return programmerServiceCluster.getProgrammer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/programmer-string/cluster/rt")
    public void addToClusterRedisTemplate(@RequestBody Programmer programmer) throws JsonProcessingException {
        programmerDaoClusterRedisTemplate.setProgrammer(String.valueOf(programmer.getId()),programmer);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/programmer-string/cluster/rt/{id}")
    public Programmer readStringFromClusterRedisTemplate(@PathVariable String id){
        return programmerDaoClusterRedisTemplate.getProgrammer(id);
    }

}
