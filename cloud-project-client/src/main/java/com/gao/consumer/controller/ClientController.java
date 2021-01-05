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
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private IFeignServer iFeignServer;

    @PostMapping("/saveUser")
    public List<User> saveUser(@RequestParam Map<String,Object> map){
        List<User> resMap = (List<User>)iFeignServer.excuteForPost("saveUser",map);
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
