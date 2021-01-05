package com.gao.consumer.controller;

import com.gao.feign.IFeignServer;
import com.gao.model.User;
import com.gao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private IFeignServer iFeignServer;

    @PostMapping("/saveUser")
    public Map<String, Object> saveUser(@RequestParam Map<String,Object> map){
        Map<String, Object> resMap = iFeignServer.excuteForPost("saveUser",map);
        logger.info("resMap[{}]",resMap);
        return resMap;
    }
    @PostMapping("/iFeignServer")
    public Map<String, Object> iFeignServer(){
        Map<String, Object> resMap = iFeignServer.consuleUser();
        logger.info("resMap[{}]",resMap);
        return resMap;
    }
}
