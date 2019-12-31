package com.redis.test.redisspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequestMapping(method = RequestMethod.POST, value = "/programmer-string")
    public void add(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        programmerService.setProgrammerAsString(String.valueOf(programmer.getId()), mapper.writeValueAsString(programmer));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/programmer-string/{id}")
    public String readString(@PathVariable String id){
        return programmerService.getProgrammer(id);
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

}
