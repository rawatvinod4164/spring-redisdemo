package com.redis.test.redisspring.services;

import com.redis.test.redisspring.dao.ProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProgrammerServiceClusterImpl {
    @Autowired
    @Qualifier(value = "programmerDaoClusterImpl")
    ProgrammerRepository programmerRepository;
    public void  setProgrammerAsString(String idKey, String programmer){
        programmerRepository.setProgrammerAsString(idKey,programmer);
    }
    public String getProgrammer(String idKey){
        return programmerRepository.getProgrammer(idKey);
    }
}
